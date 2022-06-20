package com.ssafy.happyhouse.model;

public class AdminDto {
	private String regdate;
	private String cnt;

	public AdminDto(String regdate, String cnt) {
		super();
		this.regdate = regdate;
		this.cnt = cnt;
	}

	public String getRegdate() {
		return regdate;
	}

	public void setRegdate(String regdate) {
		this.regdate = regdate;
	}

	public String getCnt() {
		return cnt;
	}

	public void setCnt(String cnt) {
		this.cnt = cnt;
	}

	@Override
	public String toString() {
		return "AdminDto [regdate=" + regdate + ", cnt=" + cnt + "]";
	}

}
