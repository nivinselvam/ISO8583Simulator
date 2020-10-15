/*
 * This class is used to load the constant values. 
 * This picks the fep name and loads the property file specific to the FEP.
 * 
 */

package com.BaseFiles;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.apache.log4j.Logger;

import com.X9Files.X9Decoder;

public class BaseConstants {
	public String echoMessageLength;
	public String authorizationRequestMTI;
	public String authorizationResponseMTI;
	public String financialSalesRequestMTI;
	public String financialSalesResponseMTI;
	public String financialForceDraftRequestMTI;
	public String financialForceDraftResponseMTI;
	public String reversalRequestMTI;
	public String reversalResponseMTI;
	public String reconciliationRequestMTI;
	public String reconciliationResponseMTI;
	public Integer[] elementsInAuthorizationTransaction;
	public Integer[] elementsInFinancialSalesTransaction;
	public Integer[] elementsInFinancialForceDraftTransaction;
	public Integer[] elementsInReversalTransaction;
	public Integer[] elementsInReconciliationTransaction;
	public List<String> balanceInquiryCodes;
	public List<String> activationRechargeCodes;
	public List<Integer> elementsInHexFormatforFCBTransaction;
	public String nameOfbitfield2;
	public String nameOfbitfield3;
	public String nameOfbitfield4;
	public String nameOfbitfield5;
	public String nameOfbitfield6;
	public String nameOfbitfield7;
	public String nameOfbitfield8;
	public String nameOfbitfield9;
	public String nameOfbitfield10;
	public String nameOfbitfield11;
	public String nameOfbitfield12;
	public String nameOfbitfield13;
	public String nameOfbitfield14;
	public String nameOfbitfield15;
	public String nameOfbitfield16;
	public String nameOfbitfield17;
	public String nameOfbitfield18;
	public String nameOfbitfield19;
	public String nameOfbitfield20;
	public String nameOfbitfield21;
	public String nameOfbitfield22;
	public String nameOfbitfield23;
	public String nameOfbitfield24;
	public String nameOfbitfield25;
	public String nameOfbitfield26;
	public String nameOfbitfield27;
	public String nameOfbitfield28;
	public String nameOfbitfield29;
	public String nameOfbitfield30;
	public String nameOfbitfield31;
	public String nameOfbitfield32;
	public String nameOfbitfield33;
	public String nameOfbitfield34;
	public String nameOfbitfield35;
	public String nameOfbitfield36;
	public String nameOfbitfield37;
	public String nameOfbitfield38;
	public String nameOfbitfield39;
	public String nameOfbitfield40;
	public String nameOfbitfield41;
	public String nameOfbitfield42;
	public String nameOfbitfield43;
	public String nameOfbitfield44;
	public String nameOfbitfield45;
	public String nameOfbitfield46;
	public String nameOfbitfield47;
	public String nameOfbitfield48;
	public String nameOfbitfield49;
	public String nameOfbitfield50;
	public String nameOfbitfield51;
	public String nameOfbitfield52;
	public String nameOfbitfield53;
	public String nameOfbitfield54;
	public String nameOfbitfield55;
	public String nameOfbitfield56;
	public String nameOfbitfield57;
	public String nameOfbitfield58;
	public String nameOfbitfield59;
	public String nameOfbitfield60;
	public String nameOfbitfield61;
	public String nameOfbitfield62;
	public String nameOfbitfield63;
	public String nameOfbitfield64;
	public String nameOfbitfield65;
	public String nameOfbitfield66;
	public String nameOfbitfield67;
	public String nameOfbitfield68;
	public String nameOfbitfield69;
	public String nameOfbitfield70;
	public String nameOfbitfield71;
	public String nameOfbitfield72;
	public String nameOfbitfield73;
	public String nameOfbitfield74;
	public String nameOfbitfield75;
	public String nameOfbitfield76;
	public String nameOfbitfield77;
	public String nameOfbitfield78;
	public String nameOfbitfield79;
	public String nameOfbitfield80;
	public String nameOfbitfield81;
	public String nameOfbitfield82;
	public String nameOfbitfield83;
	public String nameOfbitfield84;
	public String nameOfbitfield85;
	public String nameOfbitfield86;
	public String nameOfbitfield87;
	public String nameOfbitfield88;
	public String nameOfbitfield89;
	public String nameOfbitfield90;
	public String nameOfbitfield91;
	public String nameOfbitfield92;
	public String nameOfbitfield93;
	public String nameOfbitfield94;
	public String nameOfbitfield95;
	public String nameOfbitfield96;
	public String nameOfbitfield97;
	public String nameOfbitfield98;
	public String nameOfbitfield99;
	public String nameOfbitfield100;
	public String nameOfbitfield101;
	public String nameOfbitfield102;
	public String nameOfbitfield103;
	public String nameOfbitfield104;
	public String nameOfbitfield105;
	public String nameOfbitfield106;
	public String nameOfbitfield107;
	public String nameOfbitfield108;
	public String nameOfbitfield109;
	public String nameOfbitfield110;
	public String nameOfbitfield111;
	public String nameOfbitfield112;
	public String nameOfbitfield113;
	public String nameOfbitfield114;
	public String nameOfbitfield115;
	public String nameOfbitfield116;
	public String nameOfbitfield117;
	public String nameOfbitfield118;
	public String nameOfbitfield119;
	public String nameOfbitfield120;
	public String nameOfbitfield121;
	public String nameOfbitfield122;
	public String nameOfbitfield123;
	public String nameOfbitfield124;
	public Integer HeaderStartPoint;
	public Integer HeaderEndPoint;
	public Integer mtiStartPoint;
	public Integer mtiEndPoint;
	public Integer primaryBitmapStartPoint;
	public Integer primaryBitmapEndPoint;
	public Integer primaryBitmapPosition;
	public Integer secondaryBitmapStartPoint;
	public Integer secondaryBitmapEndPoint;
	public Integer secondaryBitmapEndPosition;
	public String sendResponseVariableName;
	public String authorizationResultVariableName;
	public String financialSalesResultVariableName;
	public String financialForceDraftResultVariableName;
	public String reversalResultVariableName;
	public String reconciliationResultVariableName;
	public String declineCodeVariablename;
	public String approvalAmountFieldName;
	public String isHalfApprovalRequiredVariableName;
	public String approvalValue;
	public String declineValue;
	public String partialApprovalValue;
	

