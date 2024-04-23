package com.example.demo.controller;

import java.util.ArrayList;
import java.util.List;

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

	@GetMapping
	public String newAdd(Model model) {

		model.addAttribute("userInfo", new UserInfo());
		model.addAttribute("telInfos", List.of(new TelInfo()));
		return "add";
	}

	@PostMapping("/add")
	public String addFunction(@Validated CustomerInfoForm customerInfoForm, BindingResult bindingResult,
			Model model, RedirectAttributes redirectAttributes) {

		// formからEntityに(userInfoテーブル)
		UserInfo userInfo = new UserInfo();
		userInfo.setCustomername(customerInfoForm.getCustomername());
		userInfo.setEmail(customerInfoForm.getEmail());
		userInfo.setGender(customerInfoForm.getGender());
		userInfo.setAddress(customerInfoForm.getAddress());
		
		UserInfo savedCustomerId = addService.add1(userInfo);

		// formからEntityに(telInfoテーブル)
		List<TelInfo> telInfos = new ArrayList<>();
		
		String[] tels = customerInfoForm.getTel();
		int telOrder = 1;
		
		for(String tel : tels) {
			if(tel != null && !tel.isEmpty()) {
				TelInfo telInfo = new TelInfo();
				telInfo.setCustomerID(savedCustomerId.getCustomerID()); 
				telInfo.setTel(tel);
				telInfo.setTelOrder(telOrder++);
				telInfos.add(telInfo);
				}
			}
		addService.add2(telInfos);
		
		StringBuilder successMessage = new StringBuilder();

		successMessage.append("登録に成功しました。");
		
		model.addAttribute("success", successMessage.toString());
		return "add";
	}

}
