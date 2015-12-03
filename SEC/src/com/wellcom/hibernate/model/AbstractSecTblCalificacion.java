package com.wellcom.hibernate.model;

/**
 * AbstractSecTblCalificacion entity provides the base persistence definition of
 * the SecTblCalificacion entity. @author MyEclipse Persistence Tools
 */

public abstract class AbstractSecTblCalificacion implements
		java.io.Serializable {

	// Fields

	private String idCalificacion;
	private SecTblCiclo secTblCiclo;
	private SecTblAlumnoGrupo secTblAlumnoGrupo;
	private SecTblEscuela secTblEscuela;
	private SecTblPeriodo secTblPeriodo;
	private SecTblProfesorMateria secTblProfesorMateria;
	private Integer calificacion;

	// Constructors

	/** default constructor */
	public AbstractSecTblCalificacion() {
	}

	/** full constructor */
	public AbstractSecTblCalificacion(String idCalificacion,
			SecTblCiclo secTblCiclo, SecTblAlumnoGrupo secTblAlumnoGrupo,
			SecTblEscuela secTblEscuela, SecTblPeriodo secTblPeriodo,
			SecTblProfesorMateria secTblProfesorMateria, Integer calificacion) {
		this.idCalificacion = idCalificacion;
		this.secTblCiclo = secTblCiclo;
		this.secTblAlumnoGrupo = secTblAlumnoGrupo;
		this.secTblEscuela = secTblEscuela;
		this.secTblPeriodo = secTblPeriodo;
		this.secTblProfesorMateria = secTblProfesorMateria;
		this.calificacion = calificacion;
	}

	// Property accessors

	public String getIdCalificacion() {
		return this.idCalificacion;
	}

	public void setIdCalificacion(String idCalificacion) {
		this.idCalificacion = idCalificacion;
	}

	public SecTblCiclo getSecTblCiclo() {
		return this.secTblCiclo;
	}

	public void setSecTblCiclo(SecTblCiclo secTblCiclo) {
		this.secTblCiclo = secTblCiclo;
	}

	public SecTblAlumnoGrupo getSecTblAlumnoGrupo() {
		return this.secTblAlumnoGrupo;
	}

	public void setSecTblAlumnoGrupo(SecTblAlumnoGrupo secTblAlumnoGrupo) {
		this.secTblAlumnoGrupo = secTblAlumnoGrupo;
	}

	public SecTblEscuela getSecTblEscuela() {
		return this.secTblEscuela;
	}

	public void setSecTblEscuela(SecTblEscuela secTblEscuela) {
		this.secTblEscuela = secTblEscuela;
	}

	public SecTblPeriodo getSecTblPeriodo() {
		return this.secTblPeriodo;
	}

	public void setSecTblPeriodo(SecTblPeriodo secTblPeriodo) {
		this.secTblPeriodo = secTblPeriodo;
	}

	public SecTblProfesorMateria getSecTblProfesorMateria() {
		return this.secTblProfesorMateria;
	}

	public void setSecTblProfesorMateria(
			SecTblProfesorMateria secTblProfesorMateria) {
		this.secTblProfesorMateria = secTblProfesorMateria;
	}

	public Integer getCalificacion() {
		return this.calificacion;
	}

	public void setCalificacion(Integer calificacion) {
		this.calificacion = calificacion;
	}

}