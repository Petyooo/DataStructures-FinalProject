/*
 * Tree.java
 * Petio Todorov
 * 18/1/20
 * 
 * This is a Tree structure.
 * 
 * A lot of the code that is commented out below
 * is from the exercises in the Practicum, which asked you to implement a new method,
 * test it, realize that it's not perfect and create an improved version.
 * 
 */

import java.util.Comparator;

public class Tree {
	/*
	private class NaturalComparator implements Comparator
	{
		public int compare(Object a, Object b)
		{
			return ((Comparable)a).compareTo(b);
		}
	}
	*/
	
	/////////////////////////////////////////////////
	//TreeNode code is below. This is a helper class for Tree. 
	//It contains a value, a pointer to the left child and a pointer to the right child
	/////////////////////////////////////////////////
	public class TreeNode implements Comparable
	{
		private Comparable value;
		private TreeNode leftNode;
		private TreeNode rightNode;
		
		/*
		 * TreeNode Constructor 1
		 * 
		 * Creates a new TreeNode which has a value v. It sets the leftNode and rightNode to null.
		 *
		 * @param v a value stored in the TreeNode
		 *
		 */	
		public TreeNode(Comparable v){
			value = v;
			leftNode = null;
			rightNode = null;
		}
		
		/*
		 * TreeNode Constructor 2
		 * 
		 * Creates a new TreeNode which has a value v. It sets the leftNode to left and rightNode to right.
		 *
		 * @param v a value stored in the TreeNode
		 * 
		 * @param left another TreeNode which is the left child
		 * 
		 * @param right another TreeNode which is the right child
		 */	
		public TreeNode(Comparable v, TreeNode left, TreeNode right){
			value = v;
			leftNode = left;
			rightNode = right;
		}
		
		//returns a pointer to the leftNode of the current node
		public TreeNode getLeftTree(){
			return leftNode;
		}
		
		//returns a pointer to the rightNode of the current node
		public TreeNode getRightTree(){
			return rightNode;
		}
		
		//returns the value stored in the node
		public Comparable getValue(){
			return value;
		}

		//compareTo- two TreeNodes's are compared based on their value
		public int compareTo(Object o) {
			return value.compareTo(((TreeNode)o).value);
		}
		
		//toString()- creates a string to represent the node which is the node value
		public String toString()
		{
			return value.toString();
		}
	}
		
	/////////////////////////////////////////////////
	//Actual Tree code is below.
	/////////////////////////////////////////////////
	
	// the root of our tree
	private TreeNode root;
	
	//Constructor
	public Tree(){
		root = null;
	}
	
	/*
	 * traverse 
	 * 
	 * Can be implemented with a stack or a queue.
	 * Allows you to traverse the tree in a particular way 
	 * and perform a particular operation on each TreeNode using run. 
	 *
	 * @param action this is a TreeAction
	 *
	 */
	public void traverse(TreeAction action){
		Queue t = new Queue();
		//Stack t = new Stack();
		t.push(root);
		while(!t.empty())
		{
			TreeNode n = (TreeNode)t.pop();
			action.run(n);
			 
			if(n.getLeftTree() != null) t.push(n.getLeftTree());
			if(n.getRightTree() != null) t.push(n.getRightTree());
		}
	}
	
	//this is a recursive method
	public void traverseNode(TreeNode n,TreeAction action)
	{
		if(n != null)
		{
			if(n.getLeftTree() != null) traverseNode(n.getLeftTree(),action); 
			action.run(n);
			if(n.getRightTree() != null) traverseNode(n.getRightTree(),action);
		}
	}
	
	//start at the root node and go through the Tree
	public void traverseInOrder(TreeAction action){
		traverseNode(root,action);
	} 
	
	//implement print using stack
//	public void print()
//	{
//		Stack t = new Stack();
//		t.push(root);
//		while(!t.empty())
//		{
//			TreeNode n = (TreeNode)t.pop();
//			System.out.println(n.value);
//			if(n.getRightTree() != null)
//			{
//				t.push(n.getRightTree());
//			}
//			if(n.getLeftTree() != null)
//			{
//				t.push(n.getLeftTree());
//			}
//		}
//	}	
	
	//implement print using queue
//	public void print()
//	{
//		Queue t = new Queue();
//		t.push(root);
//		while(!t.empty())
//		{
//			TreeNode n = (TreeNode)t.pop();
//			System.out.println(n.value);
//			if(n.getRightTree() != null)
//			{
//				t.push(n.getRightTree());
//			}
//			if(n.getLeftTree() != null)
//			{
//				t.push(n.getLeftTree());
//			}
//		}
//	}
	
	//implement print using TreePrinter class
	public void print()
	{
		TreePrinter tp = new TreePrinter(); //note- i didn't make a constructor in treeprinter
											//or in treeaction. java makes a default one for new classes
		traverse(tp);
	}
	
