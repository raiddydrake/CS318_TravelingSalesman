import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

public class Algorithms {
	// instance variables
	private HashMap<Node, List<Edge>> map;
	private ArrayList<Edge> edges;
	
	// constructor
	public Algorithms() {
		this.sortEdges();
	}
	
	public void sortEdges() {
		for (List<Edge> connectedEdges : map.values()) {
			edges.addAll(connectedEdges);
		}
		Collections.sort(edges);
	}
	
	private ArrayList<Edge> sortedEdges() {
		ArrayList<Edge> usedEdges = new ArrayList<Edge>();
		int index = 0;
		while (usedEdges.size() < map.size()) {
			if (edges.get(index).isFree()) {
				edges.get(index).used();
				usedEdges.add(edges.get(index));
				// ...
			}	
		}
		return usedEdges;
	}
	
	public ArrayList<Node> nearestNeighbor(Node startingNode){
		ArrayList<Node> usedNodes = new ArrayList<Node>();
		usedNodes.add(startingNode);
		Node currentNode = startingNode;
		while(usedNodes.size() < map.size()) {
			for (int index = 0; index < map.get(currentNode).size(); index++) {
				if (map.get(currentNode).get(index).isFree()) {
					// ...
				}
			}
			
		}
		return usedNodes;
	}

}
