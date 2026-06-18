package com.office.model;
public class Zsk {
	private Long id;//ID
	private String title;//标题
	private String subtitle;//副标题
	private String zsContent;//知识内容
	private String fbTime;//发布时间
	private String searchKeyWord;//关键词
	public Long getId(){
		return id;
	}
	public void setId(Long id){
		this.id = id;
	}
	public String getTitle(){
		return title;
	}
	public void setTitle(String title){
		this.title = title == null ? null : title.trim();
	}
	public String getSubtitle(){
		return subtitle;
	}
	public void setSubtitle(String subtitle){
		this.subtitle = subtitle == null ? null : subtitle.trim();
	}
	public String getZsContent(){
		return zsContent;
	}
	public void setZsContent(String zsContent){
		this.zsContent = zsContent == null ? null : zsContent.trim();
	}
	public String getFbTime(){
		return fbTime;
	}
	public void setFbTime(String fbTime){
		this.fbTime = fbTime == null ? null : fbTime.trim();
	}
	public String getSearchKeyWord(){
		return searchKeyWord;
	}
	public void setSearchKeyWord(String searchKeyWord){
		this.searchKeyWord = searchKeyWord == null ? null : searchKeyWord.trim();
	}
}
