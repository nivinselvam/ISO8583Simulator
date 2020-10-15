/*
 * This file loads the data that is required for the simulator to function.
 * It creates the app folder first when the application is launched and copies all the properties files into it.
 * It also loads the default values into the base variables from the properties files available inside the source code.
 */

package com.BaseFiles;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.Set;

import org.apache.log4j.Logger;

public class ConfigurationTracker {

	private Logger logger = Logger.getLogger(ConfigurationTracker.class);
	private Map<String, String> fepPropertiesMap = new LinkedHashMap<String, String>();
	private List<Object> fepPropertiesFromConfigFile;
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

			// FEP name should be updated into the source code to start processing the FEP.
			Initializer.setFEPname(fepPropertiesMap.get("fepName"));
			Initializer.setPortNumber(Integer.parseInt(fepPropertiesMap.get("portNumber")));

			property.clear();

			property.load(new FileInputStream(new File(Initializer.getPropertiesFilePath()
					+ Initializer.getFepPropertyFiles().get(Initializer.getFEPname()))));

			for (Object prop : property.keySet()) {
				fepPropertiesMap.put(prop.toString(), property.getProperty(prop.toString()));
			}
			property.clear();
		} catch (FileNotFoundException e) {
			logger.error(e.toString());
		} catch (IOException e) {
			logger.error(e.toString());
		}
	}

	// -------------------------------------------------------------------------------------------------------------
	/*
	 * This method is used to update the fepPropertiesMap with the values from fep
	 * property file
	 */
	// -------------------------------------------------------------------------------------------------------------
	public void updatePropertiesMapFromFepPropertyFile(String fepName, String portNumber) {
		try {
			fepPropertiesMap.clear();
			fepPropertiesMap.put("fepName", fepName);
			fepPropertiesMap.put("portNumber", portNumber);
			property.load(new FileInputStream(
					new File(Initializer.getPropertiesFilePath() + Initializer.getFepPropertyFiles().get(fepName))));
			for (Object prop : property.keySet()) {
				fepPropertiesMap.put(prop.toString(), property.getProperty(prop.toString()));
			}
			property.clear();
		} catch (FileNotFoundException e) {
			logger.error(e.toString());
		} catch (IOException e) {
			logger.error(e.toString());
		}
	}

	// -------------------------------------------------------------------------------------------------------------
	/*
	 * This method is used to update the fepPropertiesMap with the configuration
	 * changes made by the user in the update configuration file
	 */
	// -------------------------------------------------------------------------------------------------------------
	public void updatePropertiesMapFromConfigurationFile() {
		// This boolean variable is used to determine if a configuration change was made
		boolean configChanged = false, serverStopRequired = false, serverStopped = false;
		try {
			property.load(
					new FileInputStream(new File(Initializer.getApplicationFolder() + Initializer.configFileName)));
		} catch (FileNotFoundException e) {
			logger.error("Unable to access the update configuration file in the application path");
		} catch (IOException e) {
			logger.error(e.toString());
		}

		fepPropertiesFromConfigFile = Arrays.asList(property.keySet().toArray());

		// If the Config File contains the FEP to be changed, then that has to be
		// prioritized as the first change to make sure the config is written in the
		// correct FEP property file
		if (fepPropertiesFromConfigFile.contains("fepName")) {
			if (Initializer.getFepPropertyFiles().containsKey(property.getProperty("fepName"))) {
				updatePropertiesMapFromFepPropertyFile(property.getProperty("fepName"),
						String.valueOf(Initializer.getPortNumber()));
			}
		}

		for (Object prop : fepPropertiesFromConfigFile) {
			if (prop.toString().equals("fepName")) {
				serverStopRequired = true;
				configChanged = true;
			} else if (prop.toString().equals("portNumber")) {
				int portNumber = Integer.parseInt(property.getProperty("portNumber"));
				if (portNumber > 1024 && portNumber < 65536) {
					fepPropertiesMap.put("portNumber", String.valueOf(portNumber));
				}
				serverStopRequired = true;
				configChanged = true;
				fepPropertiesMap.put(prop.toString(), property.getProperty(prop.toString()));
			} else if (fepPropertiesMap.containsKey(prop.toString())) {
				configChanged = true;
				fepPropertiesMap.put(prop.toString(), property.getProperty(prop.toString()));
			} 
		}

		if (!configChanged) {
			logger.info(
					"Configuration was not changed. If a change was intented, please check and correct field name in the update configuration file");
		} else {
			if (serverStopRequired) {
				try {
					Initializer.getServer().getServerSocket().close();
					serverStopped = true;
					if (Initializer.isGUIenabled()) {
						Initializer.getAppGui().stopServerGUIChanges();
					}
					reloadConfiguration();
				} catch (Exception e) {
					logger.info("Server socket is not in closable state.");
					reloadConfiguration();
				}
				if (serverStopped) {
					try {
						Initializer.setServer(new ServerInitializer());
						if (Initializer.isGUIenabled()) {
							Initializer.getAppGui().startServerGUIChanges();
						}
					} catch (Exception e) {
						logger.error("Unable start the server after configuration update");
					}
				}

			} else {
				reloadConfiguration();
			}

		}
		property.clear();
	}

	private void reloadConfiguration() {
		Initializer.setFEPname(Initializer.getConfigurationTracker().getFepPropertiesMap().get("fepName"));
		Initializer.setPortNumber(Integer
				.parseInt(Initializer.getConfigurationTracker().getFepPropertiesMap().get("portNumber")));
		Initializer.getBaseConstants().loadConstantValues();
		Initializer.getBaseVariables().loadDefaultValues();
		updateCommonVariablesFile(Initializer.getFEPname(), String.valueOf(Initializer.getPortNumber()));
		updatePropertiesFileFromMap();
	}

	// -------------------------------------------------------------------------------------------------------------
	/*
	 * This method is used to update the fepPropertiesMap with the configuration
	 * changes made on the GUI. This will only call the method in APPGUI class which
	 * does the real work. It has been called here to make sure all the fep
	 * properties mapping are done using this class.
	 */
	// -------------------------------------------------------------------------------------------------------------
	public void updatePropertiesMapFromGUI() {
		Initializer.getAppGui().updateFepPropertiesMapFromGUI();
	}

	// -------------------------------------------------------------------------------------------------------------
	/*
	 * This method is used to update the fep variables properties files with values
	 * that are available in the fep properties map.
	 */
	// -------------------------------------------------------------------------------------------------------------
	public void updatePropertiesFileFromMap() {
		try {
			property.load(new FileInputStream(new File(Initializer.getPropertiesFilePath()
					+ Initializer.getFepPropertyFiles().get(Initializer.getFEPname()))));
			for (String currentKey : property.stringPropertyNames()) {
				property.setProperty(currentKey,
						Initializer.getConfigurationTracker().fepPropertiesMap.get(currentKey));
			}
			property.store(new FileOutputStream(new File(Initializer.getPropertiesFilePath()
					+ Initializer.getFepPropertyFiles().get(Initializer.getFEPname()))), "");
		} catch (FileNotFoundException e) {
			logger.error(e.toString());
		} catch (IOException e) {
			logger.error(e.toString());
		}
		property.clear();
	}

	// -------------------------------------------------------------------------------------------------------------
	/*
	 * This method is used to update the common variables files with values that are
	 * changed by the user
	 */
	// -------------------------------------------------------------------------------------------------------------
	public void updateCommonVariablesFile(String fepName, String portNumber) {
		try {
			property.setProperty("fepName", fepName);
			property.setProperty("portNumber", portNumber);

			property.store(
					new FileOutputStream(new File(
							Initializer.getPropertiesFilePath() + Initializer.getFepPropertyFiles().get("Common"))),
					"");
		} catch (FileNotFoundException e) {
			logger.error(e.toString());
		} catch (IOException e) {
			logger.error(e.toString());
		}
		property.clear();
	}
}