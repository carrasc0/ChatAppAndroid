package com.example.mvvmtest.util

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.mvvmtest.viewmodel.ChatViewModel
import com.example.mvvmtest.viewmodel.MatchViewModel

@Suppress("UNCHECKED_CAST")
class ChatViewModelFactory(private val nickname: Int) : ViewModelProvider.NewInstanceFactory() {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return ChatViewModel(nickname) as T
    }
}

@Suppress("UNCHECKED_CAST")
class MatchViewModelFactory() : ViewModelProvider.NewInstanceFactory() {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return MatchViewModel() as T
    }
}