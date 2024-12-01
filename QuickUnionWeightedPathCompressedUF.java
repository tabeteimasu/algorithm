public class QuickUnionWeightedPathCompressedUF{
	private int[] id;
	private int[] sz;	
	public QuickUnionWeightedPathCompressedUF(int N)
	{
		id = new int[N];
		sz = new int[N];
		for(int i=0;i<N;i++)
		{
			id[i]=i;
			sz[i]=1;
		}
	}

	private int root(int i)
	{
		while(i != id[i])
		{
			id[i] = id[id[i]]; //make every other node in path point to its grandparent
			i = id[i]; //chase parent until reach root
		}
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
		if(i==j) return;
		if(sz[i]<sz[j])
		{
			id[i] = j; // make the root of p the child of root of q
			sz[j]+=sz[i];
		}
		else
		{
			id[j] = i;
			sz[i]+=sz[j];
		}
	}
}
