package Helpers;

import java.sql.Connection;
import java.sql.*; 
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.PreparedStatement;

public class ConectionDB {
	private static PropertiesReader propertiesrd = new PropertiesReader();
	private static Connection connection = null;
	
	
		public Connection getConnection() {
		try {
			String dbnu = propertiesrd.getValue("dbnu");
			String dbcon = propertiesrd.getValue("dbcon");
			String dburl = propertiesrd.getValue("dburl");
			String dbdriver = propertiesrd.getValue("dbdriver");
			
			Class.forName(dbdriver);
			connection = DriverManager.getConnection(dbnu, dbcon, dburl);
			if(connection != null) {
				System.out.println("Conexion creada exitosamente");
			}else {
				System.out.println("Imposible crear conexion");
			}
		}catch(SQLException e){
			e.printStackTrace();
		}catch(Exception e) {
			e.printStackTrace();
		}
		return connection;
	}
		
		public boolean PreparedStatement(Connection conectar, String[]obj) {
			
			try {
				String propertiesreader = propertiesrd.getSQL("insert");
				PreparedStatement prepareds = conectar.prepareStatement(propertiesreader);
				prepareds.setString(1, obj[0]);
				prepareds.setString(2, obj[1]);
				prepareds.setString(3, obj[2]);
				prepareds.setString(4, obj[3]);
				prepareds.setString(5, obj[4]);
				prepareds.setString(6, obj[5]);
				prepareds.executeUpdate();
				prepareds.close();
			}catch(SQLException e){
				e.printStackTrace();
			}
			return false;
			
		}

		public static boolean psDelete(Connection connect, String nu, String hashpass) {
			String sentence = propertiesrd.getSQL("delete");
			boolean result = false;
			
			try {
				PreparedStatement pst = null;
				pst = connection.prepareStatement(sentence);
				pst.setString(1, nu);
				pst.setString(2, hashpass);
				result = true;
				pst.execute();
				pst.close();
				
			} catch (Exception e) {
				System.out.println(e);
				result = false;
			}
			
			
			return result;		
			}
		
		public boolean Login(String[] objeto, Connection connect) {
			boolean producto = false;
			
			try {
				String sentencia = propertiesrd.getSQL("Login");
				PreparedStatement preparedstmt;
				ResultSet resultados;
				preparedstmt = connect.prepareStatement(sentencia);
				preparedstmt.setString(1, objeto[0]);
				preparedstmt.setString(2, objeto[1]);
				resultados = preparedstmt.executeQuery();
				if (resultados.next()) {
					producto = true;
				}
			}catch (Exception e) {
				producto  = false;
			}
			return producto;
		}
		
		public static String[] DatosLogin (String nu, String co) {
			String[] objeto = {"","","","","","","","",""};
			try {
				String sentencia = propertiesrd.getSQL("Login");
				PreparedStatement preparedstmt;
				ResultSet resultados;
				preparedstmt = connection.prepareStatement(sentencia);
				preparedstmt.setString(1, nu);
				preparedstmt.setString(2, co);
				resultados = preparedstmt.executeQuery();
				while(resultados.next()) {
					for(int i=0; i<9; i++) {
						objeto[i]=resultados.getString(i+1);
						System.out.println(objeto[i]);
					}
				}
			}catch (Exception e) {
				System.out.println(e);
			}
			return null;
		}
}
