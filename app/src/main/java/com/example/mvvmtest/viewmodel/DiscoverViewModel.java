package com.example.mvvmtest.viewmodel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.mvvmtest.dagger.component.ApiController;
import com.example.mvvmtest.manager.Preferences;
import com.example.mvvmtest.model.DiscoverUser;
import com.example.mvvmtest.repository.DiscoverRepository;
import com.example.mvvmtest.util.DiscoverAction;

import java.util.List;

import javax.inject.Inject;

public class DiscoverViewModel extends ViewModel {

    @Inject
    protected Preferences preferences;

    @Inject
    DiscoverRepository discoverRepository;

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
        discoverRepository.userAction(idUser, action);
    }

}
