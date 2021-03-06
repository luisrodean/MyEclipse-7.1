package com.wellcom.hibernate.model;

import java.util.HashSet;
import java.util.Set;

/**
 * AbstractSecTblAlumnoGrupo entity provides the base persistence definition of
 * the SecTblAlumnoGrupo entity. @author MyEclipse Persistence Tools
 */

public abstract class AbstractSecTblAlumnoGrupo implements java.io.Serializable {

	// Fields

	private String idAlumnoGrupo;
	private SecTblGrupo secTblGrupo;
	private SecTblAlumno secTblAlumno;
	private Set secTblCalificacions = new HashSet(0);

	// Constructors

	/** default constructor */
	public AbstractSecTblAlumnoGrupo() {
	}

	/** minimal constructor */
	public AbstractSecTblAlumnoGrupo(String idAlumnoGrupo,
			SecTblGrupo secTblGrupo, SecTblAlumno secTblAlumno) {
		this.idAlumnoGrupo = idAlumnoGrupo;
		this.secTblGrupo = secTblGrupo;
		this.secTblAlumno = secTblAlumno;
	}

	/** full constructor */
	public AbstractSecTblAlumnoGrupo(String idAlumnoGrupo,
			SecTblGrupo secTblGrupo, SecTblAlumno secTblAlumno,
			Set secTblCalificacions) {
		this.idAlumnoGrupo = idAlumnoGrupo;
		this.secTblGrupo = secTblGrupo;
		this.secTblAlumno = secTblAlumno;
		this.secTblCalificacions = secTblCalificacions;
	}

	// Property accessors

	public String getIdAlumnoGrupo() {
		return this.idAlumnoGrupo;
	}

	public void setIdAlumnoGrupo(String idAlumnoGrupo) {
		this.idAlumnoGrupo = idAlumnoGrupo;
	}

	public SecTblGrupo getSecTblGrupo() {
		return this.secTblGrupo;
	}

	public void setSecTblGrupo(SecTblGrupo secTblGrupo) {
		this.secTblGrupo = secTblGrupo;
	}

	public SecTblAlumno getSecTblAlumno() {
		return this.secTblAlumno;
	}

	public void setSecTblAlumno(SecTblAlumno secTblAlumno) {
		this.secTblAlumno = secTblAlumno;
	}

	public Set getSecTblCalificacions() {
		return this.secTblCalificacions;
	}

	public void setSecTblCalificacions(Set secTblCalificacions) {
		this.secTblCalificacions = secTblCalificacions;
	}

}