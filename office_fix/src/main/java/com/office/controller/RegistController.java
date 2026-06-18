package com.office.controller;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import com.office.controller.LoginModel;
import org.springframework.web.bind.annotation.ResponseBody;
import com.office.util.CommonVal;
import  com.office.model.*;
import com.office.dao.*;
import com.office.util.*;
import com.office.service.*;
import java.text.SimpleDateFormat;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.ArrayList;
import java.util.*;
import org.springframework.web.bind.annotation.CrossOrigin;
@CrossOrigin(origins = "*")
@Controller
@RequestMapping("/regist")
public class RegistController{
	SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	SimpleDateFormat sdf2 = new SimpleDateFormat("yyyy-MM-dd");
	SimpleDateFormat sdf3 = new SimpleDateFormat("yyyyMMddHHmmss");
	@Autowired
	UserMapper userMapper;
	@Autowired
	FixMapper fixMapper;
	@Autowired
	DepartMapper departMapper;
	@Autowired
	DepartService departService;
	/**
	提交维修员注册信息接口
	*/
	@RequestMapping("fixRegistSubmit")
	@ResponseBody
	public Object fixRegistSubmit(String imgCode,String imgCodeToken,String loginName,String password,String name,String celphone,String szType,ModelMap modelMap,HttpServletRequest request,HttpServletResponse response) {
		Map<String,Object> rs = new HashMap<String,Object>();
		HttpSession session = request.getSession();
		Cache codeCache = CacheManage.getCacheInfo(imgCodeToken);
		if(codeCache==null){
			rs.put("code",0);
			rs.put("msg","图片验证码错误");
			return rs;
		}
		String imgCode2 = codeCache.getValue().toString();
		if (!imgCode.toLowerCase().equals(imgCode2.toLowerCase())){//当前验证码存储session的值是否和输入的值一致
			rs.put("code",0);
			rs.put("msg", "图片验证码错误");
			return rs;
		}
		if(loginName==null||loginName.equals("")){
			rs.put("code",0);
			rs.put("msg",   "请填入登录名");
			return rs;
		}
		if(loginName!=null){
			//初始化sql查询类
			FixExample te = new FixExample();
			FixExample.Criteria tc = te.createCriteria();
			//查询是否删除
			tc.andIsDeleteEqualTo(2);
			//查询登录名
			tc.andLoginNameEqualTo(loginName);
			long count = (long)fixMapper.countByExample(te);
			if(count>0){
				rs.put("code",0);
				rs.put("msg",   "该账号已被注册，请用该账号登录");
				return rs;
			}
		}
		if(password==null||password.equals("")){
			rs.put("code",0);
			rs.put("msg",   "请填入密码");
			return rs;
		}
		if(name==null||name.equals("")){
			rs.put("code",0);
			rs.put("msg",   "请填入姓名");
			return rs;
		}
		if(celphone==null||celphone.equals("")){
			rs.put("code",0);
			rs.put("msg",   "请填入联系电话");
			return rs;
		}
		if(celphone!=null&&celphone.trim().equals("")==false){
		Pattern p = Pattern.compile("^[1](([3|5|8][\\d])|([4][4,5,6,7,8,9])|([6][2,5,6,7])|([7][^9])|([9][1,8,9]))[\\d]{8}$");
			Matcher m = p.matcher(celphone);
			if( m.matches()==false){
				;
				rs.put("code",0);
				rs.put("msg",   "请填入正确的联系电话");
				return rs;
			}
		}
		if(szType==null||szType.equals("")){
			rs.put("code",0);
			rs.put("msg",   "请填入擅长类型");
			return rs;
		}
		Fix model = new Fix();
		model.setLoginName(loginName);
		model.setPassword(password);
		model.setName(name);
		model.setCelphone(celphone);
		model.setSzType(szType);
		model.setCheckStatus(1);//默认待审核,
		model.setTevalScore(0.0);//默认0.0
		model.setZcTime(sdf1.format(new Date()));//当前时间格式
		model.setIsDelete(2);//默认否,
		fixMapper.insertSelective(model);//插入数据库
		rs.put("code",1);
		rs.put("msg",  "注册成功，请使用该账号密码登录");
		return rs;
	}
	/**
	提交用户注册信息接口
	*/
	@RequestMapping("userRegistSubmit")
	@ResponseBody
	public Object userRegistSubmit(String imgCode,String imgCodeToken,String loginName,String password,String name,String celphone,Long depart,ModelMap modelMap,HttpServletRequest request,HttpServletResponse response) {
		Map<String,Object> rs = new HashMap<String,Object>();
		HttpSession session = request.getSession();
		Cache codeCache = CacheManage.getCacheInfo(imgCodeToken);
		if(codeCache==null){
			rs.put("code",0);
			rs.put("msg","图片验证码错误");
			return rs;
		}
		String imgCode2 = codeCache.getValue().toString();
		if (!imgCode.toLowerCase().equals(imgCode2.toLowerCase())){//当前验证码存储session的值是否和输入的值一致
			rs.put("code",0);
			rs.put("msg", "图片验证码错误");
			return rs;
		}
		if(loginName==null||loginName.equals("")){
			rs.put("code",0);
			rs.put("msg",   "请填入登录名");
			return rs;
		}
		if(loginName!=null){
			//初始化sql查询类
			UserExample te = new UserExample();
			UserExample.Criteria tc = te.createCriteria();
			//查询是否删除
			tc.andIsDeleteEqualTo(2);
			//查询登录名
			tc.andLoginNameEqualTo(loginName);
			long count = (long)userMapper.countByExample(te);
			if(count>0){
				rs.put("code",0);
				rs.put("msg",   "该账号已被注册，请用该账号登录");
				return rs;
			}
		}
		if(password==null||password.equals("")){
			rs.put("code",0);
			rs.put("msg",   "请填入密码");
			return rs;
		}
		if(name==null||name.equals("")){
			rs.put("code",0);
			rs.put("msg",   "请填入姓名");
			return rs;
		}
		if(celphone==null||celphone.equals("")){
			rs.put("code",0);
			rs.put("msg",   "请填入联系电话");
			return rs;
		}
		if(celphone!=null&&celphone.trim().equals("")==false){
		Pattern p = Pattern.compile("^[1](([3|5|8][\\d])|([4][4,5,6,7,8,9])|([6][2,5,6,7])|([7][^9])|([9][1,8,9]))[\\d]{8}$");
			Matcher m = p.matcher(celphone);
			if( m.matches()==false){
				;
				rs.put("code",0);
				rs.put("msg",   "请填入正确的联系电话");
				return rs;
			}
		}
		if(depart==null){
			rs.put("code",0);
			rs.put("msg",   "请填入部门");
			return rs;
		}
		User model = new User();
		model.setLoginName(loginName);
		model.setPassword(password);
		model.setName(name);
		model.setCelphone(celphone);
		model.setDepart(depart);
		model.setZcTime(sdf1.format(new Date()));//当前时间格式
		model.setIsDelete(2);//默认否,
		userMapper.insertSelective(model);//插入数据库
		rs.put("code",1);
		rs.put("msg",  "注册成功，请使用该账号密码登录");
		return rs;
	}
	@RequestMapping("getListData")
	@ResponseBody
	public Object getListData(HttpServletRequest request,HttpServletResponse response) {
		Map<String,Object> rs = new HashMap<String,Object>();
		//查询部门
		DepartExample departE = new DepartExample();
		DepartExample.Criteria departC = departE.createCriteria();
		//查询是否删除
		departC.andIsDeleteEqualTo(2);
		List<Depart> departList = departMapper.selectByExample(departE);
		List<Map<String,Object>>  departList2 = new ArrayList<Map<String,Object>>();
		for(Depart m:departList){
			Map<String,Object> map = new HashMap<String,Object>();
			map.put("id",m.getId());
			map.put("name",m.getDepartName());
			departList2.add(map);
		}
		rs.put("departList",departList2);
		return rs;
	}
}
