package com.wellcom.quartz;

import java.util.Date;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import org.quartz.JobDetail;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.SchedulerFactory;
import org.quartz.SimpleTrigger;

/**
 * Clase donde se implementa el escuchador definido en web.xml
 * El listener se ejecutará al arrancar la aplicación. Su funcionamiento
 * será definir una tarea en Quartz y lanzar el trigger con los
 * parámetros que interesen (en este caso un retardo de 60 segundos).
 * 
 * @author angel.alvarez
 * 
 */
public class QuartzListener implements ServletContextListener {

	public void contextInitialized(ServletContextEvent event) {
		try {
			//Instanciamos el gestor de tareas, a través de la clase SchedulerFactory
			SchedulerFactory schedFact = new org.quartz.impl.StdSchedulerFactory();
			Scheduler sched = schedFact.getScheduler();
			
			//Arrancamos el gestor de tareas.
			sched.start();
	
			//Definimos un trabajo
			/*QuartzJob - clase donde se define el trabajo a realizar (en este*
			 * caso, es la misma que la definida en quartz_jobs.xml pues queremos
			 * ejecutar lo mismo) */
			JobDetail job= new JobDetail("inicioJob", "inicioJobs", QuartzJob.class);
			System.out.println("Definido el Job en el listener " + new Date().toString());
			
			//Fecha Ejecución - 1 minuto de retardo tras el arranque del server
			Date fecha_ejec = new Date(System.currentTimeMillis() + 60000);
			
			//Definimos un trigger que ocurrirá una sola vez y no se repetirá
			SimpleTrigger trigger = new SimpleTrigger("inicioTrigger", "inicioTriggers", fecha_ejec);
			
			//Registramos en el  gestor el trabajo y su trigger asociado.
			//El gestor será responsable de ejecutar el trabajo cuando se dispare el trigger 
			sched.scheduleJob(job, trigger);
			
		} catch (SchedulerException e) {
			e.printStackTrace();
		}
	}
	
	public void contextDestroyed(ServletContextEvent event) {}
}