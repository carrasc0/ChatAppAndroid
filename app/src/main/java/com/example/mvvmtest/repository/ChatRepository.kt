package com.example.mvvmtest.repository


import com.example.mvvmtest.dagger.component.ApiController
import com.example.mvvmtest.manager.FlechPreferences
import com.example.mvvmtest.model.Message
import com.example.mvvmtest.model.Request.GetMessagesRequest
import com.example.mvvmtest.network.ChatInterface
import com.orhanobut.logger.AndroidLogAdapter
import com.orhanobut.logger.Logger
import javax.inject.Inject


class ChatRepository(private val chatInterface: ChatInterface) : BaseRepository() {

    @Inject
    lateinit var flechPreferences: FlechPreferences

    init {
        Logger.addLogAdapter(AndroidLogAdapter())
        ApiController.getAppComponent().inject(this)
    }

    suspend fun getMessages(nickname: Int): MutableList<Message>? {
        return safeApiCall(
                call = { chatInterface.getMessages(GetMessagesRequest(flechPreferences.idUser, nickname)).await() },
                error = "Error fetching messages"
        )?.messagess?.toMutableList()
    }
}
