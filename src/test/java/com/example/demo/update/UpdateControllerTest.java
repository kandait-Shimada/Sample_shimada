package com.example.demo.update;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.MapBindingResult;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.mvc.support.RedirectAttributesModelMap;

import com.example.demo.controller.UpdateController;
import com.example.demo.entity.CustomerInfo;
import com.example.demo.entity.TelInfo;
import com.example.demo.form.CustomerInfoForm;
import com.example.demo.service.UpdateService;

@SpringBootTest
@ActiveProfiles("test")
@Transactional
public class UpdateControllerTest {

	@Mock
	private UpdateService updateService;

	@Mock
	private Model model;

	@InjectMocks
	private UpdateController updateController;

	@BeforeEach
	public void setUp() {
		MockitoAnnotations.openMocks(this);
	}

	/*
	 * 項番58
	 */
	@Test
	public void updateFindById58() {

		Integer customer_ID = 2;

		CustomerInfo customerInfo = new CustomerInfo();
		customerInfo.setCustomer_ID(customer_ID);
		customerInfo.setCustomer_name("Test2");
		customerInfo.setEmail("test@2ca.co.jp");
		customerInfo.setGender("女");
		customerInfo.setAddress("青森県2");
		TelInfo telInfo = new TelInfo();
		telInfo.setTel("02112345678");
		telInfo.setCustomerInfo(customerInfo);
		customerInfo.setTelInfos(Arrays.asList(telInfo));

		when(updateService.findById(customer_ID)).thenReturn(Optional.of(customerInfo));

		Map<String, Object> modelAttributes = new HashMap<>();
		when(model.addAttribute(anyString(), any())).thenAnswer(invocation -> {
			modelAttributes.put(invocation.getArgument(0), invocation.getArgument(1));
			return model;
		});

		String viewName = updateController.updateFindById(model, customer_ID);

		assertEquals("update", viewName);
		assertTrue(modelAttributes.containsKey("customer"));
		assertEquals(customerInfo, modelAttributes.get("customer"));
	}

	/*
	 * 追加ケース項番97
	 */
	@Test
	public void updateFindById97() {

		Integer customer_ID = 1;

		CustomerInfo customerInfo = new CustomerInfo();
		customerInfo.setCustomer_ID(customer_ID);

		when(updateService.findById(customer_ID)).thenReturn(Optional.empty());

		Map<String, Object> modelAttributes = new HashMap<>();
		when(model.addAttribute(anyString(), any())).thenAnswer(invocation -> {
			modelAttributes.put(invocation.getArgument(0), invocation.getArgument(1));
			return model;
		});

		String viewName = updateController.updateFindById(model, customer_ID);

		assertEquals("search", viewName);
		assertTrue(modelAttributes.containsKey("error"));
		assertEquals("入力された顧客IDのデータが存在しません。", modelAttributes.get("error"));

	}

	/*
	 * 項番59
	 */
	@Test
	public void updateFunction59() {

		CustomerInfoForm customerInfoForm = new CustomerInfoForm();
		customerInfoForm.setCustomer_ID(50);
		customerInfoForm.setCustomer_name("Test50");
		customerInfoForm.setEmail("test@50ca.co.jp");
		customerInfoForm.setGender("男");
		customerInfoForm.setAddress("群馬県50");
		List<TelInfo> telInfos = new ArrayList<>();
		String[] tels = new String[] { "50112345678", "50212345678", "50312345678", "50412345678", "50512345678" };
		int telorder = 0;
		for (String tel : tels) {
			TelInfo telInfo = new TelInfo();
			telInfo.setCustomer_ID(50);
			telInfo.setTel(tel);
			telInfo.setTelorder(telorder++);
			telInfos.add(telInfo);
		}
		customerInfoForm.setTelInfos(telInfos);

		BindingResult result = new MapBindingResult(new HashMap<>(), "customerInfoForm");

		RedirectAttributes redirectAttributes = new RedirectAttributesModelMap();

		Map<String, Object> modelAttributes = new HashMap<>();
		when(model.addAttribute(any(String.class), any(Object.class))).thenAnswer(invocation -> {
			modelAttributes.put(invocation.getArgument(0), invocation.getArgument(1));
			return model;
		});

		try {
			String viewName = updateController.updateFunction(customerInfoForm, result, model, redirectAttributes);
			assertEquals("update", viewName);
			assertTrue(modelAttributes.containsKey("success"));
			assertEquals("顧客情報を更新しました。", modelAttributes.get("success"));
			assertEquals(customerInfoForm, modelAttributes.get("customer"));
		} catch (Exception e) {
			fail();
		}

	}

	/*
	 * 項番60
	 */
	@Test
	public void updateFunction60() {

		CustomerInfoForm customerInfoForm = new CustomerInfoForm();
		customerInfoForm.setCustomer_ID(51);
		customerInfoForm.setCustomer_name("Test51");
		customerInfoForm.setEmail("test@51ca.co.jp");
		customerInfoForm.setGender("男");
		customerInfoForm.setAddress("埼玉県51");
		List<TelInfo> telInfos = new ArrayList<>();
		String[] tels = new String[] { "51112345678", "", "", "", "" };
		int telorder = 0;
		for (String tel : tels) {
			TelInfo telInfo = new TelInfo();
			telInfo.setCustomer_ID(51);
			telInfo.setTel(tel);
			telInfo.setTelorder(telorder++);
			telInfos.add(telInfo);
		}
		customerInfoForm.setTelInfos(telInfos);

		BindingResult result = new MapBindingResult(new HashMap<>(), "customerInfoForm");

		RedirectAttributes redirectAttributes = new RedirectAttributesModelMap();

		Map<String, Object> modelAttributes = new HashMap<>();
		when(model.addAttribute(any(String.class), any(Object.class))).thenAnswer(invocation -> {
			modelAttributes.put(invocation.getArgument(0), invocation.getArgument(1));
			return model;
		});

		try {
			String viewName = updateController.updateFunction(customerInfoForm, result, model, redirectAttributes);
			assertEquals("update", viewName);
			assertTrue(modelAttributes.containsKey("success"));
			assertEquals("顧客情報を更新しました。", modelAttributes.get("success"));
			assertEquals(customerInfoForm, modelAttributes.get("customer"));
		} catch (Exception e) {
			fail();
		}
	}

	/*
	 * 項番61
	 */
	@Test
	public void updateFunction61() {

		CustomerInfoForm customerInfoForm = new CustomerInfoForm();
		customerInfoForm.setCustomer_ID(87);
		customerInfoForm.setCustomer_name("Test1234567890123456");
		customerInfoForm.setEmail("test@87ca.co.jp");
		customerInfoForm.setGender("男");
		customerInfoForm.setAddress("石川県87");
		List<TelInfo> telInfos = new ArrayList<>();
		String[] tels = new String[] { "87112345678", "", "", "", "" };
		int telorder = 0;
		for (String tel : tels) {
			TelInfo telInfo = new TelInfo();
			telInfo.setCustomer_ID(87);
			telInfo.setTel(tel);
			telInfo.setTelorder(telorder++);
			telInfos.add(telInfo);
		}
		customerInfoForm.setTelInfos(telInfos);

		BindingResult result = new MapBindingResult(new HashMap<>(), "customerInfoForm");

		RedirectAttributes redirectAttributes = new RedirectAttributesModelMap();

		Map<String, Object> modelAttributes = new HashMap<>();
		when(model.addAttribute(any(String.class), any(Object.class))).thenAnswer(invocation -> {
			modelAttributes.put(invocation.getArgument(0), invocation.getArgument(1));
			return model;
		});

		try {
			String viewName = updateController.updateFunction(customerInfoForm, result, model, redirectAttributes);
			assertEquals("update", viewName);
			assertTrue(modelAttributes.containsKey("success"));
			assertEquals("顧客情報を更新しました。", modelAttributes.get("success"));
			assertEquals(customerInfoForm, modelAttributes.get("customer"));
			assertEquals(customerInfoForm, modelAttributes.get("customer"));
		} catch (Exception e) {
			fail();
		}
	}

