/*
 * This class initiates the server processing.
 * Server socket is created and will keep listing to the configured port for any client connections.
 */

package com.BaseFiles;

import java.io.IOException;
import java.net.*;
import java.util.ArrayList;

import javax.swing.JOptionPane;

import org.apache.log4j.Logger;

import com.HPSfiles.HPSsocketDataProcessor;
import com.X9Files.X9socketDataProcessor;

public class ServerInitializer extends Thread {
	private ServerSocket serverSocket;
	private Socket socket;
	private boolean shouldRun = true;
	private boolean serverStarted = false;
	private ArrayList<BaseSocketDataProcessor> connections = new ArrayList<BaseSocketDataProcessor>();
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
			serverSocket = new ServerSocket(Initializer.getPortNumber());
			serverStarted = true;
			logger.info(Initializer.getFEPname() + " Server started successfully");
			while (shouldRun) {
				socket = serverSocket.accept();
				loadSocketDataProcessor();
				socketDataProcessor.start();
				connections.add(socketDataProcessor);
			}
		}catch (BindException e) {
			serverStarted = false;
			logger.error("Cannot start the server on the configured port number. It is already in use");
			JOptionPane.showMessageDialog(null, "Cannot start the server on the configured port number. It is already in use");
		}catch (SocketException e) {
			logger.info("Server stopped");
		}catch (IOException e) {
			logger.error(e.toString());
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
		}else if(Initializer.getFEPname().equals("X9")) {
			socketDataProcessor = new X9socketDataProcessor();
		}
	}

}
