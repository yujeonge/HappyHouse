package com.ssafy.happyhouse.model;

public class AnsDto {
	int articleno;
	String content;
	String regtime;
	public int getArticleno() {
		return articleno;
	}
	public void setArticleno(int articleno) {
		this.articleno = articleno;
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
	@Override
	public String toString() {
		return "AnsDto [articleno=" + articleno + ", content=" + content + ", regtime=" + regtime + "]";
	}
	
	
}