	/*
	 * 項番62
	 */
	@Test
	public void updateFunction62() {

		CustomerInfoForm customerInfoForm = new CustomerInfoForm();
		customerInfoForm.setCustomer_ID(88);
		customerInfoForm.setCustomer_name("Test88");
		customerInfoForm.setEmail("test@88ca.co.jp");
		customerInfoForm.setGender("男");
		customerInfoForm.setAddress("石川県88");
		List<TelInfo> telInfos = new ArrayList<>();
		String[] tels = new String[] { "88112345678", "", "", "", "" };
		int telorder = 0;
		for (String tel : tels) {
			TelInfo telInfo = new TelInfo();
			telInfo.setCustomer_ID(88);
			telInfo.setTel(tel);
			telInfo.setTelorder(telorder++);
			telInfos.add(telInfo);
		}
		customerInfoForm.setTelInfos(telInfos);

		BindingResult result = new MapBindingResult(new HashMap<>(), "customerInfoForm");

		RedirectAttributes redirectAttributes = new RedirectAttributesModelMap();

		Map<String, Object> modelAttributes = new HashMap<>();
		when(model.addAttribute(any(String.class), any(Object.class))).thenAnswer(invocation -> {
			modelAttributes.put(invocation.getArgument(0), invocation.getArgument(1));
			return model;
		});

		try {
			String viewName = updateController.updateFunction(customerInfoForm, result, model, redirectAttributes);
			assertEquals("update", viewName);
			assertTrue(modelAttributes.containsKey("success"));
			assertEquals("顧客情報を更新しました。", modelAttributes.get("success"));
			assertEquals(customerInfoForm, modelAttributes.get("customer"));
		} catch (Exception e) {
			fail();
		}
	}

	/*
	 * 項番63
	 */
	@Test
	public void updateFunction63() {

		CustomerInfoForm customerInfoForm = new CustomerInfoForm();
		customerInfoForm.setCustomer_ID(89);
		customerInfoForm.setCustomer_name("Test89");
		customerInfoForm.setEmail("test@89ca.co.jp");
		customerInfoForm.setGender("男");
		customerInfoForm.setAddress("石川県89");
		List<TelInfo> telInfos = new ArrayList<>();
		String[] tels = new String[] { "89112345678", "89212345678", "", "", "" };
		int telorder = 0;
		for (String tel : tels) {
			TelInfo telInfo = new TelInfo();
			telInfo.setCustomer_ID(89);
			telInfo.setTel(tel);
			telInfo.setTelorder(telorder++);
			telInfos.add(telInfo);
		}
		customerInfoForm.setTelInfos(telInfos);

		BindingResult result = new MapBindingResult(new HashMap<>(), "customerInfoForm");

		RedirectAttributes redirectAttributes = new RedirectAttributesModelMap();

		Map<String, Object> modelAttributes = new HashMap<>();
		when(model.addAttribute(any(String.class), any(Object.class))).thenAnswer(invocation -> {
			modelAttributes.put(invocation.getArgument(0), invocation.getArgument(1));
			return model;
		});

		try {
			String viewName = updateController.updateFunction(customerInfoForm, result, model, redirectAttributes);
			assertEquals("update", viewName);
			assertTrue(modelAttributes.containsKey("success"));
			assertEquals("顧客情報を更新しました。", modelAttributes.get("success"));
			assertEquals(customerInfoForm, modelAttributes.get("customer"));
		} catch (Exception e) {
			fail();
		}
	}

	/*
	 * 項番64
	 */
	@Test
	public void updateFunction64() {

		CustomerInfoForm customerInfoForm = new CustomerInfoForm();
		customerInfoForm.setCustomer_ID(90);
		customerInfoForm.setCustomer_name("Test90");
		customerInfoForm.setEmail("test@90ca.co.jp");
		customerInfoForm.setGender("男");
		customerInfoForm.setAddress("石川県90");
		List<TelInfo> telInfos = new ArrayList<>();
		String[] tels = new String[] { "90112345678", "", "90312345678", "", "" };
		int telorder = 0;
		for (String tel : tels) {
			TelInfo telInfo = new TelInfo();
			telInfo.setCustomer_ID(90);
			telInfo.setTel(tel);
			telInfo.setTelorder(telorder++);
			telInfos.add(telInfo);
		}
		customerInfoForm.setTelInfos(telInfos);

		BindingResult result = new MapBindingResult(new HashMap<>(), "customerInfoForm");

		RedirectAttributes redirectAttributes = new RedirectAttributesModelMap();

		Map<String, Object> modelAttributes = new HashMap<>();
		when(model.addAttribute(any(String.class), any(Object.class))).thenAnswer(invocation -> {
			modelAttributes.put(invocation.getArgument(0), invocation.getArgument(1));
			return model;
		});

		try {
			String viewName = updateController.updateFunction(customerInfoForm, result, model, redirectAttributes);
			assertEquals("update", viewName);
			assertTrue(modelAttributes.containsKey("success"));
			assertEquals("顧客情報を更新しました。", modelAttributes.get("success"));
			assertEquals(customerInfoForm, modelAttributes.get("customer"));
		} catch (Exception e) {
			fail();
		}
	}

	/*
	 * 項番65
	 */
	@Test
	public void updateFunction65() {

		CustomerInfoForm customerInfoForm = new CustomerInfoForm();
		customerInfoForm.setCustomer_ID(91);
		customerInfoForm.setCustomer_name("Test91");
		customerInfoForm.setEmail("test@91ca.co.jp");
		customerInfoForm.setGender("男");
		customerInfoForm.setAddress("石川県91");
		List<TelInfo> telInfos = new ArrayList<>();
		String[] tels = new String[] { "91112345678", "", "", "91412345678", "" };
		int telorder = 0;
		for (String tel : tels) {
			TelInfo telInfo = new TelInfo();
			telInfo.setCustomer_ID(91);
			telInfo.setTel(tel);
			telInfo.setTelorder(telorder++);
			telInfos.add(telInfo);
		}
		customerInfoForm.setTelInfos(telInfos);

		BindingResult result = new MapBindingResult(new HashMap<>(), "customerInfoForm");

		RedirectAttributes redirectAttributes = new RedirectAttributesModelMap();

		Map<String, Object> modelAttributes = new HashMap<>();
		when(model.addAttribute(any(String.class), any(Object.class))).thenAnswer(invocation -> {
			modelAttributes.put(invocation.getArgument(0), invocation.getArgument(1));
			return model;
		});

		try {
			String viewName = updateController.updateFunction(customerInfoForm, result, model, redirectAttributes);
			assertEquals("update", viewName);
			assertTrue(modelAttributes.containsKey("success"));
			assertEquals("顧客情報を更新しました。", modelAttributes.get("success"));
			assertEquals(customerInfoForm, modelAttributes.get("customer"));
		} catch (Exception e) {
			fail();
		}
	}

	/*
	 * 項番66
	 */
	@Test
	public void updateFunction66() {

		CustomerInfoForm customerInfoForm = new CustomerInfoForm();
		customerInfoForm.setCustomer_ID(92);
		customerInfoForm.setCustomer_name("Test92");
		customerInfoForm.setEmail("test@92ca.co.jp");
		customerInfoForm.setGender("男");
		customerInfoForm.setAddress("石川県92");
		List<TelInfo> telInfos = new ArrayList<>();
		String[] tels = new String[] { "92112345678", "", "", "", "92512345678" };
		int telorder = 0;
		for (String tel : tels) {
			TelInfo telInfo = new TelInfo();
			telInfo.setCustomer_ID(92);
			telInfo.setTel(tel);
			telInfo.setTelorder(telorder++);
			telInfos.add(telInfo);
		}
		customerInfoForm.setTelInfos(telInfos);

		BindingResult result = new MapBindingResult(new HashMap<>(), "customerInfoForm");

		RedirectAttributes redirectAttributes = new RedirectAttributesModelMap();

		Map<String, Object> modelAttributes = new HashMap<>();
		when(model.addAttribute(any(String.class), any(Object.class))).thenAnswer(invocation -> {
			modelAttributes.put(invocation.getArgument(0), invocation.getArgument(1));
			return model;
		});

		try {
			String viewName = updateController.updateFunction(customerInfoForm, result, model, redirectAttributes);
			assertEquals("update", viewName);
			assertTrue(modelAttributes.containsKey("success"));
			assertEquals("顧客情報を更新しました。", modelAttributes.get("success"));
			assertEquals(customerInfoForm, modelAttributes.get("customer"));
		} catch (Exception e) {
		}
	}

