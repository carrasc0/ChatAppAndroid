package com.example.chatapp.model.Request;

import com.google.gson.annotations.SerializedName;

public class BaseRequest {

    @SerializedName("id")
    private int id;

    public BaseRequest(int id) {
        this.id = id;
    }
}
