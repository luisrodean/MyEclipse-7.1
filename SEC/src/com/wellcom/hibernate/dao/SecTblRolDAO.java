package com.wellcom.hibernate.dao;

import java.util.List;
import java.util.Set;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.LockMode;
import org.hibernate.Query;
import org.hibernate.criterion.Example;

import com.wellcom.hibernate.model.SecTblRol;

/**
 * A data access object (DAO) providing persistence and search support for
 * SecTblRol entities. Transaction control of the save(), update() and delete()
 * operations can directly support Spring container-managed transactions or they
 * can be augmented to handle user-managed Spring transactions. Each of these
 * methods provides additional information for how to configure it for the
 * desired type of transaction control.
 * 
 * @see com.wellcom.hibernate.dao.SecTblRol
 * @author MyEclipse Persistence Tools
 */

public class SecTblRolDAO extends BaseHibernateDAO {
	private static final Log log = LogFactory.getLog(SecTblRolDAO.class);
	// property constants
	public static final String TIPO_ROL = "tipoRol";

	public void save(SecTblRol transientInstance) {
		log.debug("saving SecTblRol instance");
		try {
			getSession().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	public void delete(SecTblRol persistentInstance) {
		log.debug("deleting SecTblRol instance");
		try {
			getSession().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public SecTblRol findById(java.lang.Integer id) {
		log.debug("getting SecTblRol instance with id: " + id);
		try {
			SecTblRol instance = (SecTblRol) getSession().get(
					"com.wellcom.hibernate.model.SecTblRol", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List findByExample(SecTblRol instance) {
		log.debug("finding SecTblRol instance by example");
		try {
			List results = getSession().createCriteria(
					"com.wellcom.hibernate.model.SecTblRol").add(
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
		log.debug("finding SecTblRol instance with property: " + propertyName
				+ ", value: " + value);
		try {
			String queryString = "from SecTblRol as model where model."
					+ propertyName + "= ?";
			Query queryObject = getSession().createQuery(queryString);
			queryObject.setParameter(0, value);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}

	public List findByTipoRol(Object tipoRol) {
		return findByProperty(TIPO_ROL, tipoRol);
	}

	public List findAll() {
		log.debug("finding all SecTblRol instances");
		try {
			String queryString = "from SecTblRol";
			Query queryObject = getSession().createQuery(queryString);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public SecTblRol merge(SecTblRol detachedInstance) {
		log.debug("merging SecTblRol instance");
		try {
			SecTblRol result = (SecTblRol) getSession().merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(SecTblRol instance) {
		log.debug("attaching dirty SecTblRol instance");
		try {
			getSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(SecTblRol instance) {
		log.debug("attaching clean SecTblRol instance");
		try {
			getSession().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}
}