package com;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

public class Tester {

	public static void main(String[] args) {

//		PropertyConfigurator.configure("log4j.properties");
//		String requestPacket = "45480000230100000101432020202000005445534F000000000000014601313130303030650000C98004333133303030303030303030303030303030303030333236323030353131303532303234353534313834306130303130313235343134433130305445534F202020205445534F54455354323520202020203434423337313233343536373839313233355E414D45524943414E204558505245535320335E323030363137383930323200200000C00000004158202030334A594E303330313038343030313130314949443034303031204001";
//		BaseResponseGenerator response = new HPSresponseGenerator(requestPacket);
//		response.getResponsePacket();
		
		Properties prop = new Properties();
		try {
			prop.load(new FileInputStream(new File(System.getProperty("user.home")+"\\ISO8583Simulator\\HPSConstants.properties")));
			System.out.println(prop.getProperty("valueOfBitfield54"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		

	}

}
