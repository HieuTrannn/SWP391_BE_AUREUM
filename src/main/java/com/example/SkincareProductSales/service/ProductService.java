package com.example.SkincareProductSales.service;

import com.example.SkincareProductSales.entity.Category;
import com.example.SkincareProductSales.entity.Product;
import com.example.SkincareProductSales.entity.request.ProductRequest;
import com.example.SkincareProductSales.repository.CategoryRepository;
import com.example.SkincareProductSales.repository.ProductRepository;
import jakarta.persistence.EntityNotFoundException;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.parameters.P;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {

    @Autowired
    ProductRepository productRepository;

    @Autowired
    ModelMapper modelMapper;

    @Autowired
    CategoryRepository categoryRepository;

    public List<Product> getAllProducts(){
        return productRepository.findProductsByIsDeletedFalse();
    }

    public List<Product> getAllProductsIsDeleted() {
        return productRepository.findProductsByIsDeletedTrue();
    }

    public Product create(ProductRequest productRequest){
        // product request => peoduct entity
        Product product = modelMapper.map(productRequest, Product.class);
        // category tìm thấy được từ yêu cầu người dùng => lấy ra categoryId
        Category category = categoryRepository.findCategoryById(productRequest.categoryId);
        if (category == null) {
            throw new NullPointerException("Category ID " + productRequest.getCategoryId() + " does not exist");
        }
        // tìm nó trong database và set nó trong product
        product.setCategory(category);

        // gọi xuống repo để lưu xuống database
        return productRepository.save(product);
    }

    public Product update(long productId, ProductRequest productRequest){
        Product currentProduct = getProductById(productId);

        currentProduct.setName(productRequest.getName());
        currentProduct.setBrand(productRequest.getBrand());
        currentProduct.setDescription(productRequest.getDescription());
        currentProduct.setQuantity(productRequest.getQuantity());
        currentProduct.setPrice(productRequest.getPrice());
        currentProduct.setImage(productRequest.getImage());
        currentProduct.setCode(productRequest.getCode());
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
