package com.wellcom.io;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.Map;

import org.apache.poi.hssf.usermodel.HSSFDateUtil;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.FormulaEvaluator;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.lowagie.text.List;
import com.wellcom.hibernate.dao.SecTblAlumnoGrupoDAO;
import com.wellcom.hibernate.dao.SecTblGrupoDAO;
import com.wellcom.hibernate.dao.SecTblParametrosDAO;
import com.wellcom.hibernate.model.SecTblAlumno;
import com.wellcom.hibernate.model.SecTblAlumnoGrupo;
import com.wellcom.hibernate.model.SecTblEscuela;
import com.wellcom.hibernate.model.SecTblParametros;
import com.wellcom.hibernate.model.SecTblPeriodo;

public class LecturaArchivoCarga {
	SimpleDateFormat formato = new SimpleDateFormat("EEE MMM dd HH:mm:ss Z yyyy");
	private int registros;
	private int registrosNo;
	private int registrosSi;
	private String instanceRoot;
	private String ur;
	private ArrayList<String> casfimList;
	private String[] NombreArchivo;
	

	StringBuffer dato = new StringBuffer();
	
	//******************************DAO*****************//
	//private TblAccionistasDAO AccDao; 
	
	public LecturaArchivoCarga(String instanceRoot, String ur){
		this.instanceRoot = instanceRoot;
		this.ur = ur;
	}
	
	public LecturaArchivoCarga() {
		
	}
	
	public String[] getNombreArchivo() {
		return NombreArchivo;
	}

