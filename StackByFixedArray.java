import java.util.Iterator;
public class FixedCapacityStackOfStrings implements Iterable<String>
{
	private String[] s;
	private int N = 0;
	
	public FixedCapacityStackOfStrings(int capacity)
	{	s=new String[capacity];		}

	public boolean isEmpty()
	{	return N==0;	}
	
	public void push(String item)
	{	s[N++]=item;	}
	
	//loitering
	//public String pop()
	//{	return s[--N];	}

	public String pop()
	{
		String item = s[--N];
		s[N] = null;
		return item;
	}

	public Iterator<String> iterator()
	{return new ReverseArrayIterator();}

	private class ReverseArrayIterator implements Iterator<String>
	{
		private int i = N;
		public boolean hasNext() {return i>0;}
		public String next() {return s[--i];}
	}

	public static void main(String args[])
	{
		FixedCapacityStackOfStrings stack = new FixedCapacityStackOfStrings(10);
		stack.push("one");
		stack.push("two");
		stack.pop();
		stack.push("three");
		for(String item:stack){
			System.out.println(item);
		}
		Iterator<String> i = stack.iterator();
		while(i.hasNext()){
			String item = i.next();
			System.out.println(item);
		}
	}
}
