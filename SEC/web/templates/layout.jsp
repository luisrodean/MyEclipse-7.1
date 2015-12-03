<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%><%@ page language="java" import="java.util.*" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="s" 	uri="/struts-tags"%>
<%@ taglib prefix="sj"	uri="/struts-jquery-tags"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
   "http://www.w3.org/TR/html4/loose.dtd">
<html>

	<head>
		<script type="text/javascript" src="js/jquery-1.9.1.min.js"></script>
		<script type="text/javascript" src="js/jquery-1.10.2.js"></script>
		
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<title><tiles:insertAttribute name="title" ignore="true" /></title>
		<link href="css/styles.css" rel="stylesheet" type="text/css">
	</head>
	
	

	<body id="bodyPage" >

		<div id="header">
			<tiles:insertAttribute name="header" />
		</div>
		
		<div id="divMenu">
			<tiles:insertAttribute name="menu" />
		</div>
		<div id="cuerpo"  >
	    	<tiles:insertAttribute name="body" />
		</div>

		<div id="divFooter">
			<tiles:insertAttribute name="footer" />
		</div>


	</body>
</html>