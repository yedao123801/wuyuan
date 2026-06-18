package com.office.model;
public class PjReq {
	private Long id;//ID
	private String reqNo;//申请编号
	private String pjName;//配件名
	private Integer xyNum;//需要数量
	private String reqNote;//申请说明
	private Integer reqStatus;//申请状态
	private String checkRemark;//审核备注
	private String createTime;//创建时间
	private Long fix;//维修员
	public Long getId(){
		return id;
	}
	public void setId(Long id){
		this.id = id;
	}
	public String getReqNo(){
		return reqNo;
	}
	public void setReqNo(String reqNo){
		this.reqNo = reqNo == null ? null : reqNo.trim();
	}
	public String getPjName(){
		return pjName;
	}
	public void setPjName(String pjName){
		this.pjName = pjName == null ? null : pjName.trim();
	}
	public Integer getXyNum(){
		return xyNum;
	}
	public void setXyNum(Integer xyNum){
		this.xyNum = xyNum;
	}
	public String getReqNote(){
		return reqNote;
	}
	public void setReqNote(String reqNote){
		this.reqNote = reqNote == null ? null : reqNote.trim();
	}
	public Integer getReqStatus(){
		return reqStatus;
	}
	public void setReqStatus(Integer reqStatus){
		this.reqStatus = reqStatus;
	}
	public String getCheckRemark(){
		return checkRemark;
	}
	public void setCheckRemark(String checkRemark){
		this.checkRemark = checkRemark == null ? null : checkRemark.trim();
	}
	public String getCreateTime(){
		return createTime;
	}
	public void setCreateTime(String createTime){
		this.createTime = createTime == null ? null : createTime.trim();
	}
	public Long getFix(){
		return fix;
	}
	public void setFix(Long fix){
		this.fix = fix;
	}
}