	/*
	 * 項番67
	 */
	@Test
	public void updateFunction67() {

		CustomerInfoForm customerInfoForm = new CustomerInfoForm();
		customerInfoForm.setCustomer_ID(93);
		customerInfoForm.setCustomer_name("Test93");
		customerInfoForm.setEmail(
				"testabcdefghigklmnopqrstuvwxyzabcdefghigklmnopqrstuvwxyzabcdefghigklmnopqrstuvwxyzabcdefg@93ca.co.jp");
		customerInfoForm.setGender("男");
		customerInfoForm.setAddress("石川県93");
		List<TelInfo> telInfos = new ArrayList<>();
		String[] tels = new String[] { "93112345678", "", "", "", "" };
		int telorder = 0;
		for (String tel : tels) {
				TelInfo telInfo = new TelInfo();
				telInfo.setCustomer_ID(93);
				telInfo.setTel(tel);
				telInfo.setTelorder(telorder++);
				telInfos.add(telInfo);
		}
		customerInfoForm.setTelInfos(telInfos);

		BindingResult result = new MapBindingResult(new HashMap<>(), "customerInfoForm");

		RedirectAttributes redirectAttributes = new RedirectAttributesModelMap();

		Map<String, Object> modelAttributes = new HashMap<>();
		when(model.addAttribute(any(String.class), any(Object.class))).thenAnswer(invocation -> {
			modelAttributes.put(invocation.getArgument(0), invocation.getArgument(1));
			return model;
		});

		try {
			String viewName = updateController.updateFunction(customerInfoForm, result, model, redirectAttributes);
			assertEquals("update", viewName);
			assertTrue(modelAttributes.containsKey("success"));
			assertEquals("顧客情報を更新しました。", modelAttributes.get("success"));
			assertEquals(customerInfoForm, modelAttributes.get("customer"));
		} catch (Exception e) {
			fail();
		}
	}

	/*
	 * 項番68
	 */
	@Test
	public void updateFunction68() {

		CustomerInfoForm customerInfoForm = new CustomerInfoForm();
		customerInfoForm.setCustomer_ID(94);
		customerInfoForm.setCustomer_name("Test94");
		customerInfoForm.setEmail("test@94ca.co.jp");
		customerInfoForm.setGender("男");
		customerInfoForm.setAddress("石川県94あいうえおかきくけこさしすせそ");
		List<TelInfo> telInfos = new ArrayList<>();
		String[] tels = new String[] { "94112345678", "", "", "", "" };
		int telorder = 0;
		for (String tel : tels) {
				TelInfo telInfo = new TelInfo();
				telInfo.setCustomer_ID(94);
				telInfo.setTel(tel);
				telInfo.setTelorder(telorder++);
				telInfos.add(telInfo);
		}
		customerInfoForm.setTelInfos(telInfos);

		BindingResult result = new MapBindingResult(new HashMap<>(), "customerInfoForm");

		RedirectAttributes redirectAttributes = new RedirectAttributesModelMap();

		Map<String, Object> modelAttributes = new HashMap<>();
		when(model.addAttribute(any(String.class), any(Object.class))).thenAnswer(invocation -> {
			modelAttributes.put(invocation.getArgument(0), invocation.getArgument(1));
			return model;
		});

		try {
			String viewName = updateController.updateFunction(customerInfoForm, result, model, redirectAttributes);
			assertEquals("update", viewName);
			assertTrue(modelAttributes.containsKey("success"));
			assertEquals("顧客情報を更新しました。", modelAttributes.get("success"));
			assertEquals(customerInfoForm, modelAttributes.get("customer"));
		} catch (Exception e) {
			fail();
		}
	}

	/*
	 * 項番69
	 */
	@Test
	public void updateFunction69() {

		CustomerInfoForm customerInfoForm = new CustomerInfoForm();
		customerInfoForm.setCustomer_ID(52);
		customerInfoForm.setCustomer_name("");
		customerInfoForm.setEmail("test@52ca.co.jp");
		customerInfoForm.setGender("男");
		customerInfoForm.setAddress("千葉県52");
		List<TelInfo> telInfos = new ArrayList<>();
		String[] tels = new String[] { "52112345678", "", "", "", "" };
		int telorder = 0;
		for (String tel : tels) {
				TelInfo telInfo = new TelInfo();
				telInfo.setCustomer_ID(52);
				telInfo.setTel(tel);
				telInfo.setTelorder(telorder++);
				telInfos.add(telInfo);
		}
		customerInfoForm.setTelInfos(telInfos);

		BindingResult result = new MapBindingResult(new HashMap<>(), "customerInfoForm");

		RedirectAttributes redirectAttributes = new RedirectAttributesModelMap();

		Map<String, Object> modelAttributes = new HashMap<>();
		when(model.addAttribute(any(String.class), any(Object.class))).thenAnswer(invocation -> {
			modelAttributes.put(invocation.getArgument(0), invocation.getArgument(1));
			return model;
		});

		try {
			String viewName = updateController.updateFunction(customerInfoForm, result, model, redirectAttributes);
			assertEquals("update", viewName);
			assertTrue(modelAttributes.containsKey("error"));
			assertEquals("顧客名は入力必須です。", modelAttributes.get("error"));
			assertEquals(customerInfoForm, modelAttributes.get("customer"));
		} catch (Exception e) {
			fail();
		}

	}

	/*
	 * 項番70
	 */
	@Test
	public void updateFunction70() {

		CustomerInfoForm customerInfoForm = new CustomerInfoForm();
		customerInfoForm.setCustomer_ID(53);
		customerInfoForm.setCustomer_name("Test53");
		customerInfoForm.setEmail("test@53ca.co.jp");
		customerInfoForm.setGender("男");
		customerInfoForm.setAddress("東京都53");
		List<TelInfo> telInfos = new ArrayList<>();
		String[] tels = new String[] { " ", "", "", "", "" };
		int telorder = 0;
		for (String tel : tels) {
				TelInfo telInfo = new TelInfo();
				telInfo.setCustomer_ID(53);
				telInfo.setTel(tel);
				telInfo.setTelorder(telorder++);
				telInfos.add(telInfo);
		}
		customerInfoForm.setTelInfos(telInfos);

		BindingResult result = new MapBindingResult(new HashMap<>(), "customerInfoForm");

		RedirectAttributes redirectAttributes = new RedirectAttributesModelMap();

		Map<String, Object> modelAttributes = new HashMap<>();
		when(model.addAttribute(any(String.class), any(Object.class))).thenAnswer(invocation -> {
			modelAttributes.put(invocation.getArgument(0), invocation.getArgument(1));
			return model;
		});

		try {
			String viewName = updateController.updateFunction(customerInfoForm, result, model, redirectAttributes);
			assertEquals("update", viewName);
			assertTrue(modelAttributes.containsKey("error"));
			assertEquals("電話番号①は入力必須です。", modelAttributes.get("error"));
			assertEquals(customerInfoForm, modelAttributes.get("customer"));
		} catch (Exception e) {
			e.printStackTrace();
			fail(e.getMessage());
		}
	}

	/*
	 * 項番28
	 */
	@Test
	public void updateFunction71() {

		CustomerInfoForm customerInfoForm = new CustomerInfoForm();
		customerInfoForm.setCustomer_ID(54);
		customerInfoForm.setCustomer_name("Test54");
		customerInfoForm.setEmail("");
		customerInfoForm.setGender("男");
		customerInfoForm.setAddress("神奈川県54");
		List<TelInfo> telInfos = new ArrayList<>();
		String[] tels = new String[] { "54112345678", "", "", "", "" };
		int telorder = 0;
		for (String tel : tels) {
				TelInfo telInfo = new TelInfo();
				telInfo.setCustomer_ID(54);
				telInfo.setTel(tel);
				telInfo.setTelorder(telorder++);
				telInfos.add(telInfo);
		}
		customerInfoForm.setTelInfos(telInfos);

		BindingResult result = new MapBindingResult(new HashMap<>(), "customerInfoForm");

		RedirectAttributes redirectAttributes = new RedirectAttributesModelMap();

		Map<String, Object> modelAttributes = new HashMap<>();
		when(model.addAttribute(any(String.class), any(Object.class))).thenAnswer(invocation -> {
			modelAttributes.put(invocation.getArgument(0), invocation.getArgument(1));
			return model;
		});

		try {
			String viewName = updateController.updateFunction(customerInfoForm, result, model, redirectAttributes);
			assertEquals("update", viewName);
			assertTrue(modelAttributes.containsKey("error"));
			assertEquals("メールアドレスは入力必須です。", modelAttributes.get("error"));
			assertEquals(customerInfoForm, modelAttributes.get("customer"));
		} catch (Exception e) {
			fail();
		}
	}

