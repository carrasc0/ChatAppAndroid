package com.example.mvvmtest.network;

import com.example.mvvmtest.model.Request.GetMessagesRequest;
import com.example.mvvmtest.model.Response.BaseResponse;
import com.example.mvvmtest.model.Response.DiscoverUsersResponse;
import com.example.mvvmtest.model.Response.GetMessagesResponse;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.PUT;

public interface RetrofitInterface {

    @Headers("Content-Type: application/json")
    @POST("/getMessages")
    Call<GetMessagesResponse> getMessages(@Body GetMessagesRequest request);

    @Headers("Content-Type: application/json")
    @POST("/getDiscoverUsers")
    Call<DiscoverUsersResponse> getDiscoverUsers(@Body GetMessagesRequest request);

    @Headers("Content-Type: application/json")
    @POST("/userAction")
    Call<BaseResponse> userAction();

    @Headers("Content-Type: application/json")
    @PUT("/setCoordinates")
    Call<BaseResponse> setCoordinates();


}
