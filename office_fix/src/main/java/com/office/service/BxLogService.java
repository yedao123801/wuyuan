package com.office.service;
import java.util.Map;
import com.office.controller.LoginModel;
import com.office.model.*;
import java.io.InputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;
public interface BxLogService{
	/**
	获取列表的id集合
	*/
	public List<Long> getIds(BxLog bxLog,List<Map<String,Object>> queryParams,LoginModel login);
	/**
	设置登录人可见的数据权限
	*/
	public void setQueryPerm(BxLog model,List<Map<String,Object>> queryParams,LoginModel login);
	/**
	查询封装好的报修记录列表
	*/
	public List<Map<String,Object>> getDataList(List<BxLog> tl,LoginModel login);
	/**
	查询总数量和总页数
	*/
	public Map<String,Long> getCount(BxLog queryModel,List<Map<String,Object>> queryParams,LoginModel login,Integer page,Integer pageSize);
	/**
	本表作为其他表的下拉列表，只显示id和名称
	*/
	public List<Map<String,Object>> getSelectList(BxLog bxLog,List<Map<String,Object>> queryParams,LoginModel login);
	/**
	根据ID查询该数据的简易信息
	*/
	public Map<String,Object> getIdNameShowById(Long id);
	/**
	分页查询报修记录数据列表
	*/
	public List<BxLog> getBxLogList(BxLog queryModel,List<Map<String,Object>> queryParams,LoginModel login,Integer page,Integer pageSize,String rangeType) ;
	/**
	封装报修记录为前台展示的数据形式
	*/
	public Map<String,Object> getBxLogModel(BxLog model,LoginModel login);
	/**
	* 删除数据
	*/
	public void delete(Long id);
	/**
	派单
	*/
	public String pd(BxLog model,LoginModel login);
	/**
	驳回
	*/
	public String bh(BxLog model,LoginModel login);
	/**
	完成
	*/
	public String wc(BxLog model,LoginModel login);
	/**
	验收
	*/
	public String ys(BxLog model,LoginModel login);
}
