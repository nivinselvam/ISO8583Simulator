package com.INCOMMfiles;

import java.util.TreeMap;

import org.apache.log4j.Logger;

import com.BaseFiles.BaseResponseGenerator;
import com.BaseFiles.BitfieldComparator;
import com.BaseFiles.Initializer;

public class INCOMMresponseGenerator extends BaseResponseGenerator {
	private static Logger logger = Logger.getLogger(INCOMMresponseGenerator.class);

	public INCOMMresponseGenerator(String requestPacket) {
		super(requestPacket);
	}
	
	// ------------------------------------------------------------------------------------------------------------------
	/*
	 * Validate if the transaction request is an echo request.
	 */
	// ------------------------------------------------------------------------------------------------------------------
	@Override
	public boolean validateifConnectivityCheck() {
		loadDecoder(requestPacket);
		decoder.decodeTransactionPacket();
		requestMTI = decoder.getMTI();
		requestBitfieldsWithValues = decoder.getBitfieldsWithValue();
		logger.info("Request Packet: ");
		decoder.printDecodedData();
		//In the below line of code, reconciliation represents the network health check MTI.
		if (requestMTI.equals(Initializer.getBaseConstants().reconciliationRequestMTI)) {
			return true;
		} else {
			return false;
		}
	}
	
	@Override
	public String connectivityCheckResponse() {				
		responseBitfieldswithValue = new TreeMap<String, String>(new BitfieldComparator());
		responseBitfieldswithValue = requestBitfieldsWithValues;
		loadEncoder();
		responsePacket = encoder.generateEncodedData();
		loadDecoder(responsePacket);
		decoder.decodeTransactionPacket();
		logger.info("Response Packet");
		decoder.printDecodedData();
		return responsePacket;
	}

	@Override
	public void authorizationPendingBitfieldsUpdate() {

		if (transactionResult.equals("Approve")) {
			responseBitfieldswithValue.put(Initializer.getBaseConstants().nameOfbitfield38, setBitfieldLengthIfRequired(
					Initializer.getBaseConstants().nameOfbitfield38, Initializer.getBaseVariables().valueOfBitfield38));
			elementsInTransaction.add(38);
			responseBitfieldswithValue.put(Initializer.getBaseConstants().nameOfbitfield39,
					setBitfieldLengthIfRequired(Initializer.getBaseConstants().nameOfbitfield39,
							Initializer.getBaseVariables().ValueOfBitfield39Approval));
		} else if (transactionResult.equals("Decline")) {
			responseBitfieldswithValue.put(Initializer.getBaseConstants().nameOfbitfield39,
					setBitfieldLengthIfRequired(Initializer.getBaseConstants().nameOfbitfield39,
							Initializer.getBaseVariables().ValueOfBitfield39Decline));
			responseBitfieldswithValue.put(Initializer.getBaseConstants().nameOfbitfield44, setBitfieldLengthIfRequired(
					Initializer.getBaseConstants().nameOfbitfield44, Initializer.getBaseVariables().valueOfBitfield44));
			elementsInTransaction.add(44);
		}

	}

