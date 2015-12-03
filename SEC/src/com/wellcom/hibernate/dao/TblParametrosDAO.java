package com.wellcom.hibernate.dao;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.Criteria;
import org.hibernate.LockMode;
import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.criterion.Example;

import com.wellcom.hibernate.model.TblParametros;
import com.wellcom.hibernate.model.TblParametrosId;

/**
 * A data access object (DAO) providing persistence and search support for
 * TblParametros entities. Transaction control of the save(), update() and
 * delete() operations can directly support Spring container-managed
 * transactions or they can be augmented to handle user-managed Spring
 * transactions. Each of these methods provides additional information for how
 * to configure it for the desired type of transaction control.
 * 
 * @see com.wellcom.hibernate.dao.TblParametros
 * @author MyEclipse Persistence Tools
 */

public class TblParametrosDAO extends BaseHibernateDAO {
	private static final Log log = LogFactory.getLog(TblParametrosDAO.class);

	// property constants

	public void save(TblParametros transientInstance) {
		log.debug("saving TblParametros instance");
		try {
			getSession().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	public void delete(TblParametros persistentInstance) {
		log.debug("deleting TblParametros instance");
		try {
			getSession().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public TblParametros findById(com.wellcom.hibernate.model.TblParametrosId id) {
		log.debug("getting TblParametros instance with id: " + id);
		try {
			TblParametros instance = (TblParametros) getSession().get(
					"com.wellcom.hibernate.dao.TblParametros", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List findByExample(TblParametros instance) {
		log.debug("finding TblParametros instance by example");
		try {
			List results = getSession().createCriteria(
					"com.wellcom.hibernate.dao.TblParametros").add(
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
		log.debug("finding TblParametros instance with property: "
				+ propertyName + ", value: " + value);
		try {
			String queryString = "from TblParametros as model where model."
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
		log.debug("finding all TblParametros instances");
		try {
			String queryString = "from TblParametros";
			Query queryObject = getSession().createQuery(queryString);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public TblParametros merge(TblParametros detachedInstance) {
		log.debug("merging TblParametros instance");
		try {
			TblParametros result = (TblParametros) getSession().merge(
					detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(TblParametros instance) {
		log.debug("attaching dirty TblParametros instance");
		try {
			getSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(TblParametros instance) {
		log.debug("attaching clean TblParametros instance");
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
		TblParametrosId parametro= new TblParametrosId();
		String query = "select ID,NOMBRE,PARAMETRO " +
						"from SEC_TBL_PARAMETROS  " +
						"where NOMBRE='" +nombre+ "'"; 
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