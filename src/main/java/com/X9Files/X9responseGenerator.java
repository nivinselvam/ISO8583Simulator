/*
 * This class is to generate the X9 fep specific response
 */

package com.X9Files;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.List;

import com.BaseFiles.BaseResponseGenerator;
import com.BaseFiles.Initializer;

public class X9responseGenerator extends BaseResponseGenerator {
	private String valueOfBitfield63FromRequestPacket;
	private List<String> validValuesOfBitfield63 = new ArrayList<String>(Arrays.asList("\\951O01\\", "\\952O01\\", "\\953O01\\", "\\954O01\\", "\\966O01\\", "\\967O01\\", "\\968O01\\"));

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

			valueOfBitfield63FromRequestPacket = responseBitfieldswithValue.get(Initializer.getBaseConstants().nameOfbitfield63);
			if (valueOfBitfield63FromRequestPacket != null && validValuesOfBitfield63.contains(valueOfBitfield63FromRequestPacket)) {
				responseBitfieldswithValue.put(Initializer.getBaseConstants().nameOfbitfield62,
						setBitfieldLengthIfRequired(Initializer.getBaseConstants().nameOfbitfield62,
								Initializer.getBaseVariables().valueOfBitfield62));
				elementsInTransaction.add(62);
			}
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
			
			valueOfBitfield63FromRequestPacket = responseBitfieldswithValue.get(Initializer.getBaseConstants().nameOfbitfield63);
			if (requestBitfieldsWithValues.get(Initializer.getBaseConstants().nameOfbitfield63) != null
					&& validValuesOfBitfield63.contains(valueOfBitfield63FromRequestPacket)) {
				responseBitfieldswithValue.put(Initializer.getBaseConstants().nameOfbitfield62,
						setBitfieldLengthIfRequired(Initializer.getBaseConstants().nameOfbitfield62,
								Initializer.getBaseVariables().valueOfBitfield62));
				elementsInTransaction.add(62);
			}
		}
		if (transactionResult.equals("Decline")) {
			responseBitfieldswithValue.put(Initializer.getBaseConstants().nameOfbitfield44, setBitfieldLengthIfRequired(
					Initializer.getBaseConstants().nameOfbitfield44, Initializer.getBaseVariables().valueOfBitfield44));
			elementsInTransaction.add(44);
		}

	}

	@Override
	public void financialForceDraftPendingBitfieldsUpdate() {
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
	public void reversalPendingBitfieldsUpdate() {
		if (transactionResult.equalsIgnoreCase(Initializer.getBaseConstants().approvalValue)) {
			responseBitfieldswithValue.put(Initializer.getBaseConstants().nameOfbitfield38, setBitfieldLengthIfRequired(
					Initializer.getBaseConstants().nameOfbitfield38, Initializer.getBaseVariables().valueOfBitfield38));
			elementsInTransaction.add(38);
			responseBitfieldswithValue.put(Initializer.getBaseConstants().nameOfbitfield39,
					setBitfieldLengthIfRequired(Initializer.getBaseConstants().nameOfbitfield39,
							Initializer.getBaseVariables().ValueOfBitfield39Reversal));
		} else if (transactionResult.equalsIgnoreCase(Initializer.getBaseConstants().declineValue)) {
			responseBitfieldswithValue.put(Initializer.getBaseConstants().nameOfbitfield39,
					setBitfieldLengthIfRequired(Initializer.getBaseConstants().nameOfbitfield39,
							Initializer.getBaseVariables().ValueOfBitfield39Decline));
			responseBitfieldswithValue.put(Initializer.getBaseConstants().nameOfbitfield44, setBitfieldLengthIfRequired(
					Initializer.getBaseConstants().nameOfbitfield44, Initializer.getBaseVariables().valueOfBitfield44));
			elementsInTransaction.add(44);
		}
	}

	@Override
	public void reconciliationPendingBitfieldsUpdate() {

		responseBitfieldswithValue.put(Initializer.getBaseConstants().nameOfbitfield7,
				setBitfieldLengthIfRequired(Initializer.getBaseConstants().nameOfbitfield7, generateBitField7Value()));

		if (transactionResult.equalsIgnoreCase(Initializer.getBaseConstants().approvalValue)) {
			responseBitfieldswithValue.put(Initializer.getBaseConstants().nameOfbitfield39,
					setBitfieldLengthIfRequired(Initializer.getBaseConstants().nameOfbitfield39,
							Initializer.getBaseVariables().ValueOfBitfield39Reconciliation));
		} else if (transactionResult.equalsIgnoreCase(Initializer.getBaseConstants().declineValue)) {
			responseBitfieldswithValue.put(Initializer.getBaseConstants().nameOfbitfield39,
					setBitfieldLengthIfRequired(Initializer.getBaseConstants().nameOfbitfield39,
							Initializer.getBaseVariables().ValueOfBitfield39Decline));
		}
	}

	// ------------------------------------------------------------------------------------------------------------------
	/*
	 * This method is used to generate a dynamic amount for partial approval. This
	 * takes transaction amount as input and returns half of it.
	 */
	// ------------------------------------------------------------------------------------------------------------------
	@Override
	public String generateBitfield4() {
		String prefix;
		int requestedAmount, expectedLength, currentLength;
		requestedAmount = Integer.parseInt(
				(requestBitfieldsWithValues.get(Initializer.getBaseConstants().nameOfbitfield4).substring(4)));
		// Subtracting 4 from the expected length since first 4 digits in bitfield4 will
		// be a prefix.
		expectedLength = Initializer.getBitfieldData().bitfieldLength
				.get(Initializer.getBaseConstants().nameOfbitfield4) - 4;
		prefix = requestBitfieldsWithValues.get(Initializer.getBaseConstants().nameOfbitfield4).substring(0, 4);

		if (Initializer.getBaseVariables().isHalfApprovalRequired.equalsIgnoreCase("true")
				|| requestedAmount < Integer.parseInt(Initializer.getBaseVariables().valueOfBitfield4)) {
			bitfield4 = Integer.toString(requestedAmount / 2);
			currentLength = bitfield4.length();
			// Bitfield4 has a fixed length of 12 digits and has to have 0's for
			// the digits missing.
			for (int i = 0; i < expectedLength - currentLength; i++) {
				bitfield4 = "0" + bitfield4;
			}
			bitfield4 = prefix + bitfield4;
		} else {
			bitfield4 = prefix + Initializer.getConverter().zeroPadding(Initializer.getBaseVariables().valueOfBitfield4,
					expectedLength);
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

	@Override
	public boolean isOutdoorTransaction() {
		return false;
		
	}

}
