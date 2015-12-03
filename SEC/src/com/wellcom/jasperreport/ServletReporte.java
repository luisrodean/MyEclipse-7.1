package com.wellcom.jasperreport;


import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.sql.Connection;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.sql.DataSource;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JRExporter;
import net.sf.jasperreports.engine.JRExporterParameter;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.export.JRPdfExporter;
import net.sf.jasperreports.engine.export.JRXlsExporter;
import net.sf.jasperreports.engine.export.JRXlsExporterParameter;
import net.sf.jasperreports.engine.util.JRLoader;

//import com.wellcom.hibernate.dao.HerramientasDAO;


public class ServletReporte extends HttpServlet {

	/**
	 * 
	 */
	//LLave para reporte Jasper
	//protected static final String llave=new HerramientasDAO().leerFactor();
	
	private static final long serialVersionUID = 1L;

	/**
	 * Constructor of the object.
	 */
	public ServletReporte() {
		super();
	}

	/**
	 * Destruction of the servlet. <br>
	 */
	public void destroy() {
		super.destroy(); // Just puts "destroy" string in log
		// Put your code here
	}
	
	
	
	/*Se utiliza este metodo para realizar la conexion con el Data Pool(jdbc/MDT)
	 *que esta dado de alta en el servidor(en este caso Galssfish)
	 * */
	 private Connection getConexion() {
	     //Se crea un objeto del tipo javax.naming.Context
		 Context ctx;
		 //Se crea un objeto de tipo java.sql.Connecion
	     Connection conx = null;
	  	 //Se inicializa el objeto Context
	         try {
				ctx = new InitialContext();
			 /*Se realiza la conexion con el Data pool si es exitosa
	          * se almacena en una variable de tipo javax.sql.DatSource de lo contrario el objeto regresa NULL
	          * y lanza una excepcion
	          */
				DataSource dataSource;
				dataSource = (DataSource) ctx.lookup("jdbc/MDT");
				conx = dataSource.getConnection();
				//System.out.println("Conexion exitosa: "+ conx);
	         } catch (NamingException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
					System.err.println("---> Error al enlazar con el data pool");
	         } catch (SQLException e) {
	        	 System.err.println("---> Error en la conexion a BD.");
				// TODO Auto-generated catch block
				e.printStackTrace();
	         }
	     return conx;
	 }
	 
	/**
	 * The doGet method of the servlet. <br>
	 * 
	 * This method is called when a form has its tag value method equals to get.
	 * 
	 * @param request
	 *            the request send by the client to the server
	 * @param response
	 *            the response send by the server to the client
	 * @throws ServletException
	 *             if an error occurred
	 * @throws IOException
	 *             if an error occurred
	 */
	//Metodo para Establecer los parametros para el filtro numero de remesa
	
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		//Path del Logo
		InputStream img=getServletContext().getResourceAsStream("/WEB-INF/SHCP-PORTAL.png");
		
		//Path del Logo del Header
		InputStream img2=getServletContext().getResourceAsStream("/WEB-INF/CNBV-PORTAL.png");
		
	    //Conexion al Data Pool
	    Connection conx = this.getConexion();
	    //Mapa para enviar los parametros al reporte
		HashMap<String, Object> mp = new HashMap<String, Object>();
		
		
		//Recuperacion del usuario logueado
		HttpSession sesion= request.getSession();
		String usr = (String) sesion.getAttribute("usuarioActivo");
		
		String casfim = request.getParameter("filtroCasFim");
		String comentarios = request.getParameter("comentarios");
		//Creacion del mapa de parametros a usar en el reporte
		mp.clear();
		mp.put("comentarios",comentarios);
		mp.put("casfim",casfim);
		mp.put("img1", img);
		mp.put("img2", img2);
		
		System.out.println("comentarios"+comentarios);
		System.out.println("casfim"+casfim);
		
		//Obtencion de la bandera accion que indica que JSP mando llamar al servlet
		String accion = request.getParameter("accion");
		String tiporpt = request.getParameter("tiporpt");
		
		System.out.println("Reporte: "+accion);
		System.out.println("tiporpt: "+tiporpt);
		
		
		String nombreReporte;
		
