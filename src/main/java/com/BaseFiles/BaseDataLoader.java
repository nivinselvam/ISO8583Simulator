/*
 * This file loads the data that is required for the simulator to function.
 * It creates the app folder first when the application is launched and copies all the properties files into it.
 * It also loads the default values into the base variables from the properties files available inside the source code.
 */

package com.BaseFiles;

import java.io.File;
import java.nio.file.FileSystemException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.Map;

import javax.swing.JOptionPane;

import org.apache.log4j.Logger;

public class BaseDataLoader {

	private File fileToCopy;
	private Path destinationPath;
	private static Logger logger = Logger.getLogger(BaseDataLoader.class);

	// -------------------------------------------------------------------------------------------------------------
	/*
	 * This method creates the app folder and places the properties files of all the
	 * FEPS in the app folder. These are files that are stored internally and will
	 * be copied during the sim start into the user folder.
	 */
	// -------------------------------------------------------------------------------------------------------------
	public boolean createAppFolder() {
		String fileName = "";
		try {
			for (Map.Entry<String, String> currentEntry : Initializer.getFepPropertyFiles().entrySet()) {
				fileName = currentEntry.getValue();
				fileToCopy = new File(Initializer.getApplicationDefaultFilesPath() + "/" + fileName);
				destinationPath = Paths.get(Initializer.getFEPpropertiesFilesPath() + "/" + fileName);
				Files.copy(fileToCopy.toPath(), destinationPath, StandardCopyOption.REPLACE_EXISTING);
				logger.debug(currentEntry.getValue() + " file successfully copied into FEPproperty folder");
			}
			return true;
		} catch(FileSystemException e) {
			logger.fatal("Unable to access the Application default files folder. Please check if another instance is already running.");
			JOptionPane.showMessageDialog(null, "Unable to access the Application default files folder."+"\n"+"Please check if another instance is already running.");
			return false;
		} catch (Exception e) {
			logger.fatal("Unable to copy the " + fileName + " file into FEPproperty folder");
			JOptionPane.showMessageDialog(null, e.toString());
			return false;
		}

	}

}
