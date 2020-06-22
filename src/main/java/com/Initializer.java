package com;

import java.awt.EventQueue;
import java.util.HashMap;
import java.util.Map;

import javax.swing.JOptionPane;

import org.apache.log4j.PropertyConfigurator;

public class Initializer {

	private static String fepName = "HPS";
	private static boolean guiEnabled = true;
	private static BaseConstants constants = new BaseConstants();
	private static ServerInitializer server = null;
	private static Converter converter = new Converter();
	private static BitFieldData bitfieldData = new BitFieldData();
	private static BaseDataLoader dataLoader = new BaseDataLoader();
	private static BaseVariables variables = new BaseVariables();
	public static BaseFileWatcher fileWatcher = new BaseFileWatcher();
	private static AppGUI appGui;
	private static int portNumber;
	private static Map<String,String> fepPropertyFiles = new HashMap<String, String>();

	public static void main(String[] args) {
		PropertyConfigurator.configure("log4j.properties");
		fepPropertyFiles.put("Common", "CommonVariables.properties");
		fepPropertyFiles.put("HPS", "HPSConstants.properties");
		fepPropertyFiles.put("FCB", "FCBConstants.properties");
		fepPropertyFiles.put("INCOMM", "IncommConstants.properties");
		if (dataLoader.createAppFolder()) {
			fileWatcher.start();
			if (guiEnabled) {
				EventQueue.invokeLater(new Runnable() {
					public void run() {
						try {
							AppGUI window = new AppGUI();
							window.getFrmISO8583Simulator().setVisible(true);
						} catch (Exception e) {
							e.printStackTrace();
						}
					}
				});
			} else {
				server = new ServerInitializer();
				server.start();
			}
		}else {
			JOptionPane.showMessageDialog(null, "Unable to create the app folder");
		}

	}

	public static Map<String, String> getFepPropertyFiles() {
		return fepPropertyFiles;
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
