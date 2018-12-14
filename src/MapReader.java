import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;


public class MapReader {
	// instance variables
    private String fileName;
    private String line;
	private ArrayList<Edge> edges;
	private ArrayList<Node> nodes;
    
    // constructor
    public MapReader() {
    	fileName = "resources/Map1.csv";
    	nodes = new ArrayList<Node>();
    	edges = new ArrayList<Edge>();
    	
    	
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
        				nodes.add(new Node(data[index]));
        			}
        			
                } else {
                	for (int index = 1; index < data.length; index++) {
                		if (!data[index].equals("")) {
	                		edges.add(new Edge(Integer.parseInt(data[index]), nodes.get(row-1), nodes.get(index-1)));
                		}
                	}
                }
                row ++; 
            }   

            // Always close files.
            bufferedReader.close();
            
            Collections.sort(edges);
            
            for (int index = 0; index < edges.size(); index++) {
            	edges.get(index).getNode1().addEdge(edges.get(index));
            	edges.get(index).getNode2().addEdge(edges.get(index));
            }
            
        }
        catch(FileNotFoundException ex) {
           System.out.println("Unable to open file '" + fileName + "'");                
        }
        catch(IOException ex) {
            System.out.print("Error reading file '" + fileName + "'");
        }
    }
    
    public ArrayList<Node> getNodes(){
    	return nodes;
    }
    
    public ArrayList<Edge> getEdges(){
    	return edges;
    }

                 

}
