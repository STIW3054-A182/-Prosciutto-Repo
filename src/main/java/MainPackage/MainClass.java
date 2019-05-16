package MainPackage;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import COM.STIW3054.project01.*;


public class MainClass {
	

	public static void main( String[] args ) throws FileNotFoundException{

		PropertiesQueries prop = new PropertiesQueries("URL.txt","C:\\Users\\HP-PC\\Desktop\\URL.txt");		
		BufferedReader reader = new BufferedReader(new FileReader(prop.getPath()));

		CaptureListChessURL chess = new CaptureListChessURL(reader);
		
		int coreCount = Runtime.getRuntime().availableProcessors();
        ExecutorService service = Executors.newFixedThreadPool(1); 
		
        chess.scanProcedure();
        
		ChessURL [] curls = chess.getData();
		
		CheckURL [] url = new CheckURL [curls.length];
		
		for (int i = 0 ; i < chess.getData().length ; i++) {
			System.out.println(curls[i].URLname());
			System.out.println(curls[i].retiveURLContent());
		}
		
		service.shutdown();
		
		
	
		
		
		
		
		
		
		
		
		
		
		
		
    }
	
	
}
