package com.example.mvvmtest.network;

import com.example.mvvmtest.model.Request.OpenTalkRequest;
import com.example.mvvmtest.model.Response.OpenTalkResponse;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Headers;
import retrofit2.http.POST;

public interface RetrofitInterface {

    @Headers("Content-Type: application/json")
    @POST("/getMessages")
    Call<OpenTalkResponse> openTalk(@Body OpenTalkRequest request);


}
