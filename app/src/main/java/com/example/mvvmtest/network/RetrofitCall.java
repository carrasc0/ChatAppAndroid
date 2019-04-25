package com.example.mvvmtest.network;

import com.example.mvvmtest.dagger.component.ApiController;
import com.example.mvvmtest.model.Request.OpenTalkRequest;
import com.example.mvvmtest.model.Response.OpenTalkResponse;
import com.google.gson.Gson;

import retrofit2.Call;
import retrofit2.Callback;

public class RetrofitCall {

    private RetrofitInterface retrofitInterface;
    private Gson gson;

    public RetrofitCall(RetrofitInterface retrofitInterface, Gson gson) {
        ApiController.getAppComponent().inject(this);
        this.retrofitInterface = retrofitInterface;
        this.gson = gson;
    }

    public void openTalk(int sender, int nickname, Callback<OpenTalkResponse> callback) {
        OpenTalkRequest openTalkRequest = new OpenTalkRequest(sender, nickname);
        Call<OpenTalkResponse> call = retrofitInterface.openTalk(openTalkRequest);
        call.enqueue(callback);
    }

}
