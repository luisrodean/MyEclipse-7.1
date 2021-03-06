package com.wellcom.hibernate.model;

import java.util.Set;

/**
 * SecTblPeriodo entity. @author MyEclipse Persistence Tools
 */
public class SecTblPeriodo extends AbstractSecTblPeriodo implements
		java.io.Serializable {

	// Constructors

	/** default constructor */
	public SecTblPeriodo() {
	}

	/** minimal constructor */
	public SecTblPeriodo(Integer idPeriodo, Integer periodo) {
		super(idPeriodo, periodo);
	}

	/** full constructor */
	public SecTblPeriodo(Integer idPeriodo, Integer periodo,
			Set secTblCalificacions) {
		super(idPeriodo, periodo, secTblCalificacions);
	}

}
