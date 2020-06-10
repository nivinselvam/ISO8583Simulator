/*
 * This class is used to generate the encoded data of the respose packet that should be sent to the client.
 * The child class which inherits this class should implement a couple of things.
 * i) value of bitmap should be generated using the  generateBitmap() method. 
 * ii) consolidateBitfieldString should be generated from the generateConsolidateBitfieldString.
 * The generated values along with existing MTI, header values can be used to create the endoded data.
 */

package com;

import java.util.Map;
import java.util.TreeSet;
import org.apache.log4j.Logger;

public abstract class BaseEncoder {
	private static Logger logger = Logger.getLogger(BaseEncoder.class);
	protected String consolidateBitfieldString;
	protected String bitmap;
	protected String MTI;
	protected String header;
	protected TreeSet<Integer> elementsInTransaction;
	protected Map<String, String> responseBitFieldsWithValue;
	protected String responsePacket;

	public BaseEncoder(String header, String MTI, TreeSet<Integer> elementsInTransaction,
			Map<String, String> responseBitFieldsWithValue) {
		this.header = header;
		this.MTI = MTI;
		this.elementsInTransaction = elementsInTransaction;
		this.responseBitFieldsWithValue = responseBitFieldsWithValue;
	}

	public abstract String generateEncodedData();

	// --------------------------------------------------------------------------------------------------
	/*
	 * This function is used to create a bitmap after the elements invovled in the
	 * transaction are identified. This takes Treeset of elements in transaction as
	 * input and generates the bitmap based on it
	 */
	// ---------------------------------------------------------------------------------------------------
	public static String generateBitmap(TreeSet<Integer> elementsInTransaction) {
		int highestBitfield, bitmapLength, counter;
		StringBuffer bitmapBuffer = new StringBuffer();
		highestBitfield = elementsInTransaction.last();
		/*
		 * Below condition is used to set the bitfield1 of the bitmap. Value 0 means
		 * only primary bitmap is involved and 1 means both primary and secondary
		 * bitmaps are involved
		 */
		if (highestBitfield < 65) {
			bitmapLength = 64;
			bitmapBuffer.append("0");
		} else {
			bitmapLength = 128;
			bitmapBuffer.append("1");
		}
		// Value starts from 2 since bitfield 1 is already set above.
		counter = 2;
		for (Integer currentElement : elementsInTransaction) {
			boolean matchFound = false;
			do {
				if (counter == currentElement) {
					bitmapBuffer.append("1");
					counter++;
					// if the final element is reached, loop will break and the
					// remaining bits will not be set.
					// To avoid this we will not break the loop when it is last
					// element.
					if (counter <= highestBitfield) {
						matchFound = true;
					}
				} else {
					bitmapBuffer.append("0");
					counter++;
				}
				if ((counter - 1) % 8 == 0) {
					bitmapBuffer.append(" ");
				}
				if (matchFound) {
					break;
				}

			} while (counter <= bitmapLength);
		}
		return bitmapBuffer.toString();
	}

	// ---------------------------------------------------------------------------------------------------------------------
	/*
	 * This function is used to generate string of all the bitfield values involved
	 * in the transaction Certain bitfield are expected to have variable length. So
	 * bitfield value is picked from BitfieldValue hashmap in Bitfield data file and
	 * compared in the bitfieldlength hashmap. If the bitfield is a variable length
	 * one, then a prefix is added to denote the length
	 */
	// ---------------------------------------------------------------------------------------------------------------------
	public String generateConsolidateBitfieldString(Map<String, String> responseBitFieldsWithValue) {
		String finalBitfieldValues = "", currentBitfield, currentBitfieldLength, emptySpaces;
		int currentBit, numberOfSpacesRequired;
		logger.debug("Trying to generate the bitfield values");
		for (Map.Entry<String, String> currentEntry : responseBitFieldsWithValue.entrySet()) {
			currentBitfield = currentEntry.getKey();
			logger.debug("Generating the value of " + currentBitfield);
			currentBit = Integer.parseInt(currentBitfield.substring(8));
			if (Initializer.getBitfieldData().bitfieldLength.get(currentBitfield) > 0) {
				numberOfSpacesRequired = Initializer.getBitfieldData().bitfieldLength.get(currentBitfield)
						- currentEntry.getValue().length();
				emptySpaces = "";
				for (int i = 0; i < numberOfSpacesRequired; i++) {
					emptySpaces = emptySpaces + " ";
				}

				finalBitfieldValues = finalBitfieldValues + currentEntry.getValue() + emptySpaces;
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
			}
		}
		return finalBitfieldValues;
	}

}