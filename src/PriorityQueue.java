/*
 * Class represents priority queue
 * @Helped and advised creation by Charlie Street
 */

public class PriorityQueue<A, B extends Comparable<B>> implements DataStructure<A>
{
	
	private Function<A, B> heuristic; // f value g + h
	private IList<A> priorityQueue;

	public PriorityQueue(Function<A, B> heuristic)
	{
		this.priorityQueue = new Nil<A>();
		this.heuristic = heuristic;		
	}
	
	//Big Charles says - priority queue sorts the items in the list for you. From smaller to greater
	@Override
	public void insertItem(A e) {
		
		boolean itemFound = false; //Used to check if the correct position is found
		IList<A> temporaryPriority = this.priorityQueue;//Temporary list
		IList<A> listBeforePosition = new Nil<A>();//Creates a list of items before the found position
		
		while(!itemFound)
		{
			if(temporaryPriority.isEmpty())
			{
				itemFound = true;
				this.priorityQueue = priorityQueue.append(e);
			}
			else if(heuristic.apply(e).compareTo(heuristic.apply(temporaryPriority.head()))<=0)
			{
				listBeforePosition = listBeforePosition.append(temporaryPriority.head());
				temporaryPriority = temporaryPriority.tail();
			}
			else if(heuristic.apply(e).compareTo(heuristic.apply(temporaryPriority.head()))>0)
			{
				itemFound = true;
				this.priorityQueue = listBeforePosition.append(new Cons<A>(e, temporaryPriority));
			}
		}
	}

	//write another function which in the apply method for that function - returns the sum of the manhattan apply and the GCost apply

	@Override
	public void insertList(IList<A> toAdd) {
		while(!toAdd.isEmpty())
		{
			this.insertItem(toAdd.head());
			toAdd = toAdd.tail();
		}
	}

	@Override
	public void removeHead() {
		this.priorityQueue = this.priorityQueue.tail();
	}		

	@Override
	public Maybe<A> getHead() {
		if(this.priorityQueue.isEmpty())
		{
			return new Nothing<A>();
		}
		else
		{
			return new Just<A>(this.priorityQueue.head());
		}
	}

	@Override
	public boolean isEmpty() {
		return this.priorityQueue.isEmpty();
	}

	@Override
	public boolean checkForDuplicates(A a) {
		return this.priorityQueue.has(a);
	}

	@Override
	public boolean contains(A a) {
		return this.priorityQueue.has(a);
	}
	
}