package com.HPSfiles;

import org.apache.log4j.Logger;

import com.BaseFiles.BaseResponseGenerator;
import com.BaseFiles.Initializer;

public class HPSresponseGenerator extends BaseResponseGenerator {
	private static Logger logger = Logger.getLogger(HPSresponseGenerator.class);
	private StringBuffer responsePacketBuffer = new StringBuffer(super.requestPacket);
	private String valueOfBitfield22;

	public HPSresponseGenerator(String requestPacket) {
		super(requestPacket);
	}

	// ------------------------------------------------------------------------------------------------------------------
	/*
	 * Validate if the transaction request is an echo request.
	 */
	// ------------------------------------------------------------------------------------------------------------------
	@Override
	public boolean validateifConnectivityCheck() {
		if (requestPacket.length() <= Integer.parseInt(Initializer.getBaseConstants().echoMessageLength)) {
			logger.debug("Request packet passed the connectivity validation check. This is an echo request.");
			return true;
		} else {
			return false;
		}
	}

	@Override
	public String connectivityCheckResponse() {
		for (int i = 0; i < responsePacketBuffer.length(); i++) {
			if (i == 8 || i == 9) {
				responsePacketBuffer.setCharAt(i, '0');
			}
		}
		logger.debug("Value of the echo request was altered to create the echo response.");
		return responsePacketBuffer.toString();
	}

