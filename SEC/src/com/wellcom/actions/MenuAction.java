package com.wellcom.actions;


import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;




public class MenuAction extends CoreAction {
	
		 
		
		public String execute(){
			System.out.println("\n\n---> Entro");
			return SUCCESS;
		}
		
		//Metodo a ejecutar para ser resuleto por Tiles
		public String tiger() { 
			 System.out.println("tiger");
			   return "tiger"; 
		}
		
		public String lion() { 
			   return "lion"; 
		}

		public void setApplication(Map<String, Object> arg0) {
			this.application = arg0;
			
		}




		public void setSession(Map<String, Object> arg0) {
			// TODO Auto-generated method stub
			this.session= arg0;
		}




		public void setServletRequest(HttpServletRequest arg0) {
			// TODO Auto-generated method stub
			this.request = arg0;
		}




		public void setServletResponse(HttpServletResponse arg0) {
			// TODO Auto-generated method stub
			this.response = arg0;
		}
}
