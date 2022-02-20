/*
 * CircularVector.java
 * Petio Todorov
 * 18/1/20
 * 
 * An implementation of circular vector (dynamic array) which allows O(1) addFirst and addLast
 * and removeFirst and removeLast because it keeps track of the index of the first element in 
 * the base vector. This way shifting of elements is not done when an element is added/removed 
 * at the top or bottom of the circular vector.
 * 
 * I will use CV as shorthand notation for Circular Vector.
 * 
 * I also implemented the extendCircularCapacity() method which is cool. 
 * It moves the elements of the CV to a CV with a larger capacity. 
 * 
 */


public class CircularVector implements Comparable
{
	private Vector data;
	private int first;
	private int count;

	//CV Constructor, creates a new Vector as the basis.
	public CircularVector()
	{
		count = 0;
		first = 0;
		data = new Vector(5);
	}
	
	//the length/size (# of elements of the CV)
	public int size()
	{
		return count;
	}

	/*
	 * AddFirst
	 *  
	 * Adds information (Comparable element) into first (start) position of the CV
	 * What you are really doing is adding element one position behind the current 
	 * "first" element! 
	 * 
	 * If the CV is full you need to extend it's capacity before adding the new element.
	 * 
	 * @param element comparable indicating the information to be added in the CV
	 * 
	 */
	public void AddFirst(Comparable element)
	{
		
		if(count == data.maximumCapacity())
		{
			extendCircularCapacity();
			//you reset first=0 in this method call
		}
		
		int maxCapacity = data.maximumCapacity();		
		first = (first+maxCapacity-1) % maxCapacity;
		data.set(first, element);
		count++;
	}

	/*
	 * AddLast
	 *  
	 * Adds information (Comparable element) into last position of the CV
	 * 
	 * If the CV is full you need to extend it's capacity before adding the new element.
	 * 
	 * @param element comparable indicating the information to be added in the CV
	 * 
	 */
	public void AddLast(Comparable element)
	{
		if(count == data.maximumCapacity())
		{
			extendCircularCapacity();
			data.addLast(element);
		}
		else
		{
			int last = (first+count) % data.maximumCapacity();
			data.set(last, element);			
		}
		count++;
	}

	//returns the element at the "first" index 
	public Comparable GetFirst()
	{
		return data.get(first);
	}
	
	//returns the element at a particular index.
	//note that you offset from the "first" index
	public Comparable Get(int index)
	{
		int curIndex = (first+index) % data.maximumCapacity();
		return data.get(curIndex);
	}
	
	//returns the last element in the CV
	//note that you offset from the "first" index
	public Comparable GetLast()
	{
		//really important to have the minus 1 here!!!
		int last = (first+count-1) % data.maximumCapacity();

		return data.get(last);
	}

	//removes the element at the "first" index 
	public void RemoveFirst()
	{
		if(count > 0)
		{
			//note how it's backwards, you do +1 when removing, -1 when adding!
			first = (first+1) % data.maximumCapacity();
			count--;
		}
	}

	//removes the last element in the CV
	//you don't actually delete the element, you fade it away by decreasing count
	public void RemoveLast()
	{
		if(count > 0)
		{
			count--;
		}
	}

	//prints out all of the elements in order from first to last
	public void print()
	{
		System.out.print("[");
		for(int i=0;i<count;i++)
		{
			int index = (first + i) % data.maximumCapacity();
			System.out.print(data.get(index) + " ");
		}
		System.out.println("]");
	}
	
	/*
	 * extendCircularCapacity
	 *  
	 * If the CV is full you can extend the capacity (total # of elements the vector can hold).
	 * 
	 * It works by creating a new base Vector with a maximum capacity of twice the current base
	 * Vector. The elements from the current base Vector are then copied to the new base Vector;
	 * The current base Vector "first" index is mapped to the 0 index of the new base Vector.
	 * 
	 */
	private void extendCircularCapacity()
	{
		Vector data2 = new Vector(2 * data.maximumCapacity());
		
		for(int j = 0; j < data.maximumCapacity(); j++)
		{
			int offsetIndex = (j + first) % data.maximumCapacity();
			data2.addLast(data.get(offsetIndex)); 
			//also we reset the new start index to 0 by using addLast!
		}
		
		data = data2;
		first = 0; //reset the start value to 0!!!
		
		//System.out.println(data.maximumCapacity()); //testing
		
	}
	
	//returns true if the CV has no elements, false otherwise
	public boolean IsEmpty()
	{
		return count == 0;
	}
	
	//toString()- creates a string which lists the data in the CV from first to last
	public String toString() {
		
		String vecValues = ""; //note you must use double quotes, not single!!!
		
		for(int i = 0; i < size(); i++)
		{			
			int index = (first + i) % data.maximumCapacity();
			vecValues += data.get(index).toString() + " ";
		}
			
		return ("The Circular Vector contains: " + vecValues);
	}
	
	//toString()- creates a string which lists the data in the CV from last to first
	//I was using it in Queue at one point but came to realize it's not 100% necessary
	//I'm leaving it because it might be potentially useful for some future application.
	public String reverseToString() {
		
		String vecValues = ""; //note you must use double quotes, not single!!!
		
		for(int i = size()-1; i >= 0; i--)
		{			
			int index = (first + i) % data.maximumCapacity();
			vecValues += data.get(index).toString() + " ";
		}
			
		return ("The Circular Vector contains: " + vecValues);
	}
	
	//compareTo- two CV's are compared based on their size (length)
	public int compareTo(Object o)
	{
		CircularVector cur = (CircularVector)o;
		if(this.size() < cur.size())
			return -1;
		else if(this.size() == cur.size())
			return 0;
		else
			return 1;
	}
}