/*
 * Graph.java
 * Petio Todorov
 * 18/1/20
 * 
 * Implementation of a Graph using Adjacency List Representation.

 * My version is based on SortedVector. A lot of the code that is commented out below
 * is from the exercises in the Practicum, which asked you to implement a new method,
 * test it, realize that it's not perfect and create an improved version, e.g. findPath.
 * 
 */


public class Graph
{
	/////////////////////////////////////////////////
	//Node code is below. This is a helper class for Edge/Graph.
	/////////////////////////////////////////////////
    public class Node implements Comparable
    {
        private Comparable info;
        private SortedVector edges;
        private boolean visited;
        
		/*
		 * Node Constructor
		 * 
		 * Creates a new Node which is identified by a label and keeps track of
		 * the edges going from this node to other nodes.
		 *
		 * @param label a value used to identify or mark the node
		 *
		 */	
        public Node(Comparable label)
        {
            info = label;
            edges = new SortedVector(5);
        }
        
		/*
		 * addEdge- O(n) because I am using SortedVector. Even though you can search in O(log(n))
		 * adding an element is O(n).
		 * 
		 * Adds an edge to the of list/vector of edges for this node.
		 *
		 * @param e an edge between 2 nodes
		 *
		 */	
        public void addEdge(Edge e)
        {
            edges.addSorted(e);
        }
        
    	//compareTo- two nodes are equal if they have the same label
        public int compareTo(Object o)
        {
            Node n = (Node)o;
            return info.compareTo(n.info);
        }
        
        //returns the label of this node
        public Comparable getLabel()
        {
            return info;
        }
        
        //returns the structure containing all of the edges emanating from this node 
        public Comparable getEdges()
        {
        	return edges;
        }
        
    	//toString()- creates a string which represents the node label
        public String toString()
        {
        	return info.toString();
        }
        
        /*
         * setVisited
         * 
         * This is an important addition that was made for the findPath method. It allows us to keep
         * track of whether a particular node was already visited.
         * 
         * @param visited a boolean indicating whether this edge has been visited or not
         */
        public void setVisited(boolean visited)
        {
            this.visited = visited;
        }
        
        //returns true/false depending on whether this node was visited or not
        public boolean wasVisited()
        {
            return visited;
        }
    }
    
	/////////////////////////////////////////////////
	//Edge code is below. This is a helper class for Node/Graph. 
	/////////////////////////////////////////////////
    private class Edge implements Comparable
    {
        private Node toNode;
        private Comparable weight;
        
		/*
		 * Edge Constructor
		 * 
		 * Creates a new Edge to another node, "to", with a weight "weight". It emanates from the current node.
		 *
		 * @param to a Node at which the edge ends
		 *
		 * @param weight a value used to indicate the weight (value) of the edge
		 */	
        public Edge(Node to, Comparable weight)
        {
            toNode = to;
            this.weight = weight;
        }
        
        //returns the weight of the edge
        public Comparable getWeight()
        {
        	return weight;
        }
        
        //returns the Node at which the edge ends
        public Node getToNode()
        {
        	return toNode;
        }
        
    	//compareTo- two Edges are compared based on the weight
        public int compareTo(Object o)
        {	
    		Edge cur = (Edge)o;
    		if(this.weight.compareTo(cur.getWeight()) < 0)
    			return -1;
    		else if(this.weight.compareTo(cur.getWeight()) == 0)
    			return 0;
    		else
    			return 1;
        }
        
    	//toString()- creates a string which represents Edge by the node it's going to
        //and weight
        public String toString()
        {
        	String s = String.format("(%s,%s)", toNode.toString(), this.weight.toString());
        	return s;
        }
    }
    
    
	/////////////////////////////////////////////////
	//Actual Graph code is below.
	/////////////////////////////////////////////////
    private SortedVector nodes;
    
    //constructor
    public Graph()
    {
        nodes = new SortedVector(10);
    }
    
