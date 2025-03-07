package com.example.SkincareProductSales.api;

import com.example.SkincareProductSales.entity.Feedback;
import com.example.SkincareProductSales.entity.request.FeedbackRequest;
import com.example.SkincareProductSales.service.FeedbackService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/feedback")
@SecurityRequirement(name = "api")
@CrossOrigin("*")
public class FeedbackAPI {

    @Autowired
    FeedbackService feedbackService;

    @PostMapping
    public ResponseEntity createFeedback (@Valid @RequestBody FeedbackRequest feedbackRequest) {
        Feedback feedback = feedbackService.createFeedback(feedbackRequest);
        return ResponseEntity.ok(feedback);
    }

    @GetMapping
    public ResponseEntity getAllFeedback () {
        List<Feedback> feedbacks = feedbackService.getAllFeedback();
        return ResponseEntity.ok(feedbacks);
    }

    @GetMapping("/getAllFeedbackIsDeleted")
    public ResponseEntity getAllFeedbackIsDeleted () {
        List<Feedback> feedbacks = feedbackService.getAllFeedbackIsDeleted();
        return ResponseEntity.ok(feedbacks);
    }

    @GetMapping("{id}")
    public ResponseEntity getFeedbackById(@PathVariable long id) {
        Feedback feedback = feedbackService.getFeedbackById(id);
        return ResponseEntity.ok(feedback);
    }

    @PutMapping("{id}")
    public ResponseEntity updateFeedback (@PathVariable long id, @Valid @RequestBody FeedbackRequest feedbackRequest) {
        Feedback updateFeedback = feedbackService.updateFeedback(id, feedbackRequest);
        return ResponseEntity.ok(updateFeedback);
    }

    @DeleteMapping("{id}")
    public ResponseEntity deleteFeedback (@PathVariable long id) {
        Feedback deleteFeedback = feedbackService.deleteFeedback(id);
        return ResponseEntity.ok(deleteFeedback);
    }
}
