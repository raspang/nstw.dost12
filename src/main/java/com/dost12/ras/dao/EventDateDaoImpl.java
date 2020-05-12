package com.dost12.ras.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import com.dost12.ras.model.EventDate;



@Repository("eventDateDao")
public class EventDateDaoImpl extends AbstractDao<Integer, EventDate> implements EventDateDao {

	static final Logger logger = LoggerFactory.getLogger(EventDateDaoImpl.class);
	
	public EventDate findById(Integer id) {
		EventDate eventDate = getByKey(id);
		return eventDate;
	}

	

	@SuppressWarnings("unchecked")
	public List<EventDate> findAllEventDates() {
		Criteria criteria = createEntityCriteria().addOrder(Order.asc("id"));
		criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);//To avoid duplicates.
		List<EventDate> eventDates = (List<EventDate>) criteria.list();
		
		// No need to fetch userProfiles since we are not showing them on list page. Let them lazy load. 
		// Uncomment below lines for eagerly fetching of userProfiles if you want.
		/*
		for(User user : users){
			Hibernate.initialize(user.getUserProfiles());
		}*/
		return eventDates;
	}

	public void save(EventDate eventDate) {
		persist(eventDate);
	}

	public void delete(EventDate eventDate) {
/*		Criteria crit = createEntityCriteria();
		crit.add(Restrictions.eq("ssoId", sso));
		User user = (User)crit.uniqueResult();*/
		delete(eventDate);
	}



	@Override
	public List<EventDate> findAllEnableEventDates() {
		Criteria criteria = createEntityCriteria().addOrder(Order.asc("id"));
		
		criteria.add(Restrictions.eq("enable", true));
		
		
		criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);//To avoid duplicates.
		List<EventDate> eventDates = (List<EventDate>) criteria.list();
		
		// No need to fetch userProfiles since we are not showing them on list page. Let them lazy load. 
		// Uncomment below lines for eagerly fetching of userProfiles if you want.
		/*
		for(User user : users){
			Hibernate.initialize(user.getUserProfiles());
		}*/
		return eventDates;
	}



}