	/*
	 * 項番72
	 */
	@Test
	public void updateFunction72() {

		CustomerInfoForm customerInfoForm = new CustomerInfoForm();
		customerInfoForm.setCustomer_ID(55);
		customerInfoForm.setCustomer_name("Test55");
		customerInfoForm.setEmail("test@55ca.co.jp");
		customerInfoForm.setGender("");
		customerInfoForm.setAddress("新潟県55");
		List<TelInfo> telInfos = new ArrayList<>();
		String[] tels = new String[] { "55112345678", "", "", "", "" };
		int telorder = 0;
		for (String tel : tels) {
				TelInfo telInfo = new TelInfo();
				telInfo.setCustomer_ID(55);
				telInfo.setTel(tel);
				telInfo.setTelorder(telorder++);
				telInfos.add(telInfo);
		}
		customerInfoForm.setTelInfos(telInfos);

		BindingResult result = new MapBindingResult(new HashMap<>(), "customerInfoForm");

		RedirectAttributes redirectAttributes = new RedirectAttributesModelMap();

		Map<String, Object> modelAttributes = new HashMap<>();
		when(model.addAttribute(any(String.class), any(Object.class))).thenAnswer(invocation -> {
			modelAttributes.put(invocation.getArgument(0), invocation.getArgument(1));
			return model;
		});

		try {
			String viewName = updateController.updateFunction(customerInfoForm, result, model, redirectAttributes);
			assertEquals("update", viewName);
			assertTrue(modelAttributes.containsKey("error"));
			assertEquals("性別は入力必須です。", modelAttributes.get("error"));
			assertEquals(customerInfoForm, modelAttributes.get("customer"));
		} catch (Exception e) {
			fail();
		}
	}

	/*
	 * 項番73
	 */
	@Test
	public void updateFunction73() {

		CustomerInfoForm customerInfoForm = new CustomerInfoForm();
		customerInfoForm.setCustomer_ID(56);
		customerInfoForm.setCustomer_name("Test56");
		customerInfoForm.setEmail("test@56ca.co.jp");
		customerInfoForm.setGender("男");
		customerInfoForm.setAddress("");
		List<TelInfo> telInfos = new ArrayList<>();
		String[] tels = new String[] { "56112345678", "", "", "", "" };
		int telorder = 0;
		for (String tel : tels) {
				TelInfo telInfo = new TelInfo();
				telInfo.setCustomer_ID(56);
				telInfo.setTel(tel);
				telInfo.setTelorder(telorder++);
				telInfos.add(telInfo);
		}
		customerInfoForm.setTelInfos(telInfos);

		BindingResult result = new MapBindingResult(new HashMap<>(), "customerInfoForm");

		RedirectAttributes redirectAttributes = new RedirectAttributesModelMap();

		Map<String, Object> modelAttributes = new HashMap<>();
		when(model.addAttribute(any(String.class), any(Object.class))).thenAnswer(invocation -> {
			modelAttributes.put(invocation.getArgument(0), invocation.getArgument(1));
			return model;
		});

		try {
			String viewName = updateController.updateFunction(customerInfoForm, result, model, redirectAttributes);
			assertEquals("update", viewName);
			assertTrue(modelAttributes.containsKey("error"));
			assertEquals("住所は入力必須です。", modelAttributes.get("error"));
			assertEquals(customerInfoForm, modelAttributes.get("customer"));
		} catch (Exception e) {
			fail();
		}
	}

	/*
	 * 項番74
	 */
	@Test
	public void updateFunction74() {

		CustomerInfoForm customerInfoForm = new CustomerInfoForm();
		customerInfoForm.setCustomer_ID(57);
		customerInfoForm.setCustomer_name("");
		customerInfoForm.setEmail("");
		customerInfoForm.setGender("");
		customerInfoForm.setAddress("");
		List<TelInfo> telInfos = new ArrayList<>();
		String[] tels = new String[] { " ", "", "", "", "" };
		int telorder = 0;
		for (String tel : tels) {
				TelInfo telInfo = new TelInfo();
				telInfo.setCustomer_ID(57);
				telInfo.setTel(tel);
				telInfo.setTelorder(telorder++);
				telInfos.add(telInfo);
		}
		customerInfoForm.setTelInfos(telInfos);

		BindingResult result = new MapBindingResult(new HashMap<>(), "customerInfoForm");

		RedirectAttributes redirectAttributes = new RedirectAttributesModelMap();

		Map<String, Object> modelAttributes = new HashMap<>();
		when(model.addAttribute(any(String.class), any(Object.class))).thenAnswer(invocation -> {
			modelAttributes.put(invocation.getArgument(0), invocation.getArgument(1));
			return model;
		});

		try {
			String viewName = updateController.updateFunction(customerInfoForm, result, model, redirectAttributes);
			assertEquals("update", viewName);
			assertTrue(modelAttributes.containsKey("error"));
			assertEquals("顧客名は入力必須です。<br>"
					+ "電話番号①は入力必須です。<br>"
					+ "メールアドレスは入力必須です。<br>"
					+ "性別は入力必須です。<br>"
					+ "住所は入力必須です。", modelAttributes.get("error"));
			assertEquals(customerInfoForm, modelAttributes.get("customer"));

		} catch (Exception e) {
			fail();
		}
	}

	/*
	 * 項番75
	 */
	@Test
	public void updateFunction75() {

		CustomerInfoForm customerInfoForm = new CustomerInfoForm();
		customerInfoForm.setCustomer_ID(58);
		customerInfoForm.setCustomer_name("Test12345678901234567");
		customerInfoForm.setEmail("test@58ca.co.jp");
		customerInfoForm.setGender("男");
		customerInfoForm.setAddress("福井県58");
		List<TelInfo> telInfos = new ArrayList<>();
		String[] tels = new String[] { "58112345678", "", "", "", "" };
		int telorder = 0;
		for (String tel : tels) {
				TelInfo telInfo = new TelInfo();
				telInfo.setCustomer_ID(58);
				telInfo.setTel(tel);
				telInfo.setTelorder(telorder++);
				telInfos.add(telInfo);
		}
		customerInfoForm.setTelInfos(telInfos);

		MapBindingResult result = new MapBindingResult(new HashMap<>(), "customerInfoForm");

		result.addError(new FieldError("customerInfoForm", "customer_name", "顧客名は20文字以内で入力してください。"));

		RedirectAttributes redirectAttributes = new RedirectAttributesModelMap();

		Map<String, Object> modelAttributes = new HashMap<>();
		when(model.addAttribute(any(String.class), any(Object.class))).thenAnswer(invocation -> {
			modelAttributes.put(invocation.getArgument(0), invocation.getArgument(1));
			return model;
		});

		try {
			String viewName = updateController.updateFunction(customerInfoForm, result, model, redirectAttributes);
			assertEquals("update", viewName);
			assertTrue(modelAttributes.containsKey("error"));
			assertEquals("顧客名は20文字以内で入力してください。", modelAttributes.get("error"));
			assertEquals(customerInfoForm, modelAttributes.get("customer"));

		} catch (Exception e) {
			fail();
		}
	}

	/*
	 * 項番76
	 */
	@Test
	public void updateFunction76() {

		CustomerInfoForm customerInfoForm = new CustomerInfoForm();
		customerInfoForm.setCustomer_ID(59);
		customerInfoForm.setCustomer_name("Test59");
		customerInfoForm.setEmail(
				"testabcdefghigklmnopqrstuvwxyzabcdefghigklmnopqrstuvwxyzabcdefghigklmnopqrstuvwxyzabcdefgh@59ca.co.jp");
		customerInfoForm.setGender("男");
		customerInfoForm.setAddress("山梨県59");
		List<TelInfo> telInfos = new ArrayList<>();
		String[] tels = new String[] { "59112345678", "", "", "", "" };
		int telorder = 0;
		for (String tel : tels) {
				TelInfo telInfo = new TelInfo();
				telInfo.setCustomer_ID(59);
				telInfo.setTel(tel);
				telInfo.setTelorder(telorder++);
				telInfos.add(telInfo);
		}
		customerInfoForm.setTelInfos(telInfos);

		BindingResult result = new MapBindingResult(new HashMap<>(), "customerInfoForm");

		result.addError(new FieldError("customerInfoForm", "email", "メールは100文字以内で入力してください。"));

		RedirectAttributes redirectAttributes = new RedirectAttributesModelMap();

		Map<String, Object> modelAttributes = new HashMap<>();
		when(model.addAttribute(any(String.class), any(Object.class))).thenAnswer(invocation -> {
			modelAttributes.put(invocation.getArgument(0), invocation.getArgument(1));
			return model;
		});

		try {
			String viewName = updateController.updateFunction(customerInfoForm, result, model, redirectAttributes);
			assertEquals("update", viewName);
			assertTrue(modelAttributes.containsKey("error"));
			assertEquals("メールアドレスは100文字以内で入力してください。", modelAttributes.get("error"));
			assertEquals(customerInfoForm, modelAttributes.get("customer"));

		} catch (Exception e) {
			fail();
		}
	}

