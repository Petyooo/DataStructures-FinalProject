/*
 * DictionaryPair.java
 * Petio Todorov
 * 18/1/20
 * 
 * An implementation of DictionaryPair which is used in Dictionary class.
 * 
 * Note you shouldn't use DictionaryPair outside of the Dictionary class, even though it's public.
 * What you should do instead is return the value stored in the dictionary pair, which is a Comparable!
 * 
 */

public  class  DictionaryPair implements Comparable //you need to be able to compare 2 dpairs
{
    private Comparable key;
	private Comparable value;
	
	/*
	 * DictionaryPair Constructor
	 * 
	 * Creates a new DictionaryPair which consists of a key and a value stored together as one object. 
	 *
	 * @param someKey a comparable indicating a key
	 *
	 * @param someValue a comparable indicating a value associated with that key
	 * 
	 */
	public DictionaryPair (Comparable someKey, Comparable someValue)
	{
		this.key = someKey;
		this.value = someValue;		
	}
	
	//returns the key of this DictionaryPair
	public Comparable getKey()
	{
		return this.key;
	}
	
	//returns the value of this DictionaryPair
	public Comparable getValue()
	{
		return this.value;
	}
	
	//sets the key of this DictionaryPair to a new key
	public void setKey(Comparable newKey)
	{
		this.key = newKey;
	}
	
	//sets the value of this DictionaryPair to a new value
	public void setValue(Comparable newValue)
	{
		this.value = newValue;
	}
	
	//toString()- creates a string which lists the value of the DictionaryPair
	public String toString()
	{
		return value.toString();
	}
	
	//compareTo- two DictionaryPairs are compared based on their key
	//this is useful when adding DictionaryPairs to the Dictionary
	//which is a SortedVector
	public int compareTo(Object o)
	{
		DictionaryPair p = (DictionaryPair)o;
		
		if(this.key.compareTo(p.getKey()) < 0)
		{
			return -1;
		}
		else if(this.key.compareTo(p.getKey()) == 0)
		{
			return 0;
		}
		else
			return 1;
	}
}