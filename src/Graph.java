import java.util.*;

// We represent a graph as a set of nodes. 
// This is a minimal class so that a graph can be created.

public class Graph<A> {
	
	private Map<Node<A>, Maybe<Node<A>>> pathMap;

  // Keep the implementation of sets open, by using the Set interface:
  private Set<Node<A>> nodes;       

  // Constructs the empty graph:
  public Graph() {
    // Choose any implementation of sets you please, but you need to
    // choose one.
    nodes = new LinkedHashSet<Node<A>>();
    
    pathMap = new LinkedHashMap<Node<A>,Maybe<Node<A>>>();
  }
  
  public Map<Node<A>, Maybe<Node<A>>> getPathMap()
  {
	  return this.pathMap;
  }

  // Get method:
  public Set<Node<A>> nodes() {
    return nodes;
  }

  // Finds or else creates a node with a given contents c:
  public Node<A> nodeWith(A c) { 
    for (Node<A> node : nodes) {  // Inefficient for large graph.
      if (node.contentsEquals(c))
        return node; // Found.
    }
    // Not found, hence create it:
    Node<A> node = new Node<A>(c);
    nodes.add(node);
    return node;
  }

  // Builds sample graph for testing:
  public static void main(String args []) {    

    int [][] nick = {
      {0,0,1,0,0,1}, 
      {0,1,0,0,1,1,0,2}, 
      {0,2,0,3,0,1}, 
      {0,3,0,2,0,4}, 
      {0,4,0,3,0,5}, 
      {0,5,0,6,1,5,0,4}, 
      {0,6,1,6,0,5}, 
      {1,0,0,0,1,1,2,0}, 
      {1,1,1,2,2,1,1,0,0,1}, 
      {1,2,2,2,1,1,1,3}, 
      {1,3,1,2,1,4,2,3}, 
      {1,4,2,4,1,5,1,3}, 
      {1,5,1,4,2,5,1,6,0,5}, 
      {1,6,0,6,1,5,2,6}, 
      {2,0,3,0,2,1,1,0}, 
      {2,1,2,2,1,1,2,0,3,1}, 
      {2,2,1,2,2,1,2,3,3,2}, 
      {2,3,2,2,2,4,3,3,1,3}, 
      {2,4,1,4,2,5,2,3,3,4}, 
      {2,5,2,4,1,5,2,6,3,5}, 
      {2,6,3,6,2,5,1,6}, 
      {3,0,2,0,3,1}, 
      {3,1,3,0,4,1,2,1,3,2}, 
      {3,2,2,2,4,2,3,1}, 
      {3,3,2,3,3,4}, 
      {3,4,2,4,3,3}, 
      {3,5,3,6,2,5,4,5}, 
      {3,6,2,6,3,5}, 
      {4,0}, 
      {4,1,4,2,5,1,3,1}, 
      {4,2,4,1,5,2,3,2}, 
      {4,3}, 
      {4,4}, 
      {4,5,5,5,3,5}, 
      {4,6}, 
      {5,0}, 
      {5,1,4,1,5,2,6,1}, 
      {5,2,4,2,5,1,6,2}, 
      {5,3}, 
      {5,4}, 
      {5,5,4,5,6,5}, 
      {5,6}, 
      {6,0,7,0,6,1}, 
      {6,1,6,0,5,1,6,2,7,1}, 
      {6,2,5,2,6,1,7,2}, 
      {6,3,7,3,6,4}, 
      {6,4,6,3,7,4}, 
      {6,5,5,5,6,6,7,5}, 
      {6,6,7,6,6,5}, 
      {7,0,6,0,7,1,8,0}, 
      {7,1,8,1,7,0,6,1,7,2}, 
      {7,2,7,3,8,2,6,2,7,1}, 
      {7,3,6,3,7,2,7,4,8,3}, 
      {7,4,7,3,8,4,6,4,7,5}, 
      {7,5,8,5,7,6,7,4,6,5}, 
      {7,6,6,6,7,5,8,6}, 
      {8,0,8,1,7,0,9,0}, 
      {8,1,8,2,9,1,7,1,8,0}, 
      {8,2,8,1,7,2,8,3}, 
      {8,3,8,2,7,3,8,4}, 
      {8,4,8,5,8,3,7,4}, 
      {8,5,9,5,8,4,7,5,8,6}, 
      {8,6,8,5,7,6,9,6}, 
      {9,0,9,1,8,0}, 
      {9,1,8,1,9,2,9,0}, 
      {9,2,9,1,9,3}, 
      {9,3,9,2,9,4}, 
      {9,4,9,5,9,3}, 
      {9,5,8,5,9,4,9,6}, 
      {9,6,9,5,8,6} 
    };

    Graph<Coordinate> nicksGraph = new Graph<Coordinate>();
    
    FunctionTotal funcTotal = new FunctionTotal( nicksGraph, nicksGraph.nodeWith(new Coordinate(0,1)), nicksGraph.nodeWith(new Coordinate(7,1)) );

    for (int i = 0; i < nick.length; i++) {
      // What we are going to do relies on the two following facts
      // about nick:
      assert(nick[i].length >= 2);       // (1)
      assert(nick[i].length % 2 == 0);   // (2)

      int x = nick[i][0]; // Can't get array out of bounds 
      int y = nick[i][1]; // because of assertion (1).
      Coordinate c = new Coordinate(x, y);
      Node<Coordinate> node = nicksGraph.nodeWith(c);

      // And next we add its successors. We rely on assertion (2)
      // again to avoid array out of bounds. Now we start from
      // position 2, as positions 0 and 1 have already been looked at
      // (they are x and y). Notice that we need to increment by 2.

      for (int j = 2; j < nick[i].length; j=j+2) {
        int sx = nick[i][j];   
        int sy = nick[i][j+1]; 
        Coordinate sc = new Coordinate(sx, sy);
        Node<Coordinate> s = nicksGraph.nodeWith(sc);
        node.addSuccessor(s);
      }
    }
    // Done. We have the graph. Now we print it back to be sure this worked:
    for (Node<Coordinate> node : nicksGraph.nodes()) {
      System.out.print("(" + node.contents().x + "," + node.contents().y + "): ");
      for(Node<Coordinate> s : node.successors()) {
        System.out.print("(" + s.contents().x + "," + s.contents().y + "), ");
      }
      System.out.println();
    } 
    
    System.out.println("Nodes:");
    
    /*BFS*/
   // System.out.println(nicksGraph.findNode(nicksGraph.nodeWith(new Coordinate(4,0)), (a->a.getX()==4 && a.getY()==1), new Queue<Node<Coordinate>>()));
    /*DFS*/
    //System.out.println(nicksGraph.findNode(nicksGraph.nodeWith(new Coordinate(4,0)), (a->a.getX()==4 && a.getY()==1), new Stack<Node<Coordinate>>()));
    
    System.out.println("Paths:");
    /*BFS Path*/
    System.out.println(nicksGraph.findPath(nicksGraph.nodeWith(new Coordinate(0,1)), (a->a.getX()==7 && a.getY()==1), new PriorityQueue<Node<Coordinate>, Integer>(funcTotal)));
    /*DFS Path*/
    System.out.println(nicksGraph.findPath(nicksGraph.nodeWith(new Coordinate(1,0)), (a->a.getX()==4 && a.getY()==1), new Stack<Node<Coordinate>>()));
  }

