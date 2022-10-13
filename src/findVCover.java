import java.io.EOFException;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class findVCover {
    private static Graph graph = null;

    // hashset to represent included indices in cover
    private static List<Integer> coverSet = new ArrayList<>();
    private static List<Integer> nonCover = new ArrayList<>();

    private static Boolean[] VCover = null;

    private static long runtime = 0;
    private static int K;
    private static int coverEdges = 0;
    findVCover(Graph g) {
        K=1;
        VCover = new Boolean[g.getSize()];
        // start with max cover (every vertex)
        for (int i = 0; i < VCover.length; i++) {
            VCover[i] = false;
        }
//        coverEdges = g.totalEdges;
        K = VCover.length;
        vertexCover(VCover.length);


    }

    private void vertexCover(int k) {
        long start = System.currentTimeMillis();
        coverSet.clear();
        for(int i = 0; i < graph.getSize(); i++){
            if(removeIndex(graph.getGraph(), i)){
                coverSet.add(i);

            }
        }
        long end = System.currentTimeMillis();

        runtime = end - start;
    }

    private boolean removeIndex(int[][] g, int v){
        if(!VCover[v]){
//            List<Integer> adjList = (v);
            int[] adjList = g[v];
            VCover[v] = true;
            for(int i = 0; i < adjList.length; i++){
                int u = adjList[i];
                if(coverSet.contains(u) || VCover[u])
                    continue;
                if(removeIndex(g, u)){
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
        s.append("G0: ").append(" (").append(graph.getSize()).append(", ").append("_").append(") ( ").append(coverSet.size()).append(", ").append(runtime).append("ms) {");
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
        Graph g = new Graph(f);
        while(true){
            g.readGraph();
            g.printGraph();
            findVCover v = new findVCover(g);
            v.printCover();

        }

    }

}
