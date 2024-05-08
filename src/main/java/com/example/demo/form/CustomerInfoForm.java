package com.example.demo.form;

import java.util.List;

import org.hibernate.validator.constraints.Length;

import com.example.demo.entity.TelInfo;
import com.example.demo.repository.AddValid;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class CustomerInfoForm {

	private Integer customer_ID;

	@NotBlank
	@Length(min = 1, max = 20)
	private String customer_name;

	@NotBlank
	@Length(min = 1, max = 100)
	private String email;

	@NotBlank
	@Length(min = 1, max = 10)
	private String gender;

	@NotBlank
	@Length(min = 1, max = 20)
	private String address;

	@NotEmpty(groups = { AddValid.class })
//	@Range(max = 11)
	@Size(min = 1, max = 5)
	private String[] tel;
	
	@NotEmpty
	private List<TelInfo> telInfos; 
}

