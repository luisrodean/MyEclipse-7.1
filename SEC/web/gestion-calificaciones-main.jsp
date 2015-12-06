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
<html>
		<!--	SE REFERENCÍA UNA HOJA DE ESTILOS UTILIZADA POR Wellcom		
		<link rel="stylesheet" type="text/css" href="css/styles.css" ></link>-->
		<!-- 	SE HABILITA EL USO DE jQuery-UI Y SE ASIGNA EL TEMA	-->			
		<sj:head jqueryui="true" locale="es" jquerytheme="pepper-grinder" compressed="true" debug="true"></sj:head>
		<!-- 	SE AGREGAN LAS LIBRERIAS QUE SE UTILICEN PARA LOS EFECTOS VISUALES DE LA PÁGINA		-->	
		<script type="text/javascript" src="struts/js/base/jquery.effects.core.min.js?s2j=3.3.0"></script>
		<script type="text/javascript" src="struts/js/base/jquery.effects.highlight.min.js?s2j=3.3.0"></script>
		<script type="text/javascript" src="struts/js/base/jquery.effects.blind.min.js?s2j=3.3.0"></script>

		<base href="<%=basePath%>">    
    	<title>Gestión de Usuarios</title>
    	   
	   	<!--	TODO LO SIGUIENTE VIENE POR DEFAULT	(LE MODIFICAMOS ALGUNAS COSAS)	-->
		<meta http-equiv="cache-control" content="no-cache">
		<meta http-equiv="expires" content="0">    
		<meta http-equiv="keywords" content="banorte">
		<meta http-equiv="description" content="Página en la que se administran los usuarios de la aplicación">
				
		<!--  AQUI VA LA PARTE DE JAVASCRIPT (jQuery)-->
		<script type="text/javascript">
		var varToggleFiltros;
		var calificacion;

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
						
					}
				});

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
					$('#gridListaCalificaciones').trigger("reloadGrid");
				 }
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
					 filtroIdAlumno 		:escape($("#filtroIdAlumno").val())
					
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

			function EnvioABC(){			
				if(validarFormulario()){	
					nombre=$("#VabcNombre").val();
				
					$.getJSON("ajaxABC",{
						// Establecemos los parámetros
						 calificacion		:calificacion
					}).error(function() {
						window.location = 'login.jsp';
					});
					// Volvemos a cargar el grid
					$('#gridListaCalificaciones').trigger("reloadGrid");
				}
			}
			
			function poppupOpen(){
				$("#vista_abc").show();
				// Damos formato a la Ventana de Diálogo    
	    		$('#dialogo').dialog({
	    			title: operacion,
			        // Indica si la ventana se abre de forma automática
	        		autoOpen: true,
			        // Indica si la ventana es modal
	        		modal: true,
			        // Largo
			        width: 350,
	        		// Alto
	        		height: 200,
			        // Creamos los botones
	        		buttons: {
	           			'Aceptar': function() {
		                	EnvioABC();
		                	$(this).dialog( "close" );
	            		},
			          	Cerrar: function() {
	               			// Cerrar ventana de diálogo
	                		$(this).dialog( "close" );
	                		$("#vista_abc").hide();
	            		} 
	        		}
	    		});
			}

			function Formulario(oper){
			//asigna titulo al formulario
			operacion = oper;
			
			switch(operacion){		
				case 'Editar Calificacion':
					//Validar si hay una fila seleccionada
					if($("#gridListaCalificaciones").jqGrid('getGridParam','selrow')!=null){
					
						//recupera fila selecionada y datos de las columnas
						s = $("#gridListaCalificaciones").jqGrid('getGridParam','selrow');
						id=$("#gridListaCalificaciones").jqGrid('getCell',s,'id');
						calificacion=$("#gridListaCalificaciones").jqGrid('getCell',s,'calificacion');
					
						//asigna valores al formulario
						document.getElementById('VabcCalificacion').value=calificacion;
						
						//Abre ventana emergente
						poppupOpen();	
					
					}else{
						alert("Seleccione una materia");
					}
				break;
				}
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
<center>
		<div id="tituloTab" >Gestión de Calificaiones</div>
   		<br/>
   		 
   		<!-- DIV que contiene la tabla de filtros -->
   		<div id="divTblFiltros" class="divTblFiltros" >
   			<div id = "filtroBuscar">
				ID Alumno:		
				<input type="text" placeholder="12345..." id="filtroIdAlumno" maxlength="30" onkeypress="validarSimboloSuma(1);"></input>
			</div>
			<div id="filtroBuscar">
					<div id="boton">
						<input type="button" id="btnBuscar" value="Buscar" style="font-size: 14px;" onclick="javascript: buscar();"/>
					</div>
			</div>	
			
		</div>
		<div id="filtroGrid" class="divTblFiltrosRes" >
		<br/>		
			<!-- 	CON ESTA ETIQUETA DESPLEGAMOS EL RESULTADO DE LA OPERACIÓN EFECTUADA 	-->
			<label id="labelMensaje" ></label>
			<!-- 	A PARTIR DE AQUÍ SE CARGA LA TABLA DE USUARIOS POR MEDIO DE JQUERY -->		
			<sjg:grid
						id			="gridListaCalificaciones"			gridModel	="gridListaCalificaciones"
						href		="ajaxLlenarTablaCalificaciones"	dataType	="json"        
						caption		="Lista de Calificaciones"			altRows		="true"								
						pager		="false"							pagerInput	="false"
						pagerButtons="false"							rowList		="10, 20,30,40"
						rowNum		="10"								rownumbers	="true"
						navigator	="true"								viewrecords	="true"
						hidegrid	="false"							multiselect	="false"
						navigatorRefresh="false"						navigatorSearch="false"				
						resizable	="true"								navigatorAdd="false"
						navigatorDelete="false"							navigatorEdit="false"
						navigatorExtraButtons	= 
						"{
							Editar : { 
								title : 'Editar Calificacion', 
								icon: 'ui-icon-pencil', 
								onclick: function() { Formulario('Editar Alumno') }
								} 
						}"	>
						
				<sjg:gridColumn name="id_calificacion" key="true" title="Hidden" 		hidden="true" 			sortable="false" />
				<sjg:gridColumn name="ciclo" title="Hidden" 		hidden="true" 			sortable="false" />
				<sjg:gridColumn id="grupo" name="grupo"			title="Grupo"		index="grupo"		sortable="true"	width="50"/>
				<sjg:gridColumn id="nombre"  name="nombre"	title="Nombre"  index="nombre"	sortable="true"	width="100"
								editable="true"/>
				<sjg:gridColumn id="apellido_paterno"  name="apellido_paterno"	title="Apellido Paterno"  index="apellido_paterno"	sortable="true"	width="100"
								editable="true"/>
				<sjg:gridColumn id="apellido_materno"  name="apellido_materno"	title="Apellido Materno"  index="apellido_materno"	sortable="true"	width="100"
								editable="true"/>
				<sjg:gridColumn id="nombre_materia"  name="nombre_materia"	title="Materia"  index="nombre_materia"	sortable="true"	width="100"
								editable="true"/>
				<sjg:gridColumn id="periodo"  name="periodo"	title="Periodo"  index="periodo"	sortable="true"	width="50"
								editable="true"/>
				<sjg:gridColumn id="calificacion"  name="calificacion"	title="Calificacion"  index="calificacion"	sortable="true"	width="70"
								editable="true"/>
								
			</sjg:grid>
		</div>
		
			<sj:dialog 
		id="dialogo"
       	autoOpen="false"
       	resizable="false">
        <!-- 	CON ESTA ETIQUETA SE DESPLIEGA UNA VISTA PARA ADMINISTRAR UN ALUMNO-->
        <div id="vista_abc" style="display:none;">
			<br/>
			Calificacion				<input type="text" id="VabcCalificacion" name="VabcCalificacion" maxlength="30" /> <br/>
		</div>
	</sj:dialog>
	
</center>
  	
</html>