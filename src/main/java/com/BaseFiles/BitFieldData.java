/*--------------------------------------------------------------------------------------------
 * This file is used for maintaining the length of Bitfields per ISO 8583
 * All the positive numbers denote the exact length of the bitfield
 * Negative numbers denote that the bitfield has a variable length
 * -2 denotes that the first two bytes represents the length
 * -3 denotes that the first three bytes represents the length
 * -------------------------------------------------------------------------------------------
 */
package com.BaseFiles;

import java.util.HashMap;
import java.util.Map;

public class BitFieldData {

	public Map<String, Integer> bitfieldLength = new HashMap<String, Integer>();

	public BitFieldData() {
		if (Initializer.getFEPname().equals("HPS")) {
			// Bitfield lengths are based on ISO 8583 - 1993 version
			bitfieldLength.put("BITFIELD1", 8);
			bitfieldLength.put("BITFIELD2", -2);
			bitfieldLength.put("BITFIELD3", 6);
			bitfieldLength.put("BITFIELD4", 12);
			bitfieldLength.put("BITFIELD5", 0);
			bitfieldLength.put("BITFIELD6", 0);
			bitfieldLength.put("BITFIELD7", 10);
			bitfieldLength.put("BITFIELD8", 0);
			bitfieldLength.put("BITFIELD9", 0);
			bitfieldLength.put("BITFIELD10", 0);
			bitfieldLength.put("BITFIELD11", 6);
			bitfieldLength.put("BITFIELD12", 12);
			bitfieldLength.put("BITFIELD13", 0);
			bitfieldLength.put("BITFIELD14", 4);
			bitfieldLength.put("BITFIELD15", 6);
			bitfieldLength.put("BITFIELD16", 0);
			bitfieldLength.put("BITFIELD17", 4);
			bitfieldLength.put("BITFIELD18", 4);
			bitfieldLength.put("BITFIELD19", 3);
			bitfieldLength.put("BITFIELD20", 0);
			bitfieldLength.put("BITFIELD21", 0);
			bitfieldLength.put("BITFIELD22", 12);
			bitfieldLength.put("BITFIELD23", 3);
			bitfieldLength.put("BITFIELD24", 3);
			bitfieldLength.put("BITFIELD25", 4);
			bitfieldLength.put("BITFIELD26", 0);
			bitfieldLength.put("BITFIELD27", 0);
			bitfieldLength.put("BITFIELD28", 6);
			bitfieldLength.put("BITFIELD29", 0);
			bitfieldLength.put("BITFIELD30", 24);
			bitfieldLength.put("BITFIELD31", 0);
			bitfieldLength.put("BITFIELD32", -2);
			bitfieldLength.put("BITFIELD33", 0);
			bitfieldLength.put("BITFIELD34", -2);
			bitfieldLength.put("BITFIELD35", -2);
			bitfieldLength.put("BITFIELD36", 0);
			bitfieldLength.put("BITFIELD37", 12);
			bitfieldLength.put("BITFIELD38", 6);
			bitfieldLength.put("BITFIELD39", 3);
			bitfieldLength.put("BITFIELD40", 0);
			bitfieldLength.put("BITFIELD41", 8);
			bitfieldLength.put("BITFIELD42", 15);
			bitfieldLength.put("BITFIELD43", -2);
			bitfieldLength.put("BITFIELD44", -2);
			bitfieldLength.put("BITFIELD45", -2);
			bitfieldLength.put("BITFIELD46", -3);
			bitfieldLength.put("BITFIELD47", 0);
			bitfieldLength.put("BITFIELD48", -3);
			bitfieldLength.put("BITFIELD49", 3);
			bitfieldLength.put("BITFIELD50", 3);
			bitfieldLength.put("BITFIELD51", 0);
			bitfieldLength.put("BITFIELD52", 8);
			bitfieldLength.put("BITFIELD53", -2);
			bitfieldLength.put("BITFIELD54", -3);
			bitfieldLength.put("BITFIELD55", -3);
			bitfieldLength.put("BITFIELD56", -2);
			bitfieldLength.put("BITFIELD57", 0);
			bitfieldLength.put("BITFIELD58", -2);
			bitfieldLength.put("BITFIELD59", -3);
			bitfieldLength.put("BITFIELD60", 0);
			bitfieldLength.put("BITFIELD61", 0);
			bitfieldLength.put("BITFIELD62", -3);
			bitfieldLength.put("BITFIELD63", -3);
			bitfieldLength.put("BITFIELD64", 0);
			bitfieldLength.put("BITFIELD65", 0);
			bitfieldLength.put("BITFIELD66", 0);
			bitfieldLength.put("BITFIELD67", 0);
			bitfieldLength.put("BITFIELD68", 0);
			bitfieldLength.put("BITFIELD69", 0);
			bitfieldLength.put("BITFIELD70", 0);
			bitfieldLength.put("BITFIELD71", 0);
			bitfieldLength.put("BITFIELD72", -3);
			bitfieldLength.put("BITFIELD73", 6);
			bitfieldLength.put("BITFIELD74", 0);
			bitfieldLength.put("BITFIELD75", 0);
			bitfieldLength.put("BITFIELD76", 0);
			bitfieldLength.put("BITFIELD77", 0);
			bitfieldLength.put("BITFIELD78", 0);
			bitfieldLength.put("BITFIELD79", 0);
			bitfieldLength.put("BITFIELD80", 0);
			bitfieldLength.put("BITFIELD81", 0);
			bitfieldLength.put("BITFIELD82", 0);
			bitfieldLength.put("BITFIELD83", 0);
			bitfieldLength.put("BITFIELD84", 0);
			bitfieldLength.put("BITFIELD85", 0);
			bitfieldLength.put("BITFIELD86", 0);
			bitfieldLength.put("BITFIELD87", 0);
			bitfieldLength.put("BITFIELD88", 0);
			bitfieldLength.put("BITFIELD89", 0);
			bitfieldLength.put("BITFIELD90", 0);
			bitfieldLength.put("BITFIELD91", 0);
			bitfieldLength.put("BITFIELD92", 0);
			bitfieldLength.put("BITFIELD93", 0);
			bitfieldLength.put("BITFIELD94", 0);
			bitfieldLength.put("BITFIELD95", 0);
			bitfieldLength.put("BITFIELD96", -3);
			bitfieldLength.put("BITFIELD97", 16);
			bitfieldLength.put("BITFIELD98", 0);
			bitfieldLength.put("BITFIELD99", 0);
			bitfieldLength.put("BITFIELD100", 0);
			bitfieldLength.put("BITFIELD101", 0);
			bitfieldLength.put("BITFIELD102", -2);
			bitfieldLength.put("BITFIELD103", -2);
			bitfieldLength.put("BITFIELD104", 0);
			bitfieldLength.put("BITFIELD105", 0);
			bitfieldLength.put("BITFIELD106", 0);
			bitfieldLength.put("BITFIELD107", 0);
			bitfieldLength.put("BITFIELD108", 0);
			bitfieldLength.put("BITFIELD109", 0);
			bitfieldLength.put("BITFIELD110", 0);
			bitfieldLength.put("BITFIELD111", 0);
			bitfieldLength.put("BITFIELD112", 0);
			bitfieldLength.put("BITFIELD113", 0);
			bitfieldLength.put("BITFIELD114", 0);
			bitfieldLength.put("BITFIELD115", -3);
			bitfieldLength.put("BITFIELD116", -3);
			bitfieldLength.put("BITFIELD117", -3);
			bitfieldLength.put("BITFIELD118", 0);
			bitfieldLength.put("BITFIELD119", 0);
			bitfieldLength.put("BITFIELD120", 0);
			bitfieldLength.put("BITFIELD121", 0);
			bitfieldLength.put("BITFIELD122", 0);
			bitfieldLength.put("BITFIELD123", -3);
			bitfieldLength.put("BITFIELD124", -3);
			bitfieldLength.put("BITFIELD125", -3);
			bitfieldLength.put("BITFIELD126", -3);
			bitfieldLength.put("BITFIELD127", -3);
			bitfieldLength.put("BITFIELD128", 0);
		} else if (Initializer.getFEPname().equals("X9")) {
			// Bitfield lengths are based on ISO 8583 - 2003 version
			bitfieldLength.put("BITFIELD1", 8);
			bitfieldLength.put("BITFIELD2", -2);
			bitfieldLength.put("BITFIELD3", 6);
			bitfieldLength.put("BITFIELD4", 16);
			bitfieldLength.put("BITFIELD5", 16);
			bitfieldLength.put("BITFIELD6", 16);
			bitfieldLength.put("BITFIELD7", 10);
			bitfieldLength.put("BITFIELD8", 12);
			bitfieldLength.put("BITFIELD9", 8);
			bitfieldLength.put("BITFIELD10", 8);
			bitfieldLength.put("BITFIELD11", 12);
			bitfieldLength.put("BITFIELD12", 14);
			bitfieldLength.put("BITFIELD13", 6);
			bitfieldLength.put("BITFIELD14", 4);
			bitfieldLength.put("BITFIELD15", 8);
			bitfieldLength.put("BITFIELD16", 4);
			bitfieldLength.put("BITFIELD17", 4);
			bitfieldLength.put("BITFIELD18", -3);
			bitfieldLength.put("BITFIELD19", 3);
			bitfieldLength.put("BITFIELD20", 3);
			bitfieldLength.put("BITFIELD21", 22);
			bitfieldLength.put("BITFIELD22", 32);
			bitfieldLength.put("BITFIELD23", 3);
			bitfieldLength.put("BITFIELD24", 3);
			bitfieldLength.put("BITFIELD25", 4);
			bitfieldLength.put("BITFIELD26", 4);
			bitfieldLength.put("BITFIELD27", 54);
			bitfieldLength.put("BITFIELD28", 8);
			bitfieldLength.put("BITFIELD29", 3);
			bitfieldLength.put("BITFIELD30", 32);
			bitfieldLength.put("BITFIELD31", 23);
			bitfieldLength.put("BITFIELD32", -2);
			bitfieldLength.put("BITFIELD33", -2);
			bitfieldLength.put("BITFIELD34", -4);
			bitfieldLength.put("BITFIELD35", -2);
			bitfieldLength.put("BITFIELD36", -3);
			bitfieldLength.put("BITFIELD37", 12);
			bitfieldLength.put("BITFIELD38", 6);
			bitfieldLength.put("BITFIELD39", 4);
			bitfieldLength.put("BITFIELD40", 3);
			bitfieldLength.put("BITFIELD41", 16);
			bitfieldLength.put("BITFIELD42", -2);
			bitfieldLength.put("BITFIELD43", -4);
			bitfieldLength.put("BITFIELD44", -4);
			bitfieldLength.put("BITFIELD45", -2);
			bitfieldLength.put("BITFIELD46", -3);
			bitfieldLength.put("BITFIELD47", -3);
			bitfieldLength.put("BITFIELD48", -3);
			bitfieldLength.put("BITFIELD49", -4);
			bitfieldLength.put("BITFIELD50", -3);
			bitfieldLength.put("BITFIELD51", -3);
			bitfieldLength.put("BITFIELD52", 8);
			bitfieldLength.put("BITFIELD53", -2);
			bitfieldLength.put("BITFIELD54", -3);
			bitfieldLength.put("BITFIELD55", -4);
			bitfieldLength.put("BITFIELD56", -2);
			bitfieldLength.put("BITFIELD57", 3);
			bitfieldLength.put("BITFIELD58", -2);
			bitfieldLength.put("BITFIELD59", -3);
			bitfieldLength.put("BITFIELD60", -3);
			bitfieldLength.put("BITFIELD61", -3);
			bitfieldLength.put("BITFIELD62", -3);
			bitfieldLength.put("BITFIELD63", -3);
			bitfieldLength.put("BITFIELD64", 8);
			bitfieldLength.put("BITFIELD65", 16);
			bitfieldLength.put("BITFIELD66", -3);
			bitfieldLength.put("BITFIELD67", 2);
			bitfieldLength.put("BITFIELD68", 9);
			bitfieldLength.put("BITFIELD69", 40);
			bitfieldLength.put("BITFIELD70", 18);
			bitfieldLength.put("BITFIELD71", -4);
			bitfieldLength.put("BITFIELD72", -4);
			bitfieldLength.put("BITFIELD73", 8);
			bitfieldLength.put("BITFIELD74", 156);
			bitfieldLength.put("BITFIELD75", 90);
			bitfieldLength.put("BITFIELD76", -4);
			bitfieldLength.put("BITFIELD77", -4);
			bitfieldLength.put("BITFIELD78", -4);
			bitfieldLength.put("BITFIELD79", -4);
			bitfieldLength.put("BITFIELD80", -4);
			bitfieldLength.put("BITFIELD81", -4);
			bitfieldLength.put("BITFIELD82", -4);
			bitfieldLength.put("BITFIELD83", -4);
			bitfieldLength.put("BITFIELD84", -4);
			bitfieldLength.put("BITFIELD85", -4);
			bitfieldLength.put("BITFIELD86", -4);
			bitfieldLength.put("BITFIELD87", -4);
			bitfieldLength.put("BITFIELD88", -4);
			bitfieldLength.put("BITFIELD89", -4);
			bitfieldLength.put("BITFIELD90", -4);
			bitfieldLength.put("BITFIELD91", -4);
			bitfieldLength.put("BITFIELD92", -4);
			bitfieldLength.put("BITFIELD93", -2);
			bitfieldLength.put("BITFIELD94", -2);
			bitfieldLength.put("BITFIELD95", -2);
			bitfieldLength.put("BITFIELD96", -3);
			bitfieldLength.put("BITFIELD97", 21);
			bitfieldLength.put("BITFIELD98", 25);
			bitfieldLength.put("BITFIELD99", 11);
			bitfieldLength.put("BITFIELD100", -2);
			bitfieldLength.put("BITFIELD101", -2);
			bitfieldLength.put("BITFIELD102", -2);
			bitfieldLength.put("BITFIELD103", -2);
			bitfieldLength.put("BITFIELD104", -4);
			bitfieldLength.put("BITFIELD105", -4);
			bitfieldLength.put("BITFIELD106", -4);
			bitfieldLength.put("BITFIELD107", -4);
			bitfieldLength.put("BITFIELD108", -4);
			bitfieldLength.put("BITFIELD109", -3);
			bitfieldLength.put("BITFIELD110", -3);
			bitfieldLength.put("BITFIELD111", -4);
			bitfieldLength.put("BITFIELD112", -4);
			bitfieldLength.put("BITFIELD113", -4);
			bitfieldLength.put("BITFIELD114", -4);
			bitfieldLength.put("BITFIELD115", -4);
			bitfieldLength.put("BITFIELD116", -4);
			bitfieldLength.put("BITFIELD117", -4);
			bitfieldLength.put("BITFIELD118", -4);
			bitfieldLength.put("BITFIELD119", -4);
			bitfieldLength.put("BITFIELD120", -4);
			bitfieldLength.put("BITFIELD121", -4);
			bitfieldLength.put("BITFIELD122", -4);
			bitfieldLength.put("BITFIELD123", -4);
			bitfieldLength.put("BITFIELD124", -4);
			bitfieldLength.put("BITFIELD125", -4);
			bitfieldLength.put("BITFIELD126", -4);
			bitfieldLength.put("BITFIELD127", -4);
			bitfieldLength.put("BITFIELD128", 8);
		} else if (Initializer.getFEPname().equals("INCOMM")) {
			// Bitfield lengths are based on ISO 8583 - 1987 version
			bitfieldLength.put("BITFIELD1", 8);
			bitfieldLength.put("BITFIELD2", -2);
			bitfieldLength.put("BITFIELD3", 6);
			bitfieldLength.put("BITFIELD4", 12);
			bitfieldLength.put("BITFIELD5", 12);
			bitfieldLength.put("BITFIELD6", 12);
			bitfieldLength.put("BITFIELD7", 16);
			bitfieldLength.put("BITFIELD8", 8);
			bitfieldLength.put("BITFIELD9", 8);
			bitfieldLength.put("BITFIELD10", 8);
			bitfieldLength.put("BITFIELD11", 12);
			bitfieldLength.put("BITFIELD12", 11);
			bitfieldLength.put("BITFIELD13", 8);
			bitfieldLength.put("BITFIELD14", 4);
			bitfieldLength.put("BITFIELD15", 4);
			bitfieldLength.put("BITFIELD16", 4);
			bitfieldLength.put("BITFIELD17", 8);
			bitfieldLength.put("BITFIELD18", 4);
			bitfieldLength.put("BITFIELD19", 3);
			bitfieldLength.put("BITFIELD20", 3);
			bitfieldLength.put("BITFIELD21", -2);
			bitfieldLength.put("BITFIELD22", 3);
			bitfieldLength.put("BITFIELD23", 3);
			bitfieldLength.put("BITFIELD24", 3);
			bitfieldLength.put("BITFIELD25", 2);
			bitfieldLength.put("BITFIELD26", 4);
			bitfieldLength.put("BITFIELD27", 48);
			bitfieldLength.put("BITFIELD28", -1);
			bitfieldLength.put("BITFIELD29", -1);
			bitfieldLength.put("BITFIELD30", -1);
			bitfieldLength.put("BITFIELD31", -1);
			bitfieldLength.put("BITFIELD32", -2);
			bitfieldLength.put("BITFIELD33", -2);
			bitfieldLength.put("BITFIELD34", -2);
			bitfieldLength.put("BITFIELD35", -2);
			bitfieldLength.put("BITFIELD36", -3);
			bitfieldLength.put("BITFIELD37", 12);
			bitfieldLength.put("BITFIELD38", 20);
			bitfieldLength.put("BITFIELD39", 2);
			bitfieldLength.put("BITFIELD40", 3);
			bitfieldLength.put("BITFIELD41", 8);
			bitfieldLength.put("BITFIELD42", 15);
			bitfieldLength.put("BITFIELD43", 40);
			bitfieldLength.put("BITFIELD44", -2);
			bitfieldLength.put("BITFIELD45", -2);
			bitfieldLength.put("BITFIELD46", -4);
			bitfieldLength.put("BITFIELD47", -3);
			bitfieldLength.put("BITFIELD48", -4);
			bitfieldLength.put("BITFIELD49", 3);
			bitfieldLength.put("BITFIELD50", 3);
			bitfieldLength.put("BITFIELD51", 3);
			bitfieldLength.put("BITFIELD52", 8);
			bitfieldLength.put("BITFIELD53", 16);
			bitfieldLength.put("BITFIELD54", -3);
			bitfieldLength.put("BITFIELD55", -3);
			bitfieldLength.put("BITFIELD56", -3);
			bitfieldLength.put("BITFIELD57", -3);
			bitfieldLength.put("BITFIELD58", -3);
			bitfieldLength.put("BITFIELD59", -3);
			bitfieldLength.put("BITFIELD60", 4);
			bitfieldLength.put("BITFIELD61", -3);
			bitfieldLength.put("BITFIELD62", -4);
			bitfieldLength.put("BITFIELD63", -3);
			bitfieldLength.put("BITFIELD64", 8);
			bitfieldLength.put("BITFIELD65", 1);
			bitfieldLength.put("BITFIELD66", 1);
			bitfieldLength.put("BITFIELD67", 2);
			bitfieldLength.put("BITFIELD68", 3);
			bitfieldLength.put("BITFIELD69", 3);
			bitfieldLength.put("BITFIELD70", 3);
			bitfieldLength.put("BITFIELD71", 4);
			bitfieldLength.put("BITFIELD72", 4);
			bitfieldLength.put("BITFIELD73", 6);
			bitfieldLength.put("BITFIELD74", 10);
			bitfieldLength.put("BITFIELD75", 10);
			bitfieldLength.put("BITFIELD76", 10);
			bitfieldLength.put("BITFIELD77", 10);
			bitfieldLength.put("BITFIELD78", 10);
			bitfieldLength.put("BITFIELD79", 10);
			bitfieldLength.put("BITFIELD80", 10);
			bitfieldLength.put("BITFIELD81", 10);
			bitfieldLength.put("BITFIELD82", 10);
			bitfieldLength.put("BITFIELD83", 12);
			bitfieldLength.put("BITFIELD84", 12);
			bitfieldLength.put("BITFIELD85", 12);
			bitfieldLength.put("BITFIELD86", 16);
			bitfieldLength.put("BITFIELD87", 16);
			bitfieldLength.put("BITFIELD88", 16);
			bitfieldLength.put("BITFIELD89", 16);
			bitfieldLength.put("BITFIELD90", 42);
			bitfieldLength.put("BITFIELD91", 1);
			bitfieldLength.put("BITFIELD92", 2);
			bitfieldLength.put("BITFIELD93", 5);
			bitfieldLength.put("BITFIELD94", 7);
			bitfieldLength.put("BITFIELD95", 42);
			bitfieldLength.put("BITFIELD96", 8);
			bitfieldLength.put("BITFIELD97", -1);
			bitfieldLength.put("BITFIELD98", 25);
			bitfieldLength.put("BITFIELD99", -2);
			bitfieldLength.put("BITFIELD100", -2);
			bitfieldLength.put("BITFIELD101", -2);
			bitfieldLength.put("BITFIELD102", -2);
			bitfieldLength.put("BITFIELD103", -2);
			bitfieldLength.put("BITFIELD104", -2);
			bitfieldLength.put("BITFIELD105", -3);
			bitfieldLength.put("BITFIELD106", -3);
			bitfieldLength.put("BITFIELD107", -3);
			bitfieldLength.put("BITFIELD108", -3);
			bitfieldLength.put("BITFIELD109", -3);
			bitfieldLength.put("BITFIELD110", -4);
			bitfieldLength.put("BITFIELD111", -4);
			bitfieldLength.put("BITFIELD112", 6);
			bitfieldLength.put("BITFIELD113", 6);
			bitfieldLength.put("BITFIELD114", 6);
			bitfieldLength.put("BITFIELD115", -2);
			bitfieldLength.put("BITFIELD116", 28);
			bitfieldLength.put("BITFIELD117", 12);
			bitfieldLength.put("BITFIELD118", -3);
			bitfieldLength.put("BITFIELD119", 6);
			bitfieldLength.put("BITFIELD120", 4);
			bitfieldLength.put("BITFIELD121", -4);
			bitfieldLength.put("BITFIELD122", -3);
			bitfieldLength.put("BITFIELD123", -4);
			bitfieldLength.put("BITFIELD124", -1);
			bitfieldLength.put("BITFIELD125", -3);
			bitfieldLength.put("BITFIELD126", -3);
			bitfieldLength.put("BITFIELD127", -3);
			bitfieldLength.put("BITFIELD128", 8);
		} else if (Initializer.getFEPname().equals("FCB")) {

			// Bitfield lengths are based on ISO 8583 - 1987 version
			bitfieldLength.put("BITFIELD1", 8);
			bitfieldLength.put("BITFIELD2", -2);
			bitfieldLength.put("BITFIELD3", 6);
			bitfieldLength.put("BITFIELD4", 12);
			bitfieldLength.put("BITFIELD5", 12);
			bitfieldLength.put("BITFIELD6", 12);
			bitfieldLength.put("BITFIELD7", 16);
			bitfieldLength.put("BITFIELD8", 8);
			bitfieldLength.put("BITFIELD9", 8);
			bitfieldLength.put("BITFIELD10", 8);
			bitfieldLength.put("BITFIELD11", 6);
			bitfieldLength.put("BITFIELD12", 6);
			bitfieldLength.put("BITFIELD13", 4);
			bitfieldLength.put("BITFIELD14", 4);
			bitfieldLength.put("BITFIELD15", 4);
			bitfieldLength.put("BITFIELD16", 4);
			bitfieldLength.put("BITFIELD17", 8);
			bitfieldLength.put("BITFIELD18", 4);
			bitfieldLength.put("BITFIELD19", 3);
			bitfieldLength.put("BITFIELD20", 3);
			bitfieldLength.put("BITFIELD21", -2);
			bitfieldLength.put("BITFIELD22", 4);
			bitfieldLength.put("BITFIELD23", 3);
			bitfieldLength.put("BITFIELD24", 4);
			bitfieldLength.put("BITFIELD25", 2);
			bitfieldLength.put("BITFIELD26", 4);
			bitfieldLength.put("BITFIELD27", 48);
			bitfieldLength.put("BITFIELD28", -1);
			bitfieldLength.put("BITFIELD29", -1);
			bitfieldLength.put("BITFIELD30", -1);
			bitfieldLength.put("BITFIELD31", -1);
			bitfieldLength.put("BITFIELD32", -2);
			bitfieldLength.put("BITFIELD33", -2);
			bitfieldLength.put("BITFIELD34", -2);
			bitfieldLength.put("BITFIELD35", -2);
			bitfieldLength.put("BITFIELD36", -3);
			bitfieldLength.put("BITFIELD37", 12);
			bitfieldLength.put("BITFIELD38", 6);
			bitfieldLength.put("BITFIELD39", 2);
			bitfieldLength.put("BITFIELD40", 3);
			bitfieldLength.put("BITFIELD41", 8);
			bitfieldLength.put("BITFIELD42", 15);
			bitfieldLength.put("BITFIELD43", 40);
			bitfieldLength.put("BITFIELD44", -2);
			bitfieldLength.put("BITFIELD45", -2);
			bitfieldLength.put("BITFIELD46", -4);
			bitfieldLength.put("BITFIELD47", -3);
			bitfieldLength.put("BITFIELD48", -4);
			bitfieldLength.put("BITFIELD49", 4);
			bitfieldLength.put("BITFIELD50", 3);
			bitfieldLength.put("BITFIELD51", 3);
			bitfieldLength.put("BITFIELD52", 16);
			bitfieldLength.put("BITFIELD53", 20);
			bitfieldLength.put("BITFIELD54", -3);
			bitfieldLength.put("BITFIELD55", -3);
			bitfieldLength.put("BITFIELD56", -3);
			bitfieldLength.put("BITFIELD57", -3);
			bitfieldLength.put("BITFIELD58", -3);
			bitfieldLength.put("BITFIELD59", -3);
			bitfieldLength.put("BITFIELD60", 4);
			bitfieldLength.put("BITFIELD61", -3);
			bitfieldLength.put("BITFIELD62", -4);
			bitfieldLength.put("BITFIELD63", -3);
			bitfieldLength.put("BITFIELD64", 8);
			bitfieldLength.put("BITFIELD65", 1);
			bitfieldLength.put("BITFIELD66", 1);
			bitfieldLength.put("BITFIELD67", 2);
			bitfieldLength.put("BITFIELD68", 3);
			bitfieldLength.put("BITFIELD69", 3);
			bitfieldLength.put("BITFIELD70", 3);
			bitfieldLength.put("BITFIELD71", 4);
			bitfieldLength.put("BITFIELD72", 4);
			bitfieldLength.put("BITFIELD73", 6);
			bitfieldLength.put("BITFIELD74", 10);
			bitfieldLength.put("BITFIELD75", 10);
			bitfieldLength.put("BITFIELD76", 10);
			bitfieldLength.put("BITFIELD77", 10);
			bitfieldLength.put("BITFIELD78", 10);
			bitfieldLength.put("BITFIELD79", 10);
			bitfieldLength.put("BITFIELD80", 10);
			bitfieldLength.put("BITFIELD81", 10);
			bitfieldLength.put("BITFIELD82", 10);
			bitfieldLength.put("BITFIELD83", 12);
			bitfieldLength.put("BITFIELD84", 12);
			bitfieldLength.put("BITFIELD85", 12);
			bitfieldLength.put("BITFIELD86", 16);
			bitfieldLength.put("BITFIELD87", 16);
			bitfieldLength.put("BITFIELD88", 16);
			bitfieldLength.put("BITFIELD89", 16);
			bitfieldLength.put("BITFIELD90", 42);
			bitfieldLength.put("BITFIELD91", 1);
			bitfieldLength.put("BITFIELD92", 2);
			bitfieldLength.put("BITFIELD93", 5);
			bitfieldLength.put("BITFIELD94", 7);
			bitfieldLength.put("BITFIELD95", 42);
			bitfieldLength.put("BITFIELD96", 8);
			bitfieldLength.put("BITFIELD97", -1);
			bitfieldLength.put("BITFIELD98", 25);
			bitfieldLength.put("BITFIELD99", -2);
			bitfieldLength.put("BITFIELD100", -2);
			bitfieldLength.put("BITFIELD101", -2);
			bitfieldLength.put("BITFIELD102", -2);
			bitfieldLength.put("BITFIELD103", -2);
			bitfieldLength.put("BITFIELD104", -2);
			bitfieldLength.put("BITFIELD105", -3);
			bitfieldLength.put("BITFIELD106", -3);
			bitfieldLength.put("BITFIELD107", -3);
			bitfieldLength.put("BITFIELD108", -3);
			bitfieldLength.put("BITFIELD109", -3);
			bitfieldLength.put("BITFIELD110", -4);
			bitfieldLength.put("BITFIELD111", -4);
			bitfieldLength.put("BITFIELD112", 6);
			bitfieldLength.put("BITFIELD113", 6);
			bitfieldLength.put("BITFIELD114", 6);
			bitfieldLength.put("BITFIELD115", -2);
			bitfieldLength.put("BITFIELD116", 28);
			bitfieldLength.put("BITFIELD117", 12);
			bitfieldLength.put("BITFIELD118", -3);
			bitfieldLength.put("BITFIELD119", 6);
			bitfieldLength.put("BITFIELD120", 4);
			bitfieldLength.put("BITFIELD121", -4);
			bitfieldLength.put("BITFIELD122", -3);
			bitfieldLength.put("BITFIELD123", -4);
			bitfieldLength.put("BITFIELD124", -1);
			bitfieldLength.put("BITFIELD125", -3);
			bitfieldLength.put("BITFIELD126", -3);
			bitfieldLength.put("BITFIELD127", -3);
			bitfieldLength.put("BITFIELD128", 8);
		}

	}
}