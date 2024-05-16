package com.example.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.entity.TelInfo;
import com.example.demo.entity.UserInfo;
import com.example.demo.repository.UserInfoRepository;

@Service
@Transactional
public class AddService {

	@Autowired
	private UserInfoRepository userInfoRepository;

	@Autowired
	private JdbcTemplate jdbcTemplate;

	@Transactional(rollbackFor = Exception.class)
	public void addUserInfoAndTelInfo(UserInfo userInfo, List<TelInfo> telInfos) throws Exception {
		UserInfo savedCustomerId = add1(userInfo);
		add2(savedCustomerId, telInfos);
	}

	public UserInfo add1(UserInfo userInfo) {
		return userInfoRepository.save(userInfo);
	}

	public void add2(UserInfo savedCustomerId, List<TelInfo> telInfos) {
		String sql = "INSERT INTO TELINFO (tel, customer_ID, telorder) VALUES (?, ?, ?)";
		for (TelInfo telInfo : telInfos) {
			telInfo.setCustomer_ID(savedCustomerId.getCustomer_ID());
			jdbcTemplate.update(sql, telInfo.getTel(), telInfo.getCustomer_ID(), telInfo.getTelorder());
		}
	}

}
