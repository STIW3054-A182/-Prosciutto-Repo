package COM.STIW3054.project01;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class ChessURL implements URLs {

	String url;
	Document doc; 
	String data;
	String Title;
	
	public ChessURL(String URL)  {
		this.url = URL;
	}
	
	//Check URL see if its exist 
	
	@Override
	public boolean CheckURL() {
		try {
		HttpURLConnection.setFollowRedirects(false);
	    HttpURLConnection con = (HttpURLConnection) new URL(url).openConnection();
	    con.setRequestMethod("HEAD");
	    con.setConnectTimeout(60000);
	    con.setReadTimeout(60000);
	    int responseCode = con.getResponseCode();
	    if(responseCode== HttpURLConnection.HTTP_OK){
			return true;
	    }else if (responseCode == HttpURLConnection.HTTP_CLIENT_TIMEOUT){
	    	return false;
	    }else {
			return false;	

	    }
	    }catch (Exception e){		    	
			return false;
	    }
	}
	
	
	//retrive the content of the url , if there isnt any it will return null
	
	@Override
	public String retiveURLContent() {
		if (this.CheckURL()) {
			try {
				this.doc = Jsoup.connect(url).get();
				Elements des = doc.select(".CRs1");
		    	Element titl = doc.selectFirst(".defaultDialog");
		    	if (titl != null) {
		    	data  = titl.text();
		             for (Element row : des.select("tr")) {
		                 Elements tds = row.select("td");
		                 data += "\n"+tds.get(0).text() + " " + tds.get(1).text()+ " " 
		                         + tds.get(2).text()+ " " + tds.get(3).text()+ " " + tds.get(4).text()  
		                         + " " + tds.get(5).text()+ " " + tds.get(6).text()+ " " + tds.get(7).text()+ " " 
		                         + tds.get(8).text()+ " " + tds.get(9).text()+ " " 
		                         + tds.get(10).text();
		                 }
		    	}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
    	
		return data;	
    	
	}
	@Override
	public boolean ValidityURL() {
		return false;
	}
	
	
	//return name of the url 
	
	@Override
	public String URLname() {
		return url;
	}
	
	//return the table title
	
	public String retriveTableTitle() {
		if (this.CheckURL()) {
			try {
				this.doc = Jsoup.connect(url).get();
				Element des = doc.selectFirst(".defaultDialog");
				Title = des.text();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
    	
    	return Title;
	}
	

}
