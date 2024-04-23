package com.example.demo.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class TelInfo {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer customerID;
	private String tel;
	private Integer telOrder;
	private String insDate;
	private String updDate;
}
