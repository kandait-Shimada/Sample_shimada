package com.example.demo.form;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.Range;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class SampleForm {

	// idの自動生成実装で削除
//	@NotNull
//	@Min(0)
	private Integer id;
	
	@NotBlank
	@Length(min=1, max=100)
	private String name;
	
	private Integer gender;
	
	@NotNull
	@Range(min=0, max=120)
	private Integer age;
	
	/* 「登録」か「更新」か見分ける */
    private Boolean NewSample;

}
