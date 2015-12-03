package com.wellcom.hibernate.model;

import java.util.Set;

/**
 * SecTblAlumnoGrupo entity. @author MyEclipse Persistence Tools
 */
public class SecTblAlumnoGrupo extends AbstractSecTblAlumnoGrupo implements
		java.io.Serializable {

	// Constructors

	/** default constructor */
	public SecTblAlumnoGrupo() {
	}

	/** minimal constructor */
	public SecTblAlumnoGrupo(String idAlumnoGrupo, SecTblGrupo secTblGrupo,
			SecTblAlumno secTblAlumno) {
		super(idAlumnoGrupo, secTblGrupo, secTblAlumno);
	}

	/** full constructor */
	public SecTblAlumnoGrupo(String idAlumnoGrupo, SecTblGrupo secTblGrupo,
			SecTblAlumno secTblAlumno, Set secTblCalificacions) {
		super(idAlumnoGrupo, secTblGrupo, secTblAlumno, secTblCalificacions);
	}

}
