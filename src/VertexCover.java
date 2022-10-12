import java.io.EOFException;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class VertexCover {
    private static Graph graph = null;

    // hashset to represent included indices in cover
    private static List<Integer> coverSet = new ArrayList<>();

    private static Boolean[] VCover = null;

    private static long runtime = 0;
    private static int K;
    private static int coverCount = 0;
    VertexCover(Graph g, int k) {
        if (k < 1 || k > g.totalVertices) {
            throw new IndexOutOfBoundsException("Please try again with a valid k value. (1 <= K <= |V|");
        } else {
            K = k;
        }
        graph = g;
        VCover = new Boolean[g.totalVertices];
        // start with max cover (every vertex)
        for (int i = 0; i < VCover.length; i++) {
            VCover[i] = false;
        }
        coverCount = 0;
        vertexCover();
//        KCover(k);

    }

    private void vertexCover(){
        long start = System.currentTimeMillis();
        coverSet.clear();
        Graph g = new Graph(graph);
        for(int i = 0; i < graph.totalVertices; i++){
            if(!VCover[i])
            {
                g = removeIndex(g, i);
                VCover[i] = true;
            } else if (!coverSet.contains(i) && coverCount < K) {
                coverSet.add(i);
                coverCount++;
            }

        }
        long end = System.currentTimeMillis();

        runtime = end - start;
    }




    // remove index from cover (do a union)
    // remove index from cover (do a union)
    private static Graph removeIndex(Graph g, int v){
        if(!VCover[v]) {
            List<Integer> adjList = graph.getEdges(v);
            for (int i = 0; i < adjList.size(); i++) {
                int u = adjList.get(i);
                if (!VCover[u]) {
                    VCover[u] = true;
                    g.removeEdge(v, u);
//                    return addIndex(g, v, u);
                }
            }
        }

        return g;

    }
    // add index u as child of v
    private static Graph addIndex(Graph g, int v, int u){
//        g.putEdge(u, new ArrayList<>());
        if(!VCover[v]) {
            VCover[u] = true;
            return g;
        }
        return removeIndex(g, u);
    }

    // check if vertex is in cover
    private static boolean inCover(int v){
        return coverSet.contains(v);
    }

    public List<Integer> getCover(){
        return coverSet;
    }

    // pretty print cover
    public void printCover(){
        StringBuilder s = new StringBuilder();
        s.append("G").append(graph.graphNumber).append(" (").append(graph.totalVertices).append(", ").append(graph.totalEdges).append(") ( ").append(coverCount).append(", ").append(runtime).append("ms) {");
        coverSet.sort(Integer::compareTo);
        for(int i = 0; i < coverSet.size(); i++){
            s.append(coverSet.get(i));
            if(i < coverSet.size()-1)
                s.append(", ");
            else
                s.append("}");
        }
        System.out.println(s);

    }

    public static void main(String[] args) throws EOFException, FileNotFoundException {
        File f = new File(args[0]);
        System.out.println("* A Minimum Vertex Cover of every graph in graphs2022.txt *\n\t(|V|, |E|) (K, ms used) Vertex Cover");
        Scanner s = new Scanner(f);
        int i = 0;
        Random r = new Random();
        Scanner key = new Scanner(System.in);
        while(true){
            try {
                Graph g = new Graph(s, i++);
                // picking random k
                int k = 1;
                System.out.print("Enter K Value: ");
                k = key.nextInt();
                key.nextLine();
                g.printGraph();
                VertexCover v = new VertexCover(g, k);
                v.printCover();
            } catch (EOFException e){
                s.close();
                break;
            }

        }

    }

}
