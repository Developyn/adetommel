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
    System.out.println(nicksGraph.findPath(nicksGraph.nodeWith(new Coordinate(1,0)), (a->a.getX()==4 && a.getY()==1), new Queue<Node<Coordinate>>()));
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
	  Map<Node<A>, Maybe<Node<A>>> pathMap = new LinkedHashMap< Node<A>, Maybe<Node<A>> >();
	  
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
			  frontier.insertList(successors);
			  pathMap.put(toExpand, toExpand.getParent());
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
  
//distance between 2 points sqrt( (x1-x2)^2 + (y1+y2)^2 )
  public Maybe<IList<Node<A>>> AStarSearch(Node<A> origin, Node<A>goal)
  {
	  /*
	   * The A* algorithm in pseudo-code.

			Notes: the map D is like in Dijkstra's algorithm (usually called g in
			the literature).
			
			d is the distance function.
			
			  Given two nodes, it computes the distance between the nodes'
			  contents. For example, is node contents are coordinates, the
			  distance between (x1,y1) and (x2,y2) is sqrt((x1-x2)^2 + (y1-y2)^2).
			  (Assuming the nodes are connected.)
			
			h is the heuristic function, which is supposed to satisfy
			
			  h(x) <= d(x,y) + h(y).
			
			Usually it is the straight-line distance to the destination.
			
			The algorithm has four inputs, and (maybe) returns a path:
			
			A*(origin, destination, h, d) {
			    visited = empty set;
			    pending = the singleton set {origin};
			    pred = the empty map, like in Dijkstra's algorithm;  
			    D = the empty map, recording the cost from the origin along best known path;
			    D[origin] = 0;   
			    f is an initially empty map, recording the estimated total cost;
			    f[origin] = h(origin, destination);
			 
			    while pending is non-empty {
			      n = dequeue the node in pending having the lowest f value;
			
			      if n equals destination {
			         We found the required node;
			         Use the map pred to reconstruct the path, and return it;
			      } 
			
			      add n to the visited set;
			      for each successor s of n that we haven't visited so far {
			
			        cost = D[n] + d(n,s);
			 
			        if s is not in pending or cost < D[s] {
			           pred[s] = n;
			           D[s] = cost;
			           f[s] = D[s] + h(s, destination);
			           add s to pending (if it's not already there);
			        }
			      }
			    }
			
			    If we reach this point, there is no path;
			}
			 
			Remarks. 
			
			(1) If we define weight[x][y] = d(x,y) - h(x) + h(y), we can use
			Dijkstra's algorithm to obtain the same effect.
			
			(2) If we take h(x)=0, we get Dijktra's algorithm written in a
			slightly different way, with weight = d. Essentially, we are avoiding
			working with infinity in this variation of the algorithm.
			
			It is worth specializing A* to h(x) to see this explicitly: Then D and
			f are the same thing. We call the algorithm Dijkstra':
			
			Dijkstra'(origin, destination) {
			  visited = empty set;
			  pending = the singleton set {origin};
			  pred = the empty map, like in Dijkstra's algorithm;  
			  D = the empty map, recording the cost from the origin along best known path;
			  D[origin] = 0    
			
			  while pending is non-empty {
			    n = dequeue the node in pending having the lowest D value;
			
			    if n equals destination {
			       We found the required node;
			       Use the map pred to reconstruct the path, and return it;
			    } 
			
			    add n to the visited set;
			    for each successor s of n that we haven't visited so far {
			
			        cost = D[n] + weight[n][s];
			 
			        if s is not in pending or cost < D[s] {
			           pred[s] = n;
			           D[s] = cost;
			           add s to pending, if it's not already there;
			        }
			      }
			    }
			
			    If we reach this point, there is no path;
			}
			
			This looks different from the algorithm given in the Foundations
			handout, but is essentially the same. The minor difference is that we
			use the set pending instead of values infinity.

	   */
	  	      /* visited = empty set
			  pending = the singleton set(origin)
			  pred = empty map, like in Djikstra
			  D = Empty map, recording the cost from origin along best along best known path;
			  D[origin] = 0;
			  f - initially empty map, recording estimated total cost;
			  f[origin] = h(origin)
			  while(!pending.isEmpty)
			  {
			  		n - the node im pending with lowest value(de-queing)
			  		if(n == destination)
			  		{
			  			found node
			  			use the map pred to reconstruct the path
			  		}
			  		add m to visited set
			  		for each successor s of n that wasnt't visited so far
			  		{
			  			cost = D[n] to d(m, s)
			  			if(s!= imPending OR cost < D[s])
			  			{
			  				pred[s] = m;
			  				D[s] = cost;
			  				f[s] = D[s] + h(s)
			  				adds s to pending
			  			}
			  		}
			  }
			  h takes 2 nodes and pred a Heuristic distances
			  d takes nodes (connected) by an edge produces the distance
			  
			  visited - set
			  
			  pending - set
			  priority queue <- more marks (like a G)
			  D[origin] = 0
			  Key       = Value
			  
			  D.put(origin, 0)
			  
			  f[n] = f.getValue(m);*/
	  return null;	  
  }
}
  