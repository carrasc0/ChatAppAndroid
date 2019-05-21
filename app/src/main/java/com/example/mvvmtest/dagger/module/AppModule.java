package com.example.mvvmtest.dagger.module;

import android.app.Application;

import com.example.mvvmtest.manager.FlechPreferences;
import com.example.mvvmtest.repository.ChatRepository;
import com.example.mvvmtest.repository.DiscoverRepository;
import com.example.mvvmtest.repository.MatchRepository;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class AppModule {

    private Application application;

    public AppModule(Application mApplication) {
        this.application = mApplication;
    }

    @Provides
    @Singleton
    Application provideApplication() {
        return application;
    }

    @Provides
    @Singleton
    FlechPreferences providePreferences() {
        return new FlechPreferences(application);
    }

    @Provides
    @Singleton
    ChatRepository provideChatRepository() {
        return new ChatRepository();
    }

    @Provides
    @Singleton
    DiscoverRepository provideDiscoverRepository() {
        return new DiscoverRepository();
    }

    @Provides
    @Singleton
    MatchRepository provideMatchRepository() {
        return new MatchRepository();
    }

}
