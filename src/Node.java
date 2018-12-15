import java.util.ArrayList;

/**
 * Node object class definition for TSP heuristic algorithms
 * 
 * @author Amber Lopata, Conner Shumway and Raiddy Drake
 *
 */

public class Node {
	// instance variables
	private int degree;
	private String name;
	private ArrayList<Edge> connectedEdges;
	
	// constructor
	public Node(String aName) {
		degree = 0;
		name = aName;
		connectedEdges = new ArrayList<Edge>();
	}
	
	/**
	 * get name method
	 * 
	 * @return the name of the node
	 */
	public String getName() {
		return name;
	}
	
	/**
	 * get degree method
	 * 
	 * @return the degree of the node
	 */
	public int getDegree() {
		return degree;
	}
	
	/**
	 * changes the degree of the node by the given value
	 * 
	 * @param change the amount (positive or negative) the degree will be changed
	 */
	public void updateDegree(int change) {
		degree = degree + change;
	}
	
	/**
	 * adds a given edge to the list of connected edges stored in this node
	 * 
	 * @param anEdge, the edge object to be added to the list
	 */
	public void addEdge(Edge anEdge) {
		connectedEdges.add(anEdge);
	}
	
	/**
	 * returns the edge at the given index in the connectedEdges list
	 * 
	 * @param index, in the list of connected edges, of the edge to be returned
	 * @return the edge at the given index
	 */
	public Edge getConnectedEdge(int index){
		return connectedEdges.get(index);
	}	

}
