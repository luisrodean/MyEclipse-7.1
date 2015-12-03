/*###############################################################################
# Nombre del Programa :  BaseHibernateDAO.java									#
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
import org.hibernate.Transaction;

import com.wellcom.hibernate.config.HibernateSessionFactory;


/**
 * Data access object (DAO) for domain model
 * @author MyEclipse Persistence Tools
 */
public class BaseHibernateDAO implements IBaseHibernateDAO {
	
	/* 
	 * Esta variable es agregada para que ejecute correctamente los correspondientes métodos de los dao
	 * @Agregada por Eddie Lozada
	 */	
	Transaction tx;
	
	public Session getSession() {
		return HibernateSessionFactory.getSession();
	}
	
}