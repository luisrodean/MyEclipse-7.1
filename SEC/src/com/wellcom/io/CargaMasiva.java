package com.wellcom.io;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import com.wellcom.hibernate.dao.TblParametrosDAO;



public class CargaMasiva {
	private String p;
	private String u;
	private String tns;
	private String ip;
	private String ur;
	private String url;
	private String instanceRoot;
	private String carga;
	private String filtroCatalogos = "";
	private String rutaDB;
	private File archivo;
	private int registros;
	private int registrosNo;
	private int registrosSi;
	private String sector;
	private Loader loader;
	private String operResult;
	
	private final LecturaArchivoCarga lector = new LecturaArchivoCarga();
	//private final HerramientasDAO herr = new HerramientasDAO();
	private final TblParametrosDAO parametro = new TblParametrosDAO();
	
	public CargaMasiva(String p, String u, String tns, String ip, String ur,
			String url, String instanceRoot, String carga,
			String filtroCatalogos, int registros,int registrosNo, int registrosSi) {
		super();
		this.p = p;
		this.u = u;
		this.tns = tns;
		this.ip = ip;
		this.ur = ur;
		this.url = url;
		this.instanceRoot = instanceRoot;
		this.carga = carga;
		this.filtroCatalogos = filtroCatalogos;
		this.registros = registros;
		this.registrosSi = registrosSi;
		this.registrosNo = registrosNo;
	}
	
	public CargaMasiva(File archivo, String filtroCatalogos,String sector) {
		super();
		this.archivo = archivo;
		this.filtroCatalogos = filtroCatalogos;
		this.sector = sector;
	}
	
	
	
	public String getRutaDB() {
		return rutaDB;
	}

	public void setRutaDB(String rutaDB) {
		this.rutaDB = rutaDB;
	}

	public String getOperResult() {
		return operResult;
	}

	public void setOperResult(String operResult) {
		this.operResult = operResult;
	}

	public String getSector() {
		return sector;
	}

	public void setSector(String sector) {
		this.sector = sector;
	}

	public String getP() {
		return p;
	}

	public void setP(String p) {
		this.p = p;
	}

	public String getU() {
		return u;
	}

	public void setU(String u) {
		this.u = u;
	}

	public String getTns() {
		return tns;
	}

	public void setTns(String tns) {
		this.tns = tns;
	}

	public String getIp() {
		return ip;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}

	public String getUr() {
		return ur;
	}

	public void setUr(String ur) {
		this.ur = ur;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getInstanceRoot() {
		return instanceRoot;
	}

	public void setInstanceRoot(String instanceRoot) {
		this.instanceRoot = instanceRoot;
	}

	public String getCarga() {
		return carga;
	}

	public void setCarga(String carga) {
		this.carga = carga;
	}

	public String getFiltroCatalogos() {
		return filtroCatalogos;
	}

	public void setFiltroCatalogos(String filtroCatalogos) {
		this.filtroCatalogos = filtroCatalogos;
	}
	public int getRegistros() {
		return registros;
	}

	public void setRegistros(int registros) {
		this.registros = registros;
	}

	public int getRegistrosNo() {
		return registrosNo;
	}

	public void setRegistrosNo(int registrosNo) {
		this.registrosNo = registrosNo;
	}

	public int getRegistrosSi() {
		return registrosSi;
	}

	public void setRegistrosSi(int registrosSi) {
		this.registrosSi = registrosSi;
	}
	
	public Boolean CargarArchivo() {
		instanceRoot = System.getProperty("com.sun.aas.instanceRoot");
		filtroCatalogos = "sec_tbl_calificacion.csv";
		// ****************PARAMETROS PARA LA CARGA *************************** //
		TblParametrosDAO param = new TblParametrosDAO();
		
		u = param.getParametro("USER_DB");
		p = param.getParametro("PASSWORD_DB");
		tns = param.getParametro("DB_NAME");
		ip = param.getParametro("HOST");
		ur = param.getParametro("DIRECTORIO-WEB");
		rutaDB = param.getParametro("DIRECTORIO_MYSQL");
		// ******************************************************************* //
		
		if (archivo != null) {
			lector.setInstanceRoot(instanceRoot);
			lector.setUr(ur);
							
			if(lector.Xlsx(archivo,filtroCatalogos)){
				loader = new Loader();
				loader.escribirBat(rutaDB, instanceRoot+ur, filtroCatalogos, ip, u, p, tns);
				registros = lector.getRegistros();
				registrosSi = lector.getRegistrosSi();
				registrosNo = lector.getRegistrosNo();
				return true;
			}else{
				return false;
			}			
		}
		System.out.println("Termino la Carga Masiva!!");
		return false;
	}

}