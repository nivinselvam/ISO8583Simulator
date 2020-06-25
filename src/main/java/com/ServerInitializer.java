/*
 * This class initiates the server processing.
 * Server socket is created and will keep listing to the configured port for any client connections.
 */

package com;

import java.net.*;
import java.util.ArrayList;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

public class ServerInitializer extends Thread {
	private ServerSocket serverSocket;
	private Socket socket;
	private boolean shouldRun = true;
	private boolean serverStarted = false;
	private ArrayList<BaseSocketDataProcessor> connections = new ArrayList<BaseSocketDataProcessor>();
	//private String serverStatus = "";
	private BaseSocketDataProcessor socketDataProcessor;
	private Logger logger = Logger.getLogger(ServerInitializer.class);

	public ServerSocket getServerSocket() {
		return serverSocket;
	}

	public Socket getSocket() {
		return socket;
	}

	/*
	 * -----------------------------------------------------------------------------
	 * As part of run method server socket is initialized and
	 * socket data processor is instantiated.
	 * -----------------------------------------------------------------------------
	 */
	public void run() {
		try {
			PropertyConfigurator.configure("log4j.properties");
			serverSocket = new ServerSocket(Initializer.getPortNumber());
			serverStarted = true;
			System.out.println(Initializer.getFEPname() + " Server started successfully");
			logger.info(Initializer.getFEPname() + " Server started successfully");
			while (shouldRun) {
				socket = serverSocket.accept();
				loadSocketDataProcessor();
				socketDataProcessor.start();
				connections.add(socketDataProcessor);
			}
		} catch (Exception e) {	
			if(serverStarted) {
				logger.info("Server stopped");
			}else {
				logger.fatal("Unable to start the server");
				System.out.println("Unable to start the server");
				serverStarted = false;
				e.printStackTrace();
			}			
		}
	}

	/*
	 * -----------------------------------------------------------------------------
	 *  This method instantiates the socket processor based on the
	 * FEP.
	 * -----------------------------------------------------------------------------
	 * 
	 */
	public void loadSocketDataProcessor() {
		if (Initializer.getFEPname().equals("HPS")) {
			socketDataProcessor = new HPSsocketDataProcessor();
		}
	}

}
