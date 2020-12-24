package com.INCOMMfiles;

import org.apache.log4j.Logger;

import com.BaseFiles.BaseDecoder;
import com.BaseFiles.Initializer;

public class INCOMMDecoder extends BaseDecoder {
	private static Logger logger = Logger.getLogger(INCOMMDecoder.class);

	public INCOMMDecoder(String requestPacket) {
		super(requestPacket);
		readDataFormat = 2;
	}

	// -----------------------------------------------------------------------------------------------------------------------------------------
	/*
	 * This method is used to grep the MTI from the request packet. This is
	 * overridden because in Incomm the MTI is sent in hex format as part of request
	 * packet and this should be converted to ascii format
	 */
	// -----------------------------------------------------------------------------------------------------------------------------------------
	@Override
	public void generateMTI() {
		MTI = Initializer.getConverter().hexToASCII(requestPacket
				.substring(Initializer.getBaseConstants().mtiStartPoint, Initializer.getBaseConstants().mtiEndPoint));
		logger.debug("MTI is set as " + MTI);
	}

	// -----------------------------------------------------------------------------------------------------------------------------------------
	/*
	 * This function is to set the value for the bitfield as per fep requirement
	 * Values may be sometimes plain string and hex data otherwise. So bitfieldwise
	 * processing will be in getting the value in required format.
	 */
	// -----------------------------------------------------------------------------------------------------------------------------------------
	@Override
	public String getFormattedBitfieldValue(String bitfield, String bitfieldValue) {
		return Initializer.getConverter().hexToASCII(bitfieldValue);
	}

}
