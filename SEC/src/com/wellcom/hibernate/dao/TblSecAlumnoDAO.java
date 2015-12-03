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
import com.wellcom.hibernate.model.TblSecAlumno;

/**
 * A data access object (DAO) providing persistence and search support for
 * TblSecAlumno entities. Transaction control of the save(), update() and
 * delete() operations can directly support Spring container-managed
 * transactions or they can be augmented to handle user-managed Spring
 * transactions. Each of these methods provides additional information for how
 * to configure it for the desired type of transaction control.
 * 
 * @see com.wellcom.hibernate.dao.TblSecAlumno
 * @author MyEclipse Persistence Tools
 */

public class TblSecAlumnoDAO extends BaseHibernateDAO {
	private static final Log log = LogFactory.getLog(TblSecAlumnoDAO.class);
	// property constants
	public static final String NOMBRE = "nombre";
	public static final String ID = "id";
	public static final String APELLIDO_P = "apellidoP";
	public static final String APELLIDO_M = "apellidoM";
	public static final String ESCUELA = "escuela";

	public void save(TblSecAlumno transientInstance) {
		log.debug("saving TblSecAlumno instance");
		try {
			tx = getSession().beginTransaction();/*AGREGAR*/
			getSession().save(transientInstance);
			getSession().getTransaction().commit();/*AGREGAR*/
			log.debug("save successful");
		} catch (RuntimeException re) {
			tx.rollback();/*AGREGAR*/
			log.error("save failed", re);
			throw re;
		}finally/*AGREGAR*/
		{/*AGREGAR*/
			getSession().close();/*AGREGAR*/
		}/*AGREGAR*/
	}

	public void delete(TblSecAlumno persistentInstance) {
		log.debug("deleting TblSecAlumno instance");
		try {
			tx = getSession().beginTransaction();/*AGREGAR*/
			getSession().delete(persistentInstance);
			getSession().getTransaction().commit();/*AGREGAR*/

			log.debug("delete successful");
		} catch (RuntimeException re) {
			tx.rollback();/*AGREGAR*/
			log.error("delete failed", re);
			throw re;
		}finally/*AGREGAR*/
		{/*AGREGAR*/
			getSession().close();/*AGREGAR*/
		}/*AGREGAR*/
	}

