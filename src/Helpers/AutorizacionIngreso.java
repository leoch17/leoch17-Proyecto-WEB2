package Helpers;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class AutorizacionIngreso {
	public boolean authCheck(String nu, String con, String ce, String telef, String sex, String country, String state,
			String city) {
		Connection connect = null;
		PreparedStatement ps = null;
		String dbnu= "hhoqdegypgdtwz";
		String dbcon= "72ebd0b977c1c4ead6ccebfe88fbacad7f550009e88e9abc7e1efd3aeca1a580";
		String dburl= "jdbc:postgres://hhoqdegypgdtwz:72ebd0b977c1c4ead6ccebfe88fbacad7f550009e88e9abc7e1efd3aeca1a580@ec2-54-166-242-77.compute-1.amazonaws.com:5432/d4obl7d4nqmaka";
		String usuario = " ";
		usuario = nu;
		
		try {
			connect = DriverManager.getConnection(dbnu,dbcon,dburl);
			ps = connect.prepareStatement("SELECT * FROM register WHERE Nombre de Usuario = ?");
			ps.setString(1, dburl);
			ResultSet resultado = ps.executeQuery();
			
			while(resultado.next()) {
				if(usuario.equals(resultado.getString(3))) {
					return false;
				}else {
					return false;
				}
			}
			resultado.close();
			connect.close();
		}catch(Exception e) {
			System.out.println("Lo siento, se ha presentado un problema de conexion"+e.getMessage());
			e.printStackTrace();
			return false;
		}
		return false;
	}

}
