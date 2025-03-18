package com.example.SkincareProductSales.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

@Entity
public class Answer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public long id;

    @ManyToOne
    @JoinColumn(name = "question_id", nullable = false)
    public Question question;

    @Column(nullable = false)
    public String answerText;

    @ManyToOne
    @JoinColumn(name = "skin_id")
    public Skin skin; // Đáp án này thuộc về loại da nào

    @JsonIgnore
    public boolean isDeleted = false;

    public Answer() {
    }

    public Answer(long id, Question question, String answerText, Skin skin, boolean isDeleted) {
        this.id = id;
        this.question = question;
        this.answerText = answerText;
        this.skin = skin;
        this.isDeleted = isDeleted;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Question getQuestion() {
        return question;
    }

    public void setQuestion(Question question) {
        this.question = question;
    }

    public String getAnswerText() {
        return answerText;
    }

    public void setAnswerText(String answerText) {
        this.answerText = answerText;
    }

    public Skin getSkin() {
        return skin;
    }

    public void setSkin(Skin skin) {
        this.skin = skin;
    }

    public boolean isDeleted() {
        return isDeleted;
    }

    public void setDeleted(boolean deleted) {
        isDeleted = deleted;
    }
}
