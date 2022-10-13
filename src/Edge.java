public class Edge {

    public int v1;
	public int v2;
	
	Edge(int x, int y)
	{
		this.v1 = x;
		this.v2 = y;
	}
	
	public String toString()
	{
		return v1 + " -- " + v2;
	}
}