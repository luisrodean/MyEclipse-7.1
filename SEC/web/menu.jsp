<%
/*###############################################################################
# Nombre del Programa :  menu.jsp					                            #
# Autor               :  Alejandro Gonzalez Solis		                 		#
# Compania            :  WELLCOM S.A. DE C.V.                                   #
# Proyecto/Procliente :    				               	   FECHA:25/02/2015     #
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
<%@page contentType="text/html; charset=iso-8859-1"%>
<jsp:useBean id="ga" scope="session" class="com.wellcom.banorte.abwportal.GrantAccess"/>
<%@ taglib prefix="sj"	uri="/struts-jquery-tags"%>

<%!
	String posicion;
%>
<%
    if(!ga.AccessGranted(session, "grantAccess"))
        response.sendRedirect("login.jsp");

	posicion="a0";
	
	// Tratamos de obtener la posici�n establecida para el men�
	try
	{
		if(session.getAttribute("pos")!=null)
			posicion = (String)session.getAttribute("pos");
	}
	catch(Exception e)
	{
		posicion="a0";	
	}
%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1"/>
		
		<link rel="stylesheet" type="text/css" href="js/dhtmlxAccordion/codebase/skins/dhtmlxaccordion_dhx_black.css"/>
		<script src="js/dhtmlxAccordion/codebase/dhtmlxcommon.js"></script>
		<script src="js/dhtmlxAccordion/codebase/dhtmlxaccordion.js"></script>
		<script src="js/dhtmlxAccordion/codebase/dhtmlxcontainer.js"></script>
	
		<link rel="stylesheet" type="text/css" href="js/dhtmlxTree/codebase/dhtmlxtree.css" />
		<script src="js/dhtmlxTree/codebase/dhtmlxcommon.js"></script>
		<script src="js/dhtmlxTree/codebase/dhtmlxtree.js"></script>
		
		<!-- 	SE HABILITA EL USO DE jQuery-UI Y SE ASIGNA EL TEMA	-->			
		<sj:head jqueryui="true" locale="es" jquerytheme="pepper-grinder" compressed="true" debug="true"></sj:head>	
        
        <!--	SE REFERENC�A UNA HOJA DE ESTILOS UTILIZADA PARA MATERIAL	-->
		<script type="text/javascript" src="js/materialize.min.js"></script>
		<link type="text/css" rel="stylesheet" href="css/materialize.min.css"  media="screen,projection"/>	
		
		<!--	SE REFERENC�A UN FAVICON	-->
		<link rel="icon" type="image/png" href="pics/wellcom-logo.png" />
		
		<script type="text/javascript">
		$(document).ready(function(){
    	$('.materialboxed').materialbox();
  		});
  		
	//Animacion de movimiento en el menu al hacer clic
	var ventana_alto = $(window).height();		    
	/*$("#divMenu").attr("style","top:"+ventana_alto*0.25+"px;");
	$("#divMenu").click(function(){
		$("#divMenu").animate({top:'100px'});
	});
	
	//Animacion de movimiento del menu al pasar el mouse
	$("#divMenu").hover(
		function(){
			$("#divMenu").stop(true).animate({left:'250px'},300);
		},function(){
			$("#divMenu").stop(true).animate({left:'0px'},300);
		});*/
	
	</script>
		
	</head>

	<body onload="OnLoadMenu();">
		<div id="accordObj"  style="width: 230px; height: 450px;"` ></div>
		
		<script><!--
	  	var dhxAccord;
	  	var text;
	  	function OnLoadMenu() 
		{
		    		    
		    // Primero declaramos el objeto
			dhxAccord = new dhtmlXAccordion("accordObj");
	    	dhxAccord.setSkin("dhx_black");
	    	dhxAccord.setIconsPath("pics/icons/");
	    	
	   
	    	dhxAccord.addItem("a0", "Inicio");
	    	dhxAccord.setIcon("a0", "Inicio.png");
	    	
	    	dhxAccord.addItem("a4", "Calificacion");
	    	dhxAccord.setIcon("a4", "Administracion.png");
	    	
	    	
	    	//dhxAccord.addItem("a7", "Reportes");
	    	//dhxAccord.setIcon("a7", "uorg.gif");
	    	
	    	dhxAccord.addItem("a10", "Alumno");
	    	dhxAccord.setIcon("a10", "uorg.gif");
	    	dhxAccord.closeItem("a10");
	    
	    	dhxAccord.addItem("a9", "Salir");
	    	dhxAccord.setIcon("a9", "salir.png");
	    	dhxAccord.closeItem("a9");
	    	
	    		    	/** 
	    	***	Aqu� le decimos que hacer en caso de q le den click a la pesta�a Inicio
	    	**/    	        		 	
	    	dhxAccord.attachEvent("onBeforeActive", function(itemId) 
    		{
    			var allow = (itemId!="a0");
    			    		
    			if(dhxAccord.cells(itemId).getText()=='Inicio')
    				window.location.href="index";
    			
    			return allow;
    		});

	    	/** 
	    	***	Aqu� le decimos que hacer en caso de q le den click a la pesta�a Salir
	    	*
	    	dhxAccord.attachEvent("onActive", function(itemId) 
	    	{
	    		if(dhxAccord.cells(itemId).getText()=='Salir')
	    			window.location.href="salir";
	    	});
	    	
	    	/** 
	    	*** El if se pone para que se abran las opciones del men�
	    	*** siempre y cuando no sea la opci�n inicio.
	    	**/
	    	dhxAccord._enableOpenEffect = true;
	    	<%if (!posicion.equals("a0")){%>
	    	dhxAccord.cells("<%= posicion%>").open();
	   		<%}%>
	   		dhxAccord.setEffect(true);		    	
	    				
	    	/** 
	    	***
	    	*** A partir de aqu� se le d� formato a los submen�s y 
	    	*** los llenamos con los xml correspondientes.
	    	*** 
	    	**/
		

	    	var tree4 = dhxAccord.cells("a4").attachTree();
	    	tree4.setSkin("dhx_black");
	    	tree4.setImagePath("pics/icons/");
	    	tree4.enableTreeImages(true);
			tree4.enableTreeLines(true);
			tree4.attachEvent("onClick", 
				function(id)
				{
      				if(id!=undefined && id != "AdmGestionCatalogos")
      				{
						window.location.href = tree4.getUserData(id,"href");
						<%session.setAttribute("pos","a4"); %>
					}
		        });
			
			<%
			String valorRol = null;
			if(session.getAttribute("rolAccVal")!=null)
				valorRol = session.getAttribute("rolAccVal").toString();
		    %>
		    var rolValor = '<%= valorRol%>';
		    if(rolValor == 1){
		    	tree4.loadXML("js/dhtmlxTree/common/Administracion.xml");
		    }else{
		    	dhxAccord.forEachItem(function(item) {
		        				var idItem= item.getId();
		        				//alert(idItem);
		       					if(idItem == "a4"){
		       						dhxAccord.cells(idItem).hide();// Oculta la opcion Administracion
		       					}
		    				});
		    }
		    
		    	
	    	////////////////////////////Dictamenes///////////////////////////////
		   /* var tree7 = dhxAccord.cells("a7").attachTree();
	    	tree7.setSkin("dhx_black");
	    	tree7.setImagePath("pics/icons/");
	    	tree7.enableTreeImages(true);
			tree7.enableTreeLines(true);
			tree7.attachEvent("onClick", 
				function(id)
				{
      				if(id!=undefined && id != "AdmGestionCatalogos")
      				{
						window.location.href = tree7.getUserData(id,"href");
						<%session.setAttribute("pos","a7"); %>
					}
		        });
			
			<%
			String valorRol3 = null;
			if(session.getAttribute("rolAccVal")!=null)
				valorRol3 = session.getAttribute("rolAccVal").toString();
		    %>
		    var rolValor3 = '<%= valorRol3%>';
		    
		    if(rolValor3 == 1 || rolValor3 == 2){
		    	tree7.loadXML("js/dhtmlxTree/common/Dictamenes.xml");
		    } */
	    	
	    	/////////////////////////////////Buscar///////////////////////
	    	var tree10 = dhxAccord.cells("a10").attachTree();
	    	tree10.setSkin("dhx_black");
	    	tree10.setImagePath("pics/icons/");
	    	tree10.enableTreeImages(true);
			tree10.enableTreeLines(true);
			tree10.attachEvent("onClick", 
				function(id)
				{
      				if(id!=undefined && id != "AdmGestionCatalogos")
      				{
						window.location.href = tree10.getUserData(id,"href");
						<%session.setAttribute("pos","a10"); %>
					}
		        });
			
			<%
			String valorRol4 = null;
			if(session.getAttribute("rolAccVal")!=null)
				valorRol4 = session.getAttribute("rolAccVal").toString();
		    %>
		    	tree10.loadXML("js/dhtmlxTree/common/Buscar.xml");			
		}
		</script>
	</body>
</html>