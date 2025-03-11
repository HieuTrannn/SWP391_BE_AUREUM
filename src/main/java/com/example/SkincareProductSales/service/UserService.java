package com.example.SkincareProductSales.service;

import com.example.SkincareProductSales.entity.Account;
import com.example.SkincareProductSales.entity.Order;
import com.example.SkincareProductSales.entity.request.UserRequest;
import com.example.SkincareProductSales.enums.OrderStatusEnum;
import com.example.SkincareProductSales.enums.RoleEnum;
import com.example.SkincareProductSales.enums.SkinTypeEnum;
import com.example.SkincareProductSales.repository.UserRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    @Autowired
    UserRepository userRepository;

    public List<Account> getAllUsers() {
        return userRepository.findAll();
    }

    public Account getAccountById(long userId) {
        Account account = userRepository.findAccountById(userId);
        if (account == null) {
            throw new EntityNotFoundException("User with id " + userId + " not found");
        }
        return account;
    }

    public Account updateUser (long userId, UserRequest userRequest) {
        Account currentUser = userRepository.findAccountById(userId);

       currentUser.setFullName(userRequest.getFullName());
       currentUser.setPhone(userRequest.getPhone());
       currentUser.setGender(userRequest.getGender());
       currentUser.setDateOfBirth(userRequest.getDateOfBirth());
       currentUser.setAddress(userRequest.getAddress());

       return userRepository.save(currentUser);
    }

//    public Account updateUserIsActive(long userId) {
//        Account currentUser = userRepository.findAccountById(userId);
//        if (currentUser == null) {
//            throw new EntityNotFoundException("User with id " + userId + " not found");
//        }
//        currentUser.setActive(currentUser.isActive());
//        return userRepository.save(currentUser);
//    }
    public Account toggleUserIsActive(long userId) {
        Account currentUser = userRepository.findAccountById(userId);
        if (currentUser == null) {
            throw new EntityNotFoundException("User with id " + userId + " not found");
        }

        // Đảo trạng thái isActive (true -> false, false -> true)
        currentUser.setActive(!currentUser.isActive());

        return userRepository.save(currentUser);
    }

    public Account updateSkinType(SkinTypeEnum skinTypeEnum, long id){
        Account account = userRepository.findAccountById(id);
        account.setSkinTypeEnum(skinTypeEnum);
        return userRepository.save(account);
    }

        public Account updateRole(RoleEnum roleEnum, long id){
            Account account = userRepository.findAccountById(id);
            account.setRoleEnum(roleEnum);
            return userRepository.save(account);
        }
}
