import java.util.Iterator;
class stack implements Iterable<Integer>{
	private class Node{
		int item;
		Node next;
	}
	private Node first = null;
	private int size = 0;
	private int max = Integer.MIN_VALUE;
	boolean isEmpty(){
		return first == null;
	}
	int getSize(){
		return size;
	}
	void push(int item){
			Node oldfirst = first;
			first = new Node();
			first.item = item;
			if(item>max) max=item;
			first.next = oldfirst;
			size++;
	}

	int getMax(){
		return max;
	}
	
	int pop(){
		int item = first.item;
		first = first.next;
		size--;
		return item;
		
	}

	public Iterator<Integer> iterator(){
		return new ListIterator();
	}

	private class ListIterator implements Iterator<Integer>{
		private Node current = first;
		public boolean hasNext()	{return current!=null;}
		public Integer next(){
			int item = current.item;
			current = current.next;
			return item;
		}
	}
	
	
	public static void main(String args[]){
		stack s = new stack();
		System.out.println(s.isEmpty());
		s.push(1);
		s.push(2);
		s.push(3);
		System.out.println(s.getSize());
		System.out.println(s.pop());
		System.out.println(s.getSize());
		System.out.println(s.isEmpty());
		for(int item:s)
		{
			System.out.println(item);
		}
		s.push(100);
		System.out.println(s.getMax());
	}
	
}
