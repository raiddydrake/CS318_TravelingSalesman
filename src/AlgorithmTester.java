import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class AlgorithmTester {

	public static void main(String[] args) {
		MapReader mapReader = new MapReader();
		
		ArrayList<Edge> edges = mapReader.getEdges();
		
		for (int index = 0; index < edges.size(); index++) {
			System.out.println(edges.get(index).getDistance() + ", " + edges.get(index).getNode1().getName()
					+ ", " + edges.get(index).getNode2().getName());
		}
		

	}

}
