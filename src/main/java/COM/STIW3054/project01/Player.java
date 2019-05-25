package COM.STIW3054.project01;
/**
 * @author Arya Muhammad Bimo
 * @version 1.0
 * @since 2019-05-18
 * Player model class
 * 
 */
public class Player {
	String state;
	String category;
	int total;


	public void setTotal(int total) {
		this.total = total;
	}

	public Player(String state, String category, int total) {
		this.state = state;
		this.category = category;
		this.total = total;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory( String category ) {
		this.category = category;
	}
	
	public int getTotal() {
		return total;
	}
	
	
}
