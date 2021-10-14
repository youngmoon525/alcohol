package com.alcohol.finalalcohol.Dto;

import java.io.Serializable;

public class todays_tbVO implements Serializable {
	                         
	  int todays_id        ;//            number,                           --id
	  String todays_taste     ;//          varchar2(300) not null,                   --���� �Һз� : ex) flavor
	  String todays_q         ;//       varchar2(4000) not null,                     --�׽�Ʈ���� ����
	  String todays_a         ;//       varchar2(4000) not null,                    --����A����
	  int todays_a_result  ;//          number,                                 --����A���� ���ý� ���� ��
	  String todays_b         ;//     varchar2(4000) not null,                    --����B����
	  int todays_b_result  ;//            number,                             --����B���� ���ý� ���� ��
	  String todays_ref       ;//     varchar2(4000), 
	  
	  
	  
	public int getTodays_id() {
		return todays_id;
	}
	public void setTodays_id(int todays_id) {
		this.todays_id = todays_id;
	}
	public String getTodays_taste() {
		return todays_taste;
	}
	public void setTodays_taste(String todays_taste) {
		this.todays_taste = todays_taste;
	}
	public String getTodays_q() {
		return todays_q;
	}
	public void setTodays_q(String todays_q) {
		this.todays_q = todays_q;
	}
	public String getTodays_a() {
		return todays_a;
	}
	public void setTodays_a(String todays_a) {
		this.todays_a = todays_a;
	}
	public int getTodays_a_result() {
		return todays_a_result;
	}
	public void setTodays_a_result(int todays_a_result) {
		this.todays_a_result = todays_a_result;
	}
	public String getTodays_b() {
		return todays_b;
	}
	public void setTodays_b(String todays_b) {
		this.todays_b = todays_b;
	}
	public int getTodays_b_result() {
		return todays_b_result;
	}
	public void setTodays_b_result(int todays_b_result) {
		this.todays_b_result = todays_b_result;
	}
	public String getTodays_ref() {
		return todays_ref;
	}
	public void setTodays_ref(String todays_ref) {
		this.todays_ref = todays_ref;
	}
	  
	  
	  
	
}
