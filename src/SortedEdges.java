import java.util.ArrayList;

public class SortedEdges {
	
	// instance variables
	private ArrayList<Edge> edges;
	private ArrayList<Node> nodes;
	
	// constructor
	public SortedEdges() {
		MapReader reader = new MapReader();
		nodes = reader.getNodes();
		edges = reader.getEdges();
	}
	
	public String[] runAlgorithm(String startingName) {
		Node currentNode = null;
		Node startingNode = null;
		for (int index = 0; index < nodes.size(); index++) {
			if (nodes.get(index).getName().equals(startingName)) {
				currentNode = nodes.get(index);
				startingNode = nodes.get(index);
			}
		}
		ArrayList<Edge> sortEdges = runAlgorithm();
		String path = currentNode.getName();
		int distance = 0;
		int sortedNodes = 1;
		/*for (int index = 0; index < sortEdges.size(); index++) {
			System.out.println(sortEdges.get(index).getDistance() + ", " + sortEdges.get(index).getNode1().getName() + ", " +
					sortEdges.get(index).getNode2().getName());
		}*/
		while (sortedNodes <= nodes.size()) {
			for (int index = 0; index < sortEdges.size(); index++) {
				//System.out.println(sortedNodes);
				if (sortEdges.get(index).getNode1() == currentNode) {
					sortedNodes++;
					distance = distance + sortEdges.get(index).getDistance();
					currentNode = sortEdges.get(index).getNode2();
					sortEdges.remove(sortEdges.get(index));
					path = path + " -> " + currentNode.getName();
					break;
				}
				if (sortEdges.get(index).getNode2() == currentNode) {
					sortedNodes++;
					distance = distance + sortEdges.get(index).getDistance();
					currentNode = sortEdges.get(index).getNode1();
					sortEdges.remove(sortEdges.get(index));
					path = path + " -> " + currentNode.getName();
					break;
				}
			}
			
		}
		String data[] = new String[2];
		data[0] = path;
		data[1] = "" + distance;
		return data;
		
	}
	
	private ArrayList<Edge> runAlgorithm(){
		ArrayList<Edge> usedEdges = new ArrayList<Edge>();
		int index = 0;
		while (usedEdges.size() < nodes.size() && index < edges.size()) {
			if (edges.get(index).getNode1().getDegree() < 2 && edges.get(index).getNode2().getDegree() < 2) {
				usedEdges.add(edges.get(index));
				edges.get(index).getNode1().updateDegree(+1);
				edges.get(index).getNode2().updateDegree(+1);
				if(hasCycles(usedEdges) && usedEdges.size() != nodes.size()) {
					usedEdges.remove(usedEdges.size()-1);
					edges.get(index).getNode1().updateDegree(-1);
					edges.get(index).getNode2().updateDegree(-1);
				}
			}
			index++;
		}
		
		
		return usedEdges;
		
	}
	
	private boolean hasCycles(ArrayList<Edge> usedEdges) {
		
		for (int index = 0; index < usedEdges.size(); index++) {
			ArrayList<Edge> adjacentEdges = new ArrayList<Edge>();
			Node baseNode = usedEdges.get(index).getNode1();
			Node currentNode = usedEdges.get(index).getNode2();
			adjacentEdges.add(usedEdges.get(index));
			
			for (int ind = 0; ind < usedEdges.size(); ind++) {
				if(!adjacentEdges.contains(usedEdges.get(ind))) {
					if (usedEdges.get(ind).getNode1() == currentNode) {
						adjacentEdges.add(usedEdges.get(ind));
						currentNode = usedEdges.get(ind).getNode2();
					}
					if (usedEdges.get(ind).getNode2() == currentNode) {
						adjacentEdges.add(usedEdges.get(ind));
						currentNode = usedEdges.get(ind).getNode1();
					}
				}
				if (currentNode.getDegree() < 2) {
					break;
				}
				if (currentNode == baseNode) {
					return true;
				}
			}
		}
		
		
		return false; 
	}
	
}
