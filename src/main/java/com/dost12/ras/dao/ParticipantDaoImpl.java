package com.dost12.ras.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import com.dost12.ras.model.Participant;



@Repository("participantDao")
public class ParticipantDaoImpl extends AbstractDao<Long, Participant> implements ParticipantDao {

	static final Logger logger = LoggerFactory.getLogger(ParticipantDaoImpl.class);
	
	public Participant findById(Long id) {
		Participant code = getByKey(id);
		return code;
	}


	@SuppressWarnings("unchecked")
	public List<Participant> findAllVoters() {
		Criteria criteria = createEntityCriteria().addOrder(Order.desc("updated_At")).addOrder(Order.desc("id"));;
		criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);//To avoid duplicates.
		List<Participant> barangays = (List<Participant>) criteria.list();
		
		// No need to fetch userProfiles since we are not showing them on list page. Let them lazy load. 
		// Uncomment below lines for eagerly fetching of userProfiles if you want.
		/*
		for(User user : users){
			Hibernate.initialize(user.getUserProfiles());
		}*/
		return barangays;
	}

	@SuppressWarnings("unchecked")
	public List<Participant> findAllVoters(Long id1, Long id2) {
		Criteria criteria = createEntityCriteria().addOrder(Order.asc("lastName"));
		criteria.add(Restrictions.between("id", id1, id2));
		
		
		criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);//To avoid duplicates.
		List<Participant> barangays = (List<Participant>) criteria.list();
		
		// No need to fetch userProfiles since we are not showing them on list page. Let them lazy load. 
		// Uncomment below lines for eagerly fetching of userProfiles if you want.
		/*
		for(User user : users){
			Hibernate.initialize(user.getUserProfiles());
		}*/
		return barangays;
	}
	
	public void save(Participant voter) {
		persist(voter);
		
	}
	

	public void deleteCode(Participant voter) {
		delete(voter);
	}


}
