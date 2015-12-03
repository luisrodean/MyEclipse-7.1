/*###############################################################################
# Nombre del Programa :  Login.java                      	             		#
# Autor               :  Eddie Lozada											#
# Compania            :  WELLCOM S.A. DE C.V.                                   #
# Proyecto/Procliente :  			                 	   FECHA:20/03/2012     #
# Descripcion General :	 Escribe en el log del aplication server				#
#                                                                               #
# Programa Dependiente:                                                			#
# Programa Subsecuente:                                                         #
# Cond. de ejecucion  :                                       					#
#                                                                               #
#                                                                               #
# Dias de ejecucion   :  A Peticion del web, se pueden ejecutar n instancias  	#
#################################################################################
#								MODIFICACIONES                                  #
# Autor               :  Rogelio Estrada		                                #
# Compania            :  					                                    #
# Proyecto/Procliente :                              Fecha: 03/01/2014          #
# Modificacion        :                                                       	#
#-----------------------------------------------------------------------------  #
# Numero de Parametros: 0                                                       #
###############################################################################*/


package com.wellcom.actions;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.LogFactory;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Actions;
import org.apache.struts2.convention.annotation.Result;

import mx.com.prosa.sso.AMConnectionUtil;

import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;
import java.net.URLDecoder;
import java.util.Map;

import com.wellcom.directory.Jndi;
import com.wellcom.hibernate.dao.SecTblUsuariosDAO;
import com.wellcom.hibernate.dao.TblParametrosDAO;
import com.wellcom.hibernate.dao.TblSecAlumnoDAO;
import com.wellcom.hibernate.model.SecTblUsuarios;
import com.wellcom.hibernate.model.TblSecAlumno;

public class BetzaLogin extends CoreAction
{
	/**
	 * Con esta variable controlaremos la versión en la que se encuentra esta clase. 
	 * Es utilizada para validar la versión al momento de serializar y deserializar.
	 */
	private static final long serialVersionUID = 1L;
	
	//public TblAbwLogEventos 		tblAbwLogEventos;
	//public TblAbwLogEventosDAO 	tblAbwLogEventosDAO;	


	String nombre;
	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getApPat() {
		return apPat;
	}

	public void setApPat(String apPat) {
		this.apPat = apPat;
	}

	public String getApMat() {
		return apMat;
	}

	public void setApMat(String apMat) {
		this.apMat = apMat;
	}

	public String getEscuela() {
		return escuela;
	}

	public void setEscuela(String escuela) {
		this.escuela = escuela;
	}
	String apPat;
	String apMat;
	String escuela;
	String operResult;
	
	//crear objetos model y config
	TblSecAlumnoDAO AlumnoDAO;
	TblSecAlumno AlumnoModel;
	
	
	int tiempoRestante = 0;


	// Utilizada para desplegar el msg de usuario/password invalido en el jsp
	boolean validacion;	
	
	Integer rolAccVal=0;	/*oolr*/

	public boolean getValidacion()
	{
		return validacion;
	}
	

	/*
	 * 			Generamos los setters requeridos de las clases implementadas
	 *  - ApplicationAware - SessionAware - ServletRequestAware - ServletResponseAware -  
	 */
	public void setApplication(Map<String, Object> application) 
	{
		this.application = application;
	}
	public void setSession(Map<String, Object> session) 
	{
		this.session = session;
	}
	public void setServletRequest(HttpServletRequest request) 
	{
		this.request = request;
	}
	public void setServletResponse(HttpServletResponse response) 
	{
		this.response = response;
	}
	
	public BetzaLogin() {
		
		log = LogFactory.getLog(BetzaLogin.class); 
	
	}
	

	/**
	 * Casi todo el contenido de este método ha sido un copy&paste del login del sra ya que se necesita autenticar por medio de ldap.
	 * Solo se ha añadido la variable validación (para desplegar el msg de usuario/password invalido en el jsp) y
	 * la variable de sesión pos (encargada de desplegar la opción del menú correspondiente -aqui no se hace por medio de la url-)
	 */
	public String execute() throws Exception
	{
		System.out.println("Entro al Login execute");
		return LOGIN;
	}
	// Método con el que se realiza el ABC de los Usuarios
	@Actions
	({	@Action(value 	= "/ajaxlogin", 
				results = 	{@Result(name = "success", 
									type = "json") 
							})  
	})
	public String Login() throws Exception 
	{
		System.out.println("Nombre" + nombre);
		System.out.println("Apellido paterno" + apPat);
		System.out.println("Apellido materno" + apMat);
		System.out.println("Escuela" + escuela);
		try{
			//instaciar objetos model y config
		AlumnoModel = new TblSecAlumno();
		AlumnoDAO   = new TblSecAlumnoDAO();
		
		//enviar datos
		AlumnoModel.setId(AlumnoDAO.getLastId()+1);
		AlumnoModel.setNombre(nombre);
		AlumnoModel.setApellidoP(apPat);
		AlumnoModel.setApellidoM(apMat);
		AlumnoModel.setEscuela(escuela);
		System.out.println("Envio de datos");
		
		//guardar datos
		AlumnoDAO.save(AlumnoModel);
		System.out.println("Guardar datos");
		
			
		}catch(Exception e){
			
		}
		//Mensaje enviado a JSP
		operResult = "Datos guardados exitosamente " + "<br>"+ nombre + " " + apPat + " " + apMat + " " + escuela + "<input type='button'>";
		return SUCCESS;
	}

	public String getOperResult() {
		return operResult;
	}

	public void setOperResult(String operResult) {
		this.operResult = operResult;
	}
	
}
