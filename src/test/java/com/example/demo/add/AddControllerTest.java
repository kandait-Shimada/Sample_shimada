package com.example.demo.add;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

import java.util.HashMap;
import java.util.Map;

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

import com.example.demo.controller.AddController;
import com.example.demo.form.CustomerInfoForm;
import com.example.demo.service.AddService;

@SpringBootTest
@ActiveProfiles("test")
@Transactional
public class AddControllerTest {

	@Mock
	private AddService addService;

	@Mock
	private Model model;

	@InjectMocks
	private AddController addController;

	@BeforeEach
	public void setUp() {
		MockitoAnnotations.openMocks(this);
	}

	/*
	 * 項番17
	 */
	@Test
	public void addFunction17() {

		CustomerInfoForm customerInfoForm = new CustomerInfoForm();
		customerInfoForm.setCustomer_name("Test17");
		customerInfoForm.setEmail("test@17ca.co.jp");
		customerInfoForm.setGender("男");
		customerInfoForm.setAddress("石川県17");
		customerInfoForm.setTel(new String[] { "17112345678", "", "", "", "" });

		BindingResult result = new MapBindingResult(new HashMap<>(), "customerInfoForm");

		RedirectAttributes redirectAttributes = new RedirectAttributesModelMap();

		Map<String, Object> modelAttributes = new HashMap<>();
		when(model.addAttribute(any(String.class), any(Object.class))).thenAnswer(invocation -> {
			modelAttributes.put(invocation.getArgument(0), invocation.getArgument(1));
			return model;
		});

		try {
			String viewName = addController.addFunction(customerInfoForm, result, model, redirectAttributes);
			assertEquals("add", viewName);
			assertTrue(modelAttributes.containsKey("success"));
			assertEquals("顧客情報を登録しました。", modelAttributes.get("success"));

		} catch (Exception e) {
			fail();
		}

	}

	/*
	 * 項番18
	 */
	@Test
	public void addFunction18() {

		CustomerInfoForm customerInfoForm = new CustomerInfoForm();
		customerInfoForm.setCustomer_name("Test1234567890123456");
		customerInfoForm.setEmail("test@78ca.co.jp");
		customerInfoForm.setGender("男");
		customerInfoForm.setAddress("石川県78");
		customerInfoForm.setTel(new String[] { "78112345678", "", "", "", "" });

		BindingResult result = new MapBindingResult(new HashMap<>(), "customerInfoForm");

		RedirectAttributes redirectAttributes = new RedirectAttributesModelMap();

		Map<String, Object> modelAttributes = new HashMap<>();
		when(model.addAttribute(any(String.class), any(Object.class))).thenAnswer(invocation -> {
			modelAttributes.put(invocation.getArgument(0), invocation.getArgument(1));
			return model;
		});

		try {
			String viewName = addController.addFunction(customerInfoForm, result, model, redirectAttributes);
			assertEquals("add", viewName);
			assertTrue(modelAttributes.containsKey("success"));
			assertEquals("顧客情報を登録しました。", modelAttributes.get("success"));

		} catch (Exception e) {
			fail();
		}
	}

	/*
	 * 項番19
	 */
	@Test
	public void addFunction19() {

		CustomerInfoForm customerInfoForm = new CustomerInfoForm();
		customerInfoForm.setCustomer_name("Test79");
		customerInfoForm.setEmail("test@79ca.co.jp");
		customerInfoForm.setGender("男");
		customerInfoForm.setAddress("石川県79");
		customerInfoForm.setTel(new String[] { "79112345678", "", "", "", "" });

		BindingResult result = new MapBindingResult(new HashMap<>(), "customerInfoForm");

		RedirectAttributes redirectAttributes = new RedirectAttributesModelMap();

		Map<String, Object> modelAttributes = new HashMap<>();
		when(model.addAttribute(any(String.class), any(Object.class))).thenAnswer(invocation -> {
			modelAttributes.put(invocation.getArgument(0), invocation.getArgument(1));
			return model;
		});

		try {
			String viewName = addController.addFunction(customerInfoForm, result, model, redirectAttributes);
			assertEquals("add", viewName);
			assertTrue(modelAttributes.containsKey("success"));
			assertEquals("顧客情報を登録しました。", modelAttributes.get("success"));

		} catch (Exception e) {
			fail();
		}
	}

	/*
	 * 項番20
	 */
	@Test
	public void addFunction20() {

		CustomerInfoForm customerInfoForm = new CustomerInfoForm();
		customerInfoForm.setCustomer_name("Test80");
		customerInfoForm.setEmail("test@80ca.co.jp");
		customerInfoForm.setGender("男");
		customerInfoForm.setAddress("石川県80");
		customerInfoForm.setTel(new String[] { "80112345678", "80212345678", "", "", "" });

		BindingResult result = new MapBindingResult(new HashMap<>(), "customerInfoForm");

		RedirectAttributes redirectAttributes = new RedirectAttributesModelMap();

		Map<String, Object> modelAttributes = new HashMap<>();
		when(model.addAttribute(any(String.class), any(Object.class))).thenAnswer(invocation -> {
			modelAttributes.put(invocation.getArgument(0), invocation.getArgument(1));
			return model;
		});

		try {
			String viewName = addController.addFunction(customerInfoForm, result, model, redirectAttributes);
			assertEquals("add", viewName);
			assertTrue(modelAttributes.containsKey("success"));
			assertEquals("顧客情報を登録しました。", modelAttributes.get("success"));

		} catch (Exception e) {
			fail();
		}
	}

