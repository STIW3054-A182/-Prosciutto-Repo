package COM.STIW3054.project01;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

/**
 * 
 * This class provide url as object , each object contatin one url link and can retrive the content
 * @author Raihankirana 243835
 * @category URL
 *
 */
public class ChessURL implements URLs {

	/**
	 * url link
	 */
	String url;
	/**
	 * document retrived from link
	 */
	Document doc; 
	/**
	 * data fetch from the content of the website
	 */
	String data;
	/**
	 * the title 
	 */
	String Title;
	
	/**
	 * @param URL create Object of ChessURL based on link URL given 
	 */
	public ChessURL(String URL)  {
		this.url = URL;
	}
	
	//Check URL see if its exist 
	

	/* (non-Javadoc)
	 * 
	 * check if the url exist
	 * @see COM.STIW3054.project01.URLs#CheckURL()
	 */

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
	

	/* (non-Javadoc)
	 * 
	 * retrive the URL content 
	 * @see COM.STIW3054.project01.URLs#retiveURLContent()
	 */
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

		    	}else {
		    		data = "URL DOESN'T HAVE CONTENT";
		    	}
			} catch (IOException e) {
				e.printStackTrace();
			}
			return data;
		}
		return "URL DOESN'T EXIST";
	}

	/* (non-Javadoc)
	 * check to see if the url valid or not 
	 * @see COM.STIW3054.project01.URLs#ValidityURL()
	 */
	public boolean ValidityURL() {
		if (!this.data.equals("null")) {
		    return false;
		}
		else {
			return true;
		}
	}
	
	
	//return name of the url 
	

	/* (non-Javadoc)
	 * 
	 * return url link in string
	 * @see COM.STIW3054.project01.URLs#URLname()
	 */
	public String URLname() {
		return url;
	}
	
	//return the table title
	
	/**
	 * @return string of title table inside the link 
	 */
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
