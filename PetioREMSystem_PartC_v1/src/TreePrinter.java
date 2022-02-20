/*
 * TreePrinter.java
 * Petio Todorov
 * 18/1/20
 * 
 * This class extends TreeAction so it implements the corresponding abstract methods. 
 * 
 * We can make run perform some other useful operation that we want done on every element.
 */

public class TreePrinter extends TreeAction{
	
	public void run(Tree.TreeNode n)
	{
		System.out.println(n);
	}

}
