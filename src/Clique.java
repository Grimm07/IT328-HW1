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
        Graph graph = new Graph();

        graph.readGraph();

        getGraphInverse(graph);
    }
}