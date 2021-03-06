package com.wellcom.hibernate.dao;

import java.util.List;
import java.util.Set;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.LockMode;
import org.hibernate.Query;
import org.hibernate.criterion.Example;

import com.wellcom.hibernate.model.SecTblMateria;

/**
 * A data access object (DAO) providing persistence and search support for
 * SecTblMateria entities. Transaction control of the save(), update() and
 * delete() operations can directly support Spring container-managed
 * transactions or they can be augmented to handle user-managed Spring
 * transactions. Each of these methods provides additional information for how
 * to configure it for the desired type of transaction control.
 * 
 * @see com.wellcom.hibernate.dao.SecTblMateria
 * @author MyEclipse Persistence Tools
 */

public class SecTblMateriaDAO extends BaseHibernateDAO {
	private static final Log log = LogFactory.getLog(SecTblMateriaDAO.class);
	// property constants
	public static final String NOMBRE_MATERIA = "nombreMateria";

	public void save(SecTblMateria transientInstance) {
		log.debug("saving SecTblMateria instance");
		try {
			getSession().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	public void delete(SecTblMateria persistentInstance) {
		log.debug("deleting SecTblMateria instance");
		try {
			getSession().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public SecTblMateria findById(java.lang.String id) {
		log.debug("getting SecTblMateria instance with id: " + id);
		try {
			SecTblMateria instance = (SecTblMateria) getSession().get(
					"com.wellcom.hibernate.model.SecTblMateria", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List findByExample(SecTblMateria instance) {
		log.debug("finding SecTblMateria instance by example");
		try {
			List results = getSession().createCriteria(
					"com.wellcom.hibernate.model.SecTblMateria").add(
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
		log.debug("finding SecTblMateria instance with property: "
				+ propertyName + ", value: " + value);
		try {
			String queryString = "from SecTblMateria as model where model."
					+ propertyName + "= ?";
			Query queryObject = getSession().createQuery(queryString);
			queryObject.setParameter(0, value);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}

	public List findByNombreMateria(Object nombreMateria) {
		return findByProperty(NOMBRE_MATERIA, nombreMateria);
	}

	public List findAll() {
		log.debug("finding all SecTblMateria instances");
		try {
			String queryString = "from SecTblMateria";
			Query queryObject = getSession().createQuery(queryString);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public SecTblMateria merge(SecTblMateria detachedInstance) {
		log.debug("merging SecTblMateria instance");
		try {
			SecTblMateria result = (SecTblMateria) getSession().merge(
					detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(SecTblMateria instance) {
		log.debug("attaching dirty SecTblMateria instance");
		try {
			getSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(SecTblMateria instance) {
		log.debug("attaching clean SecTblMateria instance");
		try {
			getSession().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}
}