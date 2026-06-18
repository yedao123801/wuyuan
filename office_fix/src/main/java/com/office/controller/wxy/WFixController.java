package com.office.controller.wxy;
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
@RequestMapping("/wxy/fix")
public class WFixController{
	SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	SimpleDateFormat sdf2 = new SimpleDateFormat("yyyy-MM-dd");
	SimpleDateFormat sdf3 = new SimpleDateFormat("yyyyMMddHHmmss");
	@Autowired
	FixMapper fixMapper;
	@Autowired
	FixService fixService;
	@RequestMapping(value="getRelativeDataList")
	@ResponseBody
	public Map<String,Object> getList(Map<String,Object> rs ,Fix model,HttpServletRequest request){
		LoginModel login = (LoginModel) request.getSession().getAttribute(CommonVal.sessionName);
		Map<String,Object> data = new HashMap<String,Object>();
		rs.put("checkStatusList",DataListUtils.getCheckStatusList());//返回check_status列表
		data.put("checkStatusList",DataListUtils.getCheckStatusList());//返回check_status列表
		return data;
	}
	@RequestMapping(value = "queryDataDetail")
	@ResponseBody
	public Object queryDataDetail(Long id, HttpServletRequest request) {
		//根据主键获取维修员
		Fix fix = fixMapper.selectByPrimaryKey(id);
		return fix;
	}
	/**
	* 查询页面所需要的数据
	*/
	@RequestMapping(value="getInitData")
	@ResponseBody
	public Object getInitData(String loginToken,HttpServletRequest request){
		Map<String,Object> rs = new HashMap<String,Object>();
		LoginModel login = (LoginModel) request.getSession().getAttribute(CommonVal.sessionName);//获取当前登录账号信息
		//根据主键获取维修员
		Fix fix = fixMapper.selectByPrimaryKey(login.getId());
		rs.put("user",fix);
		getList( rs,null,request);//获取数据列表并返回给前台
		return rs;
	}
	/**
	获取维修员详情页数据
	*/
	@RequestMapping("getDetailData")
	@ResponseBody
	public Object getDetailData(HttpServletRequest request) {
		LoginModel login = (LoginModel) request.getSession().getAttribute(CommonVal.sessionName);
		Fix model = new Fix();
		model.setId(login.getId());
		//根据主键获取维修员
		Fix preModel = fixMapper.selectByPrimaryKey(login.getId());
		Map<String,Object> rs = new HashMap<String,Object>();
		rs.put("detail", fixService.getFixModel(preModel,login));
		return rs;
	}
	/**
	修改提交信息接口
	*/
	@RequestMapping("update1_submit")
	@ResponseBody
	public Object update1_submit(Fix model,ModelMap modelMap,HttpServletRequest request){
		LoginModel login = (LoginModel) request.getSession().getAttribute(CommonVal.sessionName);
		Map<String,Object> rs = new HashMap<String,Object>();
		String msg = fixService.update1(model,login);//执行修改操作
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
