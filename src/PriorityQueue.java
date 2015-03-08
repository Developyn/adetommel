/*
 * Class represents priority queue
 * Inspired by ideas described by Charlie Street in last weeks help-session - 04.03.15
 */

public class PriorityQueue<A, B extends Comparable<B>> implements DataStructure<A>
{
	
	private Function<A, B> fvalue; // f value gcost + heuristic
	private IList<A> priorityQueue;

	public PriorityQueue(Function<A, B> fvalue)
	{
		this.priorityQueue = new Nil<A>();
		this.fvalue = fvalue;		
	}
	
	
	@Override
	public void insertItem(A e) {
		
		//Set itemPosFound when the priority queue is empty and inserting it's first item or when
		//e is greater than the head of the temporaryPriority
		boolean itemPosFound = false;
		IList<A> temporaryPriority = this.priorityQueue;//Creates a temporary list
		IList<A> listBeforePosition = new Nil<A>();//A list used to store items which are less than the value of e
		
		while(!itemPosFound)
		{
			if(temporaryPriority.isEmpty())
			{
				itemPosFound = true;
				this.priorityQueue = priorityQueue.append(e);
			}
			//Compare the value of e to the value of the head of the temporaryPriority...
			//If the value is smaller, move the head into the listBeforePosition
			//Remove the head from the temporaryPriority by setting it to equal its tail
			else if(fvalue.apply(e).compareTo(fvalue.apply(temporaryPriority.head()))<=0)
			{
				listBeforePosition = listBeforePosition.append(temporaryPriority.head());
				temporaryPriority = temporaryPriority.tail();
			}
			//If the value of e is great than that of our temporaryPriority's head then
			//set itemFound to true and append listBeforePosition and the temporaryPriority list
			else if(fvalue.apply(e).compareTo(fvalue.apply(temporaryPriority.head()))>0)
			{
				itemPosFound = true;
				this.priorityQueue = listBeforePosition.append(new Cons<A>(e, temporaryPriority));
			}
		}
	}

	@Override
	public void insertList(IList<A> listToAdd) {
		while(!listToAdd.isEmpty())
		{
			this.insertItem(listToAdd.head());
			listToAdd = listToAdd.tail();
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