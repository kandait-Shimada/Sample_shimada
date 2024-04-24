package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.entity.CustomerInfo;

@Repository
public interface CustomerInfoRepository extends JpaRepository<CustomerInfo, String> {
	// 更新時の検索
	// CustomerInfo findById(Integer customerID);
	// CustomerInfo update1(CustomerInfo customerInfo);
	// CustomerInfo update2(CustomerInfo customerInfo);
	
	// CustomerInfo insert(CustomerInfo customerInfo);
}