	//print implemented with anonymous class- class with no name
	//note that traverseInOrder is literally one method call with a new
	//treeAction class defined inside it along with the run method
	//inside the run method we can define whatever we want
	//again, the point is that run is now generic and we can just change the code
	//inside it and traverseInOrder has a different output
//	public void print(){
//	traverseInOrder(new TreeAction()
//	{
//		public void run(TreeNode n)	{
//			System.out.println(n);
//			
//		}
//	});
//}
	
	//extension of the insertAtNode method below, starting from the root
	//of the tree
	public void insert(Comparable element){
		insertAtNode(element,root,null);
	}	
	
	// we traverse the tree.
	// Current holds the pointer to the TreeNode we are currently checking
	// Parent holds the pointer to the parent of the current TreeNode
	private void insertAtNode(Comparable element,TreeNode current,TreeNode parent)
	{
		// if the node we check is empty
		if(current == null)
		{
			TreeNode newNode = new TreeNode(element);
			// the current node is empty, but we have a parent
			if(parent != null)
			{
				// do we add it to the left?
				if(element.compareTo(parent.value) < 0)
				{
					parent.leftNode = newNode;
				}
				// or do we add it to the right?
				else
				{
					parent.rightNode = newNode;
				}
			}
			// the current node is empty and it has no parent, we actually have an empty tree
			else root = newNode;
		}
		else if(element.compareTo(current.value) == 0)
		{
			// if the element is already in the tree, what to do?
		}
		// if the element is smaller than current, go left
		else if(element.compareTo(current.value) < 0)
		{
			insertAtNode(element,current.getLeftTree(),current);
		}
		// if the element is bigger than current, go right
		else insertAtNode(element,current.getRightTree(),current);
	}

	//returns the depth of the current TreeNode
	public int depthNode(TreeNode current)
	{
		int maxDepth = 0;
		int mdl, mdr;
		// if the node we check is empty
		if(current == null)
		{
			return 0;
		}
		else
		{
			//max_depth_left and max_depth_right

			if(current.getLeftTree() == null && current.getRightTree() == null)
			{
				maxDepth = 0;	//this here was a tricky condition if both node children are null!
			}
			else
			{
				mdl = 1 + depthNode(current.getLeftTree());
				mdr = 1 + depthNode(current.getRightTree());
				
				if(mdl == mdr)
				{
					maxDepth = mdl; //could also be mdr, both work
				}
				else if(mdl > mdr)
				{
					maxDepth = mdl;
				}
				else
					maxDepth = mdr;
			}
		}
		
		return maxDepth;
	}
	
	//returns the depth of the tree
	public int depth()
	{
		return depthNode(root);
	}
	
	//returns the largestElement node starting at current TreeNode
	public Comparable largestElmNode(TreeNode current)
	{
		if(current.getRightTree() == null)
			return current.value;
		else
			return largestElmNode(current.getRightTree());
	}
	
	//returns the largest element in the tree because you start at root node
	public Comparable largestElm() 
	{
		return largestElmNode(root);
	}
	
	//returns true/false based on whether the element is somewhere in the tree or not
	//when starting from the current TreeNode
	public boolean findNode(Comparable element, TreeNode current)
	{
		if(current == null)
		{
			return false;
		}
		else if(element.compareTo(current.value) == 0)
		{
			return true;
		}
		else if(element.compareTo(current.value) < 0)
			return findNode(element, current.getLeftTree());
		else
			return findNode(element, current.getRightTree());	
		
	}
	
	//returns true/false based on whether the element is in the tree or not
	public boolean find(Comparable element)
	{
		return findNode(element, root);
	}
	
//	//returns the element
//	public Comparable findNodeKey(Comparable element, TreeNode current)
//	{
//		if(current == null)
//		{
//			return -1;
//		}
//		else if(element.compareTo(current.value) == 0)
//		{
//			return true;
//		}
//		else if(element.compareTo(current.value) < 0)
//			return findNode(element, current.getLeftTree());
//		else
//			return findNode(element, current.getRightTree());	
//	
//	}
//	
//	//returns a boolean
//	public TreeNode findKey(Comparable element)
//	{
//		return findNode(element, root);
//	}
	
	//returns the element when starting from the current TreeNode if it's found
	public Comparable findNodeKey(Comparable key, TreeNode current)
	{
		if(current == null)
		{
			return null;
		}
		else if(key.compareTo(current.value) == 0)
		{
			return current.value;
		}
		else if(key.compareTo(current.value) < 0)
			return findNodeKey(key, current.getLeftTree());
		else
			return findNodeKey(key, current.getRightTree());	
	
	}
	
	//uses findNodeKey method shown above
	//useful in terms of finding a key in the whole tree!
	public Comparable findKey(Comparable key)
	{
		return findNodeKey(key, root);
	}
}

