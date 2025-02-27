package com.example.SkincareProductSales.api;

import com.example.SkincareProductSales.entity.Blog;
import com.example.SkincareProductSales.entity.Product;
import com.example.SkincareProductSales.service.BlogService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/blog")
@CrossOrigin("*")
public class BlogAPI {
    @Autowired
    BlogService blogService;

    @PostMapping("/create")
    public ResponseEntity createBlog(@Valid @RequestBody Blog blog){
        Blog newblog = blogService.create(blog);
        return ResponseEntity.ok(newblog);
    }

    @GetMapping("/getAllBlogsIsDeleted")
    public ResponseEntity getAllBlogsIsDeleted(){
        List<Blog> blogs = blogService.getAllBlogsIsDeleted();
        return ResponseEntity.ok(blogs);
    }

    @GetMapping("/getAll")
    public ResponseEntity getAllBlogs(){
        List<Blog> blogs = blogService.getAllBlogs();
        return ResponseEntity.ok(blogs);
    }


    @GetMapping("/get/{id}")
    public ResponseEntity GetProductById(@PathVariable long id){
        Blog blog = blogService.getProductById(id);
        return ResponseEntity.ok(blog);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity updateBlog(@PathVariable long id, @Valid @RequestBody Blog blog){
        Blog updateBlog = blogService.update(id, blog);
        return ResponseEntity.ok(updateBlog);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity deleteProduct(@PathVariable long id){
        Blog deleteBlog = blogService.deleteBlog(id);
        return ResponseEntity.ok(deleteBlog);
    }
}
