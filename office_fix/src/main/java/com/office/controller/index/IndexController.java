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
@RequestMapping("/index/index")
public class IndexController{
	SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	SimpleDateFormat sdf2 = new SimpleDateFormat("yyyy-MM-dd");
	SimpleDateFormat sdf3 = new SimpleDateFormat("yyyyMMddHHmmss");
	@Autowired
	ZskMapper zskMapper;
	@Autowired
	XtNoticeMapper xtNoticeMapper;
	@Autowired
	BgServiceMapper bgServiceMapper;
	@Autowired
	ZskService zskService;
	@Autowired
	XtNoticeService xtNoticeService;
	@Autowired
	BgServiceService bgServiceService;
	@RequestMapping("getBgServiceList")
	@ResponseBody
	public Object getBgServiceList(String rangeParams,BgService queryModel,String token){
		//初始化sql查询类
		BgServiceExample be = new BgServiceExample();
		BgServiceExample.Criteria bc = be.createCriteria();
		//查询是否删除
		bc.andIsDeleteEqualTo(2);
		//分页
		be.setPageRows(10);
		be.setStartRow((0 - 1) * 10);
		be.setOrderByClause("id desc");
		List<BgService> bl = bgServiceMapper.selectByExample(be);
		List<Map<String,Object>> blist = new ArrayList<Map<String,Object>>();
		for(BgService model:bl){
			blist.add(bgServiceService.getBgServiceModel(model,null));
		}
		Map<String,Object> rs = new HashMap<String,Object>();
		rs.put("code",1);
		rs.put("data",blist);
		return rs;
	}
	@RequestMapping("getZskList")
	@ResponseBody
	public Object getZskList(String rangeParams,Zsk queryModel,String token){
		//初始化sql查询类
		ZskExample ze = new ZskExample();
		ZskExample.Criteria zc = ze.createCriteria();
		//分页
		ze.setPageRows(10);
		ze.setStartRow((0 - 1) * 10);
		ze.setOrderByClause("id desc");
		List<Zsk> zl = zskMapper.selectByExample(ze);
		List<Map<String,Object>> zlist = new ArrayList<Map<String,Object>>();
		for(Zsk model:zl){
			zlist.add(zskService.getZskModel(model,null));
		}
		Map<String,Object> rs = new HashMap<String,Object>();
		rs.put("code",1);
		rs.put("data",zlist);
		return rs;
	}
	@RequestMapping("getXtNoticeList")
	@ResponseBody
	public Object getXtNoticeList(String rangeParams,XtNotice queryModel,String token){
		//初始化sql查询类
		XtNoticeExample xe = new XtNoticeExample();
		XtNoticeExample.Criteria xc = xe.createCriteria();
		//分页
		xe.setPageRows(10);
		xe.setStartRow((0 - 1) * 10);
		xe.setOrderByClause("id desc");
		List<XtNotice> xl = xtNoticeMapper.selectByExample(xe);
		List<Map<String,Object>> xlist = new ArrayList<Map<String,Object>>();
		for(XtNotice model:xl){
			xlist.add(xtNoticeService.getXtNoticeModel(model,null));
		}
		Map<String,Object> rs = new HashMap<String,Object>();
		rs.put("code",1);
		rs.put("data",xlist);
		return rs;
	}
}
