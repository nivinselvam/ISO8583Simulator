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
import javax.swing.JOptionPane;
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
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.text.NumberFormat;
import java.util.Properties;
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
	private JLabel lblDeclineCode;
	private JLabel lblApprovalAmount;
	private JTextField txtIP;
	private JTextField txtPort;
	private JFormattedTextField txtResponseCode;
	private JFormattedTextField txtApprovalAmount;
	private JButton btnStartServer;
	private JButton btnStopServer;
	private JButton btnSaveTransactionConfiguration;
	private JButton btnSaveLogs;
	private JComboBox cbxFEP;
	private JTextArea txtareaLogs;
	private JCheckBox chckbxApproveForHalf;
	private JRadioButton rdbtnSendResponse;
	private JRadioButton rdbtnDontSendResponse;
	private NumberFormat format = NumberFormat.getInstance();
	private static Logger logger = Logger.getLogger(AppGUI.class);
	private JPanel pnAuthorization;
	private JPanel pnFinancialSales;
	private JRadioButton rdbtnFinancialSalesApprove;
	private JRadioButton rdbtnFinancialSalesDecline;
	private JRadioButton rdbtnFinancialSalesPartiallyapprove;
	private JPanel pnFinancialForceDraft;
	private JRadioButton rdbtnFinancialForceDraftApprove;
	private JRadioButton rdbtnFinancialForceDraftDecline;
	private JPanel pnReversal;
	private JRadioButton rdbtnReversalApprove;
	private JRadioButton rdbtnReversalDecline;
	private JPanel pnReconciliation;
	private JRadioButton rdbtnReconciliationApprove;
	private JRadioButton rdbtnReconciliationDecline;
	private JLabel lblOffline;
	private JLabel lblStatus;
	private JButton btnChangeServerConfiguration;
	private Properties property = new Properties();

	public JFrame getFrmISO8583Simulator() {
		return frmISO8583Simulator;
	}

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
		frmISO8583Simulator.setBounds(100, 100, 558, 803);
		frmISO8583Simulator.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		tbpnTABS = new JTabbedPane(JTabbedPane.TOP);
		GroupLayout groupLayout = new GroupLayout(frmISO8583Simulator.getContentPane());
		groupLayout.setHorizontalGroup(groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup().addContainerGap()
						.addComponent(tbpnTABS, GroupLayout.DEFAULT_SIZE, 514, Short.MAX_VALUE).addGap(20)));
		groupLayout.setVerticalGroup(groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup().addContainerGap()
						.addComponent(tbpnTABS, GroupLayout.PREFERRED_SIZE, 721, Short.MAX_VALUE).addContainerGap()));

		String[] fepNames = { "HPS", "Incomm", "FCB", "X9", "Payware", "Chase" };
		String[] result = { "Approve", "Decline", "PartiallyApprove" };

		NumberFormatter responseFormatter = new NumberFormatter(format);
		responseFormatter.setValueClass(Integer.class);
		responseFormatter.setMinimum(0);
		responseFormatter.setMaximum(999);
		responseFormatter.setAllowsInvalid(false);
		// If you want the value to be committed on each keystroke instead of focus lost
		responseFormatter.setCommitsOnValidEdit(true);

		ButtonGroup btngrpSendResponseOrNot = new ButtonGroup();

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
		ButtonGroup btngrpauthorizationResult = new ButtonGroup();

		pnMain = new JPanel();
		tbpnTABS.addTab("Server Configuration", null, pnMain, null);

		pnFEP = new JPanel();
		pnFEP.setBorder(new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null), "FEP", TitledBorder.LEFT,
				TitledBorder.ABOVE_TOP, null, new Color(0, 0, 0)));

		lblName = new JLabel("Name : ");
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

		lblIp = new JLabel("IP : ");
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
		gl_pnServerConfiguration
				.setHorizontalGroup(gl_pnServerConfiguration.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_pnServerConfiguration.createSequentialGroup().addContainerGap()
								.addGroup(gl_pnServerConfiguration.createParallelGroup(Alignment.LEADING)
										.addComponent(lblIp).addComponent(lblPort))
								.addGap(29)
								.addGroup(gl_pnServerConfiguration.createParallelGroup(Alignment.TRAILING)
										.addGroup(Alignment.LEADING, gl_pnServerConfiguration.createSequentialGroup()
												.addComponent(btnStartServer, GroupLayout.PREFERRED_SIZE, 190,
														GroupLayout.PREFERRED_SIZE)
												.addPreferredGap(ComponentPlacement.RELATED, 44, Short.MAX_VALUE)
												.addComponent(btnStopServer, GroupLayout.PREFERRED_SIZE, 184,
														GroupLayout.PREFERRED_SIZE))
										.addComponent(txtPort, GroupLayout.DEFAULT_SIZE, 418, Short.MAX_VALUE)
										.addComponent(txtIP, GroupLayout.DEFAULT_SIZE, 418, Short.MAX_VALUE))
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
												.addComponent(btnStopServer).addComponent(btnStartServer))
										.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)));
		pnServerConfiguration.setLayout(gl_pnServerConfiguration);

		lblOffline = new JLabel("Offline");

		lblStatus = new JLabel("Status : ");

		btnChangeServerConfiguration = new JButton("Save Server Configuration");
		btnChangeServerConfiguration.setBackground(SystemColor.controlHighlight);
		GroupLayout gl_pnMain = new GroupLayout(pnMain);
		gl_pnMain.setHorizontalGroup(gl_pnMain.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_pnMain.createSequentialGroup()
						.addGroup(gl_pnMain.createParallelGroup(Alignment.LEADING, false)
								.addGroup(gl_pnMain.createSequentialGroup().addGap(412).addComponent(lblStatus)
										.addPreferredGap(ComponentPlacement.RELATED).addComponent(lblOffline))
								.addGroup(gl_pnMain.createSequentialGroup().addContainerGap().addComponent(pnFEP,
										GroupLayout.DEFAULT_SIZE, 491, Short.MAX_VALUE)))
						.addContainerGap(17, Short.MAX_VALUE))
				.addGroup(gl_pnMain.createSequentialGroup().addContainerGap()
						.addComponent(pnServerConfiguration, GroupLayout.PREFERRED_SIZE, 491,
								GroupLayout.PREFERRED_SIZE)
						.addGap(17))
				.addGroup(gl_pnMain.createSequentialGroup().addContainerGap()
						.addComponent(btnChangeServerConfiguration, GroupLayout.DEFAULT_SIZE, 491, Short.MAX_VALUE)
						.addGap(17)));
		gl_pnMain.setVerticalGroup(gl_pnMain.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_pnMain.createSequentialGroup().addGap(7)
						.addGroup(gl_pnMain.createParallelGroup(Alignment.BASELINE).addComponent(lblOffline)
								.addComponent(lblStatus))
						.addPreferredGap(ComponentPlacement.RELATED)
						.addComponent(pnFEP, GroupLayout.PREFERRED_SIZE, 73, GroupLayout.PREFERRED_SIZE).addGap(32)
						.addComponent(pnServerConfiguration, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
								GroupLayout.PREFERRED_SIZE)
						.addGap(39).addComponent(btnChangeServerConfiguration).addGap(332)));
		pnMain.setLayout(gl_pnMain);

		JPanel pnTransactionConfiguration = new JPanel();
		tbpnTABS.addTab("Transaction Configuration", null, pnTransactionConfiguration, null);

		pnAuthorization = new JPanel();
		pnAuthorization.setBorder(new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null),
				"Authorisation(x100)", TitledBorder.LEADING, TitledBorder.ABOVE_TOP, null, null));

		pnFinancialSales = new JPanel();
		pnFinancialSales.setBorder(new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null),
				"Financial Sales(x200)", TitledBorder.LEADING, TitledBorder.ABOVE_TOP, null, null));

		pnFinancialForceDraft = new JPanel();
		pnFinancialForceDraft.setBorder(new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null),
				"Financial Force Draft(x220)", TitledBorder.LEADING, TitledBorder.ABOVE_TOP, null, null));

		pnReversal = new JPanel();
		pnReversal.setBorder(new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null), "Reversal(x420)",
				TitledBorder.LEADING, TitledBorder.ABOVE_TOP, null, null));

		pnReconciliation = new JPanel();
		pnReconciliation.setBorder(new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null),
				"Reconciliation(x520)", TitledBorder.LEADING, TitledBorder.ABOVE_TOP, null, null));

		pnResponse = new JPanel();
		pnResponse.setBorder(new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null), "Response",
				TitledBorder.LEFT, TitledBorder.ABOVE_TOP, null, new Color(0, 0, 0)));

		rdbtnSendResponse = new JRadioButton("Send Response");
		rdbtnSendResponse.setSelected(true);
		rdbtnDontSendResponse = new JRadioButton("Do not Send Response");
		btngrpSendResponseOrNot.add(rdbtnSendResponse);
		btngrpSendResponseOrNot.add(rdbtnDontSendResponse);
		GroupLayout gl_pnResponse = new GroupLayout(pnResponse);
		gl_pnResponse.setHorizontalGroup(gl_pnResponse.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_pnResponse.createSequentialGroup().addContainerGap().addComponent(rdbtnSendResponse)
						.addGap(58).addComponent(rdbtnDontSendResponse).addContainerGap(184, Short.MAX_VALUE)));
		gl_pnResponse
				.setVerticalGroup(gl_pnResponse.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_pnResponse.createSequentialGroup().addContainerGap()
								.addGroup(gl_pnResponse.createParallelGroup(Alignment.BASELINE)
										.addComponent(rdbtnSendResponse).addComponent(rdbtnDontSendResponse))
								.addContainerGap(17, Short.MAX_VALUE)));
		pnResponse.setLayout(gl_pnResponse);

		pnConfiguration = new JPanel();
		pnConfiguration.setBorder(new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null),
				"Response Code & Amount", TitledBorder.LEFT, TitledBorder.ABOVE_TOP, null, new Color(0, 0, 0)));

		lblDeclineCode = new JLabel("Decline Code : ");
		txtResponseCode = new JFormattedTextField(responseFormatter);
		txtResponseCode.setText("000");

		lblApprovalAmount = new JLabel("Approval Amount : ");

		txtApprovalAmount = new JFormattedTextField();
		txtApprovalAmount.setEnabled(false);

		chckbxApproveForHalf = new JCheckBox("Approve for half of transaction amount");

		chckbxApproveForHalf.setSelected(true);
		GroupLayout gl_pnConfiguration = new GroupLayout(pnConfiguration);
		gl_pnConfiguration.setHorizontalGroup(gl_pnConfiguration.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_pnConfiguration.createSequentialGroup().addContainerGap()
						.addGroup(gl_pnConfiguration.createParallelGroup(Alignment.LEADING)
								.addComponent(lblApprovalAmount).addComponent(lblDeclineCode))
						.addGap(16)
						.addGroup(gl_pnConfiguration.createParallelGroup(Alignment.LEADING)
								.addComponent(txtResponseCode, GroupLayout.DEFAULT_SIZE, 361, Short.MAX_VALUE)
								.addComponent(chckbxApproveForHalf)
								.addComponent(txtApprovalAmount, GroupLayout.DEFAULT_SIZE, 361, Short.MAX_VALUE))
						.addContainerGap()));
		gl_pnConfiguration.setVerticalGroup(gl_pnConfiguration.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_pnConfiguration.createSequentialGroup().addContainerGap()
						.addGroup(gl_pnConfiguration.createParallelGroup(Alignment.BASELINE)
								.addComponent(lblDeclineCode).addComponent(txtResponseCode, GroupLayout.PREFERRED_SIZE,
										GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
						.addGap(18)
						.addGroup(gl_pnConfiguration.createParallelGroup(Alignment.BASELINE)
								.addComponent(lblApprovalAmount).addComponent(txtApprovalAmount,
										GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
										GroupLayout.PREFERRED_SIZE))
						.addPreferredGap(ComponentPlacement.UNRELATED).addComponent(chckbxApproveForHalf)
						.addContainerGap(46, Short.MAX_VALUE)));
		pnConfiguration.setLayout(gl_pnConfiguration);

		btnSaveTransactionConfiguration = new JButton("Save Transaction Configuration");
		btnSaveTransactionConfiguration.setBackground(SystemColor.controlHighlight);

		GroupLayout gl_pnTransactionConfiguration = new GroupLayout(pnTransactionConfiguration);
		gl_pnTransactionConfiguration.setHorizontalGroup(gl_pnTransactionConfiguration
				.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_pnTransactionConfiguration.createSequentialGroup().addContainerGap()
						.addGroup(gl_pnTransactionConfiguration.createParallelGroup(Alignment.LEADING, false)
								.addComponent(pnConfiguration, 0, 0, Short.MAX_VALUE)
								.addComponent(pnReconciliation, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE,
										Short.MAX_VALUE)
								.addComponent(pnReversal, 0, 0, Short.MAX_VALUE)
								.addComponent(pnFinancialForceDraft, 0, 0, Short.MAX_VALUE)
								.addComponent(pnFinancialSales, 0, 0, Short.MAX_VALUE)
								.addComponent(pnAuthorization, 0, 0, Short.MAX_VALUE)
								.addComponent(pnResponse, GroupLayout.DEFAULT_SIZE, 496, Short.MAX_VALUE)
								.addComponent(btnSaveTransactionConfiguration, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE,
										Short.MAX_VALUE))
						.addContainerGap(12, Short.MAX_VALUE)));
		gl_pnTransactionConfiguration.setVerticalGroup(gl_pnTransactionConfiguration
				.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_pnTransactionConfiguration.createSequentialGroup().addContainerGap()
						.addComponent(pnResponse, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
								GroupLayout.PREFERRED_SIZE)
						.addPreferredGap(ComponentPlacement.RELATED)
						.addComponent(pnAuthorization, GroupLayout.PREFERRED_SIZE, 74, GroupLayout.PREFERRED_SIZE)
						.addPreferredGap(ComponentPlacement.RELATED)
						.addComponent(pnFinancialSales, GroupLayout.PREFERRED_SIZE, 70, GroupLayout.PREFERRED_SIZE)
						.addPreferredGap(ComponentPlacement.RELATED)
						.addComponent(pnFinancialForceDraft, GroupLayout.PREFERRED_SIZE, 74, GroupLayout.PREFERRED_SIZE)
						.addPreferredGap(ComponentPlacement.RELATED)
						.addComponent(pnReversal, GroupLayout.PREFERRED_SIZE, 75, GroupLayout.PREFERRED_SIZE)
						.addPreferredGap(ComponentPlacement.RELATED)
						.addComponent(pnReconciliation, GroupLayout.PREFERRED_SIZE, 78, GroupLayout.PREFERRED_SIZE)
						.addPreferredGap(ComponentPlacement.RELATED)
						.addComponent(pnConfiguration, GroupLayout.PREFERRED_SIZE, 141, GroupLayout.PREFERRED_SIZE)
						.addPreferredGap(ComponentPlacement.UNRELATED).addComponent(btnSaveTransactionConfiguration)
						.addContainerGap(30, Short.MAX_VALUE)));

		rdbtnReconciliationApprove = new JRadioButton("Approve");
		rdbtnReconciliationApprove.setSelected(true);
		rdbtnReconciliationDecline = new JRadioButton("Decline");
		ButtonGroup btngrpReconciliationResult = new ButtonGroup();
		btngrpReconciliationResult.add(rdbtnReconciliationApprove);
		btngrpReconciliationResult.add(rdbtnReconciliationDecline);
		GroupLayout gl_pnReconciliation = new GroupLayout(pnReconciliation);
		gl_pnReconciliation.setHorizontalGroup(gl_pnReconciliation.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_pnReconciliation.createSequentialGroup().addContainerGap()
						.addComponent(rdbtnReconciliationApprove).addGap(103).addComponent(rdbtnReconciliationDecline)
						.addContainerGap(221, Short.MAX_VALUE)));
		gl_pnReconciliation.setVerticalGroup(gl_pnReconciliation.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_pnReconciliation.createSequentialGroup().addGap(15)
						.addGroup(gl_pnReconciliation.createParallelGroup(Alignment.BASELINE)
								.addComponent(rdbtnReconciliationApprove).addComponent(rdbtnReconciliationDecline))
						.addContainerGap(19, Short.MAX_VALUE)));
		pnReconciliation.setLayout(gl_pnReconciliation);

		rdbtnReversalApprove = new JRadioButton("Approve");
		rdbtnReversalApprove.setSelected(true);
		rdbtnReversalDecline = new JRadioButton("Decline");
		ButtonGroup btngrpReversalResult = new ButtonGroup();
		btngrpReversalResult.add(rdbtnReversalApprove);
		btngrpReversalResult.add(rdbtnReversalDecline);
		GroupLayout gl_pnReversal = new GroupLayout(pnReversal);
		gl_pnReversal.setHorizontalGroup(gl_pnReversal.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_pnReversal.createSequentialGroup().addContainerGap().addComponent(rdbtnReversalApprove)
						.addGap(103).addComponent(rdbtnReversalDecline).addContainerGap(272, Short.MAX_VALUE)));
		gl_pnReversal.setVerticalGroup(gl_pnReversal.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_pnReversal.createSequentialGroup().addGap(14)
						.addGroup(gl_pnReversal.createParallelGroup(Alignment.BASELINE)
								.addComponent(rdbtnReversalApprove).addComponent(rdbtnReversalDecline))
						.addContainerGap(17, Short.MAX_VALUE)));
		pnReversal.setLayout(gl_pnReversal);

		rdbtnFinancialForceDraftApprove = new JRadioButton("Approve");
		rdbtnFinancialForceDraftApprove.setSelected(true);
		rdbtnFinancialForceDraftDecline = new JRadioButton("Decline");
		ButtonGroup btngrpFinancialForceDraftResult = new ButtonGroup();
		btngrpFinancialForceDraftResult.add(rdbtnFinancialForceDraftApprove);
		btngrpFinancialForceDraftResult.add(rdbtnFinancialForceDraftDecline);
		GroupLayout gl_pnFinancialForceDraft = new GroupLayout(pnFinancialForceDraft);
		gl_pnFinancialForceDraft.setHorizontalGroup(gl_pnFinancialForceDraft.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_pnFinancialForceDraft.createSequentialGroup().addContainerGap()
						.addComponent(rdbtnFinancialForceDraftApprove).addGap(103)
						.addComponent(rdbtnFinancialForceDraftDecline).addContainerGap(272, Short.MAX_VALUE)));
		gl_pnFinancialForceDraft.setVerticalGroup(gl_pnFinancialForceDraft.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_pnFinancialForceDraft.createSequentialGroup().addGap(18)
						.addGroup(gl_pnFinancialForceDraft.createParallelGroup(Alignment.BASELINE)
								.addComponent(rdbtnFinancialForceDraftApprove)
								.addComponent(rdbtnFinancialForceDraftDecline))
						.addContainerGap(12, Short.MAX_VALUE)));
		pnFinancialForceDraft.setLayout(gl_pnFinancialForceDraft);

		rdbtnFinancialSalesApprove = new JRadioButton("Approve");
		rdbtnFinancialSalesApprove.setSelected(true);
		rdbtnFinancialSalesDecline = new JRadioButton("Decline");
		rdbtnFinancialSalesPartiallyapprove = new JRadioButton("PartiallyApprove");
		ButtonGroup btngrpFinancialSalesResult = new ButtonGroup();
		btngrpFinancialSalesResult.add(rdbtnFinancialSalesApprove);
		btngrpFinancialSalesResult.add(rdbtnFinancialSalesApprove);
		btngrpFinancialSalesResult.add(rdbtnFinancialSalesDecline);
		GroupLayout gl_pnFinancialSales = new GroupLayout(pnFinancialSales);
		gl_pnFinancialSales.setHorizontalGroup(gl_pnFinancialSales.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_pnFinancialSales.createSequentialGroup().addContainerGap()
						.addComponent(rdbtnFinancialSalesApprove).addGap(102).addComponent(rdbtnFinancialSalesDecline)
						.addPreferredGap(ComponentPlacement.RELATED, 117, Short.MAX_VALUE)
						.addComponent(rdbtnFinancialSalesPartiallyapprove).addGap(41)));
		gl_pnFinancialSales.setVerticalGroup(gl_pnFinancialSales.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_pnFinancialSales.createSequentialGroup().addGap(18)
						.addGroup(gl_pnFinancialSales.createParallelGroup(Alignment.BASELINE)
								.addComponent(rdbtnFinancialSalesApprove).addComponent(rdbtnFinancialSalesDecline)
								.addComponent(rdbtnFinancialSalesPartiallyapprove))
						.addContainerGap(22, Short.MAX_VALUE)));
		pnFinancialSales.setLayout(gl_pnFinancialSales);

		JRadioButton rdbtnAuthorizationApprove = new JRadioButton("Approve");
		rdbtnAuthorizationApprove.setSelected(true);
		JRadioButton rdbtnAuthorizationDecline = new JRadioButton("Decline");
		JRadioButton rdbtnAuthorizationPartiallyapprove = new JRadioButton("PartiallyApprove");
		btngrpauthorizationResult.add(rdbtnAuthorizationApprove);
		btngrpauthorizationResult.add(rdbtnAuthorizationDecline);
		btngrpauthorizationResult.add(rdbtnAuthorizationPartiallyapprove);

		GroupLayout gl_pnAuthorization = new GroupLayout(pnAuthorization);
		gl_pnAuthorization.setHorizontalGroup(gl_pnAuthorization.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_pnAuthorization.createSequentialGroup().addContainerGap()
						.addComponent(rdbtnAuthorizationApprove).addGap(101).addComponent(rdbtnAuthorizationDecline)
						.addPreferredGap(ComponentPlacement.RELATED, 118, Short.MAX_VALUE)
						.addComponent(rdbtnAuthorizationPartiallyapprove).addGap(47)));
		gl_pnAuthorization.setVerticalGroup(gl_pnAuthorization.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_pnAuthorization.createSequentialGroup().addContainerGap(16, Short.MAX_VALUE)
						.addGroup(gl_pnAuthorization.createParallelGroup(Alignment.BASELINE)
								.addComponent(rdbtnAuthorizationApprove).addComponent(rdbtnAuthorizationDecline)
								.addComponent(rdbtnAuthorizationPartiallyapprove))
						.addGap(14)));
		pnAuthorization.setLayout(gl_pnAuthorization);
		pnTransactionConfiguration.setLayout(gl_pnTransactionConfiguration);

		pnLogs = new JPanel();
		tbpnTABS.addTab("Runtime Logs", null, pnLogs, null);

		JLabel lblRuntimeLogs = new JLabel("Logs : ");

		JScrollPane scrlpnLogs = new JScrollPane();

		JButton btnClearLogs = new JButton("Clear Logs");

		btnSaveLogs = new JButton("Save Logs");

		GroupLayout gl_pnLogs = new GroupLayout(pnLogs);
		gl_pnLogs.setHorizontalGroup(gl_pnLogs.createParallelGroup(Alignment.LEADING).addGroup(gl_pnLogs
				.createSequentialGroup().addGap(14)
				.addGroup(gl_pnLogs.createParallelGroup(Alignment.LEADING)
						.addComponent(scrlpnLogs, Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, 494, Short.MAX_VALUE)
						.addGroup(gl_pnLogs.createSequentialGroup().addComponent(lblRuntimeLogs)
								.addPreferredGap(ComponentPlacement.RELATED, 264, Short.MAX_VALUE)
								.addComponent(btnSaveLogs).addGap(18).addComponent(btnClearLogs)))
				.addContainerGap()));
		gl_pnLogs.setVerticalGroup(gl_pnLogs.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_pnLogs.createSequentialGroup().addGap(13)
						.addGroup(gl_pnLogs.createParallelGroup(Alignment.BASELINE).addComponent(btnClearLogs)
								.addComponent(btnSaveLogs).addComponent(lblRuntimeLogs))
						.addPreferredGap(ComponentPlacement.RELATED)
						.addComponent(scrlpnLogs, GroupLayout.DEFAULT_SIZE, 632, Short.MAX_VALUE).addGap(12)));

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
				Initializer.setServer(new ServerInitializer());
				Initializer.getServer().start();
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
				try {
					Initializer.getServer().getServerSocket().close();
					logger.info("Server stopped");
					System.out.println("Server stopped");
				} catch (IOException e1) {
					logger.error("Unable to stop the server");
					System.out.println("Unable to stop the server");
					JOptionPane.showMessageDialog(null, "Unable to stop the server");
				}
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
		 * When the save logs button is clicked, user gets the explorer window to save
		 * the runtime logs
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
		// -----------------------------------------------------------------------------------------------------------------------------
		/*
		 * When the change port number button is clicked, i) Validation if the set port
		 * number is correct is performed. ii) Error message is displayed if the entered
		 * port number is invalid iii) If the entered port number is valid, server is
		 * stopped, port number is changed and server is restarted.
		 */
		// -----------------------------------------------------------------------------------------------------------------------------
		btnChangeServerConfiguration.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					if (Integer.parseInt(txtPort.getText()) < 1026 || Integer.parseInt(txtPort.getText()) > 65535) {
						System.out.println(
								"Entered port number is invalid. Valid port number range is between 1026 and 65535");
						logger.info(
								"Entered port number is invalid. Valid port number range is between 1026 and 65535");
						JOptionPane.showMessageDialog(null,
								"Entered port number is invalid. Valid port number range is between 1026 and 65535");
					} else {
						property.load(new FileInputStream(new File(Initializer.getBaseConstants().appFolder) + "\\"
								+ "CommonVariables.properties"));
						if (!Initializer.getFEPname().equals(property.getProperty("fepName"))) {
							property.setProperty("fepName", cbxFEP.getSelectedItem().toString());
						}
						if (!String.valueOf(Initializer.getPortNumber()).equals(property.getProperty("portNumber"))) {
							property.setProperty("portNumber", txtPort.getText());
						}
					}

				} catch (Exception e1) {
					System.out.println(
							"Entered port number is invalid. Valid port number range is between 1026 and 65535");
					logger.info("Entered port number is invalid. Valid port number range is between 1026 and 65535");
					JOptionPane.showMessageDialog(null,
							"Entered port number is invalid. Valid port number range is between 1026 and 65535");
				}
			}
		});
	}
}
