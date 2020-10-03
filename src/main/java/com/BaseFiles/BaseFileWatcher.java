/*
 * This class is used to monitor the app folder.
 * This triggers the data reloading whenever there is a change in values.
 * This is run as a separate thread since the folder should be monitored parallely when the execution happens in the simulator
 */

package com.BaseFiles;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.FileSystems;
import java.nio.file.Paths;
import java.nio.file.StandardWatchEventKinds;
import java.nio.file.WatchEvent;
import java.nio.file.WatchKey;
import java.nio.file.WatchService;
import java.util.Properties;

import org.apache.log4j.Logger;

public class BaseFileWatcher extends Thread {
	Properties property = new Properties();
	private static Logger logger = Logger.getLogger(BaseFileWatcher.class);
	private boolean isServerRunning;

	public void run() {
		watchFolder();
	}

	// -------------------------------------------------------------------------------------------------------------
	/*
	 * This method creates the app folder and places the properties files of all the
	 * FEPS in the app folder. These are files that are stored internally and will
	 * be copied during the sim start into the user folder.
	 */
	// -------------------------------------------------------------------------------------------------------------
	public void watchFolder() {
		WatchService watchService;
		try {
			watchService = FileSystems.getDefault().newWatchService();
			Paths.get(Initializer.getApplicationFolder()).register(watchService,
					StandardWatchEventKinds.ENTRY_MODIFY);
			do {
				WatchKey watchKey = watchService.take();
				Thread.sleep(50);
				for (WatchEvent event : watchKey.pollEvents()) {
					WatchEvent.Kind kind = event.kind();
					if (StandardWatchEventKinds.ENTRY_MODIFY.equals(kind)) {
						if(event.context().toString().equals("UpdateConfiguration.properties")) {
							readUpdatedFile(event.context().toString());
						}						
					}
				}
				watchKey.reset();
			} while (true);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	// -------------------------------------------------------------------------------------------------------------
	/*
	 * This method creates the app folder and places the properties files of all the
	 * FEPS in the app folder. These are files that are stored internally and will
	 * be copied during the sim start into the user folder.
	 */
	// -------------------------------------------------------------------------------------------------------------
	private void readUpdatedFile(String fileName) throws IOException {
		property.load(new FileInputStream(new File(Initializer.getApplicationFolder()+"\\"+ fileName)));
		if (fileName.equals(Initializer.getFepPropertyFiles().get("Common"))) {
			isServerRunning = true;
			if (!Initializer.getFEPname().equals(property.getProperty("fepName"))
					|| !String.valueOf(Initializer.getPortNumber()).equals(property.getProperty("portNumber"))) {

				try {
					//Trying to close the server socket if the server is already running.
					Initializer.getServer().getServerSocket();
					Initializer.getServer().getServerSocket().close();
					isServerRunning = false;
				} catch (NullPointerException e) {
					// This is executed when the server is not running currently
					if (!Initializer.getFEPname().equals(property.getProperty("fepName"))) {
						Initializer.setFEPname(property.getProperty("fepName"));
						Initializer.setBitfieldData(new BitFieldData());
						Initializer.setBaseConstants(new BaseConstants());
						reloadBaseVariables();
					}
					if (!String.valueOf(Initializer.getPortNumber()).equals(property.getProperty("portNumber"))) {
						Initializer.setPortNumber(Integer.parseInt(property.getProperty("portNumber")));
					}
				}
				// This is executed when the server was running, and was stopped for updating
				// the values
				if (!Initializer.getFEPname().equals(property.getProperty("fepName"))) {
					Initializer.setFEPname(property.getProperty("fepName"));
					Initializer.setBitfieldData(new BitFieldData());
					Initializer.setBaseConstants(new BaseConstants());
					reloadBaseVariables();
				}
				if (!String.valueOf(Initializer.getPortNumber()).equals(property.getProperty("portNumber"))) {
					Initializer.setPortNumber(Integer.parseInt(property.getProperty("portNumber")));
				}

				if (!isServerRunning) {
					Initializer.setServer(new ServerInitializer());
					Initializer.getServer().start();
				}
			}

		} else {
			reloadBaseVariables();
		}
	}

	private void reloadBaseVariables() {
		try {
			property.load(new FileInputStream(new File(Initializer.getApplicationFolder()+"\\"+ Initializer.getFepPropertyFiles().get(Initializer.getFEPname()))));
		} catch (IOException e) {
			logger.warn("Unable to load default values into base variables");
			System.out.println("Unable to load default values into base variables");
		}
		Initializer.getBaseVariables().sendResponse = property.getProperty("sendResponse");
		
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
		
		Initializer.getBaseVariables().isHalfApprovalRequired = property.getProperty("isHalfApprovalRequired");
		Initializer.getBaseVariables().valueOfBitfield4 = property.getProperty("valueOfBitfield4");
		Initializer.getBaseVariables().valueOfBitfield37 = property.getProperty("valueOfBitfield37");
		Initializer.getBaseVariables().valueOfBitfield38 = property.getProperty("valueOfBitfield38");
		Initializer.getBaseVariables().ValueOfBitfield39Approval = property.getProperty("ValueOfBitfield39Approval");
		Initializer.getBaseVariables().ValueOfBitfield39Decline = property.getProperty("ValueOfBitfield39Decline");
		Initializer.getBaseVariables().ValueOfBitfield39Partial = property.getProperty("ValueOfBitfield39Partial");
		Initializer.getBaseVariables().ValueOfBitfield39Reversal = property.getProperty("ValueOfBitfield39Reversal");
		Initializer.getBaseVariables().ValueOfBitfield39Reconciliation = property
				.getProperty("ValueOfBitfield39Reconciliation");
		Initializer.getBaseVariables().valueOfBitfield44 = property.getProperty("valueOfBitfield44");
		Initializer.getBaseVariables().valueOfBitfield48 = property.getProperty("valueOfBitfield48");
		Initializer.getBaseVariables().valueOfBitfield54 = property.getProperty("valueOfBitfield54");
		Initializer.getBaseVariables().valueOfBitfield123 = property.getProperty("valueOfBitfield123");
	}
	
	// ---------------------------------------------------------------------------------------------------------
		/*
		 * This method is used to get the path in which the application file are placed.
		 */
		// ---------------------------------------------------------------------------------------------------------
		public String getApplicationFolder() {
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