		if(accion.equals("rptTD")){
		    
			nombreReporte = "DictamenCC.jasper";	
			mp.put("sector", "Transmisor de Dinero");
			
			 if(conx!=null)
				 if(tiporpt.equals("PDF")){
			    		response.setContentType("application/pdf");
						ServletOutputStream out = response.getOutputStream();
						PDFReport(conx,nombreReporte,mp,out);
						out.flush();
						out.close();
			    		
			    	}else if(tiporpt.equals("XLS")){
					    //Establecimiento de context
					    response.setContentType("application/vnd.ms-excel");
						ServletOutputStream out = response.getOutputStream();
						try {
							XLSReport(conx,nombreReporte,mp,out,response);
						} catch (JRException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
						out.flush();
						out.close();
			    	}
				else 
					System.out.println("Error al establecer la conexion con el Data Pool ");
		}
if(accion.equals("rptSofom")){
		    
			nombreReporte = "DictamenCC.jasper";	
			mp.put("sector", "SOFOM");
			
			 if(conx!=null)
				 if(tiporpt.equals("PDF")){
			    		response.setContentType("application/pdf");
						ServletOutputStream out = response.getOutputStream();
						PDFReport(conx,nombreReporte,mp,out);
						out.flush();
						out.close();
			    		
			    	}else if(tiporpt.equals("XLS")){
					    //Establecimiento de context
					    response.setContentType("application/vnd.ms-excel");
						ServletOutputStream out = response.getOutputStream();
						try {
							XLSReport(conx,nombreReporte,mp,out,response);
						} catch (JRException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
						out.flush();
						out.close();
			    	}
				else 
					System.out.println("Error al establecer la conexion con el Data Pool ");
		}
		if(accion.equals("rptCC")){
			nombreReporte = "DictamenCC.jasper";	
			mp.put("sector", "Centro Cambiario");
			
		    if(conx!=null)
		    	if(tiporpt.equals("PDF")){
		    		response.setContentType("application/pdf");
					ServletOutputStream out = response.getOutputStream();
					PDFReport(conx,nombreReporte,mp,out);
					out.flush();
					out.close();
		    		
		    	}else if(tiporpt.equals("XLS")){
		    		response.setContentType("application/vnd.ms-excel");
					ServletOutputStream out = response.getOutputStream();
					try {
						XLSReport(conx,nombreReporte,mp,out,response);
					} catch (JRException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					out.flush();
					out.close();
		    	}
			else 
				System.out.println("Error al establecer la conexion con el Data Pool ");
		}
	}

	
public void XLSReport(Connection con,String nombreReporte,HashMap<String, Object> mp,ServletOutputStream out,HttpServletResponse response) throws JRException {
		JasperReport reporte = null;
		JRXlsExporter exporter = null;
		JasperPrint jasperPrint = null;
		reporte = (JasperReport) JRLoader.loadObjectFromFile(getServletContext().getRealPath("WEB-INF/"+nombreReporte));
		System.out.println("Nombre del reporte: "+reporte);
		try {
			//Se llena el reporte con los datos de la BD y parametros como titulo, etc.
			jasperPrint = JasperFillManager.fillReport(reporte, mp, con);
			ByteArrayOutputStream arrayOutputStream = new ByteArrayOutputStream();
			if (jasperPrint==null) {
				out.print("No tiene datos");	
			}
			//se crea un objeto JRPdfExportes y se colocan los parametros para hacer la exportacion a PDF
			exporter = new JRXlsExporter();
			exporter.setParameter(JRXlsExporterParameter.JASPER_PRINT, jasperPrint);
			exporter.setParameter(JRXlsExporterParameter.OUTPUT_STREAM, arrayOutputStream);
			//exporter.setParameter(JRExporterParameter.,"PruebaExcel.xls");
			 /*para los reportes en excel para una mejor visualizacion utilizo esta configuracion*/
            exporter.setParameter(JRXlsExporterParameter.IS_ONE_PAGE_PER_SHEET, Boolean.FALSE);
            exporter.setParameter(JRXlsExporterParameter.IS_DETECT_CELL_TYPE, Boolean.TRUE);
            exporter.setParameter(JRXlsExporterParameter.IS_WHITE_PAGE_BACKGROUND, Boolean.FALSE);
            exporter.setParameter(JRXlsExporterParameter.IS_REMOVE_EMPTY_SPACE_BETWEEN_ROWS, Boolean.TRUE);
            exporter.setParameter(JRXlsExporterParameter.IS_IGNORE_CELL_BORDER, Boolean.FALSE);
			exporter.exportReport();
			response.setHeader("Content-disposition", "attachment; filename=Reporte.xls");
			response.setContentType("application/vnd.ms-excel");
			response.setContentLength(arrayOutputStream.toByteArray().length);
			out.write(arrayOutputStream.toByteArray());
			con.close();
			
		}catch (SQLException e) {
			System.err.println("---> Error al cerrar la conexion a BD.");
			e.printStackTrace();
		}catch (JRException e) {
			System.err.println("--->Error al exportar reporte a XLS.");
			e.printStackTrace();
//		} catch (ClassNotFoundException e) {
//			System.err.println("--->Error al llenar el reporte");
//			e.printStackTrace();
		} catch (IOException e) {
			System.err.println("--->Error en la salida out");
			e.printStackTrace();
		}

	}

	public void PDFReport(Connection con,String nombreRepote,HashMap<String, Object> mp,ServletOutputStream out) {
		JasperReport reporte = null;
		JRExporter exporter = null;
		JasperPrint jasperPrint = null;
		//carga el reporte jasper desde el directorio donde se encuentra el archivo .jasper
		try {
			reporte = (JasperReport) JRLoader.loadObjectFromFile(getServletContext().getRealPath("WEB-INF/"+nombreRepote));
		} catch (JRException e) {
			System.err.println("---> Error al cargar el archivo .jasper");
			e.printStackTrace();
		}

		try {
			//Se llena el reporte con los datos de la BD y parametros como titulo, etc.
			jasperPrint = JasperFillManager.fillReport(reporte, mp, con);

			if (jasperPrint.getPages().isEmpty()) {
				out.print("No tiene datos");	
			}
			//se crea un objeto JRPdfExportes y se colocan los parametros para hacer la exportacion a PDF
			exporter = new JRPdfExporter();
			exporter.setParameter(JRExporterParameter.JASPER_PRINT, jasperPrint);
			exporter.setParameter(JRExporterParameter.OUTPUT_STREAM, out);
			exporter.exportReport();
			//Cierre de la conexion a BD
			//con.Cerrar();
			con.close();

		}catch (SQLException e) {
			System.err.println("---> Error al cerrar la conexion a BD.");
			e.printStackTrace();
		}catch (JRException e) {
			System.err.println("--->Error al exportar reporte a PDF.");
			e.printStackTrace();
			//	} catch (ClassNotFoundException e) {
			//		System.err.println("--->Error al llenar el reporte");
			//		e.printStackTrace();
		} catch (IOException e) {
			System.err.println("--->Error en la salida out");
			e.printStackTrace();
		}

}
	
	
	
}