	@Override
	public void financialSalesPendingBitfieldsUpdate() {
		responseBitfieldswithValue.put(Initializer.getBaseConstants().nameOfbitfield5, setBitfieldLengthIfRequired(
				Initializer.getBaseConstants().nameOfbitfield5, Initializer.getBaseVariables().valueOfBitfield5));

		// Partial approval is not applicable for balance inquiry.
		isBalanceInquiry = false;
		if (Initializer.getBaseConstants().balanceInquiryCodes
				.contains(requestBitfieldsWithValues.get(Initializer.getBaseConstants().nameOfbitfield3))) {
			isBalanceInquiry = true;
			if (transactionResult.equals("Decline")) {
				bitfield4 = "000000000000";
			} else {
				bitfield4 = Initializer.getBaseVariables().valueOfBitfield4;
			}
			if (transactionResult.equals("PartiallyApprove")) {
				transactionResult = "Approve";
			}
		}
		switch (transactionResult) {
		case "Approve":
			responseBitfieldswithValue.put(Initializer.getBaseConstants().nameOfbitfield39,
					setBitfieldLengthIfRequired(Initializer.getBaseConstants().nameOfbitfield39,
							Initializer.getBaseVariables().ValueOfBitfield39Approval));
			break;
		case "Decline":
			responseBitfieldswithValue.put(Initializer.getBaseConstants().nameOfbitfield39,
					setBitfieldLengthIfRequired(Initializer.getBaseConstants().nameOfbitfield39,
							Initializer.getBaseVariables().ValueOfBitfield39Decline));
			break;
		case "PartiallyApprove":
			bitfield4 = generateBitfield4();
			responseBitfieldswithValue.put(Initializer.getBaseConstants().nameOfbitfield39,
					setBitfieldLengthIfRequired(Initializer.getBaseConstants().nameOfbitfield39,
							Initializer.getBaseVariables().ValueOfBitfield39Partial));
			break;
		}

		if (transactionResult.equals("PartiallyApprove") || isBalanceInquiry) {
			responseBitfieldswithValue.put(Initializer.getBaseConstants().nameOfbitfield4,
					setBitfieldLengthIfRequired(Initializer.getBaseConstants().nameOfbitfield4, bitfield4));
		}

		if (transactionResult.equals("Approve") || transactionResult.equals("PartiallyApprove")) {
			responseBitfieldswithValue.put(Initializer.getBaseConstants().nameOfbitfield38, setBitfieldLengthIfRequired(
					Initializer.getBaseConstants().nameOfbitfield38, Initializer.getBaseVariables().valueOfBitfield38));
			elementsInTransaction.add(38);
		}
		if (transactionResult.equals("Decline")) {
			responseBitfieldswithValue.put(Initializer.getBaseConstants().nameOfbitfield44, setBitfieldLengthIfRequired(
					Initializer.getBaseConstants().nameOfbitfield44, Initializer.getBaseVariables().valueOfBitfield44));
			elementsInTransaction.add(44);
		}

	}

	//Incomm does not support financial force draft
	@Override
	public void financialForceDraftPendingBitfieldsUpdate() {
		// TODO Auto-generated method stub

	}

	@Override
	public void reversalPendingBitfieldsUpdate() {
		responseBitfieldswithValue.put(Initializer.getBaseConstants().nameOfbitfield5, setBitfieldLengthIfRequired(
				Initializer.getBaseConstants().nameOfbitfield5, Initializer.getBaseVariables().valueOfBitfield5));

		if (transactionResult.equals("Approve")) {
			responseBitfieldswithValue.put(Initializer.getBaseConstants().nameOfbitfield38, setBitfieldLengthIfRequired(
					Initializer.getBaseConstants().nameOfbitfield38, Initializer.getBaseVariables().valueOfBitfield38));
			elementsInTransaction.add(38);
			responseBitfieldswithValue.put(Initializer.getBaseConstants().nameOfbitfield39,
					setBitfieldLengthIfRequired(Initializer.getBaseConstants().nameOfbitfield39,
							Initializer.getBaseVariables().ValueOfBitfield39Reversal));
		} else if (transactionResult.equals("Decline")) {
			responseBitfieldswithValue.put(Initializer.getBaseConstants().nameOfbitfield39,
					setBitfieldLengthIfRequired(Initializer.getBaseConstants().nameOfbitfield39,
							Initializer.getBaseVariables().ValueOfBitfield39Decline));
			responseBitfieldswithValue.put(Initializer.getBaseConstants().nameOfbitfield44, setBitfieldLengthIfRequired(
					Initializer.getBaseConstants().nameOfbitfield44, Initializer.getBaseVariables().valueOfBitfield44));
			elementsInTransaction.add(44);
		}

	}
	
	//Incomm does not support reconciliation
	@Override
	public void reconciliationPendingBitfieldsUpdate() {
		// TODO Auto-generated method stub

	}

	@Override
	public boolean isOutdoorTransaction() {
		return false;
		
	}
	
}
