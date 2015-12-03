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
    	<title>Gestión de Alumnos</title>
    	   
	   	<!--	TODO LO SIGUIENTE VIENE POR DEFAULT	(LE MODIFICAMOS ALGUNAS COSAS)	-->
		<meta http-equiv="cache-control" content="no-cache">
		<meta http-equiv="expires" content="0">    
		<meta http-equiv="keywords" content="banorte">
		<meta http-equiv="description" content="Página en la que se administran los alumnos de la aplicación">
				
		<!--  AQUI VA LA PARTE DE JAVASCRIPT (jQuery)-->
		<script type="text/javascript">

		var operacion;
		var varToggleFiltros;
		var varToggleDatos;
		var id;
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
					/*	Este método se ejecuta cada que una petición ajax termina bien ("success")	*/				
					$("#labelMensaje").ajaxSuccess(function(event, request, settings)
					{				
						if(settings.url.match('ajaxAbcAlumnos')!=null){
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

			/*-------------------------------------------------------------------------------
			-- Marca del Cambio : WELL-JMQ-P-02-0271-13 Inicia la Modificacion   03/01/2014 -
			-------------------------------------------------------------------------------*/
			function validarReload(){
				var arrayFiltros = new Array();
				var arrayGrid = new Array();
				var bandRecharge=0;
				var i;
				
				arrayFiltros[0]=$('#filtroNombre').val();
				arrayFiltros[1]=$('#filtroApellidoP').val();
				arrayFiltros[2]=$('#filtroApellidoM').val();
				arrayFiltros[3]=$('#filtroEscuela').val();
				arrayGrid[0]=$('#nombre').val();
				arrayGrid[1]=$('#apellidoP').val();
				arrayGrid[2]=$('#apellidoM').val();
				arrayGrid[3]=$('#escuela').val();

				//Lectura de arreglos
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
					$('#gridListaAlumno').trigger("reloadGrid");
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

			// Función para mostrar/ocultar grid y campos de texto	
			function Mostrar_Ocultar()
			{
				if(varToggleDatos==true)
				{
					$("#divTblDatos").hide("blind");
					$("#filtroGrid").show("blind");
					$("#divTblFiltros").show("blind");
					varToggleDatos=false;	
				}
				else
				{			
					$("#divTblDatos").show("blind");
					$("#filtroGrid").hide("blind");
					$("#divTblFiltros").hide("blind");
					varToggleDatos=true;
				}
			}
			
			// Función que se ejecuta al presionar el boton btnbuscar y busca filtros de la pantalla				
			function buscar()
			{
				bandBusqueda=1;
	
				// Mostramos la imagen "Cargando"
				$("#imgCargando").show("highlight");
				// Cambiamos el puntero del mouse a "Cargando"
				$("body").css("cursor","progress");
				// Realizamos la petición ajax
				$.getJSON("ajaxAdmBuscarAlumnos"
				,{
					// Establecemos los parámetros en el divFiltros
					 filtroId   			:escape($("#filtroId").val())
					,filtroNombre			:escape($("#filtroNombre").val())
					,filtroApellidoP		:escape($("#filtroApellidoP").val())
					,filtroApellidoM		:escape($("#filtroApellidoM").val())
					,filtroEscuela			:escape($("#filtroEscuela").val())
					
					// Los siguientes parámetros deben ir por default (si no se ponen truena la búsqueda)
					,sidx			:"nombre" 	// campo por el cual se ordenará la lista
					,sord			:"asc"	    // orden de la lista
				
			}
				,function(datos)	// Función que se ejecuta cuando termina la petición ajax
				{	
					// Volvemos a cargar el grid
					$('#gridListaAlumno').trigger("reloadGrid",[{page:1}]);
					// Escondemos la imagen "Cargando"
					$("#imgCargando").hide("highlight");
					// Cambiamos el puntero del mouse a normal
					$("body").css("cursor","auto");

					//En caso de que se ejecute un error, envia al login
				}).error(function(){ 
						window.location = 'login.jsp';
					});
			}


			/*-------------------------------------------------------------------------------
			-- Metodo que se ejecuta al presionar culquier boton del grid y envia el tipo de operacion 
			-en accion--
			-------------------------------------------------------------------------------*/

			function Tabla(accion){
				//Asignar el tipo de operacion, segun el boton pulsado del grid
				operacion=accion;
				
					switch(operacion){
					
					//Condicion que se ejecuta al presionar Boton de agregar
					case 'Agregar':
						//limpia campos
						document.getElementById('txtNombre').value='';
						document.getElementById('txtApellidoP').value='';	
						document.getElementById('txtApellidoM').value='';
						document.getElementById('txtEscuela').value='';
						//Oculta grid y filtros, y muestra formulario(campos de texto) 
						Mostrar_Ocultar();
						break;

					//Condicion que se ejecuta al presionar Boton de actualizar y eliminar
					case 'Actualizar': case 'Eliminar':
							//Valida que una fila ha sido seleccionada
							if($("#gridListaAlumno").jqGrid('getGridParam','selrow')!=null){
								//Oculta grid y tabla filtros, y muestra formulario(campos de texto) 
								Mostrar_Ocultar();
								//Recupera valores del grid
								var s = $("#gridListaAlumno").jqGrid('getGridParam','selrow');
						  		   id = $("#gridListaAlumno").jqGrid('getCell',s,'id');
								var nombre=$("#gridListaAlumno").jqGrid('getCell',s,'nombre');
								var apellidoP=$("#gridListaAlumno").jqGrid('getCell',s,'apellidoP');
								var apellidoM=$("#gridListaAlumno").jqGrid('getCell',s,'apellidoM');
								var escuela=$("#gridListaAlumno").jqGrid('getCell',s,'escuela');
								
							//asigna valores del grid a campos de texto
								document.getElementById('txtNombre').value=nombre;
								document.getElementById('txtApellidoP').value=apellidoP;	
								document.getElementById('txtApellidoM').value=apellidoM;	
								document.getElementById('txtEscuela').value=escuela;
								}else{	
									//En caso de que el usuario no seleccione ningun registro se envia msj informandole
									alert("Selecciona un elemento de la tabla");
									}
							break;
							}
					}

			/*-------------------------------------------------------------------------------
			-- 			//Metodo que se ejecuta al presionar el boton aceptar del formulario(campos de texto) -
			-------------------------------------------------------------------------------*/
			function btnAceptar(){
				//Valida que ninguno de los campos se encuentren vacio
				if($("#txtNombre").val()=='' || $("#txtApellidoP").val()=='' || $("#txtApellidoM").val()=='' || $("#txtEscuela").val()==''){
					alert("Campo vacio, por favor acompleta la informacion");
					}else{
						//Si todos los campos contienen informacion los recoge y envia al ajaxAbcAlumnos
					$.getJSON("ajaxAbcAlumnos"
						,{
							// Establecemos los parámetros
							id:id
							,operacion:operacion	
							,nombre			:escape($("#txtNombre").val())
							,apellidoP		:escape($("#txtApellidoP").val())
							,apellidoM		:escape($("#txtApellidoM").val())
							,escuela		:escape($("#txtEscuela").val())
					},function(datos)	// Función que se ejecuta cuando termina la petición ajax
					{	
						//Muestra grid y oculta tabla  
						Mostrar_Ocultar();
						// Volvemos a cargar el grid
						$('#gridListaAlumno').trigger("reloadGrid",[{page:1}]);
						// Escondemos la imagen "Cargando"
						$("#imgCargando").hide("highlight");
						// Cambiamos el puntero del mouse a normal
						$("body").css("cursor","auto");
					}).error(function(){ 
							window.location = 'login.jsp';
						});
				}
			}
		    //	---------------------------------------------------------------
			//   3.- Función que despliega el mensaje de la operación realizada
			//  ---------------------------------------------------------------
			
			
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
		<div id="tituloTab" >Gestión de Alumnos</div>
   		<br/>
   		 
   		<!-- DIV que contiene la tabla de filtros -->
   		<div id="divTblFiltros" class="divTblFiltros" >
   			<div id = "filtroBuscar">
						
				<input type="text" placeholder="Nombre" id="filtroNombre" maxlength="30" onkeypress="validarSimboloSuma(1);"></input> 
						
				<input type="text" placeholder="Apellido Paterno" id="filtroApellidoP" maxlength="20" onkeypress="validarSimboloSuma(1);"/> 
						
				<input type="text" placeholder="Apellido Materno" id="filtroApellidoM"   maxlength="50"></input>
				
				<input type="text" placeholder="Escuela" id="filtroEscuela"   maxlength="50"></input>
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
						id			="gridListaAlumno"				gridModel	="gridListaAlumno"
						href		="ajaxLlenarTablaAlumnos"		dataType	="json"        
						caption		="Lista de Alumnos"  			altRows		="true"								
						pager		="true"							pagerInput	="false"
						pagerButtons="true"							rowList		="10,20,30,40"
						rowNum		="10"							rownumbers	="true"
						navigator	="true"							viewrecords	="true"
						hidegrid	="false"						multiselect	="true"
						navigatorRefresh="false"					navigatorSearch="false"				
						resizable	="true"							navigatorAdd ="false" 
						navigatorDelete="false"						navigatorEdit="false"
						navigatorExtraButtons	= 
						"{
							Agregar : { 
								title : 'Agregar Alumno', 
								icon: 'ui-icon-check', 
								onclick: function() { Tabla('Agregar') }
								} 
								,
							Eliminar : { 
								title : 'Eliminar Alumno', 
								icon: 'ui-icon-closethick', 
								onclick: function() { Tabla('Eliminar') }
								} 
								,
							Actualizar : { 
								title : 'Actualizar Alumno', 
								icon: 'ui-icon-pencil', 
								onclick: function() { Tabla('Actualizar') }
								}
								,
							Filtros : { 
								title : 'Mostrar/Ocultar Filtros', 
								icon: 'ui-icon-search', 
								onclick: function() { toggleFiltros() }
								} 
						}"
			>
				<sjg:gridColumn id="id" name="id" key="true"  title="ID_Alumno" index="id"	hidden="true" 			sortable="true" />
				 
				<sjg:gridColumn id="nombre" name="nombre"	 title="Nombre"		index="nombre"		sortable="true"	width="150"
								editable="true"		editrules="{required:true}" editoptions="{maxlength:20, dataInit: function(elem){
																																		$(elem).bind('keypress', function(e) {
																																			return validarSimboloSuma(0)
																																		})
																																		$(elem).bind('keyup', function() {
																																			javascript:this.value=this.value.toLowerCase()
																																		})}}"/>
																																
				  <sjg:gridColumn id="apellidoP" name="apellidoP" index="apellidoP" title="Apellido Paterno" sortable="true"/>
				  <sjg:gridColumn id="apellidoM" name="apellidoM" index="apellidoM" title="Apellido Materno" sortable="true"/>
				  <sjg:gridColumn id="escuela" name="escuela" index="escuela" title="Escuela" sortable="true"/>
				
							</sjg:grid>
		</div>
		
		
		

   		<!-- DIV que contiene datos del alumno -->
      		<div id="divTblDatos" class="divTblDatos" >
   			<div id = "filtroBuscar">
			<label id="Tabla" ></label>
				<input type="text" placeholder="Nombre" id="txtNombre" maxlength="30"></input> 
						<br/>
				<input type="text" placeholder="Apellido Paterno" id="txtApellidoP" maxlength="20" /> 
						<br/>
				<input type="text" placeholder="Apellido Materno" id="txtApellidoM"   maxlength="50"></input>
						<br/>
				<input type="text" placeholder="Escuela" id="txtEscuela"   maxlength="50"></input>
						<br/>
			</div>
			<div id="filtroBuscar">
					<div id="boton">
						<input type="button" id="btnAceptar" value="Aceptar" style="font-size: 14px;" onclick="javascript: btnAceptar()"/>
						<input type="button" id="btnCancelar" value="Cancelar" style="font-size: 14px;" onclick="javascript: Mostrar_Ocultar()"/>
					</div>
			</div>	
			
		</div>
  	</body>
</html>