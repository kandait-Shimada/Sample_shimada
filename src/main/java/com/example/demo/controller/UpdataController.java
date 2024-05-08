package com.example.demo.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.demo.entity.CustomerInfo;
import com.example.demo.entity.TelInfo;
import com.example.demo.entity.UserInfo;
import com.example.demo.form.CustomerInfoForm;
import com.example.demo.service.UpdataService;

@Controller
public class UpdataController {

	@Autowired
	private UpdataService updataService;
	
	
	//データを保持して更新画面に遷移
	@GetMapping("/customer/updata/{customer_ID}")
	public String updataFindById(Model model,@PathVariable Integer customer_ID) {
		
		Optional<CustomerInfo> result = updataService.findById(customer_ID);
		    CustomerInfo customer = result.get();
//		    while (customer.getTelInfos().size() < 5) {
//	            customer.getTelInfos().add(new TelInfo()); 
//	        }
	        model.addAttribute("customer", customer);
	    
//		model.addAttribute("result", result.get());
		return "updata";
	}
	
	//画面から受け取った情報をDBに送信して更新
	@PostMapping("/updata")
	public String updataFunction(@Validated CustomerInfoForm customerInfoForm,  
			BindingResult bindingResult, Model model, RedirectAttributes redirectAttributes) {

		// formからEntityに(userInfoテーブル)
		UserInfo userInfo = new UserInfo();
		userInfo.setCustomer_ID(customerInfoForm.getCustomer_ID());
		userInfo.setCustomer_name(customerInfoForm.getCustomer_name());
		userInfo.setEmail(customerInfoForm.getEmail());
		userInfo.setGender(customerInfoForm.getGender());
		userInfo.setAddress(customerInfoForm.getAddress());
		
		updataService.updata1(userInfo);
		
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
		updataService.updata2(addTelInfos);
		
		StringBuilder successMessage = new StringBuilder();

		successMessage.append("更新に成功しました。");
		
//		model.addAttribute("success", successMessage.toString());
		model.addAttribute("customer", customerInfoForm);
		return "updata";
	}
}
