package com.dost12.ras.dao;

import java.util.List;

import com.dost12.ras.model.EventDate;


public interface EventDateDao {

	EventDate findById(Integer id);
	
	void save(EventDate eventDate);
	
	void delete(EventDate eventDate);
	
	List<EventDate> findAllEventDates();
	
	List<EventDate> findAllEnableEventDates();

}

