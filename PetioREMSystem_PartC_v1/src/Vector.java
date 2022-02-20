/*
 * Vector.java
 * Petio Todorov
 * 18/1/20
 * 
 * An implementation of vector (dynamic array).
 * 
 * Note that the contains method works in O(n). You can't use
 * binary search because the vector is not sorted.
 * 
 */

public class Vector implements Comparable
{
	private Comparable data[]; 
	private int count; 
	private int maxCapacity;
	
	/*
	 * Vector Constructor
	 *  
	 * Creates a new array with a maximum # of elements equal to the parameter capacity.
	 * 
	 * @param capacity the user can specify the capacity (max # of elements in the Vector)
	 *
	 */	
	public Vector(int capacity)
	{
		data = new Comparable[capacity];
		count = 0;
		maxCapacity = capacity;
	}

	//the length/size (# of elements in the Vector)
	public int size()
	{
		return count;
	}
 
	//returns true if the Vector has no elements, false otherwise
	public boolean isEmpty()
	{
		return size() == 0;
	}
	
	//the maximum # of elements that can be added to the vector
	public int maximumCapacity()
	{
		return maxCapacity;
	}
	
	//returns true if the number of elements in the vector
	//is the same as the maximum number of elements the vector can hold
	//it returns false otherwise.
	public boolean isFull()
	{
		return size() == maximumCapacity();
	}
	
	/*
	 * get
	 *  
	 * Returns the information (Comparable) at a particular index, "index", of the vector 
	 *
	 * @param index an integer index of the Vector
	 * 
	 */
	public Comparable get(int index)
	{
		return data[index];
	}

	/*
	 * set
	 *  
	 * Replaces the value of an already populated index of the vector
	 * or it adds the value to an index which has not already been populated
	 * and increments the number of items in the vector. 
	 * 
	 * Right now the latter part can cause an error when trying to 
	 * print the elements in the vector. For example if the 
	 * capacity is 10 and you have added elements at indexes 0 to 4, and then
	 * use the set method to add a value at index 6 this will cause a problem
	 * when printing because index 5 still points to null. I could initialize 
	 * all of the elements in between to 0, but depending on what you're
	 * doing that might not be a good idea so for now I'm going to leave it as it is.
	 * 
	 * Note: filling in the elements in between just requires a for loop.
	 *
	 * @param index an integer index of the Vector
	 *
	 * @param obj a comparable value of information that you want to add to the vector
	 * 
	 */
	public void set(int index, Comparable obj)
	{
		if((0 <= index) && (index < size()))
			data[index] = obj;
		else if((index >= size()) && (index < maximumCapacity()))
		{
			data[index] = obj;
			count++;
		}
		
		/*
		 * there is one more case, where if the user sets an element greater
		 * than the capacity we can use the extendCapacity method
		 * but I will not consider it right now.
		 */

	}

	/*
	 * contains
	 * 
	 * This method checks if a certain piece of information (Comparable obj) is
	 * stored in the vector. The complexity is O(n) because you are searching
	 * thru the vector one by one.
	 * 
	 * @param obj a comparable value that you are looking for in the vector
	 */
	public boolean contains(Comparable obj)
	{
		for(int i=0;i<count;i++)
		{
			if(data[i] == obj) return true;
		}
		return false;
	}
	
	/*
	 * shiftElementsUp
	 * 
	 * This is a helper function for the addFirst method below. 
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
	 * addFirst
	 * 
	 * This method adds an element at index 0 of the Vector.
	 * It may be necessary to shift elements up if the Vector is not empty.
	 * This is O(n)
	 * 
	 * @param item a comparable value that you want to add in the vector
	 * 
	 */
	public void addFirst(Comparable item)
	{
		
		if (isEmpty())
		{
			data[0] = item;
		}
		else if(isFull())
		{
			extendCapacity();
			shiftElementsUp(0);
			data[0] = item;
		}
		else
		{
			shiftElementsUp(0);
			data[0] = item;
		}
		
		count++;
	}

	/*
	 * addLast
	 * 
	 * This method adds an element after the current last position of the Vector.
	 * If the vector is full you may need to extend the capacity which would result
	 * in O(n).
	 * This is O(1) if you don't need to extend the capacity.
	 * 
	 * @param c a comparable value that you want to add in the vector
	 * 
	 */
	public void addLast(Comparable c)
	{
		if (isFull())
		{
			extendCapacity();
			data[count] = c;
			count++;	
		}
		else
		{
			data[count] = c;
			count++;	
		}
	}
	
	//toString()- creates a string which lists the data in the Vector starting from index 0
	public String toString()
	{
	
		String vecValues = ""; //note you must use double quotes, not single!!!
		
	
		for(int i = 0; i < size(); i++)
		{			
			vecValues += data[i].toString() + " ";
		}
			
		return ("The vector contains: " + vecValues);
			
	}
	
