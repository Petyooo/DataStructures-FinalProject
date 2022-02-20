/*
 * PriorityQueue.java
 * Petio Todorov
 * 18/1/20
 * 
 * Implementation of a Priority Queue using Sorted Linked List. New values are grouped together into Pairs
 * with their priority and element (value). Pairs have a compareTo method which compares the priority. 
 * This is what's used to determine their position inside the sorted linked list.
 * 
 */

import java.util.Comparator;

public class PriorityQueue implements Comparable
{   
	/////////////////////////////////////////////////
	//PriorityPair code is below. This is a private helper class for PriorityQueue
	/////////////////////////////////////////////////
	private class Pair implements Comparable
	{
		private Comparable element;
		private Comparable priority;
		
		/*
		 * Pair Constructor
		 * 
		 * Creates a new Pair which consists of a value and a priority. It basically couples
		 * two Comparables together into one Comparable so that they can be more easily
		 * stored in a sorted linked list. 
		 *
		 * @param element a comparable indicating the value of some item
		 *
		 * @param priority a comparable indicating the priority of some item
		 * 
		 */		
		public Pair(Comparable element, Comparable priority)
		{
			this.element = element;
			this.priority = priority;
		}
		
		//compareTo- two Pairs are compared based on their priority!!!
		public int compareTo(Object o)
		{
			Pair p = (Pair)o;
			return this.priority.compareTo(p.priority);
		}
		
		//returns the value of the Pairs' element
		public Comparable getElement()
		{
			return element;
		}
		
		//returns the value of the Pairs' priority
		public Comparable getPriority()
		{
			return priority;
		}
		
		//toString()- creates a string which lists the value of the element, not the priority
		public String toString()
		{
			return element.toString();
		}
	}
		
		/////////////////////////////////////////////////
		//Actual PriorityQueue code is below
		/////////////////////////////////////////////////
		private SortedLinkedList data;
	
		//constructor
		public PriorityQueue()
		{
			data = new SortedLinkedList();
		}
	
		//the length/size (# of elements of the PriorityQueue)
		public int size() {
			return data.size();
		}
		
		/*
		 * push
		 *  
		 * Pushes information (Comparable c) into the appropriate position in the
		 * sorted linked list. The appropriate position is found by comparing the
		 * priority of the current Pair to the priority of other pairs that are 
		 * already in the sorted linked list. This is done using the addSorted method
		 * of SortedLinkedList.
		 * 
		 * This can be O(n) because you need to find the correct position in the Sorted
		 * LinkedList for inserting the Pair.
		 * 
		 * @param c comparable indicating the information to be given as the element
		 * value of a Pair
		 * 
		 * @param priority comparable indicating the priority to be set for a new Pair
		 * 
		 */
		public void push(Comparable c, Comparable priority)
		{
			// make a pair of c and priority
			// add this pair to the sorted linked list.
			Pair newPair = new Pair(c, priority);
			data.addSorted(newPair);
		}
	
		/*
		 * Pop- removes the front element of the PriorityQueue and returns it.
		 * Note it returns the Pair element value not the Pair itself!
		 * O(1)
		 * 
		 */
		public Comparable pop()
		{
			Pair temp = (Pair) data.getFirst();
			data.removeFirst();
			return temp.getElement();
		}
	
		/*
		 * top- returns the front element of the PriorityQueue but does not remove it
		 * Note it returns the Pair element value not the Pair itself!
		 * O(1)
		 * 
		 */
		public Comparable top()
		{
			return ((Pair) data.getFirst()).getElement();
		}
		
		//toString()- creates a string which lists the data in the PriorityQueue
		public String toString()
		{
			return data.toString();
		}
		
		//compareTo- two PriorityQueues are compared based on their size (length)
		public int compareTo(Object o)
		{
			PriorityQueue cur = (PriorityQueue)o;
			if(this.size() < cur.size())
				return -1;
			else if(this.size() == cur.size())
				return 0;
			else
				return 1;
		}
}

 