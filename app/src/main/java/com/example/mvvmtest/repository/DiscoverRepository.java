package com.example.mvvmtest.repository;

import android.util.Log;

import androidx.lifecycle.MutableLiveData;

import com.example.mvvmtest.dagger.component.ApiController;
import com.example.mvvmtest.model.DiscoverUser;
import com.example.mvvmtest.model.Message;
import com.example.mvvmtest.model.Response.DiscoverUsersResponse;
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

public class DiscoverRepository {

    @Inject
    protected RetrofitCall retrofitCall;

    private ArrayList<DiscoverUser> dataSet = new ArrayList<>();

    public DiscoverRepository() {
        ApiController.getAppComponent().inject(this);
    }

    public MutableLiveData<List<DiscoverUser>> getDiscoverUsers() {
        setDiscoverUsers();
        MutableLiveData<List<DiscoverUser>> data = new MutableLiveData<>();
        data.setValue(dataSet);
        return data;
    }

    private void setDiscoverUsers() {
        retrofitCall.getDiscoverUsers(Constant.SENDER, Constant.NICKNAME, callback);
    }

    private Callback<DiscoverUsersResponse> callback = new Callback<DiscoverUsersResponse>() {
        @Override
        public void onResponse(Call<DiscoverUsersResponse> call, Response<DiscoverUsersResponse> response) {
            if (response.isSuccessful()) {
                dataSet.addAll(response.body().getDiscoverUsers());
            } else {
                Logger.e("Error get users", response.errorBody());
            }
        }

        @Override
        public void onFailure(Call<DiscoverUsersResponse> call, Throwable t) {
            Log.e("GBC", t.getMessage() + "    " + t.toString());
        }
    };

}
