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
    private static int coverEdges = 0;
    VertexCover(Graph g) {
        K=1;
        graph = g;
        VCover = new Boolean[g.totalVertices];
        // start with max cover (every vertex)
        for (int i = 0; i < VCover.length; i++) {
            VCover[i] = false;
        }
        vertexCover();

    }

    private void vertexCover(){
        long start = System.currentTimeMillis();
        coverEdges = 0;
        coverSet.clear();
        Graph g = new Graph(graph);
        for(int i = 0; i < graph.totalVertices; i++){
            if(!VCover[i])
            {
                removeIndex(g, i);

            } else {
                coverEdges++;
                coverSet.add(i);
            }

        }
        long end = System.currentTimeMillis();

        runtime = end - start;
    }




    // remove index from cover (do a union)
    private static void removeIndex(Graph g, int v){
        if(!VCover[v]) {
            List<Integer> adjList = graph.getEdges(v);
            for (int u : adjList) {
                if (!VCover[u]) {
                    VCover[u] = true;
                    g.removeEdge(v, u);
                    VCover[v] = true;
                    return;
                }
            }

        }

    }

    private boolean KCover(int k){
        K=k;
        vertexCover();
        return coverSet.size() <= k;
    }

    // pretty print cover
    public void printCover(){
        StringBuilder s = new StringBuilder();
        s.append("G").append(graph.graphNumber).append(" (").append(graph.totalVertices).append(", ").append(graph.totalEdges).append(") ( ").append(coverSet.size()).append(", ").append(runtime).append("ms) {");
        if(coverSet.size() + coverEdges > graph.totalVertices && K <= graph.totalVertices) { // if cover doesn't fit, try again with a bigger K
            K++;
            vertexCover();
            return;
        }
        int cnt = 0; // If we need more in the cover, add
        while(coverSet.size() < K){
            if(!coverSet.contains(cnt)){
                coverSet.add(cnt++);
            }
        }
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

    public static void main(String[] args) throws FileNotFoundException {
        File f = new File(args[0]);
        System.out.println("* A Minimum Vertex Cover of every graph in graphs2022.txt *\n\t(|V|, |E|) (K, ms used) Vertex Cover");
        Scanner s = new Scanner(f);
        int i = 0;
        while(true){
            try {
                Graph g = new Graph(s, i++, false);
                g.printGraph();
                VertexCover v = new VertexCover(g);
                v.printCover();
            } catch (EOFException e){
                s.close();
                break;
            }

        }

    }

}
