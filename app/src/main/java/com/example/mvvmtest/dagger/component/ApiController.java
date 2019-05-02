package com.example.mvvmtest.dagger.component;

import android.app.Application;

import com.example.mvvmtest.dagger.module.AppModule;
import com.example.mvvmtest.dagger.module.NetworkModule;
import com.example.mvvmtest.manager.Preferences;
import com.github.nkzawa.socketio.client.IO;
import com.github.nkzawa.socketio.client.Socket;

import java.net.URISyntaxException;

import javax.inject.Inject;


public class ApiController extends Application {

    private static AppComponent appComponent;
    //todo ver como meter el ide del usuario aqui o en uno de los componentes

    @Override
    public void onCreate() {
        super.onCreate();

        appComponent = DaggerAppComponent.builder()
                .appModule(new AppModule(this))
                .networkModule(new NetworkModule("http://10.0.11.71:3000", "http://10.0.11.71:8000/chat"))
                .build();

    }

    public static AppComponent getAppComponent() {
        return appComponent;
    }

}
