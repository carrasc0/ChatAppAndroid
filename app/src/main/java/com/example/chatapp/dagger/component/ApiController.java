package com.example.chatapp.dagger.component;


import android.app.Application;

import com.example.chatapp.dagger.module.AppModule;
import com.example.chatapp.dagger.module.NetworkModule;
import com.facebook.FacebookSdk;
import com.facebook.appevents.AppEventsLogger;

public class ApiController extends Application {

    private static AppComponent appComponent;
    //todo ver como meter el id del usuario aqui o en uno de los componentes

    @Override
    public void onCreate() {
        super.onCreate();

        appComponent = DaggerAppComponent.builder()
                .appModule(new AppModule(this))
                .networkModule(new NetworkModule("http://10.0.11.71:8000/chat", "http://10.0.11.71:3000"))
                .build();

    }

    public static AppComponent getAppComponent() {
        return appComponent;
    }

}
