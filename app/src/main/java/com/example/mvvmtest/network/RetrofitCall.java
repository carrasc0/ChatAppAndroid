package com.example.mvvmtest.network;

import com.example.mvvmtest.dagger.component.ApiController;
import com.example.mvvmtest.model.Request.GetMessagesRequest;
import com.example.mvvmtest.model.Response.DiscoverUsersResponse;
import com.example.mvvmtest.model.Response.GetMessagesResponse;

import retrofit2.Call;
import retrofit2.Callback;

public class RetrofitCall {

    private RetrofitInterface retrofitInterface;

    public RetrofitCall(RetrofitInterface retrofitInterface) {
        ApiController.getAppComponent().inject(this);
        this.retrofitInterface = retrofitInterface;
    }

    public void getMessages(int sender, int nickname, Callback<GetMessagesResponse> callback) {
        GetMessagesRequest getMessagesRequest = new GetMessagesRequest(sender, nickname);
        Call<GetMessagesResponse> call = retrofitInterface.getMessages(getMessagesRequest);
        call.enqueue(callback);
    }

    public void getDiscoverUsers(int sender, int nickname, Callback<DiscoverUsersResponse> callback) {
        GetMessagesRequest getMessagesRequest = new GetMessagesRequest(sender, nickname);
        Call<DiscoverUsersResponse> call = retrofitInterface.getDiscoverUsers(getMessagesRequest);
        call.enqueue(callback);
    }

}
