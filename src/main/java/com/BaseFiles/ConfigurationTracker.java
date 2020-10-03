/*
 * This file loads the data that is required for the simulator to function.
 * It creates the app folder first when the application is launched and copies all the properties files into it.
 * It also loads the default values into the base variables from the properties files available inside the source code.
 */

package com.BaseFiles;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Properties;

import javax.swing.JOptionPane;

import org.apache.log4j.Logger;

public class ConfigurationTracker {

	private Logger logger = Logger.getLogger(ConfigurationTracker.class);
	private Map<String, String> fepPropertiesMap = new LinkedHashMap<String, String>();
	private Properties property = new Properties();


	// -------------------------------------------------------------------------------------------------------------
	/*
	 * This method is used to create a map with all the FEP properties.
	 */
	// -------------------------------------------------------------------------------------------------------------

	public void createPropertiesMap() {
		try {
			property.load(new FileInputStream(new File(Initializer.getApplicationFolder() + Initializer.getFepPropertyFiles().get(Initializer.getFEPname()))));
		} catch (FileNotFoundException e) {
			logger.error(e.toString());
		} catch (IOException e) {
			logger.error(e.toString());
		}
		for (Object prop : property.keySet()) {
			fepPropertiesMap.put(prop.toString(), property.getProperty(prop.toString()));
		}
	}
}
