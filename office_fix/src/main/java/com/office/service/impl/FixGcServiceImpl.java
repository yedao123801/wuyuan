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
public class FixGcServiceImpl implements FixGcService{
	SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	SimpleDateFormat sdf2 = new SimpleDateFormat("yyyy-MM-dd");
	SimpleDateFormat sdf3 = new SimpleDateFormat("yyyyMMddHHmmss");
	SimpleDateFormat sdf4 = new SimpleDateFormat("yyyy-MM");
	@Autowired
	FixGcMapper fixGcMapper;
	@Autowired
	BxLogMapper bxLogMapper;
	@Autowired
	FixMapper fixMapper;
	/**
	新增
	*/
	@Override
	public String add(FixGc model,LoginModel login){
		if(model.getFixBz()==null||model.getFixBz().equals("")){
			return "维修步骤为必填属性";
		}
		if(model.getGhpj()==null||model.getGhpj().equals("")){
			return "更换配件为必填属性";
		}
		if(model.getXhgs()==null){
			return "消耗工时为必填属性";
		}
		model.setFix(login.getId());//登录id
		model.setCreateTime(sdf1.format(new Date()));//当前时间格式
		fixGcMapper.insertSelective(model);//插入数据库
		return "";
	}
	/**
	修改
	*/
	@Override
	public String update(FixGc model,LoginModel login){
		//根据主键获取维修过程
		FixGc preModel = fixGcMapper.selectByPrimaryKey(model.getId());
		if(model.getFixBz()==null||model.getFixBz().equals("")){
			return "维修步骤为必填属性";
		}
		if(model.getGhpj()==null||model.getGhpj().equals("")){
			return "更换配件为必填属性";
		}
		if(model.getXhgs()==null){
			return "消耗工时为必填属性";
		}
		preModel.setFixBz(model.getFixBz());
		preModel.setGhpj(model.getGhpj());
		preModel.setXhgs(model.getXhgs());
		fixGcMapper.updateByPrimaryKey(preModel);
		return "";
	}
	/**
	获取列表的id集合
	*/
	public List<Long> getIds(FixGc fixGc,List<Map<String,Object>> queryParams,LoginModel login){
		List<FixGc> tl = getFixGcList(fixGc,queryParams,login,null,null,null);
		List<Long> ids = new ArrayList<Long>();
		for(FixGc t:tl){
			ids.add(t.getId());
		}
		return ids;
	}
	/**
	设置登录人可见的数据权限
	*/
	public void setQueryPerm(FixGc model,List<Map<String,Object>> queryParams,LoginModel login){
		if(login!=null&&login.getLoginType()==3){
			//初始化sql查询类
			BxLogExample bxLogE = new BxLogExample();
			BxLogExample.Criteria bxLogC = bxLogE.createCriteria();
			//查询是否删除
			bxLogC.andIsDeleteEqualTo(2);
			//查询用户ID
			bxLogC.andUserIdEqualTo(login.getId());
			List<BxLog> bxLogl = bxLogMapper.selectByExample(bxLogE);
			List<Long> bxLogIds = new ArrayList<Long>();
			for(BxLog bxLog:bxLogl){
				bxLogIds.add(bxLog.getId());
			}
			if(bxLogIds.size()>0){
				queryParams.add(CommonUtils.getParamsMap("bxid","9",bxLogIds,"bxid"));
			}else{
				queryParams.add(CommonUtils.getParamsMap("bxid","1",0L,"bxid"));
			}
		}
		if(login!=null&&login.getLoginType()==2){
			model.setFix(login.getId());
		}
	}
	/**
	查询封装好的维修过程列表
	*/
	public List<Map<String,Object>> getDataList(List<FixGc> tl,LoginModel login){
		List<Map<String,Object>> list = new ArrayList<Map<String,Object>>();
		for(FixGc t:tl){
			list.add(getFixGcModel(t,login));
		}
		return list;
	}
	/**
	查询总数量和总页数
	*/
	public Map<String,Long> getCount(FixGc queryModel,List<Map<String,Object>> queryParams,LoginModel login,Integer page,Integer pageSize){
		setQueryPerm(queryModel,queryParams, login);
		//初始化sql查询类
		FixGcExample se = new FixGcExample();
		FixGcExample.Criteria sc = se.createCriteria();
		//集合所有的查询条件
		getAllQueryParams(se,sc,queryModel,queryParams,login);
		long count = (long)fixGcMapper.countByExample(se);
		long totalPage = count % pageSize == 0 ? count / pageSize : (count / pageSize) + 1;
		Map<String,Long> rs = new HashMap<String,Long>();
		rs.put("count",count);//数据总数
		rs.put("totalPage",totalPage);//总页数
		return rs;
	}
	/**
	本表作为其他表的下拉列表，只显示id和名称
	*/
	public List<Map<String,Object>> getSelectList(FixGc fixGc,List<Map<String,Object>> queryParams,LoginModel login){
		setQueryPerm(fixGc,queryParams, login);
		//初始化sql查询类
		FixGcExample te = new FixGcExample();
		FixGcExample.Criteria tc = te.createCriteria();
		List<FixGc> tl = getFixGcList(fixGc,queryParams,login,null,null,null);
		List<Map<String,Object>> list = new ArrayList<Map<String,Object>>();
		for(FixGc t:tl){
			list.add(getIdNameShowById(t.getId()));
		}
		return list;
	}
	/**
	根据ID查询该数据的简易信息
	*/
	public Map<String,Object> getIdNameShowById(Long id){
		//根据主键获取维修过程
		FixGc fixGc = fixGcMapper.selectByPrimaryKey(id);
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("id",id);
		String name = fixGc.getFixBz();
		map.put("name",name);
		return map;
	}
	/**
	*根据参数查询维修过程列表数据
	*/
	@Override
	public List<FixGc> getFixGcList(FixGc queryModel,List<Map<String,Object>> queryParams,LoginModel login,Integer page,Integer pageSize,String rangeType) {
		setQueryPerm(queryModel,queryParams, login);
		//初始化sql查询类
		FixGcExample se = new FixGcExample();
		FixGcExample.Criteria sc = se.createCriteria();
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
		List<FixGc> list = fixGcMapper.selectByExample(se);
		return list;
	}
	//根据参数拼查询语句
	public void getAllQueryParams(FixGcExample se,FixGcExample.Criteria sc,FixGc queryModel,List<Map<String,Object>> queryParams,LoginModel login){
		sc.andAllAttrQuery(queryParams);
		if(queryModel.getId()!=null){
			sc.andIdEqualTo(queryModel.getId());
		}
		if(queryModel.getBxid()!=null){
			//查询报修ID
			sc.andBxidEqualTo(queryModel.getBxid());
		}
		if(queryModel.getFixBz()!=null&&queryModel.getFixBz().equals("")==false){
			//模糊查询维修步骤
			sc.andFixBzLike("%"+queryModel.getFixBz()+"%");
		}
		if(queryModel.getGhpj()!=null&&queryModel.getGhpj().equals("")==false){
			//模糊查询更换配件
			sc.andGhpjLike("%"+queryModel.getGhpj()+"%");
		}
		if(queryModel.getFix()!=null){
			//查询维修员
			sc.andFixEqualTo(queryModel.getFix());
		}
	}
	/**
	封装维修过程为前台展示的数据形式
	*/
	@Override
	public Map<String,Object> getFixGcModel(FixGc model,LoginModel login){
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("fixGc",model);
		if(model.getFix()!=null){
			//根据主键获取维修员
			Fix fix = fixMapper.selectByPrimaryKey(model.getFix());
			map.put("fix",fix);
			if(fix!=null){
				map.put("fixStr",fix.getLoginName());
				map.put("fixImg",fix.getHeadImg());
			}
		}
		if(model.getBxid()!=null){
			//根据主键获取报修记录
			BxLog bxLog = bxLogMapper.selectByPrimaryKey(model.getBxid());
			map.put("bxLog",bxLog);
			if(bxLog!=null){
				map.put("bxidStr",bxLog.getBxNo());
			}
		}
		return map;
	}
	/**
	* 删除数据
	*/
	@Override
	public void delete(Long id) {
		//根据主键获取维修过程
		FixGc preModel = fixGcMapper.selectByPrimaryKey(id);
		fixGcMapper.deleteByPrimaryKey(id);
	}
}
