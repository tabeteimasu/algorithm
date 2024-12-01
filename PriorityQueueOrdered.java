import java.util.Arrays;
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
	{	pq[N++] = x;ElementarySort.insertionSort(pq,0,N-1);}

	public int delMax()
	{
		return pq[--N];
	}

	public static void main(String[] args)
	{
		PriorityQueue pq = new PriorityQueue(10);
		pq.insert(1);
		pq.insert(2);
		pq.insert(3);
		System.out.println(pq.delMax());
		pq.insert(4);
		System.out.println(pq.N);
		System.out.println(java.util.Arrays.toString(pq.pq));
	}
}
