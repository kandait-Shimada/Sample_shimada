package com.example.demo.signUp;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.util.ArrayList;
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
import org.springframework.validation.FieldError;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.demo.controller.LoginController;
import com.example.demo.form.LoginForm;
import com.example.demo.service.LoginService;

class SignUpTest {

	@InjectMocks
	private LoginController loginController;

	@Mock
	private LoginService loginService;

	@Mock
	private Model model;

	@Mock
	private RedirectAttributes redirectAttributes;

	@BeforeEach
	void setUp() {
		MockitoAnnotations.openMocks(this);
	}

	/*
	 * テスト用ログインフォーム
	 */
	private LoginForm createValidLoginForm(String username, String password) {
		LoginForm loginForm = new LoginForm();
		loginForm.setUsername(username);
		loginForm.setPassword(password);
		return loginForm;
	}

	/*
	 * テスト用文字数チェック（正常時）
	 */
	private BindingResult createBindingResult() {
		return mock(BindingResult.class);
	}

	/*
	 * テスト用文字数チェック（単一エラー発生時）
	 */
	private BindingResult createBindingResultWithErrors(String fieldName, String errorCode, String defaultMessage) {
		BindingResult bindingResult = mock(BindingResult.class);
		FieldError fieldError = new FieldError("loginForm", fieldName, null, false, new String[]{errorCode}, null, defaultMessage);
		when(bindingResult.hasErrors()).thenReturn(true);
		when(bindingResult.hasFieldErrors(fieldName)).thenReturn(true);
		when(bindingResult.getFieldError()).thenReturn(fieldError);
		return bindingResult;
	}
	
	/*
	 * テスト用文字数チェック（複数エラー発生時）
	 */
	private BindingResult createBindingResultWithUserNameAndPasswordErrors(Map<String, String> errors) {
	    BindingResult bindingResult = mock(BindingResult.class);
	    List<FieldError> fieldErrors = new ArrayList<>();

	    for (Map.Entry<String, String> entry : errors.entrySet()) {
	        FieldError fieldError = new FieldError("loginForm", entry.getKey(), null, false, new String[]{"Size"}, null, entry.getValue());
	        fieldErrors.add(fieldError);
	    }

	    when(bindingResult.hasErrors()).thenReturn(!errors.isEmpty());
	    when(bindingResult.getFieldErrors()).thenReturn(fieldErrors);
	    errors.keySet().forEach(fieldName -> 
	        when(bindingResult.hasFieldErrors(fieldName)).thenReturn(true)
	    );

	    return bindingResult;
	}

	

	/*
	 * 項番3
	 */
	@Test
	    void testSignUpSuccess() {
	        when(loginService.registerUser("test", "pass")).thenReturn(true);

	        String viewName = loginController.signUp(createValidLoginForm("test", "pass"), createBindingResult(), model, redirectAttributes);

	        assertEquals("redirect:/success", viewName);
	        verify(loginService, times(1)).registerUser("test", "pass");
	    }

	/*
	 * 項番4
	 */
	@Test
	    void testSignUpCharacterLimitCheck1() {
	        when(loginService.registerUser("characterlimitcheck1", "pass")).thenReturn(true);

	        String viewName = loginController.signUp(createValidLoginForm("characterlimitcheck1", "pass"), createBindingResult(), model, redirectAttributes);

	        assertEquals("redirect:/success", viewName);
	        verify(loginService, times(1)).registerUser("characterlimitcheck1", "pass");
	    }

	/*
	 * 項番5
	 */
	@Test
	    void testSignUpCharacterLimitCheck2() {
	        when(loginService.registerUser("test", "characterlimitcheck2")).thenReturn(true);

	        String viewName = loginController.signUp(createValidLoginForm("test", "characterlimitcheck2"), createBindingResult(), model, redirectAttributes);

	        assertEquals("redirect:/success", viewName);
	        verify(loginService, times(1)).registerUser("test", "characterlimitcheck2");
	    }

	/*
	 * 項番6
	 */
	@Test
	void testSignUpCharacterLimitCheck21() {
		String expectedErrorMessage = "ユーザー名は20文字以内で入力してください。";
		BindingResult bindingResult = createBindingResultWithErrors("username", "Size", "ユーザー名は20文字以内で入力してください。");
		String viewName = loginController.signUp(createValidLoginForm("characterlimitcheck21", "pass"), bindingResult,
				model, redirectAttributes);
		assertEquals("signUp", viewName);
		verify(model, times(1)).addAttribute("error", expectedErrorMessage);
	}

	/*
	 * 項番7
	 */
	@Test
	void testSignUpCharacterLimitCheck22() {
		String expectedErrorMessage = "パスワードは20文字以内で入力してください。";
		BindingResult bindingResult = createBindingResultWithErrors("password", "Size", "パスワードは20文字以内で入力してください。");
		String viewName = loginController.signUp(createValidLoginForm("test", "characterlimitcheck21"), bindingResult,
				model, redirectAttributes);

		assertEquals("signUp", viewName);
		verify(model, times(1)).addAttribute("error", expectedErrorMessage);
	}

	/*
	 * 項番8
	 */
	@Test
	    void testSignUpUsernameAlreadyExists() {
	        when(loginService.registerUser("test", "pass")).thenReturn(false);

	        String viewName = loginController.signUp(createValidLoginForm("test", "pass"), createBindingResult(), model, redirectAttributes);

	        assertEquals("signUp", viewName);
	        verify(model, times(1)).addAttribute("error", "このユーザー名は既に使用されています。");

	    }

