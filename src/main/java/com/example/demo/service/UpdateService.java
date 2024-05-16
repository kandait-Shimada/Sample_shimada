package com.example.demo.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.entity.CustomerInfo;
import com.example.demo.entity.TelInfo;
import com.example.demo.entity.UserInfo;
import com.example.demo.repository.CustomerInfoRepository;
import com.example.demo.repository.UserInfoRepository;

@Service
public class UpdateService {

	@Autowired
	private CustomerInfoRepository customerInfoRepository;

	@Autowired
	private UserInfoRepository userInfoRepository;

	@Autowired
	private JdbcTemplate jdbcTemplate;

	public Optional<CustomerInfo> findById(Integer customer_ID) {

		return customerInfoRepository.findById(customer_ID);
	}

	@Transactional(rollbackFor = Exception.class)
	public void updateUserInfoAndTelInfo(UserInfo userInfo, List<TelInfo> telInfos) throws Exception {
		updata1(userInfo);
		updata2(telInfos);
	}

	public UserInfo updata1(UserInfo userInfo) throws Exception {

		return userInfoRepository.save(userInfo);
	}

	public void updata2(List<TelInfo> telInfos) throws Exception {
		String sql = "MERGE INTO TELINFO t " +
				"USING (SELECT ? AS tel, ? AS customer_ID, ? AS telorder FROM dual) s " +
				"ON (t.customer_ID = s.customer_ID AND t.telorder = s.telorder) " +
				"WHEN MATCHED THEN " +
				"  UPDATE SET t.tel = s.tel " +
				"WHEN NOT MATCHED THEN " +
				"  INSERT (tel, customer_ID, telorder) VALUES (s.tel, s.customer_ID, s.telorder)";
		for (TelInfo telInfo : telInfos) {
			jdbcTemplate.update(sql, telInfo.getTel(), telInfo.getCustomer_ID(), telInfo.getTelorder());
		}
	}

}
