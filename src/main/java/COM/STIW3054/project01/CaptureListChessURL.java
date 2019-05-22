package COM.STIW3054.project01;

import java.io.BufferedReader;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

/**
 * This class will scan the file and and extract the url every line of the file and return into one array of ChessURL
 * @author Raihankirana
 * @category URL
 *
 */
public class CaptureListChessURL implements Scanning {

	/**
	 * Object that contain 
	 */
	BufferedReader urls;
	/**
	 * url link
	 */
	String url;
	/**
	 * Array of url link 
	 */
	String [] urlsplited ; 
	
	/**
	 * @param kumpulanurls buffered that capture the text file that contain urls
	 */
	public CaptureListChessURL (BufferedReader kumpulanurls) {
		this.urls = kumpulanurls;
		this.scanProcedure();
	}
	

	/* (non-Javadoc)
	 * 
	 * scanned from here 
	 * @see COM.STIW3054.project01.Scanning#scanProcedure()
	 */
	public void scanProcedure() {
		// TODO Auto-generated method stub
		url = urls.lines().collect(Collectors.joining("\n"));
		urlsplited = url.split("\n");
	}
	
	/**
	 * @return ChessURL in form of array which contain url link
	 */
	public ChessURL[] getData(){
		 ChessURL [] n = new ChessURL[urlsplited.length];
		 for (int i = 0 ; i < urlsplited.length ; i++) {
		    n[i] = new ChessURL(urlsplited[i]);
		 }
		 
		 return n;
	}
	
	
	
	

	
	
	
}
