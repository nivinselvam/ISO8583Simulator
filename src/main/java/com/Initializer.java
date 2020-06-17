package com;

import org.apache.log4j.PropertyConfigurator;

public class Initializer {

	private static String fepName = "HPS";
	private static boolean guiEnabled = false;
	private static ServerInitializer server = null;
	private static Converter converter = new Converter();
	private static BitFieldData bitfieldData = new BitFieldData();
	private static BaseDataLoader dataLoader = new BaseDataLoader();
	private static BaseVariables variables = new BaseVariables();
	public static BaseFileWatcher fileWatcher = new BaseFileWatcher();

	public static void main(String[] args) {
		PropertyConfigurator.configure("log4j.properties");
		if (guiEnabled) {

		} else {
			if (dataLoader.createAppFolder()) {
				fileWatcher.start();
				server = new ServerInitializer();
				server.start();
			}
		}
	}

	public static String getFEPname() {
		return fepName;
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

	public static Converter getConverter() {
		return converter;
	}

	public static BitFieldData getBitfieldData() {
		return bitfieldData;
	}

	public static BaseVariables getBaseVariables() {
		return variables;
	}
}
