package com.office.model;
public class User {
	private Long id;//ID
	private String loginName;//登录名
	private String password;//密码
	private String name;//姓名
	private String celphone;//联系电话
	private String headImg;//头像
	private Long depart;//部门
	private String zcTime;//注册时间
	private Integer isDelete;//是否删除
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
	public String getName(){
		return name;
	}
	public void setName(String name){
		this.name = name == null ? null : name.trim();
	}
	public String getCelphone(){
		return celphone;
	}
	public void setCelphone(String celphone){
		this.celphone = celphone == null ? null : celphone.trim();
	}
	public String getHeadImg(){
		return headImg;
	}
	public void setHeadImg(String headImg){
		this.headImg = headImg == null ? null : headImg.trim();
	}
	public Long getDepart(){
		return depart;
	}
	public void setDepart(Long depart){
		this.depart = depart;
	}
	public String getZcTime(){
		return zcTime;
	}
	public void setZcTime(String zcTime){
		this.zcTime = zcTime == null ? null : zcTime.trim();
	}
	public Integer getIsDelete(){
		return isDelete;
	}
	public void setIsDelete(Integer isDelete){
		this.isDelete = isDelete;
	}
}
