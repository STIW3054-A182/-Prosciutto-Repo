package COM.STIW3054.project01;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.util.Properties;

public class PropertiesQueries {
	
	private static Properties prop;
	String path;
	String filename;
	String state;
	String top;
	String player;
	OutputStream output;
	
	public PropertiesQueries (String filename , String path) throws FileNotFoundException{
		this.filename = filename;
		this.path = path;
		output = new FileOutputStream("src/main/resources/config.properties");
        prop = new Properties();
        prop.setProperty("path",path);
        prop.setProperty("filename",filename);
	}
	
	public void setState(String country) {
		this.state = country;
        prop.setProperty("state",state);
	}
    public void setTopPlayer(String numberTop) {
		this.top = numberTop;
        prop.setProperty("top",top);
	}
    public void setPlayername(String PlayerName) {
    	this.player = PlayerName;
        prop.setProperty("player", player);
    }
    public String getState (){
        return prop.getProperty("state");
	}
    public String getTopPlayer() {
        return prop.getProperty("top");
	}
    public String getPlayername() {
        return prop.getProperty("player");
    }
    public String getPath() {
        return prop.getProperty("path");
	}
    

	
	
	
}
