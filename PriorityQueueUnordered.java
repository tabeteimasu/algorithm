class PriorityQueue
{
	private int[] pq;
	private int N;

	public PriorityQueue(int capacity)
	{
		pq = new int[capacity];
	}

	public boolean isEmpty()
	{	return N == 0;	}

	public void insert(int x)
	{	pq[N++] = x;	}

	public int delMax()
	{
		int max = 0;
		for(int i=1;i<N;i++)
			if(max<pq[i]) max=i;
		int temp = pq[max];
		pq[max] = pq[N-1];
		pq[N-1] = temp;
		return pq[--N];
	}

	public static void main(String[] args)
	{
		PriorityQueue pq = new PriorityQueue(10);
		pq.insert(1);
		pq.insert(2);
		pq.insert(3);
		System.out.println(pq.delMax());
		System.out.println(pq.N);
		System.out.println(java.util.Arrays.toString(pq.pq));
	}
}
