/*
 * Apartment.java
 * Petio Todorov
 * 18/1/20
 * 
 * An apartment class to represent an apt by the street name, # of bedrooms,
 * # of bathrooms, price/month, floor # and total # of floors in the building.
 * 
 * It is a subclass of Estate.
 * 
 */


public class Apartment extends Estate {
	private int numFloors;
	private boolean lift;
	private int aptFloor;
	
	/*
	 * Apartment Constructor
	 * 
	 * Create a new apartment with given parameters - street name, 
	 * number of bedrooms, number of bathrooms, price per month, indication whether 
	 * there's a lift in the building, floor on which the apartment is, 
	 * number of floors in the building, and an ID #
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
	 * @param id unique identifier of the apt
	 *
	 * @return ID of the apartment
	 */
	public Apartment(String street, int bedrooms, int bathrooms, int price, 
			boolean lift, int apartmentFloor, int buildingFloors, int id) 
	{
		super(street, bedrooms, bathrooms, price, id);
		this.lift = lift;
		numFloors = buildingFloors;
		aptFloor = apartmentFloor;
	}
	
	//toString()- creates a string which lists the properties of the apt
	public String toString() 
	{
		
		String s, strLift;
		
		if(lift) strLift = "it has a lift.";
		else strLift = "it does not have a lift.";
		
		s = String.format("Apartment:\n%s\nIt is on floor %d out of %d and %s", super.toString(),
				aptFloor, numFloors, strLift);

		return s;
	}

}