	/*
	 * addNode-  O(n) because I am using SortedVector. Even though you can search in O(log(n))
	 * adding an element is O(n).
	 *  
	 * Adds a node to the graph.
	 *
	 * @param label a value used to identify or mark the node
	 *
	 */	
    public void addNode(Comparable label)
    {
        nodes.addSorted(new Node(label));
    }
    
	/* 
	 * ex 9- modified findNode which uses the find method of underlying structure
	 * in this case it's binary search
	 * here using a sortedVector pays off because you search in O(log(n))
	 * 
	 * findNode
	 *  
	 * Returns a Node which has the label nodeLabel. 
	 *
	 * @param nodeLabel the node label that you are searching for
	 *
	 */	
    private Node findNode(Comparable nodeLabel)
    {
        Node cur = new Node(nodeLabel);
        
        int index = nodes.binarySearchForIndex(cur);
        
        if(index == -1)
        	return null;
        else
        	return (Node)nodes.get(index);
    }
    
	/*
	 * addEdge
	 * 
	 * Creates a new Edge with a weight "weight" in the graph, from the node with nodeLabel1 to node with nodeLabel2. 
	 * The edge is unidirectional and it emanates from the Node with nodeLabel1.
	 * 
	 * First to find the 2 nodes with nodeLabel1 and nodeLabel2, the search is done in O(log(n)) time.
	 * Adding the edge to the edges sortedVector of the node with label nodeLabel1 takes O(log(n)).
	 *
	 * @param nodeLabel1 the node label of the start node of the edge
	 *
	 * @param nodeLabel2 the node label of the start node of the edge
	 *
	 * @param weight a value used to indicate the weight (value) of the edge
	 * 
	 */
    public void addEdge(Comparable nodeLabel1,
                        Comparable nodeLabel2, Comparable weight)
    {
        Node n1 = findNode(nodeLabel1);
        Node n2 = findNode(nodeLabel2);
        n1.addEdge(new Edge(n2, weight));
    }
    
    
	/*
	 * getEdgesOfCertainWeight
	 * 
	 * Returns all of the edges that a particular node has, which have a 
	 * weight less than or equal to the weight "weight".
	 *  
	 * First to find the node, the search is done in O(log(n)) time.
	 * 
	 * Going thru the edges of the node takes O(n) time because we are using a for loop.
	 *
	 * @param label the node label of the node whose edges we want
	 *
	 * @param weight a value used to indicate the weight (value) of the edge
	 * 
	 */
    public Comparable getEdgesOfCertainWeight(Comparable label, Comparable weight)
    {
    	Vector edgesWithCorrectWeight = new Vector(10);
    	
		SortedVector curEdges = (SortedVector)findNode(label).getEdges();
		
		for(int i = 0; i < curEdges.size(); i++)
		{
			Edge curEdge = (Edge)curEdges.get(i);
			if(curEdge.getWeight().compareTo(weight) <= 0)
			{
				edgesWithCorrectWeight.addLast(curEdge.getToNode());
			}
		}

		return edgesWithCorrectWeight;
    }
    