  //Generalisation
  public Maybe<Node<A>> findNode(Node<A> nodeStart, Predicate<A> pred, DataStructure<Node<A>> frontier)
  {
	  Collection<Node<A>> visited = new LinkedHashSet<Node<A>>();
	  frontier.insertItem(nodeStart);
	  
	  while(!frontier.isEmpty())
	  {
		  if(visited.contains(frontier.getHead().fromMaybe()))
		  {
			  frontier.removeHead();
		  }
		  else if(pred.holds(frontier.getHead().fromMaybe().contents()))
		  {
			  return frontier.getHead();
			  //reconstruct
		  }
		  else
		  {
			  Node<A> toExpand = frontier.getHead().fromMaybe();
			  visited.add(toExpand);
			  frontier.removeHead();
			  
			  IList<Node<A>> successors = new Nil<Node<A>>();
			  for(Node<A> i : toExpand.successors())
			  {
					successors = successors.append(i);
			  }
			  
			  frontier.insertList(successors);
		  }
	  }
	  return new Nothing<Node<A>>();
  }
  
  //Generalisation
  public Maybe<IList<Node<A>>> findPath(Node<A> nodeStart, Predicate<A> pred, DataStructure<Node<A>> frontier)
  {
	  Collection<Node<A>> visited = new LinkedHashSet<Node<A>>();
	  frontier.insertItem(nodeStart);
	  
	  //Hash Map to store our path
	  this.pathMap = new LinkedHashMap< Node<A>, Maybe<Node<A>> >();
	  
	  while(!frontier.isEmpty())
	  {
		  if(visited.contains(frontier.getHead().fromMaybe()))
		  {
			  frontier.removeHead();
		  }
		  else if(pred.holds(frontier.getHead().fromMaybe().contents()))
		  {
			  Node<A> goal = frontier.getHead().fromMaybe();
			  pathMap.put(goal,goal.getParent());
			  return reconstructPath(pathMap, nodeStart, goal);
		  }
		  else
		  {
			  Node<A> toExpand = frontier.getHead().fromMaybe();
			  visited.add(toExpand);
			  frontier.removeHead();
			  
			  for(Node<A> n : toExpand.successors())
			  {
				  if(!visited.contains(n) && !frontier.checkForDuplicates(n))
				  {
					  n.setParent(new Just<Node<A>>(toExpand));
				  }
			  }
			  
			  //Gets successors to put in the frontier
			  IList<Node<A>> successors = new Nil<Node<A>>();
			  for(Node<A> i : toExpand.successors())
			  {
					successors = successors.append(i);
			  }
			  pathMap.put(toExpand, toExpand.getParent());
			  frontier.insertList(successors);			  

		  }
	  }
	  return new Nothing<IList<Node<A>>>();
  }

  public Maybe<IList<Node<A>>> reconstructPath(Map<Node<A>, Maybe<Node<A>>> pathMap, Node<A> nodeStart, Node<A> goal)
  {
		Node<A> node = goal;
		IList<Node<A>> path = new Nil<Node<A>>();
		
		while(!node.contents().equals(nodeStart.contents()))
		{
			path = path.append(node);
			node = pathMap.get(node).fromMaybe();
		}
		
		path = path.append(nodeStart);
		path = path.reverse();

		return new Just<IList<Node<A>>>(path);
  }
}