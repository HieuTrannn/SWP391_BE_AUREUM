package com.example.SkincareProductSales.service;

import com.example.SkincareProductSales.entity.Product;
import com.example.SkincareProductSales.repository.ProductRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {

    @Autowired
    ProductRepository productRepository;

    public List<Product> getAllProducts(){
        return productRepository.findProductsByIsDeletedFalse();
    }

    public Product create(Product product){
        // gọi xuống repo để lưu xuống database
        return productRepository.save(product);
    }

    public Product update(long productId, Product product){
        Product currentProduct = getProductById(productId);

        currentProduct.setName(product.getName());
        currentProduct.setBrand(product.getBrand());
        currentProduct.setDescription(product.getDescription());
        currentProduct.setQuantity(product.getQuantity());
        currentProduct.setPrice(product.getPrice());
        currentProduct.setCategory(product.getCategory());
        currentProduct.setImage(product.getImage());
        currentProduct.setCode(product.getCode());
        return productRepository.save(currentProduct);
    }

    public Product delete(long productId){
        Product currentProduct = productRepository.findProductById(productId);
        if(currentProduct == null){
            throw new EntityNotFoundException("Product Not Found!");
        }
        currentProduct.setDeleted(true);
        return productRepository.save(currentProduct);
    }

    public Product getProductById(long productId){
        Product currentProduct = productRepository.findProductById(productId);
        if(currentProduct == null){
            throw new EntityNotFoundException("Product Not Found!");
        }
        return  currentProduct;
    }
}
