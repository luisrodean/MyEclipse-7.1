package com.wellcom.actions;


import java.io.File;
import java.util.Map;

import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Actions;
import org.apache.struts2.convention.annotation.Result;

import com.wellcom.io.CargaMasiva;
import com.wellcom.io.LecturaArchivoCarga;


public class CargaArchivoCalificacion extends CoreAction {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	//Filtros
	private String filtroCentrosCam;
	private File archivo;
	private String id_escuela;
	

	//Variables para el resultado de la carga
	private int registros;
	private int registrosNo;
	private int registrosSi;
	private String archivoFileName;
	

		
		public String getFiltroCentrosCam() {
			return filtroCentrosCam;
		}

		public void setFiltroCentrosCam(String filtroCentrosCam) {
			this.filtroCentrosCam = filtroCentrosCam;
		}


		public String getId_escuela() {
			return id_escuela;
		}

		public void setId_escuela(String id_escuela) {
			this.id_escuela = id_escuela;
		}

		public File getArchivo() {
			return archivo;
		}

		public void setArchivo(File archivo) {
			this.archivo = archivo;
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

		public String getArchivoFileName() {
			return archivoFileName;
		}

		public void setArchivoFileName(String archivoFileName) {
			this.archivoFileName = archivoFileName;
		}

		public String execute(){
			session.put("pos", "a4");	
			System.out.println("\n\n---> Entro carga archivo execute Calificacion");
			return "pantallaCargaCalificacion";
		}
	
		public String cargaArchivoCalificacion(){
			System.out.println("\n\n---> Entro a carga de calificacion");
			
			System.out.println("\n\n---> nombre archivo :    " + archivoFileName);
		//	System.out.println("\n\n---> usuario actual :    " + getUsusario());
			
			CargaMasiva carga = new CargaMasiva(archivo, null,null);
			System.out.println("\n\n---> Entra a carga de archivo");

			try{
				if(carga.CargarArchivo(archivoFileName)){
					System.out.println("Carga de los archivos exitosa!!");
					registros = carga.getRegistros();
					registrosSi = carga.getRegistrosSi();
					registrosNo = carga.getRegistrosNo();		
				}else{
					System.out.println("Fallo la carga de los archivos!!");
				}
			}catch(Exception e){
				System.out.println(e.getMessage());
			}
			
			
			return SUCCESS;
		}

		public void setApplication(Map<String, Object> arg0) {
			this.application = arg0;
			
		}




		public void setSession(Map<String, Object> arg0) {
			// TODO Auto-generated method stub
			this.session= arg0;
		}




		public void setServletRequest(HttpServletRequest arg0) {
			// TODO Auto-generated method stub
			this.request = arg0;
		}




		public void setServletResponse(HttpServletResponse arg0) {
			// TODO Auto-generated method stub
			this.response = arg0;
		}
	}
