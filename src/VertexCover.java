import javax.management.StringValueExp;
import java.io.EOFException;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.function.IntFunction;

public class VertexCover {
    private static Graph graph = null;

    // hashset to represent included indices in cover
    private static HashSet<Integer> coverSet = new HashSet<>();

    // boolean array indicating what idx is in cover
    // is this needed or can we replace it with the hashet?
    private static Boolean[] VCover = null;
    private static int coverCount = 0;
    VertexCover(Graph g, int k) {
        if (k < 1 || k > g.totalVertices) {
            throw new IndexOutOfBoundsException("Please try again with a valid k value. (1 <= K <= |V|");
        }


        graph = g;
        VCover = new Boolean[g.totalVertices];

        // start with max cover (every vertex)
        for (int i = 0; i < VCover.length; i++) {
            VCover[i] = true;
        }
        coverCount = VCover.length;
//        KCover(k);

    }

//    public void KCover(int k){
//        HashMap<Integer, List<Integer>> edgeList = graph.getGraph();
//        long start = System.currentTimeMillis();
//        // getting the minimum vertex cover (first one that works up until every vertex is in the cover) - i = k here
//        while(k >= graph.totalEdges) // ?
//        {
//
//            for(int v = 0; v < graph.totalVertices; v++){
//                for(int u = 0; u < graph.totalVertices; u++){
//                    if(u != v && VCover[v] && VCover[u]){ // if this edge contains nodes both in cover, remove one
//
//                    }
//                }
//            }
//            for(Edge currEdge : currentVertex.connectedEdges)
//            {
//                if(vCover[currEdge.v] == false) {}
//                else
//                {
//                    k++;
//                }
//            }
////            numVertex++;
//            vCover[currentVertex.name] = false;
//            vertexList.remove(0);
//        }
//        long runtime = System.currentTimeMillis() - start;
//        System.out.println("G" + graph.graphNumber + " ( "+ graph.totalVertices + ", " +graph.totalEdges + ")" + " (" + coverCount + ", " + runtime + ")\t" + coverSet.toString());
//    }


//    private void vertexCover(List<Vertex> vertexList, int k){
////		ArrayList<Vertex> orderedList = vertexList;
//        vertexList.sort(null);
//
//        while(k >= totalEdge/2)
//        {
//            Vertex currentVertex = vertexList.get(0);
//
//            for(Edge currEdge : currentVertex.connectedEdges)
//            {
//                if(vCover[currEdge.v] == false) {}
//                else
//                {
//                    k++;
//                }
//            }
////            numVertex++;
//            vCover[currentVertex.name] = false;
//            vertexList.remove(0);
//        }
//    }

    // adds index/vertex to cover
    private static void addIndex(int v){
        if(!VCover[v]){
            VCover[v] = true;
            coverSet.add(v);
            coverCount++;
        }

    }

    // removes index/vertex to cover
    private static void removeIndex(int v){
        if(VCover[v]){
            VCover[v] = false;
            coverSet.remove(v);
            coverCount--;
        }
    }

    // check if vertex is in cover
    private static boolean inCover(int v){
        return coverSet.contains(v);
    }

    // pretty print cover
    public void printCover(){
        System.out.println("Cover Count Total: " + coverCount);
        System.out.println("-----------------------------------------------------------------------------------------");
        int count = 0;
        for(int i :coverSet){
            System.out.print(i);
            if(++count < coverCount)
                System.out.print(",");
        }
        System.out.println("*****************************************************************************************");
    }

    public static void main(String[] args) throws EOFException, FileNotFoundException {
        Graph g = new Graph(new File(args[0]), 0);
        g.printGraph();
        IntFunction tmp = (i) -> i + 1;
        System.out.println(tmp.apply(1));

        VertexCover v = new VertexCover(g, 3);
//        System.out.println("Getting vertex cover of k=3");

//        v.getKVertexCover(0, 3);
//        v.printCover();

    }

}