	public TblSecAlumno findById(java.lang.Integer id) {
		log.debug("getting TblSecAlumno instance with id: " + id);
		try {
			TblSecAlumno instance = (TblSecAlumno) getSession().get(
					"com.wellcom.hibernate.model.TblSecAlumno", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List findByExample(TblSecAlumno instance) {
		log.debug("finding TblSecAlumno instance by example");
		try {
			List results = getSession().createCriteria(
					"com.wellcom.hibernate.model.TblSecAlumno").add(
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
		log.debug("finding TblSecAlumno instance with property: "
				+ propertyName + ", value: " + value);
		try {
			String queryString = "from TblSecAlumno as model where model."
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

	public List findByApellidoP(Object apellidoP) {
		return findByProperty(APELLIDO_P, apellidoP);
	}

	public List findByApellidoM(Object apellidoM) {
		return findByProperty(APELLIDO_M, apellidoM);
	}

	public List findByEscuela(Object escuela) {
		return findByProperty(ESCUELA, escuela);
	}

	public List findAll() {
		log.debug("finding all TblSecAlumno instances");
		try {
			String queryString = "from TblSecAlumno";
			Query queryObject = getSession().createQuery(queryString);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public TblSecAlumno merge(TblSecAlumno detachedInstance) {
		log.debug("merging TblSecAlumno instance");
		try {
			tx = getSession().beginTransaction();/*AGREGAR*/
			TblSecAlumno result = (TblSecAlumno) getSession().merge(
						detachedInstance);
			getSession().getTransaction().commit();/*AGREGAR*/
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			tx.rollback();/*AGREGAR*/
			log.error("merge failed", re);
			throw re;
		}finally/*AGREGAR*/
		{/*AGREGAR*/
			getSession().close();/*AGREGAR*/
		}/*AGREGAR*/
	}

	public void attachDirty(TblSecAlumno instance) {
		log.debug("attaching dirty TblSecAlumno instance");
		try {
			getSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(TblSecAlumno instance) {
		log.debug("attaching clean TblSecAlumno instance");
		try {
			getSession().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}
	
	
	
	//**********************************METODOS PRESONALIZADOS ****************************//
	
	public List<TblSecAlumno> findByFilters(String nombre, String apellidoP, String apellidoM, String escuela){
	    	
			List<TblSecAlumno> resultado = new ArrayList<TblSecAlumno>();
			
			String query = "select * from tbl_sec_alumno where 1=1 ";
			
			try{
				// Si los campos vienen nulos los dejamos en blanco para que no truene el toUpperCase()
		    	nombre		= nombre	== null?"":nombre;
		    	apellidoP	= apellidoP	== null?"":apellidoP;
		    	apellidoM	= apellidoM	== null?"":apellidoM;
		    	escuela		= escuela	== null?"":escuela;
			
				if(!nombre.isEmpty()){
					query +=  "and nombre like '%" +nombre +"' ";	
				}if(!apellidoP.isEmpty()){
					query += "and apellidoP like '%"+apellidoP +"' ";
				}if(!apellidoM.isEmpty()){
					query += "and apellidoM like '%"+apellidoM +"' ";
				}if(!escuela.isEmpty()){
					query += "and escuela like '%"+escuela+"' ";
				}
				//query+="group by ID"; 
				System.out.println(query);
				SQLQuery queryObject = getSession().createSQLQuery(query);
				
				queryObject.setResultTransformer(Criteria.ALIAS_TO_ENTITY_MAP);
				
				List rs=queryObject.list();
				for(int i=0;i<rs.size();i++){
					Map registro = (Map) rs.get(i);
					 TblSecAlumno user = new TblSecAlumno();
					//user.setIdUsuario(new Integer(registro.get("ID_USUARIO").toString()));
					 System.out.println("\n\n-------------->  nombre de filtro : " + registro.get("nombre").toString());
					 user.setId(new Integer(registro.get("id").toString()));
					 user.setNombre(registro.get("nombre").toString());
					 user.setApellidoP(registro.get("apellidoP").toString());
					 user.setApellidoM(registro.get("apellidoM").toString());
					 user.setEscuela(registro.get("escuela").toString());

					 //user.setId(Integer.parseInt(registro.get("id").toString()));
					resultado.add(user);
				}
				return resultado;
			}catch(Exception e){
			System.err.println(e.getMessage());
			return null;
			}

		}
		
		public void borrarUsuario(int id){
			try{
				String query = "delete from tbl_sec_alumno where id=" +id;
				System.out.println(query);
				SQLQuery queryObject = getSession().createSQLQuery(query);
				getSession().cancelQuery();
				queryObject.executeUpdate();
				
			}catch(Exception e){
				System.out.println("Fallo al eliminar el usuario"+ e.getMessage());
				getSession().getTransaction().rollback();
			}
		}
		
		
	
	public int getLastId(){
		int id=0;
		try{
			String query = "select max(ID)ID_ALUMNO from tbl_sec_alumno"; 
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
	

	public int checkDuplicates(String nombre){
    	
		List<TblSecAlumno> resultado = new ArrayList<TblSecAlumno>();
		
		String query = "select count(*) from tbl_sec_alumno";
		///"where 1=1 "; 
		
		try{
			// Si los campos vienen nulos los dejamos en blanco para que no truene el toUpperCase()
	    	
			nombre	= nombre	== null?"":nombre;
	    	
		
			if(!nombre.isEmpty()){
				query += "and usuario='"+nombre+"' ";
			}
			//query+="group by ID"; 
			System.out.println(query);
			SQLQuery queryObject = getSession().createSQLQuery(query);
			
			queryObject.setResultTransformer(Criteria.ALIAS_TO_ENTITY_MAP);
			
			
			List rs=queryObject.list();
			for(int i=0;i<rs.size();i++){
				Map registro = (Map) rs.get(i);
				TblSecAlumno user = new TblSecAlumno();
				user.setNombre(registro.get("nombre").toString());
				resultado.add(user);
			}
			return resultado.size();
		}catch(Exception e){
			e.getMessage();
			return 0;
		}

	}
	
	
}
