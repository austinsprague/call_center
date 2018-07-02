
/**
 * Customer class storing info about the customer
 * @author Sprague
 *
 */
public class Customer {
	/**
	 * Customer Id
	 */
	private String custId;
	/**
	 * Customer first name
	 */
	private String firstName;
	/**
	 * Customer last name
	 */
	private String lastName;
	/**
	 * Customer email
	 */
	private String email;
	/**
	 * Customer phone number
	 */
	private String phoneNum;
	
	/**
	 * Customer constructor
	 * @param custId String ID
	 * @param firstName String first name
	 * @param lastName String last name
	 * @param email String email
	 * @param phoneNum String phone num
	 */
	public Customer(String custId, String firstName, String lastName, String email, String phoneNum) {
		this.custId = custId;
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.phoneNum = phoneNum;
	}
	/**
	 * Get id
	 * @return String of id
	 */
	public String getId() {
		return custId;
	}
	/**
	 * Get first name
	 * @return String id
	 */
	public String getFirstN() {
		return firstName;
	}
	
	/**
	 * Get last name
	 * @return String Lastname
	 */
	public String getLastN() {
		return lastName;
	}
	
	/**
	 * Get email
	 * @return string email
	 */
	public String getEmail() {
		return email;
	}
	
	/**
	 * Get phone number
	 * @return string phone number
	 */
	public String phoneNum() {
		return phoneNum;
	}
	
	/**
	 * Concat string of customer information
	 */
	public String toString() {
		String str = "";
		str += "Customer Number: " + custId + " ";
		str += "First: " + firstName + " ";
		str += "Last: " + lastName + " ";
		str += "Email: " + email + " ";
		str += "Phone: " + phoneNum;
		return str;
	}
}
