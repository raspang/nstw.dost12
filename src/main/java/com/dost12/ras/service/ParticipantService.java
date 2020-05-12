package com.dost12.ras.service;

import java.util.List;

import com.dost12.ras.model.Participant;

public interface ParticipantService {
	
	Participant findById(Long id);
	
	void saveVoter(Participant code);
	
	void updateVoter(Participant code);
	
	void deleteVoter(Participant code);

	List<Participant> findAllVoters(); 

	List<Participant> findAllVoters(Long id1, Long id2);

}