import java.util.ArrayList;

public class NearestNeighbor {
	// instance variables
	private ArrayList<Node> nodes;
	
	// constructor
	public NearestNeighbor() {
		MapReader reader = new MapReader();
		nodes = reader.getNodes();
		
	}
	
	
	public String[] runAlgorithm(String startingName){
		Node currentNode = null;
		Node startingNode = null;
		for (int index = 0; index < nodes.size(); index++) {
			if (nodes.get(index).getName().equals(startingName)) {
				currentNode = nodes.get(index);
				startingNode = nodes.get(index);
				break;
			}
		}
		int distance = 0;
		String path = currentNode.getName();
		int usedNodes = 1;
		while (usedNodes <= nodes.size()) {
			for (int index = 0; index < nodes.size()-1; index++) {
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
				
					// final if statements
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
		String data[] = new String[2];
		data[0] = path;
		data[1] = "" + distance;
		return data;
	}

}
