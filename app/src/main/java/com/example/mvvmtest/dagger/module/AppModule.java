package com.example.mvvmtest.dagger.module;

import android.app.Application;

import com.example.mvvmtest.manager.Preferences;
import com.example.mvvmtest.repository.ChatRepository;

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
    Preferences providePreferences() {
        return new Preferences(application);
    }

    @Provides
    @Singleton
    ChatRepository provideChatRepository(){
        return new ChatRepository();
    }

}
