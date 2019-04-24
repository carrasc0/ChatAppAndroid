package com.example.mvvmtest.viewmodel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.example.mvvmtest.model.Message;
import com.github.nkzawa.socketio.client.Socket;
import javax.inject.Inject;


public class ChatViewModel extends ViewModel {

    @Inject
    Socket socket;

    //public final LiveData<Message> messageLiveData = new LiveData<>();

    public ChatViewModel() {
    }

    @Override
    protected void onCleared() {
        super.onCleared();
        disconnect();
    }

    public void sendMessage(Message message){

    }

    public void connect() {
        socket.connect();
    }

    private void disconnect() {
        socket.disconnect();
    }


}
