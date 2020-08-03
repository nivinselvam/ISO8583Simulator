/*
 * This class is used to decode the request packet as per X9 FEPs requirement
 */

package com.X9Files;

import java.util.Map;

import org.apache.log4j.Logger;

import com.BaseFiles.BaseDecoder;
import com.BaseFiles.Initializer;

public class X9Decoder extends BaseDecoder {
	private static Logger logger = Logger.getLogger(X9Decoder.class);

	public X9Decoder(String requestPacket) {
		super(requestPacket);
		// This is to configure the decoder for reading hex data
		readDataFormat = 2;
	}

	// -----------------------------------------------------------------------------------------------------------------------------------------
	/*
	 * This method is used to grep the header from the request packet. This is
	 * overridden because in X9 there is no header involved
	 */
	// -----------------------------------------------------------------------------------------------------------------------------------------
	@Override
	public void generateHeader() {
		header = "";
		logger.debug("X9 doesnt have header, so the value is set as empty value");
	}

	// -----------------------------------------------------------------------------------------------------------------------------------------
	/*
	 * This method is used to grep the MTI from the request packet. This is
	 * overridden because in X9 the MTI is sent in hex format as part of request
	 * packet and this should be converted to ascii format
	 */
	// -----------------------------------------------------------------------------------------------------------------------------------------
	@Override
	public void generateMTI() {
		MTI = Initializer.getConverter().hexToASCII(requestPacket
				.substring(Initializer.getBaseConstants().mtiStartPoint, Initializer.getBaseConstants().mtiEndPoint));
		logger.debug("MTI is set as " + MTI);
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
			logger.debug("Trying to add " + currentBitField + " and its values to the map");
			currentBitfieldLength = Initializer.getBitfieldData().bitfieldLength.get(currentBitField) * readDataFormat;
			if (currentBitfieldLength > 0 && (currentBitField.equals("BITFIELD1")) == false) {
				if (currentBitField.equals(Initializer.getBaseConstants().nameOfbitfield22)
						|| currentBitField.equals(Initializer.getBaseConstants().nameOfbitfield27)) {
					currentBitfieldLength = currentBitfieldLength / 2;
				}
				currentBitFieldValue = requestPacket.substring(currentPosition,
						currentPosition + currentBitfieldLength);
				currentBitFieldValue = getFormattedBitfieldValue(currentBitField, currentBitFieldValue);
				bitFieldswithValue.put(currentBitField, currentBitFieldValue);
				logger.debug(currentBitField + " with value " + currentBitFieldValue + " successfully added");
				currentPosition = currentPosition + currentBitfieldLength;
			} else if (currentBitfieldLength == (-2 * readDataFormat)) {
				currentBitfieldLength = Integer.parseInt(Initializer.getConverter()
						.hexToASCII(requestPacket.substring(currentPosition, currentPosition + 2 * readDataFormat)));
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
				currentBitfieldLength = Integer.parseInt(Initializer.getConverter()
						.hexToASCII(requestPacket.substring(currentPosition, currentPosition + (3 * readDataFormat))));
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
				currentBitfieldLength = Integer.parseInt(Initializer.getConverter()
						.hexToASCII(requestPacket.substring(currentPosition, currentPosition + (4 * readDataFormat))));
				currentPosition = currentPosition + (4 * readDataFormat);
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
	@Override
	public String getFormattedBitfieldValue(String bitfield, String bitfieldValue) {
		if (bitfield.equals(Initializer.getBaseConstants().nameOfbitfield22)
				|| bitfield.equals(Initializer.getBaseConstants().nameOfbitfield27)) {
			return bitfieldValue;
		} else {
			return Initializer.getConverter().hexToASCII(bitfieldValue);
		}

	}

}
