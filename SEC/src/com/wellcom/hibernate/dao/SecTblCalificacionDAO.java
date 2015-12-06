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

import com.wellcom.hibernate.model.GridCalificacion;
import com.wellcom.hibernate.model.SecTblCalificacion;

/**
 * A data access object (DAO) providing persistence and search support for
 * SecTblCalificacion entities. Transaction control of the save(), update() and
 * delete() operations can directly support Spring container-managed
 * transactions or they can be augmented to handle user-managed Spring
 * transactions. Each of these methods provides additional information for how
 * to configure it for the desired type of transaction control.
 * 
 * @see com.wellcom.hibernate.dao.SecTblCalificacion
 * @author MyEclipse Persistence Tools
 */

public class SecTblCalificacionDAO extends BaseHibernateDAO {
	private static final Log log = LogFactory
			.getLog(SecTblCalificacionDAO.class);
	// property constants
	public static final String CALIFICACION = "calificacion";

	public void save(SecTblCalificacion transientInstance) {
		log.debug("saving SecTblCalificacion instance");
		try {
			getSession().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	public void delete(SecTblCalificacion persistentInstance) {
		log.debug("deleting SecTblCalificacion instance");
		try {
			getSession().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public SecTblCalificacion findById(java.lang.String id) {
		log.debug("getting SecTblCalificacion instance with id: " + id);
		try {
			SecTblCalificacion instance = (SecTblCalificacion) getSession()
					.get("com.wellcom.hibernate.model.SecTblCalificacion", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List findByExample(SecTblCalificacion instance) {
		log.debug("finding SecTblCalificacion instance by example");
		try {
			List results = getSession().createCriteria(
					"com.wellcom.hibernate.model.SecTblCalificacion").add(
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
		log.debug("finding SecTblCalificacion instance with property: "
				+ propertyName + ", value: " + value);
		try {
			String queryString = "from SecTblCalificacion as model where model."
					+ propertyName + "= ?";
			Query queryObject = getSession().createQuery(queryString);
			queryObject.setParameter(0, value);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}

	public List findByCalificacion(Object calificacion) {
		return findByProperty(CALIFICACION, calificacion);
	}

	public List findAll() {
		log.debug("finding all SecTblCalificacion instances");
		try {
			String queryString = "from SecTblCalificacion";
			Query queryObject = getSession().createQuery(queryString);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public SecTblCalificacion merge(SecTblCalificacion detachedInstance) {
		log.debug("merging SecTblCalificacion instance");
		try {
			SecTblCalificacion result = (SecTblCalificacion) getSession()
					.merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(SecTblCalificacion instance) {
		log.debug("attaching dirty SecTblCalificacion instance");
		try {
			getSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(SecTblCalificacion instance) {
		log.debug("attaching clean SecTblCalificacion instance");
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
	
	public List<GridCalificacion> getGrid(String escuela){
		
		String query = "SELECT id_calificacion, (id_ciclo)ciclo ,(G.grupo)grupo, (A.nombre)nombre, (A.apellido_paterno)apellido_p," +
			"(A.apellido_materno)apellido_m, (M.nombre_materia)materia, (id_periodo)periodo, calificacion"+
			"FROM sec_tbl_escuela AS E ,sec_tbl_calificacion AS C,sec_tbl_alumno_grupo AS AG "+
			",sec_tbl_alumno AS A,sec_tbl_profesor AS P,sec_tbl_materia AS M "+
			",sec_tbl_profesor_materia AS PME,sec_tbl_grupo AS G WHERE "+
			"C.id_alumno_grupo = AG.id_alumno_grupo AND C.id_profesor_materia = PME.id_profesor_materia "+
			"AND E.id_escuela = '"+ escuela +"' ORDER BY grupo and A.nombre";
		
		List<GridCalificacion> resultado = new ArrayList<GridCalificacion>();
		
		try{
		
			System.out.println(query);
			SQLQuery queryObject = getSession().createSQLQuery(query);
			
			queryObject.setResultTransformer(Criteria.ALIAS_TO_ENTITY_MAP);
			
			List rs = queryObject.list();
			for(int i=0; i<rs.size() ;i++){
				Map registro = (Map) rs.get(i);
				 GridCalificacion grid = new GridCalificacion();
				 
				 grid.setId_calificacion(registro.get("id_calificacion").toString());
				 grid.setCiclo(registro.get("ciclo").toString());
				 grid.setGrupo(registro.get("grupo").toString());
				 grid.setNombre(registro.get("nombre").toString());
				 grid.setApellido_paterno(registro.get("apellido_p").toString());
				 grid.setApellido_materno(registro.get("apellido_m").toString());
				 grid.setNombre_materia(registro.get("materia").toString());
				 grid.setPeriodo(registro.get("periodo").toString());
				 grid.setCalificacion(registro.get("id").toString());
				 
				resultado.add(grid);
			}
			return resultado;
		}catch(Exception e){
		System.err.println(e.getMessage());
		return null;
		}
	}
}