
public class Edge implements Comparable<Edge> {
	
	// instance variables
	private Node node1;
	private Node node2;
	private int distance; 
	private int used;
	
	//constructor
	public Edge(int aDistance, Node firstNode, Node secondNode) {
		used = 0;
		distance = aDistance;
		node1 = firstNode;
		node2 = secondNode;
		
	}

	@Override
	public int compareTo(Edge arg0) {
		// TODO Auto-generated method stub
		return 0;
	}
	
	public boolean isFree() {
		return used == 0;
	}
	
	public void used() {
		used = 1;
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
