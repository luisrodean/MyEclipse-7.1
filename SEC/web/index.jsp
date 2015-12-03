<jsp:useBean id="ga" scope="session" class="com.wellcom.banorte.abwportal.GrantAccess"/>
<%@ taglib prefix="sj"	uri="/struts-jquery-tags"%>
<%
	System.out.println("----------------------->Login");
	 if(!ga.AccessGranted(session, "grantAccess"))  
	     response.sendRedirect("login.jsp");
	     
	 session.setAttribute("pos","a0");	    		
	 
%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">

<html >
<head>
 	<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1"/>
 	<!-- 	SE HABILITA EL USO DE jQuery-UI Y SE ASIGNA EL TEMA	-->			
		<sj:head jqueryui="true" locale="es" jquerytheme="pepper-grinder" compressed="true" debug="true"></sj:head>
<!--	SE REFERENC�A UNA HOJA DE ESTILOS UTILIZADA PARA MATERIAL	-->
		<script type="text/javascript" src="js/materialize.min.js"></script>
		<link type="text/css" rel="stylesheet" href="css/materialize.min.css"  media="screen,projection"/>	
 	
	<link href="css/styles.css" rel="stylesheet" type="text/css"/>
	<title>SEC</title>
</head>
  
	<body style="margin: 0px; padding: 0px;">
  		<div id="wrapper">
	  		<jsp:include page="header.jsp"/>
	    	<table style="margin: 0px;padding:0px;">
	        	<tr>
	        		<td id="leftPanel" valign="top"><jsp:include page="menu.jsp"/></td> 
	    			<td id="rightPanel" ><jsp:include page="main.jsp"/></td>
	  			</tr>
			</table>
			<jsp:include page="footer.jsp"/>
		</div>
  </body>
</html>