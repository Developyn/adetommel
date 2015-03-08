/**
 * Returns the F-Function value which is the total of the
 * manhattan distance and the g value cost
 */
public class FunctionTotal implements Function< Node<Coordinate>, Integer > {

	Manhattan manhDist;
	GCost gcost;
	
	/**
	 * 
	 * @param nicksGraph A graph of Coordinates
	 * @param start A starting point, is of type Node<Coordinate>
	 * @param goal A goal point, is of type Node<Coordinate>
	 */
	public FunctionTotal(Graph<Coordinate> nicksGraph, Node<Coordinate> start, Node<Coordinate> goal)
	{
		this.manhDist = new Manhattan(goal);
		this.gcost = new GCost(nicksGraph, start);
	}
	
	/*
	 * Returns the manhattan distance from Node a added to the gcost of Node a
	 */
	@Override
	public Integer apply(Node<Coordinate> a) {
		return manhDist.apply(a) + gcost.apply(a);
	}

}
