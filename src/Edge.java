/**
 * Edge object class definition for TSP heuristic algorithms
 * 
 * @author Conner Shumway, Raiddy Drake, and Amber Lopata
 *
 */

public class Edge implements Comparable<Edge> {
	
	// instance variables
	private Node node1;
	private Node node2;
	private int distance; 
	
	//constructor
	public Edge(int aDistance, Node firstNode, Node secondNode) {
		distance = aDistance;
		node1 = firstNode;
		node2 = secondNode;
		
	}

	@Override
	// instructs edges to be sorted by their distance variables
	public int compareTo(Edge anotherEdge) {
		// TODO Auto-generated method stub
		return distance - anotherEdge.distance;
	}
	
	/**
	 * get distance method
	 * 
	 * @return the distance of this edge
	 */
	public int getDistance() {
		return distance;
	}
	
	/**
	 * get node 1 method
	 * 
	 * @return the first node connected to this edge
	 */
	public Node getNode1() {
		return node1;
	}
	
	/**
	 * get node 2 method
	 * 
	 * @return the second node connected to this edge
	 */
	public Node getNode2() {
		return node2;
	}
	

}
