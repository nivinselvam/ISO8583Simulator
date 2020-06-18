package com;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.ButtonGroup;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JPanel;
import java.awt.Color;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JMenu;
import javax.swing.border.TitledBorder;
import javax.swing.text.BadLocationException;
import javax.swing.text.NumberFormatter;
import org.apache.log4j.Logger;
import javax.swing.JLabel;
import javax.swing.JComboBox;
import javax.swing.JTabbedPane;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.border.EtchedBorder;
import javax.swing.JRadioButton;
import javax.swing.JFormattedTextField;
import javax.swing.JCheckBox;
import java.awt.SystemColor;
import java.awt.event.ActionListener;
import java.io.PrintStream;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.text.NumberFormat;
import java.awt.event.ActionEvent;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

public class AppGUI {

	private JFrame frmISO8583Simulator;
	private JMenuBar menuBar;
	private JMenu mnFile;
	private JMenu mnHelp;
	private JMenuItem mntmExit;
	private JMenuItem mntmAbout;
	private JTabbedPane tbpnTABS;
	private JPanel pnMain;
	private JPanel pnFEP;
	private JPanel pnLogs;
	private JPanel pnServerConfiguration;
	private JPanel pnResponse;
	private JPanel pnConfiguration;
	private JLabel lblName;
	private JLabel lblIp;
	private JLabel lblResult;
	private JLabel lblResponseCode;
	private JLabel lblApprovalAmount;
	private JTextField txtIP;
	private JTextField txtPort;
	private JFormattedTextField txtResponseCode;
	private JFormattedTextField txtApprovalAmount;
	private JButton btnStartServer;
	private JButton btnStopServer;
	private JButton btnSaveConfiguration;
	private JButton btnSaveLogs;
	private JComboBox cbxFEP;
	private JComboBox cbxResult;
	private JTextArea txtareaLogs;
	private JCheckBox chckbxApproveForHalf;
	private JRadioButton rdbtnSendResponse;
	private JRadioButton rdbtnDontSendResponse;
	private NumberFormat format = NumberFormat.getInstance();
	private static Logger logger = Logger.getLogger(AppGUI.class);

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AppGUI window = new AppGUI();
					window.frmISO8583Simulator.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public AppGUI() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmISO8583Simulator = new JFrame();
		frmISO8583Simulator.setTitle("ISO8583 Host Simulator");
		frmISO8583Simulator.setBounds(100, 100, 540, 721);
		frmISO8583Simulator.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		tbpnTABS = new JTabbedPane(JTabbedPane.TOP);
		GroupLayout groupLayout = new GroupLayout(frmISO8583Simulator.getContentPane());
		groupLayout.setHorizontalGroup(groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup().addContainerGap().addComponent(tbpnTABS).addGap(2)));
		groupLayout.setVerticalGroup(groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup().addContainerGap()
						.addComponent(tbpnTABS, GroupLayout.DEFAULT_SIZE, 614, Short.MAX_VALUE).addContainerGap()));

		pnMain = new JPanel();
		tbpnTABS.addTab("Main", null, pnMain, null);

		pnFEP = new JPanel();
		pnFEP.setBorder(new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null), "FEP", TitledBorder.LEFT,
				TitledBorder.ABOVE_TOP, null, new Color(0, 0, 0)));

		lblName = new JLabel("Name : ");

		String[] fepNames = { "HPS", "Incomm", "FCB", "X9", "Payware", "Chase" };
		cbxFEP = new JComboBox(fepNames);
		GroupLayout gl_pnFEP = new GroupLayout(pnFEP);
		gl_pnFEP.setHorizontalGroup(gl_pnFEP.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_pnFEP.createSequentialGroup().addContainerGap().addComponent(lblName).addGap(18)
						.addComponent(cbxFEP, 0, 379, Short.MAX_VALUE).addContainerGap()));
		gl_pnFEP.setVerticalGroup(gl_pnFEP.createParallelGroup(Alignment.LEADING).addGroup(gl_pnFEP
				.createSequentialGroup().addContainerGap()
				.addGroup(gl_pnFEP.createParallelGroup(Alignment.BASELINE).addComponent(lblName).addComponent(cbxFEP,
						GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
				.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)));
		pnFEP.setLayout(gl_pnFEP);

		pnServerConfiguration = new JPanel();
		pnServerConfiguration.setBorder(new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null),
				"Connectivity", TitledBorder.LEFT, TitledBorder.ABOVE_TOP, null, new Color(0, 0, 0)));

		pnResponse = new JPanel();
		pnResponse.setBorder(new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null), "Response",
				TitledBorder.LEFT, TitledBorder.ABOVE_TOP, null, new Color(0, 0, 0)));

		pnConfiguration = new JPanel();
		pnConfiguration.setBorder(new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null),
				"Transaction Configuration", TitledBorder.LEFT, TitledBorder.ABOVE_TOP, null, new Color(0, 0, 0)));

		btnSaveConfiguration = new JButton("Save Configuration");
		btnSaveConfiguration.setBackground(SystemColor.controlHighlight);
		btnSaveConfiguration.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		GroupLayout gl_pnMain = new GroupLayout(pnMain);
		gl_pnMain.setHorizontalGroup(
			gl_pnMain.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_pnMain.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_pnMain.createParallelGroup(Alignment.LEADING, false)
						.addComponent(pnConfiguration, GroupLayout.PREFERRED_SIZE, 502, GroupLayout.PREFERRED_SIZE)
						.addComponent(pnResponse, GroupLayout.PREFERRED_SIZE, 502, GroupLayout.PREFERRED_SIZE)
						.addComponent(pnServerConfiguration, GroupLayout.PREFERRED_SIZE, 502, GroupLayout.PREFERRED_SIZE)
						.addComponent(pnFEP, GroupLayout.PREFERRED_SIZE, 502, GroupLayout.PREFERRED_SIZE)
						.addComponent(btnSaveConfiguration, GroupLayout.PREFERRED_SIZE, 502, GroupLayout.PREFERRED_SIZE))
					.addContainerGap())
		);
		gl_pnMain.setVerticalGroup(
			gl_pnMain.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_pnMain.createSequentialGroup()
					.addContainerGap()
					.addComponent(pnFEP, GroupLayout.PREFERRED_SIZE, 73, GroupLayout.PREFERRED_SIZE)
					.addGap(18)
					.addComponent(pnServerConfiguration, GroupLayout.PREFERRED_SIZE, 158, GroupLayout.PREFERRED_SIZE)
					.addGap(18)
					.addComponent(pnResponse, GroupLayout.PREFERRED_SIZE, 67, GroupLayout.PREFERRED_SIZE)
					.addGap(18)
					.addComponent(pnConfiguration, GroupLayout.PREFERRED_SIZE, 182, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addComponent(btnSaveConfiguration)
					.addContainerGap(29, Short.MAX_VALUE))
		);

		lblResult = new JLabel("Result  : ");
		String[] result = { "Approve", "Decline", "PartiallyApprove" };
		cbxResult = new JComboBox(result);

		lblResponseCode = new JLabel("Response Code : ");

		NumberFormatter responseFormatter = new NumberFormatter(format);
		responseFormatter.setValueClass(Integer.class);
		responseFormatter.setMinimum(0);
		responseFormatter.setMaximum(999);
		responseFormatter.setAllowsInvalid(false);
		// If you want the value to be committed on each keystroke instead of focus lost
		responseFormatter.setCommitsOnValidEdit(true);
		txtResponseCode = new JFormattedTextField(responseFormatter);
		txtResponseCode.setText("000");

		lblApprovalAmount = new JLabel("Approval Amount : ");

		txtApprovalAmount = new JFormattedTextField();
		txtApprovalAmount.setEnabled(false);

		chckbxApproveForHalf = new JCheckBox("Approve for half of transaction amount");

		chckbxApproveForHalf.setSelected(true);
		GroupLayout gl_pnConfiguration = new GroupLayout(pnConfiguration);
		gl_pnConfiguration.setHorizontalGroup(
			gl_pnConfiguration.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_pnConfiguration.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_pnConfiguration.createParallelGroup(Alignment.LEADING)
						.addComponent(lblApprovalAmount)
						.addComponent(lblResponseCode)
						.addComponent(lblResult, GroupLayout.PREFERRED_SIZE, 111, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_pnConfiguration.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_pnConfiguration.createParallelGroup(Alignment.LEADING)
							.addComponent(txtResponseCode, GroupLayout.DEFAULT_SIZE, 361, Short.MAX_VALUE)
							.addComponent(cbxResult, 0, 361, Short.MAX_VALUE))
						.addGroup(gl_pnConfiguration.createParallelGroup(Alignment.LEADING)
							.addComponent(chckbxApproveForHalf)
							.addComponent(txtApprovalAmount, GroupLayout.DEFAULT_SIZE, 371, Short.MAX_VALUE)))
					.addContainerGap())
		);
		gl_pnConfiguration.setVerticalGroup(
			gl_pnConfiguration.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_pnConfiguration.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_pnConfiguration.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblResult)
						.addComponent(cbxResult, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(18)
					.addGroup(gl_pnConfiguration.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblResponseCode)
						.addComponent(txtResponseCode, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(18)
					.addGroup(gl_pnConfiguration.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblApprovalAmount)
						.addComponent(txtApprovalAmount, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addComponent(chckbxApproveForHalf)
					.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
		);
		pnConfiguration.setLayout(gl_pnConfiguration);

		rdbtnSendResponse = new JRadioButton("Send Response");
		rdbtnSendResponse.setSelected(true);
		rdbtnDontSendResponse = new JRadioButton("Do not Send Response");

		ButtonGroup btngrpSendResponseOrNot = new ButtonGroup();
		btngrpSendResponseOrNot.add(rdbtnSendResponse);
		btngrpSendResponseOrNot.add(rdbtnDontSendResponse);
		GroupLayout gl_pnResponse = new GroupLayout(pnResponse);
		gl_pnResponse.setHorizontalGroup(
			gl_pnResponse.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_pnResponse.createSequentialGroup()
					.addContainerGap()
					.addComponent(rdbtnSendResponse)
					.addGap(147)
					.addComponent(rdbtnDontSendResponse)
					.addContainerGap(74, Short.MAX_VALUE))
		);
		gl_pnResponse.setVerticalGroup(
			gl_pnResponse.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_pnResponse.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_pnResponse.createParallelGroup(Alignment.BASELINE)
						.addComponent(rdbtnSendResponse)
						.addComponent(rdbtnDontSendResponse))
					.addContainerGap(17, Short.MAX_VALUE))
		);
		pnResponse.setLayout(gl_pnResponse);

		lblIp = new JLabel("IP : ");

		/*
		 * IP is taken from the system in which the simulator is running and is
		 * displayed in the txtip field
		 */
		InetAddress inet = null;
		try {
			inet = InetAddress.getLocalHost();
		} catch (UnknownHostException e1) {
			System.out.println("Unable to fetch the system details");
			logger.error("Unable to fetch the system details");
		}
		txtIP = new JTextField(inet.getHostAddress());
		txtIP.setEnabled(false);
		txtIP.setColumns(10);

		JLabel lblPort = new JLabel("Port : ");

		txtPort = new JTextField();
		txtPort.setText("9000");
		txtPort.setColumns(10);

		btnStartServer = new JButton("Start Server");

		btnStartServer.setBackground(SystemColor.controlHighlight);

		btnStopServer = new JButton("Stop Server");
		btnStopServer.setEnabled(false);

		btnStopServer.setBackground(SystemColor.controlHighlight);
		GroupLayout gl_pnServerConfiguration = new GroupLayout(pnServerConfiguration);
		gl_pnServerConfiguration.setHorizontalGroup(gl_pnServerConfiguration.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_pnServerConfiguration.createSequentialGroup().addContainerGap()
						.addGroup(gl_pnServerConfiguration
								.createParallelGroup(Alignment.LEADING).addComponent(lblIp).addComponent(lblPort))
						.addGap(29)
						.addGroup(gl_pnServerConfiguration.createParallelGroup(Alignment.TRAILING)
								.addGroup(gl_pnServerConfiguration.createSequentialGroup().addGap(0, 0, Short.MAX_VALUE)
										.addComponent(btnStartServer, GroupLayout.PREFERRED_SIZE, 169,
												GroupLayout.PREFERRED_SIZE)
										.addGap(35).addComponent(btnStopServer, GroupLayout.PREFERRED_SIZE, 176,
												GroupLayout.PREFERRED_SIZE))
								.addComponent(txtPort, GroupLayout.DEFAULT_SIZE, 380, Short.MAX_VALUE)
								.addComponent(txtIP, GroupLayout.DEFAULT_SIZE, 380, Short.MAX_VALUE))
						.addContainerGap()));
		gl_pnServerConfiguration
				.setVerticalGroup(
						gl_pnServerConfiguration.createParallelGroup(Alignment.LEADING)
								.addGroup(gl_pnServerConfiguration.createSequentialGroup().addContainerGap()
										.addGroup(gl_pnServerConfiguration.createParallelGroup(Alignment.BASELINE)
												.addComponent(lblIp).addComponent(txtIP, GroupLayout.PREFERRED_SIZE,
														GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
										.addGap(18)
										.addGroup(gl_pnServerConfiguration.createParallelGroup(Alignment.BASELINE)
												.addComponent(lblPort).addComponent(txtPort, GroupLayout.PREFERRED_SIZE,
														GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
										.addGap(18)
										.addGroup(gl_pnServerConfiguration.createParallelGroup(Alignment.BASELINE)
												.addComponent(btnStartServer).addComponent(btnStopServer))
										.addContainerGap(17, Short.MAX_VALUE)));
		pnServerConfiguration.setLayout(gl_pnServerConfiguration);
		pnMain.setLayout(gl_pnMain);

		pnLogs = new JPanel();
		tbpnTABS.addTab("Logs", null, pnLogs, null);

		JLabel lblRuntimeLogs = new JLabel("Runtime Logs : ");

		JScrollPane scrlpnLogs = new JScrollPane();

		JButton btnClearLogs = new JButton("Clear Logs");

		btnSaveLogs = new JButton("Save Logs");

		GroupLayout gl_pnLogs = new GroupLayout(pnLogs);
		gl_pnLogs.setHorizontalGroup(
			gl_pnLogs.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_pnLogs.createSequentialGroup()
					.addGap(12)
					.addComponent(lblRuntimeLogs)
					.addPreferredGap(ComponentPlacement.RELATED, 200, Short.MAX_VALUE)
					.addComponent(btnSaveLogs)
					.addGap(18)
					.addComponent(btnClearLogs)
					.addGap(22))
				.addGroup(gl_pnLogs.createSequentialGroup()
					.addGap(14)
					.addComponent(scrlpnLogs, GroupLayout.DEFAULT_SIZE, 477, Short.MAX_VALUE)
					.addGap(23))
		);
		gl_pnLogs.setVerticalGroup(
			gl_pnLogs.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_pnLogs.createSequentialGroup()
					.addGap(13)
					.addGroup(gl_pnLogs.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblRuntimeLogs)
						.addComponent(btnClearLogs)
						.addComponent(btnSaveLogs))
					.addGap(13)
					.addComponent(scrlpnLogs, GroupLayout.DEFAULT_SIZE, 543, Short.MAX_VALUE)
					.addGap(12))
		);

		txtareaLogs = new JTextArea();
		txtareaLogs.setEditable(false);
		scrlpnLogs.setViewportView(txtareaLogs);
		pnLogs.setLayout(gl_pnLogs);
		frmISO8583Simulator.getContentPane().setLayout(groupLayout);
		PrintStream printStream = new PrintStream(new CustomOutputStream(txtareaLogs));
		System.setOut(printStream);
		System.setErr(printStream);

		menuBar = new JMenuBar();
		frmISO8583Simulator.setJMenuBar(menuBar);

		mnFile = new JMenu("File");
		menuBar.add(mnFile);

		mntmExit = new JMenuItem("Exit");
		mnFile.add(mntmExit);

		mnHelp = new JMenu("Help");
		menuBar.add(mnHelp);

		mntmAbout = new JMenuItem("About");
		mnHelp.add(mntmAbout);

		// -----------------------------------------------------------------------------------------------------------------------------
		/*
		 * When the start server button is clicked, i) Server is started ii) if the
		 * server is started successfully, Start server button is disabled & Stop server
		 * button is enabled
		 */
		// -----------------------------------------------------------------------------------------------------------------------------
		btnStartServer.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				btnStopServer.setEnabled(true);
				btnStartServer.setEnabled(false);
			}
		});

		// -----------------------------------------------------------------------------------------------------------------------------
		/*
		 * When the stop server button is clicked, i) Server is stopped ii) If the
		 * server is stopped successfully, Start server button is disabled & Stop server
		 * button is enabled
		 */
		// -----------------------------------------------------------------------------------------------------------------------------
		btnStopServer.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				btnStartServer.setEnabled(true);
				btnStopServer.setEnabled(false);
			}
		});
		// -----------------------------------------------------------------------------------------------------------------------------
		/*
		 * When the Approve for half of transaction amount checkbox is checked, approval
		 * amount text field will be enabled and simulator will start approving for the
		 * specified amount
		 */
		// -----------------------------------------------------------------------------------------------------------------------------
		chckbxApproveForHalf.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (chckbxApproveForHalf.isSelected()) {
					txtApprovalAmount.setEnabled(false);
				} else {
					txtApprovalAmount.setEnabled(true);
				}
			}
		});
		// -----------------------------------------------------------------------------------------------------------------------------
		/*
		 * When the save logs button is clicked, user gets the explorer window to save the runtime logs
		 */
		// -----------------------------------------------------------------------------------------------------------------------------
		btnSaveLogs.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

			}
		});
		// -----------------------------------------------------------------------------------------------------------------------------
		/*
		 * When the clear logs button is clicked, the runtime logs text area is cleared
		 */
		// -----------------------------------------------------------------------------------------------------------------------------
		btnClearLogs.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					txtareaLogs.getDocument().remove(0, txtareaLogs.getDocument().getLength());
				} catch (BadLocationException e1) {
					System.out.println("Unable to clear the logs");
					logger.error("Unable to clear the logs");
				}
			}
		});
	}
}
