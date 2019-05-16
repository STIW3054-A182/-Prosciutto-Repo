package COM.STIW3054.project01;


public class CheckURL implements Runnable {

	ChessURL url;
	
	public CheckURL(ChessURL url) {
		// TODO Auto-generated constructor stub
		this.url = url;
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		if (url.CheckURL()) {
			// THINGS
			System.out.println(url.URLname() + "  exist");

		}else {
			// THINGS
			System.out.println(url.URLname() + "  not exist");
		}
	}

}
