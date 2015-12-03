package com.wellcom.io;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;

public class Loader {
	
	private String error;
	private String registros;
	
	//**************************Set y Get**************************//
	public String getError() {
		return error;
	}
	public void setError(String error) {
		this.error = error;
	}

	public String getRegistros() {
		return registros;
	}
	public void setRegistros(String registros) {
		this.registros = registros;
	}
	//****************************************************//
	
	private void  depura(String cadena)
    {
        System.out.println("Depura: " + cadena);
        
    }
	public void errorTipo(InputStream is, String type)
	    {
	        try
	        {
	            InputStreamReader isr = new InputStreamReader(is);
	            BufferedReader br = new BufferedReader(isr);
	            String line=null;
	            
	            while ( (line = br.readLine()) != null){
	                System.out.println(type + ">" + line);
	                if(type.equals("ERROR")){
	                	error+=line+"\n";
	                }
	                if(line.contains("Records")){
	                	registros = line;	
	                }
	            }
	            
	        }catch (IOException ioe){
	        	ioe.printStackTrace();  
	        }
	}
	
	public int ejecutarCmd(ArrayList<String> list){
   	 int r=0;
   	 try{
   		 System.out.println("Ejecutando el comando"+list.size());
			 Runtime rtime = Runtime.getRuntime();
			 //Process child = rtime.exec("cmd ");
			 
			 String[] comando = new String[list.size()];
			 int i=0;
			 //BufferedWriter outCommand = new BufferedWriter(new
			 //OutputStreamWriter(child.getOutputStream()));
			 depura("Ejecutado ");
			 for(String linea:list){
				 System.out.println("$ "+linea);
				 //outCommand.write(linea);
				 //outCommand.newLine();
				comando[i]= linea;
				i++;
			 } 
			 
			 Process child = rtime.exec(comando);
			 errorTipo(child.getErrorStream(), "ERROR");
			 errorTipo(child.getInputStream(), "SALIDA");
			 //outCommand.flush();
			 r=child.waitFor();
			 depura("Shell Ejecutado "+r);
			 //outCommand.close();

		 }catch(Exception e){
			 e.printStackTrace();
		 }
		 return r;
	}
	public void loaderOra(){
		
	}
	public void loaderMysql(String server,String user,String password,String db,String path_csv,
							String rutaDB){
		ArrayList<String> lineas=new ArrayList<String>();
		
		lineas.add("cmd");
		lineas.add("/C");
		lineas.add("cd "+ rutaDB);
		lineas.add("mysqlimport" +
					" -h "+server+" -u "+user+" -p"+password+" --fields-terminated-by=\"|\" " +
							//"--debug=LogMySQLImport.log" +
					db+" "+path_csv);
		lineas.add("taskkill /f /im cmd.exe");
		lineas.add("exit");
		lineas.add("taskkill /f /im cmd.exe");
		ejecutarCmd(lineas);
	}
	public void ejecutarBat(String pathBat){
		ArrayList<String> lineas=new ArrayList<String>();
		lineas.add(pathBat);
		ejecutarCmd(lineas);
	}
	public void escribirBat(String rutaDB,String ruta, String nombre,
			String server,String user,String password,
			String db){

        StringBuffer data = new StringBuffer();
        File outputFile = new File(ruta+nombre+".bat");
        data.append("cd "+ rutaDB);
        data.append("\n mysqlimport -h "+server+" -u "+user+" -p"+password+
					" --fields-terminated-by=\"|\" " +
					//"--debug=LogMySQLImport.log" +
					db+" "+ruta+nombre);
        data.append("\n exit");
		try {
			  FileOutputStream fos = new FileOutputStream(outputFile);
			  fos.write(data.toString().getBytes());
        	  fos.close();
        	  ejecutarBat(ruta+nombre+".bat");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			System.out.println(e.getMessage());
		}
	}
}
