import java.io.EOFException;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;
import java.util.List;


public class Graph {
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
    public Graph(Scanner scanner, int graphNum) throws EOFException, FileNotFoundException {
        graphNumber = graphNum;
        System.out.println("Beginning read of file.");
        // Read in the graph
//        Scanner scanner = new Scanner(f);
        if(!scanner.hasNextInt()){
            throw new EOFException();
        }
        int size = scanner.nextInt(); // size = # vertices
        setup(size);
        String data;
        scanner.nextLine();
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
//        scanner.close();
        System.out.println("Finished reading file.");
        System.out.println("-----------------------------------------------------------------------------------------");
//        printGraph();
    }

    public Graph(Graph g){
        totalVertices = g.totalVertices;
        totalEdges = g.totalEdges;
        graph = new HashMap<>(g.graph);
    }

//    public Graph(int v, int[][] edges){
//        totalVertices = v;
//        totalEdges = edges.length;
//        graph = new HashMap<>();
//        // representing graph as a hashs of vertices, each hash is a node, each node has a list of edges
//        for(int i = 0; i < totalVertices; i++){
//            setEdge(i, edges[i]);
//        }
//    }

    // setup method for constructors - initialize empty graph
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

    // get number of verticies in graph
    public int getTotalVertices() {
        return totalVertices;
    }

    // get all vertices in graph
    public List<Integer> getVertices() {
        return List.copyOf(graph.keySet());
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
                graph.replace(v, adj);
                // undirected graph - only count edge 1x but add both edges to list
                List<Integer> uAdj = graph.get(u);
                if(!uAdj.contains(v)){
                    uAdj.add(v);
                    graph.replace(u, uAdj);
                }

                totalEdges++;
            }
        }
    }

    public void putEdge(int v, List<Integer> edges){
        graph.replace(v, edges);
    }

    public void removeEdge(int v, int u){
        List<Integer> vList = graph.remove(v);
        List<Integer> uList = graph.remove(u);
        if(vList.contains(u)) {
            vList.remove((Integer) u);
        }
        if(uList.contains(v)){
            uList.remove((Integer) v);
            totalEdges--;
        }
        graph.put(v, vList);
        graph.put(u, uList);
    }

    public Graph getGraph(){
        return this;
    }

    public void getVertex(int v, boolean u){
        String out = v + "\tADJ: " + graph.get(v).toString();
        if(!u)
            System.out.println("V: " + out);
        else
            System.out.println("U: " + out);
    }


    public static void main(String[] args) throws EOFException, FileNotFoundException {
        File file = new File("data/graphs2022.txt");
        Scanner s = new Scanner(file);
        Graph g = new Graph(s, 0);
        g.printGraph();
        System.out.println(g.getVertices().toString());
    }
}
