/*
 * This class is used to customize the socket programming for the Incomm fep.
 * socketDataReadFormat method is implemented in such a way that the first two bytes in the byte stream is read to 
 * find out the overall length of the request packet.
 * Similarly, the way data is written into the socket is also customized to fit the requirement of the Incomm. 
 * Please note that in Incomm, when the data length is written into the socket, it will not include the header bytes 
 * i.e., if data of length 100 should be sent, data length of 100 will be written into socket unlike hps where it will be data length +2
 */

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
			socketDataLength = dataInputStream.readShort();
			logger.debug("Socket has received data of length: " + socketDataLength);
		} catch (IOException e) {
			logger.debug(e.toString());
		}

	}

	@Override
	public void socketDataWriteFormat(String responsePacket) {
		responsePacket = Initializer.getConverter().toHexString(responsePacket);
		logger.debug("Converted value of response packet in hex format " + responsePacket);
		try {
			formattedPacketBytes = responsePacket.getBytes("ISO-8859-1");
			logger.debug("Response packet successfully converted to byte array");
		} catch (UnsupportedEncodingException e) {
			logger.debug(e.toString());
		}
		socketDataLength = formattedPacketBytes.length;
	}

}
