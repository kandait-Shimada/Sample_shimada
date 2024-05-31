package com.example.demo.search;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.entity.CustomerInfo;
import com.example.demo.entity.TelInfo;
import com.example.demo.service.SearchService;

@SpringBootTest
@ActiveProfiles("test")
@Transactional
public class SearchServiceTest {

	@Autowired
	private SearchService searchService;

	/*
	 * 項番1
	 */
	@Test
	public void testSearch1() {
		CustomerInfo customerInfo = new CustomerInfo();
		customerInfo.setCustomer_ID(null);
		customerInfo.setCustomer_name("");
		List<TelInfo> telInfos = new ArrayList<>();
		customerInfo.setTelInfos(telInfos);
		customerInfo.setEmail("");
		customerInfo.setGender("指定なし");
		customerInfo.setAddress("");

		List<CustomerInfo> result = searchService.search(customerInfo);

		assertEquals(50, result.size());
	}

	/*
	 * 項番2
	 */
	@Test
	public void testSearch2() {
		CustomerInfo customerInfo = new CustomerInfo();
		customerInfo.setCustomer_ID(2);
		customerInfo.setCustomer_name("");
		List<TelInfo> telInfos = new ArrayList<>();
		customerInfo.setTelInfos(telInfos);
		customerInfo.setEmail("");
		customerInfo.setGender("指定なし");
		customerInfo.setAddress("");

		List<CustomerInfo> result = searchService.search(customerInfo);

		assertEquals(1, result.size());
		assertEquals(2, result.get(0).getCustomer_ID());
		assertEquals("Test2", result.get(0).getCustomer_name());
		assertEquals("02112345678", result.get(0).getTelInfos().get(0).getTel());
		assertEquals("test@2ca.co.jp", result.get(0).getEmail());
		assertEquals("女", result.get(0).getGender());
		assertEquals("青森県2", result.get(0).getAddress());
	}

	/*
	 * 項番3
	 */
	@Test
	public void testSearch3() {
		CustomerInfo customerInfo = new CustomerInfo();
		customerInfo.setCustomer_ID(null);
		customerInfo.setCustomer_name("Test3");
		List<TelInfo> telInfos = new ArrayList<>();
		customerInfo.setTelInfos(telInfos);
		customerInfo.setEmail("");
		customerInfo.setGender("指定なし");
		customerInfo.setAddress("");

		List<CustomerInfo> result = searchService.search(customerInfo);

		assertEquals(1, result.size());
		assertEquals(3, result.get(0).getCustomer_ID());
		assertEquals("Test3", result.get(0).getCustomer_name());
		assertEquals("03112345678", result.get(0).getTelInfos().get(0).getTel());
		assertEquals("test@3ca.co.jp", result.get(0).getEmail());
		assertEquals("男", result.get(0).getGender());
		assertEquals("岩手県3", result.get(0).getAddress());
	}

	/*
	 * 項番4
	 */
	@Test
	public void testSearch4() {
		CustomerInfo customerInfo = new CustomerInfo();
		customerInfo.setCustomer_ID(null);
		customerInfo.setCustomer_name("");
		List<TelInfo> telInfos = new ArrayList<>();
		TelInfo telInfo = new TelInfo();
		telInfo.setTel("04112345678");
		telInfos.add(telInfo);
		customerInfo.setTelInfos(telInfos);
		customerInfo.setEmail("");
		customerInfo.setGender("指定なし");
		customerInfo.setAddress("");

		List<CustomerInfo> result = searchService.search(customerInfo);

		assertEquals(1, result.size());
		assertEquals(4, result.get(0).getCustomer_ID());
		assertEquals("Test4", result.get(0).getCustomer_name());
		assertEquals("04112345678", result.get(0).getTelInfos().get(0).getTel());
		assertEquals("test@4ca.co.jp", result.get(0).getEmail());
		assertEquals("女", result.get(0).getGender());
		assertEquals("宮城県4", result.get(0).getAddress());
	}

