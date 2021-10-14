package com.alcohol.finalalcohol.Dto;

import java.io.Serializable;

public class subs_info_tbVO implements Serializable {

	 int subs_id              ;//       number,                       --���� ���̺�pkŰ/ateam_subscribe���̺��� pkŰ
	 int subs_mem_id          ;//    number not null,              --�����ϴ� ����� �ش��ϴ� ȸ�����̺�(member)�� member_id
	 String subs_address         ;//       varchar2(500),                --���� ��۹��� �ּ�(ȸ�����̺��� �ּҿ� ����)
	 String subs_order_dt        ;//       date,                         --���� ��û�� ����/��¥��
	 int subs_deliverydt      ;//     number default 1,              --��� �ް���� ��¥(�ſ��� '��'��) : ex) �ſ� '25'�� �� ��� : 25 / ������ ����Ÿ��
	 int subs_months          ;//        number default 1,             --������û ���� ��//������// ex) 12���� ��û�� ��� : 12
	 int subs_received_cnt    ;//           number default 0,          --��ǰ ��Ű�� ��۹��� Ƚ�� ex) 1����° ���� ��� : 1
	 int subs_remainder_cnt   ;//           number default 1,          --��ǰ ��Ű�� ��� �޾ƾ��ϴ� ���� Ƚ�� ex) 11���� ���� ��� : 11
	 int subs_price           ;//       number,                       --�����������/����Ÿ���� ������ ex) 50000�̸� 50,000��
	 String subs_refund          ;//       varchar2(10) default 'F',     --ȯ�� ����--����Ŭ��booleanŸ���̾�� VARCHAR2Ÿ���� T/F�� ��ü
	 int subs_refund_price    ;//        number default 0,            -
	public int getSubs_id() {
		return subs_id;
	}
	public void setSubs_id(int subs_id) {
		this.subs_id = subs_id;
	}
	public int getSubs_mem_id() {
		return subs_mem_id;
	}
	public void setSubs_mem_id(int subs_mem_id) {
		this.subs_mem_id = subs_mem_id;
	}
	public String getSubs_address() {
		return subs_address;
	}
	public void setSubs_address(String subs_address) {
		this.subs_address = subs_address;
	}
	public String getSubs_order_dt() {
		return subs_order_dt;
	}
	public void setSubs_order_dt(String subs_order_dt) {
		this.subs_order_dt = subs_order_dt;
	}
	public int getSubs_deliverydt() {
		return subs_deliverydt;
	}
	public void setSubs_deliverydt(int subs_deliverydt) {
		this.subs_deliverydt = subs_deliverydt;
	}
	public int getSubs_months() {
		return subs_months;
	}
	public void setSubs_months(int subs_months) {
		this.subs_months = subs_months;
	}
	public int getSubs_received_cnt() {
		return subs_received_cnt;
	}
	public void setSubs_received_cnt(int subs_received_cnt) {
		this.subs_received_cnt = subs_received_cnt;
	}
	public int getSubs_remainder_cnt() {
		return subs_remainder_cnt;
	}
	public void setSubs_remainder_cnt(int subs_remainder_cnt) {
		this.subs_remainder_cnt = subs_remainder_cnt;
	}
	public int getSubs_price() {
		return subs_price;
	}
	public void setSubs_price(int subs_price) {
		this.subs_price = subs_price;
	}
	public String getSubs_refund() {
		return subs_refund;
	}
	public void setSubs_refund(String subs_refund) {
		this.subs_refund = subs_refund;
	}
	public int getSubs_refund_price() {
		return subs_refund_price;
	}
	public void setSubs_refund_price(int subs_refund_price) {
		this.subs_refund_price = subs_refund_price;
	}
	
	
	
	
	
}
