package com.FCB;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.TreeSet;

import org.apache.log4j.Logger;

import com.BaseFiles.BaseEncoder;
import com.BaseFiles.Initializer;

public class FCBEncoder extends BaseEncoder {
	private static Logger logger = Logger.getLogger(FCBEncoder.class);
	private List<String> BitfieldsInHexFormat = new ArrayList<String>(
			Arrays.asList("BITFIELD37", "BITFIELD38", "BITFIELD39", "BITFIELD41", "BITFIELD42"));

	public FCBEncoder(String header, String MTI, TreeSet<Integer> elementsInTransaction,
			Map<String, String> responseBitFieldsWithValue) {
		super(header, MTI, elementsInTransaction, responseBitFieldsWithValue);
	}

	@Override
	public String generateEncodedData() {
		logger.debug("Starting the encoding of response packet");
		bitmap = generateBitmap(elementsInTransaction);
		bitmapToHex = Initializer.getConverter().binaryToHex(bitmap);
		consolidateBitfieldString = generateConsolidateBitfieldString(responseBitFieldsWithValue);
		responsePacket = (header + MTI + bitmapToHex + consolidateBitfieldString).replace(" ", "");
		return responsePacket;
	}

	// ---------------------------------------------------------------------------------------------------------------------
	/*
	 * This function is overridden since in FCB, Bitfields 37,38,39,41,42 should be
	 * in hex format. It is used to generate string of all the bitfield values
	 * involved in the transaction. Certain bitfields are expected to have variable
	 * length. So bitfield value is picked from BitfieldValue hashmap in Bitfield
	 * data file and compared in the bitfieldlength hashmap. If the bitfield is a
	 * variable length one, then a prefix is added to denote the length. *
	 */
	// ---------------------------------------------------------------------------------------------------------------------
	@Override
	public String generateConsolidateBitfieldString(Map<String, String> responseBitFieldsWithValue) {
		String finalBitfieldValues = "", currentBitfield, currentBitfieldLength, emptySpaces;
		int numberOfSpacesRequired;
		logger.debug("Trying to generate the bitfield values");
		for (Map.Entry<String, String> currentEntry : responseBitFieldsWithValue.entrySet()) {
			currentBitfield = currentEntry.getKey();
			logger.debug("Generating the value of " + currentBitfield);
			if (Initializer.getBitfieldData().bitfieldLength.get(currentBitfield) > 0) {
				numberOfSpacesRequired = Initializer.getBitfieldData().bitfieldLength.get(currentBitfield)
						- currentEntry.getValue().length();
				emptySpaces = "";
				for (int i = 0; i < numberOfSpacesRequired; i++) {
					emptySpaces = emptySpaces + " ";
				}
				if(BitfieldsInHexFormat.contains(currentBitfield)) {
					finalBitfieldValues = finalBitfieldValues + Initializer.getConverter().asciitoHex(currentEntry.getValue() + emptySpaces);
				}else {
					finalBitfieldValues = finalBitfieldValues + currentEntry.getValue() + emptySpaces;
				}				
				logger.debug("Value of " + currentBitfield + ", " + currentEntry.getValue()
						+ " was added to the response string");
			} else if (Initializer.getBitfieldData().bitfieldLength.get(currentBitfield) == -2) {
				currentBitfieldLength = currentEntry.getValue().substring(0, 2);
				finalBitfieldValues = finalBitfieldValues + currentBitfieldLength
						+ currentEntry.getValue().substring(2);
				logger.debug("Value of " + currentBitfield + " " + currentEntry.getValue()
						+ " was added to the response string");
			} else if (Initializer.getBitfieldData().bitfieldLength.get(currentBitfield) == -3) {
				currentBitfieldLength = currentEntry.getValue().substring(0, 3);
				finalBitfieldValues = finalBitfieldValues + currentBitfieldLength
						+ currentEntry.getValue().substring(3);
				logger.debug("Value of " + currentBitfield + " " + currentEntry.getValue()
						+ " was added to the response string");
			} else if (Initializer.getBitfieldData().bitfieldLength.get(currentBitfield) == -4) {
				currentBitfieldLength = currentEntry.getValue().substring(0, 4);
				finalBitfieldValues = finalBitfieldValues + currentBitfieldLength
						+ currentEntry.getValue().substring(4);
				logger.debug("Value of " + currentBitfield + " " + currentEntry.getValue()
						+ " was added to the response string");
			}
		}
		return finalBitfieldValues;
	}

}
