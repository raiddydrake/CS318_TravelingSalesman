import java.util.ArrayList;
import java.io.FileWriter;
import java.io.IOException;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

/**
 * Sorted Edges Algorithm as a class definition
 * 
 * @author Conner Shumway, Raiddy Drake and Amber Lopata
 *
 */

public class SortedEdges {
	
	// instance variables
	private ArrayList<Edge> edges;
	private ArrayList<Node> nodes;
	
	
	// constructor
	public SortedEdges() {
		// create a new MapReader object and get the lists it creates
		MapReader reader = new MapReader();
		nodes = reader.getNodes();
		edges = reader.getEdges();
	}
	
	
	// methods
	/**
	 * takes the name of the starting node and finds the corresponding node object, then makes a list of nodes in
	 * order of when they appear on the cycle, using the edges provided by the runAlgorithm private method
	 * 
	 * @param startingName, the name of the node that you would like shown first on the path
	 * @return an array of string objects containing the path and total distance
	 */
	@SuppressWarnings("unchecked")
	public String[] runAlgorithm(String startingName) {
		// create a currentNode object that is initialized as null
		Node currentNode = null;
		// create JSON objects and arrays to hold information, as well as initialize starting node 
		int currentIndex = 0;
		JSONObject obj = new JSONObject();
		JSONArray nodesjson = new JSONArray();
		JSONArray linksjson = new JSONArray();
		JSONObject firstNode = new JSONObject(); 
		firstNode.put("name", startingName);
		firstNode.put("group", 1);
		nodesjson.add(firstNode);
		// create a loop that will search for the node with the given name
		// and will make that node the current node when it is found
		for (int index = 0; index < nodes.size(); index++) {
			if (nodes.get(index).getName().equals(startingName)) {
				currentNode = nodes.get(index);
			}
		}
		// create a list of edges used in the cycle, by running the private runAlgorithm method
		ArrayList<Edge> sortEdges = runAlgorithm();
		
		// create a string object that will show the order of the nodes, starting with the given beginning node
		String path = currentNode.getName();
		
		// create an integer variable that will track the total distance of the cycle
		int distance = 0;
		// create an integer variable that tracks the number of nodes that have been sorted
		int sortedNodes = 1;
		// create a nested loop that runs through the list of used edges several times to find how the nodes connect to eachother
		while (sortedNodes <= nodes.size()) {
			for (int index = 0; index < sortEdges.size(); index++) {
				// if one of the nodes in the edge is equal to the current node, update sortedNodes and distance, 
				// make the other connected node the currentNode, remove this edge from the list so it isn't used again,
				// add the current Node's name to the path, and break the inner loop
				if (sortEdges.get(index).getNode1() == currentNode) {
					JSONObject aLink = new JSONObject();
					aLink.put("source", currentIndex);
					sortedNodes++;
					distance = distance + sortEdges.get(index).getDistance();
					currentNode = sortEdges.get(index).getNode2();
					currentIndex++;
					aLink.put("target", currentIndex);
					aLink.put("weight", 1);
					linksjson.add(aLink);
					JSONObject aNode = new JSONObject();
					aNode.put("name", currentNode.getName());
					aNode.put("group", 1);
					nodesjson.add(aNode);
					sortEdges.remove(sortEdges.get(index));
					path = path + " -> " + currentNode.getName();
					break;
				}
				if (sortEdges.get(index).getNode2() == currentNode) {
					JSONObject aLink = new JSONObject();
					aLink.put("source", currentIndex);
					sortedNodes++;
					distance = distance + sortEdges.get(index).getDistance();
					currentNode = sortEdges.get(index).getNode1();
					currentIndex++;
					aLink.put("target", currentIndex);
					aLink.put("weight", 1);
					linksjson.add(aLink);
					JSONObject aNode = new JSONObject();
					aNode.put("name", currentNode.getName());
					aNode.put("group", 1);
					nodesjson.add(aNode);
					sortEdges.remove(sortEdges.get(index));
					path = path + " -> " + currentNode.getName();
					break;
				} /* else {
					JSONObject aLink = new JSONObject();
					aLink.put("source", currentIndex);
					aLink.put("target", currentIndex + 1);
					aLink.put("weight", 0);
					linksjson.add(aLink);
				}*/
			}
			
		}
		// finalize the json objects and write to file
		currentIndex--;
		linksjson.remove(currentIndex);
		JSONObject aLink = new JSONObject();
		aLink.put("source", currentIndex);
		aLink.put("weight", 1);
		aLink.put("target", 0);
		linksjson.add(aLink);
		obj.put("nodes", nodesjson);
		obj.put("links", linksjson);
		try (FileWriter file = new FileWriter("resources/se.json")){
			file.write(obj.toJSONString());
			System.out.println("SE JSON file written.");
		} catch (IOException e) {
			e.printStackTrace();
		}
		// create an array of string objects that will hold the path and distance
		String data[] = new String[2];
		data[0] = path;
		data[1] = "" + distance;
		return data;
		
	}
	
