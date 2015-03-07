// Minimal class for a particular implementation of directed graphs.
// All we include is what is necessary to build a graph, in the class
// graph.

import java.util.*;

public class Node<A> {

  private A contents; 
  // Keep the implementation of sets open, by using the Set interface:
  private Set<Node<A>> successors; 
  private Maybe<Node<A>> parent;
  public float costFromStart;
  public float estimatedCostToGoal;

  // We can only build a node with an empty set of successors:
  public Node (A contents) {
    this.contents = contents;
    // Choose any implementation of sets you please, but you need to
    // choose one.
    this.successors = new LinkedHashSet<Node<A>>();
    this.parent = new Nothing<Node<A>>();
  }

  // Hence we need this:
  public void addSuccessor(Node<A> s) {
    successors.add(s);
  }

  public boolean contentsEquals(A c) {
    return contents.equals(c);
  }

  // Get methods:
  public A contents() {
    return contents;
  }

  public Set<Node<A>> successors() {
    return successors;
  }
  
  /**
   * toString method so when we output the node/s we want it's comprehendable
   */
  public String toString()
  {
	  return ""+this.contents;
  }
  
  /**method returns the currentParent of a node (may change)
   * 
   * @return the currentParent
   */
  public Maybe<Node<A>> getParent()
  {
	  return this.parent;
  }
	
	/** sets the new current parent of the node
	 * just can be set within method as there will only be a valid value passed to this method
	 * @param currentParent the currentParent of the node
	 */
  public void setParent(Maybe<Node<A>> currentParent)
  {
	  this.parent = currentParent;
  }  
}
