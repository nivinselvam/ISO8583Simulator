package com;

import org.apache.log4j.PropertyConfigurator;

public class Initializer {

	private static String fepName = "HPS";
	private static boolean guiEnabled = false;
	private static ServerInitializer server = null;
	private static Converter converter = new Converter();
	private static BitFieldData bitfieldData = new BitFieldData();

	public static void main(String[] args) {
		PropertyConfigurator.configure("log4j.properties");
		if(guiEnabled) {
			
		}else {
			server = new ServerInitializer();
			server.start();
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
}
