package com.example.SkincareProductSales.service;

import com.example.SkincareProductSales.entity.*;
import com.example.SkincareProductSales.entity.request.OrderDetailRequest;
import com.example.SkincareProductSales.entity.request.OrderRequest;
import com.example.SkincareProductSales.enums.DiscountTypeEnum;
import com.example.SkincareProductSales.enums.OrderStatus;
import com.example.SkincareProductSales.enums.VoucherStatusEnum;
import com.example.SkincareProductSales.repository.OrderRepository;
import com.example.SkincareProductSales.repository.ProductRepository;

import com.example.SkincareProductSales.repository.VoucherRepository;
import com.example.SkincareProductSales.utils.AccountUtils;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

@Service
public class OrderService {

    @Autowired
    OrderRepository orderRepository;

    @Autowired
    ModelMapper modelMapper;

    @Autowired
    ProductRepository productRepository;

    @Autowired
    AccountUtils accountUtils;

    @Autowired
    VoucherRepository voucherRepository;


    public Order updateStatus(OrderStatus status, long id){
        Order order = orderRepository.findOrderById(id);
        order.setStatus(status);
        return orderRepository.save(order);
    }

    public boolean isVoucherValid(Voucher voucher) {
        if (voucher == null) return false;

        // Kiểm tra hết hạn
        if (voucher.getExpiryDate().isBefore(LocalDate.now())) {
            voucher.setVoucherStatusEnum(VoucherStatusEnum.EXPIRED);
            voucherRepository.save(voucher);
            return false;
        }

        // Kiểm tra nếu voucher đã được sử dụng
        return voucher.getVoucherStatusEnum() == VoucherStatusEnum.ACTIVE;
    }



    public String create(OrderRequest orderRequest) throws Exception{

        float total = 0;
        float discountAmount = 0; // Số tiền giảm giá từ voucher

        List<OrderDetail> orderDetails = new ArrayList<>();
        Order order = modelMapper.map(orderRequest, Order.class);
        order.setAccount(accountUtils.getCurrentAccount());

        for (OrderDetailRequest orderDetailRequest: orderRequest.getDetails()){
            OrderDetail orderDetail = new OrderDetail();
            Product product = productRepository.findProductById(orderDetailRequest.getProductId());

             if (product.getQuantity() >= orderDetailRequest.getQuantity()){
                orderDetail.setProduct(product);
                orderDetail.setQuantity(orderDetailRequest.getQuantity());
                orderDetail.setPrice(product.getPrice());
                orderDetail.setOrder(order);
                orderDetails.add(orderDetail);
                product.setQuantity(product.getQuantity() - orderDetailRequest.getQuantity());
                productRepository.save(product);
                total += orderDetail.getPrice() * orderDetailRequest.getQuantity();
            }else {
                throw new RuntimeException("Product was sold out!!");
            }

            // Kiểm tra & áp dụng voucher nếu có
            if (orderRequest.getVoucherCode() != null && !orderRequest.getVoucherCode().isEmpty()) {
                Voucher voucher = voucherRepository.findVoucherByCode(orderRequest.getVoucherCode())
                        .orElseThrow(() -> new RuntimeException("Invalid voucher code"));

                // Kiểm tra voucher hợp lệ
                if (!isVoucherValid(voucher)) {
                    throw new RuntimeException("Voucher is not valid or has expired");
                }

                // Áp dụng giảm giá
                if (voucher.getDiscountTypeEnum() == DiscountTypeEnum.PERCENT) {
                    discountAmount = total * (voucher.getDiscountPrice() / 100);
                } else {
                    discountAmount = voucher.getDiscountPrice();
                }

                // Liên kết voucher với đơn hàng
                order.setVoucher(voucher);
            }

            // Tính tổng tiền sau giảm giá
            float finalTotal = total - discountAmount;

            // Lưu thông tin vào đơn hàng


            // **Cập nhật trạng thái voucher thành USED**
            if (order.getVoucher() != null) {
                Voucher usedVoucher = order.getVoucher();
                usedVoucher.setVoucherStatusEnum(VoucherStatusEnum.USED);
                voucherRepository.save(usedVoucher);
            }
            order.setDiscountAmount(order.discountAmount);
            order.setFinalTotal(finalTotal);
            order.setOrderDetails(orderDetails);
            order.setTotal(finalTotal);
        }


        Order newOrder = orderRepository.save(order);
        return createUrlPayment(newOrder);
    }

