package COM.STIW3054.project01;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.rmi.UnknownHostException;
import java.util.logging.FileHandler;
import java.util.regex.Pattern;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import java.util.Properties;
import org.jsoup.Jsoup;

/**
 * @author Dinantio Trinanda
 * @version 1.0
 * @since 2019-05-20
 * 
 */

public class TopThreeFetcher {
	Properties prop = new Properties();
	private String url, top1, top2, top3;
	Document doc; 
	String data;
	static int z = 0;
	static boolean data1 = true;
	static String[] category2 = {"U8","U10","U16","U18","","UxG","U8G","U10G","U16G","U18G","U20"};

	public TopThreeFetcher(String URL,String top3, String top2, String top1)  {
		this.url = URL;
		this.top1 = top1;
		this.top2 = top2;
		this.top3 = top3;
		
	}
	
	public String getTop() throws UnknownHostException {
			
		try {
				this.doc = Jsoup.connect(url).get();
				Elements des = doc.select(".CRs1");
		    	Element titl = doc.selectFirst(".defaultDialog");
		    	String title = doc.title();
		        int scrape = title.indexOf("9");
	            String category = title.substring(scrape + 1).replace("(", "").replace(")", "");
		    	Elements rows = des.select("tr");
		    	
		    	 
		    	
		    	if (titl != null) {
		    	
		    		if(category2[z].equals("UxG")) {
		    			System.out.println();
		    			z++;
		    			
		    		}
		    		else {
		    			System.out.println("Top 3 for " + category2[z]);
		    		}
		    	for (int i = 1; i < rows.size(); i++) { //first row is the col names so skip it.
		    		 
		    	    Element row = rows.get(i);
		    	    Elements cols = row.select("td");
	                final String rk = row.select("td:nth-of-type(1)").text();
	                final String sno = row.select("td:nth-of-type(2)").text();
	                final String name = row.select("td:nth-of-type(4)").text();
	                final String rtg = row.select("td:nth-of-type(6)").text();
	                final String state = row.select("td:nth-of-type(7)").text();
	                final String pointer = row.select("td:nth-of-type(8)").text();
	                

	                if (rk.equals(top1) ||rk.equals(top2)|| rk.equals(top3)) {
	                	
	                    String format = "| %-5s | %-5s | %-35s| %-8s| %-20s| %-8s| %-8s|\n";
	                    System.out.format(format, rk, sno, name, rtg, state, pointer,category);
	                    z++;
	                }
	                
		    	}
		    	z -= 2;
		    	System.out.format("\n");
		    	
		    	

		    	}else {
		    		data = "URL DOESN'T HAVE CONTENT";
		    		
		    		
		    	}
		    	} catch (IOException uk) {
				System.out.print("");
				z = 5;
				
			}
			
			return data;
	}
	
}