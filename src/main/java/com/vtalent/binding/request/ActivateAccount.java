package com.vtalent.binding.request;

import lombok.Data;

@Data
public class ActivateAccount {
	
	private String emailId;
	
	private String TempPassword;
	
	private String newPassword;
	
	private String confirmPassword;
}