	/**
	 * private method that takes the sorted list of edges and returns the edges that would be used in a 
	 * cycle found by using the sorted edges algorithm
	 * 
	 * @return a list of edges used in the cycle found by implementing the sorted edges algorithm
	 */
	private ArrayList<Edge> runAlgorithm(){
		// create a new empty list of edges
		ArrayList<Edge> usedEdges = new ArrayList<Edge>();
		// create an index variable to keep track of which weight in the edges list is being observed
		int index = 0;
		// create a loop that will check every edge and add it to the usedEdges list if it isn't attached to a node of
		// degree 2 or more
		while (usedEdges.size() < nodes.size() && index < edges.size()) {
			if (edges.get(index).getNode1().getDegree() < 2 && edges.get(index).getNode2().getDegree() < 2) {
				usedEdges.add(edges.get(index));
				// increment the degrees of the attached nodes
				edges.get(index).getNode1().updateDegree(+1);
				edges.get(index).getNode2().updateDegree(+1);
				// check if the added edge creates a cycle smaller than the size of the graph, and if it does 
				// undo what was done before
				if(hasCycles(usedEdges) && usedEdges.size() != nodes.size()) {
					usedEdges.remove(usedEdges.size()-1);
					edges.get(index).getNode1().updateDegree(-1);
					edges.get(index).getNode2().updateDegree(-1);
				}
			}
			// increment index
			index++;
		}
		return usedEdges;
		
	}
	
	/**
	 * private method to see if the given edges create a cycle
	 * 
	 * @param usedEdges, a list of edges to check for cycles
	 * @return true if a loop is found, false otherwise
	 */
	private boolean hasCycles(ArrayList<Edge> usedEdges) {
		// create an outer loop that checks if each of the given edges are on a cycle
		for (int index = 0; index < usedEdges.size(); index++) {
			// create a list of nodes that are added if they are adjacent to another node in the list through 
			// one of the edges in the given list
			ArrayList<Edge> adjacentEdges = new ArrayList<Edge>();
			// create a base node and current node object determined by the connected nodes of the edge being checked
			Node baseNode = usedEdges.get(index).getNode1();
			Node currentNode = usedEdges.get(index).getNode2();
			
			adjacentEdges.add(usedEdges.get(index));
			// create an inner loop that goes through the list of usedEdges to find the next node that is connected
			// through these edges
			for (int ind = 0; ind < usedEdges.size(); ind++) {
				if(!adjacentEdges.contains(usedEdges.get(ind))) { // make sure the edge being checked isn't already in the adjacentEdges list
					// if either of the connected nodes on the edge is the currentNode, add this edge to the adjacentEdges list,
					// make the other connected node the new currentNode, and restart the inner loop
					if (usedEdges.get(ind).getNode1() == currentNode) {
						adjacentEdges.add(usedEdges.get(ind));
						currentNode = usedEdges.get(ind).getNode2();
						ind = 0;
					}
					if (usedEdges.get(ind).getNode2() == currentNode) {
						adjacentEdges.add(usedEdges.get(ind));
						currentNode = usedEdges.get(ind).getNode1();
						ind = 0;
					}
				}
				// if the currentNode doesn't have a degree of 2 then there can't be a cycle in this case, 
				// so the inner loop is broken
				if (currentNode.getDegree() < 2) {
					break;
				}
				// if currentNode = baseNode, then a loop has been found so return true;
				if (currentNode == baseNode) {
					return true;
				}
			}
		}
		return false; 
	}
	
}