	/* 
	 * ex 8- added visited boolean in nodes
	 * 
	 * findPath
	 *  
	 * Returns true or false depending on whether there is a path between two nodes.
	 *
	 * @param nodeLabel1 the node label of the node at the start of the path
	 *
	 * @param nodeLabel2 the node label of the node at the end of the path 
	 * 
	 */	
    public boolean findPath(Comparable nodeLabel1, Comparable nodeLabel2)
	{
		Node startState = findNode (nodeLabel1);
		Node endState = findNode (nodeLabel2);
		Stack toDoList = new Stack();
		toDoList.push(startState);
		
		setAlltoNotVisited();
		
		while (!toDoList.empty())
		{
			Node current = (Node) toDoList.pop();
			current.setVisited(true);

			if (current == endState)
				return true;
			else
			{
				for (int i=0; i< current.edges.size(); i++)
				{
					Edge e = (Edge)current.edges.get(i);
					
					if(!e.toNode.wasVisited())
						toDoList.push(e.toNode);
				}
			}

		}
		return false;
	}
    
  
	/* 
	 * with visited boolean implemented in the Node class
	 * 
	 * getPath
	 *  
	 * Returns all of nodes that were visited to get from node with nodeLabel 1 to
	 * node with nodeLabel2
	 * 
	 * 
	 * @param nodeLabel1 the node label of the node at the start of the path
	 *
	 * @param nodeLabel2 the node label of the node at the end of the path 
	 * 
	 */	    
    public Comparable getPath(Comparable nodeLabel1, Comparable nodeLabel2)
	{
		Node startState = findNode (nodeLabel1);
		Node endState = findNode (nodeLabel2);
		Stack toDoList = new Stack();
		toDoList.push(startState);

		setAlltoNotVisited();
		
		Queue path = new Queue(); //add a new Queue to record the sequence of nodes you've been through on the path!
		while (!toDoList.empty())
		{
			Node current = (Node) toDoList.pop();
			current.setVisited(true);
			
			path.push(current);
			if (current == endState)
				return path;
			else
			{
				for (int i=0; i< current.edges.size(); i++)
				{
					Edge e = (Edge)current.edges.get(i);
					
					if(!e.toNode.wasVisited())
						toDoList.push(e.toNode);
				}
			}
		}
		return path;
	} 
    
   
    //sets all the nodes in the graph to not visited 
    public void setAlltoNotVisited()
    {
		for(int i = 0; i < nodes.size(); i++)
		{
			((Node)nodes.get(i)).setVisited(false);
		}
    }
    
	//toString()- creates a string which shows the adjacency list representation of a graph
    public String toString()
    {
    	String graphString = "";
    	for(int i = 0; i < nodes.size(); i++)
    	{
    		Node curNode = (Node) nodes.get(i);
    		String curString = "";
    		curString += curNode.toString() + " ";
    		for(int j = 0; j < ((Vector) curNode.getEdges()).size(); j++)
    		{
    			Edge curEdge = (Edge) ((Vector)curNode.getEdges()).get(j);
    			curString += curEdge.toString() + " ";
    		}
    		
        	graphString += curString + "\n";
    	}
    	return graphString;
    }
    
    //original- this is an exercise for implementing find node, 
    //I am not using it at the moment. PT 1/18/20
//    private Node findNode(Comparable nodeLabel)
//    {
//        Node res = null;
//        for (int i=0; i<nodes.size(); i++)
//        {
//            Node n = (Node)nodes.get(i);
//            if(n.getLabel() == nodeLabel)
//            {
//                res = n;
//                break;
//            }
//        }
//        return res;
//    }
    
//    //ex 9- modified findNode which uses the find method of underlying structure
//    //in this case it's find in LinkedList
//    private Node findNode(Comparable nodeLabel)
//    {
//        Node cur = new Node(nodeLabel);
//        int index = nodes.find(cur);
//
//        if(index == -1)
//        	return null;
//        else
//        	return (Node)nodes.get(index);
//    }

    //original- cycles don't work as mentioned in the exercise- loops
//  public boolean findPath(Comparable nodeLabel1, Comparable nodeLabel2)
//	{
//		Node startState = findNode (nodeLabel1);
//		Node endState = findNode (nodeLabel2);
//		Stack toDoList = new Stack();
//		toDoList.push(startState);
//		while (!toDoList.empty())
//		{
//			Node current = (Node) toDoList.pop();
//			if (current == endState)
//				return true;
//			else
//			{
//				for (int i=0; i< current.edges.size(); i++)
//				{
//					Edge e = (Edge)current.edges.get(i);
//					toDoList.push(e.toNode);
//				}
//			}
//		}
//		return false;
//	}
  
