package com.gamsa.webapp.entity;

import java.util.Date;

public class Member {
	
	private String id;
	private String pwd;
	private int role;
	private Date regDate;
	
	public Member() {
		// TODO Auto-generated constructor stub
	}
	
	
	public Member(String id, String pwd, int role, Date regDate) {
		super();
		this.id = id;
		this.pwd = pwd;
		this.role = role;
		this.regDate = regDate;
	}


	public  String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getPwd() {
		return pwd;
	}

	public void setPwd(String pwd) {
		this.pwd = pwd;
	}

	public int getRole() {
		return role;
	}

	public void setRole(int role) {
		this.role = role;
	}

	public Date getRegDate() {
		return regDate;
	}

	public void setRegDate(Date regDate) {
		this.regDate = regDate;
	}
	
	
	
}
