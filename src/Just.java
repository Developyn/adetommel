// WHAT (NOT) TO DO:
// -------------------
// REPLACE the "Exercise" lines with code (possibly empty code).
// LEAVE ALONE the "Exercise" lines for parts you are not solving.
//
// To get any marks for the whole exercise, your submission should
// compile using the "compile" script. Submissions that don't compile
// won't get any marks.
/**
 * Implementation of Just
 * (using the "composite pattern").
 */

public class Just<A> implements Maybe<A> {
  
  private final A something; 
  
  public Just(A something) {
	  this.something = something;
  }              

  public boolean isNothing() {
	  return false;
  }

  public int size() {
	  return 1;
  }

  public String toString() {
	  return "Just(" + something+")";
  }

  public boolean has(A a) {
	  return this.something.equals(a);
  }

  // Higher-order functions:

  public Maybe<A> filter(Predicate<A> p) {
	  if(p.holds(something))
	  {
		  return new Just<A>(something);
	  }
	  else
	  {
		  return this;
	  }
  }

  public <B> Maybe<B> map(Function<A,B> f) {
	  return new Just<B>(f.apply(something));
  }

  public <B> B fold(Function<A,B> f, B b) {
	  return f.apply(something);
  }

  public boolean all(Predicate<A> p) {
	  return p.holds(something);
  }

  public boolean some(Predicate<A> p) {
	  return p.holds(something);
  }

  public void forEach(Action<A> f) {
	  f.apply(something);
  }

  // Unsafe operation:
  public A fromMaybe() {
	  return something;
  }

}