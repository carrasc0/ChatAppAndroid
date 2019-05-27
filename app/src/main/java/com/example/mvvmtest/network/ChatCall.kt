package com.example.mvvmtest.network

import com.example.mvvmtest.dagger.component.ApiController
import com.example.mvvmtest.manager.FlechPreferences

import javax.inject.Inject

class ChatCall(private val chatInterface: ChatInterface) {

    @Inject
    lateinit var flechPreferences: FlechPreferences

    init {
        ApiController.getAppComponent().inject(this)
    }

    //suspend fun getMessages(nickname: Int): ResultResponse<List<Message>> = withContext(Dispatchers.IO){
    //val getMessagesRequest = GetMessagesRequest(flechPreferences.idUser, nickname)
    //val result = chatInterface.getMessages(getMessagesRequest).await()
    //}


}