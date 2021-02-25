/*
 * This class is used to customize the socket programming for the FCB fep.
 * socketDataReadFormat method is implemented in such a way that the first two bytes in the byte stream is read to 
 * find out the overall length of the request packet.
 * Similarly, the way data is written into the socket is also customized to fit the requirement of the FCB. 
 */

package com.FCB;

import java.io.IOException;
import java.io.UnsupportedEncodingException;

import org.apache.log4j.Logger;

import com.BaseFiles.BaseSocketDataProcessor;
import com.BaseFiles.Initializer;

public class FCBsocketDataProcessor extends BaseSocketDataProcessor {
	private Logger logger = Logger.getLogger(FCBsocketDataProcessor.class);
	
	
	public void socketDataReadFormat() {
		try {
			socketDataLength = dataInputStream.readShort();
			logger.debug("Socket has received data of length: "+socketDataLength);
		} catch (IOException e) {
			logger.debug(e.toString());
		}

	}

	public void socketDataWriteFormat(String responsePacket) {
		responsePacket = Initializer.getConverter().toHexString(responsePacket);
		try {
			formattedPacketBytes = responsePacket.getBytes("ISO-8859-1");
			logger.debug("Response packet successfully converted to byte array");
		} catch (UnsupportedEncodingException e) {
			logger.debug(e.toString());
		}
		socketDataLength = formattedPacketBytes.length;
	}

}
