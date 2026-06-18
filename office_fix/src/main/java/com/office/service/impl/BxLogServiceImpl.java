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
public class BxLogServiceImpl implements BxLogService{
	SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	SimpleDateFormat sdf2 = new SimpleDateFormat("yyyy-MM-dd");
	SimpleDateFormat sdf3 = new SimpleDateFormat("yyyyMMddHHmmss");
	SimpleDateFormat sdf4 = new SimpleDateFormat("yyyy-MM");
	@Autowired
	BxLogMapper bxLogMapper;
	@Autowired
	FixMapper fixMapper;
	@Autowired
	BgqyMapper bgqyMapper;
	@Autowired
	BgServiceMapper bgServiceMapper;
	@Autowired
	UserMapper userMapper;
	/**
	派单
	*/
	@Override
	public String pd(BxLog model,LoginModel login){
		//根据主键获取报修记录
		BxLog preModel = bxLogMapper.selectByPrimaryKey(model.getId());
		if(model.getAdminRemark()==null||model.getAdminRemark().equals("")){
			return "管理员备注为必填属性";
		}
		if(model.getFix()==null){
			return "维修员为必填属性";
		}
		preModel.setBxStatus(2);//报修状态设置为已派单
		preModel.setAdminRemark(model.getAdminRemark());
		preModel.setFix(model.getFix());
		bxLogMapper.updateByPrimaryKey(preModel);
		return "";
	}
	/**
	驳回
	*/
	@Override
	public String bh(BxLog model,LoginModel login){
		//根据主键获取报修记录
		BxLog preModel = bxLogMapper.selectByPrimaryKey(model.getId());
		if(model.getAdminRemark()==null||model.getAdminRemark().equals("")){
			return "管理员备注为必填属性";
		}
		preModel.setBxStatus(6);//报修状态设置为已驳回
		preModel.setAdminRemark(model.getAdminRemark());
		bxLogMapper.updateByPrimaryKey(preModel);
		return "";
	}
	/**
	完成
	*/
	@Override
	public String wc(BxLog model,LoginModel login){
		//根据主键获取报修记录
		BxLog preModel = bxLogMapper.selectByPrimaryKey(model.getId());
		if(model.getFixRemark()==null||model.getFixRemark().equals("")){
			return "维修员备注为必填属性";
		}
		preModel.setBxStatus(4);//报修状态设置为已完成
		preModel.setFixRemark(model.getFixRemark());
		bxLogMapper.updateByPrimaryKey(preModel);
		return "";
	}
	/**
	验收
	*/
	@Override
	public String ys(BxLog model,LoginModel login){
		//根据主键获取报修记录
		BxLog preModel = bxLogMapper.selectByPrimaryKey(model.getId());
		if(model.getYsNote()==null||model.getYsNote().equals("")){
			return "验收说明为必填属性";
		}
		preModel.setBxStatus(5);//报修状态设置为已验收
		preModel.setYsNote(model.getYsNote());
		bxLogMapper.updateByPrimaryKey(preModel);
		return "";
	}
	/**
	获取列表的id集合
	*/
	public List<Long> getIds(BxLog bxLog,List<Map<String,Object>> queryParams,LoginModel login){
		List<BxLog> tl = getBxLogList(bxLog,queryParams,login,null,null,null);
		List<Long> ids = new ArrayList<Long>();
		for(BxLog t:tl){
			ids.add(t.getId());
		}
		return ids;
	}
	/**
	设置登录人可见的数据权限
	*/
	public void setQueryPerm(BxLog model,List<Map<String,Object>> queryParams,LoginModel login){
		if(login!=null&&login.getLoginType()==2){
			model.setFix(login.getId());
		}
		if(login!=null&&login.getLoginType()==3){
			model.setUserId(login.getId());
		}
	}
	/**
	查询封装好的报修记录列表
	*/
	public List<Map<String,Object>> getDataList(List<BxLog> tl,LoginModel login){
		List<Map<String,Object>> list = new ArrayList<Map<String,Object>>();
		for(BxLog t:tl){
			list.add(getBxLogModel(t,login));
		}
		return list;
	}
	/**
	查询总数量和总页数
	*/
	public Map<String,Long> getCount(BxLog queryModel,List<Map<String,Object>> queryParams,LoginModel login,Integer page,Integer pageSize){
		setQueryPerm(queryModel,queryParams, login);
		//初始化sql查询类
		BxLogExample se = new BxLogExample();
		BxLogExample.Criteria sc = se.createCriteria();
		//集合所有的查询条件
		getAllQueryParams(se,sc,queryModel,queryParams,login);
		long count = (long)bxLogMapper.countByExample(se);
		long totalPage = count % pageSize == 0 ? count / pageSize : (count / pageSize) + 1;
		Map<String,Long> rs = new HashMap<String,Long>();
		rs.put("count",count);//数据总数
		rs.put("totalPage",totalPage);//总页数
		return rs;
	}
	/**
	本表作为其他表的下拉列表，只显示id和名称
	*/
	public List<Map<String,Object>> getSelectList(BxLog bxLog,List<Map<String,Object>> queryParams,LoginModel login){
		setQueryPerm(bxLog,queryParams, login);
		//初始化sql查询类
		BxLogExample te = new BxLogExample();
		BxLogExample.Criteria tc = te.createCriteria();
		List<BxLog> tl = getBxLogList(bxLog,queryParams,login,null,null,null);
		List<Map<String,Object>> list = new ArrayList<Map<String,Object>>();
		for(BxLog t:tl){
			list.add(getIdNameShowById(t.getId()));
		}
		return list;
	}
	/**
	根据ID查询该数据的简易信息
	*/
	public Map<String,Object> getIdNameShowById(Long id){
		//根据主键获取报修记录
		BxLog bxLog = bxLogMapper.selectByPrimaryKey(id);
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("id",id);
		String name = bxLog.getBxNo();
		map.put("name",name);
		return map;
	}
	/**
	*根据参数查询报修记录列表数据
	*/
	@Override
	public List<BxLog> getBxLogList(BxLog queryModel,List<Map<String,Object>> queryParams,LoginModel login,Integer page,Integer pageSize,String rangeType) {
		setQueryPerm(queryModel,queryParams, login);
		//初始化sql查询类
		BxLogExample se = new BxLogExample();
		BxLogExample.Criteria sc = se.createCriteria();
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
		List<BxLog> list = bxLogMapper.selectByExample(se);
		return list;
	}
	//根据参数拼查询语句
	public void getAllQueryParams(BxLogExample se,BxLogExample.Criteria sc,BxLog queryModel,List<Map<String,Object>> queryParams,LoginModel login){
		//查询是否删除
		sc.andIsDeleteEqualTo(2);
		sc.andAllAttrQuery(queryParams);
		if(queryModel.getId()!=null){
			sc.andIdEqualTo(queryModel.getId());
		}
		if(queryModel.getBxNo()!=null&&queryModel.getBxNo().equals("")==false){
			//模糊查询报修编号
			sc.andBxNoLike("%"+queryModel.getBxNo()+"%");
		}
		if(queryModel.getBgService()!=null){
			//查询办公设备
			sc.andBgServiceEqualTo(queryModel.getBgService());
		}
		if(queryModel.getBgqy()!=null){
			//查询办公区域
			sc.andBgqyEqualTo(queryModel.getBgqy());
		}
		if(queryModel.getYxcd()!=null){
			//查询优先程度
			sc.andYxcdEqualTo(queryModel.getYxcd());
		}
		if(queryModel.getBxStatus()!=null){
			//查询报修状态
			sc.andBxStatusEqualTo(queryModel.getBxStatus());
		}
		if(queryModel.getAdminRemark()!=null&&queryModel.getAdminRemark().equals("")==false){
			//模糊查询管理员备注
			sc.andAdminRemarkLike("%"+queryModel.getAdminRemark()+"%");
		}
		if(queryModel.getFix()!=null){
			//查询维修员
			sc.andFixEqualTo(queryModel.getFix());
		}
		if(queryModel.getUserId()!=null){
			//查询用户ID
			sc.andUserIdEqualTo(queryModel.getUserId());
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
	封装报修记录为前台展示的数据形式
	*/
	@Override
	public Map<String,Object> getBxLogModel(BxLog model,LoginModel login){
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("bxLog",model);
		if(model.getBgService()!=null){
			//根据主键获取办公设备
			BgService bgService = bgServiceMapper.selectByPrimaryKey(model.getBgService());
			map.put("bgService",bgService);
			if(bgService!=null){
				map.put("bgServiceStr",bgService.getServiceNo()+" "+bgService.getServiceName());
			}
		}
		if(model.getBgqy()!=null){
			//根据主键获取办公区域
			Bgqy bgqy = bgqyMapper.selectByPrimaryKey(model.getBgqy());
			map.put("bgqy",bgqy);
			if(bgqy!=null){
				map.put("bgqyStr",bgqy.getQyName());
			}
		}
		map.put("yxcdStr",DataListUtils.getYxcdNameById(model.getYxcd()+""));//解释优先程度字段
		map.put("gzImgList",CommonUtils.parseImgList(model.getGzImg()));
		map.put("gzVeoList",CommonUtils.parseImgList(model.getGzVeo()));
		map.put("bxStatusStr",DataListUtils.getBxStatusNameById(model.getBxStatus()+""));//解释报修状态字段
		if(model.getFix()!=null){
			//根据主键获取维修员
			Fix fix = fixMapper.selectByPrimaryKey(model.getFix());
			map.put("fix",fix);
			if(fix!=null){
				map.put("fixStr",fix.getLoginName());
				map.put("fixImg",fix.getHeadImg());
			}
		}
		if(model.getUserId()!=null){
			//根据主键获取用户
			User user = userMapper.selectByPrimaryKey(model.getUserId());
			map.put("user",user);
			if(user!=null){
				map.put("userIdStr",user.getLoginName());
				map.put("userIdImg",user.getHeadImg());
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
		//根据主键获取报修记录
		BxLog preModel = bxLogMapper.selectByPrimaryKey(id);
		BxLog bxLog = bxLogMapper.selectByPrimaryKey(id);
		bxLog.setIsDelete(1);
		bxLogMapper.updateByPrimaryKey(bxLog);
	}
}
