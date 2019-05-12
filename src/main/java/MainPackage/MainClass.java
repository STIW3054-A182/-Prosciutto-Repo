package MainPackage;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;

import COM.STIW3054.project01.*;


public class MainClass {
	

	public static void main( String[] args ) throws FileNotFoundException{

		PropertiesQueries prop = new PropertiesQueries("URL.txt","C:\\Users\\HP-PC\\Desktop\\URL.txt");		
		BufferedReader reader = new BufferedReader(new FileReader(prop.getPath()));

		
		
		
    }
	
	
}
