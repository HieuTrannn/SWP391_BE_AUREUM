package com.example.SkincareProductSales.repository;

import com.example.SkincareProductSales.entity.Blog;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

public interface BlogRepository extends JpaRepository<Blog , Long> {

    Blog findBlogById(long id);

    List<Blog> findBlogsByIsDeletedFalse();
    List<Blog> findBlogsByIsDeletedTrue();


}
