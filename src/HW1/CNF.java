package HW1;

public class CNF
{
  private int clauses;
  private int variables;
  public int[][] arr = new int[3][10];

  public void setClauses(int clauses)
  {
    this.clauses = clauses;
  }

  public void setVariables(int variables)
  {
    this.variables = variables;
  }

  public int getClauses()
  {
    return clauses;
  }

  public int getVariables()
  {
    return variables;
  }

  public static void main(String[] args)
  {
    CNF cnf = new CNF();
    cnf.clauses = 3;

    cnf.arr[0][0] = 5;

    System.out.println(cnf.arr[0][0]);

    System.out.println(cnf.clauses);
  }

}
