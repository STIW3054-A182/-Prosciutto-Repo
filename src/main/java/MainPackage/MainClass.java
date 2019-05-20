package MainPackage;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.logging.FileHandler;

import COM.STIW3054.project01.*;


public class MainClass {
	

	public static void main( String[] args ) throws IOException{

		PropertiesQueries prop = new PropertiesQueries("URL(2).txt","C:\\Users\\trina\\Desktop\\URL(2).txt");		
		BufferedReader reader = new BufferedReader(new FileReader(prop.getPath()));

		CaptureListChessURL chess = new CaptureListChessURL(reader);
		
		int coreCount = Runtime.getRuntime().availableProcessors();
   ExecutorService service = Executors.newFixedThreadPool(coreCount); 
		
        
		ChessURL [] curls = chess.getData();

		LogURL file = new LogURL(new FileHandler("URL Not Exist.log" , true));
		CheckURL [] url = new CheckURL [curls.length];	

		
  	for (int i = 0 ; i < chess.getData().length ; i++) {
			url[i] = new CheckURL(curls[i] , file);
			service.execute(url[i]);
    }
		service.shutdown();
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
    }
	
	
}
