package com.dost12.ras.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.dost12.ras.dao.EventDateDao;
import com.dost12.ras.model.EventDate;


@Service("eventDateService")
@Transactional
public class EventDateServiceImpl implements EventDateService{

	@Autowired
	private EventDateDao dao;

	
	public EventDate findById(Integer id) {
		return dao.findById(id);
	}


	public void saveEventDate(EventDate eventDate) {
		dao.save(eventDate);
	}

	/*
	 * Since the method is running with Transaction, No need to call hibernate update explicitly.
	 * Just fetch the entity from db and update it with proper values within transaction.
	 * It will be updated in db once transaction ends. 
	 */
	public void updateEventDate(EventDate eventDate) {
		EventDate entity =  dao.findById(eventDate.getId());
		if(entity!=null){
			entity.setDate(eventDate.getDate());
			entity.setEnable(eventDate.getEnable());
		}
	}
	@Override
	public List<EventDate> findAllEventDates() {
		return dao.findAllEventDates();
	}


	@Override
	public void deleteEventDate(EventDate eventDate) {
		dao.delete(eventDate);
		
	}


	@Override
	public List<EventDate> findAllEnableEventDates() {
		return dao.findAllEnableEventDates();
	}


}
