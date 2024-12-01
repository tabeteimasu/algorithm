public class QuickUnionUF{
	private int[] id;
	
	public QuickUnionUF(int N)
	{
		id = new int[N];
		for(int i=0;i<N;i++) id[i]=i;
	}

	private int root(int i)
	{
		while(i != id[i]) i = id[i]; //chase parent root until reach root
		return i;
	}

	public boolean connected(int p,int q)
	{
		return root(p) == root(q);
	}

	public void union(int p, int q)
	{
		int i = root(p);
		int j = root(q);
		id[i] = j; // make the root of p the child of root of q
	}
}
