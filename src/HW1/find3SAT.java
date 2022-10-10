package HW1;

import HW1.CNF;

import java.io.*;
import java.util.Scanner;

public class find3SAT
{
  public static void main(String args[]) throws Exception
  {
      CNF cnf[] = new CNF[20];

      for (int i = 0; i < cnf.length; i++)
      {
        cnf[i] = new CNF();
      }

    File file = new File(args[0]);

    Scanner sc = new Scanner(file);

    int satisfiablity;

    int varTracker = 0;
    int i = 0;
    int j = 0;
    int k = 0;
    int h = 0;
    int e;

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
        System.out.println("In the array: " + cnf[j].arr[k][h]);

        k++;
        i++;
      }

      cnf[j].setClauses(i / 3);
      cnf[j].setVariables(varTracker);

      System.out.println("Clauses for array " + j + ": " + cnf[j].getClauses());
      System.out.println("Variables for array " + j + ": " + cnf[j].getVariables());

      System.out.println("CNF Array " + j + " complete");

      k = 0;
      h = 0;
      i = 0;
      varTracker = 0;
      j++;

      System.out.println();


    }

    System.out.println("* Solve 3CNF in cnfs2022.txt: (reduced to K-Vertex Cover) *");
    System.out.println("X means either T or F");

    //satisfiablity = (cnf[i].getVariables() + (2*cnf[i].getClauses()));

    //System.out.println("Satisfiabilty: " + satisfiablity);

    for(i = 0; i < cnf.length; i++)
    {
      System.out.println("3CNF No." + (i+1) + ": [n=" + cnf[i].getVariables() + " k=" + cnf[i].getClauses() + "]");
      satisfiablity = (cnf[i].getVariables() + (2*cnf[i].getClauses()));
      System.out.println("Satisfiabilty: " + satisfiablity);
    }




  }


}
