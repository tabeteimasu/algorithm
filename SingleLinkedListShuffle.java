import java.util.Random;
public class SingleLinkedListShuffle
{
	private Node first;
	private Node last;
	private int size;
	private Random random = new Random();
	
	private class Node
	{
		private String value;
		private Node next;
	}

	public boolean isEmpty()
	{
		return size == 0;
	}

	public void add(String value)
	{
		Node oldLast = last;
		last = new Node();
		last.value = value;
		if(isEmpty()) first = last;
		else oldLast.next = last;
		size++;
	}

	public Node getMiddle(Node head)
	{
		Node oneStep = head;
		Node twoStep = head.next;
		while(twoStep!=null&&twoStep.next!=null)
		{
			oneStep = oneStep.next;
			twoStep = twoStep.next.next;
		}
		return oneStep;
	}

	public void shuffle()
	{
		Node shuffled = sort(first);
		while(shuffled!=null)
		{
			System.out.println(shuffled.value);
			shuffled = shuffled.next;
		}
	}

	private Node sort(Node head)
	{
		if(head==null||head.next==null) return head;
		Node middle = getMiddle(head);
		Node nextOfMiddle = middle.next;
		middle.next = null;
		Node left = sort(head);
		Node right = sort(nextOfMiddle);	
		return merge(left,right);
	}

	private  Node merge(Node left,Node right)
	{
		Node result = null;
		if(left==null) return right;
		if(right==null) return left;
		if(random.nextBoolean())
		{
			result = left;
			result.next = merge(left.next,right);
		}
		else
		{
			result = right;
			result.next = merge(left,right.next);
		}
		return result;
	}

	public static void main(String args[])
	{
		SingleLinkedListShuffle list = new SingleLinkedListShuffle();
		list.add("one");
		list.add("two");
		list.add("three");
		list.add("four");
		list.add("five");
		list.shuffle();		
	}
}
