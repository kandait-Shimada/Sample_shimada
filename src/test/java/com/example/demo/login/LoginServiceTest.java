package com.example.demo.login;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.example.demo.entity.Login;
import com.example.demo.repository.LoginRepository;
import com.example.demo.service.LoginService;

@ExtendWith(MockitoExtension.class)
class LoginServiceTest {

	@Mock
	private LoginRepository loginRepository;

	@InjectMocks
	private LoginService loginService;

	/*
	 * 項番1
	 */
	@Test
	void testAuthenticateSuccess() {
		String username = "user";
		String password = "pass";
		when(loginRepository.findByUsernameAndPassword(username, password)).thenReturn(new Login());

		boolean result = loginService.authenticate(username, password);

		assertTrue(result);
	}
}