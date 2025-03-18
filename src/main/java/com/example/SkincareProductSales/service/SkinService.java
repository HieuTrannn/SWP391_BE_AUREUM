package com.example.SkincareProductSales.service;

import com.example.SkincareProductSales.entity.*;
import com.example.SkincareProductSales.entity.request.ProductRequest;
import com.example.SkincareProductSales.entity.request.SkinRequest;
import com.example.SkincareProductSales.entity.request.UserSkinTestRequest;
import com.example.SkincareProductSales.repository.AnswerRepository;
import com.example.SkincareProductSales.repository.SkinRepository;
import com.example.SkincareProductSales.repository.UserRepository;
import jakarta.persistence.EntityNotFoundException;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class SkinService {

    @Autowired
    SkinRepository skinRepository;

    @Autowired
    ModelMapper modelMapper;

    @Autowired
    UserRepository userRepository;
    @Autowired
    private AnswerRepository answerRepository;

    public Skin createSkin(SkinRequest skinRequest) {
        Skin skin = modelMapper.map(skinRequest, Skin.class);
        return skinRepository.save(skin);
    }

    public List<Skin> getAllSkins() {
        return skinRepository.findSkinsByIsDeletedFalse();
    }

    public List<Skin> getAllSkinsIsDeleted() {
        return skinRepository.findSkinsByIsDeletedTrue();
    }

    public Skin getSkinById(long skinId){
        Skin currentSkin = skinRepository.findSkinById(skinId);
        if(currentSkin == null){
            throw new EntityNotFoundException("Product Not Found!");
        }
        return currentSkin;
    }

    public Skin updateSkin(long skinId, SkinRequest skinRequest){
        Skin currentSkin = getSkinById(skinId);
        currentSkin.setName(skinRequest.getName());
        return skinRepository.save(currentSkin);
    }

    public Skin deleteSkin(long skinId){
        Skin currentSkin = skinRepository.findSkinById(skinId);
        if(currentSkin == null){
            throw new EntityNotFoundException("Skin Not Found!");
        }
        currentSkin.setDeleted(true);
        return skinRepository.save(currentSkin);
    }

    public Skin determineSkinType(UserSkinTestRequest userSkinTestRequest){
        // Lấy danh sách câu trả lời từ DB theo danh sách answerIds
        List<Answer> answers = answerRepository.findAllById(userSkinTestRequest.getAnswerIds());

        // Khởi tạo Map để lưu điểm số của từng loại da
        Map<Skin, Integer> scoreMap = new HashMap<>();

        // Cộng điểm cho từng loại da dựa vào câu trả lời được chọn
        for (Answer answer : answers) {
            Skin skin = answer.getSkin();
            if (skin != null) {
                scoreMap.put(skin, scoreMap.getOrDefault(skin, 0) + 1);
            }
        }

        // Tìm loại da có số điểm cao nhất
        Skin bestSkin = scoreMap.entrySet()
                .stream()
                .max(Comparator.comparingInt(Map.Entry::getValue))
                .map(Map.Entry::getKey)
                .orElse(null);

        // Cập nhật loại da cho User (nếu có userId)
        if (userSkinTestRequest.getUserId() != null) {
            Account account = userRepository.findById(userSkinTestRequest.getUserId()).orElseThrow();
            account.setSkin(bestSkin);
            userRepository.save(account);
        }

        return bestSkin;
    }
}
