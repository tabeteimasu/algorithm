import java.util.Stack;
public class Queue<Item>
{
	private Stack<Item> stack1,stack2;
	public Queue()
	{
		stack1 = new Stack<>();
		stack2 = new Stack<>();
	}

	public void enqueue(Item item)
	{
		stack1.push(item);
	}

	public Item dequeue()
	{	
		if(stack2.isEmpty())
			while(!stack1.isEmpty()) stack2.push(stack1.pop());
		return stack2.pop();
	}

	public static void main(String args[])
	{
		Queue<String> q = new Queue<>();
		q.enqueue("one");
		q.enqueue("two");
		q.enqueue("three");
		q.dequeue();
		q.enqueue("four");
		System.out.print(q.dequeue());
		
		Queue<Object> qq = new Queue<>();
		qq.enqueue("String1");
		qq.enqueue(1);
		System.out.println(qq.dequeue());
	}
}
