package com.wellcom.hibernate.model;

/**
 * SecTblUsuario entity. @author MyEclipse Persistence Tools
 */
public class SecTblUsuario extends AbstractSecTblUsuario implements
		java.io.Serializable {

	// Constructors

	/** default constructor */
	public SecTblUsuario() {
	}

	/** full constructor */
	public SecTblUsuario(String idUsuario, SecTblRol secTblRol,
			SecTblEscuela secTblEscuela, String pw) {
		super(idUsuario, secTblRol, secTblEscuela, pw);
	}

}
