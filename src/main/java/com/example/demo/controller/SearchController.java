package com.example.demo.controller;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.demo.entity.TelInfo;
import com.example.demo.entity.UserInfo;
import com.example.demo.form.CustomerInfoForm;
import com.example.demo.service.AddService;

@Controller
public class AddController {

	@Autowired
	private AddService addService;

	@GetMapping("/add")
	public String addForm(Model model) {

		//		model.addAttribute("userInfo", new UserInfo());
		//		model.addAttribute("telInfos", List.of(new TelInfo()));
		return "add";
	}

	@PostMapping("/add")
	public String addFunction(@Validated CustomerInfoForm customerInfoForm, BindingResult bindingResult,
			Model model, RedirectAttributes redirectAttributes) throws Exception {

		StringBuilder errorMessage = new StringBuilder();

		// formからEntityに(userInfoテーブル)
		UserInfo userInfo = new UserInfo();
		userInfo.setCustomer_name(customerInfoForm.getCustomer_name());
		userInfo.setEmail(customerInfoForm.getEmail());
		userInfo.setGender(customerInfoForm.getGender());
		userInfo.setAddress(customerInfoForm.getAddress());

		//		UserInfo savedCustomerId = addService.add1(userInfo);

		// formからEntityに(telInfoテーブル)
		List<TelInfo> telInfos = new ArrayList<>();

		String[] tels = customerInfoForm.getTel();
		int telorder = 1;

		for (String tel : tels) {
			if (tel.isBlank()) {
				telorder++;
				continue;
			} else {
				TelInfo telInfo = new TelInfo();
				//				telInfo.setCustomer_ID(savedCustomerId.getCustomer_ID()); 
				telInfo.setTel(tel);
				telInfo.setTelorder(telorder++);
				telInfos.add(telInfo);
			}
		}

		//入力内容チェック
		String tel1Check = tels[0];
		String tel2Check = tels[1];
		String tel3Check = tels[2];
		String tel4Check = tels[3];
		String tel5Check = tels[4];
		int telLimit = 11;

		//必須項目の入力チェック
		//顧客名
		if (StringUtils.isBlank(customerInfoForm.getCustomer_name())) {
			errorMessage.append("顧客名は入力必須です。");
		}
		//電話番号①
		if (StringUtils.isBlank(tel1Check)) {
			// 改行
			if (errorMessage.length() > 0) {
				errorMessage.append("<br>");
			}
			errorMessage.append("電話番号①は入力必須です。");
		}
		//メールアドレス
		if (StringUtils.isBlank(customerInfoForm.getEmail())) {
			if (errorMessage.length() > 0) {
				errorMessage.append("<br>");
			}
			errorMessage.append("メールアドレスは入力必須です。");
		}
		//性別
		if (StringUtils.isBlank(customerInfoForm.getGender())) {
			if (errorMessage.length() > 0) {
				errorMessage.append("<br>");
			}
			errorMessage.append("性別は入力必須です。");
		}
		//住所
		if (StringUtils.isBlank(customerInfoForm.getAddress())) {
			if (errorMessage.length() > 0) {
				errorMessage.append("<br>");
			}
			errorMessage.append("住所は入力必須です。");
		}

		//文字数チェック
		//顧客名
		if (bindingResult.hasErrors()) {
			if (bindingResult.hasFieldErrors("customer_name")) {
				if (errorMessage.length() > 0) {
					errorMessage.append("<br>");
				}
				errorMessage.append("顧客名は20文字以内で入力してください。");
			}
			//メールアドレス
			if (bindingResult.hasFieldErrors("email")) {
				// 改行
				if (errorMessage.length() > 0) {
					errorMessage.append("<br>");
				}
				errorMessage.append("メールアドレスは100文字以内で入力してください。");
			}
			//性別
			if (bindingResult.hasFieldErrors("gender")) {
				// 改行
				if (errorMessage.length() > 0) {
					errorMessage.append("<br>");
				}
				errorMessage.append("性別はブラウザからボタンで入力してください。");
			}
			//住所
			if (bindingResult.hasFieldErrors("address")) {
				// 改行
				if (errorMessage.length() > 0) {
					errorMessage.append("<br>");
				}
				errorMessage.append("住所は20文字以内で入力してください。");
			}
		}
		//電話番号①
		if (!StringUtils.isBlank(tel1Check)) {
			if (telLimit < tel1Check.length() || !tel1Check.matches("\\d+")) {
				if (errorMessage.length() > 0) {
					errorMessage.append("<br>");
				}
				errorMessage.append("電話番号①は数字のみで11文字以内で入力してください。");
			}
		}
		//電話番号②
		if (!StringUtils.isBlank(tel2Check)) {
			if (telLimit < tel2Check.length() || !tel2Check.matches("\\d+")) {
				if (errorMessage.length() > 0) {
					errorMessage.append("<br>");
				}
				errorMessage.append("電話番号②は数字のみで11文字以内で入力してください。");
			}
		}
		//電話番号③
		if (!StringUtils.isBlank(tel3Check)) {
			if (telLimit < tel3Check.length() || !tel3Check.matches("\\d+")) {
				if (errorMessage.length() > 0) {
					errorMessage.append("<br>");
				}
				errorMessage.append("電話番号③は数字のみで11文字以内で入力してください。");
			}
		}
		//電話番号④
		if (!StringUtils.isBlank(tel4Check)) {
			if (telLimit < tel4Check.length() || !tel4Check.matches("\\d+")) {
				if (errorMessage.length() > 0) {
					errorMessage.append("<br>");
				}
				errorMessage.append("電話番号④は数字のみで11文字以内で入力してください。");
			}
		}
		//電話番号⑤
		if (!StringUtils.isBlank(tel5Check)) {
			if (telLimit < tel5Check.length() || !tel5Check.matches("\\d+")) {
				if (errorMessage.length() > 0) {
					errorMessage.append("<br>");
				}
				errorMessage.append("電話番号⑤は数字のみで11文字以内で入力してください。");
			}
		}
		//入力内容にミスがあるときは戻る
		if (errorMessage.length() > 0) {
			model.addAttribute("error", errorMessage.toString());
			return "add";
		}

		try {
			//		addService.add2(telInfos);
			// ロールバック制御用メソッドの呼び出し
			addService.addUserInfoAndTelInfo(userInfo, telInfos);
			StringBuilder successMessage = new StringBuilder();

			successMessage.append("顧客情報を登録しました。");

			model.addAttribute("success", successMessage.toString());
			return "add";
		} catch (Exception e) {
			errorMessage.append("登録済みの電話番号は使用できません。");
			model.addAttribute("error", errorMessage.toString());
			return "add";

		}
	}
}
