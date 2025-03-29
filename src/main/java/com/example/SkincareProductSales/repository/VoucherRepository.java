package com.example.SkincareProductSales.repository;

import com.example.SkincareProductSales.entity.Voucher;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface VoucherRepository extends JpaRepository<Voucher, Long> {
    Voucher findVoucherById(long id);

    Voucher findVoucherByCode(String code);

    List<Voucher> findVouchersByIsDeletedFalse();

    List<Voucher> findVouchersByIsDeletedTrue();
}
