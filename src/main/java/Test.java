import java.io.File;
import java.io.FileInputStream;
import java.util.Properties;

public class Test {

	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		Properties property = new Properties();
		
		property.load(new FileInputStream(new File("src/main/resources/CommonVariables.properties")));
		
		for(Object key: property.keySet()) {
			System.out.println(key.toString());
		}
	}

}
