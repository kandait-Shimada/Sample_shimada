package com.example.demo.login;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.ui.Model;

import com.example.demo.controller.LoginController;
import com.example.demo.service.LoginService;

class LoginTest {

	@InjectMocks
	private LoginController loginController;

	@Mock
	private LoginService loginService;

	@Mock
	private Model model;

	@BeforeEach
	void setUp() {
		MockitoAnnotations.openMocks(this);
	}

	/*
	 * 項番2
	 */
	@Test
    void testLoginSuccess() {
        when(loginService.authenticate("test", "pass")).thenReturn(true);

        String result = loginController.login("test", "pass", model);
        
        assertEquals("redirect:/sample", result);

	}

	/*
	 * 項番3
	 */
	@Test
    void testLoginFailure() {
        when(loginService.authenticate("fail", "pass")).thenReturn(false);

        String result = loginController.login("fail", "pass", model);

        assertEquals("login", result);
        verify(model, times(1)).addAttribute("error", "ユーザーネームとパスワードのどちらか、または両方が間違っています。");
    }

	/*
	 * 項番4
	 */
	@Test
    void testLoginThrowsException() {
        when(loginService.authenticate("test", "pass")).thenThrow(new RuntimeException("Database down"));

        String result = loginController.login("test", "pass", model);

        assertEquals("login", result);
        verify(model, times(1)).addAttribute("error", "データベースが確認できません。");
    }
}
