package com.example.mvvmtest.ui.activity

import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager

import android.os.Bundle
import android.text.TextUtils
import android.view.View
import android.widget.Toast

import com.example.mvvmtest.R
import com.example.mvvmtest.adapter.ChatAdapter
import com.example.mvvmtest.dagger.component.ApiController
import com.example.mvvmtest.manager.FlechPreferences
import com.example.mvvmtest.model.Message
import com.example.mvvmtest.model.Typing
import com.example.mvvmtest.util.Constant
import com.example.mvvmtest.viewmodel.ChatViewModel
import com.github.nkzawa.socketio.client.Socket

import javax.inject.Inject

import com.example.mvvmtest.util.ChatViewModelFactory
import com.example.mvvmtest.util.afterTextChanged
import com.example.mvvmtest.util.onFocusChange
import kotlinx.android.synthetic.main.activity_chat.*
import kotlinx.android.synthetic.main.content_chat.*

class ChatActivity : AppCompatActivity(), View.OnClickListener {

    private lateinit var chatViewModel: ChatViewModel
    private lateinit var chatAdapter: ChatAdapter
    private var nickname: Int? = null

    @Inject
    lateinit var socket: Socket

    @Inject
    lateinit var flechPreferences: FlechPreferences

    val isConnected: Boolean
        get() = socket.connected()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_chat)
        ApiController.getAppComponent().inject(this)
        nickname = intent.getIntExtra("nickname", Constant.NICKNAME)
        initViewModel()
        initAdapter()
        initViews()
        initSocket()
        checkChatStatus()
        setupEditTextBehavior()
    }


    private fun setupEditTextBehavior() {
        sendEditText.afterTextChanged {
            sendTyping(true)
        }
        sendEditText.onFocusChange {
            if (!it) sendTyping(false)
        }
    }

    private fun initViews() {
        sendButton.setOnClickListener(this)
    }

    private fun checkChatStatus() {
        if (isConnected) {
            toolbar.subtitle = "Connected"
        } else {
            toolbar.subtitle = "Disconnected"
        }
    }

    private fun initAdapter() {
        chatRecyclerView.layoutManager = LinearLayoutManager(this@ChatActivity)
        val mutableList: MutableList<Message> = arrayListOf()
        chatAdapter = ChatAdapter(mutableList)
        chatRecyclerView.adapter = chatAdapter

    }

    private fun initViewModel() {

        val viewModelFactory = ChatViewModelFactory(nickname!!)
        chatViewModel = ViewModelProviders.of(this@ChatActivity, viewModelFactory)
                .get(ChatViewModel::class.java)

        chatViewModel.getMessages()
        chatViewModel.messagesLiveData.observe(this, Observer {
            chatAdapter.addMessages(it)
        })

        chatViewModel.typingLiveData.observe(this, Observer {
            performTyping(it.value)
        })

    }

    private fun performTyping(typing: Boolean) {
        //todo manejar el typingLiveData (quitarlo o ponerlo)
    }

    private fun initSocket() {
        socket.connect()
        if (socket.connected()) {
            socket.on(Constant.SocketEvent.NEW_MESSAGE, chatViewModel.onNewMessageListener)
            socket.on(Constant.SocketEvent.TYPING, chatViewModel.onTypingListener)
        } else {
            Toast.makeText(baseContext, "No se pudo conectar", Toast.LENGTH_SHORT).show()
        }

    }

    private fun sendMessage() {
        sendTyping(false)
        val text = sendEditText!!.text.toString().trim { it <= ' ' }
        if (TextUtils.isEmpty(text)) {
            return
        }
        chatViewModel.sendMessage(socket, Message(Constant.SENDER, nickname!!, text))
        sendEditText.setText("")
    }

    private fun sendTyping(typing: Boolean) {
        chatViewModel.sendTyping(socket, Typing(flechPreferences.idUser, nickname!!, typing))
    }

    private fun disconnect() {
        socket.off(Constant.SocketEvent.NEW_MESSAGE)
        socket.off(Constant.SocketEvent.TYPING)
        socket.disconnect()
    }

    override fun onClick(v: View?) {
        when (v?.id) {
            R.id.sendButton -> {
                sendMessage()
            }
            else -> {

            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        disconnect()
    }

}
