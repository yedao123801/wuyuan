package com.office.model;
public class Admin {
	private Long id;//ID
	private String loginName;//登录名
	private String password;//密码
	public Long getId(){
		return id;
	}
	public void setId(Long id){
		this.id = id;
	}
	public String getLoginName(){
		return loginName;
	}
	public void setLoginName(String loginName){
		this.loginName = loginName == null ? null : loginName.trim();
	}
	public String getPassword(){
		return password;
	}
	public void setPassword(String password){
		this.password = password == null ? null : password.trim();
	}
}
