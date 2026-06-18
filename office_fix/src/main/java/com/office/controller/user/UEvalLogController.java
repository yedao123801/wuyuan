package com.office.controller.user;
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
@RequestMapping("/user/eval_log")
public class UEvalLogController{
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
	EvalLogService evalLogService;
	@RequestMapping(value="getRelativeDataList")
	@ResponseBody
	public Map<String,Object> getList(Map<String,Object> rs ,EvalLog model,HttpServletRequest request){
		LoginModel login = (LoginModel) request.getSession().getAttribute(CommonVal.sessionName);
		Map<String,Object> data = new HashMap<String,Object>();
		rs.put("evalStarList",DataListUtils.getEvalStarList());//返回eval_star列表
		data.put("evalStarList",DataListUtils.getEvalStarList());//返回eval_star列表
		return data;
	}
	//查询参数
	public List<Map<String,Object>> setQueryParams(String bxidFilter,String evalTime1,String evalTime2,EvalLog model,LoginModel login){
		List<Map<String,Object>> queryParams = new ArrayList<Map<String,Object>>();
		if(bxidFilter!=null&&bxidFilter.equals("")==false){
			String [] split4 = bxidFilter.split(",");
			List<Long> ids4 = new ArrayList<Long>();
			for(String str4:split4){
				if(str4.trim().equals("")==false){
					ids4.add(Long.parseLong(str4.trim()));
				}
			}
			if(ids4.size()>0){
				queryParams.add(CommonUtils.getParamsMap("bxid","9",ids4,"bxid"));
			}
		}
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
	public Object queryCount(EvalLog model,Integer page,String bxidFilter,String evalTime1,String evalTime2,HttpServletRequest request){
		LoginModel login = (LoginModel) request.getSession().getAttribute(CommonVal.sessionName);
		//设置查询参数
		List<Map<String,Object>> queryParams = setQueryParams(bxidFilter,evalTime1,evalTime2,model,login);
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
	public Object getInitData(String loginToken,Long bxid,HttpServletRequest request){
		Map<String,Object> rs = new HashMap<String,Object>();
		LoginModel login = (LoginModel) request.getSession().getAttribute(CommonVal.sessionName);//获取当前登录账号信息
		//根据主键获取用户
		User user = userMapper.selectByPrimaryKey(login.getId());
		rs.put("user",user);
		EvalLog model = new EvalLog();
		model.setBxid(bxid);
		rs.put("data",model);
		getList( rs,model,request);//获取数据列表并返回给前台
		return rs;
	}
	/**
	* 根据查询条件分页查询评价记录数据，然后返回给前台渲染
	*/
	@RequestMapping(value="queryList")
	@ResponseBody
	public Object toList(EvalLog model,Integer page,String bxidFilter,String evalTime1,String evalTime2,HttpServletRequest request){
		LoginModel login = (LoginModel) request.getSession().getAttribute(CommonVal.sessionName);
		//设置查询参数
		List<Map<String,Object>> queryParams = setQueryParams(bxidFilter,evalTime1,evalTime2,model,login);
		List<EvalLog> tlist = evalLogService.getEvalLogList(model,queryParams,login,page,CommonVal.pageSize,"id desc");
		List<Map<String,Object>> list = evalLogService.getDataList(tlist,login);
		Map<String,Object> rs  = new HashMap<String,Object>();
		rs.put("list",list);
		return rs;
	}
	/**
	新增提交信息接口
	*/
	@RequestMapping("add_submit")
	@ResponseBody
	public Object add_submit(EvalLog model,ModelMap modelMap,HttpServletRequest request){
		LoginModel login = (LoginModel) request.getSession().getAttribute(CommonVal.sessionName);
		Map<String,Object> rs = new HashMap<String,Object>();
		String msg = evalLogService.add(model,login);//执行添加操作
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
	修改提交信息接口
	*/
	@RequestMapping("update_submit")
	@ResponseBody
	public Object update_submit(EvalLog model,ModelMap modelMap,HttpServletRequest request){
		LoginModel login = (LoginModel) request.getSession().getAttribute(CommonVal.sessionName);
		Map<String,Object> rs = new HashMap<String,Object>();
		String msg = evalLogService.update(model,login);//执行修改操作
		if(msg.equals("")==false){
			rs.put("code",0);
			rs.put("msg",msg);
			return rs;
		}
		rs.put("code",1);
		rs.put("msg","修改成功");
		return rs;
	}
	/**
	删除评价记录接口
	*/
	@RequestMapping("del1")
	@ResponseBody
	public Object del1(Long id,ModelMap modelMap,HttpServletRequest request) {
		LoginModel login = (LoginModel) request.getSession().getAttribute(CommonVal.sessionName);
		Map<String,Object> rs = new HashMap<String,Object>();
		evalLogService.delete(id);
		rs.put("code",1);
		rs.put("msg","删除成功");
		return rs;
	}
	/**
	查看详情接口
	*/
	@RequestMapping("getDetailData")
	@ResponseBody
	public Object detail3(Long id,ModelMap modelMap,HttpServletRequest request) {
		LoginModel login = (LoginModel) request.getSession().getAttribute(CommonVal.sessionName);
		Map<String,Object> rs = new HashMap<String,Object>();
		EvalLog model = new EvalLog();
		model.setId(id);
		//根据主键获取评价记录
		EvalLog preModel = evalLogMapper.selectByPrimaryKey(id);
		rs.put("detail", evalLogService.getEvalLogModel(preModel,login));
		rs.put("code", 1);
		return rs;
	}
}
