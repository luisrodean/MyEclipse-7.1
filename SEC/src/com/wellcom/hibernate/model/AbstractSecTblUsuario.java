package com.wellcom.hibernate.model;

/**
 * AbstractSecTblUsuario entity provides the base persistence definition of the
 * SecTblUsuario entity. @author MyEclipse Persistence Tools
 */

public abstract class AbstractSecTblUsuario implements java.io.Serializable {

	// Fields

	private String idUsuario;
	private SecTblRol secTblRol;
	private SecTblEscuela secTblEscuela;
	private String pw;

	// Constructors

	/** default constructor */
	public AbstractSecTblUsuario() {
	}

	/** full constructor */
	public AbstractSecTblUsuario(String idUsuario, SecTblRol secTblRol,
			SecTblEscuela secTblEscuela, String pw) {
		this.idUsuario = idUsuario;
		this.secTblRol = secTblRol;
		this.secTblEscuela = secTblEscuela;
		this.pw = pw;
	}

	// Property accessors

	public String getIdUsuario() {
		return this.idUsuario;
	}

	public void setIdUsuario(String idUsuario) {
		this.idUsuario = idUsuario;
	}

	public SecTblRol getSecTblRol() {
		return this.secTblRol;
	}

	public void setSecTblRol(SecTblRol secTblRol) {
		this.secTblRol = secTblRol;
	}

	public SecTblEscuela getSecTblEscuela() {
		return this.secTblEscuela;
	}

	public void setSecTblEscuela(SecTblEscuela secTblEscuela) {
		this.secTblEscuela = secTblEscuela;
	}

	public String getPw() {
		return this.pw;
	}

	public void setPw(String pw) {
		this.pw = pw;
	}

}