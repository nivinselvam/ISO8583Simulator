package com.BaseFiles;

import java.io.File;
import java.io.FileInputStream;
import java.util.Properties;

import org.apache.log4j.Logger;

public class BaseVariables {
	public String sendResponse;
	public String authorizationTransactionResponse;
	public String financialSalesTransactionResponse;
	public String financialForceDraftTransactionResponse;
	public String reversalTransactionResponse;
	public String reconciliationTransactionResponse;
	public String isHalfApprovalRequired;
	public String valueOfBitfield4;
	public String valueOfBitfield37;
	public String valueOfBitfield38;
	public String ValueOfBitfield39Approval;
	public String ValueOfBitfield39Decline;
	public String ValueOfBitfield39Partial;
	public String ValueOfBitfield39Reversal;
	public String ValueOfBitfield39Reconciliation;
	public String valueOfBitfield44;
	public String valueOfBitfield48;
	public String valueOfBitfield54;
	public String valueOfBitfield62;
	public String valueOfBitfield123;
	public String bitfield39UpperLimit;
	private Properties property = new Properties();
	private Logger logger = Logger.getLogger(BaseVariables.class);

	public BaseVariables() {
		loadDefaultValues();
	}

	// -------------------------------------------------------------------------------------------------------------
	/*
	 * This method loads the defaults values for the simulator to start functioning.
	 * This is read from the properties files that are stored internally.
	 */
	// -------------------------------------------------------------------------------------------------------------
	public void loadDefaultValues() {
		try {
			property.load(new FileInputStream(new File(
					Initializer.getFEPpropertiesFilesPath() + "\\" + Initializer.getFepPropertyFiles().get("Common"))));
			Initializer.setFEPname(property.getProperty("fepName"));
			Initializer.setPortNumber(Integer.parseInt(property.getProperty("portNumber")));

			property.load(new FileInputStream(new File(Initializer.getFEPpropertiesFilesPath() + "\\"
					+ Initializer.getFepPropertyFiles().get(Initializer.getFEPname()))));

			sendResponse = property.getProperty("sendResponse");

			authorizationTransactionResponse = property.getProperty("authorizationTransactionResponse");
			financialSalesTransactionResponse = property.getProperty("financialSalesTransactionResponse");
			financialForceDraftTransactionResponse = property.getProperty("financialForceDraftTransactionResponse");
			reversalTransactionResponse = property.getProperty("reversalTransactionResponse");
			reconciliationTransactionResponse = property.getProperty("reconciliationTransactionResponse");

			isHalfApprovalRequired = property.getProperty("isHalfApprovalRequired");
			valueOfBitfield4 = property.getProperty("valueOfBitfield4");
			valueOfBitfield37 = property.getProperty("valueOfBitfield37");
			valueOfBitfield38 = property.getProperty("valueOfBitfield38");
			ValueOfBitfield39Approval = property.getProperty("ValueOfBitfield39Approval");
			ValueOfBitfield39Decline = property.getProperty("ValueOfBitfield39Decline");
			ValueOfBitfield39Partial = property.getProperty("ValueOfBitfield39Partial");
			ValueOfBitfield39Reversal = property.getProperty("ValueOfBitfield39Reversal");
			ValueOfBitfield39Reconciliation = property.getProperty("ValueOfBitfield39Reconciliation");
			valueOfBitfield44 = property.getProperty("valueOfBitfield44");
			valueOfBitfield48 = property.getProperty("valueOfBitfield48");
			valueOfBitfield54 = property.getProperty("valueOfBitfield54");
			valueOfBitfield62 = property.getProperty("valueOfBitfield62");
			valueOfBitfield123 = property.getProperty("valueOfBitfield123");
			setBitfield39UpperLimit();
		} catch (Exception e) {
			logger.warn("Unable to load default values into base variables");
		}
	}

	// -----------------------------------------------------------------------------------------------------------------------------
	/*
	 * This method set the decline code value. Based on the FEP, length of
	 * Bitfield39 will vary. This will identify the FEP and set upper and lower
	 * values accordingly
	 */
	// -----------------------------------------------------------------------------------------------------------------------------
	public void setBitfield39UpperLimit() {
		bitfield39UpperLimit = "";	
		for (int i = 0; i < Initializer.getBitfieldData().bitfieldLength
				.get("BITFIELD39"); i++) {
			bitfield39UpperLimit = bitfield39UpperLimit+"9";
		}
	}
}
