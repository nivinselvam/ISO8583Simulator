package com.BaseFiles;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import org.apache.log4j.Logger;

import com.FCB.FCBresponseGenerator;
import com.HPSfiles.HPSresponseGenerator;
import com.INCOMMfiles.INCOMMresponseGenerator;
import com.X9Files.X9responseGenerator;

public abstract class BaseSocketDataProcessor extends Thread {

	protected DataInputStream dataInputStream;
	private DataOutputStream dataOutputSteam;
	private boolean listenToSocket = true;
	protected boolean writeDataLengthToSocket = true;
	protected int socketDataLength;
	protected byte[] formattedPacketBytes;
	protected StringBuffer requestPacketBuffer = new StringBuffer();
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
		try {
			dataInputStream = new DataInputStream(Initializer.getServer().getSocket().getInputStream());
			dataOutputSteam = new DataOutputStream(Initializer.getServer().getSocket().getOutputStream());
			
			//Continuously monitor the socket and read data whenever available
			while (listenToSocket) {
				while (dataInputStream.available() == 0) {
					try {
						Thread.sleep(1);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}

				}
				logger.info(
						"*************************************************************************************************");
				logger.info("                                  Start of Transaction");
				logger.info(
						"*************************************************************************************************");
				
				socketDataReadFormat();
				requestPacket = readDataFromSocket();
				if (Initializer.getBaseVariables().sendResponse.equalsIgnoreCase("Yes")) {
					generateResponse(requestPacket);
					socketDataWriteFormat(responsePacket);
					writeDataToSocket();
				} else {
					logger.info("Response is not sent as per the configuration");
				}
				logger.info(
						"*************************************************************************************************");
				logger.info("                                   End of Transaction");
				logger.info(
						"*************************************************************************************************");
			}
			dataInputStream.close();
			dataOutputSteam.close();
			Initializer.getServer().getSocket().close();

		} catch (IOException e) {
			System.out.println(e.toString());
		}

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
		
		
		formattedPacketBytes = new byte[socketDataLength];
		dataInputStream.read(formattedPacketBytes, 0, socketDataLength);

		requestPacketBuffer = new StringBuffer();
		for (byte currByte : formattedPacketBytes) {
			requestPacketBuffer.append(String.format("%02x", currByte));
		}
		logger.debug("Request packet received: " + requestPacketBuffer.toString());
		return requestPacketBuffer.toString();
		
		
//		requestPacketBuffer = new StringBuffer();
//		formattedPacketBytes = new byte[socketDataLength];		
//		
//		try {
//			while(dataInputStream.available()>0) {
//				requestPacketBuffer.append((char)dataInputStream.read());
//				
//			}
//		}catch(IOException e) {
//			logger.error("Exception while reading input stream: "+e);
//		}
//		
//		return requestPacketBuffer.toString();
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
	public void writeDataToSocket() {
		logger.debug("Starting the data writing into the socket:");
		try {
			if (writeDataLengthToSocket) {
				logger.debug("Writing the data length into the socket...");
				logger.debug("Length of "+socketDataLength+" is written into socket.");
				dataOutputSteam.writeShort(socketDataLength);
			}
			logger.debug("Writing the data into the socket...");
			dataOutputSteam.write(formattedPacketBytes);
			dataOutputSteam.flush();
		} catch (IOException e) {
			logger.error(e.toString());
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
		} else if (Initializer.getFEPname().equals("X9")) {
			responses = new X9responseGenerator(requestPacket);
		} else if(Initializer.getFEPname().equals("INCOMM")) {
			responses = new INCOMMresponseGenerator(requestPacket);
		} else if(Initializer.getFEPname().equalsIgnoreCase("FCB")) {
			responses = new FCBresponseGenerator(requestPacket);
		}
		responsePacket = responses.getResponsePacket();

	}

	/*
	 * -----------------------------------------------------------------------------
	 * This method is used for closing all the open resources
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
