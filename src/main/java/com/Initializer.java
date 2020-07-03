package com;

import java.awt.EventQueue;
import java.util.HashMap;
import java.util.Map;
import javax.swing.JOptionPane;


public class Initializer {

	private static String fepName;
	private static boolean guiEnabled;
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

	public static void main(String[] args) {
		fepName = "HPS";
		guiEnabled = true;
		fepPropertyFiles = new HashMap<String, String>();
		server = null;
		mapFEPtoPropertyFile();
		converter = new Converter();
		bitfieldData = new BitFieldData();
		variables = new BaseVariables();
		constants = new BaseConstants();
		dataLoader = new BaseDataLoader();
		fileWatcher = new BaseFileWatcher();		
		if (dataLoader.createAppFolder()) {
			fileWatcher.start();
			if (guiEnabled) {
				EventQueue.invokeLater(new Runnable() {
					public void run() {
						try {
							appGui = new AppGUI();
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
		} else {
			JOptionPane.showMessageDialog(null, "Unable to create the app folder");
		}

	}

	private static void mapFEPtoPropertyFile() {
		fepPropertyFiles.put("Common", "CommonVariables.properties");
		fepPropertyFiles.put("HPS", "HPSConstants.properties");
		fepPropertyFiles.put("FCB", "FCBConstants.properties");
		fepPropertyFiles.put("INCOMM", "IncommConstants.properties");
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

	public static BaseVariables getBaseVariables() {
		return variables;
	}

	public static int getPortNumber() {
		return portNumber;
	}

	public static void setPortNumber(int portNumber) {
		Initializer.portNumber = portNumber;
	}
}
