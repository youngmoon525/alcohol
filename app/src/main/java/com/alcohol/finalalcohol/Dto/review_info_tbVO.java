package com.alcohol.finalalcohol.Dto;

import java.io.Serializable;

public class review_info_tbVO implements Serializable {
	
	int  review_id           ;//         number,                           --id
	String  review_title        ;//         varchar2(500) not null,                    --���� ����
	String  review_content      ;//         varchar2(4000) not null,                   -- ���� ����
	int  review_writer       ;//         number not null,                           -- ���� �ۼ��� id
	String  review_writedate    ;//         date default sysdate,             -- ���� �ۼ���
	int  review_readcnt      ;//         number default 0,                           -- ���� ��ȸ��
	String  review_star         ;//         varchar2(10),                     -- ���� ����
	String  review_filename     ;//         varchar2(500),                    -- ���信 ������ ������������̸�
	String  review_filepath     ;//         varchar2(500),                    -- ���信 ������ ����������ϰ��
	int  review_root         ;//         number,                           -- root : ���� ��ۿ� ��Ʈ
	int  review_step         ;//         number default 0,                 -- step : ���� ��ۿ� ����
	int  review_indent       ;//         number default 0,                 -- indent : ���� ��ۿ� �ε�Ʈ
	int  review_al_id        ;//    number,       
	public int getReview_id() {
		return review_id;
	}
	public void setReview_id(int review_id) {
		this.review_id = review_id;
	}
	public String getReview_title() {
		return review_title;
	}
	public void setReview_title(String review_title) {
		this.review_title = review_title;
	}
	public String getReview_content() {
		return review_content;
	}
	public void setReview_content(String review_content) {
		this.review_content = review_content;
	}
	public int getReview_writer() {
		return review_writer;
	}
	public void setReview_writer(int review_writer) {
		this.review_writer = review_writer;
	}
	public String getReview_writedate() {
		return review_writedate;
	}
	public void setReview_writedate(String review_writedate) {
		this.review_writedate = review_writedate;
	}
	public int getReview_readcnt() {
		return review_readcnt;
	}
	public void setReview_readcnt(int review_readcnt) {
		this.review_readcnt = review_readcnt;
	}
	public String getReview_star() {
		return review_star;
	}
	public void setReview_star(String review_star) {
		this.review_star = review_star;
	}
	public String getReview_filename() {
		return review_filename;
	}
	public void setReview_filename(String review_filename) {
		this.review_filename = review_filename;
	}
	public String getReview_filepath() {
		return review_filepath;
	}
	public void setReview_filepath(String review_filepath) {
		this.review_filepath = review_filepath;
	}
	public int getReview_root() {
		return review_root;
	}
	public void setReview_root(int review_root) {
		this.review_root = review_root;
	}
	public int getReview_step() {
		return review_step;
	}
	public void setReview_step(int review_step) {
		this.review_step = review_step;
	}
	public int getReview_indent() {
		return review_indent;
	}
	public void setReview_indent(int review_indent) {
		this.review_indent = review_indent;
	}
	public int getReview_al_id() {
		return review_al_id;
	}
	public void setReview_al_id(int review_al_id) {
		this.review_al_id = review_al_id;
	}
	
	
	
	
}
