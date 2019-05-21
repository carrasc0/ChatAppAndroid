package com.example.mvvmtest.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.example.mvvmtest.dagger.component.ApiController
import com.example.mvvmtest.manager.FlechPreferences
import com.example.mvvmtest.model.Match
import com.example.mvvmtest.repository.MatchRepository
import javax.inject.Inject
import androidx.lifecycle.MutableLiveData

class MatchViewModel : ViewModel() {

    @Inject
    lateinit var preferences: FlechPreferences

    @Inject
    lateinit var respository: MatchRepository

    init {
        ApiController.getAppComponent().inject(this)
    }

    private val matchs: MutableLiveData<List<Match>> by lazy {
        MutableLiveData<List<Match>>().also {
            loadMatch()
        }
    }


    fun getMatch(): LiveData<List<Match>> {
        return matchs
    }


    private fun loadMatch() {
        respository.getMatches()
    }
}