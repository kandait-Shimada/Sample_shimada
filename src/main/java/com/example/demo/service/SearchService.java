package com.example.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import com.example.demo.entity.CustomerInfo;
import com.example.demo.entity.TelInfo;
import com.example.demo.repository.SearchRepository;

@Service
public class SearchService {

    @Autowired
    private SearchRepository searchRepository;

    public List<CustomerInfo> search(CustomerInfo customerInfo) {
        Specification<CustomerInfo> spec = Specification.where(null);

        String check = "指定なし";
        
        Integer customer_ID = customerInfo.getCustomer_ID();
        String customer_name = customerInfo.getCustomer_name();
        List<TelInfo> telInfos = customerInfo.getTelInfos();
        String tel = "";
        if(!telInfos.isEmpty()) {
        	tel = telInfos.get(0).getTel();
        }
        String email = customerInfo.getEmail();
        String gender = customerInfo.getGender();
        if(gender.equals(check)) {
        	gender = "";
        }
        String address = customerInfo.getAddress();
       
        
        
        if (customer_ID != null) {
            spec = spec.and(SearchSpecifications.customer_IDLike(customer_ID));
        }
        if (!customer_name.isBlank()) {
            spec = spec.and(SearchSpecifications.customer_nameLike(customer_name));
        }
        if (!tel.isBlank()) {
            spec = spec.and(SearchSpecifications.telLike(tel));
        }
        if (!email.isBlank()) {
            spec = spec.and(SearchSpecifications.emailLike(email));
        }
        if (!gender.isBlank()) {
            spec = spec.and(SearchSpecifications.genderLike(gender));
        }
        if (!address.isBlank()) {
            spec = spec.and(SearchSpecifications.addressLike(address));
        }

        return searchRepository.findAll(spec);
    }
}