	/*
	 * 項番34
	 */
	@Test
	public void updateFunction77() {

		CustomerInfoForm customerInfoForm = new CustomerInfoForm();
		customerInfoForm.setCustomer_ID(60);
		customerInfoForm.setCustomer_name("Test60");
		customerInfoForm.setEmail("test@60ca.co.jp");
		customerInfoForm.setGender("男男");
		customerInfoForm.setAddress("長野県60");
		List<TelInfo> telInfos = new ArrayList<>();
		String[] tels = new String[] { "60112345678", "", "", "", "" };
		int telorder = 0;
		for (String tel : tels) {
				TelInfo telInfo = new TelInfo();
				telInfo.setCustomer_ID(60);
				telInfo.setTel(tel);
				telInfo.setTelorder(telorder++);
				telInfos.add(telInfo);
		}
		customerInfoForm.setTelInfos(telInfos);

		BindingResult result = new MapBindingResult(new HashMap<>(), "customerInfoForm");

		result.addError(new FieldError("customerInfoForm", "gender", "性別はブラウザからボタンで入力してください。"));

		RedirectAttributes redirectAttributes = new RedirectAttributesModelMap();

		Map<String, Object> modelAttributes = new HashMap<>();
		when(model.addAttribute(any(String.class), any(Object.class))).thenAnswer(invocation -> {
			modelAttributes.put(invocation.getArgument(0), invocation.getArgument(1));
			return model;
		});

		try {
			String viewName = updateController.updateFunction(customerInfoForm, result, model, redirectAttributes);
			assertEquals("update", viewName);
			assertTrue(modelAttributes.containsKey("error"));
			assertEquals("性別はブラウザからボタンで入力してください。", modelAttributes.get("error"));
			assertEquals(customerInfoForm, modelAttributes.get("customer"));

		} catch (Exception e) {
			fail();
		}
	}

	/*
	 * 項番78
	 */
	@Test
	public void updateFunction78() {

		CustomerInfoForm customerInfoForm = new CustomerInfoForm();
		customerInfoForm.setCustomer_ID(61);
		customerInfoForm.setCustomer_name("Test61");
		customerInfoForm.setEmail("test@61ca.co.jp");
		customerInfoForm.setGender("男");
		customerInfoForm.setAddress("岐阜県61あいうえおかきくけこさしすせそた");
		customerInfoForm.setTel(new String[] { "61112345678", "", "", "", "" });
		List<TelInfo> telInfos = new ArrayList<>();
		String[] tels = new String[] { "61112345678", "", "", "", "" };
		int telorder = 0;
		for (String tel : tels) {
				TelInfo telInfo = new TelInfo();
				telInfo.setCustomer_ID(61);
				telInfo.setTel(tel);
				telInfo.setTelorder(telorder++);
				telInfos.add(telInfo);
		}
		customerInfoForm.setTelInfos(telInfos);

		BindingResult result = new MapBindingResult(new HashMap<>(), "customerInfoForm");

		result.addError(new FieldError("customerInfoForm", "address", "住所は20文字以内で入力してください。"));

		RedirectAttributes redirectAttributes = new RedirectAttributesModelMap();

		Map<String, Object> modelAttributes = new HashMap<>();
		when(model.addAttribute(any(String.class), any(Object.class))).thenAnswer(invocation -> {
			modelAttributes.put(invocation.getArgument(0), invocation.getArgument(1));
			return model;
		});

		try {
			String viewName = updateController.updateFunction(customerInfoForm, result, model, redirectAttributes);
			assertEquals("update", viewName);
			assertTrue(modelAttributes.containsKey("error"));
			assertEquals("住所は20文字以内で入力してください。", modelAttributes.get("error"));
			assertEquals(customerInfoForm, modelAttributes.get("customer"));

		} catch (Exception e) {
			fail();
		}
	}

	/*
	 * 項番79
	 */
	@Test
	public void updateFunction79() {

		CustomerInfoForm customerInfoForm = new CustomerInfoForm();
		customerInfoForm.setCustomer_ID(62);
		customerInfoForm.setCustomer_name("Test62");
		customerInfoForm.setEmail("test@62ca.co.jp");
		customerInfoForm.setGender("男");
		customerInfoForm.setAddress("静岡県62");
		List<TelInfo> telInfos = new ArrayList<>();
		String[] tels = new String[] { "6211234567あ", "", "", "", "" };
		int telorder = 0;
		for (String tel : tels) {
				TelInfo telInfo = new TelInfo();
				telInfo.setCustomer_ID(62);
				telInfo.setTel(tel);
				telInfo.setTelorder(telorder++);
				telInfos.add(telInfo);
			}
		customerInfoForm.setTelInfos(telInfos);

		BindingResult result = new MapBindingResult(new HashMap<>(), "customerInfoForm");

		RedirectAttributes redirectAttributes = new RedirectAttributesModelMap();

		Map<String, Object> modelAttributes = new HashMap<>();
		when(model.addAttribute(any(String.class), any(Object.class))).thenAnswer(invocation -> {
			modelAttributes.put(invocation.getArgument(0), invocation.getArgument(1));
			return model;
		});

		try {
			String viewName = updateController.updateFunction(customerInfoForm, result, model, redirectAttributes);
			assertEquals("update", viewName);
			assertTrue(modelAttributes.containsKey("error"));
			assertEquals("電話番号①は数字のみで11文字以内で入力してください。", modelAttributes.get("error"));
			assertEquals(customerInfoForm, modelAttributes.get("customer"));

		} catch (Exception e) {
			fail();
		}
	}

	/*
	 * 項番80
	 */
	@Test
	public void updateFunction80() {

		CustomerInfoForm customerInfoForm = new CustomerInfoForm();
		customerInfoForm.setCustomer_ID(63);
		customerInfoForm.setCustomer_name("Test63");
		customerInfoForm.setEmail("test@63ca.co.jp");
		customerInfoForm.setGender("男");
		customerInfoForm.setAddress("愛知県63");
		List<TelInfo> telInfos = new ArrayList<>();
		String[] tels = new String[] { "6311234567あ", "", "", "", "" };
		int telorder = 0;
		for (String tel : tels) {
				TelInfo telInfo = new TelInfo();
				telInfo.setCustomer_ID(63);
				telInfo.setTel(tel);
				telInfo.setTelorder(telorder++);
				telInfos.add(telInfo);
		}
		customerInfoForm.setTelInfos(telInfos);

		BindingResult result = new MapBindingResult(new HashMap<>(), "customerInfoForm");

		RedirectAttributes redirectAttributes = new RedirectAttributesModelMap();

		Map<String, Object> modelAttributes = new HashMap<>();
		when(model.addAttribute(any(String.class), any(Object.class))).thenAnswer(invocation -> {
			modelAttributes.put(invocation.getArgument(0), invocation.getArgument(1));
			return model;
		});

		try {
			String viewName = updateController.updateFunction(customerInfoForm, result, model, redirectAttributes);
			assertEquals("update", viewName);
			assertTrue(modelAttributes.containsKey("error"));
			assertEquals("電話番号①は数字のみで11文字以内で入力してください。", modelAttributes.get("error"));
			assertEquals(customerInfoForm, modelAttributes.get("customer"));

		} catch (Exception e) {
			fail();
		}
	}

	/*
	 * 項番81
	 */
	@Test
	public void updateFunction81() {

		CustomerInfoForm customerInfoForm = new CustomerInfoForm();
		customerInfoForm.setCustomer_ID(64);
		customerInfoForm.setCustomer_name("Test64");
		customerInfoForm.setEmail("test@64ca.co.jp");
		customerInfoForm.setGender("男");
		customerInfoForm.setAddress("三重県64");
		List<TelInfo> telInfos = new ArrayList<>();
		String[] tels = new String[] { "64112345678あ", "", "", "", "" };
		int telorder = 0;
		for (String tel : tels) {
				TelInfo telInfo = new TelInfo();
				telInfo.setCustomer_ID(64);
				telInfo.setTel(tel);
				telInfo.setTelorder(telorder++);
				telInfos.add(telInfo);
			}
		customerInfoForm.setTelInfos(telInfos);

		BindingResult result = new MapBindingResult(new HashMap<>(), "customerInfoForm");

		RedirectAttributes redirectAttributes = new RedirectAttributesModelMap();

		Map<String, Object> modelAttributes = new HashMap<>();
		when(model.addAttribute(any(String.class), any(Object.class))).thenAnswer(invocation -> {
			modelAttributes.put(invocation.getArgument(0), invocation.getArgument(1));
			return model;
		});

		try {
			String viewName = updateController.updateFunction(customerInfoForm, result, model, redirectAttributes);
			assertEquals("update", viewName);
			assertTrue(modelAttributes.containsKey("error"));
			assertEquals("電話番号①は数字のみで11文字以内で入力してください。", modelAttributes.get("error"));
			assertEquals(customerInfoForm, modelAttributes.get("customer"));

		} catch (Exception e) {
			fail();
		}
	}

