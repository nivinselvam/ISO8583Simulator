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
	public String valueOfBitfield1;
	public String valueOfBitfield2;
	public String valueOfBitfield3;
	public String valueOfBitfield4;
	public String valueOfBitfield5;
	public String valueOfBitfield6;
	public String valueOfBitfield7;
	public String valueOfBitfield8;
	public String valueOfBitfield9;
	public String valueOfBitfield10;
	public String valueOfBitfield11;
	public String valueOfBitfield12;
	public String valueOfBitfield13;
	public String valueOfBitfield14;
	public String valueOfBitfield15;
	public String valueOfBitfield16;
	public String valueOfBitfield17;
	public String valueOfBitfield18;
	public String valueOfBitfield19;
	public String valueOfBitfield20;
	public String valueOfBitfield21;
	public String valueOfBitfield22;
	public String valueOfBitfield23;
	public String valueOfBitfield24;
	public String valueOfBitfield25;
	public String valueOfBitfield26;
	public String valueOfBitfield27;
	public String valueOfBitfield28;
	public String valueOfBitfield29;
	public String valueOfBitfield30;
	public String valueOfBitfield31;
	public String valueOfBitfield32;
	public String valueOfBitfield33;
	public String valueOfBitfield34;
	public String valueOfBitfield35;
	public String valueOfBitfield36;
	public String valueOfBitfield37;
	public String valueOfBitfield38;
	public String ValueOfBitfield39Approval;
	public String ValueOfBitfield39Decline;
	public String ValueOfBitfield39Partial;
	public String ValueOfBitfield39Reversal;
	public String ValueOfBitfield39Reconciliation;
	public String valueOfBitfield40;
	public String valueOfBitfield41;
	public String valueOfBitfield42;
	public String valueOfBitfield43;
	public String valueOfBitfield44;
	public String valueOfBitfield45;
	public String valueOfBitfield46;
	public String valueOfBitfield47;
	public String valueOfBitfield48;
	public String valueOfBitfield49;
	public String valueOfBitfield50;
	public String valueOfBitfield51;
	public String valueOfBitfield52;
	public String valueOfBitfield53;
	public String valueOfBitfield54;
	public String valueOfBitfield55;
	public String valueOfBitfield56;
	public String valueOfBitfield57;
	public String valueOfBitfield58;
	public String valueOfBitfield59;
	public String valueOfBitfield60;
	public String valueOfBitfield61;
	public String valueOfBitfield62;
	public String valueOfBitfield63;
	public String valueOfBitfield64;
	public String valueOfBitfield65;
	public String valueOfBitfield66;
	public String valueOfBitfield67;
	public String valueOfBitfield68;
	public String valueOfBitfield69;
	public String valueOfBitfield70;
	public String valueOfBitfield71;
	public String valueOfBitfield72;
	public String valueOfBitfield73;
	public String valueOfBitfield74;
	public String valueOfBitfield75;
	public String valueOfBitfield76;
	public String valueOfBitfield77;
	public String valueOfBitfield78;
	public String valueOfBitfield79;
	public String valueOfBitfield80;
	public String valueOfBitfield81;
	public String valueOfBitfield82;
	public String valueOfBitfield83;
	public String valueOfBitfield84;
	public String valueOfBitfield85;
	public String valueOfBitfield86;
	public String valueOfBitfield87;
	public String valueOfBitfield88;
	public String valueOfBitfield89;
	public String valueOfBitfield90;
	public String valueOfBitfield91;
	public String valueOfBitfield92;
	public String valueOfBitfield93;
	public String valueOfBitfield94;
	public String valueOfBitfield95;
	public String valueOfBitfield96;
	public String valueOfBitfield97;
	public String valueOfBitfield98;
	public String valueOfBitfield99;
	public String valueOfBitfield100;
	public String valueOfBitfield101;
	public String valueOfBitfield102;
	public String valueOfBitfield103;
	public String valueOfBitfield104;
	public String valueOfBitfield105;
	public String valueOfBitfield106;
	public String valueOfBitfield107;
	public String valueOfBitfield108;
	public String valueOfBitfield109;
	public String valueOfBitfield110;
	public String valueOfBitfield111;
	public String valueOfBitfield112;
	public String valueOfBitfield113;
	public String valueOfBitfield114;
	public String valueOfBitfield115;
	public String valueOfBitfield116;
	public String valueOfBitfield117;
	public String valueOfBitfield118;
	public String valueOfBitfield119;
	public String valueOfBitfield120;
	public String valueOfBitfield121;
	public String valueOfBitfield122;
	public String valueOfBitfield123;
	public String valueOfBitfield124;
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
			property.load(new FileInputStream(new File(Initializer.getApplicationDefaultFilesPath() + "/"
					+ Initializer.getFepPropertyFiles().get("Common"))));
			Initializer.setFEPname(property.getProperty("fepName"));
			Initializer.setPortNumber(Integer.parseInt(property.getProperty("portNumber")));

			property.load(new FileInputStream(new File(Initializer.getApplicationDefaultFilesPath() + "/"
					+ Initializer.getFepPropertyFiles().get(Initializer.getFEPname()))));

			sendResponse = property.getProperty("sendResponse");

			authorizationTransactionResponse = property.getProperty("authorizationTransactionResponse");
			financialSalesTransactionResponse = property.getProperty("financialSalesTransactionResponse");
			financialForceDraftTransactionResponse = property.getProperty("financialForceDraftTransactionResponse");
			reversalTransactionResponse = property.getProperty("reversalTransactionResponse");
			reconciliationTransactionResponse = property.getProperty("reconciliationTransactionResponse");

			isHalfApprovalRequired = property.getProperty("isHalfApprovalRequired");

			valueOfBitfield1 = property.getProperty("valueOfBitfield1");
			valueOfBitfield2 = property.getProperty("valueOfBitfield2");
			valueOfBitfield3 = property.getProperty("valueOfBitfield3");
			valueOfBitfield4 = property.getProperty("valueOfBitfield4");
			valueOfBitfield5 = property.getProperty("valueOfBitfield5");
			valueOfBitfield6 = property.getProperty("valueOfBitfield6");
			valueOfBitfield7 = property.getProperty("valueOfBitfield7");
			valueOfBitfield8 = property.getProperty("valueOfBitfield8");
			valueOfBitfield9 = property.getProperty("valueOfBitfield9");
			valueOfBitfield10 = property.getProperty("valueOfBitfield10");
			valueOfBitfield11 = property.getProperty("valueOfBitfield11");
			valueOfBitfield12 = property.getProperty("valueOfBitfield12");
			valueOfBitfield13 = property.getProperty("valueOfBitfield13");
			valueOfBitfield14 = property.getProperty("valueOfBitfield14");
			valueOfBitfield15 = property.getProperty("valueOfBitfield15");
			valueOfBitfield16 = property.getProperty("valueOfBitfield16");
			valueOfBitfield17 = property.getProperty("valueOfBitfield17");
			valueOfBitfield18 = property.getProperty("valueOfBitfield18");
			valueOfBitfield19 = property.getProperty("valueOfBitfield19");
			valueOfBitfield20 = property.getProperty("valueOfBitfield20");
			valueOfBitfield21 = property.getProperty("valueOfBitfield21");
			valueOfBitfield22 = property.getProperty("valueOfBitfield22");
			valueOfBitfield23 = property.getProperty("valueOfBitfield23");
			valueOfBitfield24 = property.getProperty("valueOfBitfield24");
			valueOfBitfield25 = property.getProperty("valueOfBitfield25");
			valueOfBitfield26 = property.getProperty("valueOfBitfield26");
			valueOfBitfield27 = property.getProperty("valueOfBitfield27");
			valueOfBitfield28 = property.getProperty("valueOfBitfield28");
			valueOfBitfield29 = property.getProperty("valueOfBitfield29");
			valueOfBitfield30 = property.getProperty("valueOfBitfield30");
			valueOfBitfield31 = property.getProperty("valueOfBitfield31");
			valueOfBitfield32 = property.getProperty("valueOfBitfield32");
			valueOfBitfield33 = property.getProperty("valueOfBitfield33");
			valueOfBitfield34 = property.getProperty("valueOfBitfield34");
			valueOfBitfield35 = property.getProperty("valueOfBitfield35");
			valueOfBitfield36 = property.getProperty("valueOfBitfield36");						
			valueOfBitfield37 = property.getProperty("valueOfBitfield37");
			valueOfBitfield38 = property.getProperty("valueOfBitfield38");
			ValueOfBitfield39Approval = property.getProperty("ValueOfBitfield39Approval");
			ValueOfBitfield39Decline = property.getProperty("ValueOfBitfield39Decline");
			ValueOfBitfield39Partial = property.getProperty("ValueOfBitfield39Partial");
			ValueOfBitfield39Reversal = property.getProperty("ValueOfBitfield39Reversal");
			ValueOfBitfield39Reconciliation = property.getProperty("ValueOfBitfield39Reconciliation");
			valueOfBitfield40 = property.getProperty("valueOfBitfield40");
			valueOfBitfield41 = property.getProperty("valueOfBitfield41");
			valueOfBitfield42 = property.getProperty("valueOfBitfield42");
			valueOfBitfield43 = property.getProperty("valueOfBitfield43");
			valueOfBitfield44 = property.getProperty("valueOfBitfield44");
			valueOfBitfield45 = property.getProperty("valueOfBitfield45");
			valueOfBitfield46 = property.getProperty("valueOfBitfield46");
			valueOfBitfield47 = property.getProperty("valueOfBitfield47");
			valueOfBitfield48 = property.getProperty("valueOfBitfield48");
			valueOfBitfield49 = property.getProperty("valueOfBitfield49");
			valueOfBitfield50 = property.getProperty("valueOfBitfield50");
			valueOfBitfield51 = property.getProperty("valueOfBitfield51");
			valueOfBitfield52 = property.getProperty("valueOfBitfield52");
			valueOfBitfield53 = property.getProperty("valueOfBitfield53");
			valueOfBitfield54 = property.getProperty("valueOfBitfield54");
			valueOfBitfield55 = property.getProperty("valueOfBitfield55");
			valueOfBitfield56 = property.getProperty("valueOfBitfield56");
			valueOfBitfield57 = property.getProperty("valueOfBitfield57");
			valueOfBitfield58 = property.getProperty("valueOfBitfield58");
			valueOfBitfield59 = property.getProperty("valueOfBitfield59");
			valueOfBitfield60 = property.getProperty("valueOfBitfield60");
			valueOfBitfield61 = property.getProperty("valueOfBitfield61");
			valueOfBitfield62 = property.getProperty("valueOfBitfield62");
			valueOfBitfield63 = property.getProperty("valueOfBitfield63");
			valueOfBitfield64 = property.getProperty("valueOfBitfield64");
			valueOfBitfield65 = property.getProperty("valueOfBitfield65");
			valueOfBitfield66 = property.getProperty("valueOfBitfield66");
			valueOfBitfield67 = property.getProperty("valueOfBitfield67");
			valueOfBitfield68 = property.getProperty("valueOfBitfield68");
			valueOfBitfield69 = property.getProperty("valueOfBitfield69");
			valueOfBitfield70 = property.getProperty("valueOfBitfield70");
			valueOfBitfield71 = property.getProperty("valueOfBitfield71");
			valueOfBitfield72 = property.getProperty("valueOfBitfield72");
			valueOfBitfield73 = property.getProperty("valueOfBitfield73");
			valueOfBitfield74 = property.getProperty("valueOfBitfield74");
			valueOfBitfield75 = property.getProperty("valueOfBitfield75");
			valueOfBitfield76 = property.getProperty("valueOfBitfield76");
			valueOfBitfield77 = property.getProperty("valueOfBitfield77");
			valueOfBitfield78 = property.getProperty("valueOfBitfield78");
			valueOfBitfield79 = property.getProperty("valueOfBitfield79");
			valueOfBitfield80 = property.getProperty("valueOfBitfield80");
			valueOfBitfield81 = property.getProperty("valueOfBitfield81");
			valueOfBitfield82 = property.getProperty("valueOfBitfield82");
			valueOfBitfield83 = property.getProperty("valueOfBitfield83");
			valueOfBitfield84 = property.getProperty("valueOfBitfield84");
			valueOfBitfield85 = property.getProperty("valueOfBitfield85");
			valueOfBitfield86 = property.getProperty("valueOfBitfield86");
			valueOfBitfield87 = property.getProperty("valueOfBitfield87");
			valueOfBitfield88 = property.getProperty("valueOfBitfield88");
			valueOfBitfield89 = property.getProperty("valueOfBitfield89");
			valueOfBitfield90 = property.getProperty("valueOfBitfield90");
			valueOfBitfield91 = property.getProperty("valueOfBitfield91");
			valueOfBitfield92 = property.getProperty("valueOfBitfield92");
			valueOfBitfield93 = property.getProperty("valueOfBitfield93");
			valueOfBitfield94 = property.getProperty("valueOfBitfield94");
			valueOfBitfield95 = property.getProperty("valueOfBitfield95");
			valueOfBitfield96 = property.getProperty("valueOfBitfield96");
			valueOfBitfield97 = property.getProperty("valueOfBitfield97");
			valueOfBitfield98 = property.getProperty("valueOfBitfield98");
			valueOfBitfield99 = property.getProperty("valueOfBitfield99");
			valueOfBitfield100 = property.getProperty("valueOfBitfield100");
			valueOfBitfield101 = property.getProperty("valueOfBitfield101");
			valueOfBitfield102 = property.getProperty("valueOfBitfield102");
			valueOfBitfield103 = property.getProperty("valueOfBitfield103");
			valueOfBitfield104 = property.getProperty("valueOfBitfield104");
			valueOfBitfield105 = property.getProperty("valueOfBitfield105");
			valueOfBitfield106 = property.getProperty("valueOfBitfield106");
			valueOfBitfield107 = property.getProperty("valueOfBitfield107");
			valueOfBitfield108 = property.getProperty("valueOfBitfield108");
			valueOfBitfield109 = property.getProperty("valueOfBitfield109");
			valueOfBitfield110 = property.getProperty("valueOfBitfield110");
			valueOfBitfield111 = property.getProperty("valueOfBitfield111");
			valueOfBitfield112 = property.getProperty("valueOfBitfield112");
			valueOfBitfield113 = property.getProperty("valueOfBitfield113");
			valueOfBitfield114 = property.getProperty("valueOfBitfield114");
			valueOfBitfield115 = property.getProperty("valueOfBitfield115");
			valueOfBitfield116 = property.getProperty("valueOfBitfield116");
			valueOfBitfield117 = property.getProperty("valueOfBitfield117");
			valueOfBitfield118 = property.getProperty("valueOfBitfield118");
			valueOfBitfield119 = property.getProperty("valueOfBitfield119");
			valueOfBitfield120 = property.getProperty("valueOfBitfield120");
			valueOfBitfield121 = property.getProperty("valueOfBitfield121");
			valueOfBitfield122 = property.getProperty("valueOfBitfield122");
			valueOfBitfield123 = property.getProperty("valueOfBitfield123");
			valueOfBitfield124 = property.getProperty("valueOfBitfield124");
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
		for (int i = 0; i < Initializer.getBitfieldData().bitfieldLength.get("BITFIELD39"); i++) {
			bitfield39UpperLimit = bitfield39UpperLimit + "9";
		}
	}
}
