import java.io.EOFException;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;
import java.util.List;


public class Graph {

    // graph stats - total # edges present, total # vertices present, passed graph # (id)
    public int totalEdges;
    public int totalVertices;
    public int graphNumber = 0;

    // graph representation - Hashmap value contains vertices connected to key
    private HashMap<Integer, List<Integer>> graph;

    // make an empty graph
    public Graph(int v) {
        setup(v);
    }

    // scanner reads graph from file | graph num is ID | complement tells graph to read '1' vs '0' when constructing graph
    public Graph(Scanner scanner, int graphNum, boolean complement) throws EOFException, FileNotFoundException {
        // if the # vertices is not present, throw EOFException
        if(!scanner.hasNextInt()){
            throw new EOFException();
        }
        graphNumber = graphNum;
        int size = scanner.nextInt(); // size = # vertices
        setup(size); // initialize empty graph with size from scanner
        scanner.nextLine(); // skip to next line

        for (int i = 0; i < size; i++) { // iterate through each row of graph
            // Read line and split on whitespaces
            String data = scanner.nextLine();
            String[] tokens = data.split(" ");

            for (int j = 0; j < size; j++) { // iterate through each column in row & set the edge
                if(i != j) {
                    if (!complement && tokens[j].equals("1")) {
                        setEdge(i, j);
                    } else if (complement && tokens[j].equals("0")) {
                        setEdge(i, j);
                    }
                }
            }
        }

    }

    // copy constructor
    public Graph(Graph g){
        totalVertices = g.totalVertices;
        totalEdges = g.totalEdges;
        graphNumber = g.graphNumber;
        graph = new HashMap<>(g.graph);
    }

    // setup method for constructors - initialize empty graph
    private void setup(int v){
        totalVertices = v;
        totalEdges = 0;
        graph = new HashMap<>();
        // representing vertices as a hash key, each value is a list, each list contains connected vertices
        for(int i = 0; i < v; i++){
            graph.put(i, new ArrayList<>());
        }
    }

    // pretty print graph
    public void printGraph(){
        System.out.println("Printing Graph (|V|, |E|): (" + totalVertices + ", " + totalEdges + ")");
        System.out.println("*****************************************************************************************");
        for(Map.Entry<Integer, List<Integer>> vertex: graph.entrySet()){
            System.out.print("Vertex: " + vertex.getKey() + " | AdjList: [ ");
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

    // get list of all vertices in graph
    public List<Integer> getVertices() {
        return List.copyOf(graph.keySet());
    }


    // add v<->u edge
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

    // remove edge u<->v from graph
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


    public static void main(String[] args) throws EOFException, FileNotFoundException {
        File file = new File("data/graphs2022.txt");
        Scanner s = new Scanner(file);
        Graph g = new Graph(s, 0, false);
        g.printGraph();
        System.out.println(g.getVertices().toString());
    }
}
