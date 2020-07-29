/*
 * This class is used to customize the socket programming for the HPS fep.
 * socketDataReadFormat method is implemented in such a way that the first two bytes in the byte stream is read to 
 * find out the overall length of the request packet.
 * Similarly, the way data is written into the socket is also customized to fit the requirement of the HPS. 
 */

package com;

import java.io.IOException;
import java.io.UnsupportedEncodingException;

import org.apache.log4j.Logger;

public class HPSsocketDataProcessor extends BaseSocketDataProcessor {
	private Logger logger = Logger.getLogger(HPSsocketDataProcessor.class);

	@Override
	public void socketDataReadFormat() {
		try {
			socketDataLength = dataInputStream.readShort() - 2;
		} catch (IOException e) {
			logger.debug(e.toString());
		}

	}

	public void socketDataWriteFormat(String responsePacket) {
		responsePacket = Initializer.getConverter().toHexString(responsePacket);
		try {
			formattedPacketBytes = responsePacket.getBytes("ISO-8859-1");
		} catch (UnsupportedEncodingException e) {
			logger.debug(e.toString());
		}
		socketDataLength = formattedPacketBytes.length + 2;
	}

}
