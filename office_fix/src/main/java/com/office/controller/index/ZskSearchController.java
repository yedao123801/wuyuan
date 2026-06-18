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
@RequestMapping("/index/zsk_search")
public class ZskSearchController{
	SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	SimpleDateFormat sdf2 = new SimpleDateFormat("yyyy-MM-dd");
	SimpleDateFormat sdf3 = new SimpleDateFormat("yyyyMMddHHmmss");
	@Autowired
	ZskMapper zskMapper;
	@Autowired
	ZskService zskService;
	@RequestMapping("getZskList")
	@ResponseBody
	public Object getZskList(Integer page,String rangeParams,Zsk queryModel,String token){
		Integer pageSize=10;
		//初始化sql查询类
		ZskExample ze = new ZskExample();
		ZskExample.Criteria zc = ze.createCriteria();
		if(queryModel.getTitle()!=null&&queryModel.getTitle().equals("")==false){
			//模糊查询标题
			zc.andTitleLike("%"+queryModel.getTitle()+"%");
		}
		if(page!=null){
			//分页
			ze.setPageRows(pageSize);
			ze.setStartRow((page - 1) * pageSize);
		}
		if(rangeParams==null||rangeParams.equals("")){
			rangeParams = "id desc";
		}
		ze.setOrderByClause(rangeParams);
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
	@RequestMapping("getZskCount")
	@ResponseBody
	public Object getZskCount(Zsk queryModel){
		Integer pageSize = 10;
		//初始化sql查询类
		ZskExample ze = new ZskExample();
		ZskExample.Criteria zc = ze.createCriteria();
		if(queryModel.getTitle()!=null&&queryModel.getTitle().equals("")==false){
			//模糊查询标题
			zc.andTitleLike("%"+queryModel.getTitle()+"%");
		}
		long count = (long)zskMapper.countByExample(ze);
		long totalPage = count % pageSize == 0 ? count / pageSize : (count / pageSize) + 1;
		Map<String,Object> rs = new HashMap<String,Object>();
		rs.put("code",1);
		rs.put("count",count);
		rs.put("totalPage",totalPage);
		return rs;
	}
}
