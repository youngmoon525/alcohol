package com.alcohol.finalalcohol.DTO;

import java.io.Serializable;

public class LoginDTO implements Serializable {

    String mem_email            ;
    String mem_pw               ;

    public LoginDTO(String mem_email, String mem_pw) {
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
