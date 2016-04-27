package util4j.config;
import java.io.IOException;  
import java.util.Properties;

public class ConfigDB {
	private static Properties p = new Properties(); 
	static{  
		try {  
			p.load(ClassLoader.getSystemResourceAsStream("/config.properties"));  
		} catch (IOException e) {  
			e.printStackTrace();  
		}
	}  
     
	public static String getDriver(){  
		return p.getProperty("database.driver");  
	}
	public static String getUrl(){  
		return p.getProperty("database.url");  
	}
	public static String getUser(){  
		return p.getProperty("database.user");  
	}
	public static String getPwd(){  
		return p.getProperty("database.pwd");  
	}
}
