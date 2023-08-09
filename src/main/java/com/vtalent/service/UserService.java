package com.vtalent.service;
import java.io.IOException;
import java.util.List;
import com.vtalent.binding.request.ActivateAccount;
import com.vtalent.binding.request.LogInClass;
import com.vtalent.binding.request.UserClass;

import lombok.extern.java.Log;
public interface UserService {
	//to save the user management fields
	//user registration form.....
	public boolean saveSubmitForm(UserClass user) throws IOException;
	
	//to activateAccount Screen
	public boolean activeUserAccount(ActivateAccount activateAccount);
	
	//view page get by id
	public UserClass getUserByUserId(Integer userId);
	
	//view page get all users
	public List<UserClass> getAllUsers();
	
	//view page delete by id 
	public boolean deleteByUserId(Integer userId);
	
	//view page soft delete (change account status)
	public boolean changeAccountStatus(Integer userId, String status);
	
	
	//login form page
	public String loginForm(LogInClass login );

	//forgot password page
	public String forgotPassword(String emailId) throws IOException;
}
