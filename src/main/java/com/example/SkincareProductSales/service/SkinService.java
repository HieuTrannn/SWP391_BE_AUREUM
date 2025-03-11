package com.example.SkincareProductSales.service;

import com.example.SkincareProductSales.entity.*;
import com.example.SkincareProductSales.entity.request.ProductRequest;
import com.example.SkincareProductSales.repository.SkinRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SkinService {

    @Autowired
    SkinRepository skinRepository;
    public Skin create(Skin skin) {
        return skinRepository.save(skin);
    }

    public List<Skin> getAllSkins() {
        return skinRepository.findAll();
    }

    public Skin getSkinById(long skinId){
        Skin currentSkin = skinRepository.findSkinById(skinId);
        if(currentSkin == null){
            throw new EntityNotFoundException("Product Not Found!");
        }
        return  currentSkin;
    }

    public Skin update(long productId, Skin skin){
        Skin currentSkin = getSkinById(productId);
        currentSkin.setName(skin.getName());
        return skinRepository.save(currentSkin);
    }


}
