package com.wellcom.hibernate.dao;

import java.util.List;
import java.util.Set;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.LockMode;
import org.hibernate.Query;
import org.hibernate.criterion.Example;

import com.wellcom.hibernate.model.SecTblPeriodo;

/**
 * A data access object (DAO) providing persistence and search support for
 * SecTblPeriodo entities. Transaction control of the save(), update() and
 * delete() operations can directly support Spring container-managed
 * transactions or they can be augmented to handle user-managed Spring
 * transactions. Each of these methods provides additional information for how
 * to configure it for the desired type of transaction control.
 * 
 * @see com.wellcom.hibernate.dao.SecTblPeriodo
 * @author MyEclipse Persistence Tools
 */

public class SecTblPeriodoDAO extends BaseHibernateDAO {
	private static final Log log = LogFactory.getLog(SecTblPeriodoDAO.class);
	// property constants
	public static final String PERIODO = "periodo";

	public void save(SecTblPeriodo transientInstance) {
		log.debug("saving SecTblPeriodo instance");
		try {
			getSession().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	public void delete(SecTblPeriodo persistentInstance) {
		log.debug("deleting SecTblPeriodo instance");
		try {
			getSession().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public SecTblPeriodo findById(java.lang.Integer id) {
		log.debug("getting SecTblPeriodo instance with id: " + id);
		try {
			SecTblPeriodo instance = (SecTblPeriodo) getSession().get(
					"com.wellcom.hibernate.model.SecTblPeriodo", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List findByExample(SecTblPeriodo instance) {
		log.debug("finding SecTblPeriodo instance by example");
		try {
			List results = getSession().createCriteria(
					"com.wellcom.hibernate.model.SecTblPeriodo").add(
					Example.create(instance)).list();
			log.debug("find by example successful, result size: "
					+ results.size());
			return results;
		} catch (RuntimeException re) {
			log.error("find by example failed", re);
			throw re;
		}
	}

	public List findByProperty(String propertyName, Object value) {
		log.debug("finding SecTblPeriodo instance with property: "
				+ propertyName + ", value: " + value);
		try {
			String queryString = "from SecTblPeriodo as model where model."
					+ propertyName + "= ?";
			Query queryObject = getSession().createQuery(queryString);
			queryObject.setParameter(0, value);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}

	public List findByPeriodo(Object periodo) {
		return findByProperty(PERIODO, periodo);
	}

	public List findAll() {
		log.debug("finding all SecTblPeriodo instances");
		try {
			String queryString = "from SecTblPeriodo";
			Query queryObject = getSession().createQuery(queryString);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public SecTblPeriodo merge(SecTblPeriodo detachedInstance) {
		log.debug("merging SecTblPeriodo instance");
		try {
			SecTblPeriodo result = (SecTblPeriodo) getSession().merge(
					detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(SecTblPeriodo instance) {
		log.debug("attaching dirty SecTblPeriodo instance");
		try {
			getSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(SecTblPeriodo instance) {
		log.debug("attaching clean SecTblPeriodo instance");
		try {
			getSession().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}
}