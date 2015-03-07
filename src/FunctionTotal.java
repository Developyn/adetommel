
public class FunctionTotal implements Function< Node<Coordinate>, Integer > {

	Manhattan manhDist;
	GCost gcost;
	
	public FunctionTotal(Graph<Coordinate> nicksGraph, Node<Coordinate> start, Node<Coordinate> goal)
	{
		this.manhDist = new Manhattan(goal);
		this.gcost = new GCost(nicksGraph, start);
	}
	
	@Override
	public Integer apply(Node<Coordinate> a) {
		return manhDist.apply(a) + gcost.apply(a);
	}

}
