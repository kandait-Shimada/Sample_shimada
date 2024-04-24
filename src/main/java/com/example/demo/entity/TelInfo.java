package com.example.demo.entity;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import jakarta.persistence.Column;
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
@Table(name = "TELINFO")
public class TelInfo {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer customer_ID;
	
	private String tel;
	private Integer telorder;
	
	
	@Column(name = "ins_date",updatable = false)
    @CreatedDate
	private String ins_Date;
	
	@Column(name = "upd_date")
	@LastModifiedDate
	private String upd_Date;
}
