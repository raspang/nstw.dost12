package com.dost12.ras.service;

import java.util.List;

import com.dost12.ras.model.UserProfile;


public interface UserProfileService {

	UserProfile findById(int id);

	UserProfile findByType(String type);
	
	List<UserProfile> findAll();
	
}
