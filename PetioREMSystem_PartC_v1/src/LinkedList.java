/*
 * LinkedList.java
 * Petio Todorov
 * 18/1/20
 * 
 * Implementation of a Linked List.
 * 
 * I decided not to use LinkedList in my REMSystem program implementation only after using it initially
 * because I realized that it forces you into O(n^2) searching. Not cool. It was my favorite
 * in the beginning.
 * 
 * I will use LL as shorthand notation for LL.
 */

public class LinkedList implements Comparable{
	
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
	//Actual LinkedList code is below
	/////////////////////////////////////////////////
	private ListElement head;
	private int count;
	
	//constructor- sets the head of the list to point to null!!!
	public LinkedList() {
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
	
	/*
	 * addFirst- O(1)
	 * 
	 * Replaces the head element; i.e. it adds a new ListElement with value c 
	 * to the front of the list. The new element points to the previous head!
	 *
	 * @param c a comparable indicating the value of some data you want to store
	 * 
	 */	
	public void addFirst(Comparable c) {
		head = new ListElement(c, head);
		count++;
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
	
	/*
	 * set- O(n)
	 * 
	 * Re-sets the value of the nth ListElement in the LL to Comparable c.  
	 *
	 * @param n this is the nth index in the list
	 *
	 * @param c a comparable indicating the value of some data you want to store
	 * 
	 */	
	public void set(int n, Comparable c)
	{
		ListElement d = head; 
		if(n <= size())
		{
			while(n > 0)
			{
				d = d.rest();
				n--;
			}
			d.setFirst(c);
		}

	}
	
	//returns the last ListElement in the LL- O(n)
	public Comparable getLast()
	{
		if(isEmpty())
		{
			return head;
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
			return d;
		}

	}
	
	//this one is just for testing my code out
	public void printElmValue(ListElement elm)
	{
		System.out.println(elm.first().toString());
	}
	
	/*
	 * addLast- O(n)
	 * 
	 * Adds a ListElement with element value c to the end of the LL
	 *
	 * @param c a comparable indicating the value of some data you want to store
	 * 
	 */	
	public void addLast(Comparable c)
	{
		ListElement d = head;
		ListElement newElm = new ListElement(c,null);
		
		if(isEmpty())
		{
			head = new ListElement(c, head);
			count++;
		}
		else
		{
			while(d.rest() != null)
			{
				d = d.rest();
			}
			
			d.setRest(newElm);
			count++;
		}
	}
	
	/*
	 * contains- O(n)
	 * 
	 * Returns true/false based on whether it finds a ListElement 
	 * with element value c in the LL
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
				if(d.first().compareTo(c) == 0)
					return true;
				d = d.rest();
			}
		}
		
		return false;
	}
	
	/*
	 * find- O(n)
	 * 
	 * Very similar to contains.
	 * 
	 * Returns the position of a ListElement with element value c in the LL.
	 * If there is no such element, it returns -1.
	 *
	 * @param c a comparable indicating the value of some data you want to find
	 * 
	 */
	public int find(Comparable c)
	{
		ListElement d = head;
		int index = 0;
		
		if(!isEmpty()) 
		{
			while(d != null)
			{
				if(d.first().compareTo(c) == 0)
					return index;
				d = d.rest();
				index++;
			}
		}
		
		return -1;
	}
	
	/*
	 * fropple-
	 * 
	 * This was one of the exercise questions that I had written 
	 * at the very beginning of the course. I am not using it in relation
	 * to this project. It was just an exercise for me. I have not tested
	 * it recently as I write this on 1/18/20.
	 * 
	 */
	public void fropple()
	{
		ListElement d, next, temp;

		if(size() >= 2)
		{
			d = head;
			next = d.rest();
			temp = next.rest(); //just to enter loop
			head = next;
			next.setRest(d);
			while((temp != null) && (temp.rest() != null))
			{
				//printElmValue(next);
				d.setRest(temp.rest());
				d = temp;
				//printElmValue(d);
				next = d.rest();
				temp = next.rest();
				next.setRest(d);
				//printElmValue(next);
			}

			d.setRest(temp);

		}

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
	
	/*
	 * reverse- reverse the elements of the LL in place
	 * 
	 * This was one of the exercise questions that I had written 
	 * at the very beginning of the course. I am not using it in relation
	 * to this project. It was just an exercise for me. I have not tested
	 * it recently as I write this on 1/18/20.
	 * 
	 */
	public void reverse()
	{
		ListElement cur, next, temp;
		cur = head;
		next = cur.rest();
		cur.setRest(null);
		
		while(next!= null)
		{
			temp = next.rest();
			next.setRest(cur);
			cur = next;
			next = temp;
		}
		head = cur;
	}
	
	/*
	 * append- add a whole second list to the end of the first list
	 * 
	 * This was one of the exercise questions that I had written 
	 * at the very beginning of the course. I am not using it in relation
	 * to this project. It was just an exercise for me. I have not tested
	 * it recently as I write this on 1/18/20.
	 * 
	 */
	public void append(LinkedList list2)
	{
		ListElement d, next;
		
		if(!list2.isEmpty())
		{
			d = list2.head;
			next = d.rest();
			
			while(next != null)
			{
				//printElmValue(d); //for testing
				this.addLast(d.first()); //note you are adding the value of the element, not the LLelm!
				d = next;
				next = d.rest();
			}
			
			this.addLast(d.first()); // tricky here. you miss the last element otherwise.
		}
	}
	
	//toString()- creates a string which lists the data in the LL
	public String toString() {
		String s = "";
		ListElement d = head;
		while (d != null) {
			s += d.first().toString();
			s += " ";
			d = d.rest();
		}
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
