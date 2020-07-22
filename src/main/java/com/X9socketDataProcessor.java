/*
 * This class is used to customize the socket programming for the X9 fep.
 * socketDataReadFormat method is implemented in such a way that entire set of bytes in the socket is read completely. 
 * Similarly, the way data is written into the socket is also customized to fit the requirement of the HPS. 
 */

package com;

import java.io.IOException;
import java.io.UnsupportedEncodingException;

import org.apache.log4j.Logger;

public class X9socketDataProcessor extends BaseSocketDataProcessor{
	private Logger logger = Logger.getLogger(X9socketDataProcessor.class);

	@Override
	public void socketDataReadFormat() {
		try {
			socketDataLength = dataInputStream.readShort();
		} catch (IOException e) {
			logger.debug(e.toString());
		}
		
	}

	@Override
	public void socketDataWriteFormat(String responsePacket) {
		responsePacket = converter.hexToASCII(responsePacket);
		try {
			formattedPacketBytes = responsePacket.getBytes("ISO-8859-1");
		} catch (UnsupportedEncodingException e) {
			logger.debug(e.toString());
		}
		socketDataLength = formattedPacketBytes.length;
	}

}
