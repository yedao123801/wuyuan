package com.office.controller;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.bind.annotation.RequestParam;
import java.io.IOException;
import com.office.dao.*;
import com.office.model.*;
import com.office.service.*;
import com.office.controller.*;
import com.office.util.*;
import java.util.ArrayList;
import java.text.SimpleDateFormat;
import java.util.Date;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.CrossOrigin;
import java.text.DecimalFormat;
import java.util.Collections;
import java.util.Comparator;
@CrossOrigin(origins = "*")
@Controller
@RequestMapping("/home")
public class HomeGatherController{
	SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	SimpleDateFormat sdf2 = new SimpleDateFormat("yyyy-MM-dd");
	SimpleDateFormat sdf3 = new SimpleDateFormat("yyyyMMddHHmmss");
	DecimalFormat df = new DecimalFormat("#.00");
	@Autowired
	ZskMapper zskMapper;
	@Autowired
	FixGcMapper fixGcMapper;
	@Autowired
	BxLogMapper bxLogMapper;
	@Autowired
	EvalLogMapper evalLogMapper;
	@Autowired
	FixMapper fixMapper;
	@Autowired
	UserMapper userMapper;
	@Autowired
	BgqyService bgqyService;
	@Autowired
	BgServiceService bgServiceService;
	@Autowired
	DepartService departService;
	@RequestMapping(value="getHomeMsg")
	@ResponseBody
	public Object getHomeMsg(HttpServletRequest request){
		Map<String,Object> rs = new HashMap<String,Object>();
		LoginModel login = (LoginModel) request.getSession().getAttribute(CommonVal.sessionName);//获取当前登录账号信息
		rs.put("checkStatusList",DataListUtils.getCheckStatusList());
		rs.put("bgqyList",bgqyService.getSelectList(new Bgqy(),new ArrayList(),login));
		rs.put("departList",departService.getSelectList(new Depart(),new ArrayList(),login));
		List<Map<String,Object>> dateList = new ArrayList<Map<String,Object>>();
		Map<String,Object> date1 = new HashMap<String,Object>();
		date1.put("id",1);
		date1.put("name","按日");
		dateList.add(date1);
		Map<String,Object> date2 = new HashMap<String,Object>();
		date2.put("id",2);
		date2.put("name","按周");
		dateList.add(date2);
		Map<String,Object> date3 = new HashMap<String,Object>();
		date3.put("id",3);
		date3.put("name","按月");
		dateList.add(date3);
		Map<String,Object> date4 = new HashMap<String,Object>();
		date4.put("id",4);
		date4.put("name","季度");
		dateList.add(date4);
		Map<String,Object> date5 = new HashMap<String,Object>();
		date5.put("id",5);
		date5.put("name","按年");
		dateList.add(date5);
		rs.put("dateList",dateList);
		rs.put("wxyzcs",getHomeWxyzcs(request));
		rs.put("yhzcs",getHomeYhzcs(request));
		rs.put("bxzs",getHomeBxzs(request));
		rs.put("wxgczs",getHomeWxgczs(request));
		rs.put("zskzs",getHomeZskzs(request));
		return rs;
	}
	/**
	查询维修员注册数
	**/
	public Map<String,Object> getHomeWxyzcs(HttpServletRequest request){
		Map<String,Object> rs = new HashMap<String,Object>();
		LoginModel login = (LoginModel) request.getSession().getAttribute(CommonVal.sessionName);//获取当前登录账号信息
		if(login==null){
			rs.put("code",0);
			rs.put("msg","尚未登录");
			return rs;
		}
		rs.put("login",login);
		String today = sdf2.format(new Date());
		//查询维修员注册数
		//初始化sql查询类
		FixExample qe = new FixExample();
		FixExample.Criteria qc = qe.createCriteria();
		//查询是否删除
		qc.andIsDeleteEqualTo(2);
		List<Fix> list = fixMapper.selectByExample(qe);
		rs.put("data",list.size());
		rs.put("code",1);
		rs.put("msg","请求成功");
		return rs;
	}
	/**
	查询维修员注册统计
	**/
	@RequestMapping(value="getHomeWxyzctj")
	@ResponseBody
	public Map<String,Object> getHomeWxyzctj(Integer checkStatusFilter,Integer dateRange,HttpServletRequest request){
		Map<String,Object> rs = new HashMap<String,Object>();
		LoginModel login = (LoginModel) request.getSession().getAttribute(CommonVal.sessionName);//获取当前登录账号信息
		if(login==null){
			rs.put("code",0);
			rs.put("msg","尚未登录");
			return rs;
		}
		rs.put("login",login);
		String today = sdf2.format(new Date());
		//查询维修员注册统计
		//初始化sql查询类
		FixExample qe = new FixExample();
		FixExample.Criteria qc = qe.createCriteria();
		//查询是否删除
		qc.andIsDeleteEqualTo(2);
		if(checkStatusFilter!=null){
			//查询审核状态
			qc.andCheckStatusEqualTo(checkStatusFilter);
		}
		if(dateRange==null){
			dateRange=1;
		}
		//获取开始日期和结束日期的查询范围
		Map<String,String> dateMap = DateRangeStatisticsUtil.getDateRange(dateRange);
		//日期大于等于dateMap.get("startDate")
		qc.andZcTimeGreaterThanOrEqualTo(dateMap.get("startDate"));
		//日期小于dateMap.get("endDate")
		qc.andZcTimeLessThan(dateMap.get("endDate"));
		List<Fix> list = fixMapper.selectByExample(qe);
		//根据日期查询类型获取日期列表
		List<Map<String,Object>> dlist = DateRangeStatisticsUtil.getDateList(dateRange);
		for(Map<String,Object> date:dlist){
			String startDate1 = date.get("startDate").toString();
			String endDate1 = date.get("endDate").toString();
			Integer  dataNum  = 0;//维修员量
			for(Fix d:list){
				if(d.getZcTime()==null&&d.getZcTime().equals("")==false){
					continue;
				}
				if(d.getZcTime().split(" ")[0].compareTo(startDate1)>=0&&d.getZcTime().split(" ")[0].compareTo(endDate1)<=0){
					dataNum++;
				}
			}
			//y坐标显示数据
			List<Map<String,Object>> dataShow = new ArrayList<Map<String,Object>>();
			Map<String,Object> dataNumMap = new HashMap<String,Object>();
			dataNumMap.put("yZhName","维修员量");
			dataNumMap.put("value",dataNum);
			dataShow.add(dataNumMap);
			date.put("dataShow",dataShow);
		}
		rs.put("data",DateRangeStatisticsUtil.convertToMultiDimensionChartData(dlist));
		rs.put("code",1);
		rs.put("msg","请求成功");
		return rs;
	}
	/**
	查询用户注册数
	**/
	public Map<String,Object> getHomeYhzcs(HttpServletRequest request){
		Map<String,Object> rs = new HashMap<String,Object>();
		LoginModel login = (LoginModel) request.getSession().getAttribute(CommonVal.sessionName);//获取当前登录账号信息
		if(login==null){
			rs.put("code",0);
			rs.put("msg","尚未登录");
			return rs;
		}
		rs.put("login",login);
		String today = sdf2.format(new Date());
		//查询用户注册数
		//初始化sql查询类
		UserExample qe = new UserExample();
		UserExample.Criteria qc = qe.createCriteria();
		//查询是否删除
		qc.andIsDeleteEqualTo(2);
		List<User> list = userMapper.selectByExample(qe);
		rs.put("data",list.size());
		rs.put("code",1);
		rs.put("msg","请求成功");
		return rs;
	}
	/**
	查询用户注册统计
	**/
	@RequestMapping(value="getHomeYhzctj")
	@ResponseBody
	public Map<String,Object> getHomeYhzctj(Long departFilter,Integer dateRange,HttpServletRequest request){
		Map<String,Object> rs = new HashMap<String,Object>();
		LoginModel login = (LoginModel) request.getSession().getAttribute(CommonVal.sessionName);//获取当前登录账号信息
		if(login==null){
			rs.put("code",0);
			rs.put("msg","尚未登录");
			return rs;
		}
		rs.put("login",login);
		String today = sdf2.format(new Date());
		//查询用户注册统计
		//初始化sql查询类
		UserExample qe = new UserExample();
		UserExample.Criteria qc = qe.createCriteria();
		//查询是否删除
		qc.andIsDeleteEqualTo(2);
		if(departFilter!=null){
			//查询部门
			qc.andDepartEqualTo(departFilter);
		}
		if(dateRange==null){
			dateRange=1;
		}
		//获取开始日期和结束日期的查询范围
		Map<String,String> dateMap = DateRangeStatisticsUtil.getDateRange(dateRange);
		//日期大于等于dateMap.get("startDate")
		qc.andZcTimeGreaterThanOrEqualTo(dateMap.get("startDate"));
		//日期小于dateMap.get("endDate")
		qc.andZcTimeLessThan(dateMap.get("endDate"));
		List<User> list = userMapper.selectByExample(qe);
		//根据日期查询类型获取日期列表
		List<Map<String,Object>> dlist = DateRangeStatisticsUtil.getDateList(dateRange);
		for(Map<String,Object> date:dlist){
			String startDate1 = date.get("startDate").toString();
			String endDate1 = date.get("endDate").toString();
			Integer  dataNum  = 0;//用户量
			for(User d:list){
				if(d.getZcTime()==null&&d.getZcTime().equals("")==false){
					continue;
				}
				if(d.getZcTime().split(" ")[0].compareTo(startDate1)>=0&&d.getZcTime().split(" ")[0].compareTo(endDate1)<=0){
					dataNum++;
				}
			}
			//y坐标显示数据
			List<Map<String,Object>> dataShow = new ArrayList<Map<String,Object>>();
			Map<String,Object> dataNumMap = new HashMap<String,Object>();
			dataNumMap.put("yZhName","用户量");
			dataNumMap.put("value",dataNum);
			dataShow.add(dataNumMap);
			date.put("dataShow",dataShow);
		}
		rs.put("data",DateRangeStatisticsUtil.convertToMultiDimensionChartData(dlist));
		rs.put("code",1);
		rs.put("msg","请求成功");
		return rs;
	}
	/**
	查询满意度统计
	**/
	@RequestMapping(value="getHomeMydtj")
	@ResponseBody
	public Map<String,Object> getHomeMydtj(HttpServletRequest request){
		Map<String,Object> rs = new HashMap<String,Object>();
		LoginModel login = (LoginModel) request.getSession().getAttribute(CommonVal.sessionName);//获取当前登录账号信息
		if(login==null){
			rs.put("code",0);
			rs.put("msg","尚未登录");
			return rs;
		}
		rs.put("login",login);
		String today = sdf2.format(new Date());
		//查询满意度统计
		//初始化sql查询类
		EvalLogExample qe = new EvalLogExample();
		EvalLogExample.Criteria qc = qe.createCriteria();
		List<EvalLog> list = evalLogMapper.selectByExample(qe);
		List<Map<String,Object>> dlist= DataListUtils.getEvalStarList();
		for(Map<String,Object> map:dlist){
			Integer  dataNum  = 0;
			Integer  evalStar  = 0;
			for(EvalLog d:list){
				if(map.get("id").toString().equals(d.getEvalStar()+"")){
					dataNum++;
					if(d.getEvalStar()!=null){
						evalStar+=d.getEvalStar();
					}else{
						continue;
					}
				}
			}
			//y坐标显示数据
			List<Map<String,Object>> dataShow = new ArrayList<Map<String,Object>>();
			Map<String,Object> dataNumMap = new HashMap<String,Object>();
			dataNumMap.put("yZhName","评价量");
			dataNumMap.put("value",dataNum);
			dataShow.add(dataNumMap);
			Map<String,Object> evalStarMap = new HashMap<String,Object>();
			evalStarMap.put("yZhName","评价星级");
			evalStarMap.put("value",evalStar);
			dataShow.add(evalStarMap);
			map.put("dataShow",dataShow);
		}
		rs.put("data",DateRangeStatisticsUtil.convertToMultiDimensionChartData(dlist));
		rs.put("code",1);
		rs.put("msg","请求成功");
		return rs;
	}
	/**
	查询报修统计
	**/
	@RequestMapping(value="getHomeBxtj")
	@ResponseBody
	public Map<String,Object> getHomeBxtj(Long bgqyFilter,HttpServletRequest request){
		Map<String,Object> rs = new HashMap<String,Object>();
		LoginModel login = (LoginModel) request.getSession().getAttribute(CommonVal.sessionName);//获取当前登录账号信息
		if(login==null){
			rs.put("code",0);
			rs.put("msg","尚未登录");
			return rs;
		}
		rs.put("login",login);
		String today = sdf2.format(new Date());
		//查询报修统计
		//初始化sql查询类
		BxLogExample qe = new BxLogExample();
		BxLogExample.Criteria qc = qe.createCriteria();
		//查询是否删除
		qc.andIsDeleteEqualTo(2);
		if(bgqyFilter!=null){
			//查询办公区域
			qc.andBgqyEqualTo(bgqyFilter);
		}
		List<BxLog> list = bxLogMapper.selectByExample(qe);
		List<Map<String,Object>> dlist = bgServiceService.getSelectList(new BgService(),new ArrayList(),login);
		for(Map<String,Object> map:dlist){
			Integer  dataNum  = 0;
			for(BxLog d:list){
				if(map.get("id").toString().equals(d.getBgService()+"")){
					dataNum++;
				}
			}
			//y坐标显示数据
			List<Map<String,Object>> dataShow = new ArrayList<Map<String,Object>>();
			Map<String,Object> dataNumMap = new HashMap<String,Object>();
			dataNumMap.put("yZhName","报修量");
			dataNumMap.put("value",dataNum);
			dataShow.add(dataNumMap);
			map.put("dataShow",dataShow);
		}
		rs.put("data",DateRangeStatisticsUtil.convertToMultiDimensionChartData(dlist));
		rs.put("code",1);
		rs.put("msg","请求成功");
		return rs;
	}
	/**
	查询报修总数
	**/
	public Map<String,Object> getHomeBxzs(HttpServletRequest request){
		Map<String,Object> rs = new HashMap<String,Object>();
		LoginModel login = (LoginModel) request.getSession().getAttribute(CommonVal.sessionName);//获取当前登录账号信息
		if(login==null){
			rs.put("code",0);
			rs.put("msg","尚未登录");
			return rs;
		}
		rs.put("login",login);
		String today = sdf2.format(new Date());
		//查询报修总数
		//初始化sql查询类
		BxLogExample qe = new BxLogExample();
		BxLogExample.Criteria qc = qe.createCriteria();
		//查询是否删除
		qc.andIsDeleteEqualTo(2);
		List<BxLog> list = bxLogMapper.selectByExample(qe);
		rs.put("data",list.size());
		rs.put("code",1);
		rs.put("msg","请求成功");
		return rs;
	}
	/**
	查询维修过程统计
	**/
	@RequestMapping(value="getHomeWxgctj")
	@ResponseBody
	public Map<String,Object> getHomeWxgctj(Integer dateRange,HttpServletRequest request){
		Map<String,Object> rs = new HashMap<String,Object>();
		LoginModel login = (LoginModel) request.getSession().getAttribute(CommonVal.sessionName);//获取当前登录账号信息
		if(login==null){
			rs.put("code",0);
			rs.put("msg","尚未登录");
			return rs;
		}
		rs.put("login",login);
		String today = sdf2.format(new Date());
		//查询维修过程统计
		//初始化sql查询类
		FixGcExample qe = new FixGcExample();
		FixGcExample.Criteria qc = qe.createCriteria();
		if(dateRange==null){
			dateRange=1;
		}
		//获取开始日期和结束日期的查询范围
		Map<String,String> dateMap = DateRangeStatisticsUtil.getDateRange(dateRange);
		//日期大于等于dateMap.get("startDate")
		qc.andCreateTimeGreaterThanOrEqualTo(dateMap.get("startDate"));
		//日期小于dateMap.get("endDate")
		qc.andCreateTimeLessThan(dateMap.get("endDate"));
		List<FixGc> list = fixGcMapper.selectByExample(qe);
		//根据日期查询类型获取日期列表
		List<Map<String,Object>> dlist = DateRangeStatisticsUtil.getDateList(dateRange);
		for(Map<String,Object> date:dlist){
			String startDate1 = date.get("startDate").toString();
			String endDate1 = date.get("endDate").toString();
			Integer  dataNum  = 0;//维修过程量
			for(FixGc d:list){
				if(d.getCreateTime()==null&&d.getCreateTime().equals("")==false){
					continue;
				}
				if(d.getCreateTime().split(" ")[0].compareTo(startDate1)>=0&&d.getCreateTime().split(" ")[0].compareTo(endDate1)<=0){
					dataNum++;
				}
			}
			//y坐标显示数据
			List<Map<String,Object>> dataShow = new ArrayList<Map<String,Object>>();
			Map<String,Object> dataNumMap = new HashMap<String,Object>();
			dataNumMap.put("yZhName","维修过程量");
			dataNumMap.put("value",dataNum);
			dataShow.add(dataNumMap);
			date.put("dataShow",dataShow);
		}
		rs.put("data",DateRangeStatisticsUtil.convertToMultiDimensionChartData(dlist));
		rs.put("code",1);
		rs.put("msg","请求成功");
		return rs;
	}
	/**
	查询维修过程总数
	**/
	public Map<String,Object> getHomeWxgczs(HttpServletRequest request){
		Map<String,Object> rs = new HashMap<String,Object>();
		LoginModel login = (LoginModel) request.getSession().getAttribute(CommonVal.sessionName);//获取当前登录账号信息
		if(login==null){
			rs.put("code",0);
			rs.put("msg","尚未登录");
			return rs;
		}
		rs.put("login",login);
		String today = sdf2.format(new Date());
		//查询维修过程总数
		//初始化sql查询类
		FixGcExample qe = new FixGcExample();
		FixGcExample.Criteria qc = qe.createCriteria();
		List<FixGc> list = fixGcMapper.selectByExample(qe);
		rs.put("data",list.size());
		rs.put("code",1);
		rs.put("msg","请求成功");
		return rs;
	}
	/**
	查询知识库统计
	**/
	@RequestMapping(value="getHomeZsktj")
	@ResponseBody
	public Map<String,Object> getHomeZsktj(Integer dateRange,HttpServletRequest request){
		Map<String,Object> rs = new HashMap<String,Object>();
		LoginModel login = (LoginModel) request.getSession().getAttribute(CommonVal.sessionName);//获取当前登录账号信息
		if(login==null){
			rs.put("code",0);
			rs.put("msg","尚未登录");
			return rs;
		}
		rs.put("login",login);
		String today = sdf2.format(new Date());
		//查询知识库统计
		//初始化sql查询类
		ZskExample qe = new ZskExample();
		ZskExample.Criteria qc = qe.createCriteria();
		if(dateRange==null){
			dateRange=1;
		}
		//获取开始日期和结束日期的查询范围
		Map<String,String> dateMap = DateRangeStatisticsUtil.getDateRange(dateRange);
		//日期大于等于dateMap.get("startDate")
		qc.andFbTimeGreaterThanOrEqualTo(dateMap.get("startDate"));
		//日期小于dateMap.get("endDate")
		qc.andFbTimeLessThan(dateMap.get("endDate"));
		List<Zsk> list = zskMapper.selectByExample(qe);
		//根据日期查询类型获取日期列表
		List<Map<String,Object>> dlist = DateRangeStatisticsUtil.getDateList(dateRange);
		for(Map<String,Object> date:dlist){
			String startDate1 = date.get("startDate").toString();
			String endDate1 = date.get("endDate").toString();
			Integer  dataNum  = 0;//知识库量
			for(Zsk d:list){
				if(d.getFbTime()==null&&d.getFbTime().equals("")==false){
					continue;
				}
				if(d.getFbTime().split(" ")[0].compareTo(startDate1)>=0&&d.getFbTime().split(" ")[0].compareTo(endDate1)<=0){
					dataNum++;
				}
			}
			//y坐标显示数据
			List<Map<String,Object>> dataShow = new ArrayList<Map<String,Object>>();
			Map<String,Object> dataNumMap = new HashMap<String,Object>();
			dataNumMap.put("yZhName","知识库量");
			dataNumMap.put("value",dataNum);
			dataShow.add(dataNumMap);
			date.put("dataShow",dataShow);
		}
		rs.put("data",DateRangeStatisticsUtil.convertToMultiDimensionChartData(dlist));
		rs.put("code",1);
		rs.put("msg","请求成功");
		return rs;
	}
	/**
	查询知识库总数
	**/
	public Map<String,Object> getHomeZskzs(HttpServletRequest request){
		Map<String,Object> rs = new HashMap<String,Object>();
		LoginModel login = (LoginModel) request.getSession().getAttribute(CommonVal.sessionName);//获取当前登录账号信息
		if(login==null){
			rs.put("code",0);
			rs.put("msg","尚未登录");
			return rs;
		}
		rs.put("login",login);
		String today = sdf2.format(new Date());
		//查询知识库总数
		//初始化sql查询类
		ZskExample qe = new ZskExample();
		ZskExample.Criteria qc = qe.createCriteria();
		List<Zsk> list = zskMapper.selectByExample(qe);
		rs.put("data",list.size());
		rs.put("code",1);
		rs.put("msg","请求成功");
		return rs;
	}
}
