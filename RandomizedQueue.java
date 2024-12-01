import java.util.Iterator;
//import java.util.Random;
import edu.princeton.cs.algs4.StdRandom;
import java.util.NoSuchElementException;
public class RandomizedQueue<Item> implements Iterable<Item>
{
	private Node<Item> last;
	private int size;
	private Node<Item> first;
//	private Random random;

	private class Node<Item>
	{
		Item item;
		Node<Item> next;
		Node<Item> prev;
	}

	public RandomizedQueue()
	{
		last = null;
		first = null;
		size = 0;
//		random = new Random();
	}

	public boolean isEmpty()
	{
		return size==0;	
	}

	public int size()
	{
		return size;
	}

	public void enqueue(Item item)
	{
		if(item==null) throw new IllegalArgumentException();
		Node<Item> oldLast = last;
		last = new Node<>();
		last.item = item;
		if(size==0) first = last;
		else {oldLast.next = last; last.prev = oldLast;}
		size++;
	}

	public Item dequeue()
	{
		if(size==0) throw new NoSuchElementException();
		//int randomIndex = random.nextInt(size);
		int randomIndex = StdRandom.uniformInt(size);
		Node<Item> current = first;
		Item item = null;
		for(int k=0;k<size;k++)
		{	
			if(k==randomIndex)
			{
				item = current.item;
				break;
			}
			current = current.next;
		}
		if(size==1){first=null;last=null;}
		else if(current==last)
			{last = current.prev;current.prev.next = null;current.prev=null;}
		else if(current==first)
			{first = current.next;current.next.prev = null;current.next=null;}
		else{current.prev.next = current.next;
		current.next.prev = current.prev;}
//		current = null;
		size--;
		return item;
	}

	public Item sample()
	{
		if(size==0) throw new NoSuchElementException();
		//int randomIndex = random.nextInt(size);
		int randomIndex = StdRandom.uniformInt(size);
		Node<Item> current = first;
		Item item = null;
		for(int k=0;k<size;k++)
		{	
			if(k==randomIndex)
			{
				item = current.item;
				break;
			}
			current = current.next;
		}
		return item;	
	}

	public Iterator<Item> iterator()
	{
		return new ListIterator();
	}

	private class ListIterator implements Iterator<Item>
	{	
		private Node<Item> current = first;
		public boolean hasNext(){return current!=null;}
		public Item next()
		{	
			if(current==null) throw new NoSuchElementException();
			Item item = current.item;
			current = current.next;
			return item;
		}	
		public void remove()
		{	throw new UnsupportedOperationException();	}
	}

	public static void main(String[] args)
	{
		RandomizedQueue<Object> q = new RandomizedQueue<>();
		q.enqueue("one");
		q.enqueue("two");
		System.out.println(q.isEmpty());
		System.out.println(q.size);
		q.enqueue("three");
		System.out.println(q.dequeue());
		System.out.println(q.sample());
		q.enqueue("four");
		System.out.println(q.dequeue());
		System.out.println(q.dequeue());
		System.out.println(q.dequeue());
		System.out.println("----------");
		Iterator<Object> i = q.iterator();
		while(i.hasNext())
		{
			Object item = i.next();
			System.out.println(item);
		}
	}
}
