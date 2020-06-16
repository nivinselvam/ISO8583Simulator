//
/*
 * This file is used for generating the responses for the transaction requests.
 * Constructor of this class requires the request packet to be fed in form of string.
 * Identifies the MTI from the request packet and decides the response accordingly.
 */
//
package com;

import java.util.Arrays;
import java.util.Map;
import java.util.TreeMap;
import java.util.TreeSet;
import org.apache.log4j.Logger;

public abstract class BaseResponseGenerator {
	private static Logger logger = Logger.getLogger(BaseResponseGenerator.class);
	protected String requestPacket, responsePacket, transactionResult;
	private String header, requestMTI, responseMTI;
	protected Map<String, String> requestBitfieldsWithValues, responseBitfieldswithValue;
	protected TreeSet<Integer> elementsInTransaction;
	protected boolean isBalanceInquiry;
	private BaseDecoder decoder;
	private BaseEncoder encoder;

	public BaseResponseGenerator(String requestPacket) {
		this.requestPacket = requestPacket;
	}

	public Map<String, String> getResponseBitfieldswithValue() {
		return responseBitfieldswithValue;
	}

	// ------------------------------------------------------------------------------------------------------------------
	/*
	 * This method identifies the type of transaction and generates the response
	 * based on it
	 */
	// -------------------------------------------------------------------------------------------------------------------
	public String getResponsePacket() {
		if (validateifConnectivityCheck()) {
			responsePacket = connectivityCheckResponse();
		} else {
			loadDecoder(requestPacket);
			decoder.decodeRequestPacket();
			header = decoder.getHeader();
			requestMTI = decoder.getMTI();
			requestBitfieldsWithValues = decoder.getBitfieldsWithValue();
			logger.info("Request Packet");
			decoder.printEncodedData();
			responseBitfieldswithValue = new TreeMap<String, String>(new BitfieldComparator());

			if (requestMTI.equals(BaseConstants.authorizationRequestMTI)) {
				transactionResult = Initializer.getBaseVariables().authorizationTransactionResponse;
				responseMTI = BaseConstants.authorizationResponseMTI;
				elementsInTransaction = new TreeSet<>(Arrays.asList(BaseConstants.elementsInAuthorisationTransaction));
				generateResponseBitfieldswithValue(elementsInTransaction);
				authorizationPendingBitfieldsUpdate();
			} else if (requestMTI.equals(BaseConstants.financialSalesRequestMTI)) {
				transactionResult = Initializer.getBaseVariables().financialSalesTransactionResponse;
				responseMTI = BaseConstants.financialSalesResponseMTI;
				elementsInTransaction = new TreeSet<>(Arrays.asList(BaseConstants.elementsInFinancialSalesTransaction));
				generateResponseBitfieldswithValue(elementsInTransaction);
				financialSalesPendingBitfieldsUpdate();
			} else if (requestMTI.equals(BaseConstants.financialForceDraftRequestMTI)) {
				transactionResult = Initializer.getBaseVariables().financialForceDraftTransactionResponse;
				responseMTI = BaseConstants.financialForceDraftResponseMTI;
				elementsInTransaction = new TreeSet<>(
						Arrays.asList(BaseConstants.elementsInFinancialForceDraftTransaction));
				generateResponseBitfieldswithValue(elementsInTransaction);
				financialForceDraftPendingBitfieldsUpdate();
			} else if (requestMTI.equals(BaseConstants.reversalRequestMTI)) {
				transactionResult = Initializer.getBaseVariables().reversalTransactionResponse;
				responseMTI = BaseConstants.reversalResponseMTI;
				elementsInTransaction = new TreeSet<>(Arrays.asList(BaseConstants.elementsInReversalTransaction));
				generateResponseBitfieldswithValue(elementsInTransaction);
				reversalPendingBitfieldsUpdate();
			} else if (requestMTI.equals(BaseConstants.reconciliationRequestMTI)) {
				transactionResult = Initializer.getBaseVariables().reconciliationTransactionResponse;
				responseMTI = BaseConstants.reconciliationResponseMTI;
				elementsInTransaction = new TreeSet<>(Arrays.asList(BaseConstants.elementsInReconsillationTransaction));
				generateResponseBitfieldswithValue(elementsInTransaction);
				reconciliationPendingBitfieldsUpdate();
			} else {
				logger.error("Provided MTI is invalid for creating the response packet.");
			}

			loadEncoder();
			responsePacket = encoder.generateEncodedData();
			loadDecoder(responsePacket);
			logger.info("Response Packet");
		}
		return responsePacket;
	}

