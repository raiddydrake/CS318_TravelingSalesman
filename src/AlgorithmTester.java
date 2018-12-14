import java.util.ArrayList;


public class AlgorithmTester {

	public static void main(String[] args) {
		MapReader mapReader = new MapReader();
		
		ArrayList<Edge> edges = mapReader.getEdges();
		ArrayList<Node> nodes = mapReader.getNodes();
		
		NearestNeighbor NN = new NearestNeighbor();
		SortedEdges SE = new SortedEdges();
		
		String NNdata[] = NN.runAlgorithm("Canton");
		String SEdata[] = SE.runAlgorithm("Canton");
		
		System.out.println("Nearest Neighbor:");
		System.out.println(NNdata[0]);
		System.out.println("Total Distance = " + NNdata[1]);
		
		System.out.println("---");
		
		System.out.println("Sorted Edges:");
		System.out.println(SEdata[0]);
		System.out.println("Total Distance = " + SEdata[1]);
		
		
		/*for (int index = 0; index < nodes.size(); index++) {
			System.out.println(nodes.get(index).getName());
			for (int ind = 0; ind < nodes.size() - 1; ind++) {
				System.out.println("-- " + nodes.get(index).getConnectedEdge(ind).getDistance() + "," + 
						nodes.get(index).getConnectedEdge(ind).getNode1().getName() + "," + 
						nodes.get(index).getConnectedEdge(ind).getNode2().getName());
			}
		}
		
		for (int index = 0; index < edges.size(); index++) {
			System.out.println(edges.get(index).getDistance() + ", " + edges.get(index).getNode1().getName() + ", " + 
					edges.get(index).getNode2().getName());
		}*/

	}

}
