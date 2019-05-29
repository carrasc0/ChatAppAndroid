package com.example.mvvmtest.manager

import android.util.Log
import com.example.mvvmtest.model.Message
import com.example.mvvmtest.model.Typing
import com.example.mvvmtest.util.Constant

import org.json.JSONException
import org.json.JSONObject

object JsonManager {


    fun createEmitSendMessage(message: Message): JSONObject {

        val obj = JSONObject()
        try {

            val params = JSONObject()
            params.put("sender", message.sender)
            params.put("nickname", message.nickname)
            params.put("body", message.body)

            obj.put(Constant.SocketKey.FN_KEY, Constant.SocketEvent.NEW_MESSAGE)
            obj.put(Constant.SocketKey.PARAMS_KEY, params)

            return obj
        } catch (e: JSONException) {
            e.printStackTrace()
        }

        return obj

    }

    fun createEmitTyping(sender: Int, nickname: Int, isTyping: Boolean): JSONObject {

        val obj = JSONObject()

        try {

            obj.put("sender", sender)
            obj.put("nickname", nickname)
            obj.put("typing", isTyping)

            return obj

        } catch (e: JSONException) {
            e.printStackTrace()
        }

        return obj
    }

    fun processNewMessage(data: JSONObject): Message? {
        try {
            Log.d(Constant.TAG, "message value: " + data.toString())
            val sender = data.getInt("sender")
            val nickname = data.getInt("nickname")
            val body = data.getString("body")

            return Message(sender, nickname, body)
        } catch (e: JSONException) {
            e.printStackTrace()
        }

        return null
    }

    fun processTyping(data: JSONObject): Typing? {
        try {
            Log.d(Constant.TAG, "typing value: " + data.toString())
            val sender = data.getInt("sender")
            val nickname = data.getInt("nickname")
            val typing = data.getBoolean("typing")

            return Typing(sender, nickname, typing)
        } catch (e: JSONException) {
            e.printStackTrace()
        }

        return null
    }

}
