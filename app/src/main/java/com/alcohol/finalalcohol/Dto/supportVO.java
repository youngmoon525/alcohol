package com.alcohol.finalalcohol.Dto;

public class supportVO {
    //내용
    private String content;
    //이름
    private String name;
    //뷰타입(왼쪽, 가운데, 오른쪽)
    private int viewType;

    public supportVO(String content, String name, int viewType) {
        this.content = content;
        this.name = name;
        this.viewType = viewType;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getViewType() {
        return viewType;
    }

    public void setViewType(int viewType) {
        this.viewType = viewType;
    }
}
