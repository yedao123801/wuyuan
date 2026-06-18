package com.office.service.impl;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.office.dao.*;
import com.office.model.*;
import com.office.service.*;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import com.office.util.*;
import org.springframework.ui.ModelMap;
import java.util.*;
import com.office.service.*;
import com.office.controller.LoginModel;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.text.ParseException;
import java.io.IOException;
@Service
public class PjReqServiceImpl implements PjReqService{
	SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	SimpleDateFormat sdf2 = new SimpleDateFormat("yyyy-MM-dd");
	SimpleDateFormat sdf3 = new SimpleDateFormat("yyyyMMddHHmmss");
	SimpleDateFormat sdf4 = new SimpleDateFormat("yyyy-MM");
	@Autowired
	PjReqMapper pjReqMapper;
	@Autowired
	FixMapper fixMapper;
	/**
	新增
	*/
	@Override
	public String add(PjReq model,LoginModel login){
		if(model.getPjName()==null||model.getPjName().equals("")){
			return "配件名为必填属性";
		}
		if(model.getXyNum()==null){
			return "需要数量为必填属性";
		}
		if(model.getReqNote()==null||model.getReqNote().equals("")){
			return "申请说明为必填属性";
		}
		model.setReqNo(sdf3.format(new Date())+RandomCodeUtils.getRandomCode());//随机码(当前时间+6位随机码)
		model.setReqStatus(1);//默认已申请,
		model.setCreateTime(sdf1.format(new Date()));//当前时间格式
		model.setFix(login.getId());//登录id
		pjReqMapper.insertSelective(model);//插入数据库
		return "";
	}
	/**
	接受申请
	*/
	@Override
	public String jssq(PjReq model,LoginModel login){
		//根据主键获取配件申请
		PjReq preModel = pjReqMapper.selectByPrimaryKey(model.getId());
		if(model.getCheckRemark()==null||model.getCheckRemark().equals("")){
			return "审核备注为必填属性";
		}
		preModel.setReqStatus(2);//申请状态设置为已接受申请
		preModel.setCheckRemark(model.getCheckRemark());
		pjReqMapper.updateByPrimaryKey(preModel);
		return "";
	}
	/**
	拒绝申请
	*/
	@Override
	public String jjsq(PjReq model,LoginModel login){
		//根据主键获取配件申请
		PjReq preModel = pjReqMapper.selectByPrimaryKey(model.getId());
		if(model.getCheckRemark()==null||model.getCheckRemark().equals("")){
			return "审核备注为必填属性";
		}
		preModel.setReqStatus(3);//申请状态设置为已拒绝申请
		preModel.setCheckRemark(model.getCheckRemark());
		pjReqMapper.updateByPrimaryKey(preModel);
		return "";
	}
	/**
	修改
	*/
	@Override
	public String update(PjReq model,LoginModel login){
		//根据主键获取配件申请
		PjReq preModel = pjReqMapper.selectByPrimaryKey(model.getId());
		if(model.getPjName()==null||model.getPjName().equals("")){
			return "配件名为必填属性";
		}
		if(model.getXyNum()==null){
			return "需要数量为必填属性";
		}
		if(model.getReqNote()==null||model.getReqNote().equals("")){
			return "申请说明为必填属性";
		}
		preModel.setPjName(model.getPjName());
		preModel.setXyNum(model.getXyNum());
		preModel.setReqNote(model.getReqNote());
		pjReqMapper.updateByPrimaryKey(preModel);
		return "";
	}
	/**
	获取列表的id集合
	*/
	public List<Long> getIds(PjReq pjReq,List<Map<String,Object>> queryParams,LoginModel login){
		List<PjReq> tl = getPjReqList(pjReq,queryParams,login,null,null,null);
		List<Long> ids = new ArrayList<Long>();
		for(PjReq t:tl){
			ids.add(t.getId());
		}
		return ids;
	}
	/**
	设置登录人可见的数据权限
	*/
	public void setQueryPerm(PjReq model,List<Map<String,Object>> queryParams,LoginModel login){
		if(login!=null&&login.getLoginType()==2){
			model.setFix(login.getId());
		}
	}
	/**
	查询封装好的配件申请列表
	*/
	public List<Map<String,Object>> getDataList(List<PjReq> tl,LoginModel login){
		List<Map<String,Object>> list = new ArrayList<Map<String,Object>>();
		for(PjReq t:tl){
			list.add(getPjReqModel(t,login));
		}
		return list;
	}
	/**
	查询总数量和总页数
	*/
	public Map<String,Long> getCount(PjReq queryModel,List<Map<String,Object>> queryParams,LoginModel login,Integer page,Integer pageSize){
		setQueryPerm(queryModel,queryParams, login);
		//初始化sql查询类
		PjReqExample se = new PjReqExample();
		PjReqExample.Criteria sc = se.createCriteria();
		//集合所有的查询条件
		getAllQueryParams(se,sc,queryModel,queryParams,login);
		long count = (long)pjReqMapper.countByExample(se);
		long totalPage = count % pageSize == 0 ? count / pageSize : (count / pageSize) + 1;
		Map<String,Long> rs = new HashMap<String,Long>();
		rs.put("count",count);//数据总数
		rs.put("totalPage",totalPage);//总页数
		return rs;
	}
	/**
	本表作为其他表的下拉列表，只显示id和名称
	*/
	public List<Map<String,Object>> getSelectList(PjReq pjReq,List<Map<String,Object>> queryParams,LoginModel login){
		setQueryPerm(pjReq,queryParams, login);
		//初始化sql查询类
		PjReqExample te = new PjReqExample();
		PjReqExample.Criteria tc = te.createCriteria();
		List<PjReq> tl = getPjReqList(pjReq,queryParams,login,null,null,null);
		List<Map<String,Object>> list = new ArrayList<Map<String,Object>>();
		for(PjReq t:tl){
			list.add(getIdNameShowById(t.getId()));
		}
		return list;
	}
	/**
	根据ID查询该数据的简易信息
	*/
	public Map<String,Object> getIdNameShowById(Long id){
		//根据主键获取配件申请
		PjReq pjReq = pjReqMapper.selectByPrimaryKey(id);
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("id",id);
		String name = pjReq.getReqNo();
		map.put("name",name);
		return map;
	}
	/**
	*根据参数查询配件申请列表数据
	*/
	@Override
	public List<PjReq> getPjReqList(PjReq queryModel,List<Map<String,Object>> queryParams,LoginModel login,Integer page,Integer pageSize,String rangeType) {
		setQueryPerm(queryModel,queryParams, login);
		//初始化sql查询类
		PjReqExample se = new PjReqExample();
		PjReqExample.Criteria sc = se.createCriteria();
		if(rangeType!=null&&rangeType.equals("")==false){
			se.setOrderByClause(rangeType);
		}else{
			se.setOrderByClause("id desc");
		}
		if(page!=null&&pageSize!=null){
			//分页
			se.setPageRows(pageSize);
			se.setStartRow((page - 1) * pageSize);
		}
		getAllQueryParams(se,sc,queryModel,queryParams,login);
		List<PjReq> list = pjReqMapper.selectByExample(se);
		return list;
	}
	//根据参数拼查询语句
	public void getAllQueryParams(PjReqExample se,PjReqExample.Criteria sc,PjReq queryModel,List<Map<String,Object>> queryParams,LoginModel login){
		sc.andAllAttrQuery(queryParams);
		if(queryModel.getId()!=null){
			sc.andIdEqualTo(queryModel.getId());
		}
		if(queryModel.getReqNo()!=null&&queryModel.getReqNo().equals("")==false){
			//模糊查询申请编号
			sc.andReqNoLike("%"+queryModel.getReqNo()+"%");
		}
		if(queryModel.getPjName()!=null&&queryModel.getPjName().equals("")==false){
			//模糊查询配件名
			sc.andPjNameLike("%"+queryModel.getPjName()+"%");
		}
		if(queryModel.getReqStatus()!=null){
			//查询申请状态
			sc.andReqStatusEqualTo(queryModel.getReqStatus());
		}
		if(queryModel.getCheckRemark()!=null&&queryModel.getCheckRemark().equals("")==false){
			//模糊查询审核备注
			sc.andCheckRemarkLike("%"+queryModel.getCheckRemark()+"%");
		}
		if(queryModel.getFix()!=null){
			//查询维修员
			sc.andFixEqualTo(queryModel.getFix());
		}
	}
	/**
	封装配件申请为前台展示的数据形式
	*/
	@Override
	public Map<String,Object> getPjReqModel(PjReq model,LoginModel login){
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("pjReq",model);
		map.put("reqStatusStr",DataListUtils.getReqStatusNameById(model.getReqStatus()+""));//解释申请状态字段
		if(model.getFix()!=null){
			//根据主键获取维修员
			Fix fix = fixMapper.selectByPrimaryKey(model.getFix());
			map.put("fix",fix);
			if(fix!=null){
				map.put("fixStr",fix.getLoginName());
				map.put("fixImg",fix.getHeadImg());
			}
		}
		return map;
	}
	/**
	* 删除数据
	*/
	@Override
	public void delete(Long id) {
		//根据主键获取配件申请
		PjReq preModel = pjReqMapper.selectByPrimaryKey(id);
		pjReqMapper.deleteByPrimaryKey(id);
	}
}
