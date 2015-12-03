package com.wellcom.hibernate.model;

import java.util.HashSet;
import java.util.Set;

/**
 * AbstractSecTblPadre entity provides the base persistence definition of the
 * SecTblPadre entity. @author MyEclipse Persistence Tools
 */

public abstract class AbstractSecTblPadre implements java.io.Serializable {

	// Fields

	private String idPadre;
	private String nombre;
	private String apellidoPaterno;
	private String apellidoMaterno;
	private String direccion;
	private String pw;
	private Set secTblAlumnos = new HashSet(0);

	// Constructors

	/** default constructor */
	public AbstractSecTblPadre() {
	}

	/** minimal constructor */
	public AbstractSecTblPadre(String idPadre, String nombre,
			String apellidoPaterno, String apellidoMaterno, String direccion,
			String pw) {
		this.idPadre = idPadre;
		this.nombre = nombre;
		this.apellidoPaterno = apellidoPaterno;
		this.apellidoMaterno = apellidoMaterno;
		this.direccion = direccion;
		this.pw = pw;
	}

	/** full constructor */
	public AbstractSecTblPadre(String idPadre, String nombre,
			String apellidoPaterno, String apellidoMaterno, String direccion,
			String pw, Set secTblAlumnos) {
		this.idPadre = idPadre;
		this.nombre = nombre;
		this.apellidoPaterno = apellidoPaterno;
		this.apellidoMaterno = apellidoMaterno;
		this.direccion = direccion;
		this.pw = pw;
		this.secTblAlumnos = secTblAlumnos;
	}

	// Property accessors

	public String getIdPadre() {
		return this.idPadre;
	}

	public void setIdPadre(String idPadre) {
		this.idPadre = idPadre;
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

	public String getDireccion() {
		return this.direccion;
	}

	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}

	public String getPw() {
		return this.pw;
	}

	public void setPw(String pw) {
		this.pw = pw;
	}

	public Set getSecTblAlumnos() {
		return this.secTblAlumnos;
	}

	public void setSecTblAlumnos(Set secTblAlumnos) {
		this.secTblAlumnos = secTblAlumnos;
	}

}