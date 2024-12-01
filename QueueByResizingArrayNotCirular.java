public class ResizingArrayQueueOfStrings
{	
	private String[] q;
	private int tail;
	private int head;
	private int size;
	public ResizingArrayQueueOfStrings(){
		q = new String[1];
		head = 0;
		tail = 0;
		size = 0;	
	}
	
	public void enqueue(String item)
	{
		if(tail-head==q.length) resize(2*q.length);
		q[tail++] = item;
		size++;
	}

	public String dequeue()
	{	
		String item = q[head];
		q[head++] = null;
		size--;
		if(tail-head>0&&tail-head==q.length/4) resize(q.length/2);
		return item;
	}

	private void resize(int capacity)
	{
		String[] copy = new String[capacity];
		for(int i=0;i<tail-head;i++)
			copy[i] = q[head+i];
		q = copy;
		head = 0;
		tail = size;
	}

	public static void main(String args[])
	{
		ResizingArrayQueueOfStrings queue = new ResizingArrayQueueOfStrings();
		queue.enqueue("t");
		queue.enqueue("h");
		queue.enqueue("e");
		queue.enqueue("cod");
		System.out.print(queue.dequeue());
		System.out.print(queue.dequeue());
		System.out.print(queue.dequeue());
		System.out.println(queue.size);
	}

}
