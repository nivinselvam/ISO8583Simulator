package com.BaseFiles;

import java.util.Comparator;

public class BitfieldComparator implements Comparator<String>{

	@Override
	public int compare(String o1, String o2) {
		int bitfield1 = Integer.parseInt(o1.substring(8,o1.length()));
		int bitfield2 = Integer.parseInt(o2.substring(8,o2.length()));
		if(bitfield1>bitfield2) {
			return 1;
		}else if(bitfield2>bitfield1) {
			return -1;
		}else {
			return 0;
		}
		
	}
	
}