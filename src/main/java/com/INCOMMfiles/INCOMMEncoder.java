package com.INCOMMfiles;

import java.util.Map;
import java.util.TreeSet;

import org.apache.log4j.Logger;

import com.BaseFiles.BaseEncoder;
import com.BaseFiles.Initializer;

public class INCOMMEncoder extends BaseEncoder{
	private static Logger logger = Logger.getLogger(INCOMMEncoder.class);

	public INCOMMEncoder(String header, String MTI, TreeSet<Integer> elementsInTransaction,
			Map<String, String> responseBitFieldsWithValue) {
		super(header, MTI, elementsInTransaction, responseBitFieldsWithValue);
	}

	/*
	 * In Incomm all the fields should be sent in Hex format. Hence all the values will
	 * be converted to hex format and response packet is created by concatenating them.
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

}