	//toString()- creates a string which lists the data in the Vector in reverse order
	//it starts from the last index and goes down to index 0
	//I found this method useful during the course of writing the program.
	public String reverseToString()
	{
	
		String vecValues = ""; //note you must use double quotes, not single!!!
		
		for(int i = size()-1; i >= 0; i--)
		{			
			vecValues += data[i].toString() + " ";
		}
			
		return ("The vector contains: " + vecValues);
			
	}
	
	//returns the value of the first element in the vector O(1)
	public Comparable getFirst()
	{
		return data[0];
	}

	//returns the value of the last element in the vector O(1)
	//note I must return a Comparable. As so, if the vector is empty
	//I return null; otherwise I get an error. 
	public Comparable getLast()
	{
		if(!isEmpty())
			return (Comparable) data[count-1];
		else
			return null;

	}

	//removes the last element in the Vector by fading it out
	//note it does not actually delete or re-point the value to null!
	public void removeLast()
	{
		// data[count] = null; //you actually don't need this
		count--; //this is enough
		
	} 
	
	/*
	 * removeFirst
	 * 
	 * removes the first element from the vector and shifts all the elements
	 * down by one position.
	 * 
	 */
	public void removeFirst()
	{
		if(!isEmpty())
			for(int i = 1; i <= size(); i++)
			{
				data[i-1] = data[i];
			}
			count--;
	}
	
	
	/*
	 * reverse
	 * 
	 * I think this was one of the exercise questions that I had written 
	 * at the very beginning of the course. I am not using it in relation
	 * to this project. It was just an exercise for me. I have not tested
	 * it recently as I write this on 1/18/20.
	 * 
	 */
	public void reverse()
	{
		if(!isEmpty())
		{
			int symInd; //symmetric index
			for(int i = 0; i < size()/2; i++)
			{
				symInd = size()-1-i;
				Comparable temp = get(i);
				set(i, data[symInd]);
				set(symInd, temp);
			}
		}
	}
	
	/*
	 * doubleVector
	 * 
	 * I think this was one of the exercise questions that I had written 
	 * at the very beginning of the course. I am not using it in relation
	 * to this project. It was just an exercise for me. I have not tested
	 * it recently as I write this on 1/18/20.
	 * 
	 */
	public Vector doubleVector()
	{
		Vector doubleVec = new Vector(2*maximumCapacity()); //double the capacity
		System.out.println(doubleVec.maximumCapacity());
		
		//complexity is O(n) because we have a for loop with n iterations
		for(int i = 0; i < size(); i++)
		{
			
			doubleVec.addLast(get(i));
			doubleVec.addLast(get(i));
		}
		
		return doubleVec;
	}
	
	/*
	 * interleave
	 * 
	 * This was one of the exercise questions that I had written at the very beginning
	 * of the course. I have not tested it in quite some time
	 * so as I'm writing this on 1/18/20, it may not work 100%.
	 * 
	 */
	public Vector interleave(Vector v2)
	{
		/*
		time complexity is O(n). even though we loop through 2 for loops
		we would have T(n) = 2n assuming that both vec's are equal
		but removing the constant we get O(n)
		*/
		Vector wovenVec;
		if(maximumCapacity() > v2.maximumCapacity())
			wovenVec = new Vector(2 * maximumCapacity());
		else 
			wovenVec = new Vector(2 * v2.maximumCapacity());
		
		if(size() > v2.size()) //weave smaller one
		{
			for(int i = 0; i < v2.size(); i++)
			{
				wovenVec.addLast(get(i));
				wovenVec.addLast(v2.get(i));
			}
			
			for(int i = v2.size(); i < size(); i++)
			{
				wovenVec.addLast(get(i));
			}
		}
		else
		{
			for(int i = 0; i < size(); i++)
			{
				wovenVec.addLast(get(i));
				wovenVec.addLast(v2.get(i));
			}
			
			for(int i = size(); i < v2.size(); i++)
			{
				wovenVec.addLast(v2.get(i));
			}
		}
		
		return wovenVec;
		
	}
	

	/*
	 * extendCapacity
	 *  
	 * If the Vector is full you can extend the capacity (total # of elements the vector can hold).
	 * 
	 * It works by creating a new Vector with a maximum capacity of twice the current
	 * Vector. The elements from the current Vector are then copied to the new Vector;
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
		
		//reminder- it does not return anything!
	}
	
	//compareTo- two Vector's are compared based on their size (length)
	public int compareTo(Object o)
	{
		Vector cur = (Vector)o;
		if(this.size() < cur.size())
			return -1;
		else if(this.size() == cur.size())
			return 0;
		else
			return 1;
	}
	
	
}