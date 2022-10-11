//import java.util.*;
//import java.io.*; // Import this class to handle io
//import java.util.Scanner; // Import the Scanner class to read text files
//
//
//public class findVCover
//{
//    public static void main(String[] args)
//    {
//        int size = 0;
//        int[][] graph = null;
//        boolean[] vCover = null;
//        ArrayList<Edge> edgeList = new ArrayList<Edge>();
//        ArrayList<Vertex> vertexList = new ArrayList<Vertex>();
//        int numVertex = 0;
//        Edge edge = null;
//
//
//        // Read in the graph
//        try {
//            File myObj = new File(args[0]);
//            Scanner scanner = new Scanner(myObj);
//            size = scanner.nextInt();
//            vCover = new boolean[size];
//            graph = new int[size][size];
//            String data = scanner.nextLine();
//            for(int i = 0; i < size; i++)
//            {
//                // Set vCover to true
//                vCover[i] = true;
//
//                // Read in the next line and parse
//                data = scanner.nextLine();
//                String[] tokens = data.split(" ");
//
//                Vertex vertex = new Vertex(i);
//
//                for(int j = 0; j < size; j++)
//                {
//                    if(i == j)
//                    {
//
//                    }
//                    else if(tokens[j].equals("1"))
//                    {
//                        // Create graph
//                        graph[i][j] = 1;
//
//                        // List of edges
//                        edge = new Edge(i, j);
//                        edgeList.add(edge);
//                        vertex.connectedEdges.add(edge);
//                        vertex.numEdges++;
//                        totalEdge++;
//                    }
//                }
//
//                vertexList.add(vertex);
//            }
//            scanner.close();
//        }
//        catch (FileNotFoundException e) {
//            System.out.println("An error occurred.");
//            e.printStackTrace();
//        }
//
//        // Print graph
////		for(int i = 0; i < size; i++)
////		{
////			for(int j = 0; j < size; j++)
////			{
////				System.out.print(graph[i][j] + " ");
////			}
////			System.out.println("");
////		}
//
//        // Print list of edges
////		System.out.println(edgeList);
//
//        long start = System.currentTimeMillis();
//
//
//
//        long end = System.currentTimeMillis();
//
////		for(Vertex vertex : vertexList)
////		{
////			System.out.println(vertex.name + " (" + vertex.numEdges + ") - " + vertex.connectedEdges);
////		}
//
//        System.out.print("( " + size + ", " + totalEdge/2 + ") ");
//        System.out.print("(size=" + numVertex + " ms=" + (end-start) + ") ");
//        System.out.print("{");
//
//        for(int i = 0; i < size; i++)
//        {
//            if(vCover[i] == false)
//            {
//                System.out.print(i + " ");
//            }
//        }
//
//        System.out.print("}");
//    }
//    private boolean[] vCover = null;
//    private static int totalEdge = 0;
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
//}