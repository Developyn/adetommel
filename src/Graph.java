import java.awt.Point;
import java.util.*;

// We represent a graph as a set of nodes. 
// This is a minimal class so that a graph can be created.

public class Graph<A> {

  // Keep the implementation of sets open, by using the Set interface:
  private Set<Node<A>> nodes;       

  // Constructs the empty graph:
  public Graph() {
    // Choose any implementation of sets you please, but you need to
    // choose one.
    nodes = new LinkedHashSet<Node<A>>(); 
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

    int [] [] nick = {
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
    
    //These two print lines show that the dfs and bfs methods. 
    System.out.println(nicksGraph.dfs(nicksGraph.nodeWith(new Coordinate(0,0)), a->a.getX()==3));

    System.out.println(nicksGraph.bfs(nicksGraph.nodeWith(new Coordinate(0,0)), a->a.getX()==6));
  }
  
  
  public Maybe<Node> dfs(Node<A> x, Predicate<A> p)
  {
	  Stack<Node<A>> stack = new Stack<Node<A>>();
	  Set<Node<A>> visited = new LinkedHashSet<Node<A>>();
	  Set<Node<A>> successors = new LinkedHashSet<Node<A>>();
	  
	  stack.push(x);
	 
	  //Start with an empty stack // For backtracing
	  //ush the starting node into the stack
	  while(!stack.empty())
	  {
		  Node<A> current = stack.pop();
		  
		  if(!visited.contains(current))
		  {
			  if(p.holds(current.contents()))
			  {
				  return new Just(current.contents());
			  }
			  visited.add(current);
			  successors = current.successors();
			  
			  for(Iterator<Node<A>> i = successors.iterator(); i.hasNext();)
			  {
				  stack.push(i.next());
			  }
		  }
	  }
	  return new Nothing();
  }
  
  public Maybe<Node> bfs(Node<A> x, Predicate<A> p)
  {
	  Queue<Node<A>> queue = new LinkedList<Node<A>>();
	  Set<Node<A>> visited = new LinkedHashSet<Node<A>>();
	  Set<Node<A>> successors = new LinkedHashSet<Node<A>>();
	  
	  queue.add(x);
	 
	  //Start with an empty stack // For backtracing
	  //ush the starting node into the stack
	  while(!queue.isEmpty())
	  {
		  Node<A> current = queue.poll();
		  
		  if(!visited.contains(current))
		  {
			  if(p.holds(current.contents()))
			  {
				  return new Just(current.contents());
			  }
			  visited.add(current);
			  successors = current.successors();
			  
			  for(Iterator<Node<A>> i = successors.iterator(); i.hasNext();)
			  {
				  queue.add(i.next());
			  }
		  }
	  }
	  return new Nothing();
  }
  
  
  
  
}
