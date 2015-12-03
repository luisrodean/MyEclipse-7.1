package com.wellcom.hibernate.model;

import java.util.Set;

/**
 * SecTblEscuela entity. @author MyEclipse Persistence Tools
 */
public class SecTblEscuela extends AbstractSecTblEscuela implements
		java.io.Serializable {

	// Constructors

	/** default constructor */
	public SecTblEscuela() {
	}

	/** minimal constructor */
	public SecTblEscuela(String idEscuela, String nombre, String direccion,
			Integer telefono, String correo) {
		super(idEscuela, nombre, direccion, telefono, correo);
	}

	/** full constructor */
	public SecTblEscuela(String idEscuela, String nombre, String direccion,
			Integer telefono, String correo, Set secTblCalificacions,
			Set secTblProfesorMaterias, Set secTblUsuarios) {
		super(idEscuela, nombre, direccion, telefono, correo,
				secTblCalificacions, secTblProfesorMaterias, secTblUsuarios);
	}

}
