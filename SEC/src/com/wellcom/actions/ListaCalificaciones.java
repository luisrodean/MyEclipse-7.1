package com.wellcom.actions;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Actions;
import org.apache.struts2.convention.annotation.Result;

import com.wellcom.hibernate.dao.SecTblCalificacionesDAO;
import com.wellcom.hibernate.dao.SecTblUsuariosDAO;
import com.wellcom.hibernate.model.AbstractSecTblCalificaciones;
import com.wellcom.hibernate.model.AbstractSecTblUsuarios;
import com.wellcom.hibernate.model.SecTblCalificaciones;
import com.wellcom.hibernate.model.SecTblUsuarios;


public class ListaCalificaciones extends CoreAction {
	private static final long serialVersionUID = 1L;

	public void setApplication(Map<String, Object> arg0) {
		this.application = arg0;
		
	}
	public void setSession(Map<String, Object> arg0) {
		// TODO Auto-generated method stub
		this.session= arg0;
	}

	public void setServletRequest(HttpServletRequest arg0) {
		// TODO Auto-generated method stub
		this.request = arg0;
	}

	public void setServletResponse(HttpServletResponse arg0) {
		// TODO Auto-generated method stub
		this.response = arg0;
	}
	public String execute(){
		session.put("pos", "a10");	
		System.out.println("\n\n---> Entro a execute Gestion de Calificaciones");
		listaCalificaciones=null;
		session.put("listaUsuarios", listaCalificaciones);// Limpiamos la lista que está en sesión
		return "pantallaListaCalificaciones";
		
	}
	

	// Almacena la tabla de usuarios desordenada, tal y como viene de sesión o de un find hacia la bd
	private List<AbstractSecTblCalificaciones>	listaCalificaciones;
	public 	String event;
	
	/*
	 * Estos son los campos editables de la tabla tblAbwUsuario. NO es necesario pasarle 
	 * el id porque lo almacenamos en la variable "id" en la parte del jQueryGrid
	 */
	
	private Integer idAlumno;
	private String materia;
	private Float calificacion;
	
	/*
	 * Estos son los campos que se utilizan para realizar la búsqueda
	 */
	//private String FiltroUsuId;
	private String filtroIdAlumno;
	
	/*
	 * Generamos los setters con la ayuda de myEclipse para todos los atributos que utilizaremos.
	 */
	

	/*
	 * Generamos los setters para todos los campos de búsqueda.
	 */
	
	public String getMateria() {
		return materia;
	}
	public Integer getIdAlumno() {
		return idAlumno;
	}
	public void setIdAlumno(Integer idAlumno) {
		this.idAlumno = idAlumno;
	}
	public void setMateria(String materia) {
		this.materia = materia;
	}
	public Float getCalificacion() {
		return calificacion;
	}
	public void setCalificacion(Float calificacion) {
		this.calificacion = calificacion;
	}
	public String getFiltroIdAlumno() {
		return filtroIdAlumno;
	}
	public void setFiltroIdAlumno(String filtroIdAlumno) {
		this.filtroIdAlumno = filtroIdAlumno;
	}
	
	
	/**
	 * Lo siguiente está relacionado al jQuery Grid  
	 */
	
	// Este objeto se encarga de realizar la ordenación 
	//private RolesUsuariosComp		objComparator;
	// Lista ordenada de los elementos de la tabla de usuarios (debe ser Collection obligatoriamente)  
	private ArrayList<AbstractSecTblCalificaciones>	gridListaCalificaciones;
	// Indica cuantas filas queremos mostrar (Atributo 'rowNum' en el grid del jsp)  
	private int 	rows;  
	// Total de páginas necesarias para mostrar los registros obtenidos
	private int 	total;  
	// Página actual (por default el grid la setea a 1)  
	private int		page;  
	// Indica el inicio del paginado
	private int 	desde;
	// Indica el final del paginado
	private int 	hasta;
	// Número total de registros obtenidos
	private int 	records;
	// Tipo de ordenamiento (asc - desc)  
	//private String	sord;  
	// Campo seleccionado para realizar el ordenamiento (atributo 'index' en el grid del jsp)
	//private String  sidx;
	// Indica que tipo de operación (alta->add, baja->del, cambio->edit) se quiere realizar
	private String  oper;
	// Indica el registro que se quiere modificar o borrar (atributo 'key' del jQuery grid del jsp)
	private String  id;	
	// Resultado de la operación realizada (mensaje q se despliega en el jsp)
	private String	operResult;
	
	/*
	 * Aqui vienen los getters y setters del jQuery Grid
	 * 
	 */
	public ArrayList<AbstractSecTblCalificaciones> getGridListaCalificaciones()
	{
		return gridListaCalificaciones;
	}
	public Integer getRecords() {
		return records;
	}
	public Integer getTotal() {
		return total;
	}
	public Integer getPage()
	{
		return this.page;
	}
	public String getOperResult()
	{
		return operResult;
	}
	
	public void setRecords(Integer records) {
		this.records= records;
	}
	
	public void setTotal(Integer total) {
		this.total= total;
	}

	/*public void setSord(String sord) {
		this.sord = sord;
	}
	public void setSidx(String sidx) {
		this.sidx = sidx;
	}*/
	public void setRows(int rows)
	{
		this.rows = rows;
	}
	public void setPage(int page)
	{
		this.page = page;
	}
	public void setOper(String oper)
	{
		this.oper=oper;
	}	
	public void setId(String id)
	{
		this.id=id;
	}	
	/**
	 * Aquí acaba lo relacionado con jQuery grid
	 */
	
}
