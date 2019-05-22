
package COM.STIW3054.project01;


/**
 * 
 * this class is for checking the url wheter its exist or not implement the runnable 
 * @author Raihankirana 243835
 * @category URL
 *
 */
public class CheckURL implements Runnable {

	ChessURL url;
	LogURL buff;
	
	/**
	 * @param url Object of ChessURL 
	 */
	public CheckURL(ChessURL url ) {
		// TODO Auto-generated constructor stub
		this.url = url;
	}


	/**
	 * @param chessURL object of ChessURL that suppose already inisiated 
	 * @param bw LogURL that will be generated 
	 */
	public CheckURL(ChessURL chessURL, LogURL bw) {
		// TODO Auto-generated constructor stub
		this.url = chessURL;
		this.buff = bw;
	}

	/* (non-Javadoc)
	 * checking if the url exist using CheckURL method
	 * @see java.lang.Runnable#run()
	 */
	public void run() {
		// TODO Auto-generated method stub
		
		if (url.CheckURL()) {
			System.out.println(url.URLname() + "  exist");
		}else {
			System.out.println(url.URLname() + "  not exist");
			buff.WriteLog(url.URLname());
		}
	}

}

