package com.example.SkincareProductSales.api;

import com.example.SkincareProductSales.entity.Product;
import com.example.SkincareProductSales.entity.request.ProductRequest;
import com.example.SkincareProductSales.service.ProductService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/product")
@CrossOrigin("*")
@SecurityRequirement(name = "api")

public class ProductAPI {

    @Autowired
    ProductService productService;

    @PostMapping
    public ResponseEntity createProduct(@Valid @RequestBody ProductRequest productRequest){
        Product newProduct = productService.create(productRequest);
        return ResponseEntity.ok(newProduct);
    }

    @GetMapping("/pageable")
    public ResponseEntity GetAllProductsByPage(@RequestParam(defaultValue = "0") int currentPage,
                                             @RequestParam(defaultValue = "10") int pageSize
                                         ) {
        Pageable pageable = PageRequest.of(currentPage, pageSize);
        Page<Product> products = productService.getAllProductsByPage(pageable);
        return ResponseEntity.ok(products);
    }

    @GetMapping
    public ResponseEntity getAllProduct(){
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