	/*
	 * 項番21
	 */
	@Test
	public void addFunction21() {

		CustomerInfoForm customerInfoForm = new CustomerInfoForm();
		customerInfoForm.setCustomer_name("Test81");
		customerInfoForm.setEmail("test@81ca.co.jp");
		customerInfoForm.setGender("男");
		customerInfoForm.setAddress("石川県81");
		customerInfoForm.setTel(new String[] { "81112345678", "", "81312345678", "", "" });

		BindingResult result = new MapBindingResult(new HashMap<>(), "customerInfoForm");

		RedirectAttributes redirectAttributes = new RedirectAttributesModelMap();

		Map<String, Object> modelAttributes = new HashMap<>();
		when(model.addAttribute(any(String.class), any(Object.class))).thenAnswer(invocation -> {
			modelAttributes.put(invocation.getArgument(0), invocation.getArgument(1));
			return model;
		});

		try {
			String viewName = addController.addFunction(customerInfoForm, result, model, redirectAttributes);
			assertEquals("add", viewName);
			assertTrue(modelAttributes.containsKey("success"));
			assertEquals("顧客情報を登録しました。", modelAttributes.get("success"));

		} catch (Exception e) {
			fail();
		}
	}

	/*
	 * 項番22
	 */
	@Test
	public void addFunction22() {

		CustomerInfoForm customerInfoForm = new CustomerInfoForm();
		customerInfoForm.setCustomer_name("Test82");
		customerInfoForm.setEmail("test@82ca.co.jp");
		customerInfoForm.setGender("男");
		customerInfoForm.setAddress("石川県82");
		customerInfoForm.setTel(new String[] { "82112345678", "", "", "82412345678", "" });

		BindingResult result = new MapBindingResult(new HashMap<>(), "customerInfoForm");

		RedirectAttributes redirectAttributes = new RedirectAttributesModelMap();

		Map<String, Object> modelAttributes = new HashMap<>();
		when(model.addAttribute(any(String.class), any(Object.class))).thenAnswer(invocation -> {
			modelAttributes.put(invocation.getArgument(0), invocation.getArgument(1));
			return model;
		});

		try {
			String viewName = addController.addFunction(customerInfoForm, result, model, redirectAttributes);
			assertEquals("add", viewName);
			assertTrue(modelAttributes.containsKey("success"));
			assertEquals("顧客情報を登録しました。", modelAttributes.get("success"));

		} catch (Exception e) {
			fail();
		}
	}

	/*
	 * 項番23
	 */
	@Test
	public void addFunction23() {

		CustomerInfoForm customerInfoForm = new CustomerInfoForm();
		customerInfoForm.setCustomer_name("Test83");
		customerInfoForm.setEmail("test@83ca.co.jp");
		customerInfoForm.setGender("男");
		customerInfoForm.setAddress("石川県83");
		customerInfoForm.setTel(new String[] { "83112345678", "", "", "", "83512345678" });

		BindingResult result = new MapBindingResult(new HashMap<>(), "customerInfoForm");

		RedirectAttributes redirectAttributes = new RedirectAttributesModelMap();

		Map<String, Object> modelAttributes = new HashMap<>();
		when(model.addAttribute(any(String.class), any(Object.class))).thenAnswer(invocation -> {
			modelAttributes.put(invocation.getArgument(0), invocation.getArgument(1));
			return model;
		});

		try {
			String viewName = addController.addFunction(customerInfoForm, result, model, redirectAttributes);
			assertEquals("add", viewName);
			assertTrue(modelAttributes.containsKey("success"));
			assertEquals("顧客情報を登録しました。", modelAttributes.get("success"));

		} catch (Exception e) {
			fail();
		}
	}

	/*
	 * 項番24
	 */
	@Test
	public void addFunction24() {

		CustomerInfoForm customerInfoForm = new CustomerInfoForm();
		customerInfoForm.setCustomer_name("Test84");
		customerInfoForm.setEmail("testabcdefghigklmnopqrstuvwxyzabcdefghigklmnopqrstuvwxyzabcdefghigklmnopqrstuvwxyzabcdefg@84ca.co.jp");
		customerInfoForm.setGender("男");
		customerInfoForm.setAddress("石川県84");
		customerInfoForm.setTel(new String[] { "84112345678", "", "", "", "" });

		BindingResult result = new MapBindingResult(new HashMap<>(), "customerInfoForm");

		RedirectAttributes redirectAttributes = new RedirectAttributesModelMap();

		Map<String, Object> modelAttributes = new HashMap<>();
		when(model.addAttribute(any(String.class), any(Object.class))).thenAnswer(invocation -> {
			modelAttributes.put(invocation.getArgument(0), invocation.getArgument(1));
			return model;
		});

		try {
			String viewName = addController.addFunction(customerInfoForm, result, model, redirectAttributes);
			assertEquals("add", viewName);
			assertTrue(modelAttributes.containsKey("success"));
			assertEquals("顧客情報を登録しました。", modelAttributes.get("success"));

		} catch (Exception e) {
			fail();
		}
	}

	/*
	 * 項番25
	 */
	@Test
	public void addFunction25() {

		CustomerInfoForm customerInfoForm = new CustomerInfoForm();
		customerInfoForm.setCustomer_name("Test85");
		customerInfoForm.setEmail("test@85ca.co.jp");
		customerInfoForm.setGender("男");
		customerInfoForm.setAddress("石川県85あいうえおかきくけこさしすせそ");
		customerInfoForm.setTel(new String[] { "85112345678", "", "", "", "" });

		BindingResult result = new MapBindingResult(new HashMap<>(), "customerInfoForm");

		RedirectAttributes redirectAttributes = new RedirectAttributesModelMap();

		Map<String, Object> modelAttributes = new HashMap<>();
		when(model.addAttribute(any(String.class), any(Object.class))).thenAnswer(invocation -> {
			modelAttributes.put(invocation.getArgument(0), invocation.getArgument(1));
			return model;
		});

		try {
			String viewName = addController.addFunction(customerInfoForm, result, model, redirectAttributes);
			assertEquals("add", viewName);
			assertTrue(modelAttributes.containsKey("success"));
			assertEquals("顧客情報を登録しました。", modelAttributes.get("success"));

		} catch (Exception e) {
			fail();
		}
	}

