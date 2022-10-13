import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class Clique {
    
    static Graph getGraphInverse(Graph g)
    {
        // For each item in the graph
        for(int i = 0; i < g.getSize(); i++)
        {
            for(int j = 0; j < g.getSize(); j++)
            {
                // if index == 1
                if(g.getGraph()[i][j] == 1)
                {
                    // index = 0
                    g.getGraph()[i][j] = 0;
                }
                // else index == 0
                else
                {
                    // index = 1
                    g.getGraph()[i][j] = 1;
                }
            }
        }
        
        // return inverse graph
        return g;
    }

    public static void main(String[] args)
    {
        try {
	      File myObj = new File("test.txt");
	      Scanner scanner = new Scanner(myObj);
          int size = scanner.nextInt();

	    while(size != 0)
        {
	      String data = scanner.nextLine();
          int[][] graph = new int[size][size];
          Graph endGraph = null;
	      for(int i = 0; i < size; i++)
	      {	    	  
	    	  // Read in the next line and parse
	    	  data = scanner.nextLine();  	  
	    	  String[] tokens = data.split(" ");

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
	    		  }
	    	  }

              endGraph = new Graph(size, graph);
          }

          endGraph.printGraph();

          size = scanner.nextInt();
	      }
          scanner.close();
	    }
		catch (FileNotFoundException e) {
	      System.out.println("An error occurred.");
	      e.printStackTrace();
	    }
    }
}