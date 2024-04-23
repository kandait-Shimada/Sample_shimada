package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.entity.TelInfo;

@Repository
public interface TelInfoRepository extends JpaRepository<TelInfo, String> {
	
   
}
