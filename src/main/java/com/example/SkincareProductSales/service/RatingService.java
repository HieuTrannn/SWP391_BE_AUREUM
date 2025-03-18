package com.example.SkincareProductSales.service;

import com.example.SkincareProductSales.entity.Account;
import com.example.SkincareProductSales.entity.OrderDetail;
import com.example.SkincareProductSales.entity.Rating;
import com.example.SkincareProductSales.entity.request.RatingRequest;
import com.example.SkincareProductSales.enums.OrderStatus;
import com.example.SkincareProductSales.exception.exceptions.BusinessLogicException;
import com.example.SkincareProductSales.repository.OrderDetailRepository;
import com.example.SkincareProductSales.repository.RatingRepository;
import com.example.SkincareProductSales.utils.AccountUtils;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RatingService {

    @Autowired
    RatingRepository ratingRepository;

    @Autowired
    OrderDetailRepository orderDetailRepository;

    @Autowired
    AccountUtils accountUtils;

    public Rating createRating (RatingRequest ratingRequest){
        OrderDetail orderDetail = orderDetailRepository.findOrderDetailById(ratingRequest.getOrderDetailId())
                .orElseThrow(() -> new BusinessLogicException("Order Detail Not Found"));

        // check xem order này đã thành công chưa
        // nếu đơn đã thành cong rồi thì mới rating
        // chưa thì báo lỗi
        if(!OrderStatus.PAID.equals(orderDetail.getOrder().getStatus())) {
            throw new BusinessLogicException("Order not completed so you cannot create a rating");
        }

        Account account = accountUtils.getCurrentAccount();

        // check xem user đã rating product chưa
        // nếu rồi thì báo lỗi
        // nếu chưa thì tạo mới rating
        orderDetail.getProduct().getRatings().stream().forEach(rating -> {
            if (rating.getAccount().getId() == account.getId()) {
                throw new BusinessLogicException("Product rating already exists");
            }
        });

        Rating rating = new Rating();
        rating.setAccount(account);
        rating.setRating(ratingRequest.getRating());
        rating.setComment(ratingRequest.getComment());
        rating.setImage(ratingRequest.getImage());
        rating.setProduct(orderDetail.getProduct());

        return ratingRepository.save(rating);
    }

    public Rating getRatingById (long ratingId) {
        Rating rating = ratingRepository.findRatingById(ratingId).orElseThrow();
        if(rating == null){
            throw new EntityNotFoundException("Rating not found!");
        }
        return rating;
    }

    public List<Rating> getAllRating () {
        return ratingRepository.findRatingByIsDeletedFalse();
    }

    public List<Rating> getAllRatingIsDeleted () {
        return ratingRepository.findRatingByIsDeletedTrue();
    }

    public Rating updateRating(long ratingId, RatingRequest ratingRequest){
        Rating currentRating = getRatingById(ratingId);

        currentRating.setRating(ratingRequest.getRating());
        currentRating.setComment(ratingRequest.getComment());
        currentRating.setImage(ratingRequest.getImage());

        return ratingRepository.save(currentRating);
    }

    public Rating deleteRating(long ratingId) {
        Rating currentRating = ratingRepository.findRatingById(ratingId).orElseThrow();

        if(currentRating == null){
            throw new EntityNotFoundException("Rating not found!");
        }

        currentRating.setDeleted(true);
        return ratingRepository.save(currentRating);
    }
}