	@Override
	public void authorizationPendingBitfieldsUpdate() {
		outdoorTransaction = isOutdoorTransaction();
		responseBitfieldswithValue.put(Initializer.getBaseConstants().nameOfbitfield2,
				generateBitfield2(requestBitfieldsWithValues));
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

		/*
		 * Outdoor transactions will have a static amount sent as transaction amount in
		 * the request packet. This could be $0 too. In such case, any non-Zero approval
		 * amount should be sent as response.
		 */
		if (outdoorTransaction) {
			if (requestBitfieldsWithValues.get(Initializer.getBaseConstants().nameOfbitfield4).equals("000000000000")) {
				bitfield4 = Initializer.getBaseVariables().valueOfBitfield4;
				responseBitfieldswithValue.put(Initializer.getBaseConstants().nameOfbitfield4,
						setBitfieldLengthIfRequired(Initializer.getBaseConstants().nameOfbitfield4, bitfield4));
				logger.debug(
						"Since this is an outdoor transaction and request amount is $0, value of bitfield4 is set as: "
								+ bitfield4);
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
			if(outdoorTransaction && requestBitfieldsWithValues.get(Initializer.getBaseConstants().nameOfbitfield4).equals("000000000000")) {
				bitfield4 = Initializer.getBaseVariables().valueOfBitfield4;
			}else {
				bitfield4 = generateBitfield4();
			}			
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

		addDE54IfNecessary();

		if (requestBitfieldsWithValues.containsKey(Initializer.getBaseConstants().nameOfbitfield55)) {
			responseBitfieldswithValue.put(Initializer.getBaseConstants().nameOfbitfield55,
					requestBitfieldsWithValues.get(Initializer.getBaseConstants().nameOfbitfield55));
			elementsInTransaction.add(55);
		}

	}

	@Override
	public void financialSalesPendingBitfieldsUpdate() {
		responseBitfieldswithValue.put(Initializer.getBaseConstants().nameOfbitfield2,
				generateBitfield2(requestBitfieldsWithValues));
		// Activation and Recharges transaction should not have partial approval
		if (Initializer.getBaseConstants().activationRechargeCodes
				.contains(requestBitfieldsWithValues.get(Initializer.getBaseConstants().nameOfbitfield3))
				&& transactionResult.equals("PartiallyApprove")) {
			transactionResult = "Approve";
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
			responseBitfieldswithValue.put(Initializer.getBaseConstants().nameOfbitfield4,
					setBitfieldLengthIfRequired(Initializer.getBaseConstants().nameOfbitfield4, bitfield4));
			responseBitfieldswithValue.put(Initializer.getBaseConstants().nameOfbitfield39,
					setBitfieldLengthIfRequired(Initializer.getBaseConstants().nameOfbitfield39,
							Initializer.getBaseVariables().ValueOfBitfield39Partial));
			break;
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

		addDE54IfNecessary();

	}

	@Override
	public void financialForceDraftPendingBitfieldsUpdate() {
		responseBitfieldswithValue.put(Initializer.getBaseConstants().nameOfbitfield2,
				generateBitfield2(requestBitfieldsWithValues));
		if (transactionResult.equals("PartiallyApprove")) {
			transactionResult = "Approve";
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
			responseBitfieldswithValue.put(Initializer.getBaseConstants().nameOfbitfield4,
					requestBitfieldsWithValues.get(Initializer.getBaseConstants().nameOfbitfield4));
			responseBitfieldswithValue.put(Initializer.getBaseConstants().nameOfbitfield39,
					setBitfieldLengthIfRequired(Initializer.getBaseConstants().nameOfbitfield39,
							Initializer.getBaseVariables().ValueOfBitfield39Partial));
			break;
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

		addDE54IfNecessary();
	}

	@Override
	public void reversalPendingBitfieldsUpdate() {
		if (transactionResult.equals("PartiallyApprove")) {
			transactionResult = "Approve";
		}
		responseBitfieldswithValue.put(Initializer.getBaseConstants().nameOfbitfield2,
				generateBitfield2(requestBitfieldsWithValues));
		if (transactionResult.equals("Approve")) {
			responseBitfieldswithValue.put(Initializer.getBaseConstants().nameOfbitfield39,
					setBitfieldLengthIfRequired(Initializer.getBaseConstants().nameOfbitfield39,
							Initializer.getBaseVariables().ValueOfBitfield39Reversal));
			responseBitfieldswithValue.put(Initializer.getBaseConstants().nameOfbitfield38, setBitfieldLengthIfRequired(
					Initializer.getBaseConstants().nameOfbitfield38, Initializer.getBaseVariables().valueOfBitfield38));
			elementsInTransaction.add(38);
		} else {
			responseBitfieldswithValue.put(Initializer.getBaseConstants().nameOfbitfield39,
					setBitfieldLengthIfRequired(Initializer.getBaseConstants().nameOfbitfield39,
							Initializer.getBaseVariables().ValueOfBitfield39Decline));
			responseBitfieldswithValue.put(Initializer.getBaseConstants().nameOfbitfield44, setBitfieldLengthIfRequired(
					Initializer.getBaseConstants().nameOfbitfield44, Initializer.getBaseVariables().valueOfBitfield44));
			elementsInTransaction.add(44);
		}
		addDE54IfNecessary();
	}

	@Override
	public void reconciliationPendingBitfieldsUpdate() {
		if (transactionResult.equals("PartiallyApprove")) {
			transactionResult = "Approve";
		}
		if (transactionResult.equals("Approve")) {
			responseBitfieldswithValue.put(Initializer.getBaseConstants().nameOfbitfield39,
					setBitfieldLengthIfRequired(Initializer.getBaseConstants().nameOfbitfield39,
							Initializer.getBaseVariables().ValueOfBitfield39Reconciliation));
			responseBitfieldswithValue.put(Initializer.getBaseConstants().nameOfbitfield123,
					setBitfieldLengthIfRequired(Initializer.getBaseConstants().nameOfbitfield123,
							Initializer.getBaseVariables().valueOfBitfield123));
		} else {
			responseBitfieldswithValue.put(Initializer.getBaseConstants().nameOfbitfield39,
					setBitfieldLengthIfRequired(Initializer.getBaseConstants().nameOfbitfield39,
							Initializer.getBaseVariables().ValueOfBitfield39Decline));
		}

	}

	private void addDE54IfNecessary() {
		if (Initializer.getBaseConstants().balanceInquiryCodes
				.contains(requestBitfieldsWithValues.get(Initializer.getBaseConstants().nameOfbitfield3))
				|| Initializer.getBaseConstants().activationRechargeCodes
						.contains(requestBitfieldsWithValues.get(Initializer.getBaseConstants().nameOfbitfield3))) {
			responseBitfieldswithValue.put(Initializer.getBaseConstants().nameOfbitfield54, setBitfieldLengthIfRequired(
					Initializer.getBaseConstants().nameOfbitfield54, Initializer.getBaseVariables().valueOfBitfield54));
			elementsInTransaction.add(54);
		}
	}

	@Override
	public boolean isOutdoorTransaction() {
		if (requestBitfieldsWithValues.containsKey(Initializer.getBaseConstants().nameOfbitfield22)) {
			valueOfBitfield22 = requestBitfieldsWithValues.get(Initializer.getBaseConstants().nameOfbitfield22);
			if (valueOfBitfield22.charAt(0) == 'D' || valueOfBitfield22.charAt(0) == 'K') {
				logger.debug("value of bitfield22.1 is " + valueOfBitfield22.charAt(0)
						+ ". Hence this is an outdoor transaction");
				return true;
			} else {
				logger.debug("This is not an outdoor transaction.");
				return false;
			}
		} else {
			logger.debug("This is not an outdoor transaction.");
			return false;
		}
	}

}
