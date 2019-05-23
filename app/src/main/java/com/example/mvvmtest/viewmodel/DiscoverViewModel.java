package com.example.mvvmtest.viewmodel;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.mvvmtest.dagger.component.ApiController;
import com.example.mvvmtest.manager.FlechPreferences;
import com.example.mvvmtest.model.DiscoverUser;
import com.example.mvvmtest.repository.DiscoverRepository;
import com.example.mvvmtest.util.DiscoverAction;

import java.util.List;

import javax.inject.Inject;

public class DiscoverViewModel extends ViewModel {

    @Inject
    protected FlechPreferences flechPreferences;

    @Inject
    protected DiscoverRepository discoverRepository;

    private MutableLiveData<List<DiscoverUser>> users;

    public DiscoverViewModel() {
        ApiController.getAppComponent().inject(this);
    }

    public void init() {
        users = discoverRepository.getDiscoverUsers();
    }

    @Override
    protected void onCleared() {
        super.onCleared();
    }

    public LiveData<List<DiscoverUser>> getDiscoverUsers() {
        return users;
    }

    public void onUserAction(int idUser, DiscoverAction action) {
        //discoverRepository.userAction(idUser, action);
        //init();
        List<DiscoverUser> list = users.getValue();
        for (DiscoverUser user :list) {
            if (user.getIdUser() == idUser) {
                list.remove(user);
                break;
            }
        }
        users.setValue(list);
        Log.d("GBC", "Quedan VM: " + users.getValue().size());
    }

}