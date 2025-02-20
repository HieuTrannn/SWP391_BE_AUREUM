package com.example.SkincareProductSales.service;

import com.example.SkincareProductSales.entity.Blog;
import com.example.SkincareProductSales.entity.Product;
import com.example.SkincareProductSales.repository.BlogRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BlogService {

    @Autowired
    BlogRepository blogRepository;

    public Blog create(Blog blog){
        return blogRepository.save(blog);
    }

    public List<Blog> getAllBlogs(){
        return blogRepository.findBlogsByIsDeletedFalse();
    }

    public List<Blog> getAllBlogsIsDeleted(){
        return blogRepository.findBlogsByIsDeletedTrue();
    }


    public Blog getProductById(long id){
        Blog currentBlog = blogRepository.findBlogById(id);
        if(currentBlog == null){
            throw new EntityNotFoundException("Product Not Found!");
        }
        return  currentBlog ;
    }

    public Blog update(long id, Blog blog){
        Blog currentBlog = getProductById(id);

        if (currentBlog != null){
        currentBlog.setTitle(blog.getTitle());
        currentBlog.setDetails(blog.getDetails());
        currentBlog.setAuthor(blog.getAuthor());
        currentBlog.setImg(blog.getImg());
        currentBlog.setDatePublished(blog.getDatePublished());

        }else {
            throw new EntityNotFoundException("Blog Not Found!!");
        }

        return blogRepository.save(currentBlog);
    }

    public Blog deleteBlog(long id){
        Blog currentBlog = blogRepository.findBlogById(id);
        if(currentBlog == null){
            throw new EntityNotFoundException("Blog Not Found!");
        }
            currentBlog.setDeleted(true);
        return blogRepository.save(currentBlog);
    }
}
