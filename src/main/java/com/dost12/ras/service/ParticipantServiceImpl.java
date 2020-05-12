package com.dost12.ras.service;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.dost12.ras.dao.ParticipantDao;
import com.dost12.ras.model.Participant;


@Service("participantService")
@Transactional
public class ParticipantServiceImpl implements ParticipantService{

	@Autowired
	private ParticipantDao dao;

	
	public Participant findById(Long id) {
		return dao.findById(id);
	}


	public void saveVoter(Participant voter) {
		dao.save(voter);
	}

	/*
	 * Since the method is running with Transaction, No need to call hibernate update explicitly.
	 * Just fetch the entity from db and update it with proper values within transaction.
	 * It will be updated in db once transaction ends. 
	 */
	public void updateVoter(Participant code) {
		Participant entity =  dao.findById(code.getId());
		if(entity!=null){
			entity.setCode(code.getCode());
			entity.setFirstName(code.getFirstName());
			entity.setMiddleName(code.getMiddleName());
			entity.setLastName(code.getLastName());
			entity.setCompany(code.getCompany());
			entity.setDesignation(code.getDesignation());
			entity.setContact(code.getContact());
			entity.setAge(code.getAge());
			entity.setGender(code.getGender());
			entity.setStatus(code.getStatus());
			entity.setBusiness(code.getBusiness());
			entity.setUpdated_At(new Date());
			entity.setEmail(code.getEmail());
			entity.setVip(code.isVip());
		}
	}

	public List<Participant> findAllVoters() {
		return dao.findAllVoters();
	}

	public List<Participant> findAllVoters(Long id1, Long id2) {
		return dao.findAllVoters(id1, id2);
	}


	@Override
	public void deleteVoter(Participant code) {
		dao.delete(code);
		
	}






}
