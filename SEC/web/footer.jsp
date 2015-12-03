<%
/*###############################################################################
# Nombre del Programa :  footer.jsp					                            #
# Autor               :  JOAQUIN MOJICA Q.				                 		#
# Compania            :  WELLCOM S.A. DE C.V.                                   #
# Proyecto/Procliente :    				               	   FECHA:12/12/2012     #
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
<%@ taglib prefix="sj"	uri="/struts-jquery-tags"%>
<%
	/*------------------------------------------------------------------------------------
	-- Marca del Cambio : WELL-JMQ-P-02-0271-13 Inicia la Modificación   14/04/2014 --
	------------------------------------------------------------------------------------*/
	String usuarioActivo = (String)session.getAttribute("usuarioActivo");
	String valorRol = null;
	if(session.getAttribute("rolAccVal")!=null)
		valorRol = session.getAttribute("rolAccVal").toString();
	/*------------------------------------------------------------------------------------
	-- Marca del Cambio : WELL-JMQ-P-02-0271-13 Finaliza la Modificación   14/04/2014 --
	------------------------------------------------------------------------------------*/
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"  "http://www.w3.org/TR/html4/loose.dtd">
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
        <meta http-equiv="Cache-Control" content="no-cache"/>
        <!-- 			Importamos las css del login 			-->
        <link href="css/login.css" rel="stylesheet" type="text/css">
        <!-- 	SE HABILITA EL USO DE jQuery-UI Y SE ASIGNA EL TEMA	-->			
		<sj:head jqueryui="true" locale="es" jquerytheme="pepper-grinder" compressed="true" debug="true"></sj:head>
        <!--	SE REFERENCÍA UNA HOJA DE ESTILOS UTILIZADA PARA MATERIAL	-->
		<script type="text/javascript" src="js/materialize.min.js"></script>
		<link type="text/css" rel="stylesheet" href="css/materialize.min.css"  media="screen,projection"/>	
		
        <title>SEC</title>
    </head>

<script>
	/*------------------------------------------------------------------------------------
	-- Marca del Cambio : WELL-JMQ-P-02-0271-13 Inicia la Modificación   14/04/2014 --
	------------------------------------------------------------------------------------*/
	function validarUsuario(){
		var valorRol=<%=valorRol%>
		if(valorRol==3){
			
		}else{
			alert("Se necesitan permisos de Administrador\npara acceder a estas opciones");
			return;
		 }	
	}
</script>


<div>
	<table width="100%" cellspacing="0" cellpadding="0">
		<tr>
			<td id="lp" />
		</tr>
	</table>
</div>
<body>

	<footer class="page-footer grey darken-3">			
		<div class="footer-copyright">
        	<div class="container">
            		<label><font align="left"> © 2015 Copyright Text</font></label><br/>
            		<label id="labelUsuario"><font size="3" face="arial" color="white" align="right"><b>Usuario Actual</b> <span style="font-weight:bold;color:#FFFFFF"><i>::  <%=usuarioActivo%>  :: </i></span></font></label>
            		            	<a class="grey-text text-lighten-4 right" href="#!"></a>
        	</div>
    	</div>
    </footer>

</body>	
</html>