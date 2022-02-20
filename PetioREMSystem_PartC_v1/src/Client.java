/*
 * Client.java
 * Petio Todorov
 * 18/1/20
 * 
 * A simple client class to represent clients by a name and an email.
 * Clients are also assigned an ID.
 * 
 */

public class Client implements Comparable{
	private int clientID;
	
	private String name;
	private String email;
	
	/*
	 * Client Constructor
	 * 
	 * Creates a new Client
	 *
	 * @param name this is the name of the client
	 *
	 * @param email this is the email of the client
	 * 
	 * @param id unique identifier of the client
	 * 
	 */	
	public Client(String name, String email, int id) 
	{
		this.name = name;
		this.email = email;
		clientID = id;
	}

	//returns the name of the client
	public String getName() 
	{
		return name;
	}
	
	//toString()- creates a string which lists the name and id of the client
	public String toString() 
	{
		return name + ", ID: " + String.valueOf(clientID);
	}
	
	//returns the id # of the client
	public int getID()
	{
		return clientID;
	}
	
	//I had to implement Comparable in Client also because all of my structures hold Comparables
	//Clients are compared based on their id #s
	public int compareTo(Object o) {
		Client c = (Client)o;
		if(this.clientID < c.clientID)
		{
			return -1;
		}
		else if(this.clientID == c.clientID)
		{
			return 0;
		}
		else
			return 1;
	}
}
