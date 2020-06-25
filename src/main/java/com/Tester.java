package com;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Properties;

public class Tester {

	public static void main(String[] args) {
		
		System.out.println(Initializer.getConverter().zeroPadding("1234", 10));

	}

}
