package com.example.SkincareProductSales.api;

import com.example.SkincareProductSales.entity.Rating;
import com.example.SkincareProductSales.entity.request.RatingRequest;
import com.example.SkincareProductSales.service.RatingService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/rating")
@SecurityRequirement(name = "api")
@CrossOrigin("*")
public class RatingAPI {

    @Autowired
    RatingService ratingService;

    @PostMapping
    public ResponseEntity createRating (@Valid @RequestBody RatingRequest ratingRequest) {
        Rating rating = ratingService.createRating(ratingRequest);
        return ResponseEntity.ok(rating);
    }

    @GetMapping
    public ResponseEntity getAllRating () {
        List<Rating> ratings = ratingService.getAllRating();
        return ResponseEntity.ok(ratings);
    }

    @GetMapping("{id}")
    public ResponseEntity getRatingById(@PathVariable long id) {
        Rating rating = ratingService.getRatingById(id);
        return ResponseEntity.ok(rating);
    }

    @GetMapping("/getAllRatingIsDeleted")
    public ResponseEntity getAllRatingIsDeleted () {
        List<Rating> ratings = ratingService.getAllRatingIsDeleted();
        return ResponseEntity.ok(ratings);
    }

    @PutMapping("{id}")
    public ResponseEntity updateRating (@PathVariable long id, @Valid @RequestBody RatingRequest ratingRequest) {
        Rating updateRating = ratingService.updateRating(id, ratingRequest);
        return ResponseEntity.ok(updateRating);
    }

    @DeleteMapping("{id}")
    public ResponseEntity deleteRating (@PathVariable long id) {
        Rating deleteRating = ratingService.deleteRating(id);
        return ResponseEntity.ok(deleteRating);
    }
}
