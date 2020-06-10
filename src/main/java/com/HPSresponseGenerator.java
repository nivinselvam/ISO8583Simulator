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
		responseBitfieldswithValue.put(BaseVariables.nameOfbitfield2, generateBitfield2(requestBitfieldsWithValues));
		// Partial approval is not applicable for balance inquiry.
		isBalanceInquiry = false;
		if (BaseVariables.balanceInquiryCodes.contains(requestBitfieldsWithValues.get(BaseVariables.nameOfbitfield3))) {
			isBalanceInquiry = true;
			if (transactionResult.equals("Decline")) {
				bitfield4 = "000000000000";
			} else {
				bitfield4 = BaseVariables.valueOfBitfield4;
			}
			if (transactionResult.equals("PartiallyApprove")) {
				transactionResult = "Approve";
			}
		}
		switch (transactionResult) {
		case "Approve":
			responseBitfieldswithValue.put(BaseVariables.nameOfbitfield39,
					setBitfieldValue(BaseVariables.nameOfbitfield39, BaseVariables.ValueOfBitfield39Approval));
			break;
		case "Decline":
			responseBitfieldswithValue.put(BaseVariables.nameOfbitfield39,
					setBitfieldValue(BaseVariables.nameOfbitfield39, BaseVariables.ValueOfBitfield39Decline));
			break;
		case "PartiallyApprove":
			bitfield4 = generateHalfAmountForPartialApproval(requestBitfieldsWithValues.get(BaseVariables.nameOfbitfield4));
			responseBitfieldswithValue.put(BaseVariables.nameOfbitfield39,
					setBitfieldValue(BaseVariables.nameOfbitfield39, BaseVariables.ValueOfBitfield39Partial));
			break;
		}

		if (transactionResult.equals("PartiallyApprove") || isBalanceInquiry) {
			responseBitfieldswithValue.put(BaseVariables.nameOfbitfield4,
					setBitfieldValue(BaseVariables.nameOfbitfield4, bitfield4));
		}

		if (transactionResult.equals("Approve") || transactionResult.equals("PartiallyApprove")) {
			responseBitfieldswithValue.put(BaseVariables.nameOfbitfield38,
					setBitfieldValue(BaseVariables.nameOfbitfield38, BaseVariables.valueOfBitfield38));
			elementsInTransaction.add(38);
		}
		if (transactionResult.equals("Decline")) {
			responseBitfieldswithValue.put(BaseVariables.nameOfbitfield44,
					setBitfieldValue(BaseVariables.nameOfbitfield44, BaseVariables.valueOfBitfield44));
			elementsInTransaction.add(44);
		}

		addDE54IfNecessary();

		if (requestBitfieldsWithValues.containsKey(BaseVariables.nameOfbitfield55)) {
			responseBitfieldswithValue.put(BaseVariables.nameOfbitfield55,
					requestBitfieldsWithValues.get(BaseVariables.nameOfbitfield55));
			elementsInTransaction.add(55);
		}

	}

	@Override
	public void financialSalesPendingBitfieldsUpdate() {
		responseBitfieldswithValue.put(BaseVariables.nameOfbitfield2, generateBitfield2(requestBitfieldsWithValues));
		// Activation and Recharges transaction should not have partial approval
		if (BaseVariables.activationRechargeCodes.contains(requestBitfieldsWithValues.get(BaseVariables.nameOfbitfield3))
				&& transactionResult.equals("PartiallyApprove")) {
			transactionResult = "Approve";
		}
		switch (transactionResult) {
		case "Approve":
			responseBitfieldswithValue.put(BaseVariables.nameOfbitfield39,
					setBitfieldValue(BaseVariables.nameOfbitfield39, BaseVariables.ValueOfBitfield39Approval));
			break;
		case "Decline":
			responseBitfieldswithValue.put(BaseVariables.nameOfbitfield39,
					setBitfieldValue(BaseVariables.nameOfbitfield39, BaseVariables.ValueOfBitfield39Decline));
			break;
		case "PartiallyApprove":
			bitfield4 = generateHalfAmountForPartialApproval(requestBitfieldsWithValues.get(BaseVariables.nameOfbitfield4));
			responseBitfieldswithValue.put(BaseVariables.nameOfbitfield4,
					setBitfieldValue(BaseVariables.nameOfbitfield4, bitfield4));
			responseBitfieldswithValue.put(BaseVariables.nameOfbitfield39,
					setBitfieldValue(BaseVariables.nameOfbitfield39, BaseVariables.ValueOfBitfield39Partial));
			break;
		}

		if (transactionResult.equals("Approve") || transactionResult.equals("PartiallyApprove")) {
			responseBitfieldswithValue.put(BaseVariables.nameOfbitfield38,
					setBitfieldValue(BaseVariables.nameOfbitfield38, BaseVariables.valueOfBitfield38));
			elementsInTransaction.add(38);
		}
		if (transactionResult.equals("Decline")) {
			responseBitfieldswithValue.put(BaseVariables.nameOfbitfield44,
					setBitfieldValue(BaseVariables.nameOfbitfield44, BaseVariables.valueOfBitfield44));
			elementsInTransaction.add(44);
		}

		addDE54IfNecessary();

	}

	@Override
	public void financialForceDraftPendingBitfieldsUpdate() {
		responseBitfieldswithValue.put(BaseVariables.nameOfbitfield2, generateBitfield2(requestBitfieldsWithValues));
		if (transactionResult.equals("PartiallyApprove")) {
			transactionResult = "Approve";
		}
		switch (transactionResult) {
		case "Approve":
			responseBitfieldswithValue.put(BaseVariables.nameOfbitfield39,
					setBitfieldValue(BaseVariables.nameOfbitfield39, BaseVariables.ValueOfBitfield39Approval));
			break;
		case "Decline":
			responseBitfieldswithValue.put(BaseVariables.nameOfbitfield39,
					setBitfieldValue(BaseVariables.nameOfbitfield39, BaseVariables.ValueOfBitfield39Decline));
			break;
		case "PartiallyApprove":
			bitfield4 = generateHalfAmountForPartialApproval(requestBitfieldsWithValues.get(BaseVariables.nameOfbitfield4));
			responseBitfieldswithValue.put(BaseVariables.nameOfbitfield4,
					requestBitfieldsWithValues.get(BaseVariables.nameOfbitfield4));
			responseBitfieldswithValue.put(BaseVariables.nameOfbitfield39,
					setBitfieldValue(BaseVariables.nameOfbitfield39, BaseVariables.ValueOfBitfield39Partial));
			break;
		}

		if (transactionResult.equals("Approve") || transactionResult.equals("PartiallyApprove")) {
			responseBitfieldswithValue.put(BaseVariables.nameOfbitfield38,
					setBitfieldValue(BaseVariables.nameOfbitfield38, BaseVariables.valueOfBitfield38));
			elementsInTransaction.add(38);
		}
		if (transactionResult.equals("Decline")) {
			responseBitfieldswithValue.put(BaseVariables.nameOfbitfield44,
					setBitfieldValue(BaseVariables.nameOfbitfield44, BaseVariables.valueOfBitfield44));
			elementsInTransaction.add(44);
		}

		addDE54IfNecessary();
	}

	@Override
	public void reversalPendingBitfieldsUpdate() {
		if (transactionResult.equals("PartiallyApprove")) {
			transactionResult = "Approve";
		}
		responseBitfieldswithValue.put(BaseVariables.nameOfbitfield2, generateBitfield2(requestBitfieldsWithValues));
		if (transactionResult.equals("Approve")) {
			responseBitfieldswithValue.put(BaseVariables.nameOfbitfield39,
					setBitfieldValue(BaseVariables.nameOfbitfield39, BaseVariables.ValueOfBitfield39Reversal));
			responseBitfieldswithValue.put(BaseVariables.nameOfbitfield38,
					setBitfieldValue(BaseVariables.nameOfbitfield38, BaseVariables.valueOfBitfield38));
			elementsInTransaction.add(38);
		} else {
			responseBitfieldswithValue.put(BaseVariables.nameOfbitfield39,
					setBitfieldValue(BaseVariables.nameOfbitfield39, BaseVariables.ValueOfBitfield39Decline));
			responseBitfieldswithValue.put(BaseVariables.nameOfbitfield44,
					setBitfieldValue(BaseVariables.nameOfbitfield44, BaseVariables.valueOfBitfield44));
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
			responseBitfieldswithValue.put(BaseVariables.nameOfbitfield39,
					setBitfieldValue(BaseVariables.nameOfbitfield39, BaseVariables.ValueOfBitfield39Reconsillation));
			responseBitfieldswithValue.put(BaseVariables.nameOfbitfield123,
					setBitfieldValue(BaseVariables.nameOfbitfield123, BaseVariables.valueOfBitfield123));
		} else {
			responseBitfieldswithValue.put(BaseVariables.nameOfbitfield39,
					setBitfieldValue(BaseVariables.nameOfbitfield39, BaseVariables.ValueOfBitfield39Decline));
		}
		responseBitfieldswithValue.put(BaseVariables.nameOfbitfield48,
				setBitfieldValue(BaseVariables.nameOfbitfield48, BaseVariables.valueOfBitfield48));
	}

	private void addDE54IfNecessary() {
		if (BaseVariables.balanceInquiryCodes.contains(requestBitfieldsWithValues.get(BaseVariables.nameOfbitfield3))
				|| BaseVariables.activationRechargeCodes
						.contains(requestBitfieldsWithValues.get(BaseVariables.nameOfbitfield3))) {
			responseBitfieldswithValue.put(BaseVariables.nameOfbitfield54,
					setBitfieldValue(BaseVariables.nameOfbitfield54, BaseVariables.valueOfBitfield54));
			elementsInTransaction.add(54);
		}
	}

}
