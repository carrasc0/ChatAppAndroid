package com.example.mvvmtest.repository

import com.example.mvvmtest.dagger.component.ApiController
import com.example.mvvmtest.model.Response.BaseResponse
import com.example.mvvmtest.network.RetrofitCall
import retrofit2.Callback
import javax.inject.Inject

class MainActivityRepository {

    @Inject
    lateinit var retrofitCall: RetrofitCall

    init {
        ApiController.getAppComponent().inject(this)
    }

    fun setCoordinates(latitude: String, longitude: String) {
        retrofitCall.setCoordinates(latitude, longitude, setCoordinatesCallback)
    }

    val setCoordinatesCallback : Callback<BaseResponse>? = null

}