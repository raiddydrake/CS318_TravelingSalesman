public class AlgorithmTester {

	public static void main(String[] args) {
		StopWatch NNWatch = new StopWatch();
		StopWatch SEWatch = new StopWatch();
		
		// create new algorithm objects
		NearestNeighbor NN = new NearestNeighbor();
		SortedEdges SE = new SortedEdges();
		
		NNWatch.start();
		
		// run each of the algorithm methods
		String NNdata[] = NN.runAlgorithm("A");
		
		NNWatch.stop();
		SEWatch.start();
		
		String SEdata[] = SE.runAlgorithm("A");
		
		SEWatch.stop();
		
		// print data received by the algorithm in an easily readable way 
		System.out.println("Nearest Neighbor:");
		System.out.println(NNdata[0]);
		System.out.println("Total Distance = " + NNdata[1]);
		
		System.out.println(NNWatch.getElapsedTime() + " sec");
		
		System.out.println("---");
		
		System.out.println("Sorted Edges:");
		System.out.println(SEdata[0]);
		System.out.println("Total Distance = " + SEdata[1]);
		
		System.out.println(SEWatch.getElapsedTime() + " sec");
		
		
		// unnecessary code for printing all nodes and edges (to check if MapReader is working properly)
		/*
		MapReader mapReader = new MapReader();
		
		ArrayList<Edge> edges = mapReader.getEdges();
		ArrayList<Node> nodes = mapReader.getNodes();
		
		for (int index = 0; index < nodes.size(); index++) {
			System.out.println(nodes.get(index).getName());
			for (int ind = 0; ind < nodes.size() - 1; ind++) {
				System.out.println("-- " + nodes.get(index).getConnectedEdges().get(index).getDistance() + "," + 
						nodes.get(index).getConnectedEdges().get(index).getNode1().getName() + "," + 
						nodes.get(index).getConnectedEdges().get(index).getNode2().getName());
			}
		}
		*/

	}

}
