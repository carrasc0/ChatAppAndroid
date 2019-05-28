package com.example.mvvmtest.repository


import com.example.mvvmtest.dagger.component.ApiController
import com.example.mvvmtest.model.Message
import com.example.mvvmtest.network.ChatInterface
import com.orhanobut.logger.AndroidLogAdapter
import com.orhanobut.logger.Logger


class ChatRepository(private val chatInterface: ChatInterface) : BaseRepository() {

    init {
        Logger.addLogAdapter(AndroidLogAdapter())
        ApiController.getAppComponent().inject(this)
    }

    suspend fun getMessages(): MutableList<Message>? {
        return safeApiCall(
                call = { chatInterface.getMessages(1).await() },
                error = "Error fetching messages"
        )?.messagess?.toMutableList()
    }
}
