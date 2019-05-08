package com.example.mvvmtest.repository;

import android.util.Log;

import androidx.lifecycle.MutableLiveData;

import com.example.mvvmtest.dagger.component.ApiController;
import com.example.mvvmtest.model.DiscoverUser;
import com.example.mvvmtest.model.Response.BaseResponse;
import com.example.mvvmtest.model.Response.DiscoverUsersResponse;
import com.example.mvvmtest.network.RetrofitCall;
import com.example.mvvmtest.util.Constant;
import com.example.mvvmtest.util.DiscoverAction;
import com.example.mvvmtest.util.Vices;
import com.example.mvvmtest.util.Zodiac;
import com.orhanobut.logger.Logger;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DiscoverRepository {

    private int currentUserId;

    @Inject
    RetrofitCall retrofitCall;

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
        //retrofitCall.getDiscoverUsers(Constant.SENDER, Constant.NICKNAME, callbackGetMessages);
        dataSet.add(new DiscoverUser(null, 1, 27, Zodiac.LEO, "Sofia", "La Habana", "Software engineer", "Musala Soft", "1.75", Vices.SOMETIMES, Vices.SOMETIMES));
        dataSet.add(new DiscoverUser(null, 2, 30, Zodiac.LEO, "Plovdiv", "Matanzas", "Software developer", "Musala Soft", "1.75", Vices.SOMETIMES, Vices.SOMETIMES));
    }

    public void userAction(int idUser, DiscoverAction action) {
        currentUserId = idUser;
        //retrofitCall.userAction(callbackUserAction);
        processUserActionResponse();
    }

    private Callback<DiscoverUsersResponse> callbackGetMessages = new Callback<DiscoverUsersResponse>() {
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

    private Callback<BaseResponse> callbackUserAction = new Callback<BaseResponse>() {
        @Override
        public void onResponse(Call<BaseResponse> call, Response<BaseResponse> response) {
            if (response.isSuccessful()) {
                processUserActionResponse();
            }
        }

        @Override
        public void onFailure(Call<BaseResponse> call, Throwable t) {

        }
    };

    private void processUserActionResponse() {
        for (DiscoverUser user : dataSet) {
            if (user.getIdUser() == currentUserId) {
                dataSet.remove(user);
                break;
            }
        }
        Log.d("GBC", "Quedan: " + dataSet.size());
    }
}
