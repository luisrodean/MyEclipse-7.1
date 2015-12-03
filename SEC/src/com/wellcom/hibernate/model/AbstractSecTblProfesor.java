package com.wellcom.hibernate.model;

import java.util.HashSet;
import java.util.Set;

/**
 * AbstractSecTblProfesor entity provides the base persistence definition of the
 * SecTblProfesor entity. @author MyEclipse Persistence Tools
 */

public abstract class AbstractSecTblProfesor implements java.io.Serializable {

	// Fields

	private String idProfesor;
	private String nombre;
	private String apellidoPaterno;
	private String apellidoMaterno;
	private Set secTblProfesorMaterias = new HashSet(0);

	// Constructors

	/** default constructor */
	public AbstractSecTblProfesor() {
	}

	/** minimal constructor */
	public AbstractSecTblProfesor(String idProfesor, String nombre,
			String apellidoPaterno, String apellidoMaterno) {
		this.idProfesor = idProfesor;
		this.nombre = nombre;
		this.apellidoPaterno = apellidoPaterno;
		this.apellidoMaterno = apellidoMaterno;
	}

	/** full constructor */
	public AbstractSecTblProfesor(String idProfesor, String nombre,
			String apellidoPaterno, String apellidoMaterno,
			Set secTblProfesorMaterias) {
		this.idProfesor = idProfesor;
		this.nombre = nombre;
		this.apellidoPaterno = apellidoPaterno;
		this.apellidoMaterno = apellidoMaterno;
		this.secTblProfesorMaterias = secTblProfesorMaterias;
	}

	// Property accessors

	public String getIdProfesor() {
		return this.idProfesor;
	}

	public void setIdProfesor(String idProfesor) {
		this.idProfesor = idProfesor;
	}

	public String getNombre() {
		return this.nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getApellidoPaterno() {
		return this.apellidoPaterno;
	}

	public void setApellidoPaterno(String apellidoPaterno) {
		this.apellidoPaterno = apellidoPaterno;
	}

	public String getApellidoMaterno() {
		return this.apellidoMaterno;
	}

	public void setApellidoMaterno(String apellidoMaterno) {
		this.apellidoMaterno = apellidoMaterno;
	}

	public Set getSecTblProfesorMaterias() {
		return this.secTblProfesorMaterias;
	}

	public void setSecTblProfesorMaterias(Set secTblProfesorMaterias) {
		this.secTblProfesorMaterias = secTblProfesorMaterias;
	}

}