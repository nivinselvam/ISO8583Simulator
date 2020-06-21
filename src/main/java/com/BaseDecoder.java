/*
 * This class will help in decoding the request packet.
 * In case of FEPS with hexadecimal values read data format should be set as 2.
 * And getFormattedBitfieldValue should be overridden.
 */

package com;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import org.apache.log4j.Logger;

public class BaseDecoder {
	protected String requestPacket;
	protected String header;
	protected String MTI;
	private String primaryBitMap;
	private String primaryBitmapValue;
	private String secondaryBitmap;
	private String secondaryBitmapValue;
	private String consolidatedBitmap;
	private String currentBitField;
	private String currentBitFieldValue;
	private int currentPosition, currentBitfieldLength;
	private boolean isSecondaryBitmapAvailable = false;
	private Map<String, String> bitFieldswithValue = new LinkedHashMap<String, String>();
	private static Logger logger = Logger.getLogger(BaseDecoder.class);
	private List<String> elementsInTransaction;
	// The readDataFormat variable is used to decide if request packet contains
	// bitfield values in hex format or not. value 1 means bitfield values are plain
	// string and 2 means hex format
	protected int readDataFormat = 1;

	public BaseDecoder(String requestPacket) {
		try {
			this.requestPacket = requestPacket;
		} catch (NumberFormatException e) {
			logger.fatal("Request packet Format error. Please make sure the Hex data is correct");
		}
	}

	/*
	 * -----------------------------------------------------------------------------
	 * This method is used to grep the header from the request packet
	 * -----------------------------------------------------------------------------
	 */
	public void generateHeader() {
		try {
			header = requestPacket.substring(Initializer.getBaseConstants().HeaderStartPoint, Initializer.getBaseConstants().HeaderEndPoint);
			logger.debug("Header is set as " + header);
		} catch (NumberFormatException e) {
			header = "";
			logger.warn("Header is empty");
		}
	}

	public String getHeader() {
		return header;
	}
	
	public void setHeader(String header) {
		this.header = header;
	}

	/*
	 * -----------------------------------------------------------------------------
	 * This method is used to grep the MTI from the request packet
	 * -----------------------------------------------------------------------------
	 */
	public void generateMTI() {
		MTI = requestPacket.substring(Initializer.getBaseConstants().mtiStartPoint, Initializer.getBaseConstants().mtiEndPoint);
		logger.debug("MTI is set as " + MTI);
	}

	public String getMTI() {
		return MTI;
	}
	
	public void setMTI(String MTI) {
		this.MTI = MTI;
	}

	/*
	 * -----------------------------------------------------------------------------
	 * This method is used to grep the primary bitmap from the request packet
	 * -----------------------------------------------------------------------------
	 */
	public void generatePrimaryBitmap() {
		primaryBitmapValue = requestPacket.substring(Initializer.getBaseConstants().primaryBitmapStartPoint,
				Initializer.getBaseConstants().primaryBitmapEndPoint);
		primaryBitMap = Initializer.getConverter().hexToBinary(primaryBitmapValue);
		logger.debug("Primary bitmap has been set as " + primaryBitmapValue);
		currentPosition = Initializer.getBaseConstants().primaryBitmapPosition;
		if (primaryBitMap.charAt(0) == '1') {
			isSecondaryBitmapAvailable = true;
			logger.debug("Secondary bitmap is available");
		}
	}

	public String getPrimaryBitmap() {
		return primaryBitMap;
	}

	/*
	 * -----------------------------------------------------------------------------
	 * This method is used to grep the secondary bitmap from the request packet
	 * -----------------------------------------------------------------------------
	 */
	public void generateSecondaryBitmap() {
		secondaryBitmap = "";
		if (isSecondaryBitmapAvailable) {
			secondaryBitmapValue = requestPacket.substring(Initializer.getBaseConstants().secondaryBitmapStartPoint,
					Initializer.getBaseConstants().secondaryBitmapEndPoint);
			secondaryBitmap = Initializer.getConverter().hexToBinary(secondaryBitmapValue);
			currentPosition = Initializer.getBaseConstants().secondaryBitmapEndPosition;
			logger.debug("Secondary Bitmap is set as " + secondaryBitmapValue);
		}
	}

	public String getSecondaryBitmap() {
		return secondaryBitmap;
	}

	/*
	 * -----------------------------------------------------------------------------
	 * This method is used to grep the consolidated bitmap of the transaction
	 * -----------------------------------------------------------------------------
	 */
	public void generateConsolidatedBitmap() {
		if (isSecondaryBitmapAvailable) {
			consolidatedBitmap = primaryBitMap + secondaryBitmap;
		} else {
			consolidatedBitmap = primaryBitMap;
		}
		logger.debug("Consolidated bitmap is set as " + consolidatedBitmap);
	}

	public String getConsolidatedBitmap() {
		return consolidatedBitmap;
	}

	/*
	 * -----------------------------------------------------------------------------
	 * This method is used to grep the bitfield values from the request packet
	 * -----------------------------------------------------------------------------
	 */
	public void generateBitFieldwithValues() {
		logger.debug("Trying to identify the bitfields with values");
		bitFieldswithValue = bitfieldAndValueMapping();
	}

	public Map<String, String> getBitfieldsWithValue() {
		return bitFieldswithValue;
	}

	// -----------------------------------------------------------------------------------------------------------
	/*
	 * Takes the hex array as input, splits the data and convets into ascii values
	 */
	// -----------------------------------------------------------------------------------------------------------
	public void decodeRequestPacket() {
		logger.debug("Starting the decoding of packet");
		if (requestPacket.length() > Integer.parseInt(Initializer.getBaseConstants().echoMessageLength)) {
			generateHeader();
			generateMTI();
			generatePrimaryBitmap();
			generateSecondaryBitmap();
			generateConsolidatedBitmap();
			generateBitFieldwithValues();
		}
	}

	// --------------------------------------------------------------------------------------------------
	/*
	 * This function is used to identify the bitfields involved in the transaction
	 * Takes the consolidated bitmap as input and returns a string with integers
	 * representing bitfields
	 */
	// ---------------------------------------------------------------------------------------------------
	private List<String> getElementsInTransaction(String bitmap) {
		elementsInTransaction = new ArrayList<String>();
		for (int i = 0; i < bitmap.length(); i++) {
			if (bitmap.charAt(i) == '1') {
				elementsInTransaction.add(String.valueOf(i + 1));
			}
		}
		return elementsInTransaction;
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
				;
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
	public String getFormattedBitfieldValue(String bitfield, String bitfieldValue) {
		return bitfieldValue;
	}

	// -----------------------------------------------------------------------------------------------------------------------------------------
	/*
	 * This function is used to retrive all the available data involved in the
	 * transaction
	 */
	// -----------------------------------------------------------------------------------------------------------------------------------------
	public void printEncodedData() {
		logger.info(
				"--------------------------------------------------------------------------------------------------------------");
		try {
			logger.info("Header: " + header);
			System.out.println("Header: " + header);
			logger.info("MTI: " + MTI);
			System.out.println("MTI: " + MTI);
			for (Map.Entry<String, String> currentEntry : bitFieldswithValue.entrySet()) {
				logger.info(currentEntry.getKey() + ": " + currentEntry.getValue());
				System.out.println(currentEntry.getKey() + ": " + currentEntry.getValue());
			}
			logger.info(
					"--------------------------------------------------------------------------------------------------------------");
		} catch (NullPointerException e) {
			logger.error("Unable to decode the request packet.");
			System.out.println("Unable to decode the request packet.");
		}

	}
}