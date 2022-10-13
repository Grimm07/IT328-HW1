import java.io.EOFException;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;
import java.util.List;

public class Graph {
    private ArrayList<Edge> edgeList;
    private ArrayList<Vertex> vertexList;
    private int size;
    private int[][] graph;

    private Scanner graphScan;

    Graph(File f) throws FileNotFoundException {
        graphScan = new Scanner(f);
        this.edgeList = new ArrayList<Edge>();
        this.vertexList = new ArrayList<Vertex>();
    }

    Graph(int sz, int[][] list)
    {
        this.edgeList = new ArrayList<Edge>();
        this.vertexList = new ArrayList<Vertex>();
        this.size = sz;
        this.graph = list;

        createLists(sz, list);
    }

    // Getter for vertex list
    public ArrayList<Vertex> getVertexList()
    {
        return vertexList;
    }

    //Setter for size
    public void setSize(int sz)
    {
        this.size = sz;
    }

    // Getter for size
    public int getSize()
    {
        return size;
    }

    // Setter for graph
    public void setGraph(int sz)
    {
        this.graph = new int[sz][sz];
    }

    // Getter for graph
    public int[][] getGraph()
    {
        return graph;
    }

    public void createLists(int sz, int[][] list)
    {
        for(int i = 0; i < sz; i++)
        {
            Vertex vertex = new Vertex(i);

            for(int j = i; j < sz; j++)
            {
                if(list[i][j] == 1)
                {
                    Edge edge = new Edge(i, j);
                    vertex.connectedEdges.add(edge);
                    this.edgeList.add(edge);
                }
            }

            this.vertexList.add(vertex);
        }
    }

    // Read in graph
    public void readGraph()
    {
		try {
          if(!graphScan.hasNextInt()){
              throw new FileNotFoundException();
          }
	      setSize(graphScan.nextInt());
          setGraph(size);

	      String data = graphScan.nextLine();
	      for(int i = 0; i < size; i++)
	      {	    	  
	    	  // Read in the next line and parse
	    	  data = graphScan.nextLine();
	    	  String[] tokens = data.split(" ");
	    	  
	    	  Vertex vertex = new Vertex(i);

	    	  for(int j = 0; j < size; j++)
	    	  {
	    		  if(i == j)
	    		  {
	    			  // if i = j, ignore it
	    		  }
	    		  else if(tokens[j].equals("1"))
	    		  {
	    			  // Create graph
	    			  graph[i][j] = 1;

                      // List of edges
	    			  Edge edge = new Edge(i, j);
	    			  edgeList.add(edge);
	    			  vertex.connectedEdges.add(edge);
	    		  }
	    	  }
	    	  vertexList.add(vertex);
	      }
	    }
		catch (FileNotFoundException e) {
	      graphScan.close();
	    }
    }

    // Prints current representation of graph
    public void printGraph()
    {
        for(int i = 0; i < size; i++)
        {
            for(int j = 0; j < size; j++)
            {
                System.out.print(graph[i][j]);
            }
            System.out.println("");
        }
    }
}
