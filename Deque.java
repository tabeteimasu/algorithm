import java.util.Iterator;
import java.util.NoSuchElementException;
public class Deque<Item> implements Iterable<Item>
{
	private Node<Item> first;
	private Node<Item> last;
	private int size;
	
	private class Node<Item>
	{
		private Item item;
		private Node<Item> next;
	}
	
	public Deque()
	{
		first = null;
		last = null;
		size = 0;
	}

	public boolean isEmpty()
	{
		return size == 0;
	}

	public int size()
	{
		return size;
	}

	public void addFirst(Item item)
	{
		if(item==null) throw new IllegalArgumentException();
		Node<Item> oldFirst = first;
		first = new Node<>();
		first.item = item;
		first.next = oldFirst;
		if(this.isEmpty()) last = first;
		size++;
	}

	public void addLast(Item item)
	{
		if(item==null) throw new IllegalArgumentException();
		Node<Item> oldLast = last;
		last = new Node<>();
		last.item = item;
		if(this.isEmpty()) first = last;
		else {oldLast.next = last;}
		size++;
	}

	public Item removeFirst()
	{
		if(this.isEmpty()) throw new NoSuchElementException();
		Item item = first.item;
		first = first.next;
		size--;
		return item;
	}

	public Item removeLast()
	{
		if(this.isEmpty()) throw new NoSuchElementException();
		Item item = last.item;
		if(size==1) {first = null;last = null;}
		else{
		Node<Item> current = first;
		while(current.next!=null&&current.next!=last)
			current = current.next;
		last = current;
		last.next = null;
		}
		size--;
		return item;
	}

	public Iterator<Item> iterator()
	{
		return new ListIterator();
	}

	private class ListIterator implements Iterator<Item>
	{
		private Node<Item> current = first;
		public boolean hasNext()
		{	return current!=null;	}
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

	public static void main(String args[])
	{
		Deque<Object> d = new Deque<>();
		System.out.println(d.isEmpty());
		System.out.println(d.size());
		d.addLast(4);
		d.addFirst(1);
		d.addFirst(2);
		d.addLast(3);
		System.out.print(d.removeFirst());
		System.out.print(d.removeFirst());
		System.out.print(d.removeLast());
		System.out.print(d.removeFirst());
		System.out.println("--iterating--");
		Iterator<Object> i = d.iterator();
		while(i.hasNext())
		{
			Object item = i.next();
			System.out.println(item);
		}
	}
}
