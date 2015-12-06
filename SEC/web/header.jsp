<link href="https://fonts.googleapis.com/icon?family=Material+Icons" rel="stylesheet">
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

<div id="bar" >
  <nav>
    <div class="nav-wrapper blue-grey darken-4">
      <a href="index" class="brand-logo"><%=usuarioActivo%></a>
      <ul class="right hide-on-med-and-down">
        <li><a href="salir"><i class="large material-icons">power_settings_new</i></a></li>
      </ul>
    </div>
  </nav>
</div>