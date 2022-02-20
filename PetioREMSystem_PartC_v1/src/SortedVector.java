/*
 * SortedVector.java
 * Petio Todorov
 * 18/1/20
 * 
 * An implementation of Sorted vector. All of the elements are added in sorted order into
 * the vector using the addSorted method. 
 * 
 * Binary search is possible only because the elements are sorted in some order.
 * 
 * It does not have addFirst/addLast/set because the user would be able to put the elements
 * out of order.  
 * 
 * I use this data structure throughout my REMSystem program because it allows for binary 
 * search which is O(log(n)). I think the binarySearchForIndex and binarySearchForPlacement
 * methods are pretty cool.
 * 
 */

public class SortedVector implements Comparable
{
	
	private Comparable data[];
	private int count;
	private int maxCapacity;
	
	/*
	 * SortedVector Constructor
	 *  
	 * Creates a new array with a maximum # of elements equal to the parameter capacity.
	 * 
	 * @param capacity the user can specify the capacity (max # of elements in the SortedVector)
	 *
	 */		
	public SortedVector(int capacity)
	{
		data = new Comparable[capacity];
		count = 0;
		maxCapacity = capacity;
	}

	//the length/size (# of elements in the SortedVector)
	public int size()
	{
		return count;
	}
 
	//returns true if the SortedVector has no elements, false otherwise
	public boolean isEmpty()
	{
		return size() == 0;
	}
	
	//the maximum # of elements that can be added to the SortedVector
	public int maximumCapacity()
	{
		return maxCapacity;
	}
	
	//returns true if the number of elements in the SortedVector
	//is the same as the maximum number of elements the SortedVector can hold
	//it returns false otherwise.
	public boolean isFull()
	{
		return size() == maximumCapacity();
	}
	
	/*
	 * get
	 *  
	 * Returns the information (Comparable) at a particular index, "index", of the SortedVector
	 * This is O(1). 
	 *
	 * @param index an integer index of the SortedVector
	 * 
	 */
	public Comparable get(int index) 
	{
		return data[index];
	}

	/*
	 * shiftElementsUp
	 * 
	 * This is a helper function for the addSorted method below. 
	 * It shifts elements up by one position starting from the last element
	 * and going down to the index "start". This method is O(n).
	 * 
	 * @param start the starting index, you want to shift all elements from this index
	 * to the end of the vector up by one position
	 */
	private void shiftElementsUp(int start) //helper function
	{
		for(int i = size(); i > start; i--) 
		{
			data[i] = data[i-1];
		}
	}

	/*
	 * addSorted
	 * 
	 * This is a super important method for SortedVector.
	 * 
	 * This method adds an element at the appropriate index based on the element value
	 * (Comparable item). That is, this element checks in which position the element should
	 * be inserted so that the elements in the vector remain in sorted order. It does
	 * so using the binarySearchForPlacement(Comparable item) method which does a 
	 * binarySearch (O(log(n)) to find the index where the element should be placed.
	 * 
	 * It may be necessary to shift elements up if the SortedVector is not empty.
	 * This is O(n)
	 * 
	 * @param item a comparable value that you want to add in the vector
	 * 
	 */
	public void addSorted(Comparable item) //sorted smallest to largest
	{
		
		if (isEmpty()) 
		{
			data[0] = item;
		}
		else
		{
			if(isFull())
			{
				extendCapacity();
			}
			
			//no need to search if new item is bigger than last, keeps O(1)
			if(data[count-1].compareTo(item) < 0) 
			{
				data[count] = item;
			}
			else
			{
				int position = binarySearchForPlacement(item);
				shiftElementsUp(position); //shift elements from this start position
				data[position] = item;
			}

		}
		
		count++;
	}	
	
	
	/*
	 * binarySearchForIndex
	 * 
	 * This method returns the position of an element in the vector through binary search,
	 * so O(log(n). If the vector is empty or if the element is not in the sorted 
	 * vector, we return -1.
	 * 
	 * If the key has duplicates in the vector, this will return only the first!
	 * 
	 * @param key a comparable that you are searching for in the SortedVector
	 * 
	 */
	public int binarySearchForIndex(Comparable key)
	{
		
		if(isEmpty()) return -1;
		else
		{
			int start = 0;
			int end = count - 1;
			
			while(start <= end)
			{
				int middle = (start + end + 1) / 2;
				if(key.compareTo(data[middle]) < 0) end = middle -1;
				else if(key.compareTo(data[middle]) > 0) start = middle + 1;
				else return middle;
			}

		}
		
		return -1; //if element was not found in the list
		
	}
	
