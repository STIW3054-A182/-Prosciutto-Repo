package COM.STIW3054.project01;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CountPoints {

	ChessURL[] url;

	public static double points = 0;
	public static double pointsPerState = 0;
	public static double pointsTotal = 0;
	public static int p ;
	public static String [] cation;
	
	public CountPoints(ChessURL[] url) {
		this.url = url;
		
	}

	public void calculate() {
		int coreCount = Runtime.getRuntime().availableProcessors();
        ExecutorService service = Executors.newFixedThreadPool(coreCount); 
        
		String Location = null;
		
		String regexcategory  =  "((U\\d{1,4}G|U\\d{1,4})|JUNIOR)";
	    String rgxLocation = "(\\d{1,5}\\s)(\\w+|\\w+\\s\\w+)(\\s\\d,\\d)";
	    
		cation = null;

	    Matcher m1 = null;
	    Matcher m = null;
	    
        String format ="| %-13s| %-9s| %-8s|\n";

	     System.out.println("-----------Counting Points----------");
	     System.out.println("please wait , calculating states ...");

	    
	    for (int i = 0 ; i < url.length ; i++) {
			m = Pattern.compile(regexcategory).matcher(url[i].retiveURLContent());
		    m1 = Pattern.compile(rgxLocation).matcher(url[i].retiveURLContent());
		    
		    while (m1.find()) {
			  Location += ("\n")+m1.group(2).trim();
			}
		 }
		
	     cation = Location.split("\n");
	     cation = Arrays.stream(cation).distinct().toArray(String[]::new);
	     
	     System.out.println("Total Of State : "+cation.length);


	   
	     System.out.println("------------------------------------");
	     System.out.format(format, "State", "Category" , "Pts");
	     System.out.println("|--------------|----------|---------|");
	     
	    for(p = 1 ; p <cation.length;p++) {
	    pointsPerState = 0;
	    
		for (int i = 0 ; i < url.length ; i++) {
			CountingPointsRunnable run = new CountingPointsRunnable(url[i].retiveURLContent());
			service.execute(run);
		}
		System.out.format(format,"" , "Total:", pointsPerState);
        System.out.println("|--------------|----------|---------|");

	    }
        System.out.println("|-----------------------------------|");
		System.out.format(format,"GRAND TOTAL" , "", pointsTotal);
		service.shutdown();

				
	}

}
