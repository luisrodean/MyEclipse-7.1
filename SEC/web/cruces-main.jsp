<%
/*###############################################################################
# Nombre del Programa :  							                            #
# Autor               :  Leopoldo Blanco				                 		#
# Compania            :  WELLCOM S.A. DE C.V.                                   #
# Proyecto/Procliente :    				               	   FECHA:10/03/2015     #
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
# Modificación        : 				                                        #
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
		<!--	SE REFERENCÍA UNA HOJA DE ESTILOS UTILIZADA POR Wellcom		-->
		<link rel="stylesheet" type="text/css" href="css/styles.css" ></link>	
		<!-- 	SE HABILITA EL USO DE jQuery-UI Y SE ASIGNA EL TEMA	-->			
		<sj:head jqueryui="true" locale="es" jquerytheme="blitzer" compressed="true" debug="true"></sj:head>
		<base href="<%=basePath%>">    
    	<title>Cruces</title>
    	   
	   	<!--	TODO LO SIGUIENTE VIENE POR DEFAULT	(LE MODIFICAMOS ALGUNAS COSAS)	-->
		<meta http-equiv="cache-control" content="no-cache">
		<meta http-equiv="expires" content="0">    
		<meta http-equiv="keywords" content="banorte">
		<meta http-equiv="description" content="Página para carga de archivo">
				
		<!--  AQUI VA LA PARTE DE JAVASCRIPT (jQuery)-->
		<script type="text/javascript" src="js/functions.js"></script>
		<script type="text/javascript">
       var windowReport;		
       
			$(document).ready(function() 
			{			
				/*	Este método se ejecuta cada que una petición ajax termina bien ("success")	*/
					$("#boton").hide(true);
					$("#persona").hide(true);
					$("#domicilio").hide(true);
					$("#casfim").hide(true);
					$("#telefono").hide(true);
					$("#sector").hide(true);
				$("#labelMensaje").ajaxSuccess(function(event, request, settings){});
				$( "#divImagen" ).hide();
				window.onbeforeunload = function(){
            		windowReport.close();
          	    }			
			});
			
			function Filtro(){
				var opcion = $("#filtroBusqueda").val();
				if(opcion=="-1"){
					$("#persona").hide(true);
					$("#domicilio").hide(true);
					$("#casfim").hide(true);
					$("#telefono").hide(true);
					$("#sector").hide(true);
					$("#boton").hide(true);
				}else if(opcion == "POR PERSONA MORAL"||opcion == "POR PERSONA FISICA"){
					$("#persona").show(true);
					$("#domicilio").hide(true);
					$("#casfim").hide(true);
					$("#telefono").hide(true);
					$("#sector").hide(true);
					$("#boton").show(true);
				}else if(opcion == "POR DOMICILIO"){
					$("#domicilio").show("blind");
					$("#persona").hide(true);
					$("#casfim").hide(true);
					$("#telefono").hide(true);
					$("#sector").hide(true);
					$("#boton").show(true);
				}else if(opcion == "POR CASFIM"){
					$("#casfim").show("blind");
					$("#persona").hide(true);
					$("#domicilio").hide(true);
					$("#telefono").hide(true);
					$("#sector").hide(true);
					$("#boton").show(true);
				}else if(opcion == "POR TELEFONO"){
					$("#telefono").show("blind");
					$("#persona").hide(true);
					$("#domicilio").hide(true);
					$("#casfim").hide(true);
					$("#sector").hide(true);
					$("#boton").show(true);
				}else if(opcion == "POR SECTOR"){
					$("#sector").show("blind");
					$("#persona").hide(true);
					$("#domicilio").hide(true);
					$("#casfim").hide(true);
					$("#telefono").hide(true);
					$("#boton").show(true);
				}
			}
			function parpadear(){ 
				  $('#divImagen').fadeIn(500).delay(800).fadeOut(500,parpadear);
				 if(windowReport.closed){
					document.getElementById("divImagen").style.visibility="hidden";
				 }
			}

	</script>
	</head>  
	<body>
	<div id="main">
   		<div id= "tituloTab">Cruces</div>
   			<div id="divTblFiltros" class="divTblFiltros" >
   					<div id="div_form">
   					<s:form id="formBuscar" name="formBuscar" method="POST" action="buscar" >
   						<s:select label="Criterio de Busqueda: "  cssStyle="width: 200px;"
       					name="filtroBusqueda"
       					id="filtroBusqueda"
       					headerKey="-1" headerValue="Selecciona un criterio"
       					list="#{'POR PERSONA FISICA':'POR PERSONA FISICA',
       					'POR PERSONA MORAL':'POR PERSONA MORAL',
       					'POR DOMICILIO':'POR DOMICILIO',
       					'POR CASFIM':'POR CASFIM',
       					'POR TELEFONO':'POR TELEFONO',
       					'POR SECTOR':'POR SECTOR'}"
       					value="vacio"
       					required="true" onchange="javascript: Filtro();"/>
    				</s:form>
   					</div>
   					<div id="filtroBuscar">
	   					<div id="persona">
	   						<input name="nombreTxt" placeholder="Nombre" type="text" id="persona_fisicaTxt" size="20" maxlength="16" onkeypress="return validar(event)">
	   						<input name="aPaternoTxt" placeholder="Apellido Paterno" type="text" id="persona_fisicaTxt" size="20" maxlength="16" onkeypress="return validar(event)">
	   						<input name="aMaternoTxt" placeholder="Apellido Materno" type="text" id="persona_fisicaTxt" size="20" maxlength="16" onkeypress="return validar(event)">
	   					</div>
	   					<div id="domicilio">
	   						<input name="calleTxt" placeholder="Calle" type="text" id="calleTxt" size="20" maxlength="16" onkeypress="return validar(event)">
	   						<input name="coloniaTxt" placeholder="Colonia" type="text" id="coloniaTxt" size="20" maxlength="16" onkeypress="return validar(event)">
	   						<input name="delegecionTxt" placeholder="Delegacion" type="text" id="delegacionTxt" size="20" maxlength="16" onkeypress="return validar(event)">
	   					</div>
	   					<div id="casfim">
	   						<input name="casfimTxt" placeholder="CASFIM" type="text" id="casfimTxt" size="20" maxlength="16" onkeypress="return validar(event)">
	   					</div>
						<div id="telefono">
							<input name="telefonoTxt" placeholder="Telefono" type="text" id="telefonoTxt" size="20" maxlength="16" onkeypress="return validar(event)">
						</div>
						<div id="sector">
							<input name="sectorTxt" placeholder="Sector" type="text" id="sectorTxt" size="20" maxlength="16" onkeypress="return validar(event)">
						</div>
					<div id="filtroBuscar">
						<div id="boton">
							<input type="button" id="btnBuscar" value="Buscar" style="font-size: 14px;" onclick="javascript: validarCampos();"/>
						</div>
					</div>	
					</div>
				</div>
			</div>
  	</body>
</html>
