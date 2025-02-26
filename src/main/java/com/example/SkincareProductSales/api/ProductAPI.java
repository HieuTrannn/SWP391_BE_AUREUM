package com.example.SkincareProductSales.api;

import com.example.SkincareProductSales.entity.Product;
import com.example.SkincareProductSales.entity.request.ProductRequest;
import com.example.SkincareProductSales.service.ProductService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/product")
@CrossOrigin("*")
public class ProductAPI {

    @Autowired
    ProductService productService;

    @PostMapping
    public ResponseEntity createProduct(@Valid @RequestBody ProductRequest productRequest){
        Product newProduct = productService.create(productRequest);
        return ResponseEntity.ok(newProduct);
    }

    @GetMapping
    public ResponseEntity GetAllProducts(){
        List<Product> products = productService.getAllProducts();
        return ResponseEntity.ok(products);
    }

    @GetMapping("/getAllCategoryIsDeleted")
    public ResponseEntity GetAllProductIsDeleted () {
        List<Product> products = productService.getAllProductsIsDeleted();
        return ResponseEntity.ok(products);
    }

    @GetMapping("{id}")
    public ResponseEntity GetProductById(@PathVariable long id){
        Product product = productService.getProductById(id);
        return ResponseEntity.ok(product);
    }

    @PutMapping("{id}")
    public ResponseEntity updateProduct(@PathVariable long id, @Valid @RequestBody ProductRequest productRequest){
        Product updateProduct = productService.update(id, productRequest);
        return ResponseEntity.ok(updateProduct);
    }

    @DeleteMapping("{id}")
    public ResponseEntity deleteProduct(@PathVariable long id){
        Product product = productService.delete(id);
        return ResponseEntity.ok(product);
    }

    @GetMapping("/category/taytrang")
    public ResponseEntity GetAllProductCategory_1(){
        List<Product> products = productService.getAllProductsCategory_1();
        return ResponseEntity.ok(products);
    }
}
