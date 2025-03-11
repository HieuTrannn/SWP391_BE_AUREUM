package com.example.SkincareProductSales.api;


import com.example.SkincareProductSales.entity.Product;
import com.example.SkincareProductSales.entity.Skin;
import com.example.SkincareProductSales.entity.request.ProductRequest;
import com.example.SkincareProductSales.entity.request.SkinRequest;
import com.example.SkincareProductSales.service.SkinService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/skin")
@CrossOrigin("*")
@SecurityRequirement(name = "api")
public class SkinAPI {

    @Autowired
    SkinService skinService;

    @PostMapping
    public ResponseEntity createSkin(@Valid @RequestBody SkinRequest skinRequest){
        Skin newSkin = skinService.createSkin(skinRequest);
        return ResponseEntity.ok(newSkin);
    }

    @GetMapping
    public ResponseEntity getAllSkin(){
        List<Skin> skins = skinService.getAllSkins();
        return ResponseEntity.ok(skins);
    }

    @GetMapping("/getAllSkinIsDeleted")
    public ResponseEntity getAllSkinIsDeleted(){
        List<Skin> skins = skinService.getAllSkinsIsDeleted();
        return ResponseEntity.ok(skins);
    }

    @PutMapping("{id}")
    public ResponseEntity updateSkin(@PathVariable long id, @Valid @RequestBody SkinRequest skinRequest){
        Skin updateSkin = skinService.updateSkin(id, skinRequest);
        return ResponseEntity.ok(updateSkin);
    }

    @GetMapping("{id}")
    public ResponseEntity getSkinById(@PathVariable long id){
        Skin skin = skinService.getSkinById(id);
        return ResponseEntity.ok(skin);
    }

    @DeleteMapping("{id}")
    public ResponseEntity deleteSkin(@PathVariable long id){
        Skin skin = skinService.deleteSkin(id);
        return ResponseEntity.ok(skin);
    }
}
