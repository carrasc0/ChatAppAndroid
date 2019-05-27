package com.example.mvvmtest.repository


import androidx.lifecycle.MutableLiveData

import com.example.mvvmtest.dagger.component.ApiController
import com.example.mvvmtest.model.Message
import com.example.mvvmtest.network.ChatCall
import com.orhanobut.logger.AndroidLogAdapter
import com.orhanobut.logger.Logger
import java.util.ArrayList
import javax.inject.Inject


class ChatRepository {

    @Inject
    lateinit var chatCall: ChatCall

    private val dataSet = ArrayList<Message>()

    init {
        Logger.addLogAdapter(AndroidLogAdapter())
        ApiController.getAppComponent().inject(this)
    }

    val messages: MutableLiveData<List<Message>>
        get() {
            //setMessages()
            val data = MutableLiveData<List<Message>>()
            data.value = dataSet
            return data
        }

    private fun setMessages(nickname: Int) {
        //val messages = chatCall.getMessages(nickname)
    }
}
