/*###############################################################################
# Nombre del Programa :  IBaseHibernateDAO.java									#
# Autor               :  JOAQUIN MOJICA Q. & Wellcom team						#
# Compania            :  WELLCOM S.A. DE C.V.                                   #
# Proyecto/Procliente :  			                	   FECHA: 12/12/2012    #
# Descripcion General :	 														#
#                                                                               #
# Programa Dependiente:  								                        #
# Programa Subsecuente:                                                         #
# Cond. de ejecucion  : 					                                    #
#                                                                               #
#                                                                               #
# Dias de ejecucion   :  														#
#################################################################################
#								MODIFICACIONES                                  #
# Autor               :                                                         #
# Compania            :                                                         #
# Proyecto/Procliente :                              Fecha:                     #
# Modificación        :                                                         #
#-----------------------------------------------------------------------------  #
# Numero de Parametros: 0                                                       #
###############################################################################*/

package com.wellcom.hibernate.dao;

import org.hibernate.Session;


/**
 * Data access interface for domain model
 * @author MyEclipse Persistence Tools
 */
public interface IBaseHibernateDAO {
	public Session getSession();
}