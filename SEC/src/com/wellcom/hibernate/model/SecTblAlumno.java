package com.wellcom.hibernate.model;

import java.util.Set;

/**
 * SecTblAlumno entity. @author MyEclipse Persistence Tools
 */
public class SecTblAlumno extends AbstractSecTblAlumno implements
		java.io.Serializable {

	// Constructors

	/** default constructor */
	public SecTblAlumno() {
	}

	/** minimal constructor */
	public SecTblAlumno(Integer idAlummno, SecTblPadre secTblPadre,
			String nombre, String apellidoPaterno, String apellidoMaterno) {
		super(idAlummno, secTblPadre, nombre, apellidoPaterno, apellidoMaterno);
	}

	/** full constructor */
	public SecTblAlumno(Integer idAlummno, SecTblPadre secTblPadre,
			String nombre, String apellidoPaterno, String apellidoMaterno,
			Set secTblAlumnoGrupos) {
		super(idAlummno, secTblPadre, nombre, apellidoPaterno, apellidoMaterno,
				secTblAlumnoGrupos);
	}

}
