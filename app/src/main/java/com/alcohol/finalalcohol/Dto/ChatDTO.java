package com.alcohol.finalalcohol.Dto;
public class ChatDTO {
    private String nickname;
    private String msg;
    private String date;
    private String selected;

    public ChatDTO() {
    }

    public ChatDTO(String nickname, String msg, String date, String selected) {
        this.nickname = nickname;
        this.msg = msg;
        this.date = date;
        this.selected = selected;
    }

    public ChatDTO(String nickname, String msg, String date) {
        this.nickname = nickname;
        this.msg = msg;
        this.date = date;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getSelected() {
        return selected;
    }

    public void setSelected(String selected) {
        this.selected = selected;
    }
}
