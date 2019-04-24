package com.example.mvvmtest.dagger.component;

import android.app.Application;

import com.example.mvvmtest.dagger.module.AppModule;
import com.example.mvvmtest.dagger.module.NetworkModule;


public class ApiController extends Application {

    private AppComponent appComponent;

    @Override
    public void onCreate() {
        super.onCreate();

        appComponent = DaggerAppComponent.builder()
                .appModule(new AppModule(this))
                .networkModule(new NetworkModule(""))
                .build();
    }

    public AppComponent getAppComponent() {
        return appComponent;
    }
}
