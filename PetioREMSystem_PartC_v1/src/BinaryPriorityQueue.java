/*
 * BinaryPriorityQueue.java
 * Petio Todorov
 * 18/1/20
 * 
 * Implementation of a Priority Queue with only 2 priorities.
 * Implementation is done by using two Queues. You obtain
 * O(1) push and pop by adding/removing from the appropriate
 * Queue.
 * 
 * BPQ will be used as shorthand notation.
 */


public class BinaryPriorityQueue implements Comparable
{       
	private Queue highQueue;
	private Queue lowQueue;
	Comparable highPriority;
	Comparable lowPriority;

	/*
	 * BinaryPriorityQueue Constructor
	 * BPQ will be used as shorthand notation.
	 * 
	 * Creates a new Priority Queue which has only 2 priorities, low and high.
	 * For example, the priorities might be markers such as "A" and "B" or 0 and 1, 
	 * which are used to split data into 2 distinct categories.
	 *
	 * @param highPriority a comparable indicating the higher priority
	 *
	 * @param lowPriority a comparable indicating the lower priority
	 * 
	 */
	public BinaryPriorityQueue(Comparable highPriority, Comparable lowPriority)
	{
		highQueue = new Queue();
		lowQueue = new Queue();
		this.highPriority = highPriority;
		this.lowPriority = lowPriority;
	}
	
	//the length/size (# of elements of the BPQ)
	public int size() {
		return highQueue.size() + lowQueue.size();
	}
	
	/*
	 * push
	 *  
	 * Pushes information (Comparable c) into the appropriate Queue (low or high)
	 * based on the designated priority (Comparable priority) 
	 *
	 * @param c comparable indicating the information to be pushed in the BPQ
	 *
	 * @param priority comparable indicating the lowPriority or highPriority
	 * 
	 */
	public void push(Comparable c, Comparable priority)
	{
		if((this.highPriority).compareTo(priority) == 0)
		{
			highQueue.push(c);
		}
		else if((this.lowPriority).compareTo(priority) == 0)
		{
			lowQueue.push(c);
		}

	}

	/*
	 * Pop- removes the front element of the BPQ and returns it.
	 * Elements with a highPriority are popped before any elements
	 * with a lowPriority
	 * 
	 * -1 is returned if the BPQ is empty.
	 */
	public Comparable pop()
	{
		if(!highQueue.empty())
		{
			return highQueue.pop(); 
		}
		else if(!lowQueue.empty())
		{
			return lowQueue.pop();
		}
		else
			return -1;

	}

	/*
	 * Top- returns the front element of the BPQ without removing it.
	 * Elements with a highPriority are returned before any elements
	 * with a lowPriority
	 * 
	 * -1 is returned if the BPQ is empty.
	 */
	public Comparable top()
	{
		if(!highQueue.empty())
		{
			return highQueue.top(); 
		}
		else if(!lowQueue.empty())
		{
			return lowQueue.top();
		}
		else
			return -1;
	}
	
	//toString()- creates a string which lists the data in the BPQ
	public String toString()
	{
		String s1 = String.format("The high priority queue: %s and the low priority queue: %s",
				highQueue.toString(), lowQueue.toString());
		return s1;
	}
	
	//compareTo- two BPQ's are compared based on their size (length)
	public int compareTo(Object o) {
		BinaryPriorityQueue bpq = (BinaryPriorityQueue)o;
		
		if(this.size() < bpq.size())
		{
			return -1;
		}
		else if(this.size() == bpq.size())
		{
			return 0;
		}
		else
			return 1;
	}
}

 