
package COM.STIW3054.project01;
import java.io.IOException;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

/**
 * 
 * this class provide creating Log file whenever theres invalid link in CheckURL
 * @author Raihankirana 243835
 * 
 *
 */
public class LogURL {
	
	Logger LOGGER = Logger.getLogger(LogURL.class.getName());
	FileHandler write = null;
	
	public LogURL(FileHandler write) {
		this.write = write;
	}
	
	public void WriteLog(String URL) {
	
		LOGGER = Logger.getLogger(" ");
		write.setFormatter(new SimpleFormatter());
    	LOGGER.addHandler(write);
		
		LOGGER.info("\n"+URL +" ------> not exist");
		
	}

}

