package com.example.demo.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import com.example.demo.entity.CustomerInfo;

public interface SearchRepository extends JpaRepository<CustomerInfo, String>, JpaSpecificationExecutor<CustomerInfo> {




}

