import java.awt.*;
import java.io.EOFException;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;
import java.util.List;


//class Edge implements Comparable<Edge> {
//    public int u;
//    public int v;
//
//    public Edge(int x, int y)
//    {
//        this.u = x;
//        this.v = y;
//    }
//
//    public int compareTo(Edge e){
//        if (u == e.u && v == e.v){ // if they are the same, return 0
//            return 0;
//        } else if(u == e.u || u == e.v || v == e.u || v == e.v){ // if edge shares a vertex
//            return -1;
//        } else {
//            return 1;
//        }
//    }
//
//    public String toString()
//    {
//        return u + " -- " + v;
//    }
//}

//class Vertex implements Comparable<Vertex>{
//    public int name;
//    public int numEdges;
//    public ArrayList<Edge> connectedEdges;
//
//    public Vertex(int vertex)
//    {
//        this.name = vertex;
//        this.numEdges = 0;
//        this.connectedEdges = new ArrayList<Edge>();
//    }
//
//    public void addEdge(Edge e){
//        numEdges++;
//        connectedEdges.add(e);
//    }
//
//    public int compareTo(Vertex v)
//    {
//        return Integer.compare(name, v.name);
//    }
//}


public class Graph {
//    private List<Entry<Integer, Integer>> Edges;
//    private List<Vertex> Vertices;
    public int totalEdges;
    public int totalVertices;
    public int graphNumber = 0;

    private HashMap<Integer, List<Integer>> graph;



    // make an empty graph
    public Graph(int v) {
        setup(v);
    }

    // constructor reads graph from file, given a starting line (reads # vertex line, then graph)
    public Graph(File f, int startLine) throws EOFException, FileNotFoundException {
        graphNumber = startLine;
        System.out.println("Beginning read of file.");
        // Read in the graph
        Scanner scanner = new Scanner(f);
        if(!scanner.hasNextInt()){
            throw new EOFException();
        }
        int size = scanner.nextInt(); // size = # vertices
        setup(size);
        String data = scanner.nextLine(); // jumping to next line?
        for (int i = 0; i < size; i++) {
            // Read in the next line
            data = scanner.nextLine();
            String[] tokens = data.split(" ");

            for (int j = 0; j < size; j++) {
                if (tokens[j].equals("1")) {
                    if(i != j){
                        setEdge(i, j);
                    }
                }
            }

        }
        scanner.close();
        System.out.println("Finished reading file.");
        System.out.println("-----------------------------------------------------------------------------------------");
//        printGraph();
    }

    public Graph(int v, List<List<Integer>> edges){
        totalVertices = v;
        totalEdges = edges.size();
        graph = new HashMap<>();
        // representing graph as a hashs of vertices, each hash is a node, each node has a list of edges
        for(int i = 0; i < v; i++){
            graph.put(i, edges.get(i));
        }
    }

    // setup method for constructor
    private void setup(int v){
        totalVertices = v;
        totalEdges = 0;
        graph = new HashMap<>();
        // representing graph as a hashs of vertices, each hash is a node, each node has a list of edges
        for(int i = 0; i < v; i++){
            graph.put(i, new ArrayList<>());
        }
    }

    public void printGraph(){
        System.out.println("Printing Graph (|V|, |E|): (" + totalVertices + ", " + totalEdges + ")");
        System.out.println("*****************************************************************************************");
        for(Map.Entry<Integer, List<Integer>> vertex: graph.entrySet()){
            System.out.print("Key: " + vertex.getKey() + " | AdjList: [ ");
            List<Integer> adj = vertex.getValue();
            for(int i=0; i < adj.size(); i++){
                if(i < vertex.getValue().size() - 1)
                    System.out.print(adj.get(i) + ",");
                else
                    System.out.print(adj.get(i));
            }
            System.out.println(" ]");
        }
        System.out.println("-----------------------------------------------------------------------------------------");
    }


    // returns adj list of vertex
    public List<Integer> getEdges(int i) {
        return graph.get(i);
    }

    // adds list if not already present
    public void setEdge(int v, int u){
        if(!graph.containsKey(v)){
            throw new NoSuchElementException("Key "+ v +" not present in graph.");
        } else if(!graph.containsKey(u)){
            throw new NoSuchElementException("Edge destination "+ u + " not present in graph.");
        } else {
            List<Integer> adj = graph.get(v);
            if(!adj.contains(u)){
                adj.add(u);
                graph.put(v, adj);
                // undirected graph - only count edge 1x but add both edges to list
                List<Integer> uAdj = graph.get(u);
                if(!uAdj.contains(v)){
                    uAdj.add(v);
                    graph.put(u, uAdj);
                }

                totalEdges++;
            }

        }
    }

    public HashMap<Integer, List<Integer>> getGraph(){
        return graph;
    }


    public static void main(String[] args) throws EOFException, FileNotFoundException {
        File file = new File("data/graphs2022.txt");
        Graph g = new Graph(file, 0);
        g.printGraph();
    }
}
