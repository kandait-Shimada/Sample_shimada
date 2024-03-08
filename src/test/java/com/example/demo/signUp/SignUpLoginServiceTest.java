package com.example.demo.signUp;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
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
class SignUpLoginServiceTest {

	@Mock
	private LoginRepository loginRepository;

	@InjectMocks
	private LoginService loginService;

	/*
	 * 項番1
	 */
	@Test
	void testRegisterUserSuccess() {
	    String username = "newUser";
	    String password = "newPass";
	    when(loginRepository.findByUsername(username)).thenReturn(null);

	    boolean result = loginService.registerUser(username, password);

	    assertTrue(result);
	    verify(loginRepository).save(any(Login.class));
	}

	/*
	 * 項番2
	 */
	@Test
	void testRegisterUserFailure() {
	    String username = "existingUser";
	    String password = "newPass";
	    when(loginRepository.findByUsername(username)).thenReturn(new Login());

	    boolean result = loginService.registerUser(username, password);

	    assertFalse(result);
	    verify(loginRepository, never()).save(any(Login.class));
	}
}
