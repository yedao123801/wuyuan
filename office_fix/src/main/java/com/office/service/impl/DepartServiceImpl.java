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
public class DepartServiceImpl implements DepartService{
	SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	SimpleDateFormat sdf2 = new SimpleDateFormat("yyyy-MM-dd");
	SimpleDateFormat sdf3 = new SimpleDateFormat("yyyyMMddHHmmss");
	SimpleDateFormat sdf4 = new SimpleDateFormat("yyyy-MM");
	@Autowired
	DepartMapper departMapper;
	@Autowired
	UserMapper userMapper;
	/**
	新增
	*/
	@Override
	public String add(Depart model,LoginModel login){
		if(model.getDepartName()==null||model.getDepartName().equals("")){
			return "部门名为必填属性";
		}
		//检查是否同名
		if(model.getDepartName()!=null){
			Depart queryModel = new Depart();
			List<Map<String,Object>> queryParams = new ArrayList<Map<String,Object>>();
			setQueryPerm(queryModel,queryParams, login);
			//初始化sql查询类
			DepartExample te = new DepartExample();
			DepartExample.Criteria tc = te.createCriteria();
			//集合所有的查询条件
			getAllQueryParams(te,tc,queryModel,queryParams,login);
			//查询部门名
			tc.andDepartNameEqualTo(model.getDepartName());
			long count = (long)departMapper.countByExample(te);
			if(count>0){
				return "系统已存在该 部门名 ,请重新填写";
			}
		}
		model.setIsDelete(2);//默认否,
		departMapper.insertSelective(model);//插入数据库
		return "";
	}
	/**
	修改
	*/
	@Override
	public String update(Depart model,LoginModel login){
		//根据主键获取部门
		Depart preModel = departMapper.selectByPrimaryKey(model.getId());
		if(model.getDepartName()==null||model.getDepartName().equals("")){
			return "部门名为必填属性";
		}
		//检查是否同名
		if(model.getDepartName()!=null){
			Depart queryModel = new Depart();
			List<Map<String,Object>> queryParams = new ArrayList<Map<String,Object>>();
			setQueryPerm(queryModel,queryParams, login);
			//初始化sql查询类
			DepartExample te = new DepartExample();
			DepartExample.Criteria tc = te.createCriteria();
			//集合所有的查询条件
			getAllQueryParams(te,tc,queryModel,queryParams,login);
			tc.andIdNotEqualTo(model.getId());
			//查询部门名
			tc.andDepartNameEqualTo(model.getDepartName());
			long count = (long)departMapper.countByExample(te);
			if(count>0){
				return "系统已存在该 部门名 ,请重新填写";
			}
		}
		preModel.setDepartName(model.getDepartName());
		departMapper.updateByPrimaryKey(preModel);
		return "";
	}
	/**
	获取列表的id集合
	*/
	public List<Long> getIds(Depart depart,List<Map<String,Object>> queryParams,LoginModel login){
		List<Depart> tl = getDepartList(depart,queryParams,login,null,null,null);
		List<Long> ids = new ArrayList<Long>();
		for(Depart t:tl){
			ids.add(t.getId());
		}
		return ids;
	}
	/**
	设置登录人可见的数据权限
	*/
	public void setQueryPerm(Depart model,List<Map<String,Object>> queryParams,LoginModel login){
		if(login!=null&&login.getLoginType()==3){
			//根据主键获取用户
			User user = userMapper.selectByPrimaryKey(login.getId());
			model.setId(user.getDepart());
		}
	}
	/**
	查询封装好的部门列表
	*/
	public List<Map<String,Object>> getDataList(List<Depart> tl,LoginModel login){
		List<Map<String,Object>> list = new ArrayList<Map<String,Object>>();
		for(Depart t:tl){
			list.add(getDepartModel(t,login));
		}
		return list;
	}
	/**
	查询总数量和总页数
	*/
	public Map<String,Long> getCount(Depart queryModel,List<Map<String,Object>> queryParams,LoginModel login,Integer page,Integer pageSize){
		setQueryPerm(queryModel,queryParams, login);
		//初始化sql查询类
		DepartExample se = new DepartExample();
		DepartExample.Criteria sc = se.createCriteria();
		//集合所有的查询条件
		getAllQueryParams(se,sc,queryModel,queryParams,login);
		long count = (long)departMapper.countByExample(se);
		long totalPage = count % pageSize == 0 ? count / pageSize : (count / pageSize) + 1;
		Map<String,Long> rs = new HashMap<String,Long>();
		rs.put("count",count);//数据总数
		rs.put("totalPage",totalPage);//总页数
		return rs;
	}
	/**
	本表作为其他表的下拉列表，只显示id和名称
	*/
	public List<Map<String,Object>> getSelectList(Depart depart,List<Map<String,Object>> queryParams,LoginModel login){
		setQueryPerm(depart,queryParams, login);
		//初始化sql查询类
		DepartExample te = new DepartExample();
		DepartExample.Criteria tc = te.createCriteria();
		List<Depart> tl = getDepartList(depart,queryParams,login,null,null,null);
		List<Map<String,Object>> list = new ArrayList<Map<String,Object>>();
		for(Depart t:tl){
			list.add(getIdNameShowById(t.getId()));
		}
		return list;
	}
	/**
	根据ID查询该数据的简易信息
	*/
	public Map<String,Object> getIdNameShowById(Long id){
		//根据主键获取部门
		Depart depart = departMapper.selectByPrimaryKey(id);
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("id",id);
		String name = depart.getDepartName();
		map.put("name",name);
		return map;
	}
	/**
	*根据参数查询部门列表数据
	*/
	@Override
	public List<Depart> getDepartList(Depart queryModel,List<Map<String,Object>> queryParams,LoginModel login,Integer page,Integer pageSize,String rangeType) {
		setQueryPerm(queryModel,queryParams, login);
		//初始化sql查询类
		DepartExample se = new DepartExample();
		DepartExample.Criteria sc = se.createCriteria();
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
		List<Depart> list = departMapper.selectByExample(se);
		return list;
	}
	//根据参数拼查询语句
	public void getAllQueryParams(DepartExample se,DepartExample.Criteria sc,Depart queryModel,List<Map<String,Object>> queryParams,LoginModel login){
		//查询是否删除
		sc.andIsDeleteEqualTo(2);
		sc.andAllAttrQuery(queryParams);
		if(queryModel.getId()!=null){
			sc.andIdEqualTo(queryModel.getId());
		}
		if(queryModel.getDepartName()!=null&&queryModel.getDepartName().equals("")==false){
			//模糊查询部门名
			sc.andDepartNameLike("%"+queryModel.getDepartName()+"%");
		}
		if(queryModel.getIsDelete()!=null){
			//查询是否删除
			sc.andIsDeleteEqualTo(queryModel.getIsDelete());
		}
	}
	/**
	封装部门为前台展示的数据形式
	*/
	@Override
	public Map<String,Object> getDepartModel(Depart model,LoginModel login){
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("depart",model);
		map.put("isDeleteStr",DataListUtils.getIsDeleteNameById(model.getIsDelete()+""));//解释是否删除字段
		return map;
	}
	/**
	* 删除数据
	*/
	@Override
	public void delete(Long id) {
		//根据主键获取部门
		Depart preModel = departMapper.selectByPrimaryKey(id);
		Depart depart = departMapper.selectByPrimaryKey(id);
		depart.setIsDelete(1);
		departMapper.updateByPrimaryKey(depart);
	}
}
