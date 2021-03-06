<%
/*###############################################################################
# Nombre del Programa :  carga-archivos-ccambiario-main.jsp						#
# Autor               :  Alejandro Gonzalez Solis	  							#
# Compania            :  WELLCOM S.A. DE C.V.                                   #
# Proyecto/Procliente :  			                	   FECHA:25/02/2015     #
# Descripcion General :	 														#
#                                                                               #
# Programa Dependiente:  							 	                        #
# Programa Subsecuente:                                                         #
# Cond. de ejecucion  :  						                                #
#                                                                               #
#                                                                               #
# Dias de ejecucion   :  A Peticion del web, se pueden ejecutar n instancias  	#
#################################################################################
#								MODIFICACIONES                                  #
# Autor               :                                                         #
# Compania            :                                                         #
# Proyecto/Procliente :                              Fecha:                     #
# Modificaci�n        :                                                         #
#-----------------------------------------------------------------------------  #
# Numero de Parametros: 0                                                       #
###############################################################################*/
%>

<%@ page language="java" import="java.util.*" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="s" 	uri="/struts-tags"%>
<%@ taglib prefix="sj"	uri="/struts-jquery-tags"%>
<%@ taglib prefix="sjg"	uri="/struts-jquery-grid-tags"%>

<jsp:useBean id="ga"	 scope="session" 	class="com.wellcom.banorte.abwportal.GrantAccess"/>

<%
	 if(!ga.AccessGranted(session, "grantAccess"))  
	     response.sendRedirect("login.jsp");

%>

<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";

//String tempo1 = request.getAttribute("tempo1").toString();
//String tempo2 = request.getAttribute("tempo2").toString();
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
 	<head>
		<!--	SE REFERENC�A UNA HOJA DE ESTILOS UTILIZADA POR Wellcom		-->
		<link rel="stylesheet" type="text/css" href="css/styles.css" ></link>	
		<link href="css/ui-lightness/jquery-ui-1.10.4.css" rel="stylesheet">
		<!-- 	SE HABILITA EL USO DE jQuery-UI Y SE ASIGNA EL TEMA	-->			
		<!-- 	SE AGREGAN LAS LIBRERIAS QUE SE UTILICEN PARA LOS EFECTOS VISUALES DE LA P�GINA		-->	
		<script type="text/javascript" src="js/jquery-1.10.2.js"></script>
		<script type="text/javascript" src="js/jquery-ui-1.10.4.js"></script>
		<base href="<%=basePath%>">    
    	<title>Carga Archivos</title>
    	   
	   	<!--	TODO LO SIGUIENTE VIENE POR DEFAULT	(LE MODIFICAMOS ALGUNAS COSAS)	-->
		<meta http-equiv="cache-control" content="no-cache">
		<meta http-equiv="expires" content="0">    
		<meta http-equiv="keywords" content="banorte">
		<meta http-equiv="description" content="P�gina para carga de archivo">
				
		<!--  AQUI VA LA PARTE DE JAVASCRIPT (jQuery)-->

       <script type="text/javascript">	
       		var remesaDif;
			$(document).ready(function(){	
				$( "#divImagen" ).hide();
				$( "#advert-rem" ).hide();
				
			});
			$( document ).ajaxSuccess(function(){
			
				alert("Se completo la carga!");
			});
			
			function parpadear(){ 
				  $('#divImagen').fadeIn(500).delay(800).fadeOut(500,parpadear);
			}
			
			//Funci�n que valida los campos del formulario
			function validarCampos()
			{
				var valorFiltroCAtalogos = $("#filtroTransmisorD").val();

				if(valorFiltroCAtalogos==="-1"){
			    	alert("Selecciona un catalogo.");
			    	return;
				}
				var bandSalir=0;
				arch= $("#archivo").val();
				
				if((arch.search("xls") || arch.search("xlsx")) == -1 ){
					alert("Archivo no valido");
					document.getElementById("divImagen").style.visibility="hidden";
					return false;
				}else{ 
					document.forms["formCargaArchivo"].submit();
					return true;
					//cargarArchivoMasivo();
				}
			}
			
			function cargarArchivoMasivo(){
				var params=arch;
				$( "#divImagen" ).hide();
				$.ajax({
							url			: "ajaxGuardarArchivo",
							dataType	: "html",
							type 		: "POST",
							processData	: false,
							data 		: params
				});
				
			}

	</script>
		<style>
	body{
		font: 62.5% "Trebuchet MS", sans-serif;
		
	}
	.demoHeaders {
		margin-top: 2em;
	}
	#dialog-link {
		padding: .4em 1em .4em 20px;
		text-decoration: none;
		position: relative;
	}
	#dialog-link span.ui-icon {
		margin: 0 5px 0 0;
		position: absolute;
		left: .2em;
		top: 50%;
		margin-top: -8px;
	}
	#icons {
		margin: 0;
		padding: 0;
	}
	#icons li {
		margin: 2px;
		position: relative;
		padding: 4px 0;
		cursor: pointer;
		float: left;
		list-style: none;
	}
	#icons span.ui-icon {
		float: left;
		margin: 0 4px;
	}
	.fakewindowcontain .ui-widget-overlay {
		position: absolute;
	}
	</style>
	</head>  
	<body>
   	 <div id= "tituloTab">Carga de Archivos Transmisores de Dinero</div>
	  <div id="main">
   		<div id="divTblFiltros" class="divTblFiltros" >
   			
   			<div id="div_form">
		        <s:form id="formCargaArchivo" name="formCargaArchivo" enctype="multipart/form-data" method="POST" action="cargaArchivoTD" >	        
					
						<s:select label="Transmisor de Dinero: "  cssStyle="width: 250px;"
	       					name="filtroTransmisorD"
	       					id="filtroTransmisorD"
	       					headerKey="-1" headerValue="Selecciona un cat�logo"
	       					list="#{'tbl_accionistas.csv':'Accionistas', 'tbl_adeudos.csv':'Adeudos',
	       					'tbl_asociaciones.csv':'Asociaciones',
	       					'tbl_datos_generales.csv':'Datos Generales',
	       					'tbl_base_registro.csv':'Base de Registro',
	       					'tbl_ccc.csv':'Comit� de Comunicaci�n y Control',
	       					'tbl_ctrl.csv':'CTRL',
	       					'tbl_informe_auditoria.csv':'Informe de Auditoria',
	       					'tbl_representante_legal.csv':'Oficial de Cumplimiento',
	       					'tbl_representante_legal.csv':'Representante Legal',
	       					'tbl_transacciones_acciones':'Transacciones de Acciones'}"
	       					value="vacio"
	       					required="true"/>
					
						<s:file label="Archivo" id="archivo" name="archivo"  />
	    		</s:form>
	    	
	   			<div id="div_form">
	    			<input type="button" id="btnCargar" value="Cargar" style="font-size: 10px; width: 100px; margin-top: 10px;" onclick="javascript: validarCampos();"/>
	    		</div>
    		</div>
    		<!--Lineas 140 a 145 agregadas 11-Dic-2013-->
	        <s:div id="divImagen">
				<img src="pics/banorte_loader.gif" width="20" height="20">
					<label><VAR>Cargando...</VAR></label>
			</s:div>
	        
				<div id="div_form">
	   				<div id="filtroResultado">
    		 			<s:include value="resultado-carga-archivo.jsp"></s:include>
    		 		</div>
    		 	</div>
			</div>
			
		</div>
  	</body>
  </html>
