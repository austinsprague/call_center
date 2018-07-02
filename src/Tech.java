/**
 * Tech class to hold all tech information
 * @author Sprague
 *
 */
public class Tech {
	/**
	 * String of Tech ID
	 */
	private String id;
	/**
	 * String of Tech first name
	 */
	private String firstName;
	/**
	 * String of Tech last Name
	 */
	private String lastName;
	/**
	 * String of Tech User Name
	 */
	private String userName;
	/**
	 * String of Tech Schedule
	 */
	private String schedule;

	/**
	 * Tech constructor
	 * 
	 * @param id
	 *            String of Tech Id
	 * @param firstName
	 *            String of Tech first name
	 * @param lastName
	 *            String of Tech last name
	 * @param userName
	 *            String of Tech user name
	 * @param schedule
	 *            String of Tech schedule
	 */
	public Tech(String id, String firstName, String lastName, String userName, String schedule) {
		this.id = id;
		this.firstName = firstName;
		this.lastName = lastName;
		this.userName = userName;
		this.schedule = schedule;
	}

	/**
	 * Get Tech Id
	 * @return String of Id
	 */
	public String getId() {
		return id;
	}
	
	/**
	 * Get String of Tech first
	 * @return String of Tech firsti
	 */
	public String getFirstN() {
		return firstName;
	}

	/**
	 * String of tech last name
	 * @return String lastname
	 */
	public String getLastN() {
		return lastName;
	}
	
	/**
	 * Get tech username
	 * @return string of user name
	 */
	public String getUserName() {
		return userName;
	}

	/**
	 * get tech schedule
	 * @return string schedule
	 */
	public String getSchedule() {
		return schedule;
	}

	/**
	 * Concate Tech Info
	 */
	public String toString() {
		String str = "";
		str += "ID: " + id;
		str += " TECHFIRST:" + firstName;
		str += " TECHLAST:" + lastName;
		str += " TECHusername: " + userName;
		str += " TECHSCHEDULE: " + schedule;
		return str;
	}
}
