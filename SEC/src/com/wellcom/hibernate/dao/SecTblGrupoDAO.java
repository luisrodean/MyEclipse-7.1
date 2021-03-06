package com.wellcom.hibernate.dao;

import java.util.List;
import java.util.Map;
import java.util.Set;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.Criteria;
import org.hibernate.LockMode;
import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.criterion.Example;

import com.wellcom.hibernate.model.SecTblGrupo;

/**
 * A data access object (DAO) providing persistence and search support for
 * SecTblGrupo entities. Transaction control of the save(), update() and
 * delete() operations can directly support Spring container-managed
 * transactions or they can be augmented to handle user-managed Spring
 * transactions. Each of these methods provides additional information for how
 * to configure it for the desired type of transaction control.
 * 
 * @see com.wellcom.hibernate.dao.SecTblGrupo
 * @author MyEclipse Persistence Tools
 */

public class SecTblGrupoDAO extends BaseHibernateDAO {
	private static final Log log = LogFactory.getLog(SecTblGrupoDAO.class);
	// property constants
	public static final String GRUPO = "grupo";

	public void save(SecTblGrupo transientInstance) {
		log.debug("saving SecTblGrupo instance");
		try {
			getSession().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	public void delete(SecTblGrupo persistentInstance) {
		log.debug("deleting SecTblGrupo instance");
		try {
			getSession().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public SecTblGrupo findById(java.lang.Integer id) {
		log.debug("getting SecTblGrupo instance with id: " + id);
		try {
			SecTblGrupo instance = (SecTblGrupo) getSession().get(
					"com.wellcom.hibernate.model.SecTblGrupo", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List findByExample(SecTblGrupo instance) {
		log.debug("finding SecTblGrupo instance by example");
		try {
			List results = getSession().createCriteria(
					"com.wellcom.hibernate.model.SecTblGrupo").add(
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
		log.debug("finding SecTblGrupo instance with property: " + propertyName
				+ ", value: " + value);
		try {
			String queryString = "from SecTblGrupo as model where model."
					+ propertyName + "= ?";
			Query queryObject = getSession().createQuery(queryString);
			queryObject.setParameter(0, value);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}

	public List findByGrupo(Object grupo) {
		return findByProperty(GRUPO, grupo);
	}

	public List findAll() {
		log.debug("finding all SecTblGrupo instances");
		try {
			String queryString = "from SecTblGrupo";
			Query queryObject = getSession().createQuery(queryString);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public SecTblGrupo merge(SecTblGrupo detachedInstance) {
		log.debug("merging SecTblGrupo instance");
		try {
			SecTblGrupo result = (SecTblGrupo) getSession().merge(
					detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(SecTblGrupo instance) {
		log.debug("attaching dirty SecTblGrupo instance");
		try {
			getSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(SecTblGrupo instance) {
		log.debug("attaching clean SecTblGrupo instance");
		try {
			getSession().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}
	
	/**
	 * Metodos personalizados
	 */


	public Integer getIdGrupo(String grupo){
		Integer id = null;
		
		try{
			String query = "select (id_grupo)ID from sec_tbl_grupo where grupo = '" + grupo + "'"; 
			System.out.println(query);
			SQLQuery queryObject = getSession().createSQLQuery(query);
			
			queryObject.setResultTransformer(Criteria.ALIAS_TO_ENTITY_MAP);
			
			List rs = queryObject.list();
			Map registro = (Map) rs.get(0);
			id = Integer.parseInt(registro.get("ID").toString());
			System.out.println("\n--------->id Grupo:  " + id);
			return id;
		}catch(Exception e){
			e.getMessage();
			return id;
		}
		
	}
	
}
