/*
 * Class to work out the Manhattan distance from the current node to the goal node
 */
public class Manhattan implements Function< Node< Coordinate>, Integer > {
	
	private Node<Coordinate> goal;
	
	/*
	 * Manhattan Constructor
	 * Sets the paramater goal to the class's private goal variable
	 */
	public Manhattan(Node<Coordinate> goal)
	{
		this.goal = goal;
	}
	
	/*
	 * Returns the absolute value of the goal x coordinate - the current x coordinate
	 * +
	 * the goal y coordinate - the current y coordinate
	 */
	public Integer apply(Node<Coordinate> coord) {
		
		int coordX = coord.contents().getX();
		int coordY = coord.contents().getY();		
		
		int goalX = goal.contents().getX();
		int goalY = goal.contents().getY();
		
		return Math.abs(goalX - coordX) + Math.abs(goalY - coordY);
	}
}
