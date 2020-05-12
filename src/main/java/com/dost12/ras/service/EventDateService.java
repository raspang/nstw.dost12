package com.dost12.ras.service;

import java.util.List;

import com.dost12.ras.model.EventDate;

public interface EventDateService {
	
	EventDate findById(Integer id);
	
	void saveEventDate(EventDate eventDate);
	
	void updateEventDate(EventDate eventDate);
	
	void deleteEventDate(EventDate eventDate);

	List<EventDate> findAllEventDates(); 
	
	List<EventDate> findAllEnableEventDates();


}