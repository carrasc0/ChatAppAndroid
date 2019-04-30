package com.example.mvvmtest.viewmodel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.mvvmtest.dagger.component.ApiController;
import com.example.mvvmtest.manager.Preferences;
import com.example.mvvmtest.model.Message;
import com.example.mvvmtest.repository.ChatRepository;
import com.example.mvvmtest.util.Constant;
import com.github.nkzawa.emitter.Emitter;
import com.github.nkzawa.socketio.client.Socket;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import javax.inject.Inject;


public class ChatViewModel extends ViewModel {

    private Socket socket;

    @Inject
    Preferences preferences;

    ChatRepository chatRepository;

    private MutableLiveData<List<Message>> mMessages;
    private MutableLiveData<Boolean> isTyping;

    public ChatViewModel() {
        ApiController.getAppComponent().inject(this);
    }

    public void init() {
        socket = ApiController.getSocket();
        connect();
        suscribeSocketEvents();
        mMessages = new MutableLiveData<>();
        if (mMessages != null && isTyping != null) {
            return;
        }
        chatRepository = new ChatRepository();
        //mMessages = chatRepository.getMessages();
        isTyping = new MutableLiveData<>();
        isTyping.postValue(false);

    }


    @Override
    protected void onCleared() {
        super.onCleared();
        disconnect();
    }

    public LiveData<List<Message>> getMessages() {
        return mMessages;
    }

    public LiveData<Boolean> getIsTyping() {
        return isTyping;
    }

    private void suscribeSocketEvents() {
        if (socket != null && socket.connected()) {
            socket.on(Constant.SocketEvent.NEW_MESSAGE, onNewMessage);
            socket.on(Constant.SocketEvent.TYPING, onTyping);
        }
    }

    private Emitter.Listener onNewMessage = new Emitter.Listener() {
        @Override
        public void call(Object... args) {
            processOnNewMessage((JSONObject) args[0]);
        }
    };

    private Emitter.Listener onTyping = new Emitter.Listener() {
        @Override
        public void call(Object... args) {

        }
    };

    private void processOnNewMessage(JSONObject data) {
        try {
            int sender = data.getInt("sender");
            int nickname = data.getInt("nickname");
            String body = data.getString("body");

            List<Message> list;
            if (mMessages != null) {
                list = mMessages.getValue();
                list.add(new Message(sender, nickname, body));
            } else {
                list = new ArrayList<>();
                list.add(new Message(sender, nickname, body));
            }

            mMessages.setValue(list);
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    private void processOnTyping(JSONObject data) {
        try {
            int sender = data.getInt("sender");
            int nickname = data.getInt("nickname");
            boolean isTyping = data.getBoolean("isTyping");


        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    public void sendMessage(Message message) {
        socket.emit(Constant.SocketEvent.NEW_MESSAGE, message);
    }

    public void sendTyping(int sender, int nickname) {
        socket.emit(Constant.SocketEvent.TYPING, sender, nickname);
    }

    public boolean isConnected() {
        return socket.connected();
    }

    private void connect() {
        socket.connect();
    }

    private void disconnect() {
        socket.off(Constant.SocketEvent.NEW_MESSAGE);
        socket.off(Constant.SocketEvent.TYPING);
        socket.disconnect();
    }


}
