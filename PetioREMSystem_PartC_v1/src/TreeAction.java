/*
 * TreeAction.java
 * Petio Todorov
 * 18/1/20
 * 
 * This is a TreeAction class which is abstract. An abstract class can't be instantiated but it can be subclassed.
 * An abstract method is declared without an implementation. Subclasses however usually have an implementation
 * unless they also choose to be designated as abstract.
 * 
 * Note it creates a default constructor automatically.
 * 
 */

public abstract class TreeAction {
	public abstract void run(Tree.TreeNode n);
}

