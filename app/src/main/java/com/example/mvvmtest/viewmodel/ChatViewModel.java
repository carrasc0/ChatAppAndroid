package com.example.mvvmtest.viewmodel;

import android.arch.lifecycle.ViewModel;
import com.github.nkzawa.socketio.client.Socket;
import javax.inject.Inject;


public class ChatViewModel extends ViewModel {

    @Inject
    Socket socket;

    public void init() {

    }

}
