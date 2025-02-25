package com.example.SkincareProductSales.service;

import com.example.SkincareProductSales.entity.Feedback;
import com.example.SkincareProductSales.entity.request.FeedbackRequest;
import com.example.SkincareProductSales.repository.FeedbackRepository;
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

    public Feedback createFeedback (FeedbackRequest feedbackRequest) {
        Feedback feedback = modelMapper.map(feedbackRequest, Feedback.class);
        return feedbackRepository.save(feedback);
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

        currentFeedback.setRating(feedbackRequest.getRating());
        currentFeedback.setComment(feedbackRequest.getComment());
        currentFeedback.setImage(feedbackRequest.getImage());

        return feedbackRepository.save(currentFeedback);
    }

    public Feedback deleteFeedback(long feedbackId) {
        Feedback currentFeedback = feedbackRepository.findFeedbackById(feedbackId);
        if (currentFeedback == null) {
            throw new EntityNotFoundException("Feedback Not Found!");
        }
        currentFeedback.setDeleted(true);
        return feedbackRepository.save(currentFeedback);
    }
}
