package COM.STIW3054.project01;
import org.jsoup.nodes.Document;
import java.util.ArrayList;



/**
 * @author Arya Muhammad Bimo
 * @version 1.0
 * @since 2019-05-18
 * Count the total players for each state.
   Count all the players.
 * 
 */

public class ShowStatistics implements Runnable {
	private String url;
	String data;
	Document doc; 
	String statesearch;
	ArrayList<Player> players;
	int grandtotal=0;
	int total;
	public ShowStatistics(ArrayList<Player> players)  {
		this.players = players;
	}
	
	
	/**
	 *  runs the application 
	 */
	public void run() {
		grandtotal+=count("KUALA LUMPUR");
		grandtotal+=count("N.SEMBILAN");
		grandtotal+=count("PULAU PINANG");
		grandtotal+=count("PAHANG");
		grandtotal+=count("PUTRAJAYA");
		grandtotal+=count("PERAK");
		grandtotal+=count("SELANGOR");
		grandtotal+=count("JOHOR");
		grandtotal+=count("KEDAH");
		grandtotal+=count("SARAWAK");
		grandtotal+=count("SABAH");
		grandtotal+=count("MELAKA");
		grandtotal+=count("KELANTAN");
	    System.out.printf("| %-12s | %-5s |\n", "------------","-----");
		String format = "| %-12s | %-6s|\n";
        System.out.format(format,"Grand Total",grandtotal);
	}
	//public 
	
	/**
	 * @param state states the state that is being searched
	 * @return total for the grandtotal to calculate
	 */
	public int count(String state){
		int total = 0;
		String category = "";
	    //for
    	for (int i = 0; i < players.size(); i++) { //first row is the col names so skip it.
    		category = players.get(i).getCategory();
    		statesearch = players.get(i).getState();
            if (statesearch.equals(state)) {
            	total++;
            }
    	}
    	
    	if(total!=0){
    		String format = "| %-12s | %-6s|\n";
            System.out.format(format,state, total);
    	}
    	return total;
	}
	
