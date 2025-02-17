package com.example.SkincareProductSales.api;

import com.example.SkincareProductSales.entity.Product;
import com.example.SkincareProductSales.service.ProductService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/product")
public class ProductAPI {

    @Autowired
    ProductService productService;

    @PostMapping
    public ResponseEntity createProduct(@Valid @RequestBody Product product){
        Product newProduct = productService.create(product);
        return ResponseEntity.ok(newProduct);
    }

    @GetMapping
    public ResponseEntity GetAllProducts(){
        List<Product> products = productService.getAllProducts();
        return ResponseEntity.ok(products);
    }

    @GetMapping("{id}")
    public ResponseEntity GetProductById(@PathVariable long id){
        Product product = productService.getProductById(id);
        return ResponseEntity.ok(product);
    }

    @PutMapping("{id}")
    public ResponseEntity updateProduct(@PathVariable long id, @Valid @RequestBody Product product){
        Product updateProduct = productService.update(id, product);
        return ResponseEntity.ok(updateProduct);
    }

    @DeleteMapping("{id}")
    public ResponseEntity deleteProduct(@PathVariable long id){
        Product product = productService.delete(id);
        return ResponseEntity.ok(product);
    }
}
