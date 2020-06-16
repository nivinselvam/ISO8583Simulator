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
		responseBitfieldswithValue.put(BaseConstants.nameOfbitfield2, generateBitfield2(requestBitfieldsWithValues));
		// Partial approval is not applicable for balance inquiry.
		isBalanceInquiry = false;
		if (BaseConstants.balanceInquiryCodes.contains(requestBitfieldsWithValues.get(BaseConstants.nameOfbitfield3))) {
			isBalanceInquiry = true;
			if (transactionResult.equals("Decline")) {
				bitfield4 = "000000000000";
			} else {
				bitfield4 = BaseConstants.valueOfBitfield4;
			}
			if (transactionResult.equals("PartiallyApprove")) {
				transactionResult = "Approve";
			}
		}
		switch (transactionResult) {
		case "Approve":
			responseBitfieldswithValue.put(BaseConstants.nameOfbitfield39,
					setBitfieldValue(BaseConstants.nameOfbitfield39, BaseConstants.ValueOfBitfield39Approval));
			break;
		case "Decline":
			responseBitfieldswithValue.put(BaseConstants.nameOfbitfield39,
					setBitfieldValue(BaseConstants.nameOfbitfield39, BaseConstants.ValueOfBitfield39Decline));
			break;
		case "PartiallyApprove":
			bitfield4 = generateHalfAmountForPartialApproval(requestBitfieldsWithValues.get(BaseConstants.nameOfbitfield4));
			responseBitfieldswithValue.put(BaseConstants.nameOfbitfield39,
					setBitfieldValue(BaseConstants.nameOfbitfield39, BaseConstants.ValueOfBitfield39Partial));
			break;
		}

		if (transactionResult.equals("PartiallyApprove") || isBalanceInquiry) {
			responseBitfieldswithValue.put(BaseConstants.nameOfbitfield4,
					setBitfieldValue(BaseConstants.nameOfbitfield4, bitfield4));
		}

		if (transactionResult.equals("Approve") || transactionResult.equals("PartiallyApprove")) {
			responseBitfieldswithValue.put(BaseConstants.nameOfbitfield38,
					setBitfieldValue(BaseConstants.nameOfbitfield38, BaseConstants.valueOfBitfield38));
			elementsInTransaction.add(38);
		}
		if (transactionResult.equals("Decline")) {
			responseBitfieldswithValue.put(BaseConstants.nameOfbitfield44,
					setBitfieldValue(BaseConstants.nameOfbitfield44, BaseConstants.valueOfBitfield44));
			elementsInTransaction.add(44);
		}

		addDE54IfNecessary();

		if (requestBitfieldsWithValues.containsKey(BaseConstants.nameOfbitfield55)) {
			responseBitfieldswithValue.put(BaseConstants.nameOfbitfield55,
					requestBitfieldsWithValues.get(BaseConstants.nameOfbitfield55));
			elementsInTransaction.add(55);
		}

	}

	@Override
	public void financialSalesPendingBitfieldsUpdate() {
		responseBitfieldswithValue.put(BaseConstants.nameOfbitfield2, generateBitfield2(requestBitfieldsWithValues));
		// Activation and Recharges transaction should not have partial approval
		if (BaseConstants.activationRechargeCodes.contains(requestBitfieldsWithValues.get(BaseConstants.nameOfbitfield3))
				&& transactionResult.equals("PartiallyApprove")) {
			transactionResult = "Approve";
		}
		switch (transactionResult) {
		case "Approve":
			responseBitfieldswithValue.put(BaseConstants.nameOfbitfield39,
					setBitfieldValue(BaseConstants.nameOfbitfield39, BaseConstants.ValueOfBitfield39Approval));
			break;
		case "Decline":
			responseBitfieldswithValue.put(BaseConstants.nameOfbitfield39,
					setBitfieldValue(BaseConstants.nameOfbitfield39, BaseConstants.ValueOfBitfield39Decline));
			break;
		case "PartiallyApprove":
			bitfield4 = generateHalfAmountForPartialApproval(requestBitfieldsWithValues.get(BaseConstants.nameOfbitfield4));
			responseBitfieldswithValue.put(BaseConstants.nameOfbitfield4,
					setBitfieldValue(BaseConstants.nameOfbitfield4, bitfield4));
			responseBitfieldswithValue.put(BaseConstants.nameOfbitfield39,
					setBitfieldValue(BaseConstants.nameOfbitfield39, BaseConstants.ValueOfBitfield39Partial));
			break;
		}

		if (transactionResult.equals("Approve") || transactionResult.equals("PartiallyApprove")) {
			responseBitfieldswithValue.put(BaseConstants.nameOfbitfield38,
					setBitfieldValue(BaseConstants.nameOfbitfield38, BaseConstants.valueOfBitfield38));
			elementsInTransaction.add(38);
		}
		if (transactionResult.equals("Decline")) {
			responseBitfieldswithValue.put(BaseConstants.nameOfbitfield44,
					setBitfieldValue(BaseConstants.nameOfbitfield44, BaseConstants.valueOfBitfield44));
			elementsInTransaction.add(44);
		}

		addDE54IfNecessary();

	}

	@Override
	public void financialForceDraftPendingBitfieldsUpdate() {
		responseBitfieldswithValue.put(BaseConstants.nameOfbitfield2, generateBitfield2(requestBitfieldsWithValues));
		if (transactionResult.equals("PartiallyApprove")) {
			transactionResult = "Approve";
		}
		switch (transactionResult) {
		case "Approve":
			responseBitfieldswithValue.put(BaseConstants.nameOfbitfield39,
					setBitfieldValue(BaseConstants.nameOfbitfield39, BaseConstants.ValueOfBitfield39Approval));
			break;
		case "Decline":
			responseBitfieldswithValue.put(BaseConstants.nameOfbitfield39,
					setBitfieldValue(BaseConstants.nameOfbitfield39, BaseConstants.ValueOfBitfield39Decline));
			break;
		case "PartiallyApprove":
			bitfield4 = generateHalfAmountForPartialApproval(requestBitfieldsWithValues.get(BaseConstants.nameOfbitfield4));
			responseBitfieldswithValue.put(BaseConstants.nameOfbitfield4,
					requestBitfieldsWithValues.get(BaseConstants.nameOfbitfield4));
			responseBitfieldswithValue.put(BaseConstants.nameOfbitfield39,
					setBitfieldValue(BaseConstants.nameOfbitfield39, BaseConstants.ValueOfBitfield39Partial));
			break;
		}

		if (transactionResult.equals("Approve") || transactionResult.equals("PartiallyApprove")) {
			responseBitfieldswithValue.put(BaseConstants.nameOfbitfield38,
					setBitfieldValue(BaseConstants.nameOfbitfield38, BaseConstants.valueOfBitfield38));
			elementsInTransaction.add(38);
		}
		if (transactionResult.equals("Decline")) {
			responseBitfieldswithValue.put(BaseConstants.nameOfbitfield44,
					setBitfieldValue(BaseConstants.nameOfbitfield44, BaseConstants.valueOfBitfield44));
			elementsInTransaction.add(44);
		}

		addDE54IfNecessary();
	}

	@Override
	public void reversalPendingBitfieldsUpdate() {
		if (transactionResult.equals("PartiallyApprove")) {
			transactionResult = "Approve";
		}
		responseBitfieldswithValue.put(BaseConstants.nameOfbitfield2, generateBitfield2(requestBitfieldsWithValues));
		if (transactionResult.equals("Approve")) {
			responseBitfieldswithValue.put(BaseConstants.nameOfbitfield39,
					setBitfieldValue(BaseConstants.nameOfbitfield39, BaseConstants.ValueOfBitfield39Reversal));
			responseBitfieldswithValue.put(BaseConstants.nameOfbitfield38,
					setBitfieldValue(BaseConstants.nameOfbitfield38, BaseConstants.valueOfBitfield38));
			elementsInTransaction.add(38);
		} else {
			responseBitfieldswithValue.put(BaseConstants.nameOfbitfield39,
					setBitfieldValue(BaseConstants.nameOfbitfield39, BaseConstants.ValueOfBitfield39Decline));
			responseBitfieldswithValue.put(BaseConstants.nameOfbitfield44,
					setBitfieldValue(BaseConstants.nameOfbitfield44, BaseConstants.valueOfBitfield44));
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
			responseBitfieldswithValue.put(BaseConstants.nameOfbitfield39,
					setBitfieldValue(BaseConstants.nameOfbitfield39, BaseConstants.ValueOfBitfield39Reconsillation));
			responseBitfieldswithValue.put(BaseConstants.nameOfbitfield123,
					setBitfieldValue(BaseConstants.nameOfbitfield123, BaseConstants.valueOfBitfield123));
		} else {
			responseBitfieldswithValue.put(BaseConstants.nameOfbitfield39,
					setBitfieldValue(BaseConstants.nameOfbitfield39, BaseConstants.ValueOfBitfield39Decline));
		}
		responseBitfieldswithValue.put(BaseConstants.nameOfbitfield48,
				setBitfieldValue(BaseConstants.nameOfbitfield48, BaseConstants.valueOfBitfield48));
	}

	private void addDE54IfNecessary() {
		if (BaseConstants.balanceInquiryCodes.contains(requestBitfieldsWithValues.get(BaseConstants.nameOfbitfield3))
				|| BaseConstants.activationRechargeCodes
						.contains(requestBitfieldsWithValues.get(BaseConstants.nameOfbitfield3))) {
			responseBitfieldswithValue.put(BaseConstants.nameOfbitfield54,
					setBitfieldValue(BaseConstants.nameOfbitfield54, BaseConstants.valueOfBitfield54));
			elementsInTransaction.add(54);
		}
	}

}
