package com.example.mvvmtest.model

data class Match(
        val id: Int,
        val created_at: String,
        val id_user: Int,
        val image: Int,
        val name: String,
        val age: Int,
        val last_message: String)