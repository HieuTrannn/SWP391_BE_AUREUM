package com.example.SkincareProductSales.service;

import com.example.SkincareProductSales.entity.Voucher;
import com.example.SkincareProductSales.entity.request.VoucherRequest;
import com.example.SkincareProductSales.enums.DiscountTypeEnum;
import com.example.SkincareProductSales.enums.VoucherStatusEnum;
import com.example.SkincareProductSales.repository.VoucherRepository;
import jakarta.persistence.EntityNotFoundException;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class VoucherService {

    @Autowired
    VoucherRepository voucherRepository;

    @Autowired
    ModelMapper modelMapper;

    public Voucher createVoucher(VoucherRequest voucherRequest) {
        Voucher voucher = modelMapper.map(voucherRequest, Voucher.class);

        voucher.setVoucherStatusEnum(VoucherStatusEnum.ACTIVE);
        return voucherRepository.save(voucher);
    }

    public float applyVoucher(String code, float totalAmount) {
        Voucher voucher = voucherRepository.findVoucherByCode(code);
        if (voucher == null) {
            throw new EntityNotFoundException("Voucher không tồn tại");
        }

        if (voucher.getVoucherStatusEnum() != VoucherStatusEnum.ACTIVE) {
            throw new IllegalArgumentException("Voucher không còn hiệu lực");
        }

        if (voucher.getExpiryDate().isBefore(LocalDate.now())) {
            throw new IllegalArgumentException("Voucher đã hết hạn");
        }

        if (totalAmount < voucher.getMinOrderValue()) {
            throw new IllegalArgumentException("Giá trị đơn hàng không đủ để áp dụng voucher");
        }

        float discount = voucher.getDiscountPrice();
        if (voucher.getDiscountTypeEnum() == DiscountTypeEnum.PERCENT) {
            discount = (totalAmount * discount) / 100;
        }

        return Math.max(totalAmount - discount, 0); // Đảm bảo tổng tiền không âm
    }


    public Voucher getVoucherById(long voucherId) {
        Voucher currentVoucher = voucherRepository.findVoucherById(voucherId);
        if (currentVoucher == null) {
            throw new EntityNotFoundException("Voucher Not Found");
        }

        return currentVoucher;
    }

    public List<Voucher> getAllVouchers() {
        return voucherRepository.findVouchersByIsDeletedFalse();
    }

    public List<Voucher> getAllVouchersIsDeleted() {
        return voucherRepository.findVouchersByIsDeletedTrue();
    }

    public Voucher updateVoucher(long voucherId, VoucherRequest voucherRequest) {
        Voucher currentVoucher = getVoucherById(voucherId);

        // Kiểm tra nếu là PERCENT thì discountPrice không được lớn hơn 100
        if (voucherRequest.getDiscountTypeEnum() == DiscountTypeEnum.PERCENT &&
                voucherRequest.getDiscountPrice() > 100) {
            throw new IllegalArgumentException("Discount percentage cannot exceed 100%");
        }

        currentVoucher.setCode(voucherRequest.getCode());
        currentVoucher.setDiscountPrice(voucherRequest.getDiscountPrice());
        currentVoucher.setMinOrderValue(voucherRequest.getMinOrderValue());
        currentVoucher.setExpiryDate(voucherRequest.getExpiryDate());
        currentVoucher.setDiscountTypeEnum(voucherRequest.getDiscountTypeEnum()); // Cập nhật discount type
        currentVoucher.setVoucherStatusEnum(voucherRequest.getVoucherStatusEnum()); // Cập nhật status

        return voucherRepository.save(currentVoucher);
    }

    public Voucher deleteVoucher(long voucherId) {
        Voucher currentVoucher = getVoucherById(voucherId);

        if (currentVoucher == null) {
            throw new EntityNotFoundException("Voucher Not Found");
        }

        currentVoucher.setDeleted(true);
        return voucherRepository.save(currentVoucher);
    }

    public Voucher updateVoucherStatus (long voucherId, VoucherStatusEnum status) {
        Voucher currentVoucher = voucherRepository.findVoucherById(voucherId);
        currentVoucher.setVoucherStatusEnum(status);

        return voucherRepository.save(currentVoucher);
    }
}
