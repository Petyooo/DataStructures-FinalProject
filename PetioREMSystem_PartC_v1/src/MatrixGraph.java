/*
 * MatrixGraph.java
 * Petio Todorov
 * 18/1/20
 * 
 * Implementation of a Graph using a matrix.
 * It is used in conjunction with MatrixGraph.java
 * 
 * It stores values in an N by N matrix.
 * 
 */

public class MatrixGraph 
{
	private Matrix data;
	
	/*
	 * MatrixGraph Constructor
	 * 
	 * Creates a new nrNodes x nrNodes matrix.
	 *
	 * @param nrNodes number of rows/columns of the graph
	 * 
	 */
	public MatrixGraph(int nrNodes)
	{
		data = new Matrix(nrNodes);
	}
	
	/*
	 * addEdge- O(1)
	 * 
	 * Creates an edge between node from and node to and sets the weight of the edge to w.
	 * Note- this node is unidirectional. You'd need a separate edge going in the other direction
	 * from to to from. 
	 *
	 * @param from the start node of the edge
	 * 
	 * @param to the end node of the edge
	 * 
 	 * @param w the value you want to set for the edge between from and to
	 */
	public void addEdge(int from, int to, double w)
	{
		data.set(from, to, w);
	}
	
	/*
	 * getEdge- O(1)
	 * 
	 * Returns the weight of the edge between node from and node to 
	 * 
	 * @param from the start node of the edge
	 * 
	 * @param to the end node of the edge
	 * 
	 */
	public double getEdge(int from, int to)
	{
		return (Double)data.get(from, to);
	}
}