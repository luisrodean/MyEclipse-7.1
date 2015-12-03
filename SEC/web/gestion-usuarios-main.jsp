<%
/*###############################################################################
# Nombre del Programa :  							                            #
# Autor               :  JOAQUIN MOJICA Q. & Wellcom team						#
# Compania            :  WELLCOM S.A. DE C.V.                                   #
# Proyecto/Procliente :    				               	   FECHA:05/07/2012     #
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
# Modificaci�n        :                                                         #
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
		<!--	SE REFERENC�A UNA HOJA DE ESTILOS UTILIZADA POR Wellcom		-->
		<link rel="stylesheet" type="text/css" href="css/styles.css" ></link>	
		<!-- 	SE HABILITA EL USO DE jQuery-UI Y SE ASIGNA EL TEMA	-->			
		<sj:head jqueryui="true" locale="es" jquerytheme="pepper-grinder" compressed="true" debug="true"></sj:head>
		<!-- 	SE AGREGAN LAS LIBRERIAS QUE SE UTILICEN PARA LOS EFECTOS VISUALES DE LA P�GINA		-->	
		<script type="text/javascript" src="struts/js/base/jquery.effects.core.min.js?s2j=3.3.0"></script>
		<script type="text/javascript" src="struts/js/base/jquery.effects.highlight.min.js?s2j=3.3.0"></script>
		<script type="text/javascript" src="struts/js/base/jquery.effects.blind.min.js?s2j=3.3.0"></script>

		<base href="<%=basePath%>">    
    	<title>Gesti�n de Usuarios</title>
    	   
	   	<!--	TODO LO SIGUIENTE VIENE POR DEFAULT	(LE MODIFICAMOS ALGUNAS COSAS)	-->
		<meta http-equiv="cache-control" content="no-cache">
		<meta http-equiv="expires" content="0">    
		<meta http-equiv="keywords" content="banorte">
		<meta http-equiv="description" content="P�gina en la que se administran los usuarios de la aplicaci�n">
				
		<!--  AQUI VA LA PARTE DE JAVASCRIPT (jQuery)-->
		<script type="text/javascript">
		var varToggleFiltros;

		/*-------------------------------------------------------------------------------
		-- Marca del Cambio : WELL-JMQ-P-02-0271-13 Inicia la Modificacion   03/01/2014 -
		-------------------------------------------------------------------------------*/
		var bandBusqueda=0;
		/*-------------------------------------------------------------------------------
		-- Marca del Cambio : WELL-JMQ-P-02-0271-13 Finaliza la Modificacion 03/01/2014 -
		-------------------------------------------------------------------------------*/
		
			$(document).ready(function() 
			{	
				//$('#gridListaUsuarios').trigger("reloadGrid");		
				/*	Este m�todo se ejecuta cada que una petici�n ajax termina bien ("success")	*/				
				$("#labelMensaje").ajaxSuccess(function(event, request, settings)
				{				
					//alert(settings.url);
					if(settings.url.match('ajaxAbcUsuarios')!=null){
						mostrarMensaje(request);
						//setInterval(function(){location.reload();} ,3000);
						//buscar();
						//$('#gridListaUsuarios').trigger("reloadGrid");
						
					}
				});

			});


			/*
			--------------------------------------
			1.-			Validaciones
			--------------------------------------
			*/
			
			// Con �sta funci�n, al presionar la tecla "Enter" se realiza la b�squeda 
			function validarEnter()
			{
				// Si se presion� la tecla "Enter" realizamos la b�squeda
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

			/*-------------------------------------------------------------------------------
			-- Marca del Cambio : WELL-JMQ-P-02-0271-13 Inicia la Modificacion   03/01/2014 -
			-------------------------------------------------------------------------------*/
			function validarReload(){
				var arrayFiltros = new Array();
				var arrayGrid = new Array();
				var bandRecharge=0;
				var i;
				
				arrayFiltros[0]=$('#filtroNombre').val();
				arrayFiltros[1]=$('#filtroLogin').val();
				arrayFiltros[2]=$('#filtroMail').val();
				arrayGrid[0]=$('#nombre').val();
				arrayGrid[1]=$('#usuario').val();
				arrayGrid[2]=$('#mail').val();
				
				for(i=0; i < 3; i++){
					if(arrayFiltros[i]!="" && bandBusqueda==1){
						if(arrayGrid[i].indexOf(arrayFiltros[i]) == -1){
							bandRecharge=1;
							break;
						}
					}
				}
				
				if(bandRecharge==1){
					buscar();
					bandRecharge=0;
				}else{
					$('#gridListaUsuarios').trigger("reloadGrid");
				 }
			}
			/*-------------------------------------------------------------------------------
			-- Marca del Cambio : WELL-JMQ-P-02-0271-13 Finaliza la Modificacion   03/01/2014 -
			-------------------------------------------------------------------------------*/
			
			
			/*
			--------------------------------------
			2.-		Funciones de los Filtros
			--------------------------------------
			*/
			
			// Funci�n para mostrar/ocultar los filtros de la pantalla				
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
				// Realizamos la petici�n ajax
				$.getJSON("ajaxAdmBuscarUsuarios"
				,{
					// Establecemos los par�metros
					 filtroNombre		:escape($("#filtroNombre").val())
					,filtroLogin		:escape($("#filtroLogin").val())
					,filtroMail			:escape($("#filtroMail").val())
					
					// Los siguientes par�metros deben ir por default (si no se ponen truena la b�squeda)
					,sidx			:"nombre" 	// campo por el cual se ordenar� la lista
					,sord			:"asc"			// orden de la lista
				}
				,function(datos)	// Funci�n que se ejecuta cuando termina la petici�n ajax
				{	
					/*-------------------------------------------------------------------------------
					-- Marca del Cambio : WELL-JMQ-P-02-0271-13 Inicia la Modificacion   16/12/2013 -
					-------------------------------------------------------------------------------*/
					// Volvemos a cargar el grid
					$('#gridListaUsuarios').trigger("reloadGrid",[{page:1}]);
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
			3.- Funci�n que despliega el mensaje de la operaci�n realizada
			---------------------------------------------------------------
			*/	
			
			function mostrarMensaje(request)
			{
				// Convertimos el responseText del request a jSON y obtenemos el mensaje (variable operResult del Action)
				var operResult = $.parseJSON(request.responseText).operResult;
				// Verificamos si es el mensaje es error para ponerle el estilo correspondiente
				if(operResult.search(/error/i) === -1)
					$('#labelMensaje').removeClass('labelMensajeError').addClass('labelMensajeSuccess');
				else // Si entra aqu� el mensaje que se despliega es de error
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
		<div id="tituloTab" >Gesti�n de Usuarios</div>
   		<br/>
   		 
   		<!-- DIV que contiene la tabla de filtros -->
   		<div id="divTblFiltros" class="divTblFiltros" >
   			<div id = "filtroBuscar">
						
				<input type="text" placeholder="Nombre" id="filtroNombre" maxlength="30" onkeypress="validarSimboloSuma(1);"></input> 
						
				<input type="text" placeholder="Login" id="filtroLogin" maxlength="20" onkeypress="validarSimboloSuma(1);"/> 
						
				<input type="text" placeholder="E-mail" id="filtroMail"   maxlength="50"></input>
			</div>
			<div id="filtroBuscar">
					<div id="boton">
						<input type="button" id="btnBuscar" value="Buscar" style="font-size: 14px;" onclick="javascript: buscar();"/>
					</div>
			</div>	
			
		</div>
		<div id="filtroGrid" class="divTblFiltrosRes" >
		<br/>		
			<!-- 	CON ESTA ETIQUETA DESPLEGAMOS EL RESULTADO DE LA OPERACI�N EFECTUADA 	-->
			<label id="labelMensaje" ></label>
			<!-- 	A PARTIR DE AQU� SE CARGA LA TABLA DE USUARIOS POR MEDIO DE JQUERY -->		
			<sjg:grid
						id			="gridListaUsuarios"			gridModel	="gridListaUsuarios"
						href		="ajaxLlenarTablaUsuarios"		dataType	="json"        
						caption		="Lista de Usuarios"			altRows		="true"								
						pager		="true"							pagerInput	="false"
						pagerButtons="true"							rowList		="10, 20,30,40"
						rowNum		="10"							rownumbers	="true"
						navigator	="true"							viewrecords	="true"
						hidegrid	="false"						multiselect	="true"
						navigatorRefresh="false"					navigatorSearch="false"				
						resizable	="true"							editurl		="ajaxAbcUsuarios" 
						navigatorAddOptions="{closeAfterAdd:true, closeAfterSubmit:true}"
						navigatorEditOptions="{reloadAfterSubmit:true}"
						navigatorExtraButtons	= 
						"{
							Filtros : { 
								title : 'Mostrar/Ocultar Filtros', 
								icon: 'ui-icon-search', 
								onclick: function() { toggleFiltros() }
								} 
						}"
			>
				<sjg:gridColumn name="idUsuario" key="true" title="Hidden" 		hidden="true" 			sortable="false" />
				
				<sjg:gridColumn id="usuario" name="usuario"			title="Login"		index="usuario"		sortable="true"	width="150"
								editable="true"		editrules="{required:true}" editoptions="{maxlength:20, dataInit: function(elem){
																																		$(elem).bind('keypress', function(e) {
																																			return validarSimboloSuma(0)
																																		})
																																		$(elem).bind('keyup', function() {
																																			javascript:this.value=this.value.toLowerCase()
																																		})}}"/>
																																
				<sjg:gridColumn name="password"	title="Contrase�a"  index="password"	sortable="true"	width="150"
								edittype="password" editable="true"		editrules="{required:true,edithidden:true}" hidden = "true" editoptions="{maxlength:10, dataInit: function(elem){$(elem).bind('keypress', function(e) {return validarSimboloSuma(0)})}}"/>
				
			</sjg:grid>
		</div>
  	</body>
</html>