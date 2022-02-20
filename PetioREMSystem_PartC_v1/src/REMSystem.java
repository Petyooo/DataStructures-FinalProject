/*
 * REMSystem.java
 * Petio Todorov
 * 18/1/20
 * 
 * This is a Real Estate Management System class. It stores various real estate data such as
 * houses, apartments, clients, and VIP Clients. This system is also responsible for assigning IDs
 * to the objects it manages.
 * 
 */

public class REMSystem implements IManagementSystem{
	//counter used for estates and clients
	private int objectCounter = 0; 
	
	/*
	 * I'm using SortedVector for the estates (houses/apts.) 
	 * A tree is better in time complexity for insert/removal O(log(n)), but there
	 * is the question of what happens in terms of
	 * re-organizing the tree, which can become complex
	 * so I am sticking with SortedVector which has O(n) insertion/removal
	 * but still has the same search time as tree O(log(n)) when using binary search.
	 * 
	 * I am sorting estates based on their price. A tree can be based only on
	 * one entity which would probably be the ID numbers so you still need O(n)
	 * to search through all of the nodes in the tree to check if they are within
	 * a certain price range.
	 * 
	 * Don't use LinkedList because it has to use the get method which makes it O(n^2)
	 * 
	 * */
	private SortedVector estates;
	
	/*
	 * The 2 counters below are used to more easily keep track of # of houses vs. # of apts.
	 * otherwise you'd have to loop thru estates and count based on
	 * instanceOf house or apt. They're useful for printHouses and printApt
	 */
	private int houseCounter = 0;
	private int aptCounter = 0;
	
	private SortedVector clients;
	
	/*
	 * The counter below is used to more easily keep track of # of reg. clients vs. # of vip clients.
	 * otherwise you'd have to loop thru clients and count based on
	 * instanceOf reg or vip. They're useful for printRegClient and printVIPClient.
	 * Number of reg. clients is clients.size()-vipClientCounter.
	 */
	private int vipClientCounter = 0;

	private Graph map;
	
	//constructor
	public REMSystem()
	{
		estates = new SortedVector(5);
		clients = new SortedVector(5); 
		map = new Graph();
	}	
	
	/*
	 * Add a new house with given parameters - street where the house is located,
	 * number of bedrooms, number of bathrooms, price per month - to the management system
	 * 
	 * O(n) because you have to shift elements when adding the house to the estates SortedVector
	 * 
	 * Estates are sorted on price.
	 * 
	 * @param street where the house is located
	 *
	 * @param bedrooms number of bedrooms
	 * 
	 * @param bathrooms number of bathroom
	 * 
	 * @param price price per month
	 *
	 * @return ID of the house
	 */
	public int addNewHouse(String street, int bedrooms, int bathrooms, int price)
	{
		objectCounter++;
		House newHouse = new House(street, bedrooms, bathrooms, price, objectCounter);
		estates.addSorted(newHouse);

		houseCounter++;	
		return objectCounter;
	}

	/*
	 * Add a new apartment with given parameters - street where the apartment is located, 
	 * number of bedrooms, number of bathrooms, price per month, indication whether 
	 * there's a lift in the building, floor on which the apartment is, 
	 * number of floors in the building - to the management system
	 *
	 * O(n) because you have to shift elements when adding the apt to the estates SortedVector
	 * 
	 * Estates are sorted on price.
	 * 
	 * @param street where the house is located
	 *
	 * @param bedrooms number of bedrooms
	 * 
	 * @param bathrooms number of bathrooms
	 * 
	 * @param price price per month
	 * 
	 * @param lift is there a lift in the building
	 * 
	 * @param apartmentFloor floor on which is the apartment
	 * 
	 * @param buildingFloors number of floors in the building
	 * 
	 * @return ID of the apartment
	 */
	public int addNewApartment(String street, int bedrooms, int bathrooms, int price, boolean lift, int apartmentFloor,	int buildingFloors)
	{
		objectCounter++;
		Apartment newApt = new Apartment(street, bedrooms, bathrooms, price, lift, apartmentFloor, buildingFloors, objectCounter);
		estates.addSorted(newApt);

		aptCounter++;
		return objectCounter;
	}

	/*
	 * Add a new client with given parameters - name, email address to the
	 * management system
	 * 
	 * O(n) because you have to shift elements when adding the client to the clients SortedVector
	 * 
	 * Clients are sorted on ID
	 *
	 * @param name name of the client
	 * 
	 * @param emailAddress email address of the client
	 *
	 * @return ID of the client
	 */
	public int addRegularClient(String name, String emailAddress)
	{
		objectCounter++;
		Client newClient = new Client(name, emailAddress, objectCounter);
		clients.addSorted(newClient);
		
		return objectCounter;
	}

