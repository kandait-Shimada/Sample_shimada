package com.example.demo.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
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
@Table(name = "TELINFO")
public class TelInfo {

	
	@Column(name = "customer_id")
	private String customer_ID;
	
	@Id
	private String tel;
	
	private Integer telorder;
	
	
//	@Column(name = "ins_date",updatable = false)
//    @CreatedDate
//	private String ins_Date;
//	
//	@Column(name = "upd_date")
//	@LastModifiedDate
//	private String upd_Date;
}
