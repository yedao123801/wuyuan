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
public class UserServiceImpl implements UserService{
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
	public String add(User model,LoginModel login){
		if(model.getLoginName()==null||model.getLoginName().equals("")){
			return "登录名为必填属性";
		}
		//检查是否同名
		if(model.getLoginName()!=null){
			User queryModel = new User();
			List<Map<String,Object>> queryParams = new ArrayList<Map<String,Object>>();
			setQueryPerm(queryModel,queryParams, login);
			//初始化sql查询类
			UserExample te = new UserExample();
			UserExample.Criteria tc = te.createCriteria();
			//集合所有的查询条件
			getAllQueryParams(te,tc,queryModel,queryParams,login);
			//查询登录名
			tc.andLoginNameEqualTo(model.getLoginName());
			long count = (long)userMapper.countByExample(te);
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
		if(model.getDepart()==null){
			return "部门为必填属性";
		}
		model.setZcTime(sdf1.format(new Date()));//当前时间格式
		model.setIsDelete(2);//默认否,
		userMapper.insertSelective(model);//插入数据库
		return "";
	}
	/**
	修改
	*/
	@Override
	public String update(User model,LoginModel login){
		//根据主键获取用户
		User preModel = userMapper.selectByPrimaryKey(model.getId());
		if(model.getLoginName()==null||model.getLoginName().equals("")){
			return "登录名为必填属性";
		}
		//检查是否同名
		if(model.getLoginName()!=null){
			User queryModel = new User();
			List<Map<String,Object>> queryParams = new ArrayList<Map<String,Object>>();
			setQueryPerm(queryModel,queryParams, login);
			//初始化sql查询类
			UserExample te = new UserExample();
			UserExample.Criteria tc = te.createCriteria();
			//集合所有的查询条件
			getAllQueryParams(te,tc,queryModel,queryParams,login);
			tc.andIdNotEqualTo(model.getId());
			//查询登录名
			tc.andLoginNameEqualTo(model.getLoginName());
			long count = (long)userMapper.countByExample(te);
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
		if(model.getDepart()==null){
			return "部门为必填属性";
		}
		preModel.setLoginName(model.getLoginName());
		preModel.setName(model.getName());
		preModel.setCelphone(model.getCelphone());
		preModel.setHeadImg(model.getHeadImg());
		preModel.setDepart(model.getDepart());
		userMapper.updateByPrimaryKey(preModel);
		return "";
	}
	/**
	修改
	*/
	@Override
	public String update1(User model,LoginModel login){
		//根据主键获取用户
		User preModel = userMapper.selectByPrimaryKey(model.getId());
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
		if(model.getDepart()==null){
			return "部门为必填属性";
		}
		preModel.setName(model.getName());
		preModel.setCelphone(model.getCelphone());
		preModel.setHeadImg(model.getHeadImg());
		preModel.setDepart(model.getDepart());
		userMapper.updateByPrimaryKey(preModel);
		return "";
	}
	/**
	获取列表的id集合
	*/
	public List<Long> getIds(User user,List<Map<String,Object>> queryParams,LoginModel login){
		List<User> tl = getUserList(user,queryParams,login,null,null,null);
		List<Long> ids = new ArrayList<Long>();
		for(User t:tl){
			ids.add(t.getId());
		}
		return ids;
	}
	/**
	设置登录人可见的数据权限
	*/
	public void setQueryPerm(User model,List<Map<String,Object>> queryParams,LoginModel login){
		if(login!=null&&login.getLoginType()==3){
			//根据主键获取用户
			User user = userMapper.selectByPrimaryKey(login.getId());
			model.setDepart(user.getDepart());
		}
		if(login!=null&&login.getLoginType()==3){
			model.setId(login.getId());
		}
	}
	/**
	查询封装好的用户列表
	*/
	public List<Map<String,Object>> getDataList(List<User> tl,LoginModel login){
		List<Map<String,Object>> list = new ArrayList<Map<String,Object>>();
		for(User t:tl){
			list.add(getUserModel(t,login));
		}
		return list;
	}
	/**
	查询总数量和总页数
	*/
	public Map<String,Long> getCount(User queryModel,List<Map<String,Object>> queryParams,LoginModel login,Integer page,Integer pageSize){
		setQueryPerm(queryModel,queryParams, login);
		//初始化sql查询类
		UserExample se = new UserExample();
		UserExample.Criteria sc = se.createCriteria();
		//集合所有的查询条件
		getAllQueryParams(se,sc,queryModel,queryParams,login);
		long count = (long)userMapper.countByExample(se);
		long totalPage = count % pageSize == 0 ? count / pageSize : (count / pageSize) + 1;
		Map<String,Long> rs = new HashMap<String,Long>();
		rs.put("count",count);//数据总数
		rs.put("totalPage",totalPage);//总页数
		return rs;
	}
	/**
	本表作为其他表的下拉列表，只显示id和名称
	*/
	public List<Map<String,Object>> getSelectList(User user,List<Map<String,Object>> queryParams,LoginModel login){
		setQueryPerm(user,queryParams, login);
		//初始化sql查询类
		UserExample te = new UserExample();
		UserExample.Criteria tc = te.createCriteria();
		List<User> tl = getUserList(user,queryParams,login,null,null,null);
		List<Map<String,Object>> list = new ArrayList<Map<String,Object>>();
		for(User t:tl){
			list.add(getIdNameShowById(t.getId()));
		}
		return list;
	}
	/**
	根据ID查询该数据的简易信息
	*/
	public Map<String,Object> getIdNameShowById(Long id){
		//根据主键获取用户
		User user = userMapper.selectByPrimaryKey(id);
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("id",id);
		String name = user.getLoginName();
		map.put("name",name);
		map.put("img",user.getHeadImg());
		return map;
	}
	/**
	*根据参数查询用户列表数据
	*/
	@Override
	public List<User> getUserList(User queryModel,List<Map<String,Object>> queryParams,LoginModel login,Integer page,Integer pageSize,String rangeType) {
		setQueryPerm(queryModel,queryParams, login);
		//初始化sql查询类
		UserExample se = new UserExample();
		UserExample.Criteria sc = se.createCriteria();
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
		List<User> list = userMapper.selectByExample(se);
		return list;
	}
	//根据参数拼查询语句
	public void getAllQueryParams(UserExample se,UserExample.Criteria sc,User queryModel,List<Map<String,Object>> queryParams,LoginModel login){
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
		if(queryModel.getDepart()!=null){
			//查询部门
			sc.andDepartEqualTo(queryModel.getDepart());
		}
		if(queryModel.getIsDelete()!=null){
			//查询是否删除
			sc.andIsDeleteEqualTo(queryModel.getIsDelete());
		}
	}
	/**
	封装用户为前台展示的数据形式
	*/
	@Override
	public Map<String,Object> getUserModel(User model,LoginModel login){
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("user",model);
		map.put("headImgList",CommonUtils.parseImgList(model.getHeadImg()));
		if(model.getDepart()!=null){
			//根据主键获取部门
			Depart depart = departMapper.selectByPrimaryKey(model.getDepart());
			map.put("depart",depart);
			if(depart!=null){
				map.put("departStr",depart.getDepartName());
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
		//根据主键获取用户
		User preModel = userMapper.selectByPrimaryKey(id);
		User user = userMapper.selectByPrimaryKey(id);
		user.setIsDelete(1);
		userMapper.updateByPrimaryKey(user);
	}
}
