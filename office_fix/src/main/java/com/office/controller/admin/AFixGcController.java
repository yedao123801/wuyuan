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
@RequestMapping("/admin/fix_gc")
public class AFixGcController{
	SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	SimpleDateFormat sdf2 = new SimpleDateFormat("yyyy-MM-dd");
	SimpleDateFormat sdf3 = new SimpleDateFormat("yyyyMMddHHmmss");
	@Autowired
	FixGcMapper fixGcMapper;
	@Autowired
	BxLogMapper bxLogMapper;
	@Autowired
	FixMapper fixMapper;
	@Autowired
	AdminMapper adminMapper;
	@Autowired
	FixGcService fixGcService;
	//查询参数
	public List<Map<String,Object>> setQueryParams(String createTime1,String createTime2,FixGc model,LoginModel login){
		List<Map<String,Object>> queryParams = new ArrayList<Map<String,Object>>();
		if(createTime1!=null&&createTime1.equals("")==false){
			queryParams.add(CommonUtils.getParamsMap("create_time","4",createTime1,"createTime"));
		}
		if(createTime2!=null&&createTime2.equals("")==false){
			queryParams.add(CommonUtils.getParamsMap("create_time","7",createTime2,"createTime"));
		}
		return queryParams;
	}
	/**
	* 根据查询条件分页查询维修过程数据总数
	*/
	@RequestMapping(value="queryCount")
	@ResponseBody
	public Object queryCount(FixGc model,Integer page,String createTime1,String createTime2,HttpServletRequest request){
		LoginModel login = (LoginModel) request.getSession().getAttribute(CommonVal.sessionName);
		//设置查询参数
		List<Map<String,Object>> queryParams = setQueryParams(createTime1,createTime2,model,login);
		Map<String,Long> rs = fixGcService.getCount(model,queryParams,login,page,CommonVal.pageSize);
		return rs;
	}
	@RequestMapping(value = "queryDataDetail")
	@ResponseBody
	public Object queryDataDetail(Long id, HttpServletRequest request) {
		//根据主键获取维修过程
		FixGc fixGc = fixGcMapper.selectByPrimaryKey(id);
		return fixGc;
	}
	/**
	* 查询页面所需要的数据
	*/
	@RequestMapping(value="getInitData")
	@ResponseBody
	public Object getInitData(String loginToken,Long bxid,HttpServletRequest request){
		Map<String,Object> rs = new HashMap<String,Object>();
		LoginModel login = (LoginModel) request.getSession().getAttribute(CommonVal.sessionName);//获取当前登录账号信息
		//根据主键获取管理员
		Admin admin = adminMapper.selectByPrimaryKey(login.getId());
		rs.put("user",admin);
		FixGc model = new FixGc();
		model.setBxid(bxid);
		rs.put("data",model);
		return rs;
	}
	/**
	* 根据查询条件分页查询维修过程数据，然后返回给前台渲染
	*/
	@RequestMapping(value="queryList")
	@ResponseBody
	public Object toList(FixGc model,Integer page,String createTime1,String createTime2,HttpServletRequest request){
		LoginModel login = (LoginModel) request.getSession().getAttribute(CommonVal.sessionName);
		//设置查询参数
		List<Map<String,Object>> queryParams = setQueryParams(createTime1,createTime2,model,login);
		List<FixGc> tlist = fixGcService.getFixGcList(model,queryParams,login,page,CommonVal.pageSize,"id desc");
		List<Map<String,Object>> list = fixGcService.getDataList(tlist,login);
		Map<String,Object> rs  = new HashMap<String,Object>();
		rs.put("list",list);
		return rs;
	}
}
