package com.alcohol.finalalcohol.DTO;

public class My_SubsDetail_DTO {
    String month, al_List, price_state;

    public My_SubsDetail_DTO(String month, String al_List, String price_state) {
        this.month = month;
        this.al_List = al_List;
        this.price_state = price_state;
    }

    public String getMonth() {
        return month;
    }

    public void setMonth(String month) {
        this.month = month;
    }

    public String getAl_List() {
        return al_List;
    }

    public void setAl_List(String al_List) {
        this.al_List = al_List;
    }

    public String getPrice_state() {
        return price_state;
    }

    public void setPrice_state(String price_state) {
        this.price_state = price_state;
    }
}