	/*
	 * Add a new VIP client with given parameters - name, email address to the
	 * management system
	 *
	 * O(n) because you have to shift elements when adding the VIPClient to the clients SortedVector
	 * 
	 * VIP Clients are stored in the same structure as the regular clients, so they are also sorted on ID.
	 * 
	 * @param name name of the client
	 * 
	 * @param emailAddress email address of the client
	 *
	 * @return ID of the VIP client
	 */
	public int addVIPClient(String name, String emailAddress)
	{
		objectCounter++;
		Client newVIPClient = new VIPClient(name, emailAddress, objectCounter);
		clients.addSorted(newVIPClient);

		vipClientCounter++;
		return objectCounter;
	}

	/*
	 * Search for a place based on the price range. Print all the found properties.
	 *
	 * Searching on a SortedVector is O(log(n)) to get the indices but we still have a for loop
	 * to print all of the estates within the price range so complexity is O(n)
	 *
	 * @param minPrice minimal price of a place (house or apartment)
	 * 
	 * @param maxPrice maximal price of a place (house or apartment)
	 */
	public void searchOnPrice(int minPrice, int maxPrice)
	{
		String s = String.format("The estates with a price range between %d and %d are:",
				minPrice, maxPrice);
		System.out.println(s);
		
		int minIndex = estates.binarySearchForPlacement(new Estate(null, 0, 0, minPrice, 0));
		int maxIndex = estates.binarySearchForPlacement(new Estate(null, 0, 0, maxPrice+1, 0));
		
		int count = 0;
		for (int i = minIndex; i < maxIndex; i++)
		{
			System.out.println(estates.get(i).toString()); //this is why you shouldn't use linkedList
			System.out.println();
			count++;
		}
		
		if (count == 0)
		{
			System.out.println("None\n");
		}
	}

	/*
	 * Search for a place based on the minimum number of bedrooms and max price.
	 * Print all the found properties.
	 *
	 * Searching on a SortedVector is O(log(n)) to get the index of the estate with the max price. 
	 * But we still have a for loop to go through all of the estates less than this max price so complexity is O(n)
	 *
	 * @param minBedrooms minimum of bedrooms in the house or apartment
	 * 
	 * @param maxPrice maximal price of a place (house or apartment)
	 */
	public void searchOnBedroomsAndPrice(int minBedrooms, int maxPrice)
	{
		String s = String.format("The estates with a minimum of %d bedrooms and max price of %d are:",
				minBedrooms, maxPrice);
		System.out.println(s);

		int maxIndex = estates.binarySearchForPlacement(new Estate(null, 0, 0, maxPrice+1, 0));
		
		int count = 0;
		for (int i = 0; i < maxIndex; i++)
		{
			Estate curEstate = (Estate)estates.get(i); //for readability
			if(curEstate.getBedrooms() >= minBedrooms)
			{
				System.out.println(curEstate.toString());
				System.out.println();
				count++;
			}
		}
		
		if (count == 0)
		{
			System.out.println("None\n");
		}
	}
	
	/*
	 * Search for a place within a circle, given by center point and radius in kilometers.
	 * Print all the found properties.
	 * 
	 * getEdgesOfCertainWeight() has complexity O(n). During the printing of the edges below,
	 * we have a separate for loop with O(n) added, so in total the complexity is O(n).
	 *
	 * @param street is a center point for the search
	 * 
	 * @param radius is radius in km for which the search is applied
	 */
	public void searchOnDistance(String street, double radius)
	{
		String s = String.format("The estates within a radius of %.1f km from %s are:",
				radius, street);
		System.out.println(s);
		Vector edges = (Vector) map.getEdgesOfCertainWeight(street, radius);
		
		if(edges.size() == 0)
		{
			System.out.println("None\n");
		}
		else {
			for(int i = 0; i < edges.size(); i++)
			{
				System.out.println(edges.get(i));
			}
			System.out.println();
		}
	}

	/*
	 * Request a visit of selected property (house or apartment)
	 *
	 * Searching for the clientIndex is O(log(n)) because we have binary search.
	 * 
	 * Searching for the property based on the ID is O(n) because the estates are
	 * sorted on price, not on ID.
	 * 
	 * Adding a visitor to the BinaryPriorityQueue of this current property is O(1).
	 *
	 * @param client ID of a client who's requesting a visit
	 * 
	 * @param property ID of a property to visit (house or apartment)
	 */
	public void requestVisit(int client, int property)
	{

		//first check if client is in the system
		int clientIndex = clients.binarySearchForIndex(new Client("", "", client));
		   
		if(clientIndex != -1)
		{
			//check if client is VIP or not
			String status;
			Client curClient = (Client)clients.get(clientIndex);
			if(curClient instanceof VIPClient)
				status = "VIP";
			else
				status = "REG";
			
			for(int i = 0; i < estates.size(); i++)
			{
				Estate curEstate = (Estate)estates.get(i);

				if(curEstate.getID() == property)
				{
					curEstate.addVisitor(curClient, status);
					break;
				}
			}
			
		}
	
	}

