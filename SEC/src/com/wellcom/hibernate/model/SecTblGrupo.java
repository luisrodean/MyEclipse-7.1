package com.wellcom.hibernate.model;

import java.util.Set;

/**
 * SecTblGrupo entity. @author MyEclipse Persistence Tools
 */
public class SecTblGrupo extends AbstractSecTblGrupo implements
		java.io.Serializable {

	// Constructors

	/** default constructor */
	public SecTblGrupo() {
	}

	/** minimal constructor */
	public SecTblGrupo(Integer idGrupo, String grupo) {
		super(idGrupo, grupo);
	}

	/** full constructor */
	public SecTblGrupo(Integer idGrupo, String grupo, Set secTblAlumnoGrupos) {
		super(idGrupo, grupo, secTblAlumnoGrupos);
	}

}
