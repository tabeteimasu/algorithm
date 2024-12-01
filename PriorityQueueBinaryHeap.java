public class PriorityQueue
{
	private String[] pq;
	private int N;
		
	public PriorityQueue(int capacity)
	{
		pq = new String[capacity+1];
	}

	public boolean isEmpty()
	{
		return N==0;
	}

	private boolean less(int i,int j)
	{
		return pq[i].compareTo(pq[j])<0;
	}


	private void exch(int i, int j)
	{
		String temp = pq[i];
		pq[i] = pq[j];
		pq[j] = temp;
	}

	private void swim(int k)
	{
		while(k>1&&less(k/2,k))
		{
			exch(k,k/2);
			k=k/2;
		}
	}

	public void insert(String x)
	{
		pq[++N] = x;
		swim(N);
	}

	private void sink(int k)
	{
		while(2*k<=N)
		{
			int j=2*k;
			if(j<N&&less(j,j+1)) j++;
			if(!less(k,j)) break;
			exch(k,j);
			k=j;
		}
	}

	public String delMax()
	{
		String max = pq[1];
		exch(1,N--);
		sink(1);
		pq[N+1] = null;
		return max;
	}

	public int generateRandNum()
	{
		java.util.Random rand = new java.util.Random();
		return rand.nextInt(N)+1;
	}
	public String sample()
	{
		int randNum = generateRandNum();
		return pq[randNum];
	}

	public String delRandom()
	{
		int randNum = generateRandNum();
		String random = pq[randNum];
		exch(randNum,N--);
		sink(randNum);
		pq[N+1] = null;
		return random;
	}

	public static void main(String[] args)
	{
		PriorityQueue pq = new PriorityQueue(11);
		pq.insert("t");
		pq.insert("p");
		pq.insert("r");
		pq.insert("n");
		pq.insert("h");
		pq.insert("o");
		pq.insert("a");
		pq.insert("e");
		pq.insert("i");
		pq.insert("g");
		pq.insert("s");
		System.out.println(java.util.Arrays.toString(pq.pq));
		pq.delMax();	
		System.out.println(java.util.Arrays.toString(pq.pq));
		System.out.println(pq.sample());
		pq.delRandom();
		System.out.println(pq.sample());
		System.out.println(java.util.Arrays.toString(pq.pq));
	}
}
