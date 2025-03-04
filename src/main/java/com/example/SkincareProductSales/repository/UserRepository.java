package com.example.SkincareProductSales.repository;

import com.example.SkincareProductSales.entity.Account;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserRepository extends JpaRepository<Account, Long> {

    Account findAccountById(long id);

}
