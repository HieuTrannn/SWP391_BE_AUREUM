package com.example.SkincareProductSales.api;

import com.example.SkincareProductSales.entity.Brand;
import com.example.SkincareProductSales.entity.request.BrandRequest;
import com.example.SkincareProductSales.service.BrandService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/brand")
@CrossOrigin("*")
@SecurityRequirement(name = "api")

public class BrandAPI {

    @Autowired
    BrandService brandService;

    @PostMapping
    public ResponseEntity createBrand(@Valid @RequestBody BrandRequest brandRequest) {
        Brand newBrand = brandService.createBrand(brandRequest);
        return ResponseEntity.ok(newBrand);
    }

    @GetMapping
    public ResponseEntity getAllBrands() {
        List<Brand> brands = brandService.getAllBrands();
        return ResponseEntity.ok(brands);
    }

    @GetMapping("/getAllBrandIsDeleted")
    public ResponseEntity getAllBrandIsDeleted() {
        List<Brand> brands = brandService.getAllBrandIsDeleted();
        return ResponseEntity.ok(brands);
    }

    @GetMapping("{id}")
    public ResponseEntity getBrandById(@PathVariable long id) {
        Brand brand = brandService.getBrandById(id);
        return ResponseEntity.ok(brand);
    }

    @PutMapping("{id}")
    public ResponseEntity updateBrand(@PathVariable long id, @Valid @RequestBody BrandRequest brandRequest) {
        Brand updateBrand = brandService.updateBrand(id, brandRequest);
        return ResponseEntity.ok(updateBrand);
    }

    @DeleteMapping("{id}")
    public ResponseEntity deleteBrand(@PathVariable long id) {
        Brand deleteBrand = brandService.deleteBrand(id);
        return ResponseEntity.ok(deleteBrand);
    }

}
