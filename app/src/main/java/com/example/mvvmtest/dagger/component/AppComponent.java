package com.example.mvvmtest.dagger.component;

import com.example.mvvmtest.adapter.ChatAdapter;
import com.example.mvvmtest.dagger.module.AppModule;
import com.example.mvvmtest.dagger.module.NetworkModule;
import com.example.mvvmtest.manager.Preferences;
import com.example.mvvmtest.network.RetrofitCall;
import com.example.mvvmtest.repository.ChatRepository;
import com.example.mvvmtest.view.activity.ChatActivity;
import com.example.mvvmtest.viewmodel.ChatViewModel;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(modules = {NetworkModule.class, AppModule.class})
public interface AppComponent {

    void inject(ChatActivity activity);

    void inject(ChatViewModel chatViewModel);

    void inject(Preferences preferences);

    void inject(RetrofitCall retrofitCall);

    void inject(ChatRepository chatRepository);

    void inject(ChatAdapter chatAdapter);

    void inject(ApiController apiController);

}
