package com;

import org.apache.log4j.Logger;

public class HPSDecoder extends BaseDecoder {
	private static Logger logger = Logger.getLogger(HPSDecoder.class);

	public HPSDecoder(String requestPacket) {
		super(requestPacket);
		// This is to configure the decoder for reading hex data
		readDataFormat = 2;

	}

	// -----------------------------------------------------------------------------------------------------------------------------------------
	/*
	 * This method is used to grep the header from the request packet. This is
	 * overridden because in HPS the header is sent in hex format as part of request
	 * packet and this should be converted to ascii format
	 */
	// -----------------------------------------------------------------------------------------------------------------------------------------
	@Override
	public void generateHeader() {
		try {
			header = Initializer.getConverter()
					.hexToASCII(requestPacket.substring(BaseConstants.HeaderStartPoint, BaseConstants.HeaderEndPoint));
			logger.debug("Header is set as " + header);
		} catch (NumberFormatException e) {
			header = "";
			logger.warn("Header is empty");
		}
	}

	// -----------------------------------------------------------------------------------------------------------------------------------------
	/*
	 * This method is used to grep the MTI from the request packet. This is
	 * overridden because in HPS the MTI is sent in hex format as part of request
	 * packet and this should be converted to ascii format
	 */
	// -----------------------------------------------------------------------------------------------------------------------------------------
	@Override
	public void generateMTI() {
		MTI = Initializer.getConverter()
				.hexToASCII(requestPacket.substring(BaseConstants.mtiStartPoint, BaseConstants.mtiEndPoint));
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
