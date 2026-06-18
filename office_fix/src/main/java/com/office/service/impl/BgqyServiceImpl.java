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
public class BgqyServiceImpl implements BgqyService{
	SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	SimpleDateFormat sdf2 = new SimpleDateFormat("yyyy-MM-dd");
	SimpleDateFormat sdf3 = new SimpleDateFormat("yyyyMMddHHmmss");
	SimpleDateFormat sdf4 = new SimpleDateFormat("yyyy-MM");
	@Autowired
	BgqyMapper bgqyMapper;
	/**
	新增
	*/
	@Override
	public String add(Bgqy model,LoginModel login){
		if(model.getQyName()==null||model.getQyName().equals("")){
			return "区域名为必填属性";
		}
		//检查是否同名
		if(model.getQyName()!=null){
			Bgqy queryModel = new Bgqy();
			List<Map<String,Object>> queryParams = new ArrayList<Map<String,Object>>();
			setQueryPerm(queryModel,queryParams, login);
			//初始化sql查询类
			BgqyExample te = new BgqyExample();
			BgqyExample.Criteria tc = te.createCriteria();
			//集合所有的查询条件
			getAllQueryParams(te,tc,queryModel,queryParams,login);
			//查询区域名
			tc.andQyNameEqualTo(model.getQyName());
			long count = (long)bgqyMapper.countByExample(te);
			if(count>0){
				return "系统已存在该 区域名 ,请重新填写";
			}
		}
		model.setIsDelete(2);//默认否,
		bgqyMapper.insertSelective(model);//插入数据库
		return "";
	}
	/**
	修改
	*/
	@Override
	public String update(Bgqy model,LoginModel login){
		//根据主键获取办公区域
		Bgqy preModel = bgqyMapper.selectByPrimaryKey(model.getId());
		if(model.getQyName()==null||model.getQyName().equals("")){
			return "区域名为必填属性";
		}
		//检查是否同名
		if(model.getQyName()!=null){
			Bgqy queryModel = new Bgqy();
			List<Map<String,Object>> queryParams = new ArrayList<Map<String,Object>>();
			setQueryPerm(queryModel,queryParams, login);
			//初始化sql查询类
			BgqyExample te = new BgqyExample();
			BgqyExample.Criteria tc = te.createCriteria();
			//集合所有的查询条件
			getAllQueryParams(te,tc,queryModel,queryParams,login);
			tc.andIdNotEqualTo(model.getId());
			//查询区域名
			tc.andQyNameEqualTo(model.getQyName());
			long count = (long)bgqyMapper.countByExample(te);
			if(count>0){
				return "系统已存在该 区域名 ,请重新填写";
			}
		}
		preModel.setQyName(model.getQyName());
		bgqyMapper.updateByPrimaryKey(preModel);
		return "";
	}
	/**
	获取列表的id集合
	*/
	public List<Long> getIds(Bgqy bgqy,List<Map<String,Object>> queryParams,LoginModel login){
		List<Bgqy> tl = getBgqyList(bgqy,queryParams,login,null,null,null);
		List<Long> ids = new ArrayList<Long>();
		for(Bgqy t:tl){
			ids.add(t.getId());
		}
		return ids;
	}
	/**
	设置登录人可见的数据权限
	*/
	public void setQueryPerm(Bgqy model,List<Map<String,Object>> queryParams,LoginModel login){
	}
	/**
	查询封装好的办公区域列表
	*/
	public List<Map<String,Object>> getDataList(List<Bgqy> tl,LoginModel login){
		List<Map<String,Object>> list = new ArrayList<Map<String,Object>>();
		for(Bgqy t:tl){
			list.add(getBgqyModel(t,login));
		}
		return list;
	}
	/**
	查询总数量和总页数
	*/
	public Map<String,Long> getCount(Bgqy queryModel,List<Map<String,Object>> queryParams,LoginModel login,Integer page,Integer pageSize){
		setQueryPerm(queryModel,queryParams, login);
		//初始化sql查询类
		BgqyExample se = new BgqyExample();
		BgqyExample.Criteria sc = se.createCriteria();
		//集合所有的查询条件
		getAllQueryParams(se,sc,queryModel,queryParams,login);
		long count = (long)bgqyMapper.countByExample(se);
		long totalPage = count % pageSize == 0 ? count / pageSize : (count / pageSize) + 1;
		Map<String,Long> rs = new HashMap<String,Long>();
		rs.put("count",count);//数据总数
		rs.put("totalPage",totalPage);//总页数
		return rs;
	}
	/**
	本表作为其他表的下拉列表，只显示id和名称
	*/
	public List<Map<String,Object>> getSelectList(Bgqy bgqy,List<Map<String,Object>> queryParams,LoginModel login){
		setQueryPerm(bgqy,queryParams, login);
		//初始化sql查询类
		BgqyExample te = new BgqyExample();
		BgqyExample.Criteria tc = te.createCriteria();
		List<Bgqy> tl = getBgqyList(bgqy,queryParams,login,null,null,null);
		List<Map<String,Object>> list = new ArrayList<Map<String,Object>>();
		for(Bgqy t:tl){
			list.add(getIdNameShowById(t.getId()));
		}
		return list;
	}
	/**
	根据ID查询该数据的简易信息
	*/
	public Map<String,Object> getIdNameShowById(Long id){
		//根据主键获取办公区域
		Bgqy bgqy = bgqyMapper.selectByPrimaryKey(id);
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("id",id);
		String name = bgqy.getQyName();
		map.put("name",name);
		return map;
	}
	/**
	*根据参数查询办公区域列表数据
	*/
	@Override
	public List<Bgqy> getBgqyList(Bgqy queryModel,List<Map<String,Object>> queryParams,LoginModel login,Integer page,Integer pageSize,String rangeType) {
		setQueryPerm(queryModel,queryParams, login);
		//初始化sql查询类
		BgqyExample se = new BgqyExample();
		BgqyExample.Criteria sc = se.createCriteria();
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
		List<Bgqy> list = bgqyMapper.selectByExample(se);
		return list;
	}
	//根据参数拼查询语句
	public void getAllQueryParams(BgqyExample se,BgqyExample.Criteria sc,Bgqy queryModel,List<Map<String,Object>> queryParams,LoginModel login){
		//查询是否删除
		sc.andIsDeleteEqualTo(2);
		sc.andAllAttrQuery(queryParams);
		if(queryModel.getId()!=null){
			sc.andIdEqualTo(queryModel.getId());
		}
		if(queryModel.getQyName()!=null&&queryModel.getQyName().equals("")==false){
			//模糊查询区域名
			sc.andQyNameLike("%"+queryModel.getQyName()+"%");
		}
		if(queryModel.getIsDelete()!=null){
			//查询是否删除
			sc.andIsDeleteEqualTo(queryModel.getIsDelete());
		}
	}
	/**
	封装办公区域为前台展示的数据形式
	*/
	@Override
	public Map<String,Object> getBgqyModel(Bgqy model,LoginModel login){
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("bgqy",model);
		map.put("isDeleteStr",DataListUtils.getIsDeleteNameById(model.getIsDelete()+""));//解释是否删除字段
		return map;
	}
	/**
	* 删除数据
	*/
	@Override
	public void delete(Long id) {
		//根据主键获取办公区域
		Bgqy preModel = bgqyMapper.selectByPrimaryKey(id);
		Bgqy bgqy = bgqyMapper.selectByPrimaryKey(id);
		bgqy.setIsDelete(1);
		bgqyMapper.updateByPrimaryKey(bgqy);
	}
}
