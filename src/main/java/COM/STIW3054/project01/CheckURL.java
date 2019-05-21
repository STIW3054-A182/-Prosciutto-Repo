
package COM.STIW3054.project01;


public class CheckURL implements Runnable {

	ChessURL url;
	LogURL buff;
	
	public CheckURL(ChessURL url ) {
		// TODO Auto-generated constructor stub
		this.url = url;
	}

	public CheckURL(ChessURL chessURL, LogURL bw) {
		// TODO Auto-generated constructor stub
		this.url = chessURL;
		this.buff = bw;
	}

	
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

