package com.example.mvvmtest.viewmodel


import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.mvvmtest.dagger.component.ApiController
import com.example.mvvmtest.manager.FlechPreferences
import com.example.mvvmtest.manager.JsonManager
import com.example.mvvmtest.model.Message
import com.example.mvvmtest.model.Typing
import com.example.mvvmtest.repository.ChatRepository
import com.example.mvvmtest.util.Constant
import com.github.nkzawa.emitter.Emitter
import com.github.nkzawa.socketio.client.Socket
import kotlinx.coroutines.*
import org.json.JSONObject
import javax.inject.Inject
import kotlin.coroutines.CoroutineContext


class ChatViewModel(private val nickname: Int) : ViewModel() {

    @Inject
    lateinit var chatRepository: ChatRepository

    @Inject
    lateinit var flechPreferences: FlechPreferences

    val messagesLiveData: MutableLiveData<MutableList<Message>>
    val typingLiveData: MutableLiveData<Boolean>

    init {
        ApiController.getAppComponent().inject(this)
        messagesLiveData = MutableLiveData()
        typingLiveData = MutableLiveData()
    }

    //create a new Job
    private val parentJob = Job()
    //create a coroutine context with the job and the dispatcher
    private val coroutineContext: CoroutineContext
        get() = parentJob + Dispatchers.Default
    //create a coroutine scope with the coroutine context
    private val scope = CoroutineScope(coroutineContext)
    //live data that will be populated as news messages


    fun getMessages() {
        //launch the coroutine scope
        scope.launch {
            //get the messages
            val messages = chatRepository.getMessages(nickname)
            //post the value inside live data
            messagesLiveData.postValue(messages)
        }
    }

    fun sendMessage(socket: Socket, message: Message) {
        scope.launch {
            val jsonObject = JsonManager.createEmitSendMessage(message)
            socket.emit(Constant.SocketKey.FUNCTION_KEY, jsonObject)
        }
    }

    fun sendTyping(socket: Socket, typing: Typing) {
        scope.launch {
            val jsonObject = JsonManager.createEmitTyping(typing.sender, typing.nickname, typing.typing)
            socket.emit(Constant.SocketEvent.TYPING, jsonObject)
        }
    }

    val onNewMessageListener: Emitter.Listener = Emitter.Listener { args ->
        processOnNewMessage(args[0] as JSONObject)
    }

    val onTypingListener: Emitter.Listener = Emitter.Listener { args ->
        Log.d(Constant.TAG, "onTypingEvent ${args[0]}")
        processOnTyping(args[0] as JSONObject)
    }

    private fun processOnTyping(data: JSONObject) {
        val dataTyping = JsonManager.processTyping(data)
        if (!validateTyping(dataTyping as Typing)) return else typingLiveData.postValue(dataTyping.typing)
    }

    private fun processOnNewMessage(data: JSONObject) {
        val message = JsonManager.processNewMessage(data) ?: return
        if (!validateNewMessage(message)) {
            return
        } else {
            val newList = MutableList(1) { message }
            messagesLiveData.postValue(newList)
        }

    }

    private fun validateTyping(dataTyping: Typing): Boolean {
        return (Constant.SENDER == dataTyping.nickname) && (Constant.NICKNAME == dataTyping.sender)
    }

    private fun validateNewMessage(message: Message): Boolean {
        return ((Constant.SENDER == message.nickname) && (Constant.NICKNAME == message.sender) ||
                Constant.SENDER == message.sender && Constant.NICKNAME == message.nickname)
    }

    override fun onCleared() {
        super.onCleared()
        cancelRequest()
    }

    private fun cancelRequest() = coroutineContext.cancel()

}
