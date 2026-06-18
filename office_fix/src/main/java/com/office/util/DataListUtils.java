package com.office.util;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
/**
*
数据列表解释器，键值对列举说明工具，例如性别字段：1表示男，2表示女
*
*/
public class DataListUtils {
	public static void main(String[] args) {
	}
	/**
	**获取登录角色列表
	*/
	public static List<Map<String,Object>> getLoginTypeList(){
		List<Map<String,Object>> list = new ArrayList<Map<String,Object>>();
		list.add(getMap("1","admin"));
		list.add(getMap("2","wxy"));
		list.add(getMap("3","user"));
		return list;
	}
	/**
	**获取check_status数据列表
	*/
	public static List<Map<String,Object>> getCheckStatusList(){
		List<Map<String,Object>> list = new ArrayList<Map<String,Object>>();
		list.add(getMap("1","待审核"));
		list.add(getMap("2","审核通过"));
		list.add(getMap("3","审核不通过"));
		return list;
	}
	public static String getCheckStatusNameById(String id){//根据check_status的key值获取名称
		List<Map<String,Object>> list = getCheckStatusList();
		return getNameById(id,list);
	}
	/**
	**获取yxcd数据列表
	*/
	public static List<Map<String,Object>> getYxcdList(){
		List<Map<String,Object>> list = new ArrayList<Map<String,Object>>();
		list.add(getMap("1","普通"));
		list.add(getMap("2","紧急"));
		return list;
	}
	public static String getYxcdNameById(String id){//根据yxcd的key值获取名称
		List<Map<String,Object>> list = getYxcdList();
		return getNameById(id,list);
	}
	/**
	**获取bx_status数据列表
	*/
	public static List<Map<String,Object>> getBxStatusList(){
		List<Map<String,Object>> list = new ArrayList<Map<String,Object>>();
		list.add(getMap("1","待受理"));
		list.add(getMap("2","已派单"));
		list.add(getMap("3","维修中"));
		list.add(getMap("4","已完成"));
		list.add(getMap("5","已验收"));
		list.add(getMap("6","已驳回"));
		list.add(getMap("7","已评价"));
		return list;
	}
	public static String getBxStatusNameById(String id){//根据bx_status的key值获取名称
		List<Map<String,Object>> list = getBxStatusList();
		return getNameById(id,list);
	}
	/**
	**获取req_status数据列表
	*/
	public static List<Map<String,Object>> getReqStatusList(){
		List<Map<String,Object>> list = new ArrayList<Map<String,Object>>();
		list.add(getMap("1","已申请"));
		list.add(getMap("2","已接受申请"));
		list.add(getMap("3","已拒绝申请"));
		return list;
	}
	public static String getReqStatusNameById(String id){//根据req_status的key值获取名称
		List<Map<String,Object>> list = getReqStatusList();
		return getNameById(id,list);
	}
	/**
	**获取eval_star数据列表
	*/
	public static List<Map<String,Object>> getEvalStarList(){
		List<Map<String,Object>> list = new ArrayList<Map<String,Object>>();
		list.add(getMap("1","1星"));
		list.add(getMap("2","2星"));
		list.add(getMap("3","3星"));
		list.add(getMap("4","4星"));
		list.add(getMap("5","5星"));
		return list;
	}
	public static String getEvalStarNameById(String id){//根据eval_star的key值获取名称
		List<Map<String,Object>> list = getEvalStarList();
		return getNameById(id,list);
	}
	/**
	**获取is_delete数据列表
	*/
	public static List<Map<String,Object>> getIsDeleteList(){
		List<Map<String,Object>> list = new ArrayList<Map<String,Object>>();
		list.add(getMap("1","是"));
		list.add(getMap("2","否"));
		return list;
	}
	public static String getIsDeleteNameById(String id){//根据is_delete的key值获取名称
		List<Map<String,Object>> list = getIsDeleteList();
		return getNameById(id,list);
	}
	public static String getNameById(String id,List<Map<String,Object>> list){
		if(id==null){
			return null;
		}
		if(id.endsWith(",")){
			id = id.substring(0,id.length()-1);
		}
		String[] idsplit = id.split(",");
		String rs = "";
		for(String tmp:idsplit){
			for(Map<String,Object> map:list){
				if(map.get("id").toString().equals(tmp)){
					rs+=map.get("name").toString()+",";
				}
			}
		}
		if(rs.endsWith(",")){
			rs = rs.substring(0,rs.length()-1);
		}
		return rs;
	}
	private static Map<String,Object> getMap(String id,String name){
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("id", id);
		map.put("name", name);
		return map;
	}
}
