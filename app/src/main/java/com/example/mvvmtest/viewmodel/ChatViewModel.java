package com.example.mvvmtest.viewmodel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.mvvmtest.dagger.component.ApiController;
import com.example.mvvmtest.manager.Preferences;
import com.example.mvvmtest.model.Message;
import com.example.mvvmtest.repository.ChatRepository;
import com.github.nkzawa.socketio.client.Socket;

import java.util.List;

import javax.inject.Inject;


public class ChatViewModel extends ViewModel {

    @Inject
    protected Socket socket;

    @Inject
    Preferences preferences;

    ChatRepository chatRepository;

    private MutableLiveData<List<Message>> mMessages;

    public ChatViewModel() {
        ApiController.getAppComponent().inject(this);
    }

    public void init() {
        if (mMessages != null) {
            return;
        }
        chatRepository = new ChatRepository();
        connect();
        mMessages = chatRepository.getMessages();
    }


    @Override
    protected void onCleared() {
        super.onCleared();
        disconnect();
    }

    public LiveData<List<Message>> getMessages() {
        return mMessages;
    }


    public void sendMessage(Message message) {

    }

    public boolean isConnected() {
        return socket.connected();
    }

    public void connect() {
        socket.connect();
    }

    private void disconnect() {
        socket.disconnect();
    }


}
