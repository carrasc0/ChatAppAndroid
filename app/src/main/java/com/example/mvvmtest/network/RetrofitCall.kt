package com.example.mvvmtest.network

import com.example.mvvmtest.dagger.component.ApiController
import com.example.mvvmtest.model.Request.GetMessagesRequest
import com.example.mvvmtest.model.Response.BaseResponse
import com.example.mvvmtest.model.Response.DiscoverUsersResponse
import com.example.mvvmtest.model.Response.GetMessagesResponse

import retrofit2.Call
import retrofit2.Callback

class RetrofitCall(private val retrofitInterface: RetrofitInterface) {

    init {
        ApiController.getAppComponent().inject(this)
    }

    fun getDiscoverUsers(sender: Int, nickname: Int, callback: Callback<DiscoverUsersResponse>) {
        val getMessagesRequest = GetMessagesRequest(sender, nickname)
        val call = retrofitInterface.getDiscoverUsers(getMessagesRequest)
        call.enqueue(callback)
    }

    fun userAction(callback: Callback<BaseResponse>) {
        val call = retrofitInterface.userAction()
        call.enqueue(callback)
    }

    fun setCoordinates(latitude: String, longitude: String, callback: Callback<BaseResponse>) {
        //Call<BaseResponse> call = retrofitInterface.setCoordinates();
        //call.enqueue(callback);
    }

}
