package com.wellcom.hibernate.model;

import java.util.Set;

/**
 * SecTblMateria entity. @author MyEclipse Persistence Tools
 */
public class SecTblMateria extends AbstractSecTblMateria implements
		java.io.Serializable {

	// Constructors

	/** default constructor */
	public SecTblMateria() {
	}

	/** minimal constructor */
	public SecTblMateria(String idMateria, String nombreMateria) {
		super(idMateria, nombreMateria);
	}

	/** full constructor */
	public SecTblMateria(String idMateria, String nombreMateria,
			Set secTblProfesorMaterias) {
		super(idMateria, nombreMateria, secTblProfesorMaterias);
	}

}
