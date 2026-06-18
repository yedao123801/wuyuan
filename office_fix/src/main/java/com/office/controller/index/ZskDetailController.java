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
@RequestMapping("/index/zsk_detail")
public class ZskDetailController{
	SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	SimpleDateFormat sdf2 = new SimpleDateFormat("yyyy-MM-dd");
	SimpleDateFormat sdf3 = new SimpleDateFormat("yyyyMMddHHmmss");
	@Autowired
	ZskMapper zskMapper;
	@Autowired
	ZskService zskService;
	@RequestMapping("getZskDetail")
	@ResponseBody
	public Object getzskDetail(Long id){
		//根据主键获取知识库
		Zsk model = zskMapper.selectByPrimaryKey(id);
		Map<String,Object> rs = new HashMap<String,Object>();
		if(model==null){
			rs.put("code",0);
			rs.put("msg","该知识库不存在");
			return rs;
		}
		Map<String,Object> data = zskService.getZskModel(model,null);
		rs.put("code",1);
		rs.put("data",data);
		return rs;
	}
}
