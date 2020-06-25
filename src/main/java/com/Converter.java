package com;

import java.nio.charset.Charset;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

public class Converter {
	final static Logger logger = Logger.getLogger(Converter.class);
	public static String[] tempArray;

	Converter() {
		PropertyConfigurator.configure("log4j.properties");
	}

	// ---------------------------------------------------------------------------------------------------
	/*
	 * This function is used to added empty spaces after every two characters in the
	 * string Since all the converters are designed to handle data in byte format,
	 * String should be altered
	 */
	// ---------------------------------------------------------------------------------------------------
	public String addSpacesToString(String stringToAddSpace) {
		StringBuilder sb = new StringBuilder(stringToAddSpace);
		for (int i = 2; i < sb.length(); i += 3) {
			sb.insert(i, " ");
		}
		return sb.toString();
	}

	// --------------------------------------------------------------------------------------------------------
	/*
	 * Takes byte array as input and return the hex string Used for converting the
	 * bytes read from the Data input stream into hex
	 */
	// ----------------------------------------------------------------------------------------------------------
	public String byteToHex(byte[] byteString) {
		StringBuilder hexData = new StringBuilder();
		for (byte currByte : byteString) {
			hexData.append(String.format("%02x ", currByte));
		}
		return hexData.toString();
	}

	// -----------------------------------------------------------------------------------------------------------
	/*
	 * Takes Hex string as input and return the decimal value of it Hex string is
	 * converted into hex array to work on on byte at a time
	 */
	// ------------------------------------------------------------------------------------------------------------
	public String hexToDecimal(String hex) {
		String decimalValue = "";
		hex = hex.replaceAll("\\s", "");
		String digits = "0123456789ABCDEF";
		hex = hex.toUpperCase();
		int val = 0;
		for (int j = 0; j < hex.length(); j++) {
			char c = hex.charAt(j);
			int d = digits.indexOf(c);
			val = 16 * val + d;
		}
		decimalValue = decimalValue + Integer.toString(val) + " ";

		return decimalValue;
	}

	// -------------------------------------------------------------------------------------------------------------
	/*
	 * Takes hex string as input and returns the ASCII value of it. Hex string is
	 * converted into hex array to work on on byte at a time
	 */
	// ---------------------------------------------------------------------------------------------------------------
	public String hexToASCII(String hex) {
		hex = addSpacesToString(hex);
		tempArray = hex.split(" ");
		StringBuilder asciiValue = new StringBuilder();
		for (String currentByte : tempArray) {
			int n = Integer.valueOf(currentByte, 16);
			asciiValue.append((char) n);
		}
		tempArray = null;
		return asciiValue.toString();
	}

	// -------------------------------------------------------------------------------------------------------------
	/*
	 * Takes hex string as input and returns the binary value of it. Hex string is
	 * converted into hex array to work on one byte at a time
	 */
	// ---------------------------------------------------------------------------------------------------------------
	public String hexToBinary(String hex) {
		hex = addSpacesToString(hex);
		String[] hexArray = hex.split(" ");
		String finalBinaryValue = "";
		for (String currentByte : hexArray) {
			int i = Integer.parseInt(currentByte, 16);
			String bin = Integer.toBinaryString(i);
			int missingNumbers = 8 - bin.length();
			String temp = "";
			/*
			 * All the zeros infront gets removed after the toBinaryString operation and the
			 * bin will always start with 1 To overcome this, missing zero's are added in
			 * front
			 */
			for (int j = 0; j < missingNumbers; j++) {
				temp = temp + "0";
			}
			finalBinaryValue = finalBinaryValue + temp + bin;
		}
		return finalBinaryValue;
	}

	// -------------------------------------------------------------------------------------------------------------
	/*
	 * Takes string as input and returns the hex value of it. Spaces are added after
	 * every two characters to convert into byte format
	 */
	// ---------------------------------------------------------------------------------------------------------------
	public String asciitoHex(String asciiValue) {
		StringBuffer hex = new StringBuffer();
		try {
			char[] chars = asciiValue.toCharArray();
			String tempString = "";
			for (int i = 0; i < chars.length; i++) {
				tempString = Integer.toHexString((int) chars[i]);
				if (tempString.length() == 2) {
					hex.append(tempString);
				} else {
					hex.append("0" + tempString);
				}

			}
			hex = new StringBuffer(addSpacesToString(hex.toString()));
			logger.debug("Ascii Value " + asciiValue + " was converted to hex value " + hex);
		} catch (Exception e) {
			logger.error("Unable to perform ascii to hex operation on value " + asciiValue);
		}
		return hex.toString();
	}

	// -------------------------------------------------------------------------------------------------------------
	/*
	 * Takes bitmap String as input and returns the hex value of it. Bitmap input
	 * should be divided into 8 bits followed by space Eg.(10101010 00001111)
	 */
	// ---------------------------------------------------------------------------------------------------------------
	public String binaryToHex(String binaryValue) {
		String hexData = "", currentElementHexValue = "";
		try {
			if (binaryValue.contains(" ") == false) {
				StringBuffer sb = new StringBuffer(binaryValue);
				for (int i = 8; i < sb.length(); i += 9) {
					sb.insert(i, " ");
				}
				binaryValue = sb.toString();
			}
			tempArray = binaryValue.split(" ");
			for (String currentElement : tempArray) {
				currentElementHexValue = String.format("%X", Long.parseLong(currentElement, 2));
				if (currentElementHexValue.length() == 2) {
					hexData = hexData + currentElementHexValue + " ";
				} else {
					hexData = hexData + "0" + currentElementHexValue + " ";
				}
			}
			logger.debug("Binary value " + binaryValue + " was converted to hex value " + hexData.trim());
		} catch (Exception e) {
			logger.error("Unable to convert the binary value " + binaryValue + " to hex");
		}
		return hexData.trim();
	}

	public static String toHexString(String hex) {
		if (hex == null || hex.trim().length() == 0) {
			System.out.println("Cannot convert null HexString to ByteString! ");
			return ("");
		}
		int l = hex.length();
		if (l % 2 != 0) {
			hex = "0" + hex;
		}
		l = hex.length();
		byte[] data = new byte[l / 2];
		for (int i = 0; i < l; i += 2) {
			data[i / 2] = (byte) ((Character.digit(hex.charAt(i), 16) << 4) + Character.digit(hex.charAt(i + 1), 16));
		}
		return new String(data, Charset.forName("ISO-8859-1"));
	}
	
	public String zeroPadding(String integerValue, int expectedLength) {
		int zerosRequired = expectedLength - integerValue.length();
		if(zerosRequired>-1) {
			for(int i =0; i <zerosRequired ; i++) {
				integerValue = "0"+integerValue;
			}
			return integerValue;
		}else {
			System.out.println("Zero padding is not applicable for the given combination");
			return null;
		}
	}

}