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
    	<title>Consultas</title>
    	   
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
					$("#denominacion").hide(true);
					$("#divImagen").hide(true);
					$("#botonAceptar").hide(true);
					$("#divCruces").hide(true);
					//$("#divCruces").hide(true);
				$("#labelMensaje").ajaxSuccess(function(event, request, settings){});
				$( "#divImagen" ).hide();
				window.onbeforeunload = function(){
            		windowReport.close();
          	    }			
			});
			///////////////////////////////////////////////////AJAX SUCCESS/////////////////////////////////////////////////////
			$(document).ajaxSuccess(function(event, request, settings)
				{	
					if(settings.url.match('ajaxBuscar')!=null){
						
						mostrarResultado(request);
					}
				
			});
			////////////////////////////////////////////////////AJAX ERROR//////////////////////////////////////////////////////
			$(document).ajaxError(function(event, request, settings)
				{	
					
				var operResult = $.parseJSON(request.responseText).operResult;
					alert("Hubo un error en la busqueda");
					/*if(settings.url.match('ajaxBuscar')!=null){
						
						mostrarResultado(request);
					}*/
				
			});
			
			function Filtro(){
				var opcion = $("#filtroBusqueda").val();
				//VACIA LOS RESULTADOS ANTERIORES
					$("#personasCoincid").empty();
					$("#edosCoincid").empty();
					$("#domicCoincid").empty();
					$("#sectorCoincid").empty();
					$("#casfimCoincid").empty();
					$("#denomCoincid").empty();
					$("#telefonoCoincid").empty();
				// OCULTA LOS DIV
					$("#divCruces").hide(true);
				///////////////////////////////////////
				if(opcion=="-1"){
					$("#persona").hide(true);
					$("#domicilio").hide(true);
					$("#casfim").hide(true);
					$("#telefono").hide(true);
					$("#sector").hide(true);
					$("#denominacion").hide(true);
					$("#boton").hide(true);
				}else if(opcion == "POR PERSONA MORAL"||opcion == "POR PERSONA FISICA"){
					$("#persona").show(true);
					$("#domicilio").hide(true);
					$("#casfim").hide(true);
					$("#telefono").hide(true);
					$("#sector").hide(true);
					$("#denominacion").hide(true);
					$("#boton").show(true);
				}else if(opcion == "POR DOMICILIO"){
					$("#domicilio").show("blind");
					$("#persona").hide(true);
					$("#casfim").hide(true);
					$("#telefono").hide(true);
					$("#sector").hide(true);
					$("#denominacion").hide(true);
					$("#boton").show(true);
				}else if(opcion == "POR CASFIM"){
					$("#casfim").show("blind");
					$("#persona").hide(true);
					$("#domicilio").hide(true);
					$("#telefono").hide(true);
					$("#sector").hide(true);
					$("#denominacion").hide(true);
					$("#boton").show(true);
				}else if(opcion == "POR TELEFONO"){
					$("#telefono").show("blind");
					$("#persona").hide(true);
					$("#domicilio").hide(true);
					$("#casfim").hide(true);
					$("#sector").hide(true);
					$("#denominacion").hide(true);
					$("#boton").show(true);
				}else if(opcion == "POR SECTOR"){
					$("#sector").show("blind");
					$("#persona").hide(true);
					$("#domicilio").hide(true);
					$("#casfim").hide(true);
					$("#telefono").hide(true);
					$("#denominacion").hide(true);
					$("#boton").show(true);
				}else if(opcion == "POR DENOMINACION"){
					$("#denominacion").show(true);
					$("#sector").hide(true);
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
			function validarNumeros(e){
				
			
				//alert("Entra");
				var tecla = (document.all) ? e.keyCode : e.which; // 2
				if (tecla==8) return true; // backspace
				if (tecla==109) return true; // menos
		    if (tecla==110) return true; // punto
				if (tecla==189) return true; // guion
				if (e.ctrlKey && tecla==86) { return true}; //Ctrl v
				if (e.ctrlKey && tecla==67) { return true}; //Ctrl c
				if (e.ctrlKey && tecla==88) { return true}; //Ctrl x
				if (tecla>=96 && tecla<=105) { return true;} //numpad
		 
				patron = /[0-9]/; // patron
		 
				te = String.fromCharCode(tecla); 
				return patron.test(te); // prueba
			
			}
			function validarCampos()
			{
			//VACIA LOS RESULTADOS ANTERIORES
					$("#personasCoincid").empty();
					$("#edosCoincid").empty();
					$("#domicCoincid").empty();
					$("#sectorCoincid").empty();
					$("#casfimCoincid").empty();
					$("#denomCoincid").empty();
					$("#telefonoCoincid").empty();
			$("#divCruces").load();
				$.getJSON("ajaxBuscar"
				,{
					// Establecemos los parámetros
					filtroBusqueda		: $("#filtroBusqueda").val(),
					nombreTxt 			: $("#nombreTxt").val(),
					aPaternoTxt			: $("#aPaternoTxt").val(),
					aMaternoTxt			: $("#aMaternoTxt").val(),
					calleTxt			: $("#calleTxt").val(),
					numExtTxt			: $("#numExtTxt").val(),
					numIntTxt			: $("#numIntTxt").val(),
					coloniaTxt			: $("#coloniaTxt").val(),
					delegacionTxt		: $("#delegacionTxt").val(),
					estadoTxt			: $("#estadoTxt").val(),
					casfimTxt			: $("#casfimTxt").val(),
					sectorTxt			: $("#sectorTxt").val(),
					denomTxt			: $("#denomTxt").val(),
					telefonoTxt			: $("#telefonoTxt").val()
				},function(datos) // Función que se ejecuta cuando termina la petición ajax
				  {	
					
					});
					
					//document.forms["formBuscar"].submit();
					//$("#divImagen" ).show(true);
					//return true;
					
				
			}
			function reload() {
				alert("loadDD");
				document.getElementById('divCruces').reload(true);
    			//$('#divCruces').load();
			}
			function mostrarResultado(request)
			{
				// Convertimos el responseText del request a jSON y obtenemos el mensaje (variable operResult del Action)
				var personasCoinTxt = $.parseJSON(request.responseText).personasCoinTxt;
				var	edosCoinTxt = $.parseJSON(request.responseText).edosCoinTxt;
				var	domicCoinTxt = $.parseJSON(request.responseText).domicCoinTxt;
				var	sectorCoinTxt = $.parseJSON(request.responseText).sectorCoinTxt;
				var	casfimCoinTxt = $.parseJSON(request.responseText).casfimCoinTxt;
				var denomCoinTxt = $.parseJSON(request.responseText).denomCoinTxt;
				var telefonoCoinTxt = $.parseJSON(request.responseText).telefonoCoinTxt;
				var operResult = $.parseJSON(request.responseText).operResult;
					//$("#boton").hide(true);
					//$("#botonAceptar").show(true);
					$("#divCruces").show(true);
				if(personasCoinTxt!=null){
					$("#personasCoincid").show(true);
					$("#edosCoincid").hide(true);
					$("#domicCoincid").hide(true);
					$("#sectorCoincid").hide(true);
					$("#casfimCoincid").hide(true);
					$("#denomCoincid").hide(true);
					$("#telefonoCoincid").hide(true);
					$("#personasCoincid").append(personasCoinTxt);
					$("#personasCoincid").append(operResult);
				}else if(edosCoinTxt!=null){
					$("#personasCoincid").hide(true);
					$("#edosCoincid").show(true);
					$("#domicCoincid").hide(true);
					$("#sectorCoincid").hide(true);
					$("#casfimCoincid").hide(true);
					$("#denomCoincid").hide(true);
					$("#telefonoCoincid").hide(true);
					$("#edosCoincid").append(edosCoinTxt);
					$("#edosCoincid").append(operResult);
				}else if(domicCoinTxt!=null){
					$("#personasCoincid").hide(true);
					$("#edosCoincid").hide(true);
					$("#domicCoincid").show(true);
					$("#sectorCoincid").hide(true);
					$("#casfimCoincid").hide(true);
					$("#denomCoincid").hide(true);
					$("#telefonoCoincid").hide(true);
					$("#domicCoincid").append(domicCoinTxt);
					$("#domicCoincid").append(operResult);
				}else if(sectorCoinTxt!=null){
					$("#personasCoincid").hide(true);
					$("#edosCoincid").hide(true);
					$("#domicCoincid").hide(true);
					$("#sectorCoincid").show(true);
					$("#casfimCoincid").hide(true);
					$("#denomCoincid").hide(true);
					$("#telefonoCoincid").hide(true);
					$("#sectorCoincid").append(sectorCoinTxt);
					$("#sectorCoincid").append(operResult);
				}else if(casfimCoinTxt!=null){
					$("#personasCoincid").hide(true);
					$("#edosCoincid").hide(true);
					$("#domicCoincid").hide(true);
					$("#sectorCoincid").hide(true);
					$("#denomCoincid").hide(true);
					$("#telefonoCoincid").hide(true);
					$("#casfimCoincid").show(true);
					$("#casfimCoincid").append(casfimCoinTxt);
					$("#casfimCoincid").append(operResult);
				}else if(denomCoinTxt!=null){
					$("#personasCoincid").hide(true);
					$("#edosCoincid").hide(true);
					$("#domicCoincid").hide(true);
					$("#sectorCoincid").hide(true);
					$("#casfimCoincid").hide(true);
					$("#telefonoCoincid").hide(true);
					$("#denomCoincid").show(true);
					$("#denomCoincid").append(denomCoinTxt);
					$("#denomCoincid").append(operResult);
				}else if(telefonoCoinTxt!=null){
					$("#personasCoincid").hide(true);
					$("#edosCoincid").hide(true);
					$("#domicCoincid").hide(true);
					$("#sectorCoincid").hide(true);
					$("#casfimCoincid").hide(true);
					$("#denomCoincid").hide(true);
					$("#telefonoCoincid").show(true);
					$("#telefonoCoincid").append(telefonoCoinTxt);
					$("#telefonoCoincid").append(operResult);
				}
			}
			
			function llamarReporteXLS(casfim)
			{
				if($('#filtroCasFim').val()==""?0:1){
  					var url ='servlet/ServletReporte?accion=rptCC&tiporpt=PDF&filtroCasFim='+casfim;
  					windowReport=window.open(url,'','directories=0,location=0,menubar=0,resizable=1,scrollbars=1,status=0,titlebar=0,toolbar=0,width=600,height=400');
  					
  				}else{
  					alert("Falta especificar la casfim");
  					$('#filtroCasFim').focus();
  				 }
			}

	</script>
	</head>  
	<body>
	<div id="main">
   		<div id= "tituloTab">Consultas</div>
   			<div id="divTblFiltros" class="divTblFiltros" >
   					<div id="div_form">
	   					<s:form id="formBuscar" name="formBuscar" method="POST" action="buscar" cssStyle="margin-bottom: 10px;">
   						
	   						<s:select label="Criterio de Busqueda: "  cssStyle="width: 200px;"
	       					name="filtroBusqueda"
	       					id="filtroBusqueda"
	       					headerKey="-1" headerValue="Selecciona un criterio"
	       					list="#{'POR DENOMINACION':'POR DENOMINACION',
	       					'POR PERSONA FISICA':'POR PERSONA',
	       					'POR DOMICILIO':'POR DOMICILIO',
	       					'POR CASFIM':'POR CASFIM',
	       					'POR TELEFONO':'POR TELEFONO',
	       					'POR SECTOR':'POR SECTOR'}"
	       					value="vacio"
	       					required="true" onchange="javascript: Filtro();"/>
	    					
						</s:form>
						
   					</div>
	   					<div id="filtroBuscar">
		   					<div id="denominacion">
		   						<input name="denomTxt" placeholder="Denominación" type="text" id="denomTxt" size="20" maxlength="100" onkeypress="return validar(event)">
		   					</div>
		   					<div id="persona">
		   						<input name="nombreTxt" placeholder="Nombre" type="text" id="nombreTxt" size="20" maxlength="20" onkeypress="return validar(event)">
		   						<input name="aPaternoTxt" placeholder="Apellido Paterno" type="text" id="aPaternoTxt" size="20" maxlength="20" onkeypress="return validar(event)">
		   						<input name="aMaternoTxt" placeholder="Apellido Materno" type="text" id="aMaternoTxt" size="20" maxlength="20" onkeypress="return validar(event)">
		   					</div>
		   					<div id="domicilio">
		   						<input name="calleTxt" placeholder="Calle" type="text" id="calleTxt" size="20" maxlength="20" onkeypress="return validar(event)">
		   						<input name="numExtTxt" placeholder="Numero Exterior" type="text" id="numExtTxt" size="20" maxlength="20" onkeypress="return validar(event)">
		   						<input name="numIntTxt" placeholder="Numero Interior" type="text" id="numIntTxt" size="20" maxlength="20" onkeypress="return validar(event)">
		   						<input name="coloniaTxt" placeholder="Colonia" type="text" id="coloniaTxt" size="20" maxlength="20" onkeypress="return validar(event)">
		   						<input name="delegecionTxt" placeholder="Delegacion o Municipio" type="text" id="delegacionTxt" size="20" maxlength="20" onkeypress="return validar(event)">
		   						<input name="estadoTxt" placeholder="Estado" type="text" id="estadoTxt" size="20" maxlength="20" onkeypress="return validar(event)">
		   					</div>
		   					<div id="casfim">
		   						<input name="casfimTxt" placeholder="CASFIM" type="text" id="casfimTxt" size="20" maxlength="20" onkeydown="return validarNumeros(event);">
		   					</div>
							<div id="telefono">
								<input name="telefonoTxt" placeholder="Telefono" type="text" id="telefonoTxt" size="20" maxlength="20" onkeypress="return validar(event)">
							</div>
							<div id="sector">
								<input name="sectorTxt" placeholder="Sector" type="text" id="sectorTxt" size="20" maxlength="30" onkeypress="return validar(event)">
							</div>
							<div id="filtroBuscar">
								<div id="boton">
									<input type="button" id="btnBuscar" value="Buscar" style="font-size: 14px;" onclick="javascript: validarCampos();"/>
								</div>
							</div>	
						</div>
					<s:div id="divImagen">
						<img src="pics/banorte_loader.gif" width="20" height="20">
						<br>
						<br>
						<label><VAR>Cargando...</VAR></label>
					</s:div>
			</div>
			<div id="divCruces" class="divTblFiltros" >
				<div id="denomCoincid">
							Coincidencias con denominación:<s:property value="denomCoinTxt" />
				</div>
				<div id="personasCoincid">
							Coincidencias con personas:<s:property value="personasCoinTxt" />
				</div>
				
				<div id="edosCoincid">
							Coincidencias con estados:<s:property value="edosCoinTxt" />
				</div>
				<div id="domicCoincid">
							Coincidencias con domicilios:<s:property value="domicCoinTxt" />
				</div>
				<div id="sectorCoincid">
							Coincidencias con sector:<s:property value="sectorCoinTxt" />
				</div>
				<div id="casfimCoincid">
							Coincidencias con CASFIM:<s:property value="casfimCoinTxt" />
				</div>
				<div id="telefonoCoincid">
							Coincidencias con telefonos:<s:property value="telefonoCoinTxt" />
				</div>
				<div id="filtroBuscar">
						<div id="botonAceptar">
							<input type="button" id="btnBuscar" value="Aceptar" style="font-size: 14px;" onclick="javascript: location.reload();"/>
						</div>
				</div>
			
			</div>
		</div>
  	</body>
</html>
