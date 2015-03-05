public interface DataStructure<A>
{
	public void insertItem(A e);
	public void insertList (IList<A> toAdd);
	public void removeHead();
	public Maybe<A> getHead();
	public boolean isEmpty();
	public boolean checkForDuplicates(A a);
}
