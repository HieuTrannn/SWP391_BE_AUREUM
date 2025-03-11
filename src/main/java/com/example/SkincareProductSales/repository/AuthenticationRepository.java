package com.example.SkincareProductSales.repository;

import com.example.SkincareProductSales.entity.Account;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

// nguyên lí mô hình 3 lớp
// 1. authen api: call api
// 2. authen service: xử lí logic
// 3. authen repository: lưu xuống database
public interface AuthenticationRepository extends JpaRepository<Account, Long> {

    Account findById (long id);

    Optional<Account> findByEmail(String email);

}
