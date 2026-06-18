package com.office.service.impl;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.office.dao.*;
import com.office.model.*;
import com.office.service.*;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import com.office.util.*;
import org.springframework.ui.ModelMap;
import java.util.*;
import com.office.service.*;
import com.office.controller.LoginModel;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.text.ParseException;
import java.io.IOException;
@Service
public class ZskServiceImpl implements ZskService{
	SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	SimpleDateFormat sdf2 = new SimpleDateFormat("yyyy-MM-dd");
	SimpleDateFormat sdf3 = new SimpleDateFormat("yyyyMMddHHmmss");
	SimpleDateFormat sdf4 = new SimpleDateFormat("yyyy-MM");
	@Autowired
	ZskMapper zskMapper;
	/**
	新增
	*/
	@Override
	public String add(Zsk model,LoginModel login){
		if(model.getTitle()==null||model.getTitle().equals("")){
			return "标题为必填属性";
		}
		if(model.getSubtitle()==null||model.getSubtitle().equals("")){
			return "副标题为必填属性";
		}
		if(model.getZsContent()==null||model.getZsContent().equals("")){
			return "知识内容为必填属性";
		}
		model.setFbTime(sdf1.format(new Date()));//当前时间格式
		model.setSearchKeyWord("320095");//默认320095
		String subtitle3 = model.getSubtitle()==null?"":model.getSubtitle();
		String title3 = model.getTitle()==null?"":model.getTitle();
		model.setSearchKeyWord(subtitle3+title3);
		zskMapper.insertSelective(model);//插入数据库
		return "";
	}
	/**
	修改
	*/
	@Override
	public String update(Zsk model,LoginModel login){
		//根据主键获取知识库
		Zsk preModel = zskMapper.selectByPrimaryKey(model.getId());
		if(model.getTitle()==null||model.getTitle().equals("")){
			return "标题为必填属性";
		}
		if(model.getSubtitle()==null||model.getSubtitle().equals("")){
			return "副标题为必填属性";
		}
		if(model.getZsContent()==null||model.getZsContent().equals("")){
			return "知识内容为必填属性";
		}
		preModel.setTitle(model.getTitle());
		preModel.setSubtitle(model.getSubtitle());
		preModel.setZsContent(model.getZsContent());
		zskMapper.updateByPrimaryKey(preModel);
		return "";
	}
	/**
	获取列表的id集合
	*/
	public List<Long> getIds(Zsk zsk,List<Map<String,Object>> queryParams,LoginModel login){
		List<Zsk> tl = getZskList(zsk,queryParams,login,null,null,null);
		List<Long> ids = new ArrayList<Long>();
		for(Zsk t:tl){
			ids.add(t.getId());
		}
		return ids;
	}
	/**
	设置登录人可见的数据权限
	*/
	public void setQueryPerm(Zsk model,List<Map<String,Object>> queryParams,LoginModel login){
	}
	/**
	查询封装好的知识库列表
	*/
	public List<Map<String,Object>> getDataList(List<Zsk> tl,LoginModel login){
		List<Map<String,Object>> list = new ArrayList<Map<String,Object>>();
		for(Zsk t:tl){
			list.add(getZskModel(t,login));
		}
		return list;
	}
	/**
	查询总数量和总页数
	*/
	public Map<String,Long> getCount(Zsk queryModel,List<Map<String,Object>> queryParams,LoginModel login,Integer page,Integer pageSize){
		setQueryPerm(queryModel,queryParams, login);
		//初始化sql查询类
		ZskExample se = new ZskExample();
		ZskExample.Criteria sc = se.createCriteria();
		//集合所有的查询条件
		getAllQueryParams(se,sc,queryModel,queryParams,login);
		long count = (long)zskMapper.countByExample(se);
		long totalPage = count % pageSize == 0 ? count / pageSize : (count / pageSize) + 1;
		Map<String,Long> rs = new HashMap<String,Long>();
		rs.put("count",count);//数据总数
		rs.put("totalPage",totalPage);//总页数
		return rs;
	}
	/**
	本表作为其他表的下拉列表，只显示id和名称
	*/
	public List<Map<String,Object>> getSelectList(Zsk zsk,List<Map<String,Object>> queryParams,LoginModel login){
		setQueryPerm(zsk,queryParams, login);
		//初始化sql查询类
		ZskExample te = new ZskExample();
		ZskExample.Criteria tc = te.createCriteria();
		List<Zsk> tl = getZskList(zsk,queryParams,login,null,null,null);
		List<Map<String,Object>> list = new ArrayList<Map<String,Object>>();
		for(Zsk t:tl){
			list.add(getIdNameShowById(t.getId()));
		}
		return list;
	}
	/**
	根据ID查询该数据的简易信息
	*/
	public Map<String,Object> getIdNameShowById(Long id){
		//根据主键获取知识库
		Zsk zsk = zskMapper.selectByPrimaryKey(id);
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("id",id);
		String name = zsk.getTitle();
		map.put("name",name);
		return map;
	}
	/**
	*根据参数查询知识库列表数据
	*/
	@Override
	public List<Zsk> getZskList(Zsk queryModel,List<Map<String,Object>> queryParams,LoginModel login,Integer page,Integer pageSize,String rangeType) {
		setQueryPerm(queryModel,queryParams, login);
		//初始化sql查询类
		ZskExample se = new ZskExample();
		ZskExample.Criteria sc = se.createCriteria();
		if(rangeType!=null&&rangeType.equals("")==false){
			se.setOrderByClause(rangeType);
		}else{
			se.setOrderByClause("id desc");
		}
		if(page!=null&&pageSize!=null){
			//分页
			se.setPageRows(pageSize);
			se.setStartRow((page - 1) * pageSize);
		}
		getAllQueryParams(se,sc,queryModel,queryParams,login);
		List<Zsk> list = zskMapper.selectByExample(se);
		return list;
	}
	//根据参数拼查询语句
	public void getAllQueryParams(ZskExample se,ZskExample.Criteria sc,Zsk queryModel,List<Map<String,Object>> queryParams,LoginModel login){
		sc.andAllAttrQuery(queryParams);
		if(queryModel.getId()!=null){
			sc.andIdEqualTo(queryModel.getId());
		}
		if(queryModel.getTitle()!=null&&queryModel.getTitle().equals("")==false){
			//模糊查询标题
			sc.andTitleLike("%"+queryModel.getTitle()+"%");
		}
		if(queryModel.getSubtitle()!=null&&queryModel.getSubtitle().equals("")==false){
			//模糊查询副标题
			sc.andSubtitleLike("%"+queryModel.getSubtitle()+"%");
		}
		if(queryModel.getSearchKeyWord()!=null&&queryModel.getSearchKeyWord().trim().equals("")==false){
			//模糊查询关键词
			sc.andSearchKeyWordLike("%"+queryModel.getSearchKeyWord()+"%");
		}
	}
	/**
	封装知识库为前台展示的数据形式
	*/
	@Override
	public Map<String,Object> getZskModel(Zsk model,LoginModel login){
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("zsk",model);
		return map;
	}
	/**
	* 删除数据
	*/
	@Override
	public void delete(Long id) {
		//根据主键获取知识库
		Zsk preModel = zskMapper.selectByPrimaryKey(id);
		zskMapper.deleteByPrimaryKey(id);
	}
}
