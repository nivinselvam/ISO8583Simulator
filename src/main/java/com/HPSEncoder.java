package com;

import java.util.Map;
import java.util.TreeSet;

import org.apache.log4j.Logger;

public class HPSEncoder extends BaseEncoder {
	private static Logger logger = Logger.getLogger(BaseEncoder.class);
	private String bitmapToHex;
	private String consolidatedbitfieldStringToHex;
	private String MTItoHex;
	private String headerToHex;

	public HPSEncoder(String header, String MTI, TreeSet<Integer> elementsInTransaction,
			Map<String, String> responseBitFieldsWithValue) {
		super(header, MTI, elementsInTransaction, responseBitFieldsWithValue);
	}

	/*
	 * In HPS all the fields should be sent in Hex format. Hence all the values will
	 * be converted to hex format and response packet is created by concatinating them.
	 */
	@Override
	public String generateEncodedData() {
		logger.debug("Starting the encoding of response packet");
		bitmap = generateBitmap(elementsInTransaction);
		consolidateBitfieldString = generateConsolidateBitfieldString(responseBitFieldsWithValue);
		MTItoHex = Initializer.getConverter().asciitoHex(MTI);
		headerToHex = Initializer.getConverter().asciitoHex(header);
		bitmapToHex = Initializer.getConverter().binaryToHex(bitmap);
		consolidatedbitfieldStringToHex = Initializer.getConverter().asciitoHex(consolidateBitfieldString);
		responsePacket = (headerToHex + MTItoHex + bitmapToHex + consolidatedbitfieldStringToHex).replace(" ", "");
		return responsePacket;
	}

	// --------------------------------------------------------------------------------------------------------------------
	/*
	 * This function finds the overall length of the hexData and generates the first
	 * two bytes of the hex encoding process
	 */
	// --------------------------------------------------------------------------------------------------------------------
	public String addDataLenthToRequestPacket(String hexData) {
		String finalHexData = "", lengthConvertedToHex;
		String[] tempArray = hexData.split(" ");
		int arrayLength, numberOfDigits;
		arrayLength = (tempArray.length) + 2;
		lengthConvertedToHex = Integer.toHexString(arrayLength);
		numberOfDigits = lengthConvertedToHex.length();
		switch (numberOfDigits) {
		case 1:
			lengthConvertedToHex = "000" + lengthConvertedToHex;
			break;
		case 2:
			lengthConvertedToHex = "00" + lengthConvertedToHex;
			break;
		case 3:
			lengthConvertedToHex = "0" + lengthConvertedToHex;
			break;
		default:
			logger.warn("Generated Hex Data is null");
		}

		finalHexData = Initializer.getConverter().addSpacesToString(lengthConvertedToHex) + " " + hexData;
		return finalHexData;
	}

}
