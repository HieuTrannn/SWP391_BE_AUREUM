package com.example.SkincareProductSales.api;


import com.example.SkincareProductSales.entity.Product;
import com.example.SkincareProductSales.entity.Skin;
import com.example.SkincareProductSales.entity.request.ProductRequest;
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
    public ResponseEntity createSkin(@RequestBody Skin skin){
        Skin newSkin = skinService.create(skin);
        return ResponseEntity.ok(newSkin);
    }

    @GetMapping
    public ResponseEntity getAllSkin(){
        List<Skin> newSkin = skinService.getAllSkins();
        return ResponseEntity.ok(newSkin);
    }

    @PutMapping("{id}")
    public ResponseEntity updateSkin(@PathVariable long id, @Valid @RequestBody Skin skin){
        Skin updateSkin = skinService.update(id, skin);
        return ResponseEntity.ok(updateSkin);
    }

    @GetMapping("{id}")
    public ResponseEntity getSkinById(@PathVariable long id){
        Skin skin = skinService.getSkinById(id);
        return ResponseEntity.ok(skin);
    }
}
