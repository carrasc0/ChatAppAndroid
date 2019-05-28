package com.example.mvvmtest.ui.activity;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.text.TextUtils;
import android.widget.Button;
import android.widget.EditText;

import com.example.mvvmtest.R;
import com.example.mvvmtest.adapter.ChatAdapter;
import com.example.mvvmtest.dagger.component.ApiController;
import com.example.mvvmtest.manager.FlechPreferences;
import com.example.mvvmtest.model.Message;
import com.example.mvvmtest.util.Constant;
import com.example.mvvmtest.viewmodel.ChatViewModel;
import com.github.nkzawa.socketio.client.Socket;

import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class ChatActivity extends AppCompatActivity {

    private ChatViewModel chatViewModel;
    private ChatAdapter chatAdapter;
    private int nickname;

    @Inject
    protected Socket socket;

    @Inject
    protected FlechPreferences sharedFlechPreferences;

    @BindView(R.id.chatRecyclerView)
    protected RecyclerView recyclerView;

    @BindView(R.id.sendButton)
    protected Button sendButton;

    @BindView(R.id.sendEditText)
    protected EditText sendEditText;

    @BindView(R.id.toolbar)
    protected Toolbar toolbar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat);

        ButterKnife.bind(this);
        ApiController.getAppComponent().inject(this);
        nickname = getIntent().getIntExtra("nickname", Constant.NICKNAME);
        initViewModel();
        initSocket();
        initAdapter();
        checkChatStatus();

    }

    private void checkChatStatus() {
        if (isConnected()) {
            toolbar.setSubtitle("Connected");
        } else {
            toolbar.setSubtitle("Disconnected");
        }
    }

    private void initAdapter() {
        //chatAdapter = new ChatAdapter(ChatActivity.this, chatViewModel.getMessages().getValue());
        recyclerView.setAdapter(chatAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(ChatActivity.this));
    }

    private void initViewModel() {
        chatViewModel = ViewModelProviders.of(this).get(ChatViewModel.class);
       /* chatViewModel.init(nickname);
        chatViewModel.getMessages().observe(this, new Observer<List<Message>>() {
            @Override
            public void onChanged(@Nullable List<Message> messages) {
                chatAdapter.notifyDataSetChanged();
            }
        });
        chatViewModel.getIsTyping().observe(this, new Observer<Boolean>() {
            @Override
            public void onChanged(Boolean isTyping) {
                if (isTyping) {
                    toolbar.setSubtitle("Escribiendo...");
                } else {
                    checkChatStatus();
                }
            }
        });*/

    }

    private void initSocket() {
        socket.connect();
        //socket.on(Constant.SocketEvent.NEW_MESSAGE, chatViewModel.onNewMessage);
        //socket.on(Constant.SocketEvent.TYPING, chatViewModel.onTyping);
    }

    @OnClick(R.id.sendButton)
    protected void sendMessage() {
        String text = sendEditText.getText().toString().trim();
        if (TextUtils.isEmpty(text)) {
            return;
        }
        //chatViewModel.sendMessage(socket, new Message(Constant.SENDER, nickname, text));
        sendEditText.setText("");
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
