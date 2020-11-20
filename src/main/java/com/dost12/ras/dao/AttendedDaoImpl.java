package com.dost12.ras.dao;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.hibernate.Criteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import com.dost12.ras.model.Attended;

@Repository("attendedDao")
public class AttendedDaoImpl extends AbstractDao<Long, Attended> implements AttendedDao {

	static final Logger logger = LoggerFactory.getLogger(AttendedDaoImpl.class);

	  

	public Attended findById(Long id) {
		Attended attended = getByKey(id);
		return attended;
	}

	@SuppressWarnings("unchecked")
	public List<Attended> findAllAttendeds() {
		Criteria criteria = createEntityCriteria().addOrder(Order.asc("id"));
		criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);// To avoid duplicates.
		List<Attended> attends = (List<Attended>) criteria.list();

		// No need to fetch userProfiles since we are not showing them on list page. Let
		// them lazy load.
		// Uncomment below lines for eagerly fetching of userProfiles if you want.
		/*
		 * for(User user : users){ Hibernate.initialize(user.getUserProfiles()); }
		 */
		return attends;
	}

	public void save(Attended attended) {
		persist(attended);
	}

	public void delete(Attended attended) {
		/*
		 * Criteria crit = createEntityCriteria(); crit.add(Restrictions.eq("ssoId",
		 * sso)); User user = (User)crit.uniqueResult();
		 */
		delete(attended);
	}

	@Override
	public List<Attended> findAllAttendeds(String myDate, String business) {
		Criteria criteria = createEntityCriteria().addOrder(Order.asc("id"));

		// Create date 17-04-2011 - 00h00
		Date minDate = null;
		Date maxDate = null;
		
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
		if (myDate != null) {
			
			try {
				minDate = formatter.parse(myDate);
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}


		}

		if (minDate != null) {
			// Create date 18-04-2011 - 00h00
			// -> We take the 1st date and add it 1 day in millisecond thanks to a useful
			// and not so known class
			 maxDate = new Date(minDate.getTime() + TimeUnit.DAYS.toMillis(1));
		}
	
			
			
			// The order date must be >= 17-04-2011 - 00h00
			criteria.add(Restrictions.ge("date", minDate));
			// And the order date must be < 18-04-2011 - 00h00
			criteria.add(Restrictions.lt("date", maxDate));

		
			criteria.createAlias("participant", "participant");
			
		if (!business.equals(""))
			criteria.add(Restrictions.eq("participant.business", business));

		criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);// To avoid duplicates.
		List<Attended> attends = (List<Attended>) criteria.list();

		// No need to fetch userProfiles since we are not showing them on list page. Let
		// them lazy load.
		// Uncomment below lines for eagerly fetching of userProfiles if you want.
		/*
		 * for(User user : users){ Hibernate.initialize(user.getUserProfiles()); }
		 */
		return attends;
	}

}