	/*
	 * 項番9
	 */
	@Test
	    void testSignUpThrowsException() {
	        when(loginService.registerUser("test", "pass")).thenThrow(new RuntimeException("Database down"));

	        String viewName = loginController.signUp(createValidLoginForm("test", "pass"), createBindingResult(), model, redirectAttributes);

	        assertEquals("signUp", viewName);
	        verify(model, times(1)).addAttribute("error", "データベースが確認できません。");
	    }

	/*
	 * 項番10
	 */
	@Test
	void testSignUpUserNameNullCheck() {
		String expectedErrorMessage ="ユーザー名は入力必須です。";
		String viewName = loginController.signUp(createValidLoginForm("", "pass"), createBindingResult(),
				model, redirectAttributes);
		assertEquals("signUp", viewName);
		verify(model, times(1)).addAttribute("error", expectedErrorMessage);
	}

	/*
	 * 項番11
	 */
	@Test
	void testSignUpPasswordNullCheck() {
		String expectedErrorMessage ="パスワードは入力必須です。";
		String viewName = loginController.signUp(createValidLoginForm("test", ""), createBindingResult(),
				model, redirectAttributes);
		assertEquals("signUp", viewName);
		verify(model, times(1)).addAttribute("error", expectedErrorMessage);
	}
	
	/*
	 * 項番12
	 */
	@Test
	void testSignUpUserNameAndPasswordNullCheck() {
		String expectedErrorMessage ="ユーザー名は入力必須です。<br>パスワードは入力必須です。";
		String viewName = loginController.signUp(createValidLoginForm("", ""), createBindingResult(),
				model, redirectAttributes);
		assertEquals("signUp", viewName);
		verify(model, times(1)).addAttribute("error", expectedErrorMessage);
	}
	
	/*
	 * 項番13
	 */
	@Test
	void testSignUpUserNameBlankCheck() {
		String expectedErrorMessage ="ユーザー名は入力必須です。";
		String viewName = loginController.signUp(createValidLoginForm(" ", "pass"), createBindingResult(),
				model, redirectAttributes);
		assertEquals("signUp", viewName);
		verify(model, times(1)).addAttribute("error", expectedErrorMessage);
	}
	
	/*
	 * 項番14
	 */
	@Test
	void testSignUpPasswordBlankCheck() {
		String expectedErrorMessage ="パスワードは入力必須です。";
		String viewName = loginController.signUp(createValidLoginForm("test", " "), createBindingResult(),
				model, redirectAttributes);
		assertEquals("signUp", viewName);
		verify(model, times(1)).addAttribute("error", expectedErrorMessage);
	}

	/*
	 * 項番15
	 */
	@Test
	void testSignUpUserNameAndPasswordBlankCheck() {
		String expectedErrorMessage ="ユーザー名は入力必須です。<br>パスワードは入力必須です。";
		String viewName = loginController.signUp(createValidLoginForm(" ", " "), createBindingResult(),
				model, redirectAttributes);
		assertEquals("signUp", viewName);
		verify(model, times(1)).addAttribute("error", expectedErrorMessage);
	}
	
	/*
	 * 項番16
	 */
	@Test
	void testSignUpUserNameAndPasswordCharacterLimitCheck() {
	    Map<String, String> errors = new HashMap<>();
	    errors.put("username", "ユーザー名は20文字以内で入力してください。");
	    errors.put("password", "パスワードは20文字以内で入力してください。");

	    String expectedErrorMessage = "ユーザー名は20文字以内で入力してください。<br>パスワードは20文字以内で入力してください。";
	    BindingResult bindingResult = createBindingResultWithUserNameAndPasswordErrors(errors);

	    String viewName = loginController.signUp(createValidLoginForm("characterlimitcheck23", "characterlimitcheck24"), bindingResult, model, redirectAttributes);

	    assertEquals("signUp", viewName);
	    verify(model, times(1)).addAttribute("error", expectedErrorMessage);
	}

	
	/*
	 * 項番17
	 */
	@Test
	void testSignUpUserNameLimitAndPasswordNullCheck() {
		BindingResult bindingResult = createBindingResultWithErrors("username", "Size", "ユーザー名は20文字以内で入力してください。");
		String expectedErrorMessage ="パスワードは入力必須です。<br>ユーザー名は20文字以内で入力してください。";
		String viewName = loginController.signUp(createValidLoginForm("characterlimitcheck25", ""), bindingResult,
				model, redirectAttributes);
		assertEquals("signUp", viewName);
		verify(model, times(1)).addAttribute("error", expectedErrorMessage);
	}
	
	/*
	 * 項番18
	 */
	@Test
	void testSignUpUserNameNullAndPasswordLimitCheck() {
		BindingResult bindingResult = createBindingResultWithErrors("password", "Size", "パスワードは20文字以内で入力してください。");
		String expectedErrorMessage ="ユーザー名は入力必須です。<br>パスワードは20文字以内で入力してください。";
		String viewName = loginController.signUp(createValidLoginForm("", "characterlimitcheck26"), bindingResult,
				model, redirectAttributes);
		assertEquals("signUp", viewName);
		verify(model, times(1)).addAttribute("error", expectedErrorMessage);
	}
	
}