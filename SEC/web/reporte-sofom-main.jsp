<%
/*###############################################################################
# Nombre del Programa :  							                            #
# Autor               :  Leopoldo Blanco Herrera		                 		#
# Compania            :  WELLCOM S.A. DE C.V.                                   #
# Proyecto/Procliente :    				               	   FECHA:26/02/2015     #
# Descripcion General :	 									 					#
#                                                                               #
# Programa Dependiente:  					                                    #
# Programa Subsecuente:                                                         #
# Cond. de ejecucion  :  									                    #
#                                                                               #
#                                                                               #
# Dias de ejecucion   :  A Peticion del web, se pueden ejecutar n instancias  	#
#################################################################################
#								MODIFICACIONES                                  #
# Autor               : 						                                #
# Compania            : 					                                    #
# Proyecto/Procliente : 			                       Fecha: 			    #
# Modificaci�n        : 				                                        #
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
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
 	<head>
		<!--	SE REFERENC�A UNA HOJA DE ESTILOS UTILIZADA POR Wellcom		-->
		<link rel="stylesheet" type="text/css" href="css/styles.css" ></link>	
		<!-- 	SE HABILITA EL USO DE jQuery-UI Y SE ASIGNA EL TEMA	-->			
		<sj:head jqueryui="true" locale="es" jquerytheme="blitzer" compressed="true" debug="true"></sj:head>
		<base href="<%=basePath%>">    
    	<title>Dictamen SOFOM</title>
    	   
	   	<!--	TODO LO SIGUIENTE VIENE POR DEFAULT	(LE MODIFICAMOS ALGUNAS COSAS)	-->
		<meta http-equiv="cache-control" content="no-cache">
		<meta http-equiv="expires" content="0">    
		<meta http-equiv="keywords" content="banorte">
		<meta http-equiv="description" content="P�gina para carga de archivo">
				
		<!--  AQUI VA LA PARTE DE JAVASCRIPT (jQuery)-->
		<script type="text/javascript" src="js/functions.js"></script>
		<script type="text/javascript">
       var windowReport;		
       
			$(document).ready(function() 
			{			
				/*	Este m�todo se ejecuta cada que una petici�n ajax termina bien ("success")	*/				
				$("#labelMensaje").ajaxSuccess(function(event, request, settings){});
				$( "#divImagen" ).hide();
				$( "#divComentarios" ).hide(true);
				window.onbeforeunload = function(){
            		windowReport.close();
          	    }			
			});
			
			
			
			
			/*WELLCOM P-02-0271-13-SEER Inicia Marca de cambio  03/09/2014*/
			function llamarReportePDF()
			{
				//$.post("ajaxLlamarReportePrueba");
				//alert("Ponle texto" +$('#filtroCasFim').val()+"Dictamen"+$('#listaDictamen').val());
				if($('#filtroCasFim').val()==""?0:1){
					
  						//var url ='reportsjsp/reporte-remesa.jsp?filtroCasFim='+$('#filtroCasFim').val();
  						/*WELLCOM P-02-0271-13-SEER Inicia Marca de cambio  03/09/2014*/
  						var url ='servlet/ServletReporte?accion=rptSofom&tiporpt=PDF&comentarios='+$('#comentarios').val()+'&filtroCasFim='+$('#filtroCasFim').val();
  						/*WELLCOM P-02-0271-13-SEER Termina Marca de cambio 03/09/2014*/
  						windowReport=window.open(url,'','directories=0,location=0,menubar=0,resizable=1,scrollbars=1,status=0,titlebar=0,toolbar=0,width=600,height=400');
  						if(windowReport.opener){
  							document.getElementById("divImagen").style.visibility="visible";
  							parpadear();
  						}
					
  				}else{
  					alert("Falta especificar la casfim");
  					$('#filtroCasFim').focus();
  				 }
			}


			function llamarReporteXLS()
			{
				//$.post("ajaxLlamarReportePrueba");
				//alert("Ponle texto" +$('#filtroCasFim').val()+"Dictamen"+$('#listaDictamen').val());
				if($('#filtroCasFim').val()==""?0:1){
  					//var url ='reportsjsp/reporte-remesa.jsp?filtroCasFim='+$('#filtroCasFim').val();
  					var url ='servlet/ServletReporte?accion=rptcontracargo&tiporpt=XLS&filtroCasFim='+$('#filtroCasFim').val();;
  					windowReport=window.open(url,'','directories=0,location=0,menubar=0,resizable=1,scrollbars=1,status=0,titlebar=0,toolbar=0,width=600,height=400');
  					if(windowReport.opener){
  						document.getElementById("divImagen").style.visibility="visible";
  						parpadear();
  					}
  				}else{
  					alert("Falta especificar la casfim");
  					$('#filtroCasFim').focus();
  				 }
			}
			/*WELLCOM P-02-0271-13-SEER Termina Marca de cambio 03/09/2014*/
			
			function parpadear(){ 
				  $('#divImagen').fadeIn(500).delay(800).fadeOut(500,parpadear);
				 if(windowReport.closed){
					document.getElementById("divImagen").style.visibility="hidden";
					$( "#divComentarios" ).show(true);
				 }
			}
			
			///////////////
			function incSize(event) {
   					var size = event.value.length; 
   					event.setAttribute("size", size);
   					event.setAttribute("style", "width:auto")    
			}

	</script>
	</head>  
	<body>
   		<div id= "tituloTab">Dictamen SOFOM</div>
	   		<br/>
	   		<div id="divTblFiltros" class="divTblFiltros" >
		   		<div id="divFiltroReporte">
						
							CASFIM:
							<input type="text" id="filtroCasFim" maxlength="10" size="10"></input> 
							
							<input type="button" id="btnRepPDF" name="btnRepPDF" value="Mostrar PDF" onclick="llamarReportePDF();">
							
							
							&nbsp;<img id="imgCargando" style="display:none" alt="Cargando..." src="pics/banorte_loader.gif" >
						
					</div>
					<div id="divComentarios">
						<div>
							Comentarios:
						</div>
						<div >
							<textarea rows="4" cols="50" id="comentarios"></textarea>
						</div>
					</div>		
			</div>
			<div id="divImagen">
				<img src="pics/icons/Reporte_remesa.gif" width="100" height="100">
				<br>
				<br>
				<label><VAR>REPORTE DE SOFOM</VAR></label>
			</div>
  	</body>
</html>
