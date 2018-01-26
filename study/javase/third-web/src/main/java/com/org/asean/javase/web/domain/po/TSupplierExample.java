package com.org.asean.javase.web.domain.po;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class TSupplierExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public TSupplierExample() {
        oredCriteria = new ArrayList<Criteria>();
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
            criteria = new ArrayList<Criterion>();
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

        public Criteria andSupplierIdIsNull() {
            addCriterion("supplier_id is null");
            return (Criteria) this;
        }

        public Criteria andSupplierIdIsNotNull() {
            addCriterion("supplier_id is not null");
            return (Criteria) this;
        }

        public Criteria andSupplierIdEqualTo(Long value) {
            addCriterion("supplier_id =", value, "supplierId");
            return (Criteria) this;
        }

        public Criteria andSupplierIdNotEqualTo(Long value) {
            addCriterion("supplier_id <>", value, "supplierId");
            return (Criteria) this;
        }

        public Criteria andSupplierIdGreaterThan(Long value) {
            addCriterion("supplier_id >", value, "supplierId");
            return (Criteria) this;
        }

        public Criteria andSupplierIdGreaterThanOrEqualTo(Long value) {
            addCriterion("supplier_id >=", value, "supplierId");
            return (Criteria) this;
        }

        public Criteria andSupplierIdLessThan(Long value) {
            addCriterion("supplier_id <", value, "supplierId");
            return (Criteria) this;
        }

        public Criteria andSupplierIdLessThanOrEqualTo(Long value) {
            addCriterion("supplier_id <=", value, "supplierId");
            return (Criteria) this;
        }

        public Criteria andSupplierIdIn(List<Long> values) {
            addCriterion("supplier_id in", values, "supplierId");
            return (Criteria) this;
        }

        public Criteria andSupplierIdNotIn(List<Long> values) {
            addCriterion("supplier_id not in", values, "supplierId");
            return (Criteria) this;
        }

        public Criteria andSupplierIdBetween(Long value1, Long value2) {
            addCriterion("supplier_id between", value1, value2, "supplierId");
            return (Criteria) this;
        }

        public Criteria andSupplierIdNotBetween(Long value1, Long value2) {
            addCriterion("supplier_id not between", value1, value2, "supplierId");
            return (Criteria) this;
        }

        public Criteria andCompanyCodeIsNull() {
            addCriterion("company_code is null");
            return (Criteria) this;
        }

        public Criteria andCompanyCodeIsNotNull() {
            addCriterion("company_code is not null");
            return (Criteria) this;
        }

        public Criteria andCompanyCodeEqualTo(String value) {
            addCriterion("company_code =", value, "companyCode");
            return (Criteria) this;
        }

        public Criteria andCompanyCodeNotEqualTo(String value) {
            addCriterion("company_code <>", value, "companyCode");
            return (Criteria) this;
        }

        public Criteria andCompanyCodeGreaterThan(String value) {
            addCriterion("company_code >", value, "companyCode");
            return (Criteria) this;
        }

        public Criteria andCompanyCodeGreaterThanOrEqualTo(String value) {
            addCriterion("company_code >=", value, "companyCode");
            return (Criteria) this;
        }

        public Criteria andCompanyCodeLessThan(String value) {
            addCriterion("company_code <", value, "companyCode");
            return (Criteria) this;
        }

        public Criteria andCompanyCodeLessThanOrEqualTo(String value) {
            addCriterion("company_code <=", value, "companyCode");
            return (Criteria) this;
        }

        public Criteria andCompanyCodeLike(String value) {
            addCriterion("company_code like", value, "companyCode");
            return (Criteria) this;
        }

        public Criteria andCompanyCodeNotLike(String value) {
            addCriterion("company_code not like", value, "companyCode");
            return (Criteria) this;
        }

        public Criteria andCompanyCodeIn(List<String> values) {
            addCriterion("company_code in", values, "companyCode");
            return (Criteria) this;
        }

        public Criteria andCompanyCodeNotIn(List<String> values) {
            addCriterion("company_code not in", values, "companyCode");
            return (Criteria) this;
        }

        public Criteria andCompanyCodeBetween(String value1, String value2) {
            addCriterion("company_code between", value1, value2, "companyCode");
            return (Criteria) this;
        }

        public Criteria andCompanyCodeNotBetween(String value1, String value2) {
            addCriterion("company_code not between", value1, value2, "companyCode");
            return (Criteria) this;
        }

        public Criteria andCompanyTypeIsNull() {
            addCriterion("company_type is null");
            return (Criteria) this;
        }

        public Criteria andCompanyTypeIsNotNull() {
            addCriterion("company_type is not null");
            return (Criteria) this;
        }

        public Criteria andCompanyTypeEqualTo(String value) {
            addCriterion("company_type =", value, "companyType");
            return (Criteria) this;
        }

        public Criteria andCompanyTypeNotEqualTo(String value) {
            addCriterion("company_type <>", value, "companyType");
            return (Criteria) this;
        }

        public Criteria andCompanyTypeGreaterThan(String value) {
            addCriterion("company_type >", value, "companyType");
            return (Criteria) this;
        }

        public Criteria andCompanyTypeGreaterThanOrEqualTo(String value) {
            addCriterion("company_type >=", value, "companyType");
            return (Criteria) this;
        }

        public Criteria andCompanyTypeLessThan(String value) {
            addCriterion("company_type <", value, "companyType");
            return (Criteria) this;
        }

        public Criteria andCompanyTypeLessThanOrEqualTo(String value) {
            addCriterion("company_type <=", value, "companyType");
            return (Criteria) this;
        }

        public Criteria andCompanyTypeLike(String value) {
            addCriterion("company_type like", value, "companyType");
            return (Criteria) this;
        }

        public Criteria andCompanyTypeNotLike(String value) {
            addCriterion("company_type not like", value, "companyType");
            return (Criteria) this;
        }

        public Criteria andCompanyTypeIn(List<String> values) {
            addCriterion("company_type in", values, "companyType");
            return (Criteria) this;
        }

        public Criteria andCompanyTypeNotIn(List<String> values) {
            addCriterion("company_type not in", values, "companyType");
            return (Criteria) this;
        }

        public Criteria andCompanyTypeBetween(String value1, String value2) {
            addCriterion("company_type between", value1, value2, "companyType");
            return (Criteria) this;
        }

        public Criteria andCompanyTypeNotBetween(String value1, String value2) {
            addCriterion("company_type not between", value1, value2, "companyType");
            return (Criteria) this;
        }

        public Criteria andCompanyNameIsNull() {
            addCriterion("company_name is null");
            return (Criteria) this;
        }

        public Criteria andCompanyNameIsNotNull() {
            addCriterion("company_name is not null");
            return (Criteria) this;
        }

        public Criteria andCompanyNameEqualTo(String value) {
            addCriterion("company_name =", value, "companyName");
            return (Criteria) this;
        }

        public Criteria andCompanyNameNotEqualTo(String value) {
            addCriterion("company_name <>", value, "companyName");
            return (Criteria) this;
        }

        public Criteria andCompanyNameGreaterThan(String value) {
            addCriterion("company_name >", value, "companyName");
            return (Criteria) this;
        }

        public Criteria andCompanyNameGreaterThanOrEqualTo(String value) {
            addCriterion("company_name >=", value, "companyName");
            return (Criteria) this;
        }

        public Criteria andCompanyNameLessThan(String value) {
            addCriterion("company_name <", value, "companyName");
            return (Criteria) this;
        }

        public Criteria andCompanyNameLessThanOrEqualTo(String value) {
            addCriterion("company_name <=", value, "companyName");
            return (Criteria) this;
        }

        public Criteria andCompanyNameLike(String value) {
            addCriterion("company_name like", value, "companyName");
            return (Criteria) this;
        }

        public Criteria andCompanyNameNotLike(String value) {
            addCriterion("company_name not like", value, "companyName");
            return (Criteria) this;
        }

        public Criteria andCompanyNameIn(List<String> values) {
            addCriterion("company_name in", values, "companyName");
            return (Criteria) this;
        }

        public Criteria andCompanyNameNotIn(List<String> values) {
            addCriterion("company_name not in", values, "companyName");
            return (Criteria) this;
        }

        public Criteria andCompanyNameBetween(String value1, String value2) {
            addCriterion("company_name between", value1, value2, "companyName");
            return (Criteria) this;
        }

        public Criteria andCompanyNameNotBetween(String value1, String value2) {
            addCriterion("company_name not between", value1, value2, "companyName");
            return (Criteria) this;
        }

        public Criteria andRegisteredLandIsNull() {
            addCriterion("registered_land is null");
            return (Criteria) this;
        }

        public Criteria andRegisteredLandIsNotNull() {
            addCriterion("registered_land is not null");
            return (Criteria) this;
        }

        public Criteria andRegisteredLandEqualTo(String value) {
            addCriterion("registered_land =", value, "registeredLand");
            return (Criteria) this;
        }

        public Criteria andRegisteredLandNotEqualTo(String value) {
            addCriterion("registered_land <>", value, "registeredLand");
            return (Criteria) this;
        }

        public Criteria andRegisteredLandGreaterThan(String value) {
            addCriterion("registered_land >", value, "registeredLand");
            return (Criteria) this;
        }

        public Criteria andRegisteredLandGreaterThanOrEqualTo(String value) {
            addCriterion("registered_land >=", value, "registeredLand");
            return (Criteria) this;
        }

        public Criteria andRegisteredLandLessThan(String value) {
            addCriterion("registered_land <", value, "registeredLand");
            return (Criteria) this;
        }

        public Criteria andRegisteredLandLessThanOrEqualTo(String value) {
            addCriterion("registered_land <=", value, "registeredLand");
            return (Criteria) this;
        }

        public Criteria andRegisteredLandLike(String value) {
            addCriterion("registered_land like", value, "registeredLand");
            return (Criteria) this;
        }

        public Criteria andRegisteredLandNotLike(String value) {
            addCriterion("registered_land not like", value, "registeredLand");
            return (Criteria) this;
        }

        public Criteria andRegisteredLandIn(List<String> values) {
            addCriterion("registered_land in", values, "registeredLand");
            return (Criteria) this;
        }

        public Criteria andRegisteredLandNotIn(List<String> values) {
            addCriterion("registered_land not in", values, "registeredLand");
            return (Criteria) this;
        }

        public Criteria andRegisteredLandBetween(String value1, String value2) {
            addCriterion("registered_land between", value1, value2, "registeredLand");
            return (Criteria) this;
        }

        public Criteria andRegisteredLandNotBetween(String value1, String value2) {
            addCriterion("registered_land not between", value1, value2, "registeredLand");
            return (Criteria) this;
        }

        public Criteria andCompanyLegalIsNull() {
            addCriterion("company_legal is null");
            return (Criteria) this;
        }

        public Criteria andCompanyLegalIsNotNull() {
            addCriterion("company_legal is not null");
            return (Criteria) this;
        }

        public Criteria andCompanyLegalEqualTo(String value) {
            addCriterion("company_legal =", value, "companyLegal");
            return (Criteria) this;
        }

        public Criteria andCompanyLegalNotEqualTo(String value) {
            addCriterion("company_legal <>", value, "companyLegal");
            return (Criteria) this;
        }

        public Criteria andCompanyLegalGreaterThan(String value) {
            addCriterion("company_legal >", value, "companyLegal");
            return (Criteria) this;
        }

        public Criteria andCompanyLegalGreaterThanOrEqualTo(String value) {
            addCriterion("company_legal >=", value, "companyLegal");
            return (Criteria) this;
        }

        public Criteria andCompanyLegalLessThan(String value) {
            addCriterion("company_legal <", value, "companyLegal");
            return (Criteria) this;
        }

        public Criteria andCompanyLegalLessThanOrEqualTo(String value) {
            addCriterion("company_legal <=", value, "companyLegal");
            return (Criteria) this;
        }

        public Criteria andCompanyLegalLike(String value) {
            addCriterion("company_legal like", value, "companyLegal");
            return (Criteria) this;
        }

        public Criteria andCompanyLegalNotLike(String value) {
            addCriterion("company_legal not like", value, "companyLegal");
            return (Criteria) this;
        }

        public Criteria andCompanyLegalIn(List<String> values) {
            addCriterion("company_legal in", values, "companyLegal");
            return (Criteria) this;
        }

        public Criteria andCompanyLegalNotIn(List<String> values) {
            addCriterion("company_legal not in", values, "companyLegal");
            return (Criteria) this;
        }

        public Criteria andCompanyLegalBetween(String value1, String value2) {
            addCriterion("company_legal between", value1, value2, "companyLegal");
            return (Criteria) this;
        }

        public Criteria andCompanyLegalNotBetween(String value1, String value2) {
            addCriterion("company_legal not between", value1, value2, "companyLegal");
            return (Criteria) this;
        }

        public Criteria andCompanyPhoneIsNull() {
            addCriterion("company_phone is null");
            return (Criteria) this;
        }

        public Criteria andCompanyPhoneIsNotNull() {
            addCriterion("company_phone is not null");
            return (Criteria) this;
        }

        public Criteria andCompanyPhoneEqualTo(String value) {
            addCriterion("company_phone =", value, "companyPhone");
            return (Criteria) this;
        }

        public Criteria andCompanyPhoneNotEqualTo(String value) {
            addCriterion("company_phone <>", value, "companyPhone");
            return (Criteria) this;
        }

        public Criteria andCompanyPhoneGreaterThan(String value) {
            addCriterion("company_phone >", value, "companyPhone");
            return (Criteria) this;
        }

        public Criteria andCompanyPhoneGreaterThanOrEqualTo(String value) {
            addCriterion("company_phone >=", value, "companyPhone");
            return (Criteria) this;
        }

        public Criteria andCompanyPhoneLessThan(String value) {
            addCriterion("company_phone <", value, "companyPhone");
            return (Criteria) this;
        }

        public Criteria andCompanyPhoneLessThanOrEqualTo(String value) {
            addCriterion("company_phone <=", value, "companyPhone");
            return (Criteria) this;
        }

        public Criteria andCompanyPhoneLike(String value) {
            addCriterion("company_phone like", value, "companyPhone");
            return (Criteria) this;
        }

        public Criteria andCompanyPhoneNotLike(String value) {
            addCriterion("company_phone not like", value, "companyPhone");
            return (Criteria) this;
        }

        public Criteria andCompanyPhoneIn(List<String> values) {
            addCriterion("company_phone in", values, "companyPhone");
            return (Criteria) this;
        }

        public Criteria andCompanyPhoneNotIn(List<String> values) {
            addCriterion("company_phone not in", values, "companyPhone");
            return (Criteria) this;
        }

        public Criteria andCompanyPhoneBetween(String value1, String value2) {
            addCriterion("company_phone between", value1, value2, "companyPhone");
            return (Criteria) this;
        }

        public Criteria andCompanyPhoneNotBetween(String value1, String value2) {
            addCriterion("company_phone not between", value1, value2, "companyPhone");
            return (Criteria) this;
        }

        public Criteria andCompanyAddressIsNull() {
            addCriterion("company_address is null");
            return (Criteria) this;
        }

        public Criteria andCompanyAddressIsNotNull() {
            addCriterion("company_address is not null");
            return (Criteria) this;
        }

        public Criteria andCompanyAddressEqualTo(String value) {
            addCriterion("company_address =", value, "companyAddress");
            return (Criteria) this;
        }

        public Criteria andCompanyAddressNotEqualTo(String value) {
            addCriterion("company_address <>", value, "companyAddress");
            return (Criteria) this;
        }

        public Criteria andCompanyAddressGreaterThan(String value) {
            addCriterion("company_address >", value, "companyAddress");
            return (Criteria) this;
        }

        public Criteria andCompanyAddressGreaterThanOrEqualTo(String value) {
            addCriterion("company_address >=", value, "companyAddress");
            return (Criteria) this;
        }

        public Criteria andCompanyAddressLessThan(String value) {
            addCriterion("company_address <", value, "companyAddress");
            return (Criteria) this;
        }

        public Criteria andCompanyAddressLessThanOrEqualTo(String value) {
            addCriterion("company_address <=", value, "companyAddress");
            return (Criteria) this;
        }

        public Criteria andCompanyAddressLike(String value) {
            addCriterion("company_address like", value, "companyAddress");
            return (Criteria) this;
        }

        public Criteria andCompanyAddressNotLike(String value) {
            addCriterion("company_address not like", value, "companyAddress");
            return (Criteria) this;
        }

        public Criteria andCompanyAddressIn(List<String> values) {
            addCriterion("company_address in", values, "companyAddress");
            return (Criteria) this;
        }

        public Criteria andCompanyAddressNotIn(List<String> values) {
            addCriterion("company_address not in", values, "companyAddress");
            return (Criteria) this;
        }

        public Criteria andCompanyAddressBetween(String value1, String value2) {
            addCriterion("company_address between", value1, value2, "companyAddress");
            return (Criteria) this;
        }

        public Criteria andCompanyAddressNotBetween(String value1, String value2) {
            addCriterion("company_address not between", value1, value2, "companyAddress");
            return (Criteria) this;
        }

        public Criteria andRegisteredEmailIsNull() {
            addCriterion("registered_email is null");
            return (Criteria) this;
        }

        public Criteria andRegisteredEmailIsNotNull() {
            addCriterion("registered_email is not null");
            return (Criteria) this;
        }

        public Criteria andRegisteredEmailEqualTo(String value) {
            addCriterion("registered_email =", value, "registeredEmail");
            return (Criteria) this;
        }

        public Criteria andRegisteredEmailNotEqualTo(String value) {
            addCriterion("registered_email <>", value, "registeredEmail");
            return (Criteria) this;
        }

        public Criteria andRegisteredEmailGreaterThan(String value) {
            addCriterion("registered_email >", value, "registeredEmail");
            return (Criteria) this;
        }

        public Criteria andRegisteredEmailGreaterThanOrEqualTo(String value) {
            addCriterion("registered_email >=", value, "registeredEmail");
            return (Criteria) this;
        }

        public Criteria andRegisteredEmailLessThan(String value) {
            addCriterion("registered_email <", value, "registeredEmail");
            return (Criteria) this;
        }

        public Criteria andRegisteredEmailLessThanOrEqualTo(String value) {
            addCriterion("registered_email <=", value, "registeredEmail");
            return (Criteria) this;
        }

        public Criteria andRegisteredEmailLike(String value) {
            addCriterion("registered_email like", value, "registeredEmail");
            return (Criteria) this;
        }

        public Criteria andRegisteredEmailNotLike(String value) {
            addCriterion("registered_email not like", value, "registeredEmail");
            return (Criteria) this;
        }

        public Criteria andRegisteredEmailIn(List<String> values) {
            addCriterion("registered_email in", values, "registeredEmail");
            return (Criteria) this;
        }

        public Criteria andRegisteredEmailNotIn(List<String> values) {
            addCriterion("registered_email not in", values, "registeredEmail");
            return (Criteria) this;
        }

        public Criteria andRegisteredEmailBetween(String value1, String value2) {
            addCriterion("registered_email between", value1, value2, "registeredEmail");
            return (Criteria) this;
        }

        public Criteria andRegisteredEmailNotBetween(String value1, String value2) {
            addCriterion("registered_email not between", value1, value2, "registeredEmail");
            return (Criteria) this;
        }

        public Criteria andLinkmanNameIsNull() {
            addCriterion("linkman_name is null");
            return (Criteria) this;
        }

        public Criteria andLinkmanNameIsNotNull() {
            addCriterion("linkman_name is not null");
            return (Criteria) this;
        }

        public Criteria andLinkmanNameEqualTo(String value) {
            addCriterion("linkman_name =", value, "linkmanName");
            return (Criteria) this;
        }

        public Criteria andLinkmanNameNotEqualTo(String value) {
            addCriterion("linkman_name <>", value, "linkmanName");
            return (Criteria) this;
        }

        public Criteria andLinkmanNameGreaterThan(String value) {
            addCriterion("linkman_name >", value, "linkmanName");
            return (Criteria) this;
        }

        public Criteria andLinkmanNameGreaterThanOrEqualTo(String value) {
            addCriterion("linkman_name >=", value, "linkmanName");
            return (Criteria) this;
        }

        public Criteria andLinkmanNameLessThan(String value) {
            addCriterion("linkman_name <", value, "linkmanName");
            return (Criteria) this;
        }

        public Criteria andLinkmanNameLessThanOrEqualTo(String value) {
            addCriterion("linkman_name <=", value, "linkmanName");
            return (Criteria) this;
        }

        public Criteria andLinkmanNameLike(String value) {
            addCriterion("linkman_name like", value, "linkmanName");
            return (Criteria) this;
        }

        public Criteria andLinkmanNameNotLike(String value) {
            addCriterion("linkman_name not like", value, "linkmanName");
            return (Criteria) this;
        }

        public Criteria andLinkmanNameIn(List<String> values) {
            addCriterion("linkman_name in", values, "linkmanName");
            return (Criteria) this;
        }

        public Criteria andLinkmanNameNotIn(List<String> values) {
            addCriterion("linkman_name not in", values, "linkmanName");
            return (Criteria) this;
        }

        public Criteria andLinkmanNameBetween(String value1, String value2) {
            addCriterion("linkman_name between", value1, value2, "linkmanName");
            return (Criteria) this;
        }

        public Criteria andLinkmanNameNotBetween(String value1, String value2) {
            addCriterion("linkman_name not between", value1, value2, "linkmanName");
            return (Criteria) this;
        }

        public Criteria andLinkmanPhoneIsNull() {
            addCriterion("linkman_phone is null");
            return (Criteria) this;
        }

        public Criteria andLinkmanPhoneIsNotNull() {
            addCriterion("linkman_phone is not null");
            return (Criteria) this;
        }

        public Criteria andLinkmanPhoneEqualTo(String value) {
            addCriterion("linkman_phone =", value, "linkmanPhone");
            return (Criteria) this;
        }

        public Criteria andLinkmanPhoneNotEqualTo(String value) {
            addCriterion("linkman_phone <>", value, "linkmanPhone");
            return (Criteria) this;
        }

        public Criteria andLinkmanPhoneGreaterThan(String value) {
            addCriterion("linkman_phone >", value, "linkmanPhone");
            return (Criteria) this;
        }

        public Criteria andLinkmanPhoneGreaterThanOrEqualTo(String value) {
            addCriterion("linkman_phone >=", value, "linkmanPhone");
            return (Criteria) this;
        }

        public Criteria andLinkmanPhoneLessThan(String value) {
            addCriterion("linkman_phone <", value, "linkmanPhone");
            return (Criteria) this;
        }

        public Criteria andLinkmanPhoneLessThanOrEqualTo(String value) {
            addCriterion("linkman_phone <=", value, "linkmanPhone");
            return (Criteria) this;
        }

        public Criteria andLinkmanPhoneLike(String value) {
            addCriterion("linkman_phone like", value, "linkmanPhone");
            return (Criteria) this;
        }

        public Criteria andLinkmanPhoneNotLike(String value) {
            addCriterion("linkman_phone not like", value, "linkmanPhone");
            return (Criteria) this;
        }

        public Criteria andLinkmanPhoneIn(List<String> values) {
            addCriterion("linkman_phone in", values, "linkmanPhone");
            return (Criteria) this;
        }

        public Criteria andLinkmanPhoneNotIn(List<String> values) {
            addCriterion("linkman_phone not in", values, "linkmanPhone");
            return (Criteria) this;
        }

        public Criteria andLinkmanPhoneBetween(String value1, String value2) {
            addCriterion("linkman_phone between", value1, value2, "linkmanPhone");
            return (Criteria) this;
        }

        public Criteria andLinkmanPhoneNotBetween(String value1, String value2) {
            addCriterion("linkman_phone not between", value1, value2, "linkmanPhone");
            return (Criteria) this;
        }

        public Criteria andTradeTypeIsNull() {
            addCriterion("trade_type is null");
            return (Criteria) this;
        }

        public Criteria andTradeTypeIsNotNull() {
            addCriterion("trade_type is not null");
            return (Criteria) this;
        }

        public Criteria andTradeTypeEqualTo(String value) {
            addCriterion("trade_type =", value, "tradeType");
            return (Criteria) this;
        }

        public Criteria andTradeTypeNotEqualTo(String value) {
            addCriterion("trade_type <>", value, "tradeType");
            return (Criteria) this;
        }

        public Criteria andTradeTypeGreaterThan(String value) {
            addCriterion("trade_type >", value, "tradeType");
            return (Criteria) this;
        }

        public Criteria andTradeTypeGreaterThanOrEqualTo(String value) {
            addCriterion("trade_type >=", value, "tradeType");
            return (Criteria) this;
        }

        public Criteria andTradeTypeLessThan(String value) {
            addCriterion("trade_type <", value, "tradeType");
            return (Criteria) this;
        }

        public Criteria andTradeTypeLessThanOrEqualTo(String value) {
            addCriterion("trade_type <=", value, "tradeType");
            return (Criteria) this;
        }

        public Criteria andTradeTypeLike(String value) {
            addCriterion("trade_type like", value, "tradeType");
            return (Criteria) this;
        }

        public Criteria andTradeTypeNotLike(String value) {
            addCriterion("trade_type not like", value, "tradeType");
            return (Criteria) this;
        }

        public Criteria andTradeTypeIn(List<String> values) {
            addCriterion("trade_type in", values, "tradeType");
            return (Criteria) this;
        }

        public Criteria andTradeTypeNotIn(List<String> values) {
            addCriterion("trade_type not in", values, "tradeType");
            return (Criteria) this;
        }

        public Criteria andTradeTypeBetween(String value1, String value2) {
            addCriterion("trade_type between", value1, value2, "tradeType");
            return (Criteria) this;
        }

        public Criteria andTradeTypeNotBetween(String value1, String value2) {
            addCriterion("trade_type not between", value1, value2, "tradeType");
            return (Criteria) this;
        }

        public Criteria andProductTypeIsNull() {
            addCriterion("product_type is null");
            return (Criteria) this;
        }

        public Criteria andProductTypeIsNotNull() {
            addCriterion("product_type is not null");
            return (Criteria) this;
        }

        public Criteria andProductTypeEqualTo(String value) {
            addCriterion("product_type =", value, "productType");
            return (Criteria) this;
        }

        public Criteria andProductTypeNotEqualTo(String value) {
            addCriterion("product_type <>", value, "productType");
            return (Criteria) this;
        }

        public Criteria andProductTypeGreaterThan(String value) {
            addCriterion("product_type >", value, "productType");
            return (Criteria) this;
        }

        public Criteria andProductTypeGreaterThanOrEqualTo(String value) {
            addCriterion("product_type >=", value, "productType");
            return (Criteria) this;
        }

        public Criteria andProductTypeLessThan(String value) {
            addCriterion("product_type <", value, "productType");
            return (Criteria) this;
        }

        public Criteria andProductTypeLessThanOrEqualTo(String value) {
            addCriterion("product_type <=", value, "productType");
            return (Criteria) this;
        }

        public Criteria andProductTypeLike(String value) {
            addCriterion("product_type like", value, "productType");
            return (Criteria) this;
        }

        public Criteria andProductTypeNotLike(String value) {
            addCriterion("product_type not like", value, "productType");
            return (Criteria) this;
        }

        public Criteria andProductTypeIn(List<String> values) {
            addCriterion("product_type in", values, "productType");
            return (Criteria) this;
        }

        public Criteria andProductTypeNotIn(List<String> values) {
            addCriterion("product_type not in", values, "productType");
            return (Criteria) this;
        }

        public Criteria andProductTypeBetween(String value1, String value2) {
            addCriterion("product_type between", value1, value2, "productType");
            return (Criteria) this;
        }

        public Criteria andProductTypeNotBetween(String value1, String value2) {
            addCriterion("product_type not between", value1, value2, "productType");
            return (Criteria) this;
        }

        public Criteria andCompanyDescIsNull() {
            addCriterion("company_desc is null");
            return (Criteria) this;
        }

        public Criteria andCompanyDescIsNotNull() {
            addCriterion("company_desc is not null");
            return (Criteria) this;
        }

        public Criteria andCompanyDescEqualTo(String value) {
            addCriterion("company_desc =", value, "companyDesc");
            return (Criteria) this;
        }

        public Criteria andCompanyDescNotEqualTo(String value) {
            addCriterion("company_desc <>", value, "companyDesc");
            return (Criteria) this;
        }

        public Criteria andCompanyDescGreaterThan(String value) {
            addCriterion("company_desc >", value, "companyDesc");
            return (Criteria) this;
        }

        public Criteria andCompanyDescGreaterThanOrEqualTo(String value) {
            addCriterion("company_desc >=", value, "companyDesc");
            return (Criteria) this;
        }

        public Criteria andCompanyDescLessThan(String value) {
            addCriterion("company_desc <", value, "companyDesc");
            return (Criteria) this;
        }

        public Criteria andCompanyDescLessThanOrEqualTo(String value) {
            addCriterion("company_desc <=", value, "companyDesc");
            return (Criteria) this;
        }

        public Criteria andCompanyDescLike(String value) {
            addCriterion("company_desc like", value, "companyDesc");
            return (Criteria) this;
        }

        public Criteria andCompanyDescNotLike(String value) {
            addCriterion("company_desc not like", value, "companyDesc");
            return (Criteria) this;
        }

        public Criteria andCompanyDescIn(List<String> values) {
            addCriterion("company_desc in", values, "companyDesc");
            return (Criteria) this;
        }

        public Criteria andCompanyDescNotIn(List<String> values) {
            addCriterion("company_desc not in", values, "companyDesc");
            return (Criteria) this;
        }

        public Criteria andCompanyDescBetween(String value1, String value2) {
            addCriterion("company_desc between", value1, value2, "companyDesc");
            return (Criteria) this;
        }

        public Criteria andCompanyDescNotBetween(String value1, String value2) {
            addCriterion("company_desc not between", value1, value2, "companyDesc");
            return (Criteria) this;
        }

        public Criteria andCompanyLogoIsNull() {
            addCriterion("company_logo is null");
            return (Criteria) this;
        }

        public Criteria andCompanyLogoIsNotNull() {
            addCriterion("company_logo is not null");
            return (Criteria) this;
        }

        public Criteria andCompanyLogoEqualTo(String value) {
            addCriterion("company_logo =", value, "companyLogo");
            return (Criteria) this;
        }

        public Criteria andCompanyLogoNotEqualTo(String value) {
            addCriterion("company_logo <>", value, "companyLogo");
            return (Criteria) this;
        }

        public Criteria andCompanyLogoGreaterThan(String value) {
            addCriterion("company_logo >", value, "companyLogo");
            return (Criteria) this;
        }

        public Criteria andCompanyLogoGreaterThanOrEqualTo(String value) {
            addCriterion("company_logo >=", value, "companyLogo");
            return (Criteria) this;
        }

        public Criteria andCompanyLogoLessThan(String value) {
            addCriterion("company_logo <", value, "companyLogo");
            return (Criteria) this;
        }

        public Criteria andCompanyLogoLessThanOrEqualTo(String value) {
            addCriterion("company_logo <=", value, "companyLogo");
            return (Criteria) this;
        }

        public Criteria andCompanyLogoLike(String value) {
            addCriterion("company_logo like", value, "companyLogo");
            return (Criteria) this;
        }

        public Criteria andCompanyLogoNotLike(String value) {
            addCriterion("company_logo not like", value, "companyLogo");
            return (Criteria) this;
        }

        public Criteria andCompanyLogoIn(List<String> values) {
            addCriterion("company_logo in", values, "companyLogo");
            return (Criteria) this;
        }

        public Criteria andCompanyLogoNotIn(List<String> values) {
            addCriterion("company_logo not in", values, "companyLogo");
            return (Criteria) this;
        }

        public Criteria andCompanyLogoBetween(String value1, String value2) {
            addCriterion("company_logo between", value1, value2, "companyLogo");
            return (Criteria) this;
        }

        public Criteria andCompanyLogoNotBetween(String value1, String value2) {
            addCriterion("company_logo not between", value1, value2, "companyLogo");
            return (Criteria) this;
        }

        public Criteria andContractUrlIsNull() {
            addCriterion("contract_url is null");
            return (Criteria) this;
        }

        public Criteria andContractUrlIsNotNull() {
            addCriterion("contract_url is not null");
            return (Criteria) this;
        }

        public Criteria andContractUrlEqualTo(String value) {
            addCriterion("contract_url =", value, "contractUrl");
            return (Criteria) this;
        }

        public Criteria andContractUrlNotEqualTo(String value) {
            addCriterion("contract_url <>", value, "contractUrl");
            return (Criteria) this;
        }

        public Criteria andContractUrlGreaterThan(String value) {
            addCriterion("contract_url >", value, "contractUrl");
            return (Criteria) this;
        }

        public Criteria andContractUrlGreaterThanOrEqualTo(String value) {
            addCriterion("contract_url >=", value, "contractUrl");
            return (Criteria) this;
        }

        public Criteria andContractUrlLessThan(String value) {
            addCriterion("contract_url <", value, "contractUrl");
            return (Criteria) this;
        }

        public Criteria andContractUrlLessThanOrEqualTo(String value) {
            addCriterion("contract_url <=", value, "contractUrl");
            return (Criteria) this;
        }

        public Criteria andContractUrlLike(String value) {
            addCriterion("contract_url like", value, "contractUrl");
            return (Criteria) this;
        }

        public Criteria andContractUrlNotLike(String value) {
            addCriterion("contract_url not like", value, "contractUrl");
            return (Criteria) this;
        }

        public Criteria andContractUrlIn(List<String> values) {
            addCriterion("contract_url in", values, "contractUrl");
            return (Criteria) this;
        }

        public Criteria andContractUrlNotIn(List<String> values) {
            addCriterion("contract_url not in", values, "contractUrl");
            return (Criteria) this;
        }

        public Criteria andContractUrlBetween(String value1, String value2) {
            addCriterion("contract_url between", value1, value2, "contractUrl");
            return (Criteria) this;
        }

        public Criteria andContractUrlNotBetween(String value1, String value2) {
            addCriterion("contract_url not between", value1, value2, "contractUrl");
            return (Criteria) this;
        }

        public Criteria andLicenseUrlIsNull() {
            addCriterion("license_url is null");
            return (Criteria) this;
        }

        public Criteria andLicenseUrlIsNotNull() {
            addCriterion("license_url is not null");
            return (Criteria) this;
        }

        public Criteria andLicenseUrlEqualTo(String value) {
            addCriterion("license_url =", value, "licenseUrl");
            return (Criteria) this;
        }

        public Criteria andLicenseUrlNotEqualTo(String value) {
            addCriterion("license_url <>", value, "licenseUrl");
            return (Criteria) this;
        }

        public Criteria andLicenseUrlGreaterThan(String value) {
            addCriterion("license_url >", value, "licenseUrl");
            return (Criteria) this;
        }

        public Criteria andLicenseUrlGreaterThanOrEqualTo(String value) {
            addCriterion("license_url >=", value, "licenseUrl");
            return (Criteria) this;
        }

        public Criteria andLicenseUrlLessThan(String value) {
            addCriterion("license_url <", value, "licenseUrl");
            return (Criteria) this;
        }

        public Criteria andLicenseUrlLessThanOrEqualTo(String value) {
            addCriterion("license_url <=", value, "licenseUrl");
            return (Criteria) this;
        }

        public Criteria andLicenseUrlLike(String value) {
            addCriterion("license_url like", value, "licenseUrl");
            return (Criteria) this;
        }

        public Criteria andLicenseUrlNotLike(String value) {
            addCriterion("license_url not like", value, "licenseUrl");
            return (Criteria) this;
        }

        public Criteria andLicenseUrlIn(List<String> values) {
            addCriterion("license_url in", values, "licenseUrl");
            return (Criteria) this;
        }

        public Criteria andLicenseUrlNotIn(List<String> values) {
            addCriterion("license_url not in", values, "licenseUrl");
            return (Criteria) this;
        }

        public Criteria andLicenseUrlBetween(String value1, String value2) {
            addCriterion("license_url between", value1, value2, "licenseUrl");
            return (Criteria) this;
        }

        public Criteria andLicenseUrlNotBetween(String value1, String value2) {
            addCriterion("license_url not between", value1, value2, "licenseUrl");
            return (Criteria) this;
        }

        public Criteria andBankCodeIsNull() {
            addCriterion("bank_code is null");
            return (Criteria) this;
        }

        public Criteria andBankCodeIsNotNull() {
            addCriterion("bank_code is not null");
            return (Criteria) this;
        }

        public Criteria andBankCodeEqualTo(String value) {
            addCriterion("bank_code =", value, "bankCode");
            return (Criteria) this;
        }

        public Criteria andBankCodeNotEqualTo(String value) {
            addCriterion("bank_code <>", value, "bankCode");
            return (Criteria) this;
        }

        public Criteria andBankCodeGreaterThan(String value) {
            addCriterion("bank_code >", value, "bankCode");
            return (Criteria) this;
        }

        public Criteria andBankCodeGreaterThanOrEqualTo(String value) {
            addCriterion("bank_code >=", value, "bankCode");
            return (Criteria) this;
        }

        public Criteria andBankCodeLessThan(String value) {
            addCriterion("bank_code <", value, "bankCode");
            return (Criteria) this;
        }

        public Criteria andBankCodeLessThanOrEqualTo(String value) {
            addCriterion("bank_code <=", value, "bankCode");
            return (Criteria) this;
        }

        public Criteria andBankCodeLike(String value) {
            addCriterion("bank_code like", value, "bankCode");
            return (Criteria) this;
        }

        public Criteria andBankCodeNotLike(String value) {
            addCriterion("bank_code not like", value, "bankCode");
            return (Criteria) this;
        }

        public Criteria andBankCodeIn(List<String> values) {
            addCriterion("bank_code in", values, "bankCode");
            return (Criteria) this;
        }

        public Criteria andBankCodeNotIn(List<String> values) {
            addCriterion("bank_code not in", values, "bankCode");
            return (Criteria) this;
        }

        public Criteria andBankCodeBetween(String value1, String value2) {
            addCriterion("bank_code between", value1, value2, "bankCode");
            return (Criteria) this;
        }

        public Criteria andBankCodeNotBetween(String value1, String value2) {
            addCriterion("bank_code not between", value1, value2, "bankCode");
            return (Criteria) this;
        }

        public Criteria andAccountNoIsNull() {
            addCriterion("account_no is null");
            return (Criteria) this;
        }

        public Criteria andAccountNoIsNotNull() {
            addCriterion("account_no is not null");
            return (Criteria) this;
        }

        public Criteria andAccountNoEqualTo(String value) {
            addCriterion("account_no =", value, "accountNo");
            return (Criteria) this;
        }

        public Criteria andAccountNoNotEqualTo(String value) {
            addCriterion("account_no <>", value, "accountNo");
            return (Criteria) this;
        }

        public Criteria andAccountNoGreaterThan(String value) {
            addCriterion("account_no >", value, "accountNo");
            return (Criteria) this;
        }

        public Criteria andAccountNoGreaterThanOrEqualTo(String value) {
            addCriterion("account_no >=", value, "accountNo");
            return (Criteria) this;
        }

        public Criteria andAccountNoLessThan(String value) {
            addCriterion("account_no <", value, "accountNo");
            return (Criteria) this;
        }

        public Criteria andAccountNoLessThanOrEqualTo(String value) {
            addCriterion("account_no <=", value, "accountNo");
            return (Criteria) this;
        }

        public Criteria andAccountNoLike(String value) {
            addCriterion("account_no like", value, "accountNo");
            return (Criteria) this;
        }

        public Criteria andAccountNoNotLike(String value) {
            addCriterion("account_no not like", value, "accountNo");
            return (Criteria) this;
        }

        public Criteria andAccountNoIn(List<String> values) {
            addCriterion("account_no in", values, "accountNo");
            return (Criteria) this;
        }

        public Criteria andAccountNoNotIn(List<String> values) {
            addCriterion("account_no not in", values, "accountNo");
            return (Criteria) this;
        }

        public Criteria andAccountNoBetween(String value1, String value2) {
            addCriterion("account_no between", value1, value2, "accountNo");
            return (Criteria) this;
        }

        public Criteria andAccountNoNotBetween(String value1, String value2) {
            addCriterion("account_no not between", value1, value2, "accountNo");
            return (Criteria) this;
        }

        public Criteria andBankNameIsNull() {
            addCriterion("bank_name is null");
            return (Criteria) this;
        }

        public Criteria andBankNameIsNotNull() {
            addCriterion("bank_name is not null");
            return (Criteria) this;
        }

        public Criteria andBankNameEqualTo(String value) {
            addCriterion("bank_name =", value, "bankName");
            return (Criteria) this;
        }

        public Criteria andBankNameNotEqualTo(String value) {
            addCriterion("bank_name <>", value, "bankName");
            return (Criteria) this;
        }

        public Criteria andBankNameGreaterThan(String value) {
            addCriterion("bank_name >", value, "bankName");
            return (Criteria) this;
        }

        public Criteria andBankNameGreaterThanOrEqualTo(String value) {
            addCriterion("bank_name >=", value, "bankName");
            return (Criteria) this;
        }

        public Criteria andBankNameLessThan(String value) {
            addCriterion("bank_name <", value, "bankName");
            return (Criteria) this;
        }

        public Criteria andBankNameLessThanOrEqualTo(String value) {
            addCriterion("bank_name <=", value, "bankName");
            return (Criteria) this;
        }

        public Criteria andBankNameLike(String value) {
            addCriterion("bank_name like", value, "bankName");
            return (Criteria) this;
        }

        public Criteria andBankNameNotLike(String value) {
            addCriterion("bank_name not like", value, "bankName");
            return (Criteria) this;
        }

        public Criteria andBankNameIn(List<String> values) {
            addCriterion("bank_name in", values, "bankName");
            return (Criteria) this;
        }

        public Criteria andBankNameNotIn(List<String> values) {
            addCriterion("bank_name not in", values, "bankName");
            return (Criteria) this;
        }

        public Criteria andBankNameBetween(String value1, String value2) {
            addCriterion("bank_name between", value1, value2, "bankName");
            return (Criteria) this;
        }

        public Criteria andBankNameNotBetween(String value1, String value2) {
            addCriterion("bank_name not between", value1, value2, "bankName");
            return (Criteria) this;
        }

        public Criteria andBankAddressIsNull() {
            addCriterion("bank_address is null");
            return (Criteria) this;
        }

        public Criteria andBankAddressIsNotNull() {
            addCriterion("bank_address is not null");
            return (Criteria) this;
        }

        public Criteria andBankAddressEqualTo(String value) {
            addCriterion("bank_address =", value, "bankAddress");
            return (Criteria) this;
        }

        public Criteria andBankAddressNotEqualTo(String value) {
            addCriterion("bank_address <>", value, "bankAddress");
            return (Criteria) this;
        }

        public Criteria andBankAddressGreaterThan(String value) {
            addCriterion("bank_address >", value, "bankAddress");
            return (Criteria) this;
        }

        public Criteria andBankAddressGreaterThanOrEqualTo(String value) {
            addCriterion("bank_address >=", value, "bankAddress");
            return (Criteria) this;
        }

        public Criteria andBankAddressLessThan(String value) {
            addCriterion("bank_address <", value, "bankAddress");
            return (Criteria) this;
        }

        public Criteria andBankAddressLessThanOrEqualTo(String value) {
            addCriterion("bank_address <=", value, "bankAddress");
            return (Criteria) this;
        }

        public Criteria andBankAddressLike(String value) {
            addCriterion("bank_address like", value, "bankAddress");
            return (Criteria) this;
        }

        public Criteria andBankAddressNotLike(String value) {
            addCriterion("bank_address not like", value, "bankAddress");
            return (Criteria) this;
        }

        public Criteria andBankAddressIn(List<String> values) {
            addCriterion("bank_address in", values, "bankAddress");
            return (Criteria) this;
        }

        public Criteria andBankAddressNotIn(List<String> values) {
            addCriterion("bank_address not in", values, "bankAddress");
            return (Criteria) this;
        }

        public Criteria andBankAddressBetween(String value1, String value2) {
            addCriterion("bank_address between", value1, value2, "bankAddress");
            return (Criteria) this;
        }

        public Criteria andBankAddressNotBetween(String value1, String value2) {
            addCriterion("bank_address not between", value1, value2, "bankAddress");
            return (Criteria) this;
        }

        public Criteria andBankIbanIsNull() {
            addCriterion("bank_IBAN is null");
            return (Criteria) this;
        }

        public Criteria andBankIbanIsNotNull() {
            addCriterion("bank_IBAN is not null");
            return (Criteria) this;
        }

        public Criteria andBankIbanEqualTo(String value) {
            addCriterion("bank_IBAN =", value, "bankIban");
            return (Criteria) this;
        }

        public Criteria andBankIbanNotEqualTo(String value) {
            addCriterion("bank_IBAN <>", value, "bankIban");
            return (Criteria) this;
        }

        public Criteria andBankIbanGreaterThan(String value) {
            addCriterion("bank_IBAN >", value, "bankIban");
            return (Criteria) this;
        }

        public Criteria andBankIbanGreaterThanOrEqualTo(String value) {
            addCriterion("bank_IBAN >=", value, "bankIban");
            return (Criteria) this;
        }

        public Criteria andBankIbanLessThan(String value) {
            addCriterion("bank_IBAN <", value, "bankIban");
            return (Criteria) this;
        }

        public Criteria andBankIbanLessThanOrEqualTo(String value) {
            addCriterion("bank_IBAN <=", value, "bankIban");
            return (Criteria) this;
        }

        public Criteria andBankIbanLike(String value) {
            addCriterion("bank_IBAN like", value, "bankIban");
            return (Criteria) this;
        }

        public Criteria andBankIbanNotLike(String value) {
            addCriterion("bank_IBAN not like", value, "bankIban");
            return (Criteria) this;
        }

        public Criteria andBankIbanIn(List<String> values) {
            addCriterion("bank_IBAN in", values, "bankIban");
            return (Criteria) this;
        }

        public Criteria andBankIbanNotIn(List<String> values) {
            addCriterion("bank_IBAN not in", values, "bankIban");
            return (Criteria) this;
        }

        public Criteria andBankIbanBetween(String value1, String value2) {
            addCriterion("bank_IBAN between", value1, value2, "bankIban");
            return (Criteria) this;
        }

        public Criteria andBankIbanNotBetween(String value1, String value2) {
            addCriterion("bank_IBAN not between", value1, value2, "bankIban");
            return (Criteria) this;
        }

        public Criteria andBankBicIsNull() {
            addCriterion("bank_BIC is null");
            return (Criteria) this;
        }

        public Criteria andBankBicIsNotNull() {
            addCriterion("bank_BIC is not null");
            return (Criteria) this;
        }

        public Criteria andBankBicEqualTo(String value) {
            addCriterion("bank_BIC =", value, "bankBic");
            return (Criteria) this;
        }

        public Criteria andBankBicNotEqualTo(String value) {
            addCriterion("bank_BIC <>", value, "bankBic");
            return (Criteria) this;
        }

        public Criteria andBankBicGreaterThan(String value) {
            addCriterion("bank_BIC >", value, "bankBic");
            return (Criteria) this;
        }

        public Criteria andBankBicGreaterThanOrEqualTo(String value) {
            addCriterion("bank_BIC >=", value, "bankBic");
            return (Criteria) this;
        }

        public Criteria andBankBicLessThan(String value) {
            addCriterion("bank_BIC <", value, "bankBic");
            return (Criteria) this;
        }

        public Criteria andBankBicLessThanOrEqualTo(String value) {
            addCriterion("bank_BIC <=", value, "bankBic");
            return (Criteria) this;
        }

        public Criteria andBankBicLike(String value) {
            addCriterion("bank_BIC like", value, "bankBic");
            return (Criteria) this;
        }

        public Criteria andBankBicNotLike(String value) {
            addCriterion("bank_BIC not like", value, "bankBic");
            return (Criteria) this;
        }

        public Criteria andBankBicIn(List<String> values) {
            addCriterion("bank_BIC in", values, "bankBic");
            return (Criteria) this;
        }

        public Criteria andBankBicNotIn(List<String> values) {
            addCriterion("bank_BIC not in", values, "bankBic");
            return (Criteria) this;
        }

        public Criteria andBankBicBetween(String value1, String value2) {
            addCriterion("bank_BIC between", value1, value2, "bankBic");
            return (Criteria) this;
        }

        public Criteria andBankBicNotBetween(String value1, String value2) {
            addCriterion("bank_BIC not between", value1, value2, "bankBic");
            return (Criteria) this;
        }

        public Criteria andConnectionAccountIsNull() {
            addCriterion("connection_account is null");
            return (Criteria) this;
        }

        public Criteria andConnectionAccountIsNotNull() {
            addCriterion("connection_account is not null");
            return (Criteria) this;
        }

        public Criteria andConnectionAccountEqualTo(String value) {
            addCriterion("connection_account =", value, "connectionAccount");
            return (Criteria) this;
        }

        public Criteria andConnectionAccountNotEqualTo(String value) {
            addCriterion("connection_account <>", value, "connectionAccount");
            return (Criteria) this;
        }

        public Criteria andConnectionAccountGreaterThan(String value) {
            addCriterion("connection_account >", value, "connectionAccount");
            return (Criteria) this;
        }

        public Criteria andConnectionAccountGreaterThanOrEqualTo(String value) {
            addCriterion("connection_account >=", value, "connectionAccount");
            return (Criteria) this;
        }

        public Criteria andConnectionAccountLessThan(String value) {
            addCriterion("connection_account <", value, "connectionAccount");
            return (Criteria) this;
        }

        public Criteria andConnectionAccountLessThanOrEqualTo(String value) {
            addCriterion("connection_account <=", value, "connectionAccount");
            return (Criteria) this;
        }

        public Criteria andConnectionAccountLike(String value) {
            addCriterion("connection_account like", value, "connectionAccount");
            return (Criteria) this;
        }

        public Criteria andConnectionAccountNotLike(String value) {
            addCriterion("connection_account not like", value, "connectionAccount");
            return (Criteria) this;
        }

        public Criteria andConnectionAccountIn(List<String> values) {
            addCriterion("connection_account in", values, "connectionAccount");
            return (Criteria) this;
        }

        public Criteria andConnectionAccountNotIn(List<String> values) {
            addCriterion("connection_account not in", values, "connectionAccount");
            return (Criteria) this;
        }

        public Criteria andConnectionAccountBetween(String value1, String value2) {
            addCriterion("connection_account between", value1, value2, "connectionAccount");
            return (Criteria) this;
        }

        public Criteria andConnectionAccountNotBetween(String value1, String value2) {
            addCriterion("connection_account not between", value1, value2, "connectionAccount");
            return (Criteria) this;
        }

        public Criteria andBusinessPersonIsNull() {
            addCriterion("business_person is null");
            return (Criteria) this;
        }

        public Criteria andBusinessPersonIsNotNull() {
            addCriterion("business_person is not null");
            return (Criteria) this;
        }

        public Criteria andBusinessPersonEqualTo(String value) {
            addCriterion("business_person =", value, "businessPerson");
            return (Criteria) this;
        }

        public Criteria andBusinessPersonNotEqualTo(String value) {
            addCriterion("business_person <>", value, "businessPerson");
            return (Criteria) this;
        }

        public Criteria andBusinessPersonGreaterThan(String value) {
            addCriterion("business_person >", value, "businessPerson");
            return (Criteria) this;
        }

        public Criteria andBusinessPersonGreaterThanOrEqualTo(String value) {
            addCriterion("business_person >=", value, "businessPerson");
            return (Criteria) this;
        }

        public Criteria andBusinessPersonLessThan(String value) {
            addCriterion("business_person <", value, "businessPerson");
            return (Criteria) this;
        }

        public Criteria andBusinessPersonLessThanOrEqualTo(String value) {
            addCriterion("business_person <=", value, "businessPerson");
            return (Criteria) this;
        }

        public Criteria andBusinessPersonLike(String value) {
            addCriterion("business_person like", value, "businessPerson");
            return (Criteria) this;
        }

        public Criteria andBusinessPersonNotLike(String value) {
            addCriterion("business_person not like", value, "businessPerson");
            return (Criteria) this;
        }

        public Criteria andBusinessPersonIn(List<String> values) {
            addCriterion("business_person in", values, "businessPerson");
            return (Criteria) this;
        }

        public Criteria andBusinessPersonNotIn(List<String> values) {
            addCriterion("business_person not in", values, "businessPerson");
            return (Criteria) this;
        }

        public Criteria andBusinessPersonBetween(String value1, String value2) {
            addCriterion("business_person between", value1, value2, "businessPerson");
            return (Criteria) this;
        }

        public Criteria andBusinessPersonNotBetween(String value1, String value2) {
            addCriterion("business_person not between", value1, value2, "businessPerson");
            return (Criteria) this;
        }

        public Criteria andBusinessPhoneIsNull() {
            addCriterion("business_phone is null");
            return (Criteria) this;
        }

        public Criteria andBusinessPhoneIsNotNull() {
            addCriterion("business_phone is not null");
            return (Criteria) this;
        }

        public Criteria andBusinessPhoneEqualTo(String value) {
            addCriterion("business_phone =", value, "businessPhone");
            return (Criteria) this;
        }

        public Criteria andBusinessPhoneNotEqualTo(String value) {
            addCriterion("business_phone <>", value, "businessPhone");
            return (Criteria) this;
        }

        public Criteria andBusinessPhoneGreaterThan(String value) {
            addCriterion("business_phone >", value, "businessPhone");
            return (Criteria) this;
        }

        public Criteria andBusinessPhoneGreaterThanOrEqualTo(String value) {
            addCriterion("business_phone >=", value, "businessPhone");
            return (Criteria) this;
        }

        public Criteria andBusinessPhoneLessThan(String value) {
            addCriterion("business_phone <", value, "businessPhone");
            return (Criteria) this;
        }

        public Criteria andBusinessPhoneLessThanOrEqualTo(String value) {
            addCriterion("business_phone <=", value, "businessPhone");
            return (Criteria) this;
        }

        public Criteria andBusinessPhoneLike(String value) {
            addCriterion("business_phone like", value, "businessPhone");
            return (Criteria) this;
        }

        public Criteria andBusinessPhoneNotLike(String value) {
            addCriterion("business_phone not like", value, "businessPhone");
            return (Criteria) this;
        }

        public Criteria andBusinessPhoneIn(List<String> values) {
            addCriterion("business_phone in", values, "businessPhone");
            return (Criteria) this;
        }

        public Criteria andBusinessPhoneNotIn(List<String> values) {
            addCriterion("business_phone not in", values, "businessPhone");
            return (Criteria) this;
        }

        public Criteria andBusinessPhoneBetween(String value1, String value2) {
            addCriterion("business_phone between", value1, value2, "businessPhone");
            return (Criteria) this;
        }

        public Criteria andBusinessPhoneNotBetween(String value1, String value2) {
            addCriterion("business_phone not between", value1, value2, "businessPhone");
            return (Criteria) this;
        }

        public Criteria andSupplierStatusIsNull() {
            addCriterion("supplier_status is null");
            return (Criteria) this;
        }

        public Criteria andSupplierStatusIsNotNull() {
            addCriterion("supplier_status is not null");
            return (Criteria) this;
        }

        public Criteria andSupplierStatusEqualTo(Byte value) {
            addCriterion("supplier_status =", value, "supplierStatus");
            return (Criteria) this;
        }

        public Criteria andSupplierStatusNotEqualTo(Byte value) {
            addCriterion("supplier_status <>", value, "supplierStatus");
            return (Criteria) this;
        }

        public Criteria andSupplierStatusGreaterThan(Byte value) {
            addCriterion("supplier_status >", value, "supplierStatus");
            return (Criteria) this;
        }

        public Criteria andSupplierStatusGreaterThanOrEqualTo(Byte value) {
            addCriterion("supplier_status >=", value, "supplierStatus");
            return (Criteria) this;
        }

        public Criteria andSupplierStatusLessThan(Byte value) {
            addCriterion("supplier_status <", value, "supplierStatus");
            return (Criteria) this;
        }

        public Criteria andSupplierStatusLessThanOrEqualTo(Byte value) {
            addCriterion("supplier_status <=", value, "supplierStatus");
            return (Criteria) this;
        }

        public Criteria andSupplierStatusIn(List<Byte> values) {
            addCriterion("supplier_status in", values, "supplierStatus");
            return (Criteria) this;
        }

        public Criteria andSupplierStatusNotIn(List<Byte> values) {
            addCriterion("supplier_status not in", values, "supplierStatus");
            return (Criteria) this;
        }

        public Criteria andSupplierStatusBetween(Byte value1, Byte value2) {
            addCriterion("supplier_status between", value1, value2, "supplierStatus");
            return (Criteria) this;
        }

        public Criteria andSupplierStatusNotBetween(Byte value1, Byte value2) {
            addCriterion("supplier_status not between", value1, value2, "supplierStatus");
            return (Criteria) this;
        }

        public Criteria andCreateDateIsNull() {
            addCriterion("create_date is null");
            return (Criteria) this;
        }

        public Criteria andCreateDateIsNotNull() {
            addCriterion("create_date is not null");
            return (Criteria) this;
        }

        public Criteria andCreateDateEqualTo(Date value) {
            addCriterion("create_date =", value, "createDate");
            return (Criteria) this;
        }

        public Criteria andCreateDateNotEqualTo(Date value) {
            addCriterion("create_date <>", value, "createDate");
            return (Criteria) this;
        }

        public Criteria andCreateDateGreaterThan(Date value) {
            addCriterion("create_date >", value, "createDate");
            return (Criteria) this;
        }

        public Criteria andCreateDateGreaterThanOrEqualTo(Date value) {
            addCriterion("create_date >=", value, "createDate");
            return (Criteria) this;
        }

        public Criteria andCreateDateLessThan(Date value) {
            addCriterion("create_date <", value, "createDate");
            return (Criteria) this;
        }

        public Criteria andCreateDateLessThanOrEqualTo(Date value) {
            addCriterion("create_date <=", value, "createDate");
            return (Criteria) this;
        }

        public Criteria andCreateDateIn(List<Date> values) {
            addCriterion("create_date in", values, "createDate");
            return (Criteria) this;
        }

        public Criteria andCreateDateNotIn(List<Date> values) {
            addCriterion("create_date not in", values, "createDate");
            return (Criteria) this;
        }

        public Criteria andCreateDateBetween(Date value1, Date value2) {
            addCriterion("create_date between", value1, value2, "createDate");
            return (Criteria) this;
        }

        public Criteria andCreateDateNotBetween(Date value1, Date value2) {
            addCriterion("create_date not between", value1, value2, "createDate");
            return (Criteria) this;
        }

        public Criteria andFlagIsNull() {
            addCriterion("flag is null");
            return (Criteria) this;
        }

        public Criteria andFlagIsNotNull() {
            addCriterion("flag is not null");
            return (Criteria) this;
        }

        public Criteria andFlagEqualTo(Byte value) {
            addCriterion("flag =", value, "flag");
            return (Criteria) this;
        }

        public Criteria andFlagNotEqualTo(Byte value) {
            addCriterion("flag <>", value, "flag");
            return (Criteria) this;
        }

        public Criteria andFlagGreaterThan(Byte value) {
            addCriterion("flag >", value, "flag");
            return (Criteria) this;
        }

        public Criteria andFlagGreaterThanOrEqualTo(Byte value) {
            addCriterion("flag >=", value, "flag");
            return (Criteria) this;
        }

        public Criteria andFlagLessThan(Byte value) {
            addCriterion("flag <", value, "flag");
            return (Criteria) this;
        }

        public Criteria andFlagLessThanOrEqualTo(Byte value) {
            addCriterion("flag <=", value, "flag");
            return (Criteria) this;
        }

        public Criteria andFlagIn(List<Byte> values) {
            addCriterion("flag in", values, "flag");
            return (Criteria) this;
        }

        public Criteria andFlagNotIn(List<Byte> values) {
            addCriterion("flag not in", values, "flag");
            return (Criteria) this;
        }

        public Criteria andFlagBetween(Byte value1, Byte value2) {
            addCriterion("flag between", value1, value2, "flag");
            return (Criteria) this;
        }

        public Criteria andFlagNotBetween(Byte value1, Byte value2) {
            addCriterion("flag not between", value1, value2, "flag");
            return (Criteria) this;
        }

        public Criteria andDiscountTypeIsNull() {
            addCriterion("discount_type is null");
            return (Criteria) this;
        }

        public Criteria andDiscountTypeIsNotNull() {
            addCriterion("discount_type is not null");
            return (Criteria) this;
        }

        public Criteria andDiscountTypeEqualTo(String value) {
            addCriterion("discount_type =", value, "discountType");
            return (Criteria) this;
        }

        public Criteria andDiscountTypeNotEqualTo(String value) {
            addCriterion("discount_type <>", value, "discountType");
            return (Criteria) this;
        }

        public Criteria andDiscountTypeGreaterThan(String value) {
            addCriterion("discount_type >", value, "discountType");
            return (Criteria) this;
        }

        public Criteria andDiscountTypeGreaterThanOrEqualTo(String value) {
            addCriterion("discount_type >=", value, "discountType");
            return (Criteria) this;
        }

        public Criteria andDiscountTypeLessThan(String value) {
            addCriterion("discount_type <", value, "discountType");
            return (Criteria) this;
        }

        public Criteria andDiscountTypeLessThanOrEqualTo(String value) {
            addCriterion("discount_type <=", value, "discountType");
            return (Criteria) this;
        }

        public Criteria andDiscountTypeLike(String value) {
            addCriterion("discount_type like", value, "discountType");
            return (Criteria) this;
        }

        public Criteria andDiscountTypeNotLike(String value) {
            addCriterion("discount_type not like", value, "discountType");
            return (Criteria) this;
        }

        public Criteria andDiscountTypeIn(List<String> values) {
            addCriterion("discount_type in", values, "discountType");
            return (Criteria) this;
        }

        public Criteria andDiscountTypeNotIn(List<String> values) {
            addCriterion("discount_type not in", values, "discountType");
            return (Criteria) this;
        }

        public Criteria andDiscountTypeBetween(String value1, String value2) {
            addCriterion("discount_type between", value1, value2, "discountType");
            return (Criteria) this;
        }

        public Criteria andDiscountTypeNotBetween(String value1, String value2) {
            addCriterion("discount_type not between", value1, value2, "discountType");
            return (Criteria) this;
        }

        public Criteria andDiscountRateIsNull() {
            addCriterion("discount_rate is null");
            return (Criteria) this;
        }

        public Criteria andDiscountRateIsNotNull() {
            addCriterion("discount_rate is not null");
            return (Criteria) this;
        }

        public Criteria andDiscountRateEqualTo(Float value) {
            addCriterion("discount_rate =", value, "discountRate");
            return (Criteria) this;
        }

        public Criteria andDiscountRateNotEqualTo(Float value) {
            addCriterion("discount_rate <>", value, "discountRate");
            return (Criteria) this;
        }

        public Criteria andDiscountRateGreaterThan(Float value) {
            addCriterion("discount_rate >", value, "discountRate");
            return (Criteria) this;
        }

        public Criteria andDiscountRateGreaterThanOrEqualTo(Float value) {
            addCriterion("discount_rate >=", value, "discountRate");
            return (Criteria) this;
        }

        public Criteria andDiscountRateLessThan(Float value) {
            addCriterion("discount_rate <", value, "discountRate");
            return (Criteria) this;
        }

        public Criteria andDiscountRateLessThanOrEqualTo(Float value) {
            addCriterion("discount_rate <=", value, "discountRate");
            return (Criteria) this;
        }

        public Criteria andDiscountRateIn(List<Float> values) {
            addCriterion("discount_rate in", values, "discountRate");
            return (Criteria) this;
        }

        public Criteria andDiscountRateNotIn(List<Float> values) {
            addCriterion("discount_rate not in", values, "discountRate");
            return (Criteria) this;
        }

        public Criteria andDiscountRateBetween(Float value1, Float value2) {
            addCriterion("discount_rate between", value1, value2, "discountRate");
            return (Criteria) this;
        }

        public Criteria andDiscountRateNotBetween(Float value1, Float value2) {
            addCriterion("discount_rate not between", value1, value2, "discountRate");
            return (Criteria) this;
        }

        public Criteria andProceduresIsNull() {
            addCriterion("procedures is null");
            return (Criteria) this;
        }

        public Criteria andProceduresIsNotNull() {
            addCriterion("procedures is not null");
            return (Criteria) this;
        }

        public Criteria andProceduresEqualTo(Float value) {
            addCriterion("procedures =", value, "procedures");
            return (Criteria) this;
        }

        public Criteria andProceduresNotEqualTo(Float value) {
            addCriterion("procedures <>", value, "procedures");
            return (Criteria) this;
        }

        public Criteria andProceduresGreaterThan(Float value) {
            addCriterion("procedures >", value, "procedures");
            return (Criteria) this;
        }

        public Criteria andProceduresGreaterThanOrEqualTo(Float value) {
            addCriterion("procedures >=", value, "procedures");
            return (Criteria) this;
        }

        public Criteria andProceduresLessThan(Float value) {
            addCriterion("procedures <", value, "procedures");
            return (Criteria) this;
        }

        public Criteria andProceduresLessThanOrEqualTo(Float value) {
            addCriterion("procedures <=", value, "procedures");
            return (Criteria) this;
        }

        public Criteria andProceduresIn(List<Float> values) {
            addCriterion("procedures in", values, "procedures");
            return (Criteria) this;
        }

        public Criteria andProceduresNotIn(List<Float> values) {
            addCriterion("procedures not in", values, "procedures");
            return (Criteria) this;
        }

        public Criteria andProceduresBetween(Float value1, Float value2) {
            addCriterion("procedures between", value1, value2, "procedures");
            return (Criteria) this;
        }

        public Criteria andProceduresNotBetween(Float value1, Float value2) {
            addCriterion("procedures not between", value1, value2, "procedures");
            return (Criteria) this;
        }

        public Criteria andTaxIsNull() {
            addCriterion("tax is null");
            return (Criteria) this;
        }

        public Criteria andTaxIsNotNull() {
            addCriterion("tax is not null");
            return (Criteria) this;
        }

        public Criteria andTaxEqualTo(Float value) {
            addCriterion("tax =", value, "tax");
            return (Criteria) this;
        }

        public Criteria andTaxNotEqualTo(Float value) {
            addCriterion("tax <>", value, "tax");
            return (Criteria) this;
        }

        public Criteria andTaxGreaterThan(Float value) {
            addCriterion("tax >", value, "tax");
            return (Criteria) this;
        }

        public Criteria andTaxGreaterThanOrEqualTo(Float value) {
            addCriterion("tax >=", value, "tax");
            return (Criteria) this;
        }

        public Criteria andTaxLessThan(Float value) {
            addCriterion("tax <", value, "tax");
            return (Criteria) this;
        }

        public Criteria andTaxLessThanOrEqualTo(Float value) {
            addCriterion("tax <=", value, "tax");
            return (Criteria) this;
        }

        public Criteria andTaxIn(List<Float> values) {
            addCriterion("tax in", values, "tax");
            return (Criteria) this;
        }

        public Criteria andTaxNotIn(List<Float> values) {
            addCriterion("tax not in", values, "tax");
            return (Criteria) this;
        }

        public Criteria andTaxBetween(Float value1, Float value2) {
            addCriterion("tax between", value1, value2, "tax");
            return (Criteria) this;
        }

        public Criteria andTaxNotBetween(Float value1, Float value2) {
            addCriterion("tax not between", value1, value2, "tax");
            return (Criteria) this;
        }

        public Criteria andUidIsNull() {
            addCriterion("uid is null");
            return (Criteria) this;
        }

        public Criteria andUidIsNotNull() {
            addCriterion("uid is not null");
            return (Criteria) this;
        }

        public Criteria andUidEqualTo(Long value) {
            addCriterion("uid =", value, "uid");
            return (Criteria) this;
        }

        public Criteria andUidNotEqualTo(Long value) {
            addCriterion("uid <>", value, "uid");
            return (Criteria) this;
        }

        public Criteria andUidGreaterThan(Long value) {
            addCriterion("uid >", value, "uid");
            return (Criteria) this;
        }

        public Criteria andUidGreaterThanOrEqualTo(Long value) {
            addCriterion("uid >=", value, "uid");
            return (Criteria) this;
        }

        public Criteria andUidLessThan(Long value) {
            addCriterion("uid <", value, "uid");
            return (Criteria) this;
        }

        public Criteria andUidLessThanOrEqualTo(Long value) {
            addCriterion("uid <=", value, "uid");
            return (Criteria) this;
        }

        public Criteria andUidIn(List<Long> values) {
            addCriterion("uid in", values, "uid");
            return (Criteria) this;
        }

        public Criteria andUidNotIn(List<Long> values) {
            addCriterion("uid not in", values, "uid");
            return (Criteria) this;
        }

        public Criteria andUidBetween(Long value1, Long value2) {
            addCriterion("uid between", value1, value2, "uid");
            return (Criteria) this;
        }

        public Criteria andUidNotBetween(Long value1, Long value2) {
            addCriterion("uid not between", value1, value2, "uid");
            return (Criteria) this;
        }

        public Criteria andAccountIdIsNull() {
            addCriterion("account_id is null");
            return (Criteria) this;
        }

        public Criteria andAccountIdIsNotNull() {
            addCriterion("account_id is not null");
            return (Criteria) this;
        }

        public Criteria andAccountIdEqualTo(Long value) {
            addCriterion("account_id =", value, "accountId");
            return (Criteria) this;
        }

        public Criteria andAccountIdNotEqualTo(Long value) {
            addCriterion("account_id <>", value, "accountId");
            return (Criteria) this;
        }

        public Criteria andAccountIdGreaterThan(Long value) {
            addCriterion("account_id >", value, "accountId");
            return (Criteria) this;
        }

        public Criteria andAccountIdGreaterThanOrEqualTo(Long value) {
            addCriterion("account_id >=", value, "accountId");
            return (Criteria) this;
        }

        public Criteria andAccountIdLessThan(Long value) {
            addCriterion("account_id <", value, "accountId");
            return (Criteria) this;
        }

        public Criteria andAccountIdLessThanOrEqualTo(Long value) {
            addCriterion("account_id <=", value, "accountId");
            return (Criteria) this;
        }

        public Criteria andAccountIdIn(List<Long> values) {
            addCriterion("account_id in", values, "accountId");
            return (Criteria) this;
        }

        public Criteria andAccountIdNotIn(List<Long> values) {
            addCriterion("account_id not in", values, "accountId");
            return (Criteria) this;
        }

        public Criteria andAccountIdBetween(Long value1, Long value2) {
            addCriterion("account_id between", value1, value2, "accountId");
            return (Criteria) this;
        }

        public Criteria andAccountIdNotBetween(Long value1, Long value2) {
            addCriterion("account_id not between", value1, value2, "accountId");
            return (Criteria) this;
        }

        public Criteria andShopIdIsNull() {
            addCriterion("shop_id is null");
            return (Criteria) this;
        }

        public Criteria andShopIdIsNotNull() {
            addCriterion("shop_id is not null");
            return (Criteria) this;
        }

        public Criteria andShopIdEqualTo(Long value) {
            addCriterion("shop_id =", value, "shopId");
            return (Criteria) this;
        }

        public Criteria andShopIdNotEqualTo(Long value) {
            addCriterion("shop_id <>", value, "shopId");
            return (Criteria) this;
        }

        public Criteria andShopIdGreaterThan(Long value) {
            addCriterion("shop_id >", value, "shopId");
            return (Criteria) this;
        }

        public Criteria andShopIdGreaterThanOrEqualTo(Long value) {
            addCriterion("shop_id >=", value, "shopId");
            return (Criteria) this;
        }

        public Criteria andShopIdLessThan(Long value) {
            addCriterion("shop_id <", value, "shopId");
            return (Criteria) this;
        }

        public Criteria andShopIdLessThanOrEqualTo(Long value) {
            addCriterion("shop_id <=", value, "shopId");
            return (Criteria) this;
        }

        public Criteria andShopIdIn(List<Long> values) {
            addCriterion("shop_id in", values, "shopId");
            return (Criteria) this;
        }

        public Criteria andShopIdNotIn(List<Long> values) {
            addCriterion("shop_id not in", values, "shopId");
            return (Criteria) this;
        }

        public Criteria andShopIdBetween(Long value1, Long value2) {
            addCriterion("shop_id between", value1, value2, "shopId");
            return (Criteria) this;
        }

        public Criteria andShopIdNotBetween(Long value1, Long value2) {
            addCriterion("shop_id not between", value1, value2, "shopId");
            return (Criteria) this;
        }

        public Criteria andMerchantIdIsNull() {
            addCriterion("merchant_id is null");
            return (Criteria) this;
        }

        public Criteria andMerchantIdIsNotNull() {
            addCriterion("merchant_id is not null");
            return (Criteria) this;
        }

        public Criteria andMerchantIdEqualTo(Long value) {
            addCriterion("merchant_id =", value, "merchantId");
            return (Criteria) this;
        }

        public Criteria andMerchantIdNotEqualTo(Long value) {
            addCriterion("merchant_id <>", value, "merchantId");
            return (Criteria) this;
        }

        public Criteria andMerchantIdGreaterThan(Long value) {
            addCriterion("merchant_id >", value, "merchantId");
            return (Criteria) this;
        }

        public Criteria andMerchantIdGreaterThanOrEqualTo(Long value) {
            addCriterion("merchant_id >=", value, "merchantId");
            return (Criteria) this;
        }

        public Criteria andMerchantIdLessThan(Long value) {
            addCriterion("merchant_id <", value, "merchantId");
            return (Criteria) this;
        }

        public Criteria andMerchantIdLessThanOrEqualTo(Long value) {
            addCriterion("merchant_id <=", value, "merchantId");
            return (Criteria) this;
        }

        public Criteria andMerchantIdIn(List<Long> values) {
            addCriterion("merchant_id in", values, "merchantId");
            return (Criteria) this;
        }

        public Criteria andMerchantIdNotIn(List<Long> values) {
            addCriterion("merchant_id not in", values, "merchantId");
            return (Criteria) this;
        }

        public Criteria andMerchantIdBetween(Long value1, Long value2) {
            addCriterion("merchant_id between", value1, value2, "merchantId");
            return (Criteria) this;
        }

        public Criteria andMerchantIdNotBetween(Long value1, Long value2) {
            addCriterion("merchant_id not between", value1, value2, "merchantId");
            return (Criteria) this;
        }

        public Criteria andCustomerNameIsNull() {
            addCriterion("customer_name is null");
            return (Criteria) this;
        }

        public Criteria andCustomerNameIsNotNull() {
            addCriterion("customer_name is not null");
            return (Criteria) this;
        }

        public Criteria andCustomerNameEqualTo(String value) {
            addCriterion("customer_name =", value, "customerName");
            return (Criteria) this;
        }

        public Criteria andCustomerNameNotEqualTo(String value) {
            addCriterion("customer_name <>", value, "customerName");
            return (Criteria) this;
        }

        public Criteria andCustomerNameGreaterThan(String value) {
            addCriterion("customer_name >", value, "customerName");
            return (Criteria) this;
        }

        public Criteria andCustomerNameGreaterThanOrEqualTo(String value) {
            addCriterion("customer_name >=", value, "customerName");
            return (Criteria) this;
        }

        public Criteria andCustomerNameLessThan(String value) {
            addCriterion("customer_name <", value, "customerName");
            return (Criteria) this;
        }

        public Criteria andCustomerNameLessThanOrEqualTo(String value) {
            addCriterion("customer_name <=", value, "customerName");
            return (Criteria) this;
        }

        public Criteria andCustomerNameLike(String value) {
            addCriterion("customer_name like", value, "customerName");
            return (Criteria) this;
        }

        public Criteria andCustomerNameNotLike(String value) {
            addCriterion("customer_name not like", value, "customerName");
            return (Criteria) this;
        }

        public Criteria andCustomerNameIn(List<String> values) {
            addCriterion("customer_name in", values, "customerName");
            return (Criteria) this;
        }

        public Criteria andCustomerNameNotIn(List<String> values) {
            addCriterion("customer_name not in", values, "customerName");
            return (Criteria) this;
        }

        public Criteria andCustomerNameBetween(String value1, String value2) {
            addCriterion("customer_name between", value1, value2, "customerName");
            return (Criteria) this;
        }

        public Criteria andCustomerNameNotBetween(String value1, String value2) {
            addCriterion("customer_name not between", value1, value2, "customerName");
            return (Criteria) this;
        }

        public Criteria andPayeeNameIsNull() {
            addCriterion("payee_name is null");
            return (Criteria) this;
        }

        public Criteria andPayeeNameIsNotNull() {
            addCriterion("payee_name is not null");
            return (Criteria) this;
        }

        public Criteria andPayeeNameEqualTo(String value) {
            addCriterion("payee_name =", value, "payeeName");
            return (Criteria) this;
        }

        public Criteria andPayeeNameNotEqualTo(String value) {
            addCriterion("payee_name <>", value, "payeeName");
            return (Criteria) this;
        }

        public Criteria andPayeeNameGreaterThan(String value) {
            addCriterion("payee_name >", value, "payeeName");
            return (Criteria) this;
        }

        public Criteria andPayeeNameGreaterThanOrEqualTo(String value) {
            addCriterion("payee_name >=", value, "payeeName");
            return (Criteria) this;
        }

        public Criteria andPayeeNameLessThan(String value) {
            addCriterion("payee_name <", value, "payeeName");
            return (Criteria) this;
        }

        public Criteria andPayeeNameLessThanOrEqualTo(String value) {
            addCriterion("payee_name <=", value, "payeeName");
            return (Criteria) this;
        }

        public Criteria andPayeeNameLike(String value) {
            addCriterion("payee_name like", value, "payeeName");
            return (Criteria) this;
        }

        public Criteria andPayeeNameNotLike(String value) {
            addCriterion("payee_name not like", value, "payeeName");
            return (Criteria) this;
        }

        public Criteria andPayeeNameIn(List<String> values) {
            addCriterion("payee_name in", values, "payeeName");
            return (Criteria) this;
        }

        public Criteria andPayeeNameNotIn(List<String> values) {
            addCriterion("payee_name not in", values, "payeeName");
            return (Criteria) this;
        }

        public Criteria andPayeeNameBetween(String value1, String value2) {
            addCriterion("payee_name between", value1, value2, "payeeName");
            return (Criteria) this;
        }

        public Criteria andPayeeNameNotBetween(String value1, String value2) {
            addCriterion("payee_name not between", value1, value2, "payeeName");
            return (Criteria) this;
        }

        public Criteria andPayeeAddressIsNull() {
            addCriterion("payee_address is null");
            return (Criteria) this;
        }

        public Criteria andPayeeAddressIsNotNull() {
            addCriterion("payee_address is not null");
            return (Criteria) this;
        }

        public Criteria andPayeeAddressEqualTo(String value) {
            addCriterion("payee_address =", value, "payeeAddress");
            return (Criteria) this;
        }

        public Criteria andPayeeAddressNotEqualTo(String value) {
            addCriterion("payee_address <>", value, "payeeAddress");
            return (Criteria) this;
        }

        public Criteria andPayeeAddressGreaterThan(String value) {
            addCriterion("payee_address >", value, "payeeAddress");
            return (Criteria) this;
        }

        public Criteria andPayeeAddressGreaterThanOrEqualTo(String value) {
            addCriterion("payee_address >=", value, "payeeAddress");
            return (Criteria) this;
        }

        public Criteria andPayeeAddressLessThan(String value) {
            addCriterion("payee_address <", value, "payeeAddress");
            return (Criteria) this;
        }

        public Criteria andPayeeAddressLessThanOrEqualTo(String value) {
            addCriterion("payee_address <=", value, "payeeAddress");
            return (Criteria) this;
        }

        public Criteria andPayeeAddressLike(String value) {
            addCriterion("payee_address like", value, "payeeAddress");
            return (Criteria) this;
        }

        public Criteria andPayeeAddressNotLike(String value) {
            addCriterion("payee_address not like", value, "payeeAddress");
            return (Criteria) this;
        }

        public Criteria andPayeeAddressIn(List<String> values) {
            addCriterion("payee_address in", values, "payeeAddress");
            return (Criteria) this;
        }

        public Criteria andPayeeAddressNotIn(List<String> values) {
            addCriterion("payee_address not in", values, "payeeAddress");
            return (Criteria) this;
        }

        public Criteria andPayeeAddressBetween(String value1, String value2) {
            addCriterion("payee_address between", value1, value2, "payeeAddress");
            return (Criteria) this;
        }

        public Criteria andPayeeAddressNotBetween(String value1, String value2) {
            addCriterion("payee_address not between", value1, value2, "payeeAddress");
            return (Criteria) this;
        }

        public Criteria andPayeeCountryIsNull() {
            addCriterion("payee_country is null");
            return (Criteria) this;
        }

        public Criteria andPayeeCountryIsNotNull() {
            addCriterion("payee_country is not null");
            return (Criteria) this;
        }

        public Criteria andPayeeCountryEqualTo(String value) {
            addCriterion("payee_country =", value, "payeeCountry");
            return (Criteria) this;
        }

        public Criteria andPayeeCountryNotEqualTo(String value) {
            addCriterion("payee_country <>", value, "payeeCountry");
            return (Criteria) this;
        }

        public Criteria andPayeeCountryGreaterThan(String value) {
            addCriterion("payee_country >", value, "payeeCountry");
            return (Criteria) this;
        }

        public Criteria andPayeeCountryGreaterThanOrEqualTo(String value) {
            addCriterion("payee_country >=", value, "payeeCountry");
            return (Criteria) this;
        }

        public Criteria andPayeeCountryLessThan(String value) {
            addCriterion("payee_country <", value, "payeeCountry");
            return (Criteria) this;
        }

        public Criteria andPayeeCountryLessThanOrEqualTo(String value) {
            addCriterion("payee_country <=", value, "payeeCountry");
            return (Criteria) this;
        }

        public Criteria andPayeeCountryLike(String value) {
            addCriterion("payee_country like", value, "payeeCountry");
            return (Criteria) this;
        }

        public Criteria andPayeeCountryNotLike(String value) {
            addCriterion("payee_country not like", value, "payeeCountry");
            return (Criteria) this;
        }

        public Criteria andPayeeCountryIn(List<String> values) {
            addCriterion("payee_country in", values, "payeeCountry");
            return (Criteria) this;
        }

        public Criteria andPayeeCountryNotIn(List<String> values) {
            addCriterion("payee_country not in", values, "payeeCountry");
            return (Criteria) this;
        }

        public Criteria andPayeeCountryBetween(String value1, String value2) {
            addCriterion("payee_country between", value1, value2, "payeeCountry");
            return (Criteria) this;
        }

        public Criteria andPayeeCountryNotBetween(String value1, String value2) {
            addCriterion("payee_country not between", value1, value2, "payeeCountry");
            return (Criteria) this;
        }

        public Criteria andOverdraftIsNull() {
            addCriterion("overdraft is null");
            return (Criteria) this;
        }

        public Criteria andOverdraftIsNotNull() {
            addCriterion("overdraft is not null");
            return (Criteria) this;
        }

        public Criteria andOverdraftEqualTo(Double value) {
            addCriterion("overdraft =", value, "overdraft");
            return (Criteria) this;
        }

        public Criteria andOverdraftNotEqualTo(Double value) {
            addCriterion("overdraft <>", value, "overdraft");
            return (Criteria) this;
        }

        public Criteria andOverdraftGreaterThan(Double value) {
            addCriterion("overdraft >", value, "overdraft");
            return (Criteria) this;
        }

        public Criteria andOverdraftGreaterThanOrEqualTo(Double value) {
            addCriterion("overdraft >=", value, "overdraft");
            return (Criteria) this;
        }

        public Criteria andOverdraftLessThan(Double value) {
            addCriterion("overdraft <", value, "overdraft");
            return (Criteria) this;
        }

        public Criteria andOverdraftLessThanOrEqualTo(Double value) {
            addCriterion("overdraft <=", value, "overdraft");
            return (Criteria) this;
        }

        public Criteria andOverdraftIn(List<Double> values) {
            addCriterion("overdraft in", values, "overdraft");
            return (Criteria) this;
        }

        public Criteria andOverdraftNotIn(List<Double> values) {
            addCriterion("overdraft not in", values, "overdraft");
            return (Criteria) this;
        }

        public Criteria andOverdraftBetween(Double value1, Double value2) {
            addCriterion("overdraft between", value1, value2, "overdraft");
            return (Criteria) this;
        }

        public Criteria andOverdraftNotBetween(Double value1, Double value2) {
            addCriterion("overdraft not between", value1, value2, "overdraft");
            return (Criteria) this;
        }

        public Criteria andTokenIsNull() {
            addCriterion("token is null");
            return (Criteria) this;
        }

        public Criteria andTokenIsNotNull() {
            addCriterion("token is not null");
            return (Criteria) this;
        }

        public Criteria andTokenEqualTo(Long value) {
            addCriterion("token =", value, "token");
            return (Criteria) this;
        }

        public Criteria andTokenNotEqualTo(Long value) {
            addCriterion("token <>", value, "token");
            return (Criteria) this;
        }

        public Criteria andTokenGreaterThan(Long value) {
            addCriterion("token >", value, "token");
            return (Criteria) this;
        }

        public Criteria andTokenGreaterThanOrEqualTo(Long value) {
            addCriterion("token >=", value, "token");
            return (Criteria) this;
        }

        public Criteria andTokenLessThan(Long value) {
            addCriterion("token <", value, "token");
            return (Criteria) this;
        }

        public Criteria andTokenLessThanOrEqualTo(Long value) {
            addCriterion("token <=", value, "token");
            return (Criteria) this;
        }

        public Criteria andTokenIn(List<Long> values) {
            addCriterion("token in", values, "token");
            return (Criteria) this;
        }

        public Criteria andTokenNotIn(List<Long> values) {
            addCriterion("token not in", values, "token");
            return (Criteria) this;
        }

        public Criteria andTokenBetween(Long value1, Long value2) {
            addCriterion("token between", value1, value2, "token");
            return (Criteria) this;
        }

        public Criteria andTokenNotBetween(Long value1, Long value2) {
            addCriterion("token not between", value1, value2, "token");
            return (Criteria) this;
        }

        public Criteria andParkInfoIsNull() {
            addCriterion("park_info is null");
            return (Criteria) this;
        }

        public Criteria andParkInfoIsNotNull() {
            addCriterion("park_info is not null");
            return (Criteria) this;
        }

        public Criteria andParkInfoEqualTo(String value) {
            addCriterion("park_info =", value, "parkInfo");
            return (Criteria) this;
        }

        public Criteria andParkInfoNotEqualTo(String value) {
            addCriterion("park_info <>", value, "parkInfo");
            return (Criteria) this;
        }

        public Criteria andParkInfoGreaterThan(String value) {
            addCriterion("park_info >", value, "parkInfo");
            return (Criteria) this;
        }

        public Criteria andParkInfoGreaterThanOrEqualTo(String value) {
            addCriterion("park_info >=", value, "parkInfo");
            return (Criteria) this;
        }

        public Criteria andParkInfoLessThan(String value) {
            addCriterion("park_info <", value, "parkInfo");
            return (Criteria) this;
        }

        public Criteria andParkInfoLessThanOrEqualTo(String value) {
            addCriterion("park_info <=", value, "parkInfo");
            return (Criteria) this;
        }

        public Criteria andParkInfoLike(String value) {
            addCriterion("park_info like", value, "parkInfo");
            return (Criteria) this;
        }

        public Criteria andParkInfoNotLike(String value) {
            addCriterion("park_info not like", value, "parkInfo");
            return (Criteria) this;
        }

        public Criteria andParkInfoIn(List<String> values) {
            addCriterion("park_info in", values, "parkInfo");
            return (Criteria) this;
        }

        public Criteria andParkInfoNotIn(List<String> values) {
            addCriterion("park_info not in", values, "parkInfo");
            return (Criteria) this;
        }

        public Criteria andParkInfoBetween(String value1, String value2) {
            addCriterion("park_info between", value1, value2, "parkInfo");
            return (Criteria) this;
        }

        public Criteria andParkInfoNotBetween(String value1, String value2) {
            addCriterion("park_info not between", value1, value2, "parkInfo");
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