	public BaseConstants() {
		loadConstantValues();
	}


	public void loadConstantValues() {
		// This helps to decide if the fep supports echo message or not
		echoMessageLength = Initializer.getConfigurationTracker().getFepPropertiesMap().get("echoMessageLength");

		// Transaction MTI:
		authorizationRequestMTI = Initializer.getConfigurationTracker().getFepPropertiesMap().get("authorizationRequestMTI");
		authorizationResponseMTI = Initializer.getConfigurationTracker().getFepPropertiesMap().get("authorizationResponseMTI");
		financialSalesRequestMTI = Initializer.getConfigurationTracker().getFepPropertiesMap().get("financialSalesRequestMTI");
		financialSalesResponseMTI = Initializer.getConfigurationTracker().getFepPropertiesMap().get("financialSalesResponseMTI");
		financialForceDraftRequestMTI = Initializer.getConfigurationTracker().getFepPropertiesMap().get("financialForceDraftRequestMTI");
		financialForceDraftResponseMTI = Initializer.getConfigurationTracker().getFepPropertiesMap().get("financialForceDraftResponseMTI");
		reversalRequestMTI = Initializer.getConfigurationTracker().getFepPropertiesMap().get("reversalRequestMTI");
		reversalResponseMTI = Initializer.getConfigurationTracker().getFepPropertiesMap().get("reversalResponseMTI");
		reconciliationRequestMTI = Initializer.getConfigurationTracker().getFepPropertiesMap().get("reconciliationRequestMTI");
		reconciliationResponseMTI = Initializer.getConfigurationTracker().getFepPropertiesMap().get("reconciliationResponseMTI");
		// BitFields involved in Transaction
		elementsInAuthorizationTransaction = generateIntegerArrayFromString(Initializer.getConfigurationTracker().getFepPropertiesMap().get("elementsInAuthorizationTransaction"));
		elementsInFinancialSalesTransaction = generateIntegerArrayFromString(Initializer.getConfigurationTracker().getFepPropertiesMap().get("elementsInFinancialSalesTransaction"));
		elementsInFinancialForceDraftTransaction = generateIntegerArrayFromString(Initializer.getConfigurationTracker().getFepPropertiesMap().get("elementsInFinancialForceDraftTransaction"));
		elementsInReversalTransaction = generateIntegerArrayFromString(Initializer.getConfigurationTracker().getFepPropertiesMap().get("elementsInReversalTransaction"));
		elementsInReconciliationTransaction = generateIntegerArrayFromString(Initializer.getConfigurationTracker().getFepPropertiesMap().get("elementsInReconciliationTransaction"));

		// Codes to be validated during transaction
		balanceInquiryCodes = generateArrayListFromString(Initializer.getConfigurationTracker().getFepPropertiesMap().get("balanceInquiryCodes"));
		activationRechargeCodes = generateArrayListFromString(Initializer.getConfigurationTracker().getFepPropertiesMap().get("activationRechargeCodes"));
		// Below constant is FCB fep specific
		elementsInHexFormatforFCBTransaction = new ArrayList<Integer>(
				Arrays.asList(37, 38, 39, 41, 42, 60, 63));

		// BitField Names:
		nameOfbitfield2 = "BITFIELD2";
		nameOfbitfield3 = "BITFIELD3";
		nameOfbitfield4 = "BITFIELD4";
		nameOfbitfield5 = "BITFIELD5";
		nameOfbitfield6 = "BITFIELD6";
		nameOfbitfield7 = "BITFIELD7";
		nameOfbitfield8 = "BITFIELD8";
		nameOfbitfield9 = "BITFIELD9";
		nameOfbitfield10 = "BITFIELD10";
		nameOfbitfield11 = "BITFIELD11";
		nameOfbitfield12 = "BITFIELD12";
		nameOfbitfield13 = "BITFIELD13";
		nameOfbitfield14 = "BITFIELD14";
		nameOfbitfield15 = "BITFIELD15";
		nameOfbitfield16 = "BITFIELD16";
		nameOfbitfield17 = "BITFIELD17";
		nameOfbitfield18 = "BITFIELD18";
		nameOfbitfield19 = "BITFIELD19";
		nameOfbitfield20 = "BITFIELD20";
		nameOfbitfield21 = "BITFIELD21";
		nameOfbitfield22 = "BITFIELD22";
		nameOfbitfield23 = "BITFIELD23";
		nameOfbitfield24 = "BITFIELD24";
		nameOfbitfield25 = "BITFIELD25";
		nameOfbitfield26 = "BITFIELD26";
		nameOfbitfield27 = "BITFIELD27";
		nameOfbitfield28 = "BITFIELD28";
		nameOfbitfield29 = "BITFIELD29";
		nameOfbitfield30 = "BITFIELD30";
		nameOfbitfield31 = "BITFIELD31";
		nameOfbitfield32 = "BITFIELD32";
		nameOfbitfield33 = "BITFIELD33";
		nameOfbitfield34 = "BITFIELD34";
		nameOfbitfield35 = "BITFIELD35";
		nameOfbitfield36 = "BITFIELD36";
		nameOfbitfield37 = "BITFIELD37";
		nameOfbitfield38 = "BITFIELD38";
		nameOfbitfield39 = "BITFIELD39";
		nameOfbitfield40 = "BITFIELD40";
		nameOfbitfield41 = "BITFIELD41";
		nameOfbitfield42 = "BITFIELD42";
		nameOfbitfield43 = "BITFIELD43";
		nameOfbitfield44 = "BITFIELD44";
		nameOfbitfield45 = "BITFIELD45";
		nameOfbitfield46 = "BITFIELD46";
		nameOfbitfield47 = "BITFIELD47";
		nameOfbitfield48 = "BITFIELD48";
		nameOfbitfield49 = "BITFIELD49";
		nameOfbitfield50 = "BITFIELD50";
		nameOfbitfield51 = "BITFIELD51";
		nameOfbitfield52 = "BITFIELD52";
		nameOfbitfield53 = "BITFIELD53";
		nameOfbitfield54 = "BITFIELD54";
		nameOfbitfield55 = "BITFIELD55";
		nameOfbitfield56 = "BITFIELD56";
		nameOfbitfield57 = "BITFIELD57";
		nameOfbitfield58 = "BITFIELD58";
		nameOfbitfield59 = "BITFIELD59";
		nameOfbitfield60 = "BITFIELD60";
		nameOfbitfield61 = "BITFIELD61";
		nameOfbitfield62 = "BITFIELD62";
		nameOfbitfield63 = "BITFIELD63";
		nameOfbitfield64 = "BITFIELD64";
		nameOfbitfield65 = "BITFIELD65";
		nameOfbitfield66 = "BITFIELD66";
		nameOfbitfield67 = "BITFIELD67";
		nameOfbitfield68 = "BITFIELD68";
		nameOfbitfield69 = "BITFIELD69";
		nameOfbitfield70 = "BITFIELD70";
		nameOfbitfield71 = "BITFIELD71";
		nameOfbitfield72 = "BITFIELD72";
		nameOfbitfield73 = "BITFIELD73";
		nameOfbitfield74 = "BITFIELD74";
		nameOfbitfield75 = "BITFIELD75";
		nameOfbitfield76 = "BITFIELD76";
		nameOfbitfield77 = "BITFIELD77";
		nameOfbitfield78 = "BITFIELD78";
		nameOfbitfield79 = "BITFIELD79";
		nameOfbitfield80 = "BITFIELD80";
		nameOfbitfield81 = "BITFIELD81";
		nameOfbitfield82 = "BITFIELD82";
		nameOfbitfield83 = "BITFIELD83";
		nameOfbitfield84 = "BITFIELD84";
		nameOfbitfield85 = "BITFIELD85";
		nameOfbitfield86 = "BITFIELD86";
		nameOfbitfield87 = "BITFIELD87";
		nameOfbitfield88 = "BITFIELD88";
		nameOfbitfield89 = "BITFIELD89";
		nameOfbitfield90 = "BITFIELD90";
		nameOfbitfield91 = "BITFIELD91";
		nameOfbitfield92 = "BITFIELD92";
		nameOfbitfield93 = "BITFIELD93";
		nameOfbitfield94 = "BITFIELD94";
		nameOfbitfield95 = "BITFIELD95";
		nameOfbitfield96 = "BITFIELD96";
		nameOfbitfield97 = "BITFIELD97";
		nameOfbitfield98 = "BITFIELD98";
		nameOfbitfield99 = "BITFIELD99";
		nameOfbitfield100 = "BITFIELD100";
		nameOfbitfield101 = "BITFIELD101";
		nameOfbitfield102 = "BITFIELD102";
		nameOfbitfield103 = "BITFIELD103";
		nameOfbitfield104 = "BITFIELD104";
		nameOfbitfield105 = "BITFIELD105";
		nameOfbitfield106 = "BITFIELD106";
		nameOfbitfield107 = "BITFIELD107";
		nameOfbitfield108 = "BITFIELD108";
		nameOfbitfield109 = "BITFIELD109";
		nameOfbitfield110 = "BITFIELD110";
		nameOfbitfield111 = "BITFIELD111";
		nameOfbitfield112 = "BITFIELD112";
		nameOfbitfield113 = "BITFIELD113";
		nameOfbitfield114 = "BITFIELD114";
		nameOfbitfield115 = "BITFIELD115";
		nameOfbitfield116 = "BITFIELD116";
		nameOfbitfield117 = "BITFIELD117";
		nameOfbitfield118 = "BITFIELD118";
		nameOfbitfield119 = "BITFIELD119";
		nameOfbitfield120 = "BITFIELD120";
		nameOfbitfield121 = "BITFIELD121";
		nameOfbitfield122 = "BITFIELD122";
		nameOfbitfield123 = "BITFIELD123";
		nameOfbitfield124 = "BITFIELD124";

		// Decoding details:
		HeaderStartPoint = Integer.parseInt(Initializer.getConfigurationTracker().getFepPropertiesMap().get("HeaderStartPoint"));
		HeaderEndPoint = Integer.parseInt(Initializer.getConfigurationTracker().getFepPropertiesMap().get("HeaderEndPoint"));
		mtiStartPoint = Integer.parseInt(Initializer.getConfigurationTracker().getFepPropertiesMap().get("mtiStartPoint"));
		mtiEndPoint = Integer.parseInt(Initializer.getConfigurationTracker().getFepPropertiesMap().get("mtiEndPoint"));
		primaryBitmapStartPoint = Integer.parseInt(Initializer.getConfigurationTracker().getFepPropertiesMap().get("primaryBitmapStartPoint"));
		primaryBitmapEndPoint = Integer.parseInt(Initializer.getConfigurationTracker().getFepPropertiesMap().get("primaryBitmapEndPoint"));
		primaryBitmapPosition = Integer.parseInt(Initializer.getConfigurationTracker().getFepPropertiesMap().get("primaryBitmapPosition"));
		secondaryBitmapStartPoint = Integer.parseInt(Initializer.getConfigurationTracker().getFepPropertiesMap().get("secondaryBitmapStartPoint"));
		secondaryBitmapEndPoint = Integer.parseInt(Initializer.getConfigurationTracker().getFepPropertiesMap().get("secondaryBitmapEndPoint"));
		secondaryBitmapEndPosition = Integer.parseInt(Initializer.getConfigurationTracker().getFepPropertiesMap().get("secondaryBitmapEndPosition"));
		
		sendResponseVariableName = "sendResponse";
		authorizationResultVariableName = "authorizationTransactionResponse";
		financialSalesResultVariableName = "financialSalesTransactionResponse";
		financialForceDraftResultVariableName = "financialForceDraftTransactionResponse";
		reversalResultVariableName = "reversalTransactionResponse";
		reconciliationResultVariableName = "reconciliationTransactionResponse";
		declineCodeVariablename = "ValueOfBitfield39Decline";
		approvalAmountFieldName = "valueOfBitfield4";
		isHalfApprovalRequiredVariableName = "isHalfApprovalRequired";
		approvalValue = "Approve";
		declineValue = "Decline";
		partialApprovalValue = "PartiallyApprove";

	}

	// -------------------------------------------------------------------------------------------------------------
	/*
	 * This method is used to take the string from properties file which denotes
	 * elements involved in the transaction and create an array. This takes String
	 * values as input and returns and integer array. Make sure the property file
	 * has the values separated by ",".
	 */
	// -------------------------------------------------------------------------------------------------------------
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

	// -------------------------------------------------------------------------------------------------------------
	/*
	 * This method is used to take the string from properties file which denotes
	 * elements involved in the transaction and create an array list. This takes
	 * String value as input and returns and String array list. Make sure the
	 * property file has the values separated by ",".
	 */
	// -------------------------------------------------------------------------------------------------------------
	public static ArrayList<String> generateArrayListFromString(String elementsInTransaction) {
		elementsInTransaction = elementsInTransaction.replace(" ", "");
		ArrayList<String> elementsInTransactionList = new ArrayList<String>();
		for (String currentString : elementsInTransaction.split(",")) {
			elementsInTransactionList.add(currentString);
		}
		return elementsInTransactionList;
	}

}
