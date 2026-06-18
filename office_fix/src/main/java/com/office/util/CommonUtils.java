package com.office.util;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;
import java.util.List;
import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Arrays;
import java.util.concurrent.ThreadLocalRandom;
import java.util.Locale;
public class CommonUtils {
	//获取访问者ip地址
	public static String getClientIp(HttpServletRequest request) {
	    String remoteAddr = "";
	    if (request != null) {
	        remoteAddr = request.getHeader("X-FORWARDED-FOR");
	        if (remoteAddr == null || "".equals(remoteAddr)) {
	            remoteAddr = request.getRemoteAddr();
	        }
	    }
	    return remoteAddr;
	}

	/**
		根据传入date计算该事件加上多少天/分/时/月/年的时间
	*/
	public static String getDiffDate(String date,int time,SimpleDateFormat df,Integer timeType){
		Date d =null;
		try {
			d=df.parse(date);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		 Calendar calendar=Calendar.getInstance();   
		calendar.setTime(d); 
		if(timeType==1){//分
			calendar.set(Calendar.MINUTE,calendar.get(Calendar.MINUTE)+time);//分
		}else if(timeType==2){//时
			calendar.set(Calendar.HOUR_OF_DAY,calendar.get(Calendar.HOUR_OF_DAY)+time);//让日期加上天数	
		}else if(timeType==3){//天
			calendar.set(Calendar.DAY_OF_MONTH,calendar.get(Calendar.DAY_OF_MONTH)+time);//让日期加上天数	
		}else if(timeType==4){//月份
			calendar.set(Calendar.MONTH,calendar.get(Calendar.MONTH)+time);//让日期加上天数	
		}else if(timeType==5){//年
			calendar.set(Calendar.YEAR,calendar.get(Calendar.YEAR)+time);//让日期加上天数	
		}
		   return df.format(calendar.getTime());
	}
	
	  //获取天数差
    public static int getDays(String d1,String d2){
    	SimpleDateFormat sdf2 = new SimpleDateFormat("yyyy-MM-dd");
    	Date date1 = null;
    	Date date2 = null;
    	try {
			date1 = sdf2.parse(d1);
			date2 = sdf2.parse(d2);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		long daysBetween=(date2.getTime()-date1.getTime())/(60*60*24*1000)+1;
		return (int)daysBetween;
	}
    //获取小时差
    public static Double getHours(String d1,String d2){
    	SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    	Date date1 = null;
    	Date date2 = null;
    	try {
			date1 = sdf1.parse(d1);
			date2 = sdf1.parse(d2);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		long mins=(date2.getTime()-date1.getTime()+1000000)/(60*1000);
		Double rs = (double)mins/60.0;
		return rs;
	}
    //获取分钟差
    public static Integer getMins(String d1,String d2){
    	SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    	Date date1 = null;
    	Date date2 = null;
    	try {
			date1 = sdf1.parse(d1);
			date2 = sdf1.parse(d2);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		long mins=(date2.getTime()-date1.getTime()+1000000)/(60*1000);
		return (int)mins;
	}
	
	// 打乱顺序
    public static <T> void shuffle(List<T> list) {
        int size = list.size();
        Random random = new Random();
        for(int i = 0; i < size; i++) {
            // 获取随机位置
            int randomPos = random.nextInt(size);
            // 当前元素与随机元素交换
            Collections.swap(list, i, randomPos);
        }
    }
    
    //在列表最前方加上个全部选项
    public static List<Map<String,Object>> addAllSelectToList(List<Map<String,Object>> list){
    	List<Map<String,Object>> list2 = new ArrayList<Map<String,Object>>();
    	Map<String,Object> map = new HashMap<String,Object>();
    	map.put("id","");
    	map.put("name","全部");
    	list2.add(map);
    	list2.addAll(list);
    	return list2;
    }
    /**
    	检查是否数据项相同
    **/
    private boolean  checkIsEqual(String str,String str2){
		if(str==null||str2==null){
			return false;
		}
		String [] split1 = str.split(",");
		String [] split2 = str2.split(",");
		boolean isCompete=true;
		for(String tmp:split2){
			if(str.contains(tmp+",")==false&&str.contains(","+tmp)==false&&str.equals(""+tmp)==false){
				isCompete=false;
				break;
			}
		}
		boolean isCompete2=true;
		for(String tmp:split1){
			if(str2.contains(tmp+",")==false&&str2.contains(","+tmp)==false&&str2.equals(""+tmp)==false){
				isCompete2=false;
				break;
			}
		}	
		return isCompete&&isCompete2;
	}
	  /**
    	检查str是否包含str2所有数据项
    **/
	private boolean  checkIsContain(String str,String str2){
		if(str==null||str2==null){
			return false;
		}
		String [] split = str2.split(",");
		boolean isCompete=true;
		for(String tmp:split){
			if(str.contains(tmp+",")==false&&str.contains(","+tmp)==false&&str.equals(""+tmp)==false){
				isCompete=false;
				break;
			}
		}
		return isCompete;
	}
	
		/**
     * 将Object对象里面的属性和值转化成Map对象
     *
     * @param obj
     * @return
     * @throws IllegalAccessException
     */
    public static Map<String, Object> objectToMap(Object obj) {
        Map<String, Object> map = new HashMap<String,Object>();
        Class<?> clazz = obj.getClass();
        for (Field field : clazz.getDeclaredFields()) {
            field.setAccessible(true);
            String fieldName = field.getName();
            Object value=null;
			try {
				value = field.get(obj);
			} catch (IllegalArgumentException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IllegalAccessException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
            map.put(fieldName, value);
        }
        return map;
    }
    
    
    public static String coverToShowTime(String timeStr, SimpleDateFormat sf) {
		if(timeStr==null||timeStr.trim().equals("")){
			return "";
		}
		Date updateTime = null;
		try {
			updateTime = sf.parse(timeStr);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		long time = (System.currentTimeMillis() - updateTime.getTime()) / 1000;
		// 小于60 秒显示刚刚
		String result = sf.format(updateTime);
		long temp = 0;
		if(time<0){
			return timeStr;
		}
		if (time < 60) {
			result = "刚刚";
			// 小于60分钟直接显示几分钟前
		} else if ((temp = time / 60) < 60) {
			result = temp + "分钟前";
			// 小于24小时直接显示几小时前
		} else if ((temp = time / (60 * 60)) < 24) {
			result = temp + "小时前";
			// 小于7天显示几天前
		} else if ((temp = time / (86400)) < 7) {
			result = temp + "天前";
			// //大于7天小于14天显示1周前
		} else if ((temp = time / (86400 * 7)) < 2) {
			result = temp + "周前";
		} else {
			result = timeStr;
		}
		return result;
	}
	
	
	public static String hideStr(String str,Integer preStrNum,Integer laterStrNum,String replaceStr){
		replaceStr = replaceStr+replaceStr+replaceStr+replaceStr+replaceStr+replaceStr;
		return str.replaceAll("([\\s\\S]{"+preStrNum+"})[\\s\\S]+([\\s\\S]{"+laterStrNum+"})","$1"+replaceStr+"$2");
	}
	
	
		private static char [] numlist = new char[]{'0','1','2','3','4','5','6','7','8','9','一','二','三','四','五','六','七',
		'八','九','零','壹','贰','叁','肆','伍','陆','柒','捌','玖','拾'};//可以表达数字的字符
	private static char [] specialWord=new char []{' ',',','.','-','_','+',':','~','/','，','	','(',')',';','：','；','[','【',']','】','、'};
	/**
	 * 检查字符中是否有联系方式
	 * @param str
	 * @return
	 */
	public static boolean checkHasConnectMsg(String str){
		char[] chars = str.toCharArray();
		int num=0;
		for(int i=0;i<chars.length;i++){
			boolean isCompete=false;
			for(char str2:numlist){
				if(str2==chars[i]){
					num++;
					isCompete=true;
					break;
				}
			}
			boolean isSpecial=false;
			for(char str2:specialWord){
				if(str2==chars[i]){
					isSpecial=true;
					break;
				}
			}
			if(isCompete==false&&isSpecial==false){
				num=0;
			}
			if(num>5){//大于5个连续数字
				return true;
			}
		}
		return false;
	}
	
	public static Map<String,Object> getParamsMap(String attrName,String queryType,Object value,String attrName2){
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("attrName",attrName);
		map.put("queryType",queryType);
		map.put("value",value);
		map.put("attrName2",attrName2);
		return map;
	}
	  /**
     * 生成唯一的6位数字随机码
     * @param codes 已存在的码列表（需避免重复）
     * @return 6位唯一随机码
     * @throws RuntimeException 达到最大重试次数仍未生成唯一码时抛出
     */
    public static String generateUnique6DigitCode(List<String> codes) {
        // 判空处理：如果传入的列表为null，视为空列表
        if (codes == null) {
            codes = new ArrayList();
        }
		int maxRetry = 1000;
        int retryCount = 0;
        while (retryCount < maxRetry) {
            // 步骤1：生成0-999999的随机整数（覆盖所有6位数字组合）
            int randomNum = ThreadLocalRandom.current().nextInt(1000000);
            // 步骤2：格式化为6位字符串，不足6位补0（例如5→000005）
            String randomCode = String.format("%06d", randomNum);
            // 步骤3：检查是否唯一，唯一则返回
            if (!codes.contains(randomCode)) {
                return randomCode;
            }
            retryCount++;
        }
        // 达到最大重试次数仍未生成，抛出异常提示
        throw new RuntimeException("达到最大重试次数（" + maxRetry + "次），无法生成唯一的6位随机码");
    }
     /**
     * 生成唯一的4位数字随机码
     * @param codes 已存在的码列表（需避免重复）
     * @return 4位唯一随机码
     * @throws RuntimeException 达到最大重试次数仍未生成唯一码时抛出
     */
    public static String generateUnique4DigitCode(List<String> codes) {
        // 判空处理：如果传入的列表为null，视为空列表
        if (codes == null) {
            codes = new ArrayList();
        }
		int maxRetry = 1000;
        int retryCount = 0;
        while (retryCount < maxRetry) {
            // 步骤1：生成0-9999的随机整数（覆盖所有4位数字组合）
            int randomNum = ThreadLocalRandom.current().nextInt(10000);
            // 步骤2：格式化为4位字符串，不足4位补0（例如5→0005）
            String randomCode = String.format("%04d", randomNum);
            // 步骤3：检查是否唯一，唯一则返回
            if (!codes.contains(randomCode)) {
                return randomCode;
            }
            retryCount++;
        }
        // 达到最大重试次数仍未生成，抛出异常提示
        throw new RuntimeException("达到最大重试次数（" + maxRetry + "次），无法生成唯一的4位随机码");
    }
	//将图片解析转成List形式
	public static List<String> parseImgList(String imgUrl) {
        List<String> imgList = new ArrayList<>();
        if (imgUrl != null && !imgUrl.trim().isEmpty()) {
        	String [] split = imgUrl.split(";");
        	for(String str:split) {
        		if(str.trim().equals("")==false) {
        			imgList.add(str.trim());
        		}
        	}
        }
        return imgList;
    }
     public static List<Map<String,String>> parseFileList(String fileUrl) {
       List<Map<String,String>> list = new ArrayList<Map<String,String>>();
		if(fileUrl!=null){
			String [] split = fileUrl.split(";");
			for(String tmpstr:split){
				if(tmpstr.equals("")==false){
					Map<String,String> tmpMap = new HashMap<String,String>();
					String [] tmp = tmpstr.split("/");
					tmpMap.put("url",tmpstr);
					tmpMap.put("name", tmp[tmp.length-1]);
					list.add(tmpMap);
				}
			}
		}
        return list;
    }
	 /**
     * 核心转换方法
     * @param number 待转换的double数字
     * @return 直观表示的字符串
     */
    public static String numberFormat(double number) {
    	// 单位常量定义
         String[] UNITS = {"", "万", "亿", "万亿"};
        // 单位对应的数值量级
         double[] SCALES = {1, 10000, 100000000, 1000000000000L};
        // 最大支持的量级（万亿）
         int MAX_UNIT_INDEX = UNITS.length - 1;
        // 处理NaN和无穷大的特殊情况
        if (Double.isNaN(number) || Double.isInfinite(number)) {
            return String.valueOf(number);
        }
        // 将double转换为BigDecimal，避免浮点数精度问题
        BigDecimal bigDecimal = new BigDecimal(number);
        // 找到合适的单位（从大到小匹配）
        int unitIndex = 0;
        for (int i = MAX_UNIT_INDEX; i >= 0; i--) {
            if (bigDecimal.abs().compareTo(BigDecimal.valueOf(SCALES[i])) >= 0) {
                unitIndex = i;
                break;
            }
        }
        // 计算转换后的数值（除以对应量级）
        BigDecimal scaledValue = bigDecimal.divide(
                BigDecimal.valueOf(SCALES[unitIndex]),
                2, // 保留最多2位小数
                RoundingMode.HALF_UP // 四舍五入
        );
        // 去掉末尾的零和多余的小数点
     // 转换为字符串，自动去掉末尾零
        String formatted = scaledValue.stripTrailingZeros().toPlainString();
        // 处理整数情况（如1500.0 → 1500）
        if (formatted.contains(".")) {
            // 去掉末尾的小数点（如果小数点后全是零）
        	formatted = formatted.replaceAll("\\.0*$", "");
        }
        // 拼接单位返回
        return formatted + UNITS[unitIndex];
    }
	/**
	 * 根据返回列表进行分页
	 * @param list
	 * @param maxNum
	 * @param perNum
	 * @return
	 */
	public static List<List> splitList(List list,int maxNum,int perNum){
		List<List> list2 = new ArrayList<List>();
		List<Object> list3 = PageUtils.getCurrentPageList(list, 1, maxNum);
		int totalPage =0;
		if(list.size()>0){
			if(list.size()%perNum==0){
				totalPage = list3.size()/perNum;	
			}else{
				totalPage = list3.size()/perNum+1;
			}
		}
		//1页  0~3
		//2  4-7
		//3 8-11
		for(int i=1;i<=totalPage;i++){
			if(list3.size()<((i)*perNum)){
				List<Object> tmp = new ArrayList<Object>();
				tmp.addAll(list3.subList((i-1)*perNum, list3.size()));
				list2.add(tmp);
			}else{
				List<Object> tmp = new ArrayList<Object>();
				tmp.addAll(list3.subList((i-1)*perNum, (i-1)*perNum+(perNum)));
				list2.add(tmp);
			}
		}
		return list2;
	}
	//本季度的第一个日期
	 public static void setToQuarterFirstDay(Calendar cal) {
        // 1. 获取当前月份（0-11，对应1-12月）
        int currentMonth = cal.get(Calendar.MONTH);
        // 2. 判断所属季度，计算季度第一个月份
        int quarterFirstMonth;
        if (currentMonth >= 0 && currentMonth <= 2) { // 1-3月（Q1）
            quarterFirstMonth = Calendar.JANUARY; // 0
        } else if (currentMonth >= 3 && currentMonth <= 5) { // 4-6月（Q2）
            quarterFirstMonth = Calendar.APRIL; // 3
        } else if (currentMonth >= 6 && currentMonth <= 8) { // 7-9月（Q3）
            quarterFirstMonth = Calendar.JULY; // 6
        } else { // 10-12月（Q4）
            quarterFirstMonth = Calendar.OCTOBER; // 9
        }
        // 3. 设置为季度第一个月的1日（年份保持不变）
        cal.set(Calendar.MONTH, quarterFirstMonth);
        cal.set(Calendar.DAY_OF_MONTH, 1);
    }
		//上一个季度的第一个日期
	   public static void setToLastQuarterFirstDay(Calendar cal) {
        // 1. 获取当前月份（0-11，对应1-12月）
        int currentMonth = cal.get(Calendar.MONTH);
        // 2. 获取当前年份
        int currentYear = cal.get(Calendar.YEAR);
        // 3. 计算上季度的首月和对应年份（处理跨年）
        int lastQuarterFirstMonth;
        int lastQuarterYear = currentYear;
        if (currentMonth >= 0 && currentMonth <= 2) { // 当前是Q1（1-3月）→ 上季度是去年Q4
            lastQuarterFirstMonth = Calendar.OCTOBER; // Q4首月：10月（对应Calendar的9）
            lastQuarterYear -= 1; // 年份减1
        } else if (currentMonth >= 3 && currentMonth <= 5) { // 当前是Q2（4-6月）→ 上季度是Q1
            lastQuarterFirstMonth = Calendar.JANUARY; // Q1首月：1月（对应Calendar的0）
        } else if (currentMonth >= 6 && currentMonth <= 8) { // 当前是Q3（7-9月）→ 上季度是Q2
            lastQuarterFirstMonth = Calendar.APRIL; // Q2首月：4月（对应Calendar的3）
        } else { // 当前是Q4（10-12月）→ 上季度是Q3
            lastQuarterFirstMonth = Calendar.JULY; // Q3首月：7月（对应Calendar的6）
        }
        // 4. 设置为上季度首月的1日，年份更新为上季度年份
        cal.set(lastQuarterYear, lastQuarterFirstMonth, 1);
    }
     /**
  	获取指定年份的四个季度日期范围，返回List<Map<String, Object>>格式
     * @param year 目标年份（如2026、2024）
     * @return List<Map<String, Object>>：
     *         每个Map对应一个季度，包含3个字段：
     *         - "startDate"：String类型，季度开始日期（yyyy-MM-dd）
     *         - "endDate"：String类型，季度结束日期（yyyy-MM-dd）
     */
     public static List<Map<String, Object>> getYearQuarterRanges(int year) {
        List<Map<String, Object>> quarterList = new ArrayList<>();
 	SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd", Locale.CHINA);
        // 定义四个季度的首月、末月（Calendar月份：0=1月，11=12月），按Q1-Q4顺序排列
        int[][] quarterConfig = {
                {Calendar.JANUARY, Calendar.MARCH},     // 第1季度：1-3月
                {Calendar.APRIL, Calendar.JUNE},        // 第2季度：4-6月
                {Calendar.JULY, Calendar.SEPTEMBER},    // 第3季度：7-9月
                {Calendar.OCTOBER, Calendar.DECEMBER}   // 第4季度：10-12月
        };
        for (int[] config : quarterConfig) {
            int startMonth = config[0];       // 季度首月
            int endMonth = config[1];         // 季度末月
            // 1. 计算季度开始日期（首月1日）
            Calendar startCal = Calendar.getInstance();
            startCal.set(year, startMonth, 1);
            String startDate = sdf.format(startCal.getTime());
            // 2. 计算季度结束日期（末月最后一天，自动适配闰年/月末）
            Calendar endCal = Calendar.getInstance();
            endCal.set(year, endMonth, 1);    // 先设为末月1日
            endCal.add(Calendar.MONTH, 1);    // 加1个月（如3月→4月）
            endCal.add(Calendar.DAY_OF_MONTH, -1); // 减1天→末月最后一天
            String endDate = sdf.format(endCal.getTime());
            // 3. 构建单个季度的Map（仅保留startDate和endDate）
            Map<String, Object> quarterMap = new HashMap<>();
            quarterMap.put("startDate", startDate);      // 季度开始日期
            quarterMap.put("endDate", endDate);          // 季度结束日期
            // 4. 将季度Map添加到List中（顺序：Q1→Q2→Q3→Q4）
            quarterList.add(quarterMap);
        }
        return quarterList;
    }
    //获取本周开始日期
     public static String getWeekStartDate() {
        // 1. 获取当前日期的Calendar实例
        Calendar cal = Calendar.getInstance();
        // 2. 清空时分秒，保证仅处理日期维度
        cal.set(Calendar.HOUR_OF_DAY, 0);
        cal.set(Calendar.MINUTE, 0);
        cal.set(Calendar.SECOND, 0);
        cal.set(Calendar.MILLISECOND, 0);
        // 3. 核心：调整到本周一（中国习惯的周起始日）
        // Calendar.DAY_OF_WEEK规则：1=周日，2=周一，3=周二...7=周六
        int currentDayOfWeek = cal.get(Calendar.DAY_OF_WEEK);
        // 计算需要回退的天数：周日(1)回退6天，周一(2)回退0天，周二(3)回退1天...
        int daysToBack = (currentDayOfWeek == 1) ? 6 : (currentDayOfWeek - 2);
        cal.add(Calendar.DAY_OF_MONTH, -daysToBack);
        // 4. 格式化输出为yyyy-MM-dd
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd", Locale.CHINA);
        return sdf.format(cal.getTime());
    }
    //上周第一个日期
    public static String getLastWeekStartDate() {
        // 1. 获取当前日期的Calendar实例
        Calendar cal = Calendar.getInstance();
        // 清空时分秒，仅处理日期维度
        cal.set(Calendar.HOUR_OF_DAY, 0);
        cal.set(Calendar.MINUTE, 0);
        cal.set(Calendar.SECOND, 0);
        cal.set(Calendar.MILLISECOND, 0);
        // 2. 先调整到本周一（本周起始日）
        int currentDayOfWeek = cal.get(Calendar.DAY_OF_WEEK);
        int daysToBackToThisWeekMonday = (currentDayOfWeek == 1) ? 6 : (currentDayOfWeek - 2);
        cal.add(Calendar.DAY_OF_MONTH, -daysToBackToThisWeekMonday);
        // 3. 再往前推7天，得到上周一（上周起始日）
        cal.add(Calendar.DAY_OF_MONTH, -7);
        // 4. 格式化输出为yyyy-MM-dd
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd", Locale.CHINA);
        return sdf.format(cal.getTime());
    }
    /**
 * 计算两个经纬度字符串（lng,lat）之间的距离（千米为单位）
 * @param lngLat1 第一个点的经纬度字符串，格式："经度,纬度"（如："116.397470,39.908823"）
 * @param lngLat2 第二个点的经纬度字符串，格式同上
 * @return 两点间距离（单位：千米，保留2位小数）；参数异常/计算失败返回-1.0
 */
public static double calculateDistanceKm(String lngLat1, String lngLat2) {
    // 地球半径（千米），WGS84坐标系标准值
    final double EARTH_RADIUS = 6371.0;
    try {
        // 1. 校验参数非空
        if (lngLat1 == null || lngLat1.trim().isEmpty() || lngLat2 == null || lngLat2.trim().isEmpty()) {
            System.err.println("经纬度字符串不能为空！");
            return -1.0;
        }
        // 2. 拆分经纬度字符串（按英文逗号分割）
        String[] lngLatArr1 = lngLat1.trim().split(",");
        String[] lngLatArr2 = lngLat2.trim().split(",");
        // 校验拆分结果是否为2个元素（经度+纬度）
        if (lngLatArr1.length != 2 || lngLatArr2.length != 2) {
            System.err.println("经纬度格式错误！正确格式：\"经度,纬度\"（如116.397470,39.908823）");
            return -1.0;
        }
        // 3. 字符串转double类型（经度/纬度）
        double lng1 = Double.parseDouble(lngLatArr1[0].trim());
        double lat1 = Double.parseDouble(lngLatArr1[1].trim());
        double lng2 = Double.parseDouble(lngLatArr2[0].trim());
        double lat2 = Double.parseDouble(lngLatArr2[1].trim());
        // 4. 校验经纬度范围（经度[-180,180]，纬度[-90,90]）
        if (!isValidLng(lng1) || !isValidLat(lat1) || !isValidLng(lng2) || !isValidLat(lat2)) {
            System.err.println("经纬度超出合法范围！经度[-180,180]，纬度[-90,90]");
            return -1.0;
        }
        // 5. 角度转弧度（三角函数计算必需）
        double radLng1 = Math.toRadians(lng1);
        double radLat1 = Math.toRadians(lat1);
        double radLng2 = Math.toRadians(lng2);
        double radLat2 = Math.toRadians(lat2);
        // 6. Haversine公式核心计算球面距离
        double deltaLng = radLng1 - radLng2;
        double deltaLat = radLat1 - radLat2;
        double a = Math.sin(deltaLat / 2) * Math.sin(deltaLat / 2) +
                   Math.cos(radLat1) * Math.cos(radLat2) *
                   Math.sin(deltaLng / 2) * Math.sin(deltaLng / 2);
        double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));
        double distanceKm = EARTH_RADIUS * c;
        // 7. 保留2位小数（业务常用精度），返回千米单位结果
        return Math.round(distanceKm * 100) / 100.0;
    } catch (NumberFormatException e) {
        System.err.println("经纬度字符串转换为数字失败：" + e.getMessage());
        return -1.0;
    } catch (Exception e) {
        System.err.println("计算经纬度距离异常：" + e.getMessage());
        return -1.0;
    }
}
/**
 * 校验经度是否合法（辅助方法）
 */
private static boolean isValidLng(double lng) {
    return lng >= -180.0 && lng <= 180.0;
}
/**
 * 校验纬度是否合法（辅助方法）
 */
private static boolean isValidLat(double lat) {
    return lat >= -90.0 && lat <= 90.0;
}
/**
 * 通用List分页截取工具方法
 * @param <T> List中元素的泛型类型（适配任意类型，如String、Product、Integer等）
 * @param page 页码（从1开始，传入<=0的数值会自动修正为1）
 * @param pageSize 每页条数（传入<=0的数值会自动修正为10）
 * @param list 原始待分页的List（为null时返回空List）
 * @return 分页后的子List，无数据时返回空List（而非null）
 */
public static <T> List<T> pageList(int page, int pageSize, List<T> list) {
    // 1. 边界校验：原始List为null/空，直接返回空List
    if (list == null || list.isEmpty()) {
        return new ArrayList<>();
    }
    // 2. 修正非法的页码/页大小（避免业务传参错误导致异常）
    int validPage = page <= 0 ? 1 : page; // 页码<=0则取第1页
    int validPageSize = pageSize <= 0 ? 10 : pageSize; // 页大小<=0则默认每页10条
    // 3. 计算分页起始/结束索引
    int startIndex = (validPage - 1) * validPageSize; // 起始索引（从0开始）
    int endIndex = Math.min(startIndex + validPageSize, list.size()); // 结束索引（不超过List总长度）
    // 4. 处理起始索引超出数据范围的情况（比如总共有5条，取第2页、每页10条，返回空）
    if (startIndex >= list.size()) {
        return new ArrayList<>();
    }
    // 5. 截取子列表（subList返回的是原List的视图，转成新ArrayList避免原List修改影响结果）
    List<T> pageList = list.subList(startIndex, endIndex);
    return new ArrayList<>(pageList); // 转为新List，解除与原List的关联
}
}
