package Helpers;

import java.io.InputStream;  
import java.util.Properties;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

public class PropertiesReader {
	private static Properties properties = new Properties();
	private static InputStream inputstream = null;
	private static String path = "src/Helpers/propertiesreader";
	
	
	public void Properties() {
		try {
		inputstream = new FileInputStream(path);
		properties.load(inputstream);
		
		if(inputstream != null) {
			properties.load(inputstream);
		}else {
			throw new FileNotFoundException("Archivo del Properties '"+path+"' ");
		}
		
		}catch(Exception e) {
			e.printStackTrace();
		}finally{
			try {
				inputstream.close();
			}catch (IOException e){
				e.printStackTrace();
			}
		}
	}
	
	public String getValue(String value) {
		
		return properties.getProperty(value);
	}

	public String getSQL(String producto) {
		
		return properties.getProperty(producto);
	}
}
