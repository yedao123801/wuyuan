package com.office.model;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
public class FixGcExample {
	protected String orderByClause;
	protected boolean distinct;
	protected int startRow;
	protected int pageRows;
	protected List<Criteria> oredCriteria;
	public FixGcExample() {
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
		public Criteria andFixBzIsNull() {
			addCriterion("fix_bz is null");
			return (Criteria) this;
		}
		public Criteria andFixBzIsNotNull(){
			addCriterion("fix_bz is not null");
			return (Criteria) this;
		}
		public Criteria andFixBzEqualTo(String value) {
			addCriterion("fix_bz =", value, "fixBz");
			return (Criteria) this;
		}
		public Criteria andFixBzNotEqualTo(String value) {
			addCriterion("fix_bz <>", value, "fixBz");
			return (Criteria) this;
		}
		public Criteria andFixBzGreaterThan(String value) {
			addCriterion("fix_bz >", value, "fixBz");
			return (Criteria) this;
		}
		public Criteria andFixBzGreaterThanOrEqualTo(String value) {
			addCriterion("fix_bz >=", value, "FixBz");
			return (Criteria) this;
		}
		public Criteria andFixBzLessThan(String value) {
			addCriterion("fix_bz <", value, "fixBz");
			return (Criteria) this;
		}
		public Criteria andFixBzLessThanOrEqualTo(String value) {
			addCriterion("fix_bz <=", value, "fixBz");
			return (Criteria) this;
		}
		public Criteria andFixBzIn(List<String> values) {
			addCriterion("fix_bz in", values, "fixBz");
			return (Criteria) this;
		}
		public Criteria andFixBzNotIn(List<String> values) {
			addCriterion("fix_bz not in", values, "fixBz");
			return (Criteria) this;
		}
		public Criteria andFixBzBetween(String value1, String value2) {
			addCriterion("fix_bz between", value1, value2, "fixBz");
			return (Criteria) this;
		}
		public Criteria andFixBzNotBetween(String value1, String value2) {
			addCriterion("fix_bz not between", value1, value2, "fixBz");
			return (Criteria) this;
		}
		public Criteria andFixBzLike(String value) {
			addCriterion("fix_bz like", value, "fixBz");
			return (Criteria) this;
		}
		public Criteria andFixBzNotLike(String value) {
			addCriterion("fix_bz not like", value, "fixBz");
			return (Criteria) this;
		}
		public Criteria andGhpjIsNull() {
			addCriterion("ghpj is null");
			return (Criteria) this;
		}
		public Criteria andGhpjIsNotNull(){
			addCriterion("ghpj is not null");
			return (Criteria) this;
		}
		public Criteria andGhpjEqualTo(String value) {
			addCriterion("ghpj =", value, "ghpj");
			return (Criteria) this;
		}
		public Criteria andGhpjNotEqualTo(String value) {
			addCriterion("ghpj <>", value, "ghpj");
			return (Criteria) this;
		}
		public Criteria andGhpjGreaterThan(String value) {
			addCriterion("ghpj >", value, "ghpj");
			return (Criteria) this;
		}
		public Criteria andGhpjGreaterThanOrEqualTo(String value) {
			addCriterion("ghpj >=", value, "Ghpj");
			return (Criteria) this;
		}
		public Criteria andGhpjLessThan(String value) {
			addCriterion("ghpj <", value, "ghpj");
			return (Criteria) this;
		}
		public Criteria andGhpjLessThanOrEqualTo(String value) {
			addCriterion("ghpj <=", value, "ghpj");
			return (Criteria) this;
		}
		public Criteria andGhpjIn(List<String> values) {
			addCriterion("ghpj in", values, "ghpj");
			return (Criteria) this;
		}
		public Criteria andGhpjNotIn(List<String> values) {
			addCriterion("ghpj not in", values, "ghpj");
			return (Criteria) this;
		}
		public Criteria andGhpjBetween(String value1, String value2) {
			addCriterion("ghpj between", value1, value2, "ghpj");
			return (Criteria) this;
		}
		public Criteria andGhpjNotBetween(String value1, String value2) {
			addCriterion("ghpj not between", value1, value2, "ghpj");
			return (Criteria) this;
		}
		public Criteria andGhpjLike(String value) {
			addCriterion("ghpj like", value, "ghpj");
			return (Criteria) this;
		}
		public Criteria andGhpjNotLike(String value) {
			addCriterion("ghpj not like", value, "ghpj");
			return (Criteria) this;
		}
		public Criteria andXhgsIsNull() {
			addCriterion("xhgs is null");
			return (Criteria) this;
		}
		public Criteria andXhgsIsNotNull(){
			addCriterion("xhgs is not null");
			return (Criteria) this;
		}
		public Criteria andXhgsEqualTo(Double value) {
			addCriterion("xhgs =", value, "xhgs");
			return (Criteria) this;
		}
		public Criteria andXhgsNotEqualTo(Double value) {
			addCriterion("xhgs <>", value, "xhgs");
			return (Criteria) this;
		}
		public Criteria andXhgsGreaterThan(Double value) {
			addCriterion("xhgs >", value, "xhgs");
			return (Criteria) this;
		}
		public Criteria andXhgsGreaterThanOrEqualTo(Double value) {
			addCriterion("xhgs >=", value, "Xhgs");
			return (Criteria) this;
		}
		public Criteria andXhgsLessThan(Double value) {
			addCriterion("xhgs <", value, "xhgs");
			return (Criteria) this;
		}
		public Criteria andXhgsLessThanOrEqualTo(Double value) {
			addCriterion("xhgs <=", value, "xhgs");
			return (Criteria) this;
		}
		public Criteria andXhgsIn(List<Double> values) {
			addCriterion("xhgs in", values, "xhgs");
			return (Criteria) this;
		}
		public Criteria andXhgsNotIn(List<Double> values) {
			addCriterion("xhgs not in", values, "xhgs");
			return (Criteria) this;
		}
		public Criteria andXhgsBetween(Double value1, Double value2) {
			addCriterion("xhgs between", value1, value2, "xhgs");
			return (Criteria) this;
		}
		public Criteria andXhgsNotBetween(Double value1, Double value2) {
			addCriterion("xhgs not between", value1, value2, "xhgs");
			return (Criteria) this;
		}
		public Criteria andXhgsLike(Double value) {
			addCriterion("xhgs like", value, "xhgs");
			return (Criteria) this;
		}
		public Criteria andXhgsNotLike(Double value) {
			addCriterion("xhgs not like", value, "xhgs");
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
		public Criteria andBxidIsNull() {
			addCriterion("bxid is null");
			return (Criteria) this;
		}
		public Criteria andBxidIsNotNull(){
			addCriterion("bxid is not null");
			return (Criteria) this;
		}
		public Criteria andBxidEqualTo(Long value) {
			addCriterion("bxid =", value, "bxid");
			return (Criteria) this;
		}
		public Criteria andBxidNotEqualTo(Long value) {
			addCriterion("bxid <>", value, "bxid");
			return (Criteria) this;
		}
		public Criteria andBxidGreaterThan(Long value) {
			addCriterion("bxid >", value, "bxid");
			return (Criteria) this;
		}
		public Criteria andBxidGreaterThanOrEqualTo(Long value) {
			addCriterion("bxid >=", value, "Bxid");
			return (Criteria) this;
		}
		public Criteria andBxidLessThan(Long value) {
			addCriterion("bxid <", value, "bxid");
			return (Criteria) this;
		}
		public Criteria andBxidLessThanOrEqualTo(Long value) {
			addCriterion("bxid <=", value, "bxid");
			return (Criteria) this;
		}
		public Criteria andBxidIn(List<Long> values) {
			if(values.size()==0){
				addCriterion("bxid =", 0, "bxid");
				return (Criteria) this;
			}
			addCriterion("bxid in", values, "bxid");
			return (Criteria) this;
		}
		public Criteria andBxidNotIn(List<Long> values) {
			addCriterion("bxid not in", values, "bxid");
			return (Criteria) this;
		}
		public Criteria andBxidBetween(Long value1, Long value2) {
			addCriterion("bxid between", value1, value2, "bxid");
			return (Criteria) this;
		}
		public Criteria andBxidNotBetween(Long value1, Long value2) {
			addCriterion("bxid not between", value1, value2, "bxid");
			return (Criteria) this;
		}
		public Criteria andBxidLike(Long value) {
			addCriterion("bxid like", value, "bxid");
			return (Criteria) this;
		}
		public Criteria andBxidNotLike(Long value) {
			addCriterion("bxid not like", value, "bxid");
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
