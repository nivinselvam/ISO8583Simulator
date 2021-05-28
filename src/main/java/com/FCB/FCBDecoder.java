package com.FCB;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;

import com.BaseFiles.BaseDecoder;
import com.BaseFiles.Initializer;

public class FCBDecoder extends BaseDecoder {
	private static Logger logger = Logger.getLogger(FCBDecoder.class);
	private List<String> BitfieldsInHexFormat = new ArrayList<String>(Arrays.asList("BITFIELD37", "BITFIELD38",
			"BITFIELD39", "BITFIELD41", "BITFIELD42", "BITFIELD60", "BITFIELD63"));

	public FCBDecoder(String requestPacket) {
		super(requestPacket);
	}

	// -----------------------------------------------------------------------------------------------------
	/*
	 * This function is used to process the bitmap and identify the values
	 * associated with bitfields Takes consolidated bitmap involved in the
	 * transaction as input. Returns a linked hashmap which has bitfields as key and
	 * corresponding values as hashmap value
	 */
	// ------------------------------------------------------------------------------------------------------
	public Map<String, String> bitfieldAndValueMapping() {
		String tempString;
		for (String element : getElementsInTransaction(consolidatedBitmap)) {
			currentBitField = "BITFIELD" + element;
			if (BitfieldsInHexFormat.contains(currentBitField)) {
				readDataFormat = 2;
			} else {
				readDataFormat = 1;
			}
			logger.debug("Trying to add " + currentBitField + " and its values to the map");
			currentBitfieldLength = Initializer.getBitfieldData().bitfieldLength.get(currentBitField) * readDataFormat;
			if (currentBitfieldLength > 0 && (currentBitField.equals("BITFIELD1")) == false) {
				currentBitFieldValue = requestPacket.substring(currentPosition,
						currentPosition + currentBitfieldLength);
				currentBitFieldValue = getFormattedBitfieldValue(currentBitField, currentBitFieldValue);
				bitFieldswithValue.put(currentBitField, currentBitFieldValue);
				logger.debug(currentBitField + " with value " + currentBitFieldValue + " successfully added");
				currentPosition = currentPosition + currentBitfieldLength;
			} else if (currentBitfieldLength == (-2 * readDataFormat)) {
				if (BitfieldsInHexFormat.contains(currentBitField)) {
					currentBitfieldLength = Integer.parseInt(Initializer.getConverter().hexToASCII(
							requestPacket.substring(currentPosition, currentPosition + 2 * readDataFormat)));
				} else {
					currentBitfieldLength = Integer
							.parseInt(requestPacket.substring(currentPosition, currentPosition + 2 * readDataFormat));
				}
				currentPosition = currentPosition + (2 * readDataFormat);
				currentBitfieldLength = (currentBitfieldLength) * readDataFormat;
				currentBitFieldValue = requestPacket.substring(currentPosition,
						currentPosition + currentBitfieldLength);
				tempString = (Integer.toString(currentBitfieldLength / readDataFormat));
				if (tempString.length() < 2) {
					if (tempString.length() < 1) {
						tempString = "00" + tempString;
					} else {
						tempString = "0" + tempString;
					}
				}
				currentBitFieldValue = tempString + getFormattedBitfieldValue(currentBitField, currentBitFieldValue);
				bitFieldswithValue.put(currentBitField, currentBitFieldValue);
				logger.debug(currentBitField + " with value " + currentBitFieldValue + " successfully added");
				currentPosition = currentPosition + currentBitfieldLength;
			} else if (currentBitfieldLength == (-3 * readDataFormat)) {
				if (BitfieldsInHexFormat.contains(currentBitField)) {
					currentBitfieldLength = Integer.parseInt(Initializer.getConverter().hexToASCII(
							requestPacket.substring(currentPosition, currentPosition + 3 * readDataFormat)));
				} else {
					currentBitfieldLength = Integer
							.parseInt(requestPacket.substring(currentPosition, currentPosition + 3 * readDataFormat));
				}
				currentPosition = currentPosition + (3 * readDataFormat);
				currentBitfieldLength = (currentBitfieldLength) * readDataFormat;
				currentBitFieldValue = requestPacket.substring(currentPosition,
						currentPosition + currentBitfieldLength);
				tempString = (Integer.toString(currentBitfieldLength / readDataFormat));
				if (tempString.length() < 3) {
					if (tempString.length() < 2) {
						if (tempString.length() < 1) {
							tempString = "000" + tempString;
						} else {
							tempString = "00" + tempString;
						}
					} else {
						tempString = "0" + tempString;
					}
				}
				currentBitFieldValue = tempString + getFormattedBitfieldValue(currentBitField, currentBitFieldValue);
				bitFieldswithValue.put(currentBitField, currentBitFieldValue);
				logger.debug(currentBitField + " with value " + currentBitFieldValue + " successfully added");
				currentPosition = currentPosition + currentBitfieldLength;
			} else if (currentBitfieldLength == (-4 * readDataFormat)) {
				// This is to make sure that the bitfields in hex format are converted to string
				if (BitfieldsInHexFormat.contains(currentBitField)) {
					// This is to make sure length of bitfields 60 and 63 are read as plain strings
					if (currentBitField.equals(Initializer.getBaseConstants().nameOfbitfield60)
							|| currentBitField.equals(Initializer.getBaseConstants().nameOfbitfield63)) {
						currentBitfieldLength = Integer.parseInt(
								requestPacket.substring(currentPosition, currentPosition + 4 * (readDataFormat / 2)));
						currentPosition = currentPosition + (4 * (readDataFormat / 2));
					} else {
						currentBitfieldLength = Integer.parseInt(Initializer.getConverter().hexToASCII(
								requestPacket.substring(currentPosition, currentPosition + 4 * readDataFormat)));
						currentPosition = currentPosition + (4 * readDataFormat);
					}

				} else {
					currentBitfieldLength = Integer
							.parseInt(requestPacket.substring(currentPosition, currentPosition + 4 * readDataFormat));
					currentPosition = currentPosition + (4 * readDataFormat);
				}
				currentBitfieldLength = (currentBitfieldLength) * readDataFormat;
				currentBitFieldValue = requestPacket.substring(currentPosition,
						currentPosition + currentBitfieldLength);
				tempString = (Integer.toString(currentBitfieldLength / readDataFormat));
				if (tempString.length() < 4) {
					if (tempString.length() < 3) {
						if (tempString.length() < 2) {
							if (tempString.length() < 1) {
								tempString = "0000";
							} else {
								tempString = "000" + tempString;
							}
						} else {
							tempString = "00" + tempString;
						}
					} else {
						tempString = "0" + tempString;
					}
				}
				currentBitFieldValue = tempString + getFormattedBitfieldValue(currentBitField, currentBitFieldValue);
				bitFieldswithValue.put(currentBitField, currentBitFieldValue);
				logger.debug(currentBitField + " with value " + currentBitFieldValue + " successfully added");
				currentPosition = currentPosition + currentBitfieldLength;
			}
		}
		return bitFieldswithValue;
	}

	// -----------------------------------------------------------------------------------------------------------------------------------------
	/*
	 * This function is to set the value for the bitfield as per fep requirement
	 * Values may be sometimes plain string and hex data otherwise. So bitfieldwise
	 * processing will be in getting the value in required format.
	 */
	// -----------------------------------------------------------------------------------------------------------------------------------------
	public String getFormattedBitfieldValue(String bitfield, String bitfieldValue) {
		if (BitfieldsInHexFormat.contains(bitfield)) {
			return Initializer.getConverter().hexToASCII(bitfieldValue);
		} else {
			return bitfieldValue;
		}

	}

}
