package com.wellcom.hibernate.model;

import java.util.HashSet;
import java.util.Set;

/**
 * AbstractSecTblEscuela entity provides the base persistence definition of the
 * SecTblEscuela entity. @author MyEclipse Persistence Tools
 */

public abstract class AbstractSecTblEscuela implements java.io.Serializable {

	// Fields

	private String idEscuela;
	private String nombre;
	private String direccion;
	private Integer telefono;
	private String correo;
	private Set secTblCalificacions = new HashSet(0);
	private Set secTblProfesorMaterias = new HashSet(0);
	private Set secTblUsuarios = new HashSet(0);

	// Constructors

	/** default constructor */
	public AbstractSecTblEscuela() {
	}

	/** minimal constructor */
	public AbstractSecTblEscuela(String idEscuela, String nombre,
			String direccion, Integer telefono, String correo) {
		this.idEscuela = idEscuela;
		this.nombre = nombre;
		this.direccion = direccion;
		this.telefono = telefono;
		this.correo = correo;
	}

	/** full constructor */
	public AbstractSecTblEscuela(String idEscuela, String nombre,
			String direccion, Integer telefono, String correo,
			Set secTblCalificacions, Set secTblProfesorMaterias,
			Set secTblUsuarios) {
		this.idEscuela = idEscuela;
		this.nombre = nombre;
		this.direccion = direccion;
		this.telefono = telefono;
		this.correo = correo;
		this.secTblCalificacions = secTblCalificacions;
		this.secTblProfesorMaterias = secTblProfesorMaterias;
		this.secTblUsuarios = secTblUsuarios;
	}

	// Property accessors

	public String getIdEscuela() {
		return this.idEscuela;
	}

	public void setIdEscuela(String idEscuela) {
		this.idEscuela = idEscuela;
	}

	public String getNombre() {
		return this.nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getDireccion() {
		return this.direccion;
	}

	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}

	public Integer getTelefono() {
		return this.telefono;
	}

	public void setTelefono(Integer telefono) {
		this.telefono = telefono;
	}

	public String getCorreo() {
		return this.correo;
	}

	public void setCorreo(String correo) {
		this.correo = correo;
	}

	public Set getSecTblCalificacions() {
		return this.secTblCalificacions;
	}

	public void setSecTblCalificacions(Set secTblCalificacions) {
		this.secTblCalificacions = secTblCalificacions;
	}

	public Set getSecTblProfesorMaterias() {
		return this.secTblProfesorMaterias;
	}

	public void setSecTblProfesorMaterias(Set secTblProfesorMaterias) {
		this.secTblProfesorMaterias = secTblProfesorMaterias;
	}

	public Set getSecTblUsuarios() {
		return this.secTblUsuarios;
	}

	public void setSecTblUsuarios(Set secTblUsuarios) {
		this.secTblUsuarios = secTblUsuarios;
	}

}