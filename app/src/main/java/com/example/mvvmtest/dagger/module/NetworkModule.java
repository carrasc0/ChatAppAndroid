package com.example.mvvmtest.dagger.module;

import com.github.nkzawa.socketio.client.IO;
import com.github.nkzawa.socketio.client.Socket;

import java.net.URISyntaxException;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class NetworkModule {

    @Provides
    @Singleton
    Socket provideSocket(String url) {
        try {
            return IO.socket(url);
        } catch (URISyntaxException e) {
            throw new RuntimeException(e);
        }
    }



}
