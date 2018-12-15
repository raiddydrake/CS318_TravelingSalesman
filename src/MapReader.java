import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;

/**
 * Map Reader class definition
 * 
 * @author Conner Shumway, Raiddy Drake and Amber Lopata
 *
 */

public class MapReader {
	// instance variables
    private String fileName;
    private String line;
	private ArrayList<Edge> edges;
	private ArrayList<Node> nodes;
    
    // constructor
    public MapReader() {
    	// ENTER THE NAME OF THE FILE WITH THE DISTANCE TABLE HERE
    	fileName = "resources/MaggieFionaMap.csv";
    	
    	// initialize nodes and edges
    	nodes = new ArrayList<Node>();
    	edges = new ArrayList<Edge>();
    	
    	
    	try {
            // FileReader reads text files in the default encoding
    		FileReader fileReader = new FileReader(fileName);

            //  wrap FileReader in BufferedReader
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            
            // start a loop that will run through the distance table until all values are read
            // lines on the distance table are read left to right
            int row = 0;
            while((line = bufferedReader.readLine()) != null) {
            	// split based on commas
                String[] data = line.split(",");
                
                // if reading the top row, make a new node object for each name and add that node to the nodes list
                if (row == 0) {
        			for (int index = 1; index < data.length; index++) {
        				nodes.add(new Node(data[index]));
        			}
        			
                } else {
                	// for each distance value, create a new edge object with that distance and 
                	// connected nodes based on the row and column it's in
                	for (int index = 1; index < data.length; index++) {
                		if (!data[index].equals("")) {
	                		edges.add(new Edge(Integer.parseInt(data[index]), nodes.get(row-1), nodes.get(index-1)));
                		}
                	}
                }
                row ++; 
            }   

            // close files
            bufferedReader.close();
            
            // sort the list of edges based on distances
            Collections.sort(edges);
            
            // add each edge to the lists of edges in their connected nodes
            for (int index = 0; index < edges.size(); index++) {
            	edges.get(index).getNode1().addEdge(edges.get(index));
            	edges.get(index).getNode2().addEdge(edges.get(index));
            }
            
        }
    	// catch errors if they occur
        catch(FileNotFoundException ex) {
           System.out.println("Unable to open file '" + fileName + "'");                
        }
        catch(IOException ex) {
            System.out.print("Error reading file '" + fileName + "'");
        }
    }
    
    /**
     * @return the list of node objects
     */
    public ArrayList<Node> getNodes(){
    	return nodes;
    }
    
    /**
     * @return the list edge objects
     */
    public ArrayList<Edge> getEdges(){
    	return edges;
    }

                 

}
