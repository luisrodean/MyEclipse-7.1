<%
/*###############################################################################

# Nombre del Programa :  errorPage.jsp                                          #

# Autor               :  JOAQUIN MOJICA Q.										#

# Compania            :  WELLCOM S.A. DE C.V.                                   #

# Proyecto/Procliente :  			                 	   FECHA:09/03/2012     #

# Descripcion General :	 Pagina para la visualizacion de Errores  				#

#                                                                               #

# Programa Dependiente:  Login.jsp												#

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

# Modificaci�n        :                                                         #

#-----------------------------------------------------------------------------  #

# Numero de Parametros: 0                                                       #

###############################################################################*/
%>

<%@ page isErrorPage="true" %>

<html>
	<body> 
			<br><blockquote><h5 align="left"><BR><BR>Imposible satisfacer su requerimiento.
	    		<br>Posible falla ocasionada por:<br><br>
	    	</h5>
			<font color="red">
			<%    	
		    	String msgError="";
		   		int pos=0;
		   		
		   		try{
		    		pos=exception.getMessage().indexOf(":");
		   		}catch(Exception e){
			   		pos = -2;
		     		exception = e;
		   		}
		
		       	if (pos > -1)
		       	{
		            msgError =exception.getMessage();
		            exception.printStackTrace();
		       	}
		       	else
		      		msgError="ERRORPROGRAMADO";
	
				System.out.println("msgError..." + msgError);
	
	
	
	       		if (msgError.indexOf("ORA-00001") > -1 )
	          		out.println("<B><I>No puede haber registros duplicados. Por favor verifique</I></B>");
				else if (msgError.indexOf("ORA-1012") > -1 )
	          		out.println("<B><I>No existe conexi&oacute;n con Oracle. Por favor verifique</I></B>");
	       		else if (msgError.indexOf("ORA-0051") > -1 )
	          		out.println("<B><I>Se produjo un fin de intervalo mientras se esperaba un cierto recurso. Por favor verifique</I></B>");
	       		else if (msgError.indexOf("ORA-0061") > -1 )
	          		out.println("<B><I>La transacci&oacute;n fue cancelada debido a un bloqueo<I></B>");
	       		else if (msgError.indexOf("ORA-1001") > -1 )
	          		out.println("<B><I>Operaci&oacute;n ilegal con un cursor</I></B>");
	       		else if (msgError.indexOf("ORA-1017") > -1 )
	          		out.println("<B><I>Nombre de usuario o contrase�a inv&aacute;lidos</I></B>");
	       		else if (msgError.indexOf("ORA-1403") > -1 )
	          		out.println("<B><I>No se ha encontrado ning&uacute;n dato</I></B>");
	       		else if (msgError.indexOf("ORA-1422") > -1 )
	          		out.println("<B><I>Hay m&aacute;s de una fila que corresponde a una orden Select..Into</I></B>");
	       		else if (msgError.indexOf("ORA-1476") > -1 )
	          		out.println("<B><I>Divisi&oacute;n por cero</I></B>");
	       		else if (msgError.indexOf("ORA-1722") > -1 )
	          		out.println("<B><I>Fall&oacute; la conversi&oacute;n a un n&uacute;mero;por ejemplo, IA no es v&aacute;lido</I></B>");
	       		else if (msgError.indexOf("ORA-6500") > -1 )
	          		out.println("<B><I>Error interno PL/SQL, generado cuando PL/SQL se queda sin memoria</I></B>");
	       		else if (msgError.indexOf("ORA-6501") > -1 )
	          		out.println("<B><I>Error interno PL/SQL</I></B>");
	       		else if (msgError.indexOf("ORA-6502") > -1 )
	          		out.println("<B><I>Error de truncamiento, aritm&eacute;tico o de conversi&oacute;n</I></B>");
	       		else if (msgError.indexOf("ORA-6504") > -1 )
	          		out.println("<B><I>Una variable de cursor del host y una variable de cursor PL/SQL tienen tipos de filas incompatibles</I></B>");
	       		else if (msgError.indexOf("ORA-6511") > -1 )
	          		out.println("<B><I>Se ha intentado abrir un cursor que ya est&aacute; abierto</I></B>");
	       		else if (msgError.indexOf("ORA-6530") > -1 )
	          		out.println("<B><I>Se ha intentado asignar valores a los atributos de un objeto que tiene el valor de NULL</I></B>");
	       		else if (msgError.indexOf("ORA-6531") > -1 )
	          		out.println("<B><I>Se ha intenado aplicar m&eacute;todos de colecci&oacute;n distintos de EXISTS a una tabla &oacute; array PL/SQL con valor NULL</I></B>");
	       		else if (msgError.indexOf("ORA-6532") > -1 )
	          		out.println("<B><I>Una referencia a una tabla anidada o &iacute;ndice de array se encuentra fuera del rango declarado (por ejemplo -1)</I></B>");
	       		else if (msgError.indexOf("ORA-6533") > -1 )
	          		out.println("<B><I>Una referencia a una tabla anidada o &iacute;ndice de array es mayor que el n&uacute;mero de elementos de la colecci&oacute;n</I></B>");
	       		else if (msgError.indexOf("ORA-00904") > -1 )
	          		out.println("<B><I>Referencia a un nombre de columna inv&aacute;lido.</I></B>");
	       		else if (msgError.indexOf("ORA-01438") > -1 )
	          		out.println("<B><I>Valor mayor que el m&aacute;ximo permitido para esta columna.</I></B>");
	       		else if (msgError.indexOf("ORA-02292") > -1 )
	          		out.println("<B><I>Imposible eliminar registro, tiene datos dependientes.<br>Por favor rectifique</I></B>");
	       		else if (msgError.indexOf("ORA-01847") > -1 || msgError.indexOf("ORA-01843") > -1 )
	          		out.println("<B><I>Fecha Invalida. Verifique datos de captura.</I></B>");
	       		else if (msgError.indexOf("CONNECTION") > -1 )
	          		out.println("<B><I>No existe conexi&oacute;n a la base de datos.</I></B>");
	        	else if (msgError.indexOf("NODATA") > -1 )
	          		out.println("<B><I>Debe informar login y password. Estos campos no pueden ser nulos. <br> Por favor intente nuevamente.</I></B>");
	        	else if (msgError.indexOf("NOEXISTS") > -1 )
	          		out.println("<B><I>El usuario no existe. Por favor verifique. </I></B>");
	        	else if (msgError.indexOf("NOGROUP") > -1 )
	          		out.println("<B><I>El usuario no tiene permiso de accesar al sistema. </I></B>");
	       		else if (msgError.indexOf("[LDAP: error code 32") > -1 )
	       			//Modificaci�n Juan Mej�a 31-03-2011
	       			out.println("<B><I>Error de autenticaci&oacute;n<br> Por favor intentelo de nuevo.</I></B>");
	       		else if (msgError.indexOf("[LDAP: error code 34") > -1 )
	       			out.println("<B><I>Error de autenticaci&oacute;n<br> Por favor intentelo de nuevo.</I></B>");
	       		else if (msgError.indexOf("[LDAP: error code 49")> -1 )
	       			out.println("<B><I>Error de autenticaci&oacute;n<br> Por favor intentelo de nuevo.</I></B>");
	       		else if (pos == -2 ) //ERRORPROGRAMADO
	          		out.println("<B><I>" + "Error en la operaci�n normal del Sistema. " + "<BR><BR>Su tiempo de conexion ha expirado.</I></B>");
				else
	          		out.println("<B><I>" + exception.getMessage() + "<BR><BR>Por favor, contacte al administrador.</I></B>");
	
			%>
			</font>
		</blockquote>
		<h6>
			<br>
			<font color="red">
			<%
	
	 			java.io.CharArrayWriter cw = new java.io.CharArrayWriter();
	 			java.io.PrintWriter pw = new java.io.PrintWriter(cw,true);
	 			exception.printStackTrace(pw);
	
	 			// Habilitar para DEBUG de la Aplicacion
	  			// out.println(cw.toString());
	
	 		%>
			</font>
		</h6>
		<br>
	</body>
</html>