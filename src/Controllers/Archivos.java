package Controllers;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.Part;

import org.json.JSONObject;

public class Archivos {
	
	
	public static String SubidaArchivos(String ce, Part archivo)throws ServletException, IOException {
		Part archivopart = archivo;
		String nombrearchivo = Paths.get(archivopart.getSubmittedFileName()).getFileName().toString();
		JSONObject js = new JSONObject();
		String correo = " "; 
		String filenombre = " ";
		correo = ce;
		filenombre = nombrearchivo;
		
		String path = " ";
		File subida = new File(path);
		if(subida.exists()) {
			
		}else{
			subida.mkdirs();
		}
		
		Path camino = Paths.get(path+filenombre);
		try(InputStream input = archivopart.getInputStream()){
			Files.copy(input, camino, StandardCopyOption.REPLACE_EXISTING);
		}
		try {
			String dburl = "jdbc:postgres://hhoqdegypgdtwz:72ebd0b977c1c4ead6ccebfe88fbacad7f550009e88e9abc7e1efd3aeca1a580@ec2-54-166-242-77.compute-1.amazonaws.com:5432/d4obl7d4nqmaka";
			String dbcon = "72ebd0b977c1c4ead6ccebfe88fbacad7f550009e88e9abc7e1efd3aeca1a580";
			String dbuser = "hhoqdegypgdtwz";
			Connection connect = null;
			PreparedStatement preparedstmt = null;
			
			if(!ce.equals("") && !filenombre.equals("")) {
				connect = DriverManager.getConnection(dburl, dbcon, dbuser);
				preparedstmt = connect.prepareStatement("INSERT INTO Archivo (nu,ce,nombrearchivo) values (?,?)");
				preparedstmt.setString(1, correo);
				preparedstmt.setString(2, filenombre);
				preparedstmt.executeUpdate();
				js.put("Mensaje", "Archivo Cargado Satisfactoriamente");
				js.put("Estado", 200);
				js.put("ce", ce);
				js.put("filenombre", filenombre);
				return js.toString();
			}else {
				js.put("Mensaje", "Ha Ocurrido Un Problema Al Momento De Cargar El Archivo Seleccionado");
				js.put("Estado", 500);
				return js.toString();
			}
		}catch(SQLException e){
			System.out.println(e.getMessage());
		}
		js.put("Mensaje", "Ha Ocurrido Un Problema Al Momento De Cargar El Archivo Seleccionado");
		js.put("Estado", 500);
		return js.toString();
	}
	
	public static String ObtencionDatos(String ce)throws ServletException{
		String dbnu= "hhoqdegypgdtwz";
		String dbcon= "72ebd0b977c1c4ead6ccebfe88fbacad7f550009e88e9abc7e1efd3aeca1a580";
		String dburl= "jdbc:postgres://hhoqdegypgdtwz:72ebd0b977c1c4ead6ccebfe88fbacad7f550009e88e9abc7e1efd3aeca1a580@ec2-54-166-242-77.compute-1.amazonaws.com:5432/d4obl7d4nqmaka";
		JSONObject js = new JSONObject();
		Connection connect = null;
		PreparedStatement preparedstmt = null;
		String correo = " ";
		correo  = ce;
		try {
			if(!ce.equals(" ")) {
				connect = DriverManager.getConnection(dburl, dbnu, dbcon);
				preparedstmt = connect.prepareStatement("SELECT * FROM register WHERE ce = ?");
				preparedstmt.setString(1, correo);
				ResultSet resultados = preparedstmt.executeQuery();
				js.put("Mensaje", "El Archivo Ha Sido Encontrado Satisfactoriamente");
				js.put("Estado", 200);
				while(resultados.next()) {
					js.accumulate("Archivos", resultados.getString(3));
				}
				return js.toString();
			}else {
				js.put("Estado", 500);
				js.put("Mensaje", "Usted No Posee Archivos Guardados Actualmente");
				return js.toString();
			}
		}catch (SQLException e) {
			e.printStackTrace();
		}
		js.put("Estado", 500);
		js.put("Mensaje", "Usted No Posee Archivos Guardados Actualmente");
		return js.toString();
	}
	
	public static String EliminacionArchivos(String ce, String nombrearchivo)throws ServletException{
		String dbnu= "hhoqdegypgdtwz";
		String dbcon= "72ebd0b977c1c4ead6ccebfe88fbacad7f550009e88e9abc7e1efd3aeca1a580";
		String dburl= "jdbc:postgres://hhoqdegypgdtwz:72ebd0b977c1c4ead6ccebfe88fbacad7f550009e88e9abc7e1efd3aeca1a580@ec2-54-166-242-77.compute-1.amazonaws.com:5432/d4obl7d4nqmaka";
		JSONObject js = new JSONObject();
		Connection connect = null;
		PreparedStatement preparedstmt = null;
		String archivo = "";
		archivo = nombrearchivo;
		String correo = "";
		correo = ce;
		try {
			if(!archivo.equals("")) {
				connect = DriverManager.getConnection(dburl, dbcon, dbnu);
				preparedstmt = connect.prepareStatement("DELETE FROM archivos WHERE ce = ? AND nombrearchivo = ?");
				preparedstmt.setString(1, correo);
				preparedstmt.setString(2, archivo);
				preparedstmt.executeUpdate();
				js.put("Memsaje", "Su Archivo Ha Sido Eliminado Satisfactoriamente");
				js.put("Estado", 200);
				return js.toString();
			}else {
				js.put("Estado", 500);
				js.put("Mensaje", "Ha Ocurrido Un Problema Al Momento de Eliminar El Archivo Seleccionado");
				return js.toString();
			}
		}catch (SQLException e) {
			e.printStackTrace();
		}
		js.put("Estado", 500);
		js.put("Mensaje", "Ha Ocurrido Un Problema Al Momento de Eliminar El Archivo Seleccionado");
		return js.toString();
	}

}
