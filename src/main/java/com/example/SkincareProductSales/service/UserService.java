package com.example.SkincareProductSales.service;

import com.example.SkincareProductSales.entity.Account;
import com.example.SkincareProductSales.entity.Skin;
import com.example.SkincareProductSales.entity.request.SkinRequest;
import com.example.SkincareProductSales.entity.request.UserRequest;
import com.example.SkincareProductSales.entity.request.UserSkinRequest;
import com.example.SkincareProductSales.repository.SkinRepository;
import com.example.SkincareProductSales.repository.UserRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    @Autowired
    UserRepository userRepository;

    @Autowired
    SkinRepository skinRepository;

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

        // Lấy đối tượng Skin từ skinId trong userRequest
        Skin skin = skinRepository.findSkinById(userRequest.getSkinId());

        // Cập nhật skin của người dùng
        currentUser.setSkin(skin);

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

    public Account updateUserSkin(long userId, UserSkinRequest userSkinRequest) {
        Account account = getAccountById(userId);

        Skin skin = skinRepository.findSkinById(userSkinRequest.getSkinId());

        account.setSkin(skin);

        return userRepository.save(account);
    }
}
