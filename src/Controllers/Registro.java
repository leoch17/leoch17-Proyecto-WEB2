package Controllers;

import java.sql.Connection; 
import Helpers.HashCode;
import Helpers.ConectionDB;

public class Registro {
	  private static ConectionDB cdb = new ConectionDB();
	  private static HashCode hs = new HashCode();
	  Connection connect = cdb.getConnection();
	  
	  public String register(String nu, String con, String ce, String telef, String sex, String date) {
			
			String pass = hs.generateHash(con);
			String [] obj = {nu, pass, ce, telef, sex, date};
			String info= " ";
			
			try {
				boolean producto = cdb.PreparedStatement(connect, obj);
				if(producto == true) {
					info = "{\"message\": \"Excelente, usted se ha registrado satisfactoriamente\", \"status\": 200 }";
				}else {
					info = "{\"message\": \"Lo lamentamos, no se ha podido completar su registro\", \"status\": 503 }";
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
			return info;	
		}


}
