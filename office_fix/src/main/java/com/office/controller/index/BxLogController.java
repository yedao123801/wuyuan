package com.office.controller.index;
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
import java.io.IOException;
import com.office.dao.*;
import com.office.model.*;
import com.office.service.*;
import com.office.controller.*;
import com.office.util.*;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Collections;
@Controller
@RequestMapping("/index/bx_log")
public class BxLogController{
	SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	SimpleDateFormat sdf2 = new SimpleDateFormat("yyyy-MM-dd");
	SimpleDateFormat sdf3 = new SimpleDateFormat("yyyyMMddHHmmss");
	@Autowired
	BxLogMapper bxLogMapper;
	@Autowired
	BgServiceMapper bgServiceMapper;
	@Autowired
	BxLogService bxLogService;
	@RequestMapping("createBxLog")
	@ResponseBody
	public Object createBxLog(BxLog model,HttpServletRequest request,String token){
		Map<String,Object> rs = new HashMap<String,Object>();
		//根据主键获取办公设备
		BgService bgService2 = bgServiceMapper.selectByPrimaryKey(model.getBgService());
		if(bgService2==null||bgService2.getIsDelete()==1){
			rs.put("code",0);
			rs.put("msg","非法操作");
			return rs;
		}
		LoginModel login = LoginUtil.getCurrentLogin(token);
		if(login==null){
			rs.put("code",0);
			rs.put("msg","尚未登录");
			return rs;
		}
		return createBxLogMethod(model, login);
	}
	//创建报修记录方法
	public Map<String,Object> createBxLogMethod(BxLog model,LoginModel login){
		Map<String,Object> rs = new HashMap<String,Object>();
		if(model.getYxcd()==null){
			rs.put("code",0);
			rs.put("msg","请填写优先程度");
			return rs;
		}
		if(model.getGzms()==null||model.getGzms().equals("")){
			rs.put("code",0);
			rs.put("msg","请填写故障描述");
			return rs;
		}
		if(model.getGzLocation()==null||model.getGzLocation().equals("")){
			rs.put("code",0);
			rs.put("msg","请填写故障位置");
			return rs;
		}
		BgService bgService = bgServiceMapper.selectByPrimaryKey(model.getBgService());
		model.setBxNo(sdf3.format(new Date())+RandomCodeUtils.getRandomCode());//随机码(当前时间+6位随机码)
		model.setUserId(login.getId());//登录id
		model.setCreateTime(sdf1.format(new Date()));//当前时间格式
		model.setIsDelete(2);//默认否,

		String fixRemark3 = model.getFixRemark()==null?"":model.getFixRemark();
		String ysNote3 = model.getYsNote()==null?"":model.getYsNote();
		String adminRemark3 = model.getAdminRemark()==null?"":model.getAdminRemark();
		String gzLocation3 = model.getGzLocation()==null?"":model.getGzLocation();
		String gzms3 = model.getGzms()==null?"":model.getGzms();
		String serviceType3 = model.getServiceType()==null?"":model.getServiceType();
		String bxNo3 = model.getBxNo()==null?"":model.getBxNo();
		model.setSearchKeyWord(fixRemark3+ysNote3+adminRemark3+gzLocation3+gzms3+serviceType3+bxNo3);
		//根据主键获取办公设备
		BgService bgServiceT = bgServiceMapper.selectByPrimaryKey(model.getBgService());
		if(bgServiceT!=null){
			model.setServiceType(bgServiceT.getServiceType());//赋值bgServiceT的设备类型
		}
		if(bgServiceT!=null){
			model.setBgqy(bgServiceT.getBgqy());//赋值bgServiceT的办公区域
		}
		model.setBxStatus(1);
		bxLogMapper.insertSelective(model);//插入数据库
		rs.put("code",1);
		rs.put("msg","操作成功");
		return rs;
	}
	//查询报修记录查询框下拉数据
	@RequestMapping(value="getListData")
	@ResponseBody
	public Object getBxLogQueryDataList(HttpServletRequest request){
		Map<String,Object> rs = new HashMap<String,Object>();
		rs.put("code",1);
		rs.put("yxcdList",DataListUtils.getYxcdList());
		return rs;
	}
}
