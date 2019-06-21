package com.example.chatapp.model.Response

import com.example.chatapp.model.Message

data class GetMessagesResponse(val messages: MutableList<Message>) : BaseResponse()
