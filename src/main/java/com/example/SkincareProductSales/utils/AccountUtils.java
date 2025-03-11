package com.example.SkincareProductSales.utils;

import com.example.SkincareProductSales.entity.Account;
import com.example.SkincareProductSales.repository.AuthenticationRepository;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

@Component
public class AccountUtils implements ApplicationContextAware {

    private static AuthenticationRepository userRepo;


    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        userRepo = applicationContext.getBean(AuthenticationRepository.class);
    }

    public Account getCurrentAccount() {
        String email = SecurityContextHolder.getContext().getAuthentication().getName();

        return userRepo.findByEmail(email).orElseThrow() ;
    }

}
