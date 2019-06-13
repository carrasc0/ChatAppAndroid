package com.example.mvvmtest.network;

import com.example.mvvmtest.model.Response.BaseResponse;
import com.example.mvvmtest.model.Response.DiscoverUsersResponse;

import kotlinx.coroutines.Deferred;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

public interface RetrofitInterface {

    @GET("/getMatch/{id}")
    Call<BaseResponse> getMatch(@Path("id") int id);

    @GET("/getUserDataEditProfile/{id}")
    Call<BaseResponse> getUserDataEditProfile(@Path("id") int id);

    @GET("/getDiscoverUsers/{id}")
    Call<DiscoverUsersResponse> getDiscoverUsers(@Path("id") int id);



    @Headers("Content-Type: application/json")
    @POST("/userAction")
    Call<BaseResponse> userAction();

    @Headers("Content-Type: application/json")
    @PUT("/setCoordinates")
    Deferred<BaseResponse> setCoordinates();


}
