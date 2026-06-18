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
public class FixServiceImpl implements FixService{
	SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	SimpleDateFormat sdf2 = new SimpleDateFormat("yyyy-MM-dd");
	SimpleDateFormat sdf3 = new SimpleDateFormat("yyyyMMddHHmmss");
	SimpleDateFormat sdf4 = new SimpleDateFormat("yyyy-MM");
	@Autowired
	EvalLogMapper evalLogMapper;
	@Autowired
	FixMapper fixMapper;
	/**
	新增
	*/
	@Override
	public String add(Fix model,LoginModel login){
		if(model.getLoginName()==null||model.getLoginName().equals("")){
			return "登录名为必填属性";
		}
		//检查是否同名
		if(model.getLoginName()!=null){
			Fix queryModel = new Fix();
			List<Map<String,Object>> queryParams = new ArrayList<Map<String,Object>>();
			setQueryPerm(queryModel,queryParams, login);
			//初始化sql查询类
			FixExample te = new FixExample();
			FixExample.Criteria tc = te.createCriteria();
			//集合所有的查询条件
			getAllQueryParams(te,tc,queryModel,queryParams,login);
			//查询登录名
			tc.andLoginNameEqualTo(model.getLoginName());
			long count = (long)fixMapper.countByExample(te);
			if(count>0){
				return "系统已存在该 登录名 ,请重新填写";
			}
		}
		if(model.getPassword()==null||model.getPassword().equals("")){
			return "密码为必填属性";
		}
		if(model.getName()==null||model.getName().equals("")){
			return "姓名为必填属性";
		}
		if(model.getCelphone()==null||model.getCelphone().equals("")){
			return "联系电话为必填属性";
		}
		if(model.getCelphone()!=null){
		Pattern p = Pattern.compile("^[1](([3|5|8][\\d])|([4][4,5,6,7,8,9])|([6][2,5,6,7])|([7][^9])|([9][1,8,9]))[\\d]{8}$");
			Matcher m = p.matcher(model.getCelphone());
			if( m.matches()==false){
				return "请输入正确的联系电话";
			}
		}
		if(model.getHeadImg()==null||model.getHeadImg().equals("")){
			return "头像为必填属性";
		}
		if(model.getHeadImg()!=null){
			String [] fileSplit = model.getHeadImg().split(";");
			if(fileSplit.length>1){
				return "头像的图片数量不能大于1";
			}
		}
		if(model.getSzType()==null||model.getSzType().equals("")){
			return "擅长类型为必填属性";
		}
		if(model.getFixZz()==null||model.getFixZz().equals("")){
			return "维修资质为必填属性";
		}
		if(model.getFixZz()!=null){
			String [] fileSplit = model.getFixZz().split(";");
			if(fileSplit.length>1){
				return "维修资质的图片数量不能大于1";
			}
		}
		model.setCheckStatus(1);//默认待审核,
		model.setTevalScore(0.0);//默认0.0
		model.setZcTime(sdf1.format(new Date()));//当前时间格式
		model.setIsDelete(2);//默认否,
		fixMapper.insertSelective(model);//插入数据库
		calcuteFix(model.getId());
		return "";
	}
	/**
	修改
	*/
	@Override
	public String update(Fix model,LoginModel login){
		//根据主键获取维修员
		Fix preModel = fixMapper.selectByPrimaryKey(model.getId());
		if(model.getLoginName()==null||model.getLoginName().equals("")){
			return "登录名为必填属性";
		}
		//检查是否同名
		if(model.getLoginName()!=null){
			Fix queryModel = new Fix();
			List<Map<String,Object>> queryParams = new ArrayList<Map<String,Object>>();
			setQueryPerm(queryModel,queryParams, login);
			//初始化sql查询类
			FixExample te = new FixExample();
			FixExample.Criteria tc = te.createCriteria();
			//集合所有的查询条件
			getAllQueryParams(te,tc,queryModel,queryParams,login);
			tc.andIdNotEqualTo(model.getId());
			//查询登录名
			tc.andLoginNameEqualTo(model.getLoginName());
			long count = (long)fixMapper.countByExample(te);
			if(count>0){
				return "系统已存在该 登录名 ,请重新填写";
			}
		}
		if(model.getName()==null||model.getName().equals("")){
			return "姓名为必填属性";
		}
		if(model.getCelphone()==null||model.getCelphone().equals("")){
			return "联系电话为必填属性";
		}
		if(model.getCelphone()!=null){
		Pattern p = Pattern.compile("^[1](([3|5|8][\\d])|([4][4,5,6,7,8,9])|([6][2,5,6,7])|([7][^9])|([9][1,8,9]))[\\d]{8}$");
			Matcher m = p.matcher(model.getCelphone());
			if( m.matches()==false){
				return "请输入正确的联系电话";
			}
		}
		if(model.getHeadImg()==null||model.getHeadImg().equals("")){
			return "头像为必填属性";
		}
		if(model.getHeadImg()!=null){
			String [] fileSplit = model.getHeadImg().split(";");
			if(fileSplit.length>1){
				return "头像的图片数量不能大于1";
			}
		}
		if(model.getSzType()==null||model.getSzType().equals("")){
			return "擅长类型为必填属性";
		}
		if(model.getFixZz()==null||model.getFixZz().equals("")){
			return "维修资质为必填属性";
		}
		if(model.getFixZz()!=null){
			String [] fileSplit = model.getFixZz().split(";");
			if(fileSplit.length>1){
				return "维修资质的图片数量不能大于1";
			}
		}
		preModel.setLoginName(model.getLoginName());
		preModel.setName(model.getName());
		preModel.setCelphone(model.getCelphone());
		preModel.setHeadImg(model.getHeadImg());
		preModel.setSzType(model.getSzType());
		preModel.setFixZz(model.getFixZz());
		fixMapper.updateByPrimaryKey(preModel);
		calcuteFix(preModel.getId());
		return "";
	}
	/**
	修改
	*/
	@Override
	public String update1(Fix model,LoginModel login){
		//根据主键获取维修员
		Fix preModel = fixMapper.selectByPrimaryKey(model.getId());
		if(model.getName()==null||model.getName().equals("")){
			return "姓名为必填属性";
		}
		if(model.getCelphone()==null||model.getCelphone().equals("")){
			return "联系电话为必填属性";
		}
		if(model.getCelphone()!=null){
		Pattern p = Pattern.compile("^[1](([3|5|8][\\d])|([4][4,5,6,7,8,9])|([6][2,5,6,7])|([7][^9])|([9][1,8,9]))[\\d]{8}$");
			Matcher m = p.matcher(model.getCelphone());
			if( m.matches()==false){
				return "请输入正确的联系电话";
			}
		}
		if(model.getHeadImg()==null||model.getHeadImg().equals("")){
			return "头像为必填属性";
		}
		if(model.getHeadImg()!=null){
			String [] fileSplit = model.getHeadImg().split(";");
			if(fileSplit.length>1){
				return "头像的图片数量不能大于1";
			}
		}
		if(model.getSzType()==null||model.getSzType().equals("")){
			return "擅长类型为必填属性";
		}
		if(model.getFixZz()==null||model.getFixZz().equals("")){
			return "维修资质为必填属性";
		}
		if(model.getFixZz()!=null){
			String [] fileSplit = model.getFixZz().split(";");
			if(fileSplit.length>1){
				return "维修资质的图片数量不能大于1";
			}
		}
		preModel.setName(model.getName());
		preModel.setCelphone(model.getCelphone());
		preModel.setHeadImg(model.getHeadImg());
		preModel.setSzType(model.getSzType());
		preModel.setFixZz(model.getFixZz());
		fixMapper.updateByPrimaryKey(preModel);
		calcuteFix(preModel.getId());
		return "";
	}
	public void calcuteFix(Long id){
		//根据主键获取维修员
		Fix t = fixMapper.selectByPrimaryKey(id);
		Double evalLogEvalStar = 0.0;
		//初始化sql查询类
		EvalLogExample ee1 = new EvalLogExample();
		EvalLogExample.Criteria ec1 = ee1.createCriteria();
		//查询维修员
		ec1.andFixEqualTo(id);
		List<EvalLog> elist1 = evalLogMapper.selectByExample(ee1);
		for(EvalLog evalLog:elist1){
			if(evalLog.getEvalStar()!=null){
				evalLogEvalStar+=evalLog.getEvalStar();
			}
		}
		if(elist1.size()>0){
			evalLogEvalStar = evalLogEvalStar/elist1.size();
		}
		t.setTevalScore(evalLogEvalStar);
		fixMapper.updateByPrimaryKey(t);
	}
	/**
	获取列表的id集合
	*/
	public List<Long> getIds(Fix fix,List<Map<String,Object>> queryParams,LoginModel login){
		fix.setCheckStatus(2);
		List<Fix> tl = getFixList(fix,queryParams,login,null,null,null);
		List<Long> ids = new ArrayList<Long>();
		for(Fix t:tl){
			ids.add(t.getId());
		}
		return ids;
	}
	/**
	设置登录人可见的数据权限
	*/
	public void setQueryPerm(Fix model,List<Map<String,Object>> queryParams,LoginModel login){
		if(login!=null&&login.getLoginType()==2){
			model.setId(login.getId());
		}
	}
	/**
	查询封装好的维修员列表
	*/
	public List<Map<String,Object>> getDataList(List<Fix> tl,LoginModel login){
		List<Map<String,Object>> list = new ArrayList<Map<String,Object>>();
		for(Fix t:tl){
			list.add(getFixModel(t,login));
		}
		return list;
	}
	/**
	查询总数量和总页数
	*/
	public Map<String,Long> getCount(Fix queryModel,List<Map<String,Object>> queryParams,LoginModel login,Integer page,Integer pageSize){
		setQueryPerm(queryModel,queryParams, login);
		//初始化sql查询类
		FixExample se = new FixExample();
		FixExample.Criteria sc = se.createCriteria();
		//集合所有的查询条件
		getAllQueryParams(se,sc,queryModel,queryParams,login);
		long count = (long)fixMapper.countByExample(se);
		long totalPage = count % pageSize == 0 ? count / pageSize : (count / pageSize) + 1;
		Map<String,Long> rs = new HashMap<String,Long>();
		rs.put("count",count);//数据总数
		rs.put("totalPage",totalPage);//总页数
		return rs;
	}
	/**
	本表作为其他表的下拉列表，只显示id和名称
	*/
	public List<Map<String,Object>> getSelectList(Fix fix,List<Map<String,Object>> queryParams,LoginModel login){
		setQueryPerm(fix,queryParams, login);
		//初始化sql查询类
		FixExample te = new FixExample();
		FixExample.Criteria tc = te.createCriteria();
		fix.setCheckStatus(2);
		List<Fix> tl = getFixList(fix,queryParams,login,null,null,null);
		List<Map<String,Object>> list = new ArrayList<Map<String,Object>>();
		for(Fix t:tl){
			list.add(getIdNameShowById(t.getId()));
		}
		return list;
	}
	/**
	根据ID查询该数据的简易信息
	*/
	public Map<String,Object> getIdNameShowById(Long id){
		//根据主键获取维修员
		Fix fix = fixMapper.selectByPrimaryKey(id);
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("id",id);
		String name = fix.getLoginName();
		map.put("name",name);
		map.put("img",fix.getHeadImg());
		return map;
	}
	/**
	*根据参数查询维修员列表数据
	*/
	@Override
	public List<Fix> getFixList(Fix queryModel,List<Map<String,Object>> queryParams,LoginModel login,Integer page,Integer pageSize,String rangeType) {
		setQueryPerm(queryModel,queryParams, login);
		//初始化sql查询类
		FixExample se = new FixExample();
		FixExample.Criteria sc = se.createCriteria();
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
		List<Fix> list = fixMapper.selectByExample(se);
		return list;
	}
	//根据参数拼查询语句
	public void getAllQueryParams(FixExample se,FixExample.Criteria sc,Fix queryModel,List<Map<String,Object>> queryParams,LoginModel login){
		//查询是否删除
		sc.andIsDeleteEqualTo(2);
		sc.andAllAttrQuery(queryParams);
		if(queryModel.getId()!=null){
			sc.andIdEqualTo(queryModel.getId());
		}
		if(queryModel.getLoginName()!=null&&queryModel.getLoginName().equals("")==false){
			//模糊查询登录名
			sc.andLoginNameLike("%"+queryModel.getLoginName()+"%");
		}
		if(queryModel.getName()!=null&&queryModel.getName().equals("")==false){
			//模糊查询姓名
			sc.andNameLike("%"+queryModel.getName()+"%");
		}
		if(queryModel.getCheckStatus()!=null){
			//查询审核状态
			sc.andCheckStatusEqualTo(queryModel.getCheckStatus());
		}
		if(queryModel.getIsDelete()!=null){
			//查询是否删除
			sc.andIsDeleteEqualTo(queryModel.getIsDelete());
		}
	}
	/**
	封装维修员为前台展示的数据形式
	*/
	@Override
	public Map<String,Object> getFixModel(Fix model,LoginModel login){
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("fix",model);
		map.put("headImgList",CommonUtils.parseImgList(model.getHeadImg()));
		map.put("fixZzList",CommonUtils.parseImgList(model.getFixZz()));
		map.put("checkStatusStr",DataListUtils.getCheckStatusNameById(model.getCheckStatus()+""));//解释审核状态字段
		map.put("isDeleteStr",DataListUtils.getIsDeleteNameById(model.getIsDelete()+""));//解释是否删除字段
		return map;
	}
	/**
	* 删除数据
	*/
	@Override
	public void delete(Long id) {
		//根据主键获取维修员
		Fix preModel = fixMapper.selectByPrimaryKey(id);
		Fix fix = fixMapper.selectByPrimaryKey(id);
		fix.setIsDelete(1);
		fixMapper.updateByPrimaryKey(fix);
		calcuteFix(preModel.getId());
	}
}
