import java.util.ArrayList;

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
	
	public String getName() {
		return name;
	}
	
	public int getDegree() {
		return degree;
	}
	
	public void updateDegree(int change) {
		degree = degree + change;
	}
	
	public void addEdge(Edge anEdge) {
		connectedEdges.add(anEdge);
	}
	
	public Edge getConnectedEdge(int index){
		return connectedEdges.get(index);
	}
	
	
	public Node getConnectedNode(Edge connectedEdge) {
		Node connectedNode = null;
		for (int index = 0; index < connectedEdges.size(); index++) {
			if (connectedEdge == connectedEdges.get(index)) {
				if (connectedEdge.getNode1() == this) {
					connectedNode = connectedEdge.getNode2();
					break;
				}
				if (connectedEdge.getNode2() == this) {
					connectedNode = connectedEdge.getNode1();
					break;
				}
			}
		}
		return connectedNode;
	}
	

}
