package web.shedule.dao;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Projections;

import web.shedule.model.Professors;
import web.shedule.util.Debug;

public class ProfessorsDao extends
		AbstractSimpleGenericDao<Professors, Integer> {

	private static final Log log = LogFactory.getLog(ProfessorsDao.class);

	@SuppressWarnings("unchecked")
	public List<Professors> findByCriteria(DetachedCriteria dc, int from,
			int size) {
		Debug.d("Return professors from " + from + " to " + size);

		try {
			Criteria criteria = dc.getExecutableCriteria(hSession);
			criteria.setFirstResult(from);
			criteria.setMaxResults(size);
			List list = criteria.list();
			return list;
		} catch (HibernateException e) {
			e.printStackTrace();
			return null;

		}
	}

	public int countByCriteria(DetachedCriteria dc) {
		if (log.isDebugEnabled())
			log.debug("count Customers");

		try {
			Criteria criteria = dc.getExecutableCriteria(hSession);
			criteria.setProjection(Projections.rowCount());
			return ((Long) criteria.list().get(0)).intValue();
		} catch (HibernateException e) {
			log.error(e.getMessage(), e);
			throw e;
		}
	}

	public int nextProfessors() {
		if (log.isDebugEnabled())
			log.debug("find next customer number");

		try {
			DetachedCriteria dc = DetachedCriteria.forClass(Professors.class);
			Criteria criteria = dc.getExecutableCriteria(hSession);
			criteria.setProjection(Projections.max("id"));
			return ((Integer) criteria.list().get(0)).intValue() + 1;
		} catch (HibernateException e) {
			log.error(e.getMessage(), e);
			throw e;
		}
	}

	public List<String> getListProfNames() {
		Query query = hSession.createQuery("select name from Professors");
		return query.list();

	}

}
