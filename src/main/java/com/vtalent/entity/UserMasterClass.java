package com.vtalent.entity;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import lombok.Data;

@Data
@Entity 
@Table(name = "User_Master")
public class UserMasterClass {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "User_Id")
	private Integer userId;
	
	@Column(name = "Full_Name")
	private String fullName;
	
	@Column(name = "Email_ID")
	private String emailId;
	
	@Column(name = "Mobile_Number")
	private Long mobileNumber;
	
	@Column(name = "Gender")
	private String gender;
	
	@Column(name = "Password")
	private String password;
	
	@Column(name = "Account_Status")
	private String AccStatus;
	
	@Column(name = "Date_Of_Birth")
	private String dateOfBirth;
	
	@Column(name = "SSN_Number")
	private Long ssn;
	
	@Column(name = "Created_Date",updatable = false)
	@CreationTimestamp
	private LocalDate createDate;
	
	@Column(name = "Updated_Date",insertable = false)
	@UpdateTimestamp
	private LocalDate updateDate;
	
}
