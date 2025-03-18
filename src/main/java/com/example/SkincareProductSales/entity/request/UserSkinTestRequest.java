package com.example.SkincareProductSales.entity.request;

import java.util.List;

public class UserSkinTestRequest {
    public Long userId;
    public List<Long> answerIds; // Danh sách ID của đáp án mà user chọn

    public UserSkinTestRequest() {
    }

    public UserSkinTestRequest(Long userId, List<Long> answerIds) {
        this.userId = userId;
        this.answerIds = answerIds;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public List<Long> getAnswerIds() {
        return answerIds;
    }

    public void setAnswerIds(List<Long> answerIds) {
        this.answerIds = answerIds;
    }
}
