import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.function.Consumer;

public class DistanceConsumer implements Consumer<String> {
	// instance variables
	private HashMap<Node, List<Edge>> map;
	private ArrayList<Edge> edges;
	private ArrayList<Node> nodes;
	
	// constructor
	public DistanceConsumer(HashMap<Node, List<Edge>> aMap, ArrayList<Edge> someEdges) {
		map = aMap;
		edges = someEdges;
	}
	

	@Override
	public void accept(String line) {
		//split based on commas
		String[] data = line.split(",");
		
		if (data[0].equals("")) {
			for (int index = 1; index < data.length; index++) {
				map.put(new Node(data[index]), new ArrayList<Edge>());
				nodes.add(new Node(data[index]));
			}
		} else {
			for (int index = 0; index < data.length; index++) {
				if (!data[index].equals("")) {
					map.get(nodes.get(index)).add(new Edge(Integer.parseInt(data[index])));
				}
			}
		}
	}
	

}
