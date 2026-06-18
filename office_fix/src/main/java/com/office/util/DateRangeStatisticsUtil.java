package com.office.util;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.Month;
import java.time.format.DateTimeFormatter;
import java.time.temporal.IsoFields;
import java.time.temporal.TemporalAdjusters;
import java.util.*;
/**
* 时间范围统计工具类（最终优化版：周维度按“前N周”逻辑调整）
* 核心优化：周维度改为“以当前周为最后一周，向前推11周”，符合“前12周”常规认知
*/
public class DateRangeStatisticsUtil {
    // ===================== 基础常量定义 =====================
    private static final DateTimeFormatter DATE_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd");
    private static final DateTimeFormatter MONTH_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM");
    private static final DateTimeFormatter YEAR_FORMATTER = DateTimeFormatter.ofPattern("yyyy");
    private static final String[] WEEK_DAY_NAMES = {"周一", "周二", "周三", "周四", "周五", "周六", "周日"};
        // ===================== 兼容工具方法：季度日期计算 =====================
        private static LocalDate getFirstDayOfQuarter(LocalDate date) {
            int year = date.getYear();
            Month month = date.getMonth();
            Month firstMonthOfQuarter = Month.of(((month.getValue() - 1) / 3) * 3 + 1);
            return LocalDate.of(year, firstMonthOfQuarter, 1);
        }
        private static LocalDate getLastDayOfQuarter(LocalDate date) {
            int year = date.getYear();
            Month month = date.getMonth();
            Month lastMonthOfQuarter = Month.of(((month.getValue() - 1) / 3) * 3 + 3);
            return LocalDate.of(year, lastMonthOfQuarter, 1).with(TemporalAdjusters.lastDayOfMonth());
        }
        // ===================== 数值格式化工具方法 =====================
        private static Object formatNumber(Object value) {
            if (value == null) {
                return 0;
            }
            if (value instanceof Double || value instanceof Float) {
                BigDecimal bd = new BigDecimal(value.toString());
                return bd.setScale(2, RoundingMode.HALF_UP).doubleValue();
            }
            return value;
        }
        // ===================== 基础时间范围方法（核心优化：周维度范围） =====================
        public static Map<String, String> getDateRange(Integer dateRange) {
            if (dateRange == null || dateRange < 1 || dateRange > 5) {
                throw new IllegalArgumentException("dateRange参数非法，仅支持1(近7天)/2(近12周)/3(近12个月)/4(近4季度)/5(近5年)");
            }
            LocalDate startDate = null;
            LocalDate endDate = LocalDate.now(); // 结束日期固定为当前日期
            LocalDate now = endDate;
            switch (dateRange) {
                case 1: // 近7天：本周一 → 当前日期
                startDate = now.with(TemporalAdjusters.previousOrSame(DayOfWeek.MONDAY));
                break;
                case 2: // 前12周：当前周向前推11周的周一 → 当前日期（核心优化）
                // 当前周的周一
                LocalDate currentWeekMonday = now.with(TemporalAdjusters.previousOrSame(DayOfWeek.MONDAY));
                // 前12周的第一周：当前周向前推11周
                startDate = currentWeekMonday.minusWeeks(11);
                break;
                case 3: // 近12个月：12个月前当月第一天 → 当前日期
                startDate = now.minusMonths(11).with(TemporalAdjusters.firstDayOfMonth());
                break;
                case 4: // 近4季度：4季度前当季第一天 → 当前日期
                startDate = getFirstDayOfQuarter(now.minusMonths(9));
                break;
                case 5: // 近5年：5年前当年第一天 → 当前日期
                startDate = now.minusYears(4).with(TemporalAdjusters.firstDayOfYear());
                break;
            }
            Map<String, String> resultMap = new HashMap<>(2);
            resultMap.put("startDate", startDate.format(DATE_FORMATTER));
            resultMap.put("endDate", endDate.format(DATE_FORMATTER));
            return resultMap;
        }
        public static List<Map<String, Object>> getDateList(Integer dateRange) {
            if (dateRange == null || dateRange < 1 || dateRange > 5) {
                throw new IllegalArgumentException("dateRange参数非法，仅支持1(近7天)/2(近12周)/3(近12个月)/4(近4季度)/5(近5年)");
            }
            List<Map<String, Object>> dateList = new ArrayList<>();
            LocalDate now = LocalDate.now();
            switch (dateRange) {
                case 1: // 近7天：周一→周日（结束日期不超当前日期）
                LocalDate monday = now.with(TemporalAdjusters.previousOrSame(DayOfWeek.MONDAY));
                for (int i = 0; i < 7; i++) {
                    LocalDate currentDay = monday.plusDays(i);
                    LocalDate dayEndDate = currentDay.isAfter(now) ? now : currentDay;
                    Map<String, Object> periodMap = new HashMap<>(3);
                    periodMap.put("name", WEEK_DAY_NAMES[i]);
                    periodMap.put("startDate", currentDay.format(DATE_FORMATTER));
                    periodMap.put("endDate", dayEndDate.format(DATE_FORMATTER));
                    dateList.add(periodMap);
                }
                break;
                case 2: // 前12周：第1周（最早）→第12周（当前周），正序（核心优化）
                // 当前周的周一
                LocalDate currentWeekMonday = now.with(TemporalAdjusters.previousOrSame(DayOfWeek.MONDAY));
                // 遍历前12周（从最早的第1周到最近的第12周）
                for (int i = 0; i < 12; i++) {
                    // 第i+1周的周一：当前周向前推(11-i)周（i=0→前11周，i=11→当前周）
                    LocalDate weekMonday = currentWeekMonday.minusWeeks(11 - i);
                    LocalDate weekSunday = weekMonday.plusDays(6);
                    // 本周结束日期不超当前日期
                    LocalDate weekEndDate = weekSunday.isAfter(now) ? now : weekSunday;
                    Map<String, Object> periodMap = new HashMap<>(3);
                    periodMap.put("name", "第" + (i + 1) + "周"); // 简化命名：第1周→第12周
                    periodMap.put("startDate", weekMonday.format(DATE_FORMATTER));
                    periodMap.put("endDate", weekEndDate.format(DATE_FORMATTER));
                    dateList.add(periodMap);
                }
                break;
                case 3: // 近12个月：YYYY-MM，正序
                LocalDate firstMonthStart = now.minusMonths(11).with(TemporalAdjusters.firstDayOfMonth());
                for (int i = 0; i < 12; i++) {
                    LocalDate monthStart = firstMonthStart.plusMonths(i);
                    LocalDate monthEnd = monthStart.with(TemporalAdjusters.lastDayOfMonth());
                    LocalDate monthEndDate = monthEnd.isAfter(now) ? now : monthEnd;
                    Map<String, Object> periodMap = new HashMap<>(3);
                    periodMap.put("name", monthStart.format(MONTH_FORMATTER));
                    periodMap.put("startDate", monthStart.format(DATE_FORMATTER));
                    periodMap.put("endDate", monthEndDate.format(DATE_FORMATTER));
                    dateList.add(periodMap);
                }
                break;
                case 4: // 近4季度：YYYYQN，正序
                LocalDate firstQuarterStart = getFirstDayOfQuarter(now.minusMonths(9));
                for (int i = 0; i < 4; i++) {
                    LocalDate quarterStart = getFirstDayOfQuarter(firstQuarterStart.plusMonths(i * 3));
                    LocalDate quarterEnd = getLastDayOfQuarter(quarterStart);
                    LocalDate quarterEndDate = quarterEnd.isAfter(now) ? now : quarterEnd;
                    int year = quarterStart.getYear();
                    int quarter = (quarterStart.getMonthValue() - 1) / 3 + 1;
                    Map<String, Object> periodMap = new HashMap<>(3);
                    periodMap.put("name", year + "Q" + quarter);
                    periodMap.put("startDate", quarterStart.format(DATE_FORMATTER));
                    periodMap.put("endDate", quarterEndDate.format(DATE_FORMATTER));
                    dateList.add(periodMap);
                }
                break;
                case 5: // 近5年：YYYY年，正序
                LocalDate firstYearStart = now.minusYears(4).with(TemporalAdjusters.firstDayOfYear());
                for (int i = 0; i < 5; i++) {
                    LocalDate yearStart = firstYearStart.plusYears(i);
                    LocalDate yearEnd = yearStart.with(TemporalAdjusters.lastDayOfYear());
                    LocalDate yearEndDate = yearEnd.isAfter(now) ? now : yearEnd;
                    Map<String, Object> periodMap = new HashMap<>(3);
                    periodMap.put("name", yearStart.format(YEAR_FORMATTER) + "年");
                    periodMap.put("startDate", yearStart.format(DATE_FORMATTER));
                    periodMap.put("endDate", yearEndDate.format(DATE_FORMATTER));
                    dateList.add(periodMap);
                }
                break;
            }
            return dateList;
        }
        // ===================== 以下方法无修改，保持原样 =====================
        public static Map<String, Object> convertToChartData(List<Map<String, Object>> dlist) {
            return convertToChartData(dlist, 0);
        }
        public static Map<String, Object> convertToChartData(List<Map<String, Object>> dlist, int index) {
            Map<String, Object> chartData = new HashMap<>(3);
            if (dlist == null || dlist.isEmpty()) {
                chartData.put("xValue", new ArrayList());
                chartData.put("yValue", new ArrayList());
                chartData.put("yname", "");
                return chartData;
            }
            if (index < 0) {
                throw new IllegalArgumentException("Y轴维度索引不能为负数");
            }
            List<String> xValue = new ArrayList<>();
            List<Object> yValue = new ArrayList<>();
            String yname = "";
            for (Map<String, Object> dateMap : dlist) {
                Object dataShowObj = dateMap.get("dataShow");
                if (isValidDataShow(dataShowObj, index)) {
                    List<Map<String, Object>> dataShowList = (List<Map<String, Object>>) dataShowObj;
                    Map<String, Object> targetData = dataShowList.get(index);
                    yname = Optional.ofNullable(targetData.get("yZhName")).map(Object::toString).orElse("");
                    break;
                }
            }
            for (Map<String, Object> dateMap : dlist) {
                xValue.add(Optional.ofNullable(dateMap.get("name")).map(Object::toString).orElse(""));
                Object value = 0;
                Object dataShowObj = dateMap.get("dataShow");
                if (isValidDataShow(dataShowObj, index)) {
                    List<Map<String, Object>> dataShowList = (List<Map<String, Object>>) dataShowObj;
                    Map<String, Object> targetData = dataShowList.get(index);
                    value = Optional.ofNullable(targetData.get("value")).orElse(0);
                }
                yValue.add(formatNumber(value));
            }
            chartData.put("xValue", xValue);
            chartData.put("yValue", yValue);
            chartData.put("yname", yname);
            return chartData;
        }
        public static Map<String, Object> convertToMultiDimensionChartData(List<Map<String, Object>> dlist) {
            Map<String, Object> chartData = new HashMap<>(2);
            if (dlist == null || dlist.isEmpty()) {
                chartData.put("xValue", new ArrayList());
                chartData.put("yAxisData", new ArrayList());
                return chartData;
            }
            List<String> xValue = new ArrayList<>();
            List<Map<String, Object>> yAxisData = new ArrayList<>();
            List<String> allYNames = new ArrayList<>();
            for (Map<String, Object> dateMap : dlist) {
                Object dataShowObj = dateMap.get("dataShow");
                if (dataShowObj instanceof List && !((List<?>) dataShowObj).isEmpty()) {
                    List<Map<String, Object>> dataShowList = (List<Map<String, Object>>) dataShowObj;
                    for (Map<String, Object> data : dataShowList) {
                        String yName = Optional.ofNullable(data.get("yZhName")).map(Object::toString).orElse("");
                        if (!yName.isEmpty() && !allYNames.contains(yName)) {
                            allYNames.add(yName);
                        }
                    }
                    break;
                }
            }
            Map<String, List<Object>> yValuesTempMap = new HashMap<>();
            for (String yName : allYNames) {
                yValuesTempMap.put(yName, new ArrayList<>());
            }
            for (Map<String, Object> dateMap : dlist) {
                xValue.add(Optional.ofNullable(dateMap.get("name")).map(Object::toString).orElse(""));
                Map<String, Object> currentYValueMap = new HashMap<>();
                for (String yName : allYNames) {
                    currentYValueMap.put(yName, 0);
                }
                Object dataShowObj = dateMap.get("dataShow");
                if (dataShowObj instanceof List && !((List<?>) dataShowObj).isEmpty()) {
                    List<Map<String, Object>> dataShowList = (List<Map<String, Object>>) dataShowObj;
                    for (Map<String, Object> data : dataShowList) {
                        String yName = Optional.ofNullable(data.get("yZhName")).map(Object::toString).orElse("");
                        Object value = Optional.ofNullable(data.get("value")).orElse(0);
                        if (allYNames.contains(yName)) {
                            currentYValueMap.put(yName, formatNumber(value));
                        }
                    }
                }
                for (String yName : allYNames) {
                    yValuesTempMap.get(yName).add(currentYValueMap.get(yName));
                }
            }
            for (String yName : allYNames) {
                Map<String, Object> yAxisItem = new HashMap<>(2);
                yAxisItem.put("yname", yName);
                yAxisItem.put("yValues", yValuesTempMap.get(yName));
                yAxisData.add(yAxisItem);
            }
            chartData.put("xValue", xValue);
            chartData.put("yAxisData", yAxisData);
            return chartData;
        }
        private static boolean isValidDataShow(Object dataShowObj, int index) {
            return dataShowObj instanceof List
            && ((List<?>) dataShowObj).size() > index
            && ((List<?>) dataShowObj).get(index) instanceof Map;
        }
        // ===================== 测试方法 =====================
        public static void main(String[] args) {
            // 测试前12周范围和列表
            System.out.println("===== 测试前12周范围 =====");
            Map<String, String> weekRange = getDateRange(2);
            System.out.println("前12周整体范围：" + weekRange);
            System.out.println("\n===== 测试前12周列表（正序） =====");
            List<Map<String, Object>> weekList = getDateList(2);
            for (Map<String, Object> week : weekList) {
                System.out.println(week);
            }
        }
    }
