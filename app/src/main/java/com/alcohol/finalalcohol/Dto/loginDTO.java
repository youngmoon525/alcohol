package com.alcohol.finalalcohol.Dto;

import java.io.Serializable;

public class loginDTO implements Serializable {

    String mem_email            ;
    String mem_pw               ;

    public loginDTO(String mem_email, String mem_pw) {
        this.mem_email = mem_email;
        this.mem_pw = mem_pw;
    }

    public String getMem_email() {
        return mem_email;
    }

    public void setMem_email(String mem_email) {
        this.mem_email = mem_email;
    }

    public String getMem_pw() {
        return mem_pw;
    }

    public void setMem_pw(String mem_pw) {
        this.mem_pw = mem_pw;
    }
}
