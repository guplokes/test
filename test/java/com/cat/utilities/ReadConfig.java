package com.cat.utilities;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import org.apache.commons.configuration.ConfigurationException;
import org.apache.commons.configuration.PropertiesConfiguration;

public class ReadConfig {
Properties Pro;
FileInputStream fis;
File src;
	
	public ReadConfig()
	{
		src=new File("./Config/config.properties");
		
		try {
			fis=new FileInputStream(src);
			
			Pro=new Properties();
			
			Pro.load(fis);
		} catch (Exception e) {
			System.out.println("Not able to load config file "+e.getMessage());
			e.printStackTrace();
		} 
	}
	
	public String getDataFromConfig(String KeyToSearch)
	{
		return Pro.getProperty(KeyToSearch);
	}

	public String getBrowser()
	{
		return Pro.getProperty("Browser");
	}
	
	public String getURL() 
	{
		return Pro.getProperty("URL");
	}
	
	public String getUserName() 
	{
		return Pro.getProperty("userName");
	}
	public String getPassword() 
	{
		return Pro.getProperty("password");
	}
	public long getElementWaitTime()
	{
		String waitTime=Pro.getProperty("waitTime");
		return Long.parseLong(waitTime);
	}
	
	public void updateKeyValue(String Key, String Value) {
		 
		try {
			PropertiesConfiguration conf = new PropertiesConfiguration("./Config/config.properties");
			conf.setProperty(Key, Value);
			conf.save();
			fis.close();
			fis = new FileInputStream(src);
			Pro.load(fis);
			
		} catch (ConfigurationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		   
	}
}
