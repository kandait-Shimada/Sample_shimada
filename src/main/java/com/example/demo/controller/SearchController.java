package com.example.demo.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
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
    public String searchFunction(@ModelAttribute CustomerInfoForm customerInfoForm, Model model) {
        
    	CustomerInfo customerInfo = new CustomerInfo();
    	customerInfo.setCustomer_ID(customerInfoForm.getCustomer_ID());
    	customerInfo.setCustomer_name(customerInfoForm.getCustomer_name());
    	String[] formTel = customerInfoForm.getTel();
    	List<TelInfo> telInfos = new ArrayList<>();
    	if(formTel != null && formTel.length > 0) {
    		TelInfo telInfo = new TelInfo();
    		telInfo.setTel(formTel[0]);
    		telInfos.add(telInfo);
    	}
    	customerInfo.setTelInfos(telInfos);
    	customerInfo.setEmail(customerInfoForm.getEmail());
		customerInfo.setGender(customerInfoForm.getGender());
		customerInfo.setAddress(customerInfoForm.getAddress());
    	
    	

    	
    	List<CustomerInfo> result = searchService.search(customerInfo);
        model.addAttribute("result", result);
        return "search";
    }
	
//	String customer_ID = customerInfoForm.getCustomer_ID();
//	String customer_name = customerInfoForm.getCustomer_name();
//	String[] formTel = customerInfoForm.getTel();
//	String tel = null;
//	
//	if(formTel != null && formTel.length > 0) {
//		tel = formTel[0];
//	}
//	
//	String email = customerInfoForm.getEmail();
//    String gender = customerInfoForm.getGender();
//    String check = "指定なし";
//    
//    if(gender.equals(check)) {
//    	gender = null;
//    }
//    String address = customerInfoForm.getAddress();
}