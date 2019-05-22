package COM.STIW3054.project01;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 
 * this class provide the implementation of counting people from array of
 * ChessURL and calcualte based on the country and category
 * 
 * @author Raihankirana 243835
 * @version 1.0
 * @category CountingPoints
 *
 */
public class Statistic {

	ChessURL[] url;
	int points = 0;
	int pointsPerState = 0;
	int pointsTotal = 0;
	String[] cation;
	int i;
	int p;
	/**
	 * @param url Create CountPoints Based on Array of ChessURL
	 */
	public Statistic(ChessURL[] url) {
		this.url = url;
	}

	/**
	 * Calculates the total points and print out the result
	 */
	public void calculate() {
	
		
		int coreCount = Runtime.getRuntime().availableProcessors();
		ExecutorService service = Executors.newFixedThreadPool(coreCount);

		String Location = null;

		String regexcategory = "((U\\d{1,4}G|U\\d{1,4})|JUNIOR)";
		String rgxLocation = "(\\d{1,5}\\s)(\\w+|\\w+\\s\\w+)(\\s\\d,\\d)";

		cation = null;

		Matcher m = null;
		Matcher m1 = null;

		String format = "| %-13s| %-9s| %-8s|\n";

		System.out.println("-----------Counting Statistic-------");
		System.out.println("please wait , calculating states ...");

		for (int g = 0; g < url.length; g++) {
			m = Pattern.compile(regexcategory).matcher(url[g].retiveURLContent());
			m1 = Pattern.compile(rgxLocation).matcher(url[g].retiveURLContent());

			while (m1.find()) {
				Location += ("\n") + m1.group(2).trim();
			}
		}

		cation = Location.split("\n");
		cation = Arrays.stream(cation).distinct().toArray(String[]::new);

		System.out.println("Total Of State : " + cation.length);

		System.out.println("------------------------------------");
		System.out.format(format, "State", "Category", "Total");
		System.out.println("|--------------|----------|---------|");
        
		for ( p = 1; p < cation.length; p++) {
			pointsPerState = 0;

			for ( i = 0; i < url.length; i++) {
				Matcher m4 = Pattern.compile(regexcategory).matcher(url[i].retiveURLContent());
				Matcher m5 = Pattern.compile(rgxLocation).matcher(url[i].retiveURLContent());
				service.submit(new Runnable() {
					
					public void run() {
					    points = 0;
					    while (m4.find()) {
					       String catetemp = m4.group().trim(); 
						   while (m5.find()) {
							   if (cation[p].equals(m5.group(2).trim())) {
								 points++;
								 pointsPerState++;
								 pointsTotal++;
							   }

					    	}

						   
						   if( !(points == 0)) {
								System.out.format(format, cation[p], catetemp,points);

						   }   
						}
					}
		        }) ;
			}
			System.out.format(format, "", "Total:", pointsPerState);
			System.out.println("|--------------|----------|---------|");

		}
		
		
		
		System.out.println("|-----------------------------------|");
		System.out.format(format, "GRAND TOTAL", "", pointsTotal);
		service.shutdown();

	}

}
