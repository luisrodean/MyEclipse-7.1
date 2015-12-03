/*###############################################################################
# Nombre del Programa :  Jndi.java                                              #
# Autor               :  JOAQUIN MOJICA Q.                                      #
# Compania            :  WELLCOM S.A. DE C.V.                                   #
# Proyecto/Procliente :  P-02-0379-10                 	   FECHA:09/05/2011     #
# Descripcion General :	 Clase para el manejo de la seguridad                   #
#                                                                               #
# Programa Dependiente:                                                         #
# Programa Subsecuente:                                                         #
# Cond. de ejecucion  :  Acceder al sistema                                     #
#                                                                               #
#                                                                               #
# Dias de ejecucion   :  A Peticion del web, se pueden ejecutar n instancias    #
#################################################################################
#								MODIFICACIONES                                  #
# Autor               :                                                         #
# Compania            :                                                         #
# Proyecto/Procliente :                              Fecha:                     #
# Modificación        :                                                         #
#-----------------------------------------------------------------------------  #
# Numero de Parametros: 0                                                       #
###############################################################################*/


package com.wellcom.directory;

/**
 * Insert the type's description here.
 * Creation date: (1/7/2004 4:34:38 PM)
 * @author: Administrator
 */

import java.io.*;
import java.util.*;

import javax.naming.*;
import javax.naming.directory.*;

import com.wellcom.exceptions.*;
import com.wellcom.io.*;
import mx.com.prosa.sso.AMConnectionUtil;

/**
 * Demonstrates how to create an initial context to an LDAP server
 * using simple authentication.
 *
 * usage: java Simple
 */

