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
@RequestMapping("/index/xt_notice_search")
public class XtNoticeSearchController{
	SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	SimpleDateFormat sdf2 = new SimpleDateFormat("yyyy-MM-dd");
	SimpleDateFormat sdf3 = new SimpleDateFormat("yyyyMMddHHmmss");
	@Autowired
	XtNoticeMapper xtNoticeMapper;
	@Autowired
	XtNoticeService xtNoticeService;
	@RequestMapping("getXtNoticeList")
	@ResponseBody
	public Object getXtNoticeList(Integer page,String rangeParams,XtNotice queryModel,String token){
		Integer pageSize=10;
		//初始化sql查询类
		XtNoticeExample xe = new XtNoticeExample();
		XtNoticeExample.Criteria xc = xe.createCriteria();
		if(queryModel.getTitle()!=null&&queryModel.getTitle().equals("")==false){
			//模糊查询标题
			xc.andTitleLike("%"+queryModel.getTitle()+"%");
		}
		if(page!=null){
			//分页
			xe.setPageRows(pageSize);
			xe.setStartRow((page - 1) * pageSize);
		}
		if(rangeParams==null||rangeParams.equals("")){
			rangeParams = "id desc";
		}
		xe.setOrderByClause(rangeParams);
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
	@RequestMapping("getXtNoticeCount")
	@ResponseBody
	public Object getXtNoticeCount(XtNotice queryModel){
		Integer pageSize = 10;
		//初始化sql查询类
		XtNoticeExample xe = new XtNoticeExample();
		XtNoticeExample.Criteria xc = xe.createCriteria();
		if(queryModel.getTitle()!=null&&queryModel.getTitle().equals("")==false){
			//模糊查询标题
			xc.andTitleLike("%"+queryModel.getTitle()+"%");
		}
		long count = (long)xtNoticeMapper.countByExample(xe);
		long totalPage = count % pageSize == 0 ? count / pageSize : (count / pageSize) + 1;
		Map<String,Object> rs = new HashMap<String,Object>();
		rs.put("code",1);
		rs.put("count",count);
		rs.put("totalPage",totalPage);
		return rs;
	}
}
