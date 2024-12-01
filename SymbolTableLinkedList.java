public class SymbolTable
{
	private Node first = new Node("",0);
	private int size;

	public void insert(String key,int value)
	{
		if(isEmpty()) 
		{
			first.key = key;
			first.value = value;
			size++;
			return;
		}
		if(searchUpdate(key,value)) return;
		else
		{
			Node node = new Node(key,value);
			node.next = first;
			first = node;
			size++;
		}
			
	}

	public boolean isEmpty()
	{
		return size==0;
	}

	public boolean searchUpdate(String key,int value)
	{
		Node node = first;
		while(node!=null)
		{
			if(node.key.equals(key)==false)
				node = node.next;
			else
			{
				node.value = value;
				return true;
			}
		}
		return false;
	}

	public void print()
	{
		Node node = first;
		while(node!=null)
		{
			System.out.println(node.key + "->" + node.value);
			node = node.next;
		}
	}
	
		
	public static void main(String args[])
	{
		SymbolTable st = new SymbolTable();
		st.insert("S",0);
		st.insert("E",1);
		st.insert("E",2);
		st.insert("S",3);
		st.insert("G",4);
		st.print();
	}
}

public class Node
{
	String key;
	int value;
	Node next;
	Node(String key,int value)
	{
		this.key = key;
		this.value = value;
	}
}
