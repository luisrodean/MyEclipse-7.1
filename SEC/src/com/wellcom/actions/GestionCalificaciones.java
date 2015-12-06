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

import com.wellcom.hibernate.dao.SecTblCalificacionDAO;
import com.wellcom.hibernate.dao.SecTblUsuariosDAO;
import com.wellcom.hibernate.model.GridCalificacion;


public class GestionCalificaciones extends CoreAction {
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
		session.put("pos", "a4");	
		System.out.println("\n\n---> Entro a execute Gestion de Calificaciones");
		listaCalificaciones=null;
		session.put("listaCalificaciones", listaCalificaciones);// Limpiamos la lista que está en sesión
		return "pantallaGestionCalificaciones";
		
	}
	

	// Almacena la tabla de usuarios desordenada, tal y como viene de sesión o de un find hacia la bd
	private List<GridCalificacion>	listaCalificaciones;
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
	@Actions
		({	@Action(value 	= "/ajaxLlenarTablaCalificaciones",
				results = {	@Result(name = "success",type = "json") }) 
		})
	public String llenarTablaUsuarios()
	{
		if(validarSesion())
		{	
			// Obtenemos la tabla desordenada
			obtenerTabla();			
			// Realizamos la ordenación 	    
			ordenarTabla();	
		    // Quitamos los registros que no se desplegarán en el grid
		    recortarTabla();
		}
		return SUCCESS;
	}
	
	private void ordenarTabla() 
	{
		if(listaCalificaciones!=null)
		{
			// Creamos el Comparator con los dos parámetros obtenidos desde el jQuery Grid (campo->sidx y orden->sord)
			//System.out.println("Nombre y SIDX "+sidx+sord);
			//objComparator			= new TblAbwParametrosComp(sidx.isEmpty()?"nombre":sidx,sord);
			// Creamos un nuevo TreeSet pasando como parámetro el Comparator creado
			gridListaCalificaciones	= new ArrayList<GridCalificacion>();
			// Agregamos todos los registros de lista de la tabla de Parametroses
			for(GridCalificacion temp : listaCalificaciones){// Se realiza implícitamente la ordenación por el 
				//System.out.println("---------------->"+temp.getNombre());
				gridListaCalificaciones.add(temp);
			}// tipo de collection que se utiliza -> TreeSet		// tipo de collection que se utiliza -> TreeSet
		}			
	}

	@SuppressWarnings("unchecked")
	private void obtenerTabla()
	{
		// Obtenemos la lista de la sesión
		listaCalificaciones = (List<GridCalificacion>) session.get("listaCalificaciones");
		
		if(listaCalificaciones==null)
			records = total = 0;
		else
		{
		    // Obtenemos el total de registros
		    records = listaCalificaciones.size();
		    System.out.println("Lista de calificaciones------>"+records);
		    // Calculamos el total de páginas necesarias    
		    total =(int) Math.ceil((double)records / (double)rows);
//		    System.out.println("total "+total);
		}
	}	
	
	private void recortarTabla() 
	{
		if(listaCalificaciones!=null)
		{
			 // Calculamos el inicio de los registros a mostrar
			desde = (page*rows)-rows;
			// Calculamos el final de los registros a mostrar
			hasta = page*rows>records?records-1:page*rows-1;
			
			Iterator<GridCalificacion> it = gridListaCalificaciones.iterator();
	
			// Quitamos los registros del TreeSet que no se vayan a desplegar en el jQuery grid del jsp
			if(it.hasNext())
				it.next();		
			for(int cont=0;it.hasNext();cont++,it.next())
				if((cont<desde)||(cont>hasta))
					it.remove(); 
		}
	}

		
	// Método con el que se realiza el ABC de los Usuarios
	@Actions
	({	@Action(value 	= "/ajaxAbcCalificaciones", 
				results = 	{@Result(name = "success", 
									type = "json") 
							})  
	})
	public String abcCalificaciones() throws Exception 
	{
		if(validarSesion())
		{
			if(oper.equalsIgnoreCase("add"))
				registrar();
			else if(oper.equalsIgnoreCase("del"))
				borrar(id);
			else if(oper.equalsIgnoreCase("edit"))
				modificar(id);
		}
		else
			redireccionarAlLogin("Gestion Usuarios");
		
		return SUCCESS;
	}

		private void registrar()
	{
		SecTblUsuariosDAO usuDao = new SecTblUsuariosDAO();
		try{
			
			//Se crea el objeto objeto a guardar
			
			//Validar existencia del usuario y el rol
			/*int duplicado=usuDao.checkDuplicates(usuario);
			System.out.println("Duplicado: "+duplicado);
			if(duplicado<1){
				System.out.println("Inserta usuario");
				//usuDao.save(user);
				// Establecemos el mensaje a mostrar en la pantalla .jsp
				operResult = "Usuario creado satisfactoriamente";
				event="Alta de Usuario";
				//logEventos.insertar(operResult, event);
			}else{
				operResult = "El usuario ya esta registrado";
				event="Alta de Usuario";
				
			}*/
		
			
		}
		catch(Exception e)
		{
			operResult = "Error: Bug al insertar un registro en la BD <br/>Contacte al administrador";
			event="Alta de Usuario Rol";
			
			e.printStackTrace();
		}
		
	}
	
	private void modificar(String idCalificacion)
	{
		try{
			SecTblCalificacionDAO califDAO = new SecTblCalificacionDAO();
			System.out.println("Modificar Datos--------------->");
			System.out.println("Identificador a Modifiacar: "+idCalificacion);
			//Se crea el objeto usuario a guardar
	/*		GridCalificacion calificacionM = new GridCalificacion();
			calificacionM.setIdCalificacion(Integer.valueOf(idCalificacion));
			calificacionM.setIdAlumno(idAlumno);
			calificacionM.setCalificacion(calificacion);
			calificacionM.setMateria(materia);
			
			califDAO.merge(calificacionM);
			*/
			// Establecemos el mensaje a mostrar en la pantalla .jsp
			operResult = "Usuario modificado satisfactoriamente";
			event="Alta de Usuario";
			
		}
		catch(Exception e)
		{
			operResult = "Error: Bug al modificar el registro en la BD <br/>Contacte al administrador";
			event="Alta de Usuario Rol";
			
			e.printStackTrace();
		}
	}
	
	private void borrar(String id)

	{
		try{
			SecTblUsuariosDAO usuDao = new SecTblUsuariosDAO();
			System.out.println("Borrar Datos"+id);
			usuDao.borrarUsuario(id.toString());
			// Establecemos el mensaje a mostrar en la pantalla .jsp
			operResult = "Usuario Eliminado satisfactoriamente";
			event="Alta de Usuario";
		
		}
		catch(Exception e)
		{
			operResult = "Error: Bug al eliminar el registro en la BD <br/>Contacte al administrador";
			event="Alta de Usuario Rol";
		
			e.printStackTrace();
		}
	}
		
	
	// Método para realizar la búsqueda
	@Actions
	({	@Action(value 	= "/ajaxAdmBuscarCalificaciones", 
				results = 	{@Result(name = "success", 
									type = "json") 
							})  
	})
	public String buscarCalificaciones()
	{
		SecTblCalificacionDAO califDAO = new SecTblCalificacionDAO();
		
		if(validarSesion()){
			System.out.println("Entro al action de Buscar");
		//	session.put("listaCalificaciones", califDAO.findByFilters(filtroIdAlumno));
		    return SUCCESS;
		}else{
			redireccionarAlLogin("Gestion Calificaciones");
			return null;
		 }
	}
	
	/**
	 * Lo siguiente está relacionado al jQuery Grid  
	 */
	
	// Este objeto se encarga de realizar la ordenación 
	//private RolesUsuariosComp		objComparator;
	// Lista ordenada de los elementos de la tabla de usuarios (debe ser Collection obligatoriamente)  
	private ArrayList<GridCalificacion>	gridListaCalificaciones;
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
	public ArrayList<GridCalificacion> getGridListaCalificaciones()
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
