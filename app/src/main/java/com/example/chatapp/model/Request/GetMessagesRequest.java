package com.example.chatapp.model.Request;

public class GetMessagesRequest {

    private int sender;
    private int nickname;

    public GetMessagesRequest(int sender, int nickname) {
        this.sender = sender;
        this.nickname = nickname;
    }


}
