/*
 * This class is used to decode the request packet as per X9 FEPs requirement
 */

package com;

import org.apache.log4j.Logger;

public class X9Decoder extends BaseDecoder {
	private static Logger logger = Logger.getLogger(X9Decoder.class);

	public X9Decoder(String requestPacket) {
		super(requestPacket);
		// This is to configure the decoder for reading hex data
		readDataFormat = 2;
	}

	// -----------------------------------------------------------------------------------------------------------------------------------------
	/*
	 * This method is used to grep the header from the request packet. This is
	 * overridden because in X9 there is no header involved
	 */
	// -----------------------------------------------------------------------------------------------------------------------------------------
	@Override
	public void generateHeader() {
		header = "";
		logger.debug("X9 doesnt have header, so the value is set as empty value");
	}

	// -----------------------------------------------------------------------------------------------------------------------------------------
	/*
	 * This method is used to grep the MTI from the request packet. This is
	 * overridden because in X9 the MTI is sent in hex format as part of request
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
