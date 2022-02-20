/*
 * SortedLinkedList.java
 * Petio Todorov
 * 18/1/20
 * 
 * Implementation of a Sorted Linked List.
 * I removed the addFirst/AddLast/set because users would be take the list out of order.
 * 
 * Note- you can't use binarySearch on a LL because you'd somehow need to know the index
 * of each ListElement in order to find the middle element and compare it against the key.
 * This is why SortedVector is better.
 * 
 * I haven't used this structure in a while. - PT 1/18/20
 */

public class SortedLinkedList implements Comparable{
	
	/////////////////////////////////////////////////
	//ListElement code is below. This is a private helper class for LL
	/////////////////////////////////////////////////
	private class ListElement implements Comparable{
		private Comparable el1;
		private ListElement el2;

		/*
		 * ListElement Constructor 1
		 * 
		 * Creates a new ListElement which consists of a value and pointer to the next ListElement. 
		 * It basically couples two Comparables together into one Comparable.
		 *
		 * @param el a comparable indicating the value of some information
		 *
		 * @param nextElement a ListElement which the current ListElement will point to.
		 * 
		 */
		public ListElement(Comparable el, ListElement nextElement) {
			el1 = el;
			el2 = nextElement;
		}

		/*
		 * ListElement Constructor 2
		 * 
		 * Creates a new ListElement which consists of a value el. The nextElement points to null!
		 *
		 * @param el a comparable indicating the value of some information
		 * 
		 */	
		public ListElement(Comparable el) {
			this(el, null);
		}

		//returns the value of the ListElement information
		public Comparable first() {
			return el1;
		}

		//returns the ListElement which this element points to
		public ListElement rest() {
			return el2;
		}
		
		//sets the value of the ListElement information
		public void setFirst(Comparable value) {
			el1 = value;
		}

		//sets the ListElement which this element points to
		public void setRest(ListElement value) {
			el2 = value;
		}
		
		//toString()- creates a string which lists the data value stored in the ListElement 
		public String toString()
		{
			return el1.toString();
		}
		
		//compareTo- two ListElements are compared based on their label (data value)
        public int compareTo(Object o)
        {
            // two nodes are equal if they have the same label
            ListElement n = (ListElement)o;
            return el1.compareTo(n.first());

        }
	}
	
	/////////////////////////////////////////////////
	//Actual SortedLinkedList code is below
	/////////////////////////////////////////////////
	private ListElement head;
	private int count;
	
	//constructor
	public SortedLinkedList() {
		head = null;
		count = 0;
	}
	
	//the length/size (# of elements of the LL)	
	public int size() {
		return count;
	}
	
	//returns true if the LL has no elements, false otherwise
	public boolean isEmpty()
	{
		return head == null;
	}
	
	//returns the ListElement at the front of the list- O(1)
	public Comparable getFirst() {
		return head.first();
	}

	/*
	 * get- O(n) 
	 * 
	 * Returns the nth ListElement in the list.
	 *
	 * @param n this is the nth index in the list
	 * 
	 */	
	public Comparable get(int n) {
		ListElement d = head;
		while (n > 0) {
			d = d.rest();
			n--;
		}
		return d.first();
	}
	
	//returns the last ListElement in the LL- O(n)	
	public Comparable getLast()
	{
		if(isEmpty())
		{
			return head.first();
		}
		else
		{
			ListElement d = head;
			ListElement next = d.rest();
			while(next != null)
			{
				d = next;
				next = next.rest();
			}
			return d.first();
		}

	}
	
	//this one is just for testing my code out
	public void printElmValue(ListElement elm)
	{
		System.out.println(elm.first().toString());
	}
	
	/*
	 * addSorted- O(n)
	 * 
	 * Adds a ListElement with element value c in the correct position in the SortedLinkedList.
	 * It does so by comparing the value of c to the element values of other elements in the list
	 * in order.
	 *
	 * @param c a comparable indicating the value of some data you want to store
	 * 
	 */	
	public void addSorted(Comparable c)
	{
		//if the list is empty, add the element in front
		if(head==null) head = new ListElement(c,null);
		else if(head.first().compareTo(c) > 0)
		{
			//if c is smaller than the first element of the list, we add it to the front
			head = new ListElement(c, head);
			//aaah when creating a new list element you can also set
			//what it points to, so here the old head becomes the sec element
			//that's why you have 2 constructors!
		}
		else
		{
			// we have to find the first element which is bigger than c and stop. we put c before it.
			ListElement d = head;
			while((d.rest() != null)&&
					(d.rest().first().compareTo(c) < 0))
			{
				d = d.rest();
			}
			ListElement next = d.rest();
			d.setRest(new ListElement(c, next));
		}
		count++;
	}
	
	/*
	 * contains- O(n)
	 * 
	 * Returns true/false based on whether it finds a ListElement 
	 * with element value c in the LL. 
	 * This is a major difference in comparison to SortedVector where we can use
	 * binary search to get O(log(n)).
	 *
	 * @param c a comparable indicating the value of some data you want to find
	 * 
	 */
	public boolean contains(Comparable c)
	{
		ListElement d = head;
		if(!isEmpty()) 
		{
			while(d != null)
			{
				if(d.first().equals(c))
					return true;
				d = d.rest();
			}
		}
		
		return false;
	}
	

	/*
	 * removeLast- O(n)
	 * 
	 * Removes the last ListElement in the LL.
	 *
	 */
	public void removeLast() {
		ListElement cur, next;
				
		if(!isEmpty())
		{
			cur = head;
			next = cur.rest();
			
			while(next != null)
			{
				cur = next;
				next = cur.rest();
			}
			
			cur.setRest(null);
		}
		count--;
	}
	
	/*
	 * removeFirst- O(1)
	 * 
	 * Removes the first ListElement at the front of the LL.
	 *
	 */
	public void removeFirst() {
		ListElement cur, next;
		
		if(!isEmpty()) {
			cur = head;
			next = cur.rest();
			head = next;
		}
		count--;
	}
	
	//toString()- creates a string which lists the data in the LL
	public String toString() {
		String s = "(";
		ListElement d = head;
		while (d != null) {
			s += d.first().toString();
			s += " ";
			d = d.rest();
		}
		s += ")";
		return s;
	}

	//compareTo- two LL's are compared based on their size (length)
	public int compareTo(Object o)
	{
		LinkedList cur = (LinkedList)o;
		if(this.size() < cur.size())
			return -1;
		else if(this.size() == cur.size())
			return 0;
		else
			return 1;
	}

}

