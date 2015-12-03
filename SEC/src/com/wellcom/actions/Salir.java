/*###############################################################################
# Nombre del Programa :  Salir.java    				                            #
# Autor               :  JOAQUIN MOJICA Q.										#
# Compania            :  WELLCOM S.A. DE C.V.                                   #
# Proyecto/Procliente :  			                 	   FECHA:12/12/2012     #
# Descripcion General :	 					                  					#
#                                                                               #
# Programa Dependiente:                                                  		#
# Programa Subsecuente:                                                         #
# Cond. de ejecucion  :                                       					#
#                                                                               #
#                                                                               #
# Dias de ejecucion   :  A Peticion del web, se pueden ejecutar n instancias  	#
#################################################################################
#								MODIFICACIONES                                  #
# Autor               :                                                         #
# Compania            :                                                         #
# Proyecto/Procliente :                              Fecha:                     #
# ModificaciÃ³n        :                                                       	#
#-----------------------------------------------------------------------------  #
# Numero de Parametros: 0                                                       #
###############################################################################*/
package com.wellcom.actions;

import java.util.Map;
import org.apache.struts2.interceptor.SessionAware;
import com.opensymphony.xwork2.ActionSupport;

public class Salir extends ActionSupport implements SessionAware
{

	/**
	 * Con esta variable controlaremos la versión en la que se encuentra esta clase. 
	 * Es utilizada para validar la versión al momento de serializar y deserializar.
	 */
	private static final long serialVersionUID = 1L;
	private Map <String, Object> session;
	
	public void setSession(Map<String, Object> session) 
	{
		this.session = session;
	}

	public String execute()
	{
//		System.out.println("\nHa entrado al action Salir \n");
		try
		{
			session.remove("grantAccess");
			((org.apache.struts2.dispatcher.SessionMap<String, Object>)session).invalidate();
		}
		catch(Exception e)
		{
//			System.out.println("\nSe ha producido un error al tratar de invalidar la sesión\n");
			e.printStackTrace();
		}
		return LOGIN;					
	}

}
