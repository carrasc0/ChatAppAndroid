package com.example.chatapp.dagger.component

import com.example.chatapp.adapter.ChatAdapter
import com.example.chatapp.dagger.module.AppModule
import com.example.chatapp.dagger.module.NetworkModule
import com.example.chatapp.manager.FlechPreferences
import com.example.chatapp.repository.ChatRepository
import com.example.chatapp.activity.ChatActivity
import com.example.chatapp.viewmodel.ChatViewModel

import javax.inject.Singleton

import dagger.Component

@Singleton
@Component(modules = [AppModule::class, NetworkModule::class])
interface AppComponent {

    fun inject(activity: ChatActivity)

    fun inject(chatViewModel: ChatViewModel)

    fun inject(flechPreferences: FlechPreferences)

    fun inject(chatRepository: ChatRepository)

    fun inject(chatAdapter: ChatAdapter)

    fun inject(appModule: AppModule)

}
