package com.wellcom.hibernate.dao;

import java.util.List;
import java.util.Set;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.LockMode;
import org.hibernate.Query;
import org.hibernate.criterion.Example;

import com.wellcom.hibernate.model.SecTblAlumno;

/**
 * A data access object (DAO) providing persistence and search support for
 * SecTblAlumno entities. Transaction control of the save(), update() and
 * delete() operations can directly support Spring container-managed
 * transactions or they can be augmented to handle user-managed Spring
 * transactions. Each of these methods provides additional information for how
 * to configure it for the desired type of transaction control.
 * 
 * @see com.wellcom.hibernate.dao.SecTblAlumno
 * @author MyEclipse Persistence Tools
 */

public class SecTblAlumnoDAO extends BaseHibernateDAO {
	private static final Log log = LogFactory.getLog(SecTblAlumnoDAO.class);
	// property constants
	public static final String NOMBRE = "nombre";
	public static final String APELLIDO_PATERNO = "apellidoPaterno";
	public static final String APELLIDO_MATERNO = "apellidoMaterno";

	public void save(SecTblAlumno transientInstance) {
		log.debug("saving SecTblAlumno instance");
		try {
			getSession().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	public void delete(SecTblAlumno persistentInstance) {
		log.debug("deleting SecTblAlumno instance");
		try {
			getSession().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public SecTblAlumno findById(java.lang.Integer id) {
		log.debug("getting SecTblAlumno instance with id: " + id);
		try {
			SecTblAlumno instance = (SecTblAlumno) getSession().get(
					"com.wellcom.hibernate.model.SecTblAlumno", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List findByExample(SecTblAlumno instance) {
		log.debug("finding SecTblAlumno instance by example");
		try {
			List results = getSession().createCriteria(
					"com.wellcom.hibernate.model.SecTblAlumno").add(
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
		log.debug("finding SecTblAlumno instance with property: "
				+ propertyName + ", value: " + value);
		try {
			String queryString = "from SecTblAlumno as model where model."
					+ propertyName + "= ?";
			Query queryObject = getSession().createQuery(queryString);
			queryObject.setParameter(0, value);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}

	public List findByNombre(Object nombre) {
		return findByProperty(NOMBRE, nombre);
	}

	public List findByApellidoPaterno(Object apellidoPaterno) {
		return findByProperty(APELLIDO_PATERNO, apellidoPaterno);
	}

	public List findByApellidoMaterno(Object apellidoMaterno) {
		return findByProperty(APELLIDO_MATERNO, apellidoMaterno);
	}

	public List findAll() {
		log.debug("finding all SecTblAlumno instances");
		try {
			String queryString = "from SecTblAlumno";
			Query queryObject = getSession().createQuery(queryString);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public SecTblAlumno merge(SecTblAlumno detachedInstance) {
		log.debug("merging SecTblAlumno instance");
		try {
			SecTblAlumno result = (SecTblAlumno) getSession().merge(
					detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(SecTblAlumno instance) {
		log.debug("attaching dirty SecTblAlumno instance");
		try {
			getSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(SecTblAlumno instance) {
		log.debug("attaching clean SecTblAlumno instance");
		try {
			getSession().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}
}