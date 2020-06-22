package com;

public class HPSresponseGenerator extends BaseResponseGenerator {
	private StringBuffer responsePacketBuffer = new StringBuffer(super.requestPacket);
	private String bitfield4;

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
		if (requestPacket.length() < 33) {
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
		return responsePacketBuffer.toString();
	}

	@Override
	public void authorizationPendingBitfieldsUpdate() {
		responseBitfieldswithValue.put(Initializer.getBaseConstants().nameOfbitfield2, generateBitfield2(requestBitfieldsWithValues));
		// Partial approval is not applicable for balance inquiry.
		isBalanceInquiry = false;
		if (Initializer.getBaseConstants().balanceInquiryCodes.contains(requestBitfieldsWithValues.get(Initializer.getBaseConstants().nameOfbitfield3))) {
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
					setBitfieldValue(Initializer.getBaseConstants().nameOfbitfield39, Initializer.getBaseVariables().ValueOfBitfield39Approval));
			break;
		case "Decline":
			responseBitfieldswithValue.put(Initializer.getBaseConstants().nameOfbitfield39,
					setBitfieldValue(Initializer.getBaseConstants().nameOfbitfield39, Initializer.getBaseVariables().ValueOfBitfield39Decline));
			break;
		case "PartiallyApprove":
			bitfield4 = generateHalfAmountForPartialApproval(requestBitfieldsWithValues.get(Initializer.getBaseConstants().nameOfbitfield4));
			responseBitfieldswithValue.put(Initializer.getBaseConstants().nameOfbitfield39,
					setBitfieldValue(Initializer.getBaseConstants().nameOfbitfield39, Initializer.getBaseVariables().ValueOfBitfield39Partial));
			break;
		}

		if (transactionResult.equals("PartiallyApprove") || isBalanceInquiry) {
			responseBitfieldswithValue.put(Initializer.getBaseConstants().nameOfbitfield4,
					setBitfieldValue(Initializer.getBaseConstants().nameOfbitfield4, bitfield4));
		}

		if (transactionResult.equals("Approve") || transactionResult.equals("PartiallyApprove")) {
			responseBitfieldswithValue.put(Initializer.getBaseConstants().nameOfbitfield38,
					setBitfieldValue(Initializer.getBaseConstants().nameOfbitfield38, Initializer.getBaseVariables().valueOfBitfield38));
			elementsInTransaction.add(38);
		}
		if (transactionResult.equals("Decline")) {
			responseBitfieldswithValue.put(Initializer.getBaseConstants().nameOfbitfield44,
					setBitfieldValue(Initializer.getBaseConstants().nameOfbitfield44, Initializer.getBaseVariables().valueOfBitfield44));
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
		responseBitfieldswithValue.put(Initializer.getBaseConstants().nameOfbitfield2, generateBitfield2(requestBitfieldsWithValues));
		// Activation and Recharges transaction should not have partial approval
		if (Initializer.getBaseConstants().activationRechargeCodes.contains(requestBitfieldsWithValues.get(Initializer.getBaseConstants().nameOfbitfield3))
				&& transactionResult.equals("PartiallyApprove")) {
			transactionResult = "Approve";
		}
		switch (transactionResult) {
		case "Approve":
			responseBitfieldswithValue.put(Initializer.getBaseConstants().nameOfbitfield39,
					setBitfieldValue(Initializer.getBaseConstants().nameOfbitfield39, Initializer.getBaseVariables().ValueOfBitfield39Approval));
			break;
		case "Decline":
			responseBitfieldswithValue.put(Initializer.getBaseConstants().nameOfbitfield39,
					setBitfieldValue(Initializer.getBaseConstants().nameOfbitfield39, Initializer.getBaseVariables().ValueOfBitfield39Decline));
			break;
		case "PartiallyApprove":
			bitfield4 = generateHalfAmountForPartialApproval(requestBitfieldsWithValues.get(Initializer.getBaseConstants().nameOfbitfield4));
			responseBitfieldswithValue.put(Initializer.getBaseConstants().nameOfbitfield4,
					setBitfieldValue(Initializer.getBaseConstants().nameOfbitfield4, bitfield4));
			responseBitfieldswithValue.put(Initializer.getBaseConstants().nameOfbitfield39,
					setBitfieldValue(Initializer.getBaseConstants().nameOfbitfield39, Initializer.getBaseVariables().ValueOfBitfield39Partial));
			break;
		}

		if (transactionResult.equals("Approve") || transactionResult.equals("PartiallyApprove")) {
			responseBitfieldswithValue.put(Initializer.getBaseConstants().nameOfbitfield38,
					setBitfieldValue(Initializer.getBaseConstants().nameOfbitfield38, Initializer.getBaseVariables().valueOfBitfield38));
			elementsInTransaction.add(38);
		}
		if (transactionResult.equals("Decline")) {
			responseBitfieldswithValue.put(Initializer.getBaseConstants().nameOfbitfield44,
					setBitfieldValue(Initializer.getBaseConstants().nameOfbitfield44, Initializer.getBaseVariables().valueOfBitfield44));
			elementsInTransaction.add(44);
		}

		addDE54IfNecessary();

	}

