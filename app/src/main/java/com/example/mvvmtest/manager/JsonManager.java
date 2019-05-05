package com.example.mvvmtest.manager;

import com.example.mvvmtest.model.Message;
import com.example.mvvmtest.util.Constant;

import org.json.JSONException;
import org.json.JSONObject;

public class JsonManager {

    public static JSONObject createEmitSendMessage(Message message) {

        JSONObject obj = new JSONObject();
        try {

            JSONObject params = new JSONObject();
            params.put("sender", message.getSender());
            params.put("nickname", message.getNickname());
            params.put("body", message.getBody());

            obj.put(Constant.SocketKey.FN_KEY, Constant.SocketEvent.NEW_MESSAGE);
            obj.put("params", params);

            return obj;
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return obj;

    }

    public static Message processNewMessage(JSONObject data){
        try {
            int sender = data.getInt("sender");
            int nickname = data.getInt("nickname");
            String body = data.getString("body");

            return new Message(sender,nickname,body);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return null;
    }


}
