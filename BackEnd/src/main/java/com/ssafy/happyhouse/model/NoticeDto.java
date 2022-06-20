package com.ssafy.happyhouse.model;

public class NoticeDto {
	private int no;
	private String title;
	private String content;
	private String regtime;
	private String type; // 공지사항 종류

	public NoticeDto(int no, String title, String content, String regtime, String type) {
		super();
		this.no = no;
		this.title = title;
		this.content = content;
		this.regtime = regtime;
		this.type = type;
	}
	
	public NoticeDto(int no, String title, String content, String regtime) {
		super();
		this.no = no;
		this.title = title;
		this.content = content;
		this.regtime = regtime;
	}
	
	
	public NoticeDto() {
		super();
	}

	public NoticeDto(int no, String title, String content) {
		super();
		this.no = no;
		this.title = title;
		this.content = content;
	}

	public int getNo() {
		return no;
	}

	public void setNo(int no) {
		this.no = no;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getRegtime() {
		return regtime;
	}

	public void setRegtime(String regtime) {
		this.regtime = regtime;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	@Override
	public String toString() {
		return "NoticeDto [no=" + no + ", title=" + title + ", content=" + content + ", regtime=" + regtime + ", type="
				+ type + "]";
	}

}
