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
@RequestMapping("/index/bg_service_search")
public class BgServiceSearchController{
	SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	SimpleDateFormat sdf2 = new SimpleDateFormat("yyyy-MM-dd");
	SimpleDateFormat sdf3 = new SimpleDateFormat("yyyyMMddHHmmss");
	@Autowired
	BgqyMapper bgqyMapper;
	@Autowired
	BgServiceMapper bgServiceMapper;
	@Autowired
	BgqyService bgqyService;
	@Autowired
	BgServiceService bgServiceService;
	@RequestMapping("getBgServiceList")
	@ResponseBody
	public Object getBgServiceList(Integer page,String bgqyFilter,String rangeParams,BgService queryModel,String token){
		Integer pageSize=10;
		//初始化sql查询类
		BgServiceExample be = new BgServiceExample();
		BgServiceExample.Criteria bc = be.createCriteria();
		//查询是否删除
		bc.andIsDeleteEqualTo(2);
		if(queryModel.getServiceNo()!=null&&queryModel.getServiceNo().equals("")==false){
			//模糊查询设备编号
			bc.andServiceNoLike("%"+queryModel.getServiceNo()+"%");
		}
		List<Long> bgqyIds = new ArrayList<Long>();
		if(bgqyFilter!=null){
			for(String str:bgqyFilter.split(",")){
				if(str.trim().equals("")==false){
					bgqyIds.add(Long.parseLong(str.trim()));
				}
			}
		}
		if(bgqyIds.size()>0){
			//办公区域集合查询
			bc.andBgqyIn(bgqyIds);
		}
		if(page!=null){
			//分页
			be.setPageRows(pageSize);
			be.setStartRow((page - 1) * pageSize);
		}
		if(rangeParams==null||rangeParams.equals("")){
			rangeParams = "id desc";
		}
		be.setOrderByClause(rangeParams);
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
	@RequestMapping("getBgServiceCount")
	@ResponseBody
	public Object getBgServiceCount(BgService queryModel){
		Integer pageSize = 10;
		//初始化sql查询类
		BgServiceExample be = new BgServiceExample();
		BgServiceExample.Criteria bc = be.createCriteria();
		//查询是否删除
		bc.andIsDeleteEqualTo(2);
		if(queryModel.getServiceNo()!=null&&queryModel.getServiceNo().equals("")==false){
			//模糊查询设备编号
			bc.andServiceNoLike("%"+queryModel.getServiceNo()+"%");
		}
		if(queryModel.getBgqy()!=null){
			//查询办公区域
			bc.andBgqyEqualTo(queryModel.getBgqy());
		}
		long count = (long)bgServiceMapper.countByExample(be);
		long totalPage = count % pageSize == 0 ? count / pageSize : (count / pageSize) + 1;
		Map<String,Object> rs = new HashMap<String,Object>();
		rs.put("code",1);
		rs.put("count",count);
		rs.put("totalPage",totalPage);
		return rs;
	}
	//查询办公设备查询框下拉数据
	@RequestMapping(value="getListData")
	@ResponseBody
	public Object getBgServiceQueryDataList(HttpServletRequest request){
		Map<String,Object> rs = new HashMap<String,Object>();
		rs.put("code",1);
		rs.put("bgqyList",bgqyService.getSelectList(new Bgqy(),new ArrayList(),null));
		return rs;
	}
}
