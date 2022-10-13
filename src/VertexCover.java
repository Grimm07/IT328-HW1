import java.io.EOFException;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class VertexCover {
    private static Graph graph = null;

    // hashset to represent included indices in cover
    private static List<Integer> coverSet = new ArrayList<>();
    private static List<Integer> nonCover = new ArrayList<>();

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
//        coverEdges = g.totalEdges;
        K = VCover.length;
        vertexCover(0, VCover.length, new ArrayList<>());


    }

    private List<Integer> vertexCover(int v, int k, List<Integer> potentialCover){
        long start = System.currentTimeMillis();
        coverSet.clear();
        for(int i = 0; i < graph.totalVertices; i++){
            if(removeIndex(i)){
                coverSet.add(i);
            }
        }
        long end = System.currentTimeMillis();

        runtime = end - start;
        return coverSet;
    }

    private boolean removeIndex(int v){
        if(!VCover[v]){
            List<Integer> adjList = graph.getEdges(v);
            VCover[v] = true;
            for(int i = 0; i < adjList.size(); i++){
                int u = adjList.get(i);
                if(coverSet.contains(u) || VCover[u])
                    continue;
                if(removeIndex(u)){
//                    VCover[v] = false;
                    VCover[u] = false;
                    coverEdges++;
                    return false;
                }
            }
            VCover[v] = true;   
            return true;
        }
        return false;

    }







    // pretty print cover
    public void printCover(){
        StringBuilder s = new StringBuilder();
//        System.out.println("K: " + K);
        s.append("G").append(graph.graphNumber).append(" (").append(graph.totalVertices).append(", ").append(graph.totalEdges).append(") ( ").append(coverSet.size()).append(", ").append(runtime).append("ms) {");
        int cnt = 0; // If we need more in the cover, add
//        while(coverSet.size() < K){
//            if(!coverSet.contains(cnt)){
//                coverSet.add(cnt++);
//            }
//        }
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
                break;
            } catch (EOFException e){
                s.close();
                break;
            }

        }

    }

}
