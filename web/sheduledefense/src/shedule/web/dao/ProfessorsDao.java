package shedule.web.dao;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.criterion.DetachedCriteria;

import shedule.web.data.Professors;

public class ProfessorsDao extends AbstractSimpleGenericDao<Professors, Integer>{
	private static final Log log = LogFactory.getLog(ProfessorsDao.class);
	@SuppressWarnings("unchecked")
	public List<Professors> findByCriteria(DetachedCriteria dc, int from,
			int size) {
		if (log.isDebugEnabled())
			log.debug("Return Customers from " + from + " to " + size);

		try {
			Criteria criteria = dc.getExecutableCriteria(hSession);
			criteria.setFirstResult(from);
			criteria.setMaxResults(size);
			return criteria.list();
		} catch (HibernateException e) {
			log.error(e.getMessage(), e);
			throw e;
		}
	}
}
