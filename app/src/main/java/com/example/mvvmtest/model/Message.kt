package com.example.mvvmtest.model

import com.example.mvvmtest.util.Constant

data class Message(val sender: Int, val nickname: Int, val body: String) {

    constructor(id: Int,
                created_at: String,
                sender: Int,
                nickname: Int,
                body: String,
                readed: Int) : this(sender, nickname, body)

    fun isSender(id: Int): Boolean {
        return id == Constant.SENDER
    }
}
