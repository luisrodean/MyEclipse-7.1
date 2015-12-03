<%
/*###############################################################################
# Nombre del Programa :  							                            #
# Autor               :  JOAQUIN MOJICA Q. & Wellcom team						#
# Compania            :  WELLCOM S.A. DE C.V.                                   #
# Proyecto/Procliente :    				               	   FECHA:07/09/2015     #
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
# Autor               :                                                         #
# Compania            :                                                         #
# Proyecto/Procliente :                              Fecha:                     #
# Modificación        :                                                         #
#-----------------------------------------------------------------------------  #
# Numero de Parametros: 0                                                       #
###############################################################################*/
%>

<%@ page language="java" import="java.util.*" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="s" 	uri="/struts-tags"%>
<%@ taglib prefix="sj"	uri="/struts-jquery-tags"%>
<%@ taglib prefix="sjg"	uri="/struts-jquery-grid-tags"%>

<jsp:useBean id="ga"	scope="session" 	class="com.wellcom.banorte.abwportal.GrantAccess"/>

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
		<sj:head jqueryui="true" locale="es" jquerytheme="pepper-grinder" compressed="true" debug="true"></sj:head>
		<!-- 	SE AGREGAN LAS LIBRERIAS QUE SE UTILICEN PARA LOS EFECTOS VISUALES DE LA PÁGINA		-->	
		<script type="text/javascript" src="struts/js/base/jquery.effects.core.min.js?s2j=3.3.0"></script>
		<script type="text/javascript" src="struts/js/base/jquery.effects.highlight.min.js?s2j=3.3.0"></script>
		<script type="text/javascript" src="struts/js/base/jquery.effects.blind.min.js?s2j=3.3.0"></script>

		<base href="<%=basePath%>">    
    	<title>Lista de Calificaciones</title>
    	   
	   	<!--	TODO LO SIGUIENTE VIENE POR DEFAULT	(LE MODIFICAMOS ALGUNAS COSAS)	-->
		<meta http-equiv="cache-control" content="no-cache">
		<meta http-equiv="expires" content="0">    
		<meta http-equiv="keywords" content="banorte">
		<meta http-equiv="description" content="Página en la que se administran los usuarios de la aplicación">
				
		<!--  AQUI VA LA PARTE DE JAVASCRIPT (jQuery)-->
		<script type="text/javascript">
		var varToggleFiltros;

		var bandBusqueda=0;
		
			$(document).ready(function() 
			{	
				//$('#gridListaUsuarios').trigger("reloadGrid");		
				/*	Este método se ejecuta cada que una petición ajax termina bien ("success")	*/				
				$("#labelMensaje").ajaxSuccess(function(event, request, settings)
				{				
					//alert(settings.url);
					if(settings.url.match('ajaxAbcCalificaciones')!=null){
						mostrarMensaje(request);
						//setInterval(function(){location.reload();} ,3000);
						//buscar();
						//$('#gridListaUsuarios').trigger("reloadGrid");
						
					}
				});
				buscar();

			});


			/*
			--------------------------------------
			1.-			Validaciones
			--------------------------------------
			*/
			
			// Con ésta función, al presionar la tecla "Enter" se realiza la búsqueda 
			function validarEnter()
			{
				// Si se presionó la tecla "Enter" realizamos la búsqueda
				if(event.keyCode===13)
					buscar();
			}
			
			function validarSimboloSuma(entero){
				var key=window.event.keyCode;
				//Si la tecla presionada es 95 (valor ASCII del simbolo +)
				//el keycode es igual a 0 y no se escribe el valor de la tecla.
				if (key == 95)
				{
					window.event.keyCode=0;
				}
				
				if(key == 13 && entero == 1)
					buscar();
			}

			
			/*
			--------------------------------------
			2.-		Funciones de los Filtros
			--------------------------------------
			*/
			
			// Función para mostrar/ocultar los filtros de la pantalla				
			function toggleFiltros()
			{
				if(varToggleFiltros==true)
				{
					$("#divTblFiltros").show("blind");
					varToggleFiltros=false;
				}
				else
				{
					$("#divTblFiltros").hide("blind");
					varToggleFiltros=true;
				}
			}
			

			function buscar()
			{
				/*-------------------------------------------------------------------------------
				-- Marca del Cambio : WELL-JMQ-P-02-0271-13 Inicia la Modificacion   03/01/2014 -
				-------------------------------------------------------------------------------*/
				bandBusqueda=1;
				/*-------------------------------------------------------------------------------
				-- Marca del Cambio : WELL-JMQ-P-02-0271-13 Finaliza la Modificacion   03/01/2014 -
				-------------------------------------------------------------------------------*/
				// Mostramos la imagen "Cargando"
				$("#imgCargando").show("highlight");
				// Cambiamos el puntero del mouse a "Cargando"
				$("body").css("cursor","progress");
				// Realizamos la petición ajax
				$.getJSON("ajaxAdmBuscarCalificaciones"
				,{
					// Establecemos los parámetros
					 filtroIdAlumno 		:"1"
					
					// Los siguientes parámetros deben ir por default (si no se ponen truena la búsqueda)
					,sidx			:"nombre" 	// campo por el cual se ordenará la lista
					,sord			:"asc"			// orden de la lista
				}
				,function(datos)	// Función que se ejecuta cuando termina la petición ajax
				{	
					/*-------------------------------------------------------------------------------
					-- Marca del Cambio : WELL-JMQ-P-02-0271-13 Inicia la Modificacion   16/12/2013 -
					-------------------------------------------------------------------------------*/
					// Volvemos a cargar el grid
					$('#gridListaCalificaciones').trigger("reloadGrid",[{page:1}]);
					// Escondemos la imagen "Cargando"
					$("#imgCargando").hide("highlight");
					// Cambiamos el puntero del mouse a normal
					$("body").css("cursor","auto");
				}).error(function(){ 
						window.location = 'login.jsp';
					});
					/*-------------------------------------------------------------------------------
					-- Marca del Cambio : WELL-JMQ-P-02-0271-13 Finaliza la Modificacion 16/12/2013 -
					-------------------------------------------------------------------------------*/
			}
			
			/*
			---------------------------------------------------------------
			3.- Función que despliega el mensaje de la operación realizada
			---------------------------------------------------------------
			*/	
			
			function mostrarMensaje(request)
			{
				// Convertimos el responseText del request a jSON y obtenemos el mensaje (variable operResult del Action)
				var operResult = $.parseJSON(request.responseText).operResult;
				// Verificamos si es el mensaje es error para ponerle el estilo correspondiente
				if(operResult.search(/error/i) === -1)
					$('#labelMensaje').removeClass('labelMensajeError').addClass('labelMensajeSuccess');
				else // Si entra aquí el mensaje que se despliega es de error
					$('#labelMensaje').removeClass('labelMensajeSuccess').addClass('labelMensajeError');
					
				// Agregamos el mensaje
				$("#labelMensaje").html('&nbsp;&nbsp;'+operResult+'&nbsp;&nbsp;');
				// Le ponemos el efecto
				$('#labelMensaje').slideDown('slow').delay(1500).slideUp('slow');
				buscar();
			}

		</script>
	</head>  
	<body>
		<div id="tituloTab" >Calificaiones del alumno</div>
   		<br/>
		<div id="filtroGrid" class="divTblFiltrosRes" >
		<br/>		
			<!-- 	CON ESTA ETIQUETA DESPLEGAMOS EL RESULTADO DE LA OPERACIÓN EFECTUADA 	-->
			<label id="labelMensaje" ></label>
			<!-- 	A PARTIR DE AQUÍ SE CARGA LA TABLA DE USUARIOS POR MEDIO DE JQUERY -->		
			<sjg:grid
						id			="gridListaCalificaciones"			gridModel	="gridListaCalificaciones"
						href		="ajaxLlenarTablaCalificaciones"		dataType	="json"        
						caption		="Lista de Calificaciones"			altRows		="true"								
						pager		="true"							pagerInput	="false"
						pagerButtons="true"							rowList		="10, 20,30,40"
						rowNum		="10"							rownumbers	="true"
						navigator	="true"							viewrecords	="true"
						hidegrid	="false"						multiselect	="false"
						navigatorRefresh="false"					navigatorSearch="false"				
						resizable	="true"							editurl		="ajaxAbcCalificaciones" 
						navigatorAdd="false"
						navigatorEdit="false"
						navigatorDelete="false"
			>
				<sjg:gridColumn name="idCalificacion" key="true" title="Hidden" 		hidden="true" 			sortable="false" />
				<sjg:gridColumn name="idAlumno" title="Hidden" 		hidden="true" 			sortable="false" />
				
				<sjg:gridColumn id="materia" name="materia"			title="Materia"		index="materia"		sortable="true"	width="150"
								editable="true"	/>
				<sjg:gridColumn id="periodo1"  name="periodo1"	title="Periodo 1"  index="periodo1"	sortable="true"	width="150"/>
				<sjg:gridColumn id="periodo2"  name="periodo2"	title="Periodo 2"  index="periodo2"	sortable="true"	width="150"/>
				<sjg:gridColumn id="periodo3"  name="periodo3"	title="Periodo 3"  index="periodo3"	sortable="true"	width="150"/>																																
				<sjg:gridColumn id="calificacion"  name="calificacion"	title="Calificacion"  index="calificacion"	sortable="true"	width="150"/>
				
			</sjg:grid>
		</div>
  	</body>
</html>