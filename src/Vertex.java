import java.util.ArrayList;

public class Vertex {

    private int name;
	public ArrayList<Edge> connectedEdges;
	
	public Vertex(int vertex)
	{
		this.name = vertex;
		this.connectedEdges = new ArrayList<Edge>();
	}

    int getName()
    {
        return name;
    }
}