package com.example.demo.service;

import org.apache.commons.lang3.StringUtils;
import org.springframework.data.jpa.domain.Specification;

import com.example.demo.entity.CustomerInfo;
import com.example.demo.entity.TelInfo;
import com.example.demo.entity.UserInfo;

import jakarta.persistence.criteria.Join;
import jakarta.persistence.criteria.JoinType;

public class SearchSpecifications {

    public static Specification<CustomerInfo> customer_IDLike(Integer customer_ID) {
        return (root, query, cb) -> {
            if (customer_ID == null || customer_ID == 0) return null;
            return cb.equal(root.get("customer_ID"), customer_ID);
        };
    }

    public static Specification<CustomerInfo> customer_nameLike(String customer_name) {
        return (root, query, cb) -> {
            if (StringUtils.isEmpty(customer_name)) return null;
            return cb.like(root.get("customer_name"), "%" + customer_name + "%");
        };
    }

    public static Specification<CustomerInfo> telLike(String tel) {
        return (root, query, cb) -> {
            if (StringUtils.isEmpty(tel)) return null;
            Join<UserInfo, TelInfo> telJoin = root.join("telInfos", JoinType.LEFT);
            return cb.like(telJoin.get("tel"), "%" + tel + "%");
        };
    }

    public static Specification<CustomerInfo> emailLike(String email) {
        return (root, query, cb) -> {
            if (StringUtils.isEmpty(email)) return null;
            return cb.like(root.get("email"), "%" + email + "%");
        };
    }

    public static Specification<CustomerInfo> genderLike(String gender) {
        return (root, query, cb) -> {
            if (StringUtils.isEmpty(gender)) return null;
            return cb.like(root.get("gender"),  "%" + gender + "%");
        };
    }

    public static Specification<CustomerInfo> addressLike(String address) {
        return (root, query, cb) -> {
            if (StringUtils.isEmpty(address)) return null;
            return cb.like(root.get("address"), "%" + address + "%");
        };
    }
}


//public class UserSpecifications {
//
//}
