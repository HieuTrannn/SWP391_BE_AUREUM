package com.example.SkincareProductSales.service;

import com.example.SkincareProductSales.entity.*;
import com.example.SkincareProductSales.entity.request.ProductRequest;
import com.example.SkincareProductSales.repository.*;
import jakarta.persistence.EntityNotFoundException;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
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

    @Autowired
    BrandRepository brandRepository;

    @Autowired
    IngredientRepository ingredientRepository;

    @Autowired
    SkinRepository skinRepository;

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
        Brand brand = brandRepository.findBrandById(productRequest.brandId);
        List<Ingredient> ingredients = ingredientRepository.findAllById(productRequest.getIngredientId());
        Skin skin = skinRepository.findSkinById(productRequest.skinId);
        if (category == null) {
            throw new NullPointerException("Category ID " + productRequest.getCategoryId() + " does not exist");
        } else if (brand == null) {
            throw new NullPointerException("Brand ID " + productRequest.getBrandId() + " does not exist");
        }
        // tìm nó trong database và set nó trong product
        product.setCategory(category);
        product.setBrand(brand);
        product.setIngredient(ingredients);
        product.setSkin(skin);

        // gọi xuống repo để lưu xuống database
        return productRepository.save(product);
    }

    public Product update(long productId, ProductRequest productRequest){
        Product currentProduct = getProductById(productId);

        currentProduct.setName(productRequest.getName());
        currentProduct.setDescription(productRequest.getDescription());
        currentProduct.setQuantity(productRequest.getQuantity());
        currentProduct.setPrice(productRequest.getPrice());
        currentProduct.setImage(productRequest.getImage());
        currentProduct.setCode(productRequest.getCode());

        // Lấy đối tượng Category từ categoryId trong productRequest
        Category category = categoryRepository.findCategoryById(productRequest.getCategoryId());
        Brand brand = brandRepository.findBrandById(productRequest.getBrandId());
        List<Ingredient> ingredient = ingredientRepository.findAllById(productRequest.getIngredientId());
        Skin skin = skinRepository.findSkinById(productRequest.getSkinId());

        // Cập nhật category của sản phẩm
        currentProduct.setCategory(category);
        currentProduct.setBrand(brand);
        currentProduct.setIngredient(ingredient);
        currentProduct.setSkin(skin);

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

    public Page<Product> getAllProductsByPage(Pageable pageable){
        return productRepository.findProductsByIsDeletedFalse(pageable);
    }

    public List<Product> getAllProductsCategory_1() {

        return productRepository.findProductsByCategory(1L);
    }
}
