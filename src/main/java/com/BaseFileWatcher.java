/*
 * This class is used to monitor the app folder.
 * This triggers the data reloading whenever there is a change in values.
 * This is run as a separate thread since the folder should be monitored parallely when the execution happens in the simulator
 */

package com;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Paths;
import java.nio.file.StandardWatchEventKinds;
import java.nio.file.WatchEvent;
import java.nio.file.WatchKey;
import java.nio.file.WatchService;
import java.util.Properties;

public class BaseFileWatcher extends Thread{	
	Properties property = new Properties();
	
	public void run() {
		watchFolder();
	}	
	
	//-------------------------------------------------------------------------------------------------------------
	/*
	 * This method creates the app folder and places the properties files of all the FEPS in the app folder.
	 * These are files that are stored internally and will be copied during the sim start into the user folder.
	 */
	//-------------------------------------------------------------------------------------------------------------
	public void watchFolder() {
		WatchService watchService;
		try {
			watchService = FileSystems.getDefault().newWatchService();
			Paths.get(Initializer.getBaseConstants().appFolder).register(watchService, StandardWatchEventKinds.ENTRY_MODIFY);			
			do {
				WatchKey watchKey = watchService.take();
				Thread.sleep(50);
				for (WatchEvent event : watchKey.pollEvents()) {
					WatchEvent.Kind kind = event.kind();
					if (StandardWatchEventKinds.ENTRY_MODIFY.equals(kind)) {
						readUpdatedFile(event.context().toString());
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
	//-------------------------------------------------------------------------------------------------------------
	/*
	 * This method creates the app folder and places the properties files of all the FEPS in the app folder.
	 * These are files that are stored internally and will be copied during the sim start into the user folder.
	 */
	//-------------------------------------------------------------------------------------------------------------
	private void readUpdatedFile(String fileName) throws IOException {
		property.load(new FileInputStream(new File(Initializer.getBaseConstants().appFolder)+"\\"+fileName));
		Initializer.getBaseVariables().authorizationTransactionResponse = property.getProperty("authorizationTransactionResponse");
		Initializer.getBaseVariables().financialSalesTransactionResponse = property.getProperty("financialSalesTransactionResponse");
		Initializer.getBaseVariables().financialForceDraftTransactionResponse = property.getProperty("financialForceDraftTransactionResponse");
		Initializer.getBaseVariables().reversalTransactionResponse = property.getProperty("reversalTransactionResponse");
		Initializer.getBaseVariables().reconciliationTransactionResponse = property.getProperty("reconciliationTransactionResponse");
		
		Initializer.getBaseVariables().valueOfBitfield4 = property.getProperty("valueOfBitfield4");
		Initializer.getBaseVariables().valueOfBitfield37 = property.getProperty("valueOfBitfield37");
		Initializer.getBaseVariables().valueOfBitfield38 = property.getProperty("valueOfBitfield38");
		Initializer.getBaseVariables().ValueOfBitfield39Approval = property.getProperty("ValueOfBitfield39Approval");
		Initializer.getBaseVariables().ValueOfBitfield39Decline = property.getProperty("ValueOfBitfield39Decline");
		Initializer.getBaseVariables().ValueOfBitfield39Partial = property.getProperty("ValueOfBitfield39Partial");
		Initializer.getBaseVariables().ValueOfBitfield39Reversal = property.getProperty("ValueOfBitfield39Reversal");
		Initializer.getBaseVariables().ValueOfBitfield39Reconsillation = property.getProperty("ValueOfBitfield39Reconsillation");
		Initializer.getBaseVariables().valueOfBitfield44 = property.getProperty("valueOfBitfield44");
		Initializer.getBaseVariables().valueOfBitfield48 = property.getProperty("valueOfBitfield48");
		Initializer.getBaseVariables().valueOfBitfield54 = property.getProperty("valueOfBitfield54");
		Initializer.getBaseVariables().valueOfBitfield123 = property.getProperty("valueOfBitfield123");
		
	}

}