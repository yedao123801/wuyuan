package com.office.util;
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
import java.util.ArrayList;
import com.office.controller.LoginModel;
public class LoginUtil{
	public static LoginModel getCurrentLogin(String token){
		Cache loginCache = CacheManage.getCacheInfo(token);
		if(loginCache!=null){
			LoginModel login = (LoginModel)loginCache.getValue();
			return login;
		}
 	return null;
	}
}
