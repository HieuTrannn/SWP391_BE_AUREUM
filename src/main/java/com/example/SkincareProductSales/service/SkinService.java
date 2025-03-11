package com.example.SkincareProductSales.service;

import com.example.SkincareProductSales.entity.*;
import com.example.SkincareProductSales.entity.request.ProductRequest;
import com.example.SkincareProductSales.entity.request.SkinRequest;
import com.example.SkincareProductSales.repository.SkinRepository;
import jakarta.persistence.EntityNotFoundException;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SkinService {

    @Autowired
    SkinRepository skinRepository;

    @Autowired
    ModelMapper modelMapper;

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
}
