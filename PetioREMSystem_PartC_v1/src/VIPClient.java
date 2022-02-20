/*
 * VIPClient.java
 * Petio Todorov
 * 18/1/20
 * 
 * A VIP client class to represent VIP clients by a name and an email.
 * Clients are also assigned an ID. The ID is not differentiated from 
 * the id of regular Clients.
 * 
 * VIPClient is a subclass of the Client class.
 * 
 */

public class VIPClient extends Client{
	
	/*
	 * VIPClient Constructor
	 * 
	 * Creates a new VIPClient
	 *
	 * @param name this is the name of the VIP client
	 *
	 * @param email this is the email of the VIP client
	 * 
	 * @param id unique identifier of the client
	 * 
	 */	
	public VIPClient(String name, String email, int id) {
		super(name, email, id);
	}

	//toString()- creates a string which lists the name and id of the VIP client
	public String toString() {
		String s = super.getName() + ", ID: " + String.valueOf(super.getID());
		return s;
	}
	
}