	/*
	 * binarySearchForPlacement
	 * @Comparable key
	 * 
	 * This method returns the position where a new element should be inserted 
	 * into the sorted Vector. It does so via a binary search, O(log(n)), which
	 * narrows down the range within which the new key should be placed.
	 * 
	 * Note- it does not reveal whether the key is already in the vector or not.
	 * If the key is already in the vector, the method will return the next
	 * position in the vector. i.e. we allow for duplicates in the sortedVector.
	 * (e.g. you might have 2 houses with the same price)
	 * 
	 * @param key a comparable that you want to insert into the SortedVector
	 */
	public int binarySearchForPlacement(Comparable key)
	{
		
		if(!isEmpty())
		{
			int start = 0;
			int end = count - 1;
			
			boolean keyIsBigger = false; //just to initialize
			int middle = 0; //just to initialize
			
			while(start <= end)
			{
				middle = (start + end + 1) / 2;
				if(data[middle].compareTo(key) > 0)
				{
					keyIsBigger = false;
					end = middle -1;
				}
				else if(data[middle].compareTo(key) < 0)
				{
					start = middle + 1;
					keyIsBigger = true;
				}
				else return middle;
			}
			
			if(keyIsBigger) return middle+1;
			else return middle;
		}

		return -1; //if list is empty
		
	}
	
	/*
	 * binarySearch
	 * 
	 * This method is very similar to the binarySearchForIndex
	 * but it returns a boolean (true or false) on whether the key is in the
	 * vector, rather than the position the position of the key in the vector
	 * It's complexity is O(log(n). If the element is not in the sorted 
	 * vector, we return false.
	 * 
	 * @param key a comparable that you are searching for in the SortedVector
	 * 
	 */	public boolean binarySearch(Comparable key)
	{
	int start = 0;
	int end = count - 1;
	while(start <= end)
	{
		int middle = (start + end + 1) / 2;
		if(data[middle].compareTo(key) < 0) start = middle + 1;
		else if(data[middle].compareTo(key) > 0) end = middle -1;
		else return true;
	}
	return false;
	}

	//toString()- creates a string which lists the data in the SortedVector
	public String toString()
	{
	
		String vecValues = ""; //note you must use double quotes, not single!!!
		
	
		for(int i = 0; i < size(); i++)
		{			
			vecValues += data[i].toString() + " ";
		}
			
		return ("The sorted vector contains: " + vecValues);
		
	}
	
	//returns the value of the first element in the sorted vector O(1)
	public Comparable getFirst()
	{
			return data[0];
	}

	//returns the value of the last element in the sortted vector O(1)
	//note I must return a Comparable. As so, if the vector is empty
	//I return null; otherwise I get an error. 
	public Comparable getLast()
	{
		if(!isEmpty())
			return data[count-1];
		else
			return null;
	}

	/*
	 * removeLast
	 * 
	 * removes the last element from the sorted vector by fading it out.
	 * order is not affected.
	 * 
	 */
	public void removeLast()
	{
		// data[count] = null; //you actually don't need this
		count--; //this is enough
		
	} 

	/*
	 * extendCapacity
	 *  
	 * If the SortedVector is full you can extend the capacity 
	 * (total # of elements the vector can hold).
	 * 
	 * It works by creating a new array with a maximum capacity of twice the current
	 * SortedVector. The elements from the current SortedVector are then 
	 * copied to the new SortedVector;
	 * 
	 * Note- it does not return anything!!!!!
	 */
	private void extendCapacity()
	{
		
		Comparable data2[] = new Comparable[2 * maximumCapacity()];
		
		for(int i = 0; i < size(); i++)
		{
			data2[i] = get(i);
		}
		
		data = data2;
		maxCapacity = 2 * maximumCapacity(); 
		
		//this func does not return anything!
	}
	
	//compareTo- two SortedVector's are compared based on their size (length)
	public int compareTo(Object o)
	{
		SortedVector cur = (SortedVector)o;
		if(this.size() < cur.size())
			return -1;
		else if(this.size() == cur.size())
			return 0;
		else
			return 1;
	}
	
}