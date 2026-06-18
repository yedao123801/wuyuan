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
@RequestMapping("/wxy/zsk")
public class WZskController{
	SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	SimpleDateFormat sdf2 = new SimpleDateFormat("yyyy-MM-dd");
	SimpleDateFormat sdf3 = new SimpleDateFormat("yyyyMMddHHmmss");
	@Autowired
	ZskMapper zskMapper;
	@Autowired
	FixMapper fixMapper;
	@Autowired
	ZskService zskService;
	/**
	* 根据查询条件分页查询知识库数据总数
	*/
	@RequestMapping(value="queryCount")
	@ResponseBody
	public Object queryCount(Zsk model,Integer page,HttpServletRequest request){
		LoginModel login = (LoginModel) request.getSession().getAttribute(CommonVal.sessionName);
		//设置查询参数
		List<Map<String,Object>> queryParams = new ArrayList<Map<String,Object>>();
		Map<String,Long> rs = zskService.getCount(model,queryParams,login,page,CommonVal.pageSize);
		return rs;
	}
	@RequestMapping(value = "queryDataDetail")
	@ResponseBody
	public Object queryDataDetail(Long id, HttpServletRequest request) {
		//根据主键获取知识库
		Zsk zsk = zskMapper.selectByPrimaryKey(id);
		return zsk;
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
		return rs;
	}
	/**
	* 根据查询条件分页查询知识库数据，然后返回给前台渲染
	*/
	@RequestMapping(value="queryList")
	@ResponseBody
	public Object toList(Zsk model,Integer page,HttpServletRequest request){
		LoginModel login = (LoginModel) request.getSession().getAttribute(CommonVal.sessionName);
		//设置查询参数
		List<Map<String,Object>> queryParams = new ArrayList<Map<String,Object>>();
		List<Zsk> tlist = zskService.getZskList(model,queryParams,login,page,CommonVal.pageSize,"id desc");
		List<Map<String,Object>> list = zskService.getDataList(tlist,login);
		Map<String,Object> rs  = new HashMap<String,Object>();
		rs.put("list",list);
		return rs;
	}
	/**
	查看详情接口
	*/
	@RequestMapping("getDetailData")
	@ResponseBody
	public Object detail2(Long id,ModelMap modelMap,HttpServletRequest request) {
		LoginModel login = (LoginModel) request.getSession().getAttribute(CommonVal.sessionName);
		Map<String,Object> rs = new HashMap<String,Object>();
		Zsk model = new Zsk();
		model.setId(id);
		//根据主键获取知识库
		Zsk preModel = zskMapper.selectByPrimaryKey(id);
		rs.put("detail", zskService.getZskModel(preModel,login));
		rs.put("code", 1);
		return rs;
	}
}
