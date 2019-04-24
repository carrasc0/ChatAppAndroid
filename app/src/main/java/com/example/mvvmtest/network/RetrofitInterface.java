package com.example.mvvmtest.network;

import com.example.mvvmtest.model.Request.OpenTalkRequest;
import com.example.mvvmtest.model.Response.OpenTalkResponse;

import io.reactivex.Observable;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface RetrofitInterface {

    @POST("/openTalk")
    Observable<OpenTalkResponse> openTalk(@Body OpenTalkRequest request);



}
