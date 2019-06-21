package com.example.chatapp.model

import com.example.chatapp.util.Constant

data class Message(val sender: Int, val nickname: Int, val body: String) {

    constructor(id: Int,
                created_at: String,
                sender: Int,
                nickname: Int,
                body: String,
                readed: Int) : this(sender, nickname, body)

    fun isSender(): Boolean {
        return sender == Constant.SENDER
    }
}
