public class HashTable<Key,Value>
{
	private int M=7;
	private Node[] st = new Node[M];

	private static class Node
	{
		private Object key;
		private Object val;
		private Node next;
		private int hash;
		Node(Object key,Object val,Node next)
		{
			this.key = key;
			this.val = val;
			this.next = next;
			this.hash = (key.hashCode()&0x7fffffff)%7;
		}
		void print()
		{
			Node current = this;
			while(current!=null)
			{
				System.out.println(current.hash+":"+current.key+" "+current.val);
				current = current.next;
			}
		}
	}

	private int hash(Key key)
	{return (key.hashCode()&0x7fffffff)%M;}

	public Value get(Key key)
	{
		int i = hash(key);
		for(Node x=st[i];x!=null;x=x.next)
			if(key.equals(x.key)) return (Value)x.val;
		return null;
	}

	public void put(Key key,Value val)
	{
		int i = hash(key);
		for(Node x=st[i];x!=null;x=x.next)
			if(key.equals(x.key)) {x.val=val;return;}
		st[i] = new Node(key,val,st[i]);
	}

	public static void main(String[] args)
	{
		HashTable<String,Integer> ht = new HashTable<>();
		ht.put("S",0);
		ht.put("E",1);
		ht.put("A",2);
		ht.put("S",3);
		ht.put("R",4);
		ht.put("C",5);
		ht.put("H",6);
		System.out.println(ht.get("R"));
		System.out.println(ht.get("S"));
		for(Node k:ht.st)
		{
			if(k==null) continue;
			else k.print();
		}
	}
}
