package com.example.mvvmtest.viewmodel;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.mvvmtest.dagger.component.ApiController;
import com.example.mvvmtest.manager.FlechPreferences;
import com.example.mvvmtest.manager.JsonManager;
import com.example.mvvmtest.model.Message;
import com.example.mvvmtest.repository.ChatRepository;
import com.example.mvvmtest.util.Constant;
import com.github.nkzawa.emitter.Emitter;
import com.github.nkzawa.socketio.client.Socket;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;


public class ChatViewModel extends ViewModel {

    @Inject
    protected FlechPreferences flechPreferences;

    @Inject
    protected ChatRepository chatRepository;

    private int nickname;

    private MutableLiveData<List<Message>> mMessages;
    private MutableLiveData<Boolean> isTyping;

    public ChatViewModel() {
        ApiController.getAppComponent().inject(this);
    }

    public void init(int nickname) {
        this.nickname = nickname;
        mMessages = chatRepository.getMessages();
        isTyping = new MutableLiveData<>();
        isTyping.postValue(false);
    }


    @Override
    protected void onCleared() {
        super.onCleared();
    }

    public LiveData<List<Message>> getMessages() {
        return mMessages;
    }

    public LiveData<Boolean> getIsTyping() {
        return isTyping;
    }

    public Emitter.Listener onNewMessage = new Emitter.Listener() {
        @Override
        public void call(Object... args) {
            Log.d("GBC", "Entro on new message");
            Log.d("GBC", args[0].toString());
            processOnNewMessage((JSONObject) args[0]);
        }
    };

    public Emitter.Listener onTyping = new Emitter.Listener() {
        @Override
        public void call(Object... args) {

        }
    };

    private void processOnNewMessage(JSONObject data) {
        Message message = JsonManager.processNewMessage(data);
        if (message == null){
            return;
        }
        List<Message> list;
        if (mMessages != null) {
            list = mMessages.getValue();
            list.add(message);
        } else {
            list = new ArrayList<>();
            list.add(message);
        }

        mMessages.setValue(list);
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

    public void sendMessage(Socket socket, Message message) {
        JSONObject object = JsonManager.createEmitSendMessage(message);
        socket.emit(Constant.SocketKey.FUNCTION_KEY, object);
    }

    public void sendTyping(Socket socket, int sender, int nickname) {
        socket.emit(Constant.SocketEvent.TYPING, sender, nickname);
    }


}
