package com.wellcom.hibernate.model;

import java.util.Set;

/**
 * SecTblRol entity. @author MyEclipse Persistence Tools
 */
public class SecTblRol extends AbstractSecTblRol implements
		java.io.Serializable {

	// Constructors

	/** default constructor */
	public SecTblRol() {
	}

	/** minimal constructor */
	public SecTblRol(Integer idRol, String tipoRol) {
		super(idRol, tipoRol);
	}

	/** full constructor */
	public SecTblRol(Integer idRol, String tipoRol, Set secTblUsuarios) {
		super(idRol, tipoRol, secTblUsuarios);
	}

}
