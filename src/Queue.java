/**this class represents a queue data structure, it is an object with a list as an attribute
 * 
 * @author charlie street
 *
 */
public class Queue<A> implements DataStructure<A>{

	private IList<A> queue;
	
	/**constructor just creates new empty list
	 * 
	 */
	public Queue() {
		this.queue = new Nil<A>();
	}
	
	/** this method takes a single item and adds it to the queue
	 * 
	 */
	public void insertItem(A e)
	{
		this.queue = this.queue.append(e);
	}

	/**method inserts items into a list in a FIFO structure
	 * 
	 */
	@Override
	public void insertList(IList<A> toAdd) {
		this.queue = this.queue.append(toAdd);
	}

	/**method returns the queue with the first item removed and returns nothing if empty list
	 * 
	 */
	@Override
	public void removeHead() {
		this.queue = this.queue.tail();
	}

	/**method returns the front of the queue but if it is empty nothing is returned
	 * 
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
	
	/** returns if list is empty
	 * 
	 * @return whether list is empty
	 */
	public boolean isEmpty()
	{
		return this.queue.isEmpty();
	}
	
	/**overwriting to string
	 * 
	 */
	public String toString()
	{
		return this.queue.toString();
	}
	
	/**checks whether a parent should be changed by checking current list
	 * 
	 */
	public boolean checkForDuplicates(A a)
	{
		return this.queue.has(a);
	}
	
}
