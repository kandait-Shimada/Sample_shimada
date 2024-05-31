package com.example.demo.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.demo.entity.CustomerInfo;
import com.example.demo.entity.TelInfo;
import com.example.demo.entity.UserInfo;
import com.example.demo.form.CustomerInfoForm;
import com.example.demo.service.UpdateService;

@Controller
@RequestMapping("/update")
public class UpdateController {

	@Autowired
	private UpdateService updateService;

	@GetMapping("/{customer_ID}")
	public String updateFindById(Model model, @PathVariable Integer customer_ID) {
		Optional<CustomerInfo> result = updateService.findById(customer_ID);
		StringBuilder errorMessage = new StringBuilder();
		if (result.isPresent()) {
			CustomerInfo customerInfo = result.get();
			model.addAttribute("customer", customerInfo);
			return "update";
		} else {
			errorMessage.append("入力された顧客IDのデータが存在しません。");
			model.addAttribute("error", errorMessage.toString());
			return "search";
		}
	}

	@GetMapping
	public String updateDirectURL() {
		return "/search";
	}

	//画面から受け取った情報をDBに送信して更新
	@PostMapping
	public String updateFunction(@Validated CustomerInfoForm customerInfoForm,
			BindingResult bindingResult, Model model, RedirectAttributes redirectAttributes) throws Exception {

		StringBuilder errorMessage = new StringBuilder();

		// formからEntityに(userInfoテーブル)
		UserInfo userInfo = new UserInfo();
		userInfo.setCustomer_ID(customerInfoForm.getCustomer_ID());
		userInfo.setCustomer_name(customerInfoForm.getCustomer_name());
		userInfo.setEmail(customerInfoForm.getEmail());
		userInfo.setGender(customerInfoForm.getGender());
		userInfo.setAddress(customerInfoForm.getAddress());

		// formからEntityに(telInfoテーブル)
		List<TelInfo> telInfos = customerInfoForm.getTelInfos();
		List<TelInfo> addTelInfos = new ArrayList<>();
		int telorder = 1;
		for (TelInfo telInfo : telInfos) {
			if (telInfo.getTel() != null && !telInfo.getTel().isBlank()) {
				telInfo.setCustomer_ID(customerInfoForm.getCustomer_ID());
				telInfo.setTel(telInfo.getTel());
				telInfo.setTelorder(telorder++);
				addTelInfos.add(telInfo);
			} else {
				telorder++;
				continue;
			}
		}

		//入力内容チェック
		String[] tels = new String[5];
		int count = 0;
		for (TelInfo telInfo : telInfos) {
			tels[count] = telInfo.getTel();
			count++;
		}

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
		//メアド
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
			model.addAttribute("customer", customerInfoForm);
			return "update";
		}

		try {
			updateService.updateUserInfoAndTelInfo(userInfo, addTelInfos);

			StringBuilder successMessage = new StringBuilder();

			successMessage.append("顧客情報を更新しました。");

			model.addAttribute("success", successMessage.toString());
			model.addAttribute("customer", customerInfoForm);
			return "update";

		} catch (Exception e) {
			errorMessage.append("登録済みの電話番号は使用できません。");
			model.addAttribute("error", errorMessage.toString());
			model.addAttribute("customer", customerInfoForm);
			return "update";
		}

	}
}
