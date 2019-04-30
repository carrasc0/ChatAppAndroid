package com.example.mvvmtest.dagger.component;

import android.app.Application;

import com.example.mvvmtest.dagger.module.AppModule;
import com.example.mvvmtest.dagger.module.NetworkModule;
import com.github.nkzawa.socketio.client.IO;
import com.github.nkzawa.socketio.client.Socket;

import java.net.URISyntaxException;


public class ApiController extends Application {

    private static AppComponent appComponent;
    private static Socket socket;

    @Override
    public void onCreate() {
        super.onCreate();

        appComponent = DaggerAppComponent.builder()
                .appModule(new AppModule(this))
                .networkModule(new NetworkModule("http://127.0.0.1:3000"))
                .build();

        try {
            socket = IO.socket("http://127.0.0.1:8000");
        } catch (URISyntaxException e) {
            throw new RuntimeException(e);
        }

    }

    public static AppComponent getAppComponent() {
        return appComponent;
    }

    public static Socket getSocket(){
        return socket;
    }
}
