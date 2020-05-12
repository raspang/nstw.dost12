package com.dost12.ras.service;

import java.util.List;

import com.dost12.ras.model.Attended;

public interface AttendedService {
	
	Attended findById(Long id);
	
	void saveAttended(Attended attended);
	
	void updateAttended(Attended attended);
	
	void deleteAttended(Attended attended);

	List<Attended> findAllAttends(); 
	
	public List<Attended> findAllAttendeds(String date, String business);

	

}