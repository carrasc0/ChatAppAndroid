package com.example.chatapp.dagger.module

import android.app.Application

import com.example.chatapp.manager.FlechPreferences
import com.example.chatapp.network.ChatInterface
import com.example.chatapp.repository.ChatRepository

import javax.inject.Singleton

import dagger.Module
import dagger.Provides

@Module
class AppModule(private val application: Application) {

    @Provides
    @Singleton
    internal fun provideApplication(): Application {
        return application
    }

    @Provides
    @Singleton
    internal fun providePreferences(): FlechPreferences {
        return FlechPreferences(application)
    }

    @Provides
    @Singleton
    internal fun provideChatRepository(chatInterface: ChatInterface): ChatRepository {
        return ChatRepository(chatInterface)
    }

}
