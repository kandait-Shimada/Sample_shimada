package com.example.demo.form;

import org.hibernate.validator.constraints.Length;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class UserInfoForm {

	private Integer customerID;
	
	@NotBlank
	@Length(min=1, max=20)
	private String customername;
	
	@NotBlank
	@Length(min=1, max=100)
	private String email;
	
	@NotBlank
	@Length(min=1, max=10)
	private String gender;
	
	@NotBlank
	@Length(min=1, max=20)
	private String address;
	
	private String insDate;
	private String updDate;
}
