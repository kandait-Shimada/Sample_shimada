package com.example.demo.controller;



import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
/*
 * Login画面用のコントローラークラス
 * http://localhost:8080/login
 * 
 * UERNAMEとPASSWORDを入力して(中身は何でもよい)loginボタンを押下すると
 * 一覧画面に遷移する
 * 
 */
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.demo.entity.Login;
import com.example.demo.form.LoginForm;
import com.example.demo.service.LoginService;

@Controller
public class LoginController {

	@Autowired
	private LoginService loginService;

	// ログイン処理
	@GetMapping("/login")
	public String login() {
		return "login";
	}

	@PostMapping("/login")
	public String login(String username, String password, Model model) {

		try {
			boolean isAuthenticated = loginService.authenticate(username, password);

			if (isAuthenticated) {
				return "redirect:/sample";
			} else {
				model.addAttribute("error", "ユーザーネームとパスワードのどちらか、または両方が間違っています。");
				return "login";
			}
		} catch (Exception e) {
			// データベース接続の例外
			model.addAttribute("error", "データベースが確認できません。");
			return "login";
		}
	}

	// 新規登録処理
	@GetMapping("/signUp")
	public String newSignUp(Model model) {
		model.addAttribute("loginForm", new LoginForm());
		return "signUp";
	}

	@PostMapping("/signUp")
	public String signUp(@Validated LoginForm loginForm, BindingResult bindingResult,
			Model model, RedirectAttributes redirectAttributes) {

		try {
			// formからEntityに
			Login login = new Login();
			login.setUsername(loginForm.getUsername());
			login.setPassword(loginForm.getPassword());

			StringBuilder errorMessage = new StringBuilder();

			//空欄チェック
			if (StringUtils.isBlank(loginForm.getUsername()) || StringUtils.isBlank(loginForm.getPassword())) {
				// ユーザー名のエラーをチェック
				if (StringUtils.isBlank(loginForm.getUsername())) {
					errorMessage.append("ユーザー名は入力必須です。");
				}
				// パスワードのエラーをチェック
				if (StringUtils.isBlank(loginForm.getPassword())) {
					// 改行
					if (errorMessage.length() > 0) {
						errorMessage.append("<br>");
					}
					errorMessage.append("パスワードは入力必須です。");
				}
			}

			//文字数チェック
			if (bindingResult.hasErrors()) {
				// ユーザー名のエラーをチェック
				if (bindingResult.hasFieldErrors("username")) {
					if (errorMessage.length() > 0) {
						errorMessage.append("<br>");
					}
					errorMessage.append("ユーザー名は20文字以内で入力してください。");
				}
				// パスワードのエラーをチェック
				if (bindingResult.hasFieldErrors("password")) {
					// 改行
					if (errorMessage.length() > 0) {
						errorMessage.append("<br>");
					}
					errorMessage.append("パスワードは20文字以内で入力してください。");
				}
			}

			if (errorMessage.length() > 0) {
				model.addAttribute("error", errorMessage.toString());
				return "signUp";
			}
			
			// ユーザー情報の登録
			boolean check = loginService.registerUser(loginForm.getUsername(), loginForm.getPassword());
			if (check) {
				redirectAttributes.addFlashAttribute("registeredUsername", loginForm.getUsername());
				String hidePassword = "*".repeat(loginForm.getPassword().length());
				redirectAttributes.addFlashAttribute("registeredPassword", hidePassword);
				return "search";
			} else {
				// 登録が失敗した場合（ユーザー名が既に存在する場合）
				model.addAttribute("error", "このユーザー名は既に使用されています。");
				return "signUp";
			}
		} catch (Exception e) {
			// データベース接続の例外
			model.addAttribute("error", "データベースが確認できません。");
			return "signUp";
		}
	}

	@GetMapping("/success")
	public String registrationSuccess() {
		return "success";
	}
}