	/*
	 * 項番82
	 */
	@Test
	public void updateFunction82() {

		CustomerInfoForm customerInfoForm = new CustomerInfoForm();
		customerInfoForm.setCustomer_ID(65);
		customerInfoForm.setCustomer_name("Test65");
		customerInfoForm.setEmail("test@65ca.co.jp");
		customerInfoForm.setGender("男");
		customerInfoForm.setAddress("滋賀県65");
		List<TelInfo> telInfos = new ArrayList<>();
		String[] tels = new String[] { "65112345678", "6521234567あ", "", "", "" };
		int telorder = 0;
		for (String tel : tels) {
			TelInfo telInfo = new TelInfo();
			telInfo.setCustomer_ID(65);
			telInfo.setTel(tel);
			telInfo.setTelorder(telorder++);
			telInfos.add(telInfo);
		}
		customerInfoForm.setTelInfos(telInfos);

		BindingResult result = new MapBindingResult(new HashMap<>(), "customerInfoForm");

		RedirectAttributes redirectAttributes = new RedirectAttributesModelMap();

		Map<String, Object> modelAttributes = new HashMap<>();
		when(model.addAttribute(any(String.class), any(Object.class))).thenAnswer(invocation -> {
			modelAttributes.put(invocation.getArgument(0), invocation.getArgument(1));
			return model;
		});

		try {
			String viewName = updateController.updateFunction(customerInfoForm, result, model, redirectAttributes);
			assertEquals("update", viewName);
			assertTrue(modelAttributes.containsKey("error"));
			assertEquals("電話番号②は数字のみで11文字以内で入力してください。", modelAttributes.get("error"));
			assertEquals(customerInfoForm, modelAttributes.get("customer"));

		} catch (Exception e) {
			fail();
		}
	}

	/*
	 * 項番83
	 */
	@Test
	public void updateFunction83() {

		CustomerInfoForm customerInfoForm = new CustomerInfoForm();
		customerInfoForm.setCustomer_ID(66);
		customerInfoForm.setCustomer_name("Test66");
		customerInfoForm.setEmail("test@66ca.co.jp");
		customerInfoForm.setGender("男");
		customerInfoForm.setAddress("京都府66");
		List<TelInfo> telInfos = new ArrayList<>();
		String[] tels = new String[] { "66112345678", "662123456789", "", "", "" };
		int telorder = 0;
		for (String tel : tels) {
			TelInfo telInfo = new TelInfo();
			telInfo.setCustomer_ID(66);
			telInfo.setTel(tel);
			telInfo.setTelorder(telorder++);
			telInfos.add(telInfo);
		}
		customerInfoForm.setTelInfos(telInfos);

		BindingResult result = new MapBindingResult(new HashMap<>(), "customerInfoForm");

		RedirectAttributes redirectAttributes = new RedirectAttributesModelMap();

		Map<String, Object> modelAttributes = new HashMap<>();
		when(model.addAttribute(any(String.class), any(Object.class))).thenAnswer(invocation -> {
			modelAttributes.put(invocation.getArgument(0), invocation.getArgument(1));
			return model;
		});

		try {
			String viewName = updateController.updateFunction(customerInfoForm, result, model, redirectAttributes);
			assertEquals("update", viewName);
			assertTrue(modelAttributes.containsKey("error"));
			assertEquals("電話番号②は数字のみで11文字以内で入力してください。", modelAttributes.get("error"));
			assertEquals(customerInfoForm, modelAttributes.get("customer"));

		} catch (Exception e) {
			fail();
		}
	}

	/*
	 * 項番84
	 */
	@Test
	public void updateFunction84() {

		CustomerInfoForm customerInfoForm = new CustomerInfoForm();
		customerInfoForm.setCustomer_ID(67);
		customerInfoForm.setCustomer_name("Test67");
		customerInfoForm.setEmail("test@67ca.co.jp");
		customerInfoForm.setGender("男");
		customerInfoForm.setAddress("兵庫県");
		List<TelInfo> telInfos = new ArrayList<>();
		String[] tels = new String[] { "67112345678", "67212345678あ", "", "", "" };
		int telorder = 0;
		for (String tel : tels) {
			TelInfo telInfo = new TelInfo();
			telInfo.setCustomer_ID(67);
			telInfo.setTel(tel);
			telInfo.setTelorder(telorder++);
			telInfos.add(telInfo);
		}
		customerInfoForm.setTelInfos(telInfos);

		BindingResult result = new MapBindingResult(new HashMap<>(), "customerInfoForm");

		RedirectAttributes redirectAttributes = new RedirectAttributesModelMap();

		Map<String, Object> modelAttributes = new HashMap<>();
		when(model.addAttribute(any(String.class), any(Object.class))).thenAnswer(invocation -> {
			modelAttributes.put(invocation.getArgument(0), invocation.getArgument(1));
			return model;
		});

		try {
			String viewName = updateController.updateFunction(customerInfoForm, result, model, redirectAttributes);
			assertEquals("update", viewName);
			assertTrue(modelAttributes.containsKey("error"));
			assertEquals("電話番号②は数字のみで11文字以内で入力してください。", modelAttributes.get("error"));
			assertEquals(customerInfoForm, modelAttributes.get("customer"));

		} catch (Exception e) {
			fail();
		}
	}

	/*
	 * 項番85
	 */
	@Test
	public void updateFunction85() {

		CustomerInfoForm customerInfoForm = new CustomerInfoForm();
		customerInfoForm.setCustomer_ID(68);
		customerInfoForm.setCustomer_name("Test68");
		customerInfoForm.setEmail("test@68ca.co.jp");
		customerInfoForm.setGender("男");
		customerInfoForm.setAddress("兵庫県68");
		List<TelInfo> telInfos = new ArrayList<>();
		String[] tels = new String[] { "68112345678", "", "6831234567あ", "", "" };
		int telorder = 0;
		for (String tel : tels) {
			TelInfo telInfo = new TelInfo();
			telInfo.setCustomer_ID(68);
			telInfo.setTel(tel);
			telInfo.setTelorder(telorder++);
			telInfos.add(telInfo);
		}
		customerInfoForm.setTelInfos(telInfos);

		BindingResult result = new MapBindingResult(new HashMap<>(), "customerInfoForm");

		RedirectAttributes redirectAttributes = new RedirectAttributesModelMap();

		Map<String, Object> modelAttributes = new HashMap<>();
		when(model.addAttribute(any(String.class), any(Object.class))).thenAnswer(invocation -> {
			modelAttributes.put(invocation.getArgument(0), invocation.getArgument(1));
			return model;
		});

		try {
			String viewName = updateController.updateFunction(customerInfoForm, result, model, redirectAttributes);
			assertEquals("update", viewName);
			assertTrue(modelAttributes.containsKey("error"));
			assertEquals("電話番号③は数字のみで11文字以内で入力してください。", modelAttributes.get("error"));
			assertEquals(customerInfoForm, modelAttributes.get("customer"));

		} catch (Exception e) {
			fail();
		}
	}

	/*
	 * 項番86
	 */
	@Test
	public void updateFunction86() {

		CustomerInfoForm customerInfoForm = new CustomerInfoForm();
		customerInfoForm.setCustomer_ID(69);
		customerInfoForm.setCustomer_name("Test69");
		customerInfoForm.setEmail("test@69ca.co.jp");
		customerInfoForm.setGender("男");
		customerInfoForm.setAddress("山口県69");
		List<TelInfo> telInfos = new ArrayList<>();
		String[] tels = new String[] { "69112345678", "", "693123456789", "", "" };
		int telorder = 0;
		for (String tel : tels) {
			TelInfo telInfo = new TelInfo();
			telInfo.setCustomer_ID(69);
			telInfo.setTel(tel);
			telInfo.setTelorder(telorder++);
			telInfos.add(telInfo);
		}
		customerInfoForm.setTelInfos(telInfos);

		BindingResult result = new MapBindingResult(new HashMap<>(), "customerInfoForm");

		RedirectAttributes redirectAttributes = new RedirectAttributesModelMap();

		Map<String, Object> modelAttributes = new HashMap<>();
		when(model.addAttribute(any(String.class), any(Object.class))).thenAnswer(invocation -> {
			modelAttributes.put(invocation.getArgument(0), invocation.getArgument(1));
			return model;
		});

		try {
			String viewName = updateController.updateFunction(customerInfoForm, result, model, redirectAttributes);
			assertEquals("update", viewName);
			assertTrue(modelAttributes.containsKey("error"));
			assertEquals("電話番号③は数字のみで11文字以内で入力してください。", modelAttributes.get("error"));
			assertEquals(customerInfoForm, modelAttributes.get("customer"));

		} catch (Exception e) {
			fail();
		}
	}

