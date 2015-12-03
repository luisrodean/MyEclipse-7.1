package com.wellcom.hibernate.model;

import java.util.Set;

/**
 * SecTblCiclo entity. @author MyEclipse Persistence Tools
 */
public class SecTblCiclo extends AbstractSecTblCiclo implements
		java.io.Serializable {

	// Constructors

	/** default constructor */
	public SecTblCiclo() {
	}

	/** minimal constructor */
	public SecTblCiclo(String idCiclo, String ciclo) {
		super(idCiclo, ciclo);
	}

	/** full constructor */
	public SecTblCiclo(String idCiclo, String ciclo, Set secTblCalificacions) {
		super(idCiclo, ciclo, secTblCalificacions);
	}

}
