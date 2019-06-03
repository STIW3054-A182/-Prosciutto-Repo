package MainPackage;
 
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.OutputStream;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.Properties;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.logging.FileHandler;

import COM.STIW3054.project01.*;

/**
 * 
 * this class will calling all the classes in order to execute all the task 

 *
 */
public class MainClass{

	private static Properties prop = new Properties();
	private static ChessURL[] curls;
	private static CaptureListChessURL chess;
	private static BufferedReader reader;
	private static LogURL file;
	private static ArrayList<Player> players = new ArrayList<Player>();

	public static void main(String[] args) throws IOException {
		
	Thread currThread = Thread.currentThread();
        file = new LogURL(new FileHandler("URLdoesntcontainplayers.log",true));
        SetProperties();
        Retrivedata();
        System.out.println("-----------------------------------------------------------------------------------------------------");
        TotalPlayers m = new TotalPlayers (curls, file);
        m.calculate();
        CheckkingURL();
        CountPlayer();
        jointables();
	showStatistic();
	playersfromKEDAH();
	TOP3();
	winningpoints();
	PlayerResult();
		

	}

	/**
	 * setting up properties
	 */
	public static void SetProperties() {
		try (OutputStream output = new FileOutputStream("src/main/resources/config.properties")) {

			// set the properties value
			prop.setProperty("state", "KEDAH");
			prop.setProperty("playerName", "Rosli Iman Hasif");
			prop.setProperty("topPlayer3", "3");
			prop.setProperty("topPlayer2", "2");
			prop.setProperty("topPlayer1", "1");
			prop.setProperty("path",
					"src/main/java/MainPackage/url.txt");

			// save properties to project root folder
			prop.store(output, null);

		} catch (IOException io) {
			io.printStackTrace();
		}
	}

	/**
	 * 
	 * reading data from text file
	 * 
	 * @throws FileNotFoundException
	 */
	public static void Retrivedata() throws FileNotFoundException {
		reader = new BufferedReader(new FileReader(prop.getProperty("path")));
		chess = new CaptureListChessURL(reader);
		curls = chess.getData();
	}

	/**
	 * 
	 * checking each url using runnable
	 * 
	 * @throws SecurityException
	 * @throws IOException
	 */
	public static void CheckkingURL() throws SecurityException, IOException {
		file = new LogURL(new FileHandler("URL Not Exist.log", true));
		int coreCount = Runtime.getRuntime().availableProcessors();
		ExecutorService service = Executors.newFixedThreadPool(coreCount);
		for (int i = 0; i < chess.getData().length; i++) {
			CheckURL check = new CheckURL(curls[i], file);
			service.execute(check);
		}
		service.shutdown();
	}

	//
	/**
	 * for statistics
	 * 
	 * @throws UnknownHostException
	 */
	public static void jointables() throws UnknownHostException {
		for (int i = 0; i < chess.getData().length; i++) {
			JoinTables jt = new JoinTables(curls[i].URLname());
			players.addAll(jt.CreateList());
		}

	}

	/**
	 * Display statistics
	 * 
	 * 
	 */
	public static void showStatistic() {
		System.out.printf("| %-12s | %-5s |\n", "State", "Total");
		System.out.printf("| %-12s | %-5s |\n" , "------------", "-----");
		// for (int i = 0 ; i < chess.getData().length ; i++) {
		ShowStatistics stat = new ShowStatistics(players);
		stat.run();
		Statistic anotherstats = new Statistic(curls);
		anotherstats.calculate();
	}
	/**
	 * Count the number of players:
	 * 
	 * @throws java.rmi.UnknownHostException
	 */
	public static void CountPlayer() throws java.rmi.UnknownHostException {
		for (int i = 0 ; i < chess.getData().length ; i++) {
		     CountPlayers count = new CountPlayers(curls[i].URLname(),prop.getProperty("state"));
		     count.getTop(); 
		}
		
	}


	/**
	 * 
	 * Display all players from KEDAH:
	 * 
	 * 
	 * @throws UnknownHostException
	 */
	public static void playersfromKEDAH() throws UnknownHostException {
		for (int i = 0; i < chess.getData().length; i++) {
			KedahName state = new KedahName(curls[i].URLname(), prop.getProperty("state"));
			state.getkedah();
		}
	}

	/**
	 * 
	 * Display all TOP 3 players from each category.
	 * 
	 * @throws java.rmi.UnknownHostException
	 */
	public static void TOP3() throws java.rmi.UnknownHostException {
		for (int i = 0; i < chess.getData().length; i++) {
			TopThreeFetcher top = new TopThreeFetcher(curls[i].URLname(), prop.getProperty("topPlayer1"),
					prop.getProperty("topPlayer2"), prop.getProperty("topPlayer3"));
			top.getTop();
		}
	}

	/**
	 * 
	 * Count the winning points:
	 * 
	 */
	public static void winningpoints() {
		CountPoints countplay = new CountPoints(curls);
		countplay.calculate();

	}

	/**
	 * 
	 * Display a player result:
	 * 
	 * @throws UnknownHostException
	 */
	public static void PlayerResult() throws UnknownHostException {
		for (int i = 0; i < chess.getData().length; i++) {
			GetName name = new GetName(curls[i].URLname(),prop.getProperty("playerName"));
			name.getPlayerName();
		}

	}

}
