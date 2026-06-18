package com.office.model;
public class Depart {
	private Long id;//ID
	private String departName;//部门名
	private Integer isDelete;//是否删除
	public Long getId(){
		return id;
	}
	public void setId(Long id){
		this.id = id;
	}
	public String getDepartName(){
		return departName;
	}
	public void setDepartName(String departName){
		this.departName = departName == null ? null : departName.trim();
	}
	public Integer getIsDelete(){
		return isDelete;
	}
	public void setIsDelete(Integer isDelete){
		this.isDelete = isDelete;
	}
}