	/*
	 * Organize visit for each property where 5 or more people requested a visit.
	 * Print the property details and all client names.
	 *
	 * Going through the estates is O(n). If the length of the BPQ is greater than 5,
	 * then we have to remove/print all 5 visitors. Worst case scenario is that
	 * every estate in the list has at least 5 visitors so that would be a total of 5*n,
	 * but in terms of O notation this is reduced to O(n).
	 * 
	 */
	public void organizeVisits()
	{
		for(int i = 0; i < estates.size(); i++)
		{
			Estate curEstate = (Estate)estates.get(i);
			if(curEstate.visitorCount() >= 5)
			{
				System.out.println("Organize a visit for the following estate: ");
				System.out.println(curEstate);
				System.out.println();
				System.out.println("The following clients want to visit this estate: ");
				for(int j = 0; j < 5; j++)
				{
					//pop and print
					System.out.println(curEstate.removeVisitor());
				}
			}
		}
		System.out.println();
	}

	/*
	 * Print all houses in the system. Print number of houses and a summary details
	 * about each one.
	 * 
	 * This method is O(n) because you have to go through every estate in the estates
	 * SortedVector and check if it's an instance of a house.
	 */
	public void printHouses()
	{
		String s = String.format("The number of houses is %d\n", houseCounter);
		System.out.println(s);
		for (int i = 0; i < estates.size(); i++)
		{
			Estate curEstate = (Estate)estates.get(i);
			if(curEstate instanceof House)
			{
				System.out.println(curEstate);
				System.out.println();
			}
		}
	}

	/*
	 * Print all apartments in the system. Print number of apartments and a summary
	 * details about each one.
	 * 
	 * This method is O(n) because you have to go through every estate in the estates
	 * SortedVector and check if it's an instance of an apartment.
	 */
	public void printApartments()
	{
		String s = String.format("The number of apartments is %d\n", aptCounter);
		System.out.println(s);
		for (int i = 0; i < estates.size(); i++)
		{
			Estate curEstate = (Estate)estates.get(i);
			if(curEstate instanceof Apartment)
			{
				System.out.println(curEstate);
				System.out.println();
			}
		}		
	}

	/*
	 * Print all regular clients. Print number of clients and a summary details
	 * about each one.
	 * 
	 * This method is O(n) because you have to go through every client in the clients
	 * SortedVector and check if it's an instance of a regular client.
	 */
	public void printRegularClients()
	{
		String s;
		s = String.format("The number of Regular Clients is: %d. They are:", clients.size()-vipClientCounter);
		System.out.println(s);
		for(int i = 0; i < clients.size(); i++)
		{
			Client curClient = (Client) clients.get(i);
			if(!(curClient instanceof VIPClient)) //if it's not an instance of VIPClient then it's a reg client
			{
				System.out.println(curClient);
			}
		}
		System.out.println();
	}

	/*
	 * Print all VIP clients. Print number of VIP clients and a summary details
	 * about each one.
	 * 
	 * This method is O(n) because you have to go through every client in the clients
	 * SortedVector and check if it's an instance of a VIP client.
	 */
	public void printVIPClient()
	{
		String s;
		s = String.format("The number of VIP Clients is: %d. They are:", vipClientCounter);
		System.out.println(s);
		for(int i = 0; i < clients.size(); i++)
		{
			Client curClient = (Client) clients.get(i);
			if(curClient instanceof VIPClient)
			{
				System.out.println(curClient);
			}
		}
		System.out.println();
	}

	/*
	 * Adds a street into the system
	 * 
	 * This is O(n) as per my implementation of the addNode method in the Graph class.
	 * 
	 * @param streetName name of the street that is added to the system
	 * 
	 */
	public void addStreet(String streetName)
	{
		map.addNode(streetName);
	}
	
	/*
	 * Adds connection between streets into the system
	 * 
	 * This is O(n) as per my implementation of the addEdge method in the Graph class.
	 *
	 * @param street1 name of the first street that is being connected
	 * 
	 * @param street2 name of the second street that is being connected
	 * 
	 * @param distance is the distance between the streets
	 * 
	 */
	public void connectStreets(String street1, String street2, double distance)
	{
		map.addEdge(street1, street2, distance);
	}
	
}