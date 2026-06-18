package com.office.model;
public class FixGc {
	private Long id;//ID
	private String fixBz;//维修步骤
	private String ghpj;//更换配件
	private Double xhgs;//消耗工时
	private Long fix;//维修员
	private Long bxid;//报修ID
	private String createTime;//创建时间
	public Long getId(){
		return id;
	}
	public void setId(Long id){
		this.id = id;
	}
	public String getFixBz(){
		return fixBz;
	}
	public void setFixBz(String fixBz){
		this.fixBz = fixBz == null ? null : fixBz.trim();
	}
	public String getGhpj(){
		return ghpj;
	}
	public void setGhpj(String ghpj){
		this.ghpj = ghpj == null ? null : ghpj.trim();
	}
	public Double getXhgs(){
		return xhgs;
	}
	public void setXhgs(Double xhgs){
		this.xhgs = xhgs;
	}
	public Long getFix(){
		return fix;
	}
	public void setFix(Long fix){
		this.fix = fix;
	}
	public Long getBxid(){
		return bxid;
	}
	public void setBxid(Long bxid){
		this.bxid = bxid;
	}
	public String getCreateTime(){
		return createTime;
	}
	public void setCreateTime(String createTime){
		this.createTime = createTime == null ? null : createTime.trim();
	}
}