	/*
	 * 項番26
	 */
	@Test
	public void addFunction26() {

		CustomerInfoForm customerInfoForm = new CustomerInfoForm();
		customerInfoForm.setCustomer_name("");
		customerInfoForm.setEmail("test@18ca.co.jp");
		customerInfoForm.setGender("男");
		customerInfoForm.setAddress("福井県18");
		customerInfoForm.setTel(new String[] { "18112345678", "", "", "", "" });

		BindingResult result = new MapBindingResult(new HashMap<>(), "customerInfoForm");

		RedirectAttributes redirectAttributes = new RedirectAttributesModelMap();

		Map<String, Object> modelAttributes = new HashMap<>();
		when(model.addAttribute(any(String.class), any(Object.class))).thenAnswer(invocation -> {
			modelAttributes.put(invocation.getArgument(0), invocation.getArgument(1));
			return model;
		});

		try {
			String viewName = addController.addFunction(customerInfoForm, result, model, redirectAttributes);
			assertEquals("add", viewName);
			assertTrue(modelAttributes.containsKey("error"));
			assertEquals("顧客名は入力必須です。", modelAttributes.get("error"));

		} catch (Exception e) {
			fail();
		}

	}

	/*
	 * 項番27
	 */
	@Test
	public void addFunction27() {

		CustomerInfoForm customerInfoForm = new CustomerInfoForm();
		customerInfoForm.setCustomer_name("Test19");
		customerInfoForm.setEmail("test@19ca.co.jp");
		customerInfoForm.setGender("男");
		customerInfoForm.setAddress("山梨県19");
		customerInfoForm.setTel(new String[] { "", "", "", "", "" });

		BindingResult result = new MapBindingResult(new HashMap<>(), "customerInfoForm");

		RedirectAttributes redirectAttributes = new RedirectAttributesModelMap();

		Map<String, Object> modelAttributes = new HashMap<>();
		when(model.addAttribute(any(String.class), any(Object.class))).thenAnswer(invocation -> {
			modelAttributes.put(invocation.getArgument(0), invocation.getArgument(1));
			return model;
		});

		try {
			String viewName = addController.addFunction(customerInfoForm, result, model, redirectAttributes);
			assertEquals("add", viewName);
			assertTrue(modelAttributes.containsKey("error"));
			assertEquals("電話番号①は入力必須です。", modelAttributes.get("error"));

		} catch (Exception e) {
			fail();
		}
	}

	/*
	 * 項番28
	 */
	@Test
	public void addFunction28() {

		CustomerInfoForm customerInfoForm = new CustomerInfoForm();
		customerInfoForm.setCustomer_name("Test20");
		customerInfoForm.setEmail("");
		customerInfoForm.setGender("男");
		customerInfoForm.setAddress("長野県20");
		customerInfoForm.setTel(new String[] { "20112345678", "", "", "", "" });

		BindingResult result = new MapBindingResult(new HashMap<>(), "customerInfoForm");

		RedirectAttributes redirectAttributes = new RedirectAttributesModelMap();

		Map<String, Object> modelAttributes = new HashMap<>();
		when(model.addAttribute(any(String.class), any(Object.class))).thenAnswer(invocation -> {
			modelAttributes.put(invocation.getArgument(0), invocation.getArgument(1));
			return model;
		});

		try {
			String viewName = addController.addFunction(customerInfoForm, result, model, redirectAttributes);
			assertEquals("add", viewName);
			assertTrue(modelAttributes.containsKey("error"));
			assertEquals("メールアドレスは入力必須です。", modelAttributes.get("error"));

		} catch (Exception e) {
			fail();
		}
	}

	/*
	 * 項番29
	 */
	@Test
	public void addFunction29() {

		CustomerInfoForm customerInfoForm = new CustomerInfoForm();
		customerInfoForm.setCustomer_name("Test21");
		customerInfoForm.setEmail("test@21ca.co.jp");
		customerInfoForm.setGender("");
		customerInfoForm.setAddress("岐阜県21");
		customerInfoForm.setTel(new String[] { "21112345678", "", "", "", "" });

		BindingResult result = new MapBindingResult(new HashMap<>(), "customerInfoForm");

		RedirectAttributes redirectAttributes = new RedirectAttributesModelMap();

		Map<String, Object> modelAttributes = new HashMap<>();
		when(model.addAttribute(any(String.class), any(Object.class))).thenAnswer(invocation -> {
			modelAttributes.put(invocation.getArgument(0), invocation.getArgument(1));
			return model;
		});

		try {
			String viewName = addController.addFunction(customerInfoForm, result, model, redirectAttributes);
			assertEquals("add", viewName);
			assertTrue(modelAttributes.containsKey("error"));
			assertEquals("性別は入力必須です。", modelAttributes.get("error"));

		} catch (Exception e) {
			fail();
		}
	}

