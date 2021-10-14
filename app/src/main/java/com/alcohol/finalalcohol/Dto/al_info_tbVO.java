package com.alcohol.finalalcohol.Dto;

import java.io.Serializable;

public class al_info_tbVO implements Serializable {
	 int al_id               ;   
	 String al_name             ;
	 String al_manufacturer     ;
	 String al_material         ;
	 String al_vol              ;
	 int al_body             ;   
	 int al_alcohol_type     ;   
	 int al_flavor           ;   
	 int al_smell            ;   
	 int al_alcohol_bv       ;   
	 String al_real_alcohol_bv  ;
	 String al_imgname          ;
	 String al_imgpath          ;
	 String al_mini_have        ;  // varchar2(5) default 'F'
	 String al_mini_vol         ; 
	 String al_mini_imgname     ; 
	 String al_mini_imgpath     ;

	public al_info_tbVO() {
	}


	public int getAl_id() {
		return al_id;
	}
	public void setAl_id(int al_id) {
		this.al_id = al_id;
	}
	public String getAl_name() {
		return al_name;
	}
	public void setAl_name(String al_name) {
		this.al_name = al_name;
	}
	public String getAl_manufacturer() {
		return al_manufacturer;
	}
	public void setAl_manufacturer(String al_manufacturer) {
		this.al_manufacturer = al_manufacturer;
	}
	public String getAl_material() {
		return al_material;
	}
	public void setAl_material(String al_material) {
		this.al_material = al_material;
	}
	public String getAl_vol() {
		return al_vol;
	}
	public void setAl_vol(String al_vol) {
		this.al_vol = al_vol;
	}
	public int getAl_body() {
		return al_body;
	}
	public void setAl_body(int al_body) {
		this.al_body = al_body;
	}
	public int getAl_alcohol_type() {
		return al_alcohol_type;
	}
	public void setAl_alcohol_type(int al_alcohol_type) {
		this.al_alcohol_type = al_alcohol_type;
	}
	public int getAl_flavor() {
		return al_flavor;
	}
	public void setAl_flavor(int al_flavor) {
		this.al_flavor = al_flavor;
	}
	public int getAl_smell() {
		return al_smell;
	}
	public void setAl_smell(int al_smell) {
		this.al_smell = al_smell;
	}
	public int getAl_alcohol_bv() {
		return al_alcohol_bv;
	}
	public void setAl_alcohol_bv(int al_alcohol_bv) {
		this.al_alcohol_bv = al_alcohol_bv;
	}
	public String getAl_real_alcohol_bv() {
		return al_real_alcohol_bv;
	}
	public void setAl_real_alcohol_bv(String al_real_alcohol_bv) {
		this.al_real_alcohol_bv = al_real_alcohol_bv;
	}
	public String getAl_imgname() {
		return al_imgname;
	}
	public void setAl_imgname(String al_imgname) {
		this.al_imgname = al_imgname;
	}
	public String getAl_imgpath() {
		return al_imgpath;
	}
	public void setAl_imgpath(String al_imgpath) {
		this.al_imgpath = al_imgpath;
	}
	public String getAl_mini_have() {
		return al_mini_have;
	}
	public void setAl_mini_have(String al_mini_have) {
		this.al_mini_have = al_mini_have;
	}
	public String getAl_mini_vol() {
		return al_mini_vol;
	}
	public void setAl_mini_vol(String al_mini_vol) {
		this.al_mini_vol = al_mini_vol;
	}
	public String getAl_mini_imgname() {
		return al_mini_imgname;
	}
	public void setAl_mini_imgname(String al_mini_imgname) {
		this.al_mini_imgname = al_mini_imgname;
	}
	public String getAl_mini_imgpath() {
		return al_mini_imgpath;
	}
	public void setAl_mini_imgpath(String al_mini_imgpath) {
		this.al_mini_imgpath = al_mini_imgpath;
	} 
	                              
	 
}
