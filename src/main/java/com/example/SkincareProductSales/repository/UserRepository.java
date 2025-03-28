package com.example.SkincareProductSales.repository;

import com.example.SkincareProductSales.entity.Account;
import com.example.SkincareProductSales.enums.RoleEnum;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface UserRepository extends JpaRepository<Account, Long> {

    Account findAccountById(long id);

    @Query("select count(a) from Account a where a.roleEnum = :roleEnum")
    long countByRole(@Param("roleEnum") RoleEnum roleEnum);
}
