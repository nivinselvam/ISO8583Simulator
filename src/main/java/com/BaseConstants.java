/*
 * This class is used to load the constant values. 
 * This picks the fep name and loads the property file specific to the FEP.
 * 
 */

package com;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Properties;
import org.apache.log4j.Logger;

public final class BaseConstants {
	final static Logger logger = Logger.getLogger(BaseConstants.class);
	public static Properties p = new Properties();

	static {
		String fepFile = null;
		switch (Initializer.getFEPname()) {
		case "HPS":
			fepFile = "HPSConstants.properties";
			break;
		case "FCB":
			fepFile = "FCBConstants.properties";
			break;
		case "INCOMM":
			fepFile = "IncommConstants.properties";
			break;
		default:
			logger.fatal("This simulator doesnt support the entered FEP name");
		}

		File file = new File(fepFile);

		FileInputStream fis = null;
		try {
			fis = new FileInputStream(file);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		try {
			p.load(fis);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	//-------------------------------------------------------------------------------------------------------------
	// App folder path
	public static final String appFolder = System.getProperty("user.home") + "\\ISO8583Simulator";

	// This helps to decide if the fep supports echo message or not
	public static final String echoMessageLength = p.getProperty("echoMessageLength");

	// Transaction Status
	public static final String authorizationTransactionResponse = p.getProperty("authorizationTransactionResponse");
	public static final String financialSalesTransactionResponse = p.getProperty("financialSalesTransactionResponse");
	public static final String financialForceDraftTransactionResponse = p
			.getProperty("financialForceDraftTransactionResponse");
	public static final String reversalTransactionResponse = p.getProperty("reversalTransactionResponse");
	public static final String reconciliationTransactionResponse = p.getProperty("reconciliationTransactionResponse");
	// Transaction MTI:
	public static final String authorizationRequestMTI = p.getProperty("authorizationRequestMTI");
	public static final String authorizationResponseMTI = p.getProperty("authorizationResponseMTI");
	public static final String financialSalesRequestMTI = p.getProperty("financialSalesRequestMTI");
	public static final String financialSalesResponseMTI = p.getProperty("financialSalesResponseMTI");
	public static final String financialForceDraftRequestMTI = p.getProperty("financialForceDraftRequestMTI");
	public static final String financialForceDraftResponseMTI = p.getProperty("financialForceDraftResponseMTI");
	public static final String reversalRequestMTI = p.getProperty("reversalRequestMTI");
	public static final String reversalResponseMTI = p.getProperty("reversalResponseMTI");
	public static final String reconciliationRequestMTI = p.getProperty("reconsillationRequestMTI");
	public static final String reconciliationResponseMTI = p.getProperty("reconsillationResponseMTI");
	// BitFields involved in Transaction
	public static final Integer[] elementsInAuthorisationTransaction = generateIntegerArrayFromString(
			p.getProperty("elementsInAuthorisationTransaction"));
	public static final Integer[] elementsInFinancialSalesTransaction = generateIntegerArrayFromString(
			p.getProperty("elementsInFinancialSalesTransaction"));
	public static final Integer[] elementsInFinancialForceDraftTransaction = generateIntegerArrayFromString(
			p.getProperty("elementsInFinancialForceDraftTransaction"));
	public static final Integer[] elementsInReversalTransaction = generateIntegerArrayFromString(
			p.getProperty("elementsInReversalTransaction"));
	public static final Integer[] elementsInReconsillationTransaction = generateIntegerArrayFromString(
			p.getProperty("elementsInReconsillationTransaction"));

	// Codes to be validated during transaction
	public static final List<String> balanceInquiryCodes = generateArrayListFromString(
			p.getProperty("balanceInquiryCodes"));
	public static final List<String> activationRechargeCodes = generateArrayListFromString(
			p.getProperty("activationRechargeCodes"));
	// Below constant is FCB fep specific
	public static final List<Integer> elementsInHexFormatforFCBTransaction = new ArrayList<Integer>(
			Arrays.asList(37, 38, 39, 41, 42, 60, 63));

	// BitField Names:
	public static final String nameOfbitfield2 = "BITFIELD2";
	public static final String nameOfbitfield3 = "BITFIELD3";
	public static final String nameOfbitfield4 = "BITFIELD4";
	public static final String nameOfbitfield5 = "BITFIELD5";
	public static final String nameOfbitfield6 = "BITFIELD6";
	public static final String nameOfbitfield7 = "BITFIELD7";
	public static final String nameOfbitfield8 = "BITFIELD8";
	public static final String nameOfbitfield9 = "BITFIELD9";
	public static final String nameOfbitfield10 = "BITFIELD10";
	public static final String nameOfbitfield11 = "BITFIELD11";
	public static final String nameOfbitfield12 = "BITFIELD12";
	public static final String nameOfbitfield13 = "BITFIELD13";
	public static final String nameOfbitfield14 = "BITFIELD14";
	public static final String nameOfbitfield15 = "BITFIELD15";
	public static final String nameOfbitfield16 = "BITFIELD16";
	public static final String nameOfbitfield17 = "BITFIELD17";
	public static final String nameOfbitfield18 = "BITFIELD18";
	public static final String nameOfbitfield19 = "BITFIELD19";
	public static final String nameOfbitfield20 = "BITFIELD20";
	public static final String nameOfbitfield21 = "BITFIELD21";
	public static final String nameOfbitfield22 = "BITFIELD22";
	public static final String nameOfbitfield23 = "BITFIELD23";
	public static final String nameOfbitfield24 = "BITFIELD24";
	public static final String nameOfbitfield25 = "BITFIELD25";
	public static final String nameOfbitfield26 = "BITFIELD26";
	public static final String nameOfbitfield27 = "BITFIELD27";
	public static final String nameOfbitfield28 = "BITFIELD28";
	public static final String nameOfbitfield29 = "BITFIELD29";
	public static final String nameOfbitfield30 = "BITFIELD30";
	public static final String nameOfbitfield31 = "BITFIELD31";
	public static final String nameOfbitfield32 = "BITFIELD32";
	public static final String nameOfbitfield33 = "BITFIELD33";
	public static final String nameOfbitfield34 = "BITFIELD34";
	public static final String nameOfbitfield35 = "BITFIELD35";
	public static final String nameOfbitfield36 = "BITFIELD36";
	public static final String nameOfbitfield37 = "BITFIELD37";
	public static final String nameOfbitfield38 = "BITFIELD38";
	public static final String nameOfbitfield39 = "BITFIELD39";
	public static final String nameOfbitfield40 = "BITFIELD40";
	public static final String nameOfbitfield41 = "BITFIELD41";
	public static final String nameOfbitfield42 = "BITFIELD42";
	public static final String nameOfbitfield43 = "BITFIELD43";
	public static final String nameOfbitfield44 = "BITFIELD44";
	public static final String nameOfbitfield45 = "BITFIELD45";
	public static final String nameOfbitfield46 = "BITFIELD46";
	public static final String nameOfbitfield47 = "BITFIELD47";
	public static final String nameOfbitfield48 = "BITFIELD48";
	public static final String nameOfbitfield49 = "BITFIELD49";
	public static final String nameOfbitfield50 = "BITFIELD50";
	public static final String nameOfbitfield51 = "BITFIELD51";
	public static final String nameOfbitfield52 = "BITFIELD52";
	public static final String nameOfbitfield53 = "BITFIELD53";
	public static final String nameOfbitfield54 = "BITFIELD54";
	public static final String nameOfbitfield55 = "BITFIELD55";
	public static final String nameOfbitfield56 = "BITFIELD56";
	public static final String nameOfbitfield57 = "BITFIELD57";
	public static final String nameOfbitfield58 = "BITFIELD58";
	public static final String nameOfbitfield59 = "BITFIELD59";
	public static final String nameOfbitfield60 = "BITFIELD60";
	public static final String nameOfbitfield61 = "BITFIELD61";
	public static final String nameOfbitfield62 = "BITFIELD62";
	public static final String nameOfbitfield63 = "BITFIELD63";
	public static final String nameOfbitfield64 = "BITFIELD64";
	public static final String nameOfbitfield65 = "BITFIELD65";
	public static final String nameOfbitfield66 = "BITFIELD66";
	public static final String nameOfbitfield67 = "BITFIELD67";
	public static final String nameOfbitfield68 = "BITFIELD68";
	public static final String nameOfbitfield69 = "BITFIELD69";
	public static final String nameOfbitfield70 = "BITFIELD70";
	public static final String nameOfbitfield71 = "BITFIELD71";
	public static final String nameOfbitfield72 = "BITFIELD72";
	public static final String nameOfbitfield73 = "BITFIELD73";
	public static final String nameOfbitfield74 = "BITFIELD74";
	public static final String nameOfbitfield75 = "BITFIELD75";
	public static final String nameOfbitfield76 = "BITFIELD76";
	public static final String nameOfbitfield77 = "BITFIELD77";
	public static final String nameOfbitfield78 = "BITFIELD78";
	public static final String nameOfbitfield79 = "BITFIELD79";
	public static final String nameOfbitfield80 = "BITFIELD80";
	public static final String nameOfbitfield81 = "BITFIELD81";
	public static final String nameOfbitfield82 = "BITFIELD82";
	public static final String nameOfbitfield83 = "BITFIELD83";
	public static final String nameOfbitfield84 = "BITFIELD84";
	public static final String nameOfbitfield85 = "BITFIELD85";
	public static final String nameOfbitfield86 = "BITFIELD86";
	public static final String nameOfbitfield87 = "BITFIELD87";
	public static final String nameOfbitfield88 = "BITFIELD88";
	public static final String nameOfbitfield89 = "BITFIELD89";
	public static final String nameOfbitfield90 = "BITFIELD90";
	public static final String nameOfbitfield91 = "BITFIELD91";
	public static final String nameOfbitfield92 = "BITFIELD92";
	public static final String nameOfbitfield93 = "BITFIELD93";
	public static final String nameOfbitfield94 = "BITFIELD94";
	public static final String nameOfbitfield95 = "BITFIELD95";
	public static final String nameOfbitfield96 = "BITFIELD96";
	public static final String nameOfbitfield97 = "BITFIELD97";
	public static final String nameOfbitfield98 = "BITFIELD98";
	public static final String nameOfbitfield99 = "BITFIELD99";
	public static final String nameOfbitfield100 = "BITFIELD100";
	public static final String nameOfbitfield101 = "BITFIELD101";
	public static final String nameOfbitfield102 = "BITFIELD102";
	public static final String nameOfbitfield103 = "BITFIELD103";
	public static final String nameOfbitfield104 = "BITFIELD104";
	public static final String nameOfbitfield105 = "BITFIELD105";
	public static final String nameOfbitfield106 = "BITFIELD106";
	public static final String nameOfbitfield107 = "BITFIELD107";
	public static final String nameOfbitfield108 = "BITFIELD108";
	public static final String nameOfbitfield109 = "BITFIELD109";
	public static final String nameOfbitfield110 = "BITFIELD110";
	public static final String nameOfbitfield111 = "BITFIELD111";
	public static final String nameOfbitfield112 = "BITFIELD112";
	public static final String nameOfbitfield113 = "BITFIELD113";
	public static final String nameOfbitfield114 = "BITFIELD114";
	public static final String nameOfbitfield115 = "BITFIELD115";
	public static final String nameOfbitfield116 = "BITFIELD116";
	public static final String nameOfbitfield117 = "BITFIELD117";
	public static final String nameOfbitfield118 = "BITFIELD118";
	public static final String nameOfbitfield119 = "BITFIELD119";
	public static final String nameOfbitfield120 = "BITFIELD120";
	public static final String nameOfbitfield121 = "BITFIELD121";
	public static final String nameOfbitfield122 = "BITFIELD122";
	public static final String nameOfbitfield123 = "BITFIELD123";
	public static final String nameOfbitfield124 = "BITFIELD124";
	// BitField Values:
	public static final String valueOfBitfield4 = p.getProperty("valueOfBitfield4");
	public static final String valueOfBitfield37 = p.getProperty("valueOfBitfield37");
	public static final String valueOfBitfield38 = p.getProperty("valueOfBitfield38");
	public static final String ValueOfBitfield39Approval = p.getProperty("ValueOfBitfield39Approval");
	public static final String ValueOfBitfield39Decline = p.getProperty("ValueOfBitfield39Decline");
	public static final String ValueOfBitfield39Partial = p.getProperty("ValueOfBitfield39Partial");
	public static final String ValueOfBitfield39Reversal = p.getProperty("ValueOfBitfield39Reversal");
	public static final String ValueOfBitfield39Reconsillation = p.getProperty("ValueOfBitfield39Reconsillation");
	public static final String valueOfBitfield44 = p.getProperty("valueOfBitfield44");
	public static final String valueOfBitfield48 = p.getProperty("valueOfBitfield48");
	public static final String valueOfBitfield54 = p.getProperty("valueOfBitfield54");
	public static final String valueOfBitfield123 = p.getProperty("valueOfBitfield123");
	// Decoding details:
	public static final Integer HeaderStartPoint = Integer.parseInt(p.getProperty("HeaderStartPoint"));
	public static final Integer HeaderEndPoint = Integer.parseInt(p.getProperty("HeaderEndPoint"));
	public static final Integer mtiStartPoint = Integer.parseInt(p.getProperty("mtiStartPoint"));
	public static final Integer mtiEndPoint = Integer.parseInt(p.getProperty("mtiEndPoint"));
	public static final Integer primaryBitmapStartPoint = Integer.parseInt(p.getProperty("primaryBitmapStartPoint"));
	public static final Integer primaryBitmapEndPoint = Integer.parseInt(p.getProperty("primaryBitmapEndPoint"));
	public static final Integer primaryBitmapPosition = Integer.parseInt(p.getProperty("primaryBitmapPosition"));
	public static final Integer secondaryBitmapStartPoint = Integer
			.parseInt(p.getProperty("secondaryBitmapStartPoint"));
	public static final Integer secondaryBitmapEndPoint = Integer.parseInt(p.getProperty("secondaryBitmapEndPoint"));
	public static final Integer secondaryBitmapEndPosition = Integer
			.parseInt(p.getProperty("secondaryBitmapEndPosition"));
	//-------------------------------------------------------------------------------------------------------------
	/*
	 * This method is used to take the string from properties file which denotes
	 * elements involved in the transaction and create an array. This takes String
	 * values as input and returns and integer array. Make sure the property file
	 * has the values separated by ",".
	 */
	//-------------------------------------------------------------------------------------------------------------
	public static Integer[] generateIntegerArrayFromString(String elementsInTransaction) {
		elementsInTransaction = elementsInTransaction.replaceAll(" ", "");
		Integer[] elementsInTransactionArrayIntegers = new Integer[elementsInTransaction.split(",").length];
		int i = 0;
		for (String currentString : elementsInTransaction.split(",")) {
			elementsInTransactionArrayIntegers[i] = Integer.parseInt(currentString);
			i++;
		}
		return elementsInTransactionArrayIntegers;
	}
	//-------------------------------------------------------------------------------------------------------------
	/*
	 * This method is used to take the string from properties file which denotes
	 * elements involved in the transaction and create an array list. This takes
	 * String value as input and returns and String array list. Make sure the
	 * property file has the values separated by ",".
	 */
	//-------------------------------------------------------------------------------------------------------------
	public static ArrayList<String> generateArrayListFromString(String elementsInTransaction) {
		elementsInTransaction = elementsInTransaction.replace(" ", "");
		ArrayList<String> elementsInTransactionList = new ArrayList<String>();
		for (String currentString : elementsInTransaction.split(",")) {
			elementsInTransactionList.add(currentString);
		}
		return elementsInTransactionList;
	}

}
