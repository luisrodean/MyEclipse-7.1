/**
 * @author Wellcom - Eddie Lozada 2012/03/28
 * 
 * Todas las clases (a excepción de la lista de abajo) que actúen como "Actions" del framework struts2 heredarán 
 * de esta clase ya que contiene interfaces, variables y métodos necesarios para su correcto funcionamiento. 
 * 
 * Excepciones:
 * 					Salir.java
 */
package com.wellcom.actions;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.Map;

import com.opensymphony.xwork2.ActionSupport;

import org.apache.commons.logging.Log;
import org.apache.log4j.xml.DOMConfigurator;
import org.apache.struts2.interceptor.ApplicationAware;
import org.apache.struts2.interceptor.SessionAware;
import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.interceptor.ServletResponseAware;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.wellcom.banorte.abwportal.GrantAccess;
//import com.wellcom.hibernate.dao.HerramientasDAO;


abstract class BetzaCoreAction 	extends 	ActionSupport 
							implements 	ApplicationAware,
										SessionAware, 
										ServletRequestAware, 
										ServletResponseAware 
												
{

	/**
	 * Con esta variable controlaremos la versión en la que se encuentra esta clase. 
	 * Es utilizada para validar la versión al momento de serializar y deserializar.
	 */
	private static final long serialVersionUID = 1L;
	
	/* Las siguiente variable la utilizaremos para manejar la aplicación y sus atributos */
	protected Map<String, Object> 	application;
	/* Las siguiente variable la utilizaremos para manejar la sesión y sus atributos */
	protected Map<String, Object> 	session;
	/* Las siguiente variable la utilizaremos para manejar al request y sus atributos*/
	protected HttpServletRequest 	request;
	/* Las siguiente variable la utilizaremos para manejar al response y sus atributos */
	protected HttpServletResponse 	response;
	
	//protected static String key = new HerramientasDAO().leerFactor();
	
	//protected static BigDecimal sec1 = new HerramientasDAO().siguienteValor(1);
	
	Log log;
	
	public BetzaCoreAction() {
		
		// Cargamos la configuración de Log4J desde un archivo accesible via CLASSPATH  
	    DOMConfigurator.configure(Login.class.getClassLoader().getResource("log4j.xml")); 
	}

	protected boolean validarSesion(){
		
		return (new GrantAccess().AccessGranted(request.getSession(), "grantNombre"));
	}
	
	protected void redireccionarAlLogin(String nombreAction)
	{
		/*	Si entra aquí significa que no tiene una sesión válida	*/
		try {
//			System.out.println("Redireccionaremos la petición al login desde el action "+nombreAction);
			response.sendRedirect("BetzaLogin.jsp");
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

}
