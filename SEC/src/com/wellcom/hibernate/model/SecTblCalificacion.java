package com.wellcom.hibernate.model;

/**
 * SecTblCalificacion entity. @author MyEclipse Persistence Tools
 */
public class SecTblCalificacion extends AbstractSecTblCalificacion implements
		java.io.Serializable {

	// Constructors

	/** default constructor */
	public SecTblCalificacion() {
	}

	/** full constructor */
	public SecTblCalificacion(String idCalificacion, SecTblCiclo secTblCiclo,
			SecTblAlumnoGrupo secTblAlumnoGrupo, SecTblEscuela secTblEscuela,
			SecTblPeriodo secTblPeriodo,
			SecTblProfesorMateria secTblProfesorMateria, Integer calificacion) {
		super(idCalificacion, secTblCiclo, secTblAlumnoGrupo, secTblEscuela,
				secTblPeriodo, secTblProfesorMateria, calificacion);
	}

}
