
public class Manhattan implements Function< Node< Coordinate>, Integer > {
	
	private Node<Coordinate> goal;
	
	public Manhattan(Node<Coordinate> goal)
	{
		this.goal = goal;
	}

	public Integer apply(Node<Coordinate> coord) {
		
		int coordX = coord.contents().getX();
		int coordY = coord.contents().getY();
		
		
		int goalX = goal.contents().getX();
		int goalY = goal.contents().getY();
		
		return Math.abs(goalX - coordX) + Math.abs(goalY - coordY);
	}
}
