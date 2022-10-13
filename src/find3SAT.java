//Juan Carbajal
import java.io.*;
import java.io.PrintWriter;
import java.util.Random;
import java.util.Scanner;
import java.util.*;

class CNF
{
  private int clauses;
  private int variables;
  private int vertices;
  public int is1True = 0;
  public int is2True = 0;
  public int is3True = 0;
  public int is4True = 0;
  public int[][] arr = new int[3][10];


  //Setter for the number of clauses in a 3CNF.
  public void setClauses(int clauses)
  {
    this.clauses = clauses;
  }

  //Setter for the number of variables in a 3CNF.
  public void setVariables(int variables)
  {
    this.variables = variables;
  }

  //Setter for the number of vertices given from a 3CNF.
  public void setVertices(int variables, int clauses)
  {
    this.vertices = ((variables * 2) + (clauses * 3));
  }

  //Getter for the number of clauses.
  public int getClauses()
  {
    return clauses;
  }

  //Getter for the number of variables.
  public int getVariables()
  {
    return variables;
  }

  //Getter for the number of vertices.
  public int getVertices()
  {
    return vertices;
  }
}

class vtx
{
  private int value;

  //Setter for the value inside of a vtx.
  public void setValue(int value)
  {
    this.value = value;
  }

  //Getter for the value inside of a vtx.
  public int getValue()
  {
    return value;
  }
}

public class find3SAT
{

  //Adds an edge from the vertices listed in the parameters.
  static void insertEdge(ArrayList<Integer> list[],int i, int j)
  {
    list[i].add(j);
    return;
  }

  //Prints out the adjacency list.
  static void printAdjList(ArrayList<Integer> list[],int V)
  {
    for(int i = 0; i < V; i++)
    {
      System.out.print(i);

      for(int j : list[i])
        System.out.print(" -> " + j);

      System.out.println();
    }
    System.out.println();
  }

  //Converts the adjacency list into a adjacency matrix.
  static int[][] convertList(ArrayList<Integer> list[],int vertices)
  {
    int [][]matrix = new int[vertices][vertices];

    for(int i = 0; i < vertices; i++)
    {
        for(int j : list[i])
            matrix[i][j] = 1;
      }
    return matrix;
  }



  //Prints the adjacency matrix to output.
  static void printAdjMatrix(int[][] list, int vertices)
  {
    for(int i = 0; i < vertices; i++)
    {
      for(int j = 0; j < vertices; j++)
      {
        System.out.print(list[i][j] + " ");
      }

      System.out.println();
    }

    System.out.println();
  }


