package com.wellcom.hibernate.dao;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.Criteria;
import org.hibernate.LockMode;
import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.criterion.Example;

import com.wellcom.hibernate.model.AbstractSecTblUsuarios;
import com.wellcom.hibernate.model.SecTblUsuarios;

/**
 * A data access object (DAO) providing persistence and search support for
 * SecTblUsuarios entities. Transaction control of the save(), update() and
 * delete() operations can directly support Spring container-managed
 * transactions or they can be augmented to handle user-managed Spring
 * transactions. Each of these methods provides additional information for how
 * to configure it for the desired type of transaction control.
 * 
 * @see com.wellcom.hibernate.dao.SecTblUsuarios
 * @author MyEclipse Persistence Tools
 */

public class SecTblUsuariosDAO extends BaseHibernateDAO {
	private static final Log log = LogFactory.getLog(SecTblUsuariosDAO.class);
	// property constants
	public static final String PASSWORD = "password";
	public static final String USUARIO = "usuario";

	public void save(SecTblUsuarios transientInstance) {
		log.debug("saving SecTblUsuarios instance");
		try {
			getSession().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	public void delete(SecTblUsuarios persistentInstance) {
		log.debug("deleting SecTblUsuarios instance");
		try {
			getSession().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public SecTblUsuarios findById(java.lang.Integer id) {
		log.debug("getting SecTblUsuarios instance with id: " + id);
		try {
			SecTblUsuarios instance = (SecTblUsuarios) getSession().get(
					"com.wellcom.hibernate.dao.SecTblUsuarios", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List findByExample(SecTblUsuarios instance) {
		log.debug("finding SecTblUsuarios instance by example");
		try {
			List results = getSession().createCriteria(
					"com.wellcom.hibernate.dao.SecTblUsuarios").add(
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
		log.debug("finding SecTblUsuarios instance with property: "
				+ propertyName + ", value: " + value);
		try {
			String queryString = "from SecTblUsuarios as model where model."
					+ propertyName + "= ?";
			Query queryObject = getSession().createQuery(queryString);
			queryObject.setParameter(0, value);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}

	public List findByPassword(Object password) {
		return findByProperty(PASSWORD, password);
	}

	public List findByUsuario(Object usuario) {
		return findByProperty(USUARIO, usuario);
	}

	public List findAll() {
		log.debug("finding all SecTblUsuarios instances");
		try {
			String queryString = "from SecTblUsuarios";
			Query queryObject = getSession().createQuery(queryString);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public SecTblUsuarios merge(SecTblUsuarios detachedInstance) {
		log.debug("merging SecTblUsuarios instance");
		try {
			SecTblUsuarios result = (SecTblUsuarios) getSession().merge(
					detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(SecTblUsuarios instance) {
		log.debug("attaching dirty SecTblUsuarios instance");
		try {
			getSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(SecTblUsuarios instance) {
		log.debug("attaching clean SecTblUsuarios instance");
		try {
			getSession().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}
	
	//**********************************METODOS PRESONALIZADOS ****************************//
	
public List<AbstractSecTblUsuarios> findByFilters(String nombre, String usuario, String email){
    	
		List<AbstractSecTblUsuarios> resultado = new ArrayList<AbstractSecTblUsuarios>();
		
		String query = "select * from sec.sec_tbl_usuarios " +
		"where 1=1 "; 
		
		try{
			// Si los campos vienen nulos los dejamos en blanco para que no truene el toUpperCase()
	    	nombre	= nombre	== null?"":nombre;
	    	usuario	= usuario	== null?"":usuario;
	    	email	= email		== null?"":email;
		
			if(!nombre.isEmpty()){
				query +=  "and nombre like '%"+nombre+"%' ";	
			}if(!usuario.isEmpty()){
				query += "and usuario like '%"+usuario+"%' ";
			}if(!email.isEmpty()){
				query += "and email like '%"+email+"%' ";
			}
			//query+="group by ID"; 
			System.out.println(query);
			SQLQuery queryObject = getSession().createSQLQuery(query);
			
			queryObject.setResultTransformer(Criteria.ALIAS_TO_ENTITY_MAP);
			
			List rs=queryObject.list();
			for(int i=0;i<rs.size();i++){
				Map registro = (Map) rs.get(i);
				SecTblUsuarios user = new SecTblUsuarios();
				user.setIdUsuario(new Integer(registro.get("ID_USUARIO").toString()));
				user.setUsuario(registro.get("USUARIO").toString());
				resultado.add(user);
			}
			return resultado;
		}catch(Exception e){
		e.getMessage();
		return null;
		}

	}
	
	public int checkDuplicates(String usuario){
    	
		List<AbstractSecTblUsuarios> resultado = new ArrayList<AbstractSecTblUsuarios>();
		
		String query = "select count(*) from cnbv.tbl_usuarios " +
		"where 1=1 "; 
		
		try{
			// Si los campos vienen nulos los dejamos en blanco para que no truene el toUpperCase()
	    	
	    	usuario	= usuario	== null?"":usuario;
	    	
		
			if(!usuario.isEmpty()){
				query += "and usuario='"+usuario+"' ";
			}
			//query+="group by ID"; 
			System.out.println(query);
			SQLQuery queryObject = getSession().createSQLQuery(query);
			
			queryObject.setResultTransformer(Criteria.ALIAS_TO_ENTITY_MAP);
			
			
			List rs=queryObject.list();
			for(int i=0;i<rs.size();i++){
				Map registro = (Map) rs.get(i);
				AbstractSecTblUsuarios user = new SecTblUsuarios();
				user.setUsuario(registro.get("usuario").toString());
				resultado.add(user);
			}
			return resultado.size();
		}catch(Exception e){
			e.getMessage();
			return 0;
		}

	}
	
	public void borrarUsuario(String id){
		try{
			String query = "delete from tbl_usuarios " +
					"where ID="+id;
			System.out.println(query);
			SQLQuery queryObject = getSession().createSQLQuery(query);
			getSession().cancelQuery();
			queryObject.executeUpdate();
			
		}catch(Exception e){
			System.out.println("Fallo al eliminar el usuario"+ e.getMessage());
			getSession().getTransaction().rollback();
		}
	}
	
	public SecTblUsuarios login(String usuario, String password){
		SecTblUsuarios user = new SecTblUsuarios();
		try{
			String query = "select * from sec.sec_tbl_usuarios " +
							"where usuario='"+usuario+"' and password='"+password+"'"; 
			System.out.println(query);
			SQLQuery queryObject = getSession().createSQLQuery(query);
	
			queryObject.setResultTransformer(Criteria.ALIAS_TO_ENTITY_MAP);
			
			List rs=queryObject.list();
			
			Map registro = (Map) rs.get(0);
			user.setUsuario(registro.get("USUARIO").toString());
			//user.setRol(registro.get("ID_rol").toString());
			return user;
		}catch(Exception e){
			System.out.println("Error en login!! "+e.getMessage());
			e.getStackTrace();
			return null;
		}
	}
	public int getLastId(){
		int id=0;
		try{
			String query = "select max(ID_USUARIO)ID_USUARIO from sec.sec_tbl_usuarios"; 
			System.out.println(query);
			SQLQuery queryObject = getSession().createSQLQuery(query);
			
			queryObject.setResultTransformer(Criteria.ALIAS_TO_ENTITY_MAP);
			
			List rs = queryObject.list();
			Map registro = (Map) rs.get(0);
			id = Integer.parseInt(registro.get("ID_ALUMNO").toString());
			System.out.println(id);
			return id;
		}catch(Exception e){
			e.getMessage();
			return id;
			
		}
	}
}