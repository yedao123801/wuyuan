import $ from 'jquery'
export  var utils = {
    setNullToEmpty(val) {// 将所有空数据都转为'-'
        if (val === null || val === 'null' || val === '' || val == undefined || val == 'undefined') {
            return '-';
        }
        return val;
    },
    isIntNum(s) {// 是否为正整数
        var re = /^[0-9]*[1-9][0-9]*$/;
        if (s == '0') {
            return true;
        }
        return re.test(s)
    },
    isNum(val) {
        if (isNaN(val) == true) {
            return false;
        } else {
            return true;
        }
    },
	genTimestamp(){
		var time = new Date();
		return time.getTime();
	},
	downLoadFile(e){
	},
    getCheckBoxVal(boxName) {
        var valTmp = $("input[name='" + boxName + "']");
        var val = '';
        for (var i = 0; i < valTmp.length; i++) {
            if ($(valTmp[i]).prop("checked") == true) {
                val += $(valTmp[i]).val() + ",";
            }
        }
        if (val != '') {
            val = val.substring(0, val.length - 1);
        }
        return val;
    },
	setCheckBoxVal(domId,val){
		var split = val.split(',');
		 var valTmp = $("input[name='" + domId + "']");
		for(var k=0;k<split.length;k++){
			var aval = split[k];
			for (var i = 0; i < valTmp.length; i++) {
				if ($(valTmp[i]).val() == aval) {
					$(valTmp[i]).prop("checked",true);
				}
			}
		}
        var val = '';
	},
    getTimeList(startTime, endTime, df, type) {
        var list = [];
        var tmp = startTime;
        while (true) {
            if (tmp < endTime) {
                var flag = false;
                for (var i = 0; i < list.length; i++) {
                    if (list[i] == tmp) {
                        flag = true;
                    }
                }
                if (flag == false) {
                    list.push(tmp);
                }
                tmp = this.getDiffTime(tmp, 1, df, type);
            } else {
                break;
            }
        }
        var flag2 = false;
        for (var i = 0; i < list.length; i++) {
            if (list[i] == endTime) {
                flag2 = true;
            }
        }
        if (flag2 == false) {
            list.push(endTime);
        }
        return list;
    },
    getDiffTime(date, diff, df, type) {
        var time = this.transferToTime(date);
        if (type == 1) {// 年份
            time.setFullYear(time.getFullYear() + diff);
        } else if (type == 2) {// 月份
            time.setMonth(time.getMonth() + diff);
        } else if (type == 3) {// 日期
            time.setDate(time.getDate() + diff);
        } else if (type == 4) {// 小时
            time.setHours(time.getHours() + diff);
        } else if (type == 5) {// 分钟
            time.setMinutes(time.getMinutes() + diff);
        } else if (type == 6) {// 秒
            time.setSeconds(time.getSeconds() + diff);
        }
        return this.getTime(time, df);
    },
    transferToTime(date) {
        date = date.replace(/-/g, '/'); // 必须把日期'-'转为'/'
        date = date.replace(/年/g, '/'); // 必须把日期'-'转为'/'
        date = date.replace(/月/g, '/'); // 必须把日期'-'转为'/'
        date = date.replace(/日/g, '/'); // 必须把日期'-'转为'/'
        date = date.replace(/时/g, ':'); // 必须把日期'-'转为'/'
        date = date.replace(/分/g, ':'); // 必须把日期'-'转为'/'
        date = date.replace(/秒/g, ''); // 必须把日期'-'转为'/'
        var timestamp = new Date(date).getTime();
        return new Date(timestamp);
    },
    getTime(date, fmt) {
        var o = {
            "M+": date.getMonth() + 1, // 月份
            "d+": date.getDate(), // 日
            "H+": date.getHours(), // 小时
            "m+": date.getMinutes(), // 分
            "s+": date.getSeconds(), // 秒
            "q+": Math.floor((date.getMonth() + 3) / 3), // 季度
            "S": date.getMilliseconds() // 毫秒
        };
        if (/(y+)/.test(fmt)) fmt = fmt.replace(RegExp.$1, (date.getFullYear() + "").substr(4 - RegExp.$1.length));
        for (var k in o)
            if (new RegExp("(" + k + ")").test(fmt)) fmt = fmt.replace(RegExp.$1, (RegExp.$1.length == 1) ? (o[k]) : (("00" + o[k]).substr(("" + o[k]).length)));
        return fmt;
    },
    getCookie(name) {
		var msg = this.getAllCookie();
		var val = msg[name];
		if (val == undefined || val == null) {
			return '';
		} else {
			return val;
		}
	},
	/*添加cookie*/
	setCookie(name, value, outTime) {
		var expdate = new Date();
		var outms = outTime * 24 * 60 * 60 * 1000;//过期时间，以天为单位‘1’表示一天
		expdate.setTime(expdate.getTime() + outms);
		var cookieStr = name + "=" + escape(value) + ";expires=" + expdate.toGMTString()+";path=/;";
		//escape方法的作用是进行编码，主要防止value中有特殊字符
		document.cookie = cookieStr;
	},
	/*读取cookie*/
	getAllCookie() {
		var cookieStr = document.cookie;
		var cookievalue = "";
		var cookiMap = {};
		if (cookieStr != null && cookieStr != undefined) {
			var arrayCookie = cookieStr.split(';');
			for (var i = 0; i < arrayCookie.length; i++) {
				var arrayDetail = arrayCookie[i].split('=');
				if (arrayDetail.length == 2) {
					cookiMap[arrayDetail[0].trim()] = unescape(arrayDetail[1].trim());
				}
			}
		}
		return cookiMap;
	},
	/*删除cookie
	cookie的删除并不是物理意义上的直接删除，
	而是将cookie的有效期设置为失效，然后由浏览器删除失效的cookie删除
*/
	deleteCookie(cookiename) {
		var date = new Date();
		var outTime = date.getTime() - 1000;//将cookie的有效期设置为失效
		date.setTime(outTime);
		document.cookie = cookiename + "='';expires=" + date.toGMTString()+";path=/;";
	}
}
