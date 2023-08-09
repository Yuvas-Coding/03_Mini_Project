package com.vtalent.service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;

import com.vtalent.RandomCodeGenerator.RandomCodeGenerator;
import com.vtalent.ReadEmailBody.ReadRegEmailBody;
import com.vtalent.binding.request.ActivateAccount;
import com.vtalent.binding.request.LogInClass;
import com.vtalent.binding.request.UserClass;
import com.vtalent.entity.UserMasterClass;
import com.vtalent.repository.UserMasterRepository;
import com.vtalent.utils.EmailUtilsClass;

@Service
public class UserServiceImpl implements UserService {
	
	@Autowired
	private UserMasterRepository userMasterRepository;

	@Autowired
	private EmailUtilsClass emailUtilsClass;

	@Override
	public boolean saveSubmitForm(UserClass user) throws IOException {
		// TODO Auto-generated method stub
		UserMasterClass entity = new UserMasterClass();

		BeanUtils.copyProperties(user, entity);

		entity.setPassword(RandomCodeGenerator.generateRandomCode(6));
		entity.setAccStatus("In-Active");

		UserMasterClass save = userMasterRepository.save(entity);

		// TODO SMTP logic...
		String subject = "your registration success...";
		
		String fileName = "Reg-Email-Body.txt";

		String body = ReadRegEmailBody.readEmailBody(entity.getFullName(), entity.getPassword(), fileName);

		emailUtilsClass.sendEmail(user.getEmailId(), subject, body);

		return save.getUserId() != null;
	}

	@Override
	public boolean activeUserAccount(ActivateAccount activateAccount) {
		// TODO Auto-generated method stub

		UserMasterClass entity = new UserMasterClass();

		entity.setEmailId(activateAccount.getEmailId());

		entity.setPassword(activateAccount.getTempPassword());

		Example<UserMasterClass> example = Example.of(entity);

		List<UserMasterClass> findAll = userMasterRepository.findAll(example);

		if (findAll.isEmpty()) {
			return false;
		} else {
			UserMasterClass userMasterClass = findAll.get(0);

			userMasterClass.setPassword(activateAccount.getNewPassword());
			userMasterClass.setAccStatus("Active");
			userMasterRepository.save(userMasterClass);
			return true;
		}
	}

	@Override
	public List<UserClass> getAllUsers() {
		// TODO Auto-generated method stub
		List<UserMasterClass> findAll = userMasterRepository.findAll();

		List<UserClass> userClasses = new ArrayList();
		for (UserMasterClass entity : findAll) {
			UserClass userClass = new UserClass();
			BeanUtils.copyProperties(entity, userClass);
			userClasses.add(userClass);
		}
		return userClasses;
	}

	@Override
	public UserClass getUserByUserId(Integer userId) {
		// TODO Auto-generated method stub

		Optional<UserMasterClass> findById = userMasterRepository.findById(userId);

		if (findById.isPresent()) {
			UserClass userClass = new UserClass();
			UserMasterClass userMasterClass = findById.get();

			BeanUtils.copyProperties(userMasterClass,userClass);
			return userClass;

		}
		return null;
	}

	@Override
	public boolean deleteByUserId(Integer userId) {
		// TODO Auto-generated method stub
		try {
			userMasterRepository.deleteById(userId);
			return true;
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();

		}
		return false;
	}

	@Override
	public boolean changeAccountStatus(Integer userId, String status) {
		// TODO Auto-generated method stub
		Optional<UserMasterClass> findById = userMasterRepository.findById(userId);
		if (findById.isPresent()) {
			UserMasterClass userMasterClass = findById.get();
			userMasterClass.setAccStatus(status);
			userMasterRepository.save(userMasterClass);
			return true;
		}
		return false;
	}

	@Override
	public String loginForm(LogInClass login) {
		 UserMasterClass entity = new UserMasterClass();
		 entity.setEmailId(login.getEmailId());
		 entity.setPassword(login.getPassword());
		 
		 Example<UserMasterClass> of = Example.of(entity);
		 List<UserMasterClass> findAll=userMasterRepository.findAll(of);
		 
		 if(findAll.isEmpty()) {
			 return "Invalid credentials";
		 }
		 else {
			 UserMasterClass userMasterClass = findAll.get(0);
			 if (userMasterClass.getAccStatus().equals("Active")) {
				 return "Succes";
			}
			 else {
				 return "Accout not activated";
			 }
		 }

	}

	@Override
	public String forgotPassword( String emailId) throws IOException {
		// TODO Auto-generated method stub
		UserMasterClass entity = userMasterRepository.findByEmailId(emailId);
		
		if(entity == null) {
			return "invalid email";
		}
		
		String subject="Forgot password";
		
		String fileName="Recover_Password.txt";
		
		String body = ReadRegEmailBody.readEmailBody(entity.getFullName(), entity.getPassword(), fileName);
		
		boolean sendEmail = emailUtilsClass.sendEmail(emailId, subject, body);
		
		if (sendEmail) {
			return "password sent to you registered email ";
		}
		
		return null;
	}
}


