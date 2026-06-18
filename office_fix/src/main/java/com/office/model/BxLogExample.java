package com.office.model;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
public class BxLogExample {
	protected String orderByClause;
	protected boolean distinct;
	protected int startRow;
	protected int pageRows;
	protected List<Criteria> oredCriteria;
	public BxLogExample() {
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
		public Criteria andBxNoIsNull() {
			addCriterion("bx_no is null");
			return (Criteria) this;
		}
		public Criteria andBxNoIsNotNull(){
			addCriterion("bx_no is not null");
			return (Criteria) this;
		}
		public Criteria andBxNoEqualTo(String value) {
			addCriterion("bx_no =", value, "bxNo");
			return (Criteria) this;
		}
		public Criteria andBxNoNotEqualTo(String value) {
			addCriterion("bx_no <>", value, "bxNo");
			return (Criteria) this;
		}
		public Criteria andBxNoGreaterThan(String value) {
			addCriterion("bx_no >", value, "bxNo");
			return (Criteria) this;
		}
		public Criteria andBxNoGreaterThanOrEqualTo(String value) {
			addCriterion("bx_no >=", value, "BxNo");
			return (Criteria) this;
		}
		public Criteria andBxNoLessThan(String value) {
			addCriterion("bx_no <", value, "bxNo");
			return (Criteria) this;
		}
		public Criteria andBxNoLessThanOrEqualTo(String value) {
			addCriterion("bx_no <=", value, "bxNo");
			return (Criteria) this;
		}
		public Criteria andBxNoIn(List<String> values) {
			addCriterion("bx_no in", values, "bxNo");
			return (Criteria) this;
		}
		public Criteria andBxNoNotIn(List<String> values) {
			addCriterion("bx_no not in", values, "bxNo");
			return (Criteria) this;
		}
		public Criteria andBxNoBetween(String value1, String value2) {
			addCriterion("bx_no between", value1, value2, "bxNo");
			return (Criteria) this;
		}
		public Criteria andBxNoNotBetween(String value1, String value2) {
			addCriterion("bx_no not between", value1, value2, "bxNo");
			return (Criteria) this;
		}
		public Criteria andBxNoLike(String value) {
			addCriterion("bx_no like", value, "bxNo");
			return (Criteria) this;
		}
		public Criteria andBxNoNotLike(String value) {
			addCriterion("bx_no not like", value, "bxNo");
			return (Criteria) this;
		}
		public Criteria andBgServiceIsNull() {
			addCriterion("bg_service is null");
			return (Criteria) this;
		}
		public Criteria andBgServiceIsNotNull(){
			addCriterion("bg_service is not null");
			return (Criteria) this;
		}
		public Criteria andBgServiceEqualTo(Long value) {
			addCriterion("bg_service =", value, "bgService");
			return (Criteria) this;
		}
		public Criteria andBgServiceNotEqualTo(Long value) {
			addCriterion("bg_service <>", value, "bgService");
			return (Criteria) this;
		}
		public Criteria andBgServiceGreaterThan(Long value) {
			addCriterion("bg_service >", value, "bgService");
			return (Criteria) this;
		}
		public Criteria andBgServiceGreaterThanOrEqualTo(Long value) {
			addCriterion("bg_service >=", value, "BgService");
			return (Criteria) this;
		}
		public Criteria andBgServiceLessThan(Long value) {
			addCriterion("bg_service <", value, "bgService");
			return (Criteria) this;
		}
		public Criteria andBgServiceLessThanOrEqualTo(Long value) {
			addCriterion("bg_service <=", value, "bgService");
			return (Criteria) this;
		}
		public Criteria andBgServiceIn(List<Long> values) {
			if(values.size()==0){
				addCriterion("bg_service =", 0, "bgService");
				return (Criteria) this;
			}
			addCriterion("bg_service in", values, "bgService");
			return (Criteria) this;
		}
		public Criteria andBgServiceNotIn(List<Long> values) {
			addCriterion("bg_service not in", values, "bgService");
			return (Criteria) this;
		}
		public Criteria andBgServiceBetween(Long value1, Long value2) {
			addCriterion("bg_service between", value1, value2, "bgService");
			return (Criteria) this;
		}
		public Criteria andBgServiceNotBetween(Long value1, Long value2) {
			addCriterion("bg_service not between", value1, value2, "bgService");
			return (Criteria) this;
		}
		public Criteria andBgServiceLike(Long value) {
			addCriterion("bg_service like", value, "bgService");
			return (Criteria) this;
		}
		public Criteria andBgServiceNotLike(Long value) {
			addCriterion("bg_service not like", value, "bgService");
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
		public Criteria andYxcdIsNull() {
			addCriterion("yxcd is null");
			return (Criteria) this;
		}
		public Criteria andYxcdIsNotNull(){
			addCriterion("yxcd is not null");
			return (Criteria) this;
		}
		public Criteria andYxcdEqualTo(Integer value) {
			addCriterion("yxcd =", value, "yxcd");
			return (Criteria) this;
		}
		public Criteria andYxcdNotEqualTo(Integer value) {
			addCriterion("yxcd <>", value, "yxcd");
			return (Criteria) this;
		}
		public Criteria andYxcdGreaterThan(Integer value) {
			addCriterion("yxcd >", value, "yxcd");
			return (Criteria) this;
		}
		public Criteria andYxcdGreaterThanOrEqualTo(Integer value) {
			addCriterion("yxcd >=", value, "Yxcd");
			return (Criteria) this;
		}
		public Criteria andYxcdLessThan(Integer value) {
			addCriterion("yxcd <", value, "yxcd");
			return (Criteria) this;
		}
		public Criteria andYxcdLessThanOrEqualTo(Integer value) {
			addCriterion("yxcd <=", value, "yxcd");
			return (Criteria) this;
		}
		public Criteria andYxcdIn(List<Integer> values) {
			if(values.size()==0){
				addCriterion("yxcd =", 0, "yxcd");
				return (Criteria) this;
			}
			addCriterion("yxcd in", values, "yxcd");
			return (Criteria) this;
		}
		public Criteria andYxcdNotIn(List<Integer> values) {
			addCriterion("yxcd not in", values, "yxcd");
			return (Criteria) this;
		}
		public Criteria andYxcdBetween(Integer value1, Integer value2) {
			addCriterion("yxcd between", value1, value2, "yxcd");
			return (Criteria) this;
		}
		public Criteria andYxcdNotBetween(Integer value1, Integer value2) {
			addCriterion("yxcd not between", value1, value2, "yxcd");
			return (Criteria) this;
		}
		public Criteria andYxcdLike(Integer value) {
			addCriterion("yxcd like", value, "yxcd");
			return (Criteria) this;
		}
		public Criteria andYxcdNotLike(Integer value) {
			addCriterion("yxcd not like", value, "yxcd");
			return (Criteria) this;
		}
		public Criteria andGzmsIsNull() {
			addCriterion("gzms is null");
			return (Criteria) this;
		}
		public Criteria andGzmsIsNotNull(){
			addCriterion("gzms is not null");
			return (Criteria) this;
		}
		public Criteria andGzmsEqualTo(String value) {
			addCriterion("gzms =", value, "gzms");
			return (Criteria) this;
		}
		public Criteria andGzmsNotEqualTo(String value) {
			addCriterion("gzms <>", value, "gzms");
			return (Criteria) this;
		}
		public Criteria andGzmsGreaterThan(String value) {
			addCriterion("gzms >", value, "gzms");
			return (Criteria) this;
		}
		public Criteria andGzmsGreaterThanOrEqualTo(String value) {
			addCriterion("gzms >=", value, "Gzms");
			return (Criteria) this;
		}
		public Criteria andGzmsLessThan(String value) {
			addCriterion("gzms <", value, "gzms");
			return (Criteria) this;
		}
		public Criteria andGzmsLessThanOrEqualTo(String value) {
			addCriterion("gzms <=", value, "gzms");
			return (Criteria) this;
		}
		public Criteria andGzmsIn(List<String> values) {
			addCriterion("gzms in", values, "gzms");
			return (Criteria) this;
		}
		public Criteria andGzmsNotIn(List<String> values) {
			addCriterion("gzms not in", values, "gzms");
			return (Criteria) this;
		}
		public Criteria andGzmsBetween(String value1, String value2) {
			addCriterion("gzms between", value1, value2, "gzms");
			return (Criteria) this;
		}
		public Criteria andGzmsNotBetween(String value1, String value2) {
			addCriterion("gzms not between", value1, value2, "gzms");
			return (Criteria) this;
		}
		public Criteria andGzmsLike(String value) {
			addCriterion("gzms like", value, "gzms");
			return (Criteria) this;
		}
		public Criteria andGzmsNotLike(String value) {
			addCriterion("gzms not like", value, "gzms");
			return (Criteria) this;
		}
		public Criteria andGzImgIsNull() {
			addCriterion("gz_img is null");
			return (Criteria) this;
		}
		public Criteria andGzImgIsNotNull(){
			addCriterion("gz_img is not null");
			return (Criteria) this;
		}
		public Criteria andGzImgEqualTo(String value) {
			addCriterion("gz_img =", value, "gzImg");
			return (Criteria) this;
		}
		public Criteria andGzImgNotEqualTo(String value) {
			addCriterion("gz_img <>", value, "gzImg");
			return (Criteria) this;
		}
		public Criteria andGzImgGreaterThan(String value) {
			addCriterion("gz_img >", value, "gzImg");
			return (Criteria) this;
		}
		public Criteria andGzImgGreaterThanOrEqualTo(String value) {
			addCriterion("gz_img >=", value, "GzImg");
			return (Criteria) this;
		}
		public Criteria andGzImgLessThan(String value) {
			addCriterion("gz_img <", value, "gzImg");
			return (Criteria) this;
		}
		public Criteria andGzImgLessThanOrEqualTo(String value) {
			addCriterion("gz_img <=", value, "gzImg");
			return (Criteria) this;
		}
		public Criteria andGzImgIn(List<String> values) {
			addCriterion("gz_img in", values, "gzImg");
			return (Criteria) this;
		}
		public Criteria andGzImgNotIn(List<String> values) {
			addCriterion("gz_img not in", values, "gzImg");
			return (Criteria) this;
		}
		public Criteria andGzImgBetween(String value1, String value2) {
			addCriterion("gz_img between", value1, value2, "gzImg");
			return (Criteria) this;
		}
		public Criteria andGzImgNotBetween(String value1, String value2) {
			addCriterion("gz_img not between", value1, value2, "gzImg");
			return (Criteria) this;
		}
		public Criteria andGzImgLike(String value) {
			addCriterion("gz_img like", value, "gzImg");
			return (Criteria) this;
		}
		public Criteria andGzImgNotLike(String value) {
			addCriterion("gz_img not like", value, "gzImg");
			return (Criteria) this;
		}
		public Criteria andGzVeoIsNull() {
			addCriterion("gz_veo is null");
			return (Criteria) this;
		}
		public Criteria andGzVeoIsNotNull(){
			addCriterion("gz_veo is not null");
			return (Criteria) this;
		}
		public Criteria andGzVeoEqualTo(String value) {
			addCriterion("gz_veo =", value, "gzVeo");
			return (Criteria) this;
		}
		public Criteria andGzVeoNotEqualTo(String value) {
			addCriterion("gz_veo <>", value, "gzVeo");
			return (Criteria) this;
		}
		public Criteria andGzVeoGreaterThan(String value) {
			addCriterion("gz_veo >", value, "gzVeo");
			return (Criteria) this;
		}
		public Criteria andGzVeoGreaterThanOrEqualTo(String value) {
			addCriterion("gz_veo >=", value, "GzVeo");
			return (Criteria) this;
		}
		public Criteria andGzVeoLessThan(String value) {
			addCriterion("gz_veo <", value, "gzVeo");
			return (Criteria) this;
		}
		public Criteria andGzVeoLessThanOrEqualTo(String value) {
			addCriterion("gz_veo <=", value, "gzVeo");
			return (Criteria) this;
		}
		public Criteria andGzVeoIn(List<String> values) {
			addCriterion("gz_veo in", values, "gzVeo");
			return (Criteria) this;
		}
		public Criteria andGzVeoNotIn(List<String> values) {
			addCriterion("gz_veo not in", values, "gzVeo");
			return (Criteria) this;
		}
		public Criteria andGzVeoBetween(String value1, String value2) {
			addCriterion("gz_veo between", value1, value2, "gzVeo");
			return (Criteria) this;
		}
		public Criteria andGzVeoNotBetween(String value1, String value2) {
			addCriterion("gz_veo not between", value1, value2, "gzVeo");
			return (Criteria) this;
		}
		public Criteria andGzVeoLike(String value) {
			addCriterion("gz_veo like", value, "gzVeo");
			return (Criteria) this;
		}
		public Criteria andGzVeoNotLike(String value) {
			addCriterion("gz_veo not like", value, "gzVeo");
			return (Criteria) this;
		}
		public Criteria andGzLocationIsNull() {
			addCriterion("gz_location is null");
			return (Criteria) this;
		}
		public Criteria andGzLocationIsNotNull(){
			addCriterion("gz_location is not null");
			return (Criteria) this;
		}
		public Criteria andGzLocationEqualTo(String value) {
			addCriterion("gz_location =", value, "gzLocation");
			return (Criteria) this;
		}
		public Criteria andGzLocationNotEqualTo(String value) {
			addCriterion("gz_location <>", value, "gzLocation");
			return (Criteria) this;
		}
		public Criteria andGzLocationGreaterThan(String value) {
			addCriterion("gz_location >", value, "gzLocation");
			return (Criteria) this;
		}
		public Criteria andGzLocationGreaterThanOrEqualTo(String value) {
			addCriterion("gz_location >=", value, "GzLocation");
			return (Criteria) this;
		}
		public Criteria andGzLocationLessThan(String value) {
			addCriterion("gz_location <", value, "gzLocation");
			return (Criteria) this;
		}
		public Criteria andGzLocationLessThanOrEqualTo(String value) {
			addCriterion("gz_location <=", value, "gzLocation");
			return (Criteria) this;
		}
		public Criteria andGzLocationIn(List<String> values) {
			addCriterion("gz_location in", values, "gzLocation");
			return (Criteria) this;
		}
		public Criteria andGzLocationNotIn(List<String> values) {
			addCriterion("gz_location not in", values, "gzLocation");
			return (Criteria) this;
		}
		public Criteria andGzLocationBetween(String value1, String value2) {
			addCriterion("gz_location between", value1, value2, "gzLocation");
			return (Criteria) this;
		}
		public Criteria andGzLocationNotBetween(String value1, String value2) {
			addCriterion("gz_location not between", value1, value2, "gzLocation");
			return (Criteria) this;
		}
		public Criteria andGzLocationLike(String value) {
			addCriterion("gz_location like", value, "gzLocation");
			return (Criteria) this;
		}
		public Criteria andGzLocationNotLike(String value) {
			addCriterion("gz_location not like", value, "gzLocation");
			return (Criteria) this;
		}
		public Criteria andBxStatusIsNull() {
			addCriterion("bx_status is null");
			return (Criteria) this;
		}
		public Criteria andBxStatusIsNotNull(){
			addCriterion("bx_status is not null");
			return (Criteria) this;
		}
		public Criteria andBxStatusEqualTo(Integer value) {
			addCriterion("bx_status =", value, "bxStatus");
			return (Criteria) this;
		}
		public Criteria andBxStatusNotEqualTo(Integer value) {
			addCriterion("bx_status <>", value, "bxStatus");
			return (Criteria) this;
		}
		public Criteria andBxStatusGreaterThan(Integer value) {
			addCriterion("bx_status >", value, "bxStatus");
			return (Criteria) this;
		}
		public Criteria andBxStatusGreaterThanOrEqualTo(Integer value) {
			addCriterion("bx_status >=", value, "BxStatus");
			return (Criteria) this;
		}
		public Criteria andBxStatusLessThan(Integer value) {
			addCriterion("bx_status <", value, "bxStatus");
			return (Criteria) this;
		}
		public Criteria andBxStatusLessThanOrEqualTo(Integer value) {
			addCriterion("bx_status <=", value, "bxStatus");
			return (Criteria) this;
		}
		public Criteria andBxStatusIn(List<Integer> values) {
			if(values.size()==0){
				addCriterion("bx_status =", 0, "bxStatus");
				return (Criteria) this;
			}
			addCriterion("bx_status in", values, "bxStatus");
			return (Criteria) this;
		}
		public Criteria andBxStatusNotIn(List<Integer> values) {
			addCriterion("bx_status not in", values, "bxStatus");
			return (Criteria) this;
		}
		public Criteria andBxStatusBetween(Integer value1, Integer value2) {
			addCriterion("bx_status between", value1, value2, "bxStatus");
			return (Criteria) this;
		}
		public Criteria andBxStatusNotBetween(Integer value1, Integer value2) {
			addCriterion("bx_status not between", value1, value2, "bxStatus");
			return (Criteria) this;
		}
		public Criteria andBxStatusLike(Integer value) {
			addCriterion("bx_status like", value, "bxStatus");
			return (Criteria) this;
		}
		public Criteria andBxStatusNotLike(Integer value) {
			addCriterion("bx_status not like", value, "bxStatus");
			return (Criteria) this;
		}
		public Criteria andAdminRemarkIsNull() {
			addCriterion("admin_remark is null");
			return (Criteria) this;
		}
		public Criteria andAdminRemarkIsNotNull(){
			addCriterion("admin_remark is not null");
			return (Criteria) this;
		}
		public Criteria andAdminRemarkEqualTo(String value) {
			addCriterion("admin_remark =", value, "adminRemark");
			return (Criteria) this;
		}
		public Criteria andAdminRemarkNotEqualTo(String value) {
			addCriterion("admin_remark <>", value, "adminRemark");
			return (Criteria) this;
		}
		public Criteria andAdminRemarkGreaterThan(String value) {
			addCriterion("admin_remark >", value, "adminRemark");
			return (Criteria) this;
		}
		public Criteria andAdminRemarkGreaterThanOrEqualTo(String value) {
			addCriterion("admin_remark >=", value, "AdminRemark");
			return (Criteria) this;
		}
		public Criteria andAdminRemarkLessThan(String value) {
			addCriterion("admin_remark <", value, "adminRemark");
			return (Criteria) this;
		}
		public Criteria andAdminRemarkLessThanOrEqualTo(String value) {
			addCriterion("admin_remark <=", value, "adminRemark");
			return (Criteria) this;
		}
		public Criteria andAdminRemarkIn(List<String> values) {
			addCriterion("admin_remark in", values, "adminRemark");
			return (Criteria) this;
		}
		public Criteria andAdminRemarkNotIn(List<String> values) {
			addCriterion("admin_remark not in", values, "adminRemark");
			return (Criteria) this;
		}
		public Criteria andAdminRemarkBetween(String value1, String value2) {
			addCriterion("admin_remark between", value1, value2, "adminRemark");
			return (Criteria) this;
		}
		public Criteria andAdminRemarkNotBetween(String value1, String value2) {
			addCriterion("admin_remark not between", value1, value2, "adminRemark");
			return (Criteria) this;
		}
		public Criteria andAdminRemarkLike(String value) {
			addCriterion("admin_remark like", value, "adminRemark");
			return (Criteria) this;
		}
		public Criteria andAdminRemarkNotLike(String value) {
			addCriterion("admin_remark not like", value, "adminRemark");
			return (Criteria) this;
		}
		public Criteria andYsNoteIsNull() {
			addCriterion("ys_note is null");
			return (Criteria) this;
		}
		public Criteria andYsNoteIsNotNull(){
			addCriterion("ys_note is not null");
			return (Criteria) this;
		}
		public Criteria andYsNoteEqualTo(String value) {
			addCriterion("ys_note =", value, "ysNote");
			return (Criteria) this;
		}
		public Criteria andYsNoteNotEqualTo(String value) {
			addCriterion("ys_note <>", value, "ysNote");
			return (Criteria) this;
		}
		public Criteria andYsNoteGreaterThan(String value) {
			addCriterion("ys_note >", value, "ysNote");
			return (Criteria) this;
		}
		public Criteria andYsNoteGreaterThanOrEqualTo(String value) {
			addCriterion("ys_note >=", value, "YsNote");
			return (Criteria) this;
		}
		public Criteria andYsNoteLessThan(String value) {
			addCriterion("ys_note <", value, "ysNote");
			return (Criteria) this;
		}
		public Criteria andYsNoteLessThanOrEqualTo(String value) {
			addCriterion("ys_note <=", value, "ysNote");
			return (Criteria) this;
		}
		public Criteria andYsNoteIn(List<String> values) {
			addCriterion("ys_note in", values, "ysNote");
			return (Criteria) this;
		}
		public Criteria andYsNoteNotIn(List<String> values) {
			addCriterion("ys_note not in", values, "ysNote");
			return (Criteria) this;
		}
		public Criteria andYsNoteBetween(String value1, String value2) {
			addCriterion("ys_note between", value1, value2, "ysNote");
			return (Criteria) this;
		}
		public Criteria andYsNoteNotBetween(String value1, String value2) {
			addCriterion("ys_note not between", value1, value2, "ysNote");
			return (Criteria) this;
		}
		public Criteria andYsNoteLike(String value) {
			addCriterion("ys_note like", value, "ysNote");
			return (Criteria) this;
		}
		public Criteria andYsNoteNotLike(String value) {
			addCriterion("ys_note not like", value, "ysNote");
			return (Criteria) this;
		}
		public Criteria andFixRemarkIsNull() {
			addCriterion("fix_remark is null");
			return (Criteria) this;
		}
		public Criteria andFixRemarkIsNotNull(){
			addCriterion("fix_remark is not null");
			return (Criteria) this;
		}
		public Criteria andFixRemarkEqualTo(String value) {
			addCriterion("fix_remark =", value, "fixRemark");
			return (Criteria) this;
		}
		public Criteria andFixRemarkNotEqualTo(String value) {
			addCriterion("fix_remark <>", value, "fixRemark");
			return (Criteria) this;
		}
		public Criteria andFixRemarkGreaterThan(String value) {
			addCriterion("fix_remark >", value, "fixRemark");
			return (Criteria) this;
		}
		public Criteria andFixRemarkGreaterThanOrEqualTo(String value) {
			addCriterion("fix_remark >=", value, "FixRemark");
			return (Criteria) this;
		}
		public Criteria andFixRemarkLessThan(String value) {
			addCriterion("fix_remark <", value, "fixRemark");
			return (Criteria) this;
		}
		public Criteria andFixRemarkLessThanOrEqualTo(String value) {
			addCriterion("fix_remark <=", value, "fixRemark");
			return (Criteria) this;
		}
		public Criteria andFixRemarkIn(List<String> values) {
			addCriterion("fix_remark in", values, "fixRemark");
			return (Criteria) this;
		}
		public Criteria andFixRemarkNotIn(List<String> values) {
			addCriterion("fix_remark not in", values, "fixRemark");
			return (Criteria) this;
		}
		public Criteria andFixRemarkBetween(String value1, String value2) {
			addCriterion("fix_remark between", value1, value2, "fixRemark");
			return (Criteria) this;
		}
		public Criteria andFixRemarkNotBetween(String value1, String value2) {
			addCriterion("fix_remark not between", value1, value2, "fixRemark");
			return (Criteria) this;
		}
		public Criteria andFixRemarkLike(String value) {
			addCriterion("fix_remark like", value, "fixRemark");
			return (Criteria) this;
		}
		public Criteria andFixRemarkNotLike(String value) {
			addCriterion("fix_remark not like", value, "fixRemark");
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