	/*
	 * 項番30
	 */
	@Test
	public void addFunction30() {

		CustomerInfoForm customerInfoForm = new CustomerInfoForm();
		customerInfoForm.setCustomer_name("Test22");
		customerInfoForm.setEmail("test@22ca.co.jp");
		customerInfoForm.setGender("男");
		customerInfoForm.setAddress("");
		customerInfoForm.setTel(new String[] { "22112345678", "", "", "", "" });

		BindingResult result = new MapBindingResult(new HashMap<>(), "customerInfoForm");

		RedirectAttributes redirectAttributes = new RedirectAttributesModelMap();

		Map<String, Object> modelAttributes = new HashMap<>();
		when(model.addAttribute(any(String.class), any(Object.class))).thenAnswer(invocation -> {
			modelAttributes.put(invocation.getArgument(0), invocation.getArgument(1));
			return model;
		});

		try {
			String viewName = addController.addFunction(customerInfoForm, result, model, redirectAttributes);
			assertEquals("add", viewName);
			assertTrue(modelAttributes.containsKey("error"));
			assertEquals("住所は入力必須です。", modelAttributes.get("error"));

		} catch (Exception e) {
			fail();
		}
	}

	/*
	 * 項番31
	 */
	@Test
	public void addFunction31() {

		CustomerInfoForm customerInfoForm = new CustomerInfoForm();
		customerInfoForm.setCustomer_name("");
		customerInfoForm.setEmail("");
		customerInfoForm.setGender("");
		customerInfoForm.setAddress("");
		customerInfoForm.setTel(new String[] { "", "", "", "", "" });

		BindingResult result = new MapBindingResult(new HashMap<>(), "customerInfoForm");

		RedirectAttributes redirectAttributes = new RedirectAttributesModelMap();

		Map<String, Object> modelAttributes = new HashMap<>();
		when(model.addAttribute(any(String.class), any(Object.class))).thenAnswer(invocation -> {
			modelAttributes.put(invocation.getArgument(0), invocation.getArgument(1));
			return model;
		});

		try {
			String viewName = addController.addFunction(customerInfoForm, result, model, redirectAttributes);
			assertEquals("add", viewName);
			assertTrue(modelAttributes.containsKey("error"));
			assertEquals("顧客名は入力必須です。<br>"
					+ "電話番号①は入力必須です。<br>"
					+ "メールアドレスは入力必須です。<br>"
					+ "性別は入力必須です。<br>"
					+ "住所は入力必須です。", modelAttributes.get("error"));

		} catch (Exception e) {
			fail();
		}
	}
	
	/*
	 * 項番32
	 */
	@Test
	public void addFunction32() {

		CustomerInfoForm customerInfoForm = new CustomerInfoForm();
		customerInfoForm.setCustomer_name("Test12345678901234567");
		customerInfoForm.setEmail("test@24ca.co.jp");
		customerInfoForm.setGender("男");
		customerInfoForm.setAddress("三重県24");
		customerInfoForm.setTel(new String[] { "24112345678", "", "", "", "" });

		MapBindingResult result = new MapBindingResult(new HashMap<>(), "customerInfoForm");

		result.addError(new FieldError("customerInfoForm", "customer_name", "顧客名は20文字以内で入力してください。"));

		RedirectAttributes redirectAttributes = new RedirectAttributesModelMap();

		Map<String, Object> modelAttributes = new HashMap<>();
		when(model.addAttribute(any(String.class), any(Object.class))).thenAnswer(invocation -> {
			modelAttributes.put(invocation.getArgument(0), invocation.getArgument(1));
			return model;
		});

		try {
			String viewName = addController.addFunction(customerInfoForm, result, model, redirectAttributes);
			assertEquals("add", viewName);
			assertTrue(modelAttributes.containsKey("error"));
			assertEquals("顧客名は20文字以内で入力してください。", modelAttributes.get("error"));

		} catch (Exception e) {
			fail();
		}
	}

	/*
	 * 項番33
	 */
	@Test
	public void addFunction33() {

		CustomerInfoForm customerInfoForm = new CustomerInfoForm();
		customerInfoForm.setCustomer_name("Test25");
		customerInfoForm.setEmail(
				"testabcdefghigklmnopqrstuvwxyzabcdefghigklmnopqrstuvwxyzabcdefghigklmnopqrstuvwxyzabcdefgh@25ca.co.jp");
		customerInfoForm.setGender("男");
		customerInfoForm.setAddress("滋賀県25");
		customerInfoForm.setTel(new String[] { "25112345678", "", "", "", "" });

		BindingResult result = new MapBindingResult(new HashMap<>(), "customerInfoForm");

		result.addError(new FieldError("customerInfoForm", "email", "メールは100文字以内で入力してください。"));

		RedirectAttributes redirectAttributes = new RedirectAttributesModelMap();

		Map<String, Object> modelAttributes = new HashMap<>();
		when(model.addAttribute(any(String.class), any(Object.class))).thenAnswer(invocation -> {
			modelAttributes.put(invocation.getArgument(0), invocation.getArgument(1));
			return model;
		});

		try {
			String viewName = addController.addFunction(customerInfoForm, result, model, redirectAttributes);
			assertEquals("add", viewName);
			assertTrue(modelAttributes.containsKey("error"));
			assertEquals("メールアドレスは100文字以内で入力してください。", modelAttributes.get("error"));

		} catch (Exception e) {
			fail();
		}
	}

	/*
	 * 項番34
	 */
	@Test
	public void addFunction34() {

		CustomerInfoForm customerInfoForm = new CustomerInfoForm();
		customerInfoForm.setCustomer_name("Test26");
		customerInfoForm.setEmail("test@26ca.co.jp");
		customerInfoForm.setGender("男男");
		customerInfoForm.setAddress("京都府26");
		customerInfoForm.setTel(new String[] { "26112345678", "", "", "", "" });

		BindingResult result = new MapBindingResult(new HashMap<>(), "customerInfoForm");

		result.addError(new FieldError("customerInfoForm", "gender", "性別はブラウザからボタンで入力してください。"));

		RedirectAttributes redirectAttributes = new RedirectAttributesModelMap();

		Map<String, Object> modelAttributes = new HashMap<>();
		when(model.addAttribute(any(String.class), any(Object.class))).thenAnswer(invocation -> {
			modelAttributes.put(invocation.getArgument(0), invocation.getArgument(1));
			return model;
		});

		try {
			String viewName = addController.addFunction(customerInfoForm, result, model, redirectAttributes);
			assertEquals("add", viewName);
			assertTrue(modelAttributes.containsKey("error"));
			assertEquals("性別はブラウザからボタンで入力してください。", modelAttributes.get("error"));

		} catch (Exception e) {
			fail();
		}
	}

