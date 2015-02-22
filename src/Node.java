// Minimal class for a particular implementation of directed graphs.
// All we include is what is necessary to build a graph, in the class
// graph.

import java.util.*;

public class Node<A> {

  private A contents; 
  // Keep the implementation of sets open, by using the Set interface:
  private Set<Node<A>> successors; 

  // We can only build a node with an empty set of successors:
  public Node (A contents) {
    this.contents = contents;
    // Choose any implementation of sets you please, but you need to
    // choose one.
    this.successors = new LinkedHashSet<Node<A>>(); 
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
}
