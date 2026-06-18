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
public class EvalLogServiceImpl implements EvalLogService{
	SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	SimpleDateFormat sdf2 = new SimpleDateFormat("yyyy-MM-dd");
	SimpleDateFormat sdf3 = new SimpleDateFormat("yyyyMMddHHmmss");
	SimpleDateFormat sdf4 = new SimpleDateFormat("yyyy-MM");
	@Autowired
	BxLogMapper bxLogMapper;
	@Autowired
	EvalLogMapper evalLogMapper;
	@Autowired
	FixMapper fixMapper;
	@Autowired
	UserMapper userMapper;
	@Autowired
	FixService fixService;
	/**
	新增
	*/
	@Override
	public String add(EvalLog model,LoginModel login){
		BxLog bxLog = bxLogMapper.selectByPrimaryKey(model.getBxid());
		if(model.getEvalStar()==null){
			return "评价星级为必填属性";
		}
		if(model.getEvalContent()==null||model.getEvalContent().equals("")){
			return "评价内容为必填属性";
		}
		model.setUserId(login.getId());//登录id
		model.setEvalTime(sdf1.format(new Date()));//当前时间格式
		//根据主键获取报修记录
		BxLog bxLogT = bxLogMapper.selectByPrimaryKey(model.getBxid());
		if(bxLogT!=null){
			model.setFix(bxLogT.getFix());//赋值bxLogT的维修员
		}
		evalLogMapper.insertSelective(model);//插入数据库
		if(model.getFix()!=null){
			fixService.calcuteFix(model.getFix());
		}
		//查询该报修的所有评价
		//初始化sql查询类
		EvalLogExample ee22 = new EvalLogExample();
		EvalLogExample.Criteria ec22 = ee22.createCriteria();
		//查询维修员
		ec22.andFixEqualTo(bxLog.getId());
		List<EvalLog> e22 = evalLogMapper.selectByExample(ee22);
		Double tevalScore = 0.0;//统计综合评分
		for(EvalLog tmp:e22){
			if(tmp.getEvalStar()!=null){
				tevalScore+=tmp.getEvalStar();
			}
		}
		if(e22.size()>0){
			tevalScore = tevalScore/e22.size();
		}
		//根据主键获取维修员
		Fix fix2 = fixMapper.selectByPrimaryKey(model.getFix());
		fix2.setTevalScore(tevalScore);
		fixMapper.updateByPrimaryKey(fix2);
		bxLog.setBxStatus(7);
		bxLogMapper.updateByPrimaryKey(bxLog);
		return "";
	}
	/**
	修改
	*/
	@Override
	public String update(EvalLog model,LoginModel login){
		//根据主键获取评价记录
		EvalLog preModel = evalLogMapper.selectByPrimaryKey(model.getId());
		if(model.getEvalStar()==null){
			return "评价星级为必填属性";
		}
		if(model.getEvalContent()==null||model.getEvalContent().equals("")){
			return "评价内容为必填属性";
		}
		preModel.setEvalStar(model.getEvalStar());
		preModel.setEvalContent(model.getEvalContent());
		evalLogMapper.updateByPrimaryKey(preModel);
		if(preModel.getFix()!=null){
			fixService.calcuteFix(preModel.getFix());
		}
		return "";
	}
	/**
	获取列表的id集合
	*/
	public List<Long> getIds(EvalLog evalLog,List<Map<String,Object>> queryParams,LoginModel login){
		List<EvalLog> tl = getEvalLogList(evalLog,queryParams,login,null,null,null);
		List<Long> ids = new ArrayList<Long>();
		for(EvalLog t:tl){
			ids.add(t.getId());
		}
		return ids;
	}
	/**
	设置登录人可见的数据权限
	*/
	public void setQueryPerm(EvalLog model,List<Map<String,Object>> queryParams,LoginModel login){
		if(login!=null&&login.getLoginType()==2){
			model.setFix(login.getId());
		}
		if(login!=null&&login.getLoginType()==3){
			model.setUserId(login.getId());
		}
	}
	/**
	查询封装好的评价记录列表
	*/
	public List<Map<String,Object>> getDataList(List<EvalLog> tl,LoginModel login){
		List<Map<String,Object>> list = new ArrayList<Map<String,Object>>();
		for(EvalLog t:tl){
			list.add(getEvalLogModel(t,login));
		}
		return list;
	}
	/**
	查询总数量和总页数
	*/
	public Map<String,Long> getCount(EvalLog queryModel,List<Map<String,Object>> queryParams,LoginModel login,Integer page,Integer pageSize){
		setQueryPerm(queryModel,queryParams, login);
		//初始化sql查询类
		EvalLogExample se = new EvalLogExample();
		EvalLogExample.Criteria sc = se.createCriteria();
		//集合所有的查询条件
		getAllQueryParams(se,sc,queryModel,queryParams,login);
		long count = (long)evalLogMapper.countByExample(se);
		long totalPage = count % pageSize == 0 ? count / pageSize : (count / pageSize) + 1;
		Map<String,Long> rs = new HashMap<String,Long>();
		rs.put("count",count);//数据总数
		rs.put("totalPage",totalPage);//总页数
		return rs;
	}
	/**
	本表作为其他表的下拉列表，只显示id和名称
	*/
	public List<Map<String,Object>> getSelectList(EvalLog evalLog,List<Map<String,Object>> queryParams,LoginModel login){
		setQueryPerm(evalLog,queryParams, login);
		//初始化sql查询类
		EvalLogExample te = new EvalLogExample();
		EvalLogExample.Criteria tc = te.createCriteria();
		List<EvalLog> tl = getEvalLogList(evalLog,queryParams,login,null,null,null);
		List<Map<String,Object>> list = new ArrayList<Map<String,Object>>();
		for(EvalLog t:tl){
			list.add(getIdNameShowById(t.getId()));
		}
		return list;
	}
	/**
	根据ID查询该数据的简易信息
	*/
	public Map<String,Object> getIdNameShowById(Long id){
		//根据主键获取评价记录
		EvalLog evalLog = evalLogMapper.selectByPrimaryKey(id);
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("id",id);
		String name = evalLog.getEvalContent();
		map.put("name",name);
		return map;
	}
	/**
	*根据参数查询评价记录列表数据
	*/
	@Override
	public List<EvalLog> getEvalLogList(EvalLog queryModel,List<Map<String,Object>> queryParams,LoginModel login,Integer page,Integer pageSize,String rangeType) {
		setQueryPerm(queryModel,queryParams, login);
		//初始化sql查询类
		EvalLogExample se = new EvalLogExample();
		EvalLogExample.Criteria sc = se.createCriteria();
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
		List<EvalLog> list = evalLogMapper.selectByExample(se);
		return list;
	}
	//根据参数拼查询语句
	public void getAllQueryParams(EvalLogExample se,EvalLogExample.Criteria sc,EvalLog queryModel,List<Map<String,Object>> queryParams,LoginModel login){
		sc.andAllAttrQuery(queryParams);
		if(queryModel.getId()!=null){
			sc.andIdEqualTo(queryModel.getId());
		}
		if(queryModel.getBxid()!=null){
			//查询报修ID
			sc.andBxidEqualTo(queryModel.getBxid());
		}
		if(queryModel.getEvalContent()!=null&&queryModel.getEvalContent().equals("")==false){
			//模糊查询评价内容
			sc.andEvalContentLike("%"+queryModel.getEvalContent()+"%");
		}
		if(queryModel.getFix()!=null){
			//查询维修员
			sc.andFixEqualTo(queryModel.getFix());
		}
		if(queryModel.getEvalStar()!=null){
			//查询评价星级
			sc.andEvalStarEqualTo(queryModel.getEvalStar());
		}
		if(queryModel.getUserId()!=null){
			//查询用户ID
			sc.andUserIdEqualTo(queryModel.getUserId());
		}
	}
	/**
	封装评价记录为前台展示的数据形式
	*/
	@Override
	public Map<String,Object> getEvalLogModel(EvalLog model,LoginModel login){
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("evalLog",model);
		if(model.getBxid()!=null){
			//根据主键获取报修记录
			BxLog bxLog = bxLogMapper.selectByPrimaryKey(model.getBxid());
			map.put("bxLog",bxLog);
			if(bxLog!=null){
				map.put("bxidStr",bxLog.getBxNo());
			}
		}
		if(model.getFix()!=null){
			//根据主键获取维修员
			Fix fix = fixMapper.selectByPrimaryKey(model.getFix());
			map.put("fix",fix);
			if(fix!=null){
				map.put("fixStr",fix.getLoginName());
				map.put("fixImg",fix.getHeadImg());
			}
		}
		map.put("evalStarStr",DataListUtils.getEvalStarNameById(model.getEvalStar()+""));//解释评价星级字段
		if(model.getUserId()!=null){
			//根据主键获取用户
			User user = userMapper.selectByPrimaryKey(model.getUserId());
			map.put("user",user);
			if(user!=null){
				map.put("userIdStr",user.getLoginName());
				map.put("userIdImg",user.getHeadImg());
			}
		}
		return map;
	}
	/**
	* 删除数据
	*/
	@Override
	public void delete(Long id) {
		//根据主键获取评价记录
		EvalLog preModel = evalLogMapper.selectByPrimaryKey(id);
		evalLogMapper.deleteByPrimaryKey(id);
		if(preModel.getFix()!=null){
			fixService.calcuteFix(preModel.getFix());
		}
	}
}
