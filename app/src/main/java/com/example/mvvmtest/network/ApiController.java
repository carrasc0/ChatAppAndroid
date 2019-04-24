package com.example.mvvmtest.network;

import android.app.Application;

import com.github.nkzawa.socketio.client.IO;
import com.github.nkzawa.socketio.client.Socket;

import java.net.URISyntaxException;

public class ApiController extends Application {

    private Socket socket;
    private static final String URL = "http://yoururl.com";

    @Override
    public void onCreate() {
        super.onCreate();
        try {
            socket = IO.socket(URL);
        } catch (URISyntaxException e) {
            throw new RuntimeException(e);
        }
    }

    public Socket getSocket(){
        return socket;
    }

}
