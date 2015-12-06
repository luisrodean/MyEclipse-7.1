package com.wellcom.hibernate.model;

/**
 * SecTblParametros entity. @author MyEclipse Persistence Tools
 */

public class SecTblParametros implements java.io.Serializable {

	// Fields

	private Integer id;
	private String nombre;
	private String parametro;
	private String paramBlob;

	// Constructors

	/** default constructor */
	public SecTblParametros() {
	}

	/** minimal constructor */
	public SecTblParametros(Integer id) {
		this.id = id;
	}

	/** full constructor */
	public SecTblParametros(Integer id, String nombre, String parametro,
			String paramBlob) {
		this.id = id;
		this.nombre = nombre;
		this.parametro = parametro;
		this.paramBlob = paramBlob;
	}

	// Property accessors

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getNombre() {
		return this.nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getParametro() {
		return this.parametro;
	}

	public void setParametro(String parametro) {
		this.parametro = parametro;
	}

	public String getParamBlob() {
		return this.paramBlob;
	}

	public void setParamBlob(String paramBlob) {
		this.paramBlob = paramBlob;
	}

}