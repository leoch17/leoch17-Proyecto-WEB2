package Controllers;
import java.sql.Connection;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import Helpers.*;

public class Ingresar {
	private static HttpSession sesion;
	private static AutorizacionIngreso autorizar = new AutorizacionIngreso();
	static HashCode hash = new HashCode();
	private static ConectionDB cdb = new ConectionDB();
	static Connection connect = cdb.getConnection();
	
	public static String ingresar(HttpServletRequest request) {
		String nu = request.getParameter("nu");
		String con = request.getParameter("con");
		String ce = request.getParameter("ce");
		String telef = request.getParameter("telef");
		String sex = request.getParameter("sex");
		String date = request.getParameter("date");
		String country = request.getParameter("country");
		String state = request.getParameter("state");
		String city = request.getParameter("city");
		
		try {
			if(autorizar.authCheck(nu, con, ce, telef, sex, country, state, city) == true) {
				sesion = request.getSession();
				sesion.setAttribute("nu", nu);
				sesion.setAttribute("con", con);
				sesion.setAttribute("ce", ce);
				sesion.setAttribute("telef", telef);
				sesion.setAttribute("sex", sex);
				sesion.setAttribute("date", date);
				sesion.setAttribute("country", country);
				sesion.setAttribute("state", state);
				sesion.setAttribute("city", city);
				return "";
				
			}else {
				System.out.println("Lo siento, No pudiste Iniciar Sesion");
				return"{\\\"message\\\": \\\"Lo siento, No pudiste Iniciar Sesion\\\", \\\"status\\\": 500}";
			}
		}catch (Exception e) {
			return "{\"message\": \"Lo siento, No pudistes Acceder\", \"status\": 500 }";
		}
	}

		
}
