package com.wellcom.actions;


import java.io.File;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Actions;
import org.apache.struts2.convention.annotation.Result;




public class DictamenSofom extends CoreAction {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	

		public String execute(){
			session.put("pos", "a7");	
			System.out.println("\n\n---> Entro Dictamen Sofom");
			return "pantallaDictamenSofom";
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
