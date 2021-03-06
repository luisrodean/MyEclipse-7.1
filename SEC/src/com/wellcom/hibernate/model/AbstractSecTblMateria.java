package com.wellcom.hibernate.model;

import java.util.HashSet;
import java.util.Set;

/**
 * AbstractSecTblMateria entity provides the base persistence definition of the
 * SecTblMateria entity. @author MyEclipse Persistence Tools
 */

public abstract class AbstractSecTblMateria implements java.io.Serializable {

	// Fields

	private String idMateria;
	private String nombreMateria;
	private Set secTblProfesorMaterias = new HashSet(0);

	// Constructors

	/** default constructor */
	public AbstractSecTblMateria() {
	}

	/** minimal constructor */
	public AbstractSecTblMateria(String idMateria, String nombreMateria) {
		this.idMateria = idMateria;
		this.nombreMateria = nombreMateria;
	}

	/** full constructor */
	public AbstractSecTblMateria(String idMateria, String nombreMateria,
			Set secTblProfesorMaterias) {
		this.idMateria = idMateria;
		this.nombreMateria = nombreMateria;
		this.secTblProfesorMaterias = secTblProfesorMaterias;
	}

	// Property accessors

	public String getIdMateria() {
		return this.idMateria;
	}

	public void setIdMateria(String idMateria) {
		this.idMateria = idMateria;
	}

	public String getNombreMateria() {
		return this.nombreMateria;
	}

	public void setNombreMateria(String nombreMateria) {
		this.nombreMateria = nombreMateria;
	}

	public Set getSecTblProfesorMaterias() {
		return this.secTblProfesorMaterias;
	}

	public void setSecTblProfesorMaterias(Set secTblProfesorMaterias) {
		this.secTblProfesorMaterias = secTblProfesorMaterias;
	}

}