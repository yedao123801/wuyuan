package com.office.controller;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import com.office.controller.LoginModel;
import com.office.util.CommonVal;
import  com.office.model.*;
import com.office.dao.*;
import com.office.util.*;
import java.text.SimpleDateFormat;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.ArrayList;
import java.util.*;
import org.springframework.web.bind.annotation.CrossOrigin;
@CrossOrigin(origins = "*")
@Controller
@RequestMapping("/login")
public class LoginController{
	SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	SimpleDateFormat sdf2 = new SimpleDateFormat("yyyy-MM-dd");
	SimpleDateFormat sdf3 = new SimpleDateFormat("yyyyMMddHHmmss");
	@Autowired
	FixMapper fixMapper;
	@Autowired
	AdminMapper adminMapper;
	@Autowired
	UserMapper userMapper;
	/**
	退出接口
	*/
	@RequestMapping(value="sys_logout")
	@ResponseBody
	public Object sys_logout(ModelMap modelMap,String token,HttpServletRequest request){
		CacheManage.clearOnly(token);//删除登录token
		request.getSession().removeAttribute(CommonVal.sessionName);//删除登录session
		Map<String,Object> rs = new HashMap<String,Object>();
		rs.put("code",1);
		return rs;
	}
	/**
	提交登录验证信息接口
	*/
	@RequestMapping("sysSubmit")
	@ResponseBody
	public Object sysSubmit(LoginModel user,String imgCode,String imgCodeToken,ModelMap modelMap,HttpServletRequest request,HttpServletResponse response) {
		HttpSession session = request.getSession();
		Map<String,Object> rs = new HashMap<String,Object>();
		//图片验证码验证，从session中查询验证码并校验
		if(imgCode==null||imgCode.trim().equals("")){
			rs.put("code",0);
			rs.put("msg","请输入图片验证码");
			return rs;
		}
		Cache codeCache = CacheManage.getCacheInfo(imgCodeToken);
		if(codeCache==null){
			rs.put("code",0);
			rs.put("msg","图片验证码错误");
			return rs;
		}
		String imgCode2 = codeCache.getValue().toString();
		if (!imgCode.toLowerCase().equals(imgCode2.toLowerCase())){//当前验证码存储session的值是否和输入的值一致
			rs.put("code",0);
			rs.put("msg","图片验证码错误");
			return rs;
		}
		if(user.getLoginType()==null){
			rs.put("code",0);
			rs.put("msg","请选择登录角色");
			return rs;
		}
		if(user.getName()==null||user.getName().equals("")){
			rs.put("code",0);
			rs.put("msg","请输入登录名");
			return rs;
		}
		if(user.getPassword()==null||user.getPassword().equals("")){
			rs.put("code",0);
			rs.put("msg","请输入密码");
			return rs;
		}
		if(user.getLoginType()==1){
			//验证账号密码
			//初始化sql查询类
			AdminExample te = new AdminExample();
			AdminExample.Criteria tc = te.createCriteria();
			//查询登录名
			tc.andLoginNameEqualTo(user.getName());
			//查询密码
			tc.andPasswordEqualTo(user.getPassword());
			List<Admin> tl = adminMapper.selectByExample(te);
			if(tl.size()==0){//从数据库中查询不到账号
				rs.put("code",0);
				rs.put("msg","登录名或密码错误");
				return rs;
			}else{
				return setAdminLoginCache(user,tl.get(0),request);
			}
		}
		if(user.getLoginType()==2){
			//验证账号密码
			//初始化sql查询类
			FixExample te = new FixExample();
			FixExample.Criteria tc = te.createCriteria();
			//查询是否删除
			tc.andIsDeleteEqualTo(2);
			//查询登录名
			tc.andLoginNameEqualTo(user.getName());
			//查询密码
			tc.andPasswordEqualTo(user.getPassword());
			List<Fix> tl = fixMapper.selectByExample(te);
			if(tl.size()==0){//从数据库中查询不到账号
				rs.put("code",0);
				rs.put("msg","登录名或密码错误");
				return rs;
			}else{
				if(tl.get(0).getCheckStatus()==null||tl.get(0).getCheckStatus()!=2){
					rs.put("code",0);
					rs.put("msg","您的账号已被限制，无法登录");
					return rs;
				}
				return setFixLoginCache(user,tl.get(0),request);
			}
		}
		if(user.getLoginType()==3){
			//验证账号密码
			//初始化sql查询类
			UserExample te = new UserExample();
			UserExample.Criteria tc = te.createCriteria();
			//查询是否删除
			tc.andIsDeleteEqualTo(2);
			//查询登录名
			tc.andLoginNameEqualTo(user.getName());
			//查询密码
			tc.andPasswordEqualTo(user.getPassword());
			List<User> tl = userMapper.selectByExample(te);
			if(tl.size()==0){//从数据库中查询不到账号
				rs.put("code",0);
				rs.put("msg","登录名或密码错误");
				return rs;
			}else{
				return setUserLoginCache(user,tl.get(0),request);
			}
		}
		rs.put("code",1);
		rs.put("msg","登录成功");
		return rs;
	}
	public Map<String,Object> setAdminLoginCache(LoginModel user,Admin model,HttpServletRequest request){
		Map<String,Object> rs = new HashMap<String,Object>();
		LoginModel login = new LoginModel();
		login.setId(model.getId());
		login.setLoginType(1);
		login.setName(user.getName());
		String uuid = UUID.randomUUID().toString().replace("-", "").toLowerCase();
		CacheManage.putCache(uuid, new Cache(uuid,login,1000*60*60*24,false));
		rs.put("loginToken",uuid);
		rs.put("loginType",1);
		rs.put("loginId",model.getId());
		rs.put("loginName",user.getName());
		rs.put("roleName","管理员");
		rs.put("code",1);
		rs.put("msg","登录成功");
		return rs;
	}
	public Map<String,Object> setFixLoginCache(LoginModel user,Fix model,HttpServletRequest request){
		Map<String,Object> rs = new HashMap<String,Object>();
		LoginModel login = new LoginModel();
		login.setId(model.getId());
		login.setLoginType(2);
		login.setName(user.getName());
		String uuid = UUID.randomUUID().toString().replace("-", "").toLowerCase();
		CacheManage.putCache(uuid, new Cache(uuid,login,1000*60*60*24,false));
		rs.put("loginToken",uuid);
		rs.put("loginType",2);
		rs.put("loginId",model.getId());
		rs.put("loginName",user.getName());
		rs.put("roleName","维修员");
		rs.put("code",1);
		rs.put("msg","登录成功");
		return rs;
	}
	public Map<String,Object> setUserLoginCache(LoginModel user,User model,HttpServletRequest request){
		Map<String,Object> rs = new HashMap<String,Object>();
		LoginModel login = new LoginModel();
		login.setId(model.getId());
		login.setLoginType(3);
		login.setName(user.getName());
		String uuid = UUID.randomUUID().toString().replace("-", "").toLowerCase();
		CacheManage.putCache(uuid, new Cache(uuid,login,1000*60*60*24,false));
		rs.put("loginToken",uuid);
		rs.put("loginType",3);
		rs.put("loginId",model.getId());
		rs.put("loginName",user.getName());
		rs.put("roleName","用户");
		rs.put("code",1);
		rs.put("msg","登录成功");
		return rs;
	}
	@RequestMapping(value="error")
	@ResponseBody
	public Object error(HttpServletRequest request){
		Map<String,Object> rs = new HashMap<String,Object>();
		rs.put("code", 0);
		rs.put("msg", "尚未登录");
		return rs;
	}
	@RequestMapping(value="checkIsLogin")
	@ResponseBody
	public Object checkIsLogin(String loginToken,String roleName,HttpServletRequest request){
		Map<String,Object> rs = new HashMap<String,Object>();
		Cache loginCache = CacheManage.getCacheInfo(loginToken);
		if(loginCache!=null){
			LoginModel login = (LoginModel)loginCache.getValue();
			rs.put("code", 1);
			rs.put("login", login);
			rs.put("loginToken", loginToken);
			rs.put("roleName", roleName);
			return rs;
		}
		rs.put("code", 0);
		rs.put("msg", "尚未登录");
		return rs;
	}
}
