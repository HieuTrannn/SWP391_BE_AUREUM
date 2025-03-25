package com.example.SkincareProductSales.entity.request;

import jakarta.validation.constraints.NotNull;

public class AnswerRequest {
    public String answerText;

    @NotNull
    public long questionId;

    @NotNull
    public long skinId;

    public AnswerRequest() {
    }

    public AnswerRequest(String answerText, long questionId, long skinId) {
        this.answerText = answerText;
        this.questionId = questionId;
        this.skinId = skinId;
    }

    public String getAnswerText() {
        return answerText;
    }

    public void setAnswerText(String answerText) {
        this.answerText = answerText;
    }

    public long getQuestionId() {
        return questionId;
    }

    public void setQuestionId(long questionId) {
        this.questionId = questionId;
    }

    public long getSkinId() {
        return skinId;
    }

    public void setSkinId(long skinId) {
        this.skinId = skinId;
    }
}
