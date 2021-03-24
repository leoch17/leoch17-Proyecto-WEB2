package Controllers;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import java.sql.*;

import org.json.JSONObject;

import Helpers.ConectionDB;
import Helpers.HashCode;

public class Usuario {	
	static HashCode hash = new HashCode();
	private static ConectionDB cdb = new ConectionDB();
	static Connection connect = cdb.getConnection();

	public String actualizar(HttpServletRequest request, String[] obj) {
		PreparedStatement ps = null;
		Connection connect = null;
		String nu = request.getParameter("nombre de usuario");
		JSONObject js = new JSONObject();
		String dburl = "jdbc:postgres://hhoqdegypgdtwz:72ebd0b977c1c4ead6ccebfe88fbacad7f550009e88e9abc7e1efd3aeca1a580@ec2-54-166-242-77.compute-1.amazonaws.com:5432/d4obl7d4nqmaka";
		String dbcon = "72ebd0b977c1c4ead6ccebfe88fbacad7f550009e88e9abc7e1efd3aeca1a580";
		String dbuser = "hhoqdegypgdtwz";
		
		try {
			connect = DriverManager.getConnection(dburl,dbuser,dbcon);
			ps = connect.prepareStatement("SELECT * FROM register WHERE Nombre de Usuario = ?");
			ps.setString(1, nu);
			ResultSet resultado = ps.executeQuery();
			
			while(resultado.next()) {
				if(nu.equals(resultado.getString(2))) {
					js.put("mensaje", "La modificación de su perfil se ha realizado exitosamente");
					js.put("estado", 200);
					js.put("Nombre de Usuario", resultado.getString(2));
					js.put("Telefono", resultado.getString(3));
					return js.toString();
				}else {
					return "";
				}
			}
			resultado.close();
			connect.close();
		}catch (Exception e){
			System.out.println("Lo siento, se ha presentado un problema de conexion"+e.getMessage());
			e.printStackTrace();
		}
		return null;
	}

	public String eliminar(HttpServletRequest request) {
		String message = "";
    	boolean producto = false;
    	Cookie cookies[] = request.getCookies();
    	String nu = "";
    	String con = "";
    	
    	for(Cookie c: cookies) {
    		if(c.getName().equals("nu")) {
    			nu = c.getValue();
    		}
    		
    		if(c.getName().equals("con")) {
    			con = c.getValue();
    		}
    		
    		
    	}
    	try {
    		String hashpass = hash.generateHash(con);
    		producto = ConectionDB.psDelete(connect, nu, hashpass);
    		
    		if(producto == true) {
    			message = "{\"message\": \"Delete Exitoso\", "
   				 	 + "\"status\": 200 }";
        	}
    		
		} catch (Exception e) {
			message = "{\"message\": \"Delete Fallido\", "
				 	 + "\"status\": 503 }";
		}
    	
    	return message;
	}

	public boolean sesionfinalizada(HttpServletRequest request, HttpServletResponse response) {
		String notificacion = request.getParameter("notificacion");
		JSONObject js = new JSONObject();
		if(notificacion.equals("Su sesion ha finalizado")) {
			HttpSession sesion = request.getSession();
			sesion.invalidate();
			js.put("notificacion", "Su sesion ha finalizado");
			js.put("estado", 200);
			return js.toString() != null;
		}else {
			js.put("notificacion", "Lo siento, se ha presentado un problema al finalizar la sesion");
			js.put("estado", 500);
			return js.toString() != null;
		}
	}

	public String credenciales(HttpServletRequest request) {
		Cookie cookies[] = request.getCookies();
    	String nu = "";
    	String con = "";
    	String ce = "";
    	String telef = "";
    	String sex = "";
    	String date = "";
    	String country = "";
    	String state = "";
    	String city = "";
    	
    	for(Cookie c : cookies) {
    		if(c.getName().equals("nu")) {
    			nu = c.getValue();
    		}
    		
    		if(c.getName().equals("con")) {
    			con = c.getValue();
    		}
    		
    		if(c.getName().equals("ce")) {
    			ce = c.getValue();
    		}
    		
    		if(c.getName().equals("telef")) {
    			telef = c.getValue();
    		}
    		
    		if(c.getName().equals("sex")) {
    			sex = c.getValue();
    		}
    		
    		if(c.getName().equals("date")) {
    			date = c.getValue();
    		}
    		
    		if(c.getName().equals("country")) {
    			country = c.getValue();
    		}
    		
    		if(c.getName().equals("state")) {
    			state = c.getValue();
    		}
    		
    		if(c.getName().equals("city")) {
    			city = c.getValue();
    		}
    	}
    	
    	
    	System.out.println(nu + "\n" + con + "\n" + ce + "\n" + telef + "\n" + sex + "\n" + date);
    	String message = "{\"nombre de usuario\":   \""  +  nu +  "\", \"contraseña\": \"" + con + "\", \"correo electronico\": \"" + ce + "\", \"telefono\": \"" + telef + "\", \"sexo\": \"" + sex + "\", \"fecha de nacimiento\": \"" + date + "\", \"pais\": \"" + country + "\", \"estado\": \"" + state + "\", \"ciudad\": \"" + city + "\", \"status\": \"200\"}";

    	return message;
    }
	
	public String Login(String nu, String co, HttpServletRequest request, HttpServletResponse response) {
		String pass = hash.generateHash(co);
		String[] objeto = {nu, pass};
		String mensaje = " ";
		
		try {
			boolean product = cdb.Login(objeto, connect);
			if(product == true) {
				HttpSession sesion = request.getSession();
				sesion.setAttribute("nu", nu);
				String[] objeto0 = ConectionDB.DatosLogin(nu, hash.generateHash(co));
				
				Cookie cook = new Cookie("nu", objeto0[0]);
				response.addCookie(cook);
				
				Cookie cook1 = new Cookie("co", co);
				response.addCookie(cook1);
				
				Cookie cook2 = new Cookie("ce", objeto0[2]);
				response.addCookie(cook2);
				
				Cookie cook3 = new Cookie("telef", objeto0[3]);
				response.addCookie(cook3);
				
				Cookie cook4 = new Cookie("sex", objeto0[4]);
				response.addCookie(cook4);
				
				Cookie cook5 = new Cookie("date", objeto0[5]);
				response.addCookie(cook5);
				
				Cookie cook6 = new Cookie("country", objeto0[6]);
				response.addCookie(cook6);
				
				Cookie cook7 = new Cookie("state", objeto0[7]);
				response.addCookie(cook7);
				
				Cookie cook8 = new Cookie("city", objeto0[8]);
				response.addCookie(cook8);
				
				mensaje= "{\"mensaje\": \"Ha Ingresado Exitosamente\", "
					 	 + "\"estado\": 200 }";
			}else {
				mensaje = "{\"mensaje\": \"Ha ocurrido un problema para poder Ingresar\", "
						 + "\"estado\": 503 }";
			}
		}catch (Exception e){
			mensaje = "{\"mensaje\": \"Ha ocurrido un problema para poder Ingresar\", "
					 + "\"estado\": 503 }";
			e.printStackTrace();
		}
		
		
		return mensaje;
	}
	

	}


