/*
 * Matrix.java
 * Petio Todorov
 * 18/1/20
 * 
 * Implementation of a Matrix.
 * It is used in conjunction with MatrixGraph.java
 * 
 * It creates an N by N matrix.
 * 
 */

public class Matrix 
{	
	// some appropriate private members.	
	Vector matrix;
	
	/*
	 * Matrix Constructor
	 * 
	 * Creates a new vector of vectors with nrNodes rows and columns. All of the values are initialized to 0.
	 *
	 * @param nrNodes number of rows/columns of the matrix
	 * 
	 */
	public Matrix(int nrNodes)
	{
		// allocate an N-by-N matrix where N = nrNodes
		// all elements are initially 0
		matrix = new Vector(nrNodes);

		for(int i = 0; i < nrNodes; i++)
		{
			Vector col = new Vector(nrNodes);
			for(int j = 0; j < nrNodes; j++)
			{
				col.addLast(0);
			}
			matrix.addLast(col);
		}
	}
	
	//this method was used for my personal testing it returns a Vector
	public Vector see_matrix()
	{
		return matrix;
	}
	
	/*
	 * set- O(1)
	 * 
	 * Sets the value at position row and col to weight. This is why all of the values are initialized to 0 (or maybe some other
	 * number depending on the application) in the constructor.
	 *
	 * @param row the row where you want to set the value
	 * 
	 * @param col the column where you want to set the value
	 * 
	 * @param weight the value you want to set for position (row, col)
	 */
	public void set(int row, int col, Comparable weight)
	{
		// store the weight at the given row and column.
		((Vector) matrix.get(row)).set(col,weight);

	}

	/*
	 * get- O(1)
	 * 
	 * Returns the weight at position (row, col). 
	 *
	 * @param row the row where you want to get the value
	 * 
	 * @param col the column where you want to get the value
	 * 
	 */
	public Comparable get(int row, int col)
	{
		// return the weight at the given row and column.
		return ((Vector) matrix.get(row)).get(col);
	}
	
	//toString()- creates a string representation of the matrix, i.e. it shows the weights row by col.
	public String toString()
	{
		String mtrxString = "";

		for(int i = 0; i < matrix.size() ; i++)
		{
			String curString = "";
			for(int j = 0; j < ((Vector) matrix.get(i)).size(); j++)
			{
				curString += ((Vector) matrix.get(i)).get(j).toString() + " ";
			}
			mtrxString += curString + "\n";
		}
		
		return mtrxString;
			
	}	
	
}