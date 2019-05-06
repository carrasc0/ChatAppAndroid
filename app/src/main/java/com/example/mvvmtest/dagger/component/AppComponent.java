package com.example.mvvmtest.dagger.component;

import com.example.mvvmtest.adapter.ChatAdapter;
import com.example.mvvmtest.adapter.MatchAdapter;
import com.example.mvvmtest.dagger.module.AppModule;
import com.example.mvvmtest.dagger.module.NetworkModule;
import com.example.mvvmtest.manager.Preferences;
import com.example.mvvmtest.network.RetrofitCall;
import com.example.mvvmtest.repository.ChatRepository;
import com.example.mvvmtest.repository.DiscoverRepository;
import com.example.mvvmtest.view.activity.ChatActivity;
import com.example.mvvmtest.view.fragment.DiscoverFragment;
import com.example.mvvmtest.viewmodel.ChatViewModel;
import com.example.mvvmtest.viewmodel.DiscoverViewModel;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(modules = {NetworkModule.class, AppModule.class})
public interface AppComponent {

    void inject(ChatActivity activity);

    void inject(DiscoverFragment discoverFragment);

    void inject(ChatViewModel chatViewModel);

    void inject(DiscoverViewModel
                        discoverViewModel);

    void inject(Preferences preferences);

    void inject(RetrofitCall retrofitCall);

    void inject(ChatRepository chatRepository);

    void inject(DiscoverRepository discoverRepository);

    void inject(ChatAdapter chatAdapter);

    void inject(MatchAdapter matchAdapter);

    void inject(AppModule appModule);

}
