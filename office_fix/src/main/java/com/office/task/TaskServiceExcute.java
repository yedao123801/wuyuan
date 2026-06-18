package com.office.task;
import java.util.Map;
import java.util.HashMap;
import com.office.controller.LoginModel;
import com.office.model.*;
import com.office.dao.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import  com.office.service.*;
import  java.io.InputStream;
import   java.text.SimpleDateFormat;
import  com.office.util.*;
import   org.springframework.ui.ModelMap;
import   java.util.regex.Pattern;
import  java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.Date;
import java.util.ArrayList;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Component;
import org.springframework.scheduling.annotation.Scheduled;
@Component
public class TaskServiceExcute {
	@Autowired
	FixService fixService;
	SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	SimpleDateFormat sdf2 = new SimpleDateFormat("yyyy-MM-dd");
	SimpleDateFormat sdf3 = new SimpleDateFormat("yyyyMMddHHmmss");
	//每20s跑一次
	@Scheduled(cron="*/20 * * * * ?")
	public void excuteTask(){
		List<Fix> fixList=  fixService.getFixList(new Fix(),new ArrayList(),null,null,null,null);
		for( Fix fix:fixList){
			fixService.calcuteFix(fix.getId());
		}
	}
}
