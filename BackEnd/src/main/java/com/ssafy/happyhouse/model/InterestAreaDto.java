package com.ssafy.happyhouse.model;

public class InterestAreaDto {
	private String id;
	private String dongCode;
	private String juso;
	private int count;
	
	public String getJuso() {
		return juso;
	}
	public void setJuso(String juso) {
		this.juso = juso;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getDongCode() {
		return dongCode;
	}
	public void setDongCode(String dongCode) {
		this.dongCode = dongCode;
	}

	public int getCount() {
		return count;
	}
	public void setCount(int count) {
		this.count = count;
	}

	@Override
	public String toString() {
		return "InterestAreaDto [id=" + id + ", dongCode=" + dongCode + ", juso=" + juso + "]";
	}
	
}
