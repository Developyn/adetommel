/**
 * 
 * An interface for a type of DataStructure
 */
public interface DataStructure<A>
{
	public void insertItem(A e);
	public void insertList (IList<A> toAdd);
	public void removeHead();
	public Maybe<A> getHead();
	public boolean isEmpty();
	public boolean checkForDuplicates(A a);
	public boolean contains(A a);
}
