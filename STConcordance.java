import edu.princeton.cs.algs4.In;
 import edu.princeton.cs.algs4.StdIn;
 import edu.princeton.cs.algs4.StdOut;
 import edu.princeton.cs.algs4.SET;


public class Concordance
{
	public static void main(String[] args)
	{
		In in = new In(args[0]);
		String[] words = in.readAll().split("\\s+");
		ST<String,SET<Integer>> st = new ST<String,SET<Integer>>();
		for(int i=0;i<words.length;i++)
		{
			String s = words[i];
			if(!st.contains(s))
				st.put(s,new SET<Integer>());
			SET<Integer> pages = st.get(s);
			pages.add(i);
		}

		while(!StdIn.isEmpty())
		{
			String query = StdIn.readString();
			SET<Integer> set = st.get(query);
			if(set==null) StdOut.println("no such words");
			else
			{
			for(int k:set)
			{	
				int m,upper;
				if(k-4<0) m=0;
				else m=k-4; 
				if(k+4>words.length-1) upper = words.length-1;
				else upper = k+4;
				for(;m<=upper;m++)
					StdOut.print(words[m]+" ");		
			}
			}
		}
	}
}

public class ST<Key,Value>
{
	private int M = 30001;
	private Value[] vals = (Value[]) new Object[M];
	private Key[]	keys = (Key[]) 	 new Object[M];

	private int hash(Key key)
	{
		return (key.hashCode()&0x7fffffff)%M;
	}

	public void put(Key key,Value val)
	{
		int i;
		for(i=hash(key);keys[i]!=null;i=(i+1)%M)
			if(keys[i].equals(key)) break;
		keys[i] = key;
		vals[i] = val;
	}

	public Value get(Key key)
	{
		for(int i = hash(key);keys[i]!=null;i=(i+1)%M)
			if(key.equals(keys[i])) 
				return vals[i];
		return null;
	}

	public boolean contains(Key key)
	{
		for(int i = hash(key);keys[i]!=null;i=(i+1)%M)
			if(key.equals(keys[i])) 
				return true;
		return false;
	}
}
