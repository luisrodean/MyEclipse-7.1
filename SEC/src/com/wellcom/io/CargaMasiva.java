package com.wellcom.io;

import java.io.File;

import com.wellcom.hibernate.dao.SecTblParametrosDAO;


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
	private final SecTblParametrosDAO parametro = new SecTblParametrosDAO();
	
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
	
	public Boolean CargarArchivo(String archivoFileName) {
		System.out.println("\n\n---> entro a Cargar Archivo");
		instanceRoot = System.getProperty("com.sun.aas.instanceRoot");
		// ****************PARAMETROS PARA LA CARGA *************************** //
		SecTblParametrosDAO param = new SecTblParametrosDAO();
		System.out.println("\n\n---> entro a SecTblParametrosDAO");

		u = param.getParametro("USER_DB");
		p = param.getParametro("PASSWORD_DB");
		tns = param.getParametro("DB_NAME");
		ip = param.getParametro("HOST");
		ur = param.getParametro("DIRECTORIO-WEB");
		rutaDB = param.getParametro("DIRECTORIO_MYSQL");
		filtroCatalogos = param.getParametro("filtroCatalogos");
		
		// ******************************************************************* //
		
		//valida que el archivo contenga datos
		if (archivo != null) {
			System.out.println("\n\n---> archivo no nulo ");
			lector.setInstanceRoot(instanceRoot);
			lector.setUr(ur);
			//Lee titulo del archivo y obtiene los ids
			lector.setNombreArchivo(archivoFileName.split("_" , 3));
			for(int i=0;i<lector.getNombreArchivo().length;i++){
				System.out.println("\n\n---> nombre archivo " + i + " : " + lector.getNombreArchivo()[i]);
			}
			
			System.out.println("\n\n---> termina de separa nombre del archivo ");
			if(lector.Xlsx(archivo,filtroCatalogos)){
				System.out.println("\n\n---> archivo correcto ");
				loader = new Loader();
				loader.escribirBat(rutaDB, instanceRoot+ur, filtroCatalogos, ip, u, p, tns);
				registros = lector.getRegistros();
				registrosSi = lector.getRegistrosSi();
				registrosNo = lector.getRegistrosNo();
				return true;
			}else{
				System.out.println("\n\n---> archivo nulo ");
				return false;
			}			
		}
		System.out.println("Termino la Carga Masiva!!");
		return false;
	}

}