	/*
	 * 項番5
	 */
	@Test
	public void testSearch5() {
		CustomerInfo customerInfo = new CustomerInfo();
		customerInfo.setCustomer_ID(null);
		customerInfo.setCustomer_name("");
		List<TelInfo> telInfos = new ArrayList<>();
		customerInfo.setTelInfos(telInfos);
		customerInfo.setEmail("test@5ca.co.jp");
		customerInfo.setGender("指定なし");
		customerInfo.setAddress("");

		List<CustomerInfo> result = searchService.search(customerInfo);

		assertEquals(1, result.size());
		assertEquals(5, result.get(0).getCustomer_ID());
		assertEquals("Test5", result.get(0).getCustomer_name());
		assertEquals("05112345678", result.get(0).getTelInfos().get(0).getTel());
		assertEquals("test@5ca.co.jp", result.get(0).getEmail());
		assertEquals("男", result.get(0).getGender());
		assertEquals("秋田県5", result.get(0).getAddress());
	}

	/*
	 * 項番6
	 */
	@Test
	public void testSearch6() {
		CustomerInfo customerInfo = new CustomerInfo();
		customerInfo.setCustomer_ID(null);
		customerInfo.setCustomer_name("");
		List<TelInfo> telInfos = new ArrayList<>();
		customerInfo.setTelInfos(telInfos);
		customerInfo.setEmail("");
		customerInfo.setGender("女");
		customerInfo.setAddress("");

		List<CustomerInfo> result = searchService.search(customerInfo);

		assertEquals(5, result.size());
	}

	/*
	 * 項番7
	 */
	@Test
	public void testSearch7() {
		CustomerInfo customerInfo = new CustomerInfo();
		customerInfo.setCustomer_ID(null);
		customerInfo.setCustomer_name("");
		List<TelInfo> telInfos = new ArrayList<>();
		customerInfo.setTelInfos(telInfos);
		customerInfo.setEmail("");
		customerInfo.setGender("指定なし");
		customerInfo.setAddress("福島県");

		List<CustomerInfo> result = searchService.search(customerInfo);

		assertEquals(1, result.size());
		assertEquals(7, result.get(0).getCustomer_ID());
		assertEquals("Test7", result.get(0).getCustomer_name());
		assertEquals("07112345678", result.get(0).getTelInfos().get(0).getTel());
		assertEquals("test@7ca.co.jp", result.get(0).getEmail());
		assertEquals("男", result.get(0).getGender());
		assertEquals("福島県7", result.get(0).getAddress());
	}

	/*
	 * 項番8
	 */
	@Test
	public void testSearch8() {
		CustomerInfo customerInfo = new CustomerInfo();
		customerInfo.setCustomer_ID(8);
		customerInfo.setCustomer_name("Test8");
		TelInfo telInfo = new TelInfo();
		telInfo.setTel("08112345678");
		customerInfo.setTelInfos(List.of(telInfo));
		customerInfo.setEmail("test@8ca.co.jp");
		customerInfo.setGender("女");
		customerInfo.setAddress("茨城県");

		List<CustomerInfo> result = searchService.search(customerInfo);

		assertEquals(1, result.size());
		assertEquals(8, result.get(0).getCustomer_ID());
		assertEquals("Test8", result.get(0).getCustomer_name());
		assertEquals("08112345678", result.get(0).getTelInfos().get(0).getTel());
		assertEquals("test@8ca.co.jp", result.get(0).getEmail());
		assertEquals("女", result.get(0).getGender());
		assertEquals("茨城県8", result.get(0).getAddress());
	}

	/*
	 * 項番9
	 */
	@Test
	public void testSearch9() {
		CustomerInfo customerInfo = new CustomerInfo();
		customerInfo.setCustomer_ID(1);
		customerInfo.setCustomer_name("");
		List<TelInfo> telInfos = new ArrayList<>();
		customerInfo.setTelInfos(telInfos);
		customerInfo.setEmail("");
		customerInfo.setGender("指定なし");
		customerInfo.setAddress("");

		List<CustomerInfo> result = searchService.search(customerInfo);

		assertEquals(0, result.size());
	}
}
