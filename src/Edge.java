
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
	public int compareTo(Edge anotherEdge) {
		// TODO Auto-generated method stub
		return distance - anotherEdge.distance;
	}
	
	public int getDistance() {
		return distance;
	}
	
	public Node getNode1() {
		return node1;
	}
	
	public Node getNode2() {
		return node2;
	}
	

}
