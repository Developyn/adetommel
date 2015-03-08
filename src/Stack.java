/*
 * Class used to create a Stack, implements the DataStructure methods
 */
public class Stack<A> implements DataStructure<A> {

	private IList<A> stack;
	
	/*
	 * Initialises the IList stack
	 */
	public Stack() {
		this.stack = new Nil<A>();
	}
	
	/*
	 * Inserts a new item into the stack
	 */
	public void insertItem(A e)
	{
		this.stack = new Cons<A>(e,this.stack);
	}
	
	/*
	 * Inserts an IList into the stack by appending it to the current stack
	 */
	@Override
	public void insertList(IList<A> toAdd) {
		this.stack = toAdd.reverse().append(this.stack);
	}
	
	/*
	 * Removes the head of the stack by setting the stack to the value of its tail
	 */
	@Override
	public void removeHead() 
	{
		this.stack = this.stack.tail();
	}
	
	/*
	 * Gets the head of the stack as long as the size of the stack is greater than 0
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
	
	/*
	 * Returns the state of the stack, false if it has elements in it and true if it's empty
	 */
	public boolean isEmpty()
	{
		return this.stack.isEmpty();
	}
	
	/*
	 * Prints the elements of the stack
	 */
	public String toString()
	{
		return this.stack.toString();
	}
	
	/*
	 * Since Stack is used for DFS, we want the parent to be the last node it came from and therefore
	 * we shouldn't have to check for its duplicates
	 */
	public boolean checkForDuplicates(A a)
	{
		return false;
	}
	
	/*
	 * Returns true or false based on whether the Stack contains a
	 */
	@Override
	public boolean contains(A a) {
		return this.stack.has(a);
	}

}
