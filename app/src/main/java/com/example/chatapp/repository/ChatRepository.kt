package com.example.chatapp.repository


import com.example.chatapp.dagger.component.ApiController
import com.example.chatapp.manager.FlechPreferences
import com.example.chatapp.model.Message
import com.example.chatapp.model.Request.GetMessagesRequest
import com.example.chatapp.network.ChatInterface
import com.example.chatapp.util.Constant
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
                call = { chatInterface.getMessages(GetMessagesRequest(Constant.SENDER, Constant.NICKNAME)).await() },
                error = "Error fetching messages"
        )?.messages?.toMutableList()
    }
}
