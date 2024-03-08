package com.example.demo.signUp;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

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
	 * テスト用文字数チェック（エラー発生時）
	 */
	private BindingResult createBindingResultWithErrors(String fieldName, String errorCode, String defaultMessage) {
		BindingResult bindingResult = mock(BindingResult.class);
		FieldError fieldError = new FieldError("loginForm", fieldName, defaultMessage);
		when(bindingResult.hasErrors()).thenReturn(true);
		when(bindingResult.getFieldError()).thenReturn(fieldError);
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
		BindingResult bindingResult = createBindingResultWithErrors("username", "Size", "20文字以内で入力してください。");
		String viewName = loginController.signUp(createValidLoginForm("characterlimitcheck21", "pass"), bindingResult,
				model, redirectAttributes);
		assertEquals("signUp", viewName);
		verify(model, times(1)).addAttribute("error", "20文字以内で入力してください。");
	}

	/*
	 * 項番7
	 */
	@Test
	void testSignUpCharacterLimitCheck22() {
		BindingResult bindingResult = createBindingResultWithErrors("password", "Size", "20文字以内で入力してください。");
		String viewName = loginController.signUp(createValidLoginForm("test", "characterlimitcheck21"), bindingResult,
				model, redirectAttributes);

		assertEquals("signUp", viewName);
		verify(model, times(1)).addAttribute("error", "20文字以内で入力してください。");
	}

	/*
	 * 項番8
	 */
	@Test
	    void testSignUpUsernameAlreadyExists() {
	        when(loginService.registerUser("test", "pass")).thenReturn(false);

	        String viewName = loginController.signUp(createValidLoginForm("test", "pass"), createBindingResult(), model, redirectAttributes);

	        assertEquals("signUp", viewName);
	        verify(model, times(1)).addAttribute("error", "このUSERNAMEは既に使用されています。");

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

}