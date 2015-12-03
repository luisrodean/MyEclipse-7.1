/*###############################################################################
# Nombre del Programa :  WellException.java                                     #
# Autor               :  JOAQUIN MOJICA Q.                                      #
# Compania            :  WELLCOM S.A. DE C.V.                                   #
# Proyecto/Procliente :  P-02-0379-10                 	   FECHA:12/12/2012     #
# Descripcion General :	 Manejo de Excepciones				                    #
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

package com.wellcom.exceptions;

/**
 *
 * <p>Título: Clase WellException</p>
 * <p>Descripción: Manejo de excepciones</p>
 * <p>Compañía: Wellcom</p>
 * @author M. en C. Armando F. Ibarra
 */
public class WellException
  extends Exception {

  /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

public WellException() {

    super();
  }

  public WellException(String value) {

    super(value);
  }
}
