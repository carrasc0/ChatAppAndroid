package com.example.mvvmtest.dagger.component;

import com.example.mvvmtest.dagger.module.NetworkModule;
import com.example.mvvmtest.view.activity.ChatActivity;
import com.example.mvvmtest.viewmodel.ChatViewModel;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(modules = {NetworkModule.class})
public interface AppComponent {

    void inject(ChatActivity activity);

    void inject(ChatViewModel chatViewModel);
}
