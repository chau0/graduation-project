package web.shedule.dao;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Projections;

import web.shedule.model.DataSet;



public class DataSetDao extends
		AbstractSimpleGenericDao<DataSet, Integer> {

	private static final Log log = LogFactory.getLog(DataSetDao.class);

	@SuppressWarnings("unchecked")
	public List<DataSet> findByCriteria(DetachedCriteria dc, int from,
			int size) {
		if (log.isDebugEnabled())
			log.debug("Return professors from " + from + " to " + size);

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

	public int countByCriteria(DetachedCriteria dc) {
		if (log.isDebugEnabled())
			log.debug("count Jury");

		try {
			Criteria criteria = dc.getExecutableCriteria(hSession);
			criteria.setProjection(Projections.rowCount());
			return ((Long) criteria.list().get(0)).intValue();
		} catch (HibernateException e) {
			log.error(e.getMessage(), e);
			throw e;
		}
	}

	

	public int nextDataSet() {
		if (log.isDebugEnabled())
			log.debug("find next data set number");

		try {
			DetachedCriteria dc = DetachedCriteria.forClass(DataSet.class);
			Criteria criteria = dc.getExecutableCriteria(hSession);
			criteria.setProjection(Projections.max("id"));
			return ((Integer) criteria.list().get(0)).intValue() + 1;
		} catch (HibernateException e) {
			log.error(e.getMessage(), e);
			throw e;
		}
	}

}
