import java.io.File;
import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.SET;

public class FileIndex
{
	public static void main(String[] args)
	{
		ST<String,SET<File>> st = new ST<String,SET<File>>();
		
		for(String filename:args)
		{
			File file = new File(filename);
			In in = new In(file);
			while(!in.isEmpty())
			{
				String key = in.readString();
				if(!st.contains(key))
					st.put(key,new SET<File>());
				SET<File> set = st.get(key);
				set.add(file);
			}
		}

		while(!StdIn.isEmpty())
		{
			String query = StdIn.readString();
			StdOut.println(st.get(query));
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
