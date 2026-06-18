package com.office.model;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
public class PjReqExample {
	protected String orderByClause;
	protected boolean distinct;
	protected int startRow;
	protected int pageRows;
	protected List<Criteria> oredCriteria;
	public PjReqExample() {
		oredCriteria = new ArrayList<>();
	}
	public void setOrderByClause(String orderByClause) {
		this.orderByClause = orderByClause;
	}
	public String getOrderByClause() {
		return orderByClause;
	}
	public void setDistinct(boolean distinct) {
		this.distinct = distinct;
	}
	public boolean isDistinct() {
		return distinct;
	}
	public void setStartRow(int startRow) {
		this.startRow = startRow;
	}
	public int getStartRow() {
		return startRow;
	}
	public void setPageRows(int pageRows) {
		this.pageRows = pageRows;
	}
	public int getPageRows() {
		return pageRows;
	}
	public List<Criteria> getOredCriteria() {
		return oredCriteria;
	}
	public void or(Criteria criteria) {
		oredCriteria.add(criteria);
	}
	public Criteria or() {
		Criteria criteria = createCriteriaInternal();
		oredCriteria.add(criteria);
		return criteria;
	}
	public Criteria createCriteria() {
		Criteria criteria = createCriteriaInternal();
		if (oredCriteria.size() == 0) {
			oredCriteria.add(criteria);
		}
		return criteria;
	}
	protected Criteria createCriteriaInternal() {
		Criteria criteria = new Criteria();
		return criteria;
	}
	public void clear() {
		oredCriteria.clear();
		orderByClause = null;
		distinct = false;
	}
	protected abstract static class GeneratedCriteria {
		protected List<Criterion> criteria;
		protected GeneratedCriteria() {
			super();
			criteria = new ArrayList<>();
		}
		public boolean isValid() {
			return criteria.size() > 0;
		}
		public List<Criterion> getAllCriteria() {
			return criteria;
		}
		public List<Criterion> getCriteria() {
			return criteria;
		}
		protected void addCriterion(String condition) {
			if (condition == null) {
				throw new RuntimeException("Value for condition cannot be null");
			}
			criteria.add(new Criterion(condition));
		}
		protected void addCriterion(String condition, Object value, String property) {
			if (value == null) {
				throw new RuntimeException("Value for " + property + " cannot be null");
			}
			criteria.add(new Criterion(condition, value));
		}
		protected void addCriterion(String condition, Object value1, Object value2, String property) {
			if (value1 == null || value2 == null) {
				throw new RuntimeException("Between values for " + property + " cannot be null");
			}
			criteria.add(new Criterion(condition, value1, value2));
		}
		public Criteria andIdIsNull() {
			addCriterion("id is null");
			return (Criteria) this;
		}
		public Criteria andIdIsNotNull(){
			addCriterion("id is not null");
			return (Criteria) this;
		}
		public Criteria andIdEqualTo(Long value) {
			addCriterion("id =", value, "id");
			return (Criteria) this;
		}
		public Criteria andIdNotEqualTo(Long value) {
			addCriterion("id <>", value, "id");
			return (Criteria) this;
		}
		public Criteria andIdGreaterThan(Long value) {
			addCriterion("id >", value, "id");
			return (Criteria) this;
		}
		public Criteria andIdGreaterThanOrEqualTo(Long value) {
			addCriterion("id >=", value, "Id");
			return (Criteria) this;
		}
		public Criteria andIdLessThan(Long value) {
			addCriterion("id <", value, "id");
			return (Criteria) this;
		}
		public Criteria andIdLessThanOrEqualTo(Long value) {
			addCriterion("id <=", value, "id");
			return (Criteria) this;
		}
		public Criteria andIdIn(List<Long> values) {
			if(values.size()==0){
				addCriterion("id =", 0, "id");
				return (Criteria) this;
			}
			addCriterion("id in", values, "id");
			return (Criteria) this;
		}
		public Criteria andIdNotIn(List<Long> values) {
			addCriterion("id not in", values, "id");
			return (Criteria) this;
		}
		public Criteria andIdBetween(Long value1, Long value2) {
			addCriterion("id between", value1, value2, "id");
			return (Criteria) this;
		}
		public Criteria andIdNotBetween(Long value1, Long value2) {
			addCriterion("id not between", value1, value2, "id");
			return (Criteria) this;
		}
		public Criteria andIdLike(Long value) {
			addCriterion("id like", value, "id");
			return (Criteria) this;
		}
		public Criteria andIdNotLike(Long value) {
			addCriterion("id not like", value, "id");
			return (Criteria) this;
		}
		public Criteria andReqNoIsNull() {
			addCriterion("req_no is null");
			return (Criteria) this;
		}
		public Criteria andReqNoIsNotNull(){
			addCriterion("req_no is not null");
			return (Criteria) this;
		}
		public Criteria andReqNoEqualTo(String value) {
			addCriterion("req_no =", value, "reqNo");
			return (Criteria) this;
		}
		public Criteria andReqNoNotEqualTo(String value) {
			addCriterion("req_no <>", value, "reqNo");
			return (Criteria) this;
		}
		public Criteria andReqNoGreaterThan(String value) {
			addCriterion("req_no >", value, "reqNo");
			return (Criteria) this;
		}
		public Criteria andReqNoGreaterThanOrEqualTo(String value) {
			addCriterion("req_no >=", value, "ReqNo");
			return (Criteria) this;
		}
		public Criteria andReqNoLessThan(String value) {
			addCriterion("req_no <", value, "reqNo");
			return (Criteria) this;
		}
		public Criteria andReqNoLessThanOrEqualTo(String value) {
			addCriterion("req_no <=", value, "reqNo");
			return (Criteria) this;
		}
		public Criteria andReqNoIn(List<String> values) {
			addCriterion("req_no in", values, "reqNo");
			return (Criteria) this;
		}
		public Criteria andReqNoNotIn(List<String> values) {
			addCriterion("req_no not in", values, "reqNo");
			return (Criteria) this;
		}
		public Criteria andReqNoBetween(String value1, String value2) {
			addCriterion("req_no between", value1, value2, "reqNo");
			return (Criteria) this;
		}
		public Criteria andReqNoNotBetween(String value1, String value2) {
			addCriterion("req_no not between", value1, value2, "reqNo");
			return (Criteria) this;
		}
		public Criteria andReqNoLike(String value) {
			addCriterion("req_no like", value, "reqNo");
			return (Criteria) this;
		}
		public Criteria andReqNoNotLike(String value) {
			addCriterion("req_no not like", value, "reqNo");
			return (Criteria) this;
		}
		public Criteria andPjNameIsNull() {
			addCriterion("pj_name is null");
			return (Criteria) this;
		}
		public Criteria andPjNameIsNotNull(){
			addCriterion("pj_name is not null");
			return (Criteria) this;
		}
		public Criteria andPjNameEqualTo(String value) {
			addCriterion("pj_name =", value, "pjName");
			return (Criteria) this;
		}
		public Criteria andPjNameNotEqualTo(String value) {
			addCriterion("pj_name <>", value, "pjName");
			return (Criteria) this;
		}
		public Criteria andPjNameGreaterThan(String value) {
			addCriterion("pj_name >", value, "pjName");
			return (Criteria) this;
		}
		public Criteria andPjNameGreaterThanOrEqualTo(String value) {
			addCriterion("pj_name >=", value, "PjName");
			return (Criteria) this;
		}
		public Criteria andPjNameLessThan(String value) {
			addCriterion("pj_name <", value, "pjName");
			return (Criteria) this;
		}
		public Criteria andPjNameLessThanOrEqualTo(String value) {
			addCriterion("pj_name <=", value, "pjName");
			return (Criteria) this;
		}
		public Criteria andPjNameIn(List<String> values) {
			addCriterion("pj_name in", values, "pjName");
			return (Criteria) this;
		}
		public Criteria andPjNameNotIn(List<String> values) {
			addCriterion("pj_name not in", values, "pjName");
			return (Criteria) this;
		}
		public Criteria andPjNameBetween(String value1, String value2) {
			addCriterion("pj_name between", value1, value2, "pjName");
			return (Criteria) this;
		}
		public Criteria andPjNameNotBetween(String value1, String value2) {
			addCriterion("pj_name not between", value1, value2, "pjName");
			return (Criteria) this;
		}
		public Criteria andPjNameLike(String value) {
			addCriterion("pj_name like", value, "pjName");
			return (Criteria) this;
		}
		public Criteria andPjNameNotLike(String value) {
			addCriterion("pj_name not like", value, "pjName");
			return (Criteria) this;
		}
		public Criteria andXyNumIsNull() {
			addCriterion("xy_num is null");
			return (Criteria) this;
		}
		public Criteria andXyNumIsNotNull(){
			addCriterion("xy_num is not null");
			return (Criteria) this;
		}
		public Criteria andXyNumEqualTo(Integer value) {
			addCriterion("xy_num =", value, "xyNum");
			return (Criteria) this;
		}
		public Criteria andXyNumNotEqualTo(Integer value) {
			addCriterion("xy_num <>", value, "xyNum");
			return (Criteria) this;
		}
		public Criteria andXyNumGreaterThan(Integer value) {
			addCriterion("xy_num >", value, "xyNum");
			return (Criteria) this;
		}
		public Criteria andXyNumGreaterThanOrEqualTo(Integer value) {
			addCriterion("xy_num >=", value, "XyNum");
			return (Criteria) this;
		}
		public Criteria andXyNumLessThan(Integer value) {
			addCriterion("xy_num <", value, "xyNum");
			return (Criteria) this;
		}
		public Criteria andXyNumLessThanOrEqualTo(Integer value) {
			addCriterion("xy_num <=", value, "xyNum");
			return (Criteria) this;
		}
		public Criteria andXyNumIn(List<Integer> values) {
			if(values.size()==0){
				addCriterion("xy_num =", 0, "xyNum");
				return (Criteria) this;
			}
			addCriterion("xy_num in", values, "xyNum");
			return (Criteria) this;
		}
		public Criteria andXyNumNotIn(List<Integer> values) {
			addCriterion("xy_num not in", values, "xyNum");
			return (Criteria) this;
		}
		public Criteria andXyNumBetween(Integer value1, Integer value2) {
			addCriterion("xy_num between", value1, value2, "xyNum");
			return (Criteria) this;
		}
		public Criteria andXyNumNotBetween(Integer value1, Integer value2) {
			addCriterion("xy_num not between", value1, value2, "xyNum");
			return (Criteria) this;
		}
		public Criteria andXyNumLike(Integer value) {
			addCriterion("xy_num like", value, "xyNum");
			return (Criteria) this;
		}
		public Criteria andXyNumNotLike(Integer value) {
			addCriterion("xy_num not like", value, "xyNum");
			return (Criteria) this;
		}
		public Criteria andReqNoteIsNull() {
			addCriterion("req_note is null");
			return (Criteria) this;
		}
		public Criteria andReqNoteIsNotNull(){
			addCriterion("req_note is not null");
			return (Criteria) this;
		}
		public Criteria andReqNoteEqualTo(String value) {
			addCriterion("req_note =", value, "reqNote");
			return (Criteria) this;
		}
		public Criteria andReqNoteNotEqualTo(String value) {
			addCriterion("req_note <>", value, "reqNote");
			return (Criteria) this;
		}
		public Criteria andReqNoteGreaterThan(String value) {
			addCriterion("req_note >", value, "reqNote");
			return (Criteria) this;
		}
		public Criteria andReqNoteGreaterThanOrEqualTo(String value) {
			addCriterion("req_note >=", value, "ReqNote");
			return (Criteria) this;
		}
		public Criteria andReqNoteLessThan(String value) {
			addCriterion("req_note <", value, "reqNote");
			return (Criteria) this;
		}
		public Criteria andReqNoteLessThanOrEqualTo(String value) {
			addCriterion("req_note <=", value, "reqNote");
			return (Criteria) this;
		}
		public Criteria andReqNoteIn(List<String> values) {
			addCriterion("req_note in", values, "reqNote");
			return (Criteria) this;
		}
		public Criteria andReqNoteNotIn(List<String> values) {
			addCriterion("req_note not in", values, "reqNote");
			return (Criteria) this;
		}
		public Criteria andReqNoteBetween(String value1, String value2) {
			addCriterion("req_note between", value1, value2, "reqNote");
			return (Criteria) this;
		}
		public Criteria andReqNoteNotBetween(String value1, String value2) {
			addCriterion("req_note not between", value1, value2, "reqNote");
			return (Criteria) this;
		}
		public Criteria andReqNoteLike(String value) {
			addCriterion("req_note like", value, "reqNote");
			return (Criteria) this;
		}
		public Criteria andReqNoteNotLike(String value) {
			addCriterion("req_note not like", value, "reqNote");
			return (Criteria) this;
		}
		public Criteria andReqStatusIsNull() {
			addCriterion("req_status is null");
			return (Criteria) this;
		}
		public Criteria andReqStatusIsNotNull(){
			addCriterion("req_status is not null");
			return (Criteria) this;
		}
		public Criteria andReqStatusEqualTo(Integer value) {
			addCriterion("req_status =", value, "reqStatus");
			return (Criteria) this;
		}
		public Criteria andReqStatusNotEqualTo(Integer value) {
			addCriterion("req_status <>", value, "reqStatus");
			return (Criteria) this;
		}
		public Criteria andReqStatusGreaterThan(Integer value) {
			addCriterion("req_status >", value, "reqStatus");
			return (Criteria) this;
		}
		public Criteria andReqStatusGreaterThanOrEqualTo(Integer value) {
			addCriterion("req_status >=", value, "ReqStatus");
			return (Criteria) this;
		}
		public Criteria andReqStatusLessThan(Integer value) {
			addCriterion("req_status <", value, "reqStatus");
			return (Criteria) this;
		}
		public Criteria andReqStatusLessThanOrEqualTo(Integer value) {
			addCriterion("req_status <=", value, "reqStatus");
			return (Criteria) this;
		}
		public Criteria andReqStatusIn(List<Integer> values) {
			if(values.size()==0){
				addCriterion("req_status =", 0, "reqStatus");
				return (Criteria) this;
			}
			addCriterion("req_status in", values, "reqStatus");
			return (Criteria) this;
		}
		public Criteria andReqStatusNotIn(List<Integer> values) {
			addCriterion("req_status not in", values, "reqStatus");
			return (Criteria) this;
		}
		public Criteria andReqStatusBetween(Integer value1, Integer value2) {
			addCriterion("req_status between", value1, value2, "reqStatus");
			return (Criteria) this;
		}
		public Criteria andReqStatusNotBetween(Integer value1, Integer value2) {
			addCriterion("req_status not between", value1, value2, "reqStatus");
			return (Criteria) this;
		}
		public Criteria andReqStatusLike(Integer value) {
			addCriterion("req_status like", value, "reqStatus");
			return (Criteria) this;
		}
		public Criteria andReqStatusNotLike(Integer value) {
			addCriterion("req_status not like", value, "reqStatus");
			return (Criteria) this;
		}
		public Criteria andCheckRemarkIsNull() {
			addCriterion("check_remark is null");
			return (Criteria) this;
		}
		public Criteria andCheckRemarkIsNotNull(){
			addCriterion("check_remark is not null");
			return (Criteria) this;
		}
		public Criteria andCheckRemarkEqualTo(String value) {
			addCriterion("check_remark =", value, "checkRemark");
			return (Criteria) this;
		}
		public Criteria andCheckRemarkNotEqualTo(String value) {
			addCriterion("check_remark <>", value, "checkRemark");
			return (Criteria) this;
		}
		public Criteria andCheckRemarkGreaterThan(String value) {
			addCriterion("check_remark >", value, "checkRemark");
			return (Criteria) this;
		}
		public Criteria andCheckRemarkGreaterThanOrEqualTo(String value) {
			addCriterion("check_remark >=", value, "CheckRemark");
			return (Criteria) this;
		}
		public Criteria andCheckRemarkLessThan(String value) {
			addCriterion("check_remark <", value, "checkRemark");
			return (Criteria) this;
		}
		public Criteria andCheckRemarkLessThanOrEqualTo(String value) {
			addCriterion("check_remark <=", value, "checkRemark");
			return (Criteria) this;
		}
		public Criteria andCheckRemarkIn(List<String> values) {
			addCriterion("check_remark in", values, "checkRemark");
			return (Criteria) this;
		}
		public Criteria andCheckRemarkNotIn(List<String> values) {
			addCriterion("check_remark not in", values, "checkRemark");
			return (Criteria) this;
		}
		public Criteria andCheckRemarkBetween(String value1, String value2) {
			addCriterion("check_remark between", value1, value2, "checkRemark");
			return (Criteria) this;
		}
		public Criteria andCheckRemarkNotBetween(String value1, String value2) {
			addCriterion("check_remark not between", value1, value2, "checkRemark");
			return (Criteria) this;
		}
		public Criteria andCheckRemarkLike(String value) {
			addCriterion("check_remark like", value, "checkRemark");
			return (Criteria) this;
		}
		public Criteria andCheckRemarkNotLike(String value) {
			addCriterion("check_remark not like", value, "checkRemark");
			return (Criteria) this;
		}
		public Criteria andCreateTimeIsNull() {
			addCriterion("create_time is null");
			return (Criteria) this;
		}
		public Criteria andCreateTimeIsNotNull(){
			addCriterion("create_time is not null");
			return (Criteria) this;
		}
		public Criteria andCreateTimeEqualTo(String value) {
			addCriterion("create_time =", value, "createTime");
			return (Criteria) this;
		}
		public Criteria andCreateTimeNotEqualTo(String value) {
			addCriterion("create_time <>", value, "createTime");
			return (Criteria) this;
		}
		public Criteria andCreateTimeGreaterThan(String value) {
			addCriterion("create_time >", value, "createTime");
			return (Criteria) this;
		}
		public Criteria andCreateTimeGreaterThanOrEqualTo(String value) {
			addCriterion("create_time >=", value, "CreateTime");
			return (Criteria) this;
		}
		public Criteria andCreateTimeLessThan(String value) {
			addCriterion("create_time <", value, "createTime");
			return (Criteria) this;
		}
		public Criteria andCreateTimeLessThanOrEqualTo(String value) {
			addCriterion("create_time <=", value, "createTime");
			return (Criteria) this;
		}
		public Criteria andCreateTimeIn(List<String> values) {
			addCriterion("create_time in", values, "createTime");
			return (Criteria) this;
		}
		public Criteria andCreateTimeNotIn(List<String> values) {
			addCriterion("create_time not in", values, "createTime");
			return (Criteria) this;
		}
		public Criteria andCreateTimeBetween(String value1, String value2) {
			addCriterion("create_time between", value1, value2, "createTime");
			return (Criteria) this;
		}
		public Criteria andCreateTimeNotBetween(String value1, String value2) {
			addCriterion("create_time not between", value1, value2, "createTime");
			return (Criteria) this;
		}
		public Criteria andCreateTimeLike(String value) {
			addCriterion("create_time like", value, "createTime");
			return (Criteria) this;
		}
		public Criteria andCreateTimeNotLike(String value) {
			addCriterion("create_time not like", value, "createTime");
			return (Criteria) this;
		}
		public Criteria andFixIsNull() {
			addCriterion("fix is null");
			return (Criteria) this;
		}
		public Criteria andFixIsNotNull(){
			addCriterion("fix is not null");
			return (Criteria) this;
		}
		public Criteria andFixEqualTo(Long value) {
			addCriterion("fix =", value, "fix");
			return (Criteria) this;
		}
		public Criteria andFixNotEqualTo(Long value) {
			addCriterion("fix <>", value, "fix");
			return (Criteria) this;
		}
		public Criteria andFixGreaterThan(Long value) {
			addCriterion("fix >", value, "fix");
			return (Criteria) this;
		}
		public Criteria andFixGreaterThanOrEqualTo(Long value) {
			addCriterion("fix >=", value, "Fix");
			return (Criteria) this;
		}
		public Criteria andFixLessThan(Long value) {
			addCriterion("fix <", value, "fix");
			return (Criteria) this;
		}
		public Criteria andFixLessThanOrEqualTo(Long value) {
			addCriterion("fix <=", value, "fix");
			return (Criteria) this;
		}
		public Criteria andFixIn(List<Long> values) {
			if(values.size()==0){
				addCriterion("fix =", 0, "fix");
				return (Criteria) this;
			}
			addCriterion("fix in", values, "fix");
			return (Criteria) this;
		}
		public Criteria andFixNotIn(List<Long> values) {
			addCriterion("fix not in", values, "fix");
			return (Criteria) this;
		}
		public Criteria andFixBetween(Long value1, Long value2) {
			addCriterion("fix between", value1, value2, "fix");
			return (Criteria) this;
		}
		public Criteria andFixNotBetween(Long value1, Long value2) {
			addCriterion("fix not between", value1, value2, "fix");
			return (Criteria) this;
		}
		public Criteria andFixLike(Long value) {
			addCriterion("fix like", value, "fix");
			return (Criteria) this;
		}
		public Criteria andFixNotLike(Long value) {
			addCriterion("fix not like", value, "fix");
			return (Criteria) this;
		}
		public Criteria andAllAttrQuery(List<Map<String,Object>> queryParams) {
			for(Map<String,Object> p:queryParams){
				String attrName = p.get("attrName").toString();
				String attrName2 = p.get("attrName2").toString();
				String queryType = p.get("queryType").toString();
				Object value = p.get("value");
				if(queryType.equals("1")){
					addCriterion(""+attrName+" =", value, attrName2);
				}
				if(queryType.equals("2")){
					addCriterion(""+attrName+" !=", value, attrName2);
				}
				if(queryType.equals("8")){
					addCriterion(""+attrName+" like", "%"+value+"%", attrName2);
				}
				if(queryType.equals("3")){
					addCriterion(""+attrName+" =", value, attrName2);
				}
				if(queryType.equals("4")){//大于
					addCriterion(""+attrName+" >", value, attrName2);
				}
				if(queryType.equals("5")){//大于等于
					addCriterion(""+attrName+" >=", value, attrName2);
				}
				if(queryType.equals("6")){//小于
					addCriterion(""+attrName+" <", value, attrName2);
				}
				if(queryType.equals("7")){//小于等于
					addCriterion(""+attrName+" <=", value, attrName2);
				}
				if(queryType.equals("9")){//id集合
					addCriterion(""+attrName+" in", value, attrName2);
				}
			}
			return (Criteria) this;
		}
	}
	public static class Criteria extends GeneratedCriteria {
		protected Criteria() {
			super();
		}
	}
	public static class Criterion {
		private String condition;
		private Object value;
		private Object secondValue;
		private boolean noValue;
		private boolean singleValue;
		private boolean betweenValue;
		private boolean listValue;
		private String typeHandler;
		public String getCondition() {
			return condition;
		}
		public Object getValue() {
			return value;
		}
		public Object getSecondValue() {
			return secondValue;
		}
		public boolean isNoValue() {
			return noValue;
		}
		public boolean isSingleValue() {
			return singleValue;
		}
		public boolean isBetweenValue() {
			return betweenValue;
		}
		public boolean isListValue() {
			return listValue;
		}
		public String getTypeHandler() {
			return typeHandler;
		}
		protected Criterion(String condition) {
			super();
			this.condition = condition;
			this.typeHandler = null;
			this.noValue = true;
		}
		protected Criterion(String condition, Object value, String typeHandler) {
			super();
			this.condition = condition;
			this.value = value;
			this.typeHandler = typeHandler;
			if (value instanceof List<?>) {
				this.listValue = true;
			} else {
				this.singleValue = true;
			}
		}
		protected Criterion(String condition, Object value) {
			this(condition, value, null);
		}
		protected Criterion(String condition, Object value, Object secondValue, String typeHandler) {
			super();
			this.condition = condition;
			this.value = value;
			this.secondValue = secondValue;
			this.typeHandler = typeHandler;
			this.betweenValue = true;
		}
		protected Criterion(String condition, Object value, Object secondValue) {
			this(condition, value, secondValue, null);
		}
	}
}
