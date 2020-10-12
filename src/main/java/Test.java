import java.io.File;
import java.io.FileInputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import com.BaseFiles.Initializer;

public class Test {

	public static void main(String[] args) throws Exception {
		
		Properties property = new Properties();
		property.load(new FileInputStream(new File("src/main/resources/HPSVariables.properties")));
		
		System.out.println(property.stringPropertyNames());
	}

}
