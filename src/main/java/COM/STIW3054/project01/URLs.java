package COM.STIW3054.project01;

/**
 * 
 * this class provide interface for any kind of URL that want to be fetch beside ChessURL , opening possibilities of extending any kinf of url 
 * @author Raihankirana
 *
 */
public interface URLs {
	
	
	/**
	 * @return return true if the link is correct or active or can be oppened
	 */
	boolean CheckURL();
	/**
	 * @return returrn true if the url is valid containing correct content 
	 */
	boolean ValidityURL();
	/**
	 * @return return raw data of the content in string type
	 */
	String retiveURLContent();
	/**
	 * @return return link of url in string type
	 */
	String URLname();
	
}
