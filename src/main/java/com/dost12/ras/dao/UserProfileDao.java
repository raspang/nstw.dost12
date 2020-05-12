package com.dost12.ras.dao;

import java.util.List;

import com.dost12.ras.model.UserProfile;


public interface UserProfileDao {

	List<UserProfile> findAll();
	
	UserProfile findByType(String type);
	
	UserProfile findById(int id);
}
