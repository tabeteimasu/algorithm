import java.util.Iterator;
class stack implements Iterable<String>{
	private class Node{
		String item;
		Node next;
	}
	private Node first = null;
	private int size = 0;
	boolean isEmpty(){
		return first == null;
	}
	int getSize(){
		return size;
	}
	void push(String item){
			Node oldfirst = first;
			first = new Node();
			first.item = item;
			first.next = oldfirst;
			size++;
	}
	
	String pop(){
		String item = first.item;
		first = first.next;
		size--;
		return item;
		
	}

	public Iterator<String> iterator(){
		return new ListIterator();
	}

	private class ListIterator implements Iterator<String>{
		private Node current = first;
		public boolean hasNext()	{return current!=null;}
		public String next(){
			String item = current.item;
			current = current.next;
			return item;
		}
	}
	
	
	public static void main(String args[]){
		stack s = new stack();
		System.out.println(s.isEmpty());
		s.push("one");
		s.push("two");
		s.push("three");
		System.out.println(s.getSize());
		System.out.println(s.pop());
		System.out.println(s.getSize());
		System.out.println(s.isEmpty());
		for(String item:s)
		{
			System.out.println(item);
		}
	}
	
}
