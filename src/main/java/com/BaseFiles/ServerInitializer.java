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

import com.FCB.FCBsocketDataProcessor;
import com.HPSfiles.HPSsocketDataProcessor;
import com.INCOMMfiles.INCOMMsocketDataProcessor;
import com.X9Files.X9socketDataProcessor;

public class ServerInitializer extends Thread {
	private ServerSocket serverSocket;
	private Socket socket;
	private boolean shouldRun = true;
	public boolean serverStarted = false;
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
	 * As part of run method server socket is initialized and socket data processor
	 * is instantiated.
	 * -----------------------------------------------------------------------------
	 */
	public void run() {
		try {
			serverSocket = new ServerSocket(Initializer.getPortNumber());
			Initializer.serverStatusChanged = true;
			logger.debug("Server status updated successfully");
			serverStarted = true;
			logger.info(Initializer.getFEPname() + " Server started successfully");
			while (shouldRun) {
				socket = serverSocket.accept();
				loadSocketDataProcessor();
				socketDataProcessor.start();
				connections.add(socketDataProcessor);
			}
		} catch (BindException e) {
			Initializer.serverStatusChanged = true;
			logger.debug("Server status updated successfully after bindexception");
			serverStarted = false;
			logger.error("Cannot start the server on port number "+Initializer.getPortNumber()+". It is already in use");
			JOptionPane.showMessageDialog(null,
					"Cannot start the server on port number "+Initializer.getPortNumber()+". It is already in use");
		} catch (SocketException e) {
			Initializer.serverStatusChanged = true;
			logger.debug("Server status updated successfully after socket exception");
			serverStarted = false;
			logger.info("Server stopped");
		} catch (IOException e) {
			Initializer.serverStatusChanged = true;
			logger.debug("Server status updated successfully after io exception");
			serverStarted = false;
			logger.error(e.toString());
		}catch (Exception e) {
			Initializer.serverStatusChanged = true;
			logger.debug("Server status updated successfully after exception");
			JOptionPane.showMessageDialog(null, e.toString());
		}
	}
	
	
	/*
	 * -----------------------------------------------------------------------------
	 * This method instantiates the socket processor based on the FEP.
	 * -----------------------------------------------------------------------------
	 * 
	 */
	public void loadSocketDataProcessor() {
		if (Initializer.getFEPname().equalsIgnoreCase("HPS")) {
			socketDataProcessor = new HPSsocketDataProcessor();
		} else if (Initializer.getFEPname().equalsIgnoreCase("X9")) {
			socketDataProcessor = new X9socketDataProcessor();
		} else if(Initializer.getFEPname().equalsIgnoreCase("INCOMM")) {
			socketDataProcessor = new INCOMMsocketDataProcessor();
		} else if(Initializer.getFEPname().equalsIgnoreCase("FCB")) {
			socketDataProcessor = new FCBsocketDataProcessor();
		}
	}

}
