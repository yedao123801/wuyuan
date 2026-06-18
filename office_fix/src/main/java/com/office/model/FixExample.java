package com.office.model;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
public class FixExample {
	protected String orderByClause;
	protected boolean distinct;
	protected int startRow;
	protected int pageRows;
	protected List<Criteria> oredCriteria;
	public FixExample() {
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
		public Criteria andLoginNameIsNull() {
			addCriterion("login_name is null");
			return (Criteria) this;
		}
		public Criteria andLoginNameIsNotNull(){
			addCriterion("login_name is not null");
			return (Criteria) this;
		}
		public Criteria andLoginNameEqualTo(String value) {
			addCriterion("login_name =", value, "loginName");
			return (Criteria) this;
		}
		public Criteria andLoginNameNotEqualTo(String value) {
			addCriterion("login_name <>", value, "loginName");
			return (Criteria) this;
		}
		public Criteria andLoginNameGreaterThan(String value) {
			addCriterion("login_name >", value, "loginName");
			return (Criteria) this;
		}
		public Criteria andLoginNameGreaterThanOrEqualTo(String value) {
			addCriterion("login_name >=", value, "LoginName");
			return (Criteria) this;
		}
		public Criteria andLoginNameLessThan(String value) {
			addCriterion("login_name <", value, "loginName");
			return (Criteria) this;
		}
		public Criteria andLoginNameLessThanOrEqualTo(String value) {
			addCriterion("login_name <=", value, "loginName");
			return (Criteria) this;
		}
		public Criteria andLoginNameIn(List<String> values) {
			addCriterion("login_name in", values, "loginName");
			return (Criteria) this;
		}
		public Criteria andLoginNameNotIn(List<String> values) {
			addCriterion("login_name not in", values, "loginName");
			return (Criteria) this;
		}
		public Criteria andLoginNameBetween(String value1, String value2) {
			addCriterion("login_name between", value1, value2, "loginName");
			return (Criteria) this;
		}
		public Criteria andLoginNameNotBetween(String value1, String value2) {
			addCriterion("login_name not between", value1, value2, "loginName");
			return (Criteria) this;
		}
		public Criteria andLoginNameLike(String value) {
			addCriterion("login_name like", value, "loginName");
			return (Criteria) this;
		}
		public Criteria andLoginNameNotLike(String value) {
			addCriterion("login_name not like", value, "loginName");
			return (Criteria) this;
		}
		public Criteria andPasswordIsNull() {
			addCriterion("password is null");
			return (Criteria) this;
		}
		public Criteria andPasswordIsNotNull(){
			addCriterion("password is not null");
			return (Criteria) this;
		}
		public Criteria andPasswordEqualTo(String value) {
			addCriterion("password =", value, "password");
			return (Criteria) this;
		}
		public Criteria andPasswordNotEqualTo(String value) {
			addCriterion("password <>", value, "password");
			return (Criteria) this;
		}
		public Criteria andPasswordGreaterThan(String value) {
			addCriterion("password >", value, "password");
			return (Criteria) this;
		}
		public Criteria andPasswordGreaterThanOrEqualTo(String value) {
			addCriterion("password >=", value, "Password");
			return (Criteria) this;
		}
		public Criteria andPasswordLessThan(String value) {
			addCriterion("password <", value, "password");
			return (Criteria) this;
		}
		public Criteria andPasswordLessThanOrEqualTo(String value) {
			addCriterion("password <=", value, "password");
			return (Criteria) this;
		}
		public Criteria andPasswordIn(List<String> values) {
			addCriterion("password in", values, "password");
			return (Criteria) this;
		}
		public Criteria andPasswordNotIn(List<String> values) {
			addCriterion("password not in", values, "password");
			return (Criteria) this;
		}
		public Criteria andPasswordBetween(String value1, String value2) {
			addCriterion("password between", value1, value2, "password");
			return (Criteria) this;
		}
		public Criteria andPasswordNotBetween(String value1, String value2) {
			addCriterion("password not between", value1, value2, "password");
			return (Criteria) this;
		}
		public Criteria andPasswordLike(String value) {
			addCriterion("password like", value, "password");
			return (Criteria) this;
		}
		public Criteria andPasswordNotLike(String value) {
			addCriterion("password not like", value, "password");
			return (Criteria) this;
		}
		public Criteria andNameIsNull() {
			addCriterion("name is null");
			return (Criteria) this;
		}
		public Criteria andNameIsNotNull(){
			addCriterion("name is not null");
			return (Criteria) this;
		}
		public Criteria andNameEqualTo(String value) {
			addCriterion("name =", value, "name");
			return (Criteria) this;
		}
		public Criteria andNameNotEqualTo(String value) {
			addCriterion("name <>", value, "name");
			return (Criteria) this;
		}
		public Criteria andNameGreaterThan(String value) {
			addCriterion("name >", value, "name");
			return (Criteria) this;
		}
		public Criteria andNameGreaterThanOrEqualTo(String value) {
			addCriterion("name >=", value, "Name");
			return (Criteria) this;
		}
		public Criteria andNameLessThan(String value) {
			addCriterion("name <", value, "name");
			return (Criteria) this;
		}
		public Criteria andNameLessThanOrEqualTo(String value) {
			addCriterion("name <=", value, "name");
			return (Criteria) this;
		}
		public Criteria andNameIn(List<String> values) {
			addCriterion("name in", values, "name");
			return (Criteria) this;
		}
		public Criteria andNameNotIn(List<String> values) {
			addCriterion("name not in", values, "name");
			return (Criteria) this;
		}
		public Criteria andNameBetween(String value1, String value2) {
			addCriterion("name between", value1, value2, "name");
			return (Criteria) this;
		}
		public Criteria andNameNotBetween(String value1, String value2) {
			addCriterion("name not between", value1, value2, "name");
			return (Criteria) this;
		}
		public Criteria andNameLike(String value) {
			addCriterion("name like", value, "name");
			return (Criteria) this;
		}
		public Criteria andNameNotLike(String value) {
			addCriterion("name not like", value, "name");
			return (Criteria) this;
		}
		public Criteria andCelphoneIsNull() {
			addCriterion("celphone is null");
			return (Criteria) this;
		}
		public Criteria andCelphoneIsNotNull(){
			addCriterion("celphone is not null");
			return (Criteria) this;
		}
		public Criteria andCelphoneEqualTo(String value) {
			addCriterion("celphone =", value, "celphone");
			return (Criteria) this;
		}
		public Criteria andCelphoneNotEqualTo(String value) {
			addCriterion("celphone <>", value, "celphone");
			return (Criteria) this;
		}
		public Criteria andCelphoneGreaterThan(String value) {
			addCriterion("celphone >", value, "celphone");
			return (Criteria) this;
		}
		public Criteria andCelphoneGreaterThanOrEqualTo(String value) {
			addCriterion("celphone >=", value, "Celphone");
			return (Criteria) this;
		}
		public Criteria andCelphoneLessThan(String value) {
			addCriterion("celphone <", value, "celphone");
			return (Criteria) this;
		}
		public Criteria andCelphoneLessThanOrEqualTo(String value) {
			addCriterion("celphone <=", value, "celphone");
			return (Criteria) this;
		}
		public Criteria andCelphoneIn(List<String> values) {
			addCriterion("celphone in", values, "celphone");
			return (Criteria) this;
		}
		public Criteria andCelphoneNotIn(List<String> values) {
			addCriterion("celphone not in", values, "celphone");
			return (Criteria) this;
		}
		public Criteria andCelphoneBetween(String value1, String value2) {
			addCriterion("celphone between", value1, value2, "celphone");
			return (Criteria) this;
		}
		public Criteria andCelphoneNotBetween(String value1, String value2) {
			addCriterion("celphone not between", value1, value2, "celphone");
			return (Criteria) this;
		}
		public Criteria andCelphoneLike(String value) {
			addCriterion("celphone like", value, "celphone");
			return (Criteria) this;
		}
		public Criteria andCelphoneNotLike(String value) {
			addCriterion("celphone not like", value, "celphone");
			return (Criteria) this;
		}
		public Criteria andHeadImgIsNull() {
			addCriterion("head_img is null");
			return (Criteria) this;
		}
		public Criteria andHeadImgIsNotNull(){
			addCriterion("head_img is not null");
			return (Criteria) this;
		}
		public Criteria andHeadImgEqualTo(String value) {
			addCriterion("head_img =", value, "headImg");
			return (Criteria) this;
		}
		public Criteria andHeadImgNotEqualTo(String value) {
			addCriterion("head_img <>", value, "headImg");
			return (Criteria) this;
		}
		public Criteria andHeadImgGreaterThan(String value) {
			addCriterion("head_img >", value, "headImg");
			return (Criteria) this;
		}
		public Criteria andHeadImgGreaterThanOrEqualTo(String value) {
			addCriterion("head_img >=", value, "HeadImg");
			return (Criteria) this;
		}
		public Criteria andHeadImgLessThan(String value) {
			addCriterion("head_img <", value, "headImg");
			return (Criteria) this;
		}
		public Criteria andHeadImgLessThanOrEqualTo(String value) {
			addCriterion("head_img <=", value, "headImg");
			return (Criteria) this;
		}
		public Criteria andHeadImgIn(List<String> values) {
			addCriterion("head_img in", values, "headImg");
			return (Criteria) this;
		}
		public Criteria andHeadImgNotIn(List<String> values) {
			addCriterion("head_img not in", values, "headImg");
			return (Criteria) this;
		}
		public Criteria andHeadImgBetween(String value1, String value2) {
			addCriterion("head_img between", value1, value2, "headImg");
			return (Criteria) this;
		}
		public Criteria andHeadImgNotBetween(String value1, String value2) {
			addCriterion("head_img not between", value1, value2, "headImg");
			return (Criteria) this;
		}
		public Criteria andHeadImgLike(String value) {
			addCriterion("head_img like", value, "headImg");
			return (Criteria) this;
		}
		public Criteria andHeadImgNotLike(String value) {
			addCriterion("head_img not like", value, "headImg");
			return (Criteria) this;
		}
		public Criteria andSzTypeIsNull() {
			addCriterion("sz_type is null");
			return (Criteria) this;
		}
		public Criteria andSzTypeIsNotNull(){
			addCriterion("sz_type is not null");
			return (Criteria) this;
		}
		public Criteria andSzTypeEqualTo(String value) {
			addCriterion("sz_type =", value, "szType");
			return (Criteria) this;
		}
		public Criteria andSzTypeNotEqualTo(String value) {
			addCriterion("sz_type <>", value, "szType");
			return (Criteria) this;
		}
		public Criteria andSzTypeGreaterThan(String value) {
			addCriterion("sz_type >", value, "szType");
			return (Criteria) this;
		}
		public Criteria andSzTypeGreaterThanOrEqualTo(String value) {
			addCriterion("sz_type >=", value, "SzType");
			return (Criteria) this;
		}
		public Criteria andSzTypeLessThan(String value) {
			addCriterion("sz_type <", value, "szType");
			return (Criteria) this;
		}
		public Criteria andSzTypeLessThanOrEqualTo(String value) {
			addCriterion("sz_type <=", value, "szType");
			return (Criteria) this;
		}
		public Criteria andSzTypeIn(List<String> values) {
			addCriterion("sz_type in", values, "szType");
			return (Criteria) this;
		}
		public Criteria andSzTypeNotIn(List<String> values) {
			addCriterion("sz_type not in", values, "szType");
			return (Criteria) this;
		}
		public Criteria andSzTypeBetween(String value1, String value2) {
			addCriterion("sz_type between", value1, value2, "szType");
			return (Criteria) this;
		}
		public Criteria andSzTypeNotBetween(String value1, String value2) {
			addCriterion("sz_type not between", value1, value2, "szType");
			return (Criteria) this;
		}
		public Criteria andSzTypeLike(String value) {
			addCriterion("sz_type like", value, "szType");
			return (Criteria) this;
		}
		public Criteria andSzTypeNotLike(String value) {
			addCriterion("sz_type not like", value, "szType");
			return (Criteria) this;
		}
		public Criteria andFixZzIsNull() {
			addCriterion("fix_zz is null");
			return (Criteria) this;
		}
		public Criteria andFixZzIsNotNull(){
			addCriterion("fix_zz is not null");
			return (Criteria) this;
		}
		public Criteria andFixZzEqualTo(String value) {
			addCriterion("fix_zz =", value, "fixZz");
			return (Criteria) this;
		}
		public Criteria andFixZzNotEqualTo(String value) {
			addCriterion("fix_zz <>", value, "fixZz");
			return (Criteria) this;
		}
		public Criteria andFixZzGreaterThan(String value) {
			addCriterion("fix_zz >", value, "fixZz");
			return (Criteria) this;
		}
		public Criteria andFixZzGreaterThanOrEqualTo(String value) {
			addCriterion("fix_zz >=", value, "FixZz");
			return (Criteria) this;
		}
		public Criteria andFixZzLessThan(String value) {
			addCriterion("fix_zz <", value, "fixZz");
			return (Criteria) this;
		}
		public Criteria andFixZzLessThanOrEqualTo(String value) {
			addCriterion("fix_zz <=", value, "fixZz");
			return (Criteria) this;
		}
		public Criteria andFixZzIn(List<String> values) {
			addCriterion("fix_zz in", values, "fixZz");
			return (Criteria) this;
		}
		public Criteria andFixZzNotIn(List<String> values) {
			addCriterion("fix_zz not in", values, "fixZz");
			return (Criteria) this;
		}
		public Criteria andFixZzBetween(String value1, String value2) {
			addCriterion("fix_zz between", value1, value2, "fixZz");
			return (Criteria) this;
		}
		public Criteria andFixZzNotBetween(String value1, String value2) {
			addCriterion("fix_zz not between", value1, value2, "fixZz");
			return (Criteria) this;
		}
		public Criteria andFixZzLike(String value) {
			addCriterion("fix_zz like", value, "fixZz");
			return (Criteria) this;
		}
		public Criteria andFixZzNotLike(String value) {
			addCriterion("fix_zz not like", value, "fixZz");
			return (Criteria) this;
		}
		public Criteria andCheckStatusIsNull() {
			addCriterion("check_status is null");
			return (Criteria) this;
		}
		public Criteria andCheckStatusIsNotNull(){
			addCriterion("check_status is not null");
			return (Criteria) this;
		}
		public Criteria andCheckStatusEqualTo(Integer value) {
			addCriterion("check_status =", value, "checkStatus");
			return (Criteria) this;
		}
		public Criteria andCheckStatusNotEqualTo(Integer value) {
			addCriterion("check_status <>", value, "checkStatus");
			return (Criteria) this;
		}
		public Criteria andCheckStatusGreaterThan(Integer value) {
			addCriterion("check_status >", value, "checkStatus");
			return (Criteria) this;
		}
		public Criteria andCheckStatusGreaterThanOrEqualTo(Integer value) {
			addCriterion("check_status >=", value, "CheckStatus");
			return (Criteria) this;
		}
		public Criteria andCheckStatusLessThan(Integer value) {
			addCriterion("check_status <", value, "checkStatus");
			return (Criteria) this;
		}
		public Criteria andCheckStatusLessThanOrEqualTo(Integer value) {
			addCriterion("check_status <=", value, "checkStatus");
			return (Criteria) this;
		}
		public Criteria andCheckStatusIn(List<Integer> values) {
			if(values.size()==0){
				addCriterion("check_status =", 0, "checkStatus");
				return (Criteria) this;
			}
			addCriterion("check_status in", values, "checkStatus");
			return (Criteria) this;
		}
		public Criteria andCheckStatusNotIn(List<Integer> values) {
			addCriterion("check_status not in", values, "checkStatus");
			return (Criteria) this;
		}
		public Criteria andCheckStatusBetween(Integer value1, Integer value2) {
			addCriterion("check_status between", value1, value2, "checkStatus");
			return (Criteria) this;
		}
		public Criteria andCheckStatusNotBetween(Integer value1, Integer value2) {
			addCriterion("check_status not between", value1, value2, "checkStatus");
			return (Criteria) this;
		}
		public Criteria andCheckStatusLike(Integer value) {
			addCriterion("check_status like", value, "checkStatus");
			return (Criteria) this;
		}
		public Criteria andCheckStatusNotLike(Integer value) {
			addCriterion("check_status not like", value, "checkStatus");
			return (Criteria) this;
		}
		public Criteria andTevalScoreIsNull() {
			addCriterion("teval_score is null");
			return (Criteria) this;
		}
		public Criteria andTevalScoreIsNotNull(){
			addCriterion("teval_score is not null");
			return (Criteria) this;
		}
		public Criteria andTevalScoreEqualTo(Double value) {
			addCriterion("teval_score =", value, "tevalScore");
			return (Criteria) this;
		}
		public Criteria andTevalScoreNotEqualTo(Double value) {
			addCriterion("teval_score <>", value, "tevalScore");
			return (Criteria) this;
		}
		public Criteria andTevalScoreGreaterThan(Double value) {
			addCriterion("teval_score >", value, "tevalScore");
			return (Criteria) this;
		}
		public Criteria andTevalScoreGreaterThanOrEqualTo(Double value) {
			addCriterion("teval_score >=", value, "TevalScore");
			return (Criteria) this;
		}
		public Criteria andTevalScoreLessThan(Double value) {
			addCriterion("teval_score <", value, "tevalScore");
			return (Criteria) this;
		}
		public Criteria andTevalScoreLessThanOrEqualTo(Double value) {
			addCriterion("teval_score <=", value, "tevalScore");
			return (Criteria) this;
		}
		public Criteria andTevalScoreIn(List<Double> values) {
			addCriterion("teval_score in", values, "tevalScore");
			return (Criteria) this;
		}
		public Criteria andTevalScoreNotIn(List<Double> values) {
			addCriterion("teval_score not in", values, "tevalScore");
			return (Criteria) this;
		}
		public Criteria andTevalScoreBetween(Double value1, Double value2) {
			addCriterion("teval_score between", value1, value2, "tevalScore");
			return (Criteria) this;
		}
		public Criteria andTevalScoreNotBetween(Double value1, Double value2) {
			addCriterion("teval_score not between", value1, value2, "tevalScore");
			return (Criteria) this;
		}
		public Criteria andTevalScoreLike(Double value) {
			addCriterion("teval_score like", value, "tevalScore");
			return (Criteria) this;
		}
		public Criteria andTevalScoreNotLike(Double value) {
			addCriterion("teval_score not like", value, "tevalScore");
			return (Criteria) this;
		}
		public Criteria andZcTimeIsNull() {
			addCriterion("zc_time is null");
			return (Criteria) this;
		}
		public Criteria andZcTimeIsNotNull(){
			addCriterion("zc_time is not null");
			return (Criteria) this;
		}
		public Criteria andZcTimeEqualTo(String value) {
			addCriterion("zc_time =", value, "zcTime");
			return (Criteria) this;
		}
		public Criteria andZcTimeNotEqualTo(String value) {
			addCriterion("zc_time <>", value, "zcTime");
			return (Criteria) this;
		}
		public Criteria andZcTimeGreaterThan(String value) {
			addCriterion("zc_time >", value, "zcTime");
			return (Criteria) this;
		}
		public Criteria andZcTimeGreaterThanOrEqualTo(String value) {
			addCriterion("zc_time >=", value, "ZcTime");
			return (Criteria) this;
		}
		public Criteria andZcTimeLessThan(String value) {
			addCriterion("zc_time <", value, "zcTime");
			return (Criteria) this;
		}
		public Criteria andZcTimeLessThanOrEqualTo(String value) {
			addCriterion("zc_time <=", value, "zcTime");
			return (Criteria) this;
		}
		public Criteria andZcTimeIn(List<String> values) {
			addCriterion("zc_time in", values, "zcTime");
			return (Criteria) this;
		}
		public Criteria andZcTimeNotIn(List<String> values) {
			addCriterion("zc_time not in", values, "zcTime");
			return (Criteria) this;
		}
		public Criteria andZcTimeBetween(String value1, String value2) {
			addCriterion("zc_time between", value1, value2, "zcTime");
			return (Criteria) this;
		}
		public Criteria andZcTimeNotBetween(String value1, String value2) {
			addCriterion("zc_time not between", value1, value2, "zcTime");
			return (Criteria) this;
		}
		public Criteria andZcTimeLike(String value) {
			addCriterion("zc_time like", value, "zcTime");
			return (Criteria) this;
		}
		public Criteria andZcTimeNotLike(String value) {
			addCriterion("zc_time not like", value, "zcTime");
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
