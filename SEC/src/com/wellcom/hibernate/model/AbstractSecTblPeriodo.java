package com.wellcom.hibernate.model;

import java.util.HashSet;
import java.util.Set;

/**
 * AbstractSecTblPeriodo entity provides the base persistence definition of the
 * SecTblPeriodo entity. @author MyEclipse Persistence Tools
 */

public abstract class AbstractSecTblPeriodo implements java.io.Serializable {

	// Fields

	private Integer idPeriodo;
	private Integer periodo;
	private Set secTblCalificacions = new HashSet(0);

	// Constructors

	/** default constructor */
	public AbstractSecTblPeriodo() {
	}

	/** minimal constructor */
	public AbstractSecTblPeriodo(Integer idPeriodo, Integer periodo) {
		this.idPeriodo = idPeriodo;
		this.periodo = periodo;
	}

	/** full constructor */
	public AbstractSecTblPeriodo(Integer idPeriodo, Integer periodo,
			Set secTblCalificacions) {
		this.idPeriodo = idPeriodo;
		this.periodo = periodo;
		this.secTblCalificacions = secTblCalificacions;
	}

	// Property accessors

	public Integer getIdPeriodo() {
		return this.idPeriodo;
	}

	public void setIdPeriodo(Integer idPeriodo) {
		this.idPeriodo = idPeriodo;
	}

	public Integer getPeriodo() {
		return this.periodo;
	}

	public void setPeriodo(Integer periodo) {
		this.periodo = periodo;
	}

	public Set getSecTblCalificacions() {
		return this.secTblCalificacions;
	}

	public void setSecTblCalificacions(Set secTblCalificacions) {
		this.secTblCalificacions = secTblCalificacions;
	}

}