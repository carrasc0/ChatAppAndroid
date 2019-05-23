package com.example.mvvmtest.viewmodel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.mvvmtest.dagger.component.ApiController;
import com.example.mvvmtest.manager.FlechPreferences;
import com.example.mvvmtest.model.Match;
import com.example.mvvmtest.repository.MatchRepository;

import java.util.List;

import javax.inject.Inject;

public class MatchViewModel extends ViewModel {

    @Inject
    protected FlechPreferences flechPreferences;
    @Inject
    protected MatchRepository matchRepository;

    private MutableLiveData<List<Match>> items;

    public MatchViewModel() {
        ApiController.getAppComponent().inject(this);
    }

    public LiveData<List<Match>> getMatch() {
        return items;
    }

    public void init() {
        items = matchRepository.getMatches();
    }
}