	public void setNombreArchivo(String[] nombreArchivo) {
		NombreArchivo = nombreArchivo;
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


	public String getInstanceRoot() {
		return instanceRoot;
	}

	public void setInstanceRoot(String instanceRoot) {
		this.instanceRoot = instanceRoot;
	}

	public String getUr() {
		return ur;
	}

	public void setUr(String ur) {
		this.ur = ur;
	}
	

	public Boolean Xlsx(File inputFile,String nombre) {
		/*System.out.println("-->Procesando archivo de carga");
		
		Integer numCell=0,numrow=0,hoja=0;
        // For storing data into CSV files
        StringBuffer data = new StringBuffer();
        File outputFile = new File(instanceRoot+ur+nombre);

        try {
        	//escribe datos en el cvs
            FileOutputStream fos = new FileOutputStream(outputFile);
            //contador para libros de archivo
            XSSFWorkbook wBook = new XSSFWorkbook(new FileInputStream(inputFile));
            //contador para hoojas de archivo
            XSSFSheet sheet = wBook.getSheetAt(0);

            
            System.out.println("---> Numero de hojas "+wBook.getNumberOfSheets());
            
            Row row;
            Cell cell;
            
            //repite el ciclo hasta que llega al total de hojas
            while(hoja<wBook.getNumberOfSheets()){
            	//inicializa numero de hoja con el contador hoja
            	sheet = wBook.getSheetAt(hoja);
            	hoja++;
            	//crea iterador para recorrer filas
	            Iterator<Row> rowIterator = sheet.iterator();                        
	
	            //repite el ciclo hasta que no encuentre filas 
	            while (rowIterator.hasNext()) {
	            	//asigna el numero de la fila
	            	row = rowIterator.next();
	            	numrow++;
	            	//Creacion de Lista
	            	Map<String,String> lista = null;
	            	
	            	//IF PARA NO LEER LOS TITULOS DE LAS COLUMNAS
	            	if(row.getRowNum()!=0){
	            	System.out.println("---> Numero de registros " + row.getRowNum());
	            	numCell=0;
	            	
		            // POR CADA FILA SE ITERAN LAS CELDAS
		            Iterator<Cell> cellIterator = row.cellIterator();
		            
		            	while (cellIterator.hasNext()) {
		            	
		                    cell = cellIterator.next();
		                    numCell++;
		                 
		                    //IF PARA SELECCIONAR LAS CELDAS A CARGAR o leer
		                    if (numCell==1 || numCell==2 || numCell==6 || numCell==7 ){
		                    	switch(numCell){
		                    	case 1:		//Celda id ciclo
		                    		//data.append((int) cell.getNumericCellValue() + "|");
		                    		byte[] b = cell.getStringCellValue().toUpperCase().replaceAll("\n", " ").trim().getBytes("UTF-8");
		                    		String decoded = new String(b, "UTF-8");
		                    		lista.put("Id_ciclo", decoded + "|");
		                    		break;
		                    	case 2:		//Celda id alumno
		                    		
		                    		//Busca IdAlumno-Grupo
		                    		Integer id_grupo = new SecTblGrupoDAO().getIdGrupo(getNombreArchivo()[2]);
		                       		Integer idAlumno = (int) cell.getNumericCellValue();
		                       		//data.append(idAlumno+"-"+ id_grupo +"|");
		                       		lista.put("Id_alumno_grupo", idAlumno+"-"+ id_grupo +"|");
		                       		
		                    		break ;
		                    	case 6:		//Celda id periodo
		                    		//data.append((int) cell.getNumericCellValue() + "|");
		                    		lista.put("Id_periodo", (int) cell.getNumericCellValue() + "|");
		                    		break;
		                    	case 7:		//Celda calificacion
		                    		//data.append((int) cell.getNumericCellValue() + "|");
		                    		lista.put("Id_calificacion", (int) cell.getNumericCellValue() + "|");
		                    		break;
		                    	default:
		                    		
		                    		switch(cell.getCellType()){
		                    		case Cell.CELL_TYPE_BLANK:

				                    	System.out.println("Fila"+numrow+"Celda vacia"+numCell);
				                            data.append(" " + "|");
				                            break;
		                    		default:
				                    	System.out.println("DEFAULT----->Fila"+numrow+"Celda vacia"+numCell);
				                            data.append(" " + "|");
				                            
		                    		}
		                    		
		                    	}
		                    	
			                    			
				                    	System.out.println("Fila"+numrow+"Celda vacia"+numCell);
				                            data.append(" " + "|");
			
				                    
				                    	System.out.println("DEFAULT----->Fila"+numrow+"Celda vacia"+numCell);
				                            data.append(" " + "|");
			                    }
		                 	}
		            	}
	            		System.out.println("DEFAULT----->Fila   "+lista.get("Id_periodo") + lista.get("Id_ciclo") + lista.get("Id_alumno_grupo") + '\n');

		            	data.append(lista.get("Id_periodo") + lista.get("Id_ciclo") + lista.get("Id_alumno_grupo") + '\n'); 
		            }
	            }

        	System.out.println("Numero de filas procesadas:"+numrow);
        	setRegistros(numrow-1);
        	setRegistrosSi(numrow-1);
            fos.write(data.toString().getBytes());
            fos.close();
            System.out.println("TERMINO LA LECTURA DEL ARCHIVO XLSX Y LA ESCRITURA DEL ARCHIVO SCV!!");
            return true;
        } catch (Exception ioe) {
            ioe.printStackTrace();
            return false;
        }
        
        */
        
		System.out.println("-->Procesando archivo de carga");
		
		Integer numCell=0,numrow=0,hoja=0;
        // For storing data into CSV files
        StringBuffer data = new StringBuffer();
        File outputFile = new File(instanceRoot+ur+nombre);

        try {
            FileOutputStream fos = new FileOutputStream(outputFile);
            
            XSSFWorkbook wBook = new XSSFWorkbook(new FileInputStream(inputFile));
            
            XSSFSheet sheet = wBook.getSheetAt(0);
            //System.out.println("Numero de hojas!!!!!"+wBook.getNumberOfSheets());
            Row row;
            Cell cell;
            
            while(hoja<wBook.getNumberOfSheets()){
            	
            	sheet = wBook.getSheetAt(hoja);
            	hoja++;
            	
	            Iterator<Row> rowIterator = sheet.iterator();                        
	
	            while (rowIterator.hasNext()) {
	            	row = rowIterator.next();
	            	numrow++;
	            	//IF PARA NO LEER LOS TITULOS DE LAS COLUMNAS
	            	if(row.getRowNum()!=0){
	            	numCell=0;
		            // POR CADA FILA SE ITERAN LAS CELDAS
		            Iterator<Cell> cellIterator = row.cellIterator();
		            	while (cellIterator.hasNext()) 
		            	{
		            	
		                    cell = cellIterator.next();
		                    numCell++;
		                    
		                    //if (numCell==1 && numCell==3 && numCell==4 && numCell==5 ){//IF PARA SELECCIONAR LAS CELDAS A CARGAR
			                    switch (cell.getCellType()){
		
				                    case Cell.CELL_TYPE_NUMERIC:

				                    	 Double d =cell.getNumericCellValue();
				                    	if (numCell==3){//Celda periodo
				                    		//String idPeriodo = DAOPERIODO.obteneridperiodo(d);
					                     //data.append(idPeriodo+"|");
				                            
				                    	} else if(numCell==4){//Celda ciclo
				                    		//String idPeriodo = DAOCICLO.obteneridperiodo(d);
						                     //data.append(idPeriodo+"|");
				                                  
			                    	  	}else{
				                    		 data.append( d.longValue() +"|");
			                    	  	}
					                        
				                            break;
			
				                    case Cell.CELL_TYPE_STRING:	
				                    	
				                    		byte[] b = cell.getStringCellValue().toUpperCase().replaceAll("\n", " ").trim().getBytes("UTF-8");
				                    		String decoded = new String(b, "UTF-8");
				                    		data.append(decoded + "|");
				                    		
				                            break;
			
				                    case Cell.CELL_TYPE_BLANK:

				                    	System.out.println("Fila"+numrow+"Celda vacia"+numCell);
				                            data.append(" " + "|");
				                            break;
			
				                    default:
				                    	System.out.println("DEFAULT----->Fila"+numrow+"Celda vacia"+numCell);
				                            data.append(" " + "|");
			                    }
		                 	//}
		            	}
		            	data.append('\n'); 
		            }
	            }}
        	System.out.println("Numero de filas procesadas:"+numrow);
        	setRegistros(numrow-1);
        	setRegistrosSi(numrow-1);
            fos.write(data.toString().getBytes());
            fos.close();
            System.out.println("TERMINO LA LECTURA DEL ARCHIVO XLSX Y LA ESCRITURA DEL ARCHIVO SCV!!");
            return true;
        } catch (Exception ioe) {
            ioe.printStackTrace();
            return false;
        }
    }
	
	public Boolean adeudosXlsx(File inputFile,String nombre) {
		System.out.println("-->Procesando archivo de carga de calificaciones");
		
		Integer numCell=0,numrow=2,lastId=0;
        // For storing data into CSV files
        StringBuffer data = new StringBuffer();
        File outputFile = new File(instanceRoot+ur+nombre);
        System.out.println(instanceRoot+ur+nombre);

        try {
            FileOutputStream fos = new FileOutputStream(outputFile);
            
            XSSFWorkbook wBook = new XSSFWorkbook(new FileInputStream(inputFile));
            
            XSSFSheet sheet = wBook.getSheetAt(0);
            //System.out.println("Numero de hojas!!!!!"+wBook.getNumberOfSheets());
            Row row;
            Cell cell;
            
            //while(hoja<wBook.getNumberOfSheets()){
            	
            	sheet = wBook.getSheetAt(0);
	            Iterator<Row> rowIterator = sheet.iterator();                        
	
	            while (rowIterator.hasNext()) {
	            	row = rowIterator.next();
	            	numrow++;
	            	//IF PARA NO LEER LOS TITULOS DE LAS COLUMNAS
	            	if(row.getRowNum()!=0){
	            		//IF PARA LA GENERACION DE LOS ID AUTOINCREMENTAL
	            		/*if(!adeudosDao.getLastId().equals("null")){
	            			lastId = new Integer (adeudosDao.getLastId());
	            			data.append(numrow+lastId -1 +"|");
	            		}else{
	            			data.append(numrow+lastId +"|");
	            		}*/
	            	numCell=0;
		            // POR CADA FILA SE ITERAN LAS CELDAS
		            Iterator<Cell> cellIterator = row.cellIterator();
		            	while (cellIterator.hasNext()) 
		            	{
		            	
		                    cell = cellIterator.next();
		                    numCell++;
		                    
		                    if (numCell==1||numCell==3||numCell==4){//IF PARA SELECCIONAR LAS CELDAS A CARGAR

			                    System.out.println("Numero de cell!!!!!"+numCell);
		                    	switch (cell.getCellType()){
				                    case Cell.CELL_TYPE_NUMERIC:
				                    		  Double d =cell.getNumericCellValue();
				                    		  String casfim = ""+  d.longValue();
				                    		  if(numCell==4){
				                    			  
					                    		  data.append( d +"|");
				                    		  }else{
					                    		  data.append( d.longValue() +"|");
					                    		  if(numCell==1){
					                    			  //adeudosDao.delete(casfim);
					                    		  } 
				                    		  }
				                    		  
					                        
				                            break;
			
				                    case Cell.CELL_TYPE_STRING:	
				                    	
				                    		byte[] b = cell.getStringCellValue().toUpperCase().replaceAll("\n", ", ").trim().getBytes("UTF-8");
				                    		String decoded = new String(b, "UTF-8");
				                    		data.append(decoded + "|");
				                    		
				                            break;
			
				                    case Cell.CELL_TYPE_BLANK:

				                    	//System.out.println("Fila"+numrow+"Celda vacia"+numCell);
				                            data.append(" " + "|");
				                            break;
			
				                    default:
				                    	System.out.println("DEFAULT----->Fila"+numrow+"Celda vacia"+numCell);
				                            data.append(" " + "|");
			                    }
		                 	}
		            	}
		            	data.append('\n'); 
		            }
	            }
            //}

        	System.out.println("Numero de filas procesadas:"+numrow);
        	setRegistros(numrow-1);
        	setRegistrosSi(numrow-1);
            fos.write(data.toString().getBytes());
            fos.close();
            System.out.println("TERMINO LA LECTURA DEL ARCHIVO XLSX Y LA ESCRITURA DEL ARCHIVO SCV!!");
            return true;
        } catch (Exception ioe) {
            ioe.printStackTrace();
            return false;
        }
    }

	//**********************************************HERRAMIENTAS PARA LA LECTURA DEL ARCHIVO*********************//	
	protected String getDateValue(Cell cell){
        
        double numericValue = cell.getNumericCellValue();
        Date date = HSSFDateUtil.getJavaDate(numericValue);
        // Add the timezone offset again because it was subtracted automatically by Apache-POI (we need UTC)
        //long tzOffset = TimeZone.getDefault().getOffset(date.getTime());
        //date = new Date(date.getTime() + tzOffset);
        int day = date.getDate();
        int month = date.getMonth();
        int year = date.getYear();
        System.out.println("-------------->"+formato.format(date));
        if(day<10){
       	 	String dday = "0"+(day+2);
        }else{
       	 	String dday = ""+(day+2);
        }
        String fecha = day+"/"+(month+1)+"/"+(year+1900);
        return fecha;
	}
	
	private  void handleCell(int type, Cell cell, FormulaEvaluator evaluator) {

        

		    if (type == XSSFCell.CELL_TYPE_STRING) {
		      dato.append(cell.getStringCellValue()+"|");
		    } else if (type == XSSFCell.CELL_TYPE_NUMERIC) {
		    	dato.append(cell.getNumericCellValue()+"|");
		    } else if (type == XSSFCell.CELL_TYPE_BOOLEAN) {
		    	dato.append(cell.getBooleanCellValue()+"|");
		    } else if (type == XSSFCell.CELL_TYPE_FORMULA) {
		        // Re-run based on the formula type
		        evaluator.evaluateFormulaCell(cell);
		        handleCell(cell.getCachedFormulaResultType(), cell, evaluator);
		    } else {
		       System.out.println("No es de ningun tipo");
		    }
	}

	public Boolean handleSheet(File inputFile) {
		try{ 
			XSSFWorkbook wBook = new XSSFWorkbook(new FileInputStream(inputFile));
			FormulaEvaluator evaluator = wBook.getCreationHelper().createFormulaEvaluator();
	        XSSFSheet sheet = wBook.getSheetAt(1);
	        File outputFile = new File(instanceRoot+ur+"csv.csv");
            FileOutputStream fos = new FileOutputStream(outputFile);
	        System.out.println("hoja");
		    for (Row row : sheet) {
		        for (Cell cell : row) {
		            handleCell(cell.getCellType(), cell,evaluator);
		        }
			    dato.append("\n");
		    }
            fos.write(dato.toString().getBytes());
            fos.close();
		    return true;
		}catch(Exception e){
			System.out.println(e.getMessage());
			return false;
		}
	}
}
