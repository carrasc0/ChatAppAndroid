package com.example.mvvmtest.dagger.module

import android.app.Application

import com.example.mvvmtest.manager.FlechPreferences
import com.example.mvvmtest.repository.ChatRepository
import com.example.mvvmtest.repository.DiscoverRepository
import com.example.mvvmtest.repository.MainActivityRepository
import com.example.mvvmtest.repository.MatchRepository
import com.example.mvvmtest.ui.fragment.ProfileFragment
import com.orhanobut.logger.AndroidLogAdapter
import com.orhanobut.logger.Logger

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
    internal fun provideChatRepository(): ChatRepository {
        return ChatRepository()
    }

    @Provides
    @Singleton
    internal fun provideDiscoverRepository(): DiscoverRepository {
        return DiscoverRepository()
    }

    @Provides
    @Singleton
    internal fun provideMatchRepository(): MatchRepository {
        return MatchRepository()
    }

    @Provides
    @Singleton
    internal fun provideMainActivityRepository(): MainActivityRepository {
        return MainActivityRepository()
    }

}
