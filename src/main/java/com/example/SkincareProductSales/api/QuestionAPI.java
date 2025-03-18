package com.example.SkincareProductSales.api;

import com.example.SkincareProductSales.entity.Question;
import com.example.SkincareProductSales.entity.request.QuestionRequest;
import com.example.SkincareProductSales.service.QuestionService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/question")
@CrossOrigin("*")
@SecurityRequirement(name = "api")
public class QuestionAPI {

    @Autowired
    QuestionService questionService;

    @PostMapping
    public ResponseEntity createQuestion (@Valid @RequestBody QuestionRequest questionRequest) {
        Question newQuestion = questionService.createQuestion(questionRequest);
        return ResponseEntity.ok(newQuestion);
    }

    @GetMapping
    public ResponseEntity GetAllQuestion() {
        List<Question> categories = questionService.getAllQuestions();
        return ResponseEntity.ok(categories);
    }

    @GetMapping("/getAllQuestionIsDeleted")
    public ResponseEntity GetAllQuestionIsDeleted(){
        List<Question> categories = questionService.getQuestionsIsDeleted();
        return ResponseEntity.ok(categories);
    }

    @GetMapping("{id}")
    public ResponseEntity GetQuestionById(@PathVariable long id) {
        Question Question = questionService.getQuestionById(id);
        return ResponseEntity.ok(Question);
    }

    @PutMapping("{id}")
    public ResponseEntity updateQuestion(@PathVariable long id, @Valid @RequestBody QuestionRequest questionRequest){
        Question updateQuestion = questionService.updateQuestion(id, questionRequest);
        return ResponseEntity.ok(updateQuestion);
    }

    @DeleteMapping("{id}")
    public ResponseEntity deleteQuestion(@PathVariable long id) {
        Question deleteQuestion = questionService.deleteQuestion(id);
        return ResponseEntity.ok(deleteQuestion);

    }
}