  public static void main(String args[]) throws Exception
  {
    //Create all 3CNF objects.
    CNF cnf[] = new CNF[20];

    //Create array of vertices.
    vtx v[];

    //Initialize
    for (int i = 0; i < cnf.length; i++)
    {
      cnf[i] = new CNF();
    }


    //User input of file containing 3CNFs.
    File file = new File(args[0]);

    //File for input of adjacency matrices of the 3CNF graphs.


    Random rand = new Random();
    Scanner sc = new Scanner(file);
    long start;
    long end;
    int satisfiablity;
    boolean isSatisfiable = false;
    int varTracker = 0;
    int[] variables;
    int[] vertices;
    int ver = 0;
    int var = 1;
    int i = 0;
    int j = 0;
    int k = 0;
    int h = 0;
    int e;

    //Read through file, update each CNF object with information from the file.
    while (sc.hasNextLine())
    {
      String s = sc.nextLine();
      String[] numbers = s.split(" ");

      while (i < numbers.length)
      {
        e = Integer.parseInt(numbers[i]);

        if(e > varTracker)
        {
          varTracker = e;
        }

        if(k > 2)
        {
          h++;
          k = 0;
        }

        cnf[j].arr[k][h] = e;
        //System.out.println("In the array: " + cnf[j].arr[k][h]);

        k++;
        i++;
      }

      //Set the amount of clauses for the 3CNF.
      cnf[j].setClauses(i / 3);

      //Set the amount of variables for the 3CNF.
      cnf[j].setVariables(varTracker);

      //Set the amount of vertices made from the 3CNF.
      cnf[j].setVertices(cnf[j].getVariables(),cnf[j].getClauses());

      k = 0;
      h = 0;
      i = 0;
      varTracker = 0;
      j++;

      System.out.println();
    }

    //Turn 3CNF into an adjacency list, which is turned into a readable adjacency matrix.
    for(i = 0; i < cnf.length; i++)
    {
      ArrayList<Integer> []list = new ArrayList[cnf[i].getVertices()];

      vertices = new int[cnf[i].getVertices()];

      for(j = 0; j < vertices.length; j++)
      {
        vertices[j] = ver;
        ver++;
      }

      for (j = 0; j < list.length; j++)
      {
        list[j] = new ArrayList<Integer>();
      }

      //Initialize vtx objects
      v = new vtx[vertices.length];

      for(j = 0; j < v.length; j++)
      {
        v[j] = new vtx();
      }

      //Set the default values for the vertices that make up the beginning variables.
      if(cnf[i].getVariables() == 2)
      {
        v[0].setValue(1);
        v[1].setValue(-1);
        v[2].setValue(2);
        v[3].setValue(-2);
      }
      else if(cnf[i].getVariables() == 3)
      {
        v[0].setValue(1);
        v[1].setValue(-1);
        v[2].setValue(2);
        v[3].setValue(-2);
        v[4].setValue(3);
        v[5].setValue(-3);

      }
      else if (cnf[i].getVariables() == 4)
      {
        v[0].setValue(1);
        v[1].setValue(-1);
        v[2].setValue(2);
        v[3].setValue(-2);
        v[4].setValue(3);
        v[5].setValue(-3);
        v[6].setValue(4);
        v[7].setValue(-4);
      }

      k = 0;
      h = 0;

      //Set the values for the vertices from the clauses.
      for(j = (cnf[i].getVariables() * 2); j < cnf[i].getVertices(); j++)
      {
        v[j].setValue(cnf[i].arr[k][h]);

        k++;

        if(k > 2)
        {
          h++;
          k = 0;
        }

      }

//      System.out.println("Graph: " + (i+1));

      //Insert the default edges for the vertices that make up the beginning variables.
      if(cnf[i].getVariables() == 2)
      {
        insertEdge(list, 0, 1);
        insertEdge(list, 1, 0);
        insertEdge(list, 2, 3);
        insertEdge(list, 3, 2);
      }
      else if(cnf[i].getVariables() == 3)
      {
        insertEdge(list, 0, 1);
        insertEdge(list, 1, 0);
        insertEdge(list, 2, 3);
        insertEdge(list, 3, 2);
        insertEdge(list, 4, 5);
        insertEdge(list, 5, 4);
      }
      else if (cnf[i].getVariables() == 4)
      {
        insertEdge(list, 0, 1);
        insertEdge(list, 1, 0);
        insertEdge(list, 2, 3);
        insertEdge(list, 3, 2);
        insertEdge(list, 4, 5);
        insertEdge(list, 5, 4);
        insertEdge(list, 6, 7);
        insertEdge(list, 7, 6);
      }

      e = 0;

      for(j = (cnf[i].getVariables() * 2); j < cnf[i].getVertices(); j++)
      {
        //Since a clauses' 3 vertices are known to be connected to each other, the if statement inserts their edges.
        if(j == ((cnf[i].getVariables() * 2) + (3 * e)))
        {
          insertEdge(list, vertices[j], vertices[j+1]);
          insertEdge(list, vertices[j+1], vertices[j]);
          insertEdge(list, vertices[j], vertices[j+2]);
          insertEdge(list, vertices[j+2], vertices[j]);
          insertEdge(list, vertices[j+1], vertices[j+2]);
          insertEdge(list, vertices[j+2], vertices[j+1]);
          e++;
        }


        //Depending on the value stored in the vtx object, it will determine where to add an edge to connect vertices that share the same stored value.
        if(v[j].getValue() == 1)
        {

          insertEdge(list, vertices[0], vertices[j]);
          insertEdge(list, vertices[j], vertices[0]);
        }
        else if(v[j].getValue() == -1)
        {
          insertEdge(list, vertices[1], vertices[j]);
          insertEdge(list, vertices[j], vertices[1]);
        }
        else if(v[j].getValue() == 2)
        {
          insertEdge(list, vertices[2], vertices[j]);
          insertEdge(list, vertices[j], vertices[2]);
        }
        else if(v[j].getValue() == -2)
        {
          insertEdge(list, vertices[3], vertices[j]);
          insertEdge(list, vertices[j], vertices[3]);
        }
        else if(v[j].getValue() == 3)
        {
          insertEdge(list, vertices[4], vertices[j]);
          insertEdge(list, vertices[j], vertices[4]);
        }
        else if(v[j].getValue() == -3)
        {
          insertEdge(list, vertices[5], vertices[j]);
          insertEdge(list, vertices[j], vertices[5]);
        }
        else if(v[j].getValue() == 4)
        {
          insertEdge(list, vertices[6], vertices[j]);
          insertEdge(list, vertices[j], vertices[6]);
        }
        else if(v[j].getValue() == -4)
        {
          insertEdge(list, vertices[7], vertices[j]);
          insertEdge(list, vertices[j], vertices[7]);
        }


      }

//      System.out.print("Adjacency List: \n");
//      printAdjList(list, cnf[i].getVertices());

      int[][] adjMatrix = convertList(list, cnf[i].getVertices());

//      System.out.print("Adjacency Matrix: \n");
//      printAdjMatrix(adjMatrix, cnf[i].getVertices());

      ver = 0;

    }


    //Printing the results in the specified format for every 3CNF problem.
    System.out.println("* Solve 3CNF in cnfs2022.txt: (reduced to K-Vertex Cover) *");
    System.out.println("X means either T or F");
    System.out.println("");

    for(i = 0; i < cnf.length; i++)
    {

      start = System.currentTimeMillis();
      System.out.print("3CNF No." + (i+1) + ":[n=" + cnf[i].getVariables() + " k=" + cnf[i].getClauses() + "] ");// + "(") //+ (end-start) + " ms) ");

      variables = new int[cnf[i].getVariables()];

      satisfiablity = (cnf[i].getVariables() + (2*cnf[i].getClauses()));
      //System.out.println("Satisfiabilty: " + satisfiablity);

      //Use get vtx cover of satisfiablity number here.
      //determine if isSatisfiable is true or false;



      if(isSatisfiable == true)
      {
        System.out.print("Solution:[");

        //SET TRUE OR FALSE VALUES HERE DEPENDING ON VERTICES IN SATISFIABLE VERTEX COVER.
        //Assume vtx cover is an array EX: Int[] VertexCover.


        int[] vertexCover = {1,2,5,6,8,9,12,13,14,16};//{1, 2, 4, 5, 9, 11};

        if(cnf[i].getVariables() == 2)
        {
          for(j = 0; j < vertexCover.length; j++)
          {
            if(vertexCover[j] == 0)
            {
              cnf[i].is1True = 1;
            }

            if(vertexCover[j] == 2)
            {
              cnf[i].is2True = 1;
            }
          }
        }

        if(cnf[i].getVariables() == 3)
        {
          for(j = 0; j < vertexCover.length; j++)
          {
            if(vertexCover[j] == 0)
            {
              cnf[i].is1True = 1;
            }

            if(vertexCover[j] == 2)
            {
              cnf[i].is2True = 1;
            }

            if(vertexCover[j] == 4)
            {
              cnf[i].is3True = 1;
            }
          }
        }

        if(cnf[i].getVariables() == 4)
        {
          for(j = 0; j < vertexCover.length; j++)
          {
            if(vertexCover[j] == 0)
            {
              cnf[i].is1True = 1;
            }

            if(vertexCover[j] == 2)
            {
              cnf[i].is2True = 1;
            }

            if(vertexCover[j] == 4)
            {
              cnf[i].is3True = 1;
            }

            if(vertexCover[j] == 6)
            {
              cnf[i].is4True = 1;
            }

          }
        }

      }
      else
      {
        System.out.print("No Solution! Random:[");

        int max = 1;
        int min = 0;

        cnf[i].is1True = rand.nextInt(max - min + 1) + min;
        cnf[i].is2True = rand.nextInt(max - min + 1) + min;
        cnf[i].is3True = rand.nextInt(max - min + 1) + min;
        cnf[i].is4True = rand.nextInt(max - min + 1) + min;

      }

      //Print out the true or false values.
      for(j = 0; j < variables.length; j++)
      {
        variables[j] = var;

        if(variables[j] == 1 && cnf[i].is1True == 1)
        {
          System.out.print(variables[j] + ":T ");
        }
        else if(variables[j] == 1 && cnf[i].is1True == 0)
        {
          System.out.print(variables[j] + ":F ");
        }

        if(variables[j] == 2 && cnf[i].is2True == 1)
        {
          System.out.print(variables[j] + ":T ");
        }
        else if(variables[j] == 2 && cnf[i].is2True == 0)
        {
          System.out.print(variables[j] + ":F ");
        }

        if(variables[j] == 3 && cnf[i].is3True == 1)
        {
          System.out.print(variables[j] + ":T ");
        }
        else if(variables[j] == 3 && cnf[i].is3True == 0)
        {
          System.out.print(variables[j] + ":F ");
        }

        if(variables[j] == 4 && cnf[i].is4True == 1)
        {
          System.out.print(variables[j] + ":T ");
        }
        else if(variables[j] == 4 && cnf[i].is4True == 0)
        {
          System.out.print(variables[j] + ":F ");
        }

          var++;
      }

      System.out.println("]");

      for(h = 0; h < cnf[i].getClauses(); h++)
      {

        System.out.print("(");

        for (k = 0; k < 3; k++)
        {

          if(k == 2)
          {
            if(cnf[i].arr[k][h] < 0)
            {
              System.out.print(cnf[i].arr[k][h]);
            }
            else
            {
              System.out.print(" " + cnf[i].arr[k][h]);
            }
          }
          else
          {

            if(cnf[i].arr[k][h] < 0)
            {
              System.out.print(cnf[i].arr[k][h] + "|");
            }
            else
            {
              System.out.print(" " + cnf[i].arr[k][h] + "|");
            }
          }

        }

        if(h == cnf[i].getClauses() - 1)
        {
          System.out.print(")");
        }
        else
        {
          System.out.print(")^");
        }

      }

      System.out.println(" ==>");

      for(int a = 0; a < cnf[i].getClauses(); a++)
      {

        System.out.print("(");

        for (int s = 0; s < 3; s++)
        {

          if(s == 2)
          {
            if(cnf[i].arr[s][a] == 1 && cnf[i].is1True == 1 || cnf[i].arr[s][a] == -1 && cnf[i].is1True == 0 || cnf[i].arr[s][a] == 2 && cnf[i].is2True == 1 || cnf[i].arr[s][a] == -2 && cnf[i].is2True == 0 || cnf[i].arr[s][a] == 3 && cnf[i].is3True == 1 || cnf[i].arr[s][a] == -3 && cnf[i].is3True == 0 || cnf[i].arr[s][a] == 4 && cnf[i].is4True == 1 || cnf[i].arr[s][a] == -4 && cnf[i].is4True == 0)
            {
              System.out.print(" T");
            }
            else if(cnf[i].arr[s][a] == 1 && cnf[i].is1True == 0 || cnf[i].arr[s][a] == -1 && cnf[i].is1True == 1 || cnf[i].arr[s][a] == 2 && cnf[i].is2True == 0 || cnf[i].arr[s][a] == -2 && cnf[i].is2True == 1 || cnf[i].arr[s][a] == 3 && cnf[i].is3True == 0 || cnf[i].arr[s][a] == -3 && cnf[i].is3True == 1 || cnf[i].arr[s][a] == 4 && cnf[i].is4True == 0 || cnf[i].arr[s][a] == -4 && cnf[i].is4True == 1)
            {
              System.out.print(" F");
            }

          }
          else
          {
            if(cnf[i].arr[s][a] == 1 && cnf[i].is1True == 1 || cnf[i].arr[s][a] == -1 && cnf[i].is1True == 0 || cnf[i].arr[s][a] == 2 && cnf[i].is2True == 1 || cnf[i].arr[s][a] == -2 && cnf[i].is2True == 0 || cnf[i].arr[s][a] == 3 && cnf[i].is3True == 1 || cnf[i].arr[s][a] == -3 && cnf[i].is3True == 0 || cnf[i].arr[s][a] == 4 && cnf[i].is4True == 1 || cnf[i].arr[s][a] == -4 && cnf[i].is4True == 0)
            {
              System.out.print(" T|");
            }
            else if(cnf[i].arr[s][a] == 1 && cnf[i].is1True == 0 || cnf[i].arr[s][a] == -1 && cnf[i].is1True == 1 || cnf[i].arr[s][a] == 2 && cnf[i].is2True == 0 || cnf[i].arr[s][a] == -2 && cnf[i].is2True == 1 || cnf[i].arr[s][a] == 3 && cnf[i].is3True == 0 || cnf[i].arr[s][a] == -3 && cnf[i].is3True == 1 || cnf[i].arr[s][a] == 4 && cnf[i].is4True == 0 || cnf[i].arr[s][a] == -4 && cnf[i].is4True == 1)
            {
              System.out.print(" F|");
            }
          }

        }

        if(a == cnf[i].getClauses() - 1)
        {
          System.out.print(")");
        }
        else
        {
          System.out.print(")^");
        }

      }

      var = 1;

      //Calculated the process time and printed at the end because of the design of the code.
      end = System.currentTimeMillis();
      System.out.println();
      System.out.println("calculation time: (" + (end-start) + " ms) ");
      System.out.println();

    }


  }


}
