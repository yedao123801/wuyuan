package com.office.model;
public class EvalLog {
	private Long id;//ID
	private Long bxid;//报修ID
	private Long fix;//维修员
	private Integer evalStar;//评价星级
	private String evalContent;//评价内容
	private Long userId;//用户ID
	private String evalTime;//评价时间
	public Long getId(){
		return id;
	}
	public void setId(Long id){
		this.id = id;
	}
	public Long getBxid(){
		return bxid;
	}
	public void setBxid(Long bxid){
		this.bxid = bxid;
	}
	public Long getFix(){
		return fix;
	}
	public void setFix(Long fix){
		this.fix = fix;
	}
	public Integer getEvalStar(){
		return evalStar;
	}
	public void setEvalStar(Integer evalStar){
		this.evalStar = evalStar;
	}
	public String getEvalContent(){
		return evalContent;
	}
	public void setEvalContent(String evalContent){
		this.evalContent = evalContent == null ? null : evalContent.trim();
	}
	public Long getUserId(){
		return userId;
	}
	public void setUserId(Long userId){
		this.userId = userId;
	}
	public String getEvalTime(){
		return evalTime;
	}
	public void setEvalTime(String evalTime){
		this.evalTime = evalTime == null ? null : evalTime.trim();
	}
}
