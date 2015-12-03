package com.wellcom.hibernate.model;

import java.util.HashSet;
import java.util.Set;

/**
 * AbstractSecTblProfesorMateria entity provides the base persistence definition
 * of the SecTblProfesorMateria entity. @author MyEclipse Persistence Tools
 */

public abstract class AbstractSecTblProfesorMateria implements
		java.io.Serializable {

	// Fields

	private String idProfesorMateria;
	private SecTblProfesor secTblProfesor;
	private SecTblMateria secTblMateria;
	private SecTblEscuela secTblEscuela;
	private Set secTblCalificacions = new HashSet(0);

	// Constructors

	/** default constructor */
	public AbstractSecTblProfesorMateria() {
	}

	/** minimal constructor */
	public AbstractSecTblProfesorMateria(String idProfesorMateria,
			SecTblProfesor secTblProfesor, SecTblMateria secTblMateria,
			SecTblEscuela secTblEscuela) {
		this.idProfesorMateria = idProfesorMateria;
		this.secTblProfesor = secTblProfesor;
		this.secTblMateria = secTblMateria;
		this.secTblEscuela = secTblEscuela;
	}

	/** full constructor */
	public AbstractSecTblProfesorMateria(String idProfesorMateria,
			SecTblProfesor secTblProfesor, SecTblMateria secTblMateria,
			SecTblEscuela secTblEscuela, Set secTblCalificacions) {
		this.idProfesorMateria = idProfesorMateria;
		this.secTblProfesor = secTblProfesor;
		this.secTblMateria = secTblMateria;
		this.secTblEscuela = secTblEscuela;
		this.secTblCalificacions = secTblCalificacions;
	}

	// Property accessors

	public String getIdProfesorMateria() {
		return this.idProfesorMateria;
	}

	public void setIdProfesorMateria(String idProfesorMateria) {
		this.idProfesorMateria = idProfesorMateria;
	}

	public SecTblProfesor getSecTblProfesor() {
		return this.secTblProfesor;
	}

	public void setSecTblProfesor(SecTblProfesor secTblProfesor) {
		this.secTblProfesor = secTblProfesor;
	}

	public SecTblMateria getSecTblMateria() {
		return this.secTblMateria;
	}

	public void setSecTblMateria(SecTblMateria secTblMateria) {
		this.secTblMateria = secTblMateria;
	}

	public SecTblEscuela getSecTblEscuela() {
		return this.secTblEscuela;
	}

	public void setSecTblEscuela(SecTblEscuela secTblEscuela) {
		this.secTblEscuela = secTblEscuela;
	}

	public Set getSecTblCalificacions() {
		return this.secTblCalificacions;
	}

	public void setSecTblCalificacions(Set secTblCalificacions) {
		this.secTblCalificacions = secTblCalificacions;
	}

}