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

import java.util.Map;

import com.wellcom.hibernate.dao.SecTblUsuarioDAO;
import com.wellcom.hibernate.model.SecTblUsuario;


public class Login extends CoreAction
{
	/**
	 * Con esta variable controlaremos la versi�n en la que se encuentra esta clase. 
	 * Es utilizada para validar la versi�n al momento de serializar y deserializar.
	 */
	private static final long serialVersionUID = 1L;
	
	//public TblAbwLogEventos 		tblAbwLogEventos;
	//public TblAbwLogEventosDAO 	tblAbwLogEventosDAO;	


	String login;
	String password;
	//String myRole;
	//String myOu1st;
	//String myOu2nd;
	//String myOu3rd;
	//String myLogin;
	String estatusBloqueo = null;
	Boolean autentica0 = false;
	Boolean autentica1 = false;
	Boolean bloqueo = true;
		
	String operResult;
	String event;

	String galleta_sso 		= "galletaToken";    //Este es el nombre de la cookie de Inicio.jsp
	String ssoTokenID 		= null;
	Cookie cookies[] 		= null;
	String ssoTokenLimpio 	= "";
	Boolean dentro 			= false;
	int tiempoRestante = 0;

	char c;
	//Jndi myJndi;

	// Utilizada para desplegar el msg de usuario/password invalido en el jsp
	boolean validacion;	
	
	Integer rolAccVal=0;	/*oolr*/

	public boolean getValidacion()
	{
		return validacion;
	}
	
	public Boolean getBloqueo() {
		return bloqueo;
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
	
	public Login() {
		
		log = LogFactory.getLog(Login.class); 
	}
	

	/**
	 * Casi todo el contenido de este m�todo ha sido un copy&paste del login del sra ya que se necesita autenticar por medio de ldap.
	 * Solo se ha a�adido la variable validaci�n (para desplegar el msg de usuario/password invalido en el jsp) y
	 * la variable de sesi�n pos (encargada de desplegar la opci�n del men� correspondiente -aqui no se hace por medio de la url-)
	 */
	public String execute() throws Exception
	{
		System.out.println("Entro al Login execute");	
		validacion = false;

		//Establecemos la opci�n del men� al index (a0)
		session.put("pos","a0"); 				
		response.addHeader("X-FRAME-OPTIONS", "DENY");
		
		/*-------------------------------------------------------------------------------
		-- Marca del Cambio : WELL-JMQ-P-02-0271-13 Inicia la Modificacion 25/03/2014 -
		-------------------------------------------------------------------------------*/
		String user=request.getParameter("txtfLogin");
		session.put("usuarioActivo", user);
		/*-------------------------------------------------------------------------------
		-- Marca del Cambio : WELL-JMQ-P-02-0271-13 Finaliza la Modificacion 25/03/2014 -
		-------------------------------------------------------------------------------*/
		
		try
		{

			if(request.getParameter("txtfLogin") != null && request.getParameter("txtfPwd") != null && dentro == false)
			{
				String login = new String(request.getParameter("txtfLogin").getBytes("ISO-8859-1"),"UTF-8");
				String password = new String(request.getParameter("txtfPwd").getBytes("ISO-8859-1"),"UTF-8");

				if(login.length() != 0 && password.length() != 0) 
				{
					try
					{
						System.out.println("Autentificando al usuario...");
							SecTblUsuarioDAO usuDao = new SecTblUsuarioDAO();
							SecTblUsuario usuario = new SecTblUsuario();
							System.out.println("Estoy pasando el dao.Login");
							usuario = usuDao.login(login, password);
							if(usuario!=null){
								session.put("grantAccess", "true");
								session.put("rolAccVal", 1);
							}
						
					}
					catch(Exception e)
					{
						log.debug("%$% Ha ocurrido un error al tratar de autentificar al usuario. Usuario y/o contrasena invalidos. %$%");
						//e.printStackTrace();		      		      		
					}
				}
			}
			
				response.sendRedirect("index.jsp");
			
			return LOGIN;
		}
		catch(Exception e)
		{
			log.debug("\n%$% Error en el logueo. %$% \n");
			//e.printStackTrace();
			return LOGIN;
		}
	}

}
