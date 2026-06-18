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
@RequestMapping("/wxy/eval_log")
public class WEvalLogController{
	SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	SimpleDateFormat sdf2 = new SimpleDateFormat("yyyy-MM-dd");
	SimpleDateFormat sdf3 = new SimpleDateFormat("yyyyMMddHHmmss");
	@Autowired
	BxLogMapper bxLogMapper;
	@Autowired
	EvalLogMapper evalLogMapper;
	@Autowired
	FixMapper fixMapper;
	@Autowired
	UserMapper userMapper;
	@Autowired
	BxLogService bxLogService;
	@Autowired
	EvalLogService evalLogService;
	@RequestMapping(value="getRelativeDataList")
	@ResponseBody
	public Map<String,Object> getList(Map<String,Object> rs ,EvalLog model,HttpServletRequest request){
		LoginModel login = (LoginModel) request.getSession().getAttribute(CommonVal.sessionName);
		Map<String,Object> data = new HashMap<String,Object>();
		List<Map<String,Object>> bxLogList = bxLogService.getSelectList(new BxLog(),new ArrayList<Map<String,Object>>(), login);
		rs.put("bxLogList",bxLogList);
		data.put("bxLogList",bxLogList);
		return data;
	}
	//查询参数
	public List<Map<String,Object>> setQueryParams(String evalTime1,String evalTime2,EvalLog model,LoginModel login){
		List<Map<String,Object>> queryParams = new ArrayList<Map<String,Object>>();
		if(evalTime1!=null&&evalTime1.equals("")==false){
			queryParams.add(CommonUtils.getParamsMap("eval_time","4",evalTime1,"evalTime"));
		}
		if(evalTime2!=null&&evalTime2.equals("")==false){
			queryParams.add(CommonUtils.getParamsMap("eval_time","7",evalTime2,"evalTime"));
		}
		return queryParams;
	}
	/**
	* 根据查询条件分页查询评价记录数据总数
	*/
	@RequestMapping(value="queryCount")
	@ResponseBody
	public Object queryCount(EvalLog model,Integer page,String evalTime1,String evalTime2,HttpServletRequest request){
		LoginModel login = (LoginModel) request.getSession().getAttribute(CommonVal.sessionName);
		//设置查询参数
		List<Map<String,Object>> queryParams = setQueryParams(evalTime1,evalTime2,model,login);
		Map<String,Long> rs = evalLogService.getCount(model,queryParams,login,page,CommonVal.pageSize);
		return rs;
	}
	@RequestMapping(value = "queryDataDetail")
	@ResponseBody
	public Object queryDataDetail(Long id, HttpServletRequest request) {
		//根据主键获取评价记录
		EvalLog evalLog = evalLogMapper.selectByPrimaryKey(id);
		return evalLog;
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
	* 根据查询条件分页查询评价记录数据，然后返回给前台渲染
	*/
	@RequestMapping(value="queryList")
	@ResponseBody
	public Object toList(EvalLog model,Integer page,String evalTime1,String evalTime2,HttpServletRequest request){
		LoginModel login = (LoginModel) request.getSession().getAttribute(CommonVal.sessionName);
		//设置查询参数
		List<Map<String,Object>> queryParams = setQueryParams(evalTime1,evalTime2,model,login);
		List<EvalLog> tlist = evalLogService.getEvalLogList(model,queryParams,login,page,CommonVal.pageSize,"id desc");
		List<Map<String,Object>> list = evalLogService.getDataList(tlist,login);
		Map<String,Object> rs  = new HashMap<String,Object>();
		rs.put("list",list);
		return rs;
	}
}
