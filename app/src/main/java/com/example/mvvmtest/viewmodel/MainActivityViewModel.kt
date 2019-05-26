package com.example.mvvmtest.viewmodel

import androidx.lifecycle.ViewModel
import com.example.mvvmtest.dagger.component.ApiController
import com.example.mvvmtest.manager.FlechPreferences
import com.example.mvvmtest.repository.MainActivityRepository
import javax.inject.Inject

class MainActivityViewModel : ViewModel() {

    @Inject
    lateinit var preferences: FlechPreferences

    @Inject
    lateinit var repository: MainActivityRepository

    init {
        ApiController.getAppComponent().inject(this)
    }

    fun setCoordinates(latitude: String, longitude: String) {
        repository.setCoordinates(latitude, longitude)
    }

}