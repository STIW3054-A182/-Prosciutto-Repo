package MainPackage;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.logging.FileHandler;

import COM.STIW3054.project01.*;


public class MainClass {
	

	public static void main( String[] args ) throws IOException{


		PropertiesQueries prop = new PropertiesQueries("url.txt","C:\\Users\\beemo\\Documents\\-Prosciutto-Repo\\src\\main\\java\\MainPackage\\url.txt"
				+ "");		
		BufferedReader reader = new BufferedReader(new FileReader(prop.getPath()));
		//prop.setState("KEDAH");

		CaptureListChessURL chess = new CaptureListChessURL(reader);
		
		int coreCount = Runtime.getRuntime().availableProcessors();
    ExecutorService service = Executors.newFixedThreadPool(coreCount); 
		
   	ArrayList<Player> players = new ArrayList<Player>();
		ChessURL [] curls = chess.getData();
		//KedahName[] kedah = null ;
		//int grandtotal =0;

		LogURL file = new LogURL(new FileHandler("URL Not Exist.log" , true));
		CheckURL [] url = new CheckURL [curls.length];	


//for (int i = 0 ; i < chess.getData().length ; i++) {
//		url[i] = new CheckURL(curls[i] , file);
//		service.execute(url[i]);
//}		
		//getkedah
	  	//for (int i = 0 ; i < chess.getData().length ; i++) {
	  	//	KedahName kedah = new KedahName(curls[i].URLname(),prop.getState());
	  	//	kedah.getkedah();	
	    //}
	  	///getname
	  	//for (int i = 0 ; i < chess.getData().length ; i++) {
	  	//	GetName player = new GetName(curls[i].URLname(),prop.getState());
	  	//	player.getPlayerName();	
	    //}
		
	  	for (int i = 0 ; i < chess.getData().length ; i++) {
	  		JoinTables jt = new JoinTables(curls[i].URLname());
	  		players.addAll(jt.CreateList());
	    }

	  	//showstat
       System.out.printf("| %-12s | %-5s |\n", "State", "Total");
       System.out.printf("| %-12s | %-5s |\n", "------------","-----");
	  	//for (int i = 0 ; i < chess.getData().length ; i++) {
	  		ShowStatistics stat = new ShowStatistics(players);
	  		stat.run();
	   // }
	  	//System.out.println("grandtotal= "+grandtotal);
	  	  	

    //System.out.println(prop.getState());


  	for (int i = 0 ; i < chess.getData().length ; i++) {
			url[i] = new CheckURL(curls[i] , file);
			service.execute(url[i]);
    }

		service.shutdown();
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
    }
	
	
}
