package com.example.SkincareProductSales.service;

import com.example.SkincareProductSales.entity.Account;
import com.example.SkincareProductSales.entity.Feedback;
import com.example.SkincareProductSales.entity.Product;
import com.example.SkincareProductSales.entity.request.FeedbackRequest;
import com.example.SkincareProductSales.entity.response.*;
import com.example.SkincareProductSales.enums.RatingEnum;
import com.example.SkincareProductSales.repository.FeedbackRepository;
import com.example.SkincareProductSales.repository.ProductRepository;
import com.example.SkincareProductSales.repository.UserRepository;
import jakarta.persistence.EntityNotFoundException;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FeedbackService {

    @Autowired
    FeedbackRepository feedbackRepository;

    @Autowired
    ModelMapper modelMapper;

    @Autowired
    UserRepository userRepository;

    @Autowired
    ProductRepository productRepository;

    public FeedbackResponse createFeedback (FeedbackRequest feedbackRequest) {
        // feedback request => feedback entity
//        Feedback feedback = modelMapper.map(feedbackRequest, Feedback.class);

        // Tạo Feedback entity từ FeedbackRequest
        Feedback feedback = new Feedback();

        feedback.setComment(feedbackRequest.getComment());
        feedback.setImage(feedbackRequest.getImage());

        // Chuyển int thành RatingEnum
        feedback.setRatingEnum(RatingEnum.fromValue(feedbackRequest.getRating()));

        // account tìm thấy được từ yêu cầu người dùng => lấy ra userId
        Account account = userRepository.findAccountById(feedbackRequest.userId);
        Product product = productRepository.findProductById(feedbackRequest.productId);
        if (account == null) {
            throw new NullPointerException("Account ID " + feedbackRequest.userId + " does not exist");
        } else if (product == null) {
            throw new NullPointerException("Product ID " + feedbackRequest.productId + " does not exist");
        }
        // tìm nó trong database và set nó trong feedback
        feedback.setAccount(account);
        feedback.setProduct(product);

        // Lưu vào database
        feedback = feedbackRepository.save(feedback);

        //Chuyển Feedback thành FeedbackResponse
        return new FeedbackResponse(
                feedback.getId(),
                feedback.getRatingEnum(),
                feedback.getComment(),
                feedback.getImage(),

                // Tạo UserResponse từ Account
                new UserResponse(
                        account.getId(),
                        account.getFullName(),
                        account.getEmail(),
                        account.getRoleEnum(),
                        account.getSkinTypeEnum()),

                // Tạo ProductResponse từ Product
                new ProductResponse(
                        product.getId(),
                        product.getName(),
                        new BrandResponse(product.getBrand().getName()),
                        new CategoryResponse(product.getCategory().getName()),
                        product.getCode(),
                        product.getSkinTypeEnum()
                        )
        );
    }

    public Feedback getFeedbackById (long feedbackId) {
        Feedback feedback = feedbackRepository.findFeedbackById(feedbackId);
        if(feedback == null){
            throw new EntityNotFoundException("Feedback not found!");
        }
        return feedbackRepository.save(feedback);
    }

    public List<Feedback> getAllFeedback () {
        return feedbackRepository.findFeedbackByIsDeletedFalse();
    }

    public List<Feedback> getAllFeedbackIsDeleted () {
        return feedbackRepository.findFeedbackByIsDeletedTrue();
    }

    public Feedback updateFeedback(long feedbackId, FeedbackRequest feedbackRequest){
        Feedback currentFeedback = getFeedbackById(feedbackId);

        currentFeedback.setRatingEnum(RatingEnum.fromValue(feedbackRequest.getRating()));

        currentFeedback.setComment(feedbackRequest.getComment());
        currentFeedback.setImage(feedbackRequest.getImage());

        return feedbackRepository.save(currentFeedback);
    }

}
