package com.vtalent.binding.request;

import java.time.LocalDate;

import lombok.Data;

@Data
public class UserClass {
	
	private String fullName;
	
	private String emailId;
	
	private Long mobileNumber;
	
	private String gender;
	
	private String dateOfBirth;
	
	private Long ssn;

}
