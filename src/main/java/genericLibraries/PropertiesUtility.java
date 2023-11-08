package genericLibraries;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

/**
 * This class contains reusable methods to read data from properties file
 * @author Rahul
 */
public class PropertiesUtility {

	private Properties property;
	
	/**
	 * This method is used to initialize properties file
	 * @param filePath
	 */
	public void propertiesInitialization(String filepath) {
		FileInputStream fis = null;
		try {
			fis=new FileInputStream(filepath);
		}
		catch(FileNotFoundException e) {
			e.printStackTrace();
		}
		property = new Properties();
		
		try {
			property.load(fis);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * This method is used to fetch data from properties file
	 * @param key
	 * @return
	 */
	public String readFromProperties(String key) {
		return property.getProperty(key);
	}
}
