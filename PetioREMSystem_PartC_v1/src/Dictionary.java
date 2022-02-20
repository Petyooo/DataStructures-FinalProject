/*
 * Dictionary.java
 * Petio Todorov
 * 18/1/20
 * 
 * An implementation of Dictionary based on SortedVector. I am sorting based on the keys.
 * Insertion is O(n), but searching will be O(log(n)). 
 * 
 * Dictionary was going to play an important role in my REMSystem but I decided to take a step back from it.
 * Currently I have a BinaryPriorityQueue as part of each Estate in order to keep track of the clients who
 * want to visit the estate.
 * 
 * Another possibility I explored previously was to actually have a Vector called Visits in my REMSystem for each
 * estate that has new visitors. Each entry in Visits was going to be a new BinaryPriorityQueue for one particular estate.
 * Then the question came up of how you would associate each estates' BinaryPriorityQueue with the index in the vector Visits?
 * That's where Dictionary comes in- I was going to store the estate id as the key and the index of the BPQ in Visits as the value.
 * From there, if you ever wanted to add another client into the requested visit BinaryPriorityQueue for that estate, you'd first search
 * the Dictionary to see if the estate ID was already there. If it was, you'd get the index of the BPQ in Visits and you could then easily
 * access the BPQ and push the client. If the key was not in the Dictionary then you'd have to add a new BPQ with the client already in it
 * directly to the end of the Visits Vector.
 * 
 *  I found it easier to change my REMSystem to have the BPQ as a property of each estate, but nevertheless Dictionary can be a very
 *  useful structure.
 */

public class Dictionary 
{
	private SortedVector data;
	
	//constructor
	public Dictionary()
	{
		data = new SortedVector(100);
	}
	
	//the length/size (# of elements in the Dictionary)
	public int size() {
		return data.size();
	}
	
	/*
	 * add
	 *  
	 * Adds (key, value) as a Dictionary pair to the Dictionary.
	 * If the key is in the Dictionary already, it overwrites the value currently
	 * associated with the key with the new parameter value. If the key
	 * is not in the dictionary, it creates a new Dictionary pair and adds it
	 * to the dictionary.
	 * 
	 * The values in the Dictionary are sorted based on the key. Finding the key is O(log(n))
	 * but adding the DictionaryPair is O(n) because you have to shift elements.
	 *
	 * @param key the key you want to add to your dictionary
	 *
	 * @param value the value you want to associate to that key
	 */
	public void add(Comparable key, Comparable value)
	{
		int index = findPosition(key);
		if(index == -1) //if key is not in dictionary
		{
			DictionaryPair newPair = new DictionaryPair(key, value);
			data.addSorted(newPair);			
		}
		else
		{
			DictionaryPair curPair = (DictionaryPair) find(index);
			curPair.setValue(value);
		}

	}
	
	/*
	 * findPosition
	 *  
	 * Returns the index of the SortedVector (i.e. Dictionary) that you can find
	 * the Comparable "key".  
	 * 
	 * The values in the Dictionary are sorted based on the key so this method
	 * uses binary search which is is O(log(n))
	 *
	 * @param key the key you want to find in your dictionary
	 *
	 */
	public int findPosition(Comparable key)
	{
		DictionaryPair searchPair = new DictionaryPair(key,null);
		return data.binarySearchForIndex(searchPair); //returns -1 if key is not in dictionary
	}
	
	/*
	 * find
	 *  
	 * Returns the value that corresponds to the Comparable "key". 
	 * 
	 * findPosition returns the index of the key in O(log(n)). Then to access
	 * the entry at this index of the SortedVector is O(1), so in total
	 * this method works in O(log(n)).
	 *
	 * @param key the key you want to find in your dictionary
	 *
	 */
	public Comparable find(Comparable key)
	{
		int index = findPosition(key);
		
		if(index != -1) return ((DictionaryPair) data.get(index)).getValue();
		else return null;
		//returns -1 if index is not in the list
	}
	
	//toString()- creates a string which lists the key/value pairs in the Dictionary
	public String toString()
	{
		String s = "Dictionary:\n";
		for(int i = 0; i < data.size(); i++)
		{
			s += String.valueOf(((DictionaryPair) data.get(i)).getKey()) + " : " +
					String.valueOf(((DictionaryPair) data.get(i)).getValue()) + "\n";
		}
		return s;	
	}
}