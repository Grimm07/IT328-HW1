//import java.io.File;
//import java.io.FileNotFoundException;
//import java.util.ArrayList;
//import java.util.Scanner;
//
//
//
//public class Clique {
//    public Clique(Graph g, int n){
//        System.out.println("*\tMax Cliques in graphs in graphs2022.txt (Reduced to K-Vertex Cover)\t*");
//        System.out.println("(|V|, |E|) (size, ms used) Cliques");
//        System.out.println("G%d (%d, %d) (size=%d, ms=%d)");
//        int count = 0;
//        //TODO
//        for(int i = 0; i < g.totalVertices; i++){
//
//        }
//
//        System.out.println("****");
//    }
//
//    public static void getCover(int totalEdge){
//        int totalCover = 0;
////		ArrayList<Vertex> orderedList = vertexList;
//        vertexList.sort(null);
//
//        while(totalCover < totalEdge/2)
//        {
//            Vertex currentVertex = vertexList.get(0);
//
//            for(Edge currEdge : currentVertex.connectedEdges)
//            {
//                if(vCover[currEdge.v] == false) {
//
//                }
//                else
//                {
//                    totalCover++;
//                }
//            }
//            numVertex++;
//            vCover[currentVertex.name] = false;
//            vertexList.remove(0);
//        }
//
//    }
//
//    public static int numVertex = 0;
//    public static boolean[] vCover = null;
//    public static ArrayList<Vertex> vertexList;
//
//
//    public static void main(String[] args) {
//            int size = 0;
//            int[][] graph = null;
//            ArrayList<Edge> edgeList = new ArrayList<Edge>();
//            vertexList = new ArrayList<Vertex>();
//            int totalEdge = 0;
//
//            Edge edge = null;
//
//            System.out.println("------------------------------------------------------------------------------------");
//            System.out.println("Printing Complemnt HW1.Graph");
//            System.out.println("************************************************************************************");
//
//            // Print graph
//            for(int i = 0; i < size; i++)
//            {
//                for(int j = 0; j < size; j++)
//                {
//                    System.out.print(graph[i][j] + " ");
//                }
//                System.out.println();
//            }
//
//
//        // Print list of edges
////		System.out.println(edgeList);
//
//            long start = System.currentTimeMillis();
//
//
////		ArrayList<Vertex> orderedList = vertexList;
////            vertexList.sort(null);
//            System.out.println(vertexList.size());
//            getCover(totalEdge);
//            long end = System.currentTimeMillis();
//
//            for(Vertex vertex : vertexList)
//            {
//                System.out.println(vertex.name + " (" + vertex.numEdges + ") - " + vertex.connectedEdges);
//            }
//            System.out.print("( " + size + ", " + totalEdge/2 + ") ");
//            System.out.print("(size=" + numVertex + " ms=" + (end-start) + ") ");
//            System.out.print("{");
//            for(int i = 0; i < size; i++)
//            {
//                if(vCover[i] == false)
//                {
//                    System.out.print(i + " ");
//                }
//            }
//
//
//            System.out.print("}");
//        }
//}
//
