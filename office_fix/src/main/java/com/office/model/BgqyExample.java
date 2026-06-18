package com.office.model;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
public class BgqyExample {
	protected String orderByClause;
	protected boolean distinct;
	protected int startRow;
	protected int pageRows;
	protected List<Criteria> oredCriteria;
	public BgqyExample() {
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
		public Criteria andQyNameIsNull() {
			addCriterion("qy_name is null");
			return (Criteria) this;
		}
		public Criteria andQyNameIsNotNull(){
			addCriterion("qy_name is not null");
			return (Criteria) this;
		}
		public Criteria andQyNameEqualTo(String value) {
			addCriterion("qy_name =", value, "qyName");
			return (Criteria) this;
		}
		public Criteria andQyNameNotEqualTo(String value) {
			addCriterion("qy_name <>", value, "qyName");
			return (Criteria) this;
		}
		public Criteria andQyNameGreaterThan(String value) {
			addCriterion("qy_name >", value, "qyName");
			return (Criteria) this;
		}
		public Criteria andQyNameGreaterThanOrEqualTo(String value) {
			addCriterion("qy_name >=", value, "QyName");
			return (Criteria) this;
		}
		public Criteria andQyNameLessThan(String value) {
			addCriterion("qy_name <", value, "qyName");
			return (Criteria) this;
		}
		public Criteria andQyNameLessThanOrEqualTo(String value) {
			addCriterion("qy_name <=", value, "qyName");
			return (Criteria) this;
		}
		public Criteria andQyNameIn(List<String> values) {
			addCriterion("qy_name in", values, "qyName");
			return (Criteria) this;
		}
		public Criteria andQyNameNotIn(List<String> values) {
			addCriterion("qy_name not in", values, "qyName");
			return (Criteria) this;
		}
		public Criteria andQyNameBetween(String value1, String value2) {
			addCriterion("qy_name between", value1, value2, "qyName");
			return (Criteria) this;
		}
		public Criteria andQyNameNotBetween(String value1, String value2) {
			addCriterion("qy_name not between", value1, value2, "qyName");
			return (Criteria) this;
		}
		public Criteria andQyNameLike(String value) {
			addCriterion("qy_name like", value, "qyName");
			return (Criteria) this;
		}
		public Criteria andQyNameNotLike(String value) {
			addCriterion("qy_name not like", value, "qyName");
			return (Criteria) this;
		}
		public Criteria andIsDeleteIsNull() {
			addCriterion("is_delete is null");
			return (Criteria) this;
		}
		public Criteria andIsDeleteIsNotNull(){
			addCriterion("is_delete is not null");
			return (Criteria) this;
		}
		public Criteria andIsDeleteEqualTo(Integer value) {
			addCriterion("is_delete =", value, "isDelete");
			return (Criteria) this;
		}
		public Criteria andIsDeleteNotEqualTo(Integer value) {
			addCriterion("is_delete <>", value, "isDelete");
			return (Criteria) this;
		}
		public Criteria andIsDeleteGreaterThan(Integer value) {
			addCriterion("is_delete >", value, "isDelete");
			return (Criteria) this;
		}
		public Criteria andIsDeleteGreaterThanOrEqualTo(Integer value) {
			addCriterion("is_delete >=", value, "IsDelete");
			return (Criteria) this;
		}
		public Criteria andIsDeleteLessThan(Integer value) {
			addCriterion("is_delete <", value, "isDelete");
			return (Criteria) this;
		}
		public Criteria andIsDeleteLessThanOrEqualTo(Integer value) {
			addCriterion("is_delete <=", value, "isDelete");
			return (Criteria) this;
		}
		public Criteria andIsDeleteIn(List<Integer> values) {
			if(values.size()==0){
				addCriterion("is_delete =", 0, "isDelete");
				return (Criteria) this;
			}
			addCriterion("is_delete in", values, "isDelete");
			return (Criteria) this;
		}
		public Criteria andIsDeleteNotIn(List<Integer> values) {
			addCriterion("is_delete not in", values, "isDelete");
			return (Criteria) this;
		}
		public Criteria andIsDeleteBetween(Integer value1, Integer value2) {
			addCriterion("is_delete between", value1, value2, "isDelete");
			return (Criteria) this;
		}
		public Criteria andIsDeleteNotBetween(Integer value1, Integer value2) {
			addCriterion("is_delete not between", value1, value2, "isDelete");
			return (Criteria) this;
		}
		public Criteria andIsDeleteLike(Integer value) {
			addCriterion("is_delete like", value, "isDelete");
			return (Criteria) this;
		}
		public Criteria andIsDeleteNotLike(Integer value) {
			addCriterion("is_delete not like", value, "isDelete");
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
