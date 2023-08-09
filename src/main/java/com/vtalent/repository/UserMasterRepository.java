package com.vtalent.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.vtalent.entity.UserMasterClass;

public interface UserMasterRepository extends JpaRepository<UserMasterClass, Integer>{
	
	public UserMasterClass findByEmailId(String emailId);
}
