package com.BaseFiles;

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
			sendResponse = Initializer.getConfigurationTracker().getFepPropertiesMap().get("sendResponse");

			authorizationTransactionResponse = Initializer.getConfigurationTracker().getFepPropertiesMap().get("authorizationTransactionResponse");
			financialSalesTransactionResponse = Initializer.getConfigurationTracker().getFepPropertiesMap().get("financialSalesTransactionResponse");
			financialForceDraftTransactionResponse = Initializer.getConfigurationTracker().getFepPropertiesMap().get("financialForceDraftTransactionResponse");
			reversalTransactionResponse = Initializer.getConfigurationTracker().getFepPropertiesMap().get("reversalTransactionResponse");
			reconciliationTransactionResponse = Initializer.getConfigurationTracker().getFepPropertiesMap().get("reconciliationTransactionResponse");

			isHalfApprovalRequired = Initializer.getConfigurationTracker().getFepPropertiesMap().get("isHalfApprovalRequired");

			valueOfBitfield1 = Initializer.getConfigurationTracker().getFepPropertiesMap().get("valueOfBitfield1");
			valueOfBitfield2 = Initializer.getConfigurationTracker().getFepPropertiesMap().get("valueOfBitfield2");
			valueOfBitfield3 = Initializer.getConfigurationTracker().getFepPropertiesMap().get("valueOfBitfield3");
			valueOfBitfield4 = Initializer.getConfigurationTracker().getFepPropertiesMap().get("valueOfBitfield4");
			valueOfBitfield5 = Initializer.getConfigurationTracker().getFepPropertiesMap().get("valueOfBitfield5");
			valueOfBitfield6 = Initializer.getConfigurationTracker().getFepPropertiesMap().get("valueOfBitfield6");
			valueOfBitfield7 = Initializer.getConfigurationTracker().getFepPropertiesMap().get("valueOfBitfield7");
			valueOfBitfield8 = Initializer.getConfigurationTracker().getFepPropertiesMap().get("valueOfBitfield8");
			valueOfBitfield9 = Initializer.getConfigurationTracker().getFepPropertiesMap().get("valueOfBitfield9");
			valueOfBitfield10 = Initializer.getConfigurationTracker().getFepPropertiesMap().get("valueOfBitfield10");
			valueOfBitfield11 = Initializer.getConfigurationTracker().getFepPropertiesMap().get("valueOfBitfield11");
			valueOfBitfield12 = Initializer.getConfigurationTracker().getFepPropertiesMap().get("valueOfBitfield12");
			valueOfBitfield13 = Initializer.getConfigurationTracker().getFepPropertiesMap().get("valueOfBitfield13");
			valueOfBitfield14 = Initializer.getConfigurationTracker().getFepPropertiesMap().get("valueOfBitfield14");
			valueOfBitfield15 = Initializer.getConfigurationTracker().getFepPropertiesMap().get("valueOfBitfield15");
			valueOfBitfield16 = Initializer.getConfigurationTracker().getFepPropertiesMap().get("valueOfBitfield16");
			valueOfBitfield17 = Initializer.getConfigurationTracker().getFepPropertiesMap().get("valueOfBitfield17");
			valueOfBitfield18 = Initializer.getConfigurationTracker().getFepPropertiesMap().get("valueOfBitfield18");
			valueOfBitfield19 = Initializer.getConfigurationTracker().getFepPropertiesMap().get("valueOfBitfield19");
			valueOfBitfield20 = Initializer.getConfigurationTracker().getFepPropertiesMap().get("valueOfBitfield20");
			valueOfBitfield21 = Initializer.getConfigurationTracker().getFepPropertiesMap().get("valueOfBitfield21");
			valueOfBitfield22 = Initializer.getConfigurationTracker().getFepPropertiesMap().get("valueOfBitfield22");
			valueOfBitfield23 = Initializer.getConfigurationTracker().getFepPropertiesMap().get("valueOfBitfield23");
			valueOfBitfield24 = Initializer.getConfigurationTracker().getFepPropertiesMap().get("valueOfBitfield24");
			valueOfBitfield25 = Initializer.getConfigurationTracker().getFepPropertiesMap().get("valueOfBitfield25");
			valueOfBitfield26 = Initializer.getConfigurationTracker().getFepPropertiesMap().get("valueOfBitfield26");
			valueOfBitfield27 = Initializer.getConfigurationTracker().getFepPropertiesMap().get("valueOfBitfield27");
			valueOfBitfield28 = Initializer.getConfigurationTracker().getFepPropertiesMap().get("valueOfBitfield28");
			valueOfBitfield29 = Initializer.getConfigurationTracker().getFepPropertiesMap().get("valueOfBitfield29");
			valueOfBitfield30 = Initializer.getConfigurationTracker().getFepPropertiesMap().get("valueOfBitfield30");
			valueOfBitfield31 = Initializer.getConfigurationTracker().getFepPropertiesMap().get("valueOfBitfield31");
			valueOfBitfield32 = Initializer.getConfigurationTracker().getFepPropertiesMap().get("valueOfBitfield32");
			valueOfBitfield33 = Initializer.getConfigurationTracker().getFepPropertiesMap().get("valueOfBitfield33");
			valueOfBitfield34 = Initializer.getConfigurationTracker().getFepPropertiesMap().get("valueOfBitfield34");
			valueOfBitfield35 = Initializer.getConfigurationTracker().getFepPropertiesMap().get("valueOfBitfield35");
			valueOfBitfield36 = Initializer.getConfigurationTracker().getFepPropertiesMap().get("valueOfBitfield36");
			valueOfBitfield37 = Initializer.getConfigurationTracker().getFepPropertiesMap().get("valueOfBitfield37");
			valueOfBitfield38 = Initializer.getConfigurationTracker().getFepPropertiesMap().get("valueOfBitfield38");
			ValueOfBitfield39Approval = Initializer.getConfigurationTracker().getFepPropertiesMap().get("ValueOfBitfield39Approval");
			ValueOfBitfield39Decline = Initializer.getConfigurationTracker().getFepPropertiesMap().get("ValueOfBitfield39Decline");
			ValueOfBitfield39Partial = Initializer.getConfigurationTracker().getFepPropertiesMap().get("ValueOfBitfield39Partial");
			ValueOfBitfield39Reversal = Initializer.getConfigurationTracker().getFepPropertiesMap().get("ValueOfBitfield39Reversal");
			ValueOfBitfield39Reconciliation = Initializer.getConfigurationTracker().getFepPropertiesMap().get("ValueOfBitfield39Reconciliation");
			valueOfBitfield40 = Initializer.getConfigurationTracker().getFepPropertiesMap().get("valueOfBitfield40");
			valueOfBitfield41 = Initializer.getConfigurationTracker().getFepPropertiesMap().get("valueOfBitfield41");
			valueOfBitfield42 = Initializer.getConfigurationTracker().getFepPropertiesMap().get("valueOfBitfield42");
			valueOfBitfield43 = Initializer.getConfigurationTracker().getFepPropertiesMap().get("valueOfBitfield43");
			valueOfBitfield44 = Initializer.getConfigurationTracker().getFepPropertiesMap().get("valueOfBitfield44");
			valueOfBitfield45 = Initializer.getConfigurationTracker().getFepPropertiesMap().get("valueOfBitfield45");
			valueOfBitfield46 = Initializer.getConfigurationTracker().getFepPropertiesMap().get("valueOfBitfield46");
			valueOfBitfield47 = Initializer.getConfigurationTracker().getFepPropertiesMap().get("valueOfBitfield47");
			valueOfBitfield48 = Initializer.getConfigurationTracker().getFepPropertiesMap().get("valueOfBitfield48");
			valueOfBitfield49 = Initializer.getConfigurationTracker().getFepPropertiesMap().get("valueOfBitfield49");
			valueOfBitfield50 = Initializer.getConfigurationTracker().getFepPropertiesMap().get("valueOfBitfield50");
			valueOfBitfield51 = Initializer.getConfigurationTracker().getFepPropertiesMap().get("valueOfBitfield51");
			valueOfBitfield52 = Initializer.getConfigurationTracker().getFepPropertiesMap().get("valueOfBitfield52");
			valueOfBitfield53 = Initializer.getConfigurationTracker().getFepPropertiesMap().get("valueOfBitfield53");
			valueOfBitfield54 = Initializer.getConfigurationTracker().getFepPropertiesMap().get("valueOfBitfield54");
			valueOfBitfield55 = Initializer.getConfigurationTracker().getFepPropertiesMap().get("valueOfBitfield55");
			valueOfBitfield56 = Initializer.getConfigurationTracker().getFepPropertiesMap().get("valueOfBitfield56");
			valueOfBitfield57 = Initializer.getConfigurationTracker().getFepPropertiesMap().get("valueOfBitfield57");
			valueOfBitfield58 = Initializer.getConfigurationTracker().getFepPropertiesMap().get("valueOfBitfield58");
			valueOfBitfield59 = Initializer.getConfigurationTracker().getFepPropertiesMap().get("valueOfBitfield59");
			valueOfBitfield60 = Initializer.getConfigurationTracker().getFepPropertiesMap().get("valueOfBitfield60");
			valueOfBitfield61 = Initializer.getConfigurationTracker().getFepPropertiesMap().get("valueOfBitfield61");
			valueOfBitfield62 = Initializer.getConfigurationTracker().getFepPropertiesMap().get("valueOfBitfield62");
			valueOfBitfield63 = Initializer.getConfigurationTracker().getFepPropertiesMap().get("valueOfBitfield63");
			valueOfBitfield64 = Initializer.getConfigurationTracker().getFepPropertiesMap().get("valueOfBitfield64");
			valueOfBitfield65 = Initializer.getConfigurationTracker().getFepPropertiesMap().get("valueOfBitfield65");
			valueOfBitfield66 = Initializer.getConfigurationTracker().getFepPropertiesMap().get("valueOfBitfield66");
			valueOfBitfield67 = Initializer.getConfigurationTracker().getFepPropertiesMap().get("valueOfBitfield67");
			valueOfBitfield68 = Initializer.getConfigurationTracker().getFepPropertiesMap().get("valueOfBitfield68");
			valueOfBitfield69 = Initializer.getConfigurationTracker().getFepPropertiesMap().get("valueOfBitfield69");
			valueOfBitfield70 = Initializer.getConfigurationTracker().getFepPropertiesMap().get("valueOfBitfield70");
			valueOfBitfield71 = Initializer.getConfigurationTracker().getFepPropertiesMap().get("valueOfBitfield71");
			valueOfBitfield72 = Initializer.getConfigurationTracker().getFepPropertiesMap().get("valueOfBitfield72");
			valueOfBitfield73 = Initializer.getConfigurationTracker().getFepPropertiesMap().get("valueOfBitfield73");
			valueOfBitfield74 = Initializer.getConfigurationTracker().getFepPropertiesMap().get("valueOfBitfield74");
			valueOfBitfield75 = Initializer.getConfigurationTracker().getFepPropertiesMap().get("valueOfBitfield75");
			valueOfBitfield76 = Initializer.getConfigurationTracker().getFepPropertiesMap().get("valueOfBitfield76");
			valueOfBitfield77 = Initializer.getConfigurationTracker().getFepPropertiesMap().get("valueOfBitfield77");
			valueOfBitfield78 = Initializer.getConfigurationTracker().getFepPropertiesMap().get("valueOfBitfield78");
			valueOfBitfield79 = Initializer.getConfigurationTracker().getFepPropertiesMap().get("valueOfBitfield79");
			valueOfBitfield80 = Initializer.getConfigurationTracker().getFepPropertiesMap().get("valueOfBitfield80");
			valueOfBitfield81 = Initializer.getConfigurationTracker().getFepPropertiesMap().get("valueOfBitfield81");
			valueOfBitfield82 = Initializer.getConfigurationTracker().getFepPropertiesMap().get("valueOfBitfield82");
			valueOfBitfield83 = Initializer.getConfigurationTracker().getFepPropertiesMap().get("valueOfBitfield83");
			valueOfBitfield84 = Initializer.getConfigurationTracker().getFepPropertiesMap().get("valueOfBitfield84");
			valueOfBitfield85 = Initializer.getConfigurationTracker().getFepPropertiesMap().get("valueOfBitfield85");
			valueOfBitfield86 = Initializer.getConfigurationTracker().getFepPropertiesMap().get("valueOfBitfield86");
			valueOfBitfield87 = Initializer.getConfigurationTracker().getFepPropertiesMap().get("valueOfBitfield87");
			valueOfBitfield88 = Initializer.getConfigurationTracker().getFepPropertiesMap().get("valueOfBitfield88");
			valueOfBitfield89 = Initializer.getConfigurationTracker().getFepPropertiesMap().get("valueOfBitfield89");
			valueOfBitfield90 = Initializer.getConfigurationTracker().getFepPropertiesMap().get("valueOfBitfield90");
			valueOfBitfield91 = Initializer.getConfigurationTracker().getFepPropertiesMap().get("valueOfBitfield91");
			valueOfBitfield92 = Initializer.getConfigurationTracker().getFepPropertiesMap().get("valueOfBitfield92");
			valueOfBitfield93 = Initializer.getConfigurationTracker().getFepPropertiesMap().get("valueOfBitfield93");
			valueOfBitfield94 = Initializer.getConfigurationTracker().getFepPropertiesMap().get("valueOfBitfield94");
			valueOfBitfield95 = Initializer.getConfigurationTracker().getFepPropertiesMap().get("valueOfBitfield95");
			valueOfBitfield96 = Initializer.getConfigurationTracker().getFepPropertiesMap().get("valueOfBitfield96");
			valueOfBitfield97 = Initializer.getConfigurationTracker().getFepPropertiesMap().get("valueOfBitfield97");
			valueOfBitfield98 = Initializer.getConfigurationTracker().getFepPropertiesMap().get("valueOfBitfield98");
			valueOfBitfield99 = Initializer.getConfigurationTracker().getFepPropertiesMap().get("valueOfBitfield99");
			valueOfBitfield100 = Initializer.getConfigurationTracker().getFepPropertiesMap().get("valueOfBitfield100");
			valueOfBitfield101 = Initializer.getConfigurationTracker().getFepPropertiesMap().get("valueOfBitfield101");
			valueOfBitfield102 = Initializer.getConfigurationTracker().getFepPropertiesMap().get("valueOfBitfield102");
			valueOfBitfield103 = Initializer.getConfigurationTracker().getFepPropertiesMap().get("valueOfBitfield103");
			valueOfBitfield104 = Initializer.getConfigurationTracker().getFepPropertiesMap().get("valueOfBitfield104");
			valueOfBitfield105 = Initializer.getConfigurationTracker().getFepPropertiesMap().get("valueOfBitfield105");
			valueOfBitfield106 = Initializer.getConfigurationTracker().getFepPropertiesMap().get("valueOfBitfield106");
			valueOfBitfield107 = Initializer.getConfigurationTracker().getFepPropertiesMap().get("valueOfBitfield107");
			valueOfBitfield108 = Initializer.getConfigurationTracker().getFepPropertiesMap().get("valueOfBitfield108");
			valueOfBitfield109 = Initializer.getConfigurationTracker().getFepPropertiesMap().get("valueOfBitfield109");
			valueOfBitfield110 = Initializer.getConfigurationTracker().getFepPropertiesMap().get("valueOfBitfield110");
			valueOfBitfield111 = Initializer.getConfigurationTracker().getFepPropertiesMap().get("valueOfBitfield111");
			valueOfBitfield112 = Initializer.getConfigurationTracker().getFepPropertiesMap().get("valueOfBitfield112");
			valueOfBitfield113 = Initializer.getConfigurationTracker().getFepPropertiesMap().get("valueOfBitfield113");
			valueOfBitfield114 = Initializer.getConfigurationTracker().getFepPropertiesMap().get("valueOfBitfield114");
			valueOfBitfield115 = Initializer.getConfigurationTracker().getFepPropertiesMap().get("valueOfBitfield115");
			valueOfBitfield116 = Initializer.getConfigurationTracker().getFepPropertiesMap().get("valueOfBitfield116");
			valueOfBitfield117 = Initializer.getConfigurationTracker().getFepPropertiesMap().get("valueOfBitfield117");
			valueOfBitfield118 = Initializer.getConfigurationTracker().getFepPropertiesMap().get("valueOfBitfield118");
			valueOfBitfield119 = Initializer.getConfigurationTracker().getFepPropertiesMap().get("valueOfBitfield119");
			valueOfBitfield120 = Initializer.getConfigurationTracker().getFepPropertiesMap().get("valueOfBitfield120");
			valueOfBitfield121 = Initializer.getConfigurationTracker().getFepPropertiesMap().get("valueOfBitfield121");
			valueOfBitfield122 = Initializer.getConfigurationTracker().getFepPropertiesMap().get("valueOfBitfield122");
			valueOfBitfield123 = Initializer.getConfigurationTracker().getFepPropertiesMap().get("valueOfBitfield123");
			valueOfBitfield124 = Initializer.getConfigurationTracker().getFepPropertiesMap().get("valueOfBitfield124");
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