	// ------------------------------------------------------------------------------------------------------------------
	/*
	 * This method is used to generate the response bitfield value. When we receive
	 * the request packet, bitfields will have the length prefixed. This has to be
	 * removed for the HexEncoder to generate the correct value.
	 */
	// ------------------------------------------------------------------------------------------------------------------
	public String removeLLVAR(String bitfield, String bitfieldValue) {
		StringBuffer updatedValue = new StringBuffer(bitfieldValue);
		BitFieldData bitfieldLength = new BitFieldData();
		if (bitfieldLength.bitfieldLength.get(bitfield) == -2) {
			updatedValue.delete(0, 2);
		} else if (bitfieldLength.bitfieldLength.get(bitfield) == -3) {
			updatedValue.delete(0, 3);
		}
		return updatedValue.toString();
	}

	// ------------------------------------------------------------------------------------------------------------------
	/*
	 * This method is used to generate the bitfield2. This field is populated based
	 * on the existence of bitfield 2, 35 and 45. Should be picked in the priority
	 * of 2, 35 and 45 i.e., if 2 and 35 are available, value of 2 should be picked
	 * and if 35 and 45 are available 35 should be picked and so on.
	 */
	// ------------------------------------------------------------------------------------------------------------------
	public String generateBitfield2(Map<String, String> requestBitfieldsWithValues) {
		int endPoint = 0;
		String bitfield2Value = "", bitfield2Length = "";
		if (requestBitfieldsWithValues.containsKey(BaseConstants.nameOfbitfield2)) {
			return requestBitfieldsWithValues.get(BaseConstants.nameOfbitfield2);
		} else if (requestBitfieldsWithValues.containsKey(BaseConstants.nameOfbitfield35)) {
			endPoint = requestBitfieldsWithValues.get(BaseConstants.nameOfbitfield35).indexOf('=');
			bitfield2Value = requestBitfieldsWithValues.get(BaseConstants.nameOfbitfield35).substring(2, endPoint);
			bitfield2Length = Integer.toString(bitfield2Value.length());
			if (bitfield2Length.length() < 2) {
				return "0" + bitfield2Value.length() + bitfield2Value;
			} else {
				return bitfield2Value.length() + bitfield2Value;
			}

		} else if (requestBitfieldsWithValues.containsKey(BaseConstants.nameOfbitfield45)) {
			endPoint = requestBitfieldsWithValues.get(BaseConstants.nameOfbitfield45).indexOf('^');
			bitfield2Value = requestBitfieldsWithValues.get(BaseConstants.nameOfbitfield45).substring(3, endPoint);
			bitfield2Length = Integer.toString(bitfield2Value.length());
			if (bitfield2Length.length() < 2) {
				return "0" + bitfield2Value.length() + bitfield2Value;
			} else {
				return bitfield2Value.length() + bitfield2Value;
			}
		}
		return "";
	}

	// ------------------------------------------------------------------------------------------------------------------
	/*
	 * This method is used to create a responsebitfield treemap. Treemap is used to
	 * make sure the bitfield values are sorted. Numbers of bitfields that are to be
	 * sent in response should be passed to the method.
	 */
	// ------------------------------------------------------------------------------------------------------------------
	public void generateResponseBitfieldswithValue(TreeSet<Integer> elementsInTransaction) {
		for (Integer currentEntry : elementsInTransaction) {
			String key = "BITFIELD" + currentEntry;
			responseBitfieldswithValue.put(key, requestBitfieldsWithValues.get(key));
			logger.debug(key + ":" + requestBitfieldsWithValues.get(key) + " added to the response bitfield map");
		}
	}

	// ------------------------------------------------------------------------------------------------------------------
	/*
	 * This method is used to generate a dynamic amount for partial approval. This
	 * takes transaction amount as input and returns half of it.
	 */
	// ------------------------------------------------------------------------------------------------------------------
	public String generateHalfAmountForPartialApproval(String transactionAmount) {
		String bitfield4 = Integer.toString(Integer.parseInt(transactionAmount) / 2);
		// Bitfield4 has a fixed length of 12 digits and has to have 0's for
		// the digits missing.
		int length = bitfield4.length();
		String tempString = "";
		for (int i = 0; i < 12 - length; i++) {
			tempString = tempString + "0";
		}
		return tempString + bitfield4;
	}

