package com.office.model;
public class BgService {
	private Long id;//ID
	private String serviceNo;//设备编号
	private String serviceType;//设备类型
	private String serviceName;//设备名
	private String serviceXh;//设备型号
	private String cgDate;//采购日期
	private Long bgqy;//办公区域
	private Integer isDelete;//是否删除
	private String searchKeyWord;//关键词
	public Long getId(){
		return id;
	}
	public void setId(Long id){
		this.id = id;
	}
	public String getServiceNo(){
		return serviceNo;
	}
	public void setServiceNo(String serviceNo){
		this.serviceNo = serviceNo == null ? null : serviceNo.trim();
	}
	public String getServiceType(){
		return serviceType;
	}
	public void setServiceType(String serviceType){
		this.serviceType = serviceType == null ? null : serviceType.trim();
	}
	public String getServiceName(){
		return serviceName;
	}
	public void setServiceName(String serviceName){
		this.serviceName = serviceName == null ? null : serviceName.trim();
	}
	public String getServiceXh(){
		return serviceXh;
	}
	public void setServiceXh(String serviceXh){
		this.serviceXh = serviceXh == null ? null : serviceXh.trim();
	}
	public String getCgDate(){
		return cgDate;
	}
	public void setCgDate(String cgDate){
		this.cgDate = cgDate == null ? null : cgDate.trim();
	}
	public Long getBgqy(){
		return bgqy;
	}
	public void setBgqy(Long bgqy){
		this.bgqy = bgqy;
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
