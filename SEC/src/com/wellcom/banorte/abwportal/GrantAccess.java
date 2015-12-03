/*###############################################################################
# Nombre del Programa :  GrantAccess.java                                       #
# Autor               :  JOAQUIN MOJICA Q.                                      #
# Compania            :  WELLCOM S.A. DE C.V.                                   #
# Proyecto/Procliente :  			                 	   FECHA:12/03/2012     #
# Descripcion General :	 Clase para el manejo de la seguridad        	        #
#                                                                               #
# Programa Dependiente:                                                         #
# Programa Subsecuente:                                                         #
# Cond. de ejecucion  :  Acceder al sistema                                     #
#                                                                               #
#                                                                               #
# Dias de ejecucion   :  A Peticion del web, se pueden ejecutar n instancias    #
#################################################################################
#								MODIFICACIONES                                  #
# Autor               :                                                         #
# Compania            :                                                         #
# Proyecto/Procliente :                              Fecha:                     #
# Modificación        :                                                         #
#-----------------------------------------------------------------------------  #
# Numero de Parametros: 0                                                       #
###############################################################################*/
package com.wellcom.banorte.abwportal;

import javax.servlet.http.HttpSession;

/**
 *
 * @author afibarra / modificado por Eddie Lozada
 */
public class GrantAccess {

	private String sessionVar;
    
    public GrantAccess() 
    {
    }
    
    public final boolean AccessGranted( HttpSession s, String a ) 
    {    
    	//return true;
    	
        sessionVar = ( String ) s.getAttribute( "grantAccess" );
        //System.out.println("----------------------->"+sessionVar);
        if( sessionVar == null ) 
            return false;
        else 
           return true;
        
    }    

}