	/*
	 * 項番87
	 */
	@Test
	public void updateFunction87() {

		CustomerInfoForm customerInfoForm = new CustomerInfoForm();
		customerInfoForm.setCustomer_ID(70);
		customerInfoForm.setCustomer_name("Test70");
		customerInfoForm.setEmail("test@70ca.co.jp");
		customerInfoForm.setGender("男");
		customerInfoForm.setAddress("和歌山県70");
		List<TelInfo> telInfos = new ArrayList<>();
		String[] tels = new String[] { "70112345678", "", "70312345678あ", "", "" };
		int telorder = 0;
		for (String tel : tels) {
			TelInfo telInfo = new TelInfo();
			telInfo.setCustomer_ID(70);
			telInfo.setTel(tel);
			telInfo.setTelorder(telorder++);
			telInfos.add(telInfo);
		}
		customerInfoForm.setTelInfos(telInfos);

		BindingResult result = new MapBindingResult(new HashMap<>(), "customerInfoForm");

		RedirectAttributes redirectAttributes = new RedirectAttributesModelMap();

		Map<String, Object> modelAttributes = new HashMap<>();
		when(model.addAttribute(any(String.class), any(Object.class))).thenAnswer(invocation -> {
			modelAttributes.put(invocation.getArgument(0), invocation.getArgument(1));
			return model;
		});

		try {
			String viewName = updateController.updateFunction(customerInfoForm, result, model, redirectAttributes);
			assertEquals("update", viewName);
			assertTrue(modelAttributes.containsKey("error"));
			assertEquals("電話番号③は数字のみで11文字以内で入力してください。", modelAttributes.get("error"));
			assertEquals(customerInfoForm, modelAttributes.get("customer"));

		} catch (Exception e) {
			fail();
		}
	}

	/*
	 * 項番88
	 */
	@Test
	public void updateFunction88() {

		CustomerInfoForm customerInfoForm = new CustomerInfoForm();
		customerInfoForm.setCustomer_ID(71);
		customerInfoForm.setCustomer_name("Test71");
		customerInfoForm.setEmail("test@71ca.co.jp");
		customerInfoForm.setGender("男");
		customerInfoForm.setAddress("鳥取県71");
		List<TelInfo> telInfos = new ArrayList<>();
		String[] tels = new String[] { "71112345678", "", "", "7141234567あ", "" };
		int telorder = 0;
		for (String tel : tels) {
			TelInfo telInfo = new TelInfo();
			telInfo.setCustomer_ID(71);
			telInfo.setTel(tel);
			telInfo.setTelorder(telorder++);
			telInfos.add(telInfo);
		}

		customerInfoForm.setTelInfos(telInfos);

		BindingResult result = new MapBindingResult(new HashMap<>(), "customerInfoForm");

		RedirectAttributes redirectAttributes = new RedirectAttributesModelMap();

		Map<String, Object> modelAttributes = new HashMap<>();
		when(model.addAttribute(any(String.class), any(Object.class))).thenAnswer(invocation -> {
			modelAttributes.put(invocation.getArgument(0), invocation.getArgument(1));
			return model;
		});

		try {
			String viewName = updateController.updateFunction(customerInfoForm, result, model, redirectAttributes);
			assertEquals("update", viewName);
			assertTrue(modelAttributes.containsKey("error"));
			assertEquals("電話番号④は数字のみで11文字以内で入力してください。", modelAttributes.get("error"));
			assertEquals(customerInfoForm, modelAttributes.get("customer"));

		} catch (Exception e) {
			fail();
		}
	}

	/*
	 * 項番89
	 */
	@Test
	public void updateFunction89() {

		CustomerInfoForm customerInfoForm = new CustomerInfoForm();
		customerInfoForm.setCustomer_ID(72);
		customerInfoForm.setCustomer_name("Test72");
		customerInfoForm.setEmail("test@72ca.co.jp");
		customerInfoForm.setGender("男");
		customerInfoForm.setAddress("島根県72");
		List<TelInfo> telInfos = new ArrayList<>();
		String[] tels = new String[] { "72112345678", "", "", "724123456789", "" };
		int telorder = 0;
		for (String tel : tels) {
			TelInfo telInfo = new TelInfo();
			telInfo.setCustomer_ID(72);
			telInfo.setTel(tel);
			telInfo.setTelorder(telorder++);
			telInfos.add(telInfo);
		}

		customerInfoForm.setTelInfos(telInfos);

		BindingResult result = new MapBindingResult(new HashMap<>(), "customerInfoForm");

		RedirectAttributes redirectAttributes = new RedirectAttributesModelMap();

		Map<String, Object> modelAttributes = new HashMap<>();
		when(model.addAttribute(any(String.class), any(Object.class))).thenAnswer(invocation -> {
			modelAttributes.put(invocation.getArgument(0), invocation.getArgument(1));
			return model;
		});

		try {
			String viewName = updateController.updateFunction(customerInfoForm, result, model, redirectAttributes);
			assertEquals("update", viewName);
			assertTrue(modelAttributes.containsKey("error"));
			assertEquals("電話番号④は数字のみで11文字以内で入力してください。", modelAttributes.get("error"));
			assertEquals(customerInfoForm, modelAttributes.get("customer"));

		} catch (Exception e) {
			fail();
		}
	}

	/*
	 * 項番90
	 */
	@Test
	public void updateFunction90() {

		CustomerInfoForm customerInfoForm = new CustomerInfoForm();
		customerInfoForm.setCustomer_ID(73);
		customerInfoForm.setCustomer_name("Test73");
		customerInfoForm.setEmail("test@73ca.co.jp");
		customerInfoForm.setGender("男");
		customerInfoForm.setAddress("岡山県73");
		List<TelInfo> telInfos = new ArrayList<>();
		String[] tels = new String[] { "73112345678", "", "", "73412345678あ", "" };
		int telorder = 0;
		for (String tel : tels) {
			TelInfo telInfo = new TelInfo();
			telInfo.setCustomer_ID(73);
			telInfo.setTel(tel);
			telInfo.setTelorder(telorder++);
			telInfos.add(telInfo);
		}

		customerInfoForm.setTelInfos(telInfos);

		BindingResult result = new MapBindingResult(new HashMap<>(), "customerInfoForm");

		RedirectAttributes redirectAttributes = new RedirectAttributesModelMap();

		Map<String, Object> modelAttributes = new HashMap<>();
		when(model.addAttribute(any(String.class), any(Object.class))).thenAnswer(invocation -> {
			modelAttributes.put(invocation.getArgument(0), invocation.getArgument(1));
			return model;
		});

		try {
			String viewName = updateController.updateFunction(customerInfoForm, result, model, redirectAttributes);
			assertEquals("update", viewName);
			assertTrue(modelAttributes.containsKey("error"));
			assertEquals("電話番号④は数字のみで11文字以内で入力してください。", modelAttributes.get("error"));
			assertEquals(customerInfoForm, modelAttributes.get("customer"));

		} catch (Exception e) {
			fail();
		}
	}

	/*
	 * 項番91
	 */
	@Test
	public void updateFunction91() {

		CustomerInfoForm customerInfoForm = new CustomerInfoForm();
		customerInfoForm.setCustomer_ID(74);
		customerInfoForm.setCustomer_name("Test74");
		customerInfoForm.setEmail("test@74ca.co.jp");
		customerInfoForm.setGender("男");
		customerInfoForm.setAddress("広島県74");
		List<TelInfo> telInfos = new ArrayList<>();
		String[] tels = new String[] { "74112345678", "", "", "", "7451234567あ" };
		int telorder = 0;
		for (String tel : tels) {
			TelInfo telInfo = new TelInfo();
			telInfo.setCustomer_ID(74);
			telInfo.setTel(tel);
			telInfo.setTelorder(telorder++);
			telInfos.add(telInfo);
		}

		customerInfoForm.setTelInfos(telInfos);

		BindingResult result = new MapBindingResult(new HashMap<>(), "customerInfoForm");

		RedirectAttributes redirectAttributes = new RedirectAttributesModelMap();

		Map<String, Object> modelAttributes = new HashMap<>();
		when(model.addAttribute(any(String.class), any(Object.class))).thenAnswer(invocation -> {
			modelAttributes.put(invocation.getArgument(0), invocation.getArgument(1));
			return model;
		});

		try {
			String viewName = updateController.updateFunction(customerInfoForm, result, model, redirectAttributes);
			assertEquals("update", viewName);
			assertTrue(modelAttributes.containsKey("error"));
			assertEquals("電話番号⑤は数字のみで11文字以内で入力してください。", modelAttributes.get("error"));
			assertEquals(customerInfoForm, modelAttributes.get("customer"));

		} catch (Exception e) {
			fail();
		}
	}

