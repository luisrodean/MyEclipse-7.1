package com.wellcom.hibernate.model;

public class GridCalificacion {

	private String id_calificacion;
	private String ciclo;
	private String grupo;
	private String nombre;
	private String apellido_paterno;
	private String apellido_materno;
	private String nombre_materia;
	private String periodo;
	private String calificacion;
	
	
	public GridCalificacion() {
	}

	public GridCalificacion(String id_calificacion, String ciclo, String grupo,
			String nombre, String apellido_paterno, String apellido_materno,
			String nombre_materia, String periodo, String calificacion) {
		this.id_calificacion = id_calificacion;
		this.ciclo = ciclo;
		this.grupo = grupo;
		this.nombre = nombre;
		this.apellido_paterno = apellido_paterno;
		this.apellido_materno = apellido_materno;
		this.nombre_materia = nombre_materia;
		this.periodo = periodo;
		this.calificacion = calificacion;
	}

	public String getId_calificacion() {
		return id_calificacion;
	}

	public void setId_calificacion(String id_calificacion) {
		this.id_calificacion = id_calificacion;
	}

	public String getCiclo() {
		return ciclo;
	}

	public void setCiclo(String ciclo) {
		this.ciclo = ciclo;
	}

	public String getGrupo() {
		return grupo;
	}

	public void setGrupo(String grupo) {
		this.grupo = grupo;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getApellido_paterno() {
		return apellido_paterno;
	}

	public void setApellido_paterno(String apellido_paterno) {
		this.apellido_paterno = apellido_paterno;
	}

	public String getApellido_materno() {
		return apellido_materno;
	}

	public void setApellido_materno(String apellido_materno) {
		this.apellido_materno = apellido_materno;
	}

	public String getNombre_materia() {
		return nombre_materia;
	}

	public void setNombre_materia(String nombre_materia) {
		this.nombre_materia = nombre_materia;
	}

	public String getPeriodo() {
		return periodo;
	}

	public void setPeriodo(String periodo) {
		this.periodo = periodo;
	}

	public String getCalificacion() {
		return calificacion;
	}

	public void setCalificacion(String calificacion) {
		this.calificacion = calificacion;
	}
	
	
	
}
