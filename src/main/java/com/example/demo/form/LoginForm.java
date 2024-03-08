package com.example.demo.form;

import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
@Table(name = "login")
public class LoginForm {

	@NotNull
	@Size(min=0, max=20)
	private String username;	
	
	@NotNull
	@Size(min=0, max=20)
	private String password;
	    
	
}
