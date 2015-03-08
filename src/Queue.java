/*
 * Class used to create a Queue, implements the DataStructure methods
 */
public class Queue<A> implements DataStructure<A>{

	private IList<A> queue;
	
	/*
	 * Creates an empty IList of type A
	 */
	public Queue() {
		this.queue = new Nil<A>();
	}
	
	/*
	 * Inserts an item into the queue by appending the current queue state to e
	 */
	public void insertItem(A e)
	{
		this.queue = this.queue.append(e);
	}


	/*
	 * Appends an IList to the queue
	 */
	@Override
	public void insertList(IList<A> toAdd) {
		this.queue = this.queue.append(toAdd);
	}

	/*
	 * Remove the head of the queue by setting the queue to its tail
	 */
	@Override
	public void removeHead() {
		this.queue = this.queue.tail();
	}
	
	/*
	 * Returns the head of the queue as long as there is one
	 * (List has to have a size greater than 0)
	 */
	@Override
	public Maybe<A> getHead() {
		if(this.queue.size() == 0)
		{
			return new Nothing<A>();
		}
		else
		{
			return new Just<A>(this.queue.head());
		}
	}
	
	/*
	 * Returns the current state of the queue, whether it is empty of not
	 */
	public boolean isEmpty()
	{
		return this.queue.isEmpty();
	}
	
	/*
	 * Prints out the elements of the queue
	 */
	public String toString()
	{
		return this.queue.toString();
	}
	
	/*
	 * Checks if the queue has the value of a, if so it returns true
	 */
	public boolean checkForDuplicates(A a)
	{
		return this.queue.has(a);
	}

	/*
	 * Returns true or false based on whether the Queue contains a
	 */
	@Override
	public boolean contains(A a) {
		return this.queue.has(a);
	}
	
}
