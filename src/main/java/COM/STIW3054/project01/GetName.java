package COM.STIW3054.project01;

import java.io.IOException;
import java.net.UnknownHostException;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
/**
 * @author Arya Muhammad Bimo
 * @version 1.0
 * @since 2019-05-18
 * 
 */
public class GetName {
	private String url;
	Document doc; 
	String data;
	public GetName(String URL,String name)  {
		this.url = URL;
	}
	public String getPlayerName() throws UnknownHostException {
		try {
			this.doc = Jsoup.connect(url).get();
			Elements des = doc.select(".CRs1");
//	        PropertiesQueries obj = new PropertiesQueries(data, data);
	    	Element titl = doc.selectFirst(".defaultDialog");
	    	String title = doc.title();
	        int scrape = title.indexOf("9");
            String category = title.substring(scrape + 1).replace("(", "").replace(")", "");
	    	Elements rows = des.select("tr");
	    	if (titl != null) {
	    	//data  = titl.text();
	    	for (int i = 1; i < rows.size(); i++) { //first row is the col names so skip it.
	    	    Element row = rows.get(i);
	    	    Elements cols = row.select("td");
                final String rk = row.select("td:nth-of-type(1)").text();
                final String sno = row.select("td:nth-of-type(2)").text();
                final String name = row.select("td:nth-of-type(4)").text();
                final String rtg = row.select("td:nth-of-type(6)").text();
                final String state = row.select("td:nth-of-type(7)").text();
                final String pointer = row.select("td:nth-of-type(8)").text();

                if (name.equals("Rosli Iman Hasif")) {
                    String format = "| %-5s | %-5s | %-35s| %-8s| %-8s| %-8s| %-8s|\n";
                    System.out.format(format, rk, sno, name, rtg, state, pointer,category);
                }
	    	}

	    	}else {
	    		data = "URL DOESN'T HAVE CONTENT";
	    	}
		} catch (IOException uk) {
			System.out.println("Timed Out!");
		}
		return data;
}
}
