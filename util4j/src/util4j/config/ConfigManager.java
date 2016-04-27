package util4j.config;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class ConfigManager {
	private String Driver;
	private String Url;
	private String Username;
	private String Password;
	
	public ConfigManager()
	{
		String FILE_PATH_NAME="/config.properties"; 
		try 
		{
			InputStream in = getClass().getResourceAsStream(FILE_PATH_NAME);
			Properties props = new Properties();
			props.load(in);
			in.close();
			this.Driver = props.getProperty("database.driver");
			this.Url = props.getProperty("database.url");
			this.Username = props.getProperty("database.user");
			this.Password = props.getProperty("database.pwd");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	public String getDriver(){  
		return this.Driver;  
	}
	public String getUrl(){  
		return this.Url;  
	}
	public String getUser(){  
		return this.Username;  
	}
	public String getPwd(){  
		return this.Password;  
	}
}
