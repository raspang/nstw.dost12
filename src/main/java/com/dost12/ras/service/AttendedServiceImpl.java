package com.dost12.ras.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.dost12.ras.dao.AttendedDao;
import com.dost12.ras.model.Attended;


@Service("attendedService")
@Transactional
public class AttendedServiceImpl implements AttendedService{

	@Autowired
	private AttendedDao dao;

	
	public Attended findById(Long id) {
		return dao.findById(id);
	}


	public void saveAttended(Attended attended) {
		dao.save(attended);
	}

	/*
	 * Since the method is running with Transaction, No need to call hibernate update explicitly.
	 * Just fetch the entity from db and update it with proper values within transaction.
	 * It will be updated in db once transaction ends. 
	 */
	public void updateAttended(Attended attended) {
		Attended entity =  dao.findById(attended.getId());
		if(entity!=null){
			entity.setDate(attended.getDate());;

		}
	}

	public List<Attended> findAllAttends() {
		return dao.findAllAttendeds();
	}


	@Override
	public void deleteAttended(Attended attended) {
		dao.delete(attended);
		
	}


	@Override
	public List<Attended> findAllAttendeds(String date, String business) {
		// TODO Auto-generated method stub
		return dao.findAllAttendeds(date, business);
	}


}
