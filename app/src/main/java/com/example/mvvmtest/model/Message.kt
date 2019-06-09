package com.example.mvvmtest.model

data class Message(
        val id: Int,
        val created_at: String,
        val sender: Int,
        val nickname: Int,
        val body: String,
        val readed: Int)
