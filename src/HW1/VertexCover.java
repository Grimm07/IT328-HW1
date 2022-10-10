package HW1;

import java.util.HashSet;
import java.util.List;

public class VertexCover {
    private static Graph graph = null;

    // hashset to represent included indices in cover
    private static HashSet<Integer> coverSet = new HashSet<>();

    // boolean array indicating what idx is in cover
    // is this needed or can we replace it with the hashet?
    private static boolean[] VCover = null;
    private static int coverCount = 0;
    VertexCover(Graph g){
        graph = g;
        // start with max cover (every vertex)
        for (int i = 0; i < VCover.length; i++) {
            addIndex(i);
        }
        // getting the minimum vertex cover (first one that works up until every vertex is in the cover) - i = k here
        for(int i = 0; i <= coverCount; i++){
            if(getKVertexCover(0,i))
                break;
        }
    }

    private static boolean getKVertexCover(int v, int k){
        // if we already have the vertex cover, no need to continue (check coverset for list of idx)
        if(k >= coverCount){
            return true;
        } else if(k < graph.totalEdges/2 || v >= graph.totalVerticies){
            return false;
        }
        // if the current vertex is not in cover, then add it and increment count
        Vertex currV = graph.getVertex(v);
        for(Edge currEdge : currV.connectedEdges)
        {
            // if the connecting vertex is in cover, try to remove current vertex
            if(VCover[currEdge.v2]){
                removeIndex(currEdge.v2); // v == currEdge.v1
                // if we can't get the cover set without this vertex, re-add it
                if(!getKVertexCover(currEdge.v2, k)){
                    addIndex(currEdge.v2);
                }
            }
            // the following else block is only a potential route in case the preceding if statement does not work
            //else {
//                removeIndex(v);
//                addIndex(currEdge.v2);
//                // if swapping the verticies does not work, remove v2 and readd v1
//                if(!getKVertexCover(currEdge.v2, k)){
//                    removeIndex(currEdge.v2);
//                    addIndex(v);
//                }
//            }


        }
        return getKVertexCover(++v, ++k);
    }

    // adds index/vertex to cover
    private static void addIndex(int v){
        VCover[v] = true;
        coverSet.add(v);
        coverCount++;
    }

    // removes index/vertex to cover
    private static void removeIndex(int v){
        VCover[v] = false;
        coverSet.remove(v);
        coverCount--;
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

}
