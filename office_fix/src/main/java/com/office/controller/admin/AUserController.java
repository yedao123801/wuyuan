package com.office.controller.admin;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Date;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RequestMethod;
import java.io.IOException;
import com.office.dao.*;
import com.office.model.*;
import com.office.service.impl.*;
import com.office.util.*;
import java.text.SimpleDateFormat;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import java.util.ArrayList;
import com.office.controller.LoginModel;
import com.office.service.*;
import org.springframework.web.bind.annotation.CrossOrigin;
@CrossOrigin(origins = "*")
@Controller
@RequestMapping("/admin/user")
public class AUserController{
	SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	SimpleDateFormat sdf2 = new SimpleDateFormat("yyyy-MM-dd");
	SimpleDateFormat sdf3 = new SimpleDateFormat("yyyyMMddHHmmss");
	@Autowired
	AdminMapper adminMapper;
	@Autowired
	DepartMapper departMapper;
	@Autowired
	UserMapper userMapper;
	@Autowired
	DepartService departService;
	@Autowired
	UserService userService;
	@RequestMapping(value="getRelativeDataList")
	@ResponseBody
	public Map<String,Object> getList(Map<String,Object> rs ,User model,HttpServletRequest request){
		LoginModel login = (LoginModel) request.getSession().getAttribute(CommonVal.sessionName);
		Map<String,Object> data = new HashMap<String,Object>();
		List<Map<String,Object>> departList = departService.getSelectList(new Depart(),new ArrayList<Map<String,Object>>(), login);
		rs.put("departList",departList);
		data.put("departList",departList);
		return data;
	}
	/**
	* 根据查询条件分页查询用户数据总数
	*/
	@RequestMapping(value="queryCount")
	@ResponseBody
	public Object queryCount(User model,Integer page,HttpServletRequest request){
		LoginModel login = (LoginModel) request.getSession().getAttribute(CommonVal.sessionName);
		//设置查询参数
		List<Map<String,Object>> queryParams = new ArrayList<Map<String,Object>>();
		Map<String,Long> rs = userService.getCount(model,queryParams,login,page,CommonVal.pageSize);
		return rs;
	}
	@RequestMapping(value = "queryDataDetail")
	@ResponseBody
	public Object queryDataDetail(Long id, HttpServletRequest request) {
		//根据主键获取用户
		User user = userMapper.selectByPrimaryKey(id);
		return user;
	}
	/**
	* 查询页面所需要的数据
	*/
	@RequestMapping(value="getInitData")
	@ResponseBody
	public Object getInitData(String loginToken,HttpServletRequest request){
		Map<String,Object> rs = new HashMap<String,Object>();
		LoginModel login = (LoginModel) request.getSession().getAttribute(CommonVal.sessionName);//获取当前登录账号信息
		//根据主键获取管理员
		Admin admin = adminMapper.selectByPrimaryKey(login.getId());
		rs.put("user",admin);
		getList( rs,null,request);//获取数据列表并返回给前台
		return rs;
	}
	/**
	* 根据查询条件分页查询用户数据，然后返回给前台渲染
	*/
	@RequestMapping(value="queryList")
	@ResponseBody
	public Object toList(User model,Integer page,HttpServletRequest request){
		LoginModel login = (LoginModel) request.getSession().getAttribute(CommonVal.sessionName);
		//设置查询参数
		List<Map<String,Object>> queryParams = new ArrayList<Map<String,Object>>();
		List<User> tlist = userService.getUserList(model,queryParams,login,page,CommonVal.pageSize,"id desc");
		List<Map<String,Object>> list = userService.getDataList(tlist,login);
		Map<String,Object> rs  = new HashMap<String,Object>();
		rs.put("list",list);
		return rs;
	}
	/**
	新增提交信息接口
	*/
	@RequestMapping("add_submit")
	@ResponseBody
	public Object add_submit(User model,ModelMap modelMap,HttpServletRequest request){
		LoginModel login = (LoginModel) request.getSession().getAttribute(CommonVal.sessionName);
		Map<String,Object> rs = new HashMap<String,Object>();
		String msg = userService.add(model,login);//执行添加操作
		if(msg.equals("")==false){
			rs.put("code",0);
			rs.put("msg",msg);
			return rs;
		}
		rs.put("code",1);
		rs.put("msg","新增成功");
		return rs;
	}
	/**
	删除用户接口
	*/
	@RequestMapping("del")
	@ResponseBody
	public Object del(Long id,ModelMap modelMap,HttpServletRequest request) {
		LoginModel login = (LoginModel) request.getSession().getAttribute(CommonVal.sessionName);
		Map<String,Object> rs = new HashMap<String,Object>();
		userService.delete(id);
		rs.put("code",1);
		rs.put("msg","删除成功");
		return rs;
	}
	/**
	修改提交信息接口
	*/
	@RequestMapping("update_submit")
	@ResponseBody
	public Object update_submit(User model,ModelMap modelMap,HttpServletRequest request){
		LoginModel login = (LoginModel) request.getSession().getAttribute(CommonVal.sessionName);
		Map<String,Object> rs = new HashMap<String,Object>();
		String msg = userService.update(model,login);//执行修改操作
		if(msg.equals("")==false){
			rs.put("code",0);
			rs.put("msg",msg);
			return rs;
		}
		rs.put("code",1);
		rs.put("msg","修改成功");
		return rs;
	}
}
