package com.office.controller;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Arrays;
import java.util.Set;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.HandlerMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.stereotype.Component;
import com.office.util.*;
/**
过滤器，校验所有需要验证的请求，判断是否有登录，如果未登录，则强制跳转到登录页面，如果已登录，则判断是否正在进行非法请求
*/
@Component
public class LoginInterceptor  implements HandlerInterceptor{
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
		String requestUri = request.getRequestURI();
		String contextPath = request.getContextPath();
		String contextPath2 = requestUri.trim().replaceAll("/","");
		Map<String, String> maps =  getParamters( request);
		LoginModel login=null;
		if(maps.get("loginToken")!=null&&maps.get("loginToken").equals("")==false){
			String val = maps.get("loginToken");
			Cache loginCache = CacheManage.getCacheInfo(val);
			if(loginCache!=null){
				login = (LoginModel)loginCache.getValue();
				request.getSession().setAttribute(CommonVal.sessionName, login);//设置登录session，保持会话
			}
		}
		if(contextPath2.equals("office_fix")){
			return true;
		}
		if (login == null) {//登录session为空，说明尚未登录
			if(requestUri.contains("/admin/")){
				response.sendRedirect(contextPath+"/login/error");//返回报错信息
				return false;
			}
			if(requestUri.contains("/wxy/")){
				response.sendRedirect(contextPath+"/login/error");//返回报错信息
				return false;
			}
			if(requestUri.contains("/user/")){
				response.sendRedirect(contextPath+"/login/error");//返回报错信息
				return false;
			}
			return true;
		}
		return true;
	}
	public  Map<String, String>  getParamters(HttpServletRequest request) {
		Map<String, String> map = new HashMap<String,String>();
		Enumeration<String> parameterNames = request.getParameterNames();
		while (parameterNames.hasMoreElements()) {
			String key = parameterNames.nextElement();
			String value = request.getParameter(key);
			map.put(key, value);
		}
		LinkedHashMap attribute = (LinkedHashMap) request.getAttribute(HandlerMapping.URI_TEMPLATE_VARIABLES_ATTRIBUTE);
		if(attribute!=null){
			Set<Map.Entry<String, String>> entrySet = attribute.entrySet();
			for (Map.Entry<String, String> entry : entrySet) {
				map.put(entry.getKey(), entry.getValue());
			}
		}
		return map;
	}
	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
	}
	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
	}
}
