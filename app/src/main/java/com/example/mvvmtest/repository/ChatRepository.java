package com.example.mvvmtest.repository;

import android.util.Log;

import androidx.lifecycle.MutableLiveData;

import com.example.mvvmtest.dagger.component.ApiController;
import com.example.mvvmtest.model.Message;
import com.example.mvvmtest.model.Response.GetMessagesResponse;
import com.example.mvvmtest.network.RetrofitCall;
import com.example.mvvmtest.util.Constant;
import com.orhanobut.logger.AndroidLogAdapter;
import com.orhanobut.logger.Logger;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ChatRepository {

    @Inject
    protected RetrofitCall retrofitCall;

    private ArrayList<Message> dataSet = new ArrayList<>();


    public ChatRepository() {
        Logger.addLogAdapter(new AndroidLogAdapter());
        ApiController.getAppComponent().inject(this);
    }

    public MutableLiveData<List<Message>> getMessages() {
        setMessages();
        MutableLiveData<List<Message>> data = new MutableLiveData<>();
        data.setValue(dataSet);
        return data;
    }

    private void setMessages() {
        retrofitCall.getMessages(Constant.SENDER, Constant.NICKNAME, callback);
    }

    private Callback<GetMessagesResponse> callback = new Callback<GetMessagesResponse>() {
        @Override
        public void onResponse(Call<GetMessagesResponse> call, Response<GetMessagesResponse> response) {
            if (response.isSuccessful()) {
                dataSet.addAll(response.body().getMessagess());
            } else {
                Logger.e("Error get messages", response.errorBody());
            }
        }

        @Override
        public void onFailure(Call<GetMessagesResponse> call, Throwable t) {
            //Logger.e("Error get messages", t.getMessage());
            Log.e("GBC", t.getMessage() + "    " + t.toString());
        }
    };
}
