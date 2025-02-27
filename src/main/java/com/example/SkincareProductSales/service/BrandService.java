package com.example.SkincareProductSales.service;

import com.example.SkincareProductSales.entity.Brand;
import com.example.SkincareProductSales.entity.request.BrandRequest;
import com.example.SkincareProductSales.repository.BrandRepository;
import io.swagger.v3.oas.annotations.servers.Server;
import jakarta.persistence.EntityNotFoundException;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BrandService {

    @Autowired
    BrandRepository brandRepository;

    @Autowired
    ModelMapper modelMapper;


    public Brand createBrand(BrandRequest brandRequest) {
        Brand brand = modelMapper.map(brandRequest, Brand.class);
        return brandRepository.save(brand);
    }

    public Brand getBrandById(long brandId) {
        Brand currentBrand = brandRepository.findBrandById(brandId);
        if (currentBrand == null) {
            throw new EntityNotFoundException("Brand not found!");
        }
        return currentBrand;
    }

    public List<Brand> getAllBrands() {
        return brandRepository.findBrandByIsDeletedFalse();
    }

    public List<Brand> getAllBrandIsDeleted() {
        return brandRepository.findBrandByIsDeletedTrue();
    }

    public Brand updateBrand(long brandId, BrandRequest brandRequest) {
        Brand currentBrand = getBrandById(brandId);

        currentBrand.setName(brandRequest.getName());
        return brandRepository.save(currentBrand);
    }

    public Brand deleteBrand(long brandId) {
        Brand currentBrand = brandRepository.findBrandById(brandId);
        if (currentBrand == null) {
            throw new EntityNotFoundException("Brand not found!");
        }
        currentBrand.setDeleted(true);
        return brandRepository.save(currentBrand);
    }
}
