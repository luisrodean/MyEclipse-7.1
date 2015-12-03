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
        <title>SEC</title>
    </head>
    <script type="text/javascript">
    	function validar(e) {
    		tecla = (document.all) ? e.keyCode : e.which;
    		if (tecla == 8) return true;
    		patron = new RegExp("^[a-zA-Z0-9]");
    		te = String.fromCharCode(tecla);
    		return patron.test(te);
    	}
		
    </script>
    <body onload="document.getElementById('txtfLogin').focus();">
		
    	<div id="login">
      		
      		<div id="head_login"></div>
			<div id="barRojo"><font face = "verdana">Ingrese sus datos requeridos</font></div>
			<div id="contenido">	
			<div id= "div_form">	
				<form action="login" method="post" id = "formLogin">
									  
			  		<div >
			    			<input name="txtfLogin" placeholder="Usuario" type="text" id="txtfLogin" size="20" maxlength="16" onkeypress="return validar(event)">  
	          		</div>
	          								  
			  		<div align="center">
			    					<input name="txtfPwd" placeholder="Contraseña" type="password" id="txtfPwd" size="20" maxlength="16" onkeypress="return validar(event)" autocomplete="off" >				
	          		</div>
	          		<div align="center">
						<input type="submit" name="btnAccept" id="btnAccept" value="Aceptar">
					</div>
		  					<%
		  					if(	request.getAttribute("bloqueo")!=null && 
		  						request.getAttribute("bloqueo").toString().equalsIgnoreCase("false")) 
		  					{%>
		  							Usuario Bloqueado. Favor de esperar <%=request.getAttribute("tiempoRestante")%> min.	  							
		  					
		  					<%} %>
		  					<%if(	session.getAttribute("norole")!=null && 
		  						session.getAttribute("norole").toString().equalsIgnoreCase("true")) 
		  					{%>
		  							El usuario no posee roles de acceso al sistema.Por favor contate al Administrador.	  							
		  					
		  					<%} %>
		  					<%
		  					if(	request.getAttribute("validacion")!=null && 
		  						request.getAttribute("validacion").toString().equalsIgnoreCase("false") && 	request.getAttribute("bloqueo")!=null && 
		  						request.getAttribute("bloqueo").toString().equalsIgnoreCase("true")) 
		  					{%>
		  							Ingrese un usuario y/o contraseña válidos.		  							
		  					
		  					<%} %>
		  					
				</form>	
				</div>
     		</div>
			
    	</div>
    </body>
</html>