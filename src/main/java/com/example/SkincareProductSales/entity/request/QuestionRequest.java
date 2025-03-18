package com.example.SkincareProductSales.entity.request;

import com.example.SkincareProductSales.entity.Answer;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

public class QuestionRequest {

    public String questionText;

    public QuestionRequest() {
    }

    public QuestionRequest(String questionText) {
        this.questionText = questionText;
    }

    public String getQuestionText() {
        return questionText;
    }

    public void setQuestionText(String questionText) {
        this.questionText = questionText;
    }
}
