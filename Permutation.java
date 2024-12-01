import edu.princeton.cs.algs4.StdIn;
public class Permutation
{
	public static void main(String[] args)
	{
		RandomizedQueue<Object> q = new RandomizedQueue<>();
		while(!StdIn.isEmpty())		
			q.enqueue(StdIn.readString());
		int size = Integer.parseInt(args[0]);
		for(int k=0;k<size;k++)
			System.out.println((String) q.sample());
	}
}
