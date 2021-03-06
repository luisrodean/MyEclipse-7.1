package com.wellcom.hibernate.model;

import java.util.HashSet;
import java.util.Set;

/**
 * AbstractSecTblGrupo entity provides the base persistence definition of the
 * SecTblGrupo entity. @author MyEclipse Persistence Tools
 */

public abstract class AbstractSecTblGrupo implements java.io.Serializable {

	// Fields

	private Integer idGrupo;
	private String grupo;
	private Set secTblAlumnoGrupos = new HashSet(0);

	// Constructors

	/** default constructor */
	public AbstractSecTblGrupo() {
	}

	/** minimal constructor */
	public AbstractSecTblGrupo(Integer idGrupo, String grupo) {
		this.idGrupo = idGrupo;
		this.grupo = grupo;
	}

	/** full constructor */
	public AbstractSecTblGrupo(Integer idGrupo, String grupo,
			Set secTblAlumnoGrupos) {
		this.idGrupo = idGrupo;
		this.grupo = grupo;
		this.secTblAlumnoGrupos = secTblAlumnoGrupos;
	}

	// Property accessors

	public Integer getIdGrupo() {
		return this.idGrupo;
	}

	public void setIdGrupo(Integer idGrupo) {
		this.idGrupo = idGrupo;
	}

	public String getGrupo() {
		return this.grupo;
	}

	public void setGrupo(String grupo) {
		this.grupo = grupo;
	}

	public Set getSecTblAlumnoGrupos() {
		return this.secTblAlumnoGrupos;
	}

	public void setSecTblAlumnoGrupos(Set secTblAlumnoGrupos) {
		this.secTblAlumnoGrupos = secTblAlumnoGrupos;
	}

}