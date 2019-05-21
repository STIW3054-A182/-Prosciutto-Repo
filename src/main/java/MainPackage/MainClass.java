package MainPackage;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Arrays;
import java.util.Properties;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.logging.FileHandler;

import COM.STIW3054.project01.*;


public class MainClass {
	

	public static void main( String[] args ) throws IOException{
		Properties prop = new Properties();
		
		
		 try (OutputStream output = new FileOutputStream("src/main/resources/config.properties")) {

	            

	            // set the properties value
	            prop.setProperty("state", "Kedah");
	            prop.setProperty("playerName", "Rosli Iman Hanif");
	            prop.setProperty("topPlayer3", "3");
	            prop.setProperty("topPlayer2", "2");
	            prop.setProperty("topPlayer1", "1");
	            prop.setProperty("path", "C:\\Users\\trina\\Desktop\\URL(2).txt");

	            // save properties to project root folder
	            prop.store(output, null);

	           

	        } catch (IOException io) {
	            io.printStackTrace();
	        }

		BufferedReader reader = new BufferedReader(new FileReader(prop.getProperty("path")));

		CaptureListChessURL chess = new CaptureListChessURL(reader);
		
		int coreCount = Runtime.getRuntime().availableProcessors();
		ExecutorService service = Executors.newFixedThreadPool(coreCount); 
		
        
		ChessURL [] curls = chess.getData();

		LogURL file = new LogURL(new FileHandler("URL Not Exist.log" , true));
		CheckURL [] url = new CheckURL [curls.length];	

		
  	
		
		for (int i = 0 ; i < chess.getData().length ; i++) {
	  		TopThreeFetcher top = new TopThreeFetcher(curls[i].URLname(),prop.getProperty("topPlayer1"),prop.getProperty("topPlayer2"),prop.getProperty("topPlayer3"));
	  		top.getTop();	
}
		
	
		
		
		
		
		
		
		
		
		
		
		
		
    }
	
	
}
