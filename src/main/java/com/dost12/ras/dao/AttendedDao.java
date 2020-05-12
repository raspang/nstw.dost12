package com.dost12.ras.dao;

import java.util.List;

import com.dost12.ras.model.Attended;


public interface AttendedDao {

	Attended findById(Long id);
	
	void save(Attended attended);
	
	void delete(Attended attended);
	
	List<Attended> findAllAttendeds();

	List<Attended> findAllAttendeds(String date, String business);
}

