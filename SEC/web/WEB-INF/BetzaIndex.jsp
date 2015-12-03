<%@ page language="java" pageEncoding="ISO-8859-1"%>
<jsp:useBean id="ga" scope="session" class="com.wellcom.banorte.abwportal.GrantAccess"/>

<%
	System.out.println("----------------------->Login");
	 if(!ga.AccessGranted(session, "grantAccess"))  
	     response.sendRedirect("BetzaLogin.jsp");
	     
	 session.setAttribute("pos","a0");	    		
	 
%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">

<html >
<head>
 	<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1"/>
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

</html:html>
