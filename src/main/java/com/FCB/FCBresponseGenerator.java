package com.FCB;

import java.text.SimpleDateFormat;
import java.util.Date;


import org.apache.log4j.Logger;

import com.BaseFiles.BaseResponseGenerator;
import com.BaseFiles.Initializer;

public class FCBresponseGenerator extends BaseResponseGenerator {
	private static Logger logger = Logger.getLogger(FCBresponseGenerator.class);
	//private StringBuffer responsePacketBuffer = new StringBuffer(super.requestPacket);
	private Date date = new Date();
	private SimpleDateFormat sdf;

	public FCBresponseGenerator(String requestPacket) {
		super(requestPacket);
	}

	// ------------------------------------------------------------------------------------------------------------------
	/*
	 * Validate if the transaction request is an echo request.
	 */
	// ------------------------------------------------------------------------------------------------------------------
	@Override
	public boolean validateifConnectivityCheck() {
		return false;

	}

	@Override
	public String connectivityCheckResponse() {
		return null;
	}

	@Override
	public void authorizationPendingBitfieldsUpdate() {
		responseBitfieldswithValue.put(Initializer.getBaseConstants().nameOfbitfield12,
				setBitfieldLengthIfRequired(Initializer.getBaseConstants().nameOfbitfield12, generateBitfield12()));
		responseBitfieldswithValue.put(Initializer.getBaseConstants().nameOfbitfield13,
				setBitfieldLengthIfRequired(Initializer.getBaseConstants().nameOfbitfield13, generateBitfield13()));
		responseBitfieldswithValue.put(Initializer.getBaseConstants().nameOfbitfield37, setBitfieldLengthIfRequired(
				Initializer.getBaseConstants().nameOfbitfield37, Initializer.getBaseVariables().valueOfBitfield37));
		if (transactionResult.equalsIgnoreCase("Approve")) {
			responseBitfieldswithValue.put(Initializer.getBaseConstants().nameOfbitfield38, setBitfieldLengthIfRequired(
					Initializer.getBaseConstants().nameOfbitfield38, Initializer.getBaseVariables().valueOfBitfield38));
			responseBitfieldswithValue.put(Initializer.getBaseConstants().nameOfbitfield39,
					setBitfieldLengthIfRequired(Initializer.getBaseConstants().nameOfbitfield39,
							Initializer.getBaseVariables().ValueOfBitfield39Approval));
		}
	}

	@Override
	public void financialSalesPendingBitfieldsUpdate() {

	}

	@Override
	public void financialForceDraftPendingBitfieldsUpdate() {
			//There is no new field that are required for force draft. So nothing need to be implemented.
	}

	@Override
	public void reversalPendingBitfieldsUpdate() {

	}

	@Override
	public void reconciliationPendingBitfieldsUpdate() {
		responseBitfieldswithValue.put(Initializer.getBaseConstants().nameOfbitfield12,
				setBitfieldLengthIfRequired(Initializer.getBaseConstants().nameOfbitfield12, generateBitfield12()));
		responseBitfieldswithValue.put(Initializer.getBaseConstants().nameOfbitfield13,
				setBitfieldLengthIfRequired(Initializer.getBaseConstants().nameOfbitfield13, generateBitfield13()));
		responseBitfieldswithValue.put(Initializer.getBaseConstants().nameOfbitfield37, setBitfieldLengthIfRequired(
				Initializer.getBaseConstants().nameOfbitfield37, Initializer.getBaseVariables().valueOfBitfield37));
		if (transactionResult.equalsIgnoreCase("Approve")) {
			responseBitfieldswithValue.put(Initializer.getBaseConstants().nameOfbitfield39,
					setBitfieldLengthIfRequired(Initializer.getBaseConstants().nameOfbitfield39,
							Initializer.getBaseVariables().ValueOfBitfield39Reconciliation));
		}else {
			responseBitfieldswithValue.put(Initializer.getBaseConstants().nameOfbitfield39,
					setBitfieldLengthIfRequired(Initializer.getBaseConstants().nameOfbitfield39,
							Initializer.getBaseVariables().ValueOfBitfield39Decline));
		}
	}

	@Override
	public boolean isOutdoorTransaction() {
		return false;
	}

	/*
	 * This method is used to generate the current time in HHmmss format which will
	 * be used to update the bitfield 12 during runtime.
	 */
	public String generateBitfield12() {
		sdf = new SimpleDateFormat("HHmmss");
		logger.debug("Date generated for bitfield 12 is " + sdf.format(date));
		return sdf.format(date);
	}

	/*
	 * This method is used to generate the current date in MMdd format which will be
	 * used to update the bitfield 13 during runtime.
	 */
	public String generateBitfield13() {
		sdf = new SimpleDateFormat("MMdd");
		logger.debug("Time generated for bitfield 13 is " + sdf.format(date));
		return sdf.format(date);
	}

}
