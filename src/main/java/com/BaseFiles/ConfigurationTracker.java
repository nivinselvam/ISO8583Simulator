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
import org.apache.log4j.Logger;

public class ConfigurationTracker {

	private Logger logger = Logger.getLogger(ConfigurationTracker.class);
	private Map<String, String> fepPropertiesMap = new LinkedHashMap<String, String>();
	private Properties property = new Properties();

	public Map<String, String> getFepPropertiesMap() {
		return fepPropertiesMap;
	}

	// -------------------------------------------------------------------------------------------------------------
	/*
	 * This method is used to create a map with all the FEP properties.
	 */
	// -------------------------------------------------------------------------------------------------------------

	public void createPropertiesMap() {
		try {
			property.load(new FileInputStream(
					new File(Initializer.getPropertiesFilePath() + Initializer.getFepPropertyFiles().get("Common"))));

			for (Object prop : property.keySet()) {
				fepPropertiesMap.put(prop.toString(), property.getProperty(prop.toString()));
			}
			
			//FEP name should be updated into the source code to start processing the FEP.
			Initializer.setFEPname(fepPropertiesMap.get("fepName"));
			Initializer.setPortNumber(Integer.parseInt(fepPropertiesMap.get("portNumber")));

			property.clear();

			property.load(new FileInputStream(new File(Initializer.getPropertiesFilePath()
					+ Initializer.getFepPropertyFiles().get(Initializer.getFEPname()))));

			for (Object prop : property.keySet()) {
				fepPropertiesMap.put(prop.toString(), property.getProperty(prop.toString()));
			}

		} catch (FileNotFoundException e) {
			logger.error(e.toString());
		} catch (IOException e) {
			logger.error(e.toString());
		}
	}

	// -------------------------------------------------------------------------------------------------------------
	/*
	 * This method is used to update the fepPropertiesMap with the configuration
	 * changes made by the user
	 */
	// -------------------------------------------------------------------------------------------------------------
	public void updatePropertiesMap() {
		// This boolean variable is used to determine if a configuration change was made
		boolean valueChanged = false;
		try {
			property.load(
					new FileInputStream(new File(Initializer.getApplicationFolder() + Initializer.configFileName)));
		} catch (FileNotFoundException e) {
			logger.error("Unable to access the update configuration file in the application path");
		} catch (IOException e) {
			logger.error(e.toString());
		}

		for (Object prop : property.keySet()) {
			if (prop.toString().equals("fepName") || prop.toString().equals("portNumber")) {
				valueChanged = true;
			} else if (fepPropertiesMap.containsKey(prop.toString())) {
				valueChanged = true;
				fepPropertiesMap.put(prop.toString(), property.getProperty(prop.toString()));
			}
		}

		if (!valueChanged) {
			logger.info(
					"Configuration was not changed. If a change was intented, please check and correct field name in the update configuration file");
		}
	}
}
