package com.vtalent.controller;

import java.io.IOException;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.vtalent.binding.request.ActivateAccount;
import com.vtalent.binding.request.LogInClass;
import com.vtalent.binding.request.UserClass;
import com.vtalent.service.UserService;

@RestController
public class UserRestController {

	@Autowired
	private UserService userService;

	@PostMapping("/user")
	public ResponseEntity<String> userRegistration(@RequestBody UserClass user) throws IOException {

		boolean saveSubmitForm = userService.saveSubmitForm(user);

		if (saveSubmitForm) {
			return new ResponseEntity<>("Registration success", HttpStatus.OK);
		} else {
			return new ResponseEntity<>("Registration Failed", HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@PostMapping("/activate")
	public ResponseEntity<String> activateAccount(@RequestBody ActivateAccount account) {

		boolean activeUserAccount = userService.activeUserAccount(account);
		if (activeUserAccount) {

			return new ResponseEntity<>("Account Activated", HttpStatus.OK);
		} else {
			return new ResponseEntity<>("invalid temp password", HttpStatus.BAD_REQUEST);
		}

	}

	@GetMapping("/users")
	public ResponseEntity<List<UserClass>> getAllUsers() {
		List<UserClass> allUsers = userService.getAllUsers();
		return new ResponseEntity<>(allUsers, HttpStatus.OK);
	}

	@GetMapping("/user/{userId}")
	public ResponseEntity<UserClass> getUserById(@PathVariable Integer userId) {
		UserClass userByUserId = userService.getUserByUserId(userId);
		return new ResponseEntity<>(userByUserId, HttpStatus.OK);
	}

	@GetMapping("/delete/{userId}")
	public ResponseEntity<String> deleteById(@PathVariable Integer userId) {
		boolean deleteByUserId = userService.deleteByUserId(userId);
		if (deleteByUserId) {
			return new ResponseEntity<>("deleted", HttpStatus.OK);
		} else {
			return new ResponseEntity<>("Failded", HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@GetMapping("/status/{userId}/{status}")
	public ResponseEntity<String> statusChange(@PathVariable Integer userId,@PathVariable String status){
		boolean changeAccountStatus = userService.changeAccountStatus(userId, status);
		if (changeAccountStatus) {
			return new ResponseEntity<>("Stats Changed",HttpStatus.OK);
		}
		else {
			return new ResponseEntity<>("Failed to change",HttpStatus.INTERNAL_SERVER_ERROR);
			
		}	
	}
	
	@PostMapping("/login")
	public ResponseEntity<String> login(@RequestBody LogInClass login){
		String loginForm = userService.loginForm(login);
		return new ResponseEntity<>(loginForm,HttpStatus.OK);
	}
	@GetMapping("/forgotpwd/{email}")
	public ResponseEntity<String> forgotPwd(@PathVariable String email) throws IOException{
		String forgotPassword = userService.forgotPassword(email);
		return new ResponseEntity<>(forgotPassword,HttpStatus.OK);
		
	}

}
