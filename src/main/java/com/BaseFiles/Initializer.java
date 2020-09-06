package com.BaseFiles;

import java.awt.EventQueue;
import java.io.File;
import java.net.URISyntaxException;
import java.util.HashMap;
import java.util.Map;
import javax.swing.JOptionPane;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

public class Initializer {
	private static String fepName;
	private static boolean guiEnabled;
	private static String applicationDefaultFilesPath;
	private static String FEPpropertiesFilesPath;
	private static ServerInitializer server;
	private static Converter converter;
	private static BitFieldData bitfieldData;
	private static BaseVariables variables;
	private static BaseConstants constants;
	private static BaseDataLoader dataLoader;
	public static BaseFileWatcher fileWatcher;
	private static AppGUI appGui;
	private static int portNumber;
	private static Map<String, String> fepPropertyFiles;
	private static Logger logger = Logger.getLogger(Initializer.class);

	public static void main(String[] args) {
		fepName = "HPS";
		guiEnabled = true;
		bitfieldData = new BitFieldData();
		converter = new Converter();
		// GUI is instantiated here to get the logs displayed in the runtime logs window
		if (guiEnabled) {
			appGui = new AppGUI();
		}
		applicationDefaultFilesPath = getApplicationFolder() + "ApplicationDefaultFiles";
		FEPpropertiesFilesPath = getApplicationFolder() + "FEPproperties";
		PropertyConfigurator.configure(applicationDefaultFilesPath + "\\log4j.properties");
		fepPropertyFiles = new HashMap<String, String>();
		server = null;
		mapFEPtoPropertyFile();

		fileWatcher = new BaseFileWatcher();
		dataLoader = new BaseDataLoader();
		if (dataLoader.createAppFolder()) {
			constants = new BaseConstants();
			variables = new BaseVariables();
			fileWatcher.start();
			if (guiEnabled) {
				EventQueue.invokeLater(new Runnable() {
					public void run() {
						try {
							appGui.getFrmISO8583Simulator().setVisible(true);
						} catch (Exception e) {
							e.printStackTrace();
						}
					}
				});
			} else {
				server = new ServerInitializer();
				server.start();
			}
		}
	}

	// ----------------------------------------------------------------------------------------------------------
	/*
	 * This method should be updated on inclusion of any new fep and its associated
	 * property file.
	 */
	// ----------------------------------------------------------------------------------------------------------
	private static void mapFEPtoPropertyFile() {
		fepPropertyFiles.put("Common", "CommonVariables.properties");
		fepPropertyFiles.put("HPS", "HPSConstants.properties");
		fepPropertyFiles.put("FCB", "FCBConstants.properties");
		fepPropertyFiles.put("INCOMM", "IncommConstants.properties");
		fepPropertyFiles.put("X9", "X9Constants.properties");
	}

	public static String getApplicationDefaultFilesPath() {
		return applicationDefaultFilesPath;
	}

	public static String getFEPpropertiesFilesPath() {
		return FEPpropertiesFilesPath;
	}

	public static Map<String, String> getFepPropertyFiles() {
		return fepPropertyFiles;
	}

	public static AppGUI getAppGui() {
		return appGui;
	}

	public static String getFEPname() {
		return fepName;
	}

	public static BaseConstants getBaseConstants() {
		return constants;
	}

	public static void setBaseConstants(BaseConstants constants) {
		Initializer.constants = constants;
	}

	public static void setFEPname(String fepName) {
		Initializer.fepName = fepName;
	}

	public static boolean isGUIenabled() {
		return guiEnabled;
	}

	public static ServerInitializer getServer() {
		return server;
	}

	public static void setServer(ServerInitializer server) {
		Initializer.server = server;
	}

	public static Converter getConverter() {
		return converter;
	}

	public static BitFieldData getBitfieldData() {
		return bitfieldData;
	}

	public static void setBitfieldData(BitFieldData bitfieldData) {
		Initializer.bitfieldData = bitfieldData;
	}

	public static BaseVariables getBaseVariables() {
		return variables;
	}

	public static int getPortNumber() {
		return portNumber;
	}

	public static void setPortNumber(int portNumber) {
		Initializer.portNumber = portNumber;
	}

	public static BaseDataLoader getBaseDataLoader() {
		return dataLoader;
	}

	// ---------------------------------------------------------------------------------------------------------
	/*
	 * This method is used to get the path in which the application file are placed.
	 */
	// ---------------------------------------------------------------------------------------------------------
	public static final String getApplicationFolder() {
		File file;
		String applicationFolder = "";
		boolean failed = false;

		// Trying to get the path where the file is saved
		try {
			file = new File(Initializer.class.getProtectionDomain().getCodeSource().getLocation().toURI().getPath());

			if (file.isFile() || file.getPath().endsWith(".jar") || file.getPath().endsWith(".zip")) {
				applicationFolder = file.getParent();
			} else {
				applicationFolder = file.getPath();
			}
		} catch (URISyntaxException ex) {
			failed = true;
			logger.fatal("Cannot figure out base path for class with way (1): " + ex);
		}

		// The above failed?
		if (failed) {
			try {
				file = new File(Initializer.class.getClassLoader().getResource("").toURI().getPath());
				applicationFolder = file.getAbsolutePath();

				// the below is for testing purposes...
				// starts with File.separator?
				// String l = local.replaceFirst("[" + File.separator +
				// "/\\\\]", "")
			} catch (URISyntaxException ex) {
				logger.fatal("Cannot figure out base path for class with way (2): " + ex);
			}
		}

		// fix to run inside eclipse
		if (applicationFolder.endsWith(File.separator + "lib") || applicationFolder.endsWith(File.separator + "bin")
				|| applicationFolder.endsWith("bin" + File.separator)
				|| applicationFolder.endsWith("lib" + File.separator)) {
			applicationFolder = applicationFolder.substring(0, applicationFolder.length() - 4);
		}
		// fix to run inside netbeans
		if (applicationFolder.endsWith(File.separator + "build" + File.separator + "classes")) {
			applicationFolder = applicationFolder.substring(0, applicationFolder.length() - 14);
		}
		// end fix
		if (!applicationFolder.endsWith(File.separator)) {
			applicationFolder = applicationFolder + File.separator;
		}
		return applicationFolder;
	}
}
