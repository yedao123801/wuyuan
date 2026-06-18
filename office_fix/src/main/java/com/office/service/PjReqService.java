package com.office.service;
import java.util.Map;
import com.office.controller.LoginModel;
import com.office.model.*;
import java.io.InputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;
public interface PjReqService{
	/**
	获取列表的id集合
	*/
	public List<Long> getIds(PjReq pjReq,List<Map<String,Object>> queryParams,LoginModel login);
	/**
	设置登录人可见的数据权限
	*/
	public void setQueryPerm(PjReq model,List<Map<String,Object>> queryParams,LoginModel login);
	/**
	查询封装好的配件申请列表
	*/
	public List<Map<String,Object>> getDataList(List<PjReq> tl,LoginModel login);
	/**
	查询总数量和总页数
	*/
	public Map<String,Long> getCount(PjReq queryModel,List<Map<String,Object>> queryParams,LoginModel login,Integer page,Integer pageSize);
	/**
	本表作为其他表的下拉列表，只显示id和名称
	*/
	public List<Map<String,Object>> getSelectList(PjReq pjReq,List<Map<String,Object>> queryParams,LoginModel login);
	/**
	根据ID查询该数据的简易信息
	*/
	public Map<String,Object> getIdNameShowById(Long id);
	/**
	分页查询配件申请数据列表
	*/
	public List<PjReq> getPjReqList(PjReq queryModel,List<Map<String,Object>> queryParams,LoginModel login,Integer page,Integer pageSize,String rangeType) ;
	/**
	封装配件申请为前台展示的数据形式
	*/
	public Map<String,Object> getPjReqModel(PjReq model,LoginModel login);
	/**
	* 删除数据
	*/
	public void delete(Long id);
	/**
	新增
	*/
	public String add(PjReq model,LoginModel login);
	/**
	接受申请
	*/
	public String jssq(PjReq model,LoginModel login);
	/**
	拒绝申请
	*/
	public String jjsq(PjReq model,LoginModel login);
	/**
	修改
	*/
	public String update(PjReq model,LoginModel login);
}
