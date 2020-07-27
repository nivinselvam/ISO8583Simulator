/*
 * This class is to generate the X9 fep specific response
 */

package com;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class X9responseGenerator extends BaseResponseGenerator {

	public X9responseGenerator(String requestPacket) {
		super(requestPacket);
	}

	@Override
	public void authorizationPendingBitfieldsUpdate() {

		responseBitfieldswithValue.put(Initializer.getBaseConstants().nameOfbitfield7,
				setBitfieldLengthIfRequired(Initializer.getBaseConstants().nameOfbitfield7, generateBitField7Value()));

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

	}

	@Override
	public void financialSalesPendingBitfieldsUpdate() {

		responseBitfieldswithValue.put(Initializer.getBaseConstants().nameOfbitfield7,
				setBitfieldLengthIfRequired(Initializer.getBaseConstants().nameOfbitfield7, generateBitField7Value()));

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

	}

	@Override
	public void financialForceDraftPendingBitfieldsUpdate() {

	}

	@Override
	public void reversalPendingBitfieldsUpdate() {

	}

	@Override
	public void reconciliationPendingBitfieldsUpdate() {

	}

	// ------------------------------------------------------------------------------------------------------------------
	/*
	 * This method is used to generate a dynamic amount for partial approval. This
	 * takes transaction amount as input and returns half of it.
	 */
	// ------------------------------------------------------------------------------------------------------------------
	@Override
	public String generateBitfield4() {
		String tempString = "";
		int requestedAmount = Integer.parseInt(
				(requestBitfieldsWithValues.get(Initializer.getBaseConstants().nameOfbitfield4).substring(4)));
		if (Initializer.getBaseVariables().isHalfApprovalRequired.equalsIgnoreCase("true")
				|| requestedAmount < Integer.parseInt(Initializer.getBaseVariables().valueOfBitfield4)) {
			bitfield4 = Integer.toString(requestedAmount / 2);
			// Bitfield4 has a fixed length of 12 digits and has to have 0's for
			// the digits missing.
			int currentLength = bitfield4.length(), expectedLength = Initializer.getBitfieldData().bitfieldLength
					.get(Initializer.getBaseConstants().nameOfbitfield4);
			for (int i = 0; i < expectedLength - currentLength; i++) {
				tempString = tempString + "0";
			}
			bitfield4 = tempString + bitfield4;
		} else {
			bitfield4 = Initializer.getConverter().zeroPadding(Initializer.getBaseVariables().valueOfBitfield4,
					Initializer.getBitfieldData().bitfieldLength.get(Initializer.getBaseConstants().nameOfbitfield4));
		}
		return bitfield4;
	}

	// ------------------------------------------------------------------------------------------------------------------
	/*
	 * This method is used to generate bitfield 7 value specific to x9.
	 */
	// ------------------------------------------------------------------------------------------------------------------

	public String generateBitField7Value() {
		String bitfield7Value = "";
		SimpleDateFormat sdf = new SimpleDateFormat("MMddhhmmss");
		bitfield7Value = sdf.format(Calendar.getInstance().getTime());
		return bitfield7Value;

	}

}
