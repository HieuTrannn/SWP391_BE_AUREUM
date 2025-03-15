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

        currentVoucher.setCode(voucherRequest.getCode());
        currentVoucher.setDiscountPrice(voucherRequest.getDiscountPrice());
        currentVoucher.setMinOrderValue(voucherRequest.getMinOrderValue());
        currentVoucher.setExpiryDate(voucherRequest.getExpiryDate());

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
