package com.office.model;
public class Bgqy {
	private Long id;//ID
	private String qyName;//区域名
	private Integer isDelete;//是否删除
	public Long getId(){
		return id;
	}
	public void setId(Long id){
		this.id = id;
	}
	public String getQyName(){
		return qyName;
	}
	public void setQyName(String qyName){
		this.qyName = qyName == null ? null : qyName.trim();
	}
	public Integer getIsDelete(){
		return isDelete;
	}
	public void setIsDelete(Integer isDelete){
		this.isDelete = isDelete;
	}
}
