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

            obj.put("fn", Constant.SocketFunctions.MESSAGE_SENDED);
            obj.put("params", params);

            return obj;
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return obj;

    }


}
