package com.example.mvvmtest.repository;

import android.util.Log;

import androidx.lifecycle.MutableLiveData;

import com.example.mvvmtest.dagger.component.ApiController;
import com.example.mvvmtest.model.Message;
import com.example.mvvmtest.model.Response.OpenTalkResponse;
import com.example.mvvmtest.network.RetrofitCall;
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
    RetrofitCall retrofitCall;

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
        retrofitCall.openTalk(1, 2, callback);
    }

    private Callback<OpenTalkResponse> callback = new Callback<OpenTalkResponse>() {
        @Override
        public void onResponse(Call<OpenTalkResponse> call, Response<OpenTalkResponse> response) {
            if (response.isSuccessful()) {
                dataSet.addAll(response.body().getMessagess());
            } else {
                Logger.e("Error get messages", response.errorBody());
            }
        }

        @Override
        public void onFailure(Call<OpenTalkResponse> call, Throwable t) {
            //Logger.e("Error get messages", t.getMessage());
            Log.e("GBC", t.getMessage() + "    " + t.toString());
        }
    };
}
