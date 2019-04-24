package com.example.mvvmtest.model.Response;

import com.example.mvvmtest.model.Message;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class OpenTalkResponse extends BaseResponse {

    @SerializedName("messages")
    List<Message> messages;

    public List<Message> getMessagess() {
        return messages;
    }


}
