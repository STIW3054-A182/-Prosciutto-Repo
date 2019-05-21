package COM.STIW3054.project01;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class StatisticRunnable implements Runnable {

	private String URL;
    String format ="| %-13s| %-9s| %-8s|\n";


	public StatisticRunnable(String URL ) {
		// TODO Auto-generated constructor stub
		this.URL = URL;
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		String regexcategory  =  "((U\\d{1,4}G|U\\d{1,4})|JUNIOR)";
	    String rgxLocation = "(\\d{1,5}\\s)(\\w+|\\w+\\s\\w+)(\\s\\d,\\d)";
		Matcher m = Pattern.compile(regexcategory).matcher(URL);
		Matcher m1 = Pattern.compile(rgxLocation).matcher(URL);
		CountStatistics.points = 0;
	    while (m.find()) {
	       String catetemp = m.group().trim(); //catch the category 

		   while (m1.find()) {
			   if (CountStatistics.cation[CountStatistics.p].equals(m1.group(2).trim())) {
				   CountStatistics.points ++ ;
				   CountStatistics.pointsPerState ++;
				   CountStatistics.pointsTotal ++ ;
			   }

	    	}

		   
		   if( !(CountStatistics.points == 0.0)) {
				System.out.format(format, CountStatistics.cation[CountStatistics.p], catetemp,  CountStatistics.points);

		   }   
		}
	}

}
