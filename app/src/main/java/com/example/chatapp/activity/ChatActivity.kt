package com.example.chatapp.activity

import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager

import android.os.Bundle
import android.text.TextUtils
import android.util.Log
import android.view.View

import com.example.chatapp.R
import com.example.chatapp.adapter.ChatAdapter
import com.example.chatapp.dagger.component.ApiController
import com.example.chatapp.manager.FlechPreferences
import com.example.chatapp.model.Message
import com.example.chatapp.model.Typing
import com.example.chatapp.util.*
import com.example.chatapp.viewmodel.ChatViewModel
import com.github.nkzawa.socketio.client.Socket

import javax.inject.Inject

import kotlinx.android.synthetic.main.content_chat.*

class ChatActivity : AppCompatActivity(), View.OnClickListener {

    private lateinit var chatViewModel: ChatViewModel
    private lateinit var chatAdapter: ChatAdapter
    private lateinit var layoutManager: LinearLayoutManager
    private var nickname: Int? = null

    @Inject
    lateinit var socket: Socket

    @Inject
    lateinit var flechPreferences: FlechPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_chat)
        ApiController.getAppComponent().inject(this)
        //nickname = intent.getIntExtra("nickname", Constant.NICKNAME)
        nickname = Constant.NICKNAME
        setupViews()
        initViewModel()
        initAdapter()
        initViews()
        initSocket()
        setupEditTextBehavior()

    }

    fun setupViews() {

    }

    private fun setupEditTextBehavior() {
        sendEditText.afterTextChanged {
            if (it.isEmpty()) sendTyping(false) else sendTyping(true)
        }
    }

    private fun initViews() {
        sendButton.setOnClickListener(this)
    }

    private fun initAdapter() {
        layoutManager = LinearLayoutManager(this@ChatActivity)
        layoutManager.stackFromEnd = true
        chatRecyclerView.layoutManager = layoutManager
        val mutableList: MutableList<Message> = arrayListOf()
        chatAdapter = ChatAdapter(mutableList)
        chatRecyclerView.adapter = chatAdapter

        chatRecyclerView.onLayoutChange {
            if (it) scrollToBottom()
        }

    }

    private fun scrollToBottom() {
        layoutManager.smoothScrollToPosition(chatRecyclerView, null, chatAdapter.itemCount)
    }

    private fun initViewModel() {

        val viewModelFactory = ChatViewModelFactory(nickname!!)
        chatViewModel = ViewModelProviders.of(this@ChatActivity, viewModelFactory)
                .get(ChatViewModel::class.java)

        chatViewModel.getMessages()
        chatViewModel.messagesLiveData.observe(this, Observer {
            if (it != null) {
                chatAdapter.addMessages(it)
                scrollToBottom()
            }
        })

        chatViewModel.typingLiveData.observe(this, Observer {
            performTyping(it)
        })

    }

    private fun performTyping(typing: Boolean) {
        Log.d(Constant.TAG, "entro en performTyping $typing")
        if (typing) chatLinearLayout.visibility = View.VISIBLE else chatLinearLayout.visibility = View.GONE
    }

    private fun initSocket() {
        socket.connect()
        socket.on(Constant.SocketEvent.NEW_MESSAGE, chatViewModel.onNewMessageListener)
        socket.on(Constant.SocketEvent.TYPING, chatViewModel.onTypingListener)

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
        chatViewModel.sendTyping(socket, Typing(Constant.SENDER, Constant.NICKNAME, typing))
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
