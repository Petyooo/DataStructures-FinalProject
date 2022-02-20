/*
 * Stack.java
 * Petio Todorov
 * 18/1/20
 * 
 * Implementation of a Stack using a vector or a linked list (further below).
 * 
 * I have two different versions of code. I am currently running the Vector 
 * implementation. Both implementations have O(1) push and pop.
 * 
 * While I understand that a better practice would be to have two separate files of Stack
 * based on each of the basic data structures, I found it was convenient to leave the file
 * as it is and comment/uncomment the appropriate version during the course of this project.
 */


//implemented using vector
public class Stack {
	
	private Vector data;

	//constructor- make a new vector
	public Stack() {
		data = new Vector(100);
	}

	/*
	 * push- O(1)
	 *  
	 * Pushes information (Comparable c) onto the top of the stack
	 * 
	 * @param c comparable indicating the information to be pushed on the Stack
	 * 
	 */
	public void push(Comparable c) {
		data.addLast(c);
	}

	/*
	 * pop- O(1)
	 *  
	 * Removes the information (Comparable c) from the top of the stack and returns it
	 * 
	 */
	public Comparable pop() {
		Comparable last = (Comparable) data.getLast();
		data.removeLast();
		return last;
	}

	/*
	 * top- O(1)
	 *  
	 * Returns the information (Comparable c) from the top of the stack but doesn't remove it
	 * 
	 */
	public Comparable top() {
		return (Comparable) data.getLast();
	}

	//the length/size (# of elements on the stack)
	public int size() {
		return data.size();
	}
	
	//returns true if the Stack has no elements, false otherwise
	public boolean empty() {
		return data.isEmpty();
	}
	
	//toString()- creates a string which lists the data on the stack
	public String toString() {
		return data.toString();
	}
}


//implemented using linked list
//note- i have not used the code below in a while
//so there may be some errors- 1/18/20 PT
/*
public class Stack {
	
	private LinkedList data;

	//constructor
	public Stack() {
		data = new LinkedList();
	}

	public void push(Comparable c) {
		data.addFirst(c);
	}

	public Comparable pop() {
		Comparable first = (Comparable) data.getFirst();
		data.removeFirst();
		return first;
	}

	public Comparable top() {
		return data.getFirst();
	}

	public int size() {
		return data.size();
	}

	public boolean empty() {
		return data.isEmpty();
	}
	
	public String toString() {
		return data.toString();
	}
}

*/