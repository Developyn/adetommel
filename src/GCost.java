/*
 * Class used to work out the G-Cost or from getting from one node to another
 */
public class GCost implements Function<Node<Coordinate>, Integer>{
	
	Graph<Coordinate> nicksGraph;
	Node<Coordinate> start;
	
	public GCost(Graph<Coordinate> nicksGraph, Node<Coordinate> start)
	{
		this.nicksGraph = nicksGraph;
		this.start = start;
	}

	@Override
	public Integer apply(Node<Coordinate> a) {
		if(a.contentsEquals(start.contents()))
		{
			return 0;
		}
		return nicksGraph.reconstructPath(nicksGraph.getPathMap(), start, a.getParent().fromMaybe()).fromMaybe().size();
	}
}
