package com.example.demo.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
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
@Table(name = "USERINFO")
public class UserInfo {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private String customer_ID;

	private String customer_name;
	private String email;
	private String gender;
	private String address;
	
//	@Column(name = "ins_date",updatable = false)
//    @CreatedDate
//	private String ins_Date;
//	
//	@Column(name = "upd_date")
//	@LastModifiedDate
//	private String upd_Date;
}
