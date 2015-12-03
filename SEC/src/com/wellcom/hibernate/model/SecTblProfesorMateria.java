package com.wellcom.hibernate.model;

import java.util.Set;

/**
 * SecTblProfesorMateria entity. @author MyEclipse Persistence Tools
 */
public class SecTblProfesorMateria extends AbstractSecTblProfesorMateria
		implements java.io.Serializable {

	// Constructors

	/** default constructor */
	public SecTblProfesorMateria() {
	}

	/** minimal constructor */
	public SecTblProfesorMateria(String idProfesorMateria,
			SecTblProfesor secTblProfesor, SecTblMateria secTblMateria,
			SecTblEscuela secTblEscuela) {
		super(idProfesorMateria, secTblProfesor, secTblMateria, secTblEscuela);
	}

	/** full constructor */
	public SecTblProfesorMateria(String idProfesorMateria,
			SecTblProfesor secTblProfesor, SecTblMateria secTblMateria,
			SecTblEscuela secTblEscuela, Set secTblCalificacions) {
		super(idProfesorMateria, secTblProfesor, secTblMateria, secTblEscuela,
				secTblCalificacions);
	}

}
