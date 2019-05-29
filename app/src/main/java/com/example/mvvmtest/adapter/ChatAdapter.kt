package com.example.mvvmtest.adapter

import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView

import com.example.mvvmtest.R
import com.example.mvvmtest.dagger.component.ApiController
import com.example.mvvmtest.manager.FlechPreferences
import com.example.mvvmtest.model.Message
import com.example.mvvmtest.util.Constant

import javax.inject.Inject

import com.example.mvvmtest.util.inflate
import kotlinx.android.synthetic.main.design_chat_nickname.view.*
import kotlinx.android.synthetic.main.design_chat_sender.view.*

class ChatAdapter(private val items: MutableList<Message>) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private val TYPE_SENDER = 1
    private val TYPE_NICKNAME = 2

    @Inject
    lateinit var sharedFlechPreferences: FlechPreferences

    init {
        ApiController.getAppComponent().inject(this)
    }

    override fun getItemViewType(position: Int): Int {
        return if (items[position].isSender(Constant.SENDER)) TYPE_SENDER else TYPE_NICKNAME
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val view: View
        if (viewType == TYPE_SENDER) {
            view = parent.inflate(R.layout.design_chat_sender, false)
            return SenderVH(view)
        } else {
            view = parent.inflate(R.layout.design_chat_nickname, false)
            return NicknameVH(view)
        }

    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (getItemViewType(position)) {
            TYPE_SENDER -> (holder as SenderVH).bind(items[position])
            TYPE_NICKNAME -> (holder as NicknameVH).bind(items[position])
        }
    }

    override fun getItemCount(): Int = items.size

    fun addMessages(otherItems: MutableList<Message>){
        this.items.addAll(otherItems)
        notifyDataSetChanged()
    }

    inner class SenderVH(itemView: View) : RecyclerView.ViewHolder(itemView) {

        init {

        }

        fun bind(message: Message) {
            itemView.textViewChatSender.text = message.body
        }
    }

    inner class NicknameVH(itemView: View) : RecyclerView.ViewHolder(itemView) {

        init {

        }

        fun bind(message: Message) {
            itemView.textViewChatNickname.text = message.body
        }
    }

}
