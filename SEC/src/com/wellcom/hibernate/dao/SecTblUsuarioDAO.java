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

import com.wellcom.hibernate.model.SecTblUsuario;

/**
 * A data access object (DAO) providing persistence and search support for
 * SecTblUsuario entities. Transaction control of the save(), update() and
 * delete() operations can directly support Spring container-managed
 * transactions or they can be augmented to handle user-managed Spring
 * transactions. Each of these methods provides additional information for how
 * to configure it for the desired type of transaction control.
 * 
 * @see com.wellcom.hibernate.dao.SecTblUsuario
 * @author MyEclipse Persistence Tools
 */

public class SecTblUsuarioDAO extends BaseHibernateDAO {
	private static final Log log = LogFactory.getLog(SecTblUsuarioDAO.class);
	// property constants
	public static final String PW = "pw";

	public void save(SecTblUsuario transientInstance) {
		log.debug("saving SecTblUsuario instance");
		try {
			getSession().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	public void delete(SecTblUsuario persistentInstance) {
		log.debug("deleting SecTblUsuario instance");
		try {
			getSession().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public SecTblUsuario findById(java.lang.String id) {
		log.debug("getting SecTblUsuario instance with id: " + id);
		try {
			SecTblUsuario instance = (SecTblUsuario) getSession().get(
					"com.wellcom.hibernate.model.SecTblUsuario", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List findByExample(SecTblUsuario instance) {
		log.debug("finding SecTblUsuario instance by example");
		try {
			List results = getSession().createCriteria(
					"com.wellcom.hibernate.model.SecTblUsuario").add(
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
		log.debug("finding SecTblUsuario instance with property: "
				+ propertyName + ", value: " + value);
		try {
			String queryString = "from SecTblUsuario as model where model."
					+ propertyName + "= ?";
			Query queryObject = getSession().createQuery(queryString);
			queryObject.setParameter(0, value);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}

	public List findByPw(Object pw) {
		return findByProperty(PW, pw);
	}

	public List findAll() {
		log.debug("finding all SecTblUsuario instances");
		try {
			String queryString = "from SecTblUsuario";
			Query queryObject = getSession().createQuery(queryString);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public SecTblUsuario merge(SecTblUsuario detachedInstance) {
		log.debug("merging SecTblUsuario instance");
		try {
			SecTblUsuario result = (SecTblUsuario) getSession().merge(
					detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(SecTblUsuario instance) {
		log.debug("attaching dirty SecTblUsuario instance");
		try {
			getSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(SecTblUsuario instance) {
		log.debug("attaching clean SecTblUsuario instance");
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
	
	public SecTblUsuario login(String usuario, String password){
		SecTblUsuario user = new SecTblUsuario();
		try{
			String query = "select * from sec_bd.sec_tbl_usuario " +
							"where id_usuario='"+usuario+"' and pw='"+password+"'"; 
			System.out.println(query);
			SQLQuery queryObject = getSession().createSQLQuery(query);
	
			queryObject.setResultTransformer(Criteria.ALIAS_TO_ENTITY_MAP);
			
			List rs=queryObject.list();
			
			Map registro = (Map) rs.get(0);
			user.setIdUsuario(registro.get("id_usuario").toString());
			//user.setRol(registro.get("ID_rol").toString());
			return user;
		}catch(Exception e){
			System.out.println("Error en login!! "+e.getMessage());
			e.getStackTrace();
			return null;
		}
	}
	
}