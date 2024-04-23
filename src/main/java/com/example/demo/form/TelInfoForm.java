package com.example.demo.form;

import org.hibernate.validator.constraints.Range;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class TelInfoForm {

	
	@NotNull
	@Min(0)
	private Integer customerID;
	
	@NotNull
	@Range(min=0, max=11)
	private String tel;
	
	@NotNull
	private Integer telOrder;
	
	
	private String insDate;
	private String updDate;

}
