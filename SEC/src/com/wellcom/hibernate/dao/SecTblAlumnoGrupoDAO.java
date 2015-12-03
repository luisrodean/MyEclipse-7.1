package com.wellcom.hibernate.dao;

import java.util.List;
import java.util.Set;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.LockMode;
import org.hibernate.Query;
import org.hibernate.criterion.Example;

import com.wellcom.hibernate.model.SecTblAlumnoGrupo;

/**
 * A data access object (DAO) providing persistence and search support for
 * SecTblAlumnoGrupo entities. Transaction control of the save(), update() and
 * delete() operations can directly support Spring container-managed
 * transactions or they can be augmented to handle user-managed Spring
 * transactions. Each of these methods provides additional information for how
 * to configure it for the desired type of transaction control.
 * 
 * @see com.wellcom.hibernate.dao.SecTblAlumnoGrupo
 * @author MyEclipse Persistence Tools
 */

public class SecTblAlumnoGrupoDAO extends BaseHibernateDAO {
	private static final Log log = LogFactory
			.getLog(SecTblAlumnoGrupoDAO.class);

	// property constants

	public void save(SecTblAlumnoGrupo transientInstance) {
		log.debug("saving SecTblAlumnoGrupo instance");
		try {
			getSession().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	public void delete(SecTblAlumnoGrupo persistentInstance) {
		log.debug("deleting SecTblAlumnoGrupo instance");
		try {
			getSession().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public SecTblAlumnoGrupo findById(java.lang.String id) {
		log.debug("getting SecTblAlumnoGrupo instance with id: " + id);
		try {
			SecTblAlumnoGrupo instance = (SecTblAlumnoGrupo) getSession().get(
					"com.wellcom.hibernate.model.SecTblAlumnoGrupo", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List findByExample(SecTblAlumnoGrupo instance) {
		log.debug("finding SecTblAlumnoGrupo instance by example");
		try {
			List results = getSession().createCriteria(
					"com.wellcom.hibernate.model.SecTblAlumnoGrupo").add(
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
		log.debug("finding SecTblAlumnoGrupo instance with property: "
				+ propertyName + ", value: " + value);
		try {
			String queryString = "from SecTblAlumnoGrupo as model where model."
					+ propertyName + "= ?";
			Query queryObject = getSession().createQuery(queryString);
			queryObject.setParameter(0, value);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}

	public List findAll() {
		log.debug("finding all SecTblAlumnoGrupo instances");
		try {
			String queryString = "from SecTblAlumnoGrupo";
			Query queryObject = getSession().createQuery(queryString);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public SecTblAlumnoGrupo merge(SecTblAlumnoGrupo detachedInstance) {
		log.debug("merging SecTblAlumnoGrupo instance");
		try {
			SecTblAlumnoGrupo result = (SecTblAlumnoGrupo) getSession().merge(
					detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(SecTblAlumnoGrupo instance) {
		log.debug("attaching dirty SecTblAlumnoGrupo instance");
		try {
			getSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(SecTblAlumnoGrupo instance) {
		log.debug("attaching clean SecTblAlumnoGrupo instance");
		try {
			getSession().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}
}