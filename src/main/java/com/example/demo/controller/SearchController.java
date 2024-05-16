package com.example.demo.controller;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.demo.entity.CustomerInfo;
import com.example.demo.entity.TelInfo;
import com.example.demo.form.CustomerInfoForm;
import com.example.demo.service.SearchService;

@Controller
public class SearchController {

	@Autowired
	private SearchService searchService;

	//検索フォーム
	@GetMapping("/search")
	public String searchForm(Model model) {
		CustomerInfoForm customerInfoForm = new CustomerInfoForm();
		model.addAttribute("customerInfo", customerInfoForm);
		return "search";
	}

	//検索結果
	@PostMapping("/search")
	public String searchFunction(@ModelAttribute CustomerInfoForm customerInfoForm, BindingResult result, Model model)
			throws Exception {

		StringBuilder errorMessage = new StringBuilder();

			CustomerInfo customerInfo = new CustomerInfo();
			customerInfo.setCustomer_ID(customerInfoForm.getCustomer_ID());
			
			customerInfo.setCustomer_name(customerInfoForm.getCustomer_name());
			String[] formTel = customerInfoForm.getTel();
			// CustomerInfoForm、CustomerInfoの型を合わせるために追加
			List<TelInfo> telInfos = new ArrayList<>();
			if (formTel != null && formTel.length > 0) {
				TelInfo telInfo = new TelInfo();
				telInfo.setTel(formTel[0]);
				telInfos.add(telInfo);
				String tel1Check = formTel[0];
				if (!StringUtils.isBlank(tel1Check)) {
					if (!tel1Check.matches("\\d+")) {
						if (errorMessage.length() > 0) {
							errorMessage.append("<br>");
						}
						errorMessage.append("電話番号は数字のみで入力してください。");
					}
				}
			}
			customerInfo.setTelInfos(telInfos);
			customerInfo.setEmail(customerInfoForm.getEmail());
			customerInfo.setGender(customerInfoForm.getGender());
			customerInfo.setAddress(customerInfoForm.getAddress());
			
			if (errorMessage.length() > 0) {
				model.addAttribute("error", errorMessage.toString());
				return "search";
			}
			
			try {
			List<CustomerInfo> searchResult = searchService.search(customerInfo);
			model.addAttribute("result", searchResult);
			return "search";
		} catch (Exception e) {
			errorMessage.append("エラーです。");
			model.addAttribute("error", errorMessage);
			return "search";
		}

	}
}