	/*
	 * 項番35
	 */
	@Test
	public void addFunction35() {

		CustomerInfoForm customerInfoForm = new CustomerInfoForm();
		customerInfoForm.setCustomer_name("Test27");
		customerInfoForm.setEmail("test@27ca.co.jp");
		customerInfoForm.setGender("男");
		customerInfoForm.setAddress("大阪府27あいうえおかきくけこさしすせそた");
		customerInfoForm.setTel(new String[] { "27112345678", "", "", "", "" });

		BindingResult result = new MapBindingResult(new HashMap<>(), "customerInfoForm");

		result.addError(new FieldError("customerInfoForm", "address", "住所は20文字以内で入力してください。"));

		RedirectAttributes redirectAttributes = new RedirectAttributesModelMap();

		Map<String, Object> modelAttributes = new HashMap<>();
		when(model.addAttribute(any(String.class), any(Object.class))).thenAnswer(invocation -> {
			modelAttributes.put(invocation.getArgument(0), invocation.getArgument(1));
			return model;
		});

		try {
			String viewName = addController.addFunction(customerInfoForm, result, model, redirectAttributes);
			assertEquals("add", viewName);
			assertTrue(modelAttributes.containsKey("error"));
			assertEquals("住所は20文字以内で入力してください。", modelAttributes.get("error"));

		} catch (Exception e) {
			fail();
		}
	}

	/*
	 * 項番36
	 */
	@Test
	public void addFunction36() {

		CustomerInfoForm customerInfoForm = new CustomerInfoForm();
		customerInfoForm.setCustomer_name("Test28");
		customerInfoForm.setEmail("test@28ca.co.jp");
		customerInfoForm.setGender("男");
		customerInfoForm.setAddress("兵庫県28");
		customerInfoForm.setTel(new String[] { "28112345678あ", "", "", "", "" });

		BindingResult result = new MapBindingResult(new HashMap<>(), "customerInfoForm");

		RedirectAttributes redirectAttributes = new RedirectAttributesModelMap();

		Map<String, Object> modelAttributes = new HashMap<>();
		when(model.addAttribute(any(String.class), any(Object.class))).thenAnswer(invocation -> {
			modelAttributes.put(invocation.getArgument(0), invocation.getArgument(1));
			return model;
		});

		try {
			String viewName = addController.addFunction(customerInfoForm, result, model, redirectAttributes);
			assertEquals("add", viewName);
			assertTrue(modelAttributes.containsKey("error"));
			assertEquals("電話番号①は数字のみで11文字以内で入力してください。", modelAttributes.get("error"));

		} catch (Exception e) {
			fail();
		}
	}

	/*
	 * 項番37
	 */
	@Test
	public void addFunction37() {

		CustomerInfoForm customerInfoForm = new CustomerInfoForm();
		customerInfoForm.setCustomer_name("Test29");
		customerInfoForm.setEmail("test@29ca.co.jp");
		customerInfoForm.setGender("男");
		customerInfoForm.setAddress("奈良県29");
		customerInfoForm.setTel(new String[] { "291123456789", "", "", "", "" });

		BindingResult result = new MapBindingResult(new HashMap<>(), "customerInfoForm");

		RedirectAttributes redirectAttributes = new RedirectAttributesModelMap();

		Map<String, Object> modelAttributes = new HashMap<>();
		when(model.addAttribute(any(String.class), any(Object.class))).thenAnswer(invocation -> {
			modelAttributes.put(invocation.getArgument(0), invocation.getArgument(1));
			return model;
		});

		try {
			String viewName = addController.addFunction(customerInfoForm, result, model, redirectAttributes);
			assertEquals("add", viewName);
			assertTrue(modelAttributes.containsKey("error"));
			assertEquals("電話番号①は数字のみで11文字以内で入力してください。", modelAttributes.get("error"));

		} catch (Exception e) {
			fail();
		}
	}

	/*
	 * 項番38
	 */
	@Test
	public void addFunction38() {

		CustomerInfoForm customerInfoForm = new CustomerInfoForm();
		customerInfoForm.setCustomer_name("Test30");
		customerInfoForm.setEmail("test@30ca.co.jp");
		customerInfoForm.setGender("男");
		customerInfoForm.setAddress("和歌山県30");
		customerInfoForm.setTel(new String[] { "30112345678あ", "", "", "", "" });

		BindingResult result = new MapBindingResult(new HashMap<>(), "customerInfoForm");

		RedirectAttributes redirectAttributes = new RedirectAttributesModelMap();

		Map<String, Object> modelAttributes = new HashMap<>();
		when(model.addAttribute(any(String.class), any(Object.class))).thenAnswer(invocation -> {
			modelAttributes.put(invocation.getArgument(0), invocation.getArgument(1));
			return model;
		});

		try {
			String viewName = addController.addFunction(customerInfoForm, result, model, redirectAttributes);
			assertEquals("add", viewName);
			assertTrue(modelAttributes.containsKey("error"));
			assertEquals("電話番号①は数字のみで11文字以内で入力してください。", modelAttributes.get("error"));

		} catch (Exception e) {
			fail();
		}
	}
	
