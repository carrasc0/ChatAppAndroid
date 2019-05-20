package com.example.mvvmtest.model.Response;

import com.example.mvvmtest.model.UserEditProfile;
import com.google.gson.annotations.SerializedName;

public class UserEditProfileResponse extends BaseResponse {

    @SerializedName("data")
    UserEditProfile data;

    public UserEditProfile getData() {
        return data;
    }

    public void setData(UserEditProfile data) {
        this.data = data;
    }
}
