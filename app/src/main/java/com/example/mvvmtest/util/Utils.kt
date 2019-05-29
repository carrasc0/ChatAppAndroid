package com.example.mvvmtest.util

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.mvvmtest.viewmodel.ChatViewModel

@Suppress("UNCHECKED_CAST")
class ChatViewModelFactory(private val nickname: Int) : ViewModelProvider.NewInstanceFactory() {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return ChatViewModel(nickname) as T
    }
}