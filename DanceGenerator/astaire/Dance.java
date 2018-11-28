package astaire;

/**
 * Dance object represents the dance in the show.
 * 
 * @author Abdullah Hussain
 * @version 27/11/2018
 */

public class Dance {
	
	/**
	 * name of the dance is stored as string
	 */
	private String danceName;
	
	/**
	 * Constructor
	 * @param dance name of the dance
	 */
	public Dance(String dance) {
		danceName = dance;
		
	}
	
	/**
	 * 
	 * @return name of the dance
	 */
	public String getDanceName() {
		return danceName;
	}
	
	/**
	 * 
	 * @param name 			setting the name of the dance.
	 */
	public void setDanceName(String name) {
		danceName = name;
	}
	
	@Override
	public String toString() {
		return danceName;
	}

}
