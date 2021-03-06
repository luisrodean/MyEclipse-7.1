package com.wellcom.hibernate.model;

import java.util.HashSet;
import java.util.Set;

/**
 * AbstractSecTblCiclo entity provides the base persistence definition of the
 * SecTblCiclo entity. @author MyEclipse Persistence Tools
 */

public abstract class AbstractSecTblCiclo implements java.io.Serializable {

	// Fields

	private String idCiclo;
	private String ciclo;
	private Set secTblCalificacions = new HashSet(0);

	// Constructors

	/** default constructor */
	public AbstractSecTblCiclo() {
	}

	/** minimal constructor */
	public AbstractSecTblCiclo(String idCiclo, String ciclo) {
		this.idCiclo = idCiclo;
		this.ciclo = ciclo;
	}

	/** full constructor */
	public AbstractSecTblCiclo(String idCiclo, String ciclo,
			Set secTblCalificacions) {
		this.idCiclo = idCiclo;
		this.ciclo = ciclo;
		this.secTblCalificacions = secTblCalificacions;
	}

	// Property accessors

	public String getIdCiclo() {
		return this.idCiclo;
	}

	public void setIdCiclo(String idCiclo) {
		this.idCiclo = idCiclo;
	}

	public String getCiclo() {
		return this.ciclo;
	}

	public void setCiclo(String ciclo) {
		this.ciclo = ciclo;
	}

	public Set getSecTblCalificacions() {
		return this.secTblCalificacions;
	}

	public void setSecTblCalificacions(Set secTblCalificacions) {
		this.secTblCalificacions = secTblCalificacions;
	}

}