    public List<Order> getOrdersByUser(){
        Account account = accountUtils.getCurrentAccount();
        return orderRepository.findAllByAccountId(account.getId());
    }

    public List<Order> getALl(){
        return orderRepository.findAll();
    }


    public String createUrlPayment(Order order) throws Exception{
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMddHHmmss");
        LocalDateTime createDate = LocalDateTime.now();
        String formattedCreateDate = createDate.format(formatter);
        String orderId = UUID.randomUUID().toString().substring(0, 6);

        String tmnCode = "9DZT14DS";
        String secretKey = "ND04E7FEO088030GBYXWTV917RTEDN33";
        String vnpUrl = "https://sandbox.vnpayment.vn/paymentv2/vpcpay.html";
        String returnUrl = "http://localhost:5173/payment-result?orderId=" +order.getId();

        String currCode = "VND";
        Map<String, String> vnpParams = new TreeMap<>();
        vnpParams.put("vnp_Version", "2.1.0");
        vnpParams.put("vnp_Command", "pay");
        vnpParams.put("vnp_TmnCode", tmnCode);
        vnpParams.put("vnp_Locale", "vn");
        vnpParams.put("vnp_CurrCode", currCode);
        vnpParams.put("vnp_TxnRef", orderId);
        vnpParams.put("vnp_OrderInfo", "Thanh toan cho ma GD: " + orderId);
        vnpParams.put("vnp_OrderType", "other");
        vnpParams.put("vnp_Amount",(int) order.getTotal() + "00");
        vnpParams.put("vnp_ReturnUrl", returnUrl);
        vnpParams.put("vnp_CreateDate", formattedCreateDate);
        vnpParams.put("vnp_IpAddr", "167.99.74.201");
        System.out.println("order.getTotal(): " + order.getTotal());
        System.out.println("vnp_Amount: " + (int) order.getTotal() * 100);

        StringBuilder signDataBuilder = new StringBuilder();
        for (Map.Entry<String, String> entry : vnpParams.entrySet()) {
            signDataBuilder.append(URLEncoder.encode(entry.getKey(), StandardCharsets.UTF_8.toString()));
            signDataBuilder.append("=");
            signDataBuilder.append(URLEncoder.encode(entry.getValue(), StandardCharsets.UTF_8.toString()));
            signDataBuilder.append("&");
        }
        signDataBuilder.deleteCharAt(signDataBuilder.length() - 1); // Remove last '&'

        String signData = signDataBuilder.toString();
        String signed = generateHMAC(secretKey, signData);

        vnpParams.put("vnp_SecureHash", signed);

        StringBuilder urlBuilder = new StringBuilder(vnpUrl);
        urlBuilder.append("?");
        for (Map.Entry<String, String> entry : vnpParams.entrySet()) {
            urlBuilder.append(URLEncoder.encode(entry.getKey(), StandardCharsets.UTF_8.toString()));
            urlBuilder.append("=");
            urlBuilder.append(URLEncoder.encode(entry.getValue(), StandardCharsets.UTF_8.toString()));
            urlBuilder.append("&");
        }
        urlBuilder.deleteCharAt(urlBuilder.length() - 1); // Remove last '&'
        return urlBuilder.toString();
    }

    private String generateHMAC(String secretKey, String signData) throws NoSuchAlgorithmException, InvalidKeyException {
        Mac hmacSHh512 = Mac.getInstance("HmacShA512");
        SecretKeySpec keySpec = new SecretKeySpec(secretKey.getBytes(StandardCharsets.UTF_8), "hmacShA512");
        hmacSHh512.init(keySpec);
        byte[] hmacBytes = hmacSHh512.doFinal(signData.getBytes(StandardCharsets.UTF_8));

        StringBuilder result = new StringBuilder();
        for (byte b : hmacBytes) {
            result.append(String.format("%02x", b));
        }
        return result.toString();
    }
}
