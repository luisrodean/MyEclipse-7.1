package com.wellcom.ssh;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import com.jcraft.jsch.Channel;
import com.jcraft.jsch.ChannelExec;
import com.jcraft.jsch.ChannelSftp;
import com.jcraft.jsch.JSch;
import com.jcraft.jsch.JSchException;
import com.jcraft.jsch.Session;
import com.jcraft.jsch.SftpException;

public class ConexionSsh {
	
	private String u;
	private String p;
	private String ip;
	private String instanceRoot="";
	private String urlServer="";
	private String urlApp="";

	public Session conectar(){
		JSch jsch = new JSch();
		Session jschsession;
		try {
			Properties config = new java.util.Properties(); 
            config.put("StrictHostKeyChecking", "no");
            jschsession = jsch.getSession(u, ip, 22);
			jschsession.setPassword(p);
            jschsession.setConfig(config);
			jschsession.connect();
			return jschsession;
		} catch (JSchException e) {
			// TODO Auto-generated catch block
			System.out.println("Fallo Coneccion con el servidor remoto!"+e.getMessage());
			e.printStackTrace();
			return null;
		}
		
	}
	//**************************Ejecuta el comando en el server remoto*******************//
	public void ejecutarCmd(String command){
		
		String com = "cd /u01/app/oracle/product/bin/;";
		String path = "export ORACLE_HOME=/u01/app/oracle/product/; " +//CARGA en IFOC
					  "export PATH=$ORACLE_HOME/bin:$PATH;";
		
		System.out.println(command);
		Session jschsession;
		try {
			jschsession = conectar();
			Channel channel = jschsession.openChannel("exec");
			((ChannelExec)channel).setCommand(path+com+command);
			channel.setInputStream(null);
			
			((ChannelExec)channel).setErrStream(System.err);
			 
		      InputStream in=channel.getInputStream();
		 
		      channel.connect();
		 
		      byte[] tmp=new byte[1024];
		      while(true){
		        while(in.available()>0){
		          int i=in.read(tmp, 0, 1024);
		          if(i<0)break;
		          System.out.print(new String(tmp, 0, i));
		        }
		        if(channel.isClosed()){
		          if(in.available()>0) continue; 
		          System.out.println("exit-status: "+channel.getExitStatus());
		          break;
		        }
		        try{Thread.sleep(1000);}catch(Exception ee){}
		      }
			channel.disconnect();
			
			jschsession.disconnect();

			System.out.println("Ejecuto el comando Sql Loader!");
		} catch (JSchException e) {
			// TODO Auto-generated catch block
			System.out.println("Fallo Coneccion con la BD!"+e.getMessage());
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	//*************************************Elimina los archivos en el server remoto***********//
	public void eliminarSSH(String nombre){
//		File csv = new File(instanceRoot+ur+nombre+".csv");
//		File con = new File(instanceRoot+ur+"Configuracion.dat");
//		csv.delete();
//		con.delete();
		String com = "cd "+urlServer+"\n" +
		 			 //"rm LogFileName.log " +
					 //"rm Configuracion.dat " +
					 "rm "+ nombre +".bad ";
					 //"rm "+ nombre +".csv";
		
		Session jschsession;
		try {
			jschsession = conectar();
			
			Channel channel = jschsession.openChannel("exec");
			((ChannelExec)channel).setCommand(com);
			channel.setInputStream(null);
			
			((ChannelExec)channel).setErrStream(System.err);
			 
		      InputStream in=channel.getInputStream();
		 
		      channel.connect();
		 
		      byte[] tmp=new byte[1024];
		      while(true){
		        while(in.available()>0){
		          int i=in.read(tmp, 0, 1024);
		          if(i<0)break;
		          System.out.print(new String(tmp, 0, i));
		        }
		        if(channel.isClosed()){
		          if(in.available()>0) continue; 
		          System.out.println("exit-status: "+channel.getExitStatus());
		          break;
		        }
		        try{Thread.sleep(1000);}catch(Exception ee){}
		      }
			channel.disconnect();
			jschsession.disconnect();

			System.out.println("Ejecuto EL comando !");
		} catch (JSchException e) {
			// TODO Auto-generated catch block
			System.out.println("Fallo Coneccion El con la BD!"+e.getMessage());
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	//*********************************Envia archivos al servidor remoto*********************//
	public void enviarSSH(String nombre){

		File f;
		
		Session jschsession;
		try {
			jschsession = conectar();
			
			Channel channel = jschsession.openChannel("sftp");
			
	        channel.connect();
	        ChannelSftp sftpChannel = (ChannelSftp) channel;

	        // this will read file with the name test.txt and write to remote directory
	        sftpChannel.cd(urlServer);
	        if(nombre.equals("configuracion")){
	        	f = new File(instanceRoot+urlApp+"Configuracion.dat");
	        	sftpChannel.put(new FileInputStream(f), f.getName(),ChannelSftp.OVERWRITE);
	        }else{
	        	f = new File(instanceRoot+urlApp+nombre+".csv");
	        	sftpChannel.put(new FileInputStream(f), f.getName(),ChannelSftp.OVERWRITE);
	        }
		
			channel.disconnect();
			jschsession.disconnect();

			System.out.println("Se envio correctamente el archivo!");
		} catch (JSchException e) {
			// TODO Auto-generated catch block
			System.out.println("Fallo Coneccion En con la BD!"+e.getMessage());
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			System.out.println("Fallo en el archivo ssh " +e.getMessage());
			e.printStackTrace();
		} catch (SftpException e) {
			// TODO Auto-generated catch block
			System.out.println("Fallo en el archivo CTL " +e.getMessage());
			e.printStackTrace();
		}
		
	}

}
