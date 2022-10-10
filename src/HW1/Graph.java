package HW1;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;


class Edge{
    public int v1;
    public int v2;

    public Edge(int x, int y)
    {
        this.v1 = x;
        this.v2 = y;
    }

    public String toString()
    {
        return v1 + " -- " + v2;
    }
}

class Vertex implements Comparable<Vertex>{
    public int name;
    public int numEdges;
    public ArrayList<Edge> connectedEdges;

    public Vertex(int vertex)
    {
        this.name = vertex;
        this.numEdges = 0;
        this.connectedEdges = new ArrayList<Edge>();
    }

    public int compareTo(Vertex v)
    {
        if(this.numEdges < v.numEdges)
        {
            return 1;
        }
        else if(this.numEdges > v.numEdges)
        {
            return -1;
        }
        else
        {
            return 0;
        }
    }
}


public class Graph {
    private LinkedList<Integer> graph[];
    public int totalEdges;
    public int totalVerticies;



    // make an empty graph
    public Graph(int v) {

        this.graph = new LinkedList[v];
        for(int i = 0; i < v; i++)
            graph[i] = new LinkedList<>();
    }

    public Vertex getVertex(int i) {
        return graph[i];
    }

    public void addEdge(Integer v, Integer k) {
        if((0 <= v && v <= this.graph.length) && (0 <= k && k <= this.graph.length)) {
            this.graph[v].add(k);
            this.graph[k].add(v);

        } else {
            throw new IndexOutOfBoundsException();
        }

    }

    public static void main(String[] args) {
        int size = 0;
        int[][] graph = null;
        boolean[] vCover = null;
        ArrayList<Edge> edgeList = new ArrayList<Edge>();
        ArrayList<Vertex> vertexList = new ArrayList<Vertex>();
        int totalEdge = 0;
        int numVertex = 0;
        Edge edge = null;

        // Read in the graph
        try {
            File myObj = new File(args[0]);
            Scanner scanner = new Scanner(myObj);
            size = scanner.nextInt();
            vCover = new boolean[size];
            graph = new int[size][size];
            String data = scanner.nextLine();
            for (int i = 0; i < size; i++) {
                // Set vCover to true
                vCover[i] = true;

                // Read in the next line and parse
                data = scanner.nextLine();
                String[] tokens = data.split(" ");

                Vertex vertex = new Vertex(i);

                for (int j = 0; j < size; j++) {
                    if (i == j) {

                    } else if (tokens[j].equals("1")) {
                        // Create graph
                        graph[i][j] = 1;

                        // List of edges
                        edge = new Edge(i, j);
                        edgeList.add(edge);
                        vertex.connectedEdges.add(edge);
                        vertex.numEdges++;
                        totalEdge++;
                    }
                }

                vertexList.add(vertex);
            }
            scanner.close();
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }
}
