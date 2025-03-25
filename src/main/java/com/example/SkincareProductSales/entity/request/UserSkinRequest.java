package com.example.SkincareProductSales.entity.request;

import jakarta.validation.constraints.NotNull;

public class UserSkinRequest {
    @NotNull
    public long skinId;

    public UserSkinRequest() {
    }

    public UserSkinRequest(long skinId) {
        this.skinId = skinId;
    }

    public long getSkinId() {
        return skinId;
    }

    public void setSkinId(long skinId) {
        this.skinId = skinId;
    }
}
