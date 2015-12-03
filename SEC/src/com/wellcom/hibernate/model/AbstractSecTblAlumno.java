package com.wellcom.hibernate.model;

import java.util.HashSet;
import java.util.Set;

/**
 * AbstractSecTblAlumno entity provides the base persistence definition of the
 * SecTblAlumno entity. @author MyEclipse Persistence Tools
 */

public abstract class AbstractSecTblAlumno implements java.io.Serializable {

	// Fields

	private Integer idAlummno;
	private SecTblPadre secTblPadre;
	private String nombre;
	private String apellidoPaterno;
	private String apellidoMaterno;
	private Set secTblAlumnoGrupos = new HashSet(0);

	// Constructors

	/** default constructor */
	public AbstractSecTblAlumno() {
	}

	/** minimal constructor */
	public AbstractSecTblAlumno(Integer idAlummno, SecTblPadre secTblPadre,
			String nombre, String apellidoPaterno, String apellidoMaterno) {
		this.idAlummno = idAlummno;
		this.secTblPadre = secTblPadre;
		this.nombre = nombre;
		this.apellidoPaterno = apellidoPaterno;
		this.apellidoMaterno = apellidoMaterno;
	}

	/** full constructor */
	public AbstractSecTblAlumno(Integer idAlummno, SecTblPadre secTblPadre,
			String nombre, String apellidoPaterno, String apellidoMaterno,
			Set secTblAlumnoGrupos) {
		this.idAlummno = idAlummno;
		this.secTblPadre = secTblPadre;
		this.nombre = nombre;
		this.apellidoPaterno = apellidoPaterno;
		this.apellidoMaterno = apellidoMaterno;
		this.secTblAlumnoGrupos = secTblAlumnoGrupos;
	}

	// Property accessors

	public Integer getIdAlummno() {
		return this.idAlummno;
	}

	public void setIdAlummno(Integer idAlummno) {
		this.idAlummno = idAlummno;
	}

	public SecTblPadre getSecTblPadre() {
		return this.secTblPadre;
	}

	public void setSecTblPadre(SecTblPadre secTblPadre) {
		this.secTblPadre = secTblPadre;
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

	public Set getSecTblAlumnoGrupos() {
		return this.secTblAlumnoGrupos;
	}

	public void setSecTblAlumnoGrupos(Set secTblAlumnoGrupos) {
		this.secTblAlumnoGrupos = secTblAlumnoGrupos;
	}

}