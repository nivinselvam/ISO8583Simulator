/*
 * This file loads the data that is required for the simulator to function.
 * It creates the app folder first when the application is launched and copies all the properties files into it.
 * It also loads the default values into the base variables from the properties files available inside the source code.
 */

package com;

import java.io.File;
import java.io.FileInputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import org.apache.log4j.Logger;

public class BaseDataLoader {

	public Properties property = new Properties();
	private File file;
	private File fileToCopy;
	private Path destinationPath;
	private List<String> propertyFileNames = new ArrayList<String>();
	private static Logger logger = Logger.getLogger(BaseDataLoader.class);
	
	//-------------------------------------------------------------------------------------------------------------
	/*
	 * This method creates the app folder and places the properties files of all the FEPS in the app folder.
	 * These are files that are stored internally and will be copied during the sim start into the user folder.
	 */
	//-------------------------------------------------------------------------------------------------------------
	public boolean createAppFolder() {
		try {
			file = new File(BaseConstants.appFolder);
			if (!file.exists()) {
				file.mkdir();
			}

			propertyFileNames.add("HPSConstants.properties");
			propertyFileNames.add("FCBConstants.properties");
			propertyFileNames.add("IncommConstants.properties");

			for (String currentPropertyFile : propertyFileNames) {
				fileToCopy = new File(currentPropertyFile);
				destinationPath = Paths.get(BaseConstants.appFolder + "\\" + currentPropertyFile);
				Files.copy(fileToCopy.toPath(), destinationPath, StandardCopyOption.REPLACE_EXISTING);
			}
			loadDefaultValues();
			return true;
		} catch (Exception e) {
			return false;
		}

	}

	//-------------------------------------------------------------------------------------------------------------
	/*
	 * This method loads the defaults values for the simulator to start functioning.
	 * This is read from the properties files that are stored internally.
	 */
	//-------------------------------------------------------------------------------------------------------------
	public void loadDefaultValues() {
		try {
			switch (Initializer.getFEPname()) {
			case "HPS":
				property.load(new FileInputStream(new File("HPSConstants.properties")));
				break;
			case "FCB":
				property.load(new FileInputStream(new File("FCBConstants.properties")));
				break;
			case "INCOMM":
				property.load(new FileInputStream(new File("IncommConstants.properties")));
				break;
			}

			Initializer.getBaseVariables().authorizationTransactionResponse = property
					.getProperty("authorizationTransactionResponse");
			Initializer.getBaseVariables().financialSalesTransactionResponse = property
					.getProperty("financialSalesTransactionResponse");
			Initializer.getBaseVariables().financialForceDraftTransactionResponse = property
					.getProperty("financialForceDraftTransactionResponse");
			Initializer.getBaseVariables().reversalTransactionResponse = property
					.getProperty("reversalTransactionResponse");
			Initializer.getBaseVariables().reconciliationTransactionResponse = property
					.getProperty("reconciliationTransactionResponse");

			Initializer.getBaseVariables().valueOfBitfield4 = property.getProperty("valueOfBitfield4");
			Initializer.getBaseVariables().valueOfBitfield37 = property.getProperty("valueOfBitfield37");
			Initializer.getBaseVariables().valueOfBitfield38 = property.getProperty("valueOfBitfield38");
			Initializer.getBaseVariables().ValueOfBitfield39Approval = property
					.getProperty("ValueOfBitfield39Approval");
			Initializer.getBaseVariables().ValueOfBitfield39Decline = property.getProperty("ValueOfBitfield39Decline");
			Initializer.getBaseVariables().ValueOfBitfield39Partial = property.getProperty("ValueOfBitfield39Partial");
			Initializer.getBaseVariables().ValueOfBitfield39Reversal = property
					.getProperty("ValueOfBitfield39Reversal");
			Initializer.getBaseVariables().ValueOfBitfield39Reconsillation = property
					.getProperty("ValueOfBitfield39Reconsillation");
			Initializer.getBaseVariables().valueOfBitfield44 = property.getProperty("valueOfBitfield44");
			Initializer.getBaseVariables().valueOfBitfield48 = property.getProperty("valueOfBitfield48");
			Initializer.getBaseVariables().valueOfBitfield54 = property.getProperty("valueOfBitfield54");
			Initializer.getBaseVariables().valueOfBitfield123 = property.getProperty("valueOfBitfield123");
		} catch (Exception e) {
			logger.warn("Unable to load default values into base variables");
		}

	}

}
