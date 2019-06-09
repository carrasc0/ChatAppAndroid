package com.example.mvvmtest.repository

import android.util.Log

import androidx.lifecycle.MutableLiveData

import com.example.mvvmtest.dagger.component.ApiController
import com.example.mvvmtest.model.DiscoverUser
import com.example.mvvmtest.model.Response.BaseResponse
import com.example.mvvmtest.model.Response.DiscoverUsersResponse
import com.example.mvvmtest.network.RetrofitCall
import com.example.mvvmtest.util.DiscoverAction
import com.example.mvvmtest.util.Vices
import com.example.mvvmtest.util.Zodiac
import com.orhanobut.logger.Logger
import java.util.ArrayList
import javax.inject.Inject
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class DiscoverRepository {

    private var currentUserId: Int = 0

    @Inject
    lateinit var retrofitCall: RetrofitCall

    private val dataSet = ArrayList<DiscoverUser>()

    init {
        ApiController.getAppComponent().inject(this)
    }

    fun getDiscoverUsers(): MutableLiveData<List<DiscoverUser>> {
        setDiscoverUsers()
        val data = MutableLiveData<List<DiscoverUser>>()
        data.value = dataSet
        return data
    }

    private val callbackGetMessages = object : Callback<DiscoverUsersResponse> {
        override fun onResponse(call: Call<DiscoverUsersResponse>, response: Response<DiscoverUsersResponse>) {
            if (response.isSuccessful) {
                dataSet.addAll(response.body()!!.discoverUsers)
            } else {
                Logger.e("Error get users", response.errorBody())
            }
        }

        override fun onFailure(call: Call<DiscoverUsersResponse>, t: Throwable) {
            Log.e("GBC", t.message + "    " + t.toString())
        }
    }

    private val callbackUserAction = object : Callback<BaseResponse> {
        override fun onResponse(call: Call<BaseResponse>, response: Response<BaseResponse>) {
            if (response.isSuccessful) {
                processUserActionResponse()
            }
        }

        override fun onFailure(call: Call<BaseResponse>, t: Throwable) {

        }
    }

    private fun setDiscoverUsers() {
        //retrofitCall.getDiscoverUsers(Constant.SENDER, Constant.NICKNAME, callbackGetMessages);
        dataSet.add(DiscoverUser(1, 27, Zodiac.LEO.name, 300, "Sofia", "La Habana", "Software engineer", "Musala Soft", "1.75", Vices.SOMETIMES.name, Vices.SOMETIMES.name))
        dataSet.add(DiscoverUser(2, 30, Zodiac.CANCER.name, 2000, "Plovdiv", "Matanzas", "Software developer", "Musala Soft", "1.75", Vices.SOMETIMES.name, Vices.SOMETIMES.name))
    }

    fun userAction(idUser: Int, action: DiscoverAction) {
        currentUserId = idUser
        //retrofitCall.userAction(callbackUserAction);
        processUserActionResponse()
    }

    private fun processUserActionResponse() {
        for (user in dataSet) {
            if (user.idUser == currentUserId) {
                dataSet.remove(user)
                break
            }
        }
        Log.d("GBC", "Quedan: " + dataSet.size)
    }
}
