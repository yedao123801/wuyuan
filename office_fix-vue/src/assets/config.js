var uri="http://localhost:8080/office_fix";
function GetRequest() {
	var urlStr = location.href;
	if (typeof urlStr == "undefined") {
		var url = decodeURI(location.search); //获取url中"?"符后的字符串
	} else {
		var url = "?" + urlStr.split("?")[1];
	}
	var theRequest = new Object();
	if (url.indexOf("?") != -1) {
		var str = url.substr(1);
		strs = str.split("&");
		for (var i = 0; i < strs.length; i++) {
			theRequest[strs[i].split("=")[0]] = decodeURI(strs[i].split("=")[1]);
		}
	}
	return theRequest;
}
var httpData = GetRequest();
var currentLogin = getLoginCookie();
function getLoginCookie(){
	var loginId = getCookie("loginId");
	var loginName = getCookie("loginName");
	var loginType = getCookie("loginType");
	var loginToken = getCookie("loginToken");
	var roleName = getCookie("roleName");
	var map = {};
		map.loginId = loginId;
		map.loginName = loginName;
		map.loginType = loginType;
		map.loginToken = loginToken;
		map.roleName = roleName;
		return map;
}
function getCookie(name){
var msg =  getAllCookie();
var val = msg[name];
if(val==undefined||val==null){
	return '';
}else{
	return val;
}
}
  /*添加cookie*/
		    function setCookie(name,value,outTime){
		        var expdate=new Date();
		        var outms=outTime*24*60*60*1000;//过期时间，以天为单位‘1’表示一天
		        expdate.setTime(expdate.getTime()+outms);
		        var cookieStr=name+"="+escape(value)+";expires="+expdate.toGMTString();
		        //escape方法的作用是进行编码，主要防止value中有特殊字符
		        document.cookie=cookieStr;
		    }
		  /*读取cookie*/
		    function getAllCookie(){
		        var cookieStr=document.cookie;
		        var cookievalue="";
		        var cookiMap = {};
		        if(cookieStr!=null &&cookieStr!=undefined){
		            var arrayCookie=cookieStr.split(';');
		            for(var i=0;i<arrayCookie.length;i++){
		                    var arrayDetail=arrayCookie[i].split('=');
		                    if(arrayDetail.length==2){
		                    	cookiMap[arrayDetail[0].trim()] = unescape(arrayDetail[1].trim());
//		                    	  if(i==0){
//				                        cookiMap='{"'+arrayDetail[0].trim()+'":"'+arrayDetail[1].trim()+'",';
//				                    }else if(i==arrayCookie.length-1){
//				                        cookiMap+='"'+arrayDetail[0].trim()+'":"'+arrayDetail[1].trim()+'"}';
//				                    }else{
//				                        cookiMap+='"'+arrayDetail[0].trim()+'":"'+arrayDetail[1].trim()+'",';
//				                    }
		                    }
		                }
		            }
//		        if(cookiMap==''){
//		       		 var map ={};
//		        	return map;
//		        }
//		            var cookieObj=JSON.parse(cookiMap);
//		        for(var item in cookieObj){
//		            if(item==cookieName){
//		                cookievalue=unescape(cookieObj[item]);
//		            }
//		        }
		        return cookiMap;
		    }
		    /*删除cookie
	        cookie的删除并不是物理意义上的直接删除，
	        而是将cookie的有效期设置为失效，然后由浏览器删除失效的cookie删除
	    */
	    function deleteCookie(cookiename){
	        var date = new Date();
	        var outTime=date.getTime()-1000;//将cookie的有效期设置为失效
	        date.setTime(outTime);
	        document.cookie=cookiename+"='';expires="+date.toGMTString();
	    }