public class Jndi
  implements Serializable {
  /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public DirContext ctxMain;
	public String msg;
	public java.lang.String uid;
	public java.lang.String dn;
	public java.lang.String initialContext;
	public java.lang.String url;
	public java.lang.String factory;
	public java.lang.String authentication;
	public java.lang.String peopleDn;
	public java.lang.String appDn;
	public java.lang.String appUsr;
	public java.lang.String appPwd;

  /**
   * Ldap constructor comment.
   */
  public Jndi() {
    super();
  }

  /**
   * Insert the method's description here.
   * Creation date: (1/7/2004 5:23:30 PM)
   */

  public static void main(String args[]) {
    Jndi myJndi = new Jndi();
    String myRole = "", myOu1st = "", myOu2nd = "";
    try {

      myJndi.doProperties("./propiedades.xml");
      System.out.println("doLogin: " +
                         myJndi.doLogin("pmartinez", "dsfdsa"));

      /*
              String[] attrIDs = {"entrydn", "uniquemember"};
              String filter = "(&(cn=*))";
       printSearchEnumeration(myJndi.searchSubtree(myJndi.appDn, attrIDs, filter));
       */
      System.out.println("key: " + myJndi.loadKey(myJndi.dn, "sn"));
      String[] attrIDs = {
        "entrydn", "sn", "cn"};
      System.out.println("attrs: " + myJndi.loadAttrs(myJndi.dn, attrIDs));
      System.out.println("OuRoles: " + myJndi.loadOuRoles(myJndi.appDn));
      System.out.println("UidsByRol: " +
                         myJndi.loadUidsByRol(myJndi.appDn, "Comercio"));
      System.out.println("loadUidsByRolReport: " +
                         myJndi.loadUidsByRolReport(myJndi.appDn, "Comercio"));
      System.out.println("UidsByOu: " + myJndi.loadUidsByOu(myJndi.peopleDn));
      System.out.println("UidsByOu: " + myJndi.loadUidsByOu(""));
      System.out.println("UidRoles: " +
                         myJndi.loadUidRoles(myJndi.appDn, myJndi.uid));

      //Roles del Sistema
      myRole = myJndi.loadUidRoles(myJndi.appDn, myJndi.uid).toString();
       if ( (myRole.toLowerCase()).indexOf("administrador") >= 0) {
        myRole = "administrador";
      }
      else if ( (myRole.toLowerCase()).indexOf("banco") >= 0) {
        myRole = "banco";
      }
      else if ( (myRole.toLowerCase()).indexOf("corporativo") >= 0) {
        myRole = "corporativo";
      }
      else {
        myRole = "comercio";
      }
      System.out.println("myRole: " + myRole);

      //Numero Afiliacion
      myOu1st = myJndi.dn.substring( (myJndi.dn.toLowerCase()).indexOf("ou=") +
                                    3);
      System.out.println("myOu.indexOf(\",\"): " + myOu1st.indexOf(","));
      myOu1st = myOu1st.indexOf(",") >= 0 ?
        (myOu1st.substring(0, myOu1st.indexOf(","))).trim() : myOu1st;
      System.out.println("myOu1st: " + myOu1st);

      //FIID
      if ( (myJndi.dn.toLowerCase()).indexOf("ou=bancos") >= 0) {
        myOu2nd = myJndi.dn.substring(0,
                                      (myJndi.dn.toLowerCase()).indexOf("ou=bancos") -
                                      1);
        System.out.println("myOu2nd: " + myOu2nd);
        myOu2nd = myOu2nd.lastIndexOf("ou=") >= 0 ?
          myOu2nd.substring(myOu2nd.lastIndexOf("ou=") + 3).trim() : "PROSA";
      }
      else {
        myOu2nd = "PROSA";
      }
      System.out.println("myOu2nd: " + myOu2nd);


    }
    catch (Exception ex) {
      ex.printStackTrace();
    }
  }

  /**
   * Insert the method's description here.
   * Creation date: (1/7/2004 4:42:20 PM)
   */
  @SuppressWarnings("unchecked")
public DirContext doContext(String dn, String password) throws Exception {

    // Get LDAP Configuration
    //damePropiedades();
    // Set up environment for creating initial context
    DirContext ctx = null;
    Hashtable env = new Hashtable(11);
    // factory = "com.sun.jndi.ldap.LdapCtxFactory"
    env.put(Context.INITIAL_CONTEXT_FACTORY,
            factory);
    //url = "ldap://localhost:390/o=wellcom,c=mx");
    env.put(Context.PROVIDER_URL, url + initialContext);
    //authentication = "simple"
    env.put(Context.SECURITY_AUTHENTICATION, authentication);
    //userDN = "cn=S. User, ou=NewHires, o=JNDITutorial");
    env.put(Context.SECURITY_PRINCIPAL, uid);
    //password = "*****";
    env.put(Context.SECURITY_CREDENTIALS, password);
    
    if (password!=null){
        env.put("java.naming.security.principal", uid);
        env.put("java.naming.security.credentials", password);
	}
    else {

		env.put("java.naming.security.principal", appUsr);
        env.put("java.naming.security.credentials", appPwd);
        System.out.println("--*--*--*--*-- ENTRO AL ELSE " + String.valueOf(password) + "--*--*--*--");
    }

    try {

      System.out.println("initialContext: " + initialContext);
      System.out.println("dn: " + dn);

      // Create initial context
      ctx = new InitialDirContext(env);
    }
    catch (NamingException e) {
//		e.printStackTrace();
//		throw new WellException("com.wellcom.directory.Ldap.dameConexion: "+"[ERRORLDAP4].Credenciales Invalidas."+ url +", userDN="+ dn +" | "+ e);
//		throw new WellException("com.wellcom.directory.Ldap.dameConexion|" + e.toString());
      throw e;

    }
    catch (Exception e) {
//		e.printStackTrace();
//	 	throw new WellException("com.wellcom.directory.Ldap.dameConexion|" + e.toString());
      throw e;
    }

    return ctx;
  }

  /**
   * Insert the method's description here.
   * Creation date: (4/5/2004 11:54:13 PM)
   */
  @SuppressWarnings("unchecked")
public ArrayList loadKey(String ou, String key) throws WellException {

    ArrayList myArrayList = new ArrayList();
    ArrayList attrsList = new ArrayList();
    try {

      String[] attrIDs = {
        key};
      String filter = "(&(" + key + "=*))";

      NamingEnumeration answer = searchObject(ou, attrIDs, filter);

      // Print the answer
      //printSearchEnumeration(answer);

      while (answer.hasMore()) {
        SearchResult sr = (SearchResult) answer.next();
//System.out.println(">>>" + sr.getName());
        attrsList = doAttrsList(sr.getAttributes());
        myArrayList.addAll(attrsList);
//System.out.println(">>>" + myArrayList);

      }

    }
    catch (Exception e) {
      e.printStackTrace();
    }
    return myArrayList;
  }

  @SuppressWarnings("unchecked")
public ArrayList loadAttrs(String ou, String[] attrIDs) throws WellException {

    ArrayList myArrayList = new ArrayList();
    ArrayList attrsList = new ArrayList();
    try {

      String filter = "(&(" + "entrydn" + "=*))";

      NamingEnumeration answer = searchObject(ou, attrIDs, filter);

      // Print the answer
      //printSearchEnumeration(answer);

      while (answer.hasMore()) {
        SearchResult sr = (SearchResult) answer.next();
//System.out.println(">>>" + sr.getName());
        attrsList = doAttrsList(sr.getAttributes());
        myArrayList.addAll(attrsList);
//System.out.println(">>>" + myArrayList);

      }

    }
    catch (Exception e) {
      e.printStackTrace();
    }
    return myArrayList;
  }

  /**
   * Insert the method's description here.
   * Creation date: (1/7/2004 4:37:22 PM)
   */
  @SuppressWarnings("unchecked")
static void printAttrs(Attributes attrs) throws WellException {
    if (attrs == null) {
      System.out.println("No attributes");
    }
    else {
      /* Print each attribute */
      try {

        for (NamingEnumeration ae = attrs.getAll();
             ae.hasMore(); ) {
          Attribute attr = (Attribute) ae.next();
          System.out.println("attribute: " + attr.getID());

          /* print each value */
          for (NamingEnumeration e = attr.getAll();
               e.hasMore();
               System.out.println("value: " + e.next())) {
            ;
          }
        }

      }
      catch (NamingException e) {
//        e.printStackTrace();
        throw new WellException("com.wellcom.directory.Ldap.imprimeAtributos|" +
                                e.toString());

      }
      catch (Exception e) {
//		e.printStackTrace();
        throw new WellException("com.wellcom.directory.Ldap.imprimeAtributos|" +
                                e.toString());
      }
    } // end if
  }

  @SuppressWarnings("unchecked")
public ArrayList doAttrsList(Attributes attrs) throws WellException {
    ArrayList myArrayList = new ArrayList();
    if (attrs == null) {
      System.out.println("No attributes");
    }
    else {
      /* For each attribute */
      try {

        for (NamingEnumeration ae = attrs.getAll();
             ae.hasMore(); ) {
          Attribute attr = (Attribute) ae.next();
//System.out.println("attribute: " + attr.getID());

          /* For each value */
          for (NamingEnumeration e = attr.getAll();
               e.hasMore();
               myArrayList.add(e.next())) {
            ;
          }
        }
      }
      catch (NamingException e) {
//        e.printStackTrace();
        throw new WellException("com.wellcom.directory.Ldap.imprimeAtributos|" +
                                e.toString());

      }
      catch (Exception e) {
//		e.printStackTrace();
        throw new WellException("com.wellcom.directory.Ldap.imprimeAtributos|" +
                                e.toString());
      }
    } // end if
    return myArrayList;

  }

  /**
   * Insert the method's description here.
   * Creation date: (5/7/2004 2:41:07 PM)
   */
  public boolean doLogin(String login, String password) throws Exception {
      
    try {

      dn = getDn(login);
      uid = getUid();
      ctxMain = doContext(dn, password);
      System.out.println("Estoy en el Login!!!");
      
      return true;
    }

    catch (Exception e) {
      throw new WellException("com.wellcom.directory.jndi.doLogin|" +
                              e.toString());
    }
  }
  
  @SuppressWarnings({ "unchecked" })
public boolean doSemiLogin(String ssoToken)
  throws Exception
{
  try
  {
	  System.out.println("-*-*-*-*-*-* Esta en el Try del SemiLogin -*-*-*-*-*-*");
      String token =  ssoToken;
      //TODO: modificacion 01/04/2011 Lalo
      if (ssoToken == null || ssoToken == "") return false;
      //TODO: termina modificacion Lalo
      System.out.println("-*-*-*-*-*-* Antes del AMConnection -*-*-*-*-*-*");
	  AMConnectionUtil amcon = new AMConnectionUtil();
      Map usrInfo = amcon.userInfo(token);

      System.out.println("-*-*-*-*-*-* Antes del Hash -*-*-*-*-*-*");
      HashSet temp2 =  (HashSet) usrInfo.get("dn");
      System.out.println("temp2 " + temp2.toString());

      for (Iterator it=temp2.iterator(); it.hasNext();)
          dn = it.next().toString();

      HashSet temp1 =  (HashSet) usrInfo.get("uid");

      for (Iterator it=temp1.iterator(); it.hasNext();)
          uid = it.next().toString();

      System.out.println("uid " + uid);
      System.out.println("dn " + dn);

      ctxMain = doContext(dn, null);
      System.out.println("Despues del ctxMain");
      boolean flag = true;
      System.out.println("Despues del boolean");

      return flag;
  }
  catch(Exception e)
  {
      e.printStackTrace();
      throw new WellException("com.wellcom.directory.jndi.doSemiLogin|".concat(String.valueOf(String.valueOf(e.toString()))));
  }
}
  
  @SuppressWarnings("unchecked")
public NamingEnumeration searchSubtree(String ou, String[] attrIDs,
                                         String filter) {

    NamingEnumeration answer;
    // Set up the environment for creating the initial context
    Hashtable env = new Hashtable(11);
    env.put(Context.INITIAL_CONTEXT_FACTORY,
            factory);
    env.put(Context.PROVIDER_URL, url + initialContext);
    env.put(Context.SECURITY_AUTHENTICATION, authentication);
    //userDN = "cn=S. User, ou=NewHires, o=JNDITutorial");
    env.put(Context.SECURITY_PRINCIPAL, appUsr);
    //password = "*****";
    env.put(Context.SECURITY_CREDENTIALS, appPwd);
    

    try {
      // Create initial context
      DirContext ctx = new InitialDirContext(env);

      // Specify the ids of the attributes to return
      // String[] attrIDs = {"sn", "telephonenumber", "golfhandicap", "mail"};
      SearchControls ctls = new SearchControls();
      ctls.setReturningAttributes(attrIDs);
      ctls.setSearchScope(SearchControls.SUBTREE_SCOPE);

      // Specify the search filter to match
      // String filter = "(&(sn=Contreras)(mail=*))";

      // Search subtree for objects using filter
      // NamingEnumeration answer = ctx.search("", filter, ctls);
      answer = ctx.search(ou, filter, ctls);

      // Print the answer
      // printSearchEnumeration(answer);

      // Close the context when we're done
      ctx.close();
    }
    catch (Exception e) {
      e.printStackTrace();
      answer = null;
    }
    return answer;

  }

  @SuppressWarnings("unchecked")
public NamingEnumeration searchObject(String ou, String[] attrIDs,
                                        String filter) {

    NamingEnumeration answer;
    // Set up the environment for creating the initial context
    Hashtable env = new Hashtable(11);
    env.put(Context.INITIAL_CONTEXT_FACTORY,
            factory);
    env.put(Context.PROVIDER_URL, url + initialContext);

    try {
      // Create initial context
      DirContext ctx = new InitialDirContext(env);

      // Specify the ids of the attributes to return
      // String[] attrIDs = {"sn", "telephonenumber", "golfhandicap", "mail"};
      SearchControls ctls = new SearchControls();
      ctls.setReturningAttributes(attrIDs);
      ctls.setSearchScope(SearchControls.OBJECT_SCOPE);

      // Specify the search filter to match
      // String filter = "(&(sn=Contreras)(mail=*))";

      // Search subtree for objects using filter
      // NamingEnumeration answer = ctxMain.search("", filter, ctls);
      answer = ctx.search(ou, filter, ctls);

      // Print the answer
      // printSearchEnumeration(answer);

      // Close the context when we're done
      ctx.close();
    }
    catch (Exception e) {
      e.printStackTrace();
      answer = null;
    }
    return answer;

  }

  @SuppressWarnings("unchecked")
static void printSearchEnumeration(NamingEnumeration enum2) throws
    WellException {
    try {
      while (enum2.hasMore()) {
        SearchResult sr = (SearchResult)enum2.next();
        System.out.println(">>>" + sr.getName());
        printAttrs(sr.getAttributes());
      }
    }
    catch (NamingException e) {
      e.printStackTrace();
    }
  }

  @SuppressWarnings("unchecked")
public ArrayList loadOuRoles(String ou) {

    ArrayList myArrayList = new ArrayList();
    ArrayList attrsList = new ArrayList();
    try {

      String[] attrIDs = {
        "cn"};
      String filter = "(&(cn=*))";

      NamingEnumeration answer = searchSubtree(ou, attrIDs, filter);

      // Print the answer
      //printSearchEnumeration(answer);

      while (answer.hasMore()) {
        SearchResult sr = (SearchResult) answer.next();
//System.out.println(">>>" + sr.getName());
        attrsList = doAttrsList(sr.getAttributes());
        myArrayList.addAll(attrsList);
//System.out.println(">>>" + myArrayList);

      }

    }
    catch (Exception e) {
      e.printStackTrace();
    }
    return myArrayList;
  }

  @SuppressWarnings("unchecked")
public ArrayList loadUidsByRol(String ou, String rol) {

    ArrayList myArrayList = new ArrayList();
    ArrayList attrsList = new ArrayList();

    try {

      String[] attrIDs = {
        "uniquemember"};
      String filter = "(&(cn=*" + rol + "*))";

      NamingEnumeration answer = searchSubtree(ou, attrIDs, filter);

      // Print the answer
      //printSearchEnumeration(answer);

      while (answer.hasMore()) {
        SearchResult sr = (SearchResult) answer.next();
//System.out.println(">>>" + sr.getName());
        attrsList = doAttrsList(sr.getAttributes());
        myArrayList.addAll(attrsList);
//System.out.println(">>>" + myArrayList);

      }

    }
    catch (Exception e) {
      e.printStackTrace();
    }
    return myArrayList;
  }


  @SuppressWarnings("unchecked")
public ArrayList loadUidsByRolReport(String ou, String rol) {

    ArrayList myArrayList = new ArrayList();
    ArrayList attrsList = new ArrayList();

    try {


      String[] attrIDs = {
        "uniquemember"};
      String filter = "(&(cn=*" + rol + "*))";

      NamingEnumeration answer = searchSubtree(ou, attrIDs, filter);

      // Print the answer
//      printSearchEnumeration(answer);

      while (answer.hasMore()) {
        SearchResult sr = (SearchResult) answer.next();
        attrsList = doAttrsList(sr.getAttributes());
      }

      Iterator it = attrsList.iterator();

      String myUid, myLogin, myOu1st, myOu2nd;
      while (it.hasNext()) {

          myUid = (String) it.next();
System.out.println("myUid: " + myUid );
          //Login
          myLogin = myUid.substring(4, (myUid.toLowerCase()).indexOf("ou=") - 1);

          //Numero Afiliacion
          myOu1st = myUid.substring( (myUid.toLowerCase()).indexOf("ou=") + 3);
          myOu1st = myOu1st.indexOf(",") >= 0 ?
                    (myOu1st.substring(0, myOu1st.indexOf(","))).trim() : myOu1st;
System.out.println("myOu1st: " + myOu1st);

          //FIIDt
          if ( (myUid.toLowerCase()).indexOf("ou=bancos") >= 0) {
            myOu2nd = myUid.substring(0, (myUid.toLowerCase()).indexOf("ou=bancos") - 1);
            System.out.println("myOu2nd: " + myOu2nd);
            myOu2nd = myOu2nd.lastIndexOf("ou=") >= 0 ?
                      myOu2nd.substring(myOu2nd.lastIndexOf("ou=") + 3).trim() : "PROSA";
          }
          else {
            myOu2nd = "PROSA";
          }

System.out.println("myOu2nd: " + myOu2nd);

        /* Cambio para Carga de HTML.getComboBox() */
        String[] rsColumnsData = null;
        rsColumnsData = new String[7];
        rsColumnsData[0] = myLogin;
        rsColumnsData[1] = rol;
        rsColumnsData[2] = "";
        rsColumnsData[3] = "";
        rsColumnsData[4] = myOu1st;
        rsColumnsData[5] = "";
        rsColumnsData[6] = myOu2nd;
        myArrayList.add(rsColumnsData);
}
    }
    catch (Exception e) {
      e.printStackTrace();
    }
    return myArrayList;
  }


  @SuppressWarnings("unchecked")
public ArrayList loadUidsByOu(String ou) {

    ArrayList myArrayList = new ArrayList();
    ArrayList attrsList = new ArrayList();
    try {

      String[] attrIDs = {
        "uid"};
      String filter = "(&(uid=*))";

      NamingEnumeration answer = searchSubtree(ou, attrIDs, filter);

      // Print the answer
      //printSearchEnumeration(answer);

      while (answer.hasMore()) {
        SearchResult sr = (SearchResult) answer.next();
        //System.out.println(">>>" + sr.getName());

        attrsList = doAttrsList(sr.getAttributes());

        /* Version Anterior
                     myArrayList.addAll(attrsList);
                     System.out.println(attrsList.toString());
         */
//System.out.println("attrsList.toString(): " + attrsList.toString());


        /* Cambio para Carga de HTML.getComboBox() */
        String[] rsColumnsData = null;
        rsColumnsData = new String[2];
        String login = "";
        login = attrsList.toString().replace('[', ' ').replace(']', ' ').trim();
        rsColumnsData[0] = login;
        rsColumnsData[1] = login;
        myArrayList.add(rsColumnsData);

      }

    }
    catch (Exception e) {
      e.printStackTrace();
    }
    return myArrayList;
  }

  @SuppressWarnings("unchecked")
public ArrayList loadUidRoles(String ou, String uid) {

    ArrayList myArrayList = new ArrayList();
    ArrayList attrsList = new ArrayList();

    try {

      String[] attrIDs = {
        "cn"};
      String filter = "(&(uniquemember=*" + uid + "*))";

      NamingEnumeration answer = searchSubtree(ou, attrIDs, filter);

      // Print the answer
      //printSearchEnumeration(answer);

      while (answer.hasMore()) {
        SearchResult sr = (SearchResult) answer.next();
//System.out.println(">>>" + sr.getName());
        attrsList = doAttrsList(sr.getAttributes());
        myArrayList.addAll(attrsList);
//System.out.println(">>>" + myArrayList);

      }

    }
    catch (Exception e) {
      e.printStackTrace();
    }
    return myArrayList;
  }

  public void doProperties(String filePath) throws WellException {
    try {
      ResourceFile rs = new ResourceFile();
      if (filePath.length() == 0) {
        throw new WellException("lo que sea"
                                + "Argumento null.");
      }
      System.out.println("FilePath: " + filePath);
      rs.setFilePath(filePath);

      url = rs.getXMLKey("URL");
      initialContext = rs.getXMLKey("INITIALCONTEXT");
      factory = rs.getXMLKey("FACTORY");
      authentication = rs.getXMLKey("AUTHENTICATION");
      peopleDn = rs.getXMLKey("PEOPLE");
      appDn = rs.getXMLKey("APPLICATION");
      appUsr=rs.getXMLKey("ADMUSER");
      appPwd=rs.getXMLKey("ADMPASSWORD");
      String admPwd="La6kw.wvvp3";
		String limpio = "";
		char c;
		int a;

		for (int j=0; j < admPwd.length(); j++) {
			c = admPwd.charAt(j);
			a = (int) c;
			if (j % 2 == 0) {
				a = a - 3;
			}else{
				a = a + 3;			
			}
			c = (char) a;

			Character caracter = new Character(c);
			limpio = limpio + caracter;
		}

		//System.out.println("valor de password: " + limpio);

		appPwd = limpio;
		
      
//            administratorDN = rs.getXMLKey( "ADMINISTRADORES" );
//            operatorDN = rs.getXMLKey( "USERS" );
//            securityDN = rs.getXMLKey( "SEGURIDAD" );
    }
    catch (Exception e) {
      throw new WellException(
        "com.wellcom.directory.ldap.doProperties|"
        + e.toString());
    }
  }

  public void doProperties(InputStream file) throws WellException {
	    try {
		      if (file == null) {
			        throw new WellException("El archivo a cargar no se encuentra - es nulo - "
			                                + "Argumento null.");
			      }
	      ResourceFile rs = new ResourceFile(file);	      
	      //rs.setFilePath(filePath);

	      url = rs.getXMLKey("URL");
	      initialContext = rs.getXMLKey("INITIALCONTEXT");
	      factory = rs.getXMLKey("FACTORY");
	      authentication = rs.getXMLKey("AUTHENTICATION");
	      peopleDn = rs.getXMLKey("PEOPLE");
	      appDn = rs.getXMLKey("APPLICATION");
	      appUsr=rs.getXMLKey("ADMUSER");
	      appPwd=rs.getXMLKey("ADMPASSWORD");
	      String admPwd="La6kw.wvvp3";
			String limpio = "";
			char c;
			int a;

			for (int j=0; j < admPwd.length(); j++) {
				c = admPwd.charAt(j);
				a = (int) c;
				if (j % 2 == 0) {
					a = a - 3;
				}else{
					a = a + 3;			
				}
				c = (char) a;

				Character caracter = new Character(c);
				limpio = limpio + caracter;
			}

			//System.out.println("valor de password: " + limpio);

			appPwd = limpio;
			
	      
//	            administratorDN = rs.getXMLKey( "ADMINISTRADORES" );
//	            operatorDN = rs.getXMLKey( "USERS" );
//	            securityDN = rs.getXMLKey( "SEGURIDAD" );
	    }
	    catch (Exception e) {
	      throw new WellException(
	        "com.wellcom.directory.ldap.doProperties|"
	        + e.toString());
	    }
	  }

  /**
   * Insert the method's description here.
   * Creation date: (1/11/2004 3:23:25 PM)
   * @return java.lang.String
   */
  public java.lang.String getAuthentication() {
    return authentication;
  }

  /**
   * Insert the method's description here.
   * Creation date: (1/11/2004 3:23:25 PM)
   * @return java.lang.String
   */
  public java.lang.String getInitialContext() {
    return initialContext;
  }

  /**
   * Insert the method's description here.
   * Creation date: (1/11/2004 3:23:25 PM)
   * @return java.lang.String
   */
  public java.lang.String getFactory() {
    return factory;
  }

  /**
   * Insert the method's description here.
   * Creation date: (1/11/2004 3:23:25 PM)
   * @return java.lang.String
   */
  public java.lang.String getPeopleDn() {
    return peopleDn;
  }

  /**
   * Insert the method's description here.
   * Creation date: (1/11/2004 3:23:25 PM)
   * @return java.lang.String
   */
  public java.lang.String getUrl() {
    return url;
  }

  /**
   * Insert the method's description here.
   * Creation date: (5/7/2004 2:41:07 PM)
   */
  public String getUid() throws Exception {

    uid = String.valueOf(String.valueOf( (new StringBuffer(String.valueOf(
      String.valueOf(dn)))).append(",").append(initialContext)));
    /*
                peopleDn = peopleDn != null ? peopleDn.trim() : "";
                if (peopleDn != "" && peopleDn != null) {
                    uid = String.valueOf(String.valueOf((new StringBuffer(String.valueOf(String.valueOf(dn)))).append(",").append(peopleDn).append(", ").append(initialContext)));
                } else {
                    uid = String.valueOf(String.valueOf((new StringBuffer(String.valueOf(String.valueOf(dn)))).append(",").append(initialContext)));
                }
     */

    System.out.println("getUid: " + uid);

    return uid;
  }

  /**
   * Insert the method's description here.
   * Creation date: (5/7/2004 2:54:12 PM)
   */
  @SuppressWarnings("unchecked")
public String getDn(String login)

    throws Exception {
    boolean bBan = false;

    String sRuta = "";
    Hashtable env = new Hashtable();
    env.put(Context.INITIAL_CONTEXT_FACTORY,
            factory);
    //url = "ldap://localhost:390/o=wellcom,c=mx");
    env.put(Context.PROVIDER_URL, url + initialContext);
    //authentication = "simple"
    env.put(Context.SECURITY_AUTHENTICATION, authentication);
    //userDN = "cn=S. User, ou=NewHires, o=JNDITutorial");
    env.put(Context.SECURITY_PRINCIPAL, appUsr);
    //password = "*****";
    env.put(Context.SECURITY_CREDENTIALS, appPwd);
    DirContext ctx = new InitialDirContext(env);

//		sRuta = String.valueOf(String.valueOf((new StringBuffer(String.valueOf(String.valueOf(Propiedades.getProperty("URL"))))).append(Propiedades.getProperty("PEOPLE")).append(",").append(Propiedades.getProperty("INITIALCONTEXT")).append("??sub?(uid=").append(uid).append(")")));

    peopleDn = peopleDn != null ? peopleDn.trim() : "";
    if (peopleDn.length() > 0) {
      sRuta = String.valueOf(String.valueOf( (new StringBuffer(String.valueOf(
        String.valueOf(url)))).append(peopleDn).append(",").append(
        initialContext).append("??sub?(uid=").append(login).append(")")));
    }
    else {
      sRuta = String.valueOf(String.valueOf( (new StringBuffer(String.valueOf(
        String.valueOf(url)))).append(initialContext).append("??sub?(uid=").
                                            append(login).append(")")));
    }

    System.out.println("sRuta: " + sRuta);

    for (NamingEnumeration answer = ctx.search(sRuta, "", null);
         answer.hasMoreElements(); ) {
      SearchResult sr = (SearchResult) answer.next();
      sr.setRelative(false);
      dn = sr.getName();
      System.out.println("for NamingEnumeration.dn: " + dn);
      bBan = true;
    }

    if (peopleDn.length() > 0) {
      dn = String.valueOf(String.valueOf( (new StringBuffer(String.valueOf(
        String.valueOf(dn)))).append(",").append(peopleDn)));
    }

    System.out.println("getDn: " + dn);

    if (bBan) {
      ctx.close();
    }

    return dn;
  }

}
