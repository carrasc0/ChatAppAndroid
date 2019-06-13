package com.example.mvvmtest.model.Response

import com.example.mvvmtest.model.Message

data class GetMessagesResponse(val messages: MutableList<Message>) : BaseResponse()
