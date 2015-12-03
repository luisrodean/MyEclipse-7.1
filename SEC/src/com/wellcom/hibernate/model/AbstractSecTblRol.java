package com.wellcom.hibernate.model;

import java.util.HashSet;
import java.util.Set;

/**
 * AbstractSecTblRol entity provides the base persistence definition of the
 * SecTblRol entity. @author MyEclipse Persistence Tools
 */

public abstract class AbstractSecTblRol implements java.io.Serializable {

	// Fields

	private Integer idRol;
	private String tipoRol;
	private Set secTblUsuarios = new HashSet(0);

	// Constructors

	/** default constructor */
	public AbstractSecTblRol() {
	}

	/** minimal constructor */
	public AbstractSecTblRol(Integer idRol, String tipoRol) {
		this.idRol = idRol;
		this.tipoRol = tipoRol;
	}

	/** full constructor */
	public AbstractSecTblRol(Integer idRol, String tipoRol, Set secTblUsuarios) {
		this.idRol = idRol;
		this.tipoRol = tipoRol;
		this.secTblUsuarios = secTblUsuarios;
	}

	// Property accessors

	public Integer getIdRol() {
		return this.idRol;
	}

	public void setIdRol(Integer idRol) {
		this.idRol = idRol;
	}

	public String getTipoRol() {
		return this.tipoRol;
	}

	public void setTipoRol(String tipoRol) {
		this.tipoRol = tipoRol;
	}

	public Set getSecTblUsuarios() {
		return this.secTblUsuarios;
	}

	public void setSecTblUsuarios(Set secTblUsuarios) {
		this.secTblUsuarios = secTblUsuarios;
	}

}