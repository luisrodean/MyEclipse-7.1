package com.wellcom.hibernate.model;

import java.util.Set;

/**
 * SecTblProfesor entity. @author MyEclipse Persistence Tools
 */
public class SecTblProfesor extends AbstractSecTblProfesor implements
		java.io.Serializable {

	// Constructors

	/** default constructor */
	public SecTblProfesor() {
	}

	/** minimal constructor */
	public SecTblProfesor(String idProfesor, String nombre,
			String apellidoPaterno, String apellidoMaterno) {
		super(idProfesor, nombre, apellidoPaterno, apellidoMaterno);
	}

	/** full constructor */
	public SecTblProfesor(String idProfesor, String nombre,
			String apellidoPaterno, String apellidoMaterno,
			Set secTblProfesorMaterias) {
		super(idProfesor, nombre, apellidoPaterno, apellidoMaterno,
				secTblProfesorMaterias);
	}

}
