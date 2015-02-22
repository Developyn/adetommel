// WHAT (NOT) TO DO:
// -------------------
// REPLACE the "Exercise" lines with code (possibly empty code).
// LEAVE ALONE the "Exercise" lines for parts you are not solving.
//
// To get any marks for the whole exercise, your submission should
// compile using the "compile" script. Submissions that don't compile
// won't get any marks.

/**
 * Implementation of Nothing
 * (using the "composite pattern").
 */

public class Nothing<A> implements Maybe<A> {
    
  public Nothing() {
  }              

  public boolean isNothing() {
	  return true;
  }

  public int size() {
	  return 0;
  }

  public String toString() {
	  return "Nothing";
  }

  public boolean has(A a) {
	  return false;
  }

  // Higher-order functions:

  public Maybe<A> filter(Predicate<A> p) {
	  return this;
  }

  public <B> Maybe<B> map(Function<A,B> f) {
	  return new Nothing<B>();
  }

  public <B> B fold(Function<A,B> f, B b) {
	  return b;
  }

  public boolean all(Predicate<A> p) {
	  return true;
  }

  public boolean some(Predicate<A> p) {
	  return false;
  }

  public void forEach(Action<A> f) {
  }

  // Unsafe operations:
  public A fromMaybe() {
	  throw new RuntimeException("Unsafe operation!");
  }
}
