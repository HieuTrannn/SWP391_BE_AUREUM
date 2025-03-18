package com.example.SkincareProductSales.api;

import com.example.SkincareProductSales.entity.Answer;
import com.example.SkincareProductSales.entity.request.AnswerRequest;
import com.example.SkincareProductSales.service.AnswerService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/answer")
@CrossOrigin("*")
@SecurityRequirement(name = "api")
public class AnswerAPI {
    @Autowired
    AnswerService answerService;

    @PostMapping
    public ResponseEntity createAnswer (@Valid @RequestBody AnswerRequest answerRequest) {
        Answer newAnswer = answerService.createAnswer(answerRequest);
        return ResponseEntity.ok(newAnswer);
    }

    @GetMapping
    public ResponseEntity GetAllAnswer() {
        List<Answer> categories = answerService.getAllAnswers();
        return ResponseEntity.ok(categories);
    }

    @GetMapping("/getAllAnswerIsDeleted")
    public ResponseEntity GetAllAnswerIsDeleted(){
        List<Answer> categories = answerService.getAnswersIsDeleted();
        return ResponseEntity.ok(categories);
    }

    @GetMapping("{id}")
    public ResponseEntity GetAnswerById(@PathVariable long id) {
        Answer Answer = answerService.getAnswerById(id);
        return ResponseEntity.ok(Answer);
    }

    @PutMapping("{id}")
    public ResponseEntity updateAnswer(@PathVariable long id, @Valid @RequestBody AnswerRequest answerRequest){
        Answer updateAnswer = answerService.updateAnswer(id, answerRequest);
        return ResponseEntity.ok(updateAnswer);
    }

    @DeleteMapping("{id}")
    public ResponseEntity deleteAnswer(@PathVariable long id) {
        Answer deleteAnswer = answerService.deleteAnswer(id);
        return ResponseEntity.ok(deleteAnswer);

    }
}
