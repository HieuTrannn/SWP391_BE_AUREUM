package com.example.SkincareProductSales.entity.request;

import jakarta.validation.constraints.NotNull;

public class AnswerRequest {
    public String answerText;

    @NotNull
    public long QuestionId;

    @NotNull
    public long SkinId;

    public AnswerRequest() {
    }

    public AnswerRequest(String answerText, long questionId, long skinId) {
        this.answerText = answerText;
        QuestionId = questionId;
        SkinId = skinId;
    }

    public String getAnswerText() {
        return answerText;
    }

    public void setAnswerText(String answerText) {
        this.answerText = answerText;
    }

    public long getQuestionId() {
        return QuestionId;
    }

    public void setQuestionId(long questionId) {
        QuestionId = questionId;
    }

    public long getSkinId() {
        return SkinId;
    }

    public void setSkinId(long skinId) {
        SkinId = skinId;
    }
}
