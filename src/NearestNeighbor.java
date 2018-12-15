import java.util.ArrayList;

/**
 * Nearest Neighbor Algorithm as a class definition
 * 
 * @author Raiddy Drake, Conner Shumway and Amber Lopata
 *
 */

public class NearestNeighbor {
	// instance variables
	private ArrayList<Node> nodes;
	
	// constructor
	public NearestNeighbor() {
		MapReader reader = new MapReader();
		nodes = reader.getNodes();
		
	}
	
	// methods
	/**
	 * takes the name of the starting node and finds the corresponding node object, then makes a list of nodes in 
	 * the order that they appear on the cycle made by implementing the Nearest Neighbor algorithm
	 * 
	 * @param startingName, the name of the node that you would like to start at on the path
	 * @return an array of string objects containing the path and total distance
	 */
	public String[] runAlgorithm(String startingName){
		// create a currentNode and startingNode object and initialize both as null
		Node currentNode = null;
		Node startingNode = null;
		// create a loop that will search for the node with the given name
		// and will make that node the current node when it is found
		for (int index = 0; index < nodes.size(); index++) {
			if (nodes.get(index).getName().equals(startingName)) {
				currentNode = nodes.get(index);
				startingNode = nodes.get(index);
				break;
			}
		}
		// initialize an integer variable to track the total distance
		int distance = 0;
		// create a string object that will show the order of the nodes, starting with the given beginning node
		String path = currentNode.getName();
		// create an integer variable that tracks the number of used nodes
		int usedNodes = 1;
		// create a nested loop that checks the sorted lists of attached edges in every node to find the shortest one that
		// is not already on the cycle
		while (usedNodes <= nodes.size()) {
			for (int index = 0; index < nodes.size()-1; index++) {
				// if the shortest edge in the node's arraylist (at the top of the list) is attached to a node with a degree 
				// less than 2, then the current node's degree is updated, the attached node that is 
				// not the current node becomes the current node, and usedNodes, distance and the path are all updated.
				if (currentNode != currentNode.getConnectedEdge(index).getNode1() &&
						currentNode.getConnectedEdge(index).getNode1().getDegree() < 2) {
					distance = distance + currentNode.getConnectedEdge(index).getDistance();
					currentNode.updateDegree(+2);
					currentNode = currentNode.getConnectedEdge(index).getNode1();
					usedNodes++;
					path = path + " -> " + currentNode.getName();
					break;
				}if (currentNode != currentNode.getConnectedEdge(index).getNode2() &&
						currentNode.getConnectedEdge(index).getNode2().getDegree() < 2) {
					distance = distance + currentNode.getConnectedEdge(index).getDistance();
					currentNode.updateDegree(+2);
					currentNode = currentNode.getConnectedEdge(index).getNode2();
					usedNodes++;
					path = path + " -> " + currentNode.getName();
					break;
				
					// if on the last edge, one of these if statements will update the distance, usedNodes, and path for the last time
				}if (usedNodes == nodes.size() && currentNode.getConnectedEdge(index).getNode1() == startingNode) {
					distance = distance + currentNode.getConnectedEdge(index).getDistance();
					path = path + " -> " + startingNode.getName();
					usedNodes++;
					break;
				}if (usedNodes == nodes.size() && currentNode.getConnectedEdge(index).getNode2() == startingNode) {
					distance = distance + currentNode.getConnectedEdge(index).getDistance();
					path = path + " -> " + startingNode.getName();
					usedNodes++;
					break;
				}
			}
		}
		// create an array of string objects that will hold the path and distance
		String data[] = new String[2];
		data[0] = path;
		data[1] = "" + distance;
		return data;
	}

}
