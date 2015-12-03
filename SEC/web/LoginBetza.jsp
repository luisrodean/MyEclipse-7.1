<%
/*###############################################################################
# Nombre del Programa :  login.jsp                                              #
# Autor               :  JOAQUIN MOJICA Q.										#
# Compania            :  WELLCOM S.A. DE C.V.                                   #
# Proyecto/Procliente :  			                 	   FECHA:12/03/2012     #
# Descripcion General :	 Pagina para accesar                  					#
#                                                                               #
# Programa Dependiente:  web.xml                                                #
# Programa Subsecuente:                                                         #
# Cond. de ejecucion  :  Acceder al sistema                                     #
#                                                                               #
#                                                                               #
# Dias de ejecucion   :  A Peticion del web, se pueden ejecutar n instancias  	#
#################################################################################
#								MODIFICACIONES                                  #
# Autor               :                                                         #
# Compania            :                                                         #
# Proyecto/Procliente :                              Fecha:                     #
# ModificaciÃ³n        :                                                       	#
#-----------------------------------------------------------------------------  #
# Numero de Parametros: 0                                                       #
###############################################################################*/
%>
<%@page contentType="text/html" errorPage="errorPage.jsp"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"  "http://www.w3.org/TR/html4/loose.dtd">
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
        <meta http-equiv="Cache-Control" content="no-cache"/>
        <!-- 			Importamos las css del login 			-->
        <link href="css/login.css" rel="stylesheet" type="text/css">
		<script type="text/javascript" src="js/webtoolkit.base64.js"></script>
        <script type="text/javascript" src="js/jquery-1.10.2.js"></script>
        <script type="text/javascript" src="js/jquery-1.9.1.min.js"></script>
        <script type="text/javascript" src="js/jquery-ui-1.10.4.js"></script>
        <title>SEC</title>
    </head>
    
    <script type="text/javascript"> 
		function btnAceptar(){
  		alert("Acceso con exito");
		// Realizamos la petición ajax
				$.getJSON("ajaxlogin"
				,{
					// Establecemos los parámetros
					nombre: $("#txtfNombre").val(),
					apPat: $("#txtfApPat").val(),
					apMat: $("#txtfApMat").val(),
					escuela: $("#txtfEscuela").val()
				}
				,function(datos)	// Función que se ejecuta cuando termina la petición ajax
				{	
					// Escondemos la imagen "Cargando"
					$("#txtfNombre").hide();
					$("#txtfApPat").hide();
					$("#txtfApMat").hide();
					$("#txtfEscuela").hide();
				}).error(function(){ 
						window.location = 'LoginBetza.jsp';
					});
		
		}
		$(document).ajaxSuccess(function(event, request, settings)
				{				
					if(settings.url.match('ajaxlogin')!=null){
						mostrarMensaje(request);
					}
				});
		
		
		
		
					function mostrarMensaje(request)
			{
				
				// Convertimos el responseText del request a jSON y obtenemos el mensaje (variable operResult del Action)
				var operResult = $.parseJSON(request.responseText).operResult;
				// Verificamos si es el mensaje es error para ponerle el estilo correspondiente
				if(operResult.search(/error/i) === -1)
					$('#msj').removeClass('labelMensajeError').addClass('labelMensajeSuccess');
				else // Si entra aquí el mensaje que se despliega es de error
					$('#msj').removeClass('labelMensajeSuccess').addClass('labelMensajeError');
					
				// Agregamos el mensaje
				$("#msj").html('&nbsp;&nbsp;'+operResult+'&nbsp;&nbsp;');
				// Le ponemos el efecto
				$('#msj').slideDown('slow').delay(1500).slideUp('slow');
			}
		
    </script>
    <body onload="document.getElementById('txtfNombre').focus();">
		
    	<div id="login">
      		
      		<div id="head_login"></div>
			<div id="barRojo"><font face = "verdana">" BIENVENIDO "</font></div>
			<br/>
			
			<div id="contenido">	
			<div id= "div_form">	
				<form method="post" id = "formLogin">
					<label id="msj">Ingresa tus datos</label>						  
			  		<div >
			    			<input name="txtfNombre" placeholder="Nombre" type="text" id="txtfNombre" size="20" maxlength="16">  
	          		</div>
	          								  
			  		<div align="center">
			    			<input name="txtfApPat" placeholder="Apellido Paterno" type="text" id="txtfApPat" size="20" maxlength="16">				
	          		</div>
	          		<div align="center">
			    			<input name="txtfApMat" placeholder="Apellido Materno" type="text" id="txtfApMat" size="20" maxlength="16">				
	          		</div>
	          		<div align="center">
			    			<input name="txtfEscuela" placeholder="Escuela" type="text" id="txtfEscuela" size="20" maxlength="16">				
	          		</div>
	          		<div align="center">
						<input type="button" name="btnAccept" id="btnAccept"  onclick="btnAceptar()" value="Aceptar">
					</div>
				  					
				</form>	
				</div>
     		</div>
			
    	</div>
    </body>
</html>