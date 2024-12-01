class stack{
	private class Node{
		String item;
		Node next;
	}
	private Node first = null;
	private Node last = null;
	private int size = 0;
	boolean isEmpty(){
		return first == null;
	}
	int getSize(){
		return size;
	}
	/*
	void enqueue(String item){
		if(first==null)
		{
			first = new Node();
			first.item = item;
			last = first;
			size++;
		}else{
			Node oldlast = last;
			last = new Node();
			last.item=item;
			oldlast.next = last; 
			size++;
		}

	}
	*/

	void enqueue(String item){
		Node oldlast = last;
		last = new Node();
		last.item=item;
		last.next=null;
		if(isEmpty()) first = last;
		else oldlast.next = last;
		size++;
	}
	
	String dequeue(){
		if(first==null) return null;
		String item = first.item;
		first = first.next;
		size--;
		if(isEmpty()) last = null;
		return item;
		
	}
	
	public static void main(String args[]){
		stack s = new stack();
		s.dequeue();
		System.out.println(s.isEmpty());
		s.enqueue("one");
		s.enqueue("two");
		s.enqueue("three");
		System.out.println(s.getSize());
		System.out.println(s.dequeue());
		System.out.println(s.getSize());
		System.out.println(s.isEmpty());
	}
	
}
