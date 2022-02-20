/*
 * House.java
 * Petio Todorov
 * 18/1/20
 * 
 * A house class to represent a house by the street name, number of bedrooms, 
 * bathrooms, and price.
 * 
 * It is a subclass of Estate.
 * 
 */


public class House extends Estate{
	
	/*
	 * House Constructor
	 * 
	 * Create a new house with given parameters - street where the house is located,
	 * number of bedrooms, number of bathrooms, price per month, id #
	 *
	 * @param street where the house is located
	 *
	 * @param bedrooms number of bedrooms
	 * 
	 * @param bathrooms number of bathroom
	 * 
	 * @param price price per month
	 * 
	 * @param id unique identifier of the house
	 *
	 * @return ID of the house
	 */
	public House(String street, int bedrooms, int bathrooms, int price, int id)
	{
		super(street, bedrooms, bathrooms, price, id);
	}
	
	public String toString() {
		String s;
		s = String.format("House:\n%s", super.toString());
				
		return s;
	}
	
}