	/*
	 * 項番39
	 */
	@Test
	public void addFunction39() {

		CustomerInfoForm customerInfoForm = new CustomerInfoForm();
		customerInfoForm.setCustomer_name("Test31");
		customerInfoForm.setEmail("test@31ca.co.jp");
		customerInfoForm.setGender("男");
		customerInfoForm.setAddress("鳥取県31");
		customerInfoForm.setTel(new String[] { "31112345678", "3121234567あ", "", "", "" });

		BindingResult result = new MapBindingResult(new HashMap<>(), "customerInfoForm");

		RedirectAttributes redirectAttributes = new RedirectAttributesModelMap();

		Map<String, Object> modelAttributes = new HashMap<>();
		when(model.addAttribute(any(String.class), any(Object.class))).thenAnswer(invocation -> {
			modelAttributes.put(invocation.getArgument(0), invocation.getArgument(1));
			return model;
		});

		try {
			String viewName = addController.addFunction(customerInfoForm, result, model, redirectAttributes);
			assertEquals("add", viewName);
			assertTrue(modelAttributes.containsKey("error"));
			assertEquals("電話番号②は数字のみで11文字以内で入力してください。", modelAttributes.get("error"));

		} catch (Exception e) {
			fail();
		}
	}

	/*
	 * 項番40
	 */
	@Test
	public void addFunction40() {

		CustomerInfoForm customerInfoForm = new CustomerInfoForm();
		customerInfoForm.setCustomer_name("Test32");
		customerInfoForm.setEmail("test@32ca.co.jp");
		customerInfoForm.setGender("男");
		customerInfoForm.setAddress("島根県32");
		customerInfoForm.setTel(new String[] { "32112345678", "322123456789", "", "", "" });

		BindingResult result = new MapBindingResult(new HashMap<>(), "customerInfoForm");

		RedirectAttributes redirectAttributes = new RedirectAttributesModelMap();

		Map<String, Object> modelAttributes = new HashMap<>();
		when(model.addAttribute(any(String.class), any(Object.class))).thenAnswer(invocation -> {
			modelAttributes.put(invocation.getArgument(0), invocation.getArgument(1));
			return model;
		});

		try {
			String viewName = addController.addFunction(customerInfoForm, result, model, redirectAttributes);
			assertEquals("add", viewName);
			assertTrue(modelAttributes.containsKey("error"));
			assertEquals("電話番号②は数字のみで11文字以内で入力してください。", modelAttributes.get("error"));

		} catch (Exception e) {
			fail();
		}
	}

	/*
	 * 項番41
	 */
	@Test
	public void addFunction41() {

		CustomerInfoForm customerInfoForm = new CustomerInfoForm();
		customerInfoForm.setCustomer_name("Test33");
		customerInfoForm.setEmail("test@33ca.co.jp");
		customerInfoForm.setGender("男");
		customerInfoForm.setAddress("岡山県33");
		customerInfoForm.setTel(new String[] { "33112345678", "33212345678あ", "", "", "" });

		BindingResult result = new MapBindingResult(new HashMap<>(), "customerInfoForm");

		RedirectAttributes redirectAttributes = new RedirectAttributesModelMap();

		Map<String, Object> modelAttributes = new HashMap<>();
		when(model.addAttribute(any(String.class), any(Object.class))).thenAnswer(invocation -> {
			modelAttributes.put(invocation.getArgument(0), invocation.getArgument(1));
			return model;
		});

		try {
			String viewName = addController.addFunction(customerInfoForm, result, model, redirectAttributes);
			assertEquals("add", viewName);
			assertTrue(modelAttributes.containsKey("error"));
			assertEquals("電話番号②は数字のみで11文字以内で入力してください。", modelAttributes.get("error"));

		} catch (Exception e) {
			fail();
		}
	}

	/*
	 * 項番42
	 */
	@Test
	public void addFunction42() {

		CustomerInfoForm customerInfoForm = new CustomerInfoForm();
		customerInfoForm.setCustomer_name("Test34");
		customerInfoForm.setEmail("test@34ca.co.jp");
		customerInfoForm.setGender("男");
		customerInfoForm.setAddress("広島県34");
		customerInfoForm.setTel(new String[] { "34112345678", "", "3431234567あ", "", "" });

		BindingResult result = new MapBindingResult(new HashMap<>(), "customerInfoForm");

		RedirectAttributes redirectAttributes = new RedirectAttributesModelMap();

		Map<String, Object> modelAttributes = new HashMap<>();
		when(model.addAttribute(any(String.class), any(Object.class))).thenAnswer(invocation -> {
			modelAttributes.put(invocation.getArgument(0), invocation.getArgument(1));
			return model;
		});

		try {
			String viewName = addController.addFunction(customerInfoForm, result, model, redirectAttributes);
			assertEquals("add", viewName);
			assertTrue(modelAttributes.containsKey("error"));
			assertEquals("電話番号③は数字のみで11文字以内で入力してください。", modelAttributes.get("error"));

		} catch (Exception e) {
			fail();
		}
	}

	/*
	 * 項番43
	 */
	@Test
	public void addFunction43() {

		CustomerInfoForm customerInfoForm = new CustomerInfoForm();
		customerInfoForm.setCustomer_name("Test35");
		customerInfoForm.setEmail("test@35ca.co.jp");
		customerInfoForm.setGender("男");
		customerInfoForm.setAddress("山口県35");
		customerInfoForm.setTel(new String[] { "35112345678", "", "353123456789", "", "" });

		BindingResult result = new MapBindingResult(new HashMap<>(), "customerInfoForm");

		RedirectAttributes redirectAttributes = new RedirectAttributesModelMap();

		Map<String, Object> modelAttributes = new HashMap<>();
		when(model.addAttribute(any(String.class), any(Object.class))).thenAnswer(invocation -> {
			modelAttributes.put(invocation.getArgument(0), invocation.getArgument(1));
			return model;
		});

		try {
			String viewName = addController.addFunction(customerInfoForm, result, model, redirectAttributes);
			assertEquals("add", viewName);
			assertTrue(modelAttributes.containsKey("error"));
			assertEquals("電話番号③は数字のみで11文字以内で入力してください。", modelAttributes.get("error"));

		} catch (Exception e) {
			fail();
		}
	}

