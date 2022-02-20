/*
 * Queue.java
 * Petio Todorov
 * 18/1/20
 * 
 * Implementation of a Queue using a Vector, a linked list, or Circular Vector.
 * 
 * I have three different versions of code. I am currently running the Circular Vector 
 * implementation because it is being used in the BinaryPriorityQueue to achieve 
 * O(1) push and pop.
 * 
 * While I understand that a better practice would be to have three separate files of Queue
 * based on each of the basic data structures, I found it was convenient to leave the file
 * as it is and comment/uncomment the appropriate version during the course of this project.
 * 
 * It would only take long to copy the code of the versions I'm not using into their own
 * separate files.
 * 
 */

//implemented using circular vectors

public class Queue implements Comparable{
	
	private CircularVector data;
	
	//constructor
	public Queue() {
		data = new CircularVector();
	}

	/*
	 * push
	 *  
	 * Pushes information (Comparable c) into the front of the Queue using addLast.
	 * This is O(1) because I'm using Circular Vector. It would be O(n) 
	 * if using LinkedList because you have to find the last element. 
	 * It would be O(1) if using Vector as well.
	 *
	 * @param c comparable indicating the information to be pushed in the Queue
	 *
	 * 
	 */
	public void push(Comparable c) {
		data.AddLast(c);
	}

	/*
	 * Pop- removes the front element of the Queue and returns it.
	 * 
	 * I am using a Circular Vector so this is O(1). If I was using linked
	 * list it would be O(1) because you have to find the last element.
	 * With Vector it would be O(n) because you have to shift elements down.
	 * 
	 */
	public Comparable pop() {
		Comparable first = (Comparable)data.GetFirst();
		data.RemoveFirst();
		return first;
	}

	/*
	 * Top- returns the front element of the Queue without removing it.
	 * 
	 * This is O(1) whether you are using Circular Vector, LinkedList, or Vector.
	 */
	public Comparable top() {
		return (Comparable)data.GetFirst();
	}

	//the length/size (# of elements of the Queue)
	public int size() {
		return data.size();
	}

	//returns true if the Queue has no elements, false otherwise
	public boolean empty() {
		return data.IsEmpty();
	}
	
	//toString()- creates a string which lists the data in the Queue
	public String toString() {
		return data.toString();

	}
	
	//compareTo- two Queues are compared based on their size (length)
	public int compareTo(Object o)
	{
		Queue cur = (Queue)o;
		if(this.size() < cur.size())
			return -1;
		else if(this.size() == cur.size())
			return 0;
		else
			return 1;
	}


//implement using vectors

//public class Queue implements Comparable{
//	
//	private Vector data;
//	
//	public Queue() {
//		data = new Vector(100);
//	}
//
//	public void push(Comparable o) {
//		data.addFirst(o);
//	}
//
//	public Comparable pop() {
//		Comparable last = (Comparable) data.getLast();
//		data.removeLast();
//		return last;
//	}
//
//	public Comparable top() {
//		return (Comparable) data.getLast();
//	}
//
//	public int size() {
//		return data.size();
//	}
//
//	public boolean empty() {
//		return data.isEmpty();
//	}
//	
//	public String toString() {
//		return data.reverseToString();
//	}
//	
//	public int compareTo(Object o)
//	{
//		Queue cur = (Queue)o;
//		if(this.size() < cur.size())
//			return -1;
//		else if(this.size() == cur.size())
//			return 0;
//		else
//			return 1;
//	}
//}


//implemented using linkedlist

//public class Queue implements Comparable{
//	
//	private LinkedList data;
//	
//	//constructor
//	public Queue() {
//		data = new LinkedList();
//	}
//
//	public void push(Comparable o) {
//		data.addLast(o);
//	}
//
//	public Comparable pop() {
//		Comparable first = (Comparable)data.getFirst();
//		data.removeFirst();
//		return first;
//	}
//
//	public Comparable top() {
//		return (Comparable)data.getFirst();
//	}
//
//	public int size() {
//		return data.size();
//	}
//
//	public boolean empty() {
//		return data.isEmpty();
//	}
//	
//	public String toString() {
//		return data.toString();
//	}
//	
//	public int compareTo(Object o)
//	{
//		Queue cur = (Queue)o;
//		if(this.size() < cur.size())
//			return -1;
//		else if(this.size() == cur.size())
//			return 0;
//		else
//			return 1;
//	}
//}

}
