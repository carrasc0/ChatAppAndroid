package com.example.mvvmtest.model.Response;

import com.example.mvvmtest.model.DiscoverUser;
import com.example.mvvmtest.model.Message;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class DiscoverUsersResponse extends BaseResponse {

    @SerializedName("users")
    List<DiscoverUser> users;

    public List<DiscoverUser> getDiscoverUsers() {
        return users;
    }


}