	/*
	 * 項番44
	 */
	@Test
	public void addFunction44() {

		CustomerInfoForm customerInfoForm = new CustomerInfoForm();
		customerInfoForm.setCustomer_name("Test36");
		customerInfoForm.setEmail("test@36ca.co.jp");
		customerInfoForm.setGender("男");
		customerInfoForm.setAddress("徳島県36");
		customerInfoForm.setTel(new String[] { "36112345678", "", "36312345678あ", "", "" });

		BindingResult result = new MapBindingResult(new HashMap<>(), "customerInfoForm");

		RedirectAttributes redirectAttributes = new RedirectAttributesModelMap();

		Map<String, Object> modelAttributes = new HashMap<>();
		when(model.addAttribute(any(String.class), any(Object.class))).thenAnswer(invocation -> {
			modelAttributes.put(invocation.getArgument(0), invocation.getArgument(1));
			return model;
		});

		try {
			String viewName = addController.addFunction(customerInfoForm, result, model, redirectAttributes);
			assertEquals("add", viewName);
			assertTrue(modelAttributes.containsKey("error"));
			assertEquals("電話番号③は数字のみで11文字以内で入力してください。", modelAttributes.get("error"));

		} catch (Exception e) {
			fail();
		}
	}
	
	/*
	 * 項番45
	 */
	@Test
	public void addFunction45() {

		CustomerInfoForm customerInfoForm = new CustomerInfoForm();
		customerInfoForm.setCustomer_name("Test37");
		customerInfoForm.setEmail("test@37ca.co.jp");
		customerInfoForm.setGender("男");
		customerInfoForm.setAddress("香川県37");
		customerInfoForm.setTel(new String[] { "37112345678", "", "", "3741234567あ", "" });

		BindingResult result = new MapBindingResult(new HashMap<>(), "customerInfoForm");

		RedirectAttributes redirectAttributes = new RedirectAttributesModelMap();

		Map<String, Object> modelAttributes = new HashMap<>();
		when(model.addAttribute(any(String.class), any(Object.class))).thenAnswer(invocation -> {
			modelAttributes.put(invocation.getArgument(0), invocation.getArgument(1));
			return model;
		});

		try {
			String viewName = addController.addFunction(customerInfoForm, result, model, redirectAttributes);
			assertEquals("add", viewName);
			assertTrue(modelAttributes.containsKey("error"));
			assertEquals("電話番号④は数字のみで11文字以内で入力してください。", modelAttributes.get("error"));

		} catch (Exception e) {
			fail();
		}
	}

	/*
	 * 項番46
	 */
	@Test
	public void addFunction46() {

		CustomerInfoForm customerInfoForm = new CustomerInfoForm();
		customerInfoForm.setCustomer_name("Test38");
		customerInfoForm.setEmail("test@38ca.co.jp");
		customerInfoForm.setGender("男");
		customerInfoForm.setAddress("愛媛県38");
		customerInfoForm.setTel(new String[] { "38112345678", "", "", "384123456789", "" });

		BindingResult result = new MapBindingResult(new HashMap<>(), "customerInfoForm");

		RedirectAttributes redirectAttributes = new RedirectAttributesModelMap();

		Map<String, Object> modelAttributes = new HashMap<>();
		when(model.addAttribute(any(String.class), any(Object.class))).thenAnswer(invocation -> {
			modelAttributes.put(invocation.getArgument(0), invocation.getArgument(1));
			return model;
		});

		try {
			String viewName = addController.addFunction(customerInfoForm, result, model, redirectAttributes);
			assertEquals("add", viewName);
			assertTrue(modelAttributes.containsKey("error"));
			assertEquals("電話番号④は数字のみで11文字以内で入力してください。", modelAttributes.get("error"));

		} catch (Exception e) {
			fail();
		}
	}

	/*
	 * 項番47
	 */
	@Test
	public void addFunction47() {

		CustomerInfoForm customerInfoForm = new CustomerInfoForm();
		customerInfoForm.setCustomer_name("Test39");
		customerInfoForm.setEmail("test@39ca.co.jp");
		customerInfoForm.setGender("男");
		customerInfoForm.setAddress("高知県39");
		customerInfoForm.setTel(new String[] { "39112345678", "", "", "39412345678あ", "" });

		BindingResult result = new MapBindingResult(new HashMap<>(), "customerInfoForm");

		RedirectAttributes redirectAttributes = new RedirectAttributesModelMap();

		Map<String, Object> modelAttributes = new HashMap<>();
		when(model.addAttribute(any(String.class), any(Object.class))).thenAnswer(invocation -> {
			modelAttributes.put(invocation.getArgument(0), invocation.getArgument(1));
			return model;
		});

		try {
			String viewName = addController.addFunction(customerInfoForm, result, model, redirectAttributes);
			assertEquals("add", viewName);
			assertTrue(modelAttributes.containsKey("error"));
			assertEquals("電話番号④は数字のみで11文字以内で入力してください。", modelAttributes.get("error"));

		} catch (Exception e) {
			fail();
		}
	}

