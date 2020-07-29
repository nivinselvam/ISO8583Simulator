package com;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import org.apache.log4j.Logger;

public abstract class BaseSocketDataProcessor extends Thread {

	protected DataInputStream dataInputStream;
	private DataOutputStream dataOutputSteam;
	private boolean listenToSocket = true;
	protected boolean writeDataLengthToSocket = true;
	protected int socketDataLength;
	protected byte[] formattedPacketBytes;
	private StringBuffer requestPacketBuffer = new StringBuffer();
	private String requestPacket, responsePacket;
	private BaseResponseGenerator responses;
	private Logger logger = Logger.getLogger(BaseSocketDataProcessor.class);

	/*
	 * -----------------------------------------------------------------------------
	 * Class Constructor Data input stream and output stream is initialized as part
	 * of constructor since it will be used in the readsocket methods later.
	 * -----------------------------------------------------------------------------
	 */
	public BaseSocketDataProcessor() {
		try {
			dataInputStream = new DataInputStream(Initializer.getServer().getSocket().getInputStream());
			dataOutputSteam = new DataOutputStream(Initializer.getServer().getSocket().getOutputStream());
		} catch (IOException e) {
			logger.fatal(e.toString());
		}

	}

	/*
	 * -----------------------------------------------------------------------------
	 * As part of run method, following actions are performed i) Data is read from
	 * the socket ii) Response is generated based on the request iii) Data is
	 * written into the socket
	 * -----------------------------------------------------------------------------
	 */
	public void run() {
		logger.info(
				"Client " + Initializer.getServer().getSocket().getRemoteSocketAddress().toString() + " is connected");
		logger.info(
				"*************************************************************************************************");
		logger.info("                                  Start of Transaction");
		logger.info(
				"*************************************************************************************************");

		try {
			socketDataReadFormat();
			requestPacket = readDataFromSocket();
		} catch (IOException e) {
			logger.fatal(e.toString());
		}
		if (Initializer.getBaseVariables().sendResponse.equalsIgnoreCase("Yes")) {
			generateResponse(requestPacket);
			socketDataWriteFormat(responsePacket);
			writeDataToSocket(responsePacket);
		}
		logger.info(
				"*************************************************************************************************");
		logger.info("                                   End of Transaction");
		logger.info(
				"*************************************************************************************************");

	}

	/*
	 * -----------------------------------------------------------------------------
	 * This method is used to set the format(based on customer) in which data should
	 * be read from socket. This is an abstract method which should be implemented
	 * based on the customer for which the server is run
	 * -----------------------------------------------------------------------------
	 */
	public abstract void socketDataReadFormat();

	/*
	 * -----------------------------------------------------------------------------
	 * This method is used for reading the data from the socket.
	 * socketDataReadFormat method should be executed as a precondition for this
	 * method, since it decided the format should be read.
	 * -----------------------------------------------------------------------------
	 */
	public String readDataFromSocket() throws IOException {
		while (listenToSocket) {
			while (dataInputStream.available() == 0) {
				try {
					Thread.sleep(1);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}

			}
			formattedPacketBytes = new byte[socketDataLength];
			dataInputStream.read(formattedPacketBytes, 0, socketDataLength);

			for (byte currByte : formattedPacketBytes) {
				requestPacketBuffer.append(String.format("%02x", currByte));
			}
			logger.debug(requestPacketBuffer.toString());
			return requestPacketBuffer.toString();
		}
		return null;
	}

	/*
	 * -----------------------------------------------------------------------------
	 * This method is used to set the format(based on customer) in which data should
	 * be written into the socket. This is an abstract method which should be
	 * implemented based on the customer for which the server is run
	 * -----------------------------------------------------------------------------
	 */
	public abstract void socketDataWriteFormat(String responsePacket);

	/*
	 * -----------------------------------------------------------------------------
	 * This method is used for reading the data from the socket.
	 * socketDataReadFormat method should be executed as a precondition for this
	 * method, since it decided the format should be read.
	 * -----------------------------------------------------------------------------
	 */
	public void writeDataToSocket(String text) {
		try {
			logger.debug(text);
			if(writeDataLengthToSocket) {
				dataOutputSteam.writeShort(socketDataLength);
			}			
			dataOutputSteam.write(formattedPacketBytes);
			dataOutputSteam.flush();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/*
	 * -----------------------------------------------------------------------------
	 * This method is used to generate the response specific to each request type.
	 * -----------------------------------------------------------------------------
	 */
	public void generateResponse(String requestPacket) {
		if (Initializer.getFEPname().equals("HPS")) {
			responses = new HPSresponseGenerator(requestPacket);
		}else if(Initializer.getFEPname().equals("X9")) {
			responses = new X9responseGenerator(requestPacket);
		}
		responsePacket = responses.getResponsePacket();

	}

	/*
	 * -----------------------------------------------------------------------------
	 *  This method is used for closing all the open resources
	 * -----------------------------------------------------------------------------
	 */
	public void closeServer() throws IOException {
		try {
			if (dataOutputSteam != null)
				dataOutputSteam.close();
			if (dataInputStream != null)
				dataInputStream.close();
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		if (!Initializer.getServer().getSocket().isClosed())
			Initializer.getServer().getSocket().close();
		logger.info("Server connection closed");

	}
}
