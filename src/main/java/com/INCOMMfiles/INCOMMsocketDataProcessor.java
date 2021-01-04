package com.INCOMMfiles;

import java.io.IOException;
import java.io.UnsupportedEncodingException;

import org.apache.log4j.Logger;

import com.BaseFiles.BaseSocketDataProcessor;
import com.BaseFiles.Initializer;
import com.X9Files.X9socketDataProcessor;

public class INCOMMsocketDataProcessor extends BaseSocketDataProcessor {
	private Logger logger = Logger.getLogger(X9socketDataProcessor.class);

	@Override
	public void socketDataReadFormat() {
		try {
			socketDataLength = dataInputStream.readShort()-2;
		} catch (IOException e) {
			logger.debug(e.toString());
		}

	}

	@Override
	public void socketDataWriteFormat(String responsePacket) {
		responsePacket = Initializer.getConverter().hexToASCII(responsePacket);
		try {
			formattedPacketBytes = responsePacket.getBytes("ISO-8859-1");
		} catch (UnsupportedEncodingException e) {
			logger.debug(e.toString());
		}
		socketDataLength = formattedPacketBytes.length + 2;
	}

}
