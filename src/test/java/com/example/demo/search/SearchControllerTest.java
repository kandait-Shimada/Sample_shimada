package com.example.demo.search;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.MapBindingResult;

import com.example.demo.controller.SearchController;
import com.example.demo.entity.CustomerInfo;
import com.example.demo.entity.TelInfo;
import com.example.demo.form.CustomerInfoForm;
import com.example.demo.service.SearchService;

public class SearchControllerTest {

	@Mock
	private SearchService searchService;

	@Mock
	private Model model;

	@InjectMocks
	private SearchController searchController;

	@BeforeEach
	public void setUp() {
		MockitoAnnotations.openMocks(this);
	}

	/*
	 * 項番10
	 */
	@Test
	public void testSearchFunction10() throws Exception {
		CustomerInfoForm customerInfoForm = new CustomerInfoForm();
		customerInfoForm.setCustomer_ID(10);

		CustomerInfo customerInfo = new CustomerInfo();
		customerInfo.setCustomer_ID(10);
		customerInfo.setCustomer_name("Test10");
		TelInfo telInfo = new TelInfo();
		telInfo.setTel("10112345678");
		telInfo.setCustomerInfo(customerInfo);
		customerInfo.setTelInfos(Arrays.asList(telInfo));
		customerInfo.setEmail("test@10ca.co.jp");
		customerInfo.setGender("女");
		customerInfo.setAddress("群馬県10");

		List<CustomerInfo> searchResult = Arrays.asList(customerInfo);
		when(searchService.search(any(CustomerInfo.class))).thenReturn(searchResult);

		BindingResult result = new MapBindingResult(new HashMap<>(), "customerInfoForm");

		String viewName = searchController.searchFunction(customerInfoForm, result, model);

		assertEquals("search", viewName);
		verify(model, times(1)).addAttribute("result", searchResult);
	}

	/*
	 * 項番11
	 */
	@Test
	public void testSearchFunction11() throws Exception {

		CustomerInfoForm customerInfoForm = new CustomerInfoForm();
		customerInfoForm.setTel(new String[] { "testcase11" });

		BindingResult result = new MapBindingResult(new HashMap<>(), "customerInfoForm");

		Map<String, Object> modelAttributes = new HashMap<>();
		when(model.addAttribute(any(String.class), any(Object.class))).thenAnswer(invocation -> {
			modelAttributes.put(invocation.getArgument(0), invocation.getArgument(1));
			return model;
		});

		String viewName = searchController.searchFunction(customerInfoForm, result, model);

		assertEquals("search", viewName);
		assertTrue(modelAttributes.containsKey("error"));
		assertEquals("電話番号は数字のみで入力してください。", modelAttributes.get("error"));
	}
	
	/*
	 * 項番100 追加ケース
	 */
	@Test
	public void testSearchFunction100() throws Exception {
		CustomerInfoForm customerInfoForm = new CustomerInfoForm();
		customerInfoForm.setCustomer_ID(10);
		customerInfoForm.setTel(new String[] { "10112345678" });
		

		CustomerInfo customerInfo = new CustomerInfo();
		customerInfo.setCustomer_ID(10);
		customerInfo.setCustomer_name("Test10");
		TelInfo telInfo = new TelInfo();
		telInfo.setTel("10112345678");
		telInfo.setCustomerInfo(customerInfo);
		customerInfo.setTelInfos(Arrays.asList(telInfo));
		customerInfo.setEmail("test@10ca.co.jp");
		customerInfo.setGender("女");
		customerInfo.setAddress("群馬県10");

		List<CustomerInfo> searchResult = Arrays.asList(customerInfo);
		when(searchService.search(any(CustomerInfo.class))).thenReturn(searchResult);

		BindingResult result = new MapBindingResult(new HashMap<>(), "customerInfoForm");

		String viewName = searchController.searchFunction(customerInfoForm, result, model);

		assertEquals("search", viewName);
		verify(model, times(1)).addAttribute("result", searchResult);
	}
	
	
}
