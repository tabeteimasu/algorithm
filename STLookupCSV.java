import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;
public class LookupCSV
{
	public static void main(String[] args)
	{
		In in = new In(args[0]);
		int keyFiled = Integer.parseInt(args[1]);
		int valFiled = Integer.parseInt(args[2]);
		
		ST<String,String> st = new ST<String,String>();
		while(!in.isEmpty())
		{
			String line = in.readLine();
			String[] tokens = line.split(",");
			String key = tokens[keyFiled];
			String val = tokens[valFiled];
			st.put(key,val);
		}

		while(!StdIn.isEmpty())
		{
			String s = StdIn.readString();
			if(!s.contains(s)) StdOut.println("Not found");
			else StdOut.println(st.get(s));
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
}

