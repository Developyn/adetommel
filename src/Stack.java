/** this class represents a stack which has a list as an attribute and changes
 * the behaviour of the insertion of items to be that of a stack
 * @author charlie street
 *
 * @param <A>
 */
public class Stack<A> implements DataStructure<A> {

	private IList<A> stack;
	
	/**constructor initialises stack list
	 * 
	 */
	public Stack() {
		this.stack = new Nil<A>();
	}

	/** method inserts a single item into the stack
	 * 
	 */
	public void insertItem(A e)
	{
		this.stack = new Cons<A>(e,this.stack);
	}
	
	/**this method appends items to a stack in a LIFO fashion
	 * the list to add is reversed as is with the nature of the stack
	 */
	@Override
	public void insertList(IList<A> toAdd) {
		this.stack = toAdd.reverse().append(this.stack);
	}
	
	/**method removes front item from stack
	 * 
	 */
	@Override
	public void removeHead() 
	{
		this.stack = this.stack.tail();
	}
	
	/** method returns the front item from the stack
	 * 
	 */
	@Override
	public Maybe<A> getHead() 
	{
		if(this.stack.isEmpty() == true)
		{
			return new Nothing<A>();
		}
		else
		{
			return new Just<A>(this.stack.head());
		}
	}
	
	/** wrapper method for isEmpty in IList
	 * 
	 */
	public boolean isEmpty()
	{
		return this.stack.isEmpty();
	}
	
	/**overwriting toString method
	 * 
	 */
	public String toString()
	{
		return this.stack.toString();
	}
	
	/**method returns whether parent should be kept or changed
	 * in a stack always change it so return false in a stack, a new duplicate will go before the original
	 * therefore (in DFS which the stack employs) the parent should be changed to the latest duplicate added as the later item won't get expanded
	 */
	public boolean checkForDuplicates(A a)
	{
		return false;
	}

	@Override
	public boolean contains(A a) {
		return this.stack.has(a);
	}

}
