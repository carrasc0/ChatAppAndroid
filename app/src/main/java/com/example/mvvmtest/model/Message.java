package com.example.mvvmtest.model;

import com.google.gson.annotations.SerializedName;

public class Message {

    @SerializedName("id")
    private int id_message;

    @SerializedName("created_at")
    private String created_at;

    @SerializedName("sender")
    private int sender;

    @SerializedName("nickname")
    private int nickname;

    @SerializedName("body")
    private String body;

    @SerializedName("readed")
    private int readed;

    public Message() {
    }

    public Message(int sender, int nickname, String body) {
        this.sender = sender;
        this.nickname = nickname;
        this.body = body;
    }

    public boolean isSender(int id) {
        return 1 == id;
    }

    public int getId_message() {
        return id_message;
    }

    public void setId_message(int id_message) {
        this.id_message = id_message;
    }

    public String getCreated_at() {
        return created_at;
    }

    public void setCreated_at(String created_at) {
        this.created_at = created_at;
    }

    public int getSender() {
        return sender;
    }

    public void setSender(int sender) {
        this.sender = sender;
    }

    public int getNickname() {
        return nickname;
    }

    public void setNickname(int nickname) {
        this.nickname = nickname;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public int getReaded() {
        return readed;
    }

    public void setReaded(int readed) {
        this.readed = readed;
    }


}
