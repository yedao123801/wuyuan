package com.office.model;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
public class BgServiceExample {
	protected String orderByClause;
	protected boolean distinct;
	protected int startRow;
	protected int pageRows;
	protected List<Criteria> oredCriteria;
	public BgServiceExample() {
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
		public Criteria andServiceNoIsNull() {
			addCriterion("service_no is null");
			return (Criteria) this;
		}
		public Criteria andServiceNoIsNotNull(){
			addCriterion("service_no is not null");
			return (Criteria) this;
		}
		public Criteria andServiceNoEqualTo(String value) {
			addCriterion("service_no =", value, "serviceNo");
			return (Criteria) this;
		}
		public Criteria andServiceNoNotEqualTo(String value) {
			addCriterion("service_no <>", value, "serviceNo");
			return (Criteria) this;
		}
		public Criteria andServiceNoGreaterThan(String value) {
			addCriterion("service_no >", value, "serviceNo");
			return (Criteria) this;
		}
		public Criteria andServiceNoGreaterThanOrEqualTo(String value) {
			addCriterion("service_no >=", value, "ServiceNo");
			return (Criteria) this;
		}
		public Criteria andServiceNoLessThan(String value) {
			addCriterion("service_no <", value, "serviceNo");
			return (Criteria) this;
		}
		public Criteria andServiceNoLessThanOrEqualTo(String value) {
			addCriterion("service_no <=", value, "serviceNo");
			return (Criteria) this;
		}
		public Criteria andServiceNoIn(List<String> values) {
			addCriterion("service_no in", values, "serviceNo");
			return (Criteria) this;
		}
		public Criteria andServiceNoNotIn(List<String> values) {
			addCriterion("service_no not in", values, "serviceNo");
			return (Criteria) this;
		}
		public Criteria andServiceNoBetween(String value1, String value2) {
			addCriterion("service_no between", value1, value2, "serviceNo");
			return (Criteria) this;
		}
		public Criteria andServiceNoNotBetween(String value1, String value2) {
			addCriterion("service_no not between", value1, value2, "serviceNo");
			return (Criteria) this;
		}
		public Criteria andServiceNoLike(String value) {
			addCriterion("service_no like", value, "serviceNo");
			return (Criteria) this;
		}
		public Criteria andServiceNoNotLike(String value) {
			addCriterion("service_no not like", value, "serviceNo");
			return (Criteria) this;
		}
		public Criteria andServiceTypeIsNull() {
			addCriterion("service_type is null");
			return (Criteria) this;
		}
		public Criteria andServiceTypeIsNotNull(){
			addCriterion("service_type is not null");
			return (Criteria) this;
		}
		public Criteria andServiceTypeEqualTo(String value) {
			addCriterion("service_type =", value, "serviceType");
			return (Criteria) this;
		}
		public Criteria andServiceTypeNotEqualTo(String value) {
			addCriterion("service_type <>", value, "serviceType");
			return (Criteria) this;
		}
		public Criteria andServiceTypeGreaterThan(String value) {
			addCriterion("service_type >", value, "serviceType");
			return (Criteria) this;
		}
		public Criteria andServiceTypeGreaterThanOrEqualTo(String value) {
			addCriterion("service_type >=", value, "ServiceType");
			return (Criteria) this;
		}
		public Criteria andServiceTypeLessThan(String value) {
			addCriterion("service_type <", value, "serviceType");
			return (Criteria) this;
		}
		public Criteria andServiceTypeLessThanOrEqualTo(String value) {
			addCriterion("service_type <=", value, "serviceType");
			return (Criteria) this;
		}
		public Criteria andServiceTypeIn(List<String> values) {
			addCriterion("service_type in", values, "serviceType");
			return (Criteria) this;
		}
		public Criteria andServiceTypeNotIn(List<String> values) {
			addCriterion("service_type not in", values, "serviceType");
			return (Criteria) this;
		}
		public Criteria andServiceTypeBetween(String value1, String value2) {
			addCriterion("service_type between", value1, value2, "serviceType");
			return (Criteria) this;
		}
		public Criteria andServiceTypeNotBetween(String value1, String value2) {
			addCriterion("service_type not between", value1, value2, "serviceType");
			return (Criteria) this;
		}
		public Criteria andServiceTypeLike(String value) {
			addCriterion("service_type like", value, "serviceType");
			return (Criteria) this;
		}
		public Criteria andServiceTypeNotLike(String value) {
			addCriterion("service_type not like", value, "serviceType");
			return (Criteria) this;
		}
		public Criteria andServiceNameIsNull() {
			addCriterion("service_name is null");
			return (Criteria) this;
		}
		public Criteria andServiceNameIsNotNull(){
			addCriterion("service_name is not null");
			return (Criteria) this;
		}
		public Criteria andServiceNameEqualTo(String value) {
			addCriterion("service_name =", value, "serviceName");
			return (Criteria) this;
		}
		public Criteria andServiceNameNotEqualTo(String value) {
			addCriterion("service_name <>", value, "serviceName");
			return (Criteria) this;
		}
		public Criteria andServiceNameGreaterThan(String value) {
			addCriterion("service_name >", value, "serviceName");
			return (Criteria) this;
		}
		public Criteria andServiceNameGreaterThanOrEqualTo(String value) {
			addCriterion("service_name >=", value, "ServiceName");
			return (Criteria) this;
		}
		public Criteria andServiceNameLessThan(String value) {
			addCriterion("service_name <", value, "serviceName");
			return (Criteria) this;
		}
		public Criteria andServiceNameLessThanOrEqualTo(String value) {
			addCriterion("service_name <=", value, "serviceName");
			return (Criteria) this;
		}
		public Criteria andServiceNameIn(List<String> values) {
			addCriterion("service_name in", values, "serviceName");
			return (Criteria) this;
		}
		public Criteria andServiceNameNotIn(List<String> values) {
			addCriterion("service_name not in", values, "serviceName");
			return (Criteria) this;
		}
		public Criteria andServiceNameBetween(String value1, String value2) {
			addCriterion("service_name between", value1, value2, "serviceName");
			return (Criteria) this;
		}
		public Criteria andServiceNameNotBetween(String value1, String value2) {
			addCriterion("service_name not between", value1, value2, "serviceName");
			return (Criteria) this;
		}
		public Criteria andServiceNameLike(String value) {
			addCriterion("service_name like", value, "serviceName");
			return (Criteria) this;
		}
		public Criteria andServiceNameNotLike(String value) {
			addCriterion("service_name not like", value, "serviceName");
			return (Criteria) this;
		}
		public Criteria andServiceXhIsNull() {
			addCriterion("service_xh is null");
			return (Criteria) this;
		}
		public Criteria andServiceXhIsNotNull(){
			addCriterion("service_xh is not null");
			return (Criteria) this;
		}
		public Criteria andServiceXhEqualTo(String value) {
			addCriterion("service_xh =", value, "serviceXh");
			return (Criteria) this;
		}
		public Criteria andServiceXhNotEqualTo(String value) {
			addCriterion("service_xh <>", value, "serviceXh");
			return (Criteria) this;
		}
		public Criteria andServiceXhGreaterThan(String value) {
			addCriterion("service_xh >", value, "serviceXh");
			return (Criteria) this;
		}
		public Criteria andServiceXhGreaterThanOrEqualTo(String value) {
			addCriterion("service_xh >=", value, "ServiceXh");
			return (Criteria) this;
		}
		public Criteria andServiceXhLessThan(String value) {
			addCriterion("service_xh <", value, "serviceXh");
			return (Criteria) this;
		}
		public Criteria andServiceXhLessThanOrEqualTo(String value) {
			addCriterion("service_xh <=", value, "serviceXh");
			return (Criteria) this;
		}
		public Criteria andServiceXhIn(List<String> values) {
			addCriterion("service_xh in", values, "serviceXh");
			return (Criteria) this;
		}
		public Criteria andServiceXhNotIn(List<String> values) {
			addCriterion("service_xh not in", values, "serviceXh");
			return (Criteria) this;
		}
		public Criteria andServiceXhBetween(String value1, String value2) {
			addCriterion("service_xh between", value1, value2, "serviceXh");
			return (Criteria) this;
		}
		public Criteria andServiceXhNotBetween(String value1, String value2) {
			addCriterion("service_xh not between", value1, value2, "serviceXh");
			return (Criteria) this;
		}
		public Criteria andServiceXhLike(String value) {
			addCriterion("service_xh like", value, "serviceXh");
			return (Criteria) this;
		}
		public Criteria andServiceXhNotLike(String value) {
			addCriterion("service_xh not like", value, "serviceXh");
			return (Criteria) this;
		}
		public Criteria andCgDateIsNull() {
			addCriterion("cg_date is null");
			return (Criteria) this;
		}
		public Criteria andCgDateIsNotNull(){
			addCriterion("cg_date is not null");
			return (Criteria) this;
		}
		public Criteria andCgDateEqualTo(String value) {
			addCriterion("cg_date =", value, "cgDate");
			return (Criteria) this;
		}
		public Criteria andCgDateNotEqualTo(String value) {
			addCriterion("cg_date <>", value, "cgDate");
			return (Criteria) this;
		}
		public Criteria andCgDateGreaterThan(String value) {
			addCriterion("cg_date >", value, "cgDate");
			return (Criteria) this;
		}
		public Criteria andCgDateGreaterThanOrEqualTo(String value) {
			addCriterion("cg_date >=", value, "CgDate");
			return (Criteria) this;
		}
		public Criteria andCgDateLessThan(String value) {
			addCriterion("cg_date <", value, "cgDate");
			return (Criteria) this;
		}
		public Criteria andCgDateLessThanOrEqualTo(String value) {
			addCriterion("cg_date <=", value, "cgDate");
			return (Criteria) this;
		}
		public Criteria andCgDateIn(List<String> values) {
			addCriterion("cg_date in", values, "cgDate");
			return (Criteria) this;
		}
		public Criteria andCgDateNotIn(List<String> values) {
			addCriterion("cg_date not in", values, "cgDate");
			return (Criteria) this;
		}
		public Criteria andCgDateBetween(String value1, String value2) {
			addCriterion("cg_date between", value1, value2, "cgDate");
			return (Criteria) this;
		}
		public Criteria andCgDateNotBetween(String value1, String value2) {
			addCriterion("cg_date not between", value1, value2, "cgDate");
			return (Criteria) this;
		}
		public Criteria andCgDateLike(String value) {
			addCriterion("cg_date like", value, "cgDate");
			return (Criteria) this;
		}
		public Criteria andCgDateNotLike(String value) {
			addCriterion("cg_date not like", value, "cgDate");
			return (Criteria) this;
		}
		public Criteria andBgqyIsNull() {
			addCriterion("bgqy is null");
			return (Criteria) this;
		}
		public Criteria andBgqyIsNotNull(){
			addCriterion("bgqy is not null");
			return (Criteria) this;
		}
		public Criteria andBgqyEqualTo(Long value) {
			addCriterion("bgqy =", value, "bgqy");
			return (Criteria) this;
		}
		public Criteria andBgqyNotEqualTo(Long value) {
			addCriterion("bgqy <>", value, "bgqy");
			return (Criteria) this;
		}
		public Criteria andBgqyGreaterThan(Long value) {
			addCriterion("bgqy >", value, "bgqy");
			return (Criteria) this;
		}
		public Criteria andBgqyGreaterThanOrEqualTo(Long value) {
			addCriterion("bgqy >=", value, "Bgqy");
			return (Criteria) this;
		}
		public Criteria andBgqyLessThan(Long value) {
			addCriterion("bgqy <", value, "bgqy");
			return (Criteria) this;
		}
		public Criteria andBgqyLessThanOrEqualTo(Long value) {
			addCriterion("bgqy <=", value, "bgqy");
			return (Criteria) this;
		}
		public Criteria andBgqyIn(List<Long> values) {
			if(values.size()==0){
				addCriterion("bgqy =", 0, "bgqy");
				return (Criteria) this;
			}
			addCriterion("bgqy in", values, "bgqy");
			return (Criteria) this;
		}
		public Criteria andBgqyNotIn(List<Long> values) {
			addCriterion("bgqy not in", values, "bgqy");
			return (Criteria) this;
		}
		public Criteria andBgqyBetween(Long value1, Long value2) {
			addCriterion("bgqy between", value1, value2, "bgqy");
			return (Criteria) this;
		}
		public Criteria andBgqyNotBetween(Long value1, Long value2) {
			addCriterion("bgqy not between", value1, value2, "bgqy");
			return (Criteria) this;
		}
		public Criteria andBgqyLike(Long value) {
			addCriterion("bgqy like", value, "bgqy");
			return (Criteria) this;
		}
		public Criteria andBgqyNotLike(Long value) {
			addCriterion("bgqy not like", value, "bgqy");
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
		public Criteria andSearchKeyWordIsNull() {
			addCriterion("search_key_word is null");
			return (Criteria) this;
		}
		public Criteria andSearchKeyWordIsNotNull(){
			addCriterion("search_key_word is not null");
			return (Criteria) this;
		}
		public Criteria andSearchKeyWordEqualTo(String value) {
			addCriterion("search_key_word =", value, "searchKeyWord");
			return (Criteria) this;
		}
		public Criteria andSearchKeyWordNotEqualTo(String value) {
			addCriterion("search_key_word <>", value, "searchKeyWord");
			return (Criteria) this;
		}
		public Criteria andSearchKeyWordGreaterThan(String value) {
			addCriterion("search_key_word >", value, "searchKeyWord");
			return (Criteria) this;
		}
		public Criteria andSearchKeyWordGreaterThanOrEqualTo(String value) {
			addCriterion("search_key_word >=", value, "SearchKeyWord");
			return (Criteria) this;
		}
		public Criteria andSearchKeyWordLessThan(String value) {
			addCriterion("search_key_word <", value, "searchKeyWord");
			return (Criteria) this;
		}
		public Criteria andSearchKeyWordLessThanOrEqualTo(String value) {
			addCriterion("search_key_word <=", value, "searchKeyWord");
			return (Criteria) this;
		}
		public Criteria andSearchKeyWordIn(List<String> values) {
			addCriterion("search_key_word in", values, "searchKeyWord");
			return (Criteria) this;
		}
		public Criteria andSearchKeyWordNotIn(List<String> values) {
			addCriterion("search_key_word not in", values, "searchKeyWord");
			return (Criteria) this;
		}
		public Criteria andSearchKeyWordBetween(String value1, String value2) {
			addCriterion("search_key_word between", value1, value2, "searchKeyWord");
			return (Criteria) this;
		}
		public Criteria andSearchKeyWordNotBetween(String value1, String value2) {
			addCriterion("search_key_word not between", value1, value2, "searchKeyWord");
			return (Criteria) this;
		}
		public Criteria andSearchKeyWordLike(String value) {
			addCriterion("search_key_word like", value, "searchKeyWord");
			return (Criteria) this;
		}
		public Criteria andSearchKeyWordNotLike(String value) {
			addCriterion("search_key_word not like", value, "searchKeyWord");
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
