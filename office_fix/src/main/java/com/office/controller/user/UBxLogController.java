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
@RequestMapping("/user/bx_log")
public class UBxLogController{
	SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	SimpleDateFormat sdf2 = new SimpleDateFormat("yyyy-MM-dd");
	SimpleDateFormat sdf3 = new SimpleDateFormat("yyyyMMddHHmmss");
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
	@Autowired
	BxLogService bxLogService;
	@Autowired
	BgqyService bgqyService;
	@RequestMapping(value="getRelativeDataList")
	@ResponseBody
	public Map<String,Object> getList(Map<String,Object> rs ,BxLog model,HttpServletRequest request){
		LoginModel login = (LoginModel) request.getSession().getAttribute(CommonVal.sessionName);
		Map<String,Object> data = new HashMap<String,Object>();
		List<Map<String,Object>> bgqyList = bgqyService.getSelectList(new Bgqy(),new ArrayList<Map<String,Object>>(), login);
		rs.put("bgqyList",bgqyList);
		data.put("bgqyList",bgqyList);
		rs.put("yxcdList",DataListUtils.getYxcdList());//返回yxcd列表
		data.put("yxcdList",DataListUtils.getYxcdList());//返回yxcd列表
		rs.put("bxStatusList",DataListUtils.getBxStatusList());//返回bx_status列表
		data.put("bxStatusList",DataListUtils.getBxStatusList());//返回bx_status列表
		return data;
	}
	//查询参数
	public List<Map<String,Object>> setQueryParams(String bgServiceFilter,String bgqyFilter,String yxcdFilter,String bxStatusFilter,BxLog model,LoginModel login){
		List<Map<String,Object>> queryParams = new ArrayList<Map<String,Object>>();
		if(bgServiceFilter!=null&&bgServiceFilter.equals("")==false){
			String [] split4 = bgServiceFilter.split(",");
			List<Long> ids4 = new ArrayList<Long>();
			for(String str4:split4){
				if(str4.trim().equals("")==false){
					ids4.add(Long.parseLong(str4.trim()));
				}
			}
			if(ids4.size()>0){
				queryParams.add(CommonUtils.getParamsMap("bg_service","9",ids4,"bgService"));
			}
		}
		if(bgqyFilter!=null&&bgqyFilter.equals("")==false){
			String [] split4 = bgqyFilter.split(",");
			List<Long> ids4 = new ArrayList<Long>();
			for(String str4:split4){
				if(str4.trim().equals("")==false){
					ids4.add(Long.parseLong(str4.trim()));
				}
			}
			if(ids4.size()>0){
				queryParams.add(CommonUtils.getParamsMap("bgqy","9",ids4,"bgqy"));
			}
		}
		if(yxcdFilter!=null&&yxcdFilter.equals("")==false){
			String [] split4 = yxcdFilter.split(",");
			List<Integer> ids4 = new ArrayList<Integer>();
			for(String str4:split4){
				if(str4.trim().equals("")==false){
					ids4.add(Integer.parseInt(str4.trim()));
				}
			}
			if(ids4.size()>0){
				queryParams.add(CommonUtils.getParamsMap("yxcd","9",ids4,"yxcd"));
			}
		}
		if(bxStatusFilter!=null&&bxStatusFilter.equals("")==false){
			String [] split4 = bxStatusFilter.split(",");
			List<Integer> ids4 = new ArrayList<Integer>();
			for(String str4:split4){
				if(str4.trim().equals("")==false){
					ids4.add(Integer.parseInt(str4.trim()));
				}
			}
			if(ids4.size()>0){
				queryParams.add(CommonUtils.getParamsMap("bx_status","9",ids4,"bxStatus"));
			}
		}
		return queryParams;
	}
	/**
	* 根据查询条件分页查询报修记录数据总数
	*/
	@RequestMapping(value="queryCount")
	@ResponseBody
	public Object queryCount(BxLog model,Integer page,String bgServiceFilter,String bgqyFilter,String yxcdFilter,String bxStatusFilter,HttpServletRequest request){
		LoginModel login = (LoginModel) request.getSession().getAttribute(CommonVal.sessionName);
		//设置查询参数
		List<Map<String,Object>> queryParams = setQueryParams(bgServiceFilter,bgqyFilter,yxcdFilter,bxStatusFilter,model,login);
		Map<String,Long> rs = bxLogService.getCount(model,queryParams,login,page,CommonVal.pageSize);
		return rs;
	}
	@RequestMapping(value = "queryDataDetail")
	@ResponseBody
	public Object queryDataDetail(Long id, HttpServletRequest request) {
		//根据主键获取报修记录
		BxLog bxLog = bxLogMapper.selectByPrimaryKey(id);
		return bxLog;
	}
	/**
	* 查询页面所需要的数据
	*/
	@RequestMapping(value="getInitData")
	@ResponseBody
	public Object getInitData(String loginToken,HttpServletRequest request){
		Map<String,Object> rs = new HashMap<String,Object>();
		LoginModel login = (LoginModel) request.getSession().getAttribute(CommonVal.sessionName);//获取当前登录账号信息
		//根据主键获取用户
		User user = userMapper.selectByPrimaryKey(login.getId());
		rs.put("user",user);
		getList( rs,null,request);//获取数据列表并返回给前台
		return rs;
	}
	/**
	* 根据查询条件分页查询报修记录数据，然后返回给前台渲染
	*/
	@RequestMapping(value="queryList")
	@ResponseBody
	public Object toList(BxLog model,Integer page,String bgServiceFilter,String bgqyFilter,String yxcdFilter,String bxStatusFilter,HttpServletRequest request){
		LoginModel login = (LoginModel) request.getSession().getAttribute(CommonVal.sessionName);
		//设置查询参数
		List<Map<String,Object>> queryParams = setQueryParams(bgServiceFilter,bgqyFilter,yxcdFilter,bxStatusFilter,model,login);
		List<BxLog> tlist = bxLogService.getBxLogList(model,queryParams,login,page,CommonVal.pageSize,"id desc");
		List<Map<String,Object>> list = bxLogService.getDataList(tlist,login);
		Map<String,Object> rs  = new HashMap<String,Object>();
		rs.put("list",list);
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
		BxLog model = new BxLog();
		model.setId(id);
		//根据主键获取报修记录
		BxLog preModel = bxLogMapper.selectByPrimaryKey(id);
		rs.put("detail", bxLogService.getBxLogModel(preModel,login));
		rs.put("code", 1);
		return rs;
	}
	/**
	删除报修记录接口
	*/
	@RequestMapping("del1")
	@ResponseBody
	public Object del1(Long id,ModelMap modelMap,HttpServletRequest request) {
		LoginModel login = (LoginModel) request.getSession().getAttribute(CommonVal.sessionName);
		Map<String,Object> rs = new HashMap<String,Object>();
		bxLogService.delete(id);
		rs.put("code",1);
		rs.put("msg","删除成功");
		return rs;
	}
	/**
	验收提交信息接口
	*/
	@RequestMapping("ys_submit")
	@ResponseBody
	public Object ys_submit(BxLog model,ModelMap modelMap,HttpServletRequest request){
		LoginModel login = (LoginModel) request.getSession().getAttribute(CommonVal.sessionName);
		Map<String,Object> rs = new HashMap<String,Object>();
		String msg = bxLogService.ys(model,login);//执行修改操作
		if(msg.equals("")==false){
			rs.put("code",0);
			rs.put("msg",msg);
			return rs;
		}
		rs.put("code",1);
		rs.put("msg","验收成功");
		return rs;
	}
}
