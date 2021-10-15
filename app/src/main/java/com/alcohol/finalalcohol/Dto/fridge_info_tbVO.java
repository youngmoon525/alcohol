package com.alcohol.finalalcohol.Dto;

import java.io.Serializable;

public class fridge_info_tbVO implements Serializable {
	                             
	  int   fridge_id              ;
	  int   fridge_mem_id          ;//not null
	  int   fridge_position        ;//not null
	  int   fridge_al_id           ;//not null

	public fridge_info_tbVO() {
	}

	public int getFridge_id() {
		return fridge_id;
	}
	public void setFridge_id(int fridge_id) {
		this.fridge_id = fridge_id;
	}
	public int getFridge_mem_id() {
		return fridge_mem_id;
	}
	public void setFridge_mem_id(int fridge_mem_id) {
		this.fridge_mem_id = fridge_mem_id;
	}
	public int getFridge_position() {
		return fridge_position;
	}
	public void setFridge_position(int fridge_position) {
		this.fridge_position = fridge_position;
	}
	public int getFridge_al_id() {
		return fridge_al_id;
	}
	public void setFridge_al_id(int fridge_al_id) {
		this.fridge_al_id = fridge_al_id;
	}
	
	
	
	 
	 
	 
	
}