	// ------------------------------------------------------------------------------------------------------------------
	/*
	 * Below abstract methods should be implemented based on the FEP requirements of
	 * the specific transaction type. These should be used only to update the
	 * bitfield values.
	 */
	// ------------------------------------------------------------------------------------------------------------------
	public abstract void authorizationPendingBitfieldsUpdate();

	public abstract void financialSalesPendingBitfieldsUpdate();

	public abstract void financialForceDraftPendingBitfieldsUpdate();

	public abstract void reversalPendingBitfieldsUpdate();

	public abstract void reconciliationPendingBitfieldsUpdate();
	// ------------------------------------------------------------------------------------------------------------------
	/*
	 * This method is used to identify the bitfield and add length of bitfield if
	 * required.
	 */
	// ------------------------------------------------------------------------------------------------------------------

	public static String setBitfieldValue(String bitfieldName, String bitfieldValue) {
		int variableLengthValue;
		String bitfieldLength;
		BitFieldData bitfieldData = new BitFieldData();
		variableLengthValue = bitfieldData.bitfieldLength.get(bitfieldName);
		if (variableLengthValue > 0) {
			return bitfieldValue;
		} else if (variableLengthValue == -2) {
			bitfieldLength = Integer.toString(bitfieldValue.length());
			if (bitfieldLength.length() < 2) {
				bitfieldLength = "0" + bitfieldLength;
			}
			return bitfieldLength + bitfieldValue;
		} else if (variableLengthValue == -3) {
			bitfieldLength = Integer.toString(bitfieldValue.length());
			if (bitfieldLength.length() < 3) {
				if (bitfieldLength.length() < 2) {
					bitfieldLength = "00" + bitfieldLength;
				} else {
					bitfieldLength = "0" + bitfieldLength;
				}
			}
			return bitfieldLength + bitfieldValue;
		} else {
			return bitfieldValue;
		}
	}

	// ------------------------------------------------------------------------------------------------------------------
	/*
	 * This method is used to generate the binary bitmap from the elements involved
	 * in the transaction
	 */
	// ------------------------------------------------------------------------------------------------------------------
	public static String generateBinaryBitmap(TreeSet<Integer> elementsInTransaction) {
		StringBuilder binaryData = new StringBuilder();
		int highestBitfield = elementsInTransaction.last();
		int bitmapLength;
		if (highestBitfield < 65) {
			bitmapLength = 64;
			binaryData.append("0");
		} else {
			bitmapLength = 128;
			binaryData.append("1");
		}
		int i = 2;
		for (Integer currentElement : elementsInTransaction) {
			boolean matchFound = false;
			do {
				if (i == currentElement) {
					binaryData.append("1");
					i++;
					// if the final element is reached, loop will break and the
					// remaining bits will not be set.
					// To avoid this we will not break the loop when it is last
					// element.
					if (i <= highestBitfield) {
						matchFound = true;
					}
				} else {
					binaryData.append("0");
					i++;
				}
				if ((i - 1) % 8 == 0) {
					binaryData.append(" ");
				}
				if (matchFound) {
					break;
				}

			} while (i <= bitmapLength);
		}
		return binaryData.toString();
	}

	// ------------------------------------------------------------------------------------------------------------------
	/*
	 * This method is used to load the Base decoder based on the FEP.
	 */
	// ------------------------------------------------------------------------------------------------------------------
	public void loadDecoder(String dataToDecode) {
		if (Initializer.getFEPname().equals("HPS")) {
			decoder = new HPSDecoder(dataToDecode);
		}
	}

	// ------------------------------------------------------------------------------------------------------------------
	/*
	 * This method is used to load the Base encoder based on the FEP.
	 */
	// ------------------------------------------------------------------------------------------------------------------
	public void loadEncoder() {
		if (Initializer.getFEPname().equals("HPS")) {
			encoder = new HPSEncoder(header, responseMTI, elementsInTransaction, responseBitfieldswithValue);
		}
	}

	// ------------------------------------------------------------------------------------------------------------------
	/*
	 * Validate if the transaction request is an echo request. For the FEPS which
	 * support echo request, this method should be over written.
	 */
	// ------------------------------------------------------------------------------------------------------------------
	public boolean validateifConnectivityCheck() {
		return false;
	}

	// ------------------------------------------------------------------------------------------------------------------
	/*
	 * This method is used to generate the response for the echo request. For the
	 * FEPS which support echo request, this method should be over written.
	 */
	// ------------------------------------------------------------------------------------------------------------------
	public String connectivityCheckResponse() {
		return "";
	}

}