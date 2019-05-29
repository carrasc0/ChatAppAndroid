package com.example.mvvmtest.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

import com.example.mvvmtest.dagger.component.ApiController
import com.example.mvvmtest.manager.FlechPreferences
import com.example.mvvmtest.model.Match
import com.example.mvvmtest.repository.MatchRepository

import javax.inject.Inject

class MatchViewModel : ViewModel() {

    @Inject
    lateinit var flechPreferences: FlechPreferences

    @Inject
    lateinit var matchRepository: MatchRepository

    private var items: MutableLiveData<List<Match>>? = null

    val matchLiveData: LiveData<List<Match>>?
        get() = items

    init {
        ApiController.getAppComponent().inject(this)
    }

    fun getMatches() {
        items = matchRepository.getMatches()
    }
}
