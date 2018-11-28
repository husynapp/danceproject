package astaire;

/**
 * Performer object models a dance performer that are in the dance groups
 * @author Abdullah Hussain
 * @version 27-11-2018
 */

public class Performer {
	
	/**
	 * String where the name of the performer will be stored as
	 */
	private String performerName;
	
	/**
	 * constructor
	 */
	public Performer(String name) {
		performerName = name;
	}
	
	/**
	 * 
	 * @return name of the performer
	 */
	public String getPerformerName() {
		
		return performerName;
	}
	
	/**
	 * 
	 * @param name			setting the name of the performer.
	 */
	public void setPerformerName(String name) {
		performerName = name;
	}
	
	@Override
	public String toString() {
		return performerName;
	}

}
