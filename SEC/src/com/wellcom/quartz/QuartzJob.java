package com.wellcom.quartz;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

/**
 * Clase que define el trabajo a realizar. Cuando Quartz "alcance"
 * el momento de ejecución buscará este Job y lanzará el execute.
 * 
 * @author angel.alvarez
 * 
 */
public class QuartzJob implements Job {

	public void execute(JobExecutionContext context) throws JobExecutionException {
    	try{
    		//llamada a la clase que implementa la tarea a ejecutar.
    		QuartzGenerator.generator();
    	}
    	catch (Exception e) {
    		e.printStackTrace();
		}
    }
}