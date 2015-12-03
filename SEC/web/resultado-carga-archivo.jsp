<%
/*###############################################################################
# Nombre del Programa :  														#
# Autor               :  JOAQUIN MOJICA Q. & Wellcom team						#
# Compania            :  WELLCOM S.A. DE C.V.                                   #
# Proyecto/Procliente :  			                	   FECHA:14/05/2012     #
# Descripcion General :	 														#
#                                                                               #
# Programa Dependiente:    	                        							#
# Programa Subsecuente:                                                         #
# Cond. de ejecucion  :  Acceder al sistema                                     #
#                                                                               #
#                                                                               #
# Dias de ejecucion   :    														#
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

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
 "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib prefix="s" uri="/struts-tags" %>
<html>
  <head>
      <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
      <title>JSP Page</title>
      <base href="<%=basePath%>">
  	</head>
  		<body>
  			<div>
  				<table>
  					<tr>
  						<td>
      						<font size="2" face="arial">Nombre del Archivo: <s:property value="archivoFileName"/><br>
      						Total Registros: <s:property value="registros"/><br>
      						Insertados: <s:property value="registrosSi"/><br>
      						No Insertados: <s:property value="registrosNo"/><br></font>
      						<!--  Duplicados: <s:property value="duplicados"/><br> -->
  						</td>
  					</tr>
  				</table>
  			</div>
  		</body>
</html>