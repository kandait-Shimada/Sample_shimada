package com.example.demo.add;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.entity.TelInfo;
import com.example.demo.entity.UserInfo;
import com.example.demo.service.AddService;

@SpringBootTest
@ActiveProfiles("test")
@Transactional
public class AddServiceTest {

	@Autowired
	private AddService addService;

	/*
	 * 項番12
	 */
	@Test
	public void addUserInfoAndTelInfo12() {

		UserInfo userInfo = new UserInfo();
		userInfo.setCustomer_name("Test12");
		userInfo.setEmail("test@12ca.co.jp");
		userInfo.setGender("男");
		userInfo.setAddress("千葉県12");

		List<TelInfo> telInfos = new ArrayList<>();

		String[] tels = new String[] { "12112345678", "12212345678", "12312345678", "12412345678", "12512345678" };
		int telorder = 1;

		for (String tel : tels) {
			if (tel.isBlank()) {
				telorder++;
				continue;
			} else {
				TelInfo telInfo = new TelInfo();
				telInfo.setTel(tel);
				telInfo.setTelorder(telorder++);
				telInfos.add(telInfo);
			}
		}
		try {

			addService.addUserInfoAndTelInfo(userInfo, telInfos);

		} catch (Exception e) {
			fail();
		}
	}

	/*
	 * 項番13
	 */
	@Test
	public void addUserInfoAndTelInfo13() {

		UserInfo userInfo = new UserInfo();
		userInfo.setCustomer_name("Test13");
		userInfo.setEmail("test@13ca.co.jp");
		userInfo.setGender("男");
		userInfo.setAddress("東京都13");

		List<TelInfo> telInfos = new ArrayList<>();

		String[] tels = new String[] { "12112345678", "12212345678", "12112345678", "12112345678", "12112345678" };
		int telorder = 1;

		for (String tel : tels) {
			if (tel.isBlank()) {
				telorder++;
				continue;
			} else {
				TelInfo telInfo = new TelInfo();
				telInfo.setTel(tel);
				telInfo.setTelorder(telorder++);
				telInfos.add(telInfo);
			}
		}
		try {

			addService.addUserInfoAndTelInfo(userInfo, telInfos);
			fail("Expected exception was not thrown");

		} catch (Exception e) {
			assertNotNull(e);

		}
	}

	/*
	 * 項番14
	 */
	@Test
	public void add1_14() {

		UserInfo userInfo = new UserInfo();
		userInfo.setCustomer_name("Test14");
		userInfo.setEmail("test@14ca.co.jp");
		userInfo.setGender("男");
		userInfo.setAddress("神奈川県14");

		UserInfo result = addService.add1(userInfo);

		assertEquals("Test14", result.getCustomer_name());
		assertEquals("test@14ca.co.jp", result.getEmail());
		assertEquals("男", result.getGender());
		assertEquals("神奈川県14", result.getAddress());

	}

	/*
	 * 項番15
	 */
	@Test
	public void add2_15() {
		UserInfo userInfo = new UserInfo();
		userInfo.setCustomer_name("Test15");
		userInfo.setEmail("test@15ca.co.jp");
		userInfo.setGender("男");
		userInfo.setAddress("千葉県15");

		UserInfo savedUser = addService.add1(userInfo);

		List<TelInfo> telInfos = new ArrayList<>();

		String[] tels = new String[] { "15112345678", "15212345678", "15312345678", "15412345678", "15512345678" };
		int telorder = 1;

		for (String tel : tels) {
			if (tel.isBlank()) {
				telorder++;
				continue;
			} else {
				TelInfo telInfo = new TelInfo();
				telInfo.setTel(tel);
				telInfo.setTelorder(telorder++);
				telInfos.add(telInfo);
			}
		}
		addService.add2(savedUser, telInfos);

	}

	/*
	 * 項番16
	 */
	@Test
	public void add2_16() {

		UserInfo userInfo = new UserInfo();
		userInfo.setCustomer_name("Test16");
		userInfo.setEmail("test@16ca.co.jp");
		userInfo.setGender("男");
		userInfo.setAddress("埼玉県16");

		UserInfo savedUser = addService.add1(userInfo);

		List<TelInfo> telInfos = new ArrayList<>();

		String[] tels = new String[] { "16112345678" };
		int telorder = 1;

		for (String tel : tels) {
			if (tel.isBlank()) {
				telorder++;
				continue;
			} else {
				TelInfo telInfo = new TelInfo();
				telInfo.setTel(tel);
				telInfo.setTelorder(telorder++);
				telInfos.add(telInfo);
			}
		}
		addService.add2(savedUser, telInfos);
	}
}
