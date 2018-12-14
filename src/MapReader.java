import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class MapReader {
	// instance variables
    private String fileName;
    private String line;
    private HashMap<Node, List<Edge>> map;
	private ArrayList<Edge> edges;
	private ArrayList<Node> nodes;
    
    // constructor
    public MapReader() {
    	fileName = "resources/Map1.csv";
    	edges = new ArrayList<Edge>();
    	map = new HashMap<Node, List<Edge>>();
    	nodes = new ArrayList<Node>();
    	
    	
    	try {
            // FileReader reads text files in the default encoding.
    		FileReader fileReader = new FileReader(fileName);

            // Always wrap FileReader in BufferedReader.
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            
            int row = 0;
            while((line = bufferedReader.readLine()) != null) {
            	// split based on commas
                String[] data = line.split(",");
                
                if (data[0].equals("")) {
        			for (int index = 1; index < data.length; index++) {
        				map.put(new Node(data[index]), new ArrayList<Edge>());
        				nodes.add(new Node(data[index]));
        			}
        			
                } else {
                	for (int index = 1; index < data.length; index++) {
                		if (!data[index].equals("")) {
	                		map.get(nodes.get(index)).add(new Edge(Integer.parseInt(data[index]),
	                				nodes.get(row-1), nodes.get(index-1)));
	                		edges.add(new Edge(Integer.parseInt(data[index]), nodes.get(row), nodes.get(index)));
                		}
                	}
                }
                row ++; 
            }   

            // Always close files.
            bufferedReader.close();         
        }
        catch(FileNotFoundException ex) {
           System.out.println("Unable to open file '" + fileName + "'");                
        }
        catch(IOException ex) {
            System.out.print("Error reading file '" + fileName + "'");
        }
    }
    
    public HashMap<Node, List<Edge>> getMap(){
    	return map;
    }
    
    public ArrayList<Edge> getEdges(){
    	return edges;
    }

                 

}
