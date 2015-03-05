/**
 * Implementation of the empty list
 * (using the "composite pattern").
 */

public class Nil<A> implements IList<A> {
    
  public Nil() { // Nothing to do in the constructor!
  }              // Could simply remove it.

  public boolean isEmpty() { 
    return true; 
  }

  
  public int size() {
    return 0;
  }
  
  public String toString() { 
    return "Nil"; 
  }

  public IList<A> append(IList<A> l) {
    return l;
  }

  public IList<A> append(A a) {
    return new Cons<A>(a , this);
  }

  public IList<A> reverse() {
    return this;
  }

  public boolean has(A a) {
    return false;
  }

  // Higher-order functions:

  public IList<A> filter(Predicate<A> p) {
    return this; // new Nil<A>() also works.
  }

  public <B> IList<B> map(Function<A,B> f) {
    return new Nil<B>();
  }

  public <B> B fold(Function2<A,B,B> f, B b) {
    return b;
  }

  public boolean all(Predicate<A> p) {
    return true;
  }

  public boolean some(Predicate<A> p) {
    return false;
  }

  public void forEach(Action<A> f) {
    // Nothing to do, but we have to include the method. (Why?)
  }

  // Unsafe operations:
  public A head() {
    throw new RuntimeException("tried to get the head of an empty list");
  }

  public IList<A> tail() {
    throw new RuntimeException("tried to get the tail of an empty list");
  }


  // Safe:
  public <B> B cases(B b, Function2<A,IList<A>,B> f) {
    return b;
  }
}
