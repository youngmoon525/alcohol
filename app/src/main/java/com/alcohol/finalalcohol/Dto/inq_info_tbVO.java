package com.alcohol.finalalcohol.Dto;

import java.io.Serializable;

public class inq_info_tbVO implements Serializable {
	
	
	 int inq_id          ;//         number,                           --id
	 String inq_title       ;//         varchar2(500) not null,                    --���Ǳ� ����
	 String inq_content     ;//         varchar2(4000) not null,                   --���Ǳ� ����
	 int inq_writer      ;//         number not null,                    --���Ǳ� �ۼ��� id
	 String inq_writedate   ;//         date default sysdate,             --���Ǳ� �ۼ���
	 String inq_filename    ;//         varchar2(500),                    --���Ǳۿ� ������ ������������̸�
	 String inq_filepath    ;//         varchar2(500),                    --���Ǳۿ� ������ ����������ϰ��
	 int inq_root        ;//         number,                           --root : ���Ǳ� ��ۿ� ��Ʈ
	 int inq_step        ;//         number default 0,                 --step : ���Ǳ� ��ۿ� ����
	 int inq_indent      ;//         number default 0, 
	 
	 
	 
	public int getInq_id() {
		return inq_id;
	}
	public void setInq_id(int inq_id) {
		this.inq_id = inq_id;
	}
	public String getInq_title() {
		return inq_title;
	}
	public void setInq_title(String inq_title) {
		this.inq_title = inq_title;
	}
	public String getInq_content() {
		return inq_content;
	}
	public void setInq_content(String inq_content) {
		this.inq_content = inq_content;
	}
	public int getInq_writer() {
		return inq_writer;
	}
	public void setInq_writer(int inq_writer) {
		this.inq_writer = inq_writer;
	}
	public String getInq_writedate() {
		return inq_writedate;
	}
	public void setInq_writedate(String inq_writedate) {
		this.inq_writedate = inq_writedate;
	}
	public String getInq_filename() {
		return inq_filename;
	}
	public void setInq_filename(String inq_filename) {
		this.inq_filename = inq_filename;
	}
	public String getInq_filepath() {
		return inq_filepath;
	}
	public void setInq_filepath(String inq_filepath) {
		this.inq_filepath = inq_filepath;
	}
	public int getInq_root() {
		return inq_root;
	}
	public void setInq_root(int inq_root) {
		this.inq_root = inq_root;
	}
	public int getInq_step() {
		return inq_step;
	}
	public void setInq_step(int inq_step) {
		this.inq_step = inq_step;
	}
	public int getInq_indent() {
		return inq_indent;
	}
	public void setInq_indent(int inq_indent) {
		this.inq_indent = inq_indent;
	}
	
	 
	 
}
