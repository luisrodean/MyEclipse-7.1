package com.wellcom.quartz;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.commons.logging.LogFactory;

import org.apache.commons.logging.Log;

/**
 * Clase que implementa la tarea final a ejecutar
 * 
 * @author angel.alvarez
 * 
 */
public class QuartzGenerator {
	
	public static void generator() {
		
		Log log = LogFactory.getLog(QuartzGenerator.class);;
		
		try {
			SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yy_hh:mm");
			String cron = "Application_ABW_Alive_" + formato.format(new Date());
			System.out.println("Application_ABW_Alive_" + formato.format(new Date()));
			log.debug(cron); 
		} catch (Exception er) {
			er.printStackTrace();
		}
	}
	
}