	@Override
	public void financialForceDraftPendingBitfieldsUpdate() {
		responseBitfieldswithValue.put(Initializer.getBaseConstants().nameOfbitfield2, generateBitfield2(requestBitfieldsWithValues));
		if (transactionResult.equals("PartiallyApprove")) {
			transactionResult = "Approve";
		}
		switch (transactionResult) {
		case "Approve":
			responseBitfieldswithValue.put(Initializer.getBaseConstants().nameOfbitfield39,
					setBitfieldValue(Initializer.getBaseConstants().nameOfbitfield39, Initializer.getBaseVariables().ValueOfBitfield39Approval));
			break;
		case "Decline":
			responseBitfieldswithValue.put(Initializer.getBaseConstants().nameOfbitfield39,
					setBitfieldValue(Initializer.getBaseConstants().nameOfbitfield39, Initializer.getBaseVariables().ValueOfBitfield39Decline));
			break;
		case "PartiallyApprove":
			bitfield4 = generateHalfAmountForPartialApproval(requestBitfieldsWithValues.get(Initializer.getBaseConstants().nameOfbitfield4));
			responseBitfieldswithValue.put(Initializer.getBaseConstants().nameOfbitfield4,
					requestBitfieldsWithValues.get(Initializer.getBaseConstants().nameOfbitfield4));
			responseBitfieldswithValue.put(Initializer.getBaseConstants().nameOfbitfield39,
					setBitfieldValue(Initializer.getBaseConstants().nameOfbitfield39, Initializer.getBaseVariables().ValueOfBitfield39Partial));
			break;
		}

		if (transactionResult.equals("Approve") || transactionResult.equals("PartiallyApprove")) {
			responseBitfieldswithValue.put(Initializer.getBaseConstants().nameOfbitfield38,
					setBitfieldValue(Initializer.getBaseConstants().nameOfbitfield38, Initializer.getBaseVariables().valueOfBitfield38));
			elementsInTransaction.add(38);
		}
		if (transactionResult.equals("Decline")) {
			responseBitfieldswithValue.put(Initializer.getBaseConstants().nameOfbitfield44,
					setBitfieldValue(Initializer.getBaseConstants().nameOfbitfield44, Initializer.getBaseVariables().valueOfBitfield44));
			elementsInTransaction.add(44);
		}

		addDE54IfNecessary();
	}

	@Override
	public void reversalPendingBitfieldsUpdate() {
		if (transactionResult.equals("PartiallyApprove")) {
			transactionResult = "Approve";
		}
		responseBitfieldswithValue.put(Initializer.getBaseConstants().nameOfbitfield2, generateBitfield2(requestBitfieldsWithValues));
		if (transactionResult.equals("Approve")) {
			responseBitfieldswithValue.put(Initializer.getBaseConstants().nameOfbitfield39,
					setBitfieldValue(Initializer.getBaseConstants().nameOfbitfield39, Initializer.getBaseVariables().ValueOfBitfield39Reversal));
			responseBitfieldswithValue.put(Initializer.getBaseConstants().nameOfbitfield38,
					setBitfieldValue(Initializer.getBaseConstants().nameOfbitfield38, Initializer.getBaseVariables().valueOfBitfield38));
			elementsInTransaction.add(38);
		} else {
			responseBitfieldswithValue.put(Initializer.getBaseConstants().nameOfbitfield39,
					setBitfieldValue(Initializer.getBaseConstants().nameOfbitfield39, Initializer.getBaseVariables().ValueOfBitfield39Decline));
			responseBitfieldswithValue.put(Initializer.getBaseConstants().nameOfbitfield44,
					setBitfieldValue(Initializer.getBaseConstants().nameOfbitfield44, Initializer.getBaseVariables().valueOfBitfield44));
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
					setBitfieldValue(Initializer.getBaseConstants().nameOfbitfield39, Initializer.getBaseVariables().ValueOfBitfield39Reconciliation));
			responseBitfieldswithValue.put(Initializer.getBaseConstants().nameOfbitfield123,
					setBitfieldValue(Initializer.getBaseConstants().nameOfbitfield123, Initializer.getBaseVariables().valueOfBitfield123));
		} else {
			responseBitfieldswithValue.put(Initializer.getBaseConstants().nameOfbitfield39,
					setBitfieldValue(Initializer.getBaseConstants().nameOfbitfield39, Initializer.getBaseVariables().ValueOfBitfield39Decline));
		}
		responseBitfieldswithValue.put(Initializer.getBaseConstants().nameOfbitfield48,
				setBitfieldValue(Initializer.getBaseConstants().nameOfbitfield48, Initializer.getBaseVariables().valueOfBitfield48));
	}

	private void addDE54IfNecessary() {
		if (Initializer.getBaseConstants().balanceInquiryCodes.contains(requestBitfieldsWithValues.get(Initializer.getBaseConstants().nameOfbitfield3))
				|| Initializer.getBaseConstants().activationRechargeCodes
						.contains(requestBitfieldsWithValues.get(Initializer.getBaseConstants().nameOfbitfield3))) {
			responseBitfieldswithValue.put(Initializer.getBaseConstants().nameOfbitfield54,
					setBitfieldValue(Initializer.getBaseConstants().nameOfbitfield54, Initializer.getBaseVariables().valueOfBitfield54));
			elementsInTransaction.add(54);
		}
	}

}
