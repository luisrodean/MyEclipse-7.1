package com.wellcom.hibernate.model;

import java.util.Set;

/**
 * SecTblPadre entity. @author MyEclipse Persistence Tools
 */
public class SecTblPadre extends AbstractSecTblPadre implements
		java.io.Serializable {

	// Constructors

	/** default constructor */
	public SecTblPadre() {
	}

	/** minimal constructor */
	public SecTblPadre(String idPadre, String nombre, String apellidoPaterno,
			String apellidoMaterno, String direccion, String pw) {
		super(idPadre, nombre, apellidoPaterno, apellidoMaterno, direccion, pw);
	}

	/** full constructor */
	public SecTblPadre(String idPadre, String nombre, String apellidoPaterno,
			String apellidoMaterno, String direccion, String pw,
			Set secTblAlumnos) {
		super(idPadre, nombre, apellidoPaterno, apellidoMaterno, direccion, pw,
				secTblAlumnos);
	}

}
