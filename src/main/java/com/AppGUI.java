package com;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.AbstractButton;
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
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.text.NumberFormat;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;
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
	private JPanel pnSendResponse;
	private JPanel pnConfiguration;
	private JPanel pnAuthorization;
	private JPanel pnFinancialSales;
	private JPanel pnFinancialForceDraft;
	private JPanel pnReversal;
	private JPanel pnReconciliation;
	private JLabel lblName;
	private JLabel lblIp;
	private JLabel lblDeclineCode;
	private JLabel lblApprovalAmount;
	private JLabel lblStatusValue;
	private JLabel lblStatus;
	private JTextField txtIP;
	private JTextField txtPort;
	private JTextArea txtareaLogs;
	private JFormattedTextField txtDeclineCode;
	private JFormattedTextField txtApprovalAmount;
	private JButton btnStartServer;
	private JButton btnStopServer;
	private JButton btnSaveTransactionConfiguration;
	private JButton btnSaveLogs;
	private JButton btnSaveServerConfiguration;
	private JComboBox<String> cbxFEP;
	private JCheckBox chckbxApproveForHalf;
	private JRadioButton rdbtnSendResponse;
	private JRadioButton rdbtnDontSendResponse;
	private JRadioButton rdbtnFinancialSalesApprove;
	private JRadioButton rdbtnFinancialSalesDecline;
	private JRadioButton rdbtnFinancialSalesPartiallyapprove;
	private JRadioButton rdbtnFinancialForceDraftApprove;
	private JRadioButton rdbtnFinancialForceDraftDecline;
	private JRadioButton rdbtnReversalApprove;
	private JRadioButton rdbtnReversalDecline;
	private JRadioButton rdbtnReconciliationApprove;
	private JRadioButton rdbtnReconciliationDecline;
	private ButtonGroup btngrpSendResponseOrNot;
	private ButtonGroup btngrpauthorizationResult;
	private ButtonGroup btngrpFinancialSalesResult;
	private ButtonGroup btngrpFinancialForceDraftResult;
	private ButtonGroup btngrpReversalResult;
	private ButtonGroup btngrpReconciliationResult;
	private NumberFormat format = NumberFormat.getInstance();
	private static Logger logger = Logger.getLogger(AppGUI.class);
	private Map<String, String> transactionConfigurationMap = new HashMap<String, String>();
	private Properties property = new Properties();

	public JFrame getFrmISO8583Simulator() {
		return frmISO8583Simulator;
	}

	public JTextArea getTxtareaLogs() {
		return txtareaLogs;
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

		String[] fepNames = { "HPS", "X9"};

		NumberFormatter responseFormatter = new NumberFormatter(format);
		responseFormatter.setValueClass(Integer.class);
		responseFormatter.setMinimum(0);
		responseFormatter.setMaximum(999);
		responseFormatter.setAllowsInvalid(false);
		// If you want the value to be committed on each keystroke instead of focus lost
		responseFormatter.setCommitsOnValidEdit(true);

		btngrpSendResponseOrNot = new ButtonGroup();

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
		btngrpauthorizationResult = new ButtonGroup();

		pnMain = new JPanel();
		tbpnTABS.addTab("Server Configuration", null, pnMain, null);

		pnFEP = new JPanel();
		pnFEP.setBorder(new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null), "FEP", TitledBorder.LEFT,
				TitledBorder.ABOVE_TOP, null, new Color(0, 0, 0)));

		lblName = new JLabel("Name : ");
		cbxFEP = new JComboBox<String>(fepNames);
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

		lblStatusValue = new JLabel("Offline");

		lblStatus = new JLabel("Status : ");

		btnSaveServerConfiguration = new JButton("Save Server Configuration");
		btnSaveServerConfiguration.setBackground(SystemColor.controlHighlight);
		GroupLayout gl_pnMain = new GroupLayout(pnMain);
		gl_pnMain.setHorizontalGroup(
			gl_pnMain.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_pnMain.createSequentialGroup()
					.addGroup(gl_pnMain.createParallelGroup(Alignment.LEADING, false)
						.addGroup(gl_pnMain.createSequentialGroup()
							.addGap(412)
							.addComponent(lblStatus)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(lblStatusValue))
						.addGroup(gl_pnMain.createSequentialGroup()
							.addContainerGap()
							.addComponent(pnFEP, GroupLayout.DEFAULT_SIZE, 491, Short.MAX_VALUE)))
					.addContainerGap(17, Short.MAX_VALUE))
				.addGroup(gl_pnMain.createSequentialGroup()
					.addContainerGap()
					.addComponent(pnServerConfiguration, GroupLayout.PREFERRED_SIZE, 491, GroupLayout.PREFERRED_SIZE)
					.addGap(17))
				.addGroup(gl_pnMain.createSequentialGroup()
					.addContainerGap()
					.addComponent(btnSaveServerConfiguration, GroupLayout.PREFERRED_SIZE, 491, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(17, Short.MAX_VALUE))
		);
		gl_pnMain.setVerticalGroup(
			gl_pnMain.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_pnMain.createSequentialGroup()
					.addGap(7)
					.addGroup(gl_pnMain.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblStatusValue)
						.addComponent(lblStatus))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(pnFEP, GroupLayout.PREFERRED_SIZE, 73, GroupLayout.PREFERRED_SIZE)
					.addGap(32)
					.addComponent(pnServerConfiguration, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addGap(39)
					.addComponent(btnSaveServerConfiguration)
					.addGap(332))
		);
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

		pnSendResponse = new JPanel();
		pnSendResponse.setBorder(new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null), "Send Response",
				TitledBorder.LEFT, TitledBorder.ABOVE_TOP, null, new Color(0, 0, 0)));

		rdbtnSendResponse = new JRadioButton("Yes");
		rdbtnSendResponse.setSelected(true);
		rdbtnDontSendResponse = new JRadioButton("No");
		btngrpSendResponseOrNot.add(rdbtnSendResponse);
		btngrpSendResponseOrNot.add(rdbtnDontSendResponse);
		GroupLayout gl_pnSendResponse = new GroupLayout(pnSendResponse);
		gl_pnSendResponse.setHorizontalGroup(gl_pnSendResponse.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_pnSendResponse.createSequentialGroup().addContainerGap().addComponent(rdbtnSendResponse)
						.addGap(124).addComponent(rdbtnDontSendResponse).addContainerGap(273, Short.MAX_VALUE)));
		gl_pnSendResponse.setVerticalGroup(gl_pnSendResponse.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_pnSendResponse.createSequentialGroup().addContainerGap()
						.addGroup(gl_pnSendResponse.createParallelGroup(Alignment.BASELINE)
								.addComponent(rdbtnSendResponse).addComponent(rdbtnDontSendResponse))
						.addContainerGap(17, Short.MAX_VALUE)));
		pnSendResponse.setLayout(gl_pnSendResponse);

		pnConfiguration = new JPanel();
		pnConfiguration.setBorder(new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null),
				"Response Code & Amount", TitledBorder.LEFT, TitledBorder.ABOVE_TOP, null, new Color(0, 0, 0)));

		lblDeclineCode = new JLabel("Decline Code : ");
		txtDeclineCode = new JFormattedTextField(responseFormatter);
		txtDeclineCode.setText("000");

		lblApprovalAmount = new JLabel("Approval Amount : ");

		txtApprovalAmount = new JFormattedTextField();
		txtApprovalAmount.setText("0");
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
								.addComponent(txtDeclineCode, GroupLayout.DEFAULT_SIZE, 361, Short.MAX_VALUE)
								.addComponent(chckbxApproveForHalf)
								.addComponent(txtApprovalAmount, GroupLayout.DEFAULT_SIZE, 361, Short.MAX_VALUE))
						.addContainerGap()));
		gl_pnConfiguration.setVerticalGroup(gl_pnConfiguration.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_pnConfiguration.createSequentialGroup().addContainerGap()
						.addGroup(gl_pnConfiguration.createParallelGroup(Alignment.BASELINE)
								.addComponent(lblDeclineCode).addComponent(txtDeclineCode, GroupLayout.PREFERRED_SIZE,
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
								.addComponent(pnSendResponse, GroupLayout.DEFAULT_SIZE, 496, Short.MAX_VALUE)
								.addComponent(btnSaveTransactionConfiguration, GroupLayout.DEFAULT_SIZE,
										GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
						.addContainerGap(12, Short.MAX_VALUE)));
		gl_pnTransactionConfiguration.setVerticalGroup(gl_pnTransactionConfiguration
				.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_pnTransactionConfiguration.createSequentialGroup().addContainerGap()
						.addComponent(pnSendResponse, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
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
		btngrpReconciliationResult = new ButtonGroup();
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
		btngrpReversalResult = new ButtonGroup();
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
		btngrpFinancialForceDraftResult = new ButtonGroup();
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
		btngrpFinancialSalesResult = new ButtonGroup();
		btngrpFinancialSalesResult.add(rdbtnFinancialSalesApprove);
		btngrpFinancialSalesResult.add(rdbtnFinancialSalesDecline);
		btngrpFinancialSalesResult.add(rdbtnFinancialSalesPartiallyapprove);
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
				lblStatusValue.setText("Online");
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
					lblStatusValue.setText("Offline");
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
		btnSaveServerConfiguration.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				boolean update = false;
				try {
					if (Integer.parseInt(txtPort.getText()) < 1026 || Integer.parseInt(txtPort.getText()) > 65536) {
						System.out.println(
								"Entered port number is invalid. Valid port number range is between 1026 and 65535");
						logger.info(
								"Entered port number is invalid. Valid port number range is between 1026 and 65535");
						JOptionPane.showMessageDialog(null,
								"Entered port number is invalid. Valid port number range is between 1026 and 65535");
					} else {
						property.load(new FileInputStream(new File(Initializer.getFEPpropertiesFilesPath()+"\\"+ Initializer.getFepPropertyFiles().get("Common"))));
						if (!cbxFEP.getSelectedItem().toString().equals(property.getProperty("fepName"))) {
							property.setProperty("fepName", cbxFEP.getSelectedItem().toString());
							update = true;
						}
						if (!txtPort.getText().equals(property.getProperty("portNumber"))) {
							property.setProperty("portNumber", txtPort.getText());
							update = true;
						}
						if (update) {
							property.store(new FileOutputStream(new File(Initializer.getFEPpropertiesFilesPath()+"\\"+Initializer.getFepPropertyFiles().get("Common"))), null);
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
		// -----------------------------------------------------------------------------------------------------------------------------
		/*
		 * When the Save transaction configuration button is clicked, i) Current
		 * configuration is retrieved ii) FEP property file is updated with the
		 * retrieved values.
		 */
		// -----------------------------------------------------------------------------------------------------------------------------
		btnSaveTransactionConfiguration.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				getTransactionConfigurationFromGUI();
				writeTransactionConfigurationToPropertyFile();
			}
		});
	}

	// -----------------------------------------------------------------------------------------------------------------------------
	/*
	 * This method is used to find out the selected radio button in the button
	 * group. It takes the button group as input and return the name of the selected
	 * radio button
	 */
	// -----------------------------------------------------------------------------------------------------------------------------
	public String getSelectedButtonText(ButtonGroup buttonGroup) {
		for (Enumeration<AbstractButton> buttons = buttonGroup.getElements(); buttons.hasMoreElements();) {
			AbstractButton button = buttons.nextElement();

			if (button.isSelected()) {
				return button.getText();
			}
		}
		return null;
	}

	// -----------------------------------------------------------------------------------------------------------------------------
	/*
	 * This method is used to get the transaction configuration from the GUI.
	 */
	// -----------------------------------------------------------------------------------------------------------------------------
	public Map<String, String> getTransactionConfigurationFromGUI() {
		transactionConfigurationMap.put(Initializer.getBaseConstants().guisendResponsePanelName,
				getSelectedButtonText(btngrpSendResponseOrNot));
		transactionConfigurationMap.put(Initializer.getBaseConstants().guiAuthorizationResultPanelName,
				getSelectedButtonText(btngrpauthorizationResult));
		transactionConfigurationMap.put(Initializer.getBaseConstants().guiFinancialSalesResultPanelName,
				getSelectedButtonText(btngrpFinancialSalesResult));
		transactionConfigurationMap.put(Initializer.getBaseConstants().guiFinancialForceDraftResultPanelName,
				getSelectedButtonText(btngrpFinancialForceDraftResult));
		transactionConfigurationMap.put(Initializer.getBaseConstants().guiReversalResultPanelName,
				getSelectedButtonText(btngrpReversalResult));
		transactionConfigurationMap.put(Initializer.getBaseConstants().guiReconciliationResultPanelName,
				getSelectedButtonText(btngrpReconciliationResult));
		transactionConfigurationMap.put(Initializer.getBaseConstants().guiDeclineCodefieldName,
				Initializer.getConverter().zeroPadding(txtDeclineCode.getText(), 3));
		transactionConfigurationMap.put(Initializer.getBaseConstants().guiApprovalAmountFieldName,
				txtApprovalAmount.getText());
		transactionConfigurationMap.put(Initializer.getBaseConstants().guiIsHalfApprovalRequired,
				String.valueOf(chckbxApproveForHalf.isSelected()));
		return transactionConfigurationMap;
	}

	// -----------------------------------------------------------------------------------------------------------------------------
	/*
	 * This method is used to write the transaction configuration into the fep
	 * property file
	 */
	// -----------------------------------------------------------------------------------------------------------------------------
	public void writeTransactionConfigurationToPropertyFile() {
		try {
			property.load(
					new FileInputStream(new File(Initializer.getFEPpropertiesFilesPath()+"\\"+Initializer.getFepPropertyFiles().get(Initializer.getFEPname()))));
			property.setProperty(Initializer.getBaseConstants().guisendResponsePanelName,
					transactionConfigurationMap.get(Initializer.getBaseConstants().guisendResponsePanelName));
			property.setProperty(Initializer.getBaseConstants().guiAuthorizationResultPanelName,
					transactionConfigurationMap.get(Initializer.getBaseConstants().guiAuthorizationResultPanelName));
			property.setProperty(Initializer.getBaseConstants().guiFinancialSalesResultPanelName,
					transactionConfigurationMap.get(Initializer.getBaseConstants().guiFinancialSalesResultPanelName));
			property.setProperty(Initializer.getBaseConstants().guiFinancialForceDraftResultPanelName,
					transactionConfigurationMap
							.get(Initializer.getBaseConstants().guiFinancialForceDraftResultPanelName));
			property.setProperty(Initializer.getBaseConstants().guiReversalResultPanelName,
					transactionConfigurationMap.get(Initializer.getBaseConstants().guiReversalResultPanelName));
			property.setProperty(Initializer.getBaseConstants().guiReconciliationResultPanelName,
					transactionConfigurationMap.get(Initializer.getBaseConstants().guiReconciliationResultPanelName));
			property.setProperty(Initializer.getBaseConstants().guiDeclineCodefieldName,
					transactionConfigurationMap.get(Initializer.getBaseConstants().guiDeclineCodefieldName));
			property.setProperty(Initializer.getBaseConstants().guiApprovalAmountFieldName,
					transactionConfigurationMap.get(Initializer.getBaseConstants().guiApprovalAmountFieldName));
			property.setProperty(Initializer.getBaseConstants().guiIsHalfApprovalRequired,
					transactionConfigurationMap.get(Initializer.getBaseConstants().guiIsHalfApprovalRequired));

			property.store(new FileOutputStream(new File(Initializer.getApplicationFolder()+"\\FEPproperties\\"+Initializer.getFepPropertyFiles().get(Initializer.getFEPname()))), null);
		} catch (IOException e) {
			logger.error("Unable to update the fep property file");
			System.out.println("Unable to update the fep property file");
			JOptionPane.showMessageDialog(null, "Unable to update the fep property file");
		}
	}

}
