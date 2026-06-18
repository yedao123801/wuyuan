package com.office.model;
public class BxLog {
	private Long id;//ID
	private String bxNo;//报修编号
	private Long bgService;//办公设备
	private String serviceType;//设备类型
	private Long bgqy;//办公区域
	private Integer yxcd;//优先程度
	private String gzms;//故障描述
	private String gzImg;//故障图片
	private String gzVeo;//故障视频
	private String gzLocation;//故障位置
	private Integer bxStatus;//报修状态
	private String adminRemark;//管理员备注
	private String ysNote;//验收说明
	private String fixRemark;//维修员备注
	private Long fix;//维修员
	private Long userId;//用户ID
	private String createTime;//创建时间
	private Integer isDelete;//是否删除
	private String searchKeyWord;//关键词
	public Long getId(){
		return id;
	}
	public void setId(Long id){
		this.id = id;
	}
	public String getBxNo(){
		return bxNo;
	}
	public void setBxNo(String bxNo){
		this.bxNo = bxNo == null ? null : bxNo.trim();
	}
	public Long getBgService(){
		return bgService;
	}
	public void setBgService(Long bgService){
		this.bgService = bgService;
	}
	public String getServiceType(){
		return serviceType;
	}
	public void setServiceType(String serviceType){
		this.serviceType = serviceType == null ? null : serviceType.trim();
	}
	public Long getBgqy(){
		return bgqy;
	}
	public void setBgqy(Long bgqy){
		this.bgqy = bgqy;
	}
	public Integer getYxcd(){
		return yxcd;
	}
	public void setYxcd(Integer yxcd){
		this.yxcd = yxcd;
	}
	public String getGzms(){
		return gzms;
	}
	public void setGzms(String gzms){
		this.gzms = gzms == null ? null : gzms.trim();
	}
	public String getGzImg(){
		return gzImg;
	}
	public void setGzImg(String gzImg){
		this.gzImg = gzImg == null ? null : gzImg.trim();
	}
	public String getGzVeo(){
		return gzVeo;
	}
	public void setGzVeo(String gzVeo){
		this.gzVeo = gzVeo == null ? null : gzVeo.trim();
	}
	public String getGzLocation(){
		return gzLocation;
	}
	public void setGzLocation(String gzLocation){
		this.gzLocation = gzLocation == null ? null : gzLocation.trim();
	}
	public Integer getBxStatus(){
		return bxStatus;
	}
	public void setBxStatus(Integer bxStatus){
		this.bxStatus = bxStatus;
	}
	public String getAdminRemark(){
		return adminRemark;
	}
	public void setAdminRemark(String adminRemark){
		this.adminRemark = adminRemark == null ? null : adminRemark.trim();
	}
	public String getYsNote(){
		return ysNote;
	}
	public void setYsNote(String ysNote){
		this.ysNote = ysNote == null ? null : ysNote.trim();
	}
	public String getFixRemark(){
		return fixRemark;
	}
	public void setFixRemark(String fixRemark){
		this.fixRemark = fixRemark == null ? null : fixRemark.trim();
	}
	public Long getFix(){
		return fix;
	}
	public void setFix(Long fix){
		this.fix = fix;
	}
	public Long getUserId(){
		return userId;
	}
	public void setUserId(Long userId){
		this.userId = userId;
	}
	public String getCreateTime(){
		return createTime;
	}
	public void setCreateTime(String createTime){
		this.createTime = createTime == null ? null : createTime.trim();
	}
	public Integer getIsDelete(){
		return isDelete;
	}
	public void setIsDelete(Integer isDelete){
		this.isDelete = isDelete;
	}
	public String getSearchKeyWord(){
		return searchKeyWord;
	}
	public void setSearchKeyWord(String searchKeyWord){
		this.searchKeyWord = searchKeyWord == null ? null : searchKeyWord.trim();
	}
}
