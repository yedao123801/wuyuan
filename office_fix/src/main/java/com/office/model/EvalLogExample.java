package com.office.model;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
public class EvalLogExample {
	protected String orderByClause;
	protected boolean distinct;
	protected int startRow;
	protected int pageRows;
	protected List<Criteria> oredCriteria;
	public EvalLogExample() {
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
		public Criteria andEvalStarIsNull() {
			addCriterion("eval_star is null");
			return (Criteria) this;
		}
		public Criteria andEvalStarIsNotNull(){
			addCriterion("eval_star is not null");
			return (Criteria) this;
		}
		public Criteria andEvalStarEqualTo(Integer value) {
			addCriterion("eval_star =", value, "evalStar");
			return (Criteria) this;
		}
		public Criteria andEvalStarNotEqualTo(Integer value) {
			addCriterion("eval_star <>", value, "evalStar");
			return (Criteria) this;
		}
		public Criteria andEvalStarGreaterThan(Integer value) {
			addCriterion("eval_star >", value, "evalStar");
			return (Criteria) this;
		}
		public Criteria andEvalStarGreaterThanOrEqualTo(Integer value) {
			addCriterion("eval_star >=", value, "EvalStar");
			return (Criteria) this;
		}
		public Criteria andEvalStarLessThan(Integer value) {
			addCriterion("eval_star <", value, "evalStar");
			return (Criteria) this;
		}
		public Criteria andEvalStarLessThanOrEqualTo(Integer value) {
			addCriterion("eval_star <=", value, "evalStar");
			return (Criteria) this;
		}
		public Criteria andEvalStarIn(List<Integer> values) {
			if(values.size()==0){
				addCriterion("eval_star =", 0, "evalStar");
				return (Criteria) this;
			}
			addCriterion("eval_star in", values, "evalStar");
			return (Criteria) this;
		}
		public Criteria andEvalStarNotIn(List<Integer> values) {
			addCriterion("eval_star not in", values, "evalStar");
			return (Criteria) this;
		}
		public Criteria andEvalStarBetween(Integer value1, Integer value2) {
			addCriterion("eval_star between", value1, value2, "evalStar");
			return (Criteria) this;
		}
		public Criteria andEvalStarNotBetween(Integer value1, Integer value2) {
			addCriterion("eval_star not between", value1, value2, "evalStar");
			return (Criteria) this;
		}
		public Criteria andEvalStarLike(Integer value) {
			addCriterion("eval_star like", value, "evalStar");
			return (Criteria) this;
		}
		public Criteria andEvalStarNotLike(Integer value) {
			addCriterion("eval_star not like", value, "evalStar");
			return (Criteria) this;
		}
		public Criteria andEvalContentIsNull() {
			addCriterion("eval_content is null");
			return (Criteria) this;
		}
		public Criteria andEvalContentIsNotNull(){
			addCriterion("eval_content is not null");
			return (Criteria) this;
		}
		public Criteria andEvalContentEqualTo(String value) {
			addCriterion("eval_content =", value, "evalContent");
			return (Criteria) this;
		}
		public Criteria andEvalContentNotEqualTo(String value) {
			addCriterion("eval_content <>", value, "evalContent");
			return (Criteria) this;
		}
		public Criteria andEvalContentGreaterThan(String value) {
			addCriterion("eval_content >", value, "evalContent");
			return (Criteria) this;
		}
		public Criteria andEvalContentGreaterThanOrEqualTo(String value) {
			addCriterion("eval_content >=", value, "EvalContent");
			return (Criteria) this;
		}
		public Criteria andEvalContentLessThan(String value) {
			addCriterion("eval_content <", value, "evalContent");
			return (Criteria) this;
		}
		public Criteria andEvalContentLessThanOrEqualTo(String value) {
			addCriterion("eval_content <=", value, "evalContent");
			return (Criteria) this;
		}
		public Criteria andEvalContentIn(List<String> values) {
			addCriterion("eval_content in", values, "evalContent");
			return (Criteria) this;
		}
		public Criteria andEvalContentNotIn(List<String> values) {
			addCriterion("eval_content not in", values, "evalContent");
			return (Criteria) this;
		}
		public Criteria andEvalContentBetween(String value1, String value2) {
			addCriterion("eval_content between", value1, value2, "evalContent");
			return (Criteria) this;
		}
		public Criteria andEvalContentNotBetween(String value1, String value2) {
			addCriterion("eval_content not between", value1, value2, "evalContent");
			return (Criteria) this;
		}
		public Criteria andEvalContentLike(String value) {
			addCriterion("eval_content like", value, "evalContent");
			return (Criteria) this;
		}
		public Criteria andEvalContentNotLike(String value) {
			addCriterion("eval_content not like", value, "evalContent");
			return (Criteria) this;
		}
		public Criteria andUserIdIsNull() {
			addCriterion("user_id is null");
			return (Criteria) this;
		}
		public Criteria andUserIdIsNotNull(){
			addCriterion("user_id is not null");
			return (Criteria) this;
		}
		public Criteria andUserIdEqualTo(Long value) {
			addCriterion("user_id =", value, "userId");
			return (Criteria) this;
		}
		public Criteria andUserIdNotEqualTo(Long value) {
			addCriterion("user_id <>", value, "userId");
			return (Criteria) this;
		}
		public Criteria andUserIdGreaterThan(Long value) {
			addCriterion("user_id >", value, "userId");
			return (Criteria) this;
		}
		public Criteria andUserIdGreaterThanOrEqualTo(Long value) {
			addCriterion("user_id >=", value, "UserId");
			return (Criteria) this;
		}
		public Criteria andUserIdLessThan(Long value) {
			addCriterion("user_id <", value, "userId");
			return (Criteria) this;
		}
		public Criteria andUserIdLessThanOrEqualTo(Long value) {
			addCriterion("user_id <=", value, "userId");
			return (Criteria) this;
		}
		public Criteria andUserIdIn(List<Long> values) {
			if(values.size()==0){
				addCriterion("user_id =", 0, "userId");
				return (Criteria) this;
			}
			addCriterion("user_id in", values, "userId");
			return (Criteria) this;
		}
		public Criteria andUserIdNotIn(List<Long> values) {
			addCriterion("user_id not in", values, "userId");
			return (Criteria) this;
		}
		public Criteria andUserIdBetween(Long value1, Long value2) {
			addCriterion("user_id between", value1, value2, "userId");
			return (Criteria) this;
		}
		public Criteria andUserIdNotBetween(Long value1, Long value2) {
			addCriterion("user_id not between", value1, value2, "userId");
			return (Criteria) this;
		}
		public Criteria andUserIdLike(Long value) {
			addCriterion("user_id like", value, "userId");
			return (Criteria) this;
		}
		public Criteria andUserIdNotLike(Long value) {
			addCriterion("user_id not like", value, "userId");
			return (Criteria) this;
		}
		public Criteria andEvalTimeIsNull() {
			addCriterion("eval_time is null");
			return (Criteria) this;
		}
		public Criteria andEvalTimeIsNotNull(){
			addCriterion("eval_time is not null");
			return (Criteria) this;
		}
		public Criteria andEvalTimeEqualTo(String value) {
			addCriterion("eval_time =", value, "evalTime");
			return (Criteria) this;
		}
		public Criteria andEvalTimeNotEqualTo(String value) {
			addCriterion("eval_time <>", value, "evalTime");
			return (Criteria) this;
		}
		public Criteria andEvalTimeGreaterThan(String value) {
			addCriterion("eval_time >", value, "evalTime");
			return (Criteria) this;
		}
		public Criteria andEvalTimeGreaterThanOrEqualTo(String value) {
			addCriterion("eval_time >=", value, "EvalTime");
			return (Criteria) this;
		}
		public Criteria andEvalTimeLessThan(String value) {
			addCriterion("eval_time <", value, "evalTime");
			return (Criteria) this;
		}
		public Criteria andEvalTimeLessThanOrEqualTo(String value) {
			addCriterion("eval_time <=", value, "evalTime");
			return (Criteria) this;
		}
		public Criteria andEvalTimeIn(List<String> values) {
			addCriterion("eval_time in", values, "evalTime");
			return (Criteria) this;
		}
		public Criteria andEvalTimeNotIn(List<String> values) {
			addCriterion("eval_time not in", values, "evalTime");
			return (Criteria) this;
		}
		public Criteria andEvalTimeBetween(String value1, String value2) {
			addCriterion("eval_time between", value1, value2, "evalTime");
			return (Criteria) this;
		}
		public Criteria andEvalTimeNotBetween(String value1, String value2) {
			addCriterion("eval_time not between", value1, value2, "evalTime");
			return (Criteria) this;
		}
		public Criteria andEvalTimeLike(String value) {
			addCriterion("eval_time like", value, "evalTime");
			return (Criteria) this;
		}
		public Criteria andEvalTimeNotLike(String value) {
			addCriterion("eval_time not like", value, "evalTime");
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
