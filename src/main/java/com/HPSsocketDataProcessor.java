package com;

import java.io.IOException;
import java.io.UnsupportedEncodingException;

import org.apache.log4j.Logger;

public class HPSsocketDataProcessor extends BaseSocketDataProcessor {
	private Logger logger = Logger.getLogger(HPSsocketDataProcessor.class);

	public void socketDataReadFormat() {
		try {
			socketDataLength = dataInputStream.readShort() - 2;
		} catch (IOException e) {
			logger.debug(e.toString());
		}

	}

	public void socketDataWriteFormat(String responsePacket) {
		responsePacket = converter.toHexString(responsePacket);
		try {
			formattedPacketBytes = responsePacket.getBytes("ISO-8859-1");
		} catch (UnsupportedEncodingException e) {
			logger.debug(e.toString());
		}
		socketDataLength = formattedPacketBytes.length + 2;
	}

}
