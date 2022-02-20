/*
 * Estate.java
 * Petio Todorov
 * 18/1/20
 * 
 * An estate class to represent a real estate entity by the street it's on, 
 * its number of bedrooms, bathrooms, and price.
 * 
 * Examples of possible estate types are houses and apartments.
 * 
 */

public class Estate implements Comparable {
	protected int estateID;	//id of a particular estate
	protected int bedrooms, bathrooms;
	protected int price;
	protected String street;

	/*
	 * BinaryPriorityQueue inside each estate! It keeps track 
	 * of all clients who want to visit the estate.
	 * In the Estate constructor below you can see that the BPQ has 2 priorities:
	 * "VIP" and "REG". Whenever someone requests a visit, they need
	 * to specify if the client is VIP or regular (REG).  
	 * 
	 * */
	protected BinaryPriorityQueue visitors;
	
	/*
	 * Estate Constructor
	 * 
	 * Create a new Estate with given parameters - street name, 
	 * number of bedrooms, number of bathrooms, price per month and an ID #
	 *
	 * @param street where the estate is located
	 *
	 * @param bedrooms number of bedrooms
	 * 
	 * @param bathrooms number of bathrooms
	 * 
	 * @param price price per month
	 * 
	 * @param id unique identifier of the apt
	 *
	 */
	public Estate(String street, int bedrooms, int bathrooms, int price, int id)
	{
		this.street = street;
		this.bedrooms = bedrooms;
		this.bathrooms = bathrooms;
		this.price = price;
		estateID = id;
		
		/*
		 * The input order for the BPQ below matters! The first argument (e.g. "VIP")
		 * is set as the high priority and the second argument (e.g. "REG")
		 * is set as the low priority!
		 */
		visitors = new BinaryPriorityQueue("VIP","REG");
	}

	//returns the id # of the estate
	public int getID()
	{
		return estateID;
	}
	
	//toString()- creates a string which lists the data of the estate
	public String toString() {
		String s;
		s = String.format("ID#:%d\nNumber of bedrooms: %d\nNumber of bathrooms: %d\nMonthly rent: %d",
				estateID, bedrooms, bathrooms, price);

		return s;
	}
	
	//returns the price per month of the estate
	public int getPrice()
	{
		return price;
	}
	
	//returns the # of bedrooms of the estate
	public int getBedrooms()
	{
		return bedrooms;
	}
	
	//returns the TOTAL number of clients (both REG and VIP)
	//that are interested in visiting this estate
	public int visitorCount()
	{
		return visitors.size();
	}
	
	/*
	 * addVisitor
	 * 
	 * Pushes a new client into the group of clients who want to visit this estate.
	 * When a client is added to the estate visitors group, it must be specified
	 * whether the client is "VIP" or "REG".
	 * 
	 * @param client a client object
	 * 
	 * @param clientType the user has to specify whether the client is "VIP" or "REG"
	 *
	 */
	public void addVisitor(Client client, String clientType)
	{
		visitors.push(client, clientType);
	}
	
	/*
	 * removeVisitor
	 * 
	 * Removes a visitor from the group/list of people who want to
	 * visit this estate and return the CLIENT object.
	 * 
	 * One of the JAVA assistants helped me with this a lot
	 * because I was only returning the client ID which made it hard to
	 * extract other information about the client. By returning the 
	 * Client object I have access to all of the Client properties
	 * such as name, email, etc. 
	 *
	 */
	public Client removeVisitor()
	{
		return (Client)visitors.pop();
	}
	
	
	/*
	 * Note- all estates are compared based on price.
	 * This fits along with the SortedVector data structure
	 * used to hold estates in my REMSystem because it sorts them based on price.
	 * 
	 */
	public int compareTo(Object o) {
		Estate p = (Estate)o;
		if(this.price < p.price)
		{
			return -1;
		}
		else if(this.price == p.price)
		{
			return 0;
		}
		else
			return 1;
	}
	
}
