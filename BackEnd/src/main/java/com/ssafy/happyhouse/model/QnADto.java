package com.ssafy.happyhouse.model;

//list호출용
public class QnADto {
	int articleno;
	String username;
	String subject;
	String content;
	String regtime;
	String reply;
	
	public String getReply() {
		return reply;
	}
	public void setReply(String reply) {
		this.reply = reply;
	}
	public int getArticleno() {
		return articleno;
	}
	public void setArticleno(int articleno) {
		this.articleno = articleno;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getSubject() {
		return subject;
	}
	public void setSubject(String subject) {
		this.subject = subject;
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
		return "QnADto [articleno=" + articleno + ", username=" + username + ", subject=" + subject + ", content="
				+ content + ", regtime=" + regtime + ", reply=" + reply + "]";
	}
	
	
}
