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
public class BgServiceServiceImpl implements BgServiceService{
	SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	SimpleDateFormat sdf2 = new SimpleDateFormat("yyyy-MM-dd");
	SimpleDateFormat sdf3 = new SimpleDateFormat("yyyyMMddHHmmss");
	SimpleDateFormat sdf4 = new SimpleDateFormat("yyyy-MM");
	@Autowired
	BgqyMapper bgqyMapper;
	@Autowired
	BgServiceMapper bgServiceMapper;
	/**
	新增
	*/
	@Override
	public String add(BgService model,LoginModel login){
		if(model.getServiceNo()==null||model.getServiceNo().equals("")){
			return "设备编号为必填属性";
		}
		//检查是否同名
		if(model.getServiceNo()!=null){
			BgService queryModel = new BgService();
			List<Map<String,Object>> queryParams = new ArrayList<Map<String,Object>>();
			setQueryPerm(queryModel,queryParams, login);
			//初始化sql查询类
			BgServiceExample te = new BgServiceExample();
			BgServiceExample.Criteria tc = te.createCriteria();
			//集合所有的查询条件
			getAllQueryParams(te,tc,queryModel,queryParams,login);
			//查询设备编号
			tc.andServiceNoEqualTo(model.getServiceNo());
			long count = (long)bgServiceMapper.countByExample(te);
			if(count>0){
				return "系统已存在该 设备编号 ,请重新填写";
			}
		}
		if(model.getServiceType()==null||model.getServiceType().equals("")){
			return "设备类型为必填属性";
		}
		if(model.getServiceName()==null||model.getServiceName().equals("")){
			return "设备名为必填属性";
		}
		if(model.getServiceXh()==null||model.getServiceXh().equals("")){
			return "设备型号为必填属性";
		}
		if(model.getCgDate()==null||model.getCgDate().equals("")){
			return "采购日期为必填属性";
		}
		if(model.getBgqy()==null){
			return "办公区域为必填属性";
		}
		model.setIsDelete(2);//默认否,
		model.setSearchKeyWord("320095");//默认320095
		String serviceXh3 = model.getServiceXh()==null?"":model.getServiceXh();
		String serviceName3 = model.getServiceName()==null?"":model.getServiceName();
		String serviceType3 = model.getServiceType()==null?"":model.getServiceType();
		String serviceNo3 = model.getServiceNo()==null?"":model.getServiceNo();
		model.setSearchKeyWord(serviceXh3+serviceName3+serviceType3+serviceNo3);
		String today = sdf2.format(new Date());
		bgServiceMapper.insertSelective(model);//插入数据库
		return "";
	}
	/**
	修改
	*/
	@Override
	public String update(BgService model,LoginModel login){
		//根据主键获取办公设备
		BgService preModel = bgServiceMapper.selectByPrimaryKey(model.getId());
		if(model.getServiceNo()==null||model.getServiceNo().equals("")){
			return "设备编号为必填属性";
		}
		//检查是否同名
		if(model.getServiceNo()!=null){
			BgService queryModel = new BgService();
			List<Map<String,Object>> queryParams = new ArrayList<Map<String,Object>>();
			setQueryPerm(queryModel,queryParams, login);
			//初始化sql查询类
			BgServiceExample te = new BgServiceExample();
			BgServiceExample.Criteria tc = te.createCriteria();
			//集合所有的查询条件
			getAllQueryParams(te,tc,queryModel,queryParams,login);
			tc.andIdNotEqualTo(model.getId());
			//查询设备编号
			tc.andServiceNoEqualTo(model.getServiceNo());
			long count = (long)bgServiceMapper.countByExample(te);
			if(count>0){
				return "系统已存在该 设备编号 ,请重新填写";
			}
		}
		if(model.getServiceType()==null||model.getServiceType().equals("")){
			return "设备类型为必填属性";
		}
		if(model.getServiceName()==null||model.getServiceName().equals("")){
			return "设备名为必填属性";
		}
		if(model.getServiceXh()==null||model.getServiceXh().equals("")){
			return "设备型号为必填属性";
		}
		if(model.getCgDate()==null||model.getCgDate().equals("")){
			return "采购日期为必填属性";
		}
		if(model.getBgqy()==null){
			return "办公区域为必填属性";
		}
		preModel.setServiceNo(model.getServiceNo());
		preModel.setServiceType(model.getServiceType());
		preModel.setServiceName(model.getServiceName());
		preModel.setServiceXh(model.getServiceXh());
		preModel.setCgDate(model.getCgDate());
		preModel.setBgqy(model.getBgqy());
		String today = sdf2.format(new Date());
		bgServiceMapper.updateByPrimaryKey(preModel);
		return "";
	}
	/**
	获取列表的id集合
	*/
	public List<Long> getIds(BgService bgService,List<Map<String,Object>> queryParams,LoginModel login){
		List<BgService> tl = getBgServiceList(bgService,queryParams,login,null,null,null);
		List<Long> ids = new ArrayList<Long>();
		for(BgService t:tl){
			ids.add(t.getId());
		}
		return ids;
	}
	/**
	设置登录人可见的数据权限
	*/
	public void setQueryPerm(BgService model,List<Map<String,Object>> queryParams,LoginModel login){
	}
	/**
	查询封装好的办公设备列表
	*/
	public List<Map<String,Object>> getDataList(List<BgService> tl,LoginModel login){
		List<Map<String,Object>> list = new ArrayList<Map<String,Object>>();
		for(BgService t:tl){
			list.add(getBgServiceModel(t,login));
		}
		return list;
	}
	/**
	查询总数量和总页数
	*/
	public Map<String,Long> getCount(BgService queryModel,List<Map<String,Object>> queryParams,LoginModel login,Integer page,Integer pageSize){
		setQueryPerm(queryModel,queryParams, login);
		//初始化sql查询类
		BgServiceExample se = new BgServiceExample();
		BgServiceExample.Criteria sc = se.createCriteria();
		//集合所有的查询条件
		getAllQueryParams(se,sc,queryModel,queryParams,login);
		long count = (long)bgServiceMapper.countByExample(se);
		long totalPage = count % pageSize == 0 ? count / pageSize : (count / pageSize) + 1;
		Map<String,Long> rs = new HashMap<String,Long>();
		rs.put("count",count);//数据总数
		rs.put("totalPage",totalPage);//总页数
		return rs;
	}
	/**
	本表作为其他表的下拉列表，只显示id和名称
	*/
	public List<Map<String,Object>> getSelectList(BgService bgService,List<Map<String,Object>> queryParams,LoginModel login){
		setQueryPerm(bgService,queryParams, login);
		//初始化sql查询类
		BgServiceExample te = new BgServiceExample();
		BgServiceExample.Criteria tc = te.createCriteria();
		List<BgService> tl = getBgServiceList(bgService,queryParams,login,null,null,null);
		List<Map<String,Object>> list = new ArrayList<Map<String,Object>>();
		for(BgService t:tl){
			list.add(getIdNameShowById(t.getId()));
		}
		return list;
	}
	/**
	根据ID查询该数据的简易信息
	*/
	public Map<String,Object> getIdNameShowById(Long id){
		//根据主键获取办公设备
		BgService bgService = bgServiceMapper.selectByPrimaryKey(id);
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("id",id);
		String name = "（"+bgService.getServiceNo()+"）"+bgService.getServiceName();
		map.put("name",name);
		return map;
	}
	/**
	*根据参数查询办公设备列表数据
	*/
	@Override
	public List<BgService> getBgServiceList(BgService queryModel,List<Map<String,Object>> queryParams,LoginModel login,Integer page,Integer pageSize,String rangeType) {
		setQueryPerm(queryModel,queryParams, login);
		//初始化sql查询类
		BgServiceExample se = new BgServiceExample();
		BgServiceExample.Criteria sc = se.createCriteria();
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
		List<BgService> list = bgServiceMapper.selectByExample(se);
		return list;
	}
	//根据参数拼查询语句
	public void getAllQueryParams(BgServiceExample se,BgServiceExample.Criteria sc,BgService queryModel,List<Map<String,Object>> queryParams,LoginModel login){
		//查询是否删除
		sc.andIsDeleteEqualTo(2);
		sc.andAllAttrQuery(queryParams);
		if(queryModel.getId()!=null){
			sc.andIdEqualTo(queryModel.getId());
		}
		if(queryModel.getServiceNo()!=null&&queryModel.getServiceNo().equals("")==false){
			//模糊查询设备编号
			sc.andServiceNoLike("%"+queryModel.getServiceNo()+"%");
		}
		if(queryModel.getServiceName()!=null&&queryModel.getServiceName().equals("")==false){
			//模糊查询设备名
			sc.andServiceNameLike("%"+queryModel.getServiceName()+"%");
		}
		if(queryModel.getServiceXh()!=null&&queryModel.getServiceXh().equals("")==false){
			//模糊查询设备型号
			sc.andServiceXhLike("%"+queryModel.getServiceXh()+"%");
		}
		if(queryModel.getBgqy()!=null){
			//查询办公区域
			sc.andBgqyEqualTo(queryModel.getBgqy());
		}
		if(queryModel.getIsDelete()!=null){
			//查询是否删除
			sc.andIsDeleteEqualTo(queryModel.getIsDelete());
		}
		if(queryModel.getSearchKeyWord()!=null&&queryModel.getSearchKeyWord().trim().equals("")==false){
			//模糊查询关键词
			sc.andSearchKeyWordLike("%"+queryModel.getSearchKeyWord()+"%");
		}
	}
	/**
	封装办公设备为前台展示的数据形式
	*/
	@Override
	public Map<String,Object> getBgServiceModel(BgService model,LoginModel login){
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("bgService",model);
		if(model.getBgqy()!=null){
			//根据主键获取办公区域
			Bgqy bgqy = bgqyMapper.selectByPrimaryKey(model.getBgqy());
			map.put("bgqy",bgqy);
			if(bgqy!=null){
				map.put("bgqyStr",bgqy.getQyName());
			}
		}
		map.put("isDeleteStr",DataListUtils.getIsDeleteNameById(model.getIsDelete()+""));//解释是否删除字段
		return map;
	}
	/**
	* 删除数据
	*/
	@Override
	public void delete(Long id) {
		//根据主键获取办公设备
		BgService preModel = bgServiceMapper.selectByPrimaryKey(id);
		BgService bgService = bgServiceMapper.selectByPrimaryKey(id);
		bgService.setIsDelete(1);
		bgServiceMapper.updateByPrimaryKey(bgService);
	}
}
