package com.wellcom.hibernate.dao;

import java.util.List;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.Criteria;
import org.hibernate.LockMode;
import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.criterion.Example;

import com.wellcom.hibernate.model.SecTblParametros;

/**
 * A data access object (DAO) providing persistence and search support for
 * SecTblParametros entities. Transaction control of the save(), update() and
 * delete() operations can directly support Spring container-managed
 * transactions or they can be augmented to handle user-managed Spring
 * transactions. Each of these methods provides additional information for how
 * to configure it for the desired type of transaction control.
 * 
 * @see com.wellcom.hibernate.dao.SecTblParametros
 * @author MyEclipse Persistence Tools
 */

public class SecTblParametrosDAO extends BaseHibernateDAO {
	private static final Log log = LogFactory.getLog(SecTblParametrosDAO.class);
	// property constants
	public static final String NOMBRE = "nombre";
	public static final String PARAMETRO = "parametro";
	public static final String PARAM_BLOB = "paramBlob";

	public void save(SecTblParametros transientInstance) {
		log.debug("saving SecTblParametros instance");
		try {
			getSession().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	public void delete(SecTblParametros persistentInstance) {
		log.debug("deleting SecTblParametros instance");
		try {
			getSession().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public SecTblParametros findById(java.lang.Integer id) {
		log.debug("getting SecTblParametros instance with id: " + id);
		try {
			SecTblParametros instance = (SecTblParametros) getSession().get(
					"com.wellcom.hibernate.model.SecTblParametros", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List findByExample(SecTblParametros instance) {
		log.debug("finding SecTblParametros instance by example");
		try {
			List results = getSession().createCriteria(
					"com.wellcom.hibernate.model.SecTblParametros").add(
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
		log.debug("finding SecTblParametros instance with property: "
				+ propertyName + ", value: " + value);
		try {
			String queryString = "from SecTblParametros as model where model."
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

	public List findByParametro(Object parametro) {
		return findByProperty(PARAMETRO, parametro);
	}

	public List findByParamBlob(Object paramBlob) {
		return findByProperty(PARAM_BLOB, paramBlob);
	}

	public List findAll() {
		log.debug("finding all SecTblParametros instances");
		try {
			String queryString = "from SecTblParametros";
			Query queryObject = getSession().createQuery(queryString);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public SecTblParametros merge(SecTblParametros detachedInstance) {
		log.debug("merging SecTblParametros instance");
		try {
			SecTblParametros result = (SecTblParametros) getSession().merge(
					detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(SecTblParametros instance) {
		log.debug("attaching dirty SecTblParametros instance");
		try {
			getSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(SecTblParametros instance) {
		log.debug("attaching clean SecTblParametros instance");
		try {
			getSession().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}
	

	public String getParametro(String nombre){
		String ctl="";
		SecTblParametros parametro= new SecTblParametros();
		String query = "select ID,NOMBRE,PARAMETRO " +
						"from sec_tbl_parametros  " +
						"where NOMBRE='" + nombre + "'"; 
		System.out.println(query);
		SQLQuery queryObject = getSession().createSQLQuery(query);

		queryObject.setResultTransformer(Criteria.ALIAS_TO_ENTITY_MAP);
		
		List rs=queryObject.list();
		
		Map registro = (Map) rs.get(0);
		parametro.setId(new Integer (registro.get("ID").toString()));
		parametro.setNombre(registro.get("NOMBRE").toString());
		parametro.setParametro(registro.get("PARAMETRO").toString());
		//se remplaza la cadena (&quot;) por comillas (") 
		ctl = parametro.getParametro().replaceAll("&quot;", "\"");
		return ctl;
	}

}