	/*
	 * 項番92
	 */
	@Test
	public void updateFunction92() {

		CustomerInfoForm customerInfoForm = new CustomerInfoForm();
		customerInfoForm.setCustomer_ID(75);
		customerInfoForm.setCustomer_name("Test75");
		customerInfoForm.setEmail("test@75ca.co.jp");
		customerInfoForm.setGender("男");
		customerInfoForm.setAddress("山口県75");
		List<TelInfo> telInfos = new ArrayList<>();
		String[] tels = new String[] { "75112345678", "", "", "", "755123456789" };
		int telorder = 0;
		for (String tel : tels) {
			TelInfo telInfo = new TelInfo();
			telInfo.setCustomer_ID(75);
			telInfo.setTel(tel);
			telInfo.setTelorder(telorder++);
			telInfos.add(telInfo);
		}

		customerInfoForm.setTelInfos(telInfos);

		BindingResult result = new MapBindingResult(new HashMap<>(), "customerInfoForm");

		RedirectAttributes redirectAttributes = new RedirectAttributesModelMap();

		Map<String, Object> modelAttributes = new HashMap<>();
		when(model.addAttribute(any(String.class), any(Object.class))).thenAnswer(invocation -> {
			modelAttributes.put(invocation.getArgument(0), invocation.getArgument(1));
			return model;
		});

		try {
			String viewName = updateController.updateFunction(customerInfoForm, result, model, redirectAttributes);
			assertEquals("update", viewName);
			assertTrue(modelAttributes.containsKey("error"));
			assertEquals("電話番号⑤は数字のみで11文字以内で入力してください。", modelAttributes.get("error"));
			assertEquals(customerInfoForm, modelAttributes.get("customer"));

		} catch (Exception e) {
			fail();
		}
	}

	/*	
	 * 項番93
	 */
	@Test
	public void updateFunction93() {

		CustomerInfoForm customerInfoForm = new CustomerInfoForm();
		customerInfoForm.setCustomer_ID(76);
		customerInfoForm.setCustomer_name("Test76");
		customerInfoForm.setEmail("test@76ca.co.jp");
		customerInfoForm.setGender("男");
		customerInfoForm.setAddress("徳島県76");
		List<TelInfo> telInfos = new ArrayList<>();
		String[] tels = new String[] { "76112345678", "", "", "", "76512345678あ" };
		int telorder = 0;
		for (String tel : tels) {
			TelInfo telInfo = new TelInfo();
			telInfo.setCustomer_ID(76);
			telInfo.setTel(tel);
			telInfo.setTelorder(telorder++);
			telInfos.add(telInfo);
		}

		customerInfoForm.setTelInfos(telInfos);

		BindingResult result = new MapBindingResult(new HashMap<>(), "customerInfoForm");

		RedirectAttributes redirectAttributes = new RedirectAttributesModelMap();

		Map<String, Object> modelAttributes = new HashMap<>();
		when(model.addAttribute(any(String.class), any(Object.class))).thenAnswer(invocation -> {
			modelAttributes.put(invocation.getArgument(0), invocation.getArgument(1));
			return model;
		});

		try {
			String viewName = updateController.updateFunction(customerInfoForm, result, model, redirectAttributes);
			assertEquals("update", viewName);
			assertTrue(modelAttributes.containsKey("error"));
			assertEquals("電話番号⑤は数字のみで11文字以内で入力してください。", modelAttributes.get("error"));
			assertEquals(customerInfoForm, modelAttributes.get("customer"));

		} catch (Exception e) {
			fail();
		}
	}

	/*
	 * 項番94
	 */
	@Test
	public void updateFunction94() {

		CustomerInfoForm customerInfoForm = new CustomerInfoForm();
		customerInfoForm.setCustomer_ID(77);
		customerInfoForm.setCustomer_name("Test12345678901234567");
		customerInfoForm.setEmail(
				"testabcdefghigklmnopqrstuvwxyzabcdefghigklmnopqrstuvwxyzabcdefghigklmnopqrstuvwxyzabcdefgh@77ca.co.jp");
		customerInfoForm.setGender("男男");
		customerInfoForm.setAddress("香川県77あいうえおかきくけこさしすせそた");
		List<TelInfo> telInfos = new ArrayList<>();
		String[] tels = new String[] { "77112345678あ", "77212345678あ", "77312345678あ", "77412345678あ", "77512345678あ" };
		int telorder = 0;
		for (String tel : tels) {
			TelInfo telInfo = new TelInfo();
			telInfo.setCustomer_ID(77);
			telInfo.setTel(tel);
			telInfo.setTelorder(telorder++);
			telInfos.add(telInfo);
		}

		customerInfoForm.setTelInfos(telInfos);
		BindingResult result = new MapBindingResult(new HashMap<>(), "customerInfoForm");

		result.addError(new FieldError("customerInfoForm", "customer_name", "顧客名は20文字以内で入力してください。"));
		result.addError(new FieldError("customerInfoForm", "email", "メールは100文字以内で入力してください。"));
		result.addError(new FieldError("customerInfoForm", "gender", "性別はブラウザからボタンで入力してください。"));
		result.addError(new FieldError("customerInfoForm", "address", "住所は20文字以内で入力してください。"));

		RedirectAttributes redirectAttributes = new RedirectAttributesModelMap();

		Map<String, Object> modelAttributes = new HashMap<>();
		when(model.addAttribute(any(String.class), any(Object.class))).thenAnswer(invocation -> {
			modelAttributes.put(invocation.getArgument(0), invocation.getArgument(1));
			return model;
		});

		try {
			String viewName = updateController.updateFunction(customerInfoForm, result, model, redirectAttributes);
			assertEquals("update", viewName);
			assertTrue(modelAttributes.containsKey("error"));
			assertEquals("顧客名は20文字以内で入力してください。"
					+ "<br>メールアドレスは100文字以内で入力してください。"
					+ "<br>性別はブラウザからボタンで入力してください。"
					+ "<br>住所は20文字以内で入力してください。"
					+ "<br>電話番号①は数字のみで11文字以内で入力してください。"
					+ "<br>電話番号②は数字のみで11文字以内で入力してください。"
					+ "<br>電話番号③は数字のみで11文字以内で入力してください。"
					+ "<br>電話番号④は数字のみで11文字以内で入力してください。"
					+ "<br>電話番号⑤は数字のみで11文字以内で入力してください。", modelAttributes.get("error"));
			assertEquals(customerInfoForm, modelAttributes.get("customer"));

		} catch (Exception e) {
			fail();
		}
	}

	/*
	 * 項番95
	 */
	@Test
	public void updateFunction95() {

		CustomerInfoForm customerInfoForm = new CustomerInfoForm();
		customerInfoForm.setCustomer_ID(95);
		customerInfoForm.setCustomer_name("Test95");
		customerInfoForm.setEmail("test@95ca.co.jp");
		customerInfoForm.setGender("男");
		customerInfoForm.setAddress("長崎県95");
		List<TelInfo> telInfos = new ArrayList<>();
		String[] tels = new String[] { "95112345678", "", "", "", "" };
		int telorder = 0;
		for (String tel : tels) {
			TelInfo telInfo = new TelInfo();
			telInfo.setCustomer_ID(95);
			telInfo.setTel(tel);
			telInfo.setTelorder(telorder++);
			telInfos.add(telInfo);
		}

		customerInfoForm.setTelInfos(telInfos);

		BindingResult result = new MapBindingResult(new HashMap<>(), "customerInfoForm");

		RedirectAttributes redirectAttributes = new RedirectAttributesModelMap();

		Map<String, Object> modelAttributes = new HashMap<>();
		when(model.addAttribute(any(String.class), any(Object.class))).thenAnswer(invocation -> {
			modelAttributes.put(invocation.getArgument(0), invocation.getArgument(1));
			return model;
		});

		try {
			doThrow(new Exception("Duplicate phone number")).when(updateService).updateUserInfoAndTelInfo(any(), any());
			String viewName = updateController.updateFunction(customerInfoForm, result, model, redirectAttributes);
			assertEquals("update", viewName);
			assertTrue(modelAttributes.containsKey("error"));
			assertEquals("登録済みの電話番号は使用できません。", modelAttributes.get("error"));
			assertEquals(customerInfoForm, modelAttributes.get("customer"));

		} catch (Exception e) {
			fail();
		}
	}
}