  //ex 8- add visited list
//  public boolean findPath(Comparable nodeLabel1, Comparable nodeLabel2)
//	{
//		Node startState = findNode (nodeLabel1);
//		Node endState = findNode (nodeLabel2);
//		Stack toDoList = new Stack();
//		toDoList.push(startState);
//		LinkedList visited = new LinkedList();
//		
//		while (!toDoList.empty())
//		{
//			Node current = (Node) toDoList.pop();
//			visited.addFirst(current);
//
//			if (current == endState)
//				return true;
//			else
//			{
//				for (int i=0; i< current.edges.size(); i++)
//				{
//					Edge e = (Edge)current.edges.get(i);
//					
//					if(!visited.contains(e.toNode))
//						toDoList.push(e.toNode);
//				}
//			}
//
//		}
//		return false;
//	}
  
  
  //ex 11- version for LinkedList instead of Vector
//  public boolean findPath(Comparable nodeLabel1, Comparable nodeLabel2)
//	{
//		Node startState = findNode (nodeLabel1);
//		Node endState = findNode (nodeLabel2);
//		Stack toDoList = new Stack();
//		toDoList.push(startState);
//		
//		setAlltoNotVisited();
//		
//		while (!toDoList.empty())
//		{
//			Node current = (Node) toDoList.pop();
//			current.setVisited(true);
//
//			if (current == endState)
//				return true;
//			else
//			{
//				for (int i=0; i< current.edges.size(); i++)
//				{
//					Edge e = (Edge)current.edges.get(i);
//					
//					if(!e.toNode.wasVisited())
//						toDoList.push(e.toNode);
//				}
//			}
//
//		}
//		return false;
//	}
    
    
    //original- without cycle checking
//  public Comparable getPath(Comparable nodeLabel1, Comparable nodeLabel2)
//	{
//		Node startState = findNode (nodeLabel1);
//		Node endState = findNode (nodeLabel2);
//		Stack toDoList = new Stack();
//		toDoList.push(startState);
//		
//		LinkedList path = new LinkedList(); //add a new Queue to record the nodes you've visited!
//		while (!toDoList.empty())
//		{
//			Node current = (Node) toDoList.pop();
//			path.addLast(current);
//			if (current == endState)
//				return path;
//			else
//			{
//				for (int i=0; i< current.edges.size(); i++)
//				{
//					Edge e = (Edge)current.edges.get(i);
//					toDoList.push(e.toNode);
//				}
//			}
//		}
//		return path;
//	}    
  
  //modified- with visited list
//  public Comparable getPath(Comparable nodeLabel1, Comparable nodeLabel2)
//	{
//		Node startState = findNode (nodeLabel1);
//		Node endState = findNode (nodeLabel2);
//		Stack toDoList = new Stack();
//		toDoList.push(startState);
//		LinkedList visited = new LinkedList();		
//		
//		LinkedList path = new LinkedList(); //add a new Queue to record the nodes you've visited!
//		while (!toDoList.empty())
//		{
//			Node current = (Node) toDoList.pop();
//			visited.addFirst(current);
//			
//			path.addLast(current);
//			if (current == endState)
//				return path;
//			else
//			{
//				for (int i=0; i< current.edges.size(); i++)
//				{
//					Edge e = (Edge)current.edges.get(i);
//					
//					if(!visited.contains(e.toNode))
//						toDoList.push(e.toNode);
//				}
//			}
//		}
//		return path;
//	} 
    
    //modified- with visited nodes for ex10 with LinkedList
//  public Comparable getPath(Comparable nodeLabel1, Comparable nodeLabel2)
//	{
//		Node startState = findNode (nodeLabel1);
//		Node endState = findNode (nodeLabel2);
//		Stack toDoList = new Stack();
//		toDoList.push(startState);
//
//		setAlltoNotVisited();
//		
//		LinkedList path = new LinkedList(); //add a new Queue to record the nodes you've visited!
//		while (!toDoList.empty())
//		{
//			Node current = (Node) toDoList.pop();
//			current.setVisited(true);
//			
//			path.addLast(current);
//			if (current == endState)
//				return path;
//			else
//			{
//				for (int i=0; i< current.edges.size(); i++)
//				{
//					Edge e = (Edge)current.edges.get(i);
//					
//					if(!e.toNode.wasVisited())
//						toDoList.push(e.toNode);
//				}
//			}
//		}
//		return path;
//	}
    
}