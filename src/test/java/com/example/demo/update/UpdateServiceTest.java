package com.example.demo.update;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.entity.CustomerInfo;
import com.example.demo.entity.TelInfo;
import com.example.demo.entity.UserInfo;
import com.example.demo.service.UpdateService;

@SpringBootTest
@ActiveProfiles("test")
@Transactional
public class UpdateServiceTest {

	@Autowired
	private UpdateService updateService;

	/*
	 * 項番53
	 */
	@Test
	public void updateUserInfoAndTelInfo53() {

		UserInfo userInfo = new UserInfo();
		userInfo.setCustomer_ID(44);
		userInfo.setCustomer_name("Test44");
		userInfo.setEmail("test@44ca.co.jp");
		userInfo.setGender("男");
		userInfo.setAddress("大分県44");

		List<TelInfo> telInfos = new ArrayList<>();

		String[] tels = new String[] { "44112345678", "44212345678", "44312345678", "44412345678", "44512345678" };
		int telorder = 1;

		for (String tel : tels) {
			if (tel.isBlank()) {
				telorder++;
				continue;
			} else {
				TelInfo telInfo = new TelInfo();
				telInfo.setTel(tel);
				telInfo.setCustomer_ID(44);
				telInfo.setTelorder(telorder++);
				telInfos.add(telInfo);
			}
		}
		try {

			updateService.updateUserInfoAndTelInfo(userInfo, telInfos);

		} catch (Exception e) {
			fail();
		}
	}

	/*
	 * 項番54
	 */
	@Test
	public void updateUserInfoAndTelInfo54() {

		UserInfo userInfo = new UserInfo();
		userInfo.setCustomer_ID(44);
		userInfo.setCustomer_name("Test45");
		userInfo.setEmail("test@45ca.co.jp");
		userInfo.setGender("男");
		userInfo.setAddress("宮崎県45");

		List<TelInfo> telInfos = new ArrayList<>();

		String[] tels = new String[] { "44112345678", "44212345678", "44312345678", "45412345678", "45512345678" };
		int telorder = 1;

		for (String tel : tels) {
			if (tel.isBlank()) {
				telorder++;
				continue;
			} else {
				TelInfo telInfo = new TelInfo();
				telInfo.setTel(tel);
				telInfo.setCustomer_ID(45);
				telInfo.setTelorder(telorder++);
				telInfos.add(telInfo);
			}
		}
		try {

			updateService.updateUserInfoAndTelInfo(userInfo, telInfos);
			fail("Expected exception was not thrown");

		} catch (Exception e) {
			assertNotNull(e);

		}
	}

	/*
	 * 項番55
	 */
	@Test
	public void update1_55() {

		UserInfo userInfo = new UserInfo();
		userInfo.setCustomer_ID(46);
		userInfo.setCustomer_name("Test46");
		userInfo.setEmail("test@46ca.co.jp");
		userInfo.setGender("男");
		userInfo.setAddress("鹿児島県46");

		try {
			UserInfo result = updateService.update1(userInfo);
			assertEquals(46, result.getCustomer_ID());
			assertEquals("Test46", result.getCustomer_name());
			assertEquals("test@46ca.co.jp", result.getEmail());
			assertEquals("男", result.getGender());
			assertEquals("鹿児島県46", result.getAddress());
		} catch (Exception e) {
			fail();

		}
	}

	/*
	 * 項番56
	 */
	@Test
	public void update2_56() {

		List<TelInfo> telInfos = new ArrayList<>();

		String[] tels = new String[] { "47112345678", "47212345678", "47312345678", "47412345678", "47512345678" };
		int telorder = 1;

		for (String tel : tels) {
			if (tel.isBlank()) {
				telorder++;
				continue;
			} else {
				TelInfo telInfo = new TelInfo();
				telInfo.setTel(tel);
				telInfo.setCustomer_ID(47);
				telInfo.setTelorder(telorder++);
				telInfos.add(telInfo);
			}
		}
		try {
			updateService.update2(telInfos);
		} catch (Exception e) {
			fail();

		}
	}

	/*
	 * 項番57
	 */
	@Test
	public void update2_57() {

		List<TelInfo> telInfos = new ArrayList<>();

		String[] tels = new String[] { "48112345678" };
		int telorder = 1;

		for (String tel : tels) {
			if (tel.isBlank()) {
				telorder++;
				continue;
			} else {
				TelInfo telInfo = new TelInfo();
				telInfo.setTel(tel);
				telInfo.setCustomer_ID(48);
				telInfo.setTelorder(telorder++);
				telInfos.add(telInfo);
			}
		}
		try {
			updateService.update2(telInfos);
		} catch (Exception e) {
			fail();

		}
	}

	/*
	 * 項番x1追加ケース
	 */
	@Test
	public void updateFindByIdx1() {

		Integer customer_ID = 2;

		Optional<CustomerInfo> result = updateService.findById(customer_ID);

		CustomerInfo customerInfo = result.get();

		assertEquals(2, customerInfo.getCustomer_ID());
		assertEquals("Test2", customerInfo.getCustomer_name());
		assertEquals("02112345678", customerInfo.getTelInfos().get(0).getTel());
		assertEquals("test@2ca.co.jp", customerInfo.getEmail());
		assertEquals("女", customerInfo.getGender());
		assertEquals("青森県2", customerInfo.getAddress());
	}

}
