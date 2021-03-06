package com.wellcom.hibernate.dao;

import java.util.List;
import java.util.Set;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.LockMode;
import org.hibernate.Query;
import org.hibernate.criterion.Example;

import com.wellcom.hibernate.model.SecTblEscuela;

/**
 * A data access object (DAO) providing persistence and search support for
 * SecTblEscuela entities. Transaction control of the save(), update() and
 * delete() operations can directly support Spring container-managed
 * transactions or they can be augmented to handle user-managed Spring
 * transactions. Each of these methods provides additional information for how
 * to configure it for the desired type of transaction control.
 * 
 * @see com.wellcom.hibernate.dao.SecTblEscuela
 * @author MyEclipse Persistence Tools
 */

public class SecTblEscuelaDAO extends BaseHibernateDAO {
	private static final Log log = LogFactory.getLog(SecTblEscuelaDAO.class);
	// property constants
	public static final String NOMBRE = "nombre";
	public static final String DIRECCION = "direccion";
	public static final String TELEFONO = "telefono";
	public static final String CORREO = "correo";

	public void save(SecTblEscuela transientInstance) {
		log.debug("saving SecTblEscuela instance");
		try {
			getSession().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	public void delete(SecTblEscuela persistentInstance) {
		log.debug("deleting SecTblEscuela instance");
		try {
			getSession().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public SecTblEscuela findById(java.lang.String id) {
		log.debug("getting SecTblEscuela instance with id: " + id);
		try {
			SecTblEscuela instance = (SecTblEscuela) getSession().get(
					"com.wellcom.hibernate.model.SecTblEscuela", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List findByExample(SecTblEscuela instance) {
		log.debug("finding SecTblEscuela instance by example");
		try {
			List results = getSession().createCriteria(
					"com.wellcom.hibernate.model.SecTblEscuela").add(
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
		log.debug("finding SecTblEscuela instance with property: "
				+ propertyName + ", value: " + value);
		try {
			String queryString = "from SecTblEscuela as model where model."
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

	public List findByDireccion(Object direccion) {
		return findByProperty(DIRECCION, direccion);
	}

	public List findByTelefono(Object telefono) {
		return findByProperty(TELEFONO, telefono);
	}

	public List findByCorreo(Object correo) {
		return findByProperty(CORREO, correo);
	}

	public List findAll() {
		log.debug("finding all SecTblEscuela instances");
		try {
			String queryString = "from SecTblEscuela";
			Query queryObject = getSession().createQuery(queryString);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public SecTblEscuela merge(SecTblEscuela detachedInstance) {
		log.debug("merging SecTblEscuela instance");
		try {
			SecTblEscuela result = (SecTblEscuela) getSession().merge(
					detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(SecTblEscuela instance) {
		log.debug("attaching dirty SecTblEscuela instance");
		try {
			getSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(SecTblEscuela instance) {
		log.debug("attaching clean SecTblEscuela instance");
		try {
			getSession().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}
}