	/*
	 * 項番48
	 */
	@Test
	public void addFunction48() {

		CustomerInfoForm customerInfoForm = new CustomerInfoForm();
		customerInfoForm.setCustomer_name("Test40");
		customerInfoForm.setEmail("test@40ca.co.jp");
		customerInfoForm.setGender("男");
		customerInfoForm.setAddress("福岡県40");
		customerInfoForm.setTel(new String[] { "40112345678", "", "", "", "4051234567あ" });

		BindingResult result = new MapBindingResult(new HashMap<>(), "customerInfoForm");

		RedirectAttributes redirectAttributes = new RedirectAttributesModelMap();

		Map<String, Object> modelAttributes = new HashMap<>();
		when(model.addAttribute(any(String.class), any(Object.class))).thenAnswer(invocation -> {
			modelAttributes.put(invocation.getArgument(0), invocation.getArgument(1));
			return model;
		});

		try {
			String viewName = addController.addFunction(customerInfoForm, result, model, redirectAttributes);
			assertEquals("add", viewName);
			assertTrue(modelAttributes.containsKey("error"));
			assertEquals("電話番号⑤は数字のみで11文字以内で入力してください。", modelAttributes.get("error"));

		} catch (Exception e) {
			fail();
		}
	}

	/*
	 * 項番49
	 */
	@Test
	public void addFunction49() {

		CustomerInfoForm customerInfoForm = new CustomerInfoForm();
		customerInfoForm.setCustomer_name("Test41");
		customerInfoForm.setEmail("test@41ca.co.jp");
		customerInfoForm.setGender("男");
		customerInfoForm.setAddress("佐賀県41");
		customerInfoForm.setTel(new String[] { "40112345678", "", "", "", "405123456789" });

		BindingResult result = new MapBindingResult(new HashMap<>(), "customerInfoForm");

		RedirectAttributes redirectAttributes = new RedirectAttributesModelMap();

		Map<String, Object> modelAttributes = new HashMap<>();
		when(model.addAttribute(any(String.class), any(Object.class))).thenAnswer(invocation -> {
			modelAttributes.put(invocation.getArgument(0), invocation.getArgument(1));
			return model;
		});

		try {
			String viewName = addController.addFunction(customerInfoForm, result, model, redirectAttributes);
			assertEquals("add", viewName);
			assertTrue(modelAttributes.containsKey("error"));
			assertEquals("電話番号⑤は数字のみで11文字以内で入力してください。", modelAttributes.get("error"));

		} catch (Exception e) {
			fail();
		}
	}

	/*	
	 * 項番50
	 */
	@Test
	public void addFunction50() {

		CustomerInfoForm customerInfoForm = new CustomerInfoForm();
		customerInfoForm.setCustomer_name("Test42");
		customerInfoForm.setEmail("test@42ca.co.jp");
		customerInfoForm.setGender("男");
		customerInfoForm.setAddress("長崎県42");
		customerInfoForm.setTel(new String[] { "40112345678", "", "", "", "40512345678あ" });

		BindingResult result = new MapBindingResult(new HashMap<>(), "customerInfoForm");

		RedirectAttributes redirectAttributes = new RedirectAttributesModelMap();

		Map<String, Object> modelAttributes = new HashMap<>();
		when(model.addAttribute(any(String.class), any(Object.class))).thenAnswer(invocation -> {
			modelAttributes.put(invocation.getArgument(0), invocation.getArgument(1));
			return model;
		});

		try {
			String viewName = addController.addFunction(customerInfoForm, result, model, redirectAttributes);
			assertEquals("add", viewName);
			assertTrue(modelAttributes.containsKey("error"));
			assertEquals("電話番号⑤は数字のみで11文字以内で入力してください。", modelAttributes.get("error"));

		} catch (Exception e) {
			fail();
		}
	}

	/*
	 * 項番51
	 */
	@Test
	public void addFunction51() {

		CustomerInfoForm customerInfoForm = new CustomerInfoForm();
		customerInfoForm.setCustomer_name("Test12345678901234567");
		customerInfoForm.setEmail(
				"testabcdefghigklmnopqrstuvwxyzabcdefghigklmnopqrstuvwxyzabcdefghigklmnopqrstuvwxyzabcdefgh@43ca.co.jp");
		customerInfoForm.setGender("男男");
		customerInfoForm.setAddress("熊本県43あいうえおかきくけこさしすせそた");
		customerInfoForm.setTel(
				new String[] { "43112345678あ", "43212345678あ", "43312345678あ", "43412345678あ", "43512345678あ" });

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
			String viewName = addController.addFunction(customerInfoForm, result, model, redirectAttributes);
			assertEquals("add", viewName);
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

		} catch (Exception e) {
			fail();
		}
	}

	/*
	 * 項番52
	 */
	@Test
	public void addFunction52() {

		CustomerInfoForm customerInfoForm = new CustomerInfoForm();
		customerInfoForm.setCustomer_name("Test86");
		customerInfoForm.setEmail("test@86ca.co.jp");
		customerInfoForm.setGender("男");
		customerInfoForm.setAddress("長崎県86");
		customerInfoForm.setTel(new String[] { "86112345678", "", "", "", "" });

		BindingResult result = new MapBindingResult(new HashMap<>(), "customerInfoForm");

		RedirectAttributes redirectAttributes = new RedirectAttributesModelMap();

		Map<String, Object> modelAttributes = new HashMap<>();
		when(model.addAttribute(any(String.class), any(Object.class))).thenAnswer(invocation -> {
			modelAttributes.put(invocation.getArgument(0), invocation.getArgument(1));
			return model;
		});

		try {
			doThrow(new Exception("Duplicate phone number")).when(addService).addUserInfoAndTelInfo(any(), any());
			String viewName = addController.addFunction(customerInfoForm, result, model, redirectAttributes);
			assertEquals("add", viewName);
			assertTrue(modelAttributes.containsKey("error"));
			assertEquals("登録済みの電話番号は使用できません。", modelAttributes.get("error"));

		} catch (Exception e) {
			fail();
		}
	}
	
}