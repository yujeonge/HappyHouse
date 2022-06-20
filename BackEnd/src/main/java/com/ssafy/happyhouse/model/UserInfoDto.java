package com.ssafy.happyhouse.model;

public class UserInfoDto {
	private String id;
	private String password;
	private String name;
	private String email;
	private String tel;
	private String ans;
	private String que;

	public UserInfoDto(String id, String password, String name, String email, String tel, String ans, String que) {
		super();
		this.id = id;
		this.password = password;
		this.name = name;
		this.email = email;
		this.tel = tel;
		this.ans = ans;
		this.que = que;
	}

	public UserInfoDto() {
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getPassword() {
		return password;
	}

	public String getAns() {
		return ans;
	}

	public void setAns(String ans) {
		this.ans = ans;
	}

	public String getQue() {
		return que;
	}

	public void setQue(String que) {
		this.que = que;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getTel() {
		return tel;
	}

	public void setTel(String tel) {
		this.tel = tel;
	}

	@Override
	public String toString() {
		return "UserInfoDto [id=" + id + ", password=" + password + ", name=" + name + ", email=" + email + ", tel="
				+ tel + ", ans=" + ans + ", que=" + que + "]";
	}

}
