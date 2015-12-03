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

import com.wellcom.hibernate.dao.TblSecAlumnoDAO;
import com.wellcom.hibernate.model.TblSecAlumno;


public class CambiosBetza extends CoreAction {
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
		System.out.println("\n\n---> Entro a execute Gestion de alumnos");
		listaAlumno=null;
		session.put("listaAlumno", listaAlumno);// Limpiamos la lista que está en sesión
		return "pantallaGestionAlumo";
		
	}
	

	// Almacena la tabla de usuarios desordenada, tal y como viene de sesión o de un find hacia la bd
	private List<TblSecAlumno>	listaAlumno;
	public 	String event;
	
	/*
	 * Generamos los setters con la ayuda de myEclipse para todos los atributos que utilizaremos.
	 */
	
	private String nombre;
	private String apellidoP;
	private String apellidoM;
	private String escuela;
	private String operacion;
	

	public String getOperacion() {
		return operacion;
	}
	public void setOperacion(String operacion) {
		this.operacion = operacion;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getApellidoP() {
		return apellidoP;
	}
	public void setApellidoP(String apellidoP) {
		this.apellidoP = apellidoP;
	}
	public String getApellidoM() {
		return apellidoM;
	}
	public void setApellidoM(String apellidoM) {
		this.apellidoM = apellidoM;
	}
	public String getEscuela() {
		return escuela;
	}
	public void setEscuela(String escuela) {
		this.escuela = escuela;
	}
	
	/*
	 * Estos son los campos que se utilizan para realizar la búsqueda
	 */
	private String FiltroId;
	private String filtroNombre;
	private String filtroApellidoP;
	private String filtroApellidoM;
	private String filtroEscuela;

	
	/*
	 * Generamos los setters para todos los campos de búsqueda.
	 */
	public String getFiltroId() {
		return FiltroId;
	}
	public void setFiltroId(String filtroId) {
		FiltroId = filtroId;
	}
	public String getFiltroEscuela() {
		return filtroEscuela;
	}
	public void setFiltroEscuela(String filtroEscuela) {
		this.filtroEscuela = filtroEscuela;
	}

	public String getFiltroNombre() {
		return filtroNombre;
	}
	public void setFiltroNombre(String filtroNombre) {
		this.filtroNombre = filtroNombre;
	}
	public String getFiltroApellidoP() {
		return filtroApellidoP;
	}
	public void setFiltroApellidoP(String filtroApellidoP) {
		this.filtroApellidoP = filtroApellidoP;
	}
	public String getFiltroApellidoM() {
		return filtroApellidoM;
	}
	public void setFiltroApellidoM(String filtroApellidoM) {
		this.filtroApellidoM = filtroApellidoM;
	}


	
	@Actions
		({	@Action(value 	= "/ajaxLlenarTablaAlumnos",
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
		if(listaAlumno!=null)
		{
			// Creamos el Comparator con los dos parámetros obtenidos desde el jQuery Grid (campo->sidx y orden->sord)
			//System.out.println("Nombre y SIDX "+sidx+sord);
			//objComparator			= new TblAbwParametrosComp(sidx.isEmpty()?"nombre":sidx,sord);
			// Creamos un nuevo TreeSet pasando como parámetro el Comparator creado
			gridListaAlumno	= new ArrayList<TblSecAlumno>();
			// Agregamos todos los registros de lista de la tabla de Parametroses
			for(TblSecAlumno temp : listaAlumno){// Se realiza implícitamente la ordenación por el 
				//System.out.println("---------------->"+temp.getNombre());
				gridListaAlumno.add(temp);
				System.out.println("id "+temp.getId());
				System.out.println("nombre "+temp.getNombre());
			}// tipo de collection que se utiliza -> TreeSet		// tipo de collection que se utiliza -> TreeSet
		}			
	}

	@SuppressWarnings("unchecked")
	private void obtenerTabla()
	{
		// Obtenemos la lista de la sesión
		listaAlumno = (List<TblSecAlumno>) session.get("listaAlumno");
		
		if(listaAlumno == null)
			records = total = 0;
		else
		{
		    // Obtenemos el total de registros
		    records = listaAlumno.size();
		    System.out.println("Lista de alumnos------>   "+records);
		    // Calculamos el total de páginas necesarias    
		    total =(int) Math.ceil((double)records / (double)rows);
//		    System.out.println("total "+total);
		}
	}	
	
	private void recortarTabla() 
	{
		if(listaAlumno != null)
		{
			 // Calculamos el inicio de los registros a mostrar
			desde = (page*rows)-rows;
			// Calculamos el final de los registros a mostrar
			hasta = page*rows>records?records-1:page*rows-1;
			
			Iterator<TblSecAlumno> it = gridListaAlumno.iterator();
	
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
	({	@Action(value 	= "/ajaxAbcAlumnos", 
				results = 	{@Result(name = "success", 
									type = "json") 
							})  
	})
	public String abcUsuarios() throws Exception 
	{
		System.out.println("Id usuario" + id);
		System.out.println("Nombre" + nombre);
		System.out.println("Apellido paterno" + apellidoM);
		System.out.println("Apellido materno" + apellidoP);
		System.out.println("Escuela" + escuela);

		operResult = "Alumno regreso mensaje";

		
		if(validarSesion())
		{
			if(operacion.equalsIgnoreCase("Agregar")){
				Agregar();
			}
			//else if(operacion.equalsIgnoreCase("Eliminar"))
			//	borrar(id);
			else if(operacion.equalsIgnoreCase("Actualizar"))
			    modificar(id);
		}
		else
			redireccionarAlLogin("Gestion Alumno");
		return SUCCESS;
	}

		private void Agregar()
	{
		TblSecAlumnoDAO usuDao = new TblSecAlumnoDAO();
		try{
			//Envio de datos del jsp
			System.out.println("Entra a Regitrar");
			System.out.println("Nombre: " + nombre);
			System.out.println("Apellido Paterno: " + apellidoP);
			System.out.println("Apellido Materno: " + apellidoM);
			System.out.println("Escuela: " + escuela);
			
			//Se crea el objeto usuario a guardar
			TblSecAlumno user = new TblSecAlumno();
			
				int nuevoId=usuDao.getLastId()+1;
				System.out.println("/n/n Ultimo ID" + nuevoId);
				user.setId(nuevoId);
				user.setNombre(nombre);
				user.setApellidoP(apellidoP);
				user.setApellidoM(apellidoM);
				user.setEscuela(escuela);

			
			//Validar existencia del usuario 
			int duplicado=usuDao.checkDuplicates(nombre);
			System.out.println("Duplicado: "+duplicado);
			if(duplicado<1){
				System.out.println("Inserta alumno");
				usuDao.save(user);
				// Establecemos el mensaje a mostrar en la pantalla .jsp
				operResult = "Alumno creado satisfactoriamente";
				event="Alta de alumno";
				//logEventos.insertar(operResult, event);
			}else{
				operResult = "El alumno ya esta registrado";
				event="Alta de alumno";
				
			}
		
			
		}
		catch(Exception e)
		{
			operResult = "Error: Bug al insertar un registro en la BD <br/>Contacte al administrador";
			event="Alta de Usuario Rol";
			
			e.printStackTrace();
		}
		
	}
	
	private void modificar(int Id)
	{
		try{
			TblSecAlumnoDAO usuDao = new TblSecAlumnoDAO();
			System.out.println("Modificar Datos--------------->");
			System.out.println("Identificador a Modificar: "+ Id +nombre+apellidoP+apellidoM+escuela);
			//Se crea el objeto usuario a guardar
			TblSecAlumno user = new TblSecAlumno();
				user.setId(id);
				user.setNombre(nombre);
				user.setEscuela(escuela);
				user.setApellidoM(apellidoM);
				user.setApellidoP(apellidoP);
			usuDao.merge(user);
			
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
	
	private void borrar(int id)

	{
		try{
		TblSecAlumnoDAO usuDao = new TblSecAlumnoDAO();
			System.out.println("Borrar Datos" + id);
			usuDao.borrarUsuario(id);
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
	({	@Action(value 	= "/ajaxAdmBuscarAlumnos", 
				results = 	{@Result(name = "success", 
									type = "json") 
							})  
	})
	public String buscarAlumno()
	{
		if(validarSesion()){
			System.out.println("Entro al action de Buscar");
			session.put("listaAlumno", new TblSecAlumnoDAO().findByFilters(filtroNombre,filtroApellidoP,filtroApellidoM, filtroEscuela));
		    return SUCCESS;
		}else{
			redireccionarAlLogin("Gestion Alumno");
			return null;
		 }
	}
	
	/**
	 * Lo siguiente está relacionado al jQuery Grid  
	 */
	
	// Este objeto se encarga de realizar la ordenación 
	//private RolesUsuariosComp		objComparator;
	// Lista ordenada de los elementos de la tabla de usuarios (debe ser Collection obligatoriamente)  
	private ArrayList<TblSecAlumno>	gridListaAlumno;
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
	private Integer  id;	
	// Resultado de la operación realizada (mensaje q se despliega en el jsp)
	private String	operResult;
	
	/*
	 * Aqui vienen los getters y setters del jQuery Grid
	 * 
	 */
	public ArrayList<TblSecAlumno> getGridListaAlumno()
	{
		return gridListaAlumno;
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
	public void setId(Integer id)
	{
		this.id=id;
	}	
	
	public Integer getId() {
		return this.id;
	}
	/**
	 * Aquí acaba lo relacionado con jQuery grid
	 */
	
}
