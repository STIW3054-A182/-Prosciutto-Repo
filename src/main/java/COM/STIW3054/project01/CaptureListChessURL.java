package COM.STIW3054.project01;

import java.io.BufferedReader;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class CaptureListChessURL implements Scanning {

	BufferedReader urls;
	String url;
	String [] urlsplited ; 
	
	public CaptureListChessURL (BufferedReader kumpulanurls) {
		this.urls = kumpulanurls;
		this.scanProcedure();
	}
	
	@Override
	public void scanProcedure() {
		// TODO Auto-generated method stub
		url = urls.lines().collect(Collectors.joining("\n"));
		urlsplited = url.split("\n");
	}
	
	public ChessURL[] getData(){
		 ChessURL [] n = new ChessURL[urlsplited.length];
		 for (int i = 0 ; i < urlsplited.length ; i++) {
		    n[i] = new ChessURL(urlsplited[i]);
		 }
		 
		 return n;
	}
	

	
